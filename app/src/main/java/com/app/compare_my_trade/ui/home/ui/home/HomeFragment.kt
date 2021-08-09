package com.app.compare_my_trade.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.adapter.BaseRecyclerViewAdapter
import com.app.compare_my_trade.adapter.MainAdapter
import com.app.compare_my_trade.adapter.OnDataBindCallback
import com.app.compare_my_trade.adapter.decoration.MarginItemDecoration
import com.app.compare_my_trade.adapter.decoration.MarginSideItemDecoration
import com.app.compare_my_trade.databinding.BuyerItemBinding
import com.app.compare_my_trade.databinding.FragmentHomeBinding
import com.app.compare_my_trade.databinding.ItemHeaderItemsBinding
import com.app.compare_my_trade.databinding.ItemTypeDetailsBinding
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.INetworkConnection
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), INetworkConnection {

    private val homeViewModel: HomeViewModel by inject()
    var adapter: ConcatAdapter? = null
    private val listOfAdapters = mutableSetOf<RecyclerView.Adapter<out RecyclerView.ViewHolder>>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var gridView: GridView
    private var types = arrayOf("Model", "Make", "Series", "Badge", "Test2")


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun observeChanges() {
        val listItemAdapter = BaseRecyclerViewAdapter(
            R.layout.buyer_item,
            BR.itemName,
            homeViewModel.listOfCars.toMutableList(),
            null,
            object : OnDataBindCallback<BuyerItemBinding> {
                override fun onItemClick(view: View, position: Int, v: BuyerItemBinding) {
                }

                override fun onDataBind(
                    v: BuyerItemBinding,
                    onClickListener: View.OnClickListener
                ) {
//                    val mainAdapter = context?.let { MainAdapter(it, types) }
                    v.rvTypeLists.setHasFixedSize(true)
                    var manager = GridLayoutManager(
                        v.rvTypeLists.context,
                        3,
                        GridLayoutManager.VERTICAL,
                        false
                    )
                    v.rvTypeLists.layoutManager = manager
                    v.rvTypeLists.addItemDecoration(
                        MarginSideItemDecoration(
                            viewDataBinding?.rvTradingItems?.context?.resources?.getDimension(
                                R.dimen.types_items_margin
                            )?.toInt()
                        )
                    )
                    v.rvTypeLists.adapter = BaseRecyclerViewAdapter(
                        R.layout.item_type_details,
                        BR.itemTypeName,
                        types.toMutableList(),
                        null,
                        object : OnDataBindCallback<ItemTypeDetailsBinding> {
                            override fun onItemClick(
                                view: View,
                                position: Int,
                                v: ItemTypeDetailsBinding
                            ) {
                            }

                            override fun onDataBind(
                                v: ItemTypeDetailsBinding,
                                onClickListener: View.OnClickListener
                            ) {
                            }
                        })
                }

            })
        val headerAdapter = BaseRecyclerViewAdapter(
            R.layout.item_header_items,
            BR.header,
            homeViewModel.homeHeaders.toMutableList(),
            null,
            object : OnDataBindCallback<ItemHeaderItemsBinding> {
                override fun onItemClick(view: View, position: Int, v: ItemHeaderItemsBinding) {
                }

                override fun onDataBind(
                    v: ItemHeaderItemsBinding,
                    onClickListener: View.OnClickListener
                ) {
                }
            }
        )
        listOfAdapters.add(headerAdapter)
        listOfAdapters.add(listItemAdapter)
        adapter = ConcatAdapter(listOfAdapters.toList())
        viewDataBinding?.rvTradingItems?.addItemDecoration(
            MarginItemDecoration(
                viewDataBinding?.rvTradingItems?.context?.resources?.getDimension(
                    R.dimen.trading_items_margin
                )?.toInt()
            )
        )
        viewDataBinding?.rvTradingItems?.adapter = adapter
        listItemAdapter.notifyDataSetChanged()


        viewDataBinding?.sivFavorite?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_favoriteFragment)
        }
    }


    override val bindingVariable: Int
        get() = BR.homeViewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() = homeViewModel

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        //logic can be added : OPTIONAL
    }
}