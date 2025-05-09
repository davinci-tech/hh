package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.List;

/* loaded from: classes3.dex */
public class fdf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("advice_to_do")
    private List<fcf> f12457a;

    @SerializedName("latest_problem")
    private List<Integer> b;

    @SerializedName("monthly_problem")
    private int c;

    @SerializedName("age")
    private int d;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int e;

    @SerializedName("time_zone")
    private int f;

    @SerializedName("task_to_do")
    private List<fde> g;

    @SerializedName("rhythm")
    private int i;

    public void c(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.e = i;
    }

    public void a(int i) {
        this.i = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.f = i;
    }

    public void c(List<fcf> list) {
        this.f12457a = list;
    }

    public void a(List<fde> list) {
        this.g = list;
    }

    public void b(List<Integer> list) {
        this.b = list;
    }
}
