package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class baj {
    public static void b(int i, int i2, HiAggregateListener hiAggregateListener) {
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(d(i, i2), hiAggregateListener);
    }

    public static HiAggregateOption d(int i, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.DRINK_WATER_SET.value()});
        hiAggregateOption.setConstantsKey(new String[]{"drinkWater"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    public static void b(List<HiHealthData> list, HealthLifeBean healthLifeBean) {
        if (koq.b(list) || healthLifeBean == null || healthLifeBean.getId() != 10) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "refreshDayRecord dataList ", list, " lifeBean ", healthLifeBean);
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("HealthLife_DrinkWaterUtils", "refreshDayRecord hiHealthData is null");
            } else if (azi.b(hiHealthData) != recordDay) {
                LogUtil.h("HealthLife_DrinkWaterUtils", "refreshDayRecord hiHealthData ", hiHealthData, " recordDay ", Integer.valueOf(recordDay));
            } else {
                c(hiHealthData, healthLifeBean);
                return;
            }
        }
    }

    public static void mt_(final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord callback is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord recordArray is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord recordArray ", sparseArray);
            responseCallback.onResponse(-1, sparseArray);
        } else {
            b(sparseArray.keyAt(0), sparseArray.keyAt(size - 1), new HiAggregateListener() { // from class: baj.1
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i, int i2) {
                    if (koq.b(list)) {
                        LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord dataList ", list);
                        ResponseCallback.this.onResponse(-1, sparseArray);
                        return;
                    }
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData == null) {
                            LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord hiHealthData is null");
                        } else {
                            HealthLifeBean healthLifeBean = (HealthLifeBean) sparseArray.get(azi.b(hiHealthData));
                            if (healthLifeBean != null && healthLifeBean.getId() == 10) {
                                baj.c(hiHealthData, healthLifeBean);
                            } else {
                                LogUtil.h("HealthLife_DrinkWaterUtils", "refreshRecord hiHealthData ", hiHealthData, " recordBean ", healthLifeBean);
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
        int i = hiHealthData.getInt("drinkWater");
        if (healthLifeBean.getComplete() <= 0 && i >= CommonUtil.h(healthLifeBean.getTarget())) {
            long currentTimeMillis = System.currentTimeMillis();
            healthLifeBean.setTimestamp(currentTimeMillis);
            healthLifeBean.setComplete(2);
            healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
            if (healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
            }
        }
        healthLifeBean.setIsUpdated(true);
        healthLifeBean.setSyncStatus(0);
        if (i > 0) {
            healthLifeBean.setResult(String.valueOf(Math.min(i, y5.h)));
        }
    }

    public static void c(final int i, final HealthLifeBean healthLifeBean, final ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "insertHiHealthData callback is null");
            return;
        }
        if (i <= 0) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "insertHiHealthData value ", Integer.valueOf(i));
            responseCallback.onResponse(-1, "");
        } else if (healthLifeBean == null || healthLifeBean.getId() != 10) {
            LogUtil.h("HealthLife_DrinkWaterUtils", "insertHiHealthData lifeBean ", healthLifeBean);
            responseCallback.onResponse(-1, "");
        } else {
            int b = DateFormatUtil.b(System.currentTimeMillis());
            b(b, b, new HiAggregateListener() { // from class: baj.2
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i2, int i3) {
                    HiHealthData hiHealthData = new HiHealthData();
                    long currentTimeMillis = System.currentTimeMillis();
                    hiHealthData.setStartTime(currentTimeMillis);
                    hiHealthData.setEndTime(currentTimeMillis);
                    int i4 = i;
                    if (koq.b(list)) {
                        LogUtil.a("HealthLife_DrinkWaterUtils", "insertHiHealthData dataList ", list, " lifeBean ", healthLifeBean);
                        int h = CommonUtils.h(healthLifeBean.getResult());
                        i4 = h > 0 ? i + h : i;
                    }
                    hiHealthData.setValue(Math.min(i4, y5.h));
                    hiHealthData.setType(DicDataTypeUtil.DataType.DRINK_WATER.value());
                    hiHealthData.setDeviceUuid(String.valueOf(-1));
                    HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                    hiDataInsertOption.addData(hiHealthData);
                    HiHealthApi d = HiHealthManager.d(BaseApplication.e());
                    ResponseCallback responseCallback2 = responseCallback;
                    Objects.requireNonNull(responseCallback2);
                    d.insertHiHealthData(hiDataInsertOption, new bal(responseCallback2));
                }
            });
        }
    }
}
