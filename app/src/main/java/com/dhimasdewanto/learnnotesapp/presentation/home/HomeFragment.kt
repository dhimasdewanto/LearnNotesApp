package com.dhimasdewanto.learnnotesapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhimasdewanto.learnnotesapp.R
import com.dhimasdewanto.learnnotesapp.core.ScopeFragment
import com.dhimasdewanto.learnnotesapp.domain.entities.Note
import com.dhimasdewanto.learnnotesapp.presentation.adapters.NoteAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopeFragment(), KodeinAware, NoteAdapter.Interaction {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory by instance<HomeViewModelFactory>()

    private lateinit var viewModel: HomeViewModel

    private lateinit var recyclerAdapter: NoteAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        initRecyclerView()
        refreshData()
        bindStateToUI()
    }

    override fun onButtonDelete(position: Int, item: Note) {
        if (item.id != null) {
            deleteById(item.id)
        }
    }

    private fun deleteById(id: Int) = launch {
        viewModel.deleteById(id)
    }

    private fun initRecyclerView() {
        this.layoutManager = LinearLayoutManager(context)
        recyclerAdapter =
            NoteAdapter(this@HomeFragment)
        recycler_view_note.apply {
            layoutManager = this@HomeFragment.layoutManager
            adapter = recyclerAdapter
        }
    }

    private fun refreshData() = launch {
        viewModel.refreshData()
    }

    private fun bindStateToUI() = launch {
        viewModel.listNotes.observe(viewLifecycleOwner, Observer { listNotes ->
            recyclerAdapter.submitList(listNotes)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

}
