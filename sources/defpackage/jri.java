package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jri {
    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwMenstrualHelper", "queryMenstrualSwitch callback null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jri.5
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("com.huawei.health.mc");
                    if (userPreference == null || userPreference.getValue() == null) {
                        LogUtil.h("HwMenstrualHelper", "queryMenstrualSwitch userPreference value empty");
                        iBaseResponseCallback.d(-1, null);
                        return;
                    }
                    String value = userPreference.getValue();
                    LogUtil.a("HwMenstrualHelper", "queryMenstrualSwitch value:", value);
                    try {
                        MenstrualSwitchStatus menstrualSwitchStatus = new MenstrualSwitchStatus();
                        JSONObject jSONObject = new JSONObject(value);
                        menstrualSwitchStatus.setMasterSwitch(jSONObject.getInt("masterSwitch"));
                        menstrualSwitchStatus.setMenstruationStartRemindSwitch(jSONObject.getInt("menstrualStartSwitch"));
                        menstrualSwitchStatus.setMenstruationEndRemindSwitch(jSONObject.getInt("menstrualEndSwitch"));
                        menstrualSwitchStatus.setEasyPregnancyStartSwitch(jSONObject.getInt("easyPregnancyStartSwitch"));
                        menstrualSwitchStatus.setEasyPregnancyEndSwitch(jSONObject.getInt("easyPregnancyEndSwitch"));
                        iBaseResponseCallback.d(0, menstrualSwitchStatus);
                    } catch (JSONException unused) {
                        LogUtil.b("HwMenstrualHelper", "queryMenstrualSwitch JSONException");
                        iBaseResponseCallback.d(-1, null);
                    }
                }
            });
        }
    }
}
