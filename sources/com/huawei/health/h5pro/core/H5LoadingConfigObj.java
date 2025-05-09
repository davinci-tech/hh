package com.huawei.health.h5pro.core;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.webkit.HpkManager;
import java.util.Locale;

/* loaded from: classes3.dex */
public class H5LoadingConfigObj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("bg")
    public String f2365a;
    public String b;

    @SerializedName("bgPad")
    public String c;

    @SerializedName("bgDark")
    public String d;

    @SerializedName("bgPadDark")
    public String e;

    @SerializedName("icon")
    public String h;

    @SerializedName("color")
    public String i;

    @SerializedName("iconDark")
    public String j;

    public String toString() {
        return "LoadingConfigObj{baseUrl='" + this.b + "', color='" + this.i + "', bg='" + this.f2365a + "', bgDark='" + this.d + "', bgPad='" + this.c + "', bgPadDark='" + this.e + "', icon='" + this.h + "', iconDark='" + this.j + "'}";
    }

    public void setBaseUrl(String str) {
        this.b = HpkManager.b.getBaseUrl(str);
    }

    public String getIconDark() {
        return e(this.j);
    }

    public String getIcon() {
        return e(this.h);
    }

    public String getColor() {
        return this.i;
    }

    public String getBgPadDark() {
        return e(this.e);
    }

    public String getBgPad() {
        return e(this.c);
    }

    public String getBgDark() {
        return e(this.d);
    }

    public String getBg() {
        return e(this.f2365a);
    }

    private String e(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.b)) ? "" : String.format(Locale.ENGLISH, "%s%s", this.b, str);
    }
}
