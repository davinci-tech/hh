package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Set;

/* loaded from: classes8.dex */
public final class mrr {
    public static void b(String str, int i) {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("MessageCenterSpUtil", "setMsgStatus huid null !");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(11001), b(accountInfo, str), String.valueOf(i), (StorageParams) null);
    }

    public static String c(String str, String str2) {
        String a2 = a(str);
        return TextUtils.isEmpty(a2) ? str2 : a2;
    }

    private static String a(String str) {
        if (str == null) {
            return "";
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("MessageCenterSpUtil", "getMsgStatus huid null !");
            return "";
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(11001), b(accountInfo, str));
        return TextUtils.isEmpty(b) ? "" : b;
    }

    public static void b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("MessageCenterSpUtil", "clearUserMsgs huid null !");
            return;
        }
        Set<String> d = SharedPreferenceManager.d(BaseApplication.e(), String.valueOf(11001));
        if (koq.b(d)) {
            return;
        }
        int size = d.size();
        String[] strArr = new String[size];
        d.toArray(strArr);
        String b = b(accountInfo);
        for (int i = 0; i < size; i++) {
            if (strArr[i].contains(b)) {
                SharedPreferenceManager.c(BaseApplication.e(), String.valueOf(11001), strArr[i]);
            }
        }
    }

    private static String b(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(strArr.length * 16);
        for (String str : strArr) {
            sb.append("_");
            sb.append(str);
        }
        return sb.toString();
    }
}
