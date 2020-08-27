package com.chatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.judemanutd.autostarter.AutoStartPermissionHelper
import im.vector.R
import kotlinx.android.synthetic.chatapp.fragment_auto_start_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class AutoStartDialog : DialogFragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto_start_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        open.setOnClickListener(this)
        cancel.setOnClickListener(this)
        isCancelable=false
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.open -> {
                AutoStartPermissionHelper.getInstance().getAutoStartPermission(requireActivity()!!)
                dismiss()
            }
            R.id.cancel -> {
                dismiss()
            }
        }
    }
    override fun show(manager: FragmentManager, tag: String?) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }
}