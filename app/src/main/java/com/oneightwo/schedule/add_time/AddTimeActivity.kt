package com.oneightwo.schedule.add_time

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.activity_add_time.*
import java.util.*

class AddTimeActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(AddTimeViewModel::class.java)
    }

    private val adapterTime by lazy {
        AddTimeAdapter(
            ::setStartTime,
            ::setEndTime,
            ::setNumberTime
        )
    }

    private var numberTimeDialog: NumberTimeDialog? = null

    private fun initToolbar() {
        setSupportActionBar(add_time_tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        add_time_tb.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        add_time_rv.layoutManager = LinearLayoutManager(applicationContext)
        add_time_rv.adapter = adapterTime
    }

    private fun initFAB() {
        add_fab.setOnClickListener {
            viewModel.addLine(adapterTime.size())
        }
    }

    private fun initObserver() {
        with(viewModel) {
            getTimeStorage().observe(this@AddTimeActivity, Observer {
                position = null
                log("getTimeStorage")
                for ((i, v) in it.withIndex()) {
                    log("($i) ${v.start} - ${v.end}")
                }
                adapterTime.update(it)
            })

            getTime().observe(this@AddTimeActivity, Observer {
                log("getTime")
                initBells(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.time_add_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (viewModel.isFilledAllFields()) {
            viewModel.insert()
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun setStartTime(position: Int) {
        openTimeDialog(position, viewModel::saveStartTime)
    }

    private fun setEndTime(position: Int) {
        openTimeDialog(position, viewModel::saveEndTime)
    }

    private fun openTimeDialog(position: Int, setTime: (Int, String) -> Unit) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val dialogTime =
            TimePickerDialog(this@AddTimeActivity, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->
                if (m < 10) setTime(position, "$h:0$m")
                else setTime(position, "$h:$m")
            }), hour, minute, true).show()
    }

    private fun openDialog() {
        numberTimeDialog = NumberTimeDialog(this, viewModel::saveNumberTime, viewModel::dismissDialog)
        numberTimeDialog?.show()
        viewModel.isActiveDialog = true
    }

    private fun setNumberTime(position: Int) {
        viewModel.position = position
        openDialog()
    }

    private fun initActiveDialog() {
        if (viewModel.isActiveDialog) {
            openDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_time)
        initToolbar()
        initRecyclerView()
        initFAB()
        initObserver()
        initActiveDialog()
    }


}
