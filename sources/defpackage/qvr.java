package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;

/* loaded from: classes7.dex */
public class qvr {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("age")
    private int f16617a;

    @SerializedName("weight")
    private double c;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int d;

    @SerializedName("height")
    private int e;

    public void c(int i) {
        this.d = i;
    }

    public void e(int i) {
        this.f16617a = i;
    }

    public int d() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public double c() {
        return this.c;
    }

    public void c(double d) {
        this.c = d;
    }
}
