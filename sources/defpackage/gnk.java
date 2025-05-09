package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.bloodpressure.BloodPressureApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class gnk {
    public static void b(DeviceInfo deviceInfo) {
        LogUtil.a("HealthGlanceHelper", "device is : ", deviceInfo);
        a(deviceInfo, j());
    }

    public static boolean a(DeviceInfo deviceInfo, gmv gmvVar) {
        ReleaseLogUtil.e("R_HealthGlanceHelper", "sendHealthGlanceSampleEvent");
        String e = HiJsonUtil.e(gmvVar);
        LogUtil.a("HealthGlanceHelper", "healthGlanceDataResult json is : ", e);
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.healthGlanceMgr");
        cvpVar.setWearPkgName("hw.watch.health.healthcheck");
        cvpVar.setCommandId(2);
        cvpVar.a(80030006L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(cvx.c(e)));
        LogUtil.a("HealthGlanceHelper", "sendHealthGlanceSampleEvent SampleEvent: ", cvpVar.toString());
        return cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "HealthGlanceHelper");
    }

    private static gmv j() {
        LogUtil.a("HealthGlanceHelper", "getHealthGlance start");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        gmv gmvVar = new gmv();
        b(gmvVar);
        b(countDownLatch, gmvVar);
        try {
            ReleaseLogUtil.e("R_HealthGlanceHelper", "getHealthGlance is ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("R_HealthGlanceHelper", "getHealthGlance interrupted");
        }
        LogUtil.a("HealthGlanceHelper", "getHealthGlance is " + gmvVar);
        return gmvVar;
    }

    public static void b(gmv gmvVar) {
        LogUtil.a("HealthGlanceHelper", "getHeight start");
        UserInfomation userInfoFromDbSync = LoginInit.getInstance(BaseApplication.e()).getUserInfoFromDbSync();
        if (userInfoFromDbSync == null) {
            LogUtil.h("HealthGlanceHelper", "userInformation is null");
            return;
        }
        int height = userInfoFromDbSync.getHeight();
        LogUtil.a("HealthGlanceHelper", "height is : ", Integer.valueOf(height));
        if (height != 0) {
            gmvVar.c(Integer.valueOf(height));
        }
    }

    private static List<HiDataReadProOption> i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(d());
        arrayList.add(c());
        arrayList.add(b());
        arrayList.add(a());
        if (Utils.o()) {
            arrayList.add(e());
        }
        return arrayList;
    }

    private static HiDataReadProOption d() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.b(System.currentTimeMillis(), -30), System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2004});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).d(true).e();
    }

    private static HiDataReadProOption c() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.b(System.currentTimeMillis(), -30), System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.BODY_FAT_RATE.value()});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).e();
    }

    private static HiDataReadProOption b() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.b(System.currentTimeMillis(), -30), System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2006, 2007});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).e();
    }

    private static HiDataReadProOption a() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.b(System.currentTimeMillis(), -30), System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.FATTY_LIVER_RISK.value(), DicDataTypeUtil.DataType.FATTY_LIVER_GRADE.value()});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).e();
    }

    private static HiDataReadProOption e() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.b(System.currentTimeMillis(), -30), System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.OSA_LEVEL.value()});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).e();
    }

    public static void b(final CountDownLatch countDownLatch, final gmv gmvVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gnl
            @Override // java.lang.Runnable
            public final void run() {
                gnk.d(countDownLatch, gmvVar);
            }
        });
    }

    static /* synthetic */ void d(final CountDownLatch countDownLatch, final gmv gmvVar) {
        LogUtil.a("HealthGlanceHelper", "getLatestHealthGlanceData start");
        HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).readHiHealthDataEx(i(), new HiDataReadResultListener() { // from class: gnk.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HealthGlanceHelper", "getLatestHealthGlanceData error code ", Integer.valueOf(i));
                if (i == 0) {
                    if (!(obj instanceof SparseArray)) {
                        countDownLatch.countDown();
                        return;
                    }
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        gnk.aPz_(sparseArray, gmvVar);
                        gnk.aPw_(sparseArray, gmvVar);
                        gnk.aPv_(sparseArray, gmvVar);
                        gnk.aPx_(sparseArray, gmvVar);
                        gnk.aPy_(sparseArray, gmvVar);
                    } else {
                        LogUtil.h("HealthGlanceHelper", "sparseArray data is null");
                        countDownLatch.countDown();
                        return;
                    }
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPz_(SparseArray<Object> sparseArray, gmv gmvVar) {
        List arrayList = new ArrayList();
        Object obj = sparseArray.get(2004);
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthGlanceHelper", "getLatestWeight is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        double value = hiHealthData.getValue();
        LogUtil.a("HealthGlanceHelper", "getLatestWeight weight is : ", Double.valueOf(value));
        if (value != 0.0d) {
            gmvVar.d(Float.valueOf((float) value));
            gmvVar.c(Long.valueOf(hiHealthData.getStartTime()));
        }
        if (gmvVar.b().intValue() == 0 || gmvVar.a().floatValue() == 0.0f) {
            return;
        }
        double a2 = UnitUtil.a(gmvVar.a().floatValue() / Math.pow(gmvVar.b().intValue() / 100.0d, 2.0d), 1);
        LogUtil.a("HealthGlanceHelper", "getLatestWeight bmi is : ", Double.valueOf(a2));
        gmvVar.e(Float.valueOf((float) a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPw_(SparseArray<Object> sparseArray, gmv gmvVar) {
        long j;
        double d;
        List arrayList = new ArrayList();
        Object obj = sparseArray.get(DicDataTypeUtil.DataType.BODY_FAT_RATE.value());
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthGlanceHelper", "getFatPercentage is empty");
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            HiHealthData hiHealthData = (HiHealthData) arrayList.get(i);
            if (TextUtils.equals(hiHealthData.getMetaData(), "NULL") || TextUtils.equals(hiHealthData.getMetaData(), "0")) {
                d = hiHealthData.getValue();
                j = hiHealthData.getStartTime();
                break;
            }
        }
        j = 0;
        d = 0.0d;
        LogUtil.a("HealthGlanceHelper", "getLatestFatPercentage is ： ", Double.valueOf(d));
        if (d != 0.0d) {
            gmvVar.b(Float.valueOf(String.valueOf(d)));
            gmvVar.b(Long.valueOf(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPv_(SparseArray<Object> sparseArray, gmv gmvVar) {
        List arrayList = new ArrayList();
        Object obj = sparseArray.get(2006);
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthGlanceHelper", "getLatestHighBloodPressureList is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        double value = hiHealthData.getValue();
        List arrayList2 = new ArrayList();
        Object obj2 = sparseArray.get(2007);
        if (koq.e(obj2, HiHealthData.class)) {
            arrayList2 = (List) obj2;
        }
        if (koq.b(arrayList2)) {
            LogUtil.h("HealthGlanceHelper", "getLatestLowBloodPressureList is empty");
            return;
        }
        double value2 = ((HiHealthData) arrayList2.get(0)).getValue();
        LogUtil.a("HealthGlanceHelper", "highBloodPressure is : ", Double.valueOf(value), " lowBloodPressure is : ", Double.valueOf(value2));
        if (value == 0.0d || value2 == 0.0d) {
            return;
        }
        int i = (int) value;
        gmvVar.f(Integer.valueOf(i));
        int i2 = (int) value2;
        gmvVar.b(Integer.valueOf(i2));
        BloodPressureApi bloodPressureApi = (BloodPressureApi) Services.c("Main", BloodPressureApi.class);
        if (bloodPressureApi != null) {
            gmvVar.a(Integer.valueOf(bloodPressureApi.getBloodPressureGradeType(i, i2)));
        }
        gmvVar.d((Integer) 0);
        gmvVar.d(Long.valueOf(hiHealthData.getStartTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPx_(SparseArray<Object> sparseArray, gmv gmvVar) {
        List arrayList = new ArrayList();
        Object obj = sparseArray.get(DicDataTypeUtil.DataType.FATTY_LIVER_GRADE.value());
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthGlanceHelper", "getLatestFattyLiverGrade is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        double value = hiHealthData.getValue();
        List arrayList2 = new ArrayList();
        Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.FATTY_LIVER_RISK.value());
        if (koq.e(obj2, HiHealthData.class)) {
            arrayList2 = (List) obj2;
        }
        if (koq.b(arrayList2)) {
            LogUtil.h("HealthGlanceHelper", "getLatestFattyLiverRiskList is empty");
            return;
        }
        double value2 = ((HiHealthData) arrayList2.get(0)).getValue();
        LogUtil.a("HealthGlanceHelper", "fattyLiverGrade is : ", Double.valueOf(value), " fattyLiverRisk is : ", Double.valueOf(value2));
        if (value == 0.0d || value2 == 0.0d) {
            return;
        }
        gmvVar.c(Float.valueOf(String.valueOf(value)));
        gmvVar.e(Integer.valueOf((int) value2));
        gmvVar.e(Long.valueOf(hiHealthData.getStartTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aPy_(SparseArray<Object> sparseArray, gmv gmvVar) {
        if (!Utils.o()) {
            LogUtil.a("HealthGlanceHelper", "is not oversea , don't need to send sleepApnea data to device");
            return;
        }
        List arrayList = new ArrayList();
        Object obj = sparseArray.get(DicDataTypeUtil.DataType.OSA_LEVEL.value());
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthGlanceHelper", "getLatest sleepApneaGrade is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        double value = hiHealthData.getValue();
        LogUtil.a("HealthGlanceHelper", "sleepApneaGrade is : ", Double.valueOf(value));
        if (value != 0.0d) {
            gmvVar.j(Integer.valueOf((int) value));
            gmvVar.a(Long.valueOf(hiHealthData.getStartTime()));
        }
    }
}
