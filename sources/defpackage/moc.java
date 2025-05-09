package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes6.dex */
public class moc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("actions")
    private List<mnr> f15078a;

    @SerializedName(ParsedFieldTag.ACTION_TYPE)
    private int b;

    @SerializedName("dayNo")
    private int c;

    @SerializedName("actionId")
    private String d;

    @SerializedName("operationType")
    private int e;

    @SerializedName("planId")
    private String f;

    @SerializedName("planType")
    private int g;

    @SerializedName("weekNo")
    private int i;

    public String c() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public int d() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public int e() {
        return this.i;
    }

    public void a(int i) {
        this.c = i;
    }

    public int b() {
        return this.c;
    }

    public List<mnr> a() {
        return this.f15078a;
    }

    public void d(List<mnr> list) {
        this.f15078a = list;
    }
}
