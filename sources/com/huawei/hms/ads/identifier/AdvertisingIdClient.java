package com.huawei.hms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.ads.identifier.aidl.OpenDeviceIdentifierService;
import java.io.IOException;

/* loaded from: classes9.dex */
public class AdvertisingIdClient {
    private static final String NIL_UUID = "00000000-0000-0000-0000-000000000000";
    private static final String SETTINGS_AD_ID = "pps_oaid";
    private static final String SETTINGS_TRACK_LIMIT = "pps_track_limit";
    private static final String TAG = "AdvertisingIdClient";

    public static boolean verifyAdId(Context context, String str, boolean z) throws AdIdVerifyException {
        Info requestAdvertisingIdInfo = requestAdvertisingIdInfo(context);
        if (TextUtils.equals(str, requestAdvertisingIdInfo.getId())) {
            if (z == requestAdvertisingIdInfo.isLimitAdTrackingEnabled()) {
                return true;
            }
        }
        return false;
    }

    private static void updateAdvertisingIdInfo(final Context context) {
        j.f4328a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.AdvertisingIdClient.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AdvertisingIdClient.requestAdvertisingIdInfo(context);
                } catch (Throwable th) {
                    Log.w(AdvertisingIdClient.TAG, "update Id err: " + th.getClass().getSimpleName());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Info requestAdvertisingIdInfo(Context context) throws IOException {
        if (c.d(context)) {
            Log.i(TAG, "requestAdvertisingIdInfo via provider");
            return c.c(context);
        }
        Log.i(TAG, "requestAdvertisingIdInfo via aidl");
        return getIdInfoViaAIDL(context);
    }

    private static boolean isHmsValid(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || !f.a(context, str)) {
            return false;
        }
        boolean a2 = b.a(str, f.c(context, str));
        Log.i(TAG, "isHmsValid: " + a2);
        return a2;
    }

    public static boolean isAdvertisingIdAvailable(Context context) {
        try {
            context.getPackageManager().getPackageInfo(f.a(context), 128);
            new Intent("com.uodis.opendevice.OPENIDS_SERVICE").setPackage(f.a(context));
            return !r1.queryIntentServices(r2, 0).isEmpty();
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }

    public static final class Info {
        private final String advertisingId;
        private final boolean limitAdTrackingEnabled;

        public boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }

        public String getId() {
            return this.advertisingId;
        }

        public Info(String str, boolean z) {
            this.advertisingId = str;
            this.limitAdTrackingEnabled = z;
        }
    }

    private static Info getIdInfoViaAIDL(Context context) throws IOException {
        try {
            context.getPackageManager().getPackageInfo(f.a(context), 128);
            a aVar = new a();
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            String a2 = f.a(context);
            if (!isHmsValid(context, a2)) {
                return new Info("00000000-0000-0000-0000-000000000000", true);
            }
            intent.setPackage(a2);
            try {
                if (!context.bindService(intent, aVar, 1)) {
                    throw new IOException("bind failed");
                }
                try {
                    OpenDeviceIdentifierService asInterface = OpenDeviceIdentifierService.Stub.asInterface(aVar.a());
                    return new Info(asInterface.getOaid(), asInterface.isOaidTrackLimited());
                } catch (RemoteException unused) {
                    throw new IOException("bind hms service RemoteException");
                } catch (InterruptedException unused2) {
                    throw new IOException("bind hms service InterruptedException");
                }
            } finally {
                try {
                    context.unbindService(aVar);
                } catch (Throwable th) {
                    Log.w(TAG, "unbind " + th.getClass().getSimpleName());
                }
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            throw new IOException("Service not found");
        } catch (Exception unused4) {
            throw new IOException("Service not found: Exception");
        }
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException {
        try {
        } catch (Throwable th) {
            Log.w(TAG, "get Id err: " + th.getClass().getSimpleName());
        }
        if (!TextUtils.isEmpty(Settings.Global.getString(context.getContentResolver(), "pps_oaid_c"))) {
            Info a2 = c.a(context);
            return a2 != null ? a2 : requestAdvertisingIdInfo(context);
        }
        String string = Settings.Global.getString(context.getContentResolver(), SETTINGS_AD_ID);
        String string2 = Settings.Global.getString(context.getContentResolver(), SETTINGS_TRACK_LIMIT);
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            updateAdvertisingIdInfo(context);
            return new Info(string, Boolean.valueOf(string2).booleanValue());
        }
        return requestAdvertisingIdInfo(context);
    }
}
