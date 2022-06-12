package com.ozontech.feature_pdp_impl.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PdpViewModelFactory @AssistedInject constructor(
    private val interactor: PdpInteractor,
    @Assisted("guid") private val guid: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == PdpViewModel::class.java)
        return PdpViewModel(interactor, guid) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("guid") guid: String): PdpViewModelFactory
    }
}
