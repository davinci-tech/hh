package defpackage;

import com.huawei.health.tradeservice.pay.ReservedInfor;

/* loaded from: classes4.dex */
public class gku {

    /* renamed from: a, reason: collision with root package name */
    private String f12842a;
    private int b;
    private String c;
    private ReservedInfor e;

    public ReservedInfor e() {
        return this.e;
    }

    public void a(ReservedInfor reservedInfor) {
        this.e = reservedInfor;
    }

    public void d(String str) {
        this.f12842a = str;
    }

    public String a() {
        return this.f12842a;
    }

    public void d(int i) {
        this.b = i;
    }

    public int c() {
        return this.b;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.c;
    }

    public String toString() {
        return "ProductId = " + this.f12842a + " PriceType = " + this.b + " AppPayLoad = " + this.c + " ReservedInfor = " + this.e;
    }
}
