package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes2.dex */
public final class ic {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1171a = ia.c("SU2hhcmVkUHJlZmVyZW5jZUFkaXU");
    private static ic f;
    private List<String> b;
    private String c;
    private final Context d;
    private final Handler e;

    public static ic a(Context context) {
        if (f == null) {
            synchronized (ic.class) {
                if (f == null) {
                    f = new ic(context);
                }
            }
        }
        return f;
    }

    private ic(Context context) {
        this.d = context.getApplicationContext();
        if (Looper.myLooper() == null) {
            this.e = new a(Looper.getMainLooper(), this);
        } else {
            this.e = new a(this);
        }
    }

    public final void a(String str) {
        this.c = str;
    }

    public final void b(String str) {
        List<String> list = this.b;
        if (list != null) {
            list.clear();
            this.b.add(str);
        }
        a(str, OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.amap.api.col.3sl.ic$1] */
    public void a(final String str, final int i) {
        synchronized (this) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread() { // from class: com.amap.api.col.3sl.ic.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public final void run() {
                        String b = ii.b(str);
                        if (TextUtils.isEmpty(b)) {
                            return;
                        }
                        if ((i & 1) > 0) {
                            try {
                                if (Settings.System.canWrite(ic.this.d)) {
                                    Settings.System.putString(ic.this.d.getContentResolver(), ic.this.c, b);
                                }
                            } catch (Exception unused) {
                            }
                        }
                        if ((i & 16) > 0) {
                            ie.a(ic.this.d, ic.this.c, b);
                        }
                        if ((i & 256) > 0) {
                            SharedPreferences.Editor edit = ic.this.d.getSharedPreferences(ic.f1171a, 0).edit();
                            edit.putString(ic.this.c, b);
                            edit.apply();
                        }
                    }
                }.start();
                return;
            }
            String b = ii.b(str);
            if (!TextUtils.isEmpty(b)) {
                if ((i & 1) > 0) {
                    try {
                        Settings.System.putString(this.d.getContentResolver(), this.c, b);
                    } catch (Exception unused) {
                    }
                }
                if ((i & 16) > 0) {
                    ie.a(this.d, this.c, b);
                }
                if ((i & 256) > 0) {
                    SharedPreferences.Editor edit = this.d.getSharedPreferences(f1171a, 0).edit();
                    edit.putString(this.c, b);
                    edit.apply();
                }
            }
        }
    }

    static final class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ic> f1173a;

        a(ic icVar) {
            this.f1173a = new WeakReference<>(icVar);
        }

        a(Looper looper, ic icVar) {
            super(looper);
            this.f1173a = new WeakReference<>(icVar);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            ic icVar = this.f1173a.get();
            if (icVar == null || message == null || message.obj == null) {
                return;
            }
            icVar.a((String) message.obj, message.what);
        }
    }
}
