package com.ozontech.core_utils.di

import kotlin.reflect.KClass

interface DiStorage<T : DiComponent> {

	fun initAndGet(className: KClass<T>): T

	fun release(className: KClass<T>)
}

interface DiComponent