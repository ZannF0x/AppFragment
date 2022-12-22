package com.dev.zann.appfragment.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.dev.zann.appfragment.UserInfo
import com.dev.zann.appfragment.databinding.ActivityMainBinding
import com.dev.zann.appfragment.home.HomeFragment
import com.dev.zann.appfragment.infoclient.InfoClientFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_HOME_FRAGMENT = "TAG_HOME_FRAGMENT"
        const val TAG_INFO_CLIENT_FRAGMENT = "TAG_INFO_CLIENT_FRAGMENT"
        const val USER_KEY = "USER_KEY"
    }

    private lateinit var binding: ActivityMainBinding
    private var instanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instanceState = savedInstanceState
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.buttonOpenHome.setOnClickListener {
            openHomeFragment()
        }
        binding.buttonRemoveAll.setOnClickListener {
            clearAllFragments()
        }
    }

    private fun openHomeFragment() {
        if (instanceState != null) return
        if (supportFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT) != null) return
        val infoBundle = bundleOf(
            USER_KEY to UserInfo(
                id = "A45Cf098",
                name = "Zannardy Andrade Oliveira",
                age = 20,
                work = "Desenvolvedor Android Júnior"
            )
        )
        supportFragmentManager.commit {
            this.setReorderingAllowed(true)
            this.replace<HomeFragment>(
                binding.fragmentContainerView.id,
                tag = TAG_HOME_FRAGMENT,
                args = infoBundle
            )
            this.addToBackStack(TAG_HOME_FRAGMENT)
        }
    }

    private fun clearAllFragments() {
        supportFragmentManager.commit {
            supportFragmentManager.findFragmentByTag(TAG_INFO_CLIENT_FRAGMENT)?.let {
                this.remove(it)
                supportFragmentManager.popBackStack()
            }
            supportFragmentManager.findFragmentByTag(TAG_HOME_FRAGMENT)?.let {
                this.remove(it)
                supportFragmentManager.popBackStack()
            }
        }
    }
}