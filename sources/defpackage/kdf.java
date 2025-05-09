package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
class kdf {

    /* renamed from: a, reason: collision with root package name */
    private String f14301a = "GenerateCommandInfo";

    kdf() {
    }

    public String e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h(this.f14301a, "getSeizeInfo input parameter is null.");
            return null;
        }
        StringBuilder sb = new StringBuilder(10);
        sb.append(b(11, "seize"));
        sb.append(b(12, str));
        return sb.toString();
    }

    public String d(int i, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h(this.f14301a, "getReplyInfo input parameter is null.");
            return null;
        }
        StringBuilder sb = new StringBuilder(10);
        sb.append(d(15, i));
        sb.append(b(16, str));
        sb.append(b(17, str));
        return sb.toString();
    }

    public String b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h(this.f14301a, "requestSeize input parameter is null.");
            return null;
        }
        StringBuilder sb = new StringBuilder(10);
        sb.append(b(8, str));
        sb.append(b(9, str2));
        sb.append(b(10, str3));
        return sb.toString();
    }

    private String b(int i, String str) {
        String c = cvx.c(str);
        return cvx.e(i) + cvx.d(c.length() / 2) + c;
    }

    private String d(int i, int i2) {
        return cvx.e(i) + cvx.d(1) + cvx.e(i2);
    }
}
