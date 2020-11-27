package com.sm9i.keep_active

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread


class KeepService : Service() {
    companion object {
        private const val TAG = "KeepService"
        private const val NOTICE_ID = 10040
        private const val NOTICE_NAME = "定位服务"
        private const val ID = "10040"
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private var isExit: Boolean = true

    //log 线程
    private var thread: Thread = LogThread()

    inner class LogThread : Thread() {
        override fun run() {
            while (isExit) {
                Thread.sleep(1000)
                Log.d(TAG, "service is running")
            }
            super.run()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(ID, NOTICE_NAME, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            val notification: Notification = Notification.Builder(this, ID)
                    .setContentTitle("小马货运")
                    .setContentText("正在定位")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                    .build()
            startForeground(1, notification)
        } else {
            startForeground(NOTICE_ID, Notification())
        }
    }

    //设置为 START_STICKY  利用系统机制在 Service 挂掉后自动拉活:
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isExit = true
        thread.start()
        return START_STICKY
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        isExit = false
        super.onDestroy()
    }


}