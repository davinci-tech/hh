package com.huawei.operation.jsoperation;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.BloodPressure;
import com.huawei.operation.jsoperation.BloodSugar;
import com.huawei.operation.jsoperation.WeightBodyFat;
import com.huawei.operation.utils.OperationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class PluginOperationDataStored {
    private static final double DIFF_VALUE = 1.0E-6d;
    private static final double DOUBLE_ZERO_VALUE = 0.0d;
    private static final String TAG = "PluginOperation_PluginOperationDataStored";

    private static int compareDouble(double d, double d2) {
        double d3 = d - d2;
        if (d3 < -1.0E-6d) {
            return -1;
        }
        return d3 > 1.0E-6d ? 1 : 0;
    }

    private PluginOperationDataStored() {
    }

    public static void parseHealthData(int i, String str, String str2, List<HiHealthData> list) {
        LogUtil.a(TAG, "parseHealthData");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(TAG, "convertData data is null OR TextUtils.isEmpty(appID)");
            list.clear();
        } else if (i == 4) {
            sugarData(str, str2, list);
        } else if (i == 5) {
            bloodPressureData(str, str2, list);
        } else {
            if (i != 8) {
                return;
            }
            weightFatData(str, str2, list);
        }
    }

    private static void sugarData(String str, String str2, List<HiHealthData> list) {
        ArrayList arrayList = (ArrayList) OperationUtils.fromJson(str, new TypeToken<ArrayList<BloodSugar>>() { // from class: com.huawei.operation.jsoperation.PluginOperationDataStored.1
        });
        if (arrayList != null) {
            LogUtil.a(TAG, "bloodSugars.size = ", Integer.valueOf(arrayList.size()));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List<BloodSugar.SamplePointsBean> samplePoints = ((BloodSugar) it.next()).getSamplePoints();
                if (samplePoints != null) {
                    LogUtil.a(TAG, "samplePointsBeans.size = ", Integer.valueOf(samplePoints.size()));
                    for (BloodSugar.SamplePointsBean samplePointsBean : samplePoints) {
                        HiHealthData hiHealthData = new HiHealthData(10001);
                        initBloodSugarData(samplePointsBean.getValue(), hiHealthData);
                        hiHealthData.setStartTime(samplePointsBean.getStartTime());
                        hiHealthData.setEndTime(samplePointsBean.getEndTime());
                        hiHealthData.setDeviceUuid(str2);
                        list.add(hiHealthData);
                    }
                }
            }
            return;
        }
        LogUtil.a(TAG, "bloodSugars is null");
        list.clear();
    }

    private static void bloodPressureData(String str, String str2, List<HiHealthData> list) {
        ArrayList arrayList = (ArrayList) OperationUtils.fromJson(str, new TypeToken<ArrayList<BloodPressure>>() { // from class: com.huawei.operation.jsoperation.PluginOperationDataStored.2
        });
        if (arrayList != null) {
            LogUtil.a(TAG, "bloodPressure.size = ", Integer.valueOf(arrayList.size()));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List<BloodPressure.SamplePointsBean> samplePoints = ((BloodPressure) it.next()).getSamplePoints();
                if (samplePoints != null) {
                    LogUtil.a(TAG, "samplePointsBeans.size = ", Integer.valueOf(samplePoints.size()));
                    for (BloodPressure.SamplePointsBean samplePointsBean : samplePoints) {
                        HiHealthData hiHealthData = new HiHealthData(10002);
                        initBloodPressureData(samplePointsBean.getValue(), hiHealthData);
                        hiHealthData.setStartTime(samplePointsBean.getStartTime());
                        hiHealthData.setEndTime(samplePointsBean.getEndTime());
                        hiHealthData.setDeviceUuid(str2);
                        list.add(hiHealthData);
                    }
                }
            }
            return;
        }
        LogUtil.a(TAG, "bloodPressures is null");
        list.clear();
    }

    private static void weightFatData(String str, String str2, List<HiHealthData> list) {
        ArrayList arrayList = (ArrayList) OperationUtils.fromJson(str, new TypeToken<ArrayList<WeightBodyFat>>() { // from class: com.huawei.operation.jsoperation.PluginOperationDataStored.3
        });
        if (arrayList != null) {
            LogUtil.a(TAG, "weightBodyFats.size = ", Integer.valueOf(arrayList.size()));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List<WeightBodyFat.SamplePointsBean> samplePoints = ((WeightBodyFat) it.next()).getSamplePoints();
                if (samplePoints != null) {
                    LogUtil.a(TAG, "samplePointsBean.size = ", Integer.valueOf(samplePoints.size()));
                    for (WeightBodyFat.SamplePointsBean samplePointsBean : samplePoints) {
                        HiHealthData hiHealthData = new HiHealthData(10006);
                        initWeightFatData(samplePointsBean.getValue(), hiHealthData);
                        hiHealthData.setDeviceUuid(str2);
                        hiHealthData.setStartTime(samplePointsBean.getStartTime());
                        hiHealthData.setEndTime(samplePointsBean.getEndTime());
                        list.add(hiHealthData);
                    }
                }
            }
            return;
        }
        LogUtil.a(TAG, "weightBodyFats is null");
        list.clear();
    }

    private static void initBloodSugarData(BloodSugar.SamplePointsBean.ValueBean valueBean, HiHealthData hiHealthData) {
        LogUtil.a(TAG, "initBloodSugarData");
        if (valueBean == null || hiHealthData == null) {
            return;
        }
        putPropertyValueToHealthData(valueBean.getBloodSugarBeforeDawn(), "bloodsugar_before_dawn", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarAfterBreakfast(), "bloodsugar_bf_after", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarBeforeBreakfast(), "bloodsugar_bf_before", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarAfterDinner(), "bloodsugar_dn_after", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarBeforeDinner(), "bloodsugar_dn_before", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarAfterLunch(), "bloodsugar_lc_after", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarBeforeLunch(), "bloodsugar_lc_before", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodSugarBeforeSleep(), "bloodsugar_sl_before", hiHealthData);
    }

    private static void initBloodPressureData(BloodPressure.SamplePointsBean.ValueBean valueBean, HiHealthData hiHealthData) {
        LogUtil.a(TAG, "initBloodPressureData");
        if (valueBean == null || hiHealthData == null) {
            return;
        }
        putPropertyValueToHealthData(valueBean.getBloodPressureDiastolic(), "BLOOD_PRESSURE_DIASTOLIC", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBloodPressureSystolic(), "BLOOD_PRESSURE_SYSTOLIC", hiHealthData);
        putPropertyValueToHealthData(valueBean.getDataPointDynamicHeartRate(), BleConstants.BLOODPRESSURE_SPHYGMUS, hiHealthData);
    }

    private static void initWeightFatData(WeightBodyFat.SamplePointsBean.ValueBean valueBean, HiHealthData hiHealthData) {
        LogUtil.a(TAG, "initWeightFatData");
        if (valueBean == null || hiHealthData == null) {
            return;
        }
        putPropertyValueToHealthData(valueBean.getBodyWeight(), "bodyWeight", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBodyFat(), BleConstants.BODY_FAT_RATE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getBasalMetabolism(), BleConstants.BASAL_METABOLISM, hiHealthData);
        putPropertyValueToHealthData(valueBean.getBmi(), BleConstants.BMI, hiHealthData);
        putPropertyValueToHealthData(valueBean.getBodyAge(), BleConstants.BODY_AGE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getBodyFatRate(), "bodyFat", hiHealthData);
        putPropertyValueToHealthData(valueBean.getBodyScore(), BleConstants.BODY_SCORE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getBoneSalt(), BleConstants.BONE_SALT, hiHealthData);
        putPropertyValueToHealthData(valueBean.getVisceralFatLevel(), BleConstants.VISCERAL_FAT_LEVEL, hiHealthData);
        putPropertyValueToHealthData(valueBean.getProteinRate(), BleConstants.PROTEIN_RATE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getMuscleMass(), BleConstants.MUSCLE_MASS, hiHealthData);
        putPropertyValueToHealthData(valueBean.getMoistureRate(), BleConstants.MOISTURE_RATE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getMoisture(), BleConstants.MOISTURE, hiHealthData);
        putPropertyValueToHealthData(valueBean.getImpedance(), BleConstants.IMPEDANCE, hiHealthData);
    }

    private static void putPropertyValueToHealthData(double d, String str, HiHealthData hiHealthData) {
        if (compareDouble(d, 0.0d) != 0) {
            hiHealthData.putDouble(str, d);
        }
    }
}
