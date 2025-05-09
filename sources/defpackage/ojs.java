package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class ojs implements Comparable<ojs> {

    /* renamed from: a, reason: collision with root package name */
    private int f15741a;
    private String b;
    private String c;
    private Context d;
    private String e;
    private int g;

    public ojs(Context context, String str, int i, int i2, String str2, String str3) {
        this.e = str;
        this.g = i;
        this.d = context;
        this.f15741a = i2;
        this.c = str2;
        this.b = str3;
    }

    public ojs(Context context, String str, int i, int i2, String str2) {
        this.e = str;
        this.g = i;
        this.d = context;
        this.f15741a = i2;
        this.c = str2;
        this.b = "";
    }

    public String a() {
        return this.b;
    }

    public String e() {
        return this.e;
    }

    public int g() {
        return this.g;
    }

    public int c() {
        return this.f15741a;
    }

    public void d(int i) {
        this.f15741a = i;
    }

    public String d() {
        return this.c;
    }

    public void b(int i) {
        this.g = i;
        if (this.d == null) {
            LogUtil.h("ManagementViewCardData", "mContext is null");
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(ojs ojsVar) {
        return g() > ojsVar.g() ? 0 : -1;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ojs)) {
            return ((ojs) obj).e().equals(e());
        }
        return false;
    }

    public int hashCode() {
        return g();
    }

    public String toString() {
        return " mNeedShow = " + c() + ", position = " + this.g + ", cardname = " + this.e + ", cardId = " + this.c;
    }

    public ojs b() {
        return new ojs(this.d, e(), g(), c(), d(), a());
    }
}
