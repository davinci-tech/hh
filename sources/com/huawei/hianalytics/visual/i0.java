package com.huawei.hianalytics.visual;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class i0 {
    public static i0 g;
    public WeakReference<Intent> d;
    public final Handler e;

    /* renamed from: a, reason: collision with root package name */
    public final WeakHashMap<PendingIntent, String> f3927a = new WeakHashMap<>();
    public final AtomicInteger c = new AtomicInteger(0);
    public final int b = Process.myPid();
    public final boolean f = false;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f3928a;
        public int b;
        public String c;
        public long d;

        public a(String str, int i, String str2, long j) {
            this.f3928a = str;
            this.d = j;
            this.b = i;
            this.c = str2;
        }
    }

    public i0() {
        HandlerThread handlerThread = new HandlerThread("HASDKNotice");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
    }

    public static i0 a() {
        i0 i0Var;
        synchronized (i0.class) {
            if (g == null) {
                g = new i0();
            }
            i0Var = g;
        }
        return i0Var;
    }

    public final void b(Intent intent) {
        try {
            try {
                if (intent.hasExtra("HA_NOTICE_ID")) {
                    final String stringExtra = intent.getStringExtra("HA_NOTICE_ID");
                    intent.removeExtra("HA_NOTICE_ID");
                    if (TextUtils.isEmpty(stringExtra)) {
                        return;
                    }
                    this.e.post(new Runnable() { // from class: com.huawei.hianalytics.visual.i0$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            i0.this.a(stringExtra);
                        }
                    });
                }
            } catch (Exception unused) {
                HiLog.d("PushNotificationManager", "fail to get notice id");
            }
        } catch (Throwable unused2) {
            HiLog.d("PushNotificationManager", "fail to handle notification click event");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r9, final android.app.Notification r10) {
        /*
            r8 = this;
            boolean r0 = r8.f
            if (r0 == 0) goto L5
            return
        L5:
            if (r10 == 0) goto L3a
            android.app.PendingIntent r1 = r10.contentIntent
            if (r1 != 0) goto Lc
            goto L3a
        Lc:
            if (r0 == 0) goto Lf
            goto L2c
        Lf:
            android.os.Bundle r0 = r10.extras     // Catch: java.lang.Exception -> L25
            java.lang.String r1 = "android.title"
            java.lang.String r3 = r0.getString(r1)     // Catch: java.lang.Exception -> L25
            java.lang.String r5 = r10.getChannelId()     // Catch: java.lang.Exception -> L25
            com.huawei.hianalytics.visual.i0$a r0 = new com.huawei.hianalytics.visual.i0$a     // Catch: java.lang.Exception -> L25
            r6 = 0
            r2 = r0
            r4 = r9
            r2.<init>(r3, r4, r5, r6)     // Catch: java.lang.Exception -> L25
            goto L2d
        L25:
            java.lang.String r9 = "PushNotificationManager"
            java.lang.String r0 = "fail to acquire notification event"
            com.huawei.hianalytics.core.log.HiLog.d(r9, r0)
        L2c:
            r0 = 0
        L2d:
            if (r0 != 0) goto L30
            return
        L30:
            android.os.Handler r9 = r8.e
            com.huawei.hianalytics.visual.i0$$ExternalSyntheticLambda0 r1 = new com.huawei.hianalytics.visual.i0$$ExternalSyntheticLambda0
            r1.<init>()
            r9.post(r1)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.i0.a(int, android.app.Notification):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Notification notification, a aVar) {
        if (notification.contentIntent == null) {
            return;
        }
        a("$AppNoticeDisplay", aVar);
        try {
            String str = this.f3927a.get(notification.contentIntent);
            if (str == null) {
                return;
            }
            a(aVar, str);
        } catch (Exception unused) {
            HiLog.d("PushNotificationManager", "fail to save notification event");
        }
    }

    public final void a(a aVar, String str) {
        String str2;
        try {
            String str3 = b.a().g().getFilesDir() + File.separator + "hasdk_push";
            com.huawei.hianalytics.visual.a.b(str3);
            aVar.getClass();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("title", aVar.f3928a);
                jSONObject.put("time", aVar.d);
                jSONObject.put("id", aVar.b);
                jSONObject.put("channelId", aVar.c);
                str2 = jSONObject.toString();
            } catch (JSONException unused) {
                HiLog.d("PushNotificationManager", "fail to put notification event toJson");
                str2 = "";
            }
            com.huawei.hianalytics.visual.a.b(str3, str, str2);
        } catch (Exception unused2) {
            HiLog.d("PushNotificationManager", "fail to save notification event to file");
        }
    }

    public void a(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            WeakReference<Intent> weakReference = this.d;
            if (weakReference == null || weakReference.get() != intent) {
                this.d = new WeakReference<>(intent);
                if (this.f) {
                    return;
                }
                b(intent);
            }
        } catch (Exception unused) {
            HiLog.d("PushNotificationManager", "fail to handle notification click event");
        }
    }

    public final void a(String str, a aVar) {
        if (aVar == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$app_notice_title", aVar.f3928a);
            jSONObject.put("$app_notice_id", aVar.b);
            jSONObject.put("$app_notice_channel_id", aVar.c);
            b.a().a(str, jSONObject);
        } catch (JSONException unused) {
            HiLog.e("PushNotificationManager", "fail to report notification event");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        a aVar;
        String a2;
        try {
            String str2 = b.a().g().getFilesDir() + File.separator + "hasdk_push";
            com.huawei.hianalytics.visual.a.b(str2);
            a2 = com.huawei.hianalytics.visual.a.a(str2, str);
        } catch (Exception unused) {
            HiLog.d("PushNotificationManager", "fail to get notification event from file");
        }
        if (!TextUtils.isEmpty(a2)) {
            HiLog.d("PushNotificationManager", "local notification info:" + a2);
            JSONObject jSONObject = new JSONObject(a2);
            aVar = new a(jSONObject.getString("title"), jSONObject.getInt("id"), jSONObject.getString("channelId"), Utils.parseStringToLong(jSONObject.getString("time")));
            a("$AppNoticeClick", aVar);
        }
        aVar = null;
        a("$AppNoticeClick", aVar);
    }
}
