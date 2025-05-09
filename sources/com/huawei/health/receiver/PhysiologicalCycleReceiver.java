package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.receiver.PhysiologicalCycleReceiver;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jfa;
import defpackage.jfq;
import defpackage.nsf;
import defpackage.scz;
import defpackage.sqp;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class PhysiologicalCycleReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        final String stringExtra = intent.getStringExtra("switchType");
        LogUtil.a("PhysiologicalCycleReceiver", "switchType = ", stringExtra, ", currTime = ", Long.valueOf(System.currentTimeMillis()));
        ThreadPoolManager.d().execute(new Runnable() { // from class: ezo
            @Override // java.lang.Runnable
            public final void run() {
                PhysiologicalCycleReceiver.this.d(context, stringExtra);
            }
        });
    }

    public /* synthetic */ void d(Context context, String str) {
        if (b() || !Utils.i()) {
            return;
        }
        c(context, str);
    }

    private boolean b() {
        Map<String, DeviceCapability> a2 = jfq.c().a(2, "", "PhysiologicalCycleReceiver");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("PhysiologicalCycleReceiver", "isDeviceSupport, capabilityMap is null or empty");
            return false;
        }
        for (DeviceCapability deviceCapability : a2.values()) {
            if (deviceCapability == null) {
                LogUtil.h("PhysiologicalCycleReceiver", "isDeviceSupport, deviceCapability is null.");
            } else if (deviceCapability.isSupportMenstrual()) {
                return true;
            }
        }
        return false;
    }

    private void c(Context context, String str) {
        String string;
        LogUtil.a("PhysiologicalCycleReceiver", "enter showNotification");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PhysiologicalCycleReceiver", "switchType is empty.");
            return;
        }
        if (b(context, str) != 1) {
            LogUtil.h("PhysiologicalCycleReceiver", "switchType: ", str, " is closed.");
            return;
        }
        if ("easyPregnancyStartSwitch".equals(str) || "easyPregnancyEndSwitch".equals(str)) {
            a(context, str);
            return;
        }
        str.hashCode();
        if (!str.equals("menstrualEndSwitch")) {
            string = !str.equals("menstrualStartSwitch") ? "" : String.format(Locale.getDefault(), BaseApplication.getContext().getString(R.string._2130846647_res_0x7f0223b7), 3);
        } else {
            string = BaseApplication.getContext().getString(R.string._2130845955_res_0x7f022103);
        }
        CommonUtil.setNotification(context, BaseApplication.getContext().getString(R.string._2130845958_res_0x7f022106), string, "huaweihealth://huaweihealth.app/openwith?isNeedLogin=true&address=com.huawei.health.h5.cycle-calendar?h5pro=true");
    }

    private void a(final Context context, final String str) {
        sqp.c("900300050", new HiDataReadResultListener() { // from class: com.huawei.health.receiver.PhysiologicalCycleReceiver.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                String h;
                boolean e = PhysiologicalCycleReceiver.this.e(obj, i);
                ReleaseLogUtil.e("R_PhysiologicalCycleReceiver", "showNotificationEasyPregnancy isSupportOvulationFertilePeriod ", Boolean.valueOf(e));
                if (e) {
                    String str2 = str;
                    str2.hashCode();
                    if (str2.equals("easyPregnancyStartSwitch")) {
                        h = nsf.h(R.string._2130845956_res_0x7f022104);
                    } else {
                        h = !str2.equals("easyPregnancyEndSwitch") ? "" : nsf.h(R.string._2130845957_res_0x7f022105);
                    }
                    CommonUtil.setNotification(context, nsf.h(R.string._2130845958_res_0x7f022106), h, "huaweihealth://huaweihealth.app/openwith?isNeedLogin=true&address=com.huawei.health.h5.cycle-calendar?h5pro=true");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Object obj, int i) {
        ReleaseLogUtil.e("PhysiologicalCycleReceiver", "isSupportOvulationFertilePeriod errorCode ", Integer.valueOf(i), " isPullAllStatus ", Boolean.valueOf(jfa.a()));
        if (i == 0 && scz.d("PhysiologicalCycleReceiver", obj) != null) {
            return scz.e();
        }
        return false;
    }

    private int b(Context context, String str) {
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("com.huawei.health.mc");
        if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
            LogUtil.h("PhysiologicalCycleReceiver", "userPreference is null or getValue is empty.");
            return 0;
        }
        try {
            return new JSONObject(userPreference.getValue()).optInt(str, 0);
        } catch (JSONException unused) {
            LogUtil.b("PhysiologicalCycleReceiver", "isSwitchOpen JSONException");
            return 0;
        }
    }
}
