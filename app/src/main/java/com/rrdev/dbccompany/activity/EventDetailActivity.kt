package com.rrdev.dbccompany.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rrdev.base_presentation.onPostValue
import com.rrdev.dbccompany.R
import com.rrdev.dbccompany.dialog.InfoDialog
import com.rrdev.dbccompany.dialog.RegisterEventBottomDialog
import com.rrdev.dbccompany.util.formatOnPattern
import com.rrdev.domain.model.Event
import com.rrdev.presentation_event.EventDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class EventDetailActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: EventDetailViewModel by viewModel()
    private lateinit var eventId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        eventId = intent.extras?.getString(EVENT_DETAIL_PARAM, null)
            ?: throw RuntimeException("Missing eventId param")

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        buttonCheckIn.setOnClickListener {
            RegisterEventBottomDialog().apply {
                action = {name, email ->
                    viewModel.registerUserInEvent(eventId, name, email)
                }
            }.show(supportFragmentManager, null)
        }

        buttonLocale.setOnClickListener {
            viewModel.currentEvent?.let {
                openMaps(it.latitude, it.longitude)
            }
        }

        viewModel.detailEvent.onPostValue(this,
            onSuccess = {
                fillView(it)
            },
            onError = {
                InfoDialog.showInfo(
                        supportFragmentManager,
                        it.message ?: "generic error",
                        positiveAction = {
                            finish()
                        }
                )
            }
        )

        viewModel.registerEvent.onPostValue(this,
            onSuccess = {
                InfoDialog.showInfo(
                    supportFragmentManager,
                    "Registrado com sucesso!",
                    R.drawable.ic_check
                )
            },
            onError = {
                InfoDialog.showInfo(supportFragmentManager, it.message ?: "generic error")
            }
        )

        viewModel.fetchEventDetail(eventId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun openMaps(latitude: String, longitude: String) {
        val gmmIntentUri = Uri.parse("google.streetview:cbll=$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun fillView(event: Event) = with(event){

        Picasso.get()
            .load(image)
            .placeholder(R.drawable.ic_event_placeholder)
            .into(imageEvent)

        textEventTitle.text = title
        textDate.text = date.formatOnPattern()
        textDescription.text = description
    }

    companion object{
        const val EVENT_DETAIL_PARAM = "EVENT_DETAIL_PARAM"
    }
}