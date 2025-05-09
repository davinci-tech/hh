package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.operation.utils.TodoTaskInterface;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class gka {

    /* renamed from: a, reason: collision with root package name */
    private String f12833a;

    @SerializedName("currentValue")
    private String b;

    @SerializedName("linkUrl")
    private String c;

    @SerializedName("toDoId")
    private long d;
    private int e;

    @SerializedName("createTime")
    private long f;

    @SerializedName("status")
    private int g;

    @SerializedName(WorkoutRecord.Extend.COURSE_TARGET_VALUE)
    private String h;

    @SerializedName("targetUnit")
    private String i;

    @SerializedName("title")
    private String j;
    private TodoTaskInterface k;

    @SerializedName("toDoContent")
    private String m;

    @SerializedName("toDoType")
    private int n;

    public String toString() {
        return "TodoCardRecyModel{title='" + this.j + "', name='" + this.f12833a + "', type=" + this.n + ", time=" + this.f + ", todoTaskObject=" + this.k + ", id=" + this.d + ", status=" + this.g + ", linkUrl='" + this.c + "', todoContent='" + this.m + "', targetValue='" + this.h + "', targetUnit='" + this.i + "', currentValue='" + this.b + "'}";
    }

    public String n() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public String a() {
        return this.f12833a;
    }

    public void c(String str) {
        this.f12833a = str;
    }

    public long h() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public int k() {
        return this.n;
    }

    public void b(int i) {
        this.n = i;
    }

    public TodoTaskInterface o() {
        return this.k;
    }

    public void d(TodoTaskInterface todoTaskInterface) {
        this.k = todoTaskInterface;
    }

    public long b() {
        return this.d;
    }

    public int i() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String m() {
        return this.m;
    }

    public void e(String str) {
        this.m = str;
    }

    public String g() {
        return this.h;
    }

    public String j() {
        return this.i;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String f() {
        TodoTaskInterface todoTaskInterface = this.k;
        if (todoTaskInterface != null) {
            return todoTaskInterface.getProgress();
        }
        if (TextUtils.isEmpty(this.h)) {
            return "";
        }
        int e = nsn.e(this.h);
        return nsf.a(R.plurals._2130903354_res_0x7f03013a, e, UnitUtil.e(nsn.e(this.b), 1, 0), UnitUtil.e(e, 1, 0));
    }

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }
}
