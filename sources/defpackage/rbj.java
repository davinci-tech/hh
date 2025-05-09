package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes7.dex */
public class rbj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isConfigurable")
    private Integer f16696a;

    @SerializedName("isNotification")
    private Integer b;

    @SerializedName("certifiedModel")
    private List<String> c;

    @SerializedName("isRecycleQuestion")
    private Integer d;

    @SerializedName("product")
    private String e;

    @SerializedName("questionEndTime")
    private String f;

    @SerializedName("sku")
    private List<String> g;

    @SerializedName("resourceId")
    private String h;

    @SerializedName("residenceDuration")
    private Integer i;

    @SerializedName("questionStartTime")
    private String j;

    public void d(Integer num) {
        this.b = num;
    }

    public Integer b() {
        return this.b;
    }

    public void e(Integer num) {
        this.d = num;
    }

    public Integer d() {
        return this.d;
    }

    public void a(String str) {
        this.j = str;
    }

    public int[] e() {
        int[] iArr = {8, 0};
        if (TextUtils.isEmpty(this.j)) {
            return iArr;
        }
        String[] split = this.j.split(":");
        if (!StringUtils.a(split[0])) {
            return iArr;
        }
        iArr[0] = Integer.parseInt(split[0]);
        if (split.length > 1 && StringUtils.a(split[1])) {
            iArr[1] = Integer.parseInt(split[1]);
        }
        return iArr;
    }

    public void b(String str) {
        this.f = str;
    }

    public int[] c() {
        int[] iArr = {23, 0};
        if (TextUtils.isEmpty(this.f)) {
            return iArr;
        }
        String[] split = this.f.split(":");
        if (!StringUtils.a(split[0])) {
            return iArr;
        }
        iArr[0] = Integer.parseInt(split[0]);
        if (split.length > 1 && StringUtils.a(split[1])) {
            iArr[1] = Integer.parseInt(split[1]);
        }
        return iArr;
    }

    public void c(List<String> list) {
        this.c = list;
    }

    public List<String> a() {
        return this.c;
    }

    public void b(Integer num) {
        this.i = num;
    }

    public Integer i() {
        return this.i;
    }

    public String toString() {
        return "NpsInfo{resourceId='" + this.h + "', product='" + this.e + "', isConfigurable=" + this.f16696a + ", isNotification=" + this.b + ", isRecycleQuestion=" + this.d + ", questionStartTime='" + this.j + "', questionEndTime='" + this.f + "', sku=" + this.g + ", certifiedModel=" + this.c + ", residenceDuration=" + this.i + '}';
    }
}
