package com.huawei.hms.health;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.hms.hihealth.HiHealthKitClient;
import java.lang.Thread;

/* loaded from: classes4.dex */
public class aabf extends Fragment {
    private static Handler aabc;
    private View aab;
    private HandlerThread aaba;
    private Activity aabb;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.aab == null) {
            this.aab = layoutInflater.inflate(R.layout.fragment_health_kit_transparent, viewGroup, false);
        }
        if (this.aaba == null) {
            HandlerThread handlerThread = new HandlerThread("kit_activity_thread_handler");
            this.aaba = handlerThread;
            handlerThread.start();
            aabc = new aabe(this, this.aaba.getLooper());
        }
        aab aabVar = new aab("HealthKitConnectHmsInTaskThread");
        aabVar.setUncaughtExceptionHandler(new aaba(null));
        aabVar.start();
        return this.aab;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aabb = getActivity();
    }

    public static Handler aaba() {
        return aabc;
    }

    class aab extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                HiHealthKitClient.getInstance().connectHmsWithTransActivity(aabf.this.aabb);
            } catch (Throwable unused) {
                aabz.aab("HealthKitTransparentFragment", "ConnectHmsInBackGroundTaskThread has exception");
            }
        }

        aab(String str) {
            super(str);
        }
    }

    static class aaba implements Thread.UncaughtExceptionHandler {
        /* synthetic */ aaba(aabe aabeVar) {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            aabz.aab("HealthKitTransparentFragment", "HealthKitTransparentFragment thread catch exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aab() {
        aabz.aabb("HealthKitTransparentFragment", "HealthKitTransparentFragment onFinish");
        aabc = null;
        this.aaba = null;
        this.aabb.finish();
    }
}
