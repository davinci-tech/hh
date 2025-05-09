package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dqr {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("configName")
    protected String f11792a;

    @SerializedName("fileData")
    protected List<dqs> b;

    @SerializedName("filterCondition")
    protected List<dqv> c;

    @SerializedName("selectRulekey")
    protected String d;

    @SerializedName("selectRulevalue")
    protected String e;

    public dqr(String str) {
        this.f11792a = str;
    }

    public String e() {
        return this.f11792a;
    }

    public List<dqs> d() {
        List<dqs> list = this.b;
        return list == null ? new ArrayList() : list;
    }

    public void b(List<dqs> list) {
        this.b = list;
    }

    public List<dqv> c() {
        List<dqv> list = this.c;
        return list == null ? new ArrayList() : list;
    }

    public void a(List<dqv> list) {
        this.c = list;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }
}
