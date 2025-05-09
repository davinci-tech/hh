package defpackage;

import android.os.Environment;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jvy {
    public boolean e() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "GpsFileUtil");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability != null && deviceCapability.isSupportGpsPostProcessing();
    }

    public void d(byte[] bArr, String str) {
        if (bArr == null || TextUtils.isEmpty(str)) {
            LogUtil.h("GpsFileUtil", "saveFile dataInfos is null or name isEmpty");
            return;
        }
        String str2 = LogConfig.m() + "/tracktest";
        File file = new File(str2);
        if (!file.exists()) {
            LogUtil.a(a.t, 0, "GpsFileUtil", "mkdir result :", Boolean.valueOf(file.mkdirs()));
        }
        byte[] c = c(bArr, 32);
        File file2 = new File(str2 + "/" + str);
        int i = str.contains("_gps.bin") ? 17 : 18;
        try {
            if (file2.exists()) {
                LogUtil.a(a.t, 0, "GpsFileUtil", "saveFile delete result :", Boolean.valueOf(file2.delete()));
            }
            if (!file2.exists()) {
                LogUtil.a(a.t, 0, "GpsFileUtil", "saveFile createNewFile :", Boolean.valueOf(file2.createNewFile()));
            }
            jyu.e(file2, i);
            if (c != null) {
                b(file2, c);
            }
        } catch (Exception e) {
            LogUtil.b("GpsFileUtil", "saveFile error :", ExceptionUtils.d(e));
        }
    }

    public void d() {
        LogUtil.a("GpsFileUtil", "getOldFilePath()");
        if (!CommonUtil.ar() && ((CompileParameterUtil.a("IS_DEBUG_VERSION") || (CommonUtil.as() && a())) && (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) && !PermissionUtil.c() && PermissionUtil.e(BaseApplication.getContext(), PermissionUtil.PermissionType.STORAGE) == PermissionUtil.PermissionResult.GRANTED))) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tracktest");
            if (file.exists()) {
                e(file);
            }
        }
        File file2 = new File(LogConfig.m() + "/tracktest");
        if (file2.exists()) {
            e(file2);
        }
    }

    private void e(File file) {
        boolean delete;
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 20) {
            LogUtil.h("GpsFileUtil", "deleteOldFile files is null or less than 20 files.");
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                e(file2);
                delete = file2.delete();
            } else {
                delete = file2.delete();
            }
            LogUtil.c("GpsFileUtil", "deleteOldFile:", Boolean.valueOf(delete));
        }
    }

    private void b(File file, byte[] bArr) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = FileUtils.openOutputStream(file);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("GpsFileUtil", "createFile close error");
                    }
                }
            } catch (IOException e) {
                LogUtil.b("GpsFileUtil", "createFile error :", LogAnonymous.b((Throwable) e));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                        LogUtil.b("GpsFileUtil", "createFile close error");
                    }
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    LogUtil.b("GpsFileUtil", "createFile close error");
                }
            }
            throw th;
        }
    }

    private byte[] c(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length - i];
        for (int i2 = i; i2 < bArr.length; i2++) {
            bArr2[i2 - i] = bArr[i2];
        }
        return bArr2;
    }

    public ArrayList<Long> b(byte[] bArr) {
        int i;
        if (bArr == null || bArr.length < 33) {
            return null;
        }
        byte b = bArr[32];
        LogUtil.a(a.t, 0, "GpsFileUtil", "getTrackData dataFlag is :", Integer.valueOf(b));
        if (b == 0) {
            i = 15;
        } else if (b == 1 || b == 2) {
            i = 17;
        } else {
            if (b == 3) {
                LogUtil.a(a.t, 0, "GpsFileUtil", "getTrackData dataFlag is 3 :", Integer.valueOf(b));
            } else {
                LogUtil.h("GpsFileUtil", "getTrackData dataFlag is not support :", Integer.valueOf(b));
            }
            i = 19;
        }
        int i2 = i * 2;
        int i3 = i2 + 32;
        if (bArr.length >= 55 && bArr[54] == 3 && b == 0) {
            i3 = i2 + 69;
        }
        byte[] c = c(bArr, i3);
        if (c == null) {
            return null;
        }
        byte b2 = bArr[33];
        String d = cvx.d(new byte[]{bArr[36], bArr[35], bArr[34], b2});
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        long y = CommonUtil.y(d);
        LogUtil.a(a.t, 0, "GpsFileUtil", "startTime :", Long.valueOf(y));
        String d2 = cvx.d(c);
        if (TextUtils.isEmpty(d2)) {
            return null;
        }
        return e(i, y, d2);
    }

    private ArrayList<Long> e(int i, long j, String str) {
        int i2 = i * 2;
        int length = str.length() / i2;
        ArrayList<Long> arrayList = new ArrayList<>(length);
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * i2;
            StringBuilder sb = new StringBuilder();
            int i5 = i4 + 2;
            sb.append(str.substring(i5, i4 + 4));
            sb.append(str.substring(i4, i5));
            j += CommonUtil.w(sb.toString());
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    private boolean a() {
        try {
            HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("gps_files_switch_screen");
            if (userPreference == null) {
                LogUtil.a("GpsFileUtil", "isSupportGpsFile preference is null");
                return c(HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.wear_common_setting"));
            }
            return "1".equals(userPreference.getValue());
        } catch (Exception unused) {
            LogUtil.b("GpsFileUtil", "isSupportGpsFile Exception");
            return false;
        }
    }

    private boolean c(HiUserPreference hiUserPreference) {
        String value;
        if (hiUserPreference == null || (value = hiUserPreference.getValue()) == null) {
            return false;
        }
        String substring = value.substring(1, value.length() - 1);
        for (String str : substring.split(",")) {
            if ("gps_files_switch_screen".equals(str.split("=")[0].trim())) {
                substring = str.split("=")[1];
            }
        }
        LogUtil.a(a.t, 0, "GpsFileUtil", "GPS file enabled :", substring);
        return "1".equals(substring);
    }
}
