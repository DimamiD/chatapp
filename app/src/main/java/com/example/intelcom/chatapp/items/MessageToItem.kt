package com.example.intelcom.chatapp.items

import com.xwray.groupie.databinding.BindableItem

import com.example.intelcom.chatapp.R
import com.example.intelcom.chatapp.databinding.MessageToRowBinding

class MessageToItem(private val message: String): BindableItem<MessageToRowBinding>(){
    private lateinit var listener: OnMessageToPressedListener

    override fun getLayout(): Int {
        return R.layout.message_to_row
    }

    override fun bind(viewBinding: MessageToRowBinding, position: Int) {
        listener = viewBinding.root.context as OnMessageToPressedListener

        viewBinding.messageText.text = message
        viewBinding.root.setOnClickListener {
            listener.onMessageToPressed()
        }
    }

    interface OnMessageToPressedListener {
        fun onMessageToPressed()
    }

}