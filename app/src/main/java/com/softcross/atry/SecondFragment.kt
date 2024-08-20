package com.softcross.atry

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.softcross.atry.databinding.FragmentSecondBinding
import com.softcross.atry.databinding.FragmentThirdBinding

@SuppressLint("RestrictedApi")
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.page2Button1.setOnClickListener {
            findNavController().navigate(R.id.secont_to_third)
        }
        binding.page2Button2.setOnClickListener {
            findNavController().popBackStack()
        }
        var text = "Back routes: "
        val backRoutes = getRoutes()
        backRoutes.forEach { route ->
            Log.e("Route", route.title)
            text += route.title
        }
        binding.pageInfoTxt2.text = text
        binding.pageInfoTxt2.setOnClickListener {
            showDialogWithButtons(requireContext() ,backRoutes)
        }
    }

}