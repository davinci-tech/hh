package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.inter.DeviceToSource;
import com.huawei.health.sportservice.manager.SportDeviceManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.cap;
import defpackage.cei;
import defpackage.cun;
import defpackage.cvt;
import defpackage.ffs;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.fhw;
import defpackage.gve;
import defpackage.gwl;
import defpackage.hab;
import defpackage.hae;
import defpackage.ixx;
import defpackage.jpu;
import defpackage.kon;
import defpackage.koq;
import defpackage.lau;
import defpackage.lbc;
import defpackage.mwu;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SportComponentType(classify = 1, name = ComponentName.SPORT_DEVICE_MANAGER)
/* loaded from: classes4.dex */
public class SportDeviceManager implements ManagerComponent {
    private static final int DEFAULT_HEART_RATE_TYPE_LIST_SIZE = 2;
    private static final long DELIVER_FAILED_LOG_INTERVAL = 10000;
    private static final String HEART_RATE_CHANGE = "heartRate";
    private static final int HEART_RATE_INVALID = 255;
    private static final int MSG_CHECK_HEART_RATE_DISCONNECTED = 101;
    private static final String RUNNING_POSTURE_CHANGE = "runningPosture";
    private static final long RUNNING_POSTURE_MAX_DISPLAY_TIME = 15000;
    private static final String TAG = "SportService_SportDeviceManager";
    private IBaseResponseCallback mAw70DataCallback;
    private Context mContext;
    private ISportDataCallback mDeviceLinkCallback;
    private Pair<Integer, Object> mDeviceStatusCallbackPair;
    private ScheduledExecutorService mExecutor;
    private ExtendHandler mHandler;
    private boolean mHasSetConnectState;
    private HeartRateGetterUtil.HeartRateConnectStateCallBack mHeartRateDeviceConnectStateCallBack;
    private volatile ffs mRunningPostureTemp;
    private int mSportType;
    private fgm mSportCallbackOption = new fgm();
    private List<Integer> mHeartRateSuccessList = null;
    private boolean mHasWear = false;
    private boolean mHasAw70 = false;
    private boolean mIsConnectBolt = false;
    private boolean mIsConnectNemo = false;
    private int mHeartRateZone = 0;
    private int mLinkType = 2;
    private boolean mWillTickBiWhenUseAw70 = true;
    private List<Integer> mRunningPostureSuccessList = null;
    private final Map<String, DeviceToSource> mCallbackMap = new HashMap();
    private final Map<String, DeviceToSource> mSensorCallbackMap = new HashMap();
    private boolean mIsStarted = false;
    private final HashSet<String> mDataChangeSet = new HashSet<>();
    private long mLastFailedDeliverTime = SystemClock.elapsedRealtime();
    private final List<String> mSubscribeList = new ArrayList();
    private Map<String, IBaseResponseCallback> mDeviceStatusCallbackMap = Collections.synchronizedMap(new HashMap());
    private long mRunningPostureUpdateTime = 0;
    private long mReportTime = 0;
    private HiSubscribeListener mSubscribeRunningPostureListener = new HiSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager.1
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (list == null || list.isEmpty()) {
                SportDeviceManager.this.mHasAw70 = false;
            } else {
                LogUtil.a(SportDeviceManager.TAG, "regRunningPostureListener success");
                SportDeviceManager.this.mRunningPostureSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            SportDeviceManager.this.onChangeContent(hiHealthData);
        }
    };
    private HeartRateGetterUtil.HeartRateConnectStateCallBack mConnectCallBack = new HeartRateGetterUtil.HeartRateConnectStateCallBack() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager.2
        @Override // com.huawei.health.suggestion.util.HeartRateGetterUtil.HeartRateConnectStateCallBack
        public void setConnectStateChange(boolean z) {
            LogUtil.a(SportDeviceManager.TAG, "registerHeartRateConnectCallback: ", Boolean.valueOf(z));
            if (!z || SportDeviceManager.this.mHasSetConnectState || SportDeviceManager.this.mHasWear) {
                return;
            }
            SportDeviceManager.this.mHasSetConnectState = true;
            LogUtil.a(SportDeviceManager.TAG, "mHasSetConnectState: ", Boolean.valueOf(SportDeviceManager.this.mHasSetConnectState));
            if (SportDeviceManager.this.mHeartRateDeviceConnectStateCallBack != null) {
                LogUtil.a(SportDeviceManager.TAG, "mConnectCallBack");
                SportDeviceManager.this.mHeartRateDeviceConnectStateCallBack.setConnectStateChange(true);
            }
        }
    };
    private final HiSubscribeListener mSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager.3
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a(SportDeviceManager.TAG, "registerHeartRateListener onResult");
            if (list == null || list.isEmpty()) {
                return;
            }
            LogUtil.a(SportDeviceManager.TAG, "registerHeartRateListener success");
            SportDeviceManager.this.mHeartRateSuccessList = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData != null && !SportDeviceManager.this.mCallbackMap.isEmpty()) {
                if (i == 17) {
                    SportDeviceManager.this.handleReportData(hiHealthData);
                } else if (i == 13) {
                    SportDeviceManager.this.handleHeartRate(hiHealthData);
                }
                if (SportDeviceManager.this.mDataChangeSet.contains("heartRate")) {
                    return;
                }
                LogUtil.a(SportDeviceManager.TAG, "Heart change firstly");
                BaseSportManager.getInstance().setParas(SportParamsType.DEVICES_DATA_CHANGE_FIRST, hiHealthData);
                SportDeviceManager.this.mDataChangeSet.add("heartRate");
                return;
            }
            LogUtil.b(SportDeviceManager.TAG, "mSubscribeListener HiHealthData is null!");
        }
    };

    static class DataProcessHandler extends BaseHandlerCallback<SportDeviceManager> {
        private DataProcessHandler(SportDeviceManager sportDeviceManager) {
            super(sportDeviceManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        public boolean handleMessageWhenReferenceNotNull(SportDeviceManager sportDeviceManager, Message message) {
            if (message == null) {
                LogUtil.h(SportDeviceManager.TAG, "handleMessage message is null");
                return false;
            }
            if (message.what != 101) {
                return false;
            }
            if (System.currentTimeMillis() - sportDeviceManager.mReportTime <= 10000) {
                return true;
            }
            LogUtil.a(SportDeviceManager.TAG, "handleHeartRate reportTime overtime , change heartrate to invalid");
            BaseSportManager.getInstance().updateSourceData(SportDeviceManager.TAG, "HEART_RATE_DATA", 255);
            return true;
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.DEVICE_CALLBACK_AW70.equals(sportParamsType) || (obj instanceof IBaseResponseCallback)) {
            registerAw70Callback((IBaseResponseCallback) obj);
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.DEVICE_CALLBACK_AW70.equals(sportParamsType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onChangeContent(HiHealthData hiHealthData) {
        LogUtil.a(TAG, "onChange runningPosture");
        if (hiHealthData == null) {
            LogUtil.b(TAG, "onChange runningPosture, HiHealthData is null!");
            return;
        }
        if (this.mRunningPostureTemp == null) {
            this.mRunningPostureTemp = new ffs();
        }
        if (DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEPS.value() == hiHealthData.getSubType()) {
            if (!this.mSensorCallbackMap.containsKey("STEP_DATA") || hiHealthData.getValue() == -1.0d) {
                return;
            }
            LogUtil.a(TAG, "data.getStep() = ", Double.valueOf(hiHealthData.getValue()));
            this.mSensorCallbackMap.get("STEP_DATA").onDeviceDataChanged("STEP_DATA", Integer.valueOf((int) hiHealthData.getValue()));
            return;
        }
        this.mRunningPostureTemp.c(hiHealthData.getSubType(), hiHealthData.getValue());
    }

    private void registerRunningPostureDataListener() {
        if (!this.mHasAw70 && ((this.mSportType != 264 || !BaseSportManager.getInstance().isConnectBolt()) && !gwl.c())) {
            LogUtil.a(TAG, "user dont have aw70 and bolt ");
            return;
        }
        LogUtil.a(TAG, "regAw70Listener");
        HiHealthApi d = HiHealthManager.d(this.mContext);
        if (d != null) {
            LogUtil.a(TAG, "regAw70Listener, hiHealthApi is not null");
            d.subscribeHiHealthData(Arrays.asList(15, Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA.value())), this.mSubscribeRunningPostureListener);
            if (this.mExecutor == null) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.mExecutor = newSingleThreadScheduledExecutor;
                newSingleThreadScheduledExecutor.scheduleAtFixedRate(new TimeRunner(), 5000L, 5000L, TimeUnit.MILLISECONDS);
                return;
            }
            LogUtil.a(TAG, "timer init timer has initialized");
            return;
        }
        this.mHasAw70 = false;
    }

    class TimeRunner implements Runnable {
        private TimeRunner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SportDeviceManager.this.mRunningPostureTemp != null && BaseSportManager.getInstance().getStatus() == 1) {
                if (SportDeviceManager.this.mWillTickBiWhenUseAw70) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager$TimeRunner$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            SportDeviceManager.TimeRunner.this.m472x6e1e4ab3();
                        }
                    });
                    SportDeviceManager.this.mWillTickBiWhenUseAw70 = false;
                }
                LogUtil.a(SportDeviceManager.TAG, "onChange runningPosture, get a runningPosture:", SportDeviceManager.this.mRunningPostureTemp);
                if (SportDeviceManager.this.mRunningPostureTemp.s()) {
                    return;
                }
                SportDeviceManager.this.mAw70DataCallback.d(0, SportDeviceManager.this.mRunningPostureTemp);
                SportDeviceManager.this.mRunningPostureUpdateTime = System.currentTimeMillis();
                SportDeviceManager.this.mRunningPostureTemp = new ffs();
                if (SportDeviceManager.this.mDataChangeSet.contains(SportDeviceManager.RUNNING_POSTURE_CHANGE)) {
                    return;
                }
                LogUtil.a(SportDeviceManager.TAG, "Running posture change firstly");
                BaseSportManager.getInstance().setParas(SportParamsType.DEVICES_DATA_CHANGE_FIRST, SportDeviceManager.this.mRunningPostureTemp);
                SportDeviceManager.this.mDataChangeSet.add(SportDeviceManager.RUNNING_POSTURE_CHANGE);
                return;
            }
            LogUtil.a(SportDeviceManager.TAG, "TimeRunner getStatus =", Integer.valueOf(BaseSportManager.getInstance().getStatus()));
        }

        /* renamed from: lambda$run$0$com-huawei-health-sportservice-manager-SportDeviceManager$TimeRunner, reason: not valid java name */
        /* synthetic */ void m472x6e1e4ab3() {
            SportDeviceManager.this.tickBiWhenUseAw70(BaseApplication.getContext());
        }
    }

    private void resetTimer() {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mExecutor = null;
        }
    }

    public void registerAw70Callback(IBaseResponseCallback iBaseResponseCallback) {
        this.mAw70DataCallback = iBaseResponseCallback;
    }

    public void setConnectStateCallback(HeartRateGetterUtil.HeartRateConnectStateCallBack heartRateConnectStateCallBack) {
        this.mHeartRateDeviceConnectStateCallBack = heartRateConnectStateCallBack;
    }

    public SportDeviceManager() {
        this.mContext = null;
        if (this.mContext == null) {
            this.mContext = BaseApplication.getContext();
        }
    }

    public void setDataCallback(List<String> list, DeviceToSource deviceToSource) {
        if (deviceToSource == null || list == null) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.mCallbackMap.put(it.next(), deviceToSource);
        }
    }

    public void setSensorDataCallback(List<String> list, DeviceToSource deviceToSource) {
        if (deviceToSource == null || list == null) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.mSensorCallbackMap.put(it.next(), deviceToSource);
        }
    }

    public void setCallback(final IBaseResponseCallback iBaseResponseCallback) {
        mwu.d().a(TAG, new IBaseResponseCallback() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IBaseResponseCallback.this.d(0, null);
            }
        });
    }

    public void unregBoltAbnormalCallback() {
        mwu.d().b(TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReportData(HiHealthData hiHealthData) {
        String metaData = hiHealthData.getMetaData();
        LogUtil.a(TAG, "handleReportData, ", metaData);
        kon konVar = (kon) new Gson().fromJson(CommonUtil.z(metaData), kon.class);
        if (konVar == null) {
            return;
        }
        Iterator<String> it = this.mCallbackMap.keySet().iterator();
        while (it.hasNext()) {
            distributeDeviceData(it.next(), konVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHeartRate(HiHealthData hiHealthData) {
        int intValue = hiHealthData.getIntValue();
        this.mReportTime = hiHealthData.getStartTime();
        LogUtil.a(TAG, "handleHeartRate() heartRate: ", Integer.valueOf(intValue), " reportTime ", Long.valueOf(this.mReportTime));
        if (fhs.c(intValue)) {
            if (BaseSportManager.getInstance().getStatus() == 1 && BaseSportManager.getInstance().getSportType() == 283) {
                LogUtil.a(TAG, "Skipping has started. Heart rate now. heartRate is : ", Integer.valueOf(intValue));
                cei.b().setFitnessMachineControl(5, 0, new int[]{intValue});
            }
            ExtendHandler extendHandler = this.mHandler;
            if (extendHandler != null && !extendHandler.hasMessages(101)) {
                Message obtain = Message.obtain();
                obtain.what = 101;
                this.mHandler.sendMessage(obtain, 10000L);
            }
        }
        BaseSportManager.getInstance().updateSourceData(TAG, "HEART_RATE_DATA", Integer.valueOf(intValue));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void distributeDeviceData(String str, kon konVar) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1677452732:
                if (str.equals("ANAEROBIC_TRAINING_EFFECT_DATA")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1517271714:
                if (str.equals("VO2MAX_DATA")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -640358505:
                if (str.equals("AEROBIC_TRAINING_EFFECT_DATA")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -518672337:
                if (str.equals("DESCENT_DATA")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -383723197:
                if (str.equals("CALORIES_DATA")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -134282947:
                if (str.equals("STEP_DATA")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 141998887:
                if (str.equals("ALTITUDE_DATA")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 770032050:
                if (str.equals("RECOVERY_TIME_DATA")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 894316669:
                if (str.equals("PERFORMANCE_CONDITION_DATA")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1019317320:
                if (str.equals("CREEP_DATA")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1271947409:
                if (str.equals("REPORT_TIME_DATA")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1287493261:
                if (str.equals("ETE_ALGO_DATA")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1950175734:
                if (str.equals("STEP_RATE_DATA")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.c()));
                break;
            case 1:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Long.valueOf(konVar.h()));
                break;
            case 2:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.e()));
                break;
            case 3:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.n()));
                break;
            case 4:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.i()));
                break;
            case 5:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.k()));
                break;
            case 6:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.m()));
                break;
            case 7:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Long.valueOf(konVar.f()));
                break;
            case '\b':
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.g()));
                break;
            case '\t':
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.o()));
                break;
            case '\n':
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Long.valueOf(konVar.l()));
                break;
            case 11:
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.a()));
                break;
            case '\f':
                this.mCallbackMap.get(str).onDeviceDataChanged(str, Integer.valueOf(konVar.b()));
                break;
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport Enter");
        this.mHasWear = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG) != null;
        this.mIsConnectBolt = BaseSportManager.getInstance().isConnectBolt();
        this.mIsConnectNemo = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).isDeviceConnected("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c");
        ReleaseLogUtil.e(TAG, "mHasWear ", Boolean.valueOf(this.mHasWear), " mIsConnectBolt ", Boolean.valueOf(this.mIsConnectBolt), " mIsConnectNemo ", Boolean.valueOf(this.mIsConnectNemo));
        this.mHasAw70 = jpu.e(TAG) != null || this.mIsConnectBolt;
        int sportType = BaseSportManager.getInstance().getSportType();
        this.mSportType = sportType;
        this.mLinkType = fhs.a(sportType);
        constructSubscribeList();
        this.mSportCallbackOption.a(this.mSubscribeList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager$$ExternalSyntheticLambda1
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SportDeviceManager.this.m470xe5537ace(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$1$com-huawei-health-sportservice-manager-SportDeviceManager, reason: not valid java name */
    /* synthetic */ void m470xe5537ace(List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            LogUtil.a(TAG, "sportDataNotify ", map.get("TIME_ONE_SECOND_DURATION"));
            saveSportDataFromDfh(map);
            if (this.mDeviceLinkCallback == null || map == null || !(this.mHasWear || this.mHasAw70)) {
                if (SystemClock.elapsedRealtime() - this.mLastFailedDeliverTime > 10000) {
                    ReleaseLogUtil.d(TAG, "reportDataAndStatesToCallback failed: mDeviceLinkCallback:", this.mDeviceLinkCallback, " mHasWear:", Boolean.valueOf(this.mHasWear), " mHasAw70", Boolean.valueOf(this.mHasAw70));
                    this.mLastFailedDeliverTime = SystemClock.elapsedRealtime();
                    return;
                }
                return;
            }
            if (list.contains("TIME_ONE_SECOND_DURATION")) {
                LogUtil.a(TAG, "deliverSportData2Device");
                deliverSportData2Device(map);
            }
        }
    }

    private void constructSubscribeList() {
        this.mSubscribeList.add("TIME_ONE_SECOND_DURATION");
        this.mSubscribeList.add("DISTANCE_DATA");
        this.mSubscribeList.add("CALORIES_DATA");
        this.mSubscribeList.add("ACTIVE_CALORIES_DATA");
        this.mSubscribeList.add("SPEED_DATA");
        this.mSubscribeList.add("HEART_RATE_DATA");
        this.mSubscribeList.add("STEP_RATE_DATA");
        this.mSubscribeList.add("STEP_DATA");
        this.mSubscribeList.add("AEROBIC_TRAINING_EFFECT_DATA");
        this.mSubscribeList.add("ANAEROBIC_TRAINING_EFFECT_DATA");
        this.mSubscribeList.add("PERFORMANCE_CONDITION_DATA");
        this.mSubscribeList.add("CREEP_DATA");
        this.mSubscribeList.add("DESCENT_DATA");
        this.mSubscribeList.add("POWER_DATA");
        this.mSubscribeList.add("RESISTANCE_LEVEL_DATA");
        this.mSubscribeList.add("SUPPORT_DATA_RANGE_DATA");
        this.mSubscribeList.add("CADENCE_DATA");
        this.mSubscribeList.add("TEMPO_DATA");
        this.mSubscribeList.add("PADDLE_DATA");
        this.mSubscribeList.add("RUNNING_POSTURE_DATA");
    }

    private void deliverSportData2Device(Map<String, Object> map) {
        if (this.mDeviceLinkCallback == null || map == null) {
            return;
        }
        Bundle sportDataBundle = getSportDataBundle(map);
        this.mDeviceLinkCallback.getSportInfo(sportDataBundle);
        LogUtil.h(TAG, "linkageBundle: ", sportDataBundle.toString());
    }

    private Bundle getSportDataBundle(Map<String, Object> map) {
        long j;
        Object obj = map.get("TIME_ONE_SECOND_DURATION");
        if (obj instanceof Long) {
            j = ((Long) obj).longValue();
        } else {
            LogUtil.a(TAG, "getSportDataBundle() ", "TIME_ONE_SECOND_DURATION", "== 0");
            j = 0;
        }
        Bundle bundle = new Bundle();
        getLinkageBundle(map, j, bundle);
        if (this.mLinkType == 1) {
            bundle.putInt("forbid_pause", 1);
            bundle.putInt("linkType", 1);
            bundle.putInt("heartRate", getIntValue(map, "HEART_RATE_DATA").intValue());
            bundle.putInt("heartZone", this.mHeartRateZone);
            bundle.putInt("stepRate", Math.max(getIntValue(map, "STEP_RATE_DATA").intValue(), 0));
            bundle.putInt(MedalConstants.EVENT_STEPS, getIntValue(map, "STEP_DATA").intValue());
            bundle.putInt("aerobicExercise", getIntValue(map, "AEROBIC_TRAINING_EFFECT_DATA").intValue());
            bundle.putInt("anaerobicExercise", getIntValue(map, "ANAEROBIC_TRAINING_EFFECT_DATA").intValue());
            bundle.putInt("performanceIndicator", getIntValue(map, "PERFORMANCE_CONDITION_DATA").intValue());
            bundle.putInt("totalCreep", getIntValue(map, "CREEP_DATA").intValue());
            bundle.putInt(BleConstants.TOTAL_DESCENT, getIntValue(map, "DESCENT_DATA").intValue());
            bundle.putLong("sportStartTime", System.currentTimeMillis() - j);
            constructBundleForSouth(bundle, map);
            getLinkageBundle(map, bundle);
        }
        return bundle;
    }

    private void getLinkageBundle(Map<String, Object> map, Bundle bundle) {
        Object obj = map.get("RUNNING_POSTURE_DATA");
        if (obj instanceof ffs) {
            if (this.mRunningPostureUpdateTime == 0 || System.currentTimeMillis() - this.mRunningPostureUpdateTime <= RUNNING_POSTURE_MAX_DISPLAY_TIME) {
                gwl.aVa_(bundle, (ffs) obj);
            }
        }
    }

    private void getLinkageBundle(Map<String, Object> map, long j, Bundle bundle) {
        bundle.putInt(GeocodeSearch.GPS, 0);
        int intValue = getIntValue(map, "DISTANCE_DATA").intValue();
        bundle.putInt("distance", intValue);
        bundle.putInt("duration", (int) (j / 1000));
        if (this.mIsConnectNemo) {
            bundle.putInt("heartRate", getIntValue(map, "HEART_RATE_DATA").intValue());
        } else {
            bundle.putInt("heartRate", 0);
        }
        bundle.putLong("heartRateTime", 0L);
        bundle.putInt("calorie", getIntValue(map, "CALORIES_DATA").intValue() * 1000);
        int intValue2 = getIntValue(map, "ACTIVE_CALORIES_DATA").intValue();
        if (intValue2 > 0) {
            bundle.putInt(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, intValue2);
        }
        int intValue3 = getIntValue(map, "SPEED_DATA").intValue();
        float f = intValue3 * 0.01f;
        bundle.putFloat("speed", 0.27778f * f);
        bundle.putInt("speed_new", intValue3);
        if (intValue3 > 0 && intValue > 10) {
            int i = (int) (3600.0f / f);
            if (i > 6000) {
                i = 0;
            }
            bundle.putInt("pace", i);
        }
        int status = BaseSportManager.getInstance().getStatus();
        if (BaseSportManager.getInstance().isRestart() && status == 7) {
            status = 1;
        }
        bundle.putInt("sportState", status);
        bundle.putInt(BleConstants.SPORT_TYPE, this.mSportType);
        bundle.putInt("trackType", 0);
        bundle.putInt("origintarget", -1);
        bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1);
        bundle.putInt("distanceTarget", 0);
        bundle.putFloat(WorkoutRecord.Extend.COURSE_TARGET_VALUE, 0.0f);
        bundle.putDouble("altitude", 0.0d);
    }

    private void constructBundleForSouth(Bundle bundle, Map<String, Object> map) {
        if (lbc.a(this.mSportType)) {
            Object obj = map.get("SUPPORT_DATA_RANGE_DATA");
            if (obj instanceof SupportDataRange) {
                SupportDataRange supportDataRange = (SupportDataRange) obj;
                bundle.putInt("power", getIntValue(map, "POWER_DATA").intValue());
                bundle.putInt("resistanceLevel", getIntValue(map, "RESISTANCE_LEVEL_DATA").intValue());
                bundle.putInt("resistanceMaxLevel", supportDataRange.getMaxLevel());
                bundle.putInt("resistanceMinLevel", supportDataRange.getMinLevel());
            }
        }
        int i = this.mSportType;
        if (i == 273) {
            bundle.putInt("cadence", getIntValue(map, "CADENCE_DATA").intValue());
            return;
        }
        if (i == 274) {
            Object obj2 = map.get("TEMPO_DATA");
            float floatValue = obj2 instanceof Float ? ((Float) obj2).floatValue() : 0.0f;
            LogUtil.a(TAG, "BUNDLE_KEY_TOTAL_PADDLE_FREQUENCY", Float.valueOf(floatValue));
            bundle.putFloat("paddleFrequency", floatValue);
            bundle.putInt("totalPaddle", getIntValue(map, "PADDLE_DATA").intValue());
            return;
        }
        LogUtil.a(TAG, "other sportType", Integer.valueOf(i));
    }

    private Integer getIntValue(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        LogUtil.a(TAG, "getIntValue() ", str, "== 0");
        return 0;
    }

    private boolean regHeartRateListener(Context context) {
        LogUtil.a(TAG, "regHeartRateListener");
        HiHealthApi d = HiHealthManager.d(context);
        if (d == null) {
            return false;
        }
        LogUtil.a(TAG, "regHeartRateListener, hiHealthApi is not null");
        ArrayList arrayList = new ArrayList(2);
        if (this.mLinkType == 1) {
            LogUtil.a(TAG, "regHeartRateListener mLinkType == SportServiceUtils.RICH_TYPE");
            arrayList.add(17);
        }
        arrayList.add(13);
        d.subscribeHiHealthData(arrayList, this.mSubscribeListener);
        return true;
    }

    private void startHeartRateAndRunPostureMeasure() {
        LogUtil.a(TAG, "result regHeartRateListener ", Boolean.valueOf(regHeartRateListener(BaseApplication.getContext())));
        startHeartDeviceMeasure(BaseApplication.getContext());
    }

    public void setDeviceDataCallback(final ISportDataCallback iSportDataCallback) {
        if (iSportDataCallback == null) {
            ReleaseLogUtil.d(TAG, "setDeviceDataCallback failed with callback is null");
        } else if (cap.a()) {
            cap.b(new IBaseResponseCallback() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager$$ExternalSyntheticLambda2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SportDeviceManager.this.m471x4ae25ec(iSportDataCallback, i, obj);
                }
            });
        } else {
            setDeviceLinkCallback(iSportDataCallback);
        }
    }

    /* renamed from: lambda$setDeviceDataCallback$2$com-huawei-health-sportservice-manager-SportDeviceManager, reason: not valid java name */
    /* synthetic */ void m471x4ae25ec(ISportDataCallback iSportDataCallback, int i, Object obj) {
        this.mDeviceStatusCallbackPair = Pair.create(Integer.valueOf(i), obj);
        onDeviceStatusCallbackResponse(i, obj);
        if (((obj instanceof Integer) && ((Integer) obj).intValue() == 0) || BaseSportManager.getInstance().isRestart()) {
            setDeviceLinkCallback(iSportDataCallback);
        } else {
            ReleaseLogUtil.d(TAG, "setDeviceDataCallback failed with getDeviceSportStatus error. ErrorCode:", Integer.valueOf(i), " data:", obj);
        }
    }

    private void onDeviceStatusCallbackResponse(int i, Object obj) {
        LogUtil.a(TAG, "onDeviceStatusCallbackResponse() errorCode: ", Integer.valueOf(i), ", objData: ", obj);
        Map<String, IBaseResponseCallback> map = this.mDeviceStatusCallbackMap;
        if (map != null && !map.isEmpty()) {
            Iterator<IBaseResponseCallback> it = this.mDeviceStatusCallbackMap.values().iterator();
            while (it.hasNext()) {
                it.next().d(i, obj);
            }
            this.mDeviceStatusCallbackMap.clear();
            return;
        }
        LogUtil.h(TAG, "onDeviceStatusCallbackResponse() mDeviceStatusCallbackMap is null or empty");
    }

    private void setDeviceLinkCallback(ISportDataCallback iSportDataCallback) {
        if (iSportDataCallback == null) {
            LogUtil.a(TAG, "deviceLinkCallback is null");
        }
        if (!this.mIsStarted) {
            this.mDeviceLinkCallback = iSportDataCallback;
        } else {
            ReleaseLogUtil.d(TAG, "setDeviceLinkCallback failed with sport is started");
        }
    }

    public void addDeviceStatusCallback(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (this.mDeviceStatusCallbackMap != null) {
            LogUtil.a(TAG, "addDeviceStatusCallback() tag: ", str, ", callback: ", iBaseResponseCallback);
            this.mDeviceStatusCallbackMap.put(str, iBaseResponseCallback);
            Pair<Integer, Object> pair = this.mDeviceStatusCallbackPair;
            if (pair != null) {
                onDeviceStatusCallbackResponse(((Integer) pair.first).intValue(), this.mDeviceStatusCallbackPair.second);
            }
        }
    }

    public void removeDeviceStatusCallback(String str) {
        if (this.mDeviceStatusCallbackMap != null) {
            LogUtil.a(TAG, "removeDeviceStatusCallback() tag: ", str);
            this.mDeviceStatusCallbackMap.remove(str);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        gve.d();
        this.mLinkType = fhs.a(this.mSportType);
        this.mHasWear = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG) != null;
        this.mIsConnectBolt = BaseSportManager.getInstance().isConnectBolt();
        this.mHasAw70 = jpu.e(TAG) != null || this.mIsConnectBolt;
        this.mHandler = HandlerCenter.yt_(new DataProcessHandler(), "SPORT_DEVICE_THREAD");
        HeartRateGetterUtil.a().c(this.mConnectCallBack);
        startHeartRateAndRunPostureMeasure();
        registerRunningPostureDataListener();
        if (fhw.b.contains(Integer.valueOf(this.mSportType))) {
            LogUtil.a(TAG, "start coach sport");
            CoachController.d().c();
            CoachController.d().e(true);
            CoachController.d().c(this.mSportType);
            CoachController.d().a(CoachController.StatusSource.APP, 1);
        }
        if (this.mDeviceLinkCallback != null) {
            this.mIsStarted = true;
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : this.mSubscribeList) {
            linkedHashMap.put(str, BaseSportManager.getInstance().getData(str));
        }
        deliverSportData2Device(linkedHashMap);
        if (fhw.b.contains(Integer.valueOf(this.mSportType))) {
            CoachController.d().a(CoachController.StatusSource.APP, 1);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : this.mSubscribeList) {
            linkedHashMap.put(str, BaseSportManager.getInstance().getData(str));
        }
        deliverSportData2Device(linkedHashMap);
        if (fhw.b.contains(Integer.valueOf(this.mSportType))) {
            CoachController.d().a(CoachController.StatusSource.APP, 2);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport Enter");
        cei.b().removeMessageOrStateCallback(HuaweiHealth.a().getClass().getName(), true);
        HeartRateGetterUtil.a().d(this.mConnectCallBack);
        unRegHeartRateListener();
        unregisterAw70Listener();
        stopHeartDeviceMeasure();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : this.mSubscribeList) {
            linkedHashMap.put(str, BaseSportManager.getInstance().getData(str));
        }
        deliverSportData2Device(linkedHashMap);
        if (fhw.b.contains(Integer.valueOf(this.mSportType))) {
            LogUtil.a(TAG, "stop coach sport");
            CoachController.d().a(CoachController.StatusSource.APP, 3);
            CoachController.d().b(CoachController.StatusSource.NEW_LINK_WEAR);
        }
        this.mIsStarted = false;
        stopHandlerThread();
    }

    private void stopHandlerThread() {
        ExtendHandler extendHandler = this.mHandler;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.mHandler.quit(false);
            this.mHandler = null;
        }
    }

    private void unRegHeartRateListener() {
        if (koq.c(this.mHeartRateSuccessList)) {
            LogUtil.a(TAG, "unRegHeartRateListener");
            HiHealthManager.d(BaseApplication.getContext()).unSubscribeHiHealthData(this.mHeartRateSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager$$ExternalSyntheticLambda3
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a(SportDeviceManager.TAG, "unregHeartRateListener isSuccess = ", Boolean.valueOf(z));
                }
            });
        }
    }

    public void unregisterAw70Listener() {
        List<Integer> list = this.mRunningPostureSuccessList;
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a(TAG, "unregisterAw70Listener");
        HiHealthManager.d(this.mContext).unSubscribeHiHealthData(this.mRunningPostureSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportDeviceManager.4
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a(SportDeviceManager.TAG, "unregisterAw70Listener isSuccess = ", Boolean.valueOf(z));
            }
        });
    }

    public void startHeartDeviceMeasure(Context context) {
        LogUtil.a(TAG, "startHeartDeviceMeasure");
        HeartRateGetterUtil.a().d();
    }

    private void stopHeartDeviceMeasure() {
        LogUtil.a(TAG, "stopHeartDeviceMeasure");
        HeartRateGetterUtil.a().c();
    }

    public int getLinkType() {
        return this.mLinkType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tickBiWhenUseAw70(Context context) {
        String b;
        String str;
        if (context == null) {
            LogUtil.b(TAG, "context== null");
            return;
        }
        LogUtil.a(TAG, "tickBiWhenUseAw70");
        HashMap hashMap = new HashMap(16);
        hashMap.put("useRunPosture", "1");
        if (this.mSportType == 283) {
            b = cei.b().getCurrentMacAddress();
        } else {
            b = lau.d().b();
        }
        MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(b);
        if (bondedDeviceByUniqueId != null) {
            str = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(bondedDeviceByUniqueId.getProductId());
        } else {
            str = "";
        }
        hashMap.put("prodId", str);
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.FOLLOWED_DEVICES, null, TAG);
        if (deviceList != null && !deviceList.isEmpty()) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (next != null && next.getDeviceConnectState() == 2) {
                    deviceInfo = next;
                    break;
                }
            }
            if (deviceInfo != null) {
                String deviceName = deviceInfo.getDeviceName();
                int productType = deviceInfo.getProductType();
                LogUtil.a(TAG, "tickBiWhenUseAw70: deviceName = ", deviceName, ", productType = ", Integer.valueOf(productType));
                if (cvt.c(productType)) {
                    hashMap.put("RunPosturetype", "1");
                } else if (productType == 75) {
                    hashMap.put("RunPosturetype", "2");
                } else {
                    hashMap.put("RunPosturetype", "");
                }
            }
        }
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_USE_AW70_2170011.value(), hashMap, 0);
    }

    private void saveSportDataFromDfh(Map<String, Object> map) {
        if (map == null || !hab.g()) {
            LogUtil.h(TAG, "saveSportDataFromDfh() sportDataList == null or is not open smart coach");
            return;
        }
        Object data = BaseSportManager.getInstance().getData("HEART_RATE_DATA");
        int intValue = data instanceof Integer ? ((Integer) data).intValue() : 0;
        Object data2 = BaseSportManager.getInstance().getData("AEROBIC_TRAINING_EFFECT_DATA");
        int intValue2 = data2 instanceof Integer ? ((Integer) data2).intValue() : 0;
        Object data3 = BaseSportManager.getInstance().getData("ETE_ALGO_DATA");
        hae.e().aXI_(getSportDataBundle(map), intValue, intValue2, data3 instanceof Integer ? ((Integer) data3).intValue() : 0);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
        resetTimer();
        gve.b();
    }
}
