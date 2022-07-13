package com.ozontech.core_navigation_impl.navigation

import androidx.fragment.app.FragmentManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FragmentManagerHost @Inject constructor() {

	lateinit var fragmentManager: FragmentManager
	private set

	fun initOrUpdateFragmentManager(fm: FragmentManager) {
		fragmentManager = fm
	}
}