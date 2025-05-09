package defpackage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.p2p.IdentityInfo;

/* loaded from: classes7.dex */
public class trc {
    private static StringBuilder e(int i, int i2, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        StringBuilder sb = new StringBuilder();
        String e = cvx.e(1);
        String e2 = cvx.e(i2);
        String e3 = cvx.e(e2.length() / 2);
        sb.append(e);
        sb.append(e3);
        sb.append(e2);
        String e4 = cvx.e(2);
        String a2 = cvx.a(i);
        String e5 = cvx.e(a2.length() / 2);
        sb.append(e4);
        sb.append(e5);
        sb.append(a2);
        String e6 = cvx.e(3);
        String c = cvx.c(identityInfo.getPackageName());
        String d = cvx.d(c.length() / 2);
        sb.append(e6);
        sb.append(d);
        sb.append(c);
        String e7 = cvx.e(4);
        String c2 = cvx.c(identityInfo2.getPackageName());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(e7);
        sb.append(d2);
        sb.append(c2);
        LogUtil.c("WearEngine_P2pCommandUtil", "getGeneralTlv is:", sb.toString());
        if (i2 == 2) {
            String e8 = cvx.e(5);
            String c3 = cvx.c(identityInfo.getFingerPrint());
            String d3 = cvx.d(c3.length() / 2);
            sb.append(e8);
            sb.append(d3);
            sb.append(c3);
            String e9 = cvx.e(6);
            String c4 = cvx.c(identityInfo2.getFingerPrint());
            String d4 = cvx.d(c4.length() / 2);
            sb.append(e9);
            sb.append(d4);
            sb.append(c4);
        }
        return sb;
    }

    private static String a(int i, int i2, IdentityInfo identityInfo, IdentityInfo identityInfo2, byte[] bArr) {
        StringBuilder e = e(i, i2, identityInfo, identityInfo2);
        if (bArr != null && bArr.length > 0) {
            String e2 = cvx.e(7);
            String d = cvx.d(bArr.length);
            String d2 = cvx.d(bArr);
            e.append(e2);
            e.append(d);
            e.append(d2);
            LogUtil.a("WearEngine_P2pCommandUtil", "getCommandTlv messageBody is:", e.toString());
        }
        return e.toString();
    }

    private static String a(int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, int i2, int i3) {
        StringBuilder e = e(i, 3, identityInfo, identityInfo2);
        String e2 = cvx.e(8);
        String b = cvx.b(i3);
        if (i2 == 1) {
            b = cvx.b(a(identityInfo.getPackageName()));
        }
        String e3 = cvx.e(b.length() / 2);
        e.append(e2);
        e.append(e3);
        e.append(b);
        LogUtil.c("WearEngine_P2pCommandUtil", "getResponseTlv result is:", e.toString());
        return e.toString();
    }

    public static DeviceCommand e(int i, int i2, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("WearEngine_P2pCommandUtil", "DeviceCommand srcPkgName or destPkgName is null");
            return null;
        }
        return e(cvx.a(a(i, i2, new IdentityInfo(str, ""), new IdentityInfo(str2, ""), (byte[]) null)));
    }

    public static DeviceCommand a(int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, byte[] bArr) {
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.h("WearEngine_P2pCommandUtil", "getSendCommand pkgInfo is null");
            return null;
        }
        if (TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.h("WearEngine_P2pCommandUtil", "getSendCommand srcPkgName or destPkgName is null");
            return null;
        }
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("WearEngine_P2pCommandUtil", "getSendCommand data is valid");
            return null;
        }
        return e(cvx.a(a(i, 2, identityInfo, identityInfo2, bArr)));
    }

    public static DeviceCommand e(int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, int i2, int i3) {
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.h("WearEngine_P2pCommandUtil", "getResponseCommand pkgInfo is null");
            return null;
        }
        if (TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.h("WearEngine_P2pCommandUtil", "getResponseCommand srcPkgName or destPkgName is null");
            return null;
        }
        return e(cvx.a(a(i, identityInfo, identityInfo2, i2, i3)));
    }

    private static DeviceCommand e(byte[] bArr) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(52);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataLength(bArr.length);
        deviceCommand.setDataContent(bArr);
        return deviceCommand;
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 255;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 204;
        }
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            LogUtil.b("WearEngine_P2pCommandUtil", "getPackageManager is null");
            return 204;
        }
        try {
            return packageManager.getPackageInfo(str, 16384) != null ? 205 : 204;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("WearEngine_P2pCommandUtil", "package that are installed is null");
            return 204;
        }
    }

    public static void d(String str) {
        LogUtil.a("WearEngine_P2pCommandUtil", "begin to send broadcast to " + str);
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.huawei.wearengine.ping");
        intent.addFlags(536870912);
        BaseApplication.getContext().sendBroadcast(intent, "com.huawei.wearengine.permission.DEFAULT");
    }
}
