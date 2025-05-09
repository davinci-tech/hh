package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;

/* loaded from: classes.dex */
public class gmt {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("examinationReport")
    private String f12867a;

    @SerializedName("deviceCode")
    private String b;

    @SerializedName("examinationInstitution")
    private String c;

    @SerializedName("examinationDate")
    private int d;

    @SerializedName("extendData")
    private String e;

    @SerializedName("recordType")
    private int f;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int g;

    @SerializedName("name")
    private String h;

    @SerializedName("generateTime")
    private long i;

    @SerializedName("recordName")
    private String j;

    @SerializedName("reportTime")
    private long l;

    @SerializedName("remarks")
    private String n;

    @SerializedName("reminderTime")
    private long o;

    public long o() {
        return this.l;
    }

    public void a(long j) {
        this.l = j;
    }

    public long i() {
        return this.i;
    }

    public void d(long j) {
        this.i = j;
    }

    public int g() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public void e(int i) {
        this.d = i;
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.f = i;
    }

    public int j() {
        return this.f;
    }

    public void h(String str) {
        this.j = str;
    }

    public String h() {
        return this.j;
    }

    public String f() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public void j(String str) {
        this.n = str;
    }

    public String k() {
        return this.n;
    }

    public String a() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String c() {
        return this.f12867a;
    }

    public void c(String str) {
        this.f12867a = str;
    }

    public long m() {
        return this.o;
    }

    public void b(long j) {
        this.o = j;
    }

    public String d() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String e() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }
}
