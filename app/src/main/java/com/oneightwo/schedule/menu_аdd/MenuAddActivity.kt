package com.oneightwo.schedule.menu_аdd

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.dialog.AddDialog
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

    private fun initRecyclerView() {
        menu_add_rv.layoutManager = LinearLayoutManager(applicationContext)
        menu_add_rv.adapter = adapterMenu
    }

    private fun initDoneFAB() {
        done_fab.setOnClickListener {

        }
    }

    private lateinit var dialog: AlertDialog

    private fun clickedItemMenu(position: Int) {
        viewModel.position = position
        dialog = AddDialog(this, viewModel::getData, viewModel::setData, viewModel::deleteData, viewModel.position!!)
        dialog.show()
    }

    private fun initObserver() {
        with(viewModel) {
            getTime().observe(this@MenuAddActivity, Observer { })
            getTeacher().observe(this@MenuAddActivity, Observer { })
            getSubject().observe(this@MenuAddActivity, Observer { })
            getCabinet().observe(this@MenuAddActivity, Observer { })

            getTemporaryStorage().observe(this@MenuAddActivity, Observer {
                log("!!!!! $it")
                if (it.isNotEmpty()) dialog.dismiss()
                val list: MutableMap<Int, String> = itemData.toMutableMap()
                for (i in it.keys) {
                    list[i] = it[i]!!
                }
                adapterMenu.update(list.map { it.value })
            })

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_add)
        initToolbar()
        initRecyclerView()
        initDoneFAB()
        initObserver()
        adapterMenu.add(itemData.map { it.value })
    }

    companion object {

        val itemData =
            mapOf<Int, String>(
                0 to "День недели",
                1 to "Четная/Нечетная неделя",
                2 to "Начало занятия",
                3 to "Конец занятия",
                4 to "Предмет",
                5 to "Кабинет",
                6 to "Преподаватель",
                7 to "Тип занятия"
            )

    }

}
