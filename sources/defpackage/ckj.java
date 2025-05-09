package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.fitnessdatatype.ResultUtils;

/* loaded from: classes8.dex */
public class ckj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("heartRete")
    private int f768a;
    private int b;
    private String c;

    @SerializedName("offbodyValue")
    private int d;
    private int e;
    private int f;

    public void a(String str) {
        this.c = (String) ResultUtils.commonFunc(str);
    }

    public void e(int i) {
        this.e = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    public void d(int i) {
        this.d = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    public void a(int i) {
        this.f768a = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    public void b(int i) {
        this.b = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    public void c(int i) {
        this.f = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "PostureResult{postureId='" + this.c + "', completion=" + this.e + ", offBodyValue=" + this.d + ", heartRate=" + this.f768a + ", calorie=" + this.b + ", userTime=" + this.f + '}';
    }
}
