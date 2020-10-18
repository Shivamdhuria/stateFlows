package com.example.stateflow.validation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import kotlinx.android.synthetic.main.activity_validation.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ValidationActivity : AppCompatActivity() {

    internal companion object {

        operator fun invoke(context: Context) = Intent(context, ValidationActivity::class.java)
    }

    private lateinit var viewModel: ValidationViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)
        viewModel = ViewModelProvider(this).get(ValidationViewmodel::class.java)
        initListeners()
        initObserver()
    }

    private fun initListeners() {
        editText_name.addTextChangedListener {
            viewModel.setFirstName(it.toString())
        }
        editText_password.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
        editText_user.addTextChangedListener {
            viewModel.setUserId(it.toString())
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value ->
                submit_button.isEnabled = value
            }
        }
    }
}