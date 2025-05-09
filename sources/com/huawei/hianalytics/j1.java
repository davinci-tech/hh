package com.huawei.hianalytics;

import android.content.Context;
import android.os.UserManager;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public class j1 {

    /* renamed from: a, reason: collision with root package name */
    public static final j1 f3879a = new j1();

    /* renamed from: a, reason: collision with other field name */
    public boolean f43a = false;

    public boolean a() {
        if (this.f43a) {
            return true;
        }
        Context appContext = EnvUtils.getAppContext();
        if (appContext == null) {
            return false;
        }
        try {
            UserManager userManager = (UserManager) appContext.getSystemService("user");
            if (userManager != null) {
                this.f43a = userManager.isUserUnlocked();
            } else {
                this.f43a = false;
            }
        } catch (Exception unused) {
            this.f43a = false;
            HiLog.e("UserManagerPro", "user locked exception");
        }
        return this.f43a;
    }
}
