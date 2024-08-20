package com.softcross.atry

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.softcross.atry.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {

    private lateinit var binding: FragmentFourthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.page4Button1.setOnClickListener {
            findNavController().navigate(R.id.fourth_to_fifth)
        }

        binding.page4Button2.setOnClickListener {
            findNavController().popBackStack()
        }

        var text = "Back routes: "
        val backRoutes = getRoutes()
        backRoutes.forEach { route ->
            Log.e("Route", route.title)
            text += route.title
        }
        binding.pageInfoTxt4.text = text
        binding.pageInfoTxt4.setOnClickListener {
            showDialogWithButtons(requireContext() ,backRoutes, findNavController())
        }
    }

}