package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class nwp {
    public static void c(String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MenstrualPredictUtils", "getSwitchState switchKey is null");
        } else {
            jqi.a().getSwitchSetting(b(str), new IBaseResponseCallback() { // from class: nws
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    nwp.b(IBaseResponseCallback.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("MenstrualPredictUtils", "initSwitchStatus errorCode = ", Integer.valueOf(i));
        String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
        LogUtil.a("MenstrualPredictUtils", "initSwitchStatus switchStatus = ", str);
        iBaseResponseCallback.onResponse(0, Boolean.valueOf("1".equals(str)));
    }

    private static String b(String str) {
        "menstrualPredictSwitch".equals(str);
        return "menstrual_predict_switch";
    }

    public static void a(String str, String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("MenstrualPredictUtils", "setSwitchStatusBi switchKey or isChecked is null");
        } else {
            jqi.a().sendSettingSwitchCommand("hw.unitedevice.physiological", "hw.watch.health.physiological", 900300012L, str2);
            jqi.a().setSwitchSetting(b(str), str2, new IBaseResponseCallback() { // from class: nwq
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    nwp.e(IBaseResponseCallback.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("MenstrualPredictUtils", "setMenstrualPredictStatus errorCode = ", Integer.valueOf(i));
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onResponse(i, obj);
        }
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MenstrualPredictUtils", "setSwitchStatusBi isChecked is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("event", str);
        hashMap.put("setPath", "1");
        String value = AnalyticsValue.MENSTRUAL_PREDICT_SWITCH_EVENT.value();
        LogUtil.a("MenstrualPredictUtils", "setSwitchEventBi value = ", value, ", map = ", hashMap.toString());
        ixx.d().d(BaseApplication.e(), value, hashMap, 0);
    }
}
