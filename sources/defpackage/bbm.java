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
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.OpenServiceUtil;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class bbm {
    private static final int[] e = {47101, 47102, 47103, 47104, 47105, 47107, 47108, 47109};
    private static final String[] b = {"TOTAL", OpenServiceUtil.Location.STEP, "RUN", "CYCLE", "FITNESS", "CLIMB", "SWIM", "UNKNOWHIGH"};

    public static HiAggregateOption b(int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(e);
        hiAggregateOption.setConstantsKey(b);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public static void c(List<HiHealthData> list, HealthLifeBean healthLifeBean) {
        if (koq.b(list) || healthLifeBean == null || healthLifeBean.getId() != 3) {
            LogUtil.h("HealthLife_IntensityUtils", "refreshDayRecord dataList ", list, " recordBean ", healthLifeBean);
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HealthLife_IntensityUtils", "refreshDayRecord hiHealthData is null");
            } else if (azi.b(hiHealthData) != recordDay) {
                LogUtil.h("HealthLife_IntensityUtils", "refreshDayRecord hiHealthData ", hiHealthData, " recordDay ", Integer.valueOf(recordDay));
            } else {
                e(hiHealthData, healthLifeBean);
                return;
            }
        }
    }

    public static void mJ_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_IntensityUtils", "refreshRecord callback is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_IntensityUtils", "refreshRecord recordArray is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_IntensityUtils", "refreshRecord recordArray ", sparseArray);
            responseCallback.onResponse(-1, sparseArray);
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(b(sparseArray.keyAt(0), sparseArray.keyAt(size - 1)), new HiAggregateListener() { // from class: bbm.1
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (!koq.b(list)) {
                        ResponseCallback.this.onResponse(0, bbm.mI_(list, sparseArray));
                    } else {
                        LogUtil.h("HealthLife_IntensityUtils", "refreshRecord dataList ", list);
                        ResponseCallback.this.onResponse(0, bdh.nU_(3, sparseArray, new SparseIntArray()));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SparseArray<HealthLifeBean> mI_(List<HiHealthData> list, SparseArray<HealthLifeBean> sparseArray) {
        if (koq.b(list) || sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_IntensityUtils", "refreshRecord dataList ", list, " recordArray ", sparseArray);
            return sparseArray;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HealthLife_IntensityUtils", "refreshRecord hiHealthData is null");
            } else {
                int b2 = azi.b(hiHealthData);
                HealthLifeBean healthLifeBean = sparseArray.get(b2);
                if (healthLifeBean == null || healthLifeBean.getId() != 3) {
                    LogUtil.h("HealthLife_IntensityUtils", "refreshRecord hiHealthData ", hiHealthData, " recordBean ", healthLifeBean);
                } else {
                    sparseIntArray.append(b2, hiHealthData.getInt("TOTAL"));
                    hashMap.put(Integer.valueOf(b2), Integer.valueOf(azi.c(hiHealthData)));
                }
            }
        }
        SparseArray<HealthLifeBean> nU_ = bdh.nU_(3, sparseArray, sparseIntArray);
        bao.b("intensity_week_status", HiJsonUtil.e(hashMap));
        LogUtil.a("HealthLife_IntensityUtils", "refreshRecord sparseArray ", nU_);
        return nU_;
    }

    private static void e(HiHealthData hiHealthData, HealthLifeBean healthLifeBean) {
        if (hiHealthData == null || healthLifeBean == null) {
            return;
        }
        int i = hiHealthData.getInt("TOTAL");
        if (healthLifeBean.getComplete() <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setIsUpdated(true);
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(1);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            healthLifeBean.setRest(0);
            healthLifeBean.setSyncStatus(0);
            if (healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
            }
        }
        healthLifeBean.setResult(String.valueOf(i));
    }
}
