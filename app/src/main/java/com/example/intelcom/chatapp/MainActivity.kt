package com.example.intelcom.chatapp

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.example.intelcom.chatapp.databinding.ActivityMainBinding
import com.example.intelcom.chatapp.items.AdItem
import com.example.intelcom.chatapp.items.MessageFromItem
import com.example.intelcom.chatapp.items.MessageToItem


class MainActivity : AppCompatActivity(), RewardedVideoAdListener, MessageToItem.OnMessageToPressedListener, AdItem.OnAdPressedListener {

    private lateinit var mRewardedVideoAd: RewardedVideoAd
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = GroupAdapter<ViewHolder>()
        initAdapter(adapter)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()

    }

    override fun onMessageToPressed() {
        Toast.makeText(this, "CALLBACK TO: $localClassName", Toast.LENGTH_SHORT).show()
    }

    override fun onAdPressed(){
        if (mRewardedVideoAd.isLoaded) {
            mRewardedVideoAd.show()
        }
    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", AdRequest.Builder().build())
    }

    private fun initAdapter(adapter: GroupAdapter<ViewHolder>) {
        adapter.add(MessageToItem("Hello! It is my message"))
        adapter.add(MessageFromItem("Hi, this is not my message"))
        adapter.add(MessageToItem("Watch this cool add!"))
        adapter.add(AdItem("Some cool ad"))
        adapter.add(MessageFromItem("This ad is cool, I want one more!"))
        adapter.add(MessageToItem("No problem"))
        adapter.add(AdItem("Another cool ad"))
        adapter.add(MessageFromItem("Thanks!"))
    }


    override fun onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
    }

    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
            Toast.LENGTH_SHORT).show()

    }

    override fun onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
    }

}

