package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class mvq {

    @SerializedName("lastEnterTime")
    private long c;

    @SerializedName(BleConstants.SPORT_TYPE)
    private int d;

    @SerializedName("backgroundList")
    private List<Integer> b = new ArrayList();

    @SerializedName("recommendList")
    private List<Integer> e = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("stickerList")
    private List<Integer> f15206a = new ArrayList();

    @SerializedName("watermarkList")
    private List<Integer> h = new ArrayList();

    public mvq(int i) {
        this.d = i;
    }

    public void e(long j) {
        this.c = j;
    }

    public List<Integer> d() {
        return this.e;
    }

    public void e(List<Integer> list) {
        this.e = list;
    }

    public List<Integer> b() {
        return this.b;
    }

    public void d(List<Integer> list) {
        this.b = list;
    }

    public List<Integer> c() {
        return this.h;
    }

    public void c(List<Integer> list) {
        this.h = list;
    }

    public List<Integer> e() {
        return this.f15206a;
    }

    public void a(List<Integer> list) {
        this.f15206a = list;
    }

    public List<Integer> a(int i) {
        if (i == 1) {
            return this.e;
        }
        if (i == 2) {
            return this.b;
        }
        if (i == 3) {
            return this.h;
        }
        if (i == 4) {
            return this.f15206a;
        }
        return Collections.emptyList();
    }
}
