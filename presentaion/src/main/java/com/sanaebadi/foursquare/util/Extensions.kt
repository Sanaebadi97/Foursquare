package com.sanaebadi.foursquare.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanaebadi.foursquare.R
import com.sanaebadi.foursquare.ui.viewModel.base.ViewModelFactory

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).apply(RequestOptions().error(R.mipmap.ic_launcher)).into(this)
}

fun ImageView.loadCircleImage(url: String) {
    Glide.with(context).load(url).apply(
        RequestOptions.circleCropTransform()
            .error(R.mipmap.ic_launcher_round)
    ).into(this)
}

fun isLollipopOrAbove(func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        func()
    }
}

fun View.visible(b: Boolean) =
    if (b) this.visibility = View.VISIBLE else this.visibility = View.GONE

internal fun RecyclerView.linearLayout(
    context: Context,
    @RecyclerView.Orientation orientation: Int? = RecyclerView.VERTICAL,
    reverseLayout: Boolean? = false,
    stackFromEnd: Boolean? = false
) {
    val lm = LinearLayoutManager(context, orientation!!, reverseLayout!!)
    lm.stackFromEnd = stackFromEnd!!
    layoutManager = lm
    setHasFixedSize(true)
}

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> =
    Transformations.switchMap(this, body)


fun FragmentActivity.requestPermission(requestCode: Int, vararg permissions: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }
}

fun FragmentActivity.checkAppPermission(permission: String): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    else
        true

fun FragmentActivity.openAppPermissionSetting() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", this.packageName, null)
    intent.data = uri
    this.startActivityForResult(intent, Constants.APP_SETTING)
}

fun FragmentActivity.showAppSettingDialog(
    permission: String,
    requestCode: Int,
    @StringRes messageResource: Int,
    function: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setMessage(messageResource)
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        .setPositiveButton(R.string.show_app_permissions_setting) { dialog, _ ->
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                this.requestPermission(requestCode, permission)
            } else {
                this.openAppPermissionSetting()
            }
            function?.invoke()
            dialog.dismiss()
        }
        .show()
}

fun Context.appToast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).apply {
        val layout = LayoutInflater.from(this@appToast).inflate(R.layout.custom_toast, null)
        val texMessage = layout.findViewById<AppCompatTextView>(R.id.text_message)
        texMessage.text = text
        this.view = layout
    }.show()


fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this, Observer { t -> action(t) })
}


inline fun <reified VM : ViewModel> Fragment.viewModelProvider(provider: ViewModelFactory) =
    ViewModelProvider(this, provider)[VM::class.java]

inline fun <reified VM : ViewModel> AppCompatActivity.viewModelProvider(provider: ViewModelFactory) =
    ViewModelProvider(this, provider)[VM::class.java]

internal fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    this.beginTransaction()
        .add(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commitAllowingStateLoss()
}

internal fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    this.beginTransaction()
        .replace(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commitAllowingStateLoss()
}

internal fun FragmentManager.detachFragment(fragment: Fragment, popBackStack: Boolean = false) {
    this.beginTransaction()
        .detach(fragment)
        .commitAllowingStateLoss()
    if (popBackStack) popBackStack()
}