package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;

/* loaded from: classes3.dex */
public class ckn {
    private static cfi c;
    private static Boolean d = false;

    public static cfe c(HealthDevice healthDevice, HealthData healthData, cfi cfiVar, int i) {
        if (healthData instanceof ckm) {
            ckm ckmVar = (ckm) healthData;
            cfe cfeVar = new cfe();
            HiHealthData hiHealthData = new HiHealthData(i);
            long time = new Date().getTime();
            if (healthData.getStartTime() != 0 && healthData.getEndTime() != 0) {
                hiHealthData.setStartTime(healthData.getStartTime());
                hiHealthData.setEndTime(healthData.getEndTime());
            } else {
                hiHealthData.setStartTime(time);
                hiHealthData.setEndTime(time);
            }
            a(healthDevice, hiHealthData);
            double bodyFatRat = ckmVar.getBodyFatRat();
            if (bodyFatRat > 0.0d) {
                c(hiHealthData, BleConstants.BODY_FAT_RATE, Double.valueOf(bodyFatRat));
                cfeVar.e(bodyFatRat);
            }
            a(ckmVar, hiHealthData, cfeVar, cfiVar);
            if (TextUtils.isEmpty(hiHealthData.getDeviceUuid()) || "-1".equals(hiHealthData.getDeviceUuid()) || "0".equals(hiHealthData.getDeviceUuid())) {
                cfeVar.a(-1);
                hiHealthData.setDeviceUuid("-1");
            } else {
                cfeVar.a(2);
            }
            cfeVar.b(hiHealthData.getStartTime());
            cfeVar.d(hiHealthData.getEndTime());
            cfeVar.ae(ckmVar.getWeight());
            cfeVar.d(cfiVar.a());
            cfeVar.c(cfiVar.d());
            cfeVar.a(cfiVar.c());
            if (healthDevice != null) {
                cfeVar.c(healthDevice.getDeviceName());
            }
            return cfeVar;
        }
        LogUtil.h("ConverResisToWeightDataUtil", "guestConvertWeightAndFatRateData data not WeightAndFatRateData");
        return null;
    }

    private static void a(HealthDevice healthDevice, HiHealthData hiHealthData) {
        if (!(healthDevice instanceof MeasurableDevice)) {
            if (healthDevice != null) {
                hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
                return;
            }
            return;
        }
        MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
        String productId = measurableDevice.getProductId();
        String l = cpa.l(measurableDevice.getUniqueId());
        boolean z = (TextUtils.isEmpty(l) || "0".equals(l)) ? false : true;
        if (cpa.x(productId) && z) {
            hiHealthData.setDeviceUuid(l);
        } else {
            hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
        }
    }

    public static void a(HiHealthData hiHealthData, ckm ckmVar, cfi cfiVar) {
        LogUtil.c("ConverResisToWeightDataUtil", "IHealthDataHandler HiHealthDataType.DATA_SET_WEIGHT_EX bodyDatas ", Float.valueOf(ckmVar.getWeight()), " ", Float.valueOf(ckmVar.getBodyFatRat()));
        c(hiHealthData, "bodyWeight", Float.valueOf(ckmVar.getWeight()));
        if (cfiVar == null) {
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
            ckmVar.e(cfiVar.i());
        }
        c = cfiVar;
        cfiVar.b(ckmVar.getWeight());
        String i = cfiVar.i();
        MultiUsersManager.INSTANCE.saveUser(cfiVar, new IBaseResponseCallback() { // from class: ckn.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
            }
        });
        a(hiHealthData, i, ckmVar);
        cfe cfeVar = new cfe();
        double bodyFatRat = ckmVar.getBodyFatRat();
        if (bodyFatRat > 0.0d) {
            c(hiHealthData, BleConstants.BODY_FAT_RATE, Double.valueOf(bodyFatRat));
            cfeVar.e(bodyFatRat);
        }
        a(hiHealthData, cfiVar);
        d(ckmVar, hiHealthData, cfeVar, cfiVar);
        a(ckmVar, hiHealthData, cfeVar, cfiVar);
        d(ckmVar, hiHealthData, cfeVar);
        if (TextUtils.isEmpty(hiHealthData.getDeviceUuid()) || hiHealthData.getDeviceUuid().equals("-1") || hiHealthData.getDeviceUuid().equals("0")) {
            cfeVar.a(-1);
            hiHealthData.setDeviceUuid("-1");
        } else {
            cfeVar.a(2);
        }
        cfeVar.b(hiHealthData.getStartTime());
        cfeVar.d(hiHealthData.getEndTime());
        cfeVar.ae(ckmVar.getWeight());
        cfeVar.d(cfiVar.a());
        cfeVar.c(cfiVar.d());
        cfeVar.a(cfiVar.c());
        WeightDataManager.INSTANCE.setMapData(cfeVar, i);
    }

    private static void a(HiHealthData hiHealthData, cfi cfiVar) {
        c(hiHealthData, "age", Integer.valueOf(cfiVar.a()));
        c(hiHealthData, "height", Integer.valueOf(cfiVar.d()));
        c(hiHealthData, CommonConstant.KEY_GENDER, Byte.valueOf(cfiVar.c()));
    }

    private static void d(ckm ckmVar, HiHealthData hiHealthData, cfe cfeVar, cfi cfiVar) {
        if (!dfg.d().b() || ckmVar.t() || ckmVar.getBodyFatRat() <= 0.5f) {
            return;
        }
        d(hiHealthData, cfeVar, new crs(cfiVar.d(), ckmVar.getWeight(), cff.c(cfiVar.c()), cfiVar.a(), ckmVar.getBodyFatRat(), ckmVar.k()));
        a(hiHealthData, ckmVar, cfeVar);
    }

    private static void d(ckm ckmVar, HiHealthData hiHealthData, cfe cfeVar) {
        if (dfg.d().a() || dfg.d().b()) {
            return;
        }
        c(hiHealthData, BleConstants.BMI, Double.valueOf(ckmVar.b()));
        cfeVar.c(ckmVar.b());
        c(hiHealthData, BleConstants.VISCERAL_FAT_LEVEL, Double.valueOf(ckmVar.h()));
        cfeVar.l(ckmVar.h());
        c(hiHealthData, BleConstants.BASAL_METABOLISM, Double.valueOf(ckmVar.a()));
        cfeVar.d(ckmVar.a());
        c(hiHealthData, BleConstants.MOISTURE_RATE, Double.valueOf(ckmVar.m()));
        cfeVar.ai(ckmVar.m());
        c(hiHealthData, BleConstants.BONE_SALT, Double.valueOf(ckmVar.e()));
        cfeVar.j(ckmVar.e());
        c(hiHealthData, "protein", Double.valueOf(ckmVar.l()));
        cfeVar.t(ckmVar.l());
        c(hiHealthData, BleConstants.BODY_AGE, Double.valueOf(ckmVar.d()));
        cfeVar.b(ckmVar.d());
        c(hiHealthData, IndoorEquipManagerApi.KEY_HEART_RATE, Double.valueOf(ckmVar.f()));
        cfeVar.n(ckmVar.f());
    }

    private static void a(HiHealthData hiHealthData, ckm ckmVar, cfe cfeVar) {
        double bodyFatRat = ckmVar.getBodyFatRat();
        cfi cfiVar = c;
        if (cfiVar != null && !TextUtils.equals(cfiVar.i(), ckmVar.n())) {
            double k = ckmVar.k();
            if (k > 0.0d) {
                oi oiVar = new oi();
                oiVar.b(c.d(), ckmVar.getWeight(), cff.c(c.c()), c.a(), (float) k);
                try {
                    bodyFatRat = oiVar.e();
                } catch (og unused) {
                    LogUtil.b("ConverResisToWeightDataUtil", "getBodyFatRate NoAuthException");
                    bodyFatRat = 0.0d;
                }
            }
        }
        if (bodyFatRat > 0.0d) {
            c(hiHealthData, BleConstants.BODY_FAT_RATE, Double.valueOf(bodyFatRat));
            cfeVar.e(bodyFatRat);
        }
    }

    private static void d(HiHealthData hiHealthData, cfe cfeVar, crs crsVar) {
        c(hiHealthData, BleConstants.BMI, Float.valueOf(crsVar.a()));
        c(hiHealthData, BleConstants.BASAL_METABOLISM, Float.valueOf(crsVar.e()));
        c(hiHealthData, BleConstants.BODY_AGE, Float.valueOf(crsVar.d()));
        c(hiHealthData, BleConstants.BODY_SCORE, Float.valueOf(crsVar.j()));
        c(hiHealthData, BleConstants.BONE_SALT, Float.valueOf(crsVar.c()));
        c(hiHealthData, BleConstants.MUSCLE_MASS, Float.valueOf(crsVar.i()));
        c(hiHealthData, "protein", Float.valueOf(crsVar.b()));
        c(hiHealthData, BleConstants.MOISTURE_RATE, Float.valueOf(crsVar.g()));
        c(hiHealthData, BleConstants.VISCERAL_FAT_LEVEL, Float.valueOf(crsVar.k()));
        c(hiHealthData, BleConstants.IMPEDANCE, Float.valueOf(crsVar.f()));
        c(hiHealthData, "skeletalMusclelMass", Float.valueOf(crsVar.h()));
        cfeVar.b(crsVar.d());
        cfeVar.g(crsVar.j());
        cfeVar.t(crsVar.b());
        cfeVar.c(crsVar.a());
        cfeVar.d(crsVar.e());
        cfeVar.q(crsVar.i());
        cfeVar.l(crsVar.k());
        cfeVar.j(crsVar.c());
        cfeVar.ai(crsVar.g());
        cfeVar.ad(crsVar.h());
    }

    private static void a(ckm ckmVar, HiHealthData hiHealthData, cfe cfeVar, cfi cfiVar) {
        float bodyFatRat = ckmVar.getBodyFatRat();
        if (d.booleanValue()) {
            LogUtil.a("ConverResisToWeightDataUtil", "setHagridWeightData sIsRecalculateBodyFatRate is ", d);
            bodyFatRat = 0.0f;
        }
        double d2 = bodyFatRat;
        double d3 = 0.0d;
        if (d2 > 0.0d) {
            ReleaseLogUtil.e("R_Weight_ConverResisToWeightDataUtil", "the bodyFatRat is real");
            d3 = d2 / 100.0d;
        }
        LogUtil.a("ConverResisToWeightDataUtil", "setHagridWeightData scaleBodyFatRat = ", Double.valueOf(d3));
        if (!dfg.d().a() || ckmVar.t()) {
            return;
        }
        double[] d4 = cps.d(ckmVar.o());
        double[] d5 = cps.d(ckmVar.i());
        if (d4.length == 0) {
            LogUtil.h("ConverResisToWeightDataUtil", "resistanceAraay is null or its length is less then zero");
            return;
        }
        if (cps.b(d4)) {
            ReleaseLogUtil.e("R_Weight_ConverResisToWeightDataUtil", "four electrode constructor invoke");
            c(hiHealthData, cfeVar, d4, d5);
            cps d6 = d(ckmVar, cfiVar, d3, d4, d5);
            b(hiHealthData, cfeVar, d6);
            c(hiHealthData, "pole", (Number) 1);
            cfeVar.j(1);
            b(hiHealthData, ckmVar, cfeVar, d6, 1);
        } else {
            ReleaseLogUtil.e("R_Weight_ConverResisToWeightDataUtil", "eight electrode constructor invoke");
            a(hiHealthData, cfeVar, d5);
            cps c2 = c(ckmVar, cfiVar, d3, d4, d5);
            a(hiHealthData, cfeVar, c2);
            d(hiHealthData, cfeVar, d4);
            c(hiHealthData, "pole", (Number) 2);
            cfeVar.j(2);
            b(hiHealthData, ckmVar, cfeVar, c2, 2);
        }
        double f = ckmVar.f();
        c(hiHealthData, IndoorEquipManagerApi.KEY_HEART_RATE, Double.valueOf(f));
        cfeVar.n(f);
    }

    private static cps c(ckm ckmVar, cfi cfiVar, double d2, double[] dArr, double[] dArr2) {
        if (dArr2.length > 0 && cpa.b(dArr2[0])) {
            return new cps(cfiVar.d(), ckmVar.getWeight(), cff.c(cfiVar.c()), cfiVar.a(), cfiVar.g(), 8, dArr, d2, 2, dArr2);
        }
        return new cps(cfiVar.d(), ckmVar.getWeight(), cff.c(cfiVar.c()), cfiVar.a(), cfiVar.g(), dArr, d2);
    }

    private static void a(HiHealthData hiHealthData, cfe cfeVar, double[] dArr) {
        if (dArr.length <= 0 || !cpa.b(dArr[0])) {
            return;
        }
        e(hiHealthData, cfeVar, dArr);
    }

    private static void c(HiHealthData hiHealthData, cfe cfeVar, double[] dArr, double[] dArr2) {
        if (dArr2.length > 0 && cpa.b(dArr2[0])) {
            e(hiHealthData, cfeVar, dArr2);
        }
        if (dArr.length > 0) {
            d(hiHealthData, cfeVar, dArr);
        }
    }

    private static cps d(ckm ckmVar, cfi cfiVar, double d2, double[] dArr, double[] dArr2) {
        if (dArr2.length > 0 && cpa.b(dArr2[0])) {
            return new cps(cfiVar.d(), ckmVar.getWeight(), cff.c(cfiVar.c()), cfiVar.a(), cfiVar.g(), 4, dArr, d2, 1, dArr2);
        }
        return new cps(cfiVar.d(), ckmVar.getWeight(), cff.c(cfiVar.c()), cfiVar.a(), cfiVar.g(), dArr[0], d2);
    }

    private static void a(HiHealthData hiHealthData, String str, ckm ckmVar) {
        MeasurableDevice d2;
        if (ckmVar.q() && (d2 = ceo.d().d(hiHealthData.getDeviceUuid(), true)) != null && cpa.ax(d2.getProductId())) {
            LogUtil.a("ConverResisToWeightDataUtil", "weight data is suspected");
            hiHealthData.setMetaData("-1");
            return;
        }
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        if (str == null) {
            MultiUsersManager.INSTANCE.setCurrentUser(MultiUsersManager.INSTANCE.getMainUser());
            return;
        }
        if (i == null) {
            hiHealthData.setMetaData("0");
        } else if (i.equals(str)) {
            hiHealthData.setMetaData("0");
        } else {
            hiHealthData.setMetaData(str);
        }
    }

    public static void b(HiHealthData hiHealthData, cfe cfeVar, cps cpsVar) {
        if (cpsVar == null) {
            LogUtil.h("ConverResisToWeightDataUtil", "convertHaiGeWeightAlgorithmDataHalf hwWeightAlgorithm is null");
            return;
        }
        float aj = cpsVar.aj();
        c(hiHealthData, BleConstants.MOISTURE, Float.valueOf(aj));
        cfeVar.aa(aj);
        float ak = cpsVar.ak();
        c(hiHealthData, BleConstants.MOISTURE_RATE, Float.valueOf(ak));
        cfeVar.ai(ak);
        float u = cpsVar.u();
        c(hiHealthData, BleConstants.MUSCLE_MASS, Float.valueOf(u));
        cfeVar.q(u);
        float m = cpsVar.m();
        c(hiHealthData, BleConstants.BONE_SALT, Float.valueOf(m));
        cfeVar.j(m);
        float x = cpsVar.x();
        c(hiHealthData, "protein", Float.valueOf(x));
        cfeVar.t(x);
        if (hiHealthData.getDouble(BleConstants.BODY_FAT_RATE) <= 0.0d || d.booleanValue()) {
            d = false;
            float n = cpsVar.n();
            c(hiHealthData, BleConstants.BODY_FAT_RATE, Float.valueOf(n));
            cfeVar.e(n);
        }
        float c2 = cpsVar.c();
        c(hiHealthData, BleConstants.BMI, Float.valueOf(c2));
        cfeVar.c(c2);
        float a2 = cpsVar.a();
        c(hiHealthData, BleConstants.BASAL_METABOLISM, Float.valueOf(a2));
        cfeVar.d(a2);
        float af = cpsVar.af();
        c(hiHealthData, "skeletalMusclelMass", Float.valueOf(af));
        cfeVar.ad(af);
        float as = cpsVar.as();
        c(hiHealthData, BleConstants.VISCERAL_FAT_LEVEL, Float.valueOf(as));
        cfeVar.l(as);
        float ai = cpsVar.ai();
        c(hiHealthData, BleConstants.BODY_SCORE, Float.valueOf(ai));
        cfeVar.g(ai);
        float b = cpsVar.b();
        c(hiHealthData, BleConstants.BODY_AGE, Float.valueOf(b));
        cfeVar.b(b);
        float i = cpsVar.i();
        c(hiHealthData, "bodySize", Float.valueOf(i));
        cfeVar.f(i);
    }

    public static void a(HiHealthData hiHealthData, cfe cfeVar, cps cpsVar) {
        if (cpsVar == null) {
            LogUtil.h("ConverResisToWeightDataUtil", "convertHaiGeWeightAlgorithmData HwWeightAlgorithm is null");
            return;
        }
        float aj = cpsVar.aj();
        c(hiHealthData, BleConstants.MOISTURE, Float.valueOf(aj));
        cfeVar.aa(aj);
        float al = cpsVar.al();
        c(hiHealthData, BleConstants.MOISTURE_RATE, Float.valueOf(al));
        cfeVar.ai(al);
        float w = cpsVar.w();
        c(hiHealthData, BleConstants.MUSCLE_MASS, Float.valueOf(w));
        cfeVar.q(w);
        float k = cpsVar.k();
        c(hiHealthData, BleConstants.BONE_SALT, Float.valueOf(k));
        cfeVar.j(k);
        float v = cpsVar.v();
        c(hiHealthData, "protein", Float.valueOf(v));
        cfeVar.t(v);
        if (hiHealthData.getDouble(BleConstants.BODY_FAT_RATE) <= 0.0d || d.booleanValue()) {
            d = false;
            float h = cpsVar.h();
            c(hiHealthData, BleConstants.BODY_FAT_RATE, Float.valueOf(h));
            cfeVar.e(h);
        }
        float c2 = cpsVar.c();
        c(hiHealthData, BleConstants.BMI, Float.valueOf(c2));
        cfeVar.c(c2);
        e(hiHealthData, cfeVar, cpsVar);
        c(hiHealthData, cfeVar, cpsVar);
    }

    private static void d(HiHealthData hiHealthData, cfe cfeVar, double[] dArr) {
        c(hiHealthData, "lfrfImpedance", Double.valueOf(dArr[0]));
        c(hiHealthData, "lhrhImpedance", Double.valueOf(dArr[1]));
        c(hiHealthData, "lhlfImpedance", Double.valueOf(dArr[2]));
        c(hiHealthData, "lhrfImpedance", Double.valueOf(dArr[3]));
        c(hiHealthData, "rhlfImpedance", Double.valueOf(dArr[4]));
        c(hiHealthData, "rhrfImpedance", Double.valueOf(dArr[5]));
        cfeVar.c(dArr);
    }

    private static void e(HiHealthData hiHealthData, cfe cfeVar, double[] dArr) {
        c(hiHealthData, "lfrfHfImpedance", Double.valueOf(dArr[0]));
        c(hiHealthData, "lhrhHfImpedance", Double.valueOf(dArr[1]));
        c(hiHealthData, "lhlfHfImpedance", Double.valueOf(dArr[2]));
        c(hiHealthData, "lhrfHfImpedance", Double.valueOf(dArr[3]));
        c(hiHealthData, "rhlfHfImpedance", Double.valueOf(dArr[4]));
        c(hiHealthData, "rhrfHfImpedance", Double.valueOf(dArr[5]));
        cfeVar.e(dArr);
        cfeVar.e(2);
    }

    private static void e(HiHealthData hiHealthData, cfe cfeVar, cps cpsVar) {
        if (cpsVar == null) {
            LogUtil.h("ConverResisToWeightDataUtil", "setSegmentationIndicator HwWeightAlgorithm is null");
            return;
        }
        float ac = cpsVar.ac();
        c(hiHealthData, "rightLegMuscleMass", Float.valueOf(ac));
        cfeVar.v(ac);
        float q = cpsVar.q();
        c(hiHealthData, "leftLegMuscleMass", Float.valueOf(q));
        cfeVar.m(q);
        float ag = cpsVar.ag();
        c(hiHealthData, "rightArmMuscleMass", Float.valueOf(ag));
        cfeVar.ab(ag);
        float t = cpsVar.t();
        c(hiHealthData, "leftArmMuscleMass", Float.valueOf(t));
        cfeVar.r(t);
        float aq = cpsVar.aq();
        c(hiHealthData, "trunkMuscleMass", Float.valueOf(aq));
        cfeVar.z(aq);
        float aa = cpsVar.aa();
        c(hiHealthData, "rightLegFatMass", Float.valueOf(aa));
        cfeVar.w(aa);
        float r = cpsVar.r();
        c(hiHealthData, "leftLegFatMass", Float.valueOf(r));
        cfeVar.o(r);
        float ab = cpsVar.ab();
        c(hiHealthData, "rightArmFatMass", Float.valueOf(ab));
        cfeVar.u(ab);
        float s = cpsVar.s();
        c(hiHealthData, "leftArmFatMass", Float.valueOf(s));
        cfeVar.k(s);
        float an = cpsVar.an();
        c(hiHealthData, "trunkFatMass", Float.valueOf(an));
        cfeVar.ac(an);
    }

    private static void c(HiHealthData hiHealthData, cfe cfeVar, cps cpsVar) {
        if (cpsVar == null) {
            LogUtil.h("ConverResisToWeightDataUtil", "setOtherIndicator HwWeightAlgorithm is null");
            return;
        }
        float e = cpsVar.e();
        c(hiHealthData, BleConstants.BASAL_METABOLISM, Float.valueOf(e));
        cfeVar.d(e);
        float ap = cpsVar.ap();
        c(hiHealthData, "waistHipRatio", Float.valueOf(ap));
        cfeVar.ag(ap);
        float ah = cpsVar.ah();
        c(hiHealthData, "skeletalMusclelMass", Float.valueOf(ah));
        cfeVar.ad(ah);
        float y = cpsVar.y();
        c(hiHealthData, "rasm", Float.valueOf(y));
        cfeVar.x(y);
        float ao = cpsVar.ao();
        c(hiHealthData, BleConstants.VISCERAL_FAT_LEVEL, Float.valueOf(ao));
        cfeVar.l(ao);
        float ae = cpsVar.ae();
        c(hiHealthData, BleConstants.BODY_SCORE, Float.valueOf(ae));
        cfeVar.g(ae);
        float d2 = cpsVar.d();
        c(hiHealthData, BleConstants.BODY_AGE, Float.valueOf(d2));
        cfeVar.b(d2);
        float f = cpsVar.f();
        c(hiHealthData, "bodySize", Float.valueOf(f));
        cfeVar.f(f);
        float g = cpsVar.g();
        c(hiHealthData, "bodyShape", Float.valueOf(g));
        cfeVar.h(g);
        float p = cpsVar.p();
        c(hiHealthData, "muscleBalance", Float.valueOf(p));
        cfeVar.p(p);
        float j = cpsVar.j();
        c(hiHealthData, "fatBalance", Float.valueOf(j));
        cfeVar.i(j);
    }

    private static void b(HiHealthData hiHealthData, ckm ckmVar, cfe cfeVar, cps cpsVar, int i) {
        float h;
        LogUtil.a("ConverResisToWeightDataUtil", "mUser is ", c, ", bodyDatas userId is ", ckmVar.n());
        cfi cfiVar = c;
        if (cfiVar == null || TextUtils.equals(cfiVar.i(), ckmVar.n())) {
            return;
        }
        LogUtil.h("ConverResisToWeightDataUtil", "recalculate body fat");
        if (i == 1) {
            h = cpsVar.n();
        } else {
            h = cpsVar.h();
        }
        double d2 = h;
        if (d2 > 0.0d) {
            c(hiHealthData, BleConstants.BODY_FAT_RATE, Double.valueOf(d2));
            cfeVar.e(d2);
        }
    }

    private static void c(HiHealthData hiHealthData, String str, Number number) {
        if (str == null || number == null) {
            ReleaseLogUtil.d("R_Weight_ConverResisToWeightDataUtil", "setWeightPiontDataChecked: value is null or invalid, key ", str);
            return;
        }
        double a2 = CommonUtils.a(number.toString());
        if (a2 <= 0.0d) {
            ReleaseLogUtil.d("R_Weight_ConverResisToWeightDataUtil", "setWeightPiontDataChecked: value <= 0.0 for key ", str);
        } else if (hiHealthData != null) {
            hiHealthData.putDouble(str, a2);
        }
    }

    public static void e(Boolean bool) {
        d = bool;
    }
}
