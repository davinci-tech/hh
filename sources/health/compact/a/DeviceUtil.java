package health.compact.a;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import defpackage.tno;
import defpackage.tnr;

/* loaded from: classes.dex */
public class DeviceUtil {
    protected DeviceUtil() {
    }

    public static void b(Context context) {
        if (tnr.c()) {
            com.huawei.hwlogsmodel.LogUtil.a("DeviceUtil", "startDaemonService");
            Intent intent = new Intent();
            intent.setAction("com.huawei.daemonservice");
            intent.setPackage(context.getPackageName());
            intent.putExtra("startSyncData", "startSyncData");
            try {
                context.startService(intent);
            } catch (IllegalStateException | SecurityException unused) {
                com.huawei.hwlogsmodel.LogUtil.h("DeviceUtil", "start daemon service fail");
            }
        }
    }

    public static void b() {
        tnr.b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0091, code lost:
    
        if (r0.isClosed() == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a7, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a5, code lost:
    
        if (r0.isClosed() == false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a() {
        /*
            boolean r0 = health.compact.a.EnvironmentUtils.b()
            if (r0 == 0) goto L9
            defpackage.tnr.b()
        L9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "module_"
            r0.<init>(r1)
            r1 = 1000(0x3e8, float:1.401E-42)
            java.lang.String r2 = java.lang.String.valueOf(r1)
            r0.append(r2)
            java.lang.String r2 = "_has_device_table"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            boolean r0 = e(r0)
            java.lang.String r2 = "DeviceUtil"
            r3 = 0
            if (r0 != 0) goto L36
            java.lang.String r0 = "getHasDeviceDbInfo select table is not exist."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)
            return r3
        L36:
            r0 = 0
            r4 = 1
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r6 = "has_device_table"
            r7 = 2
            android.database.Cursor r0 = health.compact.a.DbManager.bGE_(r5, r1, r6, r7, r0)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r0 != 0) goto L5e
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r5 = "Query Storage Data fail."
            r1[r3] = r5     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r0 == 0) goto L5d
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto L5d
            r0.close()
        L5d:
            return r3
        L5e:
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r1 == 0) goto L8b
            java.lang.String r1 = "hasDeviceValue"
            int r1 = r0.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            int r5 = health.compact.a.CommonUtils.e(r1, r3)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r5 <= 0) goto L5e
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            java.lang.String r6 = "There is one or more LOCAL_WEAR_DEVICES whether connected or not,content is:"
            r5[r3] = r6     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            r5[r4] = r1     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            com.huawei.hwlogsmodel.LogUtil.a(r2, r5)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L96
            if (r0 == 0) goto L8a
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto L8a
            r0.close()
        L8a:
            return r4
        L8b:
            if (r0 == 0) goto Laa
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto Laa
            goto La7
        L94:
            r1 = move-exception
            goto Lab
        L96:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L94
            java.lang.String r4 = "Query Storage exception"
            r1[r3] = r4     // Catch: java.lang.Throwable -> L94
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L94
            if (r0 == 0) goto Laa
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto Laa
        La7:
            r0.close()
        Laa:
            return r3
        Lab:
            if (r0 == 0) goto Lb6
            boolean r2 = r0.isClosed()
            if (r2 != 0) goto Lb6
            r0.close()
        Lb6:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.DeviceUtil.a():boolean");
    }

    public static void fbV_(Context context, boolean z, Bundle bundle) {
        BluetoothAdapter defaultAdapter;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("DeviceUtil", "startPhoneService ", "context is null");
            return;
        }
        if (EnvironmentUtils.b()) {
            tnr.b();
        }
        if (z && ((defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || defaultAdapter.getState() != 12)) {
            com.huawei.hwlogsmodel.LogUtil.a("DeviceUtil", "startPhoneService ", "bluetooth state not on");
            return;
        }
        if ((bundle == null || !"com.huawei.ohos.health.device".equals(bundle.getString("package"))) && !(a() && tno.c())) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.StartPhoneService");
        intent.setPackage(context.getPackageName());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            context.startService(intent);
        } catch (IllegalStateException | SecurityException unused) {
            com.huawei.hwlogsmodel.LogUtil.h("DeviceUtil", "start phone service fail");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0066, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
    
        if (r3 == null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean e(java.lang.String r6) {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hwdataaccessmodel.db.contentprovider.DbContentProvider r0 = com.huawei.hwdataaccessmodel.db.contentprovider.DbContentProvider.c(r0)
            java.lang.String r1 = "DeviceUtil"
            r2 = 0
            if (r0 != 0) goto L17
            java.lang.String r6 = "isTableExist provider is null."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)
            return r2
        L17:
            r3 = 0
            android.database.sqlite.SQLiteDatabase r0 = r0.bGL_()     // Catch: android.database.sqlite.SQLiteException -> L1d
            goto L27
        L1d:
            java.lang.String r0 = "isTableExist getDatabase SQLiteException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            r0 = r3
        L27:
            if (r0 != 0) goto L33
            java.lang.String r6 = "isTableExist sqLiteDatabase is null."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)
            return r2
        L33:
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r5 = "select count(*) from sqlite_master where type = 'table' and name = '%s'"
            java.lang.String r6 = java.lang.String.format(r4, r5, r6)
            r4 = 1
            android.database.Cursor r3 = r0.rawQuery(r6, r3)     // Catch: java.lang.Throwable -> L5a
            if (r3 == 0) goto L54
            boolean r6 = r3.moveToNext()     // Catch: java.lang.Throwable -> L5a
            if (r6 == 0) goto L54
            int r6 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L5a
            if (r6 <= 0) goto L54
            r2 = r4
        L54:
            if (r3 == 0) goto L66
        L56:
            r3.close()
            goto L66
        L5a:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L67
            java.lang.String r0 = "isTableExist query occur exception."
            r6[r2] = r0     // Catch: java.lang.Throwable -> L67
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L66
            goto L56
        L66:
            return r2
        L67:
            r6 = move-exception
            if (r3 == 0) goto L6d
            r3.close()
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.DeviceUtil.e(java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x006a, code lost:
    
        if (r5 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x007d, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("DeviceUtil", "getExerciseInsertTime content = ", r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0086, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x007a, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0078, code lost:
    
        if (r5 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c() {
        /*
            java.lang.String r0 = "DeviceUtil"
            java.lang.String r1 = "enter getExerciseInsertTime"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "R_DeviceUtil"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "huid='"
            r1.<init>(r3)
            android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            health.compact.a.KeyValDbManager r3 = health.compact.a.KeyValDbManager.b(r3)
            java.lang.String r4 = "user_id"
            java.lang.String r3 = r3.e(r4)
            r1.append(r3)
            java.lang.String r3 = "'"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "getExerciseInsertTime selection"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r3)
            java.lang.String r2 = ""
            r3 = 0
            r4 = 1
            r5 = 0
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            r7 = 22
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            java.lang.String r8 = "exercise_insert_time_table"
            r9 = 2
            android.database.Cursor r5 = health.compact.a.DbManager.bGE_(r6, r7, r8, r9, r1)     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            java.lang.String r6 = "getExerciseInsertTime start to get cursor."
            r1[r3] = r6     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            if (r5 == 0) goto L6a
            boolean r1 = r5.moveToNext()     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            if (r1 == 0) goto L6a
            java.lang.String r1 = "exerciseInsertTime"
            int r1 = r5.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            java.lang.String r1 = r5.getString(r1)     // Catch: java.lang.Throwable -> L6d android.database.SQLException -> L6f
            r2 = r1
        L6a:
            if (r5 == 0) goto L7d
            goto L7a
        L6d:
            r0 = move-exception
            goto L87
        L6f:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L6d
            java.lang.String r4 = "Query Storage exception"
            r1[r3] = r4     // Catch: java.lang.Throwable -> L6d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L7d
        L7a:
            r5.close()
        L7d:
            java.lang.String r1 = "getExerciseInsertTime content = "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            return r2
        L87:
            if (r5 == 0) goto L8c
            r5.close()
        L8c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.DeviceUtil.c():java.lang.String");
    }
}
