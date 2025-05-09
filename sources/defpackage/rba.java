package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes7.dex */
public class rba {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwPayConstant.KEY_USER_ID)
    private long f16689a;

    @SerializedName("findType")
    private int c;

    @SerializedName("findContent")
    private String d;

    @SerializedName("acctType")
    private int e;

    public long a() {
        return this.f16689a;
    }

    public void c(long j) {
        this.f16689a = j;
    }

    public int e() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{userID=");
        sb.append(a());
        sb.append(", findType=");
        sb.append(e());
        sb.append(", findContent=");
        sb.append(d());
        if (e() == 0) {
            sb.append(", acctType='");
            sb.append(b());
        }
        sb.append('}');
        return sb.toString();
    }
}
