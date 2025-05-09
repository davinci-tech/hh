package com.huawei.health.manager.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.icommon.LocalStepDataReport;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StepsRecord;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class StandReportReceiver {

    /* renamed from: a, reason: collision with root package name */
    private AtomicBoolean f2787a = new AtomicBoolean(true);
    private ScreenBroadcastReceiver e = new ScreenBroadcastReceiver();
    private ArrayList<LocalStepDataReport> b = new ArrayList<>(10);
    private OnScreenOnListener c = null;

    public interface OnScreenOnListener {
        void onScreenOn();
    }

    public StandReportReceiver(OnScreenOnListener onScreenOnListener) {
        c(onScreenOnListener);
        BroadcastManager.wm_(this.e);
    }

    public int e(StepsRecord stepsRecord, boolean z) {
        if (stepsRecord == null) {
            return -3;
        }
        if (!stepsRecord.c()) {
            return -1;
        }
        boolean z2 = !this.f2787a.get();
        if (!z && z2) {
            if (z2 == (!ScreenUtil.a())) {
                return -2;
            }
            ReleaseLogUtil.d("Step_StandRptRecv", "ScrnStateUnkn LikelyOn");
        }
        try {
            Iterator<LocalStepDataReport> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().report(stepsRecord);
            }
        } catch (SecurityException unused) {
            LogUtil.h("Step_StandRptRecv", "update SecurityException ");
        }
        ReleaseLogUtil.e("Step_StandRptRecv", "RPT:", Integer.valueOf(stepsRecord.g), " ", Integer.valueOf(stepsRecord.c), " ", Integer.valueOf(stepsRecord.f13139a), " ", Integer.valueOf(stepsRecord.d), " ", Integer.valueOf(stepsRecord.b), " ", Integer.valueOf(stepsRecord.j), " ", Integer.valueOf(stepsRecord.e), " ", Integer.valueOf(stepsRecord.i));
        return 0;
    }

    public void a(LocalStepDataReport localStepDataReport) {
        if (localStepDataReport == null) {
            ReleaseLogUtil.d("Step_StandRptRecv", "add report is null.");
        } else {
            if (this.b.contains(localStepDataReport)) {
                return;
            }
            this.b.add(localStepDataReport);
        }
    }

    private void c(OnScreenOnListener onScreenOnListener) {
        if (onScreenOnListener != null) {
            this.c = onScreenOnListener;
        } else {
            ReleaseLogUtil.d("Step_StandRptRecv", "listener == null");
        }
    }

    class ScreenBroadcastReceiver extends BroadcastReceiver {
        private ScreenBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            LogUtil.a("Step_StandRptRecv", "onReceive action: ", action);
            if (action == null || "".equals(action.trim())) {
                ReleaseLogUtil.d("Step_StandRptRecv", "intent no action");
                return;
            }
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                StandReportReceiver.this.f2787a.set(true);
                if (StandReportReceiver.this.c != null) {
                    StandReportReceiver.this.c.onScreenOn();
                    return;
                }
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                StandReportReceiver.this.f2787a.set(false);
            } else {
                LogUtil.a("Step_StandRptRecv", "action:Neither right nor wrong ", action);
            }
        }
    }
}
