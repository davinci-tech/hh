package com.huawei.ui.main.stories.fitness.interactors.stressAdvice;

import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.stories.fitness.interactors.stressAdvice.PressureMeasureAdviceMgr;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PressureMeasureAdviceMgr {
    private static native String stressAdviceFromJni(String str);

    static {
        try {
            System.loadLibrary("StressAdvice");
            LogUtil.a("PressureMeasureAdviceMgr", "load stress advice lib success");
        } catch (UnsatisfiedLinkError unused) {
            LogUtil.b("PressureMeasureAdviceMgr", "load stress advice lib fail");
        }
    }

    private static int e(int i) {
        if (i == 7) {
            return 0;
        }
        if (i == 13) {
            return 1;
        }
        if (i == 59) {
            return 2;
        }
        LogUtil.h("PressureMeasureAdviceMgr", "getType switch default");
        return 0;
    }

    public static void a(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (date == null || commonUiBaseResponse == null) {
            LogUtil.h("PressureMeasureAdviceMgr", "getPressureAdvice parameter error");
            return;
        }
        long time = date.getTime();
        long j = time - (i * 86400000);
        LogUtil.a("PressureMeasureAdviceMgr", "getPressureAdvice startTime = ", Long.valueOf(j), ",endTime = ", Long.valueOf(time));
        e(j, time, i, new IBaseResponseCallback() { // from class: pxh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                PressureMeasureAdviceMgr.e(CommonUiBaseResponse.this, i2, obj);
            }
        });
    }

    public static /* synthetic */ void e(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        PressureAdeviceRetrunValue pressureAdeviceRetrunValue;
        if (i != 0) {
            commonUiBaseResponse.onResponse(i, null);
            return;
        }
        String stressAdviceFromJni = stressAdviceFromJni((String) obj);
        LogUtil.a("PressureMeasureAdviceMgr", "getPressureAdvice: ", stressAdviceFromJni);
        if (stressAdviceFromJni == null || stressAdviceFromJni.isEmpty()) {
            LogUtil.h("PressureMeasureAdviceMgr", "strAdvice is Empty ");
            commonUiBaseResponse.onResponse(100001, null);
            return;
        }
        try {
            pressureAdeviceRetrunValue = (PressureAdeviceRetrunValue) HiJsonUtil.e(stressAdviceFromJni, PressureAdeviceRetrunValue.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PressureMeasureAdviceMgr", "JsonSyntaxException");
            pressureAdeviceRetrunValue = null;
        }
        if (pressureAdeviceRetrunValue == null || pressureAdeviceRetrunValue.getAdvice_num_1() == 0 || pressureAdeviceRetrunValue.getAdvice_num_2() == 0) {
            LogUtil.h("PressureMeasureAdviceMgr", "data is null ");
            commonUiBaseResponse.onResponse(100001, null);
        } else {
            commonUiBaseResponse.onResponse(0, pressureAdeviceRetrunValue);
        }
    }

    private static void e(long j, final long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        final int e = e(i);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(e(j, j2, e), new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.stressAdvice.PressureMeasureAdviceMgr.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (obj == null) {
                    LogUtil.b("PressureMeasureAdviceMgr", "data is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = obj instanceof SparseArray ? (SparseArray) obj : null;
                if (sparseArray == null || sparseArray.size() <= 0) {
                    LogUtil.h("PressureMeasureAdviceMgr", "map is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("curr_time", j2 / 1000);
                    jSONObject.put("type", e);
                    jSONObject.put("zone", 800);
                    JSONArray duU_ = e == 0 ? PressureMeasureAdviceMgr.duU_(sparseArray) : PressureMeasureAdviceMgr.duV_(sparseArray);
                    if (duU_.length() == 0) {
                        IBaseResponseCallback.this.d(-1, null);
                    } else {
                        jSONObject.put("time_score_arr", duU_);
                        IBaseResponseCallback.this.d(0, jSONObject.toString());
                    }
                } catch (JSONException unused) {
                    LogUtil.b("PressureMeasureAdviceMgr", "readHiHealthData JSONException ");
                    IBaseResponseCallback.this.d(-1, null);
                }
            }
        });
    }

    private static HiDataReadOption e(long j, long j2, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        if (i == 0) {
            hiDataReadOption.setType(new int[]{2034});
        } else {
            hiDataReadOption.setType(new int[]{44306});
        }
        return hiDataReadOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONArray duU_(SparseArray<Object> sparseArray) throws JSONException {
        Object obj = sparseArray.get(2034);
        List arrayList = new ArrayList();
        if (obj instanceof List) {
            arrayList = (List) obj;
        }
        ArrayList<HiStressMetaData> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((HiStressMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressMetaData.class));
        }
        JSONArray jSONArray = new JSONArray();
        for (HiStressMetaData hiStressMetaData : arrayList2) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("gather_time", hiStressMetaData.fetchStressStartTime() / 1000);
            jSONObject.put(JsUtil.SCORE, hiStressMetaData.fetchStressScore());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONArray duV_(SparseArray<Object> sparseArray) throws JSONException {
        Object obj = sparseArray.get(44306);
        List<HiHealthData> arrayList = new ArrayList();
        if (obj instanceof List) {
            arrayList = (List) obj;
        }
        JSONArray jSONArray = new JSONArray();
        for (HiHealthData hiHealthData : arrayList) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("gather_time", hiHealthData.getStartTime() / 1000);
            jSONObject.put(JsUtil.SCORE, hiHealthData.getIntValue());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
