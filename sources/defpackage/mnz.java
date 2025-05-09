package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.List;

/* loaded from: classes6.dex */
public class mnz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("targetClockInDayList")
    private List<Integer> f15074a;

    @SerializedName("realClockInDayList")
    private List<Integer> b;

    @SerializedName("totalDuration")
    private int c;

    @SerializedName("totalCalorie")
    private float d;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private float e;

    @SerializedName("trainCompleteRate")
    private float j;

    public float g() {
        return this.j;
    }

    public void a(float f) {
        this.j = f;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public float d() {
        return this.d;
    }

    public List<Integer> b() {
        return this.f15074a;
    }

    public void a(List<Integer> list) {
        this.f15074a = list;
    }

    public List<Integer> a() {
        return this.b;
    }

    public void b(List<Integer> list) {
        this.b = list;
    }

    public float e() {
        return this.e;
    }

    public void e(float f) {
        this.e = f;
    }

    public void c(float f) {
        this.d = f;
    }
}
