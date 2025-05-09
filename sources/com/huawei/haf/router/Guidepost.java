package com.huawei.haf.router;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.core.AppRouterHelper;
import com.huawei.haf.router.core.AppRouterPostcard;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes.dex */
public final class Guidepost extends AppRouterPostcard {

    /* renamed from: a, reason: collision with root package name */
    private Context f2139a;
    private String b;
    private int c;
    private int d;
    private final Bundle e;
    private Bundle g;
    private int i;

    public Guidepost(String str, String str2, Uri uri) {
        super(str, str2, uri);
        this.e = new Bundle();
        this.c = -1;
        this.d = -1;
    }

    public Object j() {
        return AppRouterHelper.d(a(), this, -1, (NaviCallback) null);
    }

    public Object c(Context context) {
        this.f2139a = context;
        return AppRouterHelper.d(a(), this, -1, (NaviCallback) null);
    }

    public Object b(Context context, NaviCallback naviCallback) {
        this.f2139a = context;
        return AppRouterHelper.d(a(), this, -1, naviCallback);
    }

    public void zD_(Activity activity, int i) {
        this.f2139a = activity;
        AppRouterHelper.d(a(), this, i, (NaviCallback) null);
    }

    public void zE_(Activity activity, int i, NaviCallback naviCallback) {
        this.f2139a = activity;
        AppRouterHelper.d(a(), this, i, naviCallback);
    }

    public Guidepost zF_(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && !bundle.isEmpty() && bundle != (bundle2 = this.e)) {
            bundle2.putAll(bundle);
        }
        return this;
    }

    public Guidepost e(String str, boolean z) {
        this.e.putBoolean(str, z);
        return this;
    }

    public Guidepost a(String str, byte b) {
        this.e.putByte(str, b);
        return this;
    }

    public Guidepost b(String str, short s) {
        this.e.putShort(str, s);
        return this;
    }

    public Guidepost c(String str, int i) {
        this.e.putInt(str, i);
        return this;
    }

    public Guidepost c(String str, long j) {
        this.e.putLong(str, j);
        return this;
    }

    public Guidepost d(String str, char c) {
        this.e.putChar(str, c);
        return this;
    }

    public Guidepost d(String str, float f) {
        this.e.putFloat(str, f);
        return this;
    }

    public Guidepost d(String str, double d) {
        this.e.putDouble(str, d);
        return this;
    }

    public Guidepost e(String str, String str2) {
        this.e.putString(str, str2);
        return this;
    }

    public Guidepost zG_(String str, Parcelable parcelable) {
        this.e.putParcelable(str, parcelable);
        return this;
    }

    public Guidepost b(int i, int i2) {
        this.c = i;
        this.d = i2;
        return this;
    }

    public Guidepost b(String str) {
        this.b = str;
        return this;
    }

    public Guidepost a(int i) {
        this.i = i;
        return this;
    }

    public Guidepost b(int i) {
        this.i = i | this.i;
        return this;
    }

    public int i() {
        return this.i;
    }

    public int c() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public Bundle zC_() {
        return this.g;
    }

    public Bundle zB_() {
        return this.e;
    }

    public Context a() {
        if (this.f2139a == null) {
            this.f2139a = BaseApplication.wa_();
        }
        Context context = this.f2139a;
        return context == null ? BaseApplication.e() : context;
    }

    static Bundle zA_(Guidepost guidepost) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", guidepost.zN_());
        bundle.putString(BleConstants.KEY_PATH, guidepost.m());
        zy_(bundle, guidepost);
        return bundle;
    }

    static Guidepost zx_(Bundle bundle) {
        bundle.setClassLoader(BaseApplication.e().getClassLoader());
        Uri uri = (Uri) bundle.getParcelable("uri");
        if (uri != null) {
            Guidepost zK_ = AppRouterHelper.zK_(uri);
            zz_(zK_, bundle);
            return zK_;
        }
        String string = bundle.getString(BleConstants.KEY_PATH);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        Guidepost b = AppRouterHelper.b(string);
        zz_(b, bundle);
        return b;
    }

    private static void zy_(Bundle bundle, Guidepost guidepost) {
        bundle.putBundle("bundle", guidepost.zB_());
        bundle.putInt(ParamConstants.Param.FLAGS, guidepost.i());
        bundle.putInt("enterAnim", guidepost.c());
        bundle.putInt("exitAnim", guidepost.b());
        bundle.putString("action", guidepost.e());
        Bundle zC_ = guidepost.zC_();
        if (zC_ != null) {
            bundle.putBundle("optionBundle", zC_);
        }
    }

    private static void zz_(Guidepost guidepost, Bundle bundle) {
        guidepost.zF_(bundle.getBundle("bundle"));
        guidepost.a(bundle.getInt(ParamConstants.Param.FLAGS));
        guidepost.b(bundle.getInt("enterAnim"), bundle.getInt("exitAnim"));
        guidepost.b(bundle.getString("action"));
        Bundle bundle2 = bundle.getBundle("optionBundle");
        if (bundle2 != null) {
            guidepost.g = bundle2;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Guidepost{");
        if (zN_() != null) {
            sb.append("uri=");
            sb.append(zN_());
        } else {
            sb.append("path=");
            sb.append(m());
        }
        if (!this.e.isEmpty()) {
            sb.append(", extras=");
            sb.append(this.e);
        }
        if (this.i != 0) {
            sb.append(", flags=");
            sb.append(this.i);
        }
        if (f() != 0) {
            sb.append(", extra=");
            sb.append(f());
        }
        if (this.c != 0) {
            sb.append(", enterAnim=");
            sb.append(this.c);
        }
        if (this.d != 0) {
            sb.append(", exitAnim=");
            sb.append(this.d);
        }
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(", action=");
            sb.append(this.b);
        }
        if (n() != null) {
            sb.append(", provider=");
            sb.append(n());
        }
        sb.append("}");
        return sb.toString();
    }
}
