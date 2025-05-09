package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dqo {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("countryList")
    protected List<String> f11790a;

    @SerializedName("support")
    protected Integer b;

    @SerializedName("extInfo")
    protected Map c;

    @SerializedName("packageName")
    protected String d;

    @SerializedName("blockProductList")
    protected List<Object> e;

    @SerializedName("supportVersion")
    protected dqp h;

    public String c() {
        return this.d;
    }

    public Integer e() {
        return this.b;
    }

    public dqp b() {
        return this.h;
    }

    public Map a() {
        Map map = this.c;
        return map == null ? new HashMap() : map;
    }
}
