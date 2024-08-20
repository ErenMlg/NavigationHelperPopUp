package com.softcross.atry

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.softcross.atry.databinding.FragmentFirstBinding

@SuppressLint("RestrictedApi")
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.page1Button.setOnClickListener {
            findNavController().navigate(R.id.first_to_second)
        }
        var text = "Back routes: "
        val backRoutes = getRoutes()
        backRoutes.forEach { route ->
            Log.e("Route", route.title)
            text += route.title
        }
        binding.pageInfoTxt1.text = text
        binding.pageInfoTxt1.setOnClickListener {
            showDialogWithButtons(requireContext() ,backRoutes)
        }
    }



}