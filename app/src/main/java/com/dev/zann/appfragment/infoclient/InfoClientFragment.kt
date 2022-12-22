package com.dev.zann.appfragment.infoclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import com.dev.zann.appfragment.R
import com.dev.zann.appfragment.databinding.FragmentInfoClientBinding
import com.dev.zann.appfragment.home.HomeFragment
import com.dev.zann.appfragment.main.MainActivity

class InfoClientFragment: Fragment() {

    companion object {
        const val RESULT_INFO_CLIENT = "RESULT_INFO_CLIENT"
    }

    private lateinit var binding: FragmentInfoClientBinding
    private lateinit var userTextInfo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userTextInfo = requireArguments().getString(
            HomeFragment.USER_TEXT_KEY,
            "Sem informações!"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (this::userTextInfo.isInitialized) {
            binding.textInfoClient.text = userTextInfo
        }
        binding.buttonSetResult.setOnClickListener {
            val bundle = bundleOf(
                RESULT_INFO_CLIENT to binding.txtResult.text.toString()
            )
            setFragmentResult(RESULT_INFO_CLIENT, bundle)
            parentFragmentManager.popBackStack()
        }
    }

}