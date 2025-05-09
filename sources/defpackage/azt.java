package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class azt {
    public static void a(int i, int i2, HiAggregateListener hiAggregateListener) {
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(b(i, i2), hiAggregateListener);
    }

    public static HiAggregateOption b(int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()});
        hiAggregateOption.setConstantsKey(new String[]{"breathTrain"});
        hiAggregateOption.setAggregateType(2);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public static void e(List<HiHealthData> list, HealthLifeBean healthLifeBean) {
        if (koq.b(list) || healthLifeBean == null || healthLifeBean.getId() != 5) {
            LogUtil.h("HealthLife_BreathUtils", "refreshDayRecord dataList ", list, " recordBean ", healthLifeBean);
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HealthLife_BreathUtils", "refreshDayRecord hiHealthData is null");
            } else if (azi.b(hiHealthData) != recordDay) {
                LogUtil.h("HealthLife_BreathUtils", "refreshDayRecord hiHealthData ", hiHealthData, " recordDay ", Integer.valueOf(recordDay));
            } else {
                e(hiHealthData, healthLifeBean);
                return;
            }
        }
    }

    public static void e(final HealthLifeBean healthLifeBean, final ResponseCallback<HealthLifeBean> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_BreathUtils", "refreshDayRecord callback is null");
        } else if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BreathUtils", "refreshDayRecord lifeBean is null");
            responseCallback.onResponse(-1, null);
        } else {
            int recordDay = healthLifeBean.getRecordDay();
            a(recordDay, recordDay, new HiAggregateListener() { // from class: azt.2
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    azt.e(list, HealthLifeBean.this);
                    responseCallback.onResponse(0, HealthLifeBean.this);
                }
            });
        }
    }

    public static void mp_(final SparseArray<HealthLifeBean> sparseArray, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_BreathUtils", "refreshRecord callback is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_BreathUtils", "refreshRecord recordArray is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_BreathUtils", "refreshRecord recordArray ", sparseArray);
            responseCallback.onResponse(-1, sparseArray);
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        bbr.mN_(sparseArray, new ResponseCallback() { // from class: azx
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                countDownLatch.countDown();
            }
        });
        a(sparseArray.keyAt(0), sparseArray.keyAt(size - 1), new HiAggregateListener() { // from class: azt.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    LogUtil.h("HealthLife_BreathUtils", "refreshRecord dataList ", list);
                    countDownLatch.countDown();
                    return;
                }
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData == null) {
                        LogUtil.h("HealthLife_BreathUtils", "refreshRecord hiHealthData is null");
                    } else {
                        HealthLifeBean healthLifeBean = (HealthLifeBean) sparseArray.get(azi.b(hiHealthData));
                        if (healthLifeBean != null && healthLifeBean.getId() == 5) {
                            azt.e(hiHealthData, healthLifeBean);
                        } else {
                            LogUtil.h("HealthLife_BreathUtils", "refreshRecord hiHealthData ", hiHealthData, " recordBean ", healthLifeBean);
                        }
                    }
                }
                countDownLatch.countDown();
            }
        });
        azi.d(countDownLatch, "HealthLife_BreathUtils");
        responseCallback.onResponse(0, sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(HiHealthData hiHealthData, HealthLifeBean healthLifeBean) {
        if (hiHealthData == null || healthLifeBean == null) {
            return;
        }
        int i = hiHealthData.getInt("breathTrain");
        if (healthLifeBean.getComplete() <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(1);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            if (healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
            }
        }
        LogUtil.a("HealthLife_BreathUtils", "refreshRecord result ", healthLifeBean.getResult(), " recordDay ", Integer.valueOf(healthLifeBean.getRecordDay()));
        healthLifeBean.setIsUpdated(true);
        healthLifeBean.setSyncStatus(0);
        if (i > 0) {
            healthLifeBean.setResult(String.valueOf(i));
        }
    }
}
