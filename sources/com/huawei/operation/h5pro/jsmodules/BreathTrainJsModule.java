package com.huawei.operation.h5pro.jsmodules;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.algorithm.api.BreathTrainApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.bzb;
import health.compact.a.Services;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class BreathTrainJsModule extends JsBaseModule {
    private static final String KEY_CODE = "resCode";
    private static final String KEY_DATA = "data";
    private static final String KEY_STATUS = "status";
    private long mCallbackId = 0;
    private final IBaseResponseCallback mCallback = new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.BreathTrainJsModule$$ExternalSyntheticLambda0
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            BreathTrainJsModule.this.m714x118d2c16(i, obj);
        }
    };

    /* renamed from: lambda$new$0$com-huawei-operation-h5pro-jsmodules-BreathTrainJsModule, reason: not valid java name */
    /* synthetic */ void m714x118d2c16(int i, Object obj) {
        LogUtil.c(this.TAG, "onResponse: callbackId ", Long.valueOf(this.mCallbackId), " errCode: ", Integer.valueOf(i));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value());
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
        LogUtil.a(this.TAG, "onResponse synCloud success");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_CODE, i);
            jSONObject.put("data", new JSONObject(obj.toString()));
            onSuccessCallback(this.mCallbackId, jSONObject);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "get breath result exception");
            onFailureCallback(this.mCallbackId, "error occurs when get breath result");
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c(this.TAG, "destroy breath js module");
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.c("BreathTrainService", BreathTrainApi.class);
        bzb bzbVar = new bzb();
        bzbVar.d(4);
        breathTrainApi.breathControl(bzbVar, 0, null);
    }

    @JavascriptInterface
    public void breathControl(long j, String str) {
        LogUtil.c(this.TAG, "call breathControl: ", str);
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.c("BreathTrainService", BreathTrainApi.class);
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("breathConfig");
            int i = jSONObject.getInt("type");
            if (i == 1) {
                bzb bzbVar = new bzb();
                bzbVar.d(1);
                breathTrainApi.breathControl(bzbVar, 0, null);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(KEY_CODE, 0);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("status", breathTrainApi.getJanusDeviceConnect());
                jSONObject2.put("data", jSONObject3);
                onSuccessCallback(j, jSONObject2);
            } else {
                this.mCallbackId = j;
                breathTrainApi.breathControl(createBreathTrainOption(jSONObject), getH5ResultCode(jSONObject), this.mCallback);
                if (i == 2) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put(KEY_CODE, 0);
                    jSONObject4.put("data", "");
                    onSuccessCallback(j, jSONObject4);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "breathControl: parse parameter exception");
            onFailureCallback(j, "parse parameter failed");
        }
    }

    private int getH5ResultCode(JSONObject jSONObject) {
        try {
            return jSONObject.getInt("resultCode");
        } catch (JSONException unused) {
            return 1;
        }
    }

    private bzb createBreathTrainOption(JSONObject jSONObject) throws JSONException {
        int[] iArr;
        bzb bzbVar = new bzb();
        int i = jSONObject.getInt("type");
        bzbVar.d(i);
        if (i != 2) {
            return bzbVar;
        }
        bzbVar.b(jSONObject.getInt("duration"));
        bzbVar.c(jSONObject.getInt("rhythm"));
        bzbVar.a(jSONObject.getInt("isSupportWatch"));
        bzbVar.c(jSONObject.getBoolean("screenOn"));
        JSONArray jSONArray = jSONObject.getJSONArray("vibrateDuration");
        if (jSONArray.length() > 0) {
            iArr = new int[jSONArray.length()];
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                iArr[i2] = jSONArray.getInt(i2);
            }
        } else {
            LogUtil.h(this.TAG, "vibrate duration err: ", jSONArray.toString());
            iArr = null;
        }
        bzbVar.e(iArr);
        return bzbVar;
    }

    @JavascriptInterface
    public void getJanusDeviceConnect(long j) {
        LogUtil.c(this.TAG, "call getJanusDeviceConnect");
        try {
            int janusDeviceConnect = ((BreathTrainApi) Services.c("BreathTrainService", BreathTrainApi.class)).getJanusDeviceConnect();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", janusDeviceConnect);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "getJanusDeviceConnect: parse result exception");
            onFailureCallback(j, "parse result error");
        }
    }

    @JavascriptInterface
    public void getSettings(long j, String str) {
        LogUtil.c(this.TAG, "getSettings: callbackId: ", Long.valueOf(j), ", key: ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.TAG, "getSettings: key is empty");
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getUserPreference(str);
        if (userPreference == null) {
            LogUtil.h(this.TAG, "get setting exception: pref is null");
            onFailureCallback(j, "setting is empty");
            return;
        }
        String value = userPreference.getValue();
        LogUtil.c(this.TAG, "user setting: ", value);
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(value)) {
                LogUtil.h(this.TAG, "get setting exception: setting is empty");
                jSONObject.put(KEY_CODE, -1);
                jSONObject.put("data", "");
            } else {
                jSONObject.put(KEY_CODE, 0);
                jSONObject.put("data", value);
            }
            onSuccessCallback(j, jSONObject);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "getSettings: put result exception");
            onFailureCallback(j, "get setting failed");
        }
    }

    @JavascriptInterface
    public void putSettings(long j, String str) {
        LogUtil.c(this.TAG, "putSettings: ", " param: ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(MedalConstants.EVENT_KEY);
            String string2 = jSONObject.getString("value");
            int i = 0;
            if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string)) {
                HiUserPreference hiUserPreference = new HiUserPreference();
                hiUserPreference.setKey(string);
                hiUserPreference.setValue(string2);
                boolean userPreference = HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).setUserPreference(hiUserPreference);
                LogUtil.a(this.TAG, "putSettings: isSuccess ", Boolean.valueOf(userPreference));
                JSONObject jSONObject2 = new JSONObject();
                if (!userPreference) {
                    i = -1;
                }
                jSONObject2.put(KEY_CODE, i);
                onSuccessCallback(j, jSONObject2);
                return;
            }
            LogUtil.h(this.TAG, "putSettings: key or setting is empty");
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "putSettings: parse param failed");
            onFailureCallback(j, "parse param failed");
        }
    }

    @JavascriptInterface
    public void finishTrain(long j) {
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.c("BreathTrainService", BreathTrainApi.class);
        bzb bzbVar = new bzb();
        bzbVar.d(4);
        breathTrainApi.breathControl(bzbVar, 0, null);
        onSuccessCallback(j, getDefaultResult());
    }

    @JavascriptInterface
    public void canFinish(long j) {
        Activity activity = com.huawei.hwcommonmodel.application.BaseApplication.getActivity();
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.c("BreathTrainService", BreathTrainApi.class);
        if (breathTrainApi.canFinish(activity) == 1) {
            LogUtil.a(this.TAG, "breathe finish activity");
            activity.finish();
        } else if (breathTrainApi.canFinish(activity) == 2) {
            LogUtil.a(this.TAG, "breathe show stop dialog");
        } else {
            LogUtil.h(this.TAG, "breath can finish result not 1 2");
        }
        onSuccessCallback(j, getDefaultResult());
    }

    private JSONObject getDefaultResult() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_CODE, 0);
            jSONObject.put("data", "");
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "breath getDefaultResult() failed");
        }
        return jSONObject;
    }
}
