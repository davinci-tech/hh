package com.huawei.healthcloud.plugintrack.golf;

import android.content.Context;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseMapDataReq;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.healthcloud.plugintrack.golf.device.GolfMsgHeader;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSportType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bed;
import defpackage.cun;
import defpackage.cwi;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class Utils {
    public static boolean writeFileData(Context context, String str, String str2, byte[]... bArr) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = context.openFileOutput(str2, 0);
                for (byte[] bArr2 : bArr) {
                    fileOutputStream.write(bArr2);
                }
                return true;
            } catch (FileNotFoundException unused) {
                LogUtil.b(str, "writefile finally failed FileNotFound");
                if (fileOutputStream == null) {
                    return false;
                }
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException unused2) {
                    LogUtil.b(str, "writefile finally failed IOException");
                    return false;
                }
            }
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    LogUtil.b(str, "writefile finally failed IOException");
                }
            }
        }
    }

    public static String getFilePath(Context context, String str) {
        return context.getFilesDir() + File.separator + str;
    }

    public static boolean checkDevSendMsgRcverParams(String str, GolfMsgHeader golfMsgHeader, byte[] bArr) {
        if (golfMsgHeader == null) {
            LogUtil.h(str, "onDataReceived msg head is null, pls check.");
            return false;
        }
        if (bArr != null && bArr.length >= 4) {
            return true;
        }
        LogUtil.h(str, "onDataReceived dataContents length error");
        return false;
    }

    public static String getLanguage() {
        return bed.b();
    }

    public static int getGolfResultCode() {
        int i = GolfDeviceProxy.getInstance().isSupportGolf() ? 0 : 2;
        if (GolfDeviceProxy.getInstance().isSupportGolfV2()) {
            i = 3;
        }
        if (GolfDeviceProxy.getInstance().isSupportGolfV3()) {
            return 4;
        }
        return i;
    }

    public static String getGolfMapDeviceLevel(String str) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, str);
        if (deviceInfo == null) {
            LogUtil.h(str, "getGolfMapDeviceLevel deviceInfo == null");
            return GetGolfCourseMapDataReq.MapLevel.BASIC_LEVEL;
        }
        boolean c = cwi.c(deviceInfo, FitnessSportType.HW_FITNESS_SPORT_ALL);
        ReleaseLogUtil.e(str, "isSupportGolfAdvancedMap:", Boolean.valueOf(c));
        if (c) {
            return GetGolfCourseMapDataReq.MapLevel.ADVANCED_LEVEL;
        }
        return GetGolfCourseMapDataReq.MapLevel.BASIC_LEVEL;
    }

    public static byte[] int2Bytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }
}
