package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;

/* loaded from: classes2.dex */
public final class ht {

    /* renamed from: a, reason: collision with root package name */
    private volatile b f1151a = new b(0);
    private ji b = new ji("HttpsDecisionUtil");

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        static ht f1152a = new ht();
    }

    private static boolean c() {
        return false;
    }

    public static ht a() {
        return a.f1152a;
    }

    public final void a(Context context) {
        if (this.f1151a == null) {
            this.f1151a = new b((byte) 0);
        }
        this.f1151a.a(ji.a(context, "open_common", "a3", true));
        this.f1151a.a(context);
        ib.a(context).a();
    }

    public final void a(boolean z) {
        if (this.f1151a == null) {
            this.f1151a = new b((byte) 0);
        }
        this.f1151a.b(z);
    }

    final void a(Context context, boolean z) {
        if (this.f1151a == null) {
            this.f1151a = new b((byte) 0);
        }
        b(context, z);
        this.f1151a.a(z);
    }

    public final boolean b() {
        if (this.f1151a == null) {
            this.f1151a = new b((byte) 0);
        }
        return this.f1151a.a();
    }

    private static void b(Context context, boolean z) {
        SharedPreferences.Editor a2 = ji.a(context, "open_common");
        ji.a(a2, "a3", z);
        ji.a(a2);
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        protected boolean f1153a;
        private int b;
        private final boolean c;
        private boolean d;

        private b() {
            this.b = 0;
            this.f1153a = true;
            this.c = true;
            this.d = false;
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(Context context) {
            if (context != null && this.b <= 0) {
                this.b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public final void a(boolean z) {
            this.f1153a = z;
        }

        public final void b(boolean z) {
            this.d = z;
        }

        public final boolean a() {
            int i;
            if (!this.d) {
                boolean z = Build.VERSION.SDK_INT >= 28;
                boolean z2 = !this.f1153a || (i = this.b) <= 0 || i >= 28;
                if (!z || !z2) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith(ProxyConfig.MATCH_HTTPS)) {
            return str;
        }
        try {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.scheme(ProxyConfig.MATCH_HTTPS);
            return buildUpon.build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public final boolean b(boolean z) {
        if (c()) {
            return false;
        }
        return z || b();
    }

    public static void b(Context context) {
        b(context, true);
    }
}
