package com.rrdev.dbccompany.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.rrdev.dbccompany.R
import kotlinx.android.synthetic.main.dialog_info.view.*

class InfoDialog: DialogFragment() {

    var message: String = ""
    var imageId: Int? = null
    var positiveAction: (()->Unit)? = null
    var negativeAction: (()->Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view){

            view.materialTextView.text = message

            imageId?.let {
                view.appCompatImageView.setImageDrawable(ContextCompat.getDrawable(context, it))
            } ?: run {
                view.appCompatImageView.visibility = View.GONE
            }



            positiveAction?.let { pair ->
                view.buttonConfirm.setOnClickListener {
                    dismiss()
                    pair()
                }
            } ?: run {
                view.buttonConfirm.visibility = View.GONE
            }

            negativeAction?.let { pair ->
                view.buttonCancel.setOnClickListener {
                    dismiss()
                    pair()
                }
            } ?: run {
                view.buttonCancel.visibility = View.GONE
            }
        }
    }

    companion object{

        fun showInfo(
            fragmentManager: FragmentManager,
            messageId: String,
            imageId: Int = R.drawable.ic_error,
            positiveAction: ()->Unit = {},
            negativeAction: (()->Unit)? = null
        ) = InfoDialog().apply {
            this.message = message
            this.imageId = imageId
            this.positiveAction = positiveAction
            this.negativeAction = negativeAction
        }.show(fragmentManager, null)

        fun loading(
            message: String = "loading..."
        ) = InfoDialog().apply {
            this.message = message
        }

    }
}