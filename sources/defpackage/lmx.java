package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes5.dex */
public class lmx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deepLinkUrl")
    private String f14772a;

    @SerializedName(ParsedFieldTag.MEDAL_ID)
    private String b;

    @SerializedName("action")
    private int c;

    @SerializedName(ParsedFieldTag.LIGHT_DESC)
    private String d;

    @SerializedName("gainTime")
    private long e;

    @SerializedName("message")
    private String f;

    @SerializedName("support3D")
    private int g;

    @SerializedName("medalName")
    private String h;

    @SerializedName("session")
    private String i;

    @SerializedName("state")
    private int j;

    @SerializedName(HwPayConstant.KEY_USER_NAME)
    private String n;

    @SerializedName("supportDeeplinkUrl")
    private int o;

    public void i(String str) {
        this.i = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void a(long j) {
        this.e = j;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.h = str;
    }

    public void e(String str) {
        this.f = str;
    }

    public void c(int i) {
        this.o = i;
    }

    public void a(String str) {
        this.f14772a = str;
    }

    public void e(int i) {
        this.g = i;
    }

    public void a(int i) {
        this.j = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public void g(String str) {
        this.n = str;
    }
}
