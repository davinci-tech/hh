package com.huawei.health.h5pro.jsbridge.system.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.util.AudioManagerApi;
import com.huawei.health.h5pro.jsbridge.system.util.SensorManagerApi;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UtilEntry extends JsBaseModule {

    /* renamed from: a, reason: collision with root package name */
    public AudioManagerApi f2445a;
    public SensorManagerApi b;
    public Util d;

    @JavascriptInterface
    public void unregisterSensorListener() {
        SensorManagerApi sensorManagerApi = this.b;
        if (sensorManagerApi != null) {
            sensorManagerApi.unregisterSensorListener();
        }
        this.b = null;
    }

    @JavascriptInterface
    public void setAudioVolume(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("audioType");
            int optInt = jSONObject.optInt("audioVolume", 0);
            if (this.f2445a == null) {
                this.f2445a = new AudioManagerApi(this.mContext);
            }
            this.f2445a.setAudioVolume(optString, optInt, a(j));
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "setAudioVolume: exception(param) -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void registerSensorListener(final long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("sensorType");
            SensorManagerApi.SamplingPeriodUs samplingPeriodUs = SensorManagerApi.SamplingPeriodUs.SENSOR_DELAY_UI;
            String optString2 = jSONObject.optString("samplingPeriodUs", "SENSOR_DELAY_UI");
            if (this.b == null) {
                this.b = new SensorManagerApi(this.mContext);
            }
            this.b.registerSensorListener(optString, optString2, new SensorManagerApi.SensorDataCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.util.UtilEntry.1
                @Override // com.huawei.health.h5pro.jsbridge.system.util.SensorManagerApi.SensorDataCallback
                public void onSuccess(JSONObject jSONObject2) {
                    UtilEntry.this.onSuccessCallback(j, jSONObject2);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.util.SensorManagerApi.SensorDataCallback
                public void onFailure(int i, String str2) {
                    LogUtil.w(UtilEntry.this.TAG, i + " <- errorCode - registerSensorListener - errorMsg -> " + str2);
                    UtilEntry.this.onFailureCallback(j, str2, i);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.util.SensorManagerApi.SensorDataCallback
                public void onComplete() {
                    LogUtil.i(UtilEntry.this.TAG, "registerSensorListener: onComplete");
                    UtilEntry.this.onCompleteCallback(j, "onComplete", 1);
                }
            });
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "getSensorData: exception(param) -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void registerAudioVolumeChangeListener(long j, String str) {
        try {
            String optString = new JSONObject(str).optString("audioType");
            if (this.f2445a == null) {
                this.f2445a = new AudioManagerApi(this.mContext);
            }
            this.f2445a.registerAudioVolumeChangeListener(optString, a(j));
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "registerAudioVolumeChangeListener: exception(param) -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void openSystemSettings(long j, String str) {
        if (TextUtils.isEmpty(str) || this.mContext == null) {
            onFailureCallback(j, "invalid param");
            return;
        }
        try {
            String optString = new JSONObject(str).optString("action");
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "openSystemSettings fail:param invalid");
                return;
            }
            try {
                Intent intent = new Intent(optString);
                if (TextUtils.equals(optString, "android.settings.APPLICATION_DETAILS_SETTINGS")) {
                    intent.setData(Uri.fromParts("package", this.mContext.getApplicationContext().getPackageName(), null));
                }
                this.mContext.startActivity(intent);
                onSuccessCallback(j);
            } catch (ActivityNotFoundException unused) {
                onFailureCallback(j, "Activity not found");
            }
        } catch (JSONException unused2) {
            onFailureCallback(j, "openSystemSettings fail:param invalid");
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        unregisterSensorListener();
        AudioManagerApi audioManagerApi = this.f2445a;
        if (audioManagerApi != null) {
            audioManagerApi.unRegisterAudioVolumeChangeListener();
            this.f2445a = null;
        }
        super.onDestroy();
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.d = new UtilImpl();
    }

    @JavascriptInterface
    public boolean isAppInstalled(String str) {
        return this.d.isAppInstalled(this.mContext, str);
    }

    @JavascriptInterface
    public void getLaunchTime(long j) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance != null) {
            onSuccessCallback(j, Long.valueOf(h5ProInstance.getLaunchTime()));
        } else {
            onFailureCallback(j, "H5ProInstance is null");
        }
    }

    @JavascriptInterface
    public void getAudioVolume(long j, String str) {
        try {
            String optString = new JSONObject(str).optString("audioType");
            if (this.f2445a == null) {
                this.f2445a = new AudioManagerApi(this.mContext);
            }
            this.f2445a.getAudioVolume(optString, a(j));
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "getAudioVolume: exception(param) -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void deviceInfo(long j) {
        JSONObject deviceInfo = this.d.deviceInfo(this.mContext);
        if (deviceInfo != null) {
            onSuccessCallback(j, deviceInfo);
        } else {
            onFailureCallback(j, "fail to get device info");
        }
    }

    private AudioManagerApi.AudioVolumeListener a(final long j) {
        return new AudioManagerApi.AudioVolumeListener() { // from class: com.huawei.health.h5pro.jsbridge.system.util.UtilEntry.2
            @Override // com.huawei.health.h5pro.jsbridge.system.util.AudioManagerApi.AudioVolumeListener
            public void onSuccess(int i, int i2, int i3) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("volume", i);
                    jSONObject.put("maxVolume", i2);
                    jSONObject.put("minVolume", i3);
                    UtilEntry.this.onSuccessCallback(j, jSONObject);
                } catch (JSONException e) {
                    LogUtil.e(UtilEntry.this.TAG, "getAudioVolumeListener: exception(onSuccess) -> " + e.getMessage());
                    UtilEntry.this.onFailureCallback(j, e.getMessage());
                }
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.util.AudioManagerApi.AudioVolumeListener
            public void onFailure(int i, String str) {
                LogUtil.e(UtilEntry.this.TAG, "getAudioVolumeListener: exception(onFailure) -> " + i + " - " + str);
                UtilEntry.this.onFailureCallback(j, str, i);
            }
        };
    }
}
