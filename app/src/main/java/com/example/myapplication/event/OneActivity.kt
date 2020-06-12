//package com.example.myapplication.event
//
//import android.animation.ObjectAnimator
//import android.animation.PropertyValuesHolder
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.GestureDetector
//import android.view.MotionEvent
//import android.view.VelocityTracker
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.example.myapplication.R
//import kotlinx.android.synthetic.main.activity_one.*
//
//
////class OneActivity : AppCompatActivity(),GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
//class OneActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_one)
//        EventBus.getInstance().register(this)
//        btn.setOnClickListener {
//            startActivity(Intent(this@OneActivity, TwoActivity::class.java))
//        }
//
////        ObjectAnimator.ofFloat(btn,"translationX",0f,500f).setDuration(2000).start()
//
////        initGestureDetector()
//    }
//
//    @Subscribe
//    fun eventMethod(a: Any) {
//        Log.e("OneActivity", a.toString())
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        EventBus.getInstance().unregister(this)
//
//        // 清除速度追踪并回收内存
////        velocityTracker.clear()
////        velocityTracker.recycle()
//    }
//
//
////    var lastX = 0f
////    var lastY = 0f
////    // 速度追踪
////    var velocityTracker = VelocityTracker.obtain()
////    override fun onTouchEvent(event: MotionEvent): Boolean {
////        var x = event.rawX;
////        var y = event.rawY;
////        velocityTracker.addMovement(event)
////        when (event.action) {
////            MotionEvent.ACTION_DOWN -> {
////                velocityTracker.computeCurrentVelocity(100)
////                val xVelocity = velocityTracker.xVelocity
////                Log.e("水平滑动速度 = ", xVelocity.toString())
////                val yVelocity = velocityTracker.yVelocity
////                Log.e("垂直滑动速度 = ", yVelocity.toString())
////            }
////            MotionEvent.ACTION_MOVE -> {
////                var deltaX = x - lastX
////                var deltaY = y - lastY
////
////            }
////            MotionEvent.ACTION_UP -> {
////                velocityTracker.computeCurrentVelocity(100)
////                val xVelocity = velocityTracker.xVelocity
////                Log.e("水平滑动速度 ACTION_UP = ", xVelocity.toString())
////                val yVelocity = velocityTracker.yVelocity
////                Log.e("垂直滑动速度 ACTION_UP = ", yVelocity.toString())
////            }
////        }
////
////        lastX = x
////        lastY = y
////
////        // 接管目标 View 的 onTouchEvent 方法
////        val gestureDetectorEvent = gestureDetector?.onTouchEvent(event)
//////        return gestureDetectorEvent ?: false
//////        return super.onTouchEvent(event)
////        return true
////    }
//
//    // 手势检测
////    var gestureDetector: GestureDetector? = null
////    fun initGestureDetector(){
////        gestureDetector = GestureDetector(this,this)
////        // 解决长按屏幕后无法拖动的现象
////        gestureDetector?.setIsLongpressEnabled(false)
////    }
////
////    // 手指轻轻触摸屏幕的一瞬间
////    override fun onDown(e: MotionEvent?): Boolean {
////        return false
////    }
////
////    // 手指轻轻触摸屏幕，尚未松开或拖动，这里区分 onDown 方法
////    override fun onShowPress(e: MotionEvent?) {
////    }
////
////    // 手指轻触屏幕后松开，单击行为
////    override fun onSingleTapUp(e: MotionEvent?): Boolean {
////        return false
////    }
////
////    // 手指按下屏幕并拖动，这是拖动行为
////    override fun onScroll(
////        e1: MotionEvent?,
////        e2: MotionEvent?,
////        distanceX: Float,
////        distanceY: Float
////    ): Boolean {
////        return false
////    }
////
////    // 长按事件
////    override fun onLongPress(e: MotionEvent?) {
////
////    }
////
////    // 按下触摸屏，快速滑动后松开，快速滑动行为
////    override fun onFling(e1: MotionEvent?,e2: MotionEvent?,velocityX: Float,velocityY: Float): Boolean {
////        return false
////    }
////
////    // 双击，由连续两次的单击组成
////    override fun onDoubleTap(e: MotionEvent?): Boolean {
////        return false
////    }
////
////    // 表示发生了双击行为
////    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
////        return false
////    }
////
////    // 严格的单击行为
////    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
////        return false
////    }
//}
