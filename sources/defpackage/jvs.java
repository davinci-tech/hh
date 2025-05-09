package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class jvs {
    public static void e(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("FileServiceResultUtil", "Invalid message.");
            return;
        }
        bmt bmtVar = new bmt();
        switch (bArr[1]) {
            case 1:
                jwb e = e(deviceInfo);
                if (e != null && e.k()) {
                    if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                        if (e.g() < 3) {
                            e.e(e.g() + 1);
                            jvf.e().d(deviceInfo);
                            break;
                        }
                    } else {
                        jvf.e().d(deviceInfo);
                        break;
                    }
                } else {
                    jvv.d(deviceInfo);
                    jwb jwbVar = new jwb();
                    jwbVar.e(true);
                    if (deviceInfo != null) {
                        jvf.c().put(deviceInfo.getDeviceIdentify(), jwbVar);
                    }
                    b(bmtVar, bArr, deviceInfo);
                    break;
                }
                break;
            case 2:
                e(bmtVar, bArr, deviceInfo);
                break;
            case 3:
                a(bmtVar, bArr, deviceInfo);
                break;
            case 4:
                jvv.c(deviceInfo, true);
                d(bmtVar, bArr, deviceInfo);
                break;
            case 5:
                c(bArr, deviceInfo);
                break;
            case 6:
                jvv.a(deviceInfo, true);
                c(bmtVar, bArr, deviceInfo);
                break;
            default:
                LogUtil.h("FileServiceResultUtil", "Invalid file management service command id.");
                break;
        }
    }

    public static void c(String str, boolean z) {
        if (str == null) {
            LogUtil.h("FileServiceResultUtil", "resetRunning deviceId is null.");
            return;
        }
        LogUtil.a("FileServiceResultUtil", "resetRunning deviceInfo.");
        jvf.c().remove(str);
        jvb jvbVar = jva.b().a().get(str);
        if (jvbVar == null) {
            LogUtil.h("FileServiceResultUtil", "resetRunning ephemerisDownloadInfo is null.");
            return;
        }
        if (z) {
            jvbVar.f(jvbVar.j() + 1);
            if (jvbVar.j() == jvbVar.d().size()) {
                LogUtil.a("FileServiceResultUtil", "resetRunning remove DownloadInfo.");
                jva.b().a().remove(str);
                return;
            }
            return;
        }
        jva.b().a().remove(str);
    }

    public static jwb e(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return jvf.c().get(deviceInfo.getDeviceIdentify());
        }
        return null;
    }

    private static void b(bmt bmtVar, byte[] bArr, DeviceInfo deviceInfo) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            String str = "";
            int i = -1;
            for (bmu bmuVar : bmtVar.b(bArr2).d()) {
                byte a2 = bmuVar.a();
                if (a2 == 1) {
                    LogUtil.a("FileServiceResultUtil", "INFO_FILE_NAME filenames : ", new String(bmuVar.c(), StandardCharsets.UTF_8));
                } else if (a2 == 2) {
                    i = cvx.c(bmuVar.c(), -1);
                    LogUtil.a("FileServiceResultUtil", "INFO_FILE_TYPE file_type : ", Integer.valueOf(i));
                } else if (a2 == 3) {
                    str = new String(bmuVar.c(), StandardCharsets.UTF_8);
                    LogUtil.a("FileServiceResultUtil", "INFO_PRODUCT_ID productId.", str);
                } else if (a2 == 4) {
                    LogUtil.a("FileServiceResultUtil", "INFO_ISSUER_ID issuerId.");
                } else if (a2 == 5) {
                    LogUtil.a("FileServiceResultUtil", "INFO_CARD_GROUP_TYPE cardType : ", Integer.valueOf(cvx.c(bmuVar.c(), -1)));
                } else {
                    LogUtil.h("FileServiceResultUtil", "Invalid request file information type.");
                }
            }
            jvw.c(i, str, deviceInfo);
        } catch (bmk unused) {
            LogUtil.b("FileServiceResultUtil", "processFileManagerGetInfo TlvException");
            jvv.a(deviceInfo, false);
        }
    }

    private static void e(bmt bmtVar, byte[] bArr, DeviceInfo deviceInfo) {
        if (d(bArr)) {
            LogUtil.h("FileServiceResultUtil", "processFileManagerConsult error 7F04");
            jvv.a(deviceInfo, false);
            return;
        }
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            bmq b = bmtVar.b(bArr2);
            jwb e = e(deviceInfo);
            if (e == null) {
                LogUtil.h("FileServiceResultUtil", "processFileManagerConsult getEphemerisUtilMap is null.");
                jvv.a(deviceInfo, false);
            } else {
                b(b, e);
                jvu.d(deviceInfo);
            }
        } catch (bmk unused) {
            LogUtil.b("FileServiceResultUtil", "processFileManagerConsult() COMMAND_ID_FILE_MANAGER_CONSULT error");
            jvv.a(deviceInfo, false);
        }
    }

    private static void b(bmq bmqVar, jwb jwbVar) {
        for (bmu bmuVar : bmqVar.d()) {
            switch (bmuVar.a()) {
                case 1:
                    String str = new String(bmuVar.c(), StandardCharsets.UTF_8);
                    jwbVar.e(str);
                    LogUtil.a("FileServiceResultUtil", "CONSULT_FILE_PROTOCOL_VERSION version : ", str);
                    break;
                case 2:
                    LogUtil.a("FileServiceResultUtil", "CONSULT_TRANSFER_BITMAP_ENABLE bitmapEnable : ", Integer.valueOf(cvx.c(bmuVar.c(), -1)));
                    break;
                case 3:
                    int c = cvx.c(bmuVar.c(), -1);
                    jwbVar.g(c);
                    LogUtil.a("FileServiceResultUtil", "CONSULT_TRANSFER_UNIT_SIZE transferSize : ", Integer.valueOf(c));
                    break;
                case 4:
                    long c2 = cvx.c(bmuVar.c(), -1);
                    jwbVar.d(c2);
                    LogUtil.a("FileServiceResultUtil", "CONSULT_MAX_APPLY_DATA_SIZE maxDataSize : ", Long.valueOf(c2));
                    break;
                case 5:
                    int c3 = cvx.c(bmuVar.c(), -1);
                    jwbVar.j(c3);
                    LogUtil.a("FileServiceResultUtil", "CONSULT_TIMEOUT timeOut : ", Integer.valueOf(c3));
                    break;
                case 6:
                    int c4 = cvx.c(bmuVar.c(), -1);
                    jwbVar.b(c4);
                    LogUtil.a("FileServiceResultUtil", "CONSULT_FILE_TYPE fileType : ", Integer.valueOf(c4));
                    break;
                default:
                    LogUtil.h("FileServiceResultUtil", "Invalid file transfer negotiation type.");
                    break;
            }
        }
    }

    private static void a(bmt bmtVar, byte[] bArr, DeviceInfo deviceInfo) {
        if (d(bArr)) {
            LogUtil.h("FileServiceResultUtil", "processQuerySingleFileInfo error 7F04");
            jvv.a(deviceInfo, false);
            return;
        }
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            String str = "";
            for (bmu bmuVar : bmtVar.b(bArr2).d()) {
                if (bmuVar.a() == 1) {
                    str = new String(bmuVar.c(), StandardCharsets.UTF_8);
                    LogUtil.a("FileServiceResultUtil", "SINGLE_FILE_NAME fileName : ", str);
                } else {
                    LogUtil.h("FileServiceResultUtil", "Other file information fields.");
                }
            }
            jwb e = e(deviceInfo);
            int e2 = e != null ? e.e() : -1;
            LogUtil.a("FileServiceResultUtil", "SINGLE_FILE fileType : ", Integer.valueOf(e2));
            if (e2 == 0) {
                jvu.c(str, deviceInfo);
            } else if (e2 == 1) {
                jvu.d(str, deviceInfo);
            } else {
                LogUtil.h("FileServiceResultUtil", "SINGLE_FILE fileType Unknown");
                jvv.a(deviceInfo, false);
            }
        } catch (bmk unused) {
            LogUtil.b("FileServiceResultUtil", "processQuerySingleFileInfo TlvException");
            jvv.a(deviceInfo, false);
        }
    }

    private static void d(bmt bmtVar, byte[] bArr, DeviceInfo deviceInfo) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            String str = "";
            String str2 = str;
            int i = -1;
            for (bmu bmuVar : bmtVar.b(bArr2).d()) {
                byte a2 = bmuVar.a();
                if (a2 == 1) {
                    str = new String(bmuVar.c(), StandardCharsets.UTF_8);
                    LogUtil.a("FileServiceResultUtil", "APPLICATION_APPLY_FILENAME fileName : ", str);
                } else if (a2 == 2) {
                    i = cvx.c(bmuVar.c(), -1);
                    LogUtil.a("FileServiceResultUtil", "APPLICATION_APPLY_OFFSET offset : ", Integer.valueOf(i));
                } else if (a2 == 3) {
                    LogUtil.a("FileServiceResultUtil", "APPLICATION_APPLY_FILE_LENGTH fileLength : ", Long.valueOf(cvx.c(bmuVar.c(), -1)));
                } else if (a2 == 4) {
                    str2 = cvx.d(bmuVar.c());
                    LogUtil.a("FileServiceResultUtil", "APPLICATION_APPLY_FILE_BITMAP fileBitmap : ", str2);
                } else if (a2 == Byte.MAX_VALUE) {
                    LogUtil.a("FileServiceResultUtil", "APPLICATION_APPLY_FILE_BITMAP error : ", bmuVar.c());
                } else {
                    LogUtil.h("FileServiceResultUtil", "Invalid request file data type.");
                }
            }
            if (i != -1 && !"".equals(str)) {
                jvv.b(str, str2, i, deviceInfo);
            } else {
                jvv.a(deviceInfo, false);
            }
        } catch (bmk unused) {
            LogUtil.b("FileServiceResultUtil", "processApplicationDataToPhone() COMMAND_ID_FILE_MANAGER_APPLICATION_DATA_TO_PHONE error");
            jvv.a(deviceInfo, false);
        }
    }

    private static void c(byte[] bArr, DeviceInfo deviceInfo) {
        if (!d(bArr) || c(bArr)) {
            return;
        }
        LogUtil.h("FileServiceResultUtil", "processFileContentTransfer error 7F04 and not 100000");
        jvv.a(deviceInfo, false);
    }

    private static void c(bmt bmtVar, byte[] bArr, DeviceInfo deviceInfo) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            int i = -1;
            for (bmu bmuVar : bmtVar.b(bArr2).d()) {
                if (bmuVar.a() == 1) {
                    i = cvx.c(bmuVar.c(), -1);
                    LogUtil.a("FileServiceResultUtil", "NOTIFICATION_VALIDITY_RESULT validity : ", Integer.valueOf(i));
                } else {
                    LogUtil.h("FileServiceResultUtil", "Other file result notification types.");
                }
            }
            jvu.c(i, deviceInfo);
        } catch (bmk unused) {
            LogUtil.b("FileServiceResultUtil", "processResultNotification TlvException");
        }
    }

    private static boolean d(byte[] bArr) {
        return bArr.length >= 4 && bArr[2] == Byte.MAX_VALUE && bArr[3] == 4;
    }

    private static boolean c(byte[] bArr) {
        if (bArr.length < 8) {
            return false;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 4, bArr2, 0, 4);
        return Arrays.equals(new byte[]{0, 1, -122, -96}, bArr2);
    }
}
