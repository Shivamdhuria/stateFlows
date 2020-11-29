package com.example.stateflow.backoff

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import com.example.stateflow.backoff.data.ResultWrapper
import kotlinx.android.synthetic.main.activity_back_off.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.LazyThreadSafetyMode.NONE

class BackOffActivity : AppCompatActivity() {

    internal companion object {

        operator fun invoke(context: Context) = Intent(context, BackOffActivity::class.java)
    }

    private val adapter by lazy(NONE) { RecyclerAdapter() }
    private lateinit var viewModel: BackOffVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_off)
        viewModel = ViewModelProvider(this).get(BackOffVM::class.java)
        collectFlows()
        recycler.adapter = adapter
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewModel.liveDateFetch.collect {
                when (it) {
                    is ResultWrapper.NetworkError -> {
                        errorText.append("${it.errorMessage} \n")
                        errorText.visibility = VISIBLE
                        errorImage.visibility = VISIBLE
                        view_timer.visibility = VISIBLE
                        it.retryTime?.let { it1 -> resetCountDown(it1) }
                            ?: run { view_timer.visibility = INVISIBLE }
                    }
                    is ResultWrapper.Success<*> -> {
                        view_timer.visibility = INVISIBLE
                        errorText.visibility = INVISIBLE
                        showLoading(false)
                        adapter.submitList(it.value as? List<String>)
                    }
                    is ResultWrapper.Loading -> {
                        view_timer.visibility = INVISIBLE
                        errorImage.visibility = INVISIBLE
                        showLoading(it.isLoading)
                    }
                }
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        progressBar.visibility = if (loading) VISIBLE else INVISIBLE
    }

    private fun resetCountDown(time: Int) {
        view_timer.isCountDown = true
        view_timer.base = SystemClock.elapsedRealtime() + time
        view_timer.start()
    }
}