package com.huawei.operation.utils;

import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cuk;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvx;
import defpackage.cwi;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SampleConfigUtils {
    private static final int ACTION_MODIFY = 1;
    private static final int BIT_CONVERT_BYTE = 2;
    private static final long CONFIG_ID_ECGCE_PERIOD_NOTIFY = 900300014;
    private static final long CONFIG_ID_OSA_ACTIVE_SWITCH = 900300007;
    private static final int CONVERT_DIGIT_BIN = 2;
    private static final int CONVERT_DIGIT_DEC = 10;
    private static final int DATA_LENGTH_ECG_CE = 4;
    private static final int DEFAULT_KEY_TAG = 1;
    private static final String SRC_PCK_NAME_ECG_CE = "hw.health.ecganalysis";
    private static final String SRC_PCK_NAME_OSA = "hw.health.apneajsmodule";
    private static final String TAG = "SampleConfigUtils";
    private static final String WEAR_PCK_NAME_ECG_CE = "hw.watch.health.ecganalysis";
    private static final String WEAR_PCK_NAME_OSA = "hw.watch.health.osa";
    private static final String SWITCH_OPEN = cvx.e(1);
    private static final String SWITCH_CLOSE = cvx.e(0);

    public static void resendAsyncWhenReconnect(final DeviceInfo deviceInfo, final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.utils.SampleConfigUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SampleConfigUtils.lambda$resendAsyncWhenReconnect$0(str, deviceInfo);
            }
        });
    }

    static /* synthetic */ void lambda$resendAsyncWhenReconnect$0(String str, DeviceInfo deviceInfo) {
        String str2 = TextUtils.isEmpty(str) ? TAG : str;
        LogUtil.a(TAG, "resendWhenReconnect entered, inner tag is ", str);
        resendOsaActiveSampleConfig(deviceInfo, str2);
        resendEcgCeNotifySampleConfig(deviceInfo, str2);
    }

    private static String buildSingleHexData(int i, String str) {
        return cvx.e(i) + cvx.d(str.length() / 2) + str;
    }

    private static String buildHexData(Map<Integer, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                sb.append(buildSingleHexData(key.intValue(), value));
            }
        }
        return sb.toString();
    }

    private static void resendOsaActiveSampleConfig(DeviceInfo deviceInfo, String str) {
        if (!cwi.c(deviceInfo, 111)) {
            LogUtil.h(str, "resendOsaActiveSampleConfig failed: Device capability unsupported");
            return;
        }
        if (!Boolean.parseBoolean(getPreferenceValue(HiHealthManager.d(BaseApplication.getContext()), "is-sleep-apnea-activated"))) {
            LogUtil.a(str, "resendOsaActiveSampleConfig failed: Osa have not active yet");
            return;
        }
        LogUtil.a(str, "resendOsaActiveSampleConfig resending sample config");
        SampleConfigBuilder sampleConfigBuilder = new SampleConfigBuilder(SRC_PCK_NAME_OSA, WEAR_PCK_NAME_OSA);
        sampleConfigBuilder.addConfigInfo(CONFIG_ID_OSA_ACTIVE_SWITCH, 1, buildSingleHexData(1, SWITCH_OPEN));
        cuk.b().sendSampleConfigCommand(deviceInfo, sampleConfigBuilder, str);
    }

    public static void resendEcgCeNotifySampleConfig(DeviceInfo deviceInfo, String str) {
        if (!cwi.c(deviceInfo, OldToNewMotionPath.SPORT_TYPE_PILATES)) {
            LogUtil.h(str, "resendEcgCeNotifySampleConfig failed: Device capability unsupported");
            return;
        }
        HiHealthApi d = HiHealthManager.d(BaseApplication.getContext());
        TreeMap treeMap = new TreeMap();
        if (!Boolean.parseBoolean(getPreferenceValue(d, "com.huawei.health.ecgce.remind.measure.switch"))) {
            for (int i = 1; i <= 4; i++) {
                treeMap.put(Integer.valueOf(i), SWITCH_CLOSE);
            }
            buildAndSendEcgCe(deviceInfo, treeMap, str);
            return;
        }
        treeMap.put(1, SWITCH_OPEN);
        String preferenceValue = getPreferenceValue(d, "com.huawei.health.ecgce.remind.measure.time");
        try {
            if (preferenceValue == null) {
                throw new JSONException("timePreference is null");
            }
            JSONObject jSONObject = new JSONObject(preferenceValue);
            treeMap.put(2, cvx.e(getParsedInt(jSONObject.getString("frequence"), 2, TypedValues.CycleType.S_WAVE_PERIOD)));
            String[] split = jSONObject.getString("time").split(":");
            if (split.length == 2) {
                int parsedInt = getParsedInt(split[0], 10, "day");
                int parsedInt2 = getParsedInt(split[1], 10, "day");
                treeMap.put(3, cvx.e(parsedInt));
                treeMap.put(4, cvx.e(parsedInt2));
                buildAndSendEcgCe(deviceInfo, treeMap, str);
                return;
            }
            throw new JSONException("day format invalid");
        } catch (JSONException e) {
            LogUtil.b(str, "resendEcgCeNotifySampleConfig JSONException timeString=", preferenceValue, ", errMsg=", e.getMessage());
        }
    }

    private static void buildAndSendEcgCe(DeviceInfo deviceInfo, Map<Integer, String> map, String str) {
        LogUtil.a(str, "resendEcgCeNotifySampleConfig resending sample config, tlvMap=", HiJsonUtil.e(map));
        SampleConfigBuilder sampleConfigBuilder = new SampleConfigBuilder(SRC_PCK_NAME_ECG_CE, WEAR_PCK_NAME_ECG_CE);
        sampleConfigBuilder.addConfigInfo(CONFIG_ID_ECGCE_PERIOD_NOTIFY, 1, buildHexData(map));
        cuk.b().sendSampleConfigCommand(deviceInfo, sampleConfigBuilder, str);
    }

    private static int getParsedInt(String str, int i, String str2) throws JSONException {
        int a2 = CommonUtil.a(str, i);
        if (a2 != -1) {
            return a2;
        }
        throw new JSONException(str2 + " value invalid");
    }

    private static String getPreferenceValue(HiHealthApi hiHealthApi, String str) {
        HiUserPreference userPreference = hiHealthApi.getUserPreference(str);
        return userPreference == null ? "" : userPreference.getValue();
    }

    static class SampleConfigBuilder extends cvq {
        SampleConfigBuilder(String str, String str2) {
            setConfigInfoList(new ArrayList(10));
            setSrcPkgName(str);
            setWearPkgName(str2);
        }

        boolean addConfigInfo(long j, int i, String str) {
            cvn cvnVar = new cvn();
            cvnVar.e(j);
            cvnVar.d(i);
            cvnVar.c(cvx.a(str));
            return getConfigInfoList().add(cvnVar);
        }
    }
}
