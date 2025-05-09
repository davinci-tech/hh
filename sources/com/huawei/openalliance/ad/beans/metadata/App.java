package com.huawei.openalliance.ad.beans.metadata;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.utils.d;
import com.huawei.openalliance.ad.utils.i;

/* loaded from: classes5.dex */
public class App {
    private static final String TAG = "App";
    private Integer brand;
    private String country;
    private AppExt ext;
    private String hostPkgname;
    private String lang;
    private String lmt;

    @a
    private String mediaContent;
    private Integer mediaGpsOn;
    private String name;
    private String pkgname;
    private String sign;
    private Integer systemFlag;
    private String verCode;
    private String version;

    public String n() {
        return this.hostPkgname;
    }

    public String m() {
        return this.sign;
    }

    public AppExt l() {
        return this.ext;
    }

    public Integer k() {
        return this.mediaGpsOn;
    }

    public String j() {
        return this.verCode;
    }

    public Integer i() {
        return this.brand;
    }

    public int hashCode() {
        String str = this.pkgname;
        return str == null ? super.hashCode() : str.hashCode();
    }

    public String h() {
        return this.lmt;
    }

    public Integer g() {
        return this.systemFlag;
    }

    public void f(String str) {
        this.verCode = str;
    }

    public String f() {
        return this.country;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof App) {
            App app = (App) obj;
            if (!TextUtils.isEmpty(app.pkgname) && app.pkgname.equals(this.pkgname)) {
                return true;
            }
        }
        return false;
    }

    public void e(String str) {
        this.country = str;
    }

    public String e() {
        return this.lang;
    }

    public void d(String str) {
        this.lang = str;
    }

    public String d() {
        return this.mediaContent;
    }

    public void c(String str) {
        this.mediaContent = str;
    }

    public String c() {
        return this.pkgname;
    }

    public void b(String str) {
        this.pkgname = str;
    }

    public void b(Integer num) {
        this.mediaGpsOn = num;
    }

    public String b() {
        return this.name;
    }

    public void a(String str) {
        this.version = str;
    }

    public void a(Integer num) {
        this.brand = num;
    }

    public void a(AppExt appExt) {
        this.ext = appExt;
    }

    public String a() {
        return this.version;
    }

    private void a(Context context, String str) {
        this.pkgname = str;
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.version = context.getPackageManager().getPackageInfo(str, 128).versionName;
            this.systemFlag = Integer.valueOf(d.b(context, str) ? 1 : 0);
            this.sign = i.e(context, str);
        } catch (PackageManager.NameNotFoundException unused) {
            ho.c(TAG, "fail to get packageInfo");
        } catch (Throwable th) {
            ho.c(TAG, "fail to get packageInfo: %s", th.getClass().getSimpleName());
        }
    }

    public App(Context context, String str) {
        a(context, str);
    }

    public App(Context context, com.huawei.openalliance.ad.beans.parameter.App app) {
        if (app == null) {
            return;
        }
        a(context, app.getPkgname());
        if (app.getBrand() != null) {
            this.brand = app.getBrand();
        }
        if (TextUtils.isEmpty(this.name)) {
            this.name = app.getName();
        }
        if (TextUtils.isEmpty(this.version)) {
            this.version = app.getVersion();
        }
        if (TextUtils.isEmpty(this.hostPkgname)) {
            this.hostPkgname = app.getHostPkgname();
        }
        this.lmt = fh.b(context).bF();
    }

    public App(Context context) {
        this.pkgname = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return;
        }
        gc b = fh.b(context);
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.pkgname, 128);
            this.version = packageInfo.versionName;
            this.name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            this.brand = HiAd.a(context).j();
            this.lmt = b.bF();
            this.sign = i.e(context, this.pkgname);
        } catch (PackageManager.NameNotFoundException unused) {
            ho.c(TAG, "fail to get packageInfo");
        } catch (Throwable th) {
            ho.c(TAG, "fail to get packageInfo: %s", th.getClass().getSimpleName());
        }
    }

    public App() {
    }
}
