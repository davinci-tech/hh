package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;

/* loaded from: classes3.dex */
public class dkx {
    public static boolean b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("EmotionUtils", "countryCode is empty");
            return true;
        }
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968670_res_0x7f04005e);
        if (stringArray == null || stringArray.length < 1) {
            LogUtil.b("EmotionUtils", "isEmotionPrivacyNotAllowed() if ((countryList == null) || (countryList.length < 1))");
            return false;
        }
        LogUtil.a("EmotionUtils", "isEmotionPrivacyNotAllowed() country=", accountInfo);
        for (String str : stringArray) {
            if (accountInfo.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
