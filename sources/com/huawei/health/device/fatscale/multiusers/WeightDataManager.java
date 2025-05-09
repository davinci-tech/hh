package com.huawei.health.device.fatscale.multiusers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import defpackage.cfd;
import defpackage.cfe;
import defpackage.cpa;
import defpackage.cpt;
import defpackage.dks;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public enum WeightDataManager {
    INSTANCE;

    private static final int COMPARE_WEIGHT_BEAN_ERROR_VALUE = -1;
    private static final String DATABASE_NAME = "stand.db";
    private static final String TAG = "WeightDataManager";
    private static final Object LOCK = new Object();
    private boolean mIsShowView = true;
    private boolean mIsInitFlag = true;
    private Map<String, List<cfe>> mUserWeightDataMap = Collections.synchronizedMap(new HashMap());

    WeightDataManager() {
        cfd.a();
    }

    public boolean getShowView() {
        return this.mIsShowView;
    }

    public boolean getInitFlag() {
        return this.mIsInitFlag;
    }

    public void setInitFlag(boolean z) {
        this.mIsInitFlag = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    public Map<String, List<cfe>> getUserWeightDataMap() {
        return this.mUserWeightDataMap;
    }

    public List<cfe> getSingleUserWeightData(String str, boolean z) {
        ArrayList arrayList;
        synchronized (LOCK) {
            List<cfe> list = this.mUserWeightDataMap.get(str);
            if (list == null) {
                list = new ArrayList<>();
            }
            arrayList = new ArrayList();
            arrayList.addAll(list);
            if (z && arrayList.size() > 1) {
                Collections.sort(arrayList, getComparator());
            }
        }
        return arrayList;
    }

    private Comparator getComparator() {
        return new Comparator<cfe>() { // from class: com.huawei.health.device.fatscale.multiusers.WeightDataManager.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(cfe cfeVar, cfe cfeVar2) {
                if (cfeVar == null) {
                    return -1;
                }
                return (cfeVar2 != null && cfeVar2.au() - cfeVar.au() <= 0) ? -1 : 1;
            }
        };
    }

    public void readAllWeightDataOfUser() {
        readWeightDataByDate();
    }

    public void readWeightDataByDate() {
        MultiUsersManager.INSTANCE.getOtherUserBaseData();
    }

    public void setDataList(boolean z, List<HiHealthData> list) {
        cfe weightBean;
        synchronized (LOCK) {
            if (list != null) {
                Map<String, List<cfe>> map = this.mUserWeightDataMap;
                if (map != null) {
                    if (z) {
                        map.clear();
                    }
                    LogUtil.a(TAG, "testReadWeightData dataList.size=", Integer.valueOf(list.size()));
                    if (list.size() > 0) {
                        for (HiHealthData hiHealthData : list) {
                            if (!"-1".equals(hiHealthData.getMetaData()) && (weightBean = getWeightBean(hiHealthData)) != null) {
                                setMapData(weightBean, hiHealthData.getMetaData());
                            }
                        }
                    }
                    return;
                }
            }
            LogUtil.a(TAG, "testReadWeightData return dataList or userWeightDataMap is null");
        }
    }

    public void setDataList(List<HiHealthData> list) {
        setDataList(true, list);
    }

    public void setMapData(cfe cfeVar, String str) {
        if (str == null || str.length() == 0 || Constants.NULL.equalsIgnoreCase(str) || "0".equals(str)) {
            str = MultiUsersManager.INSTANCE.getMainUser().i();
        }
        List<cfe> list = this.mUserWeightDataMap.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!list.contains(cfeVar)) {
            list.add(cfeVar);
        }
        this.mUserWeightDataMap.put(str, list);
        this.mIsInitFlag = true;
    }

    public void removeMapData(String str, List<cfe> list) {
        synchronized (LOCK) {
            List<cfe> list2 = this.mUserWeightDataMap.get(str);
            if (koq.c(list2)) {
                if (koq.c(list)) {
                    list2.removeAll(list);
                }
                this.mUserWeightDataMap.put(str, list2);
            }
            this.mIsInitFlag = true;
        }
    }

    private cfe getWeightBean(HiHealthData hiHealthData) {
        if (hiHealthData.getDouble("bodyWeight") <= 0.0d) {
            return null;
        }
        cfe cfeVar = new cfe();
        setWeightBeanData(hiHealthData, cfeVar);
        String[] strArr = {"lfrfImpedance", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance"};
        double[] dArr = new double[6];
        for (int i = 0; i < 6; i++) {
            dArr[i] = hiHealthData.getDouble(strArr[i]);
        }
        if (cpa.a(hiHealthData)) {
            double[] dArr2 = new double[6];
            String[] strArr2 = {"lfrfHfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance"};
            for (int i2 = 0; i2 < 6; i2++) {
                dArr2[i2] = hiHealthData.getDouble(strArr2[i2]);
            }
            cfeVar.e(dArr2);
            cfeVar.e(2);
        }
        cfeVar.c(dArr);
        cfeVar.b(hiHealthData.getStartTime());
        cfeVar.d(hiHealthData.getEndTime());
        return cfeVar;
    }

    private void setWeightBeanData(HiHealthData hiHealthData, cfe cfeVar) {
        cfeVar.ae(hiHealthData.getDouble("bodyWeight"));
        cfeVar.e(hiHealthData.getDouble(BleConstants.BODY_FAT_RATE));
        cfeVar.a(hiHealthData.getDouble("bodyFat"));
        cfeVar.y(hiHealthData.getDouble(BleConstants.IMPEDANCE));
        cfeVar.aa(hiHealthData.getDouble(BleConstants.MOISTURE));
        cfeVar.ai(hiHealthData.getDouble(BleConstants.MOISTURE_RATE));
        cfeVar.l(hiHealthData.getDouble(BleConstants.VISCERAL_FAT_LEVEL));
        cfeVar.j(hiHealthData.getDouble(BleConstants.BONE_SALT));
        cfeVar.c(hiHealthData.getDouble(BleConstants.BMI));
        cfeVar.d(hiHealthData.getDouble(BleConstants.BASAL_METABOLISM));
        cfeVar.q(hiHealthData.getDouble(BleConstants.MUSCLE_MASS));
        cfeVar.t(dks.b(hiHealthData));
        cfeVar.g(hiHealthData.getDouble(BleConstants.BODY_SCORE));
        cfeVar.b(hiHealthData.getDouble(BleConstants.BODY_AGE));
        cfeVar.n(hiHealthData.getDouble(IndoorEquipManagerApi.KEY_HEART_RATE));
        cfeVar.s(hiHealthData.getDouble("pressure"));
        cfeVar.ad(hiHealthData.getDouble("skeletalMusclelMass"));
        cfeVar.d(hiHealthData.getInt("age"));
        cfeVar.c(hiHealthData.getInt("height"));
        cfeVar.a((byte) hiHealthData.getInt(CommonConstant.KEY_GENDER));
        cfeVar.a(hiHealthData.getInt("trackdata_deviceType"));
        cfeVar.j(hiHealthData.getInt("pole"));
        cfeVar.k(hiHealthData.getDouble("leftArmFatMass"));
        cfeVar.r(hiHealthData.getDouble("leftArmMuscleMass"));
        cfeVar.o(hiHealthData.getDouble("leftLegFatMass"));
        cfeVar.m(hiHealthData.getDouble("leftLegMuscleMass"));
        cfeVar.u(hiHealthData.getDouble("rightArmFatMass"));
        cfeVar.ab(hiHealthData.getDouble("rightArmMuscleMass"));
        cfeVar.w(hiHealthData.getDouble("rightLegFatMass"));
        cfeVar.v(hiHealthData.getDouble("rightLegMuscleMass"));
        cfeVar.ac(hiHealthData.getDouble("trunkFatMass"));
        cfeVar.z(hiHealthData.getDouble("trunkMuscleMass"));
        cfeVar.ag(hiHealthData.getDouble("waistHipRatio"));
        cfeVar.x(hiHealthData.getDouble("rasm"));
        cfeVar.f(hiHealthData.getDouble("bodySize"));
        cfeVar.h(hiHealthData.getDouble("bodyShape"));
        cfeVar.i(hiHealthData.getDouble("fatBalance"));
        cfeVar.p(hiHealthData.getDouble("muscleBalance"));
    }

    public String getHealthAdvice(int i, int i2, int i3) {
        String[] strArr = {calcHealthId(i, i2, i3)};
        cfd e = cfd.e();
        String str = "";
        if (e == null) {
            LogUtil.h(TAG, "assets database manager is null");
            return "";
        }
        SQLiteDatabase Et_ = e.Et_(DATABASE_NAME);
        if (Et_ == null) {
            LogUtil.b(TAG, "get db error the database map is null");
            return "";
        }
        if (!Et_.isOpen()) {
            Et_ = e.Eu_(DATABASE_NAME);
        }
        if (Et_ != null) {
            Cursor rawQuery = Et_.rawQuery("select * from data_body_stand where id = ?", strArr);
            while (rawQuery.moveToNext()) {
                str = rawQuery.getString(rawQuery.getColumnIndex("trend_text"));
            }
            rawQuery.close();
        }
        e.d(DATABASE_NAME);
        return str;
    }

    private String calcHealthId(int i, int i2, int i3) {
        return ((i * 12) + (i2 * 3) + i3 + 1) + "";
    }
}
