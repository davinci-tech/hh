package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Utils;

/* loaded from: classes4.dex */
public class gpo {
    public static boolean b() {
        return !a();
    }

    public static boolean a() {
        return Utils.o() || "1".equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1004));
    }
}
