package com.huawei.hwcommonmodel.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import defpackage.jeg;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PermissionUtil {
    private static final PermissionType[] c = {PermissionType.STORAGE_LOCATION, PermissionType.LOCATION_WITH_BACKGROUND, PermissionType.AUDIO_LOCATION};

    /* renamed from: a, reason: collision with root package name */
    private static final PermissionType[] f6269a = {PermissionType.STORAGE_LOCATION, PermissionType.LOCATION_WITH_BACKGROUND, PermissionType.AUDIO_LOCATION};
    private static final PermissionType[] e = {PermissionType.STORAGE, PermissionType.STORAGE_LOCATION, PermissionType.MEDIA_VIDEO_IMAGES, PermissionType.CAMERA_IMAGE, PermissionType.STORAGE_NETWORK_WIFI, PermissionType.AUDIO_LOCATION, PermissionType.MANAGE_EXTERNAL_STORAGE, PermissionType.SCAN};

    public enum PermissionResult {
        GRANTED,
        DENIED,
        FOREVER_DENIED
    }

    public enum PermissionType {
        STORAGE,
        LOCATION,
        LOCATION_WITH_BACKGROUND,
        STORAGE_LOCATION,
        MEDIA_VIDEO_IMAGES,
        MEDIA_AUDIO,
        CAMERA_IMAGE,
        NONE,
        STORAGE_NETWORK_WIFI,
        AUDIO_CALLS,
        PHONE_STATE,
        READ_CONTACT,
        READ_WRITE_CONTACT,
        CAMERA,
        DISTRIBUTED_DATASYNC,
        RECORD_AUDIO,
        CALENDAR,
        SCAN,
        AUDIO_LOCATION,
        MANAGE_EXTERNAL_STORAGE
    }

    private PermissionUtil() {
    }

    public static String[] c(PermissionType permissionType) {
        if (f(permissionType)) {
            return h(permissionType);
        }
        if (i(permissionType)) {
            return j(permissionType);
        }
        return b(permissionType);
    }

    private static String[] b(PermissionType permissionType) {
        if (a(permissionType)) {
            return g(permissionType);
        }
        return e(permissionType);
    }

    private static String[] e(PermissionType permissionType) {
        switch (AnonymousClass2.c[permissionType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            case 4:
            case 5:
                return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
            case 6:
            case 7:
                return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            case 8:
                return new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            case 9:
                return new String[]{"PERMISSION_NONE"};
            case 10:
                return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"};
            case 11:
                return new String[]{"android.permission.RECORD_AUDIO", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.MODIFY_AUDIO_SETTINGS"};
            case 12:
                return new String[]{"android.permission.READ_PHONE_STATE"};
            case 13:
                return new String[]{"android.permission.READ_CONTACTS"};
            case 14:
                return new String[]{"android.permission.WRITE_CONTACTS", "android.permission.READ_CONTACTS"};
            case 15:
                return new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
            case 16:
                return new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"};
            default:
                return d(permissionType);
        }
    }

    private static String[] d(PermissionType permissionType) {
        String[] strArr = new String[0];
        switch (permissionType) {
            case CAMERA:
                return new String[]{"android.permission.CAMERA"};
            case DISTRIBUTED_DATASYNC:
                return new String[]{"com.huawei.permission.DISTRIBUTED_DATASYNC"};
            case RECORD_AUDIO:
                return new String[]{"android.permission.RECORD_AUDIO"};
            default:
                LogUtil.a("PermissionUtil", "permissionType unknow:", permissionType);
                return strArr;
        }
    }

    private static String[] g(PermissionType permissionType) {
        String[] strArr = new String[0];
        int i = AnonymousClass2.c[permissionType.ordinal()];
        if (i == 5) {
            return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
        }
        if (i == 6 || i == 7) {
            return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
        }
        LogUtil.a("PermissionUtil", "permissionType unknow:", permissionType);
        return strArr;
    }

    private static String[] j(PermissionType permissionType) {
        String[] strArr = new String[0];
        int i = AnonymousClass2.c[permissionType.ordinal()];
        if (i == 5) {
            return new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"};
        }
        if (i == 6 || i == 7) {
            return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        LogUtil.a("PermissionUtil", "permissionType unknow:", permissionType);
        return strArr;
    }

    private static String[] h(PermissionType permissionType) {
        String[] strArr = new String[0];
        int i = AnonymousClass2.c[permissionType.ordinal()];
        if (i == 1) {
            return new String[]{"android.permission.MANAGE_EXTERNAL_STORAGE"};
        }
        if (i == 2) {
            return new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
        }
        if (i == 3) {
            return new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
        }
        if (i == 6) {
            return new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        if (i == 7) {
            return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        if (i == 8) {
            return new String[]{"android.permission.CAMERA", "android.permission.READ_MEDIA_IMAGES"};
        }
        if (i == 10) {
            return new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"};
        }
        if (i == 16) {
            return new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", "android.permission.NEARBY_WIFI_DEVICES"};
        }
        LogUtil.a("PermissionUtil", "permissionType unknow:", permissionType);
        return strArr;
    }

    public static boolean a(PermissionType permissionType) {
        if (c()) {
            for (PermissionType permissionType2 : c) {
                if (permissionType2 == permissionType) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean i(PermissionType permissionType) {
        ReleaseLogUtil.b("PermissionUtil", "isAndroidR ", Boolean.valueOf(a()));
        if (a()) {
            for (PermissionType permissionType2 : f6269a) {
                if (permissionType2 == permissionType) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean f(PermissionType permissionType) {
        if (g()) {
            ReleaseLogUtil.b("PermissionUtil", "isAndroidT");
            for (PermissionType permissionType2 : e) {
                if (permissionType2 == permissionType) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 31;
    }

    public static boolean g() {
        return Build.VERSION.SDK_INT >= 33;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT == 29;
    }

    public static boolean j() {
        if (a() || EnvironmentInfo.r()) {
            return CommonUtil.bh() || NotificationManagerCompat.from(BaseApplication.e()).areNotificationsEnabled();
        }
        LogUtil.c("PermissionUtil", "isUseOnlyAllowedLocation false");
        return false;
    }

    public static PermissionResult b(Context context, String[] strArr) {
        LogUtil.d("PermissionUtil", "checkPermissions::enter");
        if (context == null) {
            ReleaseLogUtil.a("R_PermissionUtil", "checkPermissions::context is null");
            return PermissionResult.DENIED;
        }
        if (strArr.length <= 0) {
            LogUtil.a("PermissionUtil", "checkPermissions::permissions empty");
            return PermissionResult.DENIED;
        }
        if ("PERMISSION_NONE".equals(strArr[0])) {
            return PermissionResult.GRANTED;
        }
        if (e(context, strArr)) {
            return PermissionResult.GRANTED;
        }
        if (!(context instanceof Activity)) {
            LogUtil.e("PermissionUtil", "checkPermissions only take Activity as context");
            return PermissionResult.DENIED;
        }
        for (String str : strArr) {
            if (d(context, str)) {
                return PermissionResult.FOREVER_DENIED;
            }
        }
        return PermissionResult.DENIED;
    }

    private static boolean d(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a("PermissionUtil", "isForeverDenied null");
            return true;
        }
        if (CommonUtil.av() && d(str)) {
            ReleaseLogUtil.b("PermissionUtil", "isAndroidOncePermission permission = ", str);
            return false;
        }
        boolean c2 = jeg.d().c(context, str);
        if (!c2 && g() && "android.permission.MANAGE_EXTERNAL_STORAGE".equals(str)) {
            return true;
        }
        boolean a2 = CommonUtil.a(context, str);
        boolean shouldShowRequestPermissionRationale = ((Activity) context).shouldShowRequestPermissionRationale(str);
        ReleaseLogUtil.b("PermissionUtil", "shouldShowRationale = ", Boolean.valueOf(shouldShowRequestPermissionRationale), " permission = ", str);
        return (c2 || a2 || shouldShowRequestPermissionRationale) ? false : true;
    }

    public static PermissionResult e(Context context, PermissionType permissionType) {
        String[] c2;
        if (PermissionType.LOCATION_WITH_BACKGROUND == permissionType) {
            c2 = b(permissionType);
        } else {
            c2 = c(permissionType);
        }
        LogUtil.d("PermissionUtil", "permissions to request:", c2);
        return b(context, c2);
    }

    public static void b(Context context, PermissionType permissionType, PermissionsResultAction permissionsResultAction) {
        LogUtil.d("PermissionUtil", "doActionWithPermissions::enter");
        if (!(context instanceof Activity)) {
            LogUtil.e("PermissionUtil", "doActionWithPermissions only take Activity as context");
            return;
        }
        String[] c2 = c(permissionType);
        LogUtil.d("PermissionUtil", "permissions to request:", Arrays.toString(c2));
        int i = AnonymousClass2.d[b(context, c2).ordinal()];
        if (i == 1) {
            permissionsResultAction.onGranted();
            return;
        }
        if (i == 2) {
            permissionsResultAction.setPermissionType(permissionType);
            bFX_((Activity) context, c2, permissionsResultAction);
        } else {
            if (i != 3) {
                return;
            }
            permissionsResultAction.onForeverDenied(permissionType);
        }
    }

    /* renamed from: com.huawei.hwcommonmodel.utils.PermissionUtil$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[PermissionResult.values().length];
            d = iArr;
            try {
                iArr[PermissionResult.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[PermissionResult.DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[PermissionResult.FOREVER_DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[PermissionType.values().length];
            c = iArr2;
            try {
                iArr2[PermissionType.MANAGE_EXTERNAL_STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[PermissionType.MEDIA_VIDEO_IMAGES.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[PermissionType.STORAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[PermissionType.LOCATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[PermissionType.LOCATION_WITH_BACKGROUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[PermissionType.STORAGE_LOCATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[PermissionType.AUDIO_LOCATION.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[PermissionType.CAMERA_IMAGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[PermissionType.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                c[PermissionType.STORAGE_NETWORK_WIFI.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                c[PermissionType.AUDIO_CALLS.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                c[PermissionType.PHONE_STATE.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                c[PermissionType.READ_CONTACT.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                c[PermissionType.READ_WRITE_CONTACT.ordinal()] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                c[PermissionType.CALENDAR.ordinal()] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                c[PermissionType.SCAN.ordinal()] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                c[PermissionType.CAMERA.ordinal()] = 17;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                c[PermissionType.DISTRIBUTED_DATASYNC.ordinal()] = 18;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                c[PermissionType.RECORD_AUDIO.ordinal()] = 19;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    public static boolean e(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!jeg.d().c(context, str)) {
                ReleaseLogUtil.b("R_PermissionUtil", "isHasPermissions permissions are not granted: ", str);
                return false;
            }
        }
        return true;
    }

    public static void bFX_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        LogUtil.c("PermissionUtil", "enter requestPermission(): activity = ", activity, ",permissions", Arrays.toString(strArr), ",action = ", permissionsResultAction);
        jeg.d().bGx_(activity, strArr, permissionsResultAction);
        CommonUtil.a(activity, strArr);
    }

    public static PermissionType e(int i) {
        if (i == 264) {
            return PermissionType.STORAGE;
        }
        return PermissionType.LOCATION;
    }

    public static boolean d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b(PermissionType.LOCATION_WITH_BACKGROUND)));
        arrayList.add("android.permission.CAMERA");
        arrayList.add("android.permission.RECORD_AUDIO");
        return a() && arrayList.contains(str);
    }

    public static boolean b() {
        if (Build.VERSION.SDK_INT < 31) {
            return true;
        }
        AlarmManager alarmManager = (AlarmManager) BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            return alarmManager.canScheduleExactAlarms();
        }
        return false;
    }
}
