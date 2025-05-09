package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class kjb implements SyncStrategy {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public boolean isNeedSync(DeviceInfo deviceInfo, long j) {
        if (!cwi.c(deviceInfo, 154)) {
            return false;
        }
        long d = kiz.d(SyncStrategy.KEY_INSERT_LAST_SYNC_TIME);
        if (BaseApplication.j()) {
            j = 1000;
        }
        if (!ScreenUtil.a()) {
            j = 180000;
        }
        return System.currentTimeMillis() - d > j;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public List<HiHealthData> fetchData(List<HiAggregateOption> list) {
        if (CollectionUtils.d(list)) {
            return new ArrayList(0);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthDataEx(list, new HiAggregateListenerEx() { // from class: kjb.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                ReleaseLogUtil.e("DEVMGR_InsertSuccessSyncStrategy", "fetchData onResult() errCode=", Integer.valueOf(i));
                if (i != 0) {
                    return;
                }
                for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                    List<HiHealthData> valueAt = sparseArray.valueAt(i3);
                    if (valueAt != null) {
                        arrayList.addAll(valueAt);
                    }
                }
                countDownLatch.countDown();
                LogUtil.a("DEVMGR_InsertSuccessSyncStrategy", "fetchData onResult() result.size=", Integer.valueOf(sparseArray.size()));
                LogUtil.c("DEVMGR_InsertSuccessSyncStrategy", "fetchData onResult() result = ", HiJsonUtil.e(sparseArray));
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.c("DEVMGR_InsertSuccessSyncStrategy", "fetchData() timeout");
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("DEVMGR_InsertSuccessSyncStrategy", "fetchData() interruptedException");
        }
        return new ArrayList(arrayList);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public List<cvu> preProcess(List<HiHealthData> list) {
        ReleaseLogUtil.e("DEVMGR_InsertSuccessSyncStrategy", "preProcessing rawDatas=", kiz.a(list));
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                arrayList.addAll(transAggregateToRead(hiHealthData));
            }
        }
        return new ArrayList(kiz.d(arrayList, SyncStrategy.SRC_PKG_NAME, SyncStrategy.WEAR_PKG_NAME));
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public void syncDataToDevice(DeviceInfo deviceInfo, List<cvu> list) {
        if (CollectionUtils.d(list)) {
            LogUtil.a("DEVMGR_InsertSuccessSyncStrategy", "syncDataToDevice() skipped sync by empty list");
            return;
        }
        int i = 0;
        for (cvu cvuVar : list) {
            LogUtil.c("DEVMGR_InsertSuccessSyncStrategy", "syncDataToDevice() onSending=", HiJsonUtil.e(cvuVar));
            if (!cuk.b().sendSamplePointCommand(deviceInfo, cvuVar, "DEVMGR_InsertSuccessSyncStrategy")) {
                ReleaseLogUtil.c("DEVMGR_InsertSuccessSyncStrategy", "syncDataToDevice() send failed");
                return;
            }
            i++;
        }
        LogUtil.a("DEVMGR_InsertSuccessSyncStrategy", "syncDataToDevice() sent (", Integer.valueOf(i), ") samplePoints");
        if (i > 0) {
            kiz.a(SyncStrategy.KEY_INSERT_LAST_SYNC_TIME);
        }
    }
}
