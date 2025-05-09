package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.EcgMetaData;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kcv {
    private static long c = -1;

    private static boolean c(long j) {
        return j >= 32;
    }

    public static void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        kda kdaVar;
        ReleaseLogUtil.e("DEVMGR_ParseEcgFileUtil", "saveEcgFileData enter");
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "saveEcgFileData callback is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "path is empty");
            iBaseResponseCallback.d(-2, -1);
            return;
        }
        try {
            kdaVar = b(str);
        } catch (RuntimeException unused) {
            ReleaseLogUtil.c("DEVMGR_ParseEcgFileUtil", "ecg file is wrong. please check.");
            kdaVar = null;
        }
        if (kdaVar == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecgFileData is null");
            iBaseResponseCallback.d(255, -1);
            return;
        }
        c = -1L;
        List<kdd> d = kdaVar.d();
        if (d == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecgDataPackList is null");
            iBaseResponseCallback.d(255, -1);
            return;
        }
        if (!d(d)) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecg endTime is Invalid");
            iBaseResponseCallback.d(-1, Long.valueOf(c));
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        String c2 = kdaVar.c();
        try {
            a(arrayList, d, kdaVar.a(), c2);
        } catch (OutOfMemoryError unused2) {
            LogUtil.b("ParseEcgFileUtil", "setHealthDataList failed");
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        c(str);
        if ("ecg_analysis_data.bin".equals(c2)) {
            c(hiDataInsertOption, iBaseResponseCallback);
        } else {
            hiDataInsertOption.setPackageName(kdaVar.a());
            d(hiDataInsertOption, iBaseResponseCallback);
        }
    }

    private static void c(String str) {
        File file = new File(str);
        boolean exists = file.exists();
        LogUtil.a("ParseEcgFileUtil", "deleteEcgFile, isExists:", Boolean.valueOf(exists));
        if (exists) {
            LogUtil.a("ParseEcgFileUtil", "deleteEcgFile, isDeleteSuccess:", Boolean.valueOf(file.delete()));
        }
    }

    private static void d(HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kcv.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_ParseEcgFileUtil", "saveData insertHiHealthData onResult type:", Integer.valueOf(i));
                if (i == 0) {
                    IBaseResponseCallback.this.d(0, Long.valueOf(kcv.c));
                    kdc.a(15);
                } else {
                    IBaseResponseCallback.this.d(i, -1);
                }
            }
        });
    }

    private static void c(HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kcv.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_ParseEcgFileUtil", "saveEcgAnalysisData insertHiHealthData onResult type:", Integer.valueOf(i));
                if (i == 0) {
                    IBaseResponseCallback.this.d(0, Long.valueOf(kcv.c));
                    kdc.a(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value());
                } else {
                    IBaseResponseCallback.this.d(i, -1);
                }
            }
        });
    }

    private static boolean d(List<kdd> list) {
        if (list == null) {
            LogUtil.h("ParseEcgFileUtil", "ecgDataPackList is null");
            return false;
        }
        for (kdd kddVar : list) {
            if (kddVar == null) {
                LogUtil.h("ParseEcgFileUtil", "saveEcgFileData ecgDataPack is null");
            } else if (c < kddVar.a()) {
                c = kddVar.a();
            }
        }
        if (c >= 0) {
            return true;
        }
        LogUtil.h("ParseEcgFileUtil", "Invalid end Time");
        return false;
    }

    public static int a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_last_time");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            String d = d();
            JSONObject jSONObject = new JSONObject(b);
            if (!jSONObject.has(d) || jSONObject.isNull(d)) {
                return 0;
            }
            return jSONObject.getInt(d);
        } catch (JSONException unused) {
            LogUtil.b("ParseEcgFileUtil", "getLastTime JSONException");
            return 0;
        }
    }

    public static void d(int i) {
        JSONObject jSONObject;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_last_time");
        String d = d();
        try {
            if (TextUtils.isEmpty(b)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(b);
            }
            jSONObject.put(d, i);
            SharedPreferenceManager.e(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_last_time", jSONObject.toString(), new StorageParams(1));
        } catch (JSONException unused) {
            LogUtil.b("ParseEcgFileUtil", "saveLastTime JSONException error");
        }
    }

    public static int c() {
        String str;
        try {
            str = SharedPreferenceManager.b(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_any_last_time");
        } catch (OutOfMemoryError unused) {
            ReleaseLogUtil.c("DEVMGR_ParseEcgFileUtil", "getEcgAnalysisLastTime OutOfMemoryError");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            String d = d();
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(d) || jSONObject.isNull(d)) {
                return 0;
            }
            return jSONObject.getInt(d);
        } catch (JSONException unused2) {
            LogUtil.b("ParseEcgFileUtil", "getEcgAnalysisLastTime JSONException");
            return 0;
        }
    }

    public static void c(int i) {
        JSONObject jSONObject;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_any_last_time");
        String d = d();
        try {
            if (TextUtils.isEmpty(b)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(b);
            }
            jSONObject.put(d, i);
            SharedPreferenceManager.e(BaseApplication.getContext(), "ecg_time_sp_tag", "ecg_any_last_time", jSONObject.toString(), new StorageParams(1));
        } catch (JSONException unused) {
            LogUtil.b("ParseEcgFileUtil", "saveEcgAnalysisLastTime JSONException error");
        }
    }

    private static String d() {
        DeviceInfo e = e();
        String securityDeviceId = e != null ? e.getSecurityDeviceId() : "";
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        return (TextUtils.isEmpty(e2) ? "" : e2) + securityDeviceId;
    }

    private static void a(List<HiHealthData> list, List<kdd> list2, String str, String str2) {
        if (list == null || list2 == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "setHealthDataList healthDataList or ecgDataPackList is null");
            return;
        }
        DeviceInfo e = e();
        String str3 = (e == null || e.getSecurityUuid() == null) ? "" : e.getSecurityUuid() + "#ANDROID21";
        for (kdd kddVar : list2) {
            if (kddVar == null) {
                ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "setHealthDataList ecgDataPack is null");
            } else {
                long h = kddVar.h();
                long a2 = kddVar.a();
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(h * 1000);
                hiHealthData.setEndTime(a2 * 1000);
                EcgMetaData ecgMetaData = new EcgMetaData();
                ecgMetaData.setEcgArrhyType(kddVar.d());
                ecgMetaData.setAverageHeartRate(kddVar.b());
                ecgMetaData.setEcgDataLength(kddVar.i());
                ecgMetaData.setUserSymptom(kddVar.j());
                ecgMetaData.setEcgAppVersion(kddVar.c());
                ecgMetaData.setPackageName(str);
                ecgMetaData.setDataSources("HUAWEI");
                List<Float> e2 = kddVar.e();
                List<Float> c2 = c(e2);
                String e3 = HiJsonUtil.e(ecgMetaData);
                LogUtil.c("ParseEcgFileUtil", "ecgMetaDataString: ", e3);
                hiHealthData.setMetaData(e3);
                hiHealthData.setSimpleData(HiJsonUtil.e(c2));
                if ("ecg_analysis_data.bin".equals(str2)) {
                    hiHealthData.setType(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value());
                } else {
                    hiHealthData.setType(31001);
                }
                hiHealthData.setDeviceUuid(str3);
                String e4 = HiJsonUtil.e(e2);
                LogUtil.c("ParseEcgFileUtil", "sequenceDataString: ", e4);
                hiHealthData.setSequenceData(e4);
                list.add(hiHealthData);
            }
        }
    }

    private static DeviceInfo e() {
        List<DeviceInfo> deviceList = jsz.b(BaseApplication.getContext()).getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ParseEcgFileUtil");
        if (deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }

    private static List<Float> c(List<Float> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list == null) {
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i % 4 == 0) {
                arrayList.add(list.get(i));
            }
            if (i >= 1200) {
                break;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static kda b(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2;
        kda kdaVar;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                File file = new File(str);
                if (!CommonUtil.c(file)) {
                    ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Invalid file path");
                    IoUtils.e((Closeable) null);
                    return null;
                }
                long length = file.length();
                if (!c(length)) {
                    ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecg file is wrong. check file header wrong.");
                    IoUtils.e((Closeable) null);
                    return null;
                }
                fileInputStream2 = FileUtils.openInputStream(file);
                try {
                    try {
                        kdaVar = new kda();
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.e(fileInputStream2);
                        throw th;
                    }
                } catch (IOException unused) {
                }
                try {
                    byte[] bArr = new byte[32];
                    int read = fileInputStream2.read(bArr);
                    kdaVar.a(file.getName());
                    if (read != 32) {
                        ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Unable to read header data");
                        IoUtils.e(fileInputStream2);
                        return kdaVar;
                    }
                    c(bArr, kdaVar);
                    if (!e(kdaVar.b(), length)) {
                        ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "check packageName wrong.");
                        IoUtils.e(fileInputStream2);
                        return kdaVar;
                    }
                    c(kdaVar, fileInputStream2);
                    if (kdaVar.e() != 0) {
                        LogUtil.h("ParseEcgFileUtil", "no support version");
                    } else {
                        e(fileInputStream2, kdaVar);
                    }
                    IoUtils.e(fileInputStream2);
                    return kdaVar;
                } catch (IOException unused2) {
                    fileInputStream3 = kdaVar;
                    FileInputStream fileInputStream4 = fileInputStream3;
                    fileInputStream3 = fileInputStream2;
                    fileInputStream = fileInputStream4;
                    ReleaseLogUtil.c("DEVMGR_ParseEcgFileUtil", "parseFileData IOException");
                    IoUtils.e(fileInputStream3);
                    return fileInputStream;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream2 = null;
            }
        } catch (IOException unused3) {
            fileInputStream = null;
        }
    }

    private static void e(FileInputStream fileInputStream, kda kdaVar) throws IOException {
        while (true) {
            byte[] bArr = new byte[56];
            int read = fileInputStream.read(bArr);
            if (read != 56) {
                if (read == -1) {
                    ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "End of parsing data");
                    return;
                } else {
                    ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Profile data length mismatch: ", Integer.valueOf(read));
                    return;
                }
            }
            kdd d = d(bArr, 56, kdaVar);
            if (d != null) {
                byte[] bArr2 = new byte[d.i()];
                int read2 = fileInputStream.read(bArr2);
                if (read2 == d.i()) {
                    LogUtil.a("ParseEcgFileUtil", "ecgDataPack.getRawDataLength: ", Integer.valueOf(d.i()));
                    a(bArr2, d);
                } else {
                    ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Raw data length mismatch rawDataLength:", Integer.valueOf(read2));
                }
                if (kdaVar != null && kdaVar.d() != null) {
                    kdaVar.d().add(d);
                }
                LogUtil.a("ParseEcgFileUtil", "Start parsing next");
            } else {
                ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecgDataPack is null");
                return;
            }
        }
    }

    private static void e(String str, kda kdaVar) {
        int a2;
        LogUtil.a("ParseEcgFileUtil", "check time.");
        if ("ecg_analysis_data.bin".equals(kdaVar.c())) {
            a2 = c();
        } else {
            a2 = a();
        }
        long j = a2 * 1000;
        long currentTimeMillis = System.currentTimeMillis();
        long y = CommonUtil.y(str) * 1000;
        ReleaseLogUtil.e("DEVMGR_ParseEcgFileUtil", "checkTime lastTime: ", Long.valueOf(j), ", nowTime: ", Long.valueOf(currentTimeMillis), ", time: ", Long.valueOf(y));
        if (y < j || y > currentTimeMillis) {
            LogUtil.h("ParseEcgFileUtil", "wrong data.");
            throw new RuntimeException("wrong timeString : " + str);
        }
    }

    private static void d(String str, String str2) {
        if (CommonUtil.y(str) <= CommonUtil.y(str2)) {
            return;
        }
        ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "checkStartTimeEndTime wrong data.");
        throw new RuntimeException("wrong startTimeString : " + str + "endTimeString : " + str2);
    }

    private static void a(byte[] bArr, kdd kddVar) {
        if (bArr == null || kddVar == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "parseRawData rawData or ecgDataPack is null");
            return;
        }
        LogUtil.a("ParseEcgFileUtil", "parseRawData");
        ArrayList arrayList = new ArrayList(16);
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 4;
            Float valueOf = Float.valueOf(cvx.i(c(bArr, i, i2)));
            if (valueOf.isNaN()) {
                ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "unitStringFloat isNaN");
                valueOf = Float.valueOf(0.0f);
            }
            arrayList.add(valueOf);
            i = i2;
        }
        kddVar.c(arrayList);
    }

    private static kdd d(byte[] bArr, int i, kda kdaVar) {
        LogUtil.a("ParseEcgFileUtil", "parseSummaryData");
        if (bArr == null || bArr.length != i) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "bytes is error argument");
            return null;
        }
        kdd kddVar = new kdd();
        blt.d("ParseEcgFileUtil", bArr, "bytes: ");
        String c2 = c(bArr, 0, 4);
        e(c2, kdaVar);
        kddVar.c(CommonUtil.y(c2));
        String c3 = c(bArr, 4, 8);
        e(c3, kdaVar);
        d(c2, c3);
        kddVar.b(CommonUtil.y(c3));
        kddVar.b(a(c(bArr, 8, 12)));
        kddVar.a(cvx.e(c(bArr, 12, 44)).trim());
        kddVar.a(CommonUtil.y(c(bArr, 44, 48)));
        kddVar.c(CommonUtil.w(c(bArr, 48, 50)));
        kddVar.d(CommonUtil.y(c(bArr, 52, 56)));
        return kddVar;
    }

    private static int a(String str) {
        long y = CommonUtil.y(str);
        if (y >= 2147483647L || y <= 0) {
            LogUtil.h("ParseEcgFileUtil", "rawDataLength is invalid:", Long.valueOf(y));
        } else {
            int i = (int) y;
            if (i <= 2400000) {
                return i;
            }
            LogUtil.h("ParseEcgFileUtil", "rawDataLength is too large:", Long.valueOf(y));
        }
        return 0;
    }

    private static void c(byte[] bArr, kda kdaVar) {
        LogUtil.a("ParseEcgFileUtil", "parseHeaderCommon enter");
        if (bArr == null) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Header is null");
            return;
        }
        blt.d("ParseEcgFileUtil", bArr, "header: ");
        if (bArr.length != 32) {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "Wrong length of file header");
            return;
        }
        kdaVar.b(CommonUtil.y(c(bArr, 0, 4)));
        kdaVar.d(jds.c(c(bArr, 4, 6), 16));
        String c2 = c(bArr, 6, 8);
        LogUtil.a("ParseEcgFileUtil", "fileVersionString :", c2);
        kdaVar.c(jds.c(c2, 16));
        String c3 = c(bArr, 8, 9);
        LogUtil.a("ParseEcgFileUtil", "fileVersionString :", c3);
        kdaVar.e(jds.c(c3, 16));
        kdaVar.e(cvx.e(c(bArr, 9, 32)));
    }

    private static String c(byte[] bArr, int i, int i2) {
        if (bArr == null || i > i2) {
            LogUtil.h("ParseEcgFileUtil", "byteToString bytes is null");
            return "";
        }
        if (i < 0) {
            LogUtil.h("ParseEcgFileUtil", "Start less than zero");
            return "";
        }
        if (i2 > bArr.length) {
            LogUtil.h("ParseEcgFileUtil", "End is too large");
            return "";
        }
        byte[] bArr2 = new byte[i2 - i];
        int i3 = 0;
        while (i < i2) {
            bArr2[i3] = bArr[i];
            i++;
            i3++;
        }
        return cvx.d(bArr2);
    }

    private static boolean e(int i, long j) {
        if (i != 0) {
            return j >= ((long) (i + 32));
        }
        ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "ecg file error. packageNameLength is 0");
        return false;
    }

    private static void c(kda kdaVar, FileInputStream fileInputStream) throws IOException {
        int b = kdaVar.b();
        byte[] bArr = new byte[b];
        int read = fileInputStream.read(bArr);
        if (read == b) {
            kdaVar.b(cvx.e(cvx.d(bArr)).trim());
        } else {
            ReleaseLogUtil.d("DEVMGR_ParseEcgFileUtil", "parsePackageName read : ", Integer.valueOf(read));
        }
    }
}
