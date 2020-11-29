package com.example.stateflow.debounce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import kotlinx.android.synthetic.main.activity_debounce.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DebounceActivity : AppCompatActivity() {
    internal companion object {

        operator fun invoke(context: Context) = Intent(context, DebounceActivity::class.java)
    }

    private lateinit var viewModel: DebounceViewmodel

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debounce)
        viewModel = ViewModelProvider(this).get(DebounceViewmodel::class.java)
        search.addTextChangedListener {
            viewModel.setSearchQuery(it.toString())
        }
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.networkOperationResult.collect { value ->
                textView.append(value)
                textView.append("\n")
            }
        }
    }
}