package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes3.dex */
public class eyw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(JsUtil.SCORE)
    private int f12388a;

    @SerializedName("endTime")
    private long b;

    @SerializedName("grade")
    private int d;

    public void e(long j) {
        this.b = j;
    }

    public int a() {
        return this.f12388a;
    }

    public void b(int i) {
        this.f12388a = i;
    }

    public void c(int i) {
        this.d = i;
    }

    public int e() {
        return this.d;
    }

    public String toString() {
        return "PressureMeasureBean{mStressEndTime=" + this.b + ", mStressScore=" + this.f12388a + ", mStressGrade=" + this.d + '}';
    }
}
