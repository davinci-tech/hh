package com.huawei.ui.main.stories.settings.js;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProCallback;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.activity.healthdata.LacticActivity;
import defpackage.hpw;
import defpackage.koq;
import defpackage.kor;
import defpackage.kox;
import defpackage.nsj;
import java.util.ArrayList;
import java.util.List;

@H5ProService(name = "CustomData", users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class CustomDataService {
    private static final int DEFAULT_VALUE = 0;
    private static final String EMPTY_OBJECT = "{}";
    private static final int FAILED_VALUE = -1;
    private static final String HR_REST = "HR_REST";
    private static final String HR_SLEEP_REST_KEY = "HR_SLEEP_REST";
    private static final int SUCCESS_VALUE = 0;
    private static final String TAG = "CustomDataService";
    private static long mStartTime;

    @H5ProCallback
    public interface CustomDataCallback {
        void onFailure(int i, String str);

        void onSuccess(Object obj);
    }

    private CustomDataService() {
    }

    @H5ProMethod
    public static void writeCustomData(final String str, final String str2, final CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter writeHeartRate, key = ", str, ", value = ", str2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.settings.js.CustomDataService.4
            @Override // java.lang.Runnable
            public void run() {
                if ("{}".equals(str2)) {
                    LogUtil.h(CustomDataService.TAG, "writeCustomData value is Empty Object");
                    customDataCallback.onFailure(-1, "empty object");
                    return;
                }
                HiHealthManager.d(BaseApplication.e()).setUserPreference(new HiUserPreference(str, str2), true);
                if ("userPreference_HeartRate_all_posture_data".equals(str)) {
                    CustomDataService.writeHeartRateZoneData(str2);
                }
                customDataCallback.onSuccess(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeHeartRateZoneData(String str) {
        try {
            HeartRateZoneMgr heartRateZoneMgr = (HeartRateZoneMgr) new Gson().fromJson(str, HeartRateZoneMgr.class);
            HeartRateThresholdConfig postureType = heartRateZoneMgr.getPostureType(1);
            HeartZoneConf heartZoneConf = new HeartZoneConf();
            kor.a().e(heartZoneConf, postureType);
            if (heartRateZoneMgr.getPostureType(1).getHeartZoneStateConfig().getIsSetWarningLimit()) {
                kox.e().c();
            } else {
                HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).setUserPreference(new HiUserPreference("custom.UserPreference_HeartRate_Limit_Flag", "0"), true);
            }
            if (heartRateZoneMgr.getPostureType(1).getHeartZoneStateConfig().getIsSetMaxHeart()) {
                kox.e().g();
            } else {
                kor.a().m();
            }
            kor.a().b(heartZoneConf);
        } catch (JsonSyntaxException | NumberFormatException unused) {
            LogUtil.b(TAG, " updateAllPostureData parse value fail ");
        }
    }

    @H5ProMethod
    public static void readCustomData(final String str, final CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter readHeartRate, key = ", str);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.settings.js.CustomDataService.1
            @Override // java.lang.Runnable
            public void run() {
                String json;
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference(str);
                LogUtil.a(CustomDataService.TAG, "userDate = ", userPreference);
                if (userPreference != null) {
                    if (!TextUtils.isEmpty(userPreference.getValue())) {
                        customDataCallback.onSuccess(CustomDataService.getHeartData(userPreference.getValue()));
                        return;
                    }
                    HeartRateZoneMgr d = kox.e().d();
                    json = d != null ? new Gson().toJson(d) : "";
                    LogUtil.a(CustomDataService.TAG, "heartRateString = ", json);
                    customDataCallback.onSuccess(json);
                    return;
                }
                HiUserPreference userPreference2 = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.UserPreference_HeartZone_Config");
                if (userPreference2 == null || userPreference2.getValue() == null) {
                    customDataCallback.onSuccess("");
                    return;
                }
                HeartRateZoneMgr d2 = kox.e().d();
                json = d2 != null ? new Gson().toJson(d2) : "";
                LogUtil.a(CustomDataService.TAG, "heartRateString = ", json);
                customDataCallback.onSuccess(json);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getHeartData(String str) {
        return isHeartDataException(str) ? "" : str;
    }

    private static boolean isHeartDataException(String str) {
        try {
            return hpw.d((HeartRateZoneMgr) new Gson().fromJson(str, HeartRateZoneMgr.class));
        } catch (JsonSyntaxException | NumberFormatException unused) {
            LogUtil.b(TAG, " updateAllPostureData parse value fail ");
            return true;
        }
    }

    @H5ProMethod
    public static void getLactateThreshold(final CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter getLactateThreshold");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.settings.js.CustomDataService.3
            @Override // java.lang.Runnable
            public void run() {
                HiAggregateOption hiAggregateOption = new HiAggregateOption();
                hiAggregateOption.setType(new int[]{42112, 42113});
                hiAggregateOption.setCount(1);
                hiAggregateOption.setStartTime(0L);
                hiAggregateOption.setEndTime(System.currentTimeMillis());
                hiAggregateOption.setSortOrder(1);
                hiAggregateOption.setAggregateType(1);
                hiAggregateOption.setGroupUnitType(3);
                hiAggregateOption.setConstantsKey(new String[]{"lthrHr", "lthrPace"});
                HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthDataPro(HiDataAggregateProOption.builder().c(hiAggregateOption).c(), new HiAggregateListener() { // from class: com.huawei.ui.main.stories.settings.js.CustomDataService.3.5
                    @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                    public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                    }

                    @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                    public void onResult(List<HiHealthData> list, int i, int i2) {
                        CustomDataService.parsingData(list, i, CustomDataCallback.this);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parsingData(List<HiHealthData> list, int i, CustomDataCallback customDataCallback) {
        if (i != 0) {
            LogUtil.b(TAG, "get lactic data error is ", Integer.valueOf(i));
            customDataCallback.onFailure(i, "");
        } else {
            if (koq.b(list)) {
                LogUtil.h(TAG, "get lactic data is empty");
                customDataCallback.onSuccess("");
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            long startTime = hiHealthData.getStartTime();
            mStartTime = startTime;
            LogUtil.a(TAG, "parsingData mStartTime is ", Long.valueOf(startTime));
            customDataCallback.onSuccess(hiHealthData);
        }
    }

    @H5ProMethod
    public static void getAverageRestingHeartRate(final CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter getAverageRestingHeartRate");
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        String[] strArr = {HR_REST, HR_SLEEP_REST_KEY};
        hiAggregateOption.setType(new int[]{46018, 46020});
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setStartTime(nsj.e(currentTimeMillis, -30));
        hiAggregateOption.setEndTime(currentTimeMillis);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setGroupUnitType(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: com.huawei.ui.main.stories.settings.js.CustomDataService.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                int parsingAverageRestData = CustomDataService.parsingAverageRestData(sparseArray);
                LogUtil.a(CustomDataService.TAG, "parsingAverageRestData result is ", Integer.valueOf(parsingAverageRestData));
                CustomDataCallback.this.onSuccess(Integer.valueOf(parsingAverageRestData));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int parsingAverageRestData(SparseArray<List<HiHealthData>> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            return 0;
        }
        kor a2 = kor.a();
        return Math.round(a2.a(a2.bPD_(sparseArray)));
    }

    @H5ProMethod
    public static void jumpToLactateThresholdData(CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter jumpToLactateThresholdData");
        try {
            Context e = BaseApplication.e();
            Intent intent = new Intent(e, (Class<?>) LacticActivity.class);
            intent.setFlags(268435456);
            intent.putExtra("key_bundle_health_last_data_time", mStartTime);
            e.startActivity(intent);
            customDataCallback.onSuccess(0);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "jumpToLactateThresholdData ActivityNotFoundException");
        }
    }

    @H5ProMethod
    public static void jumpToPersonalSetting(CustomDataCallback customDataCallback) {
        LogUtil.a(TAG, "enter jumpToPersonalSetting");
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(BaseApplication.e());
        customDataCallback.onSuccess(0);
    }
}
