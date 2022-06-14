package com.ozontech.feature_add_product_impl.presentation.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.ozontech.feature_add_product_impl.domain.interactor.AddInteractor
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val interactor: AddInteractor
) : ViewModel() {

    fun addRandomProduct(v: View) {
        interactor.addRandomProduct()
    }
}
