package com.ozontech.core_navigation_impl.di

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ozontech.core_navigation_api.NavigationApi
import com.ozontech.core_navigation_impl.MainActivity
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.feature_products_api.ProductNavigationApi
import dagger.BindsInstance
import dagger.Component
import dagger.Reusable
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
interface CoreNavigationComponent : NavigationApi, DiComponent{
}
