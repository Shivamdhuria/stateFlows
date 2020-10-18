package com.example.stateflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stateflow.validation.ValidationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(ValidationActivity.invoke(this))
    }
}