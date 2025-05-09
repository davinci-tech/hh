package defpackage;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes7.dex */
public class rbc {

    @SerializedName("appID")
    private String b;

    @SerializedName("channel")
    private String c;

    @SerializedName(DeviceInfo.TAG_DEVICE_ID)
    private String d;

    @SerializedName("accessToken")
    private String e;

    @SerializedName("fromPkg")
    private String h;

    @SerializedName(CommonUtil.PARAM_PUSH_TOKEN)
    private String i;

    @SerializedName(HwPayConstant.KEY_USER_ID)
    private long n;

    @SerializedName("serviceToken")
    private String o;

    @SerializedName("deviceType")
    private int j = 0;

    @SerializedName("phoneType")
    private String f = rbu.d();

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String g = Build.DISPLAY;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("appVersion")
    private String f16691a = String.valueOf(rbo.d());

    public rbc(String str, String str2, String str3, String str4) {
        this.b = str;
        this.c = str3;
        this.i = str2;
        this.h = str4;
    }

    public long h() {
        return this.n;
    }

    public void d(long j) {
        this.n = j;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String j() {
        return this.h;
    }

    public String d() {
        return this.f16691a;
    }

    public int a() {
        return this.j;
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String e() {
        return this.b;
    }

    public final void e(String str) {
        this.e = str;
    }

    public String c() {
        return this.c;
    }

    public String i() {
        return this.i;
    }

    public String toString() {
        return "{deviceType=" + a() + ", userID=" + h() + ", deviceID=" + b() + ", appID=" + e() + ", channel=" + c() + ", serviceToken=" + this.o + ", accessToken=" + this.e + ", pushToken=" + i() + ", phoneType=" + f() + ", romVersion=" + g() + ", appVersion='" + d() + ", fromPkg=" + j() + '}';
    }
}
