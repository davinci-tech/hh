package com.huawei.hms.support.hwid.common.c;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.e.c;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hms.support.hwid.common.e.j;
import com.huawei.up.request.HttpRequestBase;

/* loaded from: classes9.dex */
public abstract class a {
    protected String d;
    private String f;

    /* renamed from: a, reason: collision with root package name */
    protected int f5998a = 200;
    protected int b = -1;
    protected int c = -1;
    private int e = 0;
    private EnumC0153a g = EnumC0153a.XMLType;

    /* renamed from: com.huawei.hms.support.hwid.common.c.a$a, reason: collision with other inner class name */
    public enum EnumC0153a {
        XMLType,
        URLType,
        JSONType,
        Stream,
        FileType
    }

    public abstract String a();

    protected abstract String a(Context context);

    public Bundle b() {
        return c();
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt("responseCode", this.f5998a);
        bundle.putInt("resultCode", this.b);
        bundle.putInt("errorCode", this.c);
        bundle.putString(HttpRequestBase.TAG_ERROR_DESC, this.d);
        return bundle;
    }

    public void a(String str, int i) {
        this.e = i;
        String a2 = a();
        String str2 = "https://" + str + HttpRequestBase.UP_SERVER_URL_CHINA;
        g.a("HttpRequest", "setGlobalSiteId, AsUrl::=" + str2, false);
        this.f = str2 + a2;
        int i2 = this.e;
        this.f = j.a(this.f, new String[]{"\\{0\\}", (i2 < 1 || i2 > 999) ? "" : String.valueOf(i)});
        g.a("HttpRequest", "Set mGlobalHostUrl::=" + this.f, false);
    }

    public String d() {
        if (TextUtils.isEmpty(this.f)) {
            return a() + "?Version=66300&cVersion=" + c.a();
        }
        return this.f + "?Version=66300&cVersion=" + c.a();
    }
}
