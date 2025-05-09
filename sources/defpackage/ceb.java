package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.operation.utils.TodoTaskInterface;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;

/* loaded from: classes3.dex */
public class ceb implements TodoTaskInterface {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("activityContext")
    private String f657a;

    @SerializedName("activityName")
    private String b;

    @SerializedName("activityLink")
    private String c;

    @SerializedName("activityId")
    private String d;

    @SerializedName("imgUrl")
    private String e;

    @SerializedName("activityTargetTodo")
    private int f;

    @SerializedName("activityPosition")
    private int g;

    @SerializedName("activityStatus")
    private int h;

    @SerializedName(SyncDataConstant.BI_KEY_ACTIVITY_TYPE)
    private int j;

    @SerializedName("continuityTodo")
    private int k;

    @SerializedName("endDate")
    private String l;

    @SerializedName("haveHistoryTodo")
    private int m;

    @SerializedName("completeFlagTodo")
    private int n;

    @SerializedName("numberOfPeople")
    private String o;

    @SerializedName("matchBeginDate")
    private String p;

    @SerializedName(CommonUtil.PAGE_TYPE)
    private String q;

    @SerializedName("timeZone")
    private String s;

    @SerializedName(CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH)
    private int t;

    @SerializedName("teamFlag")
    private int u;

    @SerializedName("templateType")
    private int v;

    @SerializedName("targetDaysTodo")
    private int w;

    @SerializedName("rotinePosition")
    private int x;

    @SerializedName(ParsedFieldTag.BEGIN_DATE)
    private String y;

    @SerializedName("appVersion")
    private String i = "";

    @SerializedName("workoutUserLable")
    private String r = "";

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public int getIconId() {
        return 0;
    }

    public String x() {
        return this.r;
    }

    public void o(String str) {
        this.r = str;
    }

    public String u() {
        return this.q;
    }

    public void n(String str) {
        this.q = str;
    }

    public String v() {
        return this.s;
    }

    public void k(String str) {
        this.s = str;
    }

    public String c() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String t() {
        return this.y;
    }

    public void i(String str) {
        this.y = str;
    }

    public String n() {
        return this.l;
    }

    public void f(String str) {
        this.l = str;
    }

    public String o() {
        return this.o;
    }

    public void j(String str) {
        this.o = str;
    }

    public int h() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public int f() {
        return this.j;
    }

    public void b(int i) {
        this.j = i;
    }

    public int p() {
        return this.v;
    }

    public void h(int i) {
        this.v = i;
    }

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public int q() {
        return this.x;
    }

    public void i(int i) {
        this.x = i;
    }

    public int j() {
        return this.g;
    }

    public void d(int i) {
        this.g = i;
    }

    public String s() {
        return this.p;
    }

    public void h(String str) {
        this.p = str;
    }

    public String e() {
        return this.f657a;
    }

    public void d(String str) {
        this.f657a = str;
    }

    public int l() {
        return this.m;
    }

    public void g(int i) {
        this.m = i;
    }

    public int g() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int m() {
        return this.k;
    }

    public void j(int i) {
        this.k = i;
    }

    public int r() {
        return this.w;
    }

    public void f(int i) {
        this.w = i;
    }

    public int k() {
        return this.n;
    }

    public void e(int i) {
        this.n = i;
    }

    public String i() {
        return this.i;
    }

    public void g(String str) {
        this.i = str;
    }

    public void o(int i) {
        this.t = i;
    }

    public int y() {
        return this.t;
    }

    public int w() {
        return this.u;
    }

    public void l(int i) {
        this.u = i;
    }

    public boolean ac() {
        return this.u == 1;
    }

    public String toString() {
        return "OperationObject{activityId='" + this.d + "', activityName='" + this.b + "', activityImgUrl='" + this.e + "', startDate='" + this.y + "', endDate='" + this.l + "', mTimeZone='" + this.s + "', joinNum='" + this.o + "', activityStatus=" + this.h + ", activityType=" + this.j + ", teamFlag=" + this.u + ", matchBeginDate='" + this.p + "', activityContext='" + this.f657a + "', haveHistoryTodo=" + this.m + ", activityTargetTodo=" + this.f + ", continuityTodo=" + this.k + ", targetDaysTodo=" + this.w + ", completeFlagTodo=" + this.n + ", templateType=" + this.v + ", activityLink='" + this.c + "', rotinePosition=" + this.x + ", activityPosition=" + this.g + ", mPageType=" + this.q + ", mWorkoutUserLable=" + this.r + '}';
    }

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public String getProgress() {
        return "";
    }
}
