package com.huawei.hwdevice.phoneprocess.mgr.heartrate;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.HeartRateInfo;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwWorkoutServiceAw70Manager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseLinkageUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.WorkoutTypeMapManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.remote.HwStressDataProvider;
import com.huawei.operation.ble.BleConstants;
import defpackage.blt;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jsl;
import defpackage.jsz;
import defpackage.kcy;
import defpackage.kkm;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HwHeartRateManager implements ParserInterface {
    private static final String AM16_PRODUCT_ID = "6d5416d9-2167-41df-ab10-c492e152b44f";
    private static final String COM_HUAWEI_HEALTH = "com.huawei.health";
    private static final int DEFAULT_LENGTH = 1;
    private static final String DURATION = "duration";
    private static final int ERROR_PARAMS = 125001;
    private static final int ERROR_TYPE = 127;
    private static final int FIRST_LOCATION = 0;
    private static final int HEX_TO_BYTE = 2;
    private static final int HEX_TO_DECIMAL = 16;
    private static final String MAX_DURATION = "max_duration";
    private static final int MIN_SIZE = 0;
    private static final int MSG_CALIB_OPEN_TIMEOUT = 1;
    private static final int MSG_GAME_OPEN_TIMEOUT = 3;
    private static final int MSG_RELAX_OPEN_TIMEOUT = 2;
    private static final int MSG_STRESS_OPEN_TIMEOUT = 0;
    private static final String OPERATOR_TYPE = "operator_type";
    private static final int PLACEHOLDERS = 2;
    private static final String RESULT_CODE = "result_code";
    private static final String SCORE = "score";
    private static final int SECOND_TO_MILLISECOND = 1000;
    private static final int STATUS_RRI_CLOSE = 4;
    private static final int STRESS_SWITCH_OPEN_TIMEOUT_DELAY = 5000;
    private static final String TAG = "HwHeartRateManager";
    private static final int TLV_HEAD = 4;
    private static final String TYPE = "type";
    private static HwHeartRateManager sInstance = null;
    private static volatile boolean sIsHealthRriOpen = false;
    private boolean mHasReceiverDataStatus;
    private HeartRateStressHandler mHeartRateStressHandler;
    private String mProductId;
    private IBaseResponseCallback mRelaxAbortCallback;
    private IBaseResponseCallback mStressAbortCallback;
    private IBaseResponseCallback mStressCalibrationAbortCallback;
    private HandlerThread mStressHandlerThread;
    private static final Object NOTIFICATION_RRI_INFO_LOCK = new Object();
    private static final Object LOCK_OBJECT = new Object();
    private static final Object DEVICE_LIST_LOCK = new Object();
    private static List<IBaseResponseCallback> sCloseOrOpenReportSwitchCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sHeartRateStatusCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sDeviceHeartRateCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sStressStatusCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sDeviceStressCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sDevicePressCallbackList = new ArrayList(16);
    private static List<String> sKitHeartRateStatusCallerList = new ArrayList(16);
    private static List<String> sKitRriStatusCallerList = new ArrayList(16);
    private static Map<String, IBaseResponseCallback> sKitHeartRateCallbackList = new HashMap(16);
    private static Map<String, IBaseResponseCallback> sKitRriCallbackList = new HashMap(16);
    private int mLoudspeakerMuteStatus = 0;
    private Handler mHandler = new Handler();
    private List<Integer> mRriTimeGetterList = new ArrayList(16);
    private List<Integer> mRriList = new ArrayList(16);
    private List<Integer> mRriTimeList = new ArrayList(16);
    private List<Integer> mRealRriList = new ArrayList(16);
    private List<Integer> mRealTimeList = new ArrayList(16);
    private boolean mIsHealthHeartRateOpen = false;
    private int mSportType = -1;
    private IBaseResponseCallback mStressAlgorithmCallback = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a(HwHeartRateManager.TAG, "stressAlgorithmCallback onResponse");
            if (obj != null) {
                synchronized (HwHeartRateManager.DEVICE_LIST_LOCK) {
                    if (HwHeartRateManager.sDeviceStressCallbackList.size() != 0) {
                        LogUtil.a(HwHeartRateManager.TAG, "stressAlgorithmCallback onResponse sDeviceStressCallbackList back,objdata:", obj.toString());
                        ((IBaseResponseCallback) HwHeartRateManager.sDeviceStressCallbackList.get(0)).d(100000, kkm.d(obj, "notificationStressInfo"));
                    }
                }
                return;
            }
            LogUtil.h(HwHeartRateManager.TAG, "stressAlgorithmCallback onResponse object is null");
        }
    };
    private Runnable mRunnable = new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.2
        @Override // java.lang.Runnable
        public void run() {
            HwHeartRateManager.this.mHasReceiverDataStatus = false;
        }
    };

    private boolean isClose(int i) {
        return i == 2 || i == 5 || i == 10 || i == 13 || i == 3 || i == 6 || i == 11 || i == 14;
    }

    private HwHeartRateManager() {
        LogUtil.a(TAG, "enter HwHeartRateManager!");
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mStressHandlerThread = handlerThread;
        handlerThread.start();
        this.mHeartRateStressHandler = new HeartRateStressHandler(this.mStressHandlerThread.getLooper(), this);
    }

    public static HwHeartRateManager getInstance() {
        HwHeartRateManager hwHeartRateManager;
        synchronized (LOCK_OBJECT) {
            if (sInstance == null) {
                sInstance = new HwHeartRateManager();
            }
            hwHeartRateManager = sInstance;
        }
        return hwHeartRateManager;
    }

    public void setAw70LinkReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
            DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
            if (deviceList.size() != 0 && deviceInfo != null) {
                LogUtil.a(TAG, "setRunPostureReportStatus enter");
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(23);
                deviceCommand.setCommandID(15);
                deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                int i = jSONObject.getInt("status");
                int optInt = jSONObject.optInt(BleConstants.SPORT_TYPE, -1);
                String e = cvx.e(i);
                String d = cvx.d(1);
                String e2 = cvx.e(1);
                StringBuilder sb = new StringBuilder(16);
                sb.append(e2);
                sb.append(d);
                sb.append(e);
                this.mSportType = setSportType(optInt, sb);
                deviceCommand.setDataLen(sb.length() / 2);
                deviceCommand.setDataContent(cvx.a(sb.toString()));
                jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
                synchronized (DEVICE_LIST_LOCK) {
                    if (iBaseResponseCallback != null) {
                        sHeartRateStatusCallbackList.add(iBaseResponseCallback);
                    }
                }
                return;
            }
            LogUtil.h(TAG, "no device is connected.");
            iBaseResponseCallback.d(0, kkm.d(100001, "setAw70LinkReportStatus"));
        }
    }

    private int setSportType(int i, StringBuilder sb) {
        if (i == -1) {
            return 1;
        }
        Integer num = WorkoutTypeMapManager.getCloudWorkTypeMap().get(Integer.valueOf(i));
        int intValue = num != null ? num.intValue() : 1;
        sb.append(cvx.e(2));
        sb.append(cvx.e(1));
        sb.append(cvx.e(intValue));
        return intValue;
    }

    public void getAw70SupportSportType(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "getAw70SupportSportType callback is null.");
            return;
        }
        HwDeviceMgrInterface b = jsz.b(BaseApplication.getContext());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            iBaseResponseCallback.d(100000, kkm.d(false, "getAw70SupportSportType"));
            return;
        }
        Map<String, DeviceCapability> queryDeviceCapability = b.queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), TAG);
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h(TAG, "getAw70SupportSportType, deviceCapabilityHashMaps is empty");
            iBaseResponseCallback.d(100000, kkm.d(false, "getAw70SupportSportType"));
            return;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null || !deviceCapability.isSupportWorkoutCapabilicy()) {
            iBaseResponseCallback.d(100000, kkm.d(false, "getAw70SupportSportType"));
        } else {
            getWorkoutBitmap(iBaseResponseCallback, 16);
        }
    }

    private void getWorkoutBitmap(final IBaseResponseCallback iBaseResponseCallback, final int i) {
        HwWorkoutServiceAw70Manager.getInstance().getWorkoutCapability(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
                int i3 = i;
                boolean z = (intValue & i3) == i3;
                LogUtil.a(HwHeartRateManager.TAG, "isSupportSportType : ", Boolean.valueOf(z));
                iBaseResponseCallback.d(100000, kkm.d(Boolean.valueOf(z), "getAw70SupportSportType"));
            }
        });
    }

    public void setHeartRateReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        switchHeartRateDetection(2, jSONObject.getInt("status"), iBaseResponseCallback);
    }

    public void openOrCloseReport(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            int i = jSONObject.getInt(OPERATOR_TYPE);
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(18);
            String e = cvx.e(i);
            String d = cvx.d(1);
            String e2 = cvx.e(1);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d);
            sb.append(e);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
            LogUtil.a(TAG, "Enter openOrCloseReport: ", Integer.valueOf(i), " 5.23.18:", deviceCommand.toString());
            synchronized (DEVICE_LIST_LOCK) {
                if (iBaseResponseCallback != null) {
                    sCloseOrOpenReportSwitchCallbackList.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void setStressReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        if (jSONObject == null || iBaseResponseCallback == null) {
            LogUtil.h(TAG, "setStressReportStatus null");
            return;
        }
        LogUtil.a(TAG, "setStressReportStatus parameters :", jSONObject.toString());
        this.mProductId = jSONObject.optString("productId");
        int i = jSONObject.getInt("type");
        if (isClose(i) && AM16_PRODUCT_ID.equals(this.mProductId)) {
            parseRri(jSONObject);
        }
        cleanAbortCallback();
        LogUtil.a(TAG, "setStressReportStatus type: ", Integer.valueOf(i));
        switch (i) {
            case 1:
                processStressOpenCallback(jSONObject, iBaseResponseCallback);
                break;
            case 2:
                processStressClose(jSONObject);
                break;
            case 3:
                processStressAbort(getStressAbortCallback(iBaseResponseCallback));
                break;
            case 4:
                processCalibrationOpenCallback(jSONObject, iBaseResponseCallback);
                break;
            case 5:
                processCalibrationClose(jSONObject);
                break;
            case 6:
                processCalibrationAbort(getStressCalibAbortCallback(iBaseResponseCallback));
                break;
            case 7:
                getStressCalibrationFlag(this.mStressAlgorithmCallback);
                break;
            default:
                setStressReportSomeStatus(i, jSONObject, iBaseResponseCallback);
                break;
        }
    }

    private void setStressReportSomeStatus(int i, JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        switch (i) {
            case 8:
                HwStressDataProvider.c().b(8, null, this.mStressAlgorithmCallback);
                break;
            case 9:
                processRelaxOpenCallback(jSONObject, iBaseResponseCallback);
                break;
            case 10:
                processRelaxClose(jSONObject);
                break;
            case 11:
                processRelaxAbort(getRelaxAbortCallback(iBaseResponseCallback));
                break;
            case 12:
                processGameOpenCallbck(jSONObject, iBaseResponseCallback);
                break;
            case 13:
                processGameCloseCallback(jSONObject);
                break;
            case 14:
                processGameAbortCallback(iBaseResponseCallback);
                break;
            case 15:
                processCheckConnected();
                break;
            case 16:
                processRriValue(jSONObject);
                break;
            default:
                LogUtil.h(TAG, "setStressReportStatus default");
                break;
        }
    }

    private void processStressOpenCallback(JSONObject jSONObject, final IBaseResponseCallback iBaseResponseCallback) {
        processStressOpen(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (HwHeartRateManager.this.mHeartRateStressHandler != null && HwHeartRateManager.this.mHeartRateStressHandler.hasMessages(0)) {
                    HwHeartRateManager.this.mHeartRateStressHandler.removeMessages(0);
                }
                if (!(obj instanceof Integer)) {
                    LogUtil.h(HwHeartRateManager.TAG, "setStressReportStatus STRESS_OPEN object unsuitable");
                    return;
                }
                LogUtil.a(HwHeartRateManager.TAG, "stress open onResponse,objData :", obj.toString());
                int intValue = ((Integer) obj).intValue();
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("type", 1);
                    if (intValue == 100000) {
                        jSONObject2.put("result_code", 0);
                    } else {
                        jSONObject2.put("result_code", 1);
                    }
                } catch (JSONException unused) {
                    LogUtil.b(HwHeartRateManager.TAG, "stress open fail");
                }
                LogUtil.a(HwHeartRateManager.TAG, "stress open callback data :", jSONObject2.toString());
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(100000, kkm.d(jSONObject2.toString(), "setStressReportStatus"));
                } else {
                    LogUtil.h(HwHeartRateManager.TAG, "stress open callback is null");
                }
            }
        });
    }

    private void processStressClose(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("duration", jSONObject.getInt("duration"));
        HwStressDataProvider.c().b(2, jSONObject2, this.mStressAlgorithmCallback);
        setR1OpenOrCloseStress(4, null);
    }

    private void processCalibrationOpenCallback(JSONObject jSONObject, final IBaseResponseCallback iBaseResponseCallback) {
        processCalibOpen(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (HwHeartRateManager.this.mHeartRateStressHandler != null && HwHeartRateManager.this.mHeartRateStressHandler.hasMessages(1)) {
                    HwHeartRateManager.this.mHeartRateStressHandler.removeMessages(1);
                }
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("type", 4);
                        if (intValue == 100000) {
                            jSONObject2.put("result_code", 0);
                        } else {
                            jSONObject2.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "calib open fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "calib open callback data :", jSONObject2.toString());
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(100000, kkm.d(jSONObject2.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "calib open callback is null");
                    }
                }
            }
        });
    }

    private void processCalibrationClose(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("duration", jSONObject.getInt("duration"));
        HwStressDataProvider.c().b(5, jSONObject2, this.mStressAlgorithmCallback);
        setR1OpenOrCloseStress(4, null);
    }

    private void processRelaxOpenCallback(JSONObject jSONObject, final IBaseResponseCallback iBaseResponseCallback) {
        processRelaxOpen(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (HwHeartRateManager.this.mHeartRateStressHandler != null && HwHeartRateManager.this.mHeartRateStressHandler.hasMessages(2)) {
                    HwHeartRateManager.this.mHeartRateStressHandler.removeMessages(2);
                }
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("type", 9);
                        if (intValue == 100000) {
                            jSONObject2.put("result_code", 0);
                        } else {
                            jSONObject2.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "relax open fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "relax open callback data :", jSONObject2.toString());
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(100000, kkm.d(jSONObject2.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "relax open callback is null");
                    }
                }
            }
        });
    }

    private void processRelaxClose(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("duration", jSONObject.getInt("duration"));
        HwStressDataProvider.c().b(10, jSONObject2, this.mStressAlgorithmCallback);
        setR1OpenOrCloseStress(4, null);
    }

    private void processGameOpenCallbck(JSONObject jSONObject, final IBaseResponseCallback iBaseResponseCallback) {
        processGameOpen(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (HwHeartRateManager.this.mHeartRateStressHandler != null && HwHeartRateManager.this.mHeartRateStressHandler.hasMessages(3)) {
                    HwHeartRateManager.this.mHeartRateStressHandler.removeMessages(3);
                }
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("type", 12);
                        if (intValue == 100000) {
                            jSONObject2.put("result_code", 0);
                        } else {
                            jSONObject2.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "game open fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "game open callback data :", jSONObject2.toString());
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(100000, kkm.d(jSONObject2.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "game open callback is null");
                    }
                }
            }
        });
    }

    private void processGameCloseCallback(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("duration", jSONObject.getInt("duration"));
        jSONObject2.put("score", jSONObject.getInt("score"));
        HwStressDataProvider.c().b(13, jSONObject2, this.mStressAlgorithmCallback);
        setR1OpenOrCloseStress(4, null);
    }

    private void processGameAbortCallback(final IBaseResponseCallback iBaseResponseCallback) {
        processGameAbort(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("type", 14);
                        if (intValue == 100000) {
                            jSONObject.put("result_code", 0);
                        } else {
                            jSONObject.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "game abort fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "game abort callback data :", jSONObject.toString());
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(100000, kkm.d(jSONObject.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "game abort callback is null");
                    }
                }
            }
        });
    }

    private void processCheckConnected() throws JSONException {
        DeviceInfo currentConnectedDeviceInfo = getCurrentConnectedDeviceInfo();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", 15);
        if (currentConnectedDeviceInfo != null) {
            LogUtil.a(TAG, "productType :", Integer.valueOf(currentConnectedDeviceInfo.getProductType()));
            Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, currentConnectedDeviceInfo.getDeviceIdentify(), TAG);
            DeviceCapability deviceCapability = new DeviceCapability();
            if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
                deviceCapability = queryDeviceCapability.get(currentConnectedDeviceInfo.getDeviceIdentify());
            }
            if (deviceCapability == null) {
                LogUtil.h(TAG, "deviceCapability is null!");
            }
            if (currentConnectedDeviceInfo.getProductType() == 11 && deviceCapability != null && deviceCapability.isSupportStressInfo()) {
                LogUtil.a(TAG, "check connected success!");
                jSONObject.put("result_code", 0);
            } else {
                jSONObject.put("result_code", 1);
            }
        } else {
            jSONObject.put("result_code", 1);
        }
        IBaseResponseCallback iBaseResponseCallback = this.mStressAlgorithmCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, jSONObject.toString());
        }
    }

    private void processRriValue(JSONObject jSONObject) throws JSONException {
        this.mRealRriList.clear();
        this.mRealTimeList.clear();
        String string = jSONObject.getString("listrri");
        String string2 = jSONObject.getString("listtime");
        this.mRealRriList.addAll(stringToList(string));
        this.mRealTimeList.addAll(stringToList(string2));
        int[] listToArryOfRri = listToArryOfRri(this.mRealRriList);
        int[] listToArryOfRri2 = listToArryOfRri(this.mRealTimeList);
        LogUtil.a(TAG, "processRriValue arrayRri size :", Integer.valueOf(listToArryOfRri.length), ", arrayTime size :", Integer.valueOf(listToArryOfRri2.length));
        if (listToArryOfRri.length == 0) {
            int[] iArr = {0};
            HwStressDataProvider.c().c(iArr, iArr);
        } else {
            HwStressDataProvider.c().c(listToArryOfRri, listToArryOfRri2);
        }
    }

    private IBaseResponseCallback getStressAbortCallback(final IBaseResponseCallback iBaseResponseCallback) {
        IBaseResponseCallback iBaseResponseCallback2 = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("type", 3);
                        if (intValue == 100000) {
                            jSONObject.put("result_code", 0);
                        } else {
                            jSONObject.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "stress abort fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "stress abort callback data:", jSONObject.toString());
                    IBaseResponseCallback iBaseResponseCallback3 = iBaseResponseCallback;
                    if (iBaseResponseCallback3 != null) {
                        iBaseResponseCallback3.d(100000, kkm.d(jSONObject.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "stress abort callback is null");
                    }
                }
            }
        };
        this.mStressAbortCallback = iBaseResponseCallback2;
        return iBaseResponseCallback2;
    }

    private IBaseResponseCallback getStressCalibAbortCallback(final IBaseResponseCallback iBaseResponseCallback) {
        IBaseResponseCallback iBaseResponseCallback2 = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("type", 6);
                        if (intValue == 100000) {
                            jSONObject.put("result_code", 0);
                        } else {
                            jSONObject.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "calib abort fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "calib abort callback data:", jSONObject.toString());
                    IBaseResponseCallback iBaseResponseCallback3 = iBaseResponseCallback;
                    if (iBaseResponseCallback3 != null) {
                        iBaseResponseCallback3.d(100000, kkm.d(jSONObject.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "calib abort callback is null");
                    }
                }
            }
        };
        this.mStressCalibrationAbortCallback = iBaseResponseCallback2;
        return iBaseResponseCallback2;
    }

    private IBaseResponseCallback getRelaxAbortCallback(final IBaseResponseCallback iBaseResponseCallback) {
        IBaseResponseCallback iBaseResponseCallback2 = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager.11
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("type", 11);
                        if (intValue == 100000) {
                            jSONObject.put("result_code", 0);
                        } else {
                            jSONObject.put("result_code", 1);
                        }
                    } catch (JSONException unused) {
                        LogUtil.b(HwHeartRateManager.TAG, "relax abort fail");
                    }
                    LogUtil.a(HwHeartRateManager.TAG, "relax abort callback data:", jSONObject.toString());
                    IBaseResponseCallback iBaseResponseCallback3 = iBaseResponseCallback;
                    if (iBaseResponseCallback3 != null) {
                        iBaseResponseCallback3.d(100000, kkm.d(jSONObject.toString(), "setStressReportStatus"));
                    } else {
                        LogUtil.h(HwHeartRateManager.TAG, "relax abort callback is null");
                    }
                }
            }
        };
        this.mRelaxAbortCallback = iBaseResponseCallback2;
        return iBaseResponseCallback2;
    }

    private void cleanAbortCallback() {
        synchronized (DEVICE_LIST_LOCK) {
            IBaseResponseCallback iBaseResponseCallback = this.mRelaxAbortCallback;
            if (iBaseResponseCallback != null && sStressStatusCallbackList.contains(iBaseResponseCallback)) {
                LogUtil.a(TAG, "remove(mRelaxAbortCallback)");
                sStressStatusCallbackList.remove(this.mRelaxAbortCallback);
                this.mRelaxAbortCallback = null;
            }
            IBaseResponseCallback iBaseResponseCallback2 = this.mStressCalibrationAbortCallback;
            if (iBaseResponseCallback2 != null && sStressStatusCallbackList.contains(iBaseResponseCallback2)) {
                LogUtil.a(TAG, "remove(mStressCalibrationAbortCallback)");
                sStressStatusCallbackList.remove(this.mStressCalibrationAbortCallback);
                this.mStressCalibrationAbortCallback = null;
            }
            IBaseResponseCallback iBaseResponseCallback3 = this.mStressAbortCallback;
            if (iBaseResponseCallback3 != null && sStressStatusCallbackList.contains(iBaseResponseCallback3)) {
                LogUtil.a(TAG, "remove(mStressAbortCallback)");
                sStressStatusCallbackList.remove(this.mStressAbortCallback);
                this.mStressAbortCallback = null;
            }
        }
    }

    private void parseRri(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("rri");
        this.mRealRriList.clear();
        this.mRealTimeList.clear();
        String optString = optJSONObject.optString("listrri");
        String optString2 = optJSONObject.optString("listtime");
        this.mRealRriList.addAll(stringToList(optString));
        this.mRealTimeList.addAll(stringToList(optString2));
        LogUtil.a(TAG, "arrayRri size :", Integer.valueOf(this.mRealRriList.size()), ", arraytime size :", Integer.valueOf(this.mRealTimeList.size()));
    }

    private List<Integer> stringToList(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (!TextUtils.isEmpty(str) && str.contains(",")) {
            for (String str2 : str.split(",")) {
                arrayList.add(Integer.valueOf(CommonUtil.a(str2, 10)));
            }
        }
        return arrayList;
    }

    private void processStressOpen(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "stress open");
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(MAX_DURATION, jSONObject.getInt(MAX_DURATION));
        } catch (JSONException unused) {
            LogUtil.b(TAG, "processStressOpen stress open fail");
        }
        HwStressDataProvider.c().b(1, jSONObject2, null);
        setR1OpenOrCloseStress(3, iBaseResponseCallback);
        if (this.mHeartRateStressHandler == null || AM16_PRODUCT_ID.equals(this.mProductId)) {
            return;
        }
        this.mHeartRateStressHandler.sendEmptyMessageDelayed(0, 5000L);
    }

    private void processStressAbort(IBaseResponseCallback iBaseResponseCallback) {
        HwStressDataProvider.c().b(3, null, null);
        setR1OpenOrCloseStress(4, iBaseResponseCallback);
    }

    private void processCalibOpen(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("score", jSONObject.getInt("score"));
            jSONObject2.put(MAX_DURATION, jSONObject.getInt(MAX_DURATION));
        } catch (JSONException unused) {
            LogUtil.b(TAG, "processCalibOpen calib open fail");
        }
        HwStressDataProvider.c().b(4, jSONObject2, null);
        if (this.mHeartRateStressHandler != null && !AM16_PRODUCT_ID.equals(this.mProductId)) {
            this.mHeartRateStressHandler.sendEmptyMessageDelayed(1, 5000L);
        }
        setR1OpenOrCloseStress(3, iBaseResponseCallback);
    }

    private void processCalibrationAbort(IBaseResponseCallback iBaseResponseCallback) {
        HwStressDataProvider.c().b(6, null, null);
        setR1OpenOrCloseStress(4, iBaseResponseCallback);
    }

    private void processRelaxOpen(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(MAX_DURATION, jSONObject.getInt(MAX_DURATION));
        } catch (JSONException unused) {
            LogUtil.b(TAG, "processRelaxOpen relax open fail");
        }
        HwStressDataProvider.c().b(9, jSONObject2, null);
        if (this.mHeartRateStressHandler != null && !AM16_PRODUCT_ID.equals(this.mProductId)) {
            this.mHeartRateStressHandler.sendEmptyMessageDelayed(2, 5000L);
        }
        setR1OpenOrCloseStress(3, iBaseResponseCallback);
    }

    private void processRelaxAbort(IBaseResponseCallback iBaseResponseCallback) {
        HwStressDataProvider.c().b(11, null, null);
        setR1OpenOrCloseStress(4, iBaseResponseCallback);
    }

    private void processGameOpen(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(MAX_DURATION, jSONObject.getInt(MAX_DURATION));
        } catch (JSONException unused) {
            LogUtil.b(TAG, "processGameOpen game open fail");
        }
        HwStressDataProvider.c().b(12, jSONObject2, null);
        if (this.mHeartRateStressHandler != null && !AM16_PRODUCT_ID.equals(this.mProductId)) {
            this.mHeartRateStressHandler.sendEmptyMessageDelayed(3, 5000L);
        }
        setR1OpenOrCloseStress(3, iBaseResponseCallback);
    }

    private void processGameAbort(IBaseResponseCallback iBaseResponseCallback) {
        HwStressDataProvider.c().b(14, null, null);
        setR1OpenOrCloseStress(4, iBaseResponseCallback);
    }

    private void getStressCalibrationFlag(IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        int i;
        String sharedPreference = getSharedPreference("calibration_flag");
        if (TextUtils.isEmpty(sharedPreference)) {
            i = 1;
        } else {
            LogUtil.a(TAG, "calibrationFlagStr :", sharedPreference);
            i = CommonUtil.m(BaseApplication.getContext(), sharedPreference);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", 7);
        jSONObject.put("calibration_flag", i);
        LogUtil.a(TAG, "stress calib check ret data :", jSONObject.toString());
        iBaseResponseCallback.d(0, jSONObject.toString());
    }

    private String getSharedPreference(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(25), str);
    }

    /* loaded from: classes5.dex */
    static class HeartRateStressHandler extends Handler {
        private WeakReference<HwHeartRateManager> hwHeartRateManagerWeakReference;

        HeartRateStressHandler(Looper looper, HwHeartRateManager hwHeartRateManager) {
            super(looper);
            this.hwHeartRateManagerWeakReference = new WeakReference<>(hwHeartRateManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h(HwHeartRateManager.TAG, "handleMessage message is null");
                return;
            }
            HwHeartRateManager hwHeartRateManager = this.hwHeartRateManagerWeakReference.get();
            if (hwHeartRateManager == null) {
                LogUtil.h(HwHeartRateManager.TAG, "handleMessage hwHeartRateManager is null");
                return;
            }
            super.handleMessage(message);
            LogUtil.a(HwHeartRateManager.TAG, "handleMessage message.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                handleStressOpenTimeout(hwHeartRateManager);
                return;
            }
            if (i == 1) {
                handleClibOpenTimeout(hwHeartRateManager);
                return;
            }
            if (i == 2) {
                handleRelaxOpenTimeout(hwHeartRateManager, 9);
            } else if (i == 3) {
                handleGameOpenTimeout(hwHeartRateManager, 12);
            } else {
                LogUtil.h(HwHeartRateManager.TAG, "handleMessage no case");
            }
        }

        private void handleGameOpenTimeout(HwHeartRateManager hwHeartRateManager, int i) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", i);
                jSONObject.put("result_code", 1);
            } catch (JSONException unused) {
                LogUtil.b(HwHeartRateManager.TAG, "stress open timeout");
            }
            if (hwHeartRateManager.mStressAlgorithmCallback != null) {
                hwHeartRateManager.mStressAlgorithmCallback.d(0, jSONObject.toString());
            }
        }

        private void handleRelaxOpenTimeout(HwHeartRateManager hwHeartRateManager, int i) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", i);
                jSONObject.put("result_code", 1);
            } catch (JSONException unused) {
                LogUtil.b(HwHeartRateManager.TAG, "calib open timeout");
            }
            if (hwHeartRateManager.mStressAlgorithmCallback != null) {
                hwHeartRateManager.mStressAlgorithmCallback.d(0, jSONObject.toString());
            }
        }

        private void handleClibOpenTimeout(HwHeartRateManager hwHeartRateManager) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", 4);
                jSONObject.put("result_code", 1);
            } catch (JSONException unused) {
                LogUtil.b(HwHeartRateManager.TAG, "calib open timeout");
            }
            if (hwHeartRateManager.mStressAlgorithmCallback != null) {
                hwHeartRateManager.mStressAlgorithmCallback.d(0, jSONObject.toString());
            }
        }

        private void handleStressOpenTimeout(HwHeartRateManager hwHeartRateManager) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", 1);
                jSONObject.put("result_code", 1);
            } catch (JSONException unused) {
                LogUtil.b(HwHeartRateManager.TAG, "stress open timeout");
            }
            if (hwHeartRateManager.mStressAlgorithmCallback != null) {
                hwHeartRateManager.mStressAlgorithmCallback.d(0, jSONObject.toString());
            }
        }
    }

    public void setHeartRateResponse(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c(TAG, "response of HeartRate info");
        if (jSONObject == null || iBaseResponseCallback == null) {
            LogUtil.h(TAG, "setHeartRateResponse null");
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(25);
            deviceCommand.setCommandID(3);
            try {
                String b = cvx.b(jSONObject.getInt("heart_rate_response"));
                String e = cvx.e(b.length() / 2);
                String e2 = cvx.e(127);
                StringBuilder sb = new StringBuilder(16);
                sb.append(e2);
                sb.append(e);
                sb.append(b);
                deviceCommand.setDataLen(sb.length() / 2);
                deviceCommand.setDataContent(cvx.a(sb.toString()));
                jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
                iBaseResponseCallback.d(100000, kkm.d("success", "setHeartRateResponse"));
            } catch (JSONException unused) {
                LogUtil.b(TAG, "setHeartRateResponse JSONException");
            }
        }
    }

    public void registerNotificationHeartRateCallback(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (DEVICE_LIST_LOCK) {
            if (iBaseResponseCallback != null) {
                if (!sDeviceHeartRateCallbackList.contains(iBaseResponseCallback)) {
                    sDeviceHeartRateCallbackList.add(iBaseResponseCallback);
                    return;
                }
            }
            LogUtil.h(TAG, "registerNotificationHeartRateCallback add callback failed");
        }
    }

    public void registerNotificationStressCallback(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "registerNotificationStressCallback");
        synchronized (DEVICE_LIST_LOCK) {
            if (iBaseResponseCallback != null) {
                if (!sDeviceStressCallbackList.contains(iBaseResponseCallback)) {
                    sDeviceStressCallbackList.add(iBaseResponseCallback);
                    return;
                }
            }
            LogUtil.h(TAG, "registerNotificationStressCallback add callback failed");
        }
    }

    public void registerNotificationPressCallback(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "registerNotificationPressCallback start");
        synchronized (DEVICE_LIST_LOCK) {
            if (iBaseResponseCallback != null) {
                if (!sDevicePressCallbackList.contains(iBaseResponseCallback)) {
                    LogUtil.a(TAG, "registerNotificationPressCallback add success");
                    sDevicePressCallbackList.add(iBaseResponseCallback);
                    return;
                }
            }
            LogUtil.h(TAG, "registerNotificationPressCallback add callback failed");
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || cwl.b(bArr)) {
            LogUtil.h(TAG, "getResult isTimeout");
            return;
        }
        blt.d(TAG, bArr, "getResult(): ");
        String d = cvx.d(bArr);
        if (d.length() <= 4) {
            LogUtil.b(TAG, "getResult received tlv error");
            return;
        }
        try {
            cwe a2 = new cwl().a(d.substring(4));
            List<cwd> e = a2.e();
            List<cwe> a3 = a2.a();
            byte b = bArr[1];
            if (b == 1) {
                handleRemindResult(e);
            } else if (b == 2) {
                LogUtil.a(TAG, "getResult COMMAND_ID_GET_HEART_RATE_INFO");
            } else if (b == 3) {
                handleHeartRateNotice(a3, deviceInfo);
            } else if (b == 4) {
                handleRriResult(bArr, a3);
            } else if (b == 5) {
                handleRriNotice(bArr, e, a3);
            } else if (b == 16) {
                handleRunPostureData(a3);
            } else if (b == 18) {
                handleRecordRequestReport(e);
            } else {
                LogUtil.h(TAG, "getResult default");
            }
        } catch (cwg unused) {
            LogUtil.b(TAG, "getResult TlvException");
        }
    }

    private void handleRemindResult(List<cwd> list) {
        if (list == null) {
            LogUtil.h(TAG, "handleRemindResult tlvList is null");
            return;
        }
        int i = 0;
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 127) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h(TAG, "handleRemindResult default");
            }
        }
        Object obj = DEVICE_LIST_LOCK;
        synchronized (obj) {
            if (sHeartRateStatusCallbackList.size() != 0) {
                sHeartRateStatusCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "setHeartRateReportStatus"));
                sHeartRateStatusCallbackList.remove(0);
            }
        }
        synchronized (obj) {
            if (sStressStatusCallbackList.size() != 0) {
                sStressStatusCallbackList.get(0).d(0, Integer.valueOf(i));
                sStressStatusCallbackList.remove(0);
            }
        }
    }

    private void handleRunPostureData(List<cwe> list) {
        LogUtil.a(TAG, "handleRunPostureData enter");
        Map<String, Object> parseLinkageData = HwExerciseLinkageUtil.parseLinkageData(list, this.mSportType);
        synchronized (DEVICE_LIST_LOCK) {
            if (sDeviceHeartRateCallbackList.size() != 0) {
                if (parseLinkageData == null) {
                    sDeviceHeartRateCallbackList.get(0).d(100001, "");
                } else {
                    sDeviceHeartRateCallbackList.get(0).d(100000, parseLinkageData);
                }
            }
        }
    }

    private void handleHeartRateNotice(List<cwe> list, DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        String str = "";
        if (deviceInfo != null) {
            str = deviceInfo.getSecurityUuid() + "#ANDROID21";
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                HeartRateInfo heartRateInfo = new HeartRateInfo();
                heartRateInfo.setUuid(str);
                configHeartRateNotice(e, heartRateInfo);
                sendRefreshHeartRateBroadcast(heartRateInfo);
                arrayList.add(heartRateInfo);
            }
        }
        synchronized (DEVICE_LIST_LOCK) {
            if (sDeviceHeartRateCallbackList.size() != 0) {
                sDeviceHeartRateCallbackList.get(0).d(100000, kkm.d(arrayList, "notificationHeartRateInfo"));
            }
            Iterator<Map.Entry<String, IBaseResponseCallback>> it3 = sKitHeartRateCallbackList.entrySet().iterator();
            while (it3.hasNext()) {
                IBaseResponseCallback value = it3.next().getValue();
                if (value != null) {
                    value.d(100000, kkm.d(arrayList, "notificationHeartRateInfo"));
                }
            }
        }
    }

    private void configHeartRateNotice(List<cwd> list, HeartRateInfo heartRateInfo) {
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 3) {
                heartRateInfo.setHeartRateQualityInfo(CommonUtil.w(cwdVar.c()));
            } else if (w == 4) {
                heartRateInfo.setTimeInfo(CommonUtil.y(cwdVar.c()) * 1000);
            } else if (w == 5) {
                LogUtil.c(TAG, "configHeartRateNotice has credibility");
                heartRateInfo.setHeartRateCredibility(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.c(TAG, "handleHeartRateNotice default");
            }
        }
    }

    private void handleRriResult(byte[] bArr, List<cwe> list) {
        blt.d(TAG, bArr, "original data: ");
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                HeartRateInfo heartRateInfo = new HeartRateInfo();
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    parseHeartRateTlv(it3.next(), heartRateInfo);
                }
            }
        }
    }

    private void handleRriNotice(byte[] bArr, List<cwd> list, List<cwe> list2) {
        IBaseResponseCallback value;
        synchronized (NOTIFICATION_RRI_INFO_LOCK) {
            blt.d(TAG, bArr, "5.25.5 orignal data: ");
            List<IBaseResponseCallback> list3 = sDevicePressCallbackList;
            if (list3 != null && list3.size() != 0) {
                Iterator<IBaseResponseCallback> it = sDevicePressCallbackList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    IBaseResponseCallback next = it.next();
                    if (next == null) {
                        LogUtil.h(TAG, "5.25.5 callback is null");
                        break;
                    } else if (!sIsHealthRriOpen) {
                        LogUtil.h(TAG, "5.25.5 is close");
                        break;
                    } else if (parseRriTlvCallback(list, list2, next)) {
                        break;
                    }
                }
            } else {
                LogUtil.h(TAG, "no set callback about 5.25.5");
            }
            Iterator<Map.Entry<String, IBaseResponseCallback>> it2 = sKitRriCallbackList.entrySet().iterator();
            while (it2.hasNext() && ((value = it2.next().getValue()) == null || !parseRriTlvCallback(list, list2, value))) {
            }
        }
    }

    private void handleRecordRequestReport(List<cwd> list) {
        if (list.size() == 0 || list.get(0) == null) {
            LogUtil.h(TAG, "handleRecordRequestReport tlv error");
            return;
        }
        int w = CommonUtil.w(list.get(0).c());
        synchronized (DEVICE_LIST_LOCK) {
            if (sCloseOrOpenReportSwitchCallbackList.size() != 0) {
                sCloseOrOpenReportSwitchCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "setOperator"));
                sCloseOrOpenReportSwitchCallbackList.remove(0);
            }
        }
    }

    private boolean parseRriTlvCallback(List<cwd> list, List<cwe> list2, IBaseResponseCallback iBaseResponseCallback) {
        Iterator<cwd> it;
        cwd next;
        JSONObject jSONObject = new JSONObject();
        try {
            it = list.iterator();
        } catch (JSONException unused) {
            iBaseResponseCallback.d(ERROR_PARAMS, kkm.d("param is error", "registerNotificationPressCallBack"));
        }
        do {
            if (!it.hasNext()) {
                LogUtil.a(TAG, "5.25.5 create rrl_list : init create");
                parseRriMessage(list2, jSONObject);
                iBaseResponseCallback.d(0, kkm.d(jSONObject.toString(), "registerNotificationPressCallBack"));
                return false;
            }
            next = it.next();
            LogUtil.c(TAG, "5.25.5 parse tlv.value :", next.c());
        } while (!isRriTlvErrorCode(iBaseResponseCallback, jSONObject, next));
        return true;
    }

    private void parseRriMessage(List<cwe> list, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                LogUtil.a(TAG, "5.25.5 struct : init create");
                JSONObject jSONObject2 = new JSONObject();
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    dealRriTypeMessage(jSONObject2, it3.next());
                }
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put("rri_list", jSONArray);
        LogUtil.a(TAG, "5.25.5 RRI result :", jSONObject.toString());
    }

    private void dealRriTypeMessage(JSONObject jSONObject, cwd cwdVar) throws JSONException {
        LogUtil.a(TAG, "dealRriTypeMessage tag:", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
        int parseInt = Integer.parseInt(cwdVar.e(), 16);
        if (parseInt == 4) {
            jSONObject.put("rri_value", CommonUtil.w(cwdVar.c()));
        } else if (parseInt == 5) {
            jSONObject.put("rri_sqi", CommonUtil.w(cwdVar.c()));
        } else {
            LogUtil.h(TAG, "5.25.5 parse default");
        }
    }

    private boolean isRriTlvErrorCode(IBaseResponseCallback iBaseResponseCallback, JSONObject jSONObject, cwd cwdVar) throws JSONException {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 1) {
            jSONObject.put("rri_time", CommonUtil.y(cwdVar.c()));
            return false;
        }
        if (w == 127) {
            int w2 = CommonUtil.w(cwdVar.c());
            LogUtil.a(TAG, "5.25.5 device push err code :", Integer.valueOf(w2));
            iBaseResponseCallback.d(w2, kkm.d("device test error", "registerNotificationPressCallBack"));
            return true;
        }
        if (w == 6) {
            jSONObject.put("rri_intensity", CommonUtil.w(cwdVar.c()));
            return false;
        }
        if (w == 7) {
            jSONObject.put("rri_acc_state", CommonUtil.w(cwdVar.c()));
            return false;
        }
        if (w == 8) {
            jSONObject.put("rri_motion_state", CommonUtil.w(cwdVar.c()));
            return false;
        }
        LogUtil.h(TAG, "5.25.5 parse default :", cwdVar.c());
        return false;
    }

    private void sendRefreshHeartRateBroadcast(HeartRateInfo heartRateInfo) {
        LogUtil.a(TAG, "enter sendRefreshHeartRateBroadcast");
        if (kkm.a(cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, TAG))) {
            this.mHasReceiverDataStatus = true;
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 3000L);
            LogUtil.a(TAG, "sendUpdateHeartRateBroadcast.");
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.HEART_RATE_REFRESH");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            intent.putExtra(ObserveLabels.HEART_RATE, heartRateInfo.getHeartRateQualityInfo());
            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
        }
    }

    public void remindSwitchHeartRateForKit(int i, int i2, IBaseResponseCallback iBaseResponseCallback, String str) {
        synchronized (LOCK_OBJECT) {
            int handleCallback = handleCallback(i2, str);
            LogUtil.a(TAG, "Enter openOrCloseHeartRateForKit isLoudspeakerMute :", Integer.valueOf(i), ";openOrClose :", Integer.valueOf(i2), " handleCallback openOrClose :", Integer.valueOf(handleCallback));
            dealOpenOrCloseHeartRate(i, handleCallback, iBaseResponseCallback);
        }
    }

    public void registerKitHeartRateCallback(String str, IBaseResponseCallback iBaseResponseCallback) {
        sKitHeartRateCallbackList.put(str, iBaseResponseCallback);
    }

    public void unregisterKitHeartRateCallback(String str) {
        if (sKitHeartRateCallbackList.containsKey(str)) {
            sKitHeartRateCallbackList.remove(str);
        }
    }

    public void registerKitRriCallback(String str, IBaseResponseCallback iBaseResponseCallback) {
        sKitRriCallbackList.put(str, iBaseResponseCallback);
    }

    public void unregisterKitRriCallback(String str) {
        if (sKitRriCallbackList.containsKey(str)) {
            sKitRriCallbackList.remove(str);
        }
    }

    public void switchHeartRateDetection(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (LOCK_OBJECT) {
            int handleCallback = handleCallback(i2, "com.huawei.health");
            LogUtil.a(TAG, "Enter switchHeartRateDetection isLoudspeakerMute: ", Integer.valueOf(i), " openOrClose: ", Integer.valueOf(i2), " handleCallback openOrClose: ", Integer.valueOf(handleCallback));
            dealOpenOrCloseHeartRate(i, handleCallback, iBaseResponseCallback);
        }
    }

    private void dealOpenOrCloseHeartRate(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        if (i == 1 && this.mHasReceiverDataStatus && this.mLoudspeakerMuteStatus == 2 && !jsl.a().j()) {
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(25);
        deviceCommand.setCommandID(1);
        String e = cvx.e(i2);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        if (kkm.a(cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, TAG))) {
            String e3 = cvx.e(2);
            String d2 = cvx.d(1);
            String e4 = cvx.e(i);
            sb.append(e3);
            sb.append(d2);
            sb.append(e4);
        }
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        this.mLoudspeakerMuteStatus = i;
        synchronized (DEVICE_LIST_LOCK) {
            if (iBaseResponseCallback != null) {
                sHeartRateStatusCallbackList.add(iBaseResponseCallback);
            }
        }
    }

    public void getWearDeviceConnectStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (LOCK_OBJECT) {
            DeviceInfo deviceInfo = null;
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, TAG);
            LogUtil.a(TAG, "getWearDeviceConnectStatus() deviceList.size() :", Integer.valueOf(deviceList.size()));
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (next.getDeviceActiveState() == 1) {
                    deviceInfo = next;
                    break;
                }
            }
            int deviceConnectState = deviceInfo != null ? deviceInfo.getDeviceConnectState() : 0;
            LogUtil.a(TAG, "getWearDeviceConnectStatus :", Integer.valueOf(deviceConnectState));
            iBaseResponseCallback.d(100000, kkm.d(Integer.valueOf(deviceConnectState), "getWearDeviceConnectStatus"));
        }
    }

    public void setR1OpenOrCloseStress(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "isR1OpenOrCloseStress openOrClose :", Integer.valueOf(i));
        if (i == 4) {
            synchronized (this) {
                int[] listToArryOfRri = listToArryOfRri(this.mRealRriList);
                int[] listToArryOfRri2 = listToArryOfRri(this.mRealTimeList);
                LogUtil.a(TAG, "arrayRri size :", Integer.valueOf(listToArryOfRri.length), ", arraytime size :", Integer.valueOf(listToArryOfRri2.length));
                HwStressDataProvider.c().c(listToArryOfRri, listToArryOfRri2);
            }
        }
        synchronized (this) {
            LogUtil.a(TAG, "clear mRriList list");
            this.mRriList.clear();
            this.mRriTimeList.clear();
            this.mRriTimeGetterList.clear();
            this.mRealRriList.clear();
            this.mRealTimeList.clear();
        }
        if (!AM16_PRODUCT_ID.equals(this.mProductId)) {
            switchHeartRateDetection(2, i, null);
        } else if (i != 4 || !AM16_PRODUCT_ID.equals(this.mProductId)) {
            LogUtil.h(TAG, "setR1OpenOrCloseStress enter else branch");
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, 100000);
        }
        synchronized (DEVICE_LIST_LOCK) {
            if (iBaseResponseCallback != null) {
                sStressStatusCallbackList.add(iBaseResponseCallback);
            }
        }
    }

    private int[] listToArryOfRri(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            if (list.get(i) instanceof Integer) {
                iArr[i] = ((Integer) list.get(i)).intValue();
            }
        }
        return iArr;
    }

    private DeviceInfo getCurrentConnectedDeviceInfo() {
        LogUtil.a(TAG, "getCurrentConnectedDeviceInfo() enter");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, TAG);
        if (deviceList.size() != 0) {
            LogUtil.a(TAG, "getCurrentConnectedDeviceInfo() deviceInfoList.size() :", Integer.valueOf(deviceList.size()));
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo.getDeviceConnectState() == 2 && ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType()))) {
                    return deviceInfo;
                }
            }
            LogUtil.b(TAG, "getCurrentConnectedDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
            return null;
        }
        LogUtil.b(TAG, "getCurrentConnectedDeviceInfo() deviceInfoList is null");
        return null;
    }

    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        HandlerThread handlerThread = this.mStressHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mStressHandlerThread = null;
        }
        clearInstance();
    }

    private static void clearInstance() {
        synchronized (LOCK_OBJECT) {
            sInstance = null;
        }
    }

    private void parseHeartRateTlv(cwd cwdVar, HeartRateInfo heartRateInfo) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 1) {
            heartRateInfo.setTimeInfo(CommonUtil.y(cwdVar.c()) * 1000);
            synchronized (this) {
                this.mRriTimeGetterList.add(Integer.valueOf((int) heartRateInfo.getTimeInfo()));
            }
        } else {
            if (w != 4) {
                if (w == 5) {
                    heartRateInfo.setHeartRateSqiInfo(CommonUtil.a(cwdVar.c(), 16));
                    return;
                } else {
                    LogUtil.h(TAG, "no support info.");
                    return;
                }
            }
            heartRateInfo.setHrriInfo(CommonUtil.a(cwdVar.c(), 16));
            synchronized (this) {
                if (heartRateInfo.getHrriInfo() > 0) {
                    configHeartRateInfo(heartRateInfo);
                }
            }
        }
    }

    private void configHeartRateInfo(HeartRateInfo heartRateInfo) {
        if (heartRateInfo.getHrriInfo() > 32768) {
            this.mRriList.add(Integer.valueOf(heartRateInfo.getHrriInfo() - 32768));
        } else {
            this.mRriList.add(Integer.valueOf(heartRateInfo.getHrriInfo()));
        }
        List<Integer> list = this.mRriTimeList;
        List<Integer> list2 = this.mRriTimeGetterList;
        int intValue = list2.get(list2.size() - 1).intValue();
        int intValue2 = this.mRriTimeGetterList.get(0).intValue();
        List<Integer> list3 = this.mRriList;
        list.add(Integer.valueOf((intValue - intValue2) + list3.get(list3.size() - 1).intValue()));
        if (heartRateInfo.getHrriInfo() > 32768) {
            List<Integer> list4 = this.mRealTimeList;
            List<Integer> list5 = this.mRriTimeGetterList;
            int intValue3 = list5.get(list5.size() - 1).intValue();
            int intValue4 = this.mRriTimeGetterList.get(0).intValue();
            List<Integer> list6 = this.mRriList;
            list4.add(Integer.valueOf((intValue3 - intValue4) + list6.get(list6.size() - 1).intValue()));
            if (this.mRriTimeList.size() == 1) {
                this.mRealRriList.add(Integer.valueOf(heartRateInfo.getHrriInfo() - 32768));
            }
            if (this.mRriTimeList.size() > 1) {
                List<Integer> list7 = this.mRealRriList;
                List<Integer> list8 = this.mRriTimeList;
                list7.add(Integer.valueOf(list8.get(list8.size() - 1).intValue() - this.mRriTimeList.get(r1.size() - 2).intValue()));
            }
        }
    }

    public void sendEcgBlockList(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        if (jSONObject == null) {
            LogUtil.h(TAG, "");
            iBaseResponseCallback.d(100007, "");
        }
        kcy.e(BaseApplication.getContext()).b(jSONObject, iBaseResponseCallback);
    }

    private int handleCallback(int i, String str) {
        if (i == 1) {
            startHeartRateSwitch(str);
            return i;
        }
        if (i == 2) {
            return stopHeartRateSwitch(i, str);
        }
        if (i == 3) {
            startPriSwitch(str);
            return i;
        }
        if (i == 4) {
            return stopPriSwitch(i, str);
        }
        LogUtil.h(TAG, "handleCallback default");
        return i;
    }

    private void startHeartRateSwitch(String str) {
        if ("com.huawei.health".equals(str)) {
            this.mIsHealthHeartRateOpen = true;
        } else {
            if (sKitHeartRateStatusCallerList.contains(str)) {
                return;
            }
            sKitHeartRateStatusCallerList.add(str);
        }
    }

    private int stopHeartRateSwitch(int i, String str) {
        if ("com.huawei.health".equals(str)) {
            this.mIsHealthHeartRateOpen = false;
        } else if (sKitHeartRateStatusCallerList.contains(str)) {
            sKitHeartRateStatusCallerList.remove(str);
        }
        if (this.mIsHealthHeartRateOpen || !sKitHeartRateStatusCallerList.isEmpty()) {
            return 1;
        }
        return i;
    }

    private static void setIsHealthRriOpenValue(boolean z) {
        sIsHealthRriOpen = z;
    }

    private void startPriSwitch(String str) {
        if ("com.huawei.health".equals(str)) {
            setIsHealthRriOpenValue(true);
        } else {
            if (sKitRriStatusCallerList.contains(str)) {
                return;
            }
            sKitRriStatusCallerList.add(str);
        }
    }

    private int stopPriSwitch(int i, String str) {
        if ("com.huawei.health".equals(str)) {
            setIsHealthRriOpenValue(false);
        } else if (sKitRriStatusCallerList.contains(str)) {
            sKitRriStatusCallerList.remove(str);
        }
        if (sIsHealthRriOpen || !sKitRriStatusCallerList.isEmpty()) {
            return 3;
        }
        return i;
    }
}
