package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.Agent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcrowdtestapi.HealthFeedbackCallback;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.up.utils.Constants;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jgn {
    private static final Object d = new Object();
    private static jgn e;

    private jgn() {
    }

    public static jgn c() {
        jgn jgnVar;
        synchronized (d) {
            if (e == null) {
                e = new jgn();
            }
            jgnVar = e;
        }
        return jgnVar;
    }

    public void e(Context context, HealthFeedbackParams healthFeedbackParams, HealthFeedbackCallback healthFeedbackCallback) {
        jep jepVar = new jep();
        jepVar.c(context.getPackageName());
        jepVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        jepVar.b(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        jepVar.h("at");
        String a2 = CommonUtil.a(BaseApplication.getContext(), false);
        if (TextUtils.isEmpty(a2)) {
            a2 = "000000000000000";
        }
        jepVar.a(a2);
        String deviceType = SharedPreferenceUtil.getInstance(context).getDeviceType();
        jepVar.a(Integer.valueOf(deviceType != null ? CommonUtil.m(context, deviceType) : 0));
        jepVar.g(LoginInit.getInstance(context).getAccountInfo(1000));
        jepVar.d(Agent.OS_NAME);
        jepVar.d(Integer.valueOf(Constants.HEALTH_APP_LOGIN_CHANNEL));
        jeq.e().gotoFeedback(context, jepVar, healthFeedbackParams, healthFeedbackCallback);
    }
}
