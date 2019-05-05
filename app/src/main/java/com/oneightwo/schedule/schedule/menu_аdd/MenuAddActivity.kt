package com.oneightwo.schedule.schedule.menu_аdd

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.schedule.dialog_add.DialogManager
import com.oneightwo.schedule.tools.ITEM_ADD_MENU
import com.oneightwo.schedule.tools.checkError
import com.oneightwo.schedule.tools.log
import com.oneightwo.schedule.tools.setTextAndColor
import kotlinx.android.synthetic.main.activity_menu_add.*

class MenuAddActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(MenuAddViewModel::class.java)
    }

    private var dialog: DialogManager? = null

    private fun initToolbar() {
        setSupportActionBar(menu_add_tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        menu_add_tb.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val data = viewModel.getTemporaryStorage().value!!
        if (!data.isFilledMain()) {
            viewModel.isFilledMain = false
            checkError()
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.save()
            viewModel.isFilledMain = true
            if (viewModel.getIsUpdateData().value!!) {
                finish()
            }
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun initOnClick() {
        type_week_rl.setOnClickListener {
            openDialog(0)
        }

        day_of_week_rl.setOnClickListener {
            openDialog(1)
        }

        time_rl.setOnClickListener {
            openDialog(2)
        }

        subject_rl.setOnClickListener {
            openDialog(3)
        }

        cabinet_rl.setOnClickListener {
            openDialog(4)
        }

        teacher_rl.setOnClickListener {
            openDialog(5)
        }

        type_lesson_rl.setOnClickListener {
            openDialog(6)
        }

    }

    private fun initObserver() {
        with(viewModel) {
            getTime().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getTeacher().observe(this@MenuAddActivity, Observer {
                dialog?.update()
                log("DELETE TEACHER")
            })
            getSubject().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getCabinet().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })

            getTemporaryStorage().observe(this@MenuAddActivity, Observer {
                if (!isActiveDialog) {
                    dialog?.dismiss()
                }

                type_week_tv.setTextAndColor(it.get()[0], ITEM_ADD_MENU[0])
                day_of_week_tv.setTextAndColor(it.get()[1], ITEM_ADD_MENU[1])
                time_tv.setTextAndColor(it.get()[2], ITEM_ADD_MENU[2])
                subject_tv.setTextAndColor(it.get()[3], ITEM_ADD_MENU[3])
                cabinet_tv.setTextAndColor(it.get()[4], ITEM_ADD_MENU[4])
                teacher_tv.setTextAndColor(it.get()[5], ITEM_ADD_MENU[5])
                type_lesson_tv.setTextAndColor(it.get()[6], ITEM_ADD_MENU[6])

                if (!isFilledMain) {
                    checkError()
                }
            })

            getIsUpdateData().observe(this@MenuAddActivity, Observer {
                if (it) {
                    initDeleteFAB()
                }
            })

        }
    }

    private fun checkError() {
        type_week_tv.checkError(ITEM_ADD_MENU[0], error_type_week_iv)
        day_of_week_tv.checkError(ITEM_ADD_MENU[1], error_day_of_week_iv)
        time_tv.checkError(ITEM_ADD_MENU[2], error_time_iv)
        subject_tv.checkError(ITEM_ADD_MENU[3], error_subject_iv)
    }

    private fun initDeleteFAB() {
        delete_fab.show()
        delete_fab.setOnClickListener {
            viewModel.delete()
            finish()
        }
    }

    private fun openDialog(position: Int) {
        with(viewModel) {
            viewModel.position = position
            dialog =
                DialogManager(
                    this@MenuAddActivity,
                    position,
                    ::getDataString,
                    getDataTimeBell(),
                    ::setData,
                    ::deleteData,
                    {
                        viewModel.isActiveDialog = false
                    },
                    resources.configuration.orientation
                )
            dialog?.show()
            isActiveDialog = true
        }
    }


    private fun initActiveDialog() {
        if (viewModel.isActiveDialog) {
            openDialog(viewModel.position!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_add)

        val arguments = intent.extras
        val data: Schedule
        if (arguments != null) {
            data = arguments.getSerializable(Schedule::class.java.simpleName) as Schedule
            viewModel.initTemporaryStorage(data)
            initDeleteFAB()
        } else {
            viewModel.initTemporaryStorage()
        }

        initOnClick()
        initActiveDialog()
        initToolbar()
        initObserver()
    }

}
