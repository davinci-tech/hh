package com.huawei.pluginachievement.manager.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.TrackData;
import defpackage.mct;
import defpackage.mcv;
import defpackage.meh;
import defpackage.mer;
import defpackage.mfb;
import defpackage.mfd;
import defpackage.mfe;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class OnceMovementReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f8374a = new Object();
    private static ExecutorService d = Executors.newSingleThreadExecutor();
    private Context b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "onReceive");
        synchronized (f8374a) {
            this.b = context;
            cgs_(intent);
        }
    }

    private void cgt_(Intent intent) {
        int intExtra = intent.getIntExtra("share_key", 0);
        LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "shareType is ", Integer.valueOf(intExtra));
        mer.b(this.b).c(intExtra);
    }

    private void cgs_(Intent intent) {
        if (this.b == null || intent == null) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "onReceive mContext or intent is null");
            return;
        }
        if (!mcv.a()) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "isSupportAchieve is not");
        } else if (!"com.huawei.healthcloud.action.sendSportTrackDistance".equals(intent.getAction())) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "Action is Invalid!");
        } else if (-1 != intent.getIntExtra("share_key", -1)) {
            cgt_(intent);
        }
    }

    public static void e(Context context, ArrayList<TrackData> arrayList) {
        b(context);
        c(context, arrayList);
        d(context, arrayList);
    }

    public static void cgr_(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.manager.service.OnceMovementReceiver.5
            @Override // java.lang.Runnable
            public void run() {
                mfe.cgq_(bundle);
            }
        });
    }

    private static void b(Context context) {
        LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "Sync trackData Change success!");
        mct.b(context, "_syncWearData", "");
    }

    public static void c(Context context, ArrayList<TrackData> arrayList) {
        mer.b(context).c(arrayList);
    }

    public static void d(Context context, ArrayList<TrackData> arrayList) {
        synchronized (f8374a) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "getData()");
            a(context, arrayList);
        }
    }

    private static void a(final Context context, final ArrayList<TrackData> arrayList) {
        synchronized (f8374a) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "enter processGpsMovement():");
            if (d.isShutdown()) {
                d = Executors.newSingleThreadExecutor();
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                d.execute(new Runnable() { // from class: com.huawei.pluginachievement.manager.service.OnceMovementReceiver.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Utils.o()) {
                            return;
                        }
                        mfe.b(arrayList, meh.c(context), context);
                        mfd.b(context, arrayList);
                    }
                });
                Iterator<TrackData> it = arrayList.iterator();
                while (it.hasNext()) {
                    final TrackData next = it.next();
                    d.execute(new Runnable() { // from class: com.huawei.pluginachievement.manager.service.OnceMovementReceiver.2
                        @Override // java.lang.Runnable
                        public void run() {
                            OnceMovementReceiver.b(context, next);
                        }
                    });
                }
                d.shutdown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, TrackData trackData) {
        LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "enter movementRunnable:");
        meh c = meh.c(context.getApplicationContext());
        if (c.t() == null || c.t().getHuid() == null) {
            LogUtil.a("PLGACHIEVE_OnceMovementReceiver", "dealTrack userProfile or uid is null");
            c.ad();
            if (c.t() == null || c.t().getHuid() == null) {
                return;
            }
        }
        if (trackData == null) {
            LogUtil.h("PLGACHIEVE_OnceMovementReceiver", "trackData is null");
            return;
        }
        if (!Utils.o()) {
            mfd.a(trackData, c, context);
        }
        mfb.c(trackData, c, context);
        mfe.a(trackData, c, context);
    }
}
