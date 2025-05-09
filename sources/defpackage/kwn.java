package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.util.SmartRulesApi;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwn {
    private static final SmartRulesApi d = (SmartRulesApi) Services.a("HWSmartInteractMgr", SmartRulesApi.class);

    public static int b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        ArrayList<String> a2 = cjx.e().a(a(str));
        if (koq.b(a2)) {
            return 0;
        }
        return a2.size();
    }

    public static HealthDevice.HealthDeviceKind a(String str) {
        return c(str) ? HealthDevice.HealthDeviceKind.valueOf(str) : HealthDevice.HealthDeviceKind.HDK_NOT_DEVICE;
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        for (HealthDevice.HealthDeviceKind healthDeviceKind : HealthDevice.HealthDeviceKind.values()) {
            if (healthDeviceKind.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(int i, String str) {
        return d.isRuleOpen(i, str);
    }

    public static String b(int i, String str, String str2) {
        return d.getRuleData(i, str, str2);
    }

    public static int d(int i, String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(i), str);
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(new JSONObject(b).getString("priority"));
            } catch (NumberFormatException e) {
                LogUtil.b("SmarterUtils", "isRuleOpen ParseException exception = ", e.getMessage());
            } catch (JSONException e2) {
                LogUtil.b("SmarterUtils", "isRuleOpen JSONException exception = ", e2.getMessage());
            }
        }
        return 0;
    }

    public static String a(int i, String str) {
        JSONObject jSONObject;
        StringBuffer stringBuffer = new StringBuffer(5);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(i), str);
        if (TextUtils.isEmpty(b)) {
            return stringBuffer.toString();
        }
        try {
            jSONObject = new JSONObject(b);
        } catch (JSONException e) {
            LogUtil.b("SmarterUtils", "isRuleOpen JSONException exception = ", e.getMessage());
        }
        if (!jSONObject.getBoolean(k.g)) {
            return stringBuffer.toString();
        }
        JSONArray jSONArray = jSONObject.getJSONObject(e.n).getJSONArray("recommended_time");
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string = jSONObject2.getString("start_time");
                String string2 = jSONObject2.getString("end_time");
                stringBuffer.append(string);
                stringBuffer.append(string2);
            }
        }
        return stringBuffer.toString();
    }

    public static void c(Context context, int i, int i2) {
        LogUtil.a("SmarterUtils", "setMsgStatus = ", Integer.valueOf(i2));
        kvs b = kvs.b(context);
        SmartMsgDbObject a2 = b.a(i);
        if (a2 != null) {
            a2.setStatus(i2);
            b.e(a2.getId(), a2);
            LogUtil.a("SmarterUtils", "msgDbObject.getStatus() = ", Integer.valueOf(a2.getStatus()));
        }
    }

    public static boolean b(Context context, String str, long j) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20009), str);
        if (!TextUtils.isEmpty(b)) {
            try {
            } catch (NumberFormatException e) {
                LogUtil.b("SmarterUtils", "checkInterval numberFormatException = ", e.getMessage());
            }
            if (Math.abs(System.currentTimeMillis() - Long.parseLong(b)) <= j) {
                return false;
            }
        }
        return true;
    }

    public static void a(Context context, String str, long j) {
        SharedPreferenceManager.e(context, Integer.toString(20009), str, String.valueOf(j), new StorageParams());
    }

    public static void a(Context context, IBaseResponseCallback iBaseResponseCallback) {
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("custom.weight_auto_update_status");
        if (userPreference != null) {
            LogUtil.a("SmarterUtils", "weight switch status ", userPreference.getValue());
            if ("0".equals(userPreference.getValue())) {
                iBaseResponseCallback.d(100001, null);
                return;
            } else {
                iBaseResponseCallback.d(0, null);
                return;
            }
        }
        iBaseResponseCallback.d(0, null);
    }
}
