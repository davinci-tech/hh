package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.browseraction.HwSchemeBasicHealthActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class gof {
    public static void aQy_(Activity activity, int i, Uri uri) {
        if (i <= 0) {
            LogUtil.h("HealthSchemeUtil", "skipActivity invalid type.");
            return;
        }
        if (i != 13 && i != 22 && i != 110 && i != 6001) {
            switch (i) {
            }
            return;
        }
        aQx_(activity, uri);
    }

    private static void aQx_(Activity activity, Uri uri) {
        Intent intent = new Intent();
        intent.setClass(activity, HwSchemeBasicHealthActivity.class);
        intent.setData(uri);
        gnm.aPB_(activity, intent);
    }

    public static ContentValues aQw_(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return new ContentValues();
        }
        ArrayList<ContentValues> a2 = ceo.d().a(str3);
        LogUtil.a("HealthSchemeUtil", "getDeviceInfoByWiseDeivceIdOrSubMac deviceInfos size is ", Integer.valueOf(a2.size()));
        if (koq.b(a2)) {
            return new ContentValues();
        }
        if (a2.size() == 1) {
            return a2.get(0);
        }
        Iterator<ContentValues> it = a2.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("mDeviceId");
            if (!TextUtils.isEmpty(asString) && asString.equals(str)) {
                LogUtil.a("HealthSchemeUtil", "mDeviceId pass ");
                return next;
            }
            String asString2 = next.getAsString("uniqueId");
            if (!TextUtils.isEmpty(asString2) && asString2.equals(str)) {
                LogUtil.a("HealthSchemeUtil", "the device has bound on the cloud && no bound by Ble ");
                return next;
            }
            if (cpa.x(str3) && c(str2, next.getAsString("sn"))) {
                return next;
            }
            if (!TextUtils.isEmpty(asString2)) {
                int length = str2.length();
                if (asString2.replace(":", "").endsWith(length > 3 ? str2.substring(length - 3, length) : str2)) {
                    LogUtil.a("HealthSchemeUtil", "uniqueId pass");
                    return next;
                }
            }
        }
        return new ContentValues();
    }

    private static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("HealthSchemeUtil", "isMatchSubMac serialNumber is empty.");
            return false;
        }
        if (str2.endsWith(str)) {
            LogUtil.a("HealthSchemeUtil", "isMatchSubMac serialNumber pass.");
            return true;
        }
        if (!str.contains(Constants.LINK) || !str2.endsWith(str.substring(str.indexOf(Constants.LINK) + 1))) {
            return false;
        }
        LogUtil.a("HealthSchemeUtil", "isMatchSubMac serialNumber subMacTemp pass.");
        return true;
    }
}
