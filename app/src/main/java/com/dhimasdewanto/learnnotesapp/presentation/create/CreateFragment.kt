package com.dhimasdewanto.learnnotesapp.presentation.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dhimasdewanto.learnnotesapp.R
import com.dhimasdewanto.learnnotesapp.core.ScopeFragment
import com.dhimasdewanto.learnnotesapp.presentation.create.CreateViewModelFactory
import com.dhimasdewanto.learnnotesapp.presentation.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CreateFragment : ScopeFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory by instance<CreateViewModelFactory>()

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CreateViewModel::class.java)

        setOnClickAdd()
    }

    private fun setOnClickAdd() {
        btn_add.setOnClickListener {
            val title = edit_text_title.text.toString()
            val content = edit_text_content.text.toString()
            addNewNote(title, content)

            Toast.makeText(context, "Success add note!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNewNote(title: String, content: String) = launch {
        viewModel.addNote(title, content)
    }

}
