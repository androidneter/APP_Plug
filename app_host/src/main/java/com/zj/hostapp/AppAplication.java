package com.zj.hostapp;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * ProjectName: PlugAPP
 * Package: com.zj.plusapp
 * ClassName: AppAplication
 * Description: java类作用描述
 * Author: zj
 * CreateDate: 2021/03/04
 * UpdateUser: 更新作者
 * UpdateDate: 2021/03/04
 * UpdateRemark: 更新说明
 * Version: 1.0
 */
public class AppAplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
