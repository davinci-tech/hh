package com.huawei.health.sportservice.dataproducer;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.sportservice.ISportDataSaveStatusCallback;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthDeviceApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.cei;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gso;
import defpackage.gwk;
import defpackage.gwo;
import defpackage.hpx;
import defpackage.koq;
import defpackage.lau;
import defpackage.lbc;
import defpackage.up;
import defpackage.us;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, componentDep = {ComponentName.SPORT_DATA_CENTER}, name = ComponentName.DATA_STORAGE_MANAGER)
/* loaded from: classes4.dex */
public class SportDataStorageManager implements ManagerComponent {
    private static final int MSG_SAVE_DATA_TO_TMP_FILE = 1;
    private static final int ROPE_CONTROL_INTERMITTENT_SPORT = 1;
    private static final String TAG = "SportService_SportDataStorageManager";
    protected Map<String, BaseProducer> mProducerMap;
    protected boolean mIsNeedSaveToFile = false;
    private SportLaunchParams mSportLaunchParams = new SportLaunchParams();
    private fgm mSportCallbackOption = new fgm();
    private final Map<String, ISportDataSaveStatusCallback> mSportDataSaveStatusCallbacks = Collections.synchronizedMap(new LinkedHashMap());
    protected MotionPathSimplify mMotionPathSimplify = new MotionPathSimplify();
    protected MotionPath mMotionPath = new MotionPath();
    private PluginSportTrackAdapter mPluginTrackAdapter = gso.e().c();
    private ExtendHandler mExtendHandler = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            SportDataStorageManager.this.obtainDataAndSaveToFile();
            return true;
        }
    }, "SportDataStorageBackground");

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            this.mSportLaunchParams = (SportLaunchParams) obj;
            return;
        }
        if (SportParamsType.PARAMS_PRODUCER_MAP.equals(sportParamsType) && (obj instanceof Map)) {
            this.mProducerMap = (Map) obj;
        } else if (SportParamsType.DEVICES_DATA_CHANGE_FIRST.equals(sportParamsType)) {
            saveLinkedDevice();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        restoreSportData();
        if (this.mSportLaunchParams.isNeedRestart()) {
            registerTime();
        }
        this.mMotionPathSimplify.saveHasTrackPoint(false);
        this.mMotionPathSimplify.saveSportDataSource(this.mSportLaunchParams.getDataSource() != 5 ? 7 : 5);
        this.mMotionPathSimplify.saveTrackType(0);
        this.mMotionPathSimplify.saveSportType(this.mSportLaunchParams.getSportType());
        if (this.mSportLaunchParams.getSportType() == 279) {
            this.mMotionPathSimplify.saveChiefSportDataType(1);
        }
    }

    private void restoreSportData() {
        LogUtil.a(TAG, "mSportLaunchParams.isRestart()", Boolean.valueOf(this.mSportLaunchParams.isRestart()));
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null && sportLaunchParams.isRestart()) {
            MotionPathSimplify e = gwk.e(BaseApplication.e(), "simplemotion_temp.txt");
            boolean z = false;
            if (e != null) {
                if (this.mSportLaunchParams.getSportType() == e.requestSportType() && this.mSportLaunchParams.getDataSource() == e.requestSportDataSource()) {
                    z = true;
                }
                if (z) {
                    LogUtil.a(TAG, "isSameSport");
                    this.mMotionPathSimplify = e;
                }
                LogUtil.a(TAG, "mMotionPathSimplify", this.mMotionPathSimplify.toString());
            }
            MotionPath c = gwk.c(BaseApplication.e(), this.mSportLaunchParams.getSportType());
            if (c != null && z) {
                LogUtil.a(TAG, "isSameSport mMotionPath", c.toString());
                this.mMotionPath = c;
            }
            ReleaseLogUtil.e(TAG, "restoreSportData result:", Boolean.valueOf(z));
        }
        deleteFile();
    }

    public Object getData(String str) {
        MotionPathSimplify motionPathSimplify;
        if (JsUtil.DataFunc.MOTION_PATH_DATA.equals(str)) {
            return this.mMotionPath;
        }
        if ("MOTION_PATH_SIMPLIFY_DATA".equals(str)) {
            return this.mMotionPathSimplify;
        }
        if ("TEMP_ALL_STAGE_DATA".equals(str) && (motionPathSimplify = this.mMotionPathSimplify) != null) {
            return motionPathSimplify.getTemporaryData();
        }
        LogUtil.h(TAG, "get storage data with error tag:", str);
        return null;
    }

    public void stagingData(String str, Object obj) {
        if (JsUtil.DataFunc.MOTION_PATH_DATA.equals(str)) {
            if (obj instanceof MotionPath) {
                this.mMotionPath = (MotionPath) obj;
                return;
            }
            return;
        }
        if ("MOTION_PATH_SIMPLIFY_DATA".equals(str) && (obj instanceof MotionPathSimplify)) {
            this.mMotionPathSimplify = (MotionPathSimplify) obj;
        }
        if ("TEMP_ALL_STAGE_DATA".equals(str) && (obj instanceof LinkedHashMap)) {
            this.mMotionPathSimplify.getTemporaryData().putAll((LinkedHashMap) obj);
        }
    }

    public boolean isStorageData(String str) {
        return JsUtil.DataFunc.MOTION_PATH_DATA.equals(str) || "MOTION_PATH_SIMPLIFY_DATA".equals(str) || "TEMP_ALL_STAGE_DATA".equals(str);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        if (this.mSportLaunchParams.isNeedRestart()) {
            this.mIsNeedSaveToFile = true;
        }
        regBoltWearAbnormalStatus();
        ReleaseLogUtil.e(TAG, "onStartSport() sportType is ", Integer.valueOf(this.mSportLaunchParams.getSportType()));
        if (this.mSportLaunchParams.getSportType() == 283) {
            this.mMotionPathSimplify.saveDeviceUuid(cei.b().getCurrentMacAddress());
            ReleaseLogUtil.e(TAG, "onStartSport() deviceUuid is ", CommonUtil.l(this.mMotionPathSimplify.requestDeviceUuid()));
        } else if (fhs.b(this.mSportLaunchParams.getSportType())) {
            setDeviceUuid(lau.d().b());
        } else {
            LogUtil.c(TAG, "onStartSport() else");
        }
    }

    private void saveLinkedDevice() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SportDataStorageManager.this.m449x2c5d8965();
            }
        });
    }

    /* renamed from: lambda$saveLinkedDevice$0$com-huawei-health-sportservice-dataproducer-SportDataStorageManager, reason: not valid java name */
    /* synthetic */ void m449x2c5d8965() {
        LogUtil.a(TAG, "saveLinkedDevice start");
        hpx.c(this.mMotionPathSimplify);
    }

    private void setDeviceUuid(final String str) {
        HiHealthDeviceApi.a(BaseApplication.e()).fetchDataClientByUniqueId(0, str, new HiDataClientListener() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager$$ExternalSyntheticLambda3
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                SportDataStorageManager.this.m450x69a57b13(str, list);
            }
        });
    }

    /* renamed from: lambda$setDeviceUuid$1$com-huawei-health-sportservice-dataproducer-SportDataStorageManager, reason: not valid java name */
    /* synthetic */ void m450x69a57b13(String str, List list) {
        if (koq.b(list)) {
            LogUtil.a(TAG, "setDeviceUuid clientList is empty");
            return;
        }
        LogUtil.a(TAG, "setDeviceUuid clientList size is ", Integer.valueOf(list.size()));
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (((HiHealthClient) it.next()).getHiDeviceInfo() != null) {
                LogUtil.a(TAG, "setDeviceUuid hiHealthClient.getHiDeviceInfo() no null");
                this.mMotionPathSimplify.saveDeviceUuid(str);
                break;
            }
        }
        ReleaseLogUtil.e(TAG, "setDeviceUuid deviceUuid is ", CommonUtil.l(this.mMotionPathSimplify.requestDeviceUuid()));
    }

    private void regBoltWearAbnormalStatus() {
        BaseSportManager.getInstance().setBoltStatusListener(new IBaseResponseCallback() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager$$ExternalSyntheticLambda4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.a(SportDataStorageManager.TAG, "S-TAG Wear Abnormal Status is false");
            }
        });
    }

    protected void unregBoltWearAbnormalStatus() {
        BaseSportManager.getInstance().unregBoltAbnormalCallback();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.mIsNeedSaveToFile = false;
        handleEquipmentCourse();
        saveDataToDatabase(this.mMotionPathSimplify, this.mMotionPath);
        unregBoltWearAbnormalStatus();
    }

    private void handleEquipmentCourse() {
        if (!((Boolean) this.mSportLaunchParams.getExtra("equipment_course", Boolean.class, false)).booleanValue()) {
            LogUtil.a(TAG, "handleEquipmentCourse no equipmentCourse");
            return;
        }
        if (this.mSportLaunchParams.getSportType() == 283) {
            ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).ropeDataSetDeviceType(this.mMotionPathSimplify);
        }
        String str = (String) this.mSportLaunchParams.getExtra("coach_detail_name", String.class);
        String str2 = (String) this.mSportLaunchParams.getExtra("workoutid", String.class);
        LogUtil.a(TAG, "handleEquipmentCourse equipmentCourse courseName: ", str, "courseId:", str2);
        this.mMotionPathSimplify.saveRunCourseId(str2);
        this.mMotionPathSimplify.addExtendDataMap("courseName", str);
        this.mMotionPathSimplify.addExtendDataMap("sportRecordType", String.valueOf(0));
        this.mMotionPathSimplify.addExtendDataMap("attachedRecordType", String.valueOf(1));
    }

    public boolean isToSave() {
        if (((Integer) this.mSportLaunchParams.getExtra("keySportRecordIsToSave", Integer.class, 1)).intValue() != 1 || this.mSportLaunchParams.getDataSource() == 100) {
            return false;
        }
        if (this.mSportLaunchParams.getSportType() == 283) {
            Object data = BaseSportManager.getInstance().getData("SKIP_NUM_DATA");
            if (!(data instanceof Integer)) {
                LogUtil.b(TAG, "not jump yet");
                return false;
            }
            if (this.mSportLaunchParams.getSportTarget() == 8 && this.mMotionPathSimplify != null) {
                Integer num = (Integer) data;
                if (num.intValue() < 10) {
                    data = Integer.valueOf(calculateAccumulatedSkipTimes(num.intValue()));
                }
            }
            return ((Integer) data).intValue() >= 10;
        }
        if (this.mSportLaunchParams.getSportType() == 279) {
            Object data2 = BaseSportManager.getInstance().getData("CALORIES_DATA");
            if (data2 instanceof Integer) {
                return ((Integer) data2).intValue() > 0;
            }
            LogUtil.b(TAG, "not sport yet");
            return false;
        }
        Object data3 = BaseSportManager.getInstance().getData("DISTANCE_DATA");
        if (data3 instanceof Integer) {
            return ((Integer) data3).intValue() >= 100;
        }
        LogUtil.b(TAG, "not run to data yet");
        return false;
    }

    private int calculateAccumulatedSkipTimes(int i) {
        int intValue = ((Integer) BaseSportManager.getInstance().getData("SKIPPING_ACCUMULATED_COUNT_DATA")).intValue();
        return ((Integer) BaseSportManager.getInstance().getData("ROPE_MACHINE_STATUS_DATA")).intValue() == 1 ? intValue + i : intValue;
    }

    private void registerTime() {
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager$$ExternalSyntheticLambda2
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SportDataStorageManager.this.m447xfab7e38c(list, map);
            }
        };
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(ComponentName.DATA_STORAGE_MANAGER);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, sportDataNotify);
    }

    /* renamed from: lambda$registerTime$3$com-huawei-health-sportservice-dataproducer-SportDataStorageManager, reason: not valid java name */
    /* synthetic */ void m447xfab7e38c(List list, Map map) {
        ExtendHandler extendHandler;
        if (list.contains("TIME_FIVE_SECOND_DURATION") && (map.get("TIME_FIVE_SECOND_DURATION") instanceof Long) && (extendHandler = this.mExtendHandler) != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    private void checkSportAbnormal(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h(TAG, "checkSportAbnormal, motionPathSimplify == null");
            return;
        }
        if (!lbc.c(BaseSportManager.getInstance().getSportType())) {
            LogUtil.h(TAG, "checkSportAbnormal, not need check abnormal data");
            return;
        }
        us usVar = new us();
        usVar.b(motionPathSimplify.requestPaceMap());
        usVar.c(motionPathSimplify.requestPartTimeMap());
        usVar.c(motionPathSimplify.requestAvgPace());
        usVar.d(motionPathSimplify.requestTotalDistance());
        usVar.b(motionPathSimplify.requestTotalTime());
        usVar.e(motionPathSimplify.requestTotalSteps());
        usVar.d(motionPathSimplify.requestAvgStepRate());
        usVar.c(motionPathSimplify.requestSportType());
        usVar.b(motionPathSimplify.requestSportDataSource());
        LogUtil.a(TAG, "data source is ", Integer.valueOf(motionPathSimplify.requestSportDataSource()));
        int c = up.c(usVar);
        LogUtil.a(TAG, "abnormal type is ", Integer.valueOf(c));
        motionPathSimplify.saveAbnormalTrack(c);
        if (c != 0) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_ABNORMAL_TRAJECTORY_85070016.value(), c);
        }
    }

    public boolean registerSportDataSavedCallback(String str, ISportDataSaveStatusCallback iSportDataSaveStatusCallback) {
        if (TextUtils.isEmpty(str) || iSportDataSaveStatusCallback == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "registerSportDataSavedCallback failed. tag: ";
            objArr[1] = str;
            objArr[2] = ". callback is null: ";
            objArr[3] = Boolean.valueOf(iSportDataSaveStatusCallback == null);
            LogUtil.b(TAG, objArr);
            return false;
        }
        LogUtil.a(TAG, "registerSportDataSavedCallback success " + str);
        this.mSportDataSaveStatusCallbacks.put(str, iSportDataSaveStatusCallback);
        return true;
    }

    public boolean unregisterSportDataSavedCallback(String str) {
        if (TextUtils.isEmpty(str) || !this.mSportDataSaveStatusCallbacks.containsKey(str)) {
            LogUtil.b(TAG, "unregisterSportDataSavedCallback " + str + " failed. tag not in");
            return false;
        }
        LogUtil.a(TAG, "unregisterSportDataSavedCallback success " + str);
        this.mSportDataSaveStatusCallbacks.remove(str);
        return true;
    }

    private void notifySportDataSaveStatus(boolean z) {
        LinkedHashMap linkedHashMap;
        LogUtil.a(TAG, "notifySportDataSaveStatus isSaved " + z);
        synchronized (this.mSportDataSaveStatusCallbacks) {
            linkedHashMap = new LinkedHashMap(this.mSportDataSaveStatusCallbacks);
        }
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((ISportDataSaveStatusCallback) it.next()).onFinish(z);
        }
    }

    protected void notifySportDataNotSaved() {
        notifySportDataSaveStatus(false);
    }

    private void notifySportDataSaved() {
        notifySportDataSaveStatus(true);
    }

    protected void saveDataToDatabase(final MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        LogUtil.a(TAG, "saveDataToDatabase()");
        LogUtil.a(TAG, "mMotionPathSimplify", motionPath.toString());
        if (this.mPluginTrackAdapter != null) {
            if (!isToSave()) {
                LogUtil.a(TAG, "simplify.toString()", motionPathSimplify.toString());
                deleteFile();
                notifySportDataNotSaved();
                return;
            } else {
                checkSportAbnormal(motionPathSimplify);
                deleteFile();
                saveDataToFile(motionPath, motionPathSimplify);
                LogUtil.a(TAG, "data center save DataToDatabase");
                ReleaseLogUtil.e(TAG, "simplify.toString()", motionPathSimplify.toString(), "motionPath", motionPath.toString());
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.dataproducer.SportDataStorageManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportDataStorageManager.this.m448x6d425eba(motionPathSimplify);
                    }
                });
                return;
            }
        }
        notifySportDataNotSaved();
    }

    /* renamed from: lambda$saveDataToDatabase$4$com-huawei-health-sportservice-dataproducer-SportDataStorageManager, reason: not valid java name */
    /* synthetic */ void m448x6d425eba(MotionPathSimplify motionPathSimplify) {
        if (this.mPluginTrackAdapter == null) {
            this.mPluginTrackAdapter = gso.e().c();
        }
        if (this.mPluginTrackAdapter != null) {
            ReleaseLogUtil.e(TAG, "save to db");
            this.mPluginTrackAdapter.saveTrackData(motionPathSimplify, "motion_path2.txt");
        }
        notifySportDataSaved();
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        this.mExtendHandler.sendEmptyMessage(1);
        stopBackgroundThread();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void obtainDataAndSaveToFile() {
        if (this.mIsNeedSaveToFile) {
            deleteFile();
            Iterator<BaseProducer> it = this.mProducerMap.values().iterator();
            while (it.hasNext()) {
                it.next().onSaveData();
            }
            saveDataToFile(this.mMotionPath, this.mMotionPathSimplify);
        }
    }

    private void stopBackgroundThread() {
        this.mExtendHandler.quit(true);
    }

    private void saveDataToFile(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        LogUtil.a(TAG, "saveDataToFile()");
        gwo.a(BaseApplication.e(), motionPathSimplify, "simplemotion_temp.txt");
        gwo.a(BaseApplication.e(), motionPath, "motion_path2.txt");
    }

    private void deleteFile() {
        gwo.a();
        LogUtil.a(TAG, "deleteFile");
    }
}
