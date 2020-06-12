package com.example.myapplication.app;

import android.app.Application;
import android.content.Context;

import androidx.core.os.TraceCompat;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        TimeUtil.getStartTime();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Debug.startMethodTracing("测试App");
//        TraceCompat.beginSection("App调试");
        LogUtil.Builder builder = new LogUtil.Builder(this)
                .isLog(true)
                .isLogBorder(true)
                .setLogType(LogUtil.TYPE.E)
                .setTag("fly");
        LogUtil.init(builder);
//        Debug.stopMethodTracing();
//        TraceCompat.endSection();
    }
}
