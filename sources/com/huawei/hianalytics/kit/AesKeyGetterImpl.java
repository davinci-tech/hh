package com.huawei.hianalytics.kit;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.data.IAesKeyGetter;
import com.huawei.hianalytics.framework.data.WorkKeyHandler;
import com.huawei.hianalytics.j;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.u0;
import com.huawei.hianalytics.v0;
import com.huawei.hianalytics.w0;
import com.huawei.hianalytics.x0;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AesKeyGetterImpl implements IAesKeyGetter {
    public static final String TAG = "AesKeyGetterImpl";

    @Override // com.huawei.hianalytics.framework.data.IAesKeyGetter
    public void requestAesKey(String str, final IAesKeyGetter.Callback callback) {
        if (!j.m573f()) {
            reportRequest(401);
            return;
        }
        if (!x0.a(true)) {
            reportRequest(402);
            return;
        }
        if (EnvUtils.getAppContext() == null) {
            reportRequest(403);
            return;
        }
        HiAnalyticsClientImpl hiAnalyticsClientImpl = new HiAnalyticsClientImpl(EnvUtils.getAppContext(), new v0());
        hiAnalyticsClientImpl.setApiLevel(1);
        hiAnalyticsClientImpl.setKitSdkVersion(302130501);
        hiAnalyticsClientImpl.getAesKey(new u0("get_key", j.c(), str, j.e())).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hianalytics.kit.AesKeyGetterImpl$$ExternalSyntheticLambda0
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                HiLog.w(AesKeyGetterImpl.TAG, "request aes key failed");
            }
        }).addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.hianalytics.kit.AesKeyGetterImpl$$ExternalSyntheticLambda1
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                AesKeyGetterImpl.this.a(callback, (w0) obj);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.hianalytics.kit.AesKeyGetterImpl$$ExternalSyntheticLambda2
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                AesKeyGetterImpl.this.a(task);
            }
        });
    }

    @Override // com.huawei.hianalytics.framework.data.IAesKeyGetter
    public void saveWorkKeyBeanToDisk(WorkKeyHandler.WorkKeyBean workKeyBean) {
        if (workKeyBean == null) {
            return;
        }
        refreshWorkKeyToSP(workKeyBean.getEncrypted(), workKeyBean.getPubKeyVer(), workKeyBean.getWorkKey(), workKeyBean.getUploadTime(), workKeyBean.getKeyTtlTime(), workKeyBean.getSource());
    }

    @Override // com.huawei.hianalytics.framework.data.IAesKeyGetter
    public WorkKeyHandler.WorkKeyBean getWorkKeyBeanFromDisk() {
        String str;
        int i;
        String str2;
        String a2 = j.a("Privacy_MY", "ha_aes_key_json", "");
        if (TextUtils.isEmpty(a2)) {
            return new WorkKeyHandler.WorkKeyBean();
        }
        try {
            JSONObject jSONObject = new JSONObject(a2);
            long optLong = jSONObject.optLong("ha_aes_key_timestamp");
            long optLong2 = jSONObject.optLong("ha_aes_key_ttl_timestamp");
            String optString = jSONObject.optString("ha_aes_key_encrypted");
            String optString2 = jSONObject.optString("ha_aes_key_encrypted_version");
            String decrypt = AesGcmKsWrapper.decrypt("HiAnalytics_Ha_Aes_Sp_Key", jSONObject.optString("ha_aes_key"));
            int optInt = jSONObject.optInt("ha_aes_key_source", 0);
            if (optLong == 0) {
                long optLong3 = jSONObject.optLong("ha_aes_key_time");
                long optLong4 = jSONObject.optLong("ha_aes_key_ttl_time");
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (optLong3 >= elapsedRealtime || elapsedRealtime >= optLong4) {
                    str = decrypt;
                    i = optInt;
                    str2 = optString2;
                    j.m559a("Privacy_MY", "ha_aes_key_json", "");
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    optLong = currentTimeMillis - (elapsedRealtime - optLong3);
                    optLong2 = currentTimeMillis - (elapsedRealtime - optLong4);
                    str = decrypt;
                    refreshWorkKeyToSP(optString, optString2, decrypt, optLong, optLong2, optInt);
                    i = optInt;
                    str2 = optString2;
                }
            } else {
                str = decrypt;
                i = optInt;
                str2 = optString2;
            }
            WorkKeyHandler.WorkKeyBean workKeyBean = new WorkKeyHandler.WorkKeyBean();
            workKeyBean.setEncrypted(optString);
            workKeyBean.setWorkKey(str);
            workKeyBean.setUploadTime(optLong);
            workKeyBean.setKeyTtlTime(optLong2);
            workKeyBean.setPubKeyVer(str2);
            workKeyBean.setSource(i);
            return workKeyBean;
        } catch (JSONException unused) {
            HiLog.e(TAG, "obtain report aes key");
            return new WorkKeyHandler.WorkKeyBean();
        }
    }

    private void reportRequest(JSONObject jSONObject) {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
        if (instanceByTag == null) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (TextUtils.isEmpty(jSONObject.optString("message"))) {
            linkedHashMap.put("$code", jSONObject.optString("status"));
        } else {
            linkedHashMap.put("$code", String.valueOf(jSONObject.optInt("status")));
            linkedHashMap.put("$message", jSONObject.optString("message"));
            linkedHashMap.put("$version", jSONObject.optString("version"));
            linkedHashMap.put("$type", jSONObject.optString("key_type"));
        }
        instanceByTag.onEvent(1, "$request_key", linkedHashMap);
    }

    private void reportRequest(int i) {
        String str;
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
        if (instanceByTag == null) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("$code", String.valueOf(i));
        switch (i) {
            case 401:
                str = "QoES request failed because device is not Huawei device";
                break;
            case 402:
                str = "QoES request failed because hms base is not exist";
                break;
            case 403:
                str = "QoES request failed because context is null";
                break;
        }
        linkedHashMap.put("$message", str);
        instanceByTag.onEvent(1, "$request_key", linkedHashMap);
    }

    private void refreshWorkKeyToSP(String str, String str2, String str3, long j, long j2, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ha_aes_key_encrypted", str);
            jSONObject.put("ha_aes_key_encrypted_version", str2);
            jSONObject.put("ha_aes_key", AesGcmKsWrapper.encrypt("HiAnalytics_Ha_Aes_Sp_Key", str3));
            jSONObject.put("ha_aes_key_timestamp", j);
            jSONObject.put("ha_aes_key_ttl_timestamp", j2);
            jSONObject.put("ha_aes_key_source", i);
            j.m559a("Privacy_MY", "ha_aes_key_json", jSONObject.toString());
        } catch (JSONException unused) {
            HiLog.e(TAG, "put report aes key sp");
        }
    }

    private void handleQoesResponse(String str, IAesKeyGetter.Callback callback) {
        String str2;
        if (callback == null) {
            str2 = "request aes key without callback";
        } else {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("status") != 200) {
                        HiLog.i(TAG, "request aes key status code 500");
                        return;
                    }
                    long optLong = jSONObject.optLong("key_timestamp");
                    long optLong2 = jSONObject.optLong("key_ttl_timestamp");
                    if (optLong == 0) {
                        long optLong3 = jSONObject.optLong("key_time");
                        long optLong4 = jSONObject.optLong("key_ttl");
                        long currentTimeMillis = System.currentTimeMillis();
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        optLong = currentTimeMillis - (elapsedRealtime - optLong3);
                        optLong2 = currentTimeMillis - (elapsedRealtime - optLong4);
                    }
                    long j = optLong;
                    long j2 = optLong2;
                    String optString = jSONObject.optString(MedalConstants.EVENT_KEY);
                    String optString2 = jSONObject.optString("chifer");
                    String optString3 = jSONObject.optString("version");
                    if (!TextUtils.equals(optString3, "2.0")) {
                        HiLog.i(TAG, "Invalid version: " + optString3);
                        return;
                    }
                    WorkKeyHandler.WorkKeyBean workKeyBean = new WorkKeyHandler.WorkKeyBean();
                    workKeyBean.setEncrypted(optString2);
                    workKeyBean.setWorkKey(optString);
                    workKeyBean.setUploadTime(j);
                    workKeyBean.setKeyTtlTime(j2);
                    workKeyBean.setPubKeyVer(optString3);
                    workKeyBean.setSource(1);
                    callback.onResult(workKeyBean);
                    refreshWorkKeyToSP(optString2, optString3, optString, j, j2, 1);
                    HiLog.i(TAG, "request aes key success");
                    return;
                } catch (JSONException unused) {
                    HiLog.e(TAG, "request aes key error");
                    return;
                }
            }
            str2 = "request aes key failed, rsp is empty";
        }
        HiLog.i(TAG, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Task task) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (task.isSuccessful()) {
                jSONObject = new JSONObject(((w0) task.getResult()).f3959a);
            } else {
                jSONObject.put("status", task.getException().getMessage());
            }
        } catch (JSONException unused) {
            HiLog.e(TAG, "report request aes key");
        }
        reportRequest(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IAesKeyGetter.Callback callback, w0 w0Var) {
        handleQoesResponse(w0Var.f3959a, callback);
    }
}
