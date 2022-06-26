package com.ozontech.core_navigation_impl

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ozontech.core_navigation_impl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

	private val snackBar: Snackbar by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
		Snackbar.make(this, binding.root, getString(R.string.lost_connection_string), Snackbar.LENGTH_INDEFINITE)
	}

	@RequiresApi(Build.VERSION_CODES.N)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val connectivityManager: ConnectivityManager =
			getSystemService(ConnectivityManager::class.java)
		toggleSnackbar(connectivityManager.isDefaultNetworkActive)
		connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
			override fun onAvailable(network: Network) {
				toggleSnackbar(true)
			}

			override fun onLost(network: Network) {
				toggleSnackbar(false)
			}

			override fun onCapabilitiesChanged(
				network: Network,
				networkCapabilities: NetworkCapabilities
			) {
				Log.e("!!!!", "The default network changed capabilities: $networkCapabilities")
			}

			override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
				Log.e("!!!!", "The default network changed link properties: $linkProperties")
			}
		})
	}

	internal fun toggleSnackbar(isNetworkAvailable: Boolean) {
		runOnUiThread {
			if (isNetworkAvailable) {
				snackBar.dismiss()
			} else snackBar.show()
		}
	}
}