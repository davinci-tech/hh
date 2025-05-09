package defpackage;

import com.huawei.ads.fund.util.StringUtils;

/* loaded from: classes2.dex */
public class we {

    /* renamed from: a, reason: collision with root package name */
    private String f17727a;
    private int b;
    private int d;
    private int e;

    public void e() {
        this.d++;
    }

    public void b() {
        this.e++;
    }

    public void d(String str) {
        this.f17727a = str;
    }

    public void c(int i) {
        this.b = i;
    }

    public String d() {
        if (StringUtils.isBlank(this.f17727a)) {
            return null;
        }
        return this.f17727a + "," + this.e + "," + this.d + "," + this.b;
    }
}
