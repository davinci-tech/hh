package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ll {
    private static ll b;
    private Context d;
    private gc e;
    private Map<String, Class<? extends lk>> f = new HashMap();
    private BroadcastReceiver g = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.ll.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                ho.a("NotificationActionManager", "intent or action maybe empty.");
            } else {
                ho.a("NotificationActionManager", " action name:%s", intent.getAction());
                ll.this.a(context, intent);
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7186a = new byte[0];
    private static final byte[] c = new byte[0];

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.b("NotificationActionManager", "isPackageExist packageName is Empty.");
            return false;
        }
        synchronized (c) {
            Set<String> cd = this.e.cd();
            if (cd == null) {
                return false;
            }
            return cd.contains(str);
        }
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.b("NotificationActionManager", "remove packageName is Empty.");
            return;
        }
        synchronized (c) {
            try {
                Set<String> cd = this.e.cd();
                if (cd != null) {
                    cd.remove(str);
                    fh.b(this.d).b(cd);
                }
            } finally {
            }
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.b("NotificationActionManager", "add packageName is Empty.");
            return;
        }
        synchronized (c) {
            try {
                Set<String> cd = this.e.cd();
                if (cd != null) {
                    cd.add(str);
                    fh.b(this.d).b(cd);
                }
            } finally {
            }
        }
    }

    public void a(Context context, Intent intent) {
        StringBuilder sb;
        String str;
        try {
            int intExtra = intent.getIntExtra("type", 1);
            String str2 = intent.getAction() + intExtra;
            Class<? extends lk> cls = this.f.get(str2);
            if (cls != null) {
                try {
                    cls.newInstance().a(this.d, intent);
                } catch (InstantiationException unused) {
                    str = "InstantiationException can not instantiation notification Action";
                    ho.c("NotificationActionManager", str);
                } catch (Throwable unused2) {
                    str = "Throwable can not instantiation notification Action";
                    ho.c("NotificationActionManager", str);
                }
            } else {
                ho.b("NotificationActionManager", "can not find action key:" + str2);
            }
        } catch (IllegalStateException e) {
            e = e;
            sb = new StringBuilder("actionReceiver.onReceive IllegalStateException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("NotificationActionManager", sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("actionReceiver.onReceive Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("NotificationActionManager", sb.toString());
        }
    }

    public void a() {
        String str;
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.ads.notification.action.DELETE");
            com.huawei.openalliance.ad.utils.ao.a(this.d, this.g, intentFilter);
        } catch (IllegalStateException unused) {
            str = "init IllegalStateException";
            ho.c("NotificationActionManager", str);
            b();
        } catch (Exception unused2) {
            str = "init Exception";
            ho.c("NotificationActionManager", str);
            b();
        }
        b();
    }

    private void b() {
        this.f.put("com.huawei.ads.notification.action.CLICK1", lg.class);
        this.f.put("com.huawei.ads.notification.action.DELETE1", li.class);
    }

    public static ll a(Context context) {
        synchronized (f7186a) {
            if (b == null) {
                b = new ll(context);
            }
        }
        return b;
    }

    private ll(Context context) {
        this.d = context.getApplicationContext();
        this.e = fh.b(context);
    }
}
