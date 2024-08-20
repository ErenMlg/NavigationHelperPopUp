package com.softcross.atry

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.softcross.atry.databinding.FragmentThirdBinding

@SuppressLint("RestrictedApi")
class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.page3Button2.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.page3Button1.setOnClickListener {
            findNavController().navigate(R.id.third_to_fourth)
        }
        var text = "Back routes: "
        val backRoutes = getRoutes()
        backRoutes.forEach { route ->
            Log.e("Route", route.title)
            text += route.title
        }
        binding.pageInfoTxt3.text = text
        binding.pageInfoTxt3.setOnClickListener {
            showDialogWithButtons(requireContext() ,backRoutes, findNavController())
        }
    }

}