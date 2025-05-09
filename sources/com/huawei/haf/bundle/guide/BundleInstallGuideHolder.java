package com.huawei.haf.bundle.guide;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.fitness.data.WorkoutExercises;
import com.huawei.haf.bundle.InstallGuide;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class BundleInstallGuideHolder {
    private static InstallGuide c;

    /* renamed from: a, reason: collision with root package name */
    private final String f2075a;
    private BaseBundleInstallGuide d;

    public BundleInstallGuideHolder(String str) {
        this.f2075a = str;
    }

    public void xc_(Activity activity) {
        int xb_ = xb_(activity);
        LogUtil.c(this.f2075a, "initialize, launcherIndex=", Integer.valueOf(xb_));
        BaseBundleInstallGuide baseBundleInstallGuide = new BaseBundleInstallGuide(activity, xb_);
        this.d = baseBundleInstallGuide;
        if (xb_ == -1) {
            baseBundleInstallGuide.onFinish(false);
        } else {
            baseBundleInstallGuide.c();
        }
    }

    public void d() {
        LogUtil.c(this.f2075a, WorkoutExercises.CLEAN);
        BaseBundleInstallGuide baseBundleInstallGuide = this.d;
        if (baseBundleInstallGuide != null) {
            baseBundleInstallGuide.d();
            this.d = null;
        }
    }

    public static void d(InstallGuide installGuide) {
        if (installGuide != null) {
            c = installGuide;
        }
    }

    public static InstallGuide c() {
        return c;
    }

    private int xb_(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            LogUtil.e(this.f2075a, "intent == null");
            return -1;
        }
        try {
            return intent.getIntExtra("launcherIndex", -1);
        } catch (Exception unused) {
            return -1;
        }
    }
}
