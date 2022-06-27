package com.ozontech.core_navigation_impl

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ozontech.core_navigation_impl.databinding.ActivityMainBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ObsoleteCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

	private val snackBar: Snackbar by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
		Snackbar.make(
			this,
			binding.root,
			getString(R.string.lost_connection_string),
			Snackbar.LENGTH_INDEFINITE
		)
	}

	private val netWorkStatusMutableSharedFlow = MutableSharedFlow<Boolean>(
		extraBufferCapacity = 1,
		onBufferOverflow = BufferOverflow.DROP_OLDEST
	)
	private val networkStatusSharedFlow = netWorkStatusMutableSharedFlow.asSharedFlow()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		observeChannel()
		setConnectivityManager()
	}

	private fun setConnectivityManager() {
		val connectivityManager: ConnectivityManager =
			getSystemService(ConnectivityManager::class.java)
		netWorkStatusMutableSharedFlow.tryEmit(connectivityManager.activeNetwork != null)
		connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
			override fun onAvailable(network: Network) {
				netWorkStatusMutableSharedFlow.tryEmit(true)
			}
			override fun onLost(network: Network) {
				netWorkStatusMutableSharedFlow.tryEmit(false)
			}
		})
	}

	private fun observeChannel() {
		networkStatusSharedFlow.onEach {
			toggleSnackbar(it)
		}.launchIn(lifecycleScope)
	}

	private fun toggleSnackbar(isNetworkAvailable: Boolean) {
		runOnUiThread {
			if (isNetworkAvailable) {
				snackBar.dismiss()
			} else snackBar.show()
		}
	}
}