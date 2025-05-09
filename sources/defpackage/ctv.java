package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes3.dex */
public class ctv {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f11472a;
    private static final byte[] b = {92, 24, 9, 83, 70, 104, -4, 82, 39, -33};
    private static NoTitleCustomAlertDialog e;

    static {
        a();
    }

    public static void a() {
        cpw.a(false, "CommonLibUtil", "getKeFromSo ");
        String c = cuc.c();
        if (TextUtils.isEmpty(c)) {
            cpw.a(false, "CommonLibUtil", "getKeFromSo ko is null");
            return;
        }
        try {
            f11472a = ctu.b(Base64.decode(new String(cuc.e(Base64.decode(c, 0)), "UTF-8"), 0));
        } catch (UnsupportedEncodingException e2) {
            cpw.e(false, "CommonLibUtil", "getKeFromSo UnsupportedEncodingException:", e2.getMessage());
        }
    }

    public static byte[] a(int i, byte[] bArr, String str) {
        byte[] bArr2;
        if (bArr == null || bArr.length == 0 || str == null) {
            cpw.e(false, "CommonLibUtil", "whiteBoxEncrypt para are wrong!");
            return new byte[0];
        }
        try {
            bArr2 = Base64.decode(str, 0);
        } catch (IllegalArgumentException e2) {
            cpw.e(false, "CommonLibUtil", "whiteBoxEncrypt IllegalArgumentException: ", e2.getMessage());
            bArr2 = null;
        }
        if (bArr2 == null || bArr2.length == 0) {
            cpw.e(false, "CommonLibUtil", "whiteBoxEncrypt Base64 decode error!");
            return new byte[0];
        }
        return cuc.d(i, bArr, ctu.b(bArr2));
    }

    public static byte[] e(byte[] bArr, int i, int i2) throws NoSuchAlgorithmException, InvalidKeyException {
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        byte[] c = cty.c();
        if (c == null || c.length == 0 || c.length > 1024) {
            return new byte[0];
        }
        int i3 = c[0];
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i4] = c[((i4 + i3) * 8) - 2];
        }
        for (int i5 = 0; i5 < i3 / 2; i5++) {
            byte b2 = bArr2[i5];
            int i6 = (i3 - 1) - i5;
            bArr2[i5] = bArr2[i6];
            bArr2[i6] = b2;
        }
        return i3 == 0 ? new byte[0] : cua.d(bArr2, bArr, i, i2);
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            cpw.e(false, "CommonLibUtil", "para is wrong!");
            return "";
        }
        return cvx.d(bArr);
    }

    public static byte[] d(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.e(true, "CommonLibUtil", "para is wrong!");
            return new byte[0];
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            try {
                int i2 = i * 2;
                int i3 = i2 + 1;
                bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
            } catch (NumberFormatException e2) {
                cpw.e(true, "CommonLibUtil", e2.getMessage());
                return new byte[0];
            }
        }
        return bArr;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(16);
        for (String str2 : str.split(":")) {
            sb.append(str2);
        }
        cpw.a(true, "CommonLibUtil", "replaceMac:", cpw.d(sb.toString()));
        return sb.toString();
    }

    public static int[] e(int[] iArr) {
        if (iArr == null) {
            return new int[0];
        }
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i] & 255;
        }
        return iArr2;
    }

    public static byte[] d(int[] iArr) {
        if (iArr == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) (iArr[i] & 255);
        }
        return bArr;
    }

    public static void e(Context context) {
        String a2 = a(context);
        if ("239.40.".equals(a2)) {
            b(context, "multicast_ip", "239.50.");
        } else if ("239.50.".equals(a2)) {
            b(context, "multicast_ip", "239.60.");
        } else {
            b(context, "multicast_ip", "239.40.");
        }
    }

    private static void b(Context context, String str, String str2) {
        new DeviceCloudSharePreferencesManager(context).d(str, str2);
    }

    public static String a(Context context) {
        String c = new DeviceCloudSharePreferencesManager(context).c("multicast_ip");
        return (c == null || "".equals(c)) ? "239.40." : c;
    }

    public static byte[] b(int i) {
        byte[] bArr = new byte[i];
        nsg.b().nextBytes(bArr);
        return bArr;
    }

    public static byte[] e() {
        byte[] bArr = f11472a;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static byte[] d() {
        return (byte[]) b.clone();
    }

    public static boolean d(Context context) {
        if (context == null) {
            cpw.e(false, "CommonLibUtil", "isNetworkConnected context is null");
            return false;
        }
        if (!(context.getSystemService("connectivity") instanceof ConnectivityManager)) {
            LogUtil.h("CommonLibUtil", "context.getSystemService(Context.CONNECTIVITY_SERVICE) not instanceof ConnectivityManager");
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean b() {
        String str = Build.FINGERPRINT;
        cpw.a(false, "CommonLibUtil", "system ro.build.fingerprint ", str);
        return str.toLowerCase(Locale.ENGLISH).lastIndexOf("samsung") != -1;
    }

    public static boolean Mg_(Context context, LocationManager locationManager) {
        if (locationManager == null && context != null) {
            if (context.getSystemService("location") instanceof LocationManager) {
                locationManager = (LocationManager) context.getSystemService("location");
            } else {
                LogUtil.h("CommonLibUtil", "context.getSystemService(Context.LOCATION_SERVICE) ont instanceof LocationManager");
            }
        }
        if (locationManager != null) {
            boolean isProviderEnabled = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            boolean isProviderEnabled2 = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
            cpw.a(false, "CommonLibUtil", "isLocationSwitchOn(): GpsEnable ", Boolean.valueOf(isProviderEnabled), " NetworkEnable ", Boolean.valueOf(isProviderEnabled2));
            return isProviderEnabled || isProviderEnabled2;
        }
        cpw.a(false, "CommonLibUtil", "isLocationSwitchOn locationManager is null");
        return false;
    }

    public static void Mi_(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        if (context == null) {
            cpw.e(false, "CommonLibUtil", "showPermissionSettingGuide context is null");
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        if (e == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(str).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: ctv.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    cpw.c(false, "CommonLibUtil", "setNegativeButton onclick cancle");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: ctv.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    cpw.c(false, "CommonLibUtil", "setPositiveButton onclick called --- String");
                    Intent intent = new Intent();
                    intent.addFlags(268435456);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", applicationContext.getPackageName(), null));
                    ctv.Mj_(applicationContext, intent);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            e = builder.e();
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = e;
        if (noTitleCustomAlertDialog == null || noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        e.show();
    }

    public static void c(final Context context) {
        if (context == null) {
            cpw.e(false, "CommonLibUtil", "showWiFiEnableDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        String string = context.getResources().getString(R.string.IDS_device_wifi_device_product_name);
        builder.e(String.format(Locale.ENGLISH, context.getResources().getString(R.string.IDS_device_wifi_measure_no_network_msg), string)).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: ctv.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cpw.a(false, "CommonLibUtil", "showWiFiEnableDialog: do nothing");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R.string.IDS_device_wifi_go_connect, new View.OnClickListener() { // from class: ctv.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                cub.k(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public static void Mh_(String str, ImageView imageView, Drawable drawable) {
        if (imageView == null || drawable == null) {
            LogUtil.b("CommonLibUtil", "setUserPhoto: userPhotoLv or defaultUserPhoto is null");
            return;
        }
        if (!TextUtils.isEmpty(str) && !"default".equals(str)) {
            Bitmap cIe_ = nrf.cIe_(BaseApplication.getContext(), str);
            if (cIe_ != null) {
                imageView.setImageBitmap(nrf.cHX_(cIe_));
                return;
            }
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            cpw.a(false, "CommonLibUtil", "isMainUser mainUuid is null");
            return false;
        }
        if (str == null) {
            cpw.a(false, "CommonLibUtil", "isMainUser uuid is null");
            return true;
        }
        if ("0".equals(str) || "".equals(str)) {
            cpw.a(false, "CommonLibUtil", "isMainUser:", str);
            return true;
        }
        if (!Constants.NULL.equalsIgnoreCase(str) && !str.equals(str2)) {
            return false;
        }
        cpw.a(false, "CommonLibUtil", "isMainUser:", str);
        return true;
    }

    public static void Mj_(Context context, Intent intent) {
        if (context == null) {
            cpw.a(false, "CommonLibUtil", "startSystemActivity activity is null");
            return;
        }
        if (intent == null) {
            cpw.a(false, "CommonLibUtil", "startSystemActivity intent is null");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
            if (resolveActivity != null) {
                intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("CommonLibUtil", "startSystemActivity exception");
                    return;
                }
            }
            cpw.a(false, "CommonLibUtil", "startSystemActivity resolveInfo is null");
            return;
        }
        cpw.a(false, "CommonLibUtil", "startSystemActivity packageManager is null");
    }
}
