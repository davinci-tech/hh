package com.huawei.secure.android.common.intent;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.secure.android.common.activity.a;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class IntentUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8608a = "IntentUtils";
    private static final int b = 750000;

    public static void filterIntent(Intent intent) {
        filterIntentComAndSel(intent);
        filterIntentClipData(intent);
        filterIntentRWPermission(intent);
    }

    public static void filterIntentComAndSel(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.addCategory("android.intent.category.BROWSABLE").setComponent(null);
        intent.setSelector(null);
    }

    public static void filterIntentRWPermission(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.setFlags(intent.getFlags() & (-196));
    }

    public static String getCallerPackage(Activity activity) {
        a.b(f8608a, "getCallerPackage");
        if (activity == null) {
            a.a(f8608a, "getCallerPackage, activity is null");
            return "";
        }
        try {
            Object invoke = ActivityManager.class.getMethod("getService", new Class[0]).invoke(null, new Object[0]);
            String[] packagesForUid = activity.getPackageManager().getPackagesForUid(((Integer) invoke.getClass().getMethod("getLaunchedFromUid", IBinder.class).invoke(invoke, (IBinder) Activity.class.getMethod("getActivityToken", new Class[0]).invoke(activity, new Object[0]))).intValue());
            if (packagesForUid != null && packagesForUid.length >= 1) {
                String str = packagesForUid[0];
                a.b(f8608a, "getCallerPackage, package is: " + str);
                return str;
            }
            a.a(f8608a, "getCallerPackage, packages is null or empty");
            return "";
        } catch (Exception e) {
            a.a(f8608a, "getCallerPackage exception: " + e.getMessage(), true);
            return "";
        }
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(Intent intent, String str) {
        try {
            return intent.getParcelableArrayListExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getParcelableArrayListExtra failed on intent " + th.getMessage(), true);
            return null;
        }
    }

    public static int getParceledIntentSize(Intent intent) {
        Parcel obtain = Parcel.obtain();
        intent.writeToParcel(obtain, 0);
        int dataSize = obtain.dataSize();
        obtain.recycle();
        return dataSize;
    }

    public static Uri getReferrer(Activity activity) {
        try {
            return activity.getReferrer();
        } catch (Throwable th) {
            a.a(f8608a, "getReferrer: " + th.getMessage(), true);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean hasIntentBomb(android.content.Intent r2) {
        /*
            java.lang.String r0 = "IntentUtils"
            if (r2 != 0) goto La
            java.lang.String r2 = "intent is null"
            com.huawei.secure.android.common.activity.a.a(r0, r2)
            goto L21
        La:
            boolean r1 = r2 instanceof com.huawei.secure.android.common.intent.SafeIntent
            if (r1 == 0) goto L1a
            java.lang.String r1 = "safe intent"
            com.huawei.secure.android.common.activity.a.b(r0, r1)
            com.huawei.secure.android.common.intent.SafeIntent r2 = (com.huawei.secure.android.common.intent.SafeIntent) r2
            boolean r2 = r2.hasIntentBomb()
            goto L22
        L1a:
            java.lang.String r1 = "ANYTHING"
            r2.getStringExtra(r1)     // Catch: java.lang.Throwable -> L21
            r2 = 0
            goto L22
        L21:
            r2 = 1
        L22:
            if (r2 == 0) goto L29
            java.lang.String r1 = "hasIntentBomb"
            com.huawei.secure.android.common.activity.a.a(r0, r1)
        L29:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.intent.IntentUtils.hasIntentBomb(android.content.Intent):boolean");
    }

    public static boolean isIntentTooLarge(Intent intent) {
        return getParceledIntentSize(intent) > b;
    }

    public static boolean safeGetBoolean(Bundle bundle, String str) {
        return safeGetBoolean(bundle, str, false);
    }

    public static boolean safeGetBooleanExtra(Intent intent, String str, boolean z) {
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Throwable th) {
            a.a(f8608a, "getBooleanExtra failed on intent " + th.getMessage(), true);
            return z;
        }
    }

    public static Bundle safeGetBundle(Bundle bundle, String str) {
        try {
            return bundle.getBundle(str);
        } catch (Throwable th) {
            a.a(f8608a, "getBundle failed on bundle " + th.getMessage(), true);
            return new Bundle();
        }
    }

    public static Bundle safeGetBundleExtra(Intent intent, String str) {
        try {
            return intent.getBundleExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getBundleExtra failed on intent " + th.getMessage(), true);
            return new Bundle();
        }
    }

    public static byte[] safeGetByteArrayExtra(Intent intent, String str) {
        try {
            return intent.getByteArrayExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getByteArrayExtra failed on intent " + th.getMessage(), true);
            return new byte[0];
        }
    }

    public static int safeGetInt(Bundle bundle, String str, int i) {
        try {
            return bundle.getInt(str, i);
        } catch (Throwable th) {
            a.a(f8608a, "getInt failed on bundle " + th.getMessage(), true);
            return i;
        }
    }

    public static int[] safeGetIntArray(Bundle bundle, String str) {
        try {
            return bundle.getIntArray(str);
        } catch (Throwable th) {
            a.a(f8608a, "getIntArray failed on bundle " + th.getMessage(), true);
            return new int[0];
        }
    }

    public static int[] safeGetIntArrayExtra(Intent intent, String str) {
        try {
            return intent.getIntArrayExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getIntArrayExtra failed on intent " + th.getMessage(), true);
            return new int[0];
        }
    }

    public static int safeGetIntExtra(Intent intent, String str, int i) {
        try {
            return intent.getIntExtra(str, i);
        } catch (Throwable th) {
            a.a(f8608a, "getIntExtra failed on intent " + th.getMessage(), true);
            return i;
        }
    }

    public static long safeGetLongExtra(Intent intent, String str, long j) {
        try {
            return intent.getLongExtra(str, j);
        } catch (Throwable th) {
            a.a(f8608a, "getLongExtra failed on intent " + th.getMessage(), true);
            return j;
        }
    }

    @Deprecated
    public static <T extends Parcelable> T safeGetParcelable(Bundle bundle, String str) {
        try {
            return (T) bundle.getParcelable(str);
        } catch (Throwable th) {
            a.a(f8608a, "getParcelable failed on bundle " + th.getMessage(), true);
            return null;
        }
    }

    public static Parcelable[] safeGetParcelableArrayExtra(Intent intent, String str) {
        try {
            return intent.getParcelableArrayExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getParcelableArrayExtra failed on intent " + th.getMessage(), true);
            return new Parcelable[0];
        }
    }

    @Deprecated
    public static <T extends Parcelable> T safeGetParcelableExtra(Intent intent, String str) {
        try {
            return (T) intent.getParcelableExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getParcelableExtra failed on intent " + th.getMessage(), true);
            return null;
        }
    }

    public static <T extends Serializable> T safeGetSerializable(Bundle bundle, String str, Class<T> cls) {
        try {
            Serializable serializable = bundle.getSerializable(str);
            if (cls.isInstance(serializable)) {
                return cls.cast(serializable);
            }
            return null;
        } catch (Throwable th) {
            a.a(f8608a, "getSerializable exception: " + th.getMessage(), true);
            return null;
        }
    }

    @Deprecated
    public static <T extends Serializable> T safeGetSerializableExtra(Intent intent, String str) {
        try {
            return (T) intent.getSerializableExtra(str);
        } catch (ClassCastException e) {
            a.a(f8608a, "Invalide class for Serializable: " + e.getMessage(), true);
            return null;
        } catch (Throwable th) {
            a.a(f8608a, "getSerializableExtra failed on intent " + th.getMessage(), true);
            return null;
        }
    }

    public static String safeGetString(Bundle bundle, String str) {
        try {
            return bundle.getString(str);
        } catch (Throwable th) {
            a.a(f8608a, "getString failed on bundle " + th.getMessage(), true);
            return "";
        }
    }

    public static ArrayList<String> safeGetStringArrayListExtra(Intent intent, String str) {
        try {
            return intent.getStringArrayListExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getStringArrayListExtra failed on intent " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public static String safeGetStringExtra(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "getStringExtra failed on intent " + th.getMessage(), true);
            return "";
        }
    }

    public static boolean safeHasExtra(Intent intent, String str) {
        try {
            return intent.hasExtra(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static Intent safeParseUri(String str, int i) throws URISyntaxException {
        if (str == null) {
            return null;
        }
        Intent parseUri = Intent.parseUri(str, i);
        filterIntent(parseUri);
        return parseUri;
    }

    public static void safeRemoveExtra(Intent intent, String str) {
        try {
            intent.removeExtra(str);
        } catch (Throwable th) {
            a.a(f8608a, "removeExtra failed on intent " + th.getMessage(), true);
        }
    }

    public static boolean safeStartActivity(Context context, Intent intent) {
        return safeStartActivity(context, intent, null);
    }

    public static boolean safeStartActivityForResultStatic(Activity activity, Intent intent, int i) {
        try {
            activity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            a.a(f8608a, "safeStartActivityForResult: ActivityNotFoundException ", e);
            return false;
        } catch (Exception e2) {
            a.a(f8608a, "safeStartActivityForResult: Exception ", e2);
            return false;
        } catch (Throwable th) {
            a.a(f8608a, "safeStartActivityForResult: throwable ", th);
            return false;
        }
    }

    @Deprecated
    public boolean safeStartActivityForResult(Activity activity, Intent intent, int i) {
        try {
            activity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            a.a(f8608a, "safeStartActivityForResult: ActivityNotFoundException ", e);
            return false;
        } catch (Exception e2) {
            a.a(f8608a, "safeStartActivityForResult: Exception ", e2);
            return false;
        } catch (Throwable th) {
            a.a(f8608a, "safeStartActivityForResult: throwable ", th);
            return false;
        }
    }

    public static void filterIntentClipData(Intent intent) {
        if (intent != null && intent.getClipData() == null) {
            intent.setClipData(ClipData.newPlainText("avoid intent migrateExtraStreamToClipData add flags", "anything"));
        }
    }

    public static boolean safeGetBoolean(Bundle bundle, String str, boolean z) {
        try {
            return bundle.getBoolean(str, z);
        } catch (Throwable th) {
            a.a(f8608a, "getBoolean failed with throwable: " + th.getMessage());
            return z;
        }
    }

    public static boolean safeStartActivity(Context context, Intent intent, Bundle bundle) {
        try {
            context.startActivity(intent, bundle);
            return true;
        } catch (ActivityNotFoundException e) {
            a.a(f8608a, "safeStartActivity: ActivityNotFoundException ", e);
            return false;
        } catch (Exception unused) {
            a.a(f8608a, "safeStartActivityForResult: Exception");
            return false;
        } catch (Throwable unused2) {
            a.a(f8608a, "safeStartActivityForResult: throwable");
            return false;
        }
    }

    public static <T extends Parcelable> T safeGetParcelable(Bundle bundle, String str, Class<T> cls) {
        try {
            Parcelable parcelable = bundle.getParcelable(str);
            if (cls.isInstance(parcelable)) {
                return cls.cast(parcelable);
            }
            return null;
        } catch (Throwable th) {
            a.a(f8608a, "getParcelable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public static <T extends Parcelable> T safeGetParcelableExtra(Intent intent, String str, Class<T> cls) {
        try {
            Parcelable parcelableExtra = intent.getParcelableExtra(str);
            if (cls.isInstance(parcelableExtra)) {
                return cls.cast(parcelableExtra);
            }
            return null;
        } catch (Throwable th) {
            a.a(f8608a, "getParcelable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public static <T extends Serializable> T safeGetSerializableExtra(Intent intent, String str, Class<T> cls) {
        try {
            Serializable serializableExtra = intent.getSerializableExtra(str);
            if (cls.isInstance(serializableExtra)) {
                return cls.cast(serializableExtra);
            }
            return null;
        } catch (Throwable th) {
            a.a(f8608a, "getSerializable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public static boolean safeStartActivityForResultStatic(Activity activity, Intent intent, int i, Bundle bundle) {
        try {
            activity.startActivityForResult(intent, i, bundle);
            return true;
        } catch (ActivityNotFoundException e) {
            a.a(f8608a, "safeStartActivityForResult: ActivityNotFoundException ", e);
            return false;
        } catch (Exception unused) {
            a.a(f8608a, "safeStartActivityForResult: Exception");
            return false;
        } catch (Throwable unused2) {
            a.a(f8608a, "safeStartActivityForResult: throwable");
            return false;
        }
    }

    @Deprecated
    public boolean safeStartActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        try {
            activity.startActivityForResult(intent, i, bundle);
            return true;
        } catch (ActivityNotFoundException e) {
            a.a(f8608a, "safeStartActivityForResult: ActivityNotFoundException ", e);
            return false;
        } catch (Exception unused) {
            a.a(f8608a, "safeStartActivityForResult: Exception");
            return false;
        } catch (Throwable unused2) {
            a.a(f8608a, "safeStartActivityForResult: throwable");
            return false;
        }
    }
}
