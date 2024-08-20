package com.softcross.atry

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.softcross.atry.databinding.FragmentFifthBinding


class FifthFragment : Fragment() {

    private lateinit var binding: FragmentFifthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.page5Button.setOnClickListener {
            findNavController().popBackStack(R.id.firstFragment,false)
        }

        var text = "Back routes: "
        val backRoutes = getRoutes()
        backRoutes.forEach { route ->
            text += route.title
        }
        binding.pageInfoTxt5.text = text
        binding.pageInfoTxt5.setOnClickListener {
            showDialogWithButtons(requireContext(), backRoutes)
        }
    }


}