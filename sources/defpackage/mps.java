package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.watchface.mvp.model.webview.JsNetwork;

/* loaded from: classes6.dex */
public class mps {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("email")
    private String f15102a;

    @SerializedName(JsNetwork.NetworkType.MOBILE)
    private String b;

    @SerializedName("huid")
    private long c;

    @SerializedName(HealthZonePushReceiver.MEMBER_HUID)
    private Long d;

    @SerializedName("headPictureURL")
    private String e;

    @SerializedName("pushSubType")
    private int g;

    @SerializedName("nickname")
    private String h;

    public void b(String str) {
        this.e = str;
    }

    public void b(Long l) {
        this.d = l;
    }

    public void a(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.f15102a = str;
    }

    public void c(int i) {
        this.g = i;
    }

    public void a(long j) {
        this.c = j;
    }

    public void d(String str) {
        this.h = str;
    }
}
