package com.oneightwo.schedule.menu_аdd

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.dialog.DialogManager
import com.oneightwo.schedule.menu_аdd.dialog.adapter.MenuAddAdapter
import com.oneightwo.schedule.tools.ITEM_ADD_MENU
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.activity_menu_add.*

class MenuAddActivity : AppCompatActivity() {

    private val adapterMenu by lazy {
        MenuAddAdapter(
            ::clickedItemMenu
        )
    }

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
            adapterMenu.isFilledMain = false
            adapterMenu.update(data.get())
            Toast.makeText(this, "Заполните обязательные поля", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insert()
            viewModel.isFilledMain = true
            adapterMenu.isFilledMain = true
            adapterMenu.update(ITEM_ADD_MENU)
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun initRecyclerView() {
        menu_add_rv.layoutManager = LinearLayoutManager(applicationContext)
        menu_add_rv.adapter = adapterMenu
        adapterMenu.isFilledMain = viewModel.isFilledMain
    }

    private fun clickedItemMenu(position: Int) {
        viewModel.position = position
        openDialog()
    }

    private fun initObserver() {
        with(viewModel) {
            getStartTime().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getEndTime().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getTeacher().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getSubject().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })
            getCabinet().observe(this@MenuAddActivity, Observer {
                dialog?.update()
            })

            getTemporaryStorage().observe(this@MenuAddActivity, Observer {
                if (!isActiveDialog)
                    dialog?.dismiss()
                adapterMenu.update(it.get())
            })

        }
    }

    private fun openDialog() {
        dialog =
            DialogManager(
                this,
                viewModel::getData,
                viewModel::setData,
                viewModel::deleteData,
                viewModel.position!!,
                resources.configuration.orientation
            )
        viewModel.isActiveDialog = true
        dialog?.show()
    }


    private fun initActiveDialog() {
        if (viewModel.isActiveDialog) {
            log("GDE BLIAT&&&&&&&&&&&&&&")
            openDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_add)
        initActiveDialog()
        initToolbar()
        initRecyclerView()
        initObserver()
        adapterMenu.add(ITEM_ADD_MENU)
    }

}
