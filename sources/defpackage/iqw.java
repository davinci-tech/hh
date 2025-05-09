package defpackage;

import android.content.Context;
import android.os.Binder;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.QuickAppInfo;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class iqw {
    private static Map<String, String> b = a();

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.h("HiHealthKitCallController", "isHwQuickAppEngine, context is null");
            return false;
        }
        return d(context, 2);
    }

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.h("HiHealthKitCallController", "H5AppEngine, context is null");
            return false;
        }
        return d(context, 3);
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.h("HiHealthKitCallController", "isSupportedAssembleSubApplication, context is null");
            return false;
        }
        return d(context, 4);
    }

    public static boolean e(QuickAppInfo quickAppInfo) {
        if (quickAppInfo == null) {
            LogUtil.h("HiHealthKitCallController", "isWhiteQuickApp, info is null");
            return false;
        }
        return d(quickAppInfo);
    }

    public static boolean a(Context context) {
        if (context == null) {
            LogUtil.h("HiHealthKitCallController", "isPermissionErrorReturn, context is null");
            return false;
        }
        return d(context, 1);
    }

    public static boolean d(H5ProAppInfo h5ProAppInfo) {
        for (iql iqlVar : iow.d()) {
            if (iqlVar.getPackageName().equals(h5ProAppInfo.getPkgName()) && iqlVar.getPackageSignature().equals(h5ProAppInfo.getCertPrint())) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context, int i) {
        if (context == null) {
            LogUtil.h("HiHealthKitCallController", "isAllowedForRemoteTest, context is null");
            return false;
        }
        return d(context, i);
    }

    private static boolean d(Context context, int i) {
        String a2 = iwi.a(context);
        LogUtil.a("HiHealthKitCallController", "caller package name ", a2);
        if (a2 == null) {
            LogUtil.h("HiHealthKitCallController", "checkCaller, error in getCallingPackageName");
            return false;
        }
        iql[] c = c(i);
        if (c != null && c.length != 0) {
            int callingUid = Binder.getCallingUid();
            for (iql iqlVar : c) {
                boolean equals = iqlVar.getPackageName().equals(a2);
                if (equals && iqlVar.getUid() == callingUid) {
                    return true;
                }
                if (equals && iqlVar.getPackageSignature().equals(HsfSignValidator.e(context, a2))) {
                    iqlVar.setUid(callingUid);
                    return true;
                }
            }
        }
        return false;
    }

    private static iql[] c(int i) {
        iql[] iqlVarArr = new iql[0];
        switch (i) {
            case 1:
                return iow.o();
            case 2:
                return iow.u();
            case 3:
                return iow.i();
            case 4:
                return iow.t();
            case 5:
                return iow.c();
            case 6:
                return iow.l();
            case 7:
                return iow.m();
            case 8:
                return iow.v();
            case 9:
                return iow.k();
            case 10:
                return iow.f();
            case 11:
                return iow.g();
            case 12:
                return iow.h();
            case 13:
                return iow.b();
            case 14:
                return iow.a();
            case 15:
                return iow.s();
            case 16:
                return iow.r();
            case 17:
                return iow.p();
            case 18:
            case 19:
                return iow.j();
            case 20:
            case 21:
                return iow.q();
            case 22:
                return iow.e();
            case 23:
                return iow.n();
            default:
                return iqlVarArr;
        }
    }

    private static boolean d(QuickAppInfo quickAppInfo) {
        for (iql iqlVar : iow.y()) {
            if (iqlVar.getPackageName().equals(quickAppInfo.getPackageName()) && iqlVar.getPackageSignature().equals(quickAppInfo.getFingerPrint())) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(String str) {
        return b.containsKey(str);
    }

    private static Map<String, String> a() {
        iql[] y = iow.y();
        HashMap hashMap = new HashMap(16);
        for (iql iqlVar : y) {
            hashMap.put(iqlVar.getPackageName(), iqlVar.getPackageSignature());
        }
        return hashMap;
    }
}
