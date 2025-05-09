package com.huawei.health.device.wifi.control.claim;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.WeightOfflineDataSelectActivity;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import defpackage.ceo;
import defpackage.cfi;
import defpackage.cgk;
import defpackage.ckc;
import defpackage.ckm;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.csg;
import defpackage.csh;
import defpackage.csi;
import defpackage.dcx;
import defpackage.koq;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public enum ClaimWeightDataManager {
    INSTANCE;

    private static final String BASE_HEALTH_DATA_ACTIVITY = "BaseHealthDataActivity";
    private static final int CLAIM_DATA_CONFICT_FATERATE_FLUCTUATION = 5;
    private static final int CLAIM_DATA_CONFICT_NOT_MATCH = 3;
    private static final int CLAIM_DATA_CONFICT_SIMILAR = 2;
    private static final int CLAIM_DATA_NOT_CONFICT = 1;
    private static final int CLAIM_DATA_OLD_CONFICT = 0;
    public static final String CONFLICT_WEIGHT_DATA_USER_ID = "-1";
    private static final int DEFAULT_SIZE_VALUE = 16;
    private static final int DELETE_BLUETOOTH_CLAIM_DATA_DELAY = 1500;
    private static final int DELETE_DATA_DELAY_TIME = 600;
    private static final int DELETE_INTERVAL = 100;
    public static final int ERROR_UPDATE_DATA_SUCCESS = 0;
    private static final int INDEX_HUNDRED = 100;
    private static final int INDEX_TWO = 2;
    private static final int MSG_DISMISS_PROGRESS_DIALOG = 102;
    private static final int MSG_GET_NEW_CLAIM_WEIGHT_DATA = 3;
    private static final int MSG_GET_NEW_CLAIM_WEIGHT_DATA_SUCCESS = 4;
    private static final int MSG_SHOW_BLUETOOTH_CLAIM_DATA_DIALOG = 6;
    private static final int MSG_START_DELETE_WEIGHT = 5;
    private static final int MSG_START_SYNC_CLAIM_WEIGHT_DATA = 1;
    private static final int MSG_SYNC_CLAIM_DATA_SUCCESS = 2;
    private static final int MSG_UPDATE_PROGRESS_DIALOG = 101;
    private static final long SEVEN_DAY_TIMES = 604800000;
    private static final String SHARE_OLD_DATA_TIME_KEY = "weight_data_old_time";
    private static final String SHARE_RED_OLD_DATA_TIME_KEY = "weight_data_red_old_time";
    public static final String TAG = "ClaimWeightDataManager";
    private transient Map<String, CommBaseCallbackInterface> mCallBacks;
    private b mClaimHandler;
    private CommBaseCallbackInterface mCurrentUserChangedCallback;
    private ArrayList<HiTimeInterval> mDeleteData;
    private e mDeleteHandler;
    private CustomProgressDialog mDeletingDialog;
    private transient CustomProgressDialog.Builder mDeletingDialogBuilder;
    private transient HiDataOperateListener mListener;
    private String mProductId;
    private static final Object LOCK = new Object();
    private static final List<String> SUPPORT_BLUETOOTH_OFFLINE_DEVICE = new ArrayList<String>() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.2
        {
            add("34fa0346-d46c-439d-9cb0-2f696618846b");
            add("33123f39-7fc1-420b-9882-a4b0d6c61100");
            add("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f");
            add("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
            add("b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
            add("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
        }
    };
    private ArrayList<HiHealthData> mClaimDataCatch = new ArrayList<>(16);
    private ArrayList<HiHealthData> mNewClaimDataCatch = new ArrayList<>(16);
    private long mOldDataTime = 0;
    private long mOldRedDataTime = 0;
    private int mDeleteTotalSize = 0;
    private int mCurrentDeleteSize = 0;
    private boolean mIsShowRedTip = false;
    private transient EventBus.ICallback mEventCallback = new EventBus.ICallback() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.1
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar == null) {
                LogUtil.h(ClaimWeightDataManager.TAG, "ClaimWeightDataManager onEvent event is unll");
                return;
            }
            Bundle Kl_ = bVar.Kl_();
            LogUtil.a(ClaimWeightDataManager.TAG, "ClainWeightDataManager event bus event start: ", bVar.e());
            if ("evebus_show_suspected_data_claim_dialog".equals(bVar.e())) {
                if (Kl_ != null) {
                    ClaimWeightDataManager.this.mProductId = Kl_.getString("productId");
                }
                ClaimWeightDataManager.this.queryBluetoothClaimData();
            }
        }
    };

    static /* synthetic */ int access$912(ClaimWeightDataManager claimWeightDataManager, int i) {
        int i2 = claimWeightDataManager.mCurrentDeleteSize + i;
        claimWeightDataManager.mCurrentDeleteSize = i2;
        return i2;
    }

    ClaimWeightDataManager() {
        HandlerThread handlerThread = new HandlerThread("updata_wifi_data");
        handlerThread.start();
        this.mClaimHandler = new b(handlerThread.getLooper());
        this.mCallBacks = new ConcurrentHashMap(16);
        EventBus.d(this.mEventCallback, 0, "evebus_show_suspected_data_claim_dialog");
    }

    public void registerCallBack(String str, CommBaseCallbackInterface commBaseCallbackInterface) {
        Map<String, CommBaseCallbackInterface> map = this.mCallBacks;
        if (map != null) {
            map.put(str, commBaseCallbackInterface);
        } else {
            cpw.e(false, TAG, "registerCallBack mCallBacks is null");
        }
    }

    public void registerUserChangedCallBack(String str, CommBaseCallbackInterface commBaseCallbackInterface) {
        LogUtil.a(TAG, "registerCallBack the key is BaseHealthDataActivity");
        setCurrentUserChangedCallback(commBaseCallbackInterface);
        registerCallBack(str, commBaseCallbackInterface);
    }

    private void setCurrentUserChangedCallback(CommBaseCallbackInterface commBaseCallbackInterface) {
        this.mCurrentUserChangedCallback = commBaseCallbackInterface;
    }

    public void unRegisterCallBack(String str) {
        Map<String, CommBaseCallbackInterface> map = this.mCallBacks;
        if (map != null && map.containsKey(str)) {
            this.mCallBacks.remove(str);
        } else {
            cpw.e(false, TAG, "unRegisterCallBack mCallBacks is null or not contain key");
        }
    }

    public void unRegisterrUserChangedCallBack(String str) {
        LogUtil.a(TAG, "unRegisterCallBack the key is BaseHealthDataActivity");
        setCurrentUserChangedCallback(null);
        unRegisterCallBack(str);
    }

    public void onCurrentUserChanged(boolean z) {
        CommBaseCallbackInterface commBaseCallbackInterface = this.mCurrentUserChangedCallback;
        if (commBaseCallbackInterface != null) {
            commBaseCallbackInterface.onResult(0, BASE_HEALTH_DATA_ACTIVITY, Boolean.valueOf(z));
        }
    }

    public ArrayList<HiHealthData> getClaimDataCatch() {
        ArrayList<HiHealthData> arrayList;
        synchronized (LOCK) {
            ArrayList<HiHealthData> arrayList2 = this.mClaimDataCatch;
            if (arrayList2 != null) {
                cpw.a(false, TAG, "getClaimDataCatch Size ,", Integer.valueOf(arrayList2.size()));
            }
            arrayList = this.mClaimDataCatch;
        }
        return arrayList;
    }

    public Map<cfi, ArrayList<csh>> getFatRateFluctuationDataBean() {
        HashMap hashMap = new HashMap(16);
        List<cfi> mainAllUser = MultiUsersManager.INSTANCE.getMainAllUser();
        HashMap hashMap2 = new HashMap(16);
        for (cfi cfiVar : mainAllUser) {
            hashMap2.put(cfiVar.i(), cfiVar);
        }
        synchronized (LOCK) {
            Iterator<HiHealthData> it = this.mClaimDataCatch.iterator();
            while (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getInt("conflictFlag") == 5) {
                    List<csi> parseConflictUserInfoList = parseConflictUserInfoList(next.getMetaData());
                    if (koq.b(parseConflictUserInfoList)) {
                        LogUtil.h(TAG, "getFatRateFluctuationDataBean conflictUserInfoList is empty");
                    } else {
                        String s = parseConflictUserInfoList.get(0).s();
                        if ("0".equals(s)) {
                            s = MultiUsersManager.INSTANCE.getMainUser().i();
                        }
                        if (hashMap2.containsKey(s) && hashMap2.get(s) != null) {
                            fillFluctuationMap(hashMap, next, parseConflictUserInfoList, (cfi) hashMap2.get(s));
                        }
                        LogUtil.h(TAG, "no user for uuid ", s);
                    }
                }
            }
        }
        return hashMap;
    }

    private void fillFluctuationMap(Map<cfi, ArrayList<csh>> map, HiHealthData hiHealthData, List<csi> list, cfi cfiVar) {
        csh cshVar = new csh(hiHealthData);
        cshVar.d(list);
        if (map.containsKey(cfiVar)) {
            map.get(cfiVar).add(cshVar);
            return;
        }
        ArrayList<csh> arrayList = new ArrayList<>(16);
        arrayList.add(cshVar);
        map.put(cfiVar, arrayList);
    }

    public ArrayList<csh> getSimilarWeightDataBean() {
        List<cfi> mainAllUser = MultiUsersManager.INSTANCE.getMainAllUser();
        HashMap hashMap = new HashMap(16);
        for (cfi cfiVar : mainAllUser) {
            hashMap.put(cfiVar.i(), cfiVar);
        }
        ArrayList<csh> arrayList = new ArrayList<>(16);
        synchronized (LOCK) {
            Iterator<HiHealthData> it = this.mClaimDataCatch.iterator();
            while (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getInt("conflictFlag") == 2) {
                    csh cshVar = new csh(next);
                    cshVar.d(parseConflictUserInfoList(next.getMetaData()));
                    arrayList.add(cshVar);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<csh> getNotMatchDataBean() {
        ArrayList<csh> arrayList;
        synchronized (LOCK) {
            arrayList = new ArrayList<>(16);
            Iterator<HiHealthData> it = this.mClaimDataCatch.iterator();
            while (it.hasNext()) {
                HiHealthData next = it.next();
                int i = next.getInt("conflictFlag");
                if (i == 3 || i == 0) {
                    arrayList.add(new csh(next));
                }
            }
        }
        return arrayList;
    }

    private List<csi> parseConflictUserInfoList(String str) {
        List<csi> arrayList;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "parseConflictUserInfoList metaData is null");
            return new ArrayList(16);
        }
        try {
            arrayList = (List) new Gson().fromJson(str, new TypeToken<List<csi>>() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.5
            }.getType());
        } catch (JsonSyntaxException | IllegalStateException unused) {
            LogUtil.b(TAG, "parseConflictUserInfoList catch JsonSyntaxException or IllegalStateException");
            arrayList = new ArrayList<>(16);
        }
        if (koq.b(arrayList)) {
            LogUtil.h(TAG, "parseConflictUserInfoList conflictUserInfoList is empty");
        }
        return arrayList;
    }

    public boolean isShowRedTip() {
        int size = getNewClaimDataCatch().size();
        cpw.a(false, TAG, " mIsShowRedTip", Boolean.valueOf(this.mIsShowRedTip), " new device size:", Integer.valueOf(size));
        if (size > 0) {
            return this.mIsShowRedTip;
        }
        return false;
    }

    public void initShowTip() {
        ArrayList<HiHealthData> arrayList = this.mNewClaimDataCatch;
        if (arrayList != null) {
            if (arrayList.size() > 0) {
                setOldDataTime(this.mNewClaimDataCatch.get(0).getStartTime());
            } else {
                setOldDataTime(this.mOldDataTime);
            }
            initShowRed();
            this.mNewClaimDataCatch.clear();
            return;
        }
        cpw.e(false, TAG, "initShowTip mNewClaimDataCatch is null");
    }

    public void initShowRed() {
        LogUtil.a(TAG, "initShowRed mNewClaimDataCatch ", this.mNewClaimDataCatch, " mOldRedDataTime ", Long.valueOf(this.mOldRedDataTime), " mIsShowRedTip ", Boolean.valueOf(this.mIsShowRedTip));
        ArrayList<HiHealthData> arrayList = this.mNewClaimDataCatch;
        if (arrayList != null && arrayList.size() > 0) {
            setRedOldDataTime(this.mNewClaimDataCatch.get(0).getStartTime());
        } else {
            setRedOldDataTime(this.mOldRedDataTime);
        }
        this.mIsShowRedTip = false;
    }

    private void updateNewClaimData(List<HiHealthData> list) {
        this.mNewClaimDataCatch.clear();
        this.mNewClaimDataCatch.addAll(list);
        if (this.mNewClaimDataCatch.size() > 0 && this.mNewClaimDataCatch.get(0).getStartTime() > this.mOldRedDataTime) {
            long startTime = this.mNewClaimDataCatch.get(0).getStartTime();
            this.mOldRedDataTime = startTime;
            cpw.a(false, TAG, " updateNewClaimData mOldRedDataTime ", Long.valueOf(startTime));
            this.mIsShowRedTip = true;
        }
        if (this.mNewClaimDataCatch.size() > 0 || !this.mIsShowRedTip) {
            return;
        }
        this.mIsShowRedTip = false;
    }

    private void updateClaimData(List<HiHealthData> list) {
        if (list.size() <= 0) {
            Intent intent = new Intent();
            intent.putExtra("isDelUser", false);
            EventBus.d(new EventBus.b("evebus_weight_measure_notification", intent));
        }
        cpw.a(false, TAG, "data.size():", Integer.valueOf(list.size()));
        synchronized (LOCK) {
            this.mClaimDataCatch.clear();
            this.mClaimDataCatch.addAll(list);
        }
    }

    public List<HiHealthData> getNewClaimDataCatch() {
        return this.mNewClaimDataCatch;
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                cpw.e(false, ClaimWeightDataManager.TAG, "ClaimHandler handleMessage msg is null.");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                ClaimWeightDataManager.this.syncClaimData();
                return;
            }
            if (i == 2) {
                obtainMessage(3).sendToTarget();
                return;
            }
            if (i == 3) {
                ClaimWeightDataManager.this.getShowMeasureData();
                return;
            }
            if (i == 4) {
                ClaimWeightDataManager.this.sendGetNewClaimDataComplete();
            } else if (i == 6) {
                ClaimWeightDataManager claimWeightDataManager = ClaimWeightDataManager.this;
                claimWeightDataManager.showBluetoothClaimDataDialog(claimWeightDataManager.mProductId);
            } else {
                cpw.a(false, ClaimWeightDataManager.TAG, "ClaimHandler what is error");
            }
        }
    }

    public void startSync() {
        b bVar = this.mClaimHandler;
        if (bVar == null) {
            cpw.e(false, TAG, "startSync mClaimHandler is null");
        } else {
            bVar.obtainMessage(1).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGetNewClaimDataComplete() {
        synchronized (LOCK) {
            Map<String, CommBaseCallbackInterface> map = this.mCallBacks;
            if (map == null) {
                LogUtil.h(TAG, "sendGetNewClaimDataComplete mCallBacks is null");
                return;
            }
            for (Map.Entry<String, CommBaseCallbackInterface> entry : map.entrySet()) {
                if (entry == null) {
                    LogUtil.h(TAG, "sendGetNewClaimDataComplete entry is null");
                } else {
                    CommBaseCallbackInterface value = entry.getValue();
                    if (value == null) {
                        LogUtil.h(TAG, "sendGetNewClaimDataComplete commBaseCallbackInterface is null");
                    } else {
                        List<HiHealthData> newClaimDataCatch = getNewClaimDataCatch();
                        if (newClaimDataCatch == null) {
                            LogUtil.h(TAG, "sendGetNewClaimDataComplete newClaimDataCatchList is null");
                        } else {
                            value.onResult(0, "get new claim complete", Integer.valueOf(newClaimDataCatch.size()));
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncClaimData() {
        long currentTimeMillis = System.currentTimeMillis();
        String[] strArr = {BleConstants.WEIGHT_KEY};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(currentTimeMillis - 604800000, currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("-1");
        hiAggregateOption.setConstantsKey(strArr);
        initOldDataTime();
        initRedOldDataTime();
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                cpw.a(false, ClaimWeightDataManager.TAG, "syncClaimData errorCode =", Integer.valueOf(i));
                if (list == null || list.size() == 0) {
                    synchronized (ClaimWeightDataManager.LOCK) {
                        ClaimWeightDataManager.this.mClaimDataCatch.clear();
                    }
                    ClaimWeightDataManager.this.mClaimHandler.obtainMessage(2).sendToTarget();
                    Intent intent = new Intent();
                    intent.putExtra("isDelUser", false);
                    EventBus.d(new EventBus.b("evebus_weight_measure_notification", intent));
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArray("claimWeightData", new HiHealthData[0]);
                    EventBus.d(new EventBus.b("weight_message_from_producer", bundle));
                    cpw.a(false, ClaimWeightDataManager.TAG, "syncClaimData data is null");
                    return;
                }
                cpw.a(false, ClaimWeightDataManager.TAG, " callback dataList Size :", Integer.valueOf(list.size()));
                ClaimWeightDataManager.this.processWeightDataList(list);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                cpw.a(true, ClaimWeightDataManager.TAG, "syncClaimData onResultIntent called");
                ClaimWeightDataManager.this.mClaimHandler.obtainMessage(2).sendToTarget();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processWeightDataList(List<HiHealthData> list) {
        int i;
        ArrayList arrayList = new ArrayList(16);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getDouble("bodyWeight") > 0.0d && ((i = hiHealthData.getInt("conflictFlag")) > 1 || i == 0)) {
                hiHealthData.setDeviceUuid(hiHealthData.getString("device_uniquecode"));
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a(TAG, " callback healthData :", Integer.valueOf(list.size()), " dataList is :", list, " weightDataList size is: ", Integer.valueOf(arrayList.size()), " weightDataList is : ", arrayList);
        updateClaimData(arrayList);
        this.mClaimHandler.obtainMessage(2).sendToTarget();
        Bundle bundle = new Bundle();
        bundle.putParcelableArray("claimWeightData", arrayList2PrimitiveArray(arrayList));
        EventBus.d(new EventBus.b("weight_message_from_producer", bundle));
    }

    private HiHealthData[] arrayList2PrimitiveArray(List<HiHealthData> list) {
        int i = 0;
        if (koq.b(list)) {
            return new HiHealthData[0];
        }
        HiHealthData[] hiHealthDataArr = new HiHealthData[list.size()];
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            hiHealthDataArr[i] = it.next();
            i++;
        }
        return hiHealthDataArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getShowMeasureData() {
        cpw.a(false, TAG, "getShowMeasureData in mOldDataTime;", Long.valueOf(this.mOldDataTime));
        long j = this.mOldDataTime;
        ArrayList<HiHealthData> claimDataCatch = getClaimDataCatch();
        ArrayList arrayList = new ArrayList(16);
        synchronized (LOCK) {
            Iterator<HiHealthData> it = claimDataCatch.iterator();
            while (it.hasNext()) {
                HiHealthData next = it.next();
                if (next != null && next.getStartTime() > j) {
                    cpw.c(true, TAG, "getShowMeasureData add data ", next.toString());
                    arrayList.add(next);
                }
            }
        }
        cpw.a(false, TAG, "getShowMeasureData new device", Integer.valueOf(arrayList.size()));
        updateNewClaimData(arrayList);
        this.mClaimHandler.obtainMessage(4).sendToTarget();
    }

    private void initOldDataTime() {
        this.mOldDataTime = new DeviceCloudSharePreferencesManager(cpp.a()).d(SHARE_OLD_DATA_TIME_KEY);
    }

    private void setOldDataTime(long j) {
        new DeviceCloudSharePreferencesManager(cpp.a()).b(SHARE_OLD_DATA_TIME_KEY, j);
    }

    private void setRedOldDataTime(long j) {
        new DeviceCloudSharePreferencesManager(cpp.a()).b(SHARE_RED_OLD_DATA_TIME_KEY, j);
    }

    private void initRedOldDataTime() {
        this.mOldRedDataTime = new DeviceCloudSharePreferencesManager(cpp.a()).d(SHARE_RED_OLD_DATA_TIME_KEY);
    }

    public void claimWeightData(ArrayList<csh> arrayList, cfi cfiVar, String str, WeightInsertStatusCallback weightInsertStatusCallback) {
        cpw.a(false, TAG, "claimWeightData in");
        if (arrayList != null && cfiVar != null) {
            if (arrayList.size() > 0 && weightInsertStatusCallback != null) {
                setConflictFlag(arrayList);
                this.mClaimHandler.post(new csg(arrayList, cfiVar, str, weightInsertStatusCallback));
            } else {
                Object[] objArr = new Object[5];
                objArr[0] = "claimWeightData data or callback is null";
                objArr[1] = " data:";
                objArr[2] = Boolean.valueOf(arrayList == null);
                objArr[3] = " : callback:";
                objArr[4] = Boolean.valueOf(weightInsertStatusCallback == null);
                cpw.a(false, TAG, objArr);
            }
        } else {
            Object[] objArr2 = new Object[5];
            objArr2[0] = "claimWeightData data or user is null";
            objArr2[1] = " data:";
            objArr2[2] = Boolean.valueOf(arrayList == null);
            objArr2[3] = " : user:";
            objArr2[4] = Boolean.valueOf(cfiVar == null);
            cpw.a(false, TAG, objArr2);
        }
        cpw.a(false, TAG, "claimWeightData out");
    }

    private void setConflictFlag(ArrayList<csh> arrayList) {
        Iterator<csh> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().c().putInt("conflictFlag", 1);
        }
    }

    public void deleteWeightData(ArrayList<csh> arrayList, Context context, HiDataOperateListener hiDataOperateListener) {
        if (context == null) {
            cpw.a(false, TAG, "deleteWeightData context is null");
            return;
        }
        if (arrayList == null || hiDataOperateListener == null) {
            cpw.a(false, TAG, "deleteWeightData dataBeans or listener is null");
            return;
        }
        this.mListener = hiDataOperateListener;
        this.mDeleteHandler = new e(context.getMainLooper());
        ArrayList<HiTimeInterval> b2 = csg.b(arrayList);
        this.mDeleteData = b2;
        this.mDeleteTotalSize = b2.size();
        this.mCurrentDeleteSize = 0;
        showProgressDialog(context);
        if (this.mDeleteTotalSize >= 100) {
            this.mDeleteHandler.sendEmptyMessageDelayed(5, 600L);
        } else {
            sendDeleteHandler(5);
        }
    }

    public void deleteWeightData(final List<HiTimeInterval> list, final HiDataOperateListener hiDataOperateListener) {
        csg.d(list, new HiDataOperateListener() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                boolean z;
                if (obj == null) {
                    LogUtil.h(ClaimWeightDataManager.TAG, "deleteWeightData obj is null");
                    return;
                }
                if (obj instanceof Boolean) {
                    z = ((Boolean) obj).booleanValue();
                    LogUtil.a(ClaimWeightDataManager.TAG, "deleteWeightData isFlag = ", Boolean.valueOf(z));
                } else {
                    z = false;
                }
                List list2 = list;
                if (list2 != null) {
                    LogUtil.a(ClaimWeightDataManager.TAG, "deleteWeightData intervalList:", Integer.valueOf(list2.size()));
                    ClaimWeightDataManager.access$912(ClaimWeightDataManager.this, list.size());
                }
                if (ClaimWeightDataManager.this.mDeleteTotalSize >= 100) {
                    ClaimWeightDataManager.this.sendDeleteHandler(101);
                }
                if (ClaimWeightDataManager.this.mCurrentDeleteSize < ClaimWeightDataManager.this.mDeleteTotalSize) {
                    ClaimWeightDataManager.this.sendDeleteHandler(5);
                    return;
                }
                Object[] objArr = new Object[2];
                objArr[0] = "deleteWeightData complete listener ";
                objArr[1] = Boolean.valueOf(hiDataOperateListener != null);
                LogUtil.a(ClaimWeightDataManager.TAG, objArr);
                HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                if (hiDataOperateListener2 != null) {
                    hiDataOperateListener2.onResult(0, Boolean.valueOf(z));
                }
                ClaimWeightDataManager.this.sendDeleteHandler(5);
            }
        });
    }

    private void showProgressDialog(Context context) {
        if (context == null) {
            cpw.a(false, TAG, "showProgressDialog context is null");
            return;
        }
        if (this.mDeletingDialog == null) {
            this.mDeletingDialog = new CustomProgressDialog(context);
            this.mDeletingDialogBuilder = new CustomProgressDialog.Builder(context);
            this.mDeletingDialogBuilder.d(context.getResources().getString(dcx.e("IDS_hw_health_show_healthdata_deleteing")));
            CustomProgressDialog e2 = this.mDeletingDialogBuilder.e();
            this.mDeletingDialog = e2;
            e2.setCanceledOnTouchOutside(false);
            this.mDeletingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager.10
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 4;
                }
            });
        }
        if (!isActivityLiving(context) || this.mDeletingDialog.isShowing()) {
            return;
        }
        cpw.a(false, TAG, "showProgressDialog show");
        this.mDeletingDialog.show();
        this.mDeleteHandler.sendEmptyMessage(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDeleteDialog() {
        CustomProgressDialog customProgressDialog = this.mDeletingDialog;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            cpw.a(false, TAG, "showProgressDialog dismiss");
            this.mDeletingDialog.dismiss();
            this.mDeletingDialog = null;
            this.mDeletingDialogBuilder = null;
        }
        this.mListener = null;
        this.mDeleteHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDeleteHandler(int i) {
        e eVar = this.mDeleteHandler;
        if (eVar != null) {
            eVar.sendEmptyMessage(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDataInProgress(HiDataOperateListener hiDataOperateListener) {
        int i = this.mDeleteTotalSize;
        if (i >= 100) {
            int i2 = this.mCurrentDeleteSize;
            if (i2 < i) {
                if (i - i2 >= 50) {
                    i = i2 + 50;
                }
                cpw.a(false, TAG, "deleteDataInProgress mCurrentDeleteSize:", Integer.valueOf(i2), " deleteNum:", Integer.valueOf(i));
                deleteWeightData(this.mDeleteData.subList(this.mCurrentDeleteSize, i), hiDataOperateListener);
                return;
            }
            sendDeleteHandler(102);
            return;
        }
        if (this.mCurrentDeleteSize < i) {
            cpw.a(false, TAG, "deleteDataInProgress not show dialog");
            deleteWeightData(this.mDeleteData, hiDataOperateListener);
        } else {
            sendDeleteHandler(102);
        }
    }

    /* loaded from: classes7.dex */
    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                cpw.e(false, ClaimWeightDataManager.TAG, "DeleteHandler handleMessage msg is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 5) {
                ClaimWeightDataManager claimWeightDataManager = ClaimWeightDataManager.this;
                claimWeightDataManager.deleteDataInProgress(claimWeightDataManager.mListener);
            } else if (i == 101) {
                ClaimWeightDataManager.this.updateProgress();
            } else if (i == 102) {
                ClaimWeightDataManager.this.dismissDeleteDialog();
            } else {
                cpw.a(false, ClaimWeightDataManager.TAG, "DeleteHandler what is error:", Integer.valueOf(message.what));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        if (this.mDeletingDialogBuilder != null) {
            cpw.a(false, TAG, "updateProgress mCurrentDeleteSize:", Integer.valueOf(this.mCurrentDeleteSize), " mDeleteTotalSize:", Integer.valueOf(this.mDeleteTotalSize));
            int i = this.mDeleteTotalSize;
            if (i != 0) {
                int i2 = (this.mCurrentDeleteSize * 100) / i;
                this.mDeletingDialogBuilder.d(i2);
                this.mDeletingDialogBuilder.c(i2);
            }
        }
    }

    public void queryBluetoothClaimData() {
        LogUtil.a(TAG, "ClaimWeightDataManager queryBluetoothClaimData");
        if (this.mProductId == null) {
            if (ceo.d().e("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", false) != null) {
                this.mProductId = "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4";
            } else if (ceo.d().e("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", false) != null) {
                this.mProductId = "b29df4e3-b1f7-4e40-960d-4cfb63ccca05";
            } else if (ceo.d().e("e835d102-af95-48a6-ae13-2983bc06f5c0", false) != null) {
                this.mProductId = "e835d102-af95-48a6-ae13-2983bc06f5c0";
            } else if (ceo.d().e("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", false) != null) {
                this.mProductId = "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4";
            } else if (ceo.d().e("c943c933-442e-4c34-bcd0-66597f24aaed", false) != null) {
                this.mProductId = "c943c933-442e-4c34-bcd0-66597f24aaed";
            } else {
                LogUtil.h(TAG, "ClaimWeightDataManager queryBluetoothClaimData mProductId is null");
                return;
            }
        }
        b bVar = this.mClaimHandler;
        if (bVar == null) {
            LogUtil.h(TAG, "ClaimWeightDataManager queryBluetoothClaimData claimHandler is null");
            return;
        }
        bVar.removeMessages(6);
        LogUtil.a(TAG, "ClaimWeightDataManager queryBluetoothClaimData claimHandler show claim data dialog");
        this.mClaimHandler.sendEmptyMessageDelayed(6, ProfileExtendConstants.TIME_OUT);
    }

    private boolean hasSuspectedWeightData() {
        String i;
        if (!Utils.i()) {
            i = cgk.d().c();
        } else {
            cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
            if (currentUser == null) {
                LogUtil.h(TAG, "ClaimWeightDataManager hasSuspectedWeightData currentUserId is null");
                return false;
            }
            i = currentUser.i();
        }
        ArrayList<ckm> b2 = ckc.a(BaseApplication.getContext()).b(i);
        if (koq.b(b2)) {
            LogUtil.h(TAG, "ClaimWeightDataManager hasSuspectedWeightData weightAndFatRateDatas is null");
            return false;
        }
        Iterator<ckm> it = b2.iterator();
        while (it.hasNext()) {
            if (it.next().q()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBluetoothClaimDataDialog(String str) {
        Fragment a2;
        LogUtil.a(TAG, "ClaimWeightDataManager showBluetoothClaimDataDialog ", str);
        if (str == null) {
            LogUtil.h(TAG, "ClaimWeightDataManager productId is null");
            return;
        }
        if (!SUPPORT_BLUETOOTH_OFFLINE_DEVICE.contains(str)) {
            LogUtil.h(TAG, "productId is not huawei bluetooth device");
            return;
        }
        if (!hasSuspectedWeightData()) {
            LogUtil.a(TAG, "no suspectedWeightData ...");
            return;
        }
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h(TAG, "ClaimWeightDataManager showBluetoothClaimDataDialog curContext is null");
            return;
        }
        String simpleName = activity.getClass().getSimpleName();
        String simpleName2 = (!(activity instanceof DeviceMainActivity) || (a2 = ((DeviceMainActivity) activity).a()) == null) ? "" : a2.getClass().getSimpleName();
        LogUtil.a(TAG, "curFragmentName is ", simpleName2, "current context name is", simpleName);
        if ("H5ProWebViewActivity".equals(simpleName) || BASE_HEALTH_DATA_ACTIVITY.equals(simpleName) || "HagridDeviceManagerFragment".equals(simpleName2) || "WeightOfflineDataSelectActivity".equals(simpleName)) {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), WeightOfflineDataSelectActivity.class.getName());
            intent.putExtra("productId", str);
            intent.addFlags(536870912);
            if (BaseApplication.getActivity() != null) {
                try {
                    BaseApplication.getActivity().startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b(TAG, "showBluetoothClaimDataDialog ActivityNotFoundException");
                    return;
                }
            }
            LogUtil.h(TAG, "showBluetoothClaimDataDialog activity is null");
            return;
        }
        LogUtil.a(TAG, "cur context or cur fragment is not right");
    }

    private boolean isActivityLiving(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                return true;
            }
            LogUtil.h(TAG, "isActivityLiving: activity is isFinishing | isDestroyed.");
            return false;
        }
        LogUtil.h(TAG, "isActivityLiving: context isn't Activity.");
        return false;
    }
}
