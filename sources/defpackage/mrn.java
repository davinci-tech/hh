package defpackage;

import android.net.Uri;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.extension.ComponentInfo;
import health.compact.a.utils.StringUtils;

/* loaded from: classes6.dex */
class mrn {

    /* renamed from: a, reason: collision with root package name */
    private String f15131a;
    private String b;
    private String c;
    private String d;
    private int e;

    mrn() {
    }

    public String d() {
        return this.f15131a;
    }

    public String c() {
        return this.c;
    }

    public String e() {
        return this.b;
    }

    public String a() {
        return this.d;
    }

    public boolean b() {
        return (StringUtils.g(this.f15131a) || StringUtils.g(this.c) || StringUtils.g(this.b) || StringUtils.g(this.d) || this.e > BaseApplication.c() || this.e == 0 || !this.f15131a.equals("opencard") || !ComponentInfo.PluginPay_A_120.equals(Uri.parse(this.d).getQueryParameter("classPath"))) ? false : true;
    }
}
