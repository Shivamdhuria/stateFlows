package com.example.stateflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stateflow.backoff.BackOffActivity
import com.example.stateflow.debounce.DebounceActivity
import com.example.stateflow.validation.ValidationActivity
import com.example.stateflow.validation.ValidationActivity.Companion.invoke
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        validation.setOnClickListener {
            startActivity(ValidationActivity.invoke(this))
        }
        debounce.setOnClickListener {
            startActivity(DebounceActivity.invoke(this))
        }
        backOff.setOnClickListener {
            startActivity(BackOffActivity.invoke(this))
        }
    }
}