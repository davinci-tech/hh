package defpackage;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.provider.Settings;
import android.provider.Telephony;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.MagicBuild;
import java.util.List;

/* loaded from: classes5.dex */
public class kiq {
    public static void c(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(16);
        byte[] a2 = cvx.a(cvx.e(7) + cvx.e(1) + cvx.e(i));
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i, int i2) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(16);
        byte[] a2 = cvx.a((cvx.e(1) + cvx.e(1) + cvx.e(i)) + (cvx.e(7) + cvx.e(1) + cvx.e(i2)));
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static List<cwd> d(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("SmsSendCommandUtils", "parseBytesToTlv dataInfos is invalid");
            return null;
        }
        String d = cvx.d(bArr);
        try {
            return new cwl().a(d.substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("SmsSendCommandUtils", "parseBytesToTlv TlvException");
            return null;
        }
    }

    public static boolean c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "SmsSendCommandUtils");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("SmsSendCommandUtils", "isSupportSyncIconMain deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 89);
    }

    public static String e(String str) {
        String str2 = "";
        try {
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(BaseApplication.getContext());
            if (!TextUtils.isEmpty(defaultSmsPackage)) {
                return defaultSmsPackage;
            }
            str2 = Settings.Secure.getString(BaseApplication.getContext().getContentResolver(), "sms_default_application");
            LogUtil.a(str, "Settings Secure defaultSmsPackageName", str2);
            return str2;
        } catch (RuntimeException unused) {
            LogUtil.b(str, "textPackageName RuntimeException");
            return str2;
        }
    }

    public static boolean c(String str) {
        return TextUtils.equals(e("SmsSendCommandUtils"), str) && (CommonUtil.bh() || CommonUtil.bf());
    }

    public static boolean b(String str, int i) {
        return c(str) && i == 1390;
    }

    public static void b(String str) {
        if (CommonUtil.bv()) {
            return;
        }
        LogUtil.a(str, e());
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(128);
        String str = Build.BRAND + " " + Build.MODEL;
        sb.append("phoneNameModel: ");
        sb.append(str);
        if (EmuiBuild.d) {
            sb.append(",EMUI ");
            sb.append(EmuiBuild.c);
            sb.append(",API ");
            sb.append(EmuiBuild.f13113a);
        } else if (HarmonyBuild.d) {
            sb.append(",HarmonyOS ");
            sb.append(HarmonyBuild.b);
            sb.append(",API ");
            sb.append(HarmonyBuild.c);
        } else if (MagicBuild.f13130a) {
            sb.append(",MagicOS ");
            sb.append(MagicBuild.b);
            sb.append(",API ");
            sb.append(MagicBuild.d);
        }
        sb.append(",Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(",API ");
        sb.append(Build.VERSION.SDK_INT);
        String e = e("SmsSendCommandUtils");
        PackageInfo bJd_ = jrg.bJd_(e);
        String str2 = bJd_ != null ? bJd_.versionName : "";
        sb.append(",defaultSms ");
        sb.append(e);
        sb.append("_v");
        sb.append(str2);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
    
        if (r7.isClosed() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00be, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00bc, code lost:
    
        if (r7.isClosed() == false) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean d(java.lang.String r11) {
        /*
            android.net.Uri r1 = android.provider.Telephony.Sms.Inbox.CONTENT_URI
            java.lang.String r6 = "sub_id"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r3 = r1.toString()
            r7 = 0
            java.lang.String r3 = defpackage.jdz.a(r3, r2, r7, r7, r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r11, r3)
            r3 = 0
            r4 = 0
            r5 = 0
            r8 = 2
            r9 = 1
            r10 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r7 == 0) goto L78
            boolean r0 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r0 != 0) goto L34
            goto L78
        L34:
            int r0 = r7.getColumnIndexOrThrow(r6)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r0 < 0) goto L62
            int r1 = r7.getColumnCount()     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r0 < r1) goto L41
            goto L62
        L41:
            int r1 = r7.getInt(r0)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r1 != 0) goto L53
            if (r7 == 0) goto L52
            boolean r11 = r7.isClosed()
            if (r11 != 0) goto L52
            r7.close()
        L52:
            return r9
        L53:
            boolean r1 = r7.moveToNext()     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r1 != 0) goto L41
            if (r7 == 0) goto Lc1
            boolean r11 = r7.isClosed()
            if (r11 != 0) goto Lc1
            goto Lbe
        L62:
            java.lang.Object[] r0 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            java.lang.String r1 = "smsReply getSmsManager getSubscriptionId isSubIdHasZero: subIdIndex is invalid."
            r0[r10] = r1     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            com.huawei.hwlogsmodel.LogUtil.h(r11, r0)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r7 == 0) goto L77
            boolean r11 = r7.isClosed()
            if (r11 != 0) goto L77
            r7.close()
        L77:
            return r10
        L78:
            java.lang.Object[] r0 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            java.lang.String r1 = "smsReply getSmsManager getSubscriptionId isSubIdHasZero cursor is null or cannot move."
            r0[r10] = r1     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r7 != 0) goto L83
            r1 = r9
            goto L84
        L83:
            r1 = r10
        L84:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            r0[r9] = r1     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            com.huawei.hwlogsmodel.LogUtil.h(r11, r0)     // Catch: java.lang.Throwable -> L99 android.os.OperationCanceledException -> L9b java.lang.SecurityException -> L9d android.database.StaleDataException -> L9f java.lang.IllegalArgumentException -> La1 java.lang.IllegalStateException -> La3 android.database.SQLException -> La5
            if (r7 == 0) goto L98
            boolean r11 = r7.isClosed()
            if (r11 != 0) goto L98
            r7.close()
        L98:
            return r10
        L99:
            r11 = move-exception
            goto Lc2
        L9b:
            r0 = move-exception
            goto La6
        L9d:
            r0 = move-exception
            goto La6
        L9f:
            r0 = move-exception
            goto La6
        La1:
            r0 = move-exception
            goto La6
        La3:
            r0 = move-exception
            goto La6
        La5:
            r0 = move-exception
        La6:
            java.lang.Object[] r1 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L99
            java.lang.String r2 = "smsReply getSmsManager getSubscriptionId isSubIdHasZero exception: "
            r1[r10] = r2     // Catch: java.lang.Throwable -> L99
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L99
            r1[r9] = r0     // Catch: java.lang.Throwable -> L99
            com.huawei.hwlogsmodel.LogUtil.a(r11, r1)     // Catch: java.lang.Throwable -> L99
            if (r7 == 0) goto Lc1
            boolean r11 = r7.isClosed()
            if (r11 != 0) goto Lc1
        Lbe:
            r7.close()
        Lc1:
            return r10
        Lc2:
            if (r7 == 0) goto Lcd
            boolean r0 = r7.isClosed()
            if (r0 != 0) goto Lcd
            r7.close()
        Lcd:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kiq.d(java.lang.String):boolean");
    }
}
