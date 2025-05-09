package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes6.dex */
public class nzf extends nzc {
    private static final long serialVersionUID = 8455537364539177810L;
    private nzd c;
    private int e;
    private nzj j;
    private String b = "";
    private String f = "";
    private String d = "";
    private String h = "";

    /* renamed from: a, reason: collision with root package name */
    private String f15564a = "";

    public String j() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public String f() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int g() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public String k() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String c() {
        return this.f15564a;
    }

    public void e(String str) {
        this.f15564a = str;
    }

    public String e() {
        return c(0);
    }

    public String h() {
        return c(1);
    }

    public String i() {
        return c(3);
    }

    public String d() {
        return c(2);
    }

    public nzj n() {
        return this.j;
    }

    public void a(nzj nzjVar) {
        this.j = nzjVar;
    }

    public nzd b() {
        return this.c;
    }

    public void e(nzd nzdVar) {
        this.c = nzdVar;
    }

    private String c(int i) {
        if (TextUtils.isEmpty(this.f15564a)) {
            LogUtil.h("Declaration", "getOobeVersions: content version is null or empty.");
            return "";
        }
        String[] split = this.f15564a.split(Constants.LINK);
        if (split.length != 4 || i < 0 || i > 3) {
            LogUtil.h("Declaration", "getOobeVersions: content version is invalid. contentVersion: ", this.f15564a, ", index: ", Integer.valueOf(i));
            return "";
        }
        return split[i];
    }

    public String toString() {
        return "Declaration{productType='" + this.b + "', scope='" + this.f + "', featureId='" + this.d + "', index=" + this.e + ", xmlVersion='" + this.h + "', contentVersion='" + this.f15564a + "', title=" + this.j + ", content=" + this.c + '}';
    }
}
