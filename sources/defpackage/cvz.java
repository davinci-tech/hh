package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.MagicBuild;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cvz {
    public static boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("DeviceCommonUtil", "deviceInfo is null");
            return false;
        }
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel)) {
            LogUtil.h("DeviceCommonUtil", "deviceModel is empty");
            return false;
        }
        List<String> c = cfc.e().c();
        if (c == null) {
            LogUtil.b("DeviceCommonUtil", "modelList is null");
            return false;
        }
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            if (deviceModel.contains(it.next())) {
                LogUtil.a("DeviceCommonUtil", "deviceModel is honor");
                return true;
            }
        }
        return false;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceCommonUtil", "fileName is empty");
            return false;
        }
        List<String> c = cfc.e().c();
        if (c == null) {
            LogUtil.b("DeviceCommonUtil", "modelList is null");
            return false;
        }
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static int a(String str) {
        LogUtil.a("DeviceCommonUtil", "getClockStateByDevice encryptDeviceMac: ", str);
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri parse = Uri.parse(jdz.f13757a);
        Cursor cursor = null;
        try {
            try {
                Cursor query = contentResolver.query(parse, new String[]{"clockValue"}, "deviceMac = ?", new String[]{str}, null);
                if (query == null) {
                    LogUtil.a("DeviceCommonUtil", "getClockStateByDevice:", -1);
                    if (query != null) {
                        query.close();
                    }
                    return -1;
                }
                if (!query.moveToFirst()) {
                    LogUtil.a("DeviceCommonUtil", "getClockStateByDevice cursor.moveToFirst is null");
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("deviceMac", str);
                    contentValues.put("clockValue", "true");
                    contentResolver.insert(parse, contentValues);
                    LogUtil.a("DeviceCommonUtil", "getClockStateByDevice cursor.moveToFirst is null");
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                String string = query.getString(query.getColumnIndex("clockValue"));
                query.close();
                LogUtil.a("DeviceCommonUtil", "getClockStateByDevice:", string);
                if (TextUtils.equals(string, "true")) {
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                if (query != null) {
                    query.close();
                }
                return 0;
            } catch (Exception unused) {
                LogUtil.b("DeviceCommonUtil", "getClockStateByDevice exception");
                if (0 != 0) {
                    cursor.close();
                }
                LogUtil.a("DeviceCommonUtil", "getClockStateByDevice end return");
                return -1;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void b(String str, boolean z) {
        Cursor cursor = null;
        try {
            try {
                try {
                    try {
                    } catch (IllegalArgumentException e) {
                        LogUtil.b("DeviceCommonUtil", "IllegalArgumentException: ", e.getMessage());
                        if (0 == 0) {
                            return;
                        }
                    }
                } catch (SQLiteException e2) {
                    LogUtil.a("DeviceCommonUtil", "SQLiteException:", e2.getMessage());
                    if (0 == 0) {
                        return;
                    }
                }
            } catch (Exception e3) {
                LogUtil.b("DeviceCommonUtil", "Exception: ", e3.getMessage());
                if (0 == 0) {
                    return;
                }
            }
            if (str == null) {
                LogUtil.h("DeviceCommonUtil", "setClockStateValue deviceMac is null");
                return;
            }
            LogUtil.a("DeviceCommonUtil", "setClockStateValue device: ", str, " clockState: ", Boolean.valueOf(z));
            LogUtil.a("DeviceCommonUtil", "setClockStateValue encrytDeviceMac: ", str);
            ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
            Uri parse = Uri.parse(jdz.f13757a);
            String[] strArr = {str};
            ContentValues contentValues = new ContentValues();
            contentValues.put("deviceMac", str);
            if (z) {
                contentValues.put("clockValue", "true");
            } else {
                contentValues.put("clockValue", "false");
            }
            cursor = contentResolver.query(parse, null, "deviceMac = ?", strArr, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    LogUtil.a("DeviceCommonUtil", "setClockStateValue is  query data, update");
                    LogUtil.a("DeviceCommonUtil", "setClockStateValue result: ", Integer.valueOf(contentResolver.update(parse, contentValues, "deviceMac = ?", strArr)));
                } else {
                    LogUtil.a("DeviceCommonUtil", "setClockStateValue is not query data, insert ");
                    contentResolver.insert(parse, contentValues);
                }
            } else {
                LogUtil.a("DeviceCommonUtil", "setClockStateValue cursor is null");
                contentResolver.insert(parse, contentValues);
            }
            if (cursor == null) {
                return;
            }
            cursor.close();
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean d() {
        Cursor query;
        e();
        boolean z = false;
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.d), null, null, null, null);
            try {
                LogUtil.a("DeviceCommonUtil", jdz.a("MidwareAuthority", null, null, null, null));
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("DeviceCommonUtil", "isMidWareAuthority exception: ", LogAnonymous.b((Throwable) e));
        }
        if (query != null && query.getCount() != 0) {
            if (query.moveToFirst()) {
                Integer bGi_ = jdz.bGi_("value", query, "DeviceCommonUtil");
                if (bGi_ == null) {
                    LogUtil.h("DeviceCommonUtil", "isMidWareAuthority columnIndex is null");
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
            LogUtil.a("DeviceCommonUtil", "isMidwareAuthority result: ", Boolean.valueOf(z));
            return z;
        }
        LogUtil.h("DeviceCommonUtil", "isMidWareAuthority cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    private static void e() {
        LogUtil.a("DeviceCommonUtil", "checkMidWare");
        if (a() == -1 && c()) {
            c((Boolean) true);
        }
    }

    public static void c(Boolean bool) {
        try {
            if (bool == null) {
                LogUtil.h("DeviceCommonUtil", "setMidWareValue null");
                return;
            }
            LogUtil.a("DeviceCommonUtil", "enter setMidWareValue :", bool);
            ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
            Uri parse = Uri.parse(jdz.d);
            ContentValues contentValues = new ContentValues();
            if (bool.booleanValue()) {
                contentValues.put("value", "true");
            } else {
                contentValues.put("value", "false");
            }
            LogUtil.a("DeviceCommonUtil", "setMidWareValue result: ", Integer.valueOf(contentResolver.update(parse, contentValues, null, null)));
        } catch (SQLiteException e) {
            LogUtil.a("DeviceCommonUtil", "SQLiteException:", e.getMessage());
        } catch (IllegalArgumentException e2) {
            LogUtil.b("DeviceCommonUtil", "IllegalArgumentException: ", e2.getMessage());
        }
    }

    public static int a() {
        Cursor query;
        String[] strArr = {"value"};
        String[] strArr2 = {"is_Forbidden"};
        try {
            query = BaseApplication.getContext().getContentResolver().query(Uri.parse(jdz.g), strArr, "name=?", strArr2, null);
            try {
                LogUtil.a("DeviceCommonUtil", jdz.a("NotificationFlags", strArr, "name=?", strArr2, null));
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("DeviceCommonUtil", "isForbidden exception: ", LogAnonymous.b((Throwable) e));
        }
        if (query != null && query.getCount() != 0) {
            if (!query.moveToFirst()) {
                if (query != null) {
                    query.close();
                }
                LogUtil.a("DeviceCommonUtil", "USE_SYNERGY: DO_NOT_INIT,isForbidden: ", -1);
                return -1;
            }
            Integer bGi_ = jdz.bGi_("value", query, "DeviceCommonUtil");
            if (bGi_ == null) {
                LogUtil.h("DeviceCommonUtil", "isForbidden columnIndex is null");
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
            LogUtil.a("DeviceCommonUtil", objArr);
            if (query != null) {
                query.close();
            }
            return i;
        }
        LogUtil.h("DeviceCommonUtil", "isForbidden cursor is null");
        if (query != null) {
            query.close();
        }
        return -1;
    }

    public static boolean c() {
        PackageInfo packageInfo;
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        try {
            if (MagicBuild.f13130a) {
                packageInfo = packageManager.getPackageInfo("com.hihonor.synergy", 128);
            } else {
                packageInfo = packageManager.getPackageInfo("com.huawei.synergy", 128);
            }
            if (packageInfo == null) {
                LogUtil.h("DeviceCommonUtil", "isMiddleWearSupportHealth packageInfo is null");
                return false;
            }
            String str = packageInfo.versionName;
            LogUtil.a("DeviceCommonUtil", "isMiddleWearSupportHealth version:", str);
            return (str == null || "1.0".equals(str)) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("DeviceCommonUtil", "isMiddleWearSupportHealth not exist");
            return false;
        }
    }

    public static void a(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceCommonUtil", "mProductType", Integer.valueOf(deviceInfo.getProductType()));
        if (deviceInfo.getProductType() == 34) {
            if ("010404".equals(deviceInfo.getManufacture())) {
                deviceInfo.setHiLinkDeviceId("005W");
            } else if ("010303".equals(deviceInfo.getManufacture())) {
                if (deviceInfo.getDeviceModel() == null) {
                    ReleaseLogUtil.d("R_DeviceCommonUtil", "HUAWEI WATCH GT 2 Manufacture getDeviceModel is empty");
                    return;
                } else if (deviceInfo.getDeviceModel().toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
                    deviceInfo.setHiLinkDeviceId("M003");
                } else {
                    deviceInfo.setHiLinkDeviceId("005X");
                }
            }
            LogUtil.c("DeviceCommonUtil", "supplementsHiLinkDeviceId deviceInfo obtain :", deviceInfo.getHiLinkDeviceId());
        }
        LogUtil.c("DeviceCommonUtil", deviceInfo.getDeviceUdid(), " mHiLinkDeviceId not match ");
    }
}
