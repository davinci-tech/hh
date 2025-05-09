package com.huawei.operation.h5pro.jsmodules;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.beans.EcgFilterRawData;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.operation.utils.SampleConfigUtils;
import defpackage.cun;
import defpackage.mpb;
import defpackage.mxv;
import defpackage.sqa;
import health.compact.a.CommonUtil;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EcgJsModule extends JsBaseModule {
    private static final int ACC_UNIT_SIZE = 10;
    private static final int DEFAULT_TIME_LENGTH = 2;
    private static final int ECG_RESULT_SIZE = 15800;
    private static final int ECG_UNIT_SIZE = 50;
    private static final int INVALID_TIMES = 16;
    private static final int PARSE_RADIX_10 = 10;
    private static final int PER_DATA_TIME = 100;
    private static final String RECEIVER_CLASS_NAME = "com.huawei.health.receiver.EcgReminderReceiver";
    private static final int REMINDER_CODE_BASE_ECG = 4000;
    private static final int REMINDER_CODE_BASE_ECGCE = 4100;
    private static final int REMINDER_CODE_BASE_VASCULAR = 4200;
    private static final int REMINDER_CYCLE = 7;
    private static final String STR_KEY_TRUE = "true";
    private static final String TAG = "EcgJsModule";
    private static final String TIME_LINK = ":";

    @JavascriptInterface
    public void setRemindState(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("packageName", jSONObject.optBoolean("isCe") ? "com.huawei.health.h5.ecgce" : "com.huawei.health.h5.ecg");
            setRemindStateNew(jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "setRemindState JSONException");
        }
    }

    @JavascriptInterface
    public void getRemindState(long j) {
        checkRemindState(j, checkRemindSwitch("com.huawei.health.ecg.remind.measure.switch"));
    }

    @JavascriptInterface
    public void getCeRemindState(long j) {
        checkRemindState(j, checkRemindSwitch("com.huawei.health.ecgce.remind.measure.switch"));
    }

    private void checkRemindState(long j, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", 0);
            jSONObject.put("resultDesc", z);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", jSONObject);
            onSuccessCallback(j, jSONObject2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "checkRemindState JSONException");
            onFailureCallback(j, "checkRemindState JSONException");
        }
    }

    @JavascriptInterface
    public void setRemindTime(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("packageName", jSONObject.optBoolean("isCe") ? "com.huawei.health.h5.ecgce" : "com.huawei.health.h5.ecg");
            setRemindTimeNew(jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "setRemindTime JSONException");
        }
    }

    @JavascriptInterface
    public void getRemindTime(long j) {
        checkRemindTime(j, getRemindTimeInfo("com.huawei.health.ecg.remind.measure.time"));
    }

    @JavascriptInterface
    public void getCeRemindTime(long j) {
        checkRemindTime(j, getRemindTimeInfo("com.huawei.health.ecgce.remind.measure.time"));
    }

    private void checkRemindTime(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", 0);
            jSONObject.put("resultDesc", TextUtils.isEmpty(str) ? "" : new JSONObject(str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", jSONObject);
            onSuccessCallback(j, jSONObject2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "checkRemindTime JSONException");
            onFailureCallback(j, "checkRemindTime JSONException");
        }
    }

    @JavascriptInterface
    public String getVersion() {
        return mpb.e().getVersion();
    }

    @JavascriptInterface
    public int ecgFilterInit(int i) {
        return mpb.e().ecgFilterInit(i);
    }

    @JavascriptInterface
    public String getEcgSaveResult(String str) {
        try {
            EcgFilterRawData ecgFilterRawData = (EcgFilterRawData) HiJsonUtil.e(str, EcgFilterRawData.class);
            if (ecgFilterRawData.getAccData().size() % 10 == 0 && ecgFilterRawData.getEcgData().size() % 50 == 0) {
                StringBuilder sb = new StringBuilder(ECG_RESULT_SIZE);
                sb.append("[");
                int size = ecgFilterRawData.getEcgData().size() / 50;
                for (int i = 1; i <= size; i++) {
                    EcgFilterRawData ecgFilterRawData2 = new EcgFilterRawData();
                    int i2 = i * 10;
                    ecgFilterRawData2.setAccData(ecgFilterRawData.getAccData().subList(i2 - 10, i2));
                    ecgFilterRawData2.setAccLen(10);
                    long j = (i - 1) * 100;
                    ecgFilterRawData2.setAccTimeStamp(ecgFilterRawData.getAccTimeStamp() + j);
                    int i3 = i * 50;
                    ecgFilterRawData2.setEcgData(ecgFilterRawData.getEcgData().subList(i3 - 50, i3));
                    ecgFilterRawData2.setEcgLen(50);
                    ecgFilterRawData2.setEcgTimeStamp(ecgFilterRawData.getEcgTimeStamp() + j);
                    mpb.e().getEcgAlgorithmResult(HiJsonUtil.e(ecgFilterRawData2), 0);
                    if (i > 16) {
                        try {
                            String jSONArray = new JSONObject(mpb.e().getEcgSaveResult()).getJSONArray("ecgSaveData").toString();
                            sb.append(jSONArray.substring(1, jSONArray.length() - 1));
                            sb.append(",");
                        } catch (JSONException unused) {
                            LogUtil.b(TAG, "getEcgSaveResult: ecgSaveData JSONException");
                            return "";
                        }
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("ecgSaveData", sb);
                    return jSONObject.toString();
                } catch (JSONException unused2) {
                    LogUtil.b(TAG, "jsonObject.put ecgSaveData error");
                }
            }
        } catch (JsonSyntaxException unused3) {
        }
        return "";
    }

    public static boolean checkRemindSwitch(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        Object[] objArr = new Object[2];
        objArr[0] = "isRemindSwitchOpened ";
        objArr[1] = userPreference == null ? "" : userPreference.getValue();
        LogUtil.a(TAG, objArr);
        if (userPreference != null) {
            return "true".equals(userPreference.getValue());
        }
        return false;
    }

    private static String getRemindTimeInfo(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getRemindTimeInfo ";
        objArr[1] = userPreference == null ? "" : userPreference.getValue();
        LogUtil.a(TAG, objArr);
        if (userPreference != null) {
            String value = userPreference.getValue();
            if (!TextUtils.isEmpty(value)) {
                return value;
            }
        }
        return "";
    }

    private static void setReminder(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("time");
            String string2 = jSONObject.getString("frequence");
            if (TextUtils.isEmpty(string) || string.split(":").length < 2 || CommonUtil.a(string2, 10) <= 0 || TextUtils.isEmpty(str2)) {
                LogUtil.h(TAG, "setRemindTime time or remindKey is invalid");
                return;
            }
            String[] split = string.split(":");
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, CommonUtil.h(split[0]));
            calendar.set(12, CommonUtil.h(split[1]));
            calendar.set(13, 0);
            calendar.set(14, 0);
            Calendar calendar2 = Calendar.getInstance();
            int reminderCode = getReminderCode(str2);
            if (reminderCode == 0) {
                LogUtil.b(TAG, "setReminder() reminder set failed, remind key not exist, remindKey=", str2);
                return;
            }
            for (int i = 0; i < Math.min(string2.length(), 7); i++) {
                int i2 = reminderCode + i;
                cancelReminder(i2);
                if (string2.charAt(i) == '1' && (calendar.clone() instanceof Calendar)) {
                    Calendar calendar3 = (Calendar) calendar.clone();
                    calendar3.set(7, i + 1);
                    if (calendar3.before(calendar2)) {
                        calendar3.add(5, 7);
                    }
                    registerReminder(i2, calendar3.getTimeInMillis(), str2);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "setRemindTime JSONException");
        }
    }

    private static void registerReminder(int i, long j, String str) {
        LogUtil.a(TAG, "registerReminder requestCode ", Integer.valueOf(i), ", time ", Long.valueOf(j));
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setClassName(context, RECEIVER_CLASS_NAME);
        intent.putExtra("reminderHuid", LoginInit.getInstance(context).getAccountInfo(1011));
        intent.putExtra("reminderTime", j);
        intent.putExtra("requestCode", i);
        intent.putExtra("remindKey", str);
        intent.putExtra("timeZoneId", TimeZone.getDefault().getID());
        sqa.ekz_(i, intent, 201326592, 0, j);
    }

    private static void cancelReminder(int i) {
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setClassName(context, RECEIVER_CLASS_NAME);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            AlarmManager alarmManager = (AlarmManager) systemService;
            PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 201326592);
            if (broadcast != null) {
                broadcast.cancel();
                alarmManager.cancel(broadcast);
            }
        }
    }

    @JavascriptInterface
    public void getUserInfo(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("client_id");
            String string2 = jSONObject.getString("client_secret");
            String string3 = jSONObject.getString("code");
            String string4 = jSONObject.getString("at_host");
            String string5 = jSONObject.getString("info_host");
            String atFromUp = JsInteractAddition.getInstance().getAtFromUp(string4, string, string2, string3, jSONObject.has(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL) ? jSONObject.getString(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL) : "");
            if (TextUtils.isEmpty(atFromUp)) {
                onFailureCallback(j, "getUserInfo at is null");
                return;
            }
            JSONObject userInfoFromUp = JsInteractAddition.getInstance().getUserInfoFromUp(atFromUp, string5);
            if (userInfoFromUp == null) {
                onFailureCallback(j, "getUserInfo openid is null");
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", userInfoFromUp);
            onSuccessCallback(j, jSONObject2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getUserInfo JSONException");
            onFailureCallback(j, "getUserInfo JSONException");
        }
    }

    private static int getReminderCode(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -918899155) {
            if (str.equals("com.huawei.health.vascular-health.remind.measure.switch")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 155319248) {
            if (hashCode == 1407588978 && str.equals("com.huawei.health.ecgce.remind.measure.switch")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.ecg.remind.measure.switch")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return REMINDER_CODE_BASE_VASCULAR;
        }
        if (c != 1) {
            return c != 2 ? 0 : 4100;
        }
        return 4000;
    }

    private static String getRemindSwitchKey(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1391389376) {
            if (str.equals("com.huawei.health.h5.ecg")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1390358110) {
            if (hashCode == -420752163 && str.equals("com.huawei.health.h5.vascular-health")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.h5.ecgce")) {
                c = 1;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? "" : "com.huawei.health.vascular-health.remind.measure.switch" : "com.huawei.health.ecgce.remind.measure.switch" : "com.huawei.health.ecg.remind.measure.switch";
    }

    private static String getRemindTimeKey(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1391389376) {
            if (str.equals("com.huawei.health.h5.ecg")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1390358110) {
            if (hashCode == -420752163 && str.equals("com.huawei.health.h5.vascular-health")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.h5.ecgce")) {
                c = 1;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? "" : "com.huawei.health.vascular-health.remind.measure.time" : "com.huawei.health.ecgce.remind.measure.time" : "com.huawei.health.ecg.remind.measure.time";
    }

    public static void setFeatureReminder(String str) {
        setFeatureReminder(str, checkRemindSwitch(getRemindSwitchKey(str)));
    }

    private static void setFeatureReminder(String str, boolean z) {
        if (z) {
            setReminder(getRemindTimeInfo(getRemindTimeKey(str)), getRemindSwitchKey(str));
            return;
        }
        int reminderCode = getReminderCode(getRemindSwitchKey(str));
        if (reminderCode == 0) {
            LogUtil.b(TAG, "setFeatureReminder() cancel reminder failed, package name not exist, packageName=", str);
            return;
        }
        for (int i = 0; i < 7; i++) {
            cancelReminder(reminderCode + i);
        }
    }

    @JavascriptInterface
    public void setRemindStateNew(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean z = jSONObject.getBoolean("isOpened");
            String optString = jSONObject.optString("packageName");
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey(getRemindSwitchKey(optString));
            hiUserPreference.setValue(z ? "true" : "false");
            boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
            LogUtil.a(TAG, "setRemindStateNew isSuccess ", Boolean.valueOf(userPreference), ", isOpened ", Boolean.valueOf(z));
            setFeatureReminder(optString, z);
            if (userPreference) {
                SampleConfigUtils.resendEcgCeNotifySampleConfig(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG), TAG);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "setRemindStateNew JSONException");
        }
    }

    @JavascriptInterface
    public void getRemindStateNew(long j, String str) {
        String str2;
        try {
            str2 = getRemindSwitchKey(new JSONObject(str).optString("packageName"));
        } catch (JSONException unused) {
            LogUtil.h(TAG, "getRemindTimeNew JsonException");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            onFailureCallback(j, "getRemindTimeNew Undefined packageName");
        } else {
            checkRemindState(j, checkRemindSwitch(str2));
        }
    }

    @JavascriptInterface
    public void setRemindTimeNew(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("time");
            String string2 = jSONObject.getString("frequence");
            String remindTimeKey = getRemindTimeKey(jSONObject.optString("packageName"));
            if (TextUtils.isEmpty(string) || string.split(":").length < 2 || CommonUtil.a(string2, 10) <= 0 || TextUtils.isEmpty(remindTimeKey)) {
                LogUtil.h(TAG, "setRemindTimeNew packageName, time or frequency is invalid");
                return;
            }
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey(remindTimeKey);
            hiUserPreference.setValue(str);
            LogUtil.a(TAG, "setRemindTimeNew isSuccess ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference)), ", time ", str);
            mxv.a(10026);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "setRemindTimeNew JSONException");
        }
    }

    @JavascriptInterface
    public void getRemindTimeNew(long j, String str) {
        String str2;
        try {
            str2 = getRemindTimeKey(new JSONObject(str).optString("packageName"));
        } catch (JSONException unused) {
            LogUtil.h(TAG, "getRemindTimeNew JsonException");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            onFailureCallback(j, "getRemindTimeNew Undefined packageName");
        } else {
            checkRemindTime(j, getRemindTimeInfo(str2));
        }
    }
}
