package com.huawei.health.sportservice.manager;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.SourceToProducer;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fhs;
import defpackage.koq;
import defpackage.lau;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public class SportLinker implements SportLifecycle {
    public static final String TAG = "SportService_SportLinker";
    private boolean mIsNeedMerge;
    private boolean mIsSendCalorieToDevice;
    private List<Integer> mMergeDataSuccessList;
    private final HashMap<String, SourceToProducer> mBaseProducerMap = new HashMap<>();
    private final HiDataOperateListener mOperateListener = new HiDataOperateListener() { // from class: com.huawei.health.sportservice.manager.SportLinker$$ExternalSyntheticLambda0
        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public final void onResult(int i, Object obj) {
            SportLinker.lambda$new$0(i, obj);
        }
    };
    private final HiSubscribeListener mMergeDataSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportLinker.1
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                ReleaseLogUtil.e(SportLinker.TAG, "mMergeDataSubscribeListener onResult successList");
                SportLinker.this.mMergeDataSuccessList = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.h(SportLinker.TAG, "onChange mergeData HiHealthData is null!");
                return;
            }
            int intValue = hiHealthData.getIntValue();
            if (i == 22) {
                SportLinker.this.updateSourceData("STEP_DATA", Integer.valueOf(intValue));
            } else if (i == 24) {
                SportLinker.this.updateSourceData("CALORIES_DATA", Integer.valueOf(intValue));
            }
        }
    };

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
    }

    static /* synthetic */ void lambda$new$0(int i, Object obj) {
        if (i != 0) {
            LogUtil.a(TAG, "insertRealTimeHiHealthData onResult:  ", Integer.valueOf(i));
        }
    }

    public void registerProducer(String str, SourceToProducer sourceToProducer) {
        LogUtil.a(TAG, "registerProducer", str);
        this.mBaseProducerMap.put(str, sourceToProducer);
    }

    public void updateSourceData(String str, String str2, Object obj) {
        if (this.mIsNeedMerge && str2.equals("STEP_DATA")) {
            needMerge(str, obj);
        } else if (this.mIsSendCalorieToDevice && str2.equals("CALORIES_DATA")) {
            insertStepDataIntoHiHealthLib(((Integer) obj).intValue(), str, HiHealthStatusCodes.TRY_AGAIN_ERROR);
        } else {
            updateSourceData(str2, obj);
        }
    }

    private void needMerge(String str, Object obj) {
        insertStepDataIntoHiHealthLib(((Integer) obj).intValue(), str, HiHealthStatusCodes.UNSUPPORTED_PLATFORM_ERROR);
    }

    protected void insertStepDataIntoHiHealthLib(int i, String str, int i2) {
        int status = BaseSportManager.getInstance().getStatus();
        if (status == 2 || status == 3) {
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(i2);
        hiHealthData.setStartTime(System.currentTimeMillis());
        hiHealthData.setEndTime(System.currentTimeMillis());
        hiHealthData.setValue(i);
        hiHealthData.setDataSource(str);
        hiHealthData.setOwnerId(0);
        hiHealthData.putString("realtime_merge_mode", "incrementMode");
        hiHealthData.setDeviceUuid(fhs.c(BaseApplication.e()));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(BaseApplication.e()).insertRealTimeHiHealthData(hiDataInsertOption, this.mOperateListener);
    }

    public void updateSourceData(String str, Object obj) {
        if (this.mBaseProducerMap.containsKey(str)) {
            ((SourceToProducer) Objects.requireNonNull(this.mBaseProducerMap.get(str))).onSourceDataChanged(obj);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        HiHealthApi d;
        int sportType = BaseSportManager.getInstance().getSportType();
        if (sportType == 264 || sportType == 281) {
            this.mIsNeedMerge = true;
        }
        ArrayList arrayList = new ArrayList();
        if (sportType == 264) {
            boolean i = lau.d().i();
            this.mIsSendCalorieToDevice = i;
            if (i) {
                arrayList.add(24);
            }
        }
        if (this.mIsNeedMerge) {
            arrayList.add(22);
        }
        if (!koq.c(arrayList) || (d = HiHealthManager.d(BaseApplication.e())) == null) {
            return;
        }
        ReleaseLogUtil.e(TAG, "subscribeHiHealthData, hiHealthApi is not null");
        d.subscribeHiHealthData(arrayList, this.mMergeDataSubscribeListener);
    }

    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        unregMergeDataListener(BaseApplication.e());
    }

    private void unregMergeDataListener(Context context) {
        List<Integer> list = this.mMergeDataSuccessList;
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a(TAG, "unregMergeDataListener");
        HiHealthManager.d(context).unSubscribeHiHealthData(this.mMergeDataSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.health.sportservice.manager.SportLinker.2
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a(SportLinker.TAG, "unregMergeDataListener isSuccess = ", Boolean.valueOf(z));
            }
        });
    }
}
