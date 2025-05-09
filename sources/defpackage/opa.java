package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class opa {
    public static boolean e(String str) {
        if (!str.contains("h5proapp?pkg=")) {
            return false;
        }
        String[] split = str.split("h5proapp\\?pkg=");
        if (split.length < 2) {
            LogUtil.a("QrCodeH5Utils", "tryJumpH5ProApp: urlPartsRaw.length is", Integer.valueOf(split.length));
            return false;
        }
        String str2 = split[1].split("&")[0];
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("QrCodeH5Utils", "tryJumpH5ProApp: pkg name empty");
            return false;
        }
        String[] split2 = str.split("h5proapp\\?pkg=" + str2);
        String str3 = str2 + "?h5pro=true" + (split2.length >= 2 ? split2[1] : "");
        LogUtil.a("QrCodeH5Utils", "tryJumpH5ProApp: ", str3);
        bzs.e().startOperationWebPage(gxf.d(), str3);
        return true;
    }
}
