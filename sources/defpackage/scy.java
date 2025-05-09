package defpackage;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDeviceInfo;
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
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class scy {
    private static scy b;

    /* renamed from: a, reason: collision with root package name */
    private Context f17028a = BaseApplication.getContext();

    public static scy c() {
        if (b == null) {
            b = new scy();
        }
        return b;
    }

    public void b() {
        LogUtil.a("HealthMockUtil", "insertBloodOxygenData");
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(2103);
        hiHealthData.setStartTime(System.currentTimeMillis());
        hiHealthData.setEndTime(System.currentTimeMillis());
        hiHealthData.setValue(c(96, 99));
        hiHealthData.setDeviceUuid("-1");
        e(Arrays.asList(hiHealthData), new IBaseResponseCallback() { // from class: scy.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HealthMockUtil", "insertBloodOxygenData errCode = ", Integer.valueOf(i));
            }
        });
    }

    public void g() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertSleepWeekData inter");
            e(d(0, 1), new IBaseResponseCallback() { // from class: scy.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertSleepWeekData err_code = " + i);
                }
            });
        }
    }

    public void h() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertSleepMonthData inter");
            e(d(1, 4), new IBaseResponseCallback() { // from class: scy.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertSleepMonthData err_code = " + i);
                }
            });
        }
    }

    public void f() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertWeightWeekDatas inter");
            e(b(0, 1), new IBaseResponseCallback() { // from class: scy.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertWeightWeekDatas err_code = " + i);
                }
            });
        }
    }

    public void i() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertWeightMonthData inter");
            e(b(1, 4), new IBaseResponseCallback() { // from class: scy.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertWeightMonthData err_code = " + i);
                }
            });
        }
    }

    public void e() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertPressureWeekData inter");
            e(e(0, 1), new IBaseResponseCallback() { // from class: scy.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertPressureWeekData err_code = " + i);
                }
            });
        }
    }

    public void a() {
        if (CommonUtil.bu()) {
            LogUtil.a("HealthMockUtil", "insertPressureMonthData inter");
            e(e(1, 4), new IBaseResponseCallback() { // from class: scy.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertPressureMonthData err_code = " + i);
                }
            });
        }
    }

    private List<HiHealthData> e(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i < i2) {
            for (int i3 = 0; i3 < 7; i3++) {
                if (i == 0 && i3 == 0) {
                    arrayList.addAll(j());
                } else {
                    arrayList.addAll(a(i, i3));
                }
            }
            i++;
        }
        return arrayList;
    }

    private List<HiHealthData> d(int i, int i2) {
        ArrayList<HiHealthData> c;
        ArrayList arrayList = new ArrayList();
        long d = jec.d(System.currentTimeMillis());
        LogUtil.a("HealthMockUtil", "getInsertSleepHiHealthDatas startTime = ", new Date(d));
        for (int i3 = i; i3 < i2; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                if (i3 == 0 && i4 == 0) {
                    c = c(d, d - 6300000, c(99));
                } else {
                    long j = d - (((i3 * 7) + i4) * 86400000);
                    c = c(j, (j - (c(0, 3) * 3600000)) + (3600000 / c(1, 20)), c(c(0, 6)));
                }
                arrayList.addAll(c);
            }
        }
        return arrayList;
    }

    private String c(int i) {
        String str;
        InputStream e;
        str = "";
        InputStream inputStream = null;
        try {
            try {
                e = moh.e("health", "sleepdata" + String.valueOf(i) + ".txt");
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        LogUtil.b("HealthMockUtil", "getBetaFile e is ", e2.getMessage());
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            LogUtil.b("HealthMockUtil", "getBetaFile e is ", e3.getMessage());
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    LogUtil.b("HealthMockUtil", "getBetaFile e is ", e4.getMessage());
                }
            }
        }
        if (e == null) {
            if (e != null) {
                try {
                    e.close();
                } catch (IOException e5) {
                    LogUtil.b("HealthMockUtil", "getBetaFile e is ", e5.getMessage());
                }
            }
            return "";
        }
        byte[] bArr = new byte[e.available()];
        str = e.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
        if (e != null) {
            try {
                e.close();
            } catch (IOException e6) {
                LogUtil.b("HealthMockUtil", "getBetaFile e is ", e6.getMessage());
            }
        }
        return str;
    }

    private List<HiHealthData> b(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        long d = jec.d(System.currentTimeMillis());
        LogUtil.a("HealthMockUtil", "getInsertWeightHihealthDatas startTime = " + new Date(d));
        while (i < i2) {
            for (int i3 = 0; i3 < 7; i3++) {
                long j = d - (((i * 7) + i3) * 86400000);
                float c = c(68 - i, 70 - i);
                float c2 = c(5, 30);
                arrayList.add(a(28800000 + j, c * 0.98f, 0.98f * c2));
                arrayList.add(a(43200000 + j, c, c2));
                arrayList.add(a(j + 72000000, c * 0.99f, c2 * 0.99f));
            }
            i++;
        }
        return arrayList;
    }

    public static void d() {
        LogUtil.a("HealthMockUtil", "registerDeviceToHiHealth enter");
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(57);
        hiDeviceInfo.setDeviceUniqueCode("-1");
        hiDeviceInfo.setDeviceName("CH18");
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        HiHealthManager.d(BaseApplication.getContext()).registerDataClient(hiDeviceInfo, arrayList, null);
    }

    private HiHealthData a(long j, float f, float f2) {
        HiHealthData hiHealthData = new HiHealthData(10006);
        d();
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setMetaData("0");
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j);
        hiHealthData.putDouble("age", 29.0d);
        hiHealthData.putDouble("height", 176.0d);
        hiHealthData.putDouble(CommonConstant.KEY_GENDER, 1.0d);
        hiHealthData.putDouble("bodyWeight", f);
        hiHealthData.putDouble(BleConstants.BODY_FAT_RATE, f2);
        crs crsVar = new crs(176.0f, f, (byte) 1, 29, f2, 0.0d);
        hiHealthData.putDouble(BleConstants.BMI, crsVar.a());
        hiHealthData.putDouble(BleConstants.BASAL_METABOLISM, crsVar.e());
        hiHealthData.putDouble(BleConstants.BODY_AGE, crsVar.d());
        hiHealthData.putDouble(BleConstants.BODY_SCORE, crsVar.j());
        hiHealthData.putDouble(BleConstants.BONE_SALT, crsVar.c());
        hiHealthData.putDouble(BleConstants.MUSCLE_MASS, crsVar.i());
        hiHealthData.putDouble("protein", crsVar.b());
        hiHealthData.putDouble(BleConstants.MOISTURE_RATE, crsVar.g());
        hiHealthData.putDouble(BleConstants.VISCERAL_FAT_LEVEL, crsVar.k());
        hiHealthData.putDouble(BleConstants.IMPEDANCE, crsVar.f());
        hiHealthData.putDouble("skeletalMusclelMass", crsVar.h());
        return hiHealthData;
    }

    private void e(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        if (list == null) {
            LogUtil.a("HealthMockUtil", "insertMockWeightDatas,hiHealthDataList is invaild");
        } else {
            if (list.size() <= 0) {
                LogUtil.a("HealthMockUtil", "insertMockWeightDatas,hiHealthDataList's size is invaild");
                return;
            }
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(list);
            a(hiDataInsertOption, iBaseResponseCallback, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        if (i < 0) {
            LogUtil.a("HealthMockUtil", "tryTime < 0 ");
        } else {
            HiHealthNativeApi.a(this.f17028a).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: scy.10
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("HealthMockUtil", "insertHiHealthData errorCode = " + i2);
                    iBaseResponseCallback.d(i2, null);
                    if (i2 != 0) {
                        int i3 = i - 1;
                        LogUtil.a("HealthMockUtil", "count = " + i3);
                        scy.this.a(hiDataInsertOption, iBaseResponseCallback, i3);
                    }
                }
            });
        }
    }

    private List<HiHealthData> j() {
        int[] iArr = {35, 33, 30, 24, 25, 30, 31, 35, 37, 40, 45, 50, 55, 37, 62, 33, 68, 70, 85, 83, 75, 55, 30, 52, 50, 48, 20, 43, 25, 45, 39, 38, 35, 30, 15};
        ArrayList arrayList = new ArrayList();
        long d = jec.d(System.currentTimeMillis()) + 14400000;
        for (int i = 0; i < 35; i++) {
            int i2 = iArr[i];
            arrayList.add(e(d, i2, i2));
            d += 1800000;
        }
        return arrayList;
    }

    private List<HiHealthData> a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        long d = jec.d(System.currentTimeMillis() - (((i * 7) + i2) * 86400000));
        long currentTimeMillis = System.currentTimeMillis();
        int i3 = 1;
        for (long j = d; j < currentTimeMillis - 1800000; j += i3 * AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING * 1000) {
            if (j < 28800000 + d) {
                arrayList.add(e(j, 30, 15));
            } else {
                if (j < 79200000 + d) {
                    i3 = c(1, 4);
                    arrayList.add(e(j, 90, 50));
                } else if (j < d + 86400000) {
                    arrayList.add(e(j, 60, 30));
                }
            }
            i3 = 1;
        }
        return arrayList;
    }

    private HiHealthData e(long j, int i, int i2) {
        HiHealthData hiHealthData = new HiHealthData();
        HiStressMetaData hiStressMetaData = new HiStressMetaData();
        int c = c(i2, i);
        hiStressMetaData.configStressStartTime(j);
        hiStressMetaData.configStressEndTime(j);
        hiStressMetaData.configStressScore(c);
        hiStressMetaData.configStressMeasureType(1);
        hiHealthData.setValue(c);
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setTimeInterval(j, j);
        hiHealthData.setType(2034);
        hiHealthData.setMetaData(HiJsonUtil.e(hiStressMetaData));
        return hiHealthData;
    }

    public static int c(int i, int i2) {
        return (new SecureRandom().nextInt(i2) % ((i2 - i) + 1)) + i;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0151 A[Catch: Exception -> 0x01aa, RuntimeException -> 0x01c2, TryCatch #2 {RuntimeException -> 0x01c2, Exception -> 0x01aa, blocks: (B:3:0x0022, B:4:0x002d, B:6:0x0036, B:9:0x004e, B:11:0x014b, B:13:0x0151, B:15:0x0157, B:17:0x015d, B:22:0x0183, B:25:0x016c, B:27:0x0182, B:29:0x0059, B:31:0x005f, B:32:0x006a, B:34:0x0070, B:35:0x007b, B:37:0x0081, B:38:0x0099, B:40:0x009f, B:42:0x00bc, B:44:0x00c4, B:45:0x00df, B:47:0x00e5, B:48:0x00ff, B:50:0x0105, B:53:0x0122, B:57:0x0193), top: B:2:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x016c A[Catch: Exception -> 0x01aa, RuntimeException -> 0x01c2, LOOP:1: B:24:0x016a->B:25:0x016c, LOOP_END, TryCatch #2 {RuntimeException -> 0x01c2, Exception -> 0x01aa, blocks: (B:3:0x0022, B:4:0x002d, B:6:0x0036, B:9:0x004e, B:11:0x014b, B:13:0x0151, B:15:0x0157, B:17:0x015d, B:22:0x0183, B:25:0x016c, B:27:0x0182, B:29:0x0059, B:31:0x005f, B:32:0x006a, B:34:0x0070, B:35:0x007b, B:37:0x0081, B:38:0x0099, B:40:0x009f, B:42:0x00bc, B:44:0x00c4, B:45:0x00df, B:47:0x00e5, B:48:0x00ff, B:50:0x0105, B:53:0x0122, B:57:0x0193), top: B:2:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.ArrayList<com.huawei.hihealth.HiHealthData> c(long r32, long r34, java.lang.String r36) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.scy.c(long, long, java.lang.String):java.util.ArrayList");
    }

    private HiHealthData b(long j, int i, double d) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(i);
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(HiDateUtil.d(j));
        hiHealthData.setEndTime(HiDateUtil.d(j));
        hiHealthData.setValue(d);
        return hiHealthData;
    }
}
