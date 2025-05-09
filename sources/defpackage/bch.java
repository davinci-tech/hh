package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class bch {
    private static final int[] d = {40002};
    private static final String[] b = {ParsedFieldTag.STEP_SUM};

    public static HiAggregateOption c(int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(d);
        hiAggregateOption.setConstantsKey(b);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public static void b(List<HiHealthData> list, HealthLifeBean healthLifeBean) {
        if (koq.b(list) || healthLifeBean == null || healthLifeBean.getId() != 2) {
            LogUtil.h("HealthLife_StepUtils", "refreshDayRecord dataList ", list, " recordBean ", healthLifeBean);
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HealthLife_StepUtils", "refreshDayRecord hiHealthData is null");
            } else if (azi.b(hiHealthData) != recordDay) {
                LogUtil.h("HealthLife_StepUtils", "refreshDayRecord hiHealthData ", hiHealthData, " recordDay ", Integer.valueOf(recordDay));
            } else {
                c(hiHealthData, healthLifeBean);
                return;
            }
        }
    }

    public static void nh_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_StepUtils", "refreshRecord callback is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_StepUtils", "refreshRecord recordArray is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_StepUtils", "refreshRecord recordArray ", sparseArray);
            responseCallback.onResponse(-1, sparseArray);
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(c(sparseArray.keyAt(0), sparseArray.keyAt(size - 1)), new HiAggregateListener() { // from class: bch.3
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (koq.b(list)) {
                        LogUtil.h("HealthLife_StepUtils", "refreshRecord dataList ", list);
                        ResponseCallback.this.onResponse(-1, sparseArray);
                        return;
                    }
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData == null) {
                            LogUtil.h("HealthLife_StepUtils", "refreshRecord hiHealthData is null");
                        } else {
                            HealthLifeBean healthLifeBean = (HealthLifeBean) sparseArray.get(azi.b(hiHealthData));
                            if (healthLifeBean != null && healthLifeBean.getId() == 2) {
                                bch.c(hiHealthData, healthLifeBean);
                            } else {
                                LogUtil.h("HealthLife_StepUtils", "refreshRecord hiHealthData ", hiHealthData, " recordBean ", healthLifeBean);
                            }
                        }
                    }
                    ResponseCallback.this.onResponse(0, sparseArray);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(HiHealthData hiHealthData, HealthLifeBean healthLifeBean) {
        if (hiHealthData == null || healthLifeBean == null) {
            return;
        }
        int i = hiHealthData.getInt(ParsedFieldTag.STEP_SUM);
        if (healthLifeBean.getComplete() <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(1);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            healthLifeBean.setSyncStatus(0);
            if (healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
            }
        } else if (i != CommonUtil.h(healthLifeBean.getResult())) {
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setSyncStatus(0);
        } else {
            LogUtil.c("HealthLife_StepUtils", "refreshRecord recordBean ", healthLifeBean, " step ", Integer.valueOf(i));
        }
        healthLifeBean.setResult(String.valueOf(i));
    }
}
