package com.example.myapplication.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.util.PriorityRunnable
import com.example.myapplication.util.ThreadPoolManager
import kotlinx.android.synthetic.main.activity_two.*
import kotlin.math.E

class TwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val threadPoolManager = ThreadPoolManager()

        // 前三个是由线程池设置的核心数量执行的，后面的是根据我们的优先级进行排序的
        for (c in 1..10) {
            val priority = c
            threadPoolManager.executorService.execute(object : PriorityRunnable(priority) {
                override fun doSth() {
                    val name = Thread.currentThread().name
                    Log.e("OneActivity", "线程 $name 正在执行优先级为 $priority 的任务")

                    try {
                        Thread.sleep(200)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            })
        }

        btn.setOnClickListener{
            EventBus.getInstance().post("tuige")
            finish()
        }
    }
}
