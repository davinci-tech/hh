package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class qvk implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startDay")
    private int f16611a;

    @SerializedName("subCategory")
    private int b;

    @SerializedName("category")
    private int c;

    @SerializedName(ParsedFieldTag.GOAL)
    private qvi d;

    @SerializedName("stage")
    private int e;

    @SerializedName("weightManagerType")
    private int g;

    @SerializedName("userInfo")
    private qvr j;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/simplefat/v1/calculateGoal";
    }

    public int c() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int i() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int e() {
        return this.f16611a;
    }

    public void d(int i) {
        this.f16611a = i;
    }

    public qvi a() {
        return this.d;
    }

    public void b(qvi qviVar) {
        this.d = qviVar;
    }

    public qvr h() {
        return this.j;
    }

    public void e(qvr qvrVar) {
        this.j = qvrVar;
    }

    public int b() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public String toString() {
        return "CreateGoalReq{category=" + this.c + ", subCategory=" + this.b + ", weightManagerType=" + this.g + ", startDay=" + this.f16611a + ", stage=" + this.e + ", goal=" + this.d + ", userInfo=" + this.j + '}';
    }
}
