package com.ozontech.core_utils

import androidx.fragment.app.Fragment
import com.ozontech.core_utils.di.DiComponent
import kotlin.reflect.KClass

abstract class BaseFragment<T : DiComponent>(private val component: KClass<T>) : Fragment() {

	protected val currentComponent by lazy {
		getComp(component)
	}


	override fun onDestroy() {
		super.onDestroy()
		if (isRemoving)
			releaseComp(component)
	}
}