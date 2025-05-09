package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanFilter;
import android.companion.AssociationRequest;
import android.companion.BluetoothDeviceFilter;
import android.companion.BluetoothLeDeviceFilter;
import android.companion.CompanionDeviceManager;
import android.companion.DeviceFilter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class obb {
    private static final String c = System.lineSeparator();
    private NoTitleCustomAlertDialog e;

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.c("DevicePairingPageUtils", "isSupportBluetooth, context is null");
            return false;
        }
        if ((context.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) context.getSystemService("bluetooth") : null) != null) {
            return true;
        }
        LogUtil.c("DevicePairingPageUtils", "no BT Manager in this phone");
        return false;
    }

    public static boolean a(Context context) {
        if (context == null) {
            LogUtil.c("DevicePairingPageUtils", "isSwitchPage, context is null");
            return false;
        }
        if (!CommonUtil.as()) {
            return true;
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "key_device_pair_switch_ui");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        return Boolean.parseBoolean(b);
    }

    public static void c(String str, Context context, final ActivityResultLauncher<IntentSenderRequest> activityResultLauncher, int i) {
        DeviceFilter<?> build;
        LogUtil.d("DevicePairingPageUtils", "choose mac is :", str, ",bluetoothType is:", Integer.valueOf(i));
        if (!(context instanceof Activity)) {
            LogUtil.d("DevicePairingPageUtils", "context is not instanceof activity" + str);
        } else {
            if (Build.VERSION.SDK_INT >= 35) {
                Activity activity = (Activity) context;
                if (i == 1 || i == 3) {
                    build = new BluetoothDeviceFilter.Builder().setAddress(str).build();
                } else if (i == 2) {
                    build = new BluetoothLeDeviceFilter.Builder().setScanFilter(new ScanFilter.Builder().setDeviceAddress(str).build()).build();
                } else {
                    LogUtil.d("DevicePairingPageUtils", "bluetoothType is null ");
                    return;
                }
                (Build.VERSION.SDK_INT >= 29 ? (CompanionDeviceManager) activity.getSystemService(CompanionDeviceManager.class) : null).associate(new AssociationRequest.Builder().addDeviceFilter(build).setDeviceProfile("android.app.role.COMPANION_DEVICE_WATCH").setSingleDevice(true).build(), new CompanionDeviceManager.Callback() { // from class: obb.2
                    @Override // android.companion.CompanionDeviceManager.Callback
                    public void onDeviceFound(IntentSender intentSender) {
                        try {
                            ActivityResultLauncher activityResultLauncher2 = ActivityResultLauncher.this;
                            if (activityResultLauncher2 != null) {
                                activityResultLauncher2.launch(new IntentSenderRequest.Builder(intentSender).build());
                            }
                        } catch (IllegalStateException e) {
                            ReleaseLogUtil.c("R_DevicePairingPageUtils", "find device is error,exception is:", ExceptionUtils.d(e));
                        }
                    }

                    @Override // android.companion.CompanionDeviceManager.Callback
                    public void onFailure(CharSequence charSequence) {
                        LogUtil.c("DevicePairingPageUtils", "find device error: ", charSequence);
                    }
                }, (Handler) null);
                return;
            }
            LogUtil.d("DevicePairingPageUtils", "SDK version is earlier than 35");
        }
    }

    public static void c(Fragment fragment) {
        if (fragment == null) {
            LogUtil.c("DevicePairingPageUtils", "startIntent, context is null");
            return;
        }
        BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            if (browsingBiEvent != null) {
                browsingBiEvent.showFullServiceDialog(fragment.getActivity());
            }
        } else if (!e(fragment.getActivity())) {
            cTO_(fragment.getActivity());
        } else {
            d(fragment);
        }
    }

    public static void cTO_(Activity activity) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: obb.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("DevicePairingPageUtils", "Bluetooth not supported");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static void cTV_(final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: obb.3
            @Override // java.lang.Runnable
            public void run() {
                NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: obb.3.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.d("DevicePairingPageUtils", "QrCode Bluetooth not supported");
                        activity.finish();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }).e();
                e.setCancelable(false);
                e.show();
            }
        });
    }

    private static void d(Fragment fragment) {
        d(fragment.getContext());
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.c("DevicePairingPageUtils", "startOneKeyActivity, context is null");
            return;
        }
        try {
            context.startActivity(new Intent(context, (Class<?>) OneKeyScanActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DevicePairingPageUtils", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public static void b(Fragment fragment) {
        if (fragment == null) {
            LogUtil.c("DevicePairingPageUtils", "startDeviceAutoSwitchIntent, context is null");
            return;
        }
        try {
            fragment.startActivity(new Intent(fragment.getContext(), (Class<?>) DeviceAutoSwitchActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DevicePairingPageUtils", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.c("DevicePairingPageUtils", "checkGpsPermission, context is null");
            return false;
        }
        PermissionUtil.PermissionType permissionType = PermissionUtil.PermissionType.LOCATION;
        if (Build.VERSION.SDK_INT > 30) {
            permissionType = PermissionUtil.PermissionType.SCAN;
        }
        return PermissionUtil.e(context, permissionType) == PermissionUtil.PermissionResult.GRANTED;
    }

    public void cTX_(final Activity activity) {
        if (activity == null) {
            LogUtil.c("DevicePairingPageUtils", "createGpsServiceDialog, context is null");
            return;
        }
        if (activity.isFinishing()) {
            LogUtil.c("DevicePairingPageUtils", "createGpsServiceDialog, activity is finishing");
            return;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                this.e.dismiss();
            }
            this.e = null;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844111_res_0x7f0219cf).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: obb.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (obb.this.e != null) {
                    obb.this.e.dismiss();
                    obb.this.e = null;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R.string._2130844112_res_0x7f0219d0, new View.OnClickListener() { // from class: obb.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (obb.this.e != null) {
                    obb.this.e.dismiss();
                    obb.this.e = null;
                }
                activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 3);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.e = e;
        e.setCancelable(false);
        this.e.show();
    }

    public static void cTW_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.c("DevicePairingPageUtils", "startAppSettingActivity, context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivityForResult(intent, i);
    }

    public static SpannableString cTM_(int i, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.c("DevicePairingPageUtils", "deviceText or searchKey is empty");
            return new SpannableString("");
        }
        String replaceAll = str2.toLowerCase(Locale.ENGLISH).replaceAll("\\s*", "");
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        SpannableString spannableString = new SpannableString(str);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (i3 > length) {
                break;
            }
            String substring = lowerCase.substring(i2, i3);
            if (!TextUtils.isEmpty(substring) && replaceAll.contains(substring)) {
                spannableString.setSpan(new ForegroundColorSpan(i), i2, i3, 33);
            }
            i2 = i3;
        }
        return spannableString;
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int b = jfu.b(str);
        cuw c2 = jfu.c(str, false);
        return (b != -1 || c2 == null) ? b : c2.m();
    }

    public static SpannableStringBuilder cTL_(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.c("DevicePairingPageUtils", "getDevicePairGuide uuid or context is null");
            return null;
        }
        int c2 = c(str);
        if (c2 == 7) {
            return cTT_(context);
        }
        if (c2 == 14) {
            return cTU_(context);
        }
        if (c2 == 16) {
            return cTN_(context);
        }
        if (c2 == 20) {
            return cTP_(context);
        }
        if (c2 == 21) {
            return cTS_(context);
        }
        if (c2 == 34) {
            return cTR_(context);
        }
        if (c2 == 35) {
            return cTQ_(context);
        }
        LogUtil.c("DevicePairingPageUtils", "other deviceType");
        return null;
    }

    private static SpannableStringBuilder cTT_(Context context) {
        Locale locale = Locale.ENGLISH;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) (String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " "));
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_pair_guide_b3_tip1_ex));
        String str = c;
        sb.append(str);
        spannableStringBuilder.append((CharSequence) sb.toString());
        spannableStringBuilder.append((CharSequence) (String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " "));
        spannableStringBuilder.append((CharSequence) (context.getResources().getString(R.string.IDS_device_pair_guide_b3_tip2_ex) + str).replace("%1$s", "%2$s"));
        spannableStringBuilder.append((CharSequence) (String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 3) + " "));
        spannableStringBuilder.append((CharSequence) context.getResources().getString(R.string.IDS_device_pair_guide_b3_tip3));
        String spannableStringBuilder2 = spannableStringBuilder.toString();
        int indexOf = spannableStringBuilder2.indexOf("%1$s");
        if (indexOf > 0) {
            Drawable drawable = context.getResources().getDrawable(R.drawable._2131430679_res_0x7f0b0d17);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableStringBuilder.setSpan(new ImageSpan(drawable, 1), indexOf, indexOf + 4, 33);
        }
        int indexOf2 = spannableStringBuilder2.indexOf("%2$s");
        if (indexOf2 > 0) {
            Drawable drawable2 = context.getResources().getDrawable(R.drawable._2131430669_res_0x7f0b0d0d);
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
            spannableStringBuilder.setSpan(new ImageSpan(drawable2, 1), indexOf2, indexOf2 + 4, 33);
        }
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTU_(Context context) {
        Locale locale = Locale.ENGLISH;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String str = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_select_device_connect_grus_change_tip_1));
        String str2 = c;
        sb.append(str2);
        String sb2 = sb.toString();
        String str3 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String str4 = context.getResources().getString(R.string.IDS_select_device_connect_grus_change_tip_2) + str2;
        String str5 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 3) + " ";
        String string = context.getResources().getString(R.string.IDS_device_pair_guide_b3_tip3);
        spannableStringBuilder.append((CharSequence) str);
        spannableStringBuilder.append((CharSequence) sb2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) string);
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTR_(Context context) {
        Locale locale = Locale.ENGLISH;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_first));
        String str = c;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_01), context.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(locale)) + str + str;
        String str3 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_again) + str;
        String str4 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        String str5 = context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip1_new) + str;
        String str6 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String format = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip3), context.getString(R.string.IDS_device_start_paring_title).toUpperCase(locale));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb2);
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.append((CharSequence) format);
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTS_(Context context) {
        Locale locale = Locale.ENGLISH;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_first));
        String str = c;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_01), context.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(locale)) + str + str;
        String str3 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_again) + str;
        String str4 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        String str5 = context.getResources().getString(R.string.IDS_device_fortuna_pair_guide_tip1) + str;
        String str6 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String format = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip3), context.getString(R.string.IDS_device_start_paring_title).toUpperCase(locale));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb2);
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.append((CharSequence) format);
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTQ_(Context context) {
        Locale locale = Locale.ENGLISH;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_first));
        String str = c;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_01), context.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(locale)) + str + str;
        String str3 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_again) + str;
        String str4 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        String str5 = context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip1_new) + str;
        String str6 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String format = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip3), context.getString(R.string.IDS_device_start_paring_title).toUpperCase(locale));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb2);
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.append((CharSequence) format);
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTP_(Context context) {
        Locale locale = Locale.ENGLISH;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_first));
        String str = c;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_01), context.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(locale)) + str + str;
        String str3 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_again) + str;
        String str4 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        String str5 = context.getResources().getString(R.string.IDS_device_fortuna_pair_guide_tip1) + str;
        String str6 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String format = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip3), context.getString(R.string.IDS_device_start_paring_title).toUpperCase(locale));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb2);
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.append((CharSequence) format);
        return spannableStringBuilder;
    }

    private static SpannableStringBuilder cTN_(Context context) {
        Locale locale = Locale.ENGLISH;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_first));
        String str = c;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_01), context.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(locale)) + str + str;
        String str3 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip_b5_again) + str;
        String str4 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 1) + " ";
        String str5 = context.getResources().getString(R.string.IDS_device_janus_pair_guide_tip1) + str;
        String str6 = String.format(locale, context.getResources().getString(R.string.IDS_device_pair_guide_step), 2) + " ";
        String format = String.format(locale, context.getResources().getString(R.string.IDS_device_latona_pair_guide_tip3), context.getString(R.string.IDS_device_start_paring_title).toUpperCase(locale));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb2);
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.append((CharSequence) str3);
        spannableStringBuilder.append((CharSequence) str4);
        spannableStringBuilder.append((CharSequence) str5);
        spannableStringBuilder.append((CharSequence) str6);
        spannableStringBuilder.append((CharSequence) format);
        return spannableStringBuilder;
    }

    public static boolean e() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null || defaultAdapter.isEnabled();
    }

    public static boolean b() {
        if (!(BaseApplication.e().getSystemService("location") instanceof LocationManager)) {
            LogUtil.c("DevicePairingPageUtils", "isGpsLocationEnable get Location Service fail");
            return false;
        }
        LocationManager locationManager = (LocationManager) BaseApplication.e().getSystemService("location");
        boolean isProviderEnabled = locationManager.isProviderEnabled(GeocodeSearch.GPS);
        LogUtil.d("DevicePairingPageUtils", "isGPSLocationEnable：", Boolean.valueOf(isProviderEnabled));
        if (iyg.e() || iyg.b()) {
            return isProviderEnabled;
        }
        boolean isProviderEnabled2 = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        LogUtil.d("DevicePairingPageUtils", "isNetWorkLocationEnable：", Boolean.valueOf(isProviderEnabled2));
        return isProviderEnabled || isProviderEnabled2;
    }

    public static boolean e(int i) {
        LogUtil.d("DevicePairingPageUtils", "isFollowDeviceByType:", Integer.valueOf(i));
        if (cvt.c(i)) {
            return false;
        }
        boolean b = b(jfu.j(i));
        LogUtil.d("DevicePairingPageUtils", "isFollowDeviceByType result :", Boolean.valueOf(b));
        return b;
    }

    public static boolean b(String str) {
        LogUtil.d("DevicePairingPageUtils", "is plugin download uuid:", str);
        boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(str);
        LogUtil.d("DevicePairingPageUtils", "is plugin download isPluginAvailable:", Boolean.valueOf(isResourcesAvailable));
        boolean z = false;
        if (isResourcesAvailable) {
            cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
            LogUtil.d("DevicePairingPageUtils", "isFollowDeviceByUuid success update ui");
            if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
                LogUtil.c("DevicePairingPageUtils", "isFollowDeviceByUuid info or WearDeviceInfo is null");
                return false;
            }
            if ("followed_relationship".equals(pluginInfoByUuid.f().bi())) {
                z = true;
            }
        }
        LogUtil.d("DevicePairingPageUtils", "isFollowDeviceByUuid result :", Boolean.valueOf(z));
        return z;
    }

    public static void d(final Context context, final String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.c("DevicePairingPageUtils", "gotoKeepAliveSetting, activity or cid is null");
            return;
        }
        Context e = BaseApplication.e();
        final int m = CommonUtil.m(e, LoginInit.getInstance(e).getAccountInfo(1009));
        if (m == 1) {
            GRSManager.a(e).e("domainKlgMapDtlUrl", new GrsQueryCallback() { // from class: obb.7
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    obb.a(context, m, str2, str);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.c("DevicePairingPageUtils", "gotoKeepAlive, cn is fail");
                }
            });
        } else {
            GRSManager.a(e).e("helpCustomerUrl", new GrsQueryCallback() { // from class: obb.8
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    obb.a(context, m, str2, str);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.c("DevicePairingPageUtils", "gotoKeepAlive, oversea is fail");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, int i, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DevicePairingPageUtils", "getKeepAliveUrl, urlMain is ull");
            return;
        }
        if (i == 1) {
            if (LanguageUtil.h(BaseApplication.e())) {
                str3 = str + String.format(Locale.ENGLISH, "/hwtips/protection_setup/%s/protection.html?cid=%s", "zh-CN", str2);
            } else {
                str3 = str + String.format(Locale.ENGLISH, "/hwtips/protection_setup/%s/protection.html?cid=%s", "en-US", str2);
            }
            LogUtil.d("DevicePairingPageUtils", "keepAliveUrl cn: ", str3);
            H5proUtil.jumpFromDeeplink(context, str3);
            return;
        }
        String str4 = str + String.format(Locale.ENGLISH, "/handbook/SingleJumppage/protection_setup/en-US/index.html?lang=%s", CommonUtil.u());
        LogUtil.d("DevicePairingPageUtils", "keepAliveUrl oversea: ", str4);
        H5proUtil.jumpFromDeeplink(context, str4);
    }

    public static boolean d(int i) {
        return (!AppBundle.c().isBundleModule("PluginWearAbility") || AppBundle.e().getInstalledModules().contains("PluginWearAbility") || i == 2) ? false : true;
    }

    public static boolean c() {
        return (CommonUtil.ax() || CommonUtil.bf()) && AppBundle.c().isBundleModule("PluginHiAiEngine") && !AppBundle.e().getInstalledModules().contains("PluginHiAiEngine");
    }

    public static void a(String str) {
        DeviceInfo e = e(str);
        if (e == null || e.getDeviceConnectState() != 2) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(str);
            oae.c(BaseApplication.e()).e(arrayList, true);
        }
    }

    private static DeviceInfo e(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DevicePairingPageUtils");
        if (koq.b(deviceList)) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null && TextUtils.equals(str, deviceInfo.getDeviceIdentify())) {
                return deviceInfo;
            }
        }
        return null;
    }

    public static boolean a(Context context, int i) {
        if (!(context instanceof Activity)) {
            LogUtil.d("DevicePairingPageUtils", "context is not instanceof activity");
            return false;
        }
        if (Build.VERSION.SDK_INT < 35) {
            return false;
        }
        if (i != 1 && i != 3 && i != 2) {
            LogUtil.d("DevicePairingPageUtils", "bluetoothType is null ");
            return false;
        }
        if (ContextCompat.checkSelfPermission((Activity) context, "android.permission.RECEIVE_SENSITIVE_NOTIFICATIONS") != 0) {
            return true;
        }
        LogUtil.d("DevicePairingPageUtils", "isHasPermission is :true.");
        return false;
    }

    public static void d(final IBaseResponseCallback iBaseResponseCallback, Context context) {
        if (context == null) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string._2130847550_res_0x7f02273e);
        builder.czC_(R.string._2130847541_res_0x7f022735, new View.OnClickListener() { // from class: obb.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: obb.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IBaseResponseCallback iBaseResponseCallback2 = IBaseResponseCallback.this;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }
}
