package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class iuj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startTime")
    private long f13616a;

    @SerializedName("relatedDatas")
    private List<iuh> b;

    @SerializedName("datasource")
    private String c;

    @SerializedName("extensions")
    private String d;

    @SerializedName("orderId")
    private String e;

    @SerializedName("interpretTime")
    private long g;

    @SerializedName("status")
    private int i;

    public List<iuh> b() {
        return this.b;
    }

    public void b(List<iuh> list) {
        this.b = list;
    }

    public int e() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.c = str;
    }

    public void c(long j) {
        this.f13616a = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("ts=" + this.g);
        sb.append(",rds=");
        sb.append(this.b);
        sb.append(",status=" + this.i);
        sb.append(",es=" + this.d);
        sb.append(",orderId" + this.e);
        sb.append(",startTime" + this.f13616a);
        return sb.toString();
    }
}
