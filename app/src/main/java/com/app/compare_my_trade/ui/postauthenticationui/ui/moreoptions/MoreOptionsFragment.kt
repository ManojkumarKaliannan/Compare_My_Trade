package com.app.compare_my_trade.ui.postauthenticationui.ui.moreoptions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.compare_my_trade.databinding.FragmentMoreOptionsBinding

class MoreOptionsFragment : Fragment() {

    private lateinit var moreOptionsViewModel: MoreOptionsViewModel
    private var _binding: FragmentMoreOptionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moreOptionsViewModel =
            ViewModelProvider(this).get(MoreOptionsViewModel::class.java)

        _binding = FragmentMoreOptionsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}