package defpackage;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class bdi {
    public static void b(int i, int i2, HiAggregateListener hiAggregateListener) {
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(c(i, i2), hiAggregateListener);
    }

    public static HiAggregateOption c(int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("NULL");
        return hiAggregateOption;
    }

    public static void d(List<HiHealthData> list, HealthLifeBean healthLifeBean) {
        if (koq.b(list) || healthLifeBean == null || healthLifeBean.getId() != 14) {
            LogUtil.h("HealthLife_WeightUtils", "refreshDayRecord dataList ", list, " recordBean ", healthLifeBean);
            return;
        }
        int i = nY_(list).get(healthLifeBean.getRecordDay());
        if (healthLifeBean.getComplete() <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(1);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            healthLifeBean.setSyncStatus(0);
            aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
        }
        healthLifeBean.setResult(String.valueOf(i));
    }

    public static void e(final HealthLifeBean healthLifeBean, final ResponseCallback<HealthLifeBean> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_WeightUtils", "refreshDayRecord callback is null");
        } else if (healthLifeBean == null) {
            LogUtil.h("HealthLife_WeightUtils", "refreshDayRecord lifeBean is null");
            responseCallback.onResponse(-1, null);
        } else {
            int recordDay = healthLifeBean.getRecordDay();
            b(recordDay, recordDay, new HiAggregateListener() { // from class: bdi.5
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    bdi.d(list, HealthLifeBean.this);
                    responseCallback.onResponse(0, HealthLifeBean.this);
                }
            });
        }
    }

    public static void nZ_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_WeightUtils", "refreshRecord callback is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_WeightUtils", "refreshRecord recordArray is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_WeightUtils", "refreshRecord recordArray ", sparseArray);
            responseCallback.onResponse(-1, sparseArray);
        } else {
            b(sparseArray.keyAt(0), sparseArray.keyAt(size - 1), new HiAggregateListener() { // from class: bdi.3
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (koq.b(list)) {
                        LogUtil.h("HealthLife_WeightUtils", "refreshRecord dataList ", list);
                        ResponseCallback.this.onResponse(0, bdh.nU_(14, sparseArray, new SparseIntArray()));
                    } else {
                        SparseArray<HealthLifeBean> nU_ = bdh.nU_(14, sparseArray, bdi.nY_(list));
                        LogUtil.a("HealthLife_WeightUtils", "refreshRecord sparseArray ", nU_);
                        ResponseCallback.this.onResponse(0, nU_);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SparseIntArray nY_(List<HiHealthData> list) {
        if (koq.b(list)) {
            return new SparseIntArray();
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (HiHealthData hiHealthData : list) {
            double d = hiHealthData.getDouble("bodyWeight");
            int day = (int) hiHealthData.getDay();
            if (d > 0.0d && day > 0) {
                int i = sparseIntArray.get(day);
                sparseIntArray.append(day, i <= 0 ? 1 : i + 1);
            }
        }
        return sparseIntArray;
    }
}
