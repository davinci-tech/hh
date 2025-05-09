package defpackage;

import android.os.Build;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class ogx {
    public static boolean e() {
        if (Build.VERSION.SDK_INT == 28 && (BaseActivity.isFlyme() || BaseActivity.isMiui())) {
            LogUtil.a("VmallWebViewUtils", "android P: isFlyme or isMiui");
            return false;
        }
        if (!LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() && !Utils.o()) {
            return true;
        }
        LogUtil.a("VmallWebViewUtils", "Is kids: ", Boolean.valueOf(LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()), ", is oversea: ", Boolean.valueOf(Utils.o()));
        return false;
    }
}
