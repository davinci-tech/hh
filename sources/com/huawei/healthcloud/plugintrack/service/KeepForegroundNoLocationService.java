package com.huawei.healthcloud.plugintrack.service;

import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdh;

/* loaded from: classes4.dex */
public class KeepForegroundNoLocationService extends KeepForegroundService {
    @Override // com.huawei.healthcloud.plugintrack.service.KeepForegroundService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("KeepForegroundNoLocationService", "KeepForegroundNoLocationService onCreate");
        startForeground(20200523, jdh.c().xf_().build());
    }
}
