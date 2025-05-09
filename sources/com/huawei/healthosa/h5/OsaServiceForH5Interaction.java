package com.huawei.healthosa.h5;

import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.healthosa.h5.OsaServiceForH5Interaction;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.study.apnea.model.bean.algorithm.OsaAppInputS;
import com.study.apnea.utils.jni.OsaCalUtils;
import defpackage.cuk;
import defpackage.cun;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvr;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.koq;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class OsaServiceForH5Interaction extends JsBaseModule {
    private static final int BIT_CONVERT_BYTE = 2;
    private static final int COMMAND_LENGTH = 1;
    private static final int COMMAND_TYPE = 1;
    private static final long CONFIG_ID = 900300008;
    private static final long CONFIG_ID_ACTIVE_SWITCH = 900300007;
    private static final int DEFAULT_ACTION = 1;
    private static final int DEFAULT_ERROR = -1;
    private static final Object LOCK = new Object();
    private static final String RELEASE_TAG = "R_OsaServiceForH5Interaction";
    private static final int SETTING_COMMEND = 1;
    private static final String SRC_PCK_NAME = "hw.health.apneajsmodule";
    private static final int TASK_STATUS_FAILURE = 0;
    private static final int TYPE_ACTIVE = 1;
    private static final String WEAR_PCK_NAME = "hw.watch.health.osa";
    private static OsaServiceForH5Interaction mInteraction;
    private long activeCallBackId;
    private DataReceiveCallback receiveCallback = new DataReceiveCallback() { // from class: com.huawei.healthosa.h5.OsaServiceForH5Interaction.5
        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            LogUtil.c(OsaServiceForH5Interaction.this.TAG, "errorCode=", Integer.valueOf(i));
            if (cvrVar instanceof cvq) {
                List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
                if (koq.b(configInfoList)) {
                    OsaServiceForH5Interaction osaServiceForH5Interaction = OsaServiceForH5Interaction.this;
                    osaServiceForH5Interaction.onFailureCallback(osaServiceForH5Interaction.activeCallBackId, "listInfo isEmpty", -1);
                    LogUtil.a(OsaServiceForH5Interaction.this.TAG, "listInfo isEmpty");
                    return;
                } else {
                    cvn cvnVar = configInfoList.get(0);
                    if (cvnVar != null) {
                        LogUtil.c(OsaServiceForH5Interaction.this.TAG, cvnVar.toString());
                    }
                    OsaServiceForH5Interaction.this.getOsaActiveResult(cvnVar);
                    return;
                }
            }
            LogUtil.a(OsaServiceForH5Interaction.this.TAG, "message error");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0075, code lost:
    
        r1 = health.compact.a.CommonUtil.w(r4.c());
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r3.put("result", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008c, code lost:
    
        r2 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00bd, code lost:
    
        onFailureCallback(r6.activeCallBackId, r2.getMessage(), -1);
        health.compact.a.LogUtil.e(r6.TAG, "tlvException");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        r2 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a5, code lost:
    
        onFailureCallback(r6.activeCallBackId, r2.getMessage(), -1);
        health.compact.a.LogUtil.e(r6.TAG, "jsonException");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getOsaActiveResult(defpackage.cvn r7) {
        /*
            r6 = this;
            if (r7 != 0) goto Le
            java.lang.String r7 = r6.TAG
            java.lang.String r0 = "info ==null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.a(r7, r0)
            return
        Le:
            long r0 = r7.a()
            r2 = 900300007(0x35a97ce7, double:4.448073044E-315)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            java.lang.String r1 = "byteConfigData == null"
            if (r0 == 0) goto L25
            java.lang.String r7 = r6.TAG
            java.lang.Object[] r0 = new java.lang.Object[]{r1}
            health.compact.a.LogUtil.a(r7, r0)
            return
        L25:
            byte[] r7 = r7.b()
            r0 = -1
            if (r7 != 0) goto L3b
            java.lang.String r7 = r6.TAG
            java.lang.Object[] r2 = new java.lang.Object[]{r1}
            health.compact.a.LogUtil.a(r7, r2)
            long r2 = r6.activeCallBackId
            r6.onFailureCallback(r2, r1, r0)
            return
        L3b:
            java.lang.String r7 = defpackage.cvx.d(r7)
            cwl r1 = new cwl     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            r1.<init>()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            cwe r1 = r1.a(r7)     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.util.List r1 = r1.e()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            r2 = 1
            if (r1 == 0) goto L8e
            int r3 = r1.size()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            if (r3 > 0) goto L56
            goto L8e
        L56:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            r3.<init>()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.util.Iterator r1 = r1.iterator()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
        L5f:
            boolean r4 = r1.hasNext()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            if (r4 == 0) goto L83
            java.lang.Object r4 = r1.next()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            cwd r4 = (defpackage.cwd) r4     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.String r5 = r4.e()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            int r5 = health.compact.a.CommonUtil.w(r5)     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            if (r5 != r2) goto L5f
            java.lang.String r1 = r4.c()     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            int r1 = health.compact.a.CommonUtil.w(r1)     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.String r2 = "result"
            r3.put(r2, r1)     // Catch: org.json.JSONException -> L8a defpackage.cwg -> L8c
            goto L84
        L83:
            r1 = r0
        L84:
            long r4 = r6.activeCallBackId     // Catch: org.json.JSONException -> L8a defpackage.cwg -> L8c
            r6.onSuccessCallback(r4, r3)     // Catch: org.json.JSONException -> L8a defpackage.cwg -> L8c
            goto Ld1
        L8a:
            r2 = move-exception
            goto La5
        L8c:
            r2 = move-exception
            goto Lbd
        L8e:
            long r3 = r6.activeCallBackId     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.String r1 = "tlvList is null or empty"
            r6.onFailureCallback(r3, r1, r0)     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.String r1 = r6.TAG     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            java.lang.String r3 = "parseConfigData tlvList is null or empty"
            r4 = 0
            r2[r4] = r3     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            health.compact.a.LogUtil.a(r1, r2)     // Catch: org.json.JSONException -> La2 defpackage.cwg -> Lba
            return
        La2:
            r1 = move-exception
            r2 = r1
            r1 = r0
        La5:
            long r3 = r6.activeCallBackId
            java.lang.String r2 = r2.getMessage()
            r6.onFailureCallback(r3, r2, r0)
            java.lang.String r0 = r6.TAG
            java.lang.String r2 = "jsonException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.LogUtil.e(r0, r2)
            goto Ld1
        Lba:
            r1 = move-exception
            r2 = r1
            r1 = r0
        Lbd:
            long r3 = r6.activeCallBackId
            java.lang.String r2 = r2.getMessage()
            r6.onFailureCallback(r3, r2, r0)
            java.lang.String r0 = r6.TAG
            java.lang.String r2 = "tlvException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.LogUtil.e(r0, r2)
        Ld1:
            java.lang.String r0 = r6.TAG
            java.lang.String r2 = "result="
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "dataHex="
            java.lang.Object[] r7 = new java.lang.Object[]{r3, r7, r2, r1}
            health.compact.a.LogUtil.c(r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthosa.h5.OsaServiceForH5Interaction.getOsaActiveResult(cvn):void");
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        cuk.b();
    }

    public static OsaServiceForH5Interaction getInstance() {
        synchronized (LOCK) {
            if (mInteraction == null) {
                mInteraction = new OsaServiceForH5Interaction();
            }
        }
        return mInteraction;
    }

    @JavascriptInterface
    public void getOsaOutput(long j, String str) {
        LogUtil.d(this.TAG, "getOsaOutput in", str);
        String str2 = "";
        if (str == null || "".equals(str)) {
            onFailureCallback(j, "", 0);
            return;
        }
        try {
            Gson gson = new Gson();
            str2 = gson.toJson(OsaCalUtils.getInstance().osaCalAppResult((OsaAppInputS) gson.fromJson(str, OsaAppInputS.class)));
            LogUtil.d(this.TAG, "getOsaOutput out", str2);
            onSuccessCallback(j, str2);
        } catch (IllegalStateException e) {
            LogUtil.e(this.TAG, "getOsaOutput is exception : ", e.getMessage());
            onFailureCallback(j, str2, 0);
        }
    }

    @JavascriptInterface
    public void isSupportOsaOffload(long j) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (deviceInfo == null) {
            LogUtil.a(this.TAG, "deviceInfo==null");
            onFailureCallback(j, "deviceInfo==null", -1);
            return;
        }
        boolean c = cwi.c(deviceInfo, 107);
        LogUtil.c(this.TAG, "isSupportSleepApnea", Boolean.valueOf(c));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", c);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException unused) {
            LogUtil.e(this.TAG, "jsonException");
        }
    }

    @JavascriptInterface
    public void setSleepApneaActiveState(long j, String str) {
        this.activeCallBackId = j;
        LogUtil.c(this.TAG, "jsonString:", str);
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (deviceInfo == null) {
            LogUtil.a(this.TAG, "DeviceInfo==null");
            onFailureCallback(j, "DeviceInfo==null", -1);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.has("action") ? jSONObject.getInt("action") : 1;
            int i2 = jSONObject.has("state") ? jSONObject.getInt("state") : 1;
            cvq cvqVar = new cvq();
            cvqVar.setSrcPkgName(SRC_PCK_NAME);
            cvqVar.setWearPkgName(WEAR_PCK_NAME);
            cvn cvnVar = new cvn();
            cvnVar.e(CONFIG_ID_ACTIVE_SWITCH);
            cvnVar.d(i);
            String e = cvx.e(i2);
            cvnVar.c(cvx.a(cvx.e(1) + cvx.d(e.length() / 2) + e));
            ArrayList arrayList = new ArrayList();
            arrayList.add(cvnVar);
            cvqVar.setConfigInfoList(arrayList);
            cuk.b().registerDeviceSampleListener(SRC_PCK_NAME, this.receiveCallback);
            boolean sendSampleConfigCommand = cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, this.TAG);
            if (!sendSampleConfigCommand) {
                onFailureCallback(j, "isValid == false", -1);
            }
            LogUtil.c(this.TAG, "isValid:" + sendSampleConfigCommand);
        } catch (JSONException unused) {
            LogUtil.e(this.TAG, "jsonException");
            onFailureCallback(j, TrackConstants$Events.EXCEPTION, -1);
        }
    }

    private cvq constructSampleConfig(int i) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(SRC_PCK_NAME);
        cvqVar.setWearPkgName(WEAR_PCK_NAME);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(CONFIG_ID);
        cvnVar.c(cvx.a(cvx.e(1) + cvx.e(1) + cvx.e(i)));
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    @JavascriptInterface
    public void sendSleepBreatheCommand(long j, int i) {
        LogUtil.c(this.TAG, "sendSleepBreatheCommand switchStatus:", Integer.valueOf(i));
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (deviceInfo == null) {
            LogUtil.a(this.TAG, "DeviceInfo==null");
            onFailureCallback(j, "DeviceInfo==null", -1);
            return;
        }
        boolean sendSampleConfigCommand = cuk.b().sendSampleConfigCommand(deviceInfo, constructSampleConfig(i), this.TAG);
        LogUtil.c(this.TAG, "sendSampleConfigCommand isValid:", Boolean.valueOf(sendSampleConfigCommand));
        if (!sendSampleConfigCommand) {
            onFailureCallback(j, "sendSampleConfigCommand isValid == false", -1);
        } else {
            onSuccessCallback(j, "sendSampleConfigCommand success");
        }
    }

    @JavascriptInterface
    public void startSleepBreatheDetectionPage(long j) {
        LogUtil.c(this.TAG, "startSleepBreatheDetectionPage");
        try {
            cun.c().startSleepBreatheDetectionPage();
            onSuccessCallback(j, "Start sleep breathe detection page success");
        } catch (Exception e) {
            ReleaseLogUtil.c(RELEASE_TAG, "startSleepBreatheDetectionPage Exception:", ExceptionUtils.d(e));
            onFailureCallback(j, "startSleepBreatheDetectionPage exception", -1);
        }
    }

    @JavascriptInterface
    public void setSleepBreatheDetectionState(final long j, boolean z) {
        cun.c().setSleepBreatheDetectionState(z, new IBaseResponseCallback() { // from class: hyl
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                OsaServiceForH5Interaction.this.m541xc0bb71e7(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$setSleepBreatheDetectionState$0$com-huawei-healthosa-h5-OsaServiceForH5Interaction, reason: not valid java name */
    public /* synthetic */ void m541xc0bb71e7(long j, int i, Object obj) {
        if (i == 0) {
            LogUtil.c(this.TAG, "setSleepBreatheDetectionState success");
            onSuccessCallback(j, "Set sleep breathe detection success");
        } else {
            LogUtil.c(this.TAG, "setSleepBreatheDetectionState failed");
            onFailureCallback(j, "Set sleep breathe detection failed", -1);
        }
    }

    @JavascriptInterface
    public void getSleepBreatheDetectionState(final long j) {
        cun.c().getSleepBreatheDetectionState(new IBaseResponseCallback() { // from class: hyj
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                OsaServiceForH5Interaction.this.m540xbe4312d2(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$getSleepBreatheDetectionState$1$com-huawei-healthosa-h5-OsaServiceForH5Interaction, reason: not valid java name */
    public /* synthetic */ void m540xbe4312d2(long j, int i, Object obj) {
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            LogUtil.c(this.TAG, "Get sleep breathe detection success: ", str);
            onSuccessCallback(j, Boolean.valueOf(Boolean.parseBoolean(str)));
        } else {
            LogUtil.c(this.TAG, "getSleepBreatheDetectionState failed");
            onFailureCallback(j, "Get sleep breathe detection failed", -1);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        cuk.b().unRegisterDeviceSampleListener(SRC_PCK_NAME);
    }
}
