package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.ui.commonui.R$string;
import java.util.Locale;

/* loaded from: classes7.dex */
public class sdt {
    public static void e(int i) {
        LogUtil.h("ConstantValues", "errorCode = ", Integer.valueOf(i));
        Context context = BaseApplication.getContext();
        if (i == 1002 || i == 1004) {
            ThirdPartyLoginManager.getInstance().gotoStTimeActivity();
        } else if (i == 1003) {
            nrh.d(context, String.format(Locale.ENGLISH, context.getResources().getString(R$string.IDS_system_time_error), nsn.i(context)));
        } else {
            LogUtil.h("ConstantValues", "code not match");
        }
    }
}
