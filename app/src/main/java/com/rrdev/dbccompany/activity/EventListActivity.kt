package com.rrdev.dbccompany.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rrdev.base_presentation.onPostValue
import com.rrdev.dbccompany.R
import com.rrdev.dbccompany.adapter.EventAdapter
import com.rrdev.dbccompany.dialog.InfoDialog
import com.rrdev.presentation_event.EventListViewModel
import kotlinx.android.synthetic.main.activity_event_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class EventListActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: EventListViewModel by viewModel()
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        setupAdapter()

        swipeLayout.setOnRefreshListener {
            viewModel.fetchEvents()
        }

        viewModel.allEvents.onPostValue(this,
            onSuccess = {
                adapter.listItems = it
                stopRefresh()
            },
            onError = {
                stopRefresh()
                InfoDialog.showInfo(
                    supportFragmentManager,
                    it.message ?: "Um erro desconhecido aconteceu. Por favor, tente novamente."
                )
            },
            onLoading = {
                startRefresh()
            }
        )

        viewModel.fetchEvents()
    }

    private fun onItemSelected(eventId: String){
        val intent = Intent(this, EventDetailActivity::class.java).apply {
            putExtra(EventDetailActivity.EVENT_DETAIL_PARAM, eventId)
        }
        startActivity(intent)
    }

    private fun setupAdapter() {
        if (::adapter.isInitialized.not()){
            adapter = EventAdapter().apply {
                onItemClickListener = ::onItemSelected
            }
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = adapter
        }
    }

    private fun stopRefresh(){
        swipeLayout.isRefreshing = false
    }

    private fun startRefresh(){
        swipeLayout.isRefreshing = true
    }

}