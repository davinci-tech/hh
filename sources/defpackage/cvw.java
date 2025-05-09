package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cvw {
    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hwcommonmodel.datatypes.HealthSupportModel c() {
        /*
            com.google.gson.Gson r0 = new com.google.gson.Gson
            r0.<init>()
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r2 = 10024(0x2728, float:1.4047E-41)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "wear_join_supported_device"
            java.lang.String r1 = health.compact.a.SharedPreferenceManager.b(r1, r2, r3)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r3 = "CompatibilityUtil"
            if (r2 != 0) goto L31
            java.lang.Class<com.huawei.hwcommonmodel.datatypes.HealthSupportModel> r2 = com.huawei.hwcommonmodel.datatypes.HealthSupportModel.class
            java.lang.Object r0 = r0.fromJson(r1, r2)     // Catch: com.google.gson.JsonSyntaxException -> L27
            com.huawei.hwcommonmodel.datatypes.HealthSupportModel r0 = (com.huawei.hwcommonmodel.datatypes.HealthSupportModel) r0     // Catch: com.google.gson.JsonSyntaxException -> L27
            goto L3b
        L27:
            java.lang.String r0 = "JsonSyntaxException."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
            goto L3a
        L31:
            java.lang.String r0 = "json is empty."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r3, r0)
        L3a:
            r0 = 0
        L3b:
            if (r0 != 0) goto L42
            com.huawei.hwcommonmodel.datatypes.HealthSupportModel r0 = new com.huawei.hwcommonmodel.datatypes.HealthSupportModel
            r0.<init>()
        L42:
            boolean r1 = defpackage.jcu.g
            r0.setSupportNyx(r1)
            boolean r1 = health.compact.a.Utils.o()
            r2 = 1
            if (r1 == 0) goto L59
            r4 = 0
            r0.setSupportR1(r4)
            r0.setSupportLeo(r2)
            r0.setSupportPro(r2)
            goto L6d
        L59:
            boolean r4 = defpackage.jcu.i
            r0.setSupportR1(r4)
            java.lang.String r4 = "beta version support leo."
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r4)
            r0.setSupportLeo(r2)
            r0.setSupportPro(r2)
        L6d:
            java.lang.String r2 = r0.toString()
            java.lang.String r4 = "isOversea:"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r5 = "getHealthSupportModel:"
            java.lang.Object[] r1 = new java.lang.Object[]{r5, r2, r4, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cvw.c():com.huawei.hwcommonmodel.datatypes.HealthSupportModel");
    }

    public static void c(List<DeviceInfo> list, String str) {
        LogUtil.a("CompatibilityUtil", "Enter refreshDeviceSizeForWear tag:", str);
        int i = 0;
        if (list != null) {
            Iterator<DeviceInfo> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getProductType() != 16) {
                    i++;
                }
            }
            LogUtil.a("CompatibilityUtil", "deviceInfoListlist:", Integer.valueOf(list.size()), "number:", Integer.valueOf(i));
        } else {
            LogUtil.h("CompatibilityUtil", "list is null, so number is 0.");
        }
        d(i);
    }

    private static void d(int i) {
        DbManager.c(BaseApplication.getContext(), String.valueOf(10024), "device_size_device_table", 2, "deviceSizeKey varchar primary key ,deviceSizeValue deviceSizeValue varchar");
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceSizeKey", "deviceSizeValue");
        contentValues.put("deviceSizeValue", String.valueOf(i));
        if (a()) {
            DbManager.b bVar = new DbManager.b();
            bVar.b(BaseApplication.getContext());
            bVar.e(String.valueOf(10024));
            bVar.c("device_size_device_table");
            bVar.a(2);
            DbManager.bGH_(bVar, contentValues, null);
            return;
        }
        DbManager.bGC_(BaseApplication.getContext(), String.valueOf(10024), "device_size_device_table", 2, contentValues);
    }

    private static boolean a() {
        Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), String.valueOf(10024), "device_size_device_table", 2, "deviceSizeKey='deviceSizeValue'");
        if (bGE_ != null) {
            r1 = bGE_.getCount() > 0;
            bGE_.close();
        }
        LogUtil.a("CompatibilityUtil", "isHaveKeyInfo:", Boolean.valueOf(r1));
        return r1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        if (r5.isClosed() == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0071, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
        if (r5.isClosed() == false) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e() {
        /*
            java.lang.String r0 = "enter getHasDeviceDbInfo getHasDeviceDbInfo selection: "
            java.lang.String r1 = "deviceSizeKey='deviceSizeValue'"
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r2 = "CompatibilityUtil"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            java.lang.String r0 = ""
            r3 = 0
            r4 = 1
            r5 = 0
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r7 = 10024(0x2728, float:1.4047E-41)
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            java.lang.String r8 = "device_size_device_table"
            r9 = 2
            android.database.Cursor r5 = health.compact.a.DbManager.bGE_(r6, r7, r8, r9, r1)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            if (r5 == 0) goto L49
            boolean r1 = r5.moveToNext()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            if (r1 == 0) goto L35
            java.lang.String r1 = "deviceSizeValue"
            int r1 = r5.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            java.lang.String r0 = r5.getString(r1)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
        L35:
            java.lang.Object[] r1 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            java.lang.String r6 = "content is "
            r1[r3] = r6     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r1[r4] = r0     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            if (r5 == 0) goto L74
            boolean r1 = r5.isClosed()
            if (r1 != 0) goto L74
            goto L71
        L49:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            java.lang.String r6 = "Query Storage Data fail."
            r1[r3] = r6     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            if (r5 == 0) goto L5d
            boolean r1 = r5.isClosed()
            if (r1 != 0) goto L5d
            r5.close()
        L5d:
            return r0
        L5e:
            r0 = move-exception
            goto L75
        L60:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L5e
            java.lang.String r4 = "Query Storage exception."
            r1[r3] = r4     // Catch: java.lang.Throwable -> L5e
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L5e
            if (r5 == 0) goto L74
            boolean r1 = r5.isClosed()
            if (r1 != 0) goto L74
        L71:
            r5.close()
        L74:
            return r0
        L75:
            if (r5 == 0) goto L80
            boolean r1 = r5.isClosed()
            if (r1 != 0) goto L80
            r5.close()
        L80:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cvw.e():java.lang.String");
    }
}
