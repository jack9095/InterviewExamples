package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.ViewTreeObserver
import com.example.myapplication.app.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// 子线程是不能弹 toast 的
class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    // CountDownLatch 作用就是自己加了一把锁
    val mCountDownLatch = CountDownLatch(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val executor = Executors.newSingleThreadScheduledExecutor()

        repeat(100) {
            val executor = Executors.newSingleThreadScheduledExecutor()
            val task = Runnable {
                Log.e("MainActivity","@#￥%")
            }
            repeat(100) {
                executor.schedule(task, 1, TimeUnit.SECONDS)
            }
        }

        // 设置线程池中的核心数量根据 CPU 数来设置的，双核、四核、八核

        val CPU_COUNT = Runtime.getRuntime().availableProcessors()

        val CORE_POOL_SIZE = Math.max(2,Math.min(CPU_COUNT - 1,4))

        // 创建出线程池
        val newFixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE)

        // 开启一个异步
        newFixedThreadPool.submit {

        }

        init()

        // 表示不满足 CountDownLatch ，会一直等待
        mCountDownLatch.await()
    }

    fun init(){
        // 走了 init 方法，就告诉 CountDownLatch 已经执行了 init 方法
        mCountDownLatch.countDown()
        text_view.postDelayed({
            LogUtil.e("延时500毫秒")
        },500)

        // View 树的绘制监听
        text_view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return true
            }

        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }


}
