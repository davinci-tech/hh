package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cso {
    private cfi b;
    private WeightInsertStatusCallback c;
    private double[] e = new double[6];

    /* renamed from: a, reason: collision with root package name */
    private double[] f11444a = new double[6];

    private static boolean c(double d, double d2, double d3) {
        return d <= d3 && d >= d2;
    }

    public int b(int i) {
        if (i >= 1 && i <= 29) {
            return 1;
        }
        if (i <= 29 || i >= 60) {
            return (i < 60 || i >= 80) ? 4 : 3;
        }
        return 2;
    }

    public cso(cfi cfiVar, WeightInsertStatusCallback weightInsertStatusCallback) {
        this.b = cfiVar;
        this.c = weightInsertStatusCallback;
    }

    private void c(double d, HiHealthData hiHealthData, String str) {
        if (d(str, d)) {
            hiHealthData.putDouble(str, d);
        } else {
            ReleaseLogUtil.d("R_Weight_WiFiWeightSaveHandler", "setData key:", str);
            cpw.d(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler setData value:", Double.valueOf(d));
        }
    }

    private HiHealthData d(HiHealthData hiHealthData) {
        double d;
        double d2;
        if (hiHealthData == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertData data is null");
            return null;
        }
        if (this.b == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertData mCurrentUser is null");
            return null;
        }
        hiHealthData.setType(10006);
        cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler DeviceUUID is ", cpw.d(hiHealthData.getDeviceUuid()));
        cpw.a(true, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler starTime is ", Long.valueOf(hiHealthData.getStartTime()));
        double d3 = hiHealthData.getDouble("bodyWeight");
        double d4 = hiHealthData.getDouble(BleConstants.IMPEDANCE);
        double d5 = hiHealthData.getDouble(IndoorEquipManagerApi.KEY_HEART_RATE);
        cpw.a(false, "WiFiWeightSaveHandler", "weight is ", Double.valueOf(d3), " impedance:", Double.valueOf(d4), "mHeartRate: ", Double.valueOf(d5));
        float f = (float) d3;
        this.b.b(f);
        MultiUsersManager.INSTANCE.saveUser(this.b, new IBaseResponseCallback() { // from class: cso.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
            }
        });
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        String i2 = this.b.i();
        if (i == null) {
            hiHealthData.setMetaData("0");
        } else if (i.equals(i2)) {
            hiHealthData.setMetaData("0");
        } else {
            hiHealthData.setMetaData(i2);
        }
        LogUtil.a("WiFiWeightSaveHandler", "getMetaData = ", hiHealthData.getMetaData());
        cfe cfeVar = new cfe();
        c(this.b.a(), hiHealthData, "age");
        c(this.b.d(), hiHealthData, "height");
        c(this.b.c(), hiHealthData, CommonConstant.KEY_GENDER);
        boolean a2 = cpa.a(hiHealthData);
        if (hiHealthData.getInt("pole") == 2) {
            i(hiHealthData);
            ckn.a(hiHealthData, cfeVar, e(hiHealthData, cfeVar, a2));
            cfeVar.c(this.e);
            cfeVar.n(d5);
        } else if (hiHealthData.getInt("pole") == 1) {
            if (d4 == 0.0d) {
                double d6 = hiHealthData.getDouble("lfrfImpedance");
                LogUtil.a("WiFiWeightSaveHandler", "impedance from left leg right leg:", Double.valueOf(d6));
                ckn.e(true);
                d2 = d6;
            } else {
                d2 = d4;
            }
            ckn.b(hiHealthData, cfeVar, e(hiHealthData, d2, cfeVar, a2));
            cfeVar.n(d5);
        } else {
            if (d4 > 0.0d) {
                double d7 = d(f, (float) d4);
                c(d7, hiHealthData, BleConstants.BODY_FAT_RATE);
                d = d7;
            } else {
                d = 0.0d;
            }
            if (d > 0.5d && d4 > 0.0d) {
                a(hiHealthData, d4, d5, (float) d, cfeVar);
            }
            cfeVar.e(d);
        }
        if (cgs.e(hiHealthData.getDeviceUuid(), this.b.a())) {
            cfeVar.n(0.0d);
            cfeVar.e(0.0d);
            cfeVar.y(0.0d);
        }
        cfeVar.a(hiHealthData.getInt("trackdata_deviceType"));
        cfeVar.b(hiHealthData.getStartTime());
        cfeVar.d(hiHealthData.getEndTime());
        cfeVar.ae(d3);
        cfeVar.d(this.b.a());
        cfeVar.c(this.b.d());
        cfeVar.a(this.b.c());
        MultiUsersManager.INSTANCE.setCurrentUser(this.b);
        WeightDataManager.INSTANCE.setMapData(cfeVar, i2);
        ReleaseLogUtil.e("R_Weight_WiFiWeightSaveHandler", "endTime is ", Long.valueOf(hiHealthData.getEndTime()));
        return hiHealthData;
    }

    private cps e(HiHealthData hiHealthData, double d, cfe cfeVar, boolean z) {
        if (z) {
            i(hiHealthData);
            cfeVar.c(this.e);
            e(hiHealthData);
            cfeVar.e(this.f11444a);
            cfeVar.e(2);
            return new cps(this.b.d(), this.b.m(), cff.c(this.b.c()), this.b.a(), this.b.g(), 4, this.e, 1, this.f11444a);
        }
        e(0, d);
        return new cps(this.b.d(), this.b.m(), cff.c(this.b.c()), this.b.a(), this.b.g(), this.e[0]);
    }

    private cps e(HiHealthData hiHealthData, cfe cfeVar, boolean z) {
        if (z) {
            e(hiHealthData);
            cfeVar.e(this.f11444a);
            cfeVar.e(2);
            return new cps(this.b.d(), this.b.m(), cff.c(this.b.c()), this.b.a(), this.b.g(), 8, this.e, 2, this.f11444a);
        }
        return new cps(this.b.d(), this.b.m(), cff.c(this.b.c()), this.b.a(), this.b.g(), this.e);
    }

    private void i(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        String[] strArr = {"lfrfImpedance", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance"};
        for (int i = 0; i < 6; i++) {
            e(i, hiHealthData.getDouble(strArr[i]));
        }
    }

    private void e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        String[] strArr = {"lfrfHfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance"};
        for (int i = 0; i < 6; i++) {
            a(i, hiHealthData.getDouble(strArr[i]));
        }
    }

    private void a(int i, double d) {
        if (i >= 0) {
            double[] dArr = this.f11444a;
            if (i >= dArr.length) {
                return;
            }
            dArr[i] = d;
        }
    }

    private void e(int i, double d) {
        if (i >= 0) {
            double[] dArr = this.e;
            if (i >= dArr.length) {
                return;
            }
            dArr[i] = d;
        }
    }

    private void a(HiHealthData hiHealthData, double d, double d2, float f, cfe cfeVar) {
        crs crsVar = new crs(this.b.d(), this.b.m(), cff.c(this.b.c()), this.b.a(), f, d);
        c(crsVar.a(), hiHealthData, BleConstants.BMI);
        c(crsVar.e(), hiHealthData, BleConstants.BASAL_METABOLISM);
        c(crsVar.d(), hiHealthData, BleConstants.BODY_AGE);
        c(crsVar.j(), hiHealthData, BleConstants.BODY_SCORE);
        c(crsVar.c(), hiHealthData, BleConstants.BONE_SALT);
        c(crsVar.i(), hiHealthData, BleConstants.MUSCLE_MASS);
        c(crsVar.b(), hiHealthData, "protein");
        c(crsVar.g(), hiHealthData, BleConstants.MOISTURE_RATE);
        c(crsVar.k(), hiHealthData, BleConstants.VISCERAL_FAT_LEVEL);
        c(crsVar.f(), hiHealthData, BleConstants.IMPEDANCE);
        c(crsVar.h(), hiHealthData, "skeletalMusclelMass");
        cfeVar.b(crsVar.d());
        cfeVar.g(crsVar.j());
        cfeVar.t(crsVar.b());
        cfeVar.c(crsVar.a());
        cfeVar.d(crsVar.e());
        cfeVar.q(crsVar.i());
        cfeVar.l(crsVar.k());
        cfeVar.j(crsVar.c());
        cfeVar.ai(crsVar.g());
        cfeVar.n(d2);
        cfeVar.ad(crsVar.h());
    }

    private double d(float f, float f2) {
        double d;
        oi oiVar = new oi();
        oiVar.b(this.b.d(), f, cff.c(this.b.c()), this.b.a(), f2);
        cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertData checkCode == ", Integer.valueOf(oiVar.e(this.b.d(), f, cff.c(this.b.c()), this.b.a(), f2)));
        try {
            d = oiVar.e();
        } catch (og e) {
            cpw.e(true, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertData getBFR: ", e.getMessage());
            d = 0.0d;
        }
        cpw.c(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler bodyfatvalue is ", Double.valueOf(d));
        return d;
    }

    public void d(List<HiHealthData> list) {
        if (koq.b(list)) {
            cpw.d(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler onDataChanged parameter is empty");
            return;
        }
        final ArrayList arrayList = new ArrayList(16);
        final ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        for (HiHealthData hiHealthData : list) {
            hiHealthData.setModifiedTime(hiHealthData.getModifiedTime() + 1);
            arrayList.add(hiHealthData.copyData());
            arrayList2.add(hiHealthData.copyData());
            HiHealthData d = d(hiHealthData);
            if (d != null) {
                d.setOwnerId(0);
                arrayList3.add(d);
            } else {
                cpw.d(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler onDataChanged weightData is null");
            }
        }
        if (koq.b(arrayList3)) {
            cpw.d(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler onDataChanged weightDataList is empty");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList3);
        HiHealthManager.d(cpp.a()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cso.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler type is ", Integer.valueOf(i), ", obj =", obj);
                if (cso.this.c == null) {
                    cpw.d(false, "WiFiWeightSaveHandler", "onDataChanged insertHiHealthData mInsertStatusCallback is null");
                } else {
                    if (i == 0) {
                        cso.this.a((List<HiHealthData>) arrayList);
                        cso.this.e((List<HiHealthData>) arrayList2);
                        cso.this.c.isSuccess(true);
                        return;
                    }
                    cso.this.c.isSuccess(false);
                }
            }
        });
    }

    public static boolean d(String str, double d) {
        if (BleConstants.BMI.equals(str)) {
            return c(d, 1.0d, 200.0d);
        }
        if ("bodyWeight".equals(str)) {
            return c(d, 1.0d, 500.0d);
        }
        if (BleConstants.BODY_FAT_RATE.equals(str)) {
            return c(d, 0.0d, 100.0d);
        }
        if (BleConstants.MOISTURE.equals(str)) {
            return c(d, 0.0d, 100.0d);
        }
        if (BleConstants.MOISTURE_RATE.equals(str)) {
            return c(d, 0.0d, 100.0d);
        }
        if (BleConstants.VISCERAL_FAT_LEVEL.equals(str)) {
            return c(d, 1.0d, 59.0d);
        }
        if (BleConstants.MUSCLE_MASS.equals(str)) {
            return c(d, 1.0d, 150.0d);
        }
        if (BleConstants.BONE_SALT.equals(str)) {
            return c(d, 1.0d, 4.0d);
        }
        if ("protein".equals(str)) {
            return c(d, 0.0d, 100.0d);
        }
        if (BleConstants.BODY_AGE.equals(str)) {
            return c(d, 18.0d, 99.0d);
        }
        if (BleConstants.BODY_SCORE.equals(str)) {
            return c(d, 0.0d, 100.0d);
        }
        if (IndoorEquipManagerApi.KEY_HEART_RATE.equals(str)) {
            return c(d, 0.0d, 300.0d);
        }
        if ("pressure".equals(str)) {
            return c(d, 1.0d, 99.0d);
        }
        if (BleConstants.IMPEDANCE.equals(str)) {
            return c(d, 1.0d, 100000.0d);
        }
        if ("skeletalMusclelMass".equals(str)) {
            return c(d, 1.0d, 150.0d);
        }
        if ("age".equals(str)) {
            return c(d, 0.0d, 150.0d);
        }
        if (CommonConstant.KEY_GENDER.equals(str)) {
            return c(d, -1.0d, 2.0d);
        }
        if ("height".equals(str)) {
            return c(d, 1.0d, 300.0d);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list) {
        if (koq.b(list)) {
            cpw.d(false, "WiFiWeightSaveHandler", "setMainUserHeartRate dataList is empty");
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public void b(HiHealthData hiHealthData) {
        final HiHealthData c = c(hiHealthData);
        if (c != null) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.addData(c);
            c.setOwnerId(0);
            HiHealthManager.d(cpp.a()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cso.2
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    cpw.a(false, "WiFiWeightSaveHandler", "setMainUserHeartRate type is ", Integer.valueOf(i));
                    if (i == 0) {
                        cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler setMainUserHeartRate insertHiHealthData success ");
                    } else {
                        cpw.a(false, "WiFiWeightSaveHandler", "setMainUserHeartRate insertHiHealthData not correct obj =", obj);
                        cpw.a(false, "WiFiWeightSaveHandler", "setMainUserHeartRate fail start time:", Long.valueOf(c.getStartTime()));
                    }
                }
            });
        }
    }

    private HiHealthData c(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData data is null");
            return null;
        }
        if (this.b == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData mCurrentUser is null");
            return null;
        }
        cpw.a(false, "WiFiWeightSaveHandler", "convertHeartRateData DeviceUUID is ", cpw.d(hiHealthData.getDeviceUuid()));
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        String i2 = this.b.i();
        if (i == null) {
            if (!"0".equals(i2)) {
                cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData mCurrentUserId is 0");
                return null;
            }
        } else if (!i.equals(i2) && !"0".equals(i2)) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData mCurrentUser is not mainUser");
            return null;
        }
        hiHealthData.setType(2002);
        double d = hiHealthData.getDouble(IndoorEquipManagerApi.KEY_HEART_RATE);
        cpw.c(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData mHeartRate is : ", Double.valueOf(d));
        a(hiHealthData, (int) d, 0, 300);
        if (hiHealthData.getValue() > 0.0d) {
            return hiHealthData;
        }
        cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertHeartRateData verifyHeartRateData is less zero");
        return null;
    }

    private void a(HiHealthData hiHealthData, int i, int i2, int i3) {
        if (i > 0) {
            hiHealthData.setValue(i);
        } else {
            cpw.c(false, "WiFiWeightSaveHandler", "verifyHeartRateData value:", Integer.valueOf(i));
        }
        if (i > i3) {
            hiHealthData.setValue(i3);
        } else if (i < i2) {
            hiHealthData.setValue(i2);
        } else {
            cpw.a(false, "WiFiWeightSaveHandler", "value is error ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list) {
        if (koq.b(list)) {
            cpw.d(false, "WiFiWeightSaveHandler", "setMainUserPressure dataList is empty");
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            j(it.next());
        }
    }

    private void j(HiHealthData hiHealthData) {
        String deviceUuid = hiHealthData.getDeviceUuid();
        if (TextUtils.isEmpty(deviceUuid)) {
            deviceUuid = "-1";
        }
        HiStressMetaData a2 = a(hiHealthData);
        if (a2 != null) {
            a(deviceUuid, a2, new IBaseResponseCallback() { // from class: cso.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    cpw.a(false, "WiFiWeightSaveHandler", "setStressData errorCode = ", Integer.valueOf(i));
                }
            });
        }
    }

    public void a(String str, HiStressMetaData hiStressMetaData, IBaseResponseCallback iBaseResponseCallback) {
        if (hiStressMetaData == null) {
            return;
        }
        LogUtil.c("WiFiWeightSaveHandler", "setStressData uuid = ", str);
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setValue(hiStressMetaData.fetchStressScore());
        hiHealthData.setDeviceUuid(str);
        hiHealthData.setTimeInterval(hiStressMetaData.fetchStressStartTime(), hiStressMetaData.fetchStressEndTime());
        hiHealthData.setType(2034);
        LogUtil.c("WiFiWeightSaveHandler", "setStressData metaData = ", hiStressMetaData.toString(), ",setStressData HiJsonUtil.toJson(metaData) = ", HiJsonUtil.e(hiStressMetaData));
        hiHealthData.setMetaData(HiJsonUtil.e(hiStressMetaData));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        a(iBaseResponseCallback, hiDataInsertOption);
    }

    private void a(final IBaseResponseCallback iBaseResponseCallback, HiDataInsertOption hiDataInsertOption) {
        HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cso.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("WiFiWeightSaveHandler", "errorCode = ", Integer.valueOf(i), "obj = ", HiJsonUtil.e(obj));
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i, obj);
                }
            }
        });
    }

    private HiStressMetaData a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertPressureData data is null");
            return null;
        }
        if (this.b == null) {
            cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertPressureData mCurrentUser is null");
            return null;
        }
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        String i2 = this.b.i();
        if (i != null && i2 != null) {
            if (!i.equals(i2)) {
                cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertPressureData mCurrentUser is not mainUser");
                return null;
            }
            double d = hiHealthData.getDouble("pressure");
            cpw.c(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertPressureData mPressure is : ", Double.valueOf(d));
            if (!d("pressure", d)) {
                return null;
            }
            cpw.a(false, "WiFiWeightSaveHandler", "convert pressure data start");
            HiStressMetaData hiStressMetaData = new HiStressMetaData();
            hiStressMetaData.configStressStartTime(hiHealthData.getStartTime());
            hiStressMetaData.configStressEndTime(hiHealthData.getEndTime());
            hiStressMetaData.configStressMeasureType(0);
            int i3 = (int) d;
            hiStressMetaData.configStressScore(i3);
            hiStressMetaData.configStressGrade(b(i3));
            return hiStressMetaData;
        }
        cpw.a(false, "WiFiWeightSaveHandler", "WiFiWeightSaveHandler convertPressureData don't recognition user ,", "so don't insert pressure data");
        return null;
    }
}
