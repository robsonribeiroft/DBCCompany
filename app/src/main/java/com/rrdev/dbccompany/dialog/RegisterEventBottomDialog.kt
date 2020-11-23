package com.rrdev.dbccompany.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rrdev.dbccompany.R
import kotlinx.android.synthetic.main.dialog_bottom_register_event.view.*

class RegisterEventBottomDialog: BottomSheetDialogFragment() {

    var action: (name: String, email: String)->Unit = { _, _ -> }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.dialog_bottom_register_event, container, false)

        view.button_confirm.setOnClickListener {
            action(view.editTextName.text.toString(), view.editTextEmail.text.toString())
            dismiss()
        }

        return view
    }


}