package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;
import health.compact.a.MagicBuild;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bld {
    public static boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("DeviceCommonUtil", "deviceInfo is null");
            return false;
        }
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel)) {
            LogUtil.a("DeviceCommonUtil", "deviceModel is empty");
            return false;
        }
        List<String> c = cfc.e().c();
        if (c == null) {
            LogUtil.e("DeviceCommonUtil", "modelList is null");
            return false;
        }
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            if (deviceModel.contains(it.next())) {
                LogUtil.c("DeviceCommonUtil", "deviceModel is honor");
                return true;
            }
        }
        return false;
    }

    public static boolean b() {
        Cursor query;
        a();
        boolean z = false;
        try {
            query = BaseApplication.e().getContentResolver().query(Uri.parse(jdz.d), null, null, null, null);
            try {
                LogUtil.c("DeviceCommonUtil", jdz.a("MidwareAuthority", null, null, null, null));
            } finally {
            }
        } catch (Exception e) {
            LogUtil.e("DeviceCommonUtil", "isMidWareAuthority exception: ", ExceptionUtils.d(e));
        }
        if (query != null && query.getCount() != 0) {
            if (query.moveToFirst()) {
                Integer bGi_ = jdz.bGi_("value", query, "DeviceCommonUtil");
                if (bGi_ == null) {
                    LogUtil.a("DeviceCommonUtil", "isMidWareAuthority columnIndex is null");
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                z = TextUtils.equals(query.getString(bGi_.intValue()), "true");
            }
            if (query != null) {
                query.close();
            }
            LogUtil.c("DeviceCommonUtil", "isMidwareAuthority result: ", Boolean.valueOf(z));
            return z;
        }
        LogUtil.a("DeviceCommonUtil", "isMidWareAuthority cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    private static void a() {
        LogUtil.c("DeviceCommonUtil", "checkMidWare");
        if (e() == -1 && c()) {
            d(true);
        }
    }

    public static void d(Boolean bool) {
        try {
            if (bool == null) {
                LogUtil.a("DeviceCommonUtil", "setMidWareValue null");
                return;
            }
            LogUtil.c("DeviceCommonUtil", "enter setMidWareValue :", bool);
            ContentResolver contentResolver = BaseApplication.e().getContentResolver();
            Uri parse = Uri.parse(jdz.d);
            ContentValues contentValues = new ContentValues();
            if (bool.booleanValue()) {
                contentValues.put("value", "true");
            } else {
                contentValues.put("value", "false");
            }
            LogUtil.c("DeviceCommonUtil", "setMidWareValue result: ", Integer.valueOf(contentResolver.update(parse, contentValues, null, null)));
        } catch (SQLiteException e) {
            LogUtil.c("DeviceCommonUtil", "SQLiteException:", e.getMessage());
        } catch (IllegalArgumentException e2) {
            LogUtil.e("DeviceCommonUtil", "setMidWareValue IllegalArgumentException", ExceptionUtils.d(e2));
        }
    }

    public static int e() {
        Cursor query;
        String[] strArr = {"value"};
        String[] strArr2 = {"is_Forbidden"};
        try {
            query = BaseApplication.e().getContentResolver().query(Uri.parse(jdz.g), strArr, "name=?", strArr2, null);
            try {
                LogUtil.c("DeviceCommonUtil", jdz.a("NotificationFlags", strArr, "name=?", strArr2, null));
            } finally {
            }
        } catch (Exception e) {
            LogUtil.e("DeviceCommonUtil", "isForbidden exception: ", ExceptionUtils.d(e));
        }
        if (query != null && query.getCount() != 0) {
            if (!query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                LogUtil.c("DeviceCommonUtil", "USE_SYNERGY: DO_NOT_INIT,isForbidden: ", -1);
                return -1;
            }
            Integer bGi_ = jdz.bGi_("value", query, "DeviceCommonUtil");
            if (bGi_ == null) {
                LogUtil.a("DeviceCommonUtil", "isForbidden columnIndex is null");
                if (query != null) {
                    query.close();
                }
                return -1;
            }
            int i = query.getInt(bGi_.intValue());
            Object[] objArr = new Object[4];
            objArr[0] = "USE_SYNERGY: ";
            objArr[1] = Boolean.valueOf(i == 1);
            objArr[2] = ",isForbidden: ";
            objArr[3] = Integer.valueOf(i);
            LogUtil.c("DeviceCommonUtil", objArr);
            if (query != null) {
                query.close();
            }
            return i;
        }
        LogUtil.a("DeviceCommonUtil", "isForbidden cursor is null");
        if (query != null) {
            query.close();
        }
        return -1;
    }

    public static boolean c() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.e().getPackageManager();
        try {
            if (MagicBuild.f13130a) {
                packageInfo = packageManager.getPackageInfo("com.hihonor.synergy", 128);
            } else {
                packageInfo = packageManager.getPackageInfo("com.huawei.synergy", 128);
            }
            if (packageInfo == null) {
                LogUtil.a("DeviceCommonUtil", "isMiddleWearSupportHealth packageInfo is null");
                return false;
            }
            String str = packageInfo.versionName;
            LogUtil.c("DeviceCommonUtil", "isMiddleWearSupportHealth version:", str);
            return (str == null || "1.0".equals(str)) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("DeviceCommonUtil", "isMiddleWearSupportHealth not exist");
            return false;
        }
    }

    public static boolean d() {
        try {
            BaseApplication.e().getPackageManager().getApplicationInfo("com.huawei.iconnect", 0);
            LogUtil.c("DeviceCommonUtil", "iconnect pkg exist.");
            PackageInfo packageInfo = BaseApplication.e().getPackageManager().getPackageInfo("com.huawei.iconnect", 0);
            if (packageInfo == null) {
                return false;
            }
            int i = packageInfo.versionCode;
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DeviceCommonUtil", "iconnect code: ", Integer.valueOf(i));
            return i > 1;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("DeviceCommonUtil", "iconnect pkg do not exist.");
            return false;
        }
    }
}
