package com.huawei.operation.h5pro.jsmodules;

import android.util.SparseArray;
import android.webkit.JavascriptInterface;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cuk;
import defpackage.cun;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvr;
import defpackage.cwd;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mpj;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PpgJsModule extends JsBaseModule {
    private static final int ACTION_MODIFY = 1;
    private static final int ACTION_QUERY = 2;
    private static final String ARRHYTHMIA_AUTOMATIC_ANALYSIS = "ARRHYTHMIA_AUTOMATIC_ANALYSIS";
    private static final String ARRHYTHMIA_PPG_NOTIFY = "ARRHYTHMIA_PPG_NOTIFY";
    private static final int CLOSED = 0;
    private static final String CONFIG_DATA_PREFIX_SWITCH = "01010";
    private static final long CONFIG_ID_ACTIVE_SWITCH = 900300004;
    private static final long CONFIG_ID_MEASURE_SWITCH = 900300003;
    private static final long CONFIG_ID_NOTIFY_SWITCH = 900300009;
    private static final long CONFIG_ID_PERIOD_SWITCH = 900300002;
    private static final String CONFIG_MISSION_MODIFY_ACTIVE = "modifyActiveSwitch";
    private static final String CONFIG_MISSION_QUERY_ACTIVE = "queryActiveSwitch";
    private static final String CONFIG_MISSION_START_MEASURE = "startMeasure";
    private static final String CONFIG_MISSION_STOP_MEASURE = "stopMeasure";
    private static final int OPENED = 1;
    private static final String SIGNAL_BLUETOOTH_DISCONNECT = "125004";
    private static final String SIGNAL_GOOD = "126102";
    private static final String SIGNAL_START_FAIL = "125006";
    private static final String SRC_PCK_NAME = "hw.health.ppgjsmodule";
    private static final int TYPE_PPG_MEASURE_SUCCESS = 126104;
    private static final String WEAR_PCK_NAME = "hw.watch.health.arrhythmia";
    private cuk mCommandProxy;
    private List<Integer> mUnSubscribeList = new ArrayList();

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.mCommandProxy = cuk.b();
    }

    @JavascriptInterface
    public void sendSampleConfigCommand(long j, String str) {
        char c;
        LogUtil.a(this.TAG, "send sample config");
        cvq structSampleConfig = structSampleConfig(str);
        if (structSampleConfig == null) {
            onFailureCallback(j, "SampleConfig with invalid params");
            return;
        }
        cvn firstSampleConfigInfo = getFirstSampleConfigInfo(structSampleConfig);
        if (firstSampleConfigInfo == null) {
            onFailureCallback(j, "SampleConfig is missing params");
            return;
        }
        String configMission = getConfigMission(firstSampleConfigInfo);
        configMission.hashCode();
        int hashCode = configMission.hashCode();
        if (hashCode == -707934526) {
            if (configMission.equals(CONFIG_MISSION_QUERY_ACTIVE)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -346452228) {
            if (hashCode == 803874460 && configMission.equals(CONFIG_MISSION_START_MEASURE)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (configMission.equals(CONFIG_MISSION_STOP_MEASURE)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            LogUtil.a(this.TAG, "sendSampleConfigCommand query active switch state");
            this.mCommandProxy.registerDeviceSampleListener(SRC_PCK_NAME, new ActiveSwitchQueryCallbackHandler(j));
        } else if (c == 1) {
            LogUtil.a(this.TAG, "sendSampleConfigCommand manually stop measure");
            this.mCommandProxy.unRegisterDeviceSampleListener(SRC_PCK_NAME);
        } else if (c == 2) {
            LogUtil.a(this.TAG, "sendSampleConfigCommand manually start measure");
            this.mCommandProxy.registerDeviceSampleListener(SRC_PCK_NAME, new ManuallyStartCallbackHandler(j));
        } else {
            LogUtil.a(this.TAG, "sendSampleConfigCommand directly send command");
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG);
        if (!this.mCommandProxy.sendSampleConfigCommand(deviceInfo, structSampleConfig, this.TAG)) {
            callbackFailureAndUnregisterProxy("sendSampleConfigCommand", "SampleConfig with invalid params", j);
            if (CONFIG_MISSION_START_MEASURE.equals(configMission)) {
                onCompleteCallback(j, deviceInfo.getDeviceConnectState() == 2 ? SIGNAL_START_FAIL : SIGNAL_BLUETOOTH_DISCONNECT, 0);
                return;
            }
            return;
        }
        if (CONFIG_MISSION_QUERY_ACTIVE.equals(configMission) || CONFIG_MISSION_START_MEASURE.equals(configMission)) {
            return;
        }
        onSuccessCallback(j, structResult(0, "Command sent"));
    }

    private cvq structSampleConfig(String str) {
        try {
            cvq cvqVar = new cvq();
            JSONObject jSONObject = new JSONObject(str);
            cvqVar.setSrcPkgName(jSONObject.getString("srcPkgName"));
            cvqVar.setWearPkgName(jSONObject.getString("wearPkgName"));
            cvn cvnVar = new cvn();
            JSONObject jSONObject2 = jSONObject.getJSONArray("configInfoList").getJSONObject(0);
            cvnVar.e(jSONObject2.getLong("configId"));
            cvnVar.d(jSONObject2.getInt("configAction"));
            JSONArray optJSONArray = jSONObject2.optJSONArray("byteConfigData");
            if (optJSONArray != null) {
                byte[] bArr = new byte[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    bArr[i] = (byte) optJSONArray.getInt(i);
                }
                cvnVar.c(bArr);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(cvnVar);
            cvqVar.setConfigInfoList(arrayList);
            return cvqVar;
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "structSampleConfig JSONException");
            return null;
        }
    }

    private String getConfigMission(cvn cvnVar) {
        long a2 = cvnVar.a();
        if (a2 == CONFIG_ID_ACTIVE_SWITCH) {
            return cvnVar.e() == 2 ? CONFIG_MISSION_QUERY_ACTIVE : CONFIG_MISSION_MODIFY_ACTIVE;
        }
        if (a2 != CONFIG_ID_MEASURE_SWITCH) {
            return "";
        }
        String a3 = HEXUtils.a(cvnVar.b());
        a3.hashCode();
        return !a3.equals("010100") ? !a3.equals("010101") ? "" : CONFIG_MISSION_START_MEASURE : CONFIG_MISSION_STOP_MEASURE;
    }

    class ActiveSwitchQueryCallbackHandler implements DataReceiveCallback {
        private final long mCallbackId;

        ActiveSwitchQueryCallbackHandler(long j) {
            this.mCallbackId = j;
            LogUtil.a(PpgJsModule.this.TAG, "generate callback for query active switch");
        }

        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            if (!(cvrVar instanceof cvq)) {
                PpgJsModule.this.callbackFailureAndUnregisterProxy("callbackHandlerActiveSwitchQuery", "SampleConfig in invalid format", this.mCallbackId);
                return;
            }
            cvn firstSampleConfigInfo = PpgJsModule.this.getFirstSampleConfigInfo((cvq) cvrVar);
            if (firstSampleConfigInfo == null) {
                PpgJsModule.this.callbackFailureAndUnregisterProxy("callbackHandlerActiveSwitchQuery", "SampleConfig is missing params", this.mCallbackId);
                return;
            }
            String a2 = HEXUtils.a(firstSampleConfigInfo.b());
            LogUtil.a(PpgJsModule.this.TAG, "callbackHandlerActiveSwitchQuery Received byteConfigData=", a2);
            int w = CommonUtil.w(PpgJsModule.this.getFirstTlv(a2).c());
            LogUtil.a(PpgJsModule.this.TAG, "callbackHandlerActiveSwitchQuery active switch status receive success with value: ", Integer.valueOf(w));
            if (w == -1) {
                PpgJsModule.this.callbackFailureAndUnregisterProxy("callbackHandlerActiveSwitchQuery", "Switch status invalid", this.mCallbackId);
                return;
            }
            PpgJsModule ppgJsModule = PpgJsModule.this;
            ppgJsModule.onSuccessCallback(this.mCallbackId, ppgJsModule.structResult(0, String.valueOf(w)));
            PpgJsModule.this.mCommandProxy.unRegisterDeviceSampleListener(PpgJsModule.SRC_PCK_NAME);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFailureAndUnregisterProxy(String str, String str2, long j) {
        if (str != null) {
            LogUtil.b(this.TAG, str + " " + str2);
        }
        onFailureCallback(j, str2);
        this.mCommandProxy.unRegisterDeviceSampleListener(SRC_PCK_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cvn getFirstSampleConfigInfo(cvq cvqVar) {
        LogUtil.a(this.TAG, "get first sample config info in sample config");
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (configInfoList == null) {
            return null;
        }
        return configInfoList.get(0);
    }

    class ManuallyStartCallbackHandler implements DataReceiveCallback {
        private final long mCallbackId;
        private String mReceiveCode = null;

        ManuallyStartCallbackHandler(long j) {
            this.mCallbackId = j;
            LogUtil.a(PpgJsModule.this.TAG, "generate callback for start measure");
        }

        private void errorAndFail(String str) {
            PpgJsModule.this.callbackFailureAndUnregisterProxy("manuallyStartCallbackHandler", str, this.mCallbackId);
        }

        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            if (cvrVar instanceof cvq) {
                cvn firstSampleConfigInfo = PpgJsModule.this.getFirstSampleConfigInfo((cvq) cvrVar);
                if (firstSampleConfigInfo == null) {
                    errorAndFail("SampleConfig with invalid params");
                    return;
                }
                String a2 = HEXUtils.a(firstSampleConfigInfo.b());
                LogUtil.a(PpgJsModule.this.TAG, "manuallyStartCallbackHandler Received byteConfigData=", a2);
                int w = CommonUtil.w(PpgJsModule.this.getFirstTlv(a2).c());
                if (w == -1) {
                    errorAndFail("Data received an invalid error code");
                    return;
                }
                String valueOf = String.valueOf(w);
                if (this.mReceiveCode != null) {
                    LogUtil.a(PpgJsModule.this.TAG, "manuallyStartCallbackHandler completed");
                    PpgJsModule.this.onCompleteCallback(this.mCallbackId, valueOf, 0);
                    PpgJsModule.this.mCommandProxy.unRegisterDeviceSampleListener(PpgJsModule.SRC_PCK_NAME);
                    return;
                }
                PpgJsModule ppgJsModule = PpgJsModule.this;
                ppgJsModule.onSuccessCallback(this.mCallbackId, ppgJsModule.structResult(0, valueOf));
                this.mReceiveCode = valueOf;
                LogUtil.a(PpgJsModule.this.TAG, "manuallyStartCallbackHandler [CODE ", this.mReceiveCode, "] received and updated");
                if (this.mReceiveCode.equals(PpgJsModule.SIGNAL_GOOD)) {
                    return;
                }
                LogUtil.a(PpgJsModule.this.TAG, "manuallyStartCallbackHandler signal unqualified, receiveCode: ", valueOf, ", terminating");
                PpgJsModule.this.mCommandProxy.unRegisterDeviceSampleListener(PpgJsModule.SRC_PCK_NAME);
                return;
            }
            errorAndFail("SampleConfig in invalid format");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cwd getFirstTlv(String str) {
        cwd cwdVar = new cwd();
        try {
            return new cwl().a(str).e().get(0);
        } catch (cwg unused) {
            LogUtil.b(this.TAG, "getFirstTlv TlvException");
            return cwdVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> JSONObject structResult(int i, T t) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("resultCode", i);
            jSONObject2.put("resultDesc", t);
            jSONObject.put("data", jSONObject2);
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "structResult JSONException");
        }
        LogUtil.a(this.TAG, "structResult constructed json with resultCode: ", Integer.valueOf(i));
        return jSONObject;
    }

    @JavascriptInterface
    public void setPeriodSwitchState(long j, int i) {
        if (!setPeriodSwitch(i)) {
            LogUtil.a(this.TAG, "setAutoPeriodSwitchState isSuccess false, isOpened ", Integer.valueOf(i));
            onFailureCallback(j, "Not connecting correct device or targetState invalid");
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(ARRHYTHMIA_AUTOMATIC_ANALYSIS);
        hiUserPreference.setValue(String.valueOf(i));
        boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        LogUtil.a(this.TAG, "setAutoPeriodSwitchState isSuccess ", Boolean.valueOf(userPreference), ", isOpened ", Integer.valueOf(i));
        if (!userPreference) {
            setPeriodSwitch(0);
            onFailureCallback(j, "Change local setting failed");
        } else {
            onSuccessCallback(j, structResult(0, 0));
        }
    }

    private boolean setPeriodSwitch(int i) {
        if (i != 1 && i != 0) {
            return false;
        }
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(SRC_PCK_NAME);
        cvqVar.setWearPkgName(WEAR_PCK_NAME);
        cvn cvnVar = new cvn();
        cvnVar.e(CONFIG_ID_PERIOD_SWITCH);
        cvnVar.d(1);
        cvnVar.c(HEXUtils.c(CONFIG_DATA_PREFIX_SWITCH + i));
        ArrayList arrayList = new ArrayList();
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cuk.b().sendSampleConfigCommand(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG), cvqVar, this.TAG);
    }

    private int getSwitchState(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference(str);
        if (userPreference != null) {
            return CommonUtil.w(userPreference.getValue());
        }
        return 0;
    }

    @JavascriptInterface
    public void getPeriodSwitchState(long j) {
        try {
            int switchState = getSwitchState(ARRHYTHMIA_AUTOMATIC_ANALYSIS);
            if (switchState == -1) {
                LogUtil.b(this.TAG, "getPeriodSwitchState NumberFormatException");
                onFailureCallback(j, "NumberFormatException in database");
            }
            LogUtil.a(this.TAG, "getPeriodSwitchState result ", Integer.valueOf(switchState));
            onSuccessCallback(j, Integer.valueOf(switchState));
        } catch (NumberFormatException unused) {
            LogUtil.b(this.TAG, "getAutoPeriodSwitchState NumberFormatException");
            onFailureCallback(j, "NumberFormatException in database");
        }
    }

    @JavascriptInterface
    public void setNotifySwitchState(long j, int i) {
        if (!sendSetSwitchConfigCommand(i)) {
            LogUtil.b(this.TAG, "setNotifySwitchState isSuccess false, isOpened ", Integer.valueOf(i));
            onFailureCallback(j, "Not connecting correct device or targetState invalid");
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(ARRHYTHMIA_PPG_NOTIFY);
        hiUserPreference.setValue(String.valueOf(i));
        boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        LogUtil.a(this.TAG, "setNotifySwitchState isSuccess ", Boolean.valueOf(userPreference), ", isOpened ", Integer.valueOf(i));
        if (!userPreference) {
            sendSetSwitchConfigCommand(0);
            onFailureCallback(j, "Change local setting failed");
        } else {
            onSuccessCallback(j, structResult(0, 0));
        }
    }

    private boolean sendSetSwitchConfigCommand(int i) {
        if (i != 1 && i != 0) {
            return false;
        }
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(SRC_PCK_NAME);
        cvqVar.setWearPkgName(WEAR_PCK_NAME);
        cvn cvnVar = new cvn();
        cvnVar.e(CONFIG_ID_NOTIFY_SWITCH);
        cvnVar.d(1);
        cvnVar.c(HEXUtils.c(CONFIG_DATA_PREFIX_SWITCH + i));
        ArrayList arrayList = new ArrayList();
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cuk.b().sendSampleConfigCommand(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG), cvqVar, this.TAG);
    }

    @JavascriptInterface
    public void getNotifySwitchState(long j) {
        int switchState = getSwitchState(ARRHYTHMIA_PPG_NOTIFY);
        if (switchState == -1) {
            LogUtil.b(this.TAG, "getNotifySwitchState NumberFormatException");
            onFailureCallback(j, "NumberFormatException in database");
        }
        LogUtil.a(this.TAG, "getNotifySwitchState result ", Integer.valueOf(switchState));
        onSuccessCallback(j, Integer.valueOf(switchState));
    }

    @JavascriptInterface
    public void hasHistoryData(final long j) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()});
        hiDataReadOption.setCount(1);
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(1).e(), new HiDataReadResultListener() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                try {
                    if (!(obj instanceof SparseArray)) {
                        PpgJsModule.this.onFailureCallback(j, "hasHistoryData Received a result with wrong type");
                        return;
                    }
                    List list = (List) ((SparseArray) obj).get(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value());
                    String str = PpgJsModule.this.TAG;
                    Object[] objArr = new Object[2];
                    boolean z = false;
                    objArr[0] = "hasHistoryData onResult list size = ";
                    objArr[1] = Integer.valueOf(list == null ? 0 : list.size());
                    LogUtil.a(str, objArr);
                    PpgJsModule ppgJsModule = PpgJsModule.this;
                    long j2 = j;
                    if (list != null && !list.isEmpty()) {
                        z = true;
                    }
                    ppgJsModule.onSuccessCallback(j2, Boolean.valueOf(z));
                } catch (ClassCastException unused) {
                    LogUtil.b(PpgJsModule.this.TAG, "hasHistoryData onResult ClassCastException");
                    PpgJsModule.this.onFailureCallback(j, "hasHistoryData Receive data with wrong format");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                PpgJsModule.this.onFailureCallback(j, "hasHistoryData onResultIntent");
            }
        });
    }

    private List<JSONObject> toJsonList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        try {
            LogUtil.a(this.TAG, "toJsonList rawList size=", Integer.valueOf(jSONArray.length()));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "toJsonList JSONException", e.getMessage());
        }
        return arrayList;
    }

    @JavascriptInterface
    public void setProductModelPara(long j, String str) {
        try {
            LogUtil.a(this.TAG, "setProductModelPara");
            JSONObject jSONObject = new JSONObject(str);
            onSuccessCallback(j, Integer.valueOf(mpj.a().setProductModelPara(jSONObject.getInt("maxPpgAmpRange"), jSONObject.getInt("minPpgAmpRange"))));
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "setProductModelPara JSONException");
            onFailureCallback(j, "JSONException: invalid json string input");
        }
    }

    @JavascriptInterface
    public void getSqiResult(long j, String str) {
        try {
            LogUtil.a(this.TAG, "getSqiResult");
            JSONObject jSONObject = new JSONObject(str);
            List<JSONObject> list = (List) HiJsonUtil.b(jSONObject.getString("atrialDataStructList"), new TypeToken<List<JSONObject>>() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule.2
            }.getType());
            onSuccessCallback(j, Integer.valueOf(mpj.a().getSqiResult(list, list.size(), jSONObject.getInt("firstFlag"))));
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "getSqiResult JSONException");
            onFailureCallback(j, "JSONException: invalid json string input");
        }
    }

    @JavascriptInterface
    public void getDrawData(long j, String str) {
        try {
            LogUtil.a(this.TAG, "getDrawData start");
            JSONObject jSONObject = new JSONObject(str);
            List<JSONObject> jsonList = toJsonList(jSONObject.getJSONArray("ppgSamplePointClonesList"));
            List<JSONObject> jsonList2 = toJsonList(jSONObject.getJSONArray("deviceMeasureRstBeanList"));
            LogUtil.a(this.TAG, "getDrawData ppgSamplePointClonesList size=", Integer.valueOf(jsonList.size()), ", deviceMeasureRstBeanList size=", Integer.valueOf(jsonList2.size()));
            onSuccessCallback(j, mpj.a().getDrawData(jsonList, jsonList2, jsonList.size(), jsonList2.size()));
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getDrawData JSONException", e.getMessage());
            onFailureCallback(j, "JSONException: invalid json string input");
        }
    }

    @JavascriptInterface
    public void setPpgAccData(long j, String str) {
        mpj.a().setPpgAccData(str);
    }

    @JavascriptInterface
    public void startPpgAlgorithm(final long j) {
        mpj.a().startPpgAlgorithm(new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                PpgJsModule.this.m729x4ac4b64b(j, i, (JSONObject) obj);
            }
        });
    }

    /* renamed from: lambda$startPpgAlgorithm$0$com-huawei-operation-h5pro-jsmodules-PpgJsModule, reason: not valid java name */
    /* synthetic */ void m729x4ac4b64b(long j, int i, JSONObject jSONObject) {
        LogUtil.a(this.TAG, "measure responseCallback", "jsonObject:", jSONObject.toString(), "resultCode:", Integer.valueOf(i));
        if (i == 0) {
            onSuccessCallback(j, jSONObject);
            try {
                if (jSONObject.getInt("resultDesc") == TYPE_PPG_MEASURE_SUCCESS) {
                    onCompleteCallback(j, String.valueOf(TYPE_PPG_MEASURE_SUCCESS), i);
                    return;
                }
                return;
            } catch (JSONException unused) {
                LogUtil.b(this.TAG, "jsonException resultDesc not exist");
                onFailureCallback(j, "jsonException resultDesc not exist");
                return;
            }
        }
        onFailureCallback(j, jSONObject.toString());
    }

    @JavascriptInterface
    public void getPeriodMeasureResult(final long j, final String str) {
        jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PpgJsModule.this.m728xfccab095(str, j);
            }
        });
    }

    /* renamed from: lambda$getPeriodMeasureResult$2$com-huawei-operation-h5pro-jsmodules-PpgJsModule, reason: not valid java name */
    /* synthetic */ void m728xfccab095(String str, long j) {
        LogUtil.c(this.TAG, "getPeriodMeasureResult", str);
        try {
            HiHealthData hiHealthData = new HiHealthData();
            JSONObject jSONObject = new JSONObject(str);
            hiHealthData.setStartTime(jSONObject.getLong("mStartTime"));
            hiHealthData.setEndTime(jSONObject.getLong("mEndTime"));
            hiHealthData.setType(jSONObject.getInt("mType"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("mValueHolder").getJSONObject("mMap");
            hiHealthData.setSequenceData(jSONObject2.getString("detail_data"));
            hiHealthData.setDeviceUuid(jSONObject2.getString("device_uniquecode"));
            hiHealthData.setMetaData(jSONObject2.getJSONObject("meta_data").toString());
            HiHealthData periodMeasureResult = mpj.a().getPeriodMeasureResult(hiHealthData);
            periodMeasureResult.setDeviceUuid(hiHealthData.getDeviceUuid());
            String simpleData = periodMeasureResult.getSimpleData();
            ArrayList arrayList = new ArrayList();
            arrayList.add(periodMeasureResult);
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(arrayList);
            HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule$$ExternalSyntheticLambda3
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    PpgJsModule.this.m727xb210a76(i, obj);
                }
            });
            LogUtil.c(this.TAG, "drawData:", simpleData);
            onSuccessCallback(j, simpleData);
        } catch (JSONException unused) {
            LogUtil.a(this.TAG, "getPeriodMeasureResult jsonSyntaxException");
        }
    }

    /* renamed from: lambda$getPeriodMeasureResult$1$com-huawei-operation-h5pro-jsmodules-PpgJsModule, reason: not valid java name */
    /* synthetic */ void m727xb210a76(int i, Object obj) {
        health.compact.a.util.LogUtil.d(this.TAG, "save dataResult errorCode:", Integer.valueOf(i), "message：", obj);
    }

    @JavascriptInterface
    public void stopPpgAlgorithm(long j) {
        mpj.a().stopPpgAlgorithm();
    }

    @JavascriptInterface
    public void subscribePpgHiHealthData(final long j) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()));
        LogUtil.a(this.TAG, "subscribePpgHiHealthData", arrayList.toString());
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new HiSubscribeListener() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule.3
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                if (koq.c(list)) {
                    PpgJsModule.this.mUnSubscribeList.addAll(list);
                    LogUtil.a(PpgJsModule.this.TAG, "successList.toString():", list.toString());
                }
                if (koq.c(list2)) {
                    LogUtil.a(PpgJsModule.this.TAG, "failList.toString():", list2.toString());
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j2) {
                LogUtil.a(PpgJsModule.this.TAG, "onChange", Integer.valueOf(i));
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("resultCode", 0);
                    PpgJsModule.this.onSuccessCallback(j, jSONObject.toString());
                } catch (JSONException unused) {
                    LogUtil.a(PpgJsModule.this.TAG, "jsonException");
                }
            }
        });
    }

    @JavascriptInterface
    public void unSubscribePpgHiHealthData(long j) {
        LogUtil.a(this.TAG, "unSubscribePpgHiHealthData", "callbackId:", Long.valueOf(j));
        if (koq.b(this.mUnSubscribeList)) {
            LogUtil.h(this.TAG, "mSuccessList isEmpty");
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.mUnSubscribeList, new HiUnSubscribeListener() { // from class: com.huawei.operation.h5pro.jsmodules.PpgJsModule$$ExternalSyntheticLambda2
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    PpgJsModule.this.m730xf4eeb1b(z);
                }
            });
        }
    }

    /* renamed from: lambda$unSubscribePpgHiHealthData$3$com-huawei-operation-h5pro-jsmodules-PpgJsModule, reason: not valid java name */
    /* synthetic */ void m730xf4eeb1b(boolean z) {
        LogUtil.a(this.TAG, "unSubscribeHiHealthData isSuccess = ", Boolean.valueOf(z));
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(this.TAG, "onDestroy");
        mpj.a().stopPpgAlgorithm();
        unSubscribePpgHiHealthData(0L);
    }
}
