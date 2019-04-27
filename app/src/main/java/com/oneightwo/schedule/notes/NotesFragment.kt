package com.oneightwo.schedule.notes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }

    private val adapterNotes by lazy {
        NotesAdapter()
    }

    companion object {
        fun newInstance() = NotesFragment()
    }

    private fun initToolbar() {
        log("initToolbar")
        (activity as AppCompatActivity).setSupportActionBar(menu_add_notes_tb)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        log("onCreateOptionsMenu")
        inflater.inflate(R.menu.add_app_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(context, AddNoteActivity::class.java)
        startActivity(intent)
        return true
    }



    private fun initRecyclerView() {
        notes_rv.layoutManager = LinearLayoutManager(context)
        notes_rv.adapter = adapterNotes
    }

    private fun initObserver() {
        with(viewModel) {
            getNotes().observe(this@NotesFragment, Observer {

            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initToolbar()
        initRecyclerView()
        initObserver()
    }

    override fun getLayoutId() = R.layout.fragment_notes
}