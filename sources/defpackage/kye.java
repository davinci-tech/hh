package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.hwversionmgr.selfupdate.appupdate.UpdateBase;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.io.File;

/* loaded from: classes5.dex */
public class kye {
    public static void e(HwVersionManager hwVersionManager) {
        String b = hwVersionManager != null ? hwVersionManager.b() : null;
        LogUtil.a("UpdateServiceUtil", "deleteUpdateApk: path = ", b);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        File file = new File(b);
        if (file.exists()) {
            try {
                if (file.delete()) {
                    LogUtil.a("UpdateServiceUtil", "deleteUpdateApk delete success");
                }
            } catch (Exception unused) {
                LogUtil.b("UpdateServiceUtil", "deleteUpdateApk: Exception");
            }
        }
    }

    public static boolean a(int i, HwVersionManager hwVersionManager) {
        LogUtil.a("UpdateServiceUtil", "enter isNewVersionFileExist() reportType:", Integer.valueOf(i));
        String b = ((i == 2 || i == 0) && hwVersionManager != null) ? hwVersionManager.b() : "";
        if ((i == 3 || i == 1) && hwVersionManager != null) {
            b = hwVersionManager.g("");
        }
        if (TextUtils.isEmpty(b)) {
            LogUtil.b("UpdateServiceUtil", "isNewVersionFileExist() error, file path is empty...");
            return false;
        }
        boolean exists = new File(b).exists();
        LogUtil.a("UpdateServiceUtil", "isNewVersionFileExist: storagePath = ", b, " isExist = ", Boolean.valueOf(exists));
        return exists;
    }

    public static boolean a(int i, String str) {
        LogUtil.a("UpdateServiceUtil", "enter isScaleNewVersionFileExist reportType:", Integer.valueOf(i));
        String g = (i == 5 || i == 4) ? kxp.c().g(str) : "";
        LogUtil.a("UpdateServiceUtil", "isScaleNewVersionFileExist(): strAw70StorePath = ", g);
        if (TextUtils.isEmpty(g)) {
            LogUtil.b("UpdateServiceUtil", "isScaleNewVersionFileExist() error, file path is empty...");
            return false;
        }
        boolean exists = new File(g).exists();
        LogUtil.a("UpdateServiceUtil", "isScaleNewVersionFileExist isExist = ", Boolean.valueOf(exists));
        return exists;
    }

    public static void c(Context context, HwVersionManager hwVersionManager, UpdateBase updateBase) {
        if (hwVersionManager == null) {
            LogUtil.a("UpdateServiceUtil", "install versionManager is null");
            return;
        }
        String b = hwVersionManager.b();
        LogUtil.a("UpdateServiceUtil", "install: storagePath = ", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.b("UpdateServiceUtil", "install() error, file path is empty...");
            c(40, 47);
        } else if (!b(context, b)) {
            LogUtil.b("UpdateServiceUtil", "install() error, is not the same signatures...");
            c(22, 47);
        } else {
            c(27, 0);
            if (updateBase != null) {
                updateBase.d(b, null);
            }
        }
    }

    public static void c(int i, int i2) {
        a(i, i2, "", "", 0);
    }

    public static void a(int i, int i2, String str, String str2, int i3) {
        LogUtil.a("UpdateServiceUtil", "broadcastCheckState: state = ", Integer.valueOf(i), ", result = ", Integer.valueOf(i2), ",content = ", str);
        Intent intent = new Intent("action_app_check_new_version_state");
        intent.addFlags(1610612736);
        intent.putExtra("state", i);
        intent.putExtra("result", i2);
        intent.putExtra("content", str);
        intent.putExtra("minAppCode", i3);
        if (TextUtils.equals(str2, "true")) {
            intent.putExtra("isForced", true);
        } else {
            intent.putExtra("isForced", false);
        }
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void d(int i, int i2, String str, String str2, int i3) {
        LogUtil.a("UpdateServiceUtil", "broadcastCheckStateApp: state = ", Integer.valueOf(i), ", result = ", Integer.valueOf(i2), ",content = ", str);
        Intent intent = new Intent("action_app_check_new_version_state");
        intent.addFlags(1610612736);
        intent.putExtra("state", i);
        intent.putExtra("result", i2);
        intent.putExtra("content", str);
        intent.putExtra("minAppCode", i3);
        intent.putExtra("key_check_type", "type_check_app");
        if (TextUtils.equals(str2, "true")) {
            intent.putExtra("isForced", true);
        } else {
            intent.putExtra("isForced", false);
        }
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void a() {
        Intent intent = new Intent("action_ota_check_new_version_state");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static boolean b(Context context, String str) {
        return CommonUtil.q(context, str);
    }

    public static void b(int i) {
        b(new c(i).c("").c(0).d("").a("").d(0));
    }

    public static void b(c cVar) {
        b(cVar, "");
    }

    public static void b(c cVar, String str) {
        if (cVar == null) {
            LogUtil.b("UpdateServiceUtil", "broadcastAutoCheckResult null info");
            return;
        }
        LogUtil.a("UpdateServiceUtil", "broadcastAutoCheckResult: result=", Integer.valueOf(cVar.j), " name=", cVar.f14696a, " size=", Integer.valueOf(cVar.f), " isForced=", cVar.e, " appMinCode=", Integer.valueOf(cVar.b), " changelog=", cVar.d);
        Intent intent = new Intent("action_band_auto_check_new_version_result");
        intent.addFlags(1610612736);
        intent.putExtra("result", cVar.j);
        intent.putExtra("name", cVar.f14696a);
        intent.putExtra("size", cVar.f);
        intent.putExtra("changelog", cVar.d);
        intent.putExtra("minAppCode", cVar.b);
        intent.putExtra("uniqueId", str);
        if (cVar.c != null) {
            intent.putExtra("productId", cVar.c);
        }
        if (TextUtils.equals(cVar.e, "true")) {
            intent.putExtra("isForced", true);
        } else {
            intent.putExtra("isForced", false);
        }
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void b(String str, String str2, UpdateBase updateBase, AppCheckNewVersionHandler appCheckNewVersionHandler, String str3) {
        LogUtil.a("UpdateServiceUtil", "scaleBandCheckNewVersion version:", str2, ",productId:", CommonUtil.l(str), ",macAddress:", CommonUtil.l(str3));
        c(10, -1);
        if (appCheckNewVersionHandler != null) {
            appCheckNewVersionHandler.setIsScale(true);
            if (updateBase != null) {
                updateBase.e(str, str2, appCheckNewVersionHandler, str3);
            }
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        String f14696a;
        int b;
        String c;
        String d;
        String e;
        int f;
        int j;

        public c(int i) {
            this.j = i;
        }

        public c c(String str) {
            this.f14696a = str;
            return this;
        }

        public c c(int i) {
            this.f = i;
            return this;
        }

        public c d(String str) {
            this.d = str;
            return this;
        }

        public c a(String str) {
            this.e = str;
            return this;
        }

        public c d(int i) {
            this.b = i;
            return this;
        }

        public c e(String str) {
            this.c = str;
            return this;
        }
    }
}
