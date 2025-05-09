package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class nzp extends nzr {
    private static final long serialVersionUID = 8128932739017579301L;
    private String d;

    public nzp(String str) {
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public nzb d() {
        nzb nzbVar = new nzb();
        if (TextUtils.isEmpty(this.d)) {
            LogUtil.h("JsonPlaceholder", "getJsonObject: clazz is null or json is null");
            return nzbVar;
        }
        try {
            return (nzb) new Gson().fromJson(CommonUtil.z(this.d), nzb.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("JsonPlaceholder", "getJsonObject: JsonSyntaxException occurred with parsing json.", this.d);
            return nzbVar;
        }
    }

    @Override // defpackage.nzr
    public String toString() {
        return "JsonPlaceholder{mJson='" + this.d + "', mValue='" + this.f15570a + "', mPlaceholderType=" + this.c + '}';
    }
}
