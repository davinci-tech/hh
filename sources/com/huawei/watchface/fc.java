package com.huawei.watchface;

import android.app.Application;
import android.os.UserManager;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class fc {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11038a = "fc";
    private static final fc b = new fc();
    private boolean c = true;

    public static fc a() {
        return b;
    }

    private fc() {
    }

    public boolean b() {
        return this.c;
    }

    /* loaded from: classes9.dex */
    public static class a implements PPSNativeView.OnNativeAdClickListener {
        @Override // com.huawei.openalliance.ad.views.PPSNativeView.OnNativeAdClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static void a(Application application) {
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            HwLog.i(f11038a, "initPps isOversea true");
            return;
        }
        String str = f11038a;
        HwLog.i(str, "initPps isOversea false");
        UserManager userManager = (UserManager) application.getSystemService(UserManager.class);
        if (userManager != null) {
            if (userManager.isUserUnlocked()) {
                HwLog.i(str, "Init Pps Sdk - device is unlocked");
                fb.a(application, true, 4);
                fb.a(application, "themes");
                return;
            }
            HwLog.i(str, "Init Pps Sdk - device is locked");
            return;
        }
        HwLog.w(str, "Init Pps Sdk - Get UserManager failed.");
    }

    public static void a(PPSNativeView pPSNativeView) {
        String str = f11038a;
        HwLog.i(str, "setChoiceView: enter ");
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            HwLog.i(str, "isOversea true");
        } else if (pPSNativeView == null) {
            HwLog.i(str, "nativeAdView is null");
        } else {
            pPSNativeView.setIsCustomDislikeThisAdEnabled(true);
            pPSNativeView.setChoiceViewPosition(4);
        }
    }
}
