package com.example.weather.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast.*
import androidx.fragment.app.Fragment
import com.example.weather.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.liveDataExceptionMessage.observe(requireActivity()) { event ->
            event.getContentIfNotHandled()?.let { message ->
                makeText(requireContext(), message, LENGTH_SHORT).show()
            }
        }
    }

}