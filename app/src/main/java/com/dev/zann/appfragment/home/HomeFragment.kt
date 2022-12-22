package com.dev.zann.appfragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.dev.zann.appfragment.R
import com.dev.zann.appfragment.UserInfo
import com.dev.zann.appfragment.databinding.FragmentHomeBinding
import com.dev.zann.appfragment.infoclient.InfoClientFragment
import com.dev.zann.appfragment.main.MainActivity

class HomeFragment : Fragment() {

    companion object {
        const val USER_TEXT_KEY = "USER_TEXT_KEY"
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userInfo: Bundle
    private var instanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfo = this.requireArguments()
        instanceState = savedInstanceState
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setFragmentResultListener(InfoClientFragment.RESULT_INFO_CLIENT) { _, bundle ->
            binding.textResult.text = bundle.getString(
                InfoClientFragment.RESULT_INFO_CLIENT,
                ""
            )
        }
        if (this::userInfo.isInitialized) {
            val userInfo = userInfo.getSerializable(MainActivity.USER_KEY) as UserInfo
            binding.userId.text = userInfo.id
            binding.userName.text = userInfo.name
            binding.userAge.text = userInfo.age.toString()
            binding.userWork.text = userInfo.work
            binding.buttonAccessMessage.setOnClickListener {
                if (instanceState != null) return@setOnClickListener
                val bundle = Bundle()
                bundle.putString(USER_TEXT_KEY, userInfo.userTextInfo())
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<InfoClientFragment>(
                        R.id.fragmentContainerView,
                        args = bundle,
                        tag = MainActivity.TAG_INFO_CLIENT_FRAGMENT
                    )
                    addToBackStack(MainActivity.TAG_INFO_CLIENT_FRAGMENT)
                }
            }
        }
    }
}