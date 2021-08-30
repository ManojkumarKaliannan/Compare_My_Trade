package com.app.compare_my_trade.ui.postauthenticationui.ui.moreoptions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.adapter.BaseRecyclerViewAdapter
import com.app.compare_my_trade.adapter.OnDataBindCallback
import com.app.compare_my_trade.adapter.decoration.MarginItemDecoration
import com.app.compare_my_trade.adapter.decoration.MarginMoreItemDecoration
import com.app.compare_my_trade.databinding.FragmentHomeBinding
import com.app.compare_my_trade.databinding.FragmentMoreOptionsBinding
import com.app.compare_my_trade.databinding.ItemMoreBinding
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.INetworkConnection
import com.app.compare_my_trade.ui.postauthenticationui.ui.home.HomeViewModel
import com.app.compare_my_trade.utills.Singleton.addItemDecorationWithoutLastDivider
import org.koin.android.ext.android.inject

class MoreOptionsFragment : BaseFragment<FragmentMoreOptionsBinding, MoreOptionsViewModel>(),
    INetworkConnection {

    private val moreOptionsViewModel: MoreOptionsViewModel by inject()
    private var _binding: FragmentMoreOptionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return if (viewDataBinding?.root == null) {
            super.onCreateView(inflater, container, savedInstanceState)

            observeChanges()
            viewDataBinding?.root
        } else {
            viewDataBinding?.root
        }
    }

    private fun observeChanges() {
        viewDataBinding?.sivEditProfile?.setOnClickListener {
            toastUnderDevelopment()
        }
        val listMoreItemsAdapter = BaseRecyclerViewAdapter(
            R.layout.item_more,
            BR.moreItem,
            moreOptionsViewModel.menuItems.toMutableList(),
            null,
            object : OnDataBindCallback<ItemMoreBinding> {
                override fun onItemClick(view: View, position: Int, v: ItemMoreBinding) {
                    toastUnderDevelopment()
                }

                override fun onDataBind(v: ItemMoreBinding, onClickListener: View.OnClickListener) {

                }

            }
        )
        viewDataBinding?.rvMoreItems?.addItemDecoration(
            MarginItemDecoration(
                viewDataBinding?.rvMoreItems?.context?.resources?.getDimension(
                    R.dimen.trading_items_margin
                )?.toInt()
            )
        )
        viewDataBinding?.rvMoreItems?.apply {
            setHasFixedSize(true)
            adapter = listMoreItemsAdapter
            addItemDecorationWithoutLastDivider()
            addItemDecoration(
                MarginMoreItemDecoration(
                    resources.getDimension(R.dimen.moreItemsPadding)
                        .toInt()
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val bindingVariable: Int
        get() = BR.moreOptionsViewModel
    override val layoutId: Int
        get() = R.layout.fragment_more_options
    override val viewModel: MoreOptionsViewModel
        get() = moreOptionsViewModel

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        //logic can be added
    }
}