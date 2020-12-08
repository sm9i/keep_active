package com.sm9i.keep_active

import android.content.Context
import android.content.Intent

import android.os.Build
import android.util.Log
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** KeepActivePlugin */
public class KeepActivePlugin(private var mContext: Context? = null) : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {

        channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "keep_active")
        channel.setMethodCallHandler(this);
        mContext = flutterPluginBinding.applicationContext
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "keep_active")
            channel.setMethodCallHandler(KeepActivePlugin(registrar.activeContext()))
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "keep" -> keep(result)
            "stop" -> stop(result)
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        mContext = null
    }


    private fun keep(result: Result) {
        val intent = Intent(mContext, KeepService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContext?.startForegroundService(intent)

        } else {
            mContext?.startService(intent)

            result.success(true)
        }

    }

    private fun stop(result: Result) {
        val intent = Intent(mContext, KeepService::class.java)
        mContext?.stopService(intent)
        result.success(true)
    }


}
