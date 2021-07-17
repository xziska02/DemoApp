package com.peter.ziska.demoapp.flows.view.products.view

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.peter.ziska.demoapp.R
import com.peter.ziska.demoapp.base.view.BaseFragment
import com.peter.ziska.demoapp.flows.data.service.RestError
import com.peter.ziska.demoapp.flows.view.products.adapter.ProductPageAdapter
import com.peter.ziska.demoapp.flows.view.products.navigation.ProductsNavigator
import com.peter.ziska.demoapp.flows.view.products.presenter.ProductsViewModel
import kotlinx.android.synthetic.main.products_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject


class ProductsFragment : BaseFragment<ProductsViewModel>(R.layout.products_fragment) {

    override val viewModelClass: Class<ProductsViewModel>
        get() = ProductsViewModel::class.java

    lateinit var productAdapter: ProductPageAdapter

    @Inject
    lateinit var productsNavigator: ProductsNavigator

    override fun onInitializeView() {
        super.onInitializeView()
        setHasOptionsMenu(true)

        productAdapter = ProductPageAdapter(requireContext())
        productsNavigator.init(findNavController())

        productAdapter.onClick = {
        }

        productAdapter.onLikeClicked = {
            Timber.e("--- onlicke: $it")
            viewModel.updateProduct(it)
        }

        productAdapter.onAddToBasket = {
            Toast.makeText(
                requireContext(),
                requireContext().getText(R.string.addedToBasket),
                Toast.LENGTH_SHORT
            ).show()
        }

        recycler_view_products.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
        }

        swipe_refresh_layout_products.setOnRefreshListener {
            refreshData()
        }

        button_try_again.setOnClickListener {
            swipe_refresh_layout_products.isRefreshing = true
            refreshData()
        }
    }

    private fun refreshData() {
        productAdapter.refresh()
        button_try_again.isVisible = false
        text_view_error.isVisible = false
        viewModel.fetch()
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onSubscribe() {
        super.onSubscribe()
        lifecycleScope.launchWhenCreated {
            productAdapter.loadStateFlow.collectLatest { loadStates ->
                swipe_refresh_layout_products?.isRefreshing =
                    loadStates.refresh.isLoadStateLoading()
                text_view_error.isVisible =
                    loadStates.refresh.isLoadStateError() || loadStates.append.isLoadStateError() || (loadStates.append.isNotLoadingItems())
                button_try_again.isVisible =
                    loadStates.refresh.isLoadStateError() || loadStates.append.isLoadStateError() || (loadStates.append.isNotLoadingItems() && !loadStates.refresh.isLoadStateLoading())
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.productFlow.collectLatest { data ->
                productAdapter.submitData(data)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorReceiver.collectLatest {
                button_try_again.isVisible = true
                text_view_error.isVisible = true
                text_view_error.text = when (it) {
                    is RestError.Error -> text(R.string.connection_failure)
                    RestError.InvalidResponse -> text(R.string.invalid_response)
                    RestError.Timeout -> text(R.string.timeout)
                }
            }
        }
    }

    private fun LoadState.isLoadStateError() = this is LoadState.Error

    private fun LoadState.isLoadStateLoading() = this is LoadState.Loading

    private fun LoadState.isNotLoadingItems() =
        (this is LoadState.NotLoading && productAdapter.itemCount == 0 && this.endOfPaginationReached)

    private fun text(@StringRes textId: Int) = requireContext().getText(textId)
}