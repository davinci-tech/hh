package com.huawei.ui.main.stories.health.bloodpressure.util;

import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.dqu;
import defpackage.eeu;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = BloodPressureJsApi.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class BloodPressureJsApi extends JsBaseModule {
    private static final String KEY_BLOOD_PRESSURE_GRADE_DES = "BloodPressureGradeDes";
    private static final String KEY_BLOOD_PRESSURE_TYPE = "BloodPressureType";
    private static final String TAG = "BloodPressureJsApi";

    @H5ProMethod(name = "getBloodPressureGradeDes")
    public static void getBloodPressureGradeDes(int i, int i2, ServiceApiCallback<String> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getBloodPressureGradeDes: callback is null");
            return;
        }
        String b = eeu.b(i, i2);
        LogUtil.a(TAG, "getBloodPressureGradeDes, gradeDes: ", b);
        serviceApiCallback.onSuccess(b);
    }

    @H5ProMethod(name = "getBloodPressureGradeInfo")
    public static void getBloodPressureGradeInfo(int i, int i2, ServiceApiCallback<JSONObject> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getBloodPressureGradeDes: callback is null");
            return;
        }
        String b = eeu.b(i, i2);
        int c = eeu.c(i, i2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_BLOOD_PRESSURE_TYPE, c);
            jSONObject.put(KEY_BLOOD_PRESSURE_GRADE_DES, b);
        } catch (JSONException e) {
            LogUtil.b(TAG, "JSONException  ", e.getMessage());
        }
        LogUtil.a(TAG, "getBloodPressureGradeInfo, GradeInfo: ", jSONObject);
        serviceApiCallback.onSuccess(jSONObject);
    }

    @H5ProMethod(name = "getBloodPressureGradeTypeCount")
    public static void getBloodPressureGradeTypeCount(ServiceApiCallback<Integer> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getBloodPressureGradeTypeCount: callback is null");
            return;
        }
        int c = eeu.c();
        LogUtil.a(TAG, "getBloodPressureGradeTypeCount, typeCount: ", Integer.valueOf(c));
        serviceApiCallback.onSuccess(Integer.valueOf(c));
    }

    @H5ProMethod(name = "getProgressOfBloodPressure")
    public static void getProgressOfBloodPressure(int i, int i2, ServiceApiCallback<Integer> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getProgressOfBloodPressure: callback is null");
            return;
        }
        int a2 = BloodPressureUtil.a(i, i2);
        LogUtil.a(TAG, "getProgressOfBloodPressure, progress: ", Integer.valueOf(a2));
        serviceApiCallback.onSuccess(Integer.valueOf(a2));
    }

    @H5ProMethod(name = "getDynamicBloodPressureJson")
    public static void getDynamicBloodPressureJson(ServiceApiCallback<String> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getProgressOfBloodPressure: callback is null");
            return;
        }
        String a2 = dqu.b().a();
        Object[] objArr = new Object[2];
        objArr[0] = "getDynamicBloodPressureFeatureInfoJson is null: ";
        objArr[1] = Boolean.valueOf(a2 == null);
        LogUtil.a(TAG, objArr);
        serviceApiCallback.onSuccess(a2);
    }
}
