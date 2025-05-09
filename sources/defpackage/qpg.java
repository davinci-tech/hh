package defpackage;

import android.text.SpannableStringBuilder;
import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qpg {
    private long b;
    private boolean c;
    private long g;
    private List<qkg> e = new ArrayList();
    private volatile Map<String, SpannableStringBuilder> i = new HashMap();
    private List<HiHealthData> d = new ArrayList();
    private List<HiHealthData> j = new ArrayList();
    private long h = 0;

    /* renamed from: a, reason: collision with root package name */
    private long f16533a = 0;
    private String f = "TEMPERATURE_MIN_MAX";

    public void e(String str) {
        this.f = str;
    }

    public String b() {
        return this.f;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public void e(long j) {
        this.g = j;
    }

    public long c() {
        return this.g;
    }

    public void d(long j) {
        this.b = j;
    }

    public void b(long j, long j2, long j3) {
        this.e.clear();
        this.h = j;
        this.f16533a = j2;
        this.b = j3;
    }

    public void e(Map<String, SpannableStringBuilder> map) {
        this.i = map;
    }

    public Map<String, SpannableStringBuilder> a() {
        return this.i;
    }

    public List<HiHealthData> e() {
        return this.j;
    }

    public void e(List<HiHealthData> list) {
        this.j = list;
    }

    public String toString() {
        return "TemperatureData{mHealthDataList=" + this.e + ", mJumpTime=" + this.g + '}';
    }
}
