package com.lofty.workoutapp.core

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.lofty.workoutapp.R
import com.lofty.workoutapp.api.Resource
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType

abstract class BaseAlphaActivity<VB : ViewBinding, VM : ViewModel>() : AppCompatActivity() {

    val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }
    lateinit var viewModel: VM
    lateinit var binding: VB
    private var progressDialog: WeakReference<AlertDialog?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
        bindScreen()
    }

    fun <T> handleResult(resource: Resource<T>, showAlertOnSuccess: Boolean = false, showLoading: Boolean  = true, onSuccess: ((T?) -> Unit)? = null, onError: ((String?) -> Unit)? = null) {
        when (resource.status) {
            Resource.Status.LOADING -> if (showLoading) showLoading()
            Resource.Status.ERROR -> {
                onError?.invoke(resource.message)
                hideLoading()
            }
            Resource.Status.SUCCESS -> {
                onSuccess?.invoke(resource.data)
                hideLoading()
            }
        }
    }

    // Yükleniyor dialog'u göster
    fun showLoading(cancelable: Boolean = false) {
        hideLoading()
        progressDialog = WeakReference(
            AlertDialog.Builder(this)
                .setView(R.layout.progress_bar)
                .setCancelable(cancelable).create()
        )
        val dialog: AlertDialog? = progressDialog?.get()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()
    }

    // Varsa yükleniyor dialogunu gizle
    fun hideLoading() {
        if (progressDialog != null) {
            val dialog: AlertDialog? = progressDialog?.get()
            dialog?.dismiss()
            progressDialog = null
        }
    }

    fun showAlertDialog(message: String) {
        val dialog =  AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("Tamam") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    abstract fun getViewBinding(): VB

    abstract fun bindScreen()

    abstract fun getViewModelClass() : Class<VM>

}