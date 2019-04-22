package com.example.intelcom.chatapp.items

import com.xwray.groupie.databinding.BindableItem

import com.example.intelcom.chatapp.R
import com.example.intelcom.chatapp.databinding.AdRowBinding

class AdItem(private val message: String): BindableItem<AdRowBinding>(){
    private lateinit var listener: OnAdPressedListener

    override fun getLayout(): Int {
        return R.layout.ad_row
    }

    override fun bind(viewBinding: AdRowBinding, position: Int) {
        listener = viewBinding.root.context as OnAdPressedListener

        viewBinding.title.text = message
        viewBinding.ad.setOnClickListener{
            listener.onAdPressed()
        }

    }

    interface OnAdPressedListener {
        fun onAdPressed()
    }

}