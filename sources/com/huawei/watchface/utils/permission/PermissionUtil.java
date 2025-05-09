package com.huawei.watchface.utils.permission;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.cq;
import com.huawei.watchface.dv;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import defpackage.mcf;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class PermissionUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11202a = Build.MANUFACTURER.toLowerCase(Locale.ROOT);

    public enum PermissionType {
        STORAGE("STORAGE"),
        LOCATION("LOCATION"),
        LOCATION_WITH_BACKGROUND("LOCATION_WITH_BACKGROUND"),
        STORAGE_LOCATION("STORAGE_LOCATION"),
        MEDIA_VIDEO_IMAGES("MEDIA_VIDEO_IMAGES"),
        MEDIA_AUDIO("MEDIA_AUDIO"),
        CAMERA_IMAGE("CAMERA_IMAGE"),
        NONE("NONE"),
        STORAGE_NETWORK_WIFI("STORAGE_NETWORK_WIFI"),
        AUDIO_CALLS("AUDIO_CALLS"),
        PHONE_STATE("PHONE_STATE"),
        READ_CONTACT("READ_CONTACT"),
        READ_WRITE_CONTACT("READ_WRITE_CONTACT"),
        CAMERA("CAMERA"),
        DISTRIBUTED_DATASYNC("DISTRIBUTED_DATASYNC"),
        RECORD_AUDIO("RECORD_AUDIO"),
        CALENDAR("CALENDAR"),
        SCAN("SCAN"),
        AUDIO_LOCATION("AUDIO_LOCATION"),
        MANAGE_EXTERNAL_STORAGE("MANAGE_EXTERNAL_STORAGE");

        private final String permission;

        PermissionType(String str) {
            this.permission = str;
        }

        public String getPermission() {
            return this.permission;
        }
    }

    private PermissionUtil() {
    }

    public static boolean isAndroidQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static int a(Context context) {
        StringBuilder sb = new StringBuilder("manufacturer = ");
        String str = f11202a;
        sb.append(str);
        sb.append(", api level= ");
        sb.append(Build.VERSION.SDK_INT);
        HwLog.i("PermissionUtil", sb.toString());
        if (str.contains("huawei")) {
            return b(context);
        }
        if (str.contains("xiaomi")) {
            return d(context);
        }
        if (str.contains("oppo")) {
            return e(context);
        }
        if (str.contains("vivo")) {
            return c(context);
        }
        return (str.contains("samsung") || str.contains("meizu")) ? 0 : 2;
    }

    public static int b(Context context) {
        HwLog.i("PermissionUtil", "checkOnEMUI");
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        try {
            Class<?> cls = Class.forName("com.huawei.hsm.permission.PermissionManager");
            Object invoke = cls.getDeclaredMethod("canSendBroadcast", Context.class, Intent.class).invoke(cls, context, intent);
            if (invoke == null) {
                return 2;
            }
            boolean booleanValue = ((Boolean) invoke).booleanValue();
            HwLog.i("PermissionUtil", "checkOnEMUI check permission canSendBroadcast invoke result = " + booleanValue);
            return booleanValue ? 0 : -1;
        } catch (ClassNotFoundException e) {
            e = e;
            HwLog.i("PermissionUtil", "checkOnEMUI error:" + HwLog.printException(e));
            return 2;
        } catch (IllegalAccessException e2) {
            e = e2;
            HwLog.i("PermissionUtil", "checkOnEMUI error:" + HwLog.printException(e));
            return 2;
        } catch (NoSuchMethodException e3) {
            e = e3;
            HwLog.i("PermissionUtil", "checkOnEMUI error:" + HwLog.printException(e));
            return 2;
        } catch (InvocationTargetException e4) {
            e = e4;
            HwLog.i("PermissionUtil", "checkOnEMUI error:" + HwLog.printException(e));
            return 2;
        } catch (Exception e5) {
            HwLog.i("PermissionUtil", "checkOnEMUI unknown error:" + HwLog.printException(e5));
            return 2;
        }
    }

    public static int c(Context context) {
        Uri parse;
        HwLog.i("PermissionUtil", "checkOnVIVO enter");
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            HwLog.i("PermissionUtil", "contentResolver is null");
            return 2;
        }
        Cursor cursor = null;
        try {
            try {
                parse = Uri.parse("content://com.bbk.launcher2.settings/favorites");
            } catch (Exception e) {
                HwLog.e("PermissionUtil", "checkOnVIVO error:" + HwLog.printException(e));
            }
            if (parse != null && !dv.a(context, parse)) {
                HwLog.i("PermissionUtil", "Uri is not valid");
                return 2;
            }
            cursor = contentResolver.query(parse, null, null, null, null);
            if (cursor == null) {
                HwLog.i("PermissionUtil", "checkOnVIVO cursor is null");
                return 2;
            }
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                HwLog.i("PermissionUtil", "title by query is " + string);
                if (!TextUtils.isEmpty(string) && string.equals(g(context))) {
                    int i = cursor.getInt(cursor.getColumnIndexOrThrow("shortcutPermission"));
                    HwLog.i("PermissionUtil", "permission value is " + i);
                    if (i == 1 || i == 17) {
                        cq.a(cursor);
                        return -1;
                    }
                    if (i == 16) {
                        cq.a(cursor);
                        return 0;
                    }
                    if (i == 18) {
                        return 1;
                    }
                }
            }
            return 2;
        } finally {
            cq.a(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ac A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(android.content.Context r10) {
        /*
            java.lang.String r0 = "checkOnMIUI"
            java.lang.String r1 = "PermissionUtil"
            com.huawei.watchface.utils.HwLog.i(r1, r0)
            r0 = 2
            java.lang.String r2 = "appops"
            java.lang.Object r2 = r10.getSystemService(r2)     // Catch: java.lang.Exception -> Lad
            android.app.AppOpsManager r2 = (android.app.AppOpsManager) r2     // Catch: java.lang.Exception -> Lad
            android.content.Context r3 = r10.getApplicationContext()     // Catch: java.lang.Exception -> Lad
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Exception -> Lad
            android.content.pm.ApplicationInfo r10 = r10.getApplicationInfo()     // Catch: java.lang.Exception -> Lad
            int r10 = r10.uid     // Catch: java.lang.Exception -> Lad
            java.lang.Class<android.app.AppOpsManager> r4 = android.app.AppOpsManager.class
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Exception -> Lad
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.Exception -> Lad
            r5 = 3
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> Lad
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> Lad
            r8 = 0
            r6[r8] = r7     // Catch: java.lang.Exception -> Lad
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> Lad
            r9 = 1
            r6[r9] = r7     // Catch: java.lang.Exception -> Lad
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r0] = r7     // Catch: java.lang.Exception -> Lad
            java.lang.String r7 = "checkOpNoThrow"
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r7, r6)     // Catch: java.lang.Exception -> Lad
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lad
            r6 = 10017(0x2721, float:1.4037E-41)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> Lad
            r5[r8] = r6     // Catch: java.lang.Exception -> Lad
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Exception -> Lad
            r5[r9] = r10     // Catch: java.lang.Exception -> Lad
            r5[r0] = r3     // Catch: java.lang.Exception -> Lad
            java.lang.Object r10 = r4.invoke(r2, r5)     // Catch: java.lang.Exception -> Lad
            if (r10 != 0) goto L5d
            java.lang.String r10 = "checkOnMIUI check permission checkOpNoThrowMethod(AppOpsManager) invoke result is null"
            com.huawei.watchface.utils.HwLog.i(r1, r10)     // Catch: java.lang.Exception -> Lad
            return r0
        L5d:
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Exception -> Lad
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lad
            java.lang.String r3 = "checkOnMIUI check permission checkOpNoThrowMethod(AppOpsManager) invoke result = "
            r2.<init>(r3)     // Catch: java.lang.Exception -> Lad
            r2.append(r10)     // Catch: java.lang.Exception -> Lad
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lad
            com.huawei.watchface.utils.HwLog.i(r1, r2)     // Catch: java.lang.Exception -> Lad
            int r2 = r10.hashCode()     // Catch: java.lang.Exception -> Lad
            r3 = 48
            r4 = -1
            if (r2 == r3) goto L98
            r3 = 49
            if (r2 == r3) goto L8e
            r3 = 53
            if (r2 == r3) goto L84
            goto La2
        L84:
            java.lang.String r2 = "5"
            boolean r10 = r10.equals(r2)     // Catch: java.lang.Exception -> Lad
            if (r10 == 0) goto La2
            r10 = r0
            goto La3
        L8e:
            java.lang.String r2 = "1"
            boolean r10 = r10.equals(r2)     // Catch: java.lang.Exception -> Lad
            if (r10 == 0) goto La2
            r10 = r9
            goto La3
        L98:
            java.lang.String r2 = "0"
            boolean r10 = r10.equals(r2)     // Catch: java.lang.Exception -> Lad
            if (r10 == 0) goto La2
            r10 = r8
            goto La3
        La2:
            r10 = r4
        La3:
            if (r10 == 0) goto Lac
            if (r10 == r9) goto Lab
            if (r10 == r0) goto Laa
            return r0
        Laa:
            return r9
        Lab:
            return r4
        Lac:
            return r8
        Lad:
            r10 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "checkOnMIUI error:"
            r2.<init>(r3)
            java.lang.String r10 = com.huawei.watchface.utils.HwLog.printException(r10)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            com.huawei.watchface.utils.HwLog.i(r1, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.permission.PermissionUtil.d(android.content.Context):int");
    }

    public static int e(Context context) {
        HwLog.i("PermissionUtil", "checkOnOPPO");
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            HwLog.i("PermissionUtil", "checkOnOPPO contentResolver is null");
            return 2;
        }
        Uri parse = Uri.parse("content://settings/secure/launcher_shortcut_permission_settings");
        if (parse != null && !dv.a(context, parse)) {
            HwLog.i("PermissionUtil", "Uri is not valid");
            return 2;
        }
        Cursor query = contentResolver.query(parse, null, null, null, null);
        if (query == null) {
            HwLog.i("PermissionUtil", "checkOnOPPO cursor is null");
            return 2;
        }
        try {
            String packageName = context.getApplicationContext().getPackageName();
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("value"));
                HwLog.i("PermissionUtil", "permission value is " + string);
                if (!TextUtils.isEmpty(string)) {
                    if (!string.contains(packageName + ", 1")) {
                        if (string.contains(packageName + ", 0")) {
                            cq.a(query);
                            return -1;
                        }
                    } else {
                        cq.a(query);
                        return 0;
                    }
                }
            }
            return 2;
        } catch (Exception e) {
            HwLog.e("PermissionUtil", "checkOnOPPO error:" + HwLog.printException(e));
            return 2;
        } finally {
            cq.a(query);
        }
    }

    private static String g(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            HwLog.e("PermissionUtil", "getAppName error:" + HwLog.printException((Exception) e));
            return "";
        } catch (RuntimeException e2) {
            HwLog.e("PermissionUtil", "runtimeException " + HwLog.printException((Exception) e2));
            return "";
        }
    }

    public static void f(Context context) {
        Intent h;
        String str = f11202a;
        if (str.contains("huawei")) {
            h = a();
        } else if (str.contains("xiaomi")) {
            h = i(context);
        } else if (str.contains("oppo")) {
            h = k(context);
        } else if (str.contains("vivo")) {
            h = j(context);
        } else if (str.contains("meizu")) {
            h = l(context);
        } else {
            h = h(context);
        }
        try {
            h.addFlags(268435456);
            context.startActivity(h);
        } catch (Exception e) {
            HwLog.e("PermissionUtil", "start error:" + HwLog.printException(e));
            Intent h2 = h(context);
            h2.addFlags(268435456);
            mcf.cfJ_(context, h2);
        }
    }

    private static Intent h(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }

    private static Intent a() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
        return intent;
    }

    private static Intent i(Context context) {
        String a2 = CommonUtils.a("ro.miui.ui.version.name", (String) null);
        if (TextUtils.isEmpty(a2)) {
            return h(context);
        }
        try {
            int parseInt = Integer.parseInt(SafeString.substring(a2, 1));
            if (parseInt >= 9) {
                Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity"));
                intent.putExtra("extra_pkgname", context.getPackageName());
                return intent;
            }
            if (parseInt >= 7) {
                Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent2.putExtra("extra_pkgname", context.getPackageName());
                return intent2;
            }
            return h(context);
        } catch (NumberFormatException e) {
            HwLog.e("PermissionUtil", "xiaomiApi error" + HwLog.printException((Exception) e));
            return h(context);
        }
    }

    private static Intent j(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packagename", context.getPackageName());
        intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        return intent;
    }

    private static Intent k(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setClassName("com.oppo.launcher", "com.oppo.launcher.shortcut.ShortcutSettingsActivity");
        if (a(context, intent)) {
            return intent;
        }
        intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
        return intent;
    }

    private static Intent l(Context context) {
        return h(context);
    }

    private static boolean a(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
