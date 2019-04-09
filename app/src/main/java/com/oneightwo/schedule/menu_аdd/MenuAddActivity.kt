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
        if (data[0] != null && data[1] != null && data[2] != null && data[3] != null && data[4] != null) {
            viewModel.insert()
            adapterMenu.update(itemData.map { it.value })
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Заполните обязательные поля", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun initRecyclerView() {
        menu_add_rv.layoutManager = LinearLayoutManager(applicationContext)
        menu_add_rv.adapter = adapterMenu
    }

    private var dialog: DialogManager? = null

    private fun clickedItemMenu(position: Int) {
        viewModel.position = position
        viewModel.isActiveDialog = true
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
                log("!!!!! $it")
                if (it.isNotEmpty() && dialog != null) dialog?.dismiss()
                val list: MutableMap<Int, String> = itemData.toMutableMap()
                for (i in it.keys) {
                    list[i] = it[i]!!
                }
                adapterMenu.update(list.map { it.value })
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
        dialog?.show()
    }


    private fun initActiveDialog() {
        if (viewModel.isActiveDialog) {
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
        adapterMenu.add(itemData.map { it.value })
    }

}
