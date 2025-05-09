package defpackage;

import android.content.Context;
import android.os.Binder;
import android.text.TextUtils;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class ixp {
    public static boolean e(Context context, String str) {
        LogUtil.a("AuthUtils", "isVerifyHsfSignature", "Enter isVerifyHsfSignature packageName:", str);
        ixo.e(context, "context must not be null.");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AuthUtils", "isVerifyHsfSignature", "getServiceBinder packageName empty");
            return false;
        }
        if (!d(context, str)) {
            LogUtil.h("AuthUtils", "isVerifyHsfSignature", "Enter isVerifyPackageNameByUid false");
            return false;
        }
        return HsfSignValidator.c(str);
    }

    private static boolean d(Context context, String str) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid != null) {
            for (String str2 : packagesForUid) {
                if (str.equals(str2)) {
                    LogUtil.a("AuthUtils", "Enter isVerifyPackageNameByUid true");
                    return true;
                }
            }
        }
        LogUtil.a("AuthUtils", "Enter isVerifyPackageNameByUid false");
        return false;
    }
}
