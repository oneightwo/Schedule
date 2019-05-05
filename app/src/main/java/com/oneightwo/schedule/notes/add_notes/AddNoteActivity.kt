package com.oneightwo.schedule.notes.add_notes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.notes.add_notes.dialog.DialogManager
import com.oneightwo.schedule.tools.log
import com.oneightwo.schedule.tools.toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    companion object {
        const val SUBJECT_POSITION = 1
        const val TIME_POSITION = 3
        const val NOTIFICATION_POSITION = 4
    }

    private val adapterAddNote by lazy {
        AddNoteAdapter(viewModel::setData, ::openDialog)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
    }

    private var dialog: DialogManager? = null

    private fun initToolbar() {
        setSupportActionBar(add_note_tb)
        add_note_tb.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        with(viewModel) {
            if (getTemporaryStorage().value?.isFilledMain()!!) {
                toast(this@AddNoteActivity, "Сохранено")
                insert()
            } else {
                toast(this@AddNoteActivity, "Заполните поля")
            }
        }
        return true
    }

    private fun initObserver() {
        with(viewModel) {
            getTemporaryStorage().observe(this@AddNoteActivity, Observer {
//                log("NOTES -> ${it.notification}")
                log("${it.get()}")
                adapterAddNote.update(it.get())
            })
            getSubject().observe(this@AddNoteActivity, Observer {
                log("IT -> $it")
            })
        }
    }

    private fun openDialog(position: Int) {
        log("OPEN_DIALOG")
        dialog = DialogManager(
            this,
            viewModel.getData(),
            viewModel::setTime,
            viewModel::setDate,
            position,
            resources.configuration.orientation
        )
        dialog?.show()
    }

    private fun initRecyclerView() {
        menu_add_rv.layoutManager = LinearLayoutManager(this)
        menu_add_rv.adapter = adapterAddNote
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initToolbar()
        initRecyclerView()
        initObserver()
    }
}
