package com.app.compare_my_trade.ui.postauthenticationui.ui.managebids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.compare_my_trade.databinding.FragmentManageBidsBinding

class ManageBidsFragment : Fragment() {

    private lateinit var manageBidsViewModel: ManageBidsViewModel
    private var _binding: FragmentManageBidsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        manageBidsViewModel =
            ViewModelProvider(this).get(ManageBidsViewModel::class.java)

        _binding = FragmentManageBidsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}