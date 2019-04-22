package com.example.intelcom.chatapp.items

import com.xwray.groupie.databinding.BindableItem

import com.example.intelcom.chatapp.R
import com.example.intelcom.chatapp.databinding.MessageFromRowBinding

class MessageFromItem(private val message: String): BindableItem<MessageFromRowBinding>(){

    override fun getLayout(): Int {
        return R.layout.message_from_row
    }

    override fun bind(viewBinding: MessageFromRowBinding, position: Int) {
        viewBinding.messageText.text = message
    }
}