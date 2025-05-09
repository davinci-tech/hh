package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.operation.ble.BleConstants;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class mob implements Serializable, Cloneable {
    private static final long serialVersionUID = -6971445234843367795L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("duration")
    private long f15077a;

    @SerializedName("endTime")
    private long b;

    @SerializedName("completionRate")
    private float c;

    @SerializedName("actualCalorie")
    private float d;

    @SerializedName("actualDistance")
    private int e;

    @SerializedName("inPlan")
    private int f;

    @SerializedName("startTime")
    private long g;

    @SerializedName(BleConstants.SPORT_TYPE)
    private int h;

    @SerializedName("extraInfo")
    private String i;

    @SerializedName("recordId")
    private String j;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)
    private int k;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String l;

    @SerializedName("userWeight")
    private double m;

    public String n() {
        return this.l;
    }

    public void b(String str) {
        this.l = str;
    }

    public String j() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public int f() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public long i() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public long a() {
        return this.b;
    }

    public void e(long j) {
        this.b = j;
    }

    public long c() {
        return this.f15077a;
    }

    public void c(long j) {
        this.f15077a = j;
    }

    public int h() {
        return this.f;
    }

    public void e(int i) {
        this.f = i;
    }

    public String g() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public int e() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public float b() {
        return this.d;
    }

    public void e(float f) {
        this.d = f;
    }

    public int m() {
        return this.k;
    }

    public void d(int i) {
        this.k = i;
    }

    public float d() {
        return this.c;
    }

    public void b(float f) {
        this.c = f;
    }

    public void e(double d) {
        this.m = d;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
