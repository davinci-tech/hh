package com.huawei.watchface.api;

import android.content.Context;
import com.huawei.watchface.ac;
import com.huawei.watchface.mvp.model.datatype.BatchReportResp;
import com.huawei.watchface.mvp.model.datatype.ResApplyRecord;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class BatchReportRepository {
    private static final String TAG = "BatchReportRepository";

    public interface BatchReportUpdateListener {
        void onWatchFaceInfoUpdated(WatchResourcesInfo watchResourcesInfo);
    }

    public static void createBatchReport(String str) {
        if (str == null) {
            str = "";
        }
        ArrayList arrayList = new ArrayList();
        ResApplyRecord resApplyRecord = new ResApplyRecord();
        resApplyRecord.setApplyScene("1000013");
        resApplyRecord.setType("1");
        resApplyRecord.setSubType("0");
        resApplyRecord.setHitopID(str);
        arrayList.add(resApplyRecord);
        MembershipRepository.postBatchReport(arrayList, new ResponseListener<BatchReportResp>() { // from class: com.huawei.watchface.api.BatchReportRepository.1
            @Override // com.huawei.watchface.api.ResponseListener
            public void onResponse(BatchReportResp batchReportResp) {
                HwLog.i(BatchReportRepository.TAG, "createBatchReport -- result success");
            }

            @Override // com.huawei.watchface.api.ResponseListener
            public void onError() {
                HwLog.i(BatchReportRepository.TAG, "post batch report is failed.");
            }
        });
    }

    public static void update(Context context, WatchResourcesInfo watchResourcesInfo, BatchReportUpdateListener batchReportUpdateListener) {
        synchronized (BatchReportRepository.class) {
            if (context == null) {
                return;
            }
            WatchResourcesInfo currentWatchFace = HwWatchFaceBtManager.getInstance(context).getCurrentWatchFace();
            boolean isBatchReportCallNeeded = isBatchReportCallNeeded(currentWatchFace, watchResourcesInfo, batchReportUpdateListener);
            HwLog.i(TAG, "update -- isBatchReportCallNeeded:" + isBatchReportCallNeeded);
            if (isBatchReportCallNeeded) {
                String watchInfoId = currentWatchFace.getWatchInfoId();
                String watchInfoVersion = currentWatchFace.getWatchInfoVersion();
                if (ac.a().a(watchInfoId, watchInfoVersion)) {
                    ac.a().c(watchInfoId, watchInfoVersion);
                    ac.a().a(CommonUtils.B());
                }
                createBatchReport(watchInfoId);
            }
        }
    }

    private static boolean isBatchReportCallNeeded(WatchResourcesInfo watchResourcesInfo, WatchResourcesInfo watchResourcesInfo2, BatchReportUpdateListener batchReportUpdateListener) {
        if (watchResourcesInfo == null) {
            HwLog.e(TAG, "isBatchReportCallNeeded() -- currentWatchFace is null");
            return false;
        }
        if (watchResourcesInfo2 == null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder("isBatchReportCallNeeded() -- deviceCurrentWatchFace is null,batchReportUpdateListener is null:");
            sb.append(batchReportUpdateListener == null);
            HwLog.e(str, sb.toString());
            if (batchReportUpdateListener != null) {
                batchReportUpdateListener.onWatchFaceInfoUpdated(watchResourcesInfo);
            }
            return false;
        }
        String str2 = TAG;
        HwLog.i(str2, "isBatchReportCallNeeded deviceCurrentWatchFace watchfaceId:" + watchResourcesInfo2.getWatchInfoId());
        if (watchResourcesInfo2.getWatchInfoId() != null && !watchResourcesInfo2.getWatchInfoId().equals(watchResourcesInfo.getWatchInfoId())) {
            if (batchReportUpdateListener != null) {
                batchReportUpdateListener.onWatchFaceInfoUpdated(watchResourcesInfo);
            }
            return true;
        }
        HwLog.i(str2, "isBatchReportCallNeeded deviceCurrentWatchFace WatchInfoVersion:" + watchResourcesInfo2.getWatchInfoVersion());
        if (watchResourcesInfo2.getWatchInfoVersion() == null || watchResourcesInfo2.getWatchInfoVersion().equals(watchResourcesInfo.getWatchInfoVersion())) {
            return false;
        }
        if (batchReportUpdateListener != null) {
            batchReportUpdateListener.onWatchFaceInfoUpdated(watchResourcesInfo);
        }
        return true;
    }
}
