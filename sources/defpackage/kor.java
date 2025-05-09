package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.healthcloud.plugintrack.wearengine.rqmanager.RqDataPushReceiver;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.model.HiBloodPressureMetaData;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.up.model.UserInfomation;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class kor {

    /* renamed from: a, reason: collision with root package name */
    private static kor f14457a;
    private static Context c;
    private static final Object e = new Object();
    private String b;
    private List<Integer> m;
    private ExecutorService k = Executors.newSingleThreadExecutor();
    private HeartZoneConf d = new HeartZoneConf();
    private IntentFilter h = new IntentFilter();
    private List<Integer> f = new ArrayList(16);
    private CountDownLatch j = new CountDownLatch(1);
    private boolean g = false;
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: kor.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            LogUtil.a("Common_HWHealthDataManager", "receive broadcastReceiver");
            if (intent == null || (action = intent.getAction()) == null || !action.equals("com.huawei.health.heart_zone_conf_migrate_finish")) {
                return;
            }
            LogUtil.a("Common_HWHealthDataManager", "receive com.huawei.health.heart_zone_conf_migrate_finish");
            kor.this.t();
        }
    };
    private BroadcastReceiver o = new BroadcastReceiver() { // from class: kor.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Common_HWHealthDataManager", "receive broadcastReceiver");
            if (intent == null || !"com.huawei.bone.action.FITNESS_USERINFO_UPDATED".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("Common_HWHealthDataManager", "userinfo save finished then update heartzonethreshold");
            kor.this.r();
            if (EnvironmentUtils.c()) {
                LogUtil.a("Common_HWHealthDataManager", "enter main process then send rqdata to device");
                if (RqDataPushReceiver.checkDeviceRqDataCapability()) {
                    LogUtil.a("Common_HWHealthDataManager", "userInfo changed then send rqdata to device");
                    new hqa().b(BaseApplication.getContext(), DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault()));
                }
            }
        }
    };
    private HiSubscribeListener n = new HiSubscribeListener() { // from class: kor.19
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("Common_HWHealthDataManager", "subscribeHiHealthData onResult");
            if (list == null || list.isEmpty()) {
                return;
            }
            kor.this.m = list;
            LogUtil.a("Common_HWHealthDataManager", "subscribeHiHealthData success");
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("Common_HWHealthDataManager", "subscribeHiHealthData onChange type = ", Integer.valueOf(i), ",changeType = ", str);
            if (i == 102 && "HiSyncUserData".equals(str)) {
                LogUtil.a("Common_HWHealthDataManager", "subscribeHiHealthData receive userinfo update Msg");
                kor.this.r();
                kor.this.s();
            }
        }
    };

    private kor() {
        c = BaseApplication.getContext();
        r();
        LocalBroadcastManager.getInstance(c).registerReceiver(this.o, new IntentFilter("com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));
        this.h.addAction("com.huawei.health.heart_zone_conf_migrate_finish");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.i, this.h, "com.huawei.health.heartZonePermission", null);
        this.f.clear();
        this.f.add(102);
        HiHealthManager.d(c).subscribeHiHealthData(this.f, this.n);
    }

    public static kor a() {
        kor korVar;
        synchronized (e) {
            if (f14457a == null) {
                f14457a = new kor();
            }
            korVar = f14457a;
        }
        return korVar;
    }

    public void d() {
        LogUtil.a("Common_HWHealthDataManager", "enter destroy");
        if (koq.b(this.m)) {
            LogUtil.h("Common_HWHealthDataManager", "destroy, mSuccessList is empty");
            return;
        }
        HiHealthManager.d(c).unSubscribeHiHealthData(this.m, new HiUnSubscribeListener() { // from class: kor.17
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a("Common_HWHealthDataManager", "destroy is unSubscribeHiHealthData, isSuccess is ", Boolean.valueOf(z));
            }
        });
        ExecutorService executorService = this.k;
        if (executorService != null && !executorService.isShutdown()) {
            this.k.shutdown();
        }
        try {
            BaseApplication.getContext().unregisterReceiver(this.i);
        } catch (IllegalArgumentException e2) {
            LogUtil.a("Common_HWHealthDataManager", LogAnonymous.b((Throwable) e2));
        }
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.o);
        } catch (IllegalArgumentException e3) {
            LogUtil.a("Common_HWHealthDataManager", LogAnonymous.b((Throwable) e3));
        }
        f14457a = null;
    }

    public HiUserPreference d(Context context, String str) {
        return HiHealthManager.d(context).getUserPreference(str);
    }

    public void e(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getTrackDetail()");
        kop.b().e(j, j2, iBaseResponseCallback);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getTodayFitnessTotalData()");
        kop.b().b(iBaseResponseCallback);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getTodayDetailPointData()");
        kop.b().e(iBaseResponseCallback);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getTodayDetailSegentData()");
        kop.b().a(iBaseResponseCallback);
    }

    public void c(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWOldHealthDataManager getCoreSleepDetail()");
        kpi.a().c(j, i, i2, i3, iBaseResponseCallback);
    }

    public void d(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getTrackDetailData()");
        hps.a(j, j2, iBaseResponseCallback);
    }

    public void e(final long[] jArr, final double[] dArr, final List<String> list, String str, final IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            LogUtil.a("Common_HWHealthDataManager", "insertBloodPressureData deviceId == ", str);
            if (!TextUtils.isEmpty(str)) {
                e(jArr, dArr, str, list, iBaseResponseCallback);
            } else if (!TextUtils.isEmpty(this.b)) {
                e(jArr, dArr, this.b, list, iBaseResponseCallback);
            } else {
                LogUtil.a("Common_HWHealthDataManager", "deviceId == null && mDeviceUuid == null");
                HiHealthManager.d(BaseApplication.getContext()).fetchManualDataClient(new HiDataClientListener() { // from class: kor.20
                    @Override // com.huawei.hihealth.data.listener.HiDataClientListener
                    public void onResult(List<HiHealthClient> list2) {
                        if (koq.b(list2)) {
                            LogUtil.h("Common_HWHealthDataManager", "CollectionUtils.isEmpty()");
                            return;
                        }
                        HiHealthClient hiHealthClient = list2.get(0);
                        kor.this.b = hiHealthClient.getDeviceUuid();
                        LogUtil.a("Common_HWHealthDataManager", "mDeviceUuid = ", kor.this.b);
                        kor korVar = kor.this;
                        korVar.e(jArr, dArr, korVar.b, (List<String>) list, iBaseResponseCallback);
                    }
                });
            }
        }
    }

    public void d(Context context, HiHealthData hiHealthData, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || hiHealthData == null || iBaseResponseCallback == null) {
            LogUtil.b("Common_HWHealthDataManager", "insertBloodSugarData params is null");
            return;
        }
        synchronized (e) {
            String deviceUuid = hiHealthData.getDeviceUuid();
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.addData(hiHealthData);
            hiDataInsertOption.setPackageName(str);
            if (!TextUtils.isEmpty(deviceUuid)) {
                a(hiDataInsertOption, iBaseResponseCallback);
            } else if (!TextUtils.isEmpty(this.b)) {
                hiHealthData.setDeviceUuid(this.b);
                a(hiDataInsertOption, iBaseResponseCallback);
            } else {
                LogUtil.a("Common_HWHealthDataManager", "insertBloodsugarData mDeviceUuid == null || mDeviceUuid.isEmpty()");
                e(context, hiHealthData, hiDataInsertOption, iBaseResponseCallback);
            }
        }
    }

    public void b(Context context, final long[] jArr, final double[] dArr, final IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            String str = this.b;
            if (str != null && !str.isEmpty()) {
                c(jArr, dArr, 3, this.b, iBaseResponseCallback);
            }
            LogUtil.h("Common_HWHealthDataManager", "insertBloodsugarData mDeviceUuid == null || mDeviceUuid.isEmpty()");
            HiHealthManager.d(context).fetchManualDataClient(new HiDataClientListener() { // from class: kor.22
                @Override // com.huawei.hihealth.data.listener.HiDataClientListener
                public void onResult(List<HiHealthClient> list) {
                    if (koq.b(list)) {
                        return;
                    }
                    LogUtil.a("Common_HWHealthDataManager", "insertBloodSugarData clientList.size == ", Integer.valueOf(list.size()));
                    HiHealthClient hiHealthClient = list.get(0);
                    kor.this.b = hiHealthClient.getDeviceUuid();
                    kor korVar = kor.this;
                    korVar.c(jArr, dArr, 3, korVar.b, iBaseResponseCallback);
                }
            });
        }
    }

    private void e(Context context, final HiHealthData hiHealthData, final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(context).fetchManualDataClient(new HiDataClientListener() { // from class: kor.25
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                if (koq.b(list)) {
                    return;
                }
                LogUtil.a("Common_HWHealthDataManager", "insertBloodSugarData clientList.size:", Integer.valueOf(list.size()));
                HiHealthClient hiHealthClient = list.get(0);
                kor.this.b = hiHealthClient.getDeviceUuid();
                hiHealthData.setDeviceUuid(kor.this.b);
                kor.this.a(hiDataInsertOption, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long[] jArr, double[] dArr, int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        HiHealthData hiHealthData;
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        if (i != 0) {
            if (i != 1) {
                if (i == 3) {
                    hiHealthData = new HiHealthData(10001);
                    hiHealthData.setDeviceUuid(str);
                    hiHealthData.setStartTime(jArr[0]);
                    hiHealthData.setEndTime(jArr[1]);
                    hiHealthData.setType((int) dArr[0]);
                    hiHealthData.setValue(dArr[1]);
                    HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
                    hiBloodSugarMetaData.setConfirmed(true);
                    hiHealthData.setMetaData(HiJsonUtil.e(hiBloodSugarMetaData));
                } else if (i == 6) {
                    hiHealthData = new HiHealthData(2104);
                    hiHealthData.setDeviceUuid(str);
                    hiHealthData.setStartTime(jArr[0]);
                    hiHealthData.setEndTime(jArr[1]);
                    hiHealthData.setValue(dArr[0]);
                } else {
                    LogUtil.h("Common_HWHealthDataManager", "insertData dataType = ", Integer.valueOf(i));
                }
            }
            hiHealthData = null;
        } else {
            hiHealthData = new HiHealthData(10006);
            hiHealthData.setDeviceUuid(str);
            hiHealthData.setStartTime(jArr[0]);
            hiHealthData.setEndTime(jArr[1]);
            hiHealthData.putDouble("bodyWeight", dArr[0]);
            hiHealthData.putDouble(BleConstants.BODY_FAT_RATE, dArr[1]);
        }
        if (hiHealthData == null) {
            return;
        }
        hiDataInsertOption.addData(hiHealthData);
        a(hiDataInsertOption, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kor.23
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (obj != null && i == 0) {
                    LogUtil.a("Common_HWHealthDataManager", "insertData success");
                    iBaseResponseCallback.d(0, obj);
                } else {
                    LogUtil.h("Common_HWHealthDataManager", "insertData fail errorCode = ", Integer.valueOf(i));
                    iBaseResponseCallback.d(100001, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long[] jArr, double[] dArr, String str, List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        if (jArr == null || dArr == null || dArr.length < 3) {
            LogUtil.h("Common_HWHealthDataManager", "insertBloodPressureAndHeartRateData invalid parameter");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setStartTime(jArr[1]);
        hiHealthData.setEndTime(jArr[1]);
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()), Double.valueOf(dArr[0]));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()), Double.valueOf(dArr[1]));
        if (dArr[2] > 0.0d) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()), Double.valueOf(dArr[2]));
        }
        if (dArr.length == 4) {
            if (koq.c(list)) {
                HashMap hashMap2 = new HashMap(1);
                HiBloodPressureMetaData hiBloodPressureMetaData = new HiBloodPressureMetaData();
                hiBloodPressureMetaData.setActivityActions(list);
                hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()), HiJsonUtil.e(hiBloodPressureMetaData));
                hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
            }
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()), Double.valueOf(dArr[3]));
        }
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        a(hiDataInsertOption, iBaseResponseCallback);
    }

    public void g(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getVo2maxDetail()");
        kow.e().a(j, j2, iBaseResponseCallback);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getLastVo2max()");
        kow.e().c(false, iBaseResponseCallback);
    }

    public void a(long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value(), DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", BleConstants.BLOODPRESSURE_SPHYGMUS, "beforeMeasureActivity"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        if (i != 0) {
            hiAggregateOption.setCount(i);
        }
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kor.24
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (list == null || list.isEmpty()) {
                    iBaseResponseCallback.d(i2, null);
                } else {
                    iBaseResponseCallback.d(i2, list);
                }
            }
        });
    }

    public void d(Context context, HiDataReadProOption hiDataReadProOption, IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(context).readHiHealthDataPro(hiDataReadProOption, new a(iBaseResponseCallback));
    }

    public void e(Context context, HiDataReadOption hiDataReadOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kor.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                SparseArray sparseArray = (SparseArray) obj;
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Common_HWHealthDataManager", "getBloodSugarData callback is null");
                    return;
                }
                if (sparseArray == null || sparseArray.size() <= 0) {
                    iBaseResponseCallback.d(100001, null);
                    LogUtil.h("Common_HWHealthDataManager", "map none");
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                arrayList.addAll(kor.bPC_(sparseArray, 2008));
                arrayList.addAll(kor.bPC_(sparseArray, 2009));
                arrayList.addAll(kor.bPC_(sparseArray, 2010));
                arrayList.addAll(kor.bPC_(sparseArray, 2011));
                arrayList.addAll(kor.bPC_(sparseArray, 2012));
                arrayList.addAll(kor.bPC_(sparseArray, 2013));
                arrayList.addAll(kor.bPC_(sparseArray, 2014));
                arrayList.addAll(kor.bPC_(sparseArray, 2015));
                arrayList.addAll(kor.bPC_(sparseArray, 2106));
                arrayList.addAll(kor.bPC_(sparseArray, 2108));
                arrayList.addAll(kor.bPC_(sparseArray, 10001));
                LogUtil.a("Common_HWHealthDataManager", "getBloodSugarData hasData");
                if (arrayList.isEmpty()) {
                    iBaseResponseCallback.d(100001, null);
                } else {
                    LogUtil.a("Common_HWHealthDataManager", "getBloodsugarData total count = ", Integer.valueOf(arrayList.size()));
                    iBaseResponseCallback.d(0, arrayList);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("Common_HWHealthDataManager", "onResultIntent rest");
            }
        });
    }

    public void d(Context context, long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{10001});
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(1);
        if (i != 0) {
            hiDataReadOption.setCount(i);
        }
        e(context, hiDataReadOption, iBaseResponseCallback);
    }

    public static void e(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{2108});
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setCount(i);
        a().e(com.huawei.haf.application.BaseApplication.e(), hiDataReadOption, iBaseResponseCallback);
    }

    public void e(Context context, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "getLastTimeHeartRateData enter");
        kow.e().c(context, j, j2, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Common_HWHealthDataManager", "dealBloodOxygenCallback cannot callback");
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Common_HWHealthDataManager", "dealBloodOxygenCallback can not convert");
            iBaseResponseCallback.d(0, null);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            iBaseResponseCallback.d(0, null);
            return;
        }
        ArrayList<HiHealthData> arrayList = new ArrayList(16);
        Object obj2 = sparseArray.get(2103);
        if (obj2 instanceof List) {
            arrayList.addAll((List) obj2);
        }
        Object obj3 = sparseArray.get(2107);
        if (obj3 instanceof List) {
            arrayList.addAll((List) obj3);
        }
        HiHealthData hiHealthData = new HiHealthData();
        for (HiHealthData hiHealthData2 : arrayList) {
            if (hiHealthData2.getEndTime() > hiHealthData.getEndTime()) {
                hiHealthData = hiHealthData2;
            }
        }
        arrayList.clear();
        if (hiHealthData.getIntValue() > 0) {
            arrayList.add(hiHealthData);
        }
        iBaseResponseCallback.d(0, arrayList);
    }

    public void c(Context context, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        LogUtil.a("Common_HWHealthDataManager", "getLastTimeBloodOxygenData startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{2103, 2107});
        hiDataReadOption.setCount(1);
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kor.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                kor.this.c(obj, iBaseResponseCallback);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("Common_HWHealthDataManager", "getLastTimeBloodOxygenData onResultIntent");
            }
        });
    }

    public void b(Context context, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "getRestHeartRateData enter");
        kow.e().a(context, j, j2, iBaseResponseCallback);
    }

    public void b(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        kot.a().c(new ResponseCallback<Float>() { // from class: kor.2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, Float f) {
                LogUtil.h("Common_HWHealthDataManager", "initGoalWeight data ", f);
                if (f == null) {
                    iBaseResponseCallback.d(100001, Double.valueOf(60.0d));
                } else {
                    iBaseResponseCallback.d(0, Double.valueOf(Double.parseDouble(String.valueOf(f))));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        jdx.b(new Runnable() { // from class: koo
            @Override // java.lang.Runnable
            public final void run() {
                kor.this.o();
            }
        });
    }

    /* synthetic */ void o() {
        LogUtil.a("Common_HWHealthDataManager", "updateHeartRateThroshold enter");
        UserInfomation n = n();
        boolean z = n.getAgeOrDefaultValue() != 0;
        this.g = z;
        if (!z) {
            LogUtil.h("Common_HWHealthDataManager", "get user info fail.");
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("actiontype", Integer.toString(1));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap);
            return;
        }
        c(n);
    }

    private void c(UserInfomation userInfomation) {
        HiUserPreference userPreference = HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).getUserPreference("userPreference_HeartRate_all_posture_data");
        if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
            try {
                c((HeartRateZoneMgr) new Gson().fromJson(userPreference.getValue(), HeartRateZoneMgr.class));
                kox.e().b(this.d, userInfomation);
                return;
            } catch (JsonSyntaxException | NumberFormatException unused) {
                LogUtil.b("Common_HWHealthDataManager", " handleMultipleHeartRate parse value fail ");
                a(userInfomation);
                return;
            }
        }
        LogUtil.a("Common_HWHealthDataManager", "new heart data is null");
        a(userInfomation);
    }

    private void c(HeartRateZoneMgr heartRateZoneMgr) {
        e(this.d, heartRateZoneMgr.getPostureType(1));
        this.j.countDown();
    }

    public void e(HeartZoneConf heartZoneConf, HeartRateThresholdConfig heartRateThresholdConfig) {
        if (heartRateThresholdConfig == null) {
            LogUtil.h("Common_HWHealthDataManager", "writeHeartRateZoneData config is null");
            a(n());
            return;
        }
        heartZoneConf.setWarningEnble(heartRateThresholdConfig.getWarningEnable());
        heartZoneConf.setWarningLimitHR(heartRateThresholdConfig.getWarningLimit());
        heartZoneConf.setWarningEnbleHRR(heartRateThresholdConfig.getWarningEnable());
        heartZoneConf.setWarningLimitHRHRR(heartRateThresholdConfig.getWarningLimit());
        heartZoneConf.setClassifyMethod(heartRateThresholdConfig.getClassifyMethod());
        heartZoneConf.setRestHeartRateDefault(heartRateThresholdConfig.getRestHeartRateDefault());
        heartZoneConf.setRestHeartRate(heartRateThresholdConfig.getRestHeartRate());
        heartZoneConf.setFitnessThreshold(heartRateThresholdConfig.getFitnessThreshold());
        heartZoneConf.setWarmUpThreshold(heartRateThresholdConfig.getWarmUpThreshold());
        heartZoneConf.setFatBurnThreshold(heartRateThresholdConfig.getFatBurnThreshold());
        heartZoneConf.setAerobicThreshold(heartRateThresholdConfig.getAerobicThreshold());
        heartZoneConf.setAnaerobicThreshold(heartRateThresholdConfig.getAnaerobicThreshold());
        heartZoneConf.setMaxThreshold(heartRateThresholdConfig.getMaxThreshold());
        heartZoneConf.setAerobicBaseThreshold(heartRateThresholdConfig.getAerobicBaseThreshold());
        heartZoneConf.setAerobicAdvanceThreshold(heartRateThresholdConfig.getAerobicAdvanceThreshold());
        heartZoneConf.setLacticAcidThreshold(heartRateThresholdConfig.getLacticAcidThreshold());
        heartZoneConf.setAnaerobicBaseThreshold(heartRateThresholdConfig.getAnaerobicBaseThreshold());
        heartZoneConf.setAnaerobicAdvanceThreshold(heartRateThresholdConfig.getAnaerobicAdvanceThreshold());
        heartZoneConf.setHrrMaxThreshold(heartRateThresholdConfig.getMaxThreshold());
    }

    private void a(UserInfomation userInfomation) {
        String birthday = userInfomation.getBirthday();
        int ageOrDefaultValue = userInfomation.getAgeOrDefaultValue();
        if (Integer.toString(-100).equals(birthday)) {
            ageOrDefaultValue = 25;
        }
        HeartZoneConf heartZoneConf = new HeartZoneConf(ageOrDefaultValue);
        heartZoneConf.setRestHeartRateDefault(this.d.getRestHeartRateDefault());
        heartZoneConf.resetHeartZoneConf(ageOrDefaultValue);
        Context context = BaseApplication.getContext();
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Classify_Method");
        int h = userPreference != null ? CommonUtil.h(userPreference.getValue()) : 0;
        heartZoneConf.setClassifyMethod(h);
        int restHeartRateDefault = this.d.getRestHeartRateDefault();
        HiUserPreference userPreference2 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_Rest_HeartRate");
        if (userPreference2 != null) {
            restHeartRateDefault = CommonUtil.e(userPreference2.getValue(), 60);
        }
        heartZoneConf.setRestHeartRate(restHeartRateDefault);
        HiUserPreference userPreference3 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Limit_Flag");
        HiUserPreference userPreference4 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Flag");
        a(heartZoneConf, userPreference3, userPreference4);
        c(heartZoneConf, userPreference3, userPreference4);
        LogUtil.c("Common_HWHealthDataManager", "getHeartZoneConf ", heartZoneConf);
        this.d.setClassifyMethod(h);
        this.d.setRestHeartRate(restHeartRateDefault);
        this.d.setHeartZoneConf(heartZoneConf);
        this.d.setHRRHeartZoneConf(heartZoneConf);
        kox.e().b(heartZoneConf, userInfomation);
        this.j.countDown();
    }

    private void a(HeartZoneConf heartZoneConf, HiUserPreference hiUserPreference, HiUserPreference hiUserPreference2) {
        if (heartZoneConf == null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HeartZone_Config");
        int warningLimitHR = heartZoneConf.getWarningLimitHR();
        int maxThreshold = heartZoneConf.getMaxThreshold();
        if (userPreference == null || userPreference.getValue() == null) {
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        heartZoneConf.setHRZoneConf(split[0]);
        heartZoneConf.setThreshold(split[1]);
        if (hiUserPreference != null) {
            int h = CommonUtil.h(hiUserPreference.getValue());
            if (h == 0) {
                heartZoneConf.setWarningLimitHR(warningLimitHR);
            }
            kox.e().a(h != 0);
        }
        if (hiUserPreference2 != null) {
            int h2 = CommonUtil.h(hiUserPreference2.getValue());
            if (h2 == 0) {
                heartZoneConf.setMaxThreshold(maxThreshold);
            }
            kox.e().e(h2 != 0);
        }
    }

    private void c(HeartZoneConf heartZoneConf, HiUserPreference hiUserPreference, HiUserPreference hiUserPreference2) {
        if (heartZoneConf == null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HRRHeartZone_Config");
        int warningLimitHRHRR = heartZoneConf.getWarningLimitHRHRR();
        int hrrMaxThreshold = heartZoneConf.getHrrMaxThreshold();
        if (userPreference == null || userPreference.getValue() == null) {
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        heartZoneConf.setHRRHRZoneConf(split[0]);
        heartZoneConf.setHrrThreshold(split[1]);
        if (hiUserPreference != null && CommonUtil.h(hiUserPreference.getValue()) == 0) {
            heartZoneConf.setWarningLimitHRHRR(warningLimitHRHRR);
        }
        if (hiUserPreference2 == null || CommonUtil.h(hiUserPreference2.getValue()) != 0) {
            return;
        }
        heartZoneConf.setHrrMaxThreshold(hrrMaxThreshold);
    }

    public void b(final HeartZoneConf heartZoneConf) {
        this.k.execute(new Runnable() { // from class: kor.3
            @Override // java.lang.Runnable
            public void run() {
                HeartZoneConf heartZoneConf2 = heartZoneConf;
                if (heartZoneConf2 != null) {
                    kor.this.d = heartZoneConf2;
                }
                Context context = BaseApplication.getContext();
                kor.this.b(context, "custom.UserPreference_HeartRate_Limit_Flag");
                kor.this.b(context, "custom.UserPreference_HeartRate_Flag");
                HiHealthManager.d(context).setUserPreference(new HiUserPreference("custom.UserPreference_HeartRate_Classify_Method", String.valueOf(kor.this.d.getClassifyMethod())), true);
                HiHealthManager.d(context).setUserPreference(new HiUserPreference("custom.UserPreference_Rest_HeartRate", String.valueOf(kor.this.d.getRestHeartRate())), true);
                HiHealthManager.d(context).setUserPreference(new HiUserPreference("custom.UserPreference_HRRHeartZone_Config", kor.this.d.getHRRHRZoneConfStr() + "," + kor.this.d.getHrrThresholdString()), true);
                HiHealthManager.d(context).setUserPreference(new HiUserPreference("custom.UserPreference_HeartZone_Config", kor.this.d.getHRZoneConfStr() + "," + kor.this.d.getThresholdString()), true);
                LogUtil.a("Common_HWHealthDataManager", "setHRRHeartRateThrosholdConf write over");
                if (Utils.i()) {
                    HiSyncOption hiSyncOption = new HiSyncOption();
                    hiSyncOption.setSyncModel(2);
                    hiSyncOption.setSyncAction(0);
                    hiSyncOption.setSyncDataType(10026);
                    hiSyncOption.setSyncScope(1);
                    hiSyncOption.setSyncMethod(2);
                    HiHealthNativeApi.a(kor.c).synCloud(hiSyncOption, null);
                }
                CommonUtil.am(kor.c);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str) {
        try {
            if (HiHealthManager.d(context).getUserPreference(str) == null) {
                HiHealthManager.d(context).setUserPreference(new HiUserPreference(str, "0"), true);
                LogUtil.c("Common_HWHealthDataManager", "writeHeartDataToDataBase userPreferenceLimitHeart is null");
            }
        } catch (Exception e2) {
            LogUtil.b("Common_HWHealthDataManager", LogAnonymous.b((Throwable) e2));
        }
    }

    public void c(HeartZoneConf heartZoneConf) {
        LogUtil.a("Common_HWHealthDataManager", "setHeartRateThrosholdConf enter");
        this.d.setHeartZoneConf(heartZoneConf);
    }

    public void a(HeartZoneConf heartZoneConf) {
        LogUtil.a("Common_HWHealthDataManager", "setHRRHeartRateThrosholdConf enter");
        this.d.setHRRHeartZoneConf(heartZoneConf);
    }

    public void e(int i) {
        this.d.setClassifyMethod(i);
    }

    public void d(int i) {
        this.d.setRestHeartRate(i);
    }

    public void a(final String str, final String str2) {
        LogUtil.a("Common_HWHealthDataManager", "writeUnitToDb");
        this.k.execute(new Runnable() { // from class: kor.8
            @Override // java.lang.Runnable
            public void run() {
                HiHealthManager.d(kor.c).setUserPreference(new HiUserPreference(str, str2));
                if (Utils.i()) {
                    HiHealthNativeApi.a(kor.c).synCloud(HiSyncOption.getAutoSyncOption(10026), null);
                }
            }
        });
    }

    public void s() {
        this.k.execute(new Runnable() { // from class: kor.7
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.metric_imperial_unit");
                HiUserPreference userPreference2 = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.temperature_unit");
                HiUserPreference userPreference3 = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_unit");
                kor.this.d(userPreference);
                kor.this.c(userPreference2);
                kor.this.a(userPreference3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiUserPreference hiUserPreference) {
        int h = (hiUserPreference == null || !a(hiUserPreference.getValue())) ? 0 : CommonUtil.h(hiUserPreference.getValue());
        if (h != 0) {
            UnitUtil.c(h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0015, code lost:
    
        if (defpackage.nsn.e(r2.getValue()) == 1) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(com.huawei.hihealth.HiUserPreference r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L18
            java.lang.String r0 = r2.getValue()
            boolean r0 = r1.a(r0)
            if (r0 == 0) goto L18
            java.lang.String r2 = r2.getValue()
            int r2 = defpackage.nsn.e(r2)
            r0 = 1
            if (r2 != r0) goto L18
            goto L19
        L18:
            r0 = 0
        L19:
            health.compact.a.UnitUtil.c(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kor.d(com.huawei.hihealth.HiUserPreference):void");
    }

    private boolean a(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HiUserPreference hiUserPreference) {
        UnitUtil.a(!"false".equals(hiUserPreference != null ? hiUserPreference.getValue() : ""));
    }

    public void d(Context context, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        int[] d = HiHealthDataType.d(10006);
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimeInterval(j, j2);
        hiDataDeleteOption.setTypes(d);
        LogUtil.c("Common_HWHealthDataManager", "deleteWeightDatas 222 begain query");
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.6
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (!(obj instanceof Boolean)) {
                    iBaseResponseCallback.d(100001, false);
                    return;
                }
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (booleanValue) {
                    iBaseResponseCallback.d(0, Boolean.valueOf(booleanValue));
                    LogUtil.a("Common_HWHealthDataManager", "deleteWeightData ErrorConstants.SUCCESS");
                } else {
                    iBaseResponseCallback.d(100001, Boolean.valueOf(booleanValue));
                    LogUtil.h("Common_HWHealthDataManager", "deleteWeightData ErrorConstants.ERR_NONE");
                }
            }
        });
    }

    public void c(Context context, List<HiTimeInterval> list, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(HiHealthDataType.d(10006));
        LogUtil.c("Common_HWHealthDataManager", "deleteWeightDatas 111 begain query");
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.10
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        iBaseResponseCallback.d(0, true);
                        return;
                    }
                } else {
                    LogUtil.h("Common_HWHealthDataManager", "deleteWeightDatas,obj is not instanceof Boolean");
                }
                iBaseResponseCallback.d(100001, false);
            }
        });
    }

    public void e(final List<HiTimeInterval> list, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()});
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.9
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (i != 0) {
                    LogUtil.h("Common_HWHealthDataManager", "deleteBloodPressureDatas fail, errorCode = ", Integer.valueOf(i));
                    iBaseResponseCallback.d(i, false);
                } else {
                    LogUtil.a("Common_HWHealthDataManager", "deleteBloodPressureDatas success");
                    kor.this.d((List<HiTimeInterval>) list, iBaseResponseCallback);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiTimeInterval> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "deletePulse start");
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()});
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.11
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (i != 0) {
                    LogUtil.h("Common_HWHealthDataManager", "deletePulse fail, errorCode = ", Integer.valueOf(i));
                } else {
                    LogUtil.a("Common_HWHealthDataManager", "deletePulse success");
                }
                iBaseResponseCallback.d(0, true);
            }
        });
    }

    public void a(Context context, int i, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimeInterval(j, j2);
        hiDataDeleteOption.setTypes(new int[]{i});
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.12
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                if (i2 == 9) {
                    iBaseResponseCallback.d(100001, obj);
                } else {
                    iBaseResponseCallback.d(0, obj);
                }
            }
        });
    }

    public void a(Context context, List<HiTimeInterval> list, int[] iArr, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || list == null || iArr == null || iBaseResponseCallback == null) {
            LogUtil.h("context == null || hiTimeIntervalList == null || types == null || callback == null", new Object[0]);
            return;
        }
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(iArr);
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: kor.15
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (i == 9) {
                    iBaseResponseCallback.d(100001, obj);
                } else {
                    iBaseResponseCallback.d(0, obj);
                }
            }
        });
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback, long j) {
        HiTimeInterval hiTimeInterval = new HiTimeInterval();
        hiTimeInterval.setStartTime(j);
        hiTimeInterval.setEndTime(jdl.e(j));
        LogUtil.a("Common_HWHealthDataManager", "getCurrentDaySportDataOrigin getHealthApi in. DataOriginStartDay = ", hiTimeInterval);
        HiHealthManager.d(c).fetchDataSourceByType(2, hiTimeInterval, new HiDataClientListener() { // from class: kor.13
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                LogUtil.a("Common_HWHealthDataManager", "getCurrentDaySportDataOrigin getHealthApi out.");
                if (list == null) {
                    LogUtil.h("Common_HWHealthDataManager", "getCurrentDaySportDataOrigin, clientList is null");
                    iBaseResponseCallback.d(1, Collections.EMPTY_LIST);
                } else {
                    LogUtil.a("Common_HWHealthDataManager", "getCurrentDaySportDataOrigin SUCCESS");
                    iBaseResponseCallback.d(0, list);
                }
            }
        });
    }

    public HeartZoneConf g() {
        return this.d;
    }

    public HeartZoneConf h() {
        LogUtil.a("Common_HWHealthDataManager", "enter getHeartZoneConfForBluetoothSend");
        try {
            if (!this.j.await(20L, TimeUnit.SECONDS)) {
                LogUtil.h("Common_HWHealthDataManager", "get info timeout.");
                return null;
            }
            LogUtil.a("Common_HWHealthDataManager", "latch over, return object.");
            if (!this.g) {
                LogUtil.h("Common_HWHealthDataManager", "getHeartZoneConfForBluetoothSend get userinfo fail");
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("actiontype", Integer.toString(3));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap);
                return null;
            }
            if (this.d.getAnaerobicThreshold() > this.d.getMaxThreshold() || this.d.getAnaerobicAdvanceThreshold() > this.d.getHrrMaxThreshold()) {
                LogUtil.a("Common_HWHealthDataManager", "getHeartZoneConfForBluetoothSend threshold wrong");
                LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(1);
                linkedHashMap2.put("actiontype", Integer.toString(4));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap2);
            }
            return this.d;
        } catch (InterruptedException e2) {
            LogUtil.b("Common_HWHealthDataManager", "getHeartZoneConfForBluetoothSend exception : ", e2.getMessage());
            return null;
        }
    }

    public void l() {
        if (this.d.getClassifyMethod() == 1) {
            q();
        }
        this.d.resetHeartZoneConf(n().getAgeOrDefaultValue());
        m();
    }

    private void q() {
        HeartZoneConf heartZoneConf = this.d;
        heartZoneConf.setRestHeartRate(heartZoneConf.getRestHeartRateDefault());
    }

    public int f() {
        return this.d.getFitnessThreshold();
    }

    public int i() {
        return this.d.getWarmUpThreshold();
    }

    public int c() {
        return this.d.getAerobicBaseThreshold();
    }

    public int e() {
        return this.d.getAerobicAdvanceThreshold();
    }

    public UserInfomation n() {
        UserInfomation userInfoFromDbSync = LoginInit.getInstance(c).getUserInfoFromDbSync();
        if (userInfoFromDbSync != null) {
            return userInfoFromDbSync;
        }
        UserInfomation userInfomation = new UserInfomation();
        LogUtil.a("Common_HWHealthDataManager", "getLocalUserinfo new userInfomation");
        return userInfomation;
    }

    public void m() {
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("custom.UserPreference_HeartRate_Flag", "0"), true);
    }

    public int j() {
        int maxThreshold = new HeartZoneConf(n().getAgeOrDefaultValue()).getMaxThreshold();
        LogUtil.a("Common_HWHealthDataManager", "getHeartRateMaxValue from age = ", Integer.valueOf(maxThreshold));
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HeartZone_Config");
        if (userPreference != null && userPreference.getValue() != null) {
            String[] split = userPreference.getValue().split(",");
            if (split.length == 2) {
                String[] split2 = split[1].split("\\|");
                try {
                    if (split2.length > 5) {
                        maxThreshold = Integer.parseInt(split2[5]);
                        LogUtil.a("Common_HWHealthDataManager", "getHeartRateMaxValue from sp = ", Integer.valueOf(maxThreshold));
                    }
                } catch (ArrayIndexOutOfBoundsException e2) {
                    LogUtil.b("Common_HWHealthDataManager", e2.getMessage());
                } catch (NumberFormatException e3) {
                    LogUtil.b("Common_HWHealthDataManager", e3.getMessage());
                }
            }
        }
        LogUtil.c("Common_HWHealthDataManager", "getHeartRateMaxValue iMax = ", Integer.valueOf(maxThreshold));
        return maxThreshold;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        jdx.b(new Runnable() { // from class: kos
            @Override // java.lang.Runnable
            public final void run() {
                kor.this.k();
            }
        });
    }

    /* synthetic */ void k() {
        LogUtil.a("Common_HWHealthDataManager", "MigrateHeartZoneConf enter");
        HeartZoneConf heartZoneConf = new HeartZoneConf(n().getAgeOrDefaultValue());
        e("custom.UserPreference_HeartRate_Classify_Method");
        e("custom.UserPreference_Rest_HeartRate");
        d(heartZoneConf, "custom.UserPreference_HeartZone_Config");
        d(heartZoneConf, "custom.UserPreference_HRRHeartZone_Config");
        LogUtil.a("Common_HWHealthDataManager", "MigrateHeartZoneConf exit");
    }

    private void e(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        if (userPreference == null) {
            return;
        }
        int h = CommonUtil.h(userPreference.getValue());
        if (str.equals("custom.UserPreference_HeartRate_Classify_Method")) {
            e(h);
        } else if (str.equals("custom.UserPreference_Rest_HeartRate")) {
            d(h);
        } else {
            LogUtil.h("Common_HWHealthDataManager", "setHeartRateConf1 flag is wrong");
        }
    }

    private void d(HeartZoneConf heartZoneConf, String str) {
        LogUtil.c("Common_HWHealthDataManager", "getHeartZoneConf ", heartZoneConf);
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        if (userPreference == null || userPreference.getValue() == null) {
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        str.hashCode();
        if (str.equals("custom.UserPreference_HeartZone_Config")) {
            this.d.setThreshold(split[0]);
            c(this.d);
        } else if (str.equals("custom.UserPreference_HRRHeartZone_Config")) {
            this.d.setHrrThreshold(split[0]);
            a(this.d);
        } else {
            LogUtil.h("Common_HWHealthDataManager", "setHeartRateConf2 flag is wrong");
        }
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "acquireLastTrackData");
        kpm.c().c(iBaseResponseCallback);
    }

    public void c(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("Common_HWHealthDataManager", "requestDivingData with isDistinguished is ", Boolean.valueOf(z));
        kpm.c().b(j, j2, i, iBaseResponseCallback, z);
    }

    public void b(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        c(j, j2, i, iBaseResponseCallback, false);
    }

    public void c(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "requestTrackSimplifyData");
        kpm.c().c(j, j2, i, false, iBaseResponseCallback);
    }

    public void d(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        kpm.c().d(j, j2, i, iBaseResponseCallback);
    }

    public void b(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "requestTrackSimplifyData");
        kpm.c().a(kwyVar, false, iBaseResponseCallback);
    }

    public void a(long j, long j2, int[] iArr, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "requestTrackSimplifyData");
        kpm.c().b(j, j2, iArr, false, iBaseResponseCallback);
    }

    public void c(long j, long j2, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "requestTrackStatData");
        kpm.c().e(j, j2, i, i2, iBaseResponseCallback);
    }

    public void d(long j, long j2, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        kpm.c().a(j, j2, i, i2, iBaseResponseCallback);
    }

    public void c(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getStressDatasForDiagram()");
        kpl.c().e(j, j2, iBaseResponseCallback);
    }

    public void a(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager getPressureMeasureStatistic()");
        kpl.c().a(j, j2, iBaseResponseCallback);
    }

    public void d(String str, HiStressMetaData hiStressMetaData, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWHealthDataManager", "HWHealthDataManager setStressData");
        kpl.c().b(str, hiStressMetaData, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{46018, 46020});
        hiAggregateOption.setConstantsKey(new String[]{"HR_REST", "HR_SLEEP_REST"});
        hiAggregateOption.setStartTime(nsj.e(currentTimeMillis, -30));
        hiAggregateOption.setEndTime(currentTimeMillis);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setGroupUnitType(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: kor.16
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                if (sparseArray != null && sparseArray.size() != 0) {
                    kor korVar = kor.this;
                    float a2 = korVar.a(korVar.bPD_(sparseArray)) + 0.0f;
                    if (a2 != 0.0f) {
                        kor.this.d.setRestHeartRateDefault(Math.round(a2));
                    } else {
                        LogUtil.h("Common_HWHealthDataManager", "there is average rest heart rate is zero");
                        kor.this.d.setRestHeartRateDefault(60);
                    }
                    kox.e().e(a2 != 0.0f ? Math.round(a2) : 60);
                } else {
                    LogUtil.h("Common_HWHealthDataManager", "there is no average rest heart rate!", " and ", " errorCode :", Integer.valueOf(i));
                    kor.this.d.setRestHeartRateDefault(60);
                    kox.e().e(60);
                }
                kor.this.p();
            }
        });
    }

    public List<HiHealthData> bPD_(SparseArray<List<HiHealthData>> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (!koq.b(sparseArray.get(1))) {
            List<HiHealthData> list = sparseArray.get(1);
            LogUtil.a("Common_HWHealthDataManager", "average rest heart rate newValue = ", list.toString());
            arrayList.addAll(list);
        }
        if (!koq.b(sparseArray.get(0))) {
            List<HiHealthData> list2 = sparseArray.get(0);
            LogUtil.a("Common_HWHealthDataManager", "average rest heart rate oldValue = ", list2.toString());
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    public float a(List<HiHealthData> list) {
        float f;
        float f2;
        int i = 0;
        if (koq.b(list)) {
            f = 0.0f;
        } else {
            f = 0.0f;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getFloat("HR_SLEEP_REST") > 0.0f) {
                    f2 = hiHealthData.getFloat("HR_SLEEP_REST");
                } else {
                    f2 = hiHealthData.getFloat("HR_REST");
                }
                f += f2;
                i++;
            }
        }
        if (i != 0) {
            return f / i;
        }
        LogUtil.h("Common_HWHealthDataManager", "count IS  ZERO");
        return 0.0f;
    }

    public static List<HiHealthData> bPC_(SparseArray<Object> sparseArray, int i) {
        Object obj = sparseArray.get(i);
        if (obj instanceof List) {
            try {
                return (List) obj;
            } catch (ClassCastException unused) {
                LogUtil.b("Common_HWHealthDataManager", "filterHiHealthDataList, is not List<HiHealthData>");
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    public void b(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr = {257, 258, 259, 260, 262, 264, OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE, OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM, 281, 282};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: kor.18
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    health.compact.a.util.LogUtil.c("Common_HWHealthDataManager", "getSportRecords callback is null");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    iBaseResponseCallback2.d(100001, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (!(sparseArray.get(i) instanceof List)) {
                    iBaseResponseCallback.d(100001, null);
                    return;
                }
                List list = (List) sparseArray.get(i);
                if (koq.b(list)) {
                    health.compact.a.util.LogUtil.c("Common_HWHealthDataManager", "getSportRecords healthDataList none");
                    iBaseResponseCallback.d(100001, null);
                } else {
                    iBaseResponseCallback.d(0, list);
                }
            }
        });
    }

    /* loaded from: classes9.dex */
    public static class a implements HiDataReadResultListener {
        private IBaseResponseCallback b;

        public a(IBaseResponseCallback iBaseResponseCallback) {
            this.b = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.c("Common_HWHealthDataManager", "data=", obj, "errorCode=", Integer.valueOf(i), "anchor", Integer.valueOf(i2));
            if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                if (this.b == null) {
                    LogUtil.h("Common_HWHealthDataManager", "getBloodSugarData callback is null");
                    return;
                }
                if (sparseArray.size() == 0) {
                    this.b.d(100001, null);
                    LogUtil.h("Common_HWHealthDataManager", "map none");
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                arrayList.addAll(kor.bPC_(sparseArray, 2008));
                arrayList.addAll(kor.bPC_(sparseArray, 2009));
                arrayList.addAll(kor.bPC_(sparseArray, 2010));
                arrayList.addAll(kor.bPC_(sparseArray, 2011));
                arrayList.addAll(kor.bPC_(sparseArray, 2012));
                arrayList.addAll(kor.bPC_(sparseArray, 2013));
                arrayList.addAll(kor.bPC_(sparseArray, 2014));
                arrayList.addAll(kor.bPC_(sparseArray, 2015));
                arrayList.addAll(kor.bPC_(sparseArray, 2106));
                arrayList.addAll(kor.bPC_(sparseArray, 2108));
                arrayList.addAll(kor.bPC_(sparseArray, 10001));
                LogUtil.a("Common_HWHealthDataManager", "getBloodSugarData hasData");
                if (arrayList.isEmpty()) {
                    this.b.d(100001, null);
                } else {
                    LogUtil.a("Common_HWHealthDataManager", "getBloodsugarData total count = ", Integer.valueOf(arrayList.size()));
                    this.b.d(0, arrayList);
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.c("Common_HWHealthDataManager", "onResultIntent rest");
        }
    }
}
