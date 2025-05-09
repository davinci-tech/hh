package com.huawei.uikit.hwcolumnsystem.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.skx;
import defpackage.sky;
import defpackage.skz;
import defpackage.sla;
import defpackage.slf;
import defpackage.sms;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class HwColumnSystem {
    private static final String d = "HwColumnSystem";

    /* renamed from: a, reason: collision with root package name */
    private int f10620a;
    private bqmxo b;
    private int c;
    private List<Integer[]> e;
    private slf f;
    private int g;
    private int h;
    private float i;
    private akxao j;
    private String[] k;
    private boolean l;
    private boolean m;
    private Context n;
    private boolean o;
    private int p;

    public HwColumnSystem(Context context) {
        this(context, -1);
    }

    private int b(int i, float f) {
        return (int) ((i * f) + 0.5f);
    }

    private int c(int i, float f) {
        if (d() == 19) {
            return i;
        }
        Rect eby_ = sla.eby_();
        if (this.m || this.p != i) {
            this.l = d(this.n);
            this.p = i;
        }
        return this.l ? (eby_.left > 0 || eby_.right > 0) ? (b(this.n.getResources().getConfiguration().screenWidthDp, f) - eby_.left) - eby_.right : i : i;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(int r5, boolean r6) {
        /*
            r4 = this;
            java.lang.String[] r0 = r4.k
            if (r0 != 0) goto L5
            return
        L5:
            com.huawei.uikit.hwcolumnsystem.widget.bqmxo r1 = r4.b
            float r2 = r4.i
            android.util.Pair r0 = r1.a(r5, r0, r2)
            java.lang.Object r1 = r0.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r4.g = r1
            java.lang.Object r0 = r0.second
            java.lang.String r0 = (java.lang.String) r0
            if (r6 == 0) goto L23
            float r6 = r4.i
            int r5 = r4.c(r5, r6)
        L23:
            java.lang.String r6 = "^c(\\d+)m(\\d+)g(\\d+)"
            java.util.regex.Pattern r6 = java.util.regex.Pattern.compile(r6)
            java.util.regex.Matcher r6 = r6.matcher(r0)
            boolean r0 = r6.find()
            if (r0 == 0) goto L91
            int r0 = r6.groupCount()
            r1 = 3
            if (r0 != r1) goto L91
            r0 = 2
            r2 = 0
            java.lang.String r0 = r6.group(r0)     // Catch: java.lang.NumberFormatException -> L64
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L64
            float r3 = r4.i     // Catch: java.lang.NumberFormatException -> L64
            int r0 = r4.b(r0, r3)     // Catch: java.lang.NumberFormatException -> L64
            java.lang.String r1 = r6.group(r1)     // Catch: java.lang.NumberFormatException -> L62
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L62
            float r3 = r4.i     // Catch: java.lang.NumberFormatException -> L62
            int r1 = r4.b(r1, r3)     // Catch: java.lang.NumberFormatException -> L62
            r3 = 1
            java.lang.String r6 = r6.group(r3)     // Catch: java.lang.NumberFormatException -> L66
            int r2 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.NumberFormatException -> L66
            goto L6d
        L62:
            r1 = r2
            goto L66
        L64:
            r0 = r2
            r1 = r0
        L66:
            java.lang.String r6 = com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem.d
            java.lang.String r3 = "Set column system input error"
            android.util.Log.e(r6, r3)
        L6d:
            slf r6 = r4.f
            if (r6 != 0) goto L78
            slf r6 = new slf
            r6.<init>()
            r4.f = r6
        L78:
            slf r6 = r4.f
            r6.e(r0)
            slf r6 = r4.f
            r6.d(r1)
            slf r6 = r4.f
            r6.a(r2)
            slf r6 = r4.f
            r6.c(r2)
            r4.f10620a = r5
            r4.s()
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem.e(int, boolean):void");
    }

    private void k() {
        switch (this.c) {
            case -1:
            case 0:
                this.f = this.b.g();
                break;
            case 1:
            case 2:
            case 17:
            case 18:
                i();
                break;
            case 3:
                this.f = this.b.e();
                break;
            case 4:
                this.f = this.b.b();
                break;
            case 5:
                this.f = this.b.n();
                break;
            case 6:
            case 7:
                q();
                break;
            case 8:
            case 9:
                e();
                break;
            case 10:
                this.f = this.b.k();
                break;
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            default:
                m();
                break;
            case 12:
            case 20:
            case 21:
                o();
                break;
            case 19:
                this.f = this.b.a();
                break;
        }
    }

    private void l() {
        p();
        if (Build.VERSION.SDK_INT > 28) {
            this.f10620a = c(this.f10620a, this.i);
        }
        n();
        s();
    }

    private void m() {
        this.f = this.b.g();
    }

    private void n() {
        if (this.n == null) {
            return;
        }
        this.g = this.b.o();
        k();
    }

    private void o() {
        int i = this.c;
        if (i == 12) {
            this.f = this.b.a(this.g, this.f10620a, this.h);
            return;
        }
        if (i == 20) {
            this.f = this.b.c(this.g, this.f10620a, this.h);
        } else if (i != 21) {
            m();
        } else {
            this.f = this.b.d(this.g, this.f10620a, this.h);
        }
    }

    private void p() {
        DisplayMetrics displayMetrics = this.n.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        this.f10620a = i;
        this.h = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        this.i = f;
        bqmxo bqmxoVar = this.b;
        if (bqmxoVar != null) {
            bqmxoVar.b(i, f);
        }
    }

    private void q() {
        int i = this.c;
        if (i == 6) {
            this.f = this.b.m();
        } else if (i != 7) {
            m();
        } else {
            this.f = this.b.j();
        }
    }

    private void s() {
        if (this.j == null) {
            this.j = new sky();
        }
        this.j.b(this.c);
        this.j.a(this.f10620a, this.h, this.i, this.b instanceof skz);
        slf slfVar = this.f;
        if (slfVar != null) {
            this.j.a(slfVar, this.g, this.b instanceof skz);
        }
    }

    public int a() {
        slf slfVar = this.f;
        if (slfVar != null) {
            return slfVar.d();
        }
        return 0;
    }

    public int b() {
        return this.j.c();
    }

    public int c() {
        slf slfVar = this.f;
        if (slfVar != null) {
            return slfVar.a();
        }
        return 0;
    }

    public void c(boolean z) {
        this.m = z;
    }

    public float d(int i) {
        if (i <= 0) {
            return 0.0f;
        }
        return this.j.a(i);
    }

    public int d() {
        int i = this.c;
        if (i != 12) {
            return i;
        }
        bqmxo bqmxoVar = this.b;
        return bqmxoVar instanceof skz ? bqmxoVar.b(this.f10620a, this.h, this.g) : i;
    }

    public int e(Context context) {
        String[] strArr;
        if (context == null) {
            return h();
        }
        this.n = context;
        if (this.o && (strArr = this.k) != null && strArr.length == 3) {
            p();
            e(this.f10620a, Build.VERSION.SDK_INT > 28);
        } else {
            l();
        }
        return h();
    }

    public void e(int i) {
        this.c = i;
        if (this.n == null) {
            return;
        }
        l();
    }

    public int f() {
        return this.g;
    }

    public float g() {
        return d(1);
    }

    public int j() {
        return this.j.d();
    }

    public HwColumnSystem(Context context, int i) {
        this.e = new ArrayList();
        this.c = -1;
        this.f = new slf();
        this.g = 4;
        this.m = true;
        this.c = i;
        this.n = context;
        this.o = false;
        c(context);
        l();
    }

    public int h() {
        return this.j.b();
    }

    public int d(Context context, int i, int i2, float f) {
        String[] strArr;
        if (context != null && i > 0 && f >= 0.0f && !c(f, 0.0f)) {
            this.n = context;
            this.f10620a = i;
            this.h = i2;
            this.i = f;
            if (this.o && (strArr = this.k) != null && strArr.length == 3) {
                e(i, false);
            } else {
                e(i / f, f);
                s();
            }
            return h();
        }
        Log.w(d, "width and density should not below to zero!");
        return h();
    }

    private boolean d(Context context) {
        WindowManager windowManager;
        int rotation = (context == null || (windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) == null) ? 0 : windowManager.getDefaultDisplay().getRotation();
        return rotation == 0 || rotation == 2;
    }

    private void i() {
        int i = this.c;
        if (i == 1) {
            this.f = this.b.c();
            return;
        }
        if (i == 2) {
            this.f = this.b.h();
            return;
        }
        if (i == 17) {
            this.f = this.b.d();
        } else if (i != 18) {
            m();
        } else {
            this.f = this.b.f();
        }
    }

    private boolean c(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-6f;
    }

    private void c(Context context) {
        this.b = sms.b(context) == 4 ? new skx(context) : new skz(context);
    }

    private void d(int i, int i2, float f) {
        if (!this.b.b(i)) {
            i = 0;
        }
        this.f = this.b.a(this.g, new Pair<>(Integer.valueOf(this.f10620a), Integer.valueOf(this.h)), i, i2, f);
    }

    private void e(float f, float f2) {
        Pair<Integer, Integer> a2 = this.b.a(f);
        this.g = ((Integer) a2.first).intValue();
        d(this.c, ((Integer) a2.second).intValue(), f2);
    }

    private void e() {
        int i = this.c;
        if (i == 8) {
            this.f = this.b.l();
        } else if (i != 9) {
            m();
        } else {
            this.f = this.b.i();
        }
    }
}
