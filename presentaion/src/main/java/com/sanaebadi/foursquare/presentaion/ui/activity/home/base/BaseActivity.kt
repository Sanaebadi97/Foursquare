package com.sanaebadi.foursquare.presentaion.ui.activity.home.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.base.BaseFragment
import com.sanaebadi.foursquare.presentaion.util.addFragment
import com.sanaebadi.foursquare.presentaion.util.appToast
import com.sanaebadi.foursquare.presentaion.util.detachFragment
import com.sanaebadi.foursquare.presentaion.util.replaceFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : DaggerAppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    protected fun addFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        supportFragmentManager.addFragment(containerViewId, fragment, addToBackStack)
    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int, fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        supportFragmentManager.replaceFragment(containerViewId, fragment, addToBackStack)
    }

    protected fun detachFragment(fragment: Fragment, popBackStack: Boolean = false) {
        supportFragmentManager.detachFragment(fragment, popBackStack)
    }

    protected fun Disposable.track() {
        disposable.add(this)
    }

    @JvmName("trackDisposable")
    fun track(disposable: Disposable) {
        disposable.track()
    }

    protected fun Disposable.unTrack() {
        disposable.remove(this)
    }

    protected fun showMessage(@StringRes resourceId: Int) {
        showMessage(getString(resourceId))
    }

    protected fun showMessage(message: String) {
        appToast(message)
    }

    protected fun currentFragment() =
        if (supportFragmentManager.fragments.isNullOrEmpty())
            null
        else
            supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1] as? BaseFragment

}