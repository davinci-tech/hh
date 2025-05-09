package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.MultiUsersManagerApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.model.DeviceExtra;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.bjf;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dks {
    public static final String e = BaseApplication.getAppPackage();
    private static final double[] b = {25.0d, 80.0d};

    public static int a(int i) {
        if (i <= 0) {
            return 29;
        }
        return i;
    }

    public static int b(int i) {
        if (i == -100) {
            return 1;
        }
        return i;
    }

    public static int d(int i) {
        if (i <= 0) {
            return 173;
        }
        return i;
    }

    public static void WA_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattCharacteristic bluetoothGattCharacteristic2) {
        int i;
        LogUtil.a("PluginDevice_HealthUtils", "Enter setDateTime");
        if (bluetoothGattCharacteristic == null && bluetoothGattCharacteristic2 == null) {
            LogUtil.a("PluginDevice_HealthUtils", "setDateTime fail");
            return;
        }
        if (bluetoothGattCharacteristic != null) {
            LogUtil.a("PluginDevice_HealthUtils", "setDateTime mDateTimeCharacteristic != null");
            i = 7;
        } else {
            bluetoothGattCharacteristic = null;
            i = 0;
        }
        if (bluetoothGattCharacteristic2 != null) {
            LogUtil.a("PluginDevice_HealthUtils", "setDateTime mCurrentTimeCharacteristic != null");
            i = 10;
        } else {
            bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
        }
        byte[] c = c(i);
        if (bluetoothGatt != null) {
            bluetoothGattCharacteristic2.setValue(c);
            LogUtil.a("PluginDevice_HealthUtils", "setDateTime array = ", Arrays.toString(c));
            new cyv().Sb_(bluetoothGatt, bluetoothGattCharacteristic2);
        }
    }

    public static byte[] c(int i) {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        byte[] bArr = new byte[i];
        bArr[0] = (byte) (i2 & 255);
        bArr[1] = (byte) ((i2 >> 8) & 255);
        bArr[2] = (byte) ((i3 + 1) & 255);
        bArr[3] = (byte) (calendar.get(5) & 255);
        bArr[4] = (byte) (calendar.get(11) & 255);
        bArr[5] = (byte) (calendar.get(12) & 255);
        bArr[6] = (byte) (calendar.get(13) & 255);
        return bArr;
    }

    public static int c(double d) {
        if (Math.abs(d - Math.floor(d)) >= 1.0E-6d) {
            return 100;
        }
        return (int) d;
    }

    private static AnalyticsValue e(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE) {
            return AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060043;
        }
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN) {
            return AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060045;
        }
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            return AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060010;
        }
        return null;
    }

    public static void n(String str) {
        dcz d;
        AnalyticsValue e2;
        if (str == null || (d = ResourceManager.e().d(str)) == null || (e2 = e(d.l())) == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, e(str, d.n().b()));
        hashMap.put("prodId", dij.e(str));
        ixx.d().d(cpp.a(), e2.value(), hashMap, 0);
    }

    private static AnalyticsValue c(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE) {
            return AnalyticsValue.HEALTH_PLUGIN_DEVICE_MEASURE_SUCCEED_2060044;
        }
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN) {
            return AnalyticsValue.HEALTH_PLUGIN_DEVICE_MEASURE_SUCCEED_2060046;
        }
        return null;
    }

    public static void o(String str) {
        dcz d;
        AnalyticsValue c;
        if (str == null || (d = ResourceManager.e().d(str)) == null || (c = c(d.l())) == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, e(str, d.n().b()));
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, d.l().name());
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        ixx.d().d(cpp.a(), c.value(), hashMap, 0);
    }

    public static boolean f(String str) {
        if (str == null) {
            return false;
        }
        for (HealthDevice.HealthDeviceKind healthDeviceKind : HealthDevice.HealthDeviceKind.values()) {
            if (healthDeviceKind.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static HealthDevice.HealthDeviceKind c(String str) {
        return f(str) ? HealthDevice.HealthDeviceKind.valueOf(str) : HealthDevice.HealthDeviceKind.HDK_NOT_DEVICE;
    }

    public static boolean g(String str) {
        dcz d;
        if (TextUtils.isEmpty(str) || (d = ResourceManager.e().d(str)) == null) {
            return false;
        }
        return HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(d.l());
    }

    public static String a(String str, String str2) {
        String str3 = "";
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            String str4 = "&" + str + "=";
            int indexOf = str2.indexOf(str4);
            if (indexOf == -1) {
                return "";
            }
            String substring = str2.substring(indexOf + str4.length());
            LogUtil.a("PluginDevice_HealthUtils", "substring:", substring);
            if (substring != null) {
                int indexOf2 = substring.indexOf("&");
                int indexOf3 = substring.indexOf(";");
                if (indexOf2 != -1) {
                    substring = substring.substring(0, indexOf2);
                } else if (indexOf3 != -1) {
                    substring = substring.substring(0, indexOf3);
                }
                str3 = substring;
            }
            LogUtil.a("PluginDevice_HealthUtils", "param:", str3);
        }
        return str3;
    }

    public static String e(String str) {
        if (str == null) {
            LogUtil.h("PluginDevice_HealthUtils", "payload == null");
            return "";
        }
        try {
            String substring = str.substring(str.indexOf(RecordAction.ACT_COST_TIME_TAG));
            int indexOf = substring.indexOf("&");
            return indexOf != -1 ? substring.substring(2, indexOf) : "";
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.a("PluginDevice_HealthUtils", "no devicetype(t=) in NFC payload or qr code", e2.getLocalizedMessage());
            return "";
        }
    }

    public static boolean k(String str) {
        dcz d;
        if (!TextUtils.isEmpty(str) && (d = ResourceManager.e().d(str)) != null) {
            HealthDevice.HealthDeviceKind l = d.l();
            if (HealthDevice.HealthDeviceKind.HDK_TREADMILL.equals(l) || HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.equals(l) || HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE.equals(l) || HealthDevice.HealthDeviceKind.HDK_ELLIPTICAL_MACHINE.equals(l) || HealthDevice.HealthDeviceKind.HDK_WALKING_MACHINE.equals(l)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static HealthDevice.HealthDeviceKind d(String str) {
        char c;
        if (str == null) {
            return null;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 47731:
                if (str.equals("025")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 47744:
                if (str.equals("02B")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 47823:
                if (str.equals("054")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 47918:
                if (str.equals("086")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 47920:
                if (str.equals("088")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 47934:
                if (str.equals("08F")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 47945:
                if (str.equals("092")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 47948:
                if (str.equals("095")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 48225:
                if (str.equals("0B3")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 48226:
                if (str.equals("0B4")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 48244:
                if (str.equals("0BF")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 48253:
                if (str.equals("0C0")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 48254:
                if (str.equals("0C1")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 48258:
                if (str.equals("0C5")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 48768:
                if (str.equals("14C")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
        }
        return null;
    }

    public static boolean c(Context context) {
        if (context != null) {
            return LanguageUtil.m(context) && !Utils.o() && CommonUtil.y(context) && !UnitUtil.h();
        }
        LogUtil.b("PluginDevice_HealthUtils", "context == null");
        return false;
    }

    private static double d(double d) {
        return Math.round(d * 10.0d) / 10.0d;
    }

    public static double a(double d, int i) {
        if (i <= 0) {
            return 0.0d;
        }
        return d(d / Math.pow(i / 100.0d, 2.0d));
    }

    public static boolean a(double d) {
        return d(d, a());
    }

    private static boolean d(double d, double[] dArr) {
        return d >= dArr[0] && d <= dArr[1];
    }

    public static double[] a() {
        return new double[]{b(WeightConstants.c()[0]), b(WeightConstants.c()[1])};
    }

    public static double b(double d) {
        return CommonUtil.a(String.valueOf(new BigDecimal(d).setScale(0, 4)));
    }

    public static String c(double d, double d2, double d3, String str) {
        boolean z;
        String string;
        String string2;
        if (d <= 0.0d || d2 <= 0.0d || d3 < 10.0d || d3 > 250.0d) {
            LogUtil.h("PluginDevice_HealthUtils", "getMeasureResultTip weight or startWeight or goalWeight is illegal");
            return "";
        }
        LogUtil.c("PluginDevice_HealthUtils", "getMeasureResultTip weight ", Double.valueOf(d), " startWeight ", Double.valueOf(d2), " goalWeight ", Double.valueOf(d3));
        Resources resources = BaseApplication.getContext().getResources();
        if (d2 < d3) {
            z = d >= d3;
            if (z) {
                string2 = resources.getString(R.string.IDS_device_wifi_measure_result_equal_target_msg);
            } else {
                string2 = resources.getString(R.string.IDS_device_wifi_measure_result_more_target_msg, str);
            }
            LogUtil.a("PluginDevice_HealthUtils", "getMeasureResultTip gain weight isDoneGoal ", Boolean.valueOf(z));
            return string2;
        }
        if (d2 <= d3) {
            String string3 = resources.getString(R.string.IDS_device_wifi_measure_result_equal_target_msg);
            LogUtil.a("PluginDevice_HealthUtils", "getMeasureResultTip done goal");
            return string3;
        }
        z = d <= d3;
        if ((d2 - d3) / d2 >= 0.18d) {
            ReleaseLogUtil.a("R_PluginDevice_HealthUtils", "getMeasureResultTip startWeight and goalWeight is not 0.18");
            return "";
        }
        if (z) {
            string = resources.getString(R.string.IDS_device_wifi_measure_result_equal_target_msg);
        } else {
            string = resources.getString(R.string.IDS_device_wifi_measure_result_less_target_msg, str);
        }
        LogUtil.a("PluginDevice_HealthUtils", "getMeasureResultTip lose weight isDoneTarget ", Boolean.valueOf(z));
        return string;
    }

    public static void b(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (nry.d("key_person_info_setting_dialog_show_count_") == 2) {
            c();
        }
        d(context, true, iBaseResponseCallback);
    }

    private static void d(final Context context, final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        if (z && !nry.c()) {
            LogUtil.h("PluginDevice_HealthUtils", "showSetUserInfoDialog canShowSettingDialog false");
            c(iBaseResponseCallback, 0);
        } else {
            if (context == null) {
                LogUtil.h("PluginDevice_HealthUtils", "context == null");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string._2130844052_res_0x7f021994).d(R.string._2130844053_res_0x7f021995).cyR_(R.string._2130841424_res_0x7f020f50, new View.OnClickListener() { // from class: dks.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.h("PluginDevice_HealthUtils", "showSetUserInfoDialog cancel");
                    if (z) {
                        dks.c();
                        dks.c(iBaseResponseCallback, 0);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyU_(R.string._2130844051_res_0x7f021993, new View.OnClickListener() { // from class: dks.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dks.d(context, z);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            a2.setCancelable(false);
            a2.show();
        }
    }

    public static void d(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setClassName(context, KakaConstants.USER_INFO_URL);
        intent.setPackage(context.getPackageName());
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            LogUtil.h("PluginDevice_HealthUtils", "showSettingDialog resolveActivity == null");
            return;
        }
        if (z) {
            c();
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "jumpUserInfoActivity startActivity catch e:", e2.getMessage());
        }
    }

    public static void c() {
        int i;
        Context context = BaseApplication.getContext();
        String d = knl.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        KeyValDbManager b2 = KeyValDbManager.b(context);
        String str = "key_person_info_setting_dialog_show_count_" + d;
        String e2 = b2.e(str);
        LogUtil.a("PluginDevice_HealthUtils", "updateShowCount count=", e2);
        if (TextUtils.isEmpty(e2)) {
            b2.e(str, Integer.toString(1));
            return;
        }
        try {
            i = Integer.parseInt(e2);
        } catch (NumberFormatException e3) {
            LogUtil.b("PluginDevice_HealthUtils", "updateShowCountAndTime e=", LogAnonymous.b((Throwable) e3));
            i = 0;
        }
        b2.e(str, Integer.toString(i + 1));
    }

    public static void Wu_(final Handler handler) {
        ((MultiUsersManagerApi) Services.c("PluginDevice", MultiUsersManagerApi.class)).getCurrentUserForUserInfo(new ResponseCallback() { // from class: dkt
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                dks.Wy_(handler, i, (cfi) obj);
            }
        });
    }

    static /* synthetic */ void Wy_(Handler handler, int i, cfi cfiVar) {
        LogUtil.a("PluginDevice_HealthUtils", "getCurrentUserForUserInfo code = ", Integer.valueOf(i));
        if (i == 0) {
            Wz_(handler, 10001, cfiVar);
            Object[] objArr = new Object[2];
            objArr[0] = "getCurrentUserForUserInfo currentUserInfo  is null ? ";
            objArr[1] = Boolean.valueOf(cfiVar == null);
            LogUtil.a("PluginDevice_HealthUtils", objArr);
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), nsn.e("0"));
            return;
        }
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), i);
    }

    public static boolean a(Object obj) {
        if (obj instanceof HiUserInfo) {
            HiUserInfo hiUserInfo = (HiUserInfo) obj;
            return hiUserInfo.isGenderValid() && hiUserInfo.isBirthdayValid() && hiUserInfo.isHeightValid();
        }
        if (obj instanceof cfi) {
            cfi cfiVar = (cfi) obj;
            return cfiVar.t() && cfiVar.p() && cfiVar.q();
        }
        LogUtil.h("PluginDevice_HealthUtils", "object is invalid");
        return false;
    }

    public static void b(Context context, Object obj) {
        IBaseResponseCallback iBaseResponseCallback = new IBaseResponseCallback() { // from class: dks.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj2) {
                LogUtil.c("PluginDevice_HealthUtils", "checkUserWeightAndShowDialog cancel");
            }
        };
        if (obj instanceof HiUserInfo) {
            if (((HiUserInfo) obj).isWeightValid()) {
                return;
            }
            b(context, iBaseResponseCallback);
        } else if (obj instanceof cfi) {
            if (((cfi) obj).v()) {
                return;
            }
            b(context, iBaseResponseCallback);
        } else {
            if (obj instanceof JSONObject) {
                try {
                    if (((JSONObject) obj).getJSONArray("data").getJSONObject(0).getDouble("weight") <= 0.0d) {
                        b(context, iBaseResponseCallback);
                        return;
                    }
                    return;
                } catch (JSONException unused) {
                    LogUtil.h("PluginDevice_HealthUtils", "JSONObject is invalid");
                    return;
                }
            }
            LogUtil.h("PluginDevice_HealthUtils", "object is invalid");
        }
    }

    public static void Wz_(Handler handler, int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
    }

    public static Intent Wx_(dcz dczVar, String str, String str2) {
        if (dczVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PluginDevice_HealthUtils", "jumpIntroductionData productInfo or productId or uniqueId = null");
            return null;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        intent.putExtra("uniqueId", str2);
        intent.putExtra("name", e(str, dczVar.n().b()));
        intent.putExtra("deviceType", dczVar.l().name());
        intent.putExtra(Constants.KEY_BLE_SCAN_MODE, dczVar.x().c());
        intent.putExtra("bleIntroductionType", dczVar.m().d());
        return intent;
    }

    public static void d(Context context, dcz dczVar, String str, String str2) {
        LogUtil.a("PluginDevice_HealthUtils", "switchToH5ProIntro");
        if (context == null || dczVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_HealthUtils", "switchToH5ProIntro context or productInfo or productId or uniqueId = null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("uniqueId", str2);
        contentValues.put("name", dczVar.n().b());
        contentValues.put("deviceType", dczVar.l().name());
        contentValues.put("deviceId", dczVar.g());
        new BleJsInteractionCompact().startH5Pro(context, "com.huawei.health.device." + str, contentValues, "");
    }

    public static void c(Context context, dcz dczVar, String str, String str2, String str3) {
        LogUtil.a("PluginDevice_HealthUtils", "switchToH5ProIntro");
        if (context == null || dczVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_HealthUtils", "switchToH5ProIntro context or productInfo or productId or uniqueId = null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("uniqueId", str2);
        contentValues.put("name", dczVar.n().b());
        contentValues.put("deviceType", dczVar.l().name());
        contentValues.put("deviceId", dczVar.g());
        contentValues.put(BleConstants.ATTACH_SN, str3);
        new BleJsInteractionCompact().startH5Pro(context, "com.huawei.health.device." + str, contentValues, "");
    }

    public static void a(Context context, String str, String str2, String str3) {
        LogUtil.a("PluginDevice_HealthUtils", "goToH5ProIntro");
        dcz d = ResourceManager.e().d(str);
        if (context == null || d == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_HealthUtils", "goToH5ProIntro context or productInfo or productId or uniqueId = null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("uniqueId", str2);
        contentValues.put("name", d.n().b());
        contentValues.put("deviceType", d.l().name());
        contentValues.put("deviceId", d.g());
        new BleJsInteractionCompact().startH5Pro(context, "com.huawei.health.device." + str, contentValues, str3);
    }

    public static void c(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("PluginDevice_HealthUtils", "context == null, disable startWebViewActivity");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        if (d == null) {
            LogUtil.h("PluginDevice_HealthUtils", "productInfo is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("name", e(str, d.n().b()));
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "startWebViewActivity startActivity catch e:", e2.getMessage());
        }
    }

    public static void WC_(Activity activity, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || activity == null || TextUtils.isEmpty(str2)) {
            LogUtil.b("PluginDevice_HealthUtils", "startHealthKitActivity initData is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(activity.getPackageName());
        intent.setClassName(activity, "com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity");
        intent.putExtra("key_start_to_measure_to_health_kit_authorization", true);
        intent.putExtra("productId", str);
        intent.putExtra("uniqueId", str2);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        if (d == null) {
            LogUtil.b("PluginDevice_HealthUtils", "startHealthKitActivity deviceProductInfo is null");
            return;
        }
        contentValues.put("name", d.n().b());
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "startHealthKitActivity startActivity catch e:", e2.getMessage());
        }
        if (z) {
            activity.finish();
        }
    }

    public static void Ww_(Activity activity, String str) {
        if (activity == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
        String c = dcq.b().c(str);
        LogUtil.c("PluginDevice_HealthUtils", "gotoSinoH5" + c);
        intent.putExtra("url", c + "#/type=1");
        intent.putExtra("productId", str);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "gotoSinoH5 startActivity catch e:", e2.getMessage());
        }
        activity.finish();
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.a("PluginDevice_HealthUtils", "isSwitchPage,context is null");
            return false;
        }
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10008), "key_device_pair_switch_ui");
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        return Boolean.parseBoolean(b2);
    }

    public static String b(String str) {
        LogUtil.c("PluginDevice_HealthUtils", "getSubProductId productId = " + str);
        return TextUtils.isEmpty(str) ? "" : str.indexOf(com.huawei.openalliance.ad.constant.Constants.LINK) > 0 ? str.substring(0, str.indexOf(com.huawei.openalliance.ad.constant.Constants.LINK)) : str;
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        LogUtil.a("PluginDevice_HealthUtils", "isShowPrivacy country", commonCountryCode);
        if (TextUtils.isEmpty(commonCountryCode)) {
            commonCountryCode = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        }
        if (TextUtils.isEmpty(commonCountryCode)) {
            return false;
        }
        String e2 = jah.c().e("domain_honor_country");
        LogUtil.h("PluginDevice_HealthUtils", "honorCountrysString: ", e2);
        if (e2 == null) {
            LogUtil.h("PluginDevice_HealthUtils", "honorCountrysString is null");
            return true;
        }
        if (e2.equals("ALL")) {
            return true;
        }
        for (String str : e2.split(",")) {
            if (str.equalsIgnoreCase(commonCountryCode)) {
                return true;
            }
        }
        return false;
    }

    public static void e(Context context, String str) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.ui.device.activity.adddevice.AllDeviceListActivity");
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("kind_id", str);
        }
        gnm.aPB_(context, intent);
    }

    public static void e() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    public static void d() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.UI_DEVICE_LIST_CHANGED");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    public static boolean d(String str, String str2) {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        if (TextUtils.isEmpty(str2)) {
            HealthDevice bondedDevice = healthDeviceEntryApi.getBondedDevice(str);
            if (bondedDevice != null) {
                LogUtil.h("PluginDevice_HealthUtils", "isBondedThirdPartyDevice uniqueId is null and thirdPartyDevice name: ", bondedDevice.getDeviceName());
            }
            return bondedDevice != null;
        }
        if (healthDeviceEntryApi.isDeviceKitUniversal(str)) {
            com.huawei.hihealth.device.open.HealthDevice bondedDeviceUniversal = healthDeviceEntryApi.getBondedDeviceUniversal(str, str2);
            if (bondedDeviceUniversal != null) {
                LogUtil.h("PluginDevice_HealthUtils", "isBondedThirdPartyDevice by prodId and uniqueId thirdPartyDevice name: ", bondedDeviceUniversal.getDeviceName());
            }
            return bondedDeviceUniversal != null;
        }
        HealthDevice bondedDeviceByUniqueId = healthDeviceEntryApi.getBondedDeviceByUniqueId(str2);
        if (bondedDeviceByUniqueId != null) {
            LogUtil.h("PluginDevice_HealthUtils", "isBondedThirdPartyDevice by uniqueId thirdPartyDevice name: ", bondedDeviceByUniqueId.getDeviceName());
        }
        return healthDeviceEntryApi.getBondedDeviceByUniqueId(str2) != null;
    }

    public static boolean j(String str) {
        dcz d = ResourceManager.e().d(str);
        return (d == null || d.l() == null || !HealthDevice.HealthDeviceKind.HDK_FITTINGS.name().equals(d.l().name())) ? false : true;
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_HealthUtils", "isBondedFittingsDevice attachUniqueId or uniqueId is null");
            return false;
        }
        MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(str, false);
        if (bondedDeviceByUniqueId == null) {
            return false;
        }
        DeviceExtra deviceExtra = (DeviceExtra) HiJsonUtil.e(bondedDeviceByUniqueId.getExtra(), DeviceExtra.class);
        if (deviceExtra != null && koq.c(deviceExtra.getFittings())) {
            Iterator<DeviceExtra.DeviceData> it = deviceExtra.getFittings().iterator();
            while (it.hasNext()) {
                if (str2.equals(it.next().getUniqueId())) {
                    LogUtil.a("PluginDevice_HealthUtils", "isBondedFittingsDevice is true");
                    return true;
                }
            }
        }
        LogUtil.a("PluginDevice_HealthUtils", "isBondedFittingsDevice is false");
        return false;
    }

    public static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str2)) {
            LogUtil.a("PluginDevice_HealthUtils", "startThirdDeviceBindingPage context or productId is null");
            return;
        }
        dcz d = ResourceManager.e().d(str2);
        if (d == null) {
            LogUtil.a("PluginDevice_HealthUtils", "startThirdDeviceBindingPage productInfo null");
            e(context, str);
        } else if (str2.equals("9bf158ba-49b0-46aa-9fdf-ed75da1569cf")) {
            b(context, d);
        } else {
            e(context, d);
        }
    }

    private static void e(Context context, dcz dczVar) {
        if (context == null || dczVar == null) {
            LogUtil.a("PluginDevice_HealthUtils", "startThirdDevicePairGuide context or productInfo is null");
            return;
        }
        Intent intent = new Intent();
        if (dczVar.d().size() > 0) {
            intent.setPackage(cez.w);
            intent.setClassName(cez.w, "com.huawei.ui.device.activity.adddevice.PairingGuideActivity");
            intent.putExtra("kind_id", dczVar.l().name());
            if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiOrHonourDevice(dczVar.t())) {
                intent.putExtra("pair_guide", "2");
            } else {
                intent.putExtra("pair_guide", "5");
            }
            List<msx> c = mst.a().c(dczVar.t());
            if (koq.c(c) && c.get(0).j() != null) {
                intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, cxs.a(c.get(0).j()));
            }
        } else {
            intent.setClassName(cez.w, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
            intent.setFlags(AppRouterExtras.COLDSTART);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(dczVar.t());
        intent.putStringArrayListExtra("uuid_list", arrayList);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "startThirdDevicePairGuide startActivity catch e:", e2.getMessage());
        }
    }

    private static void b(Context context, dcz dczVar) {
        if (context == null || dczVar == null) {
            LogUtil.a("PluginDevice_HealthUtils", "startThirdPartyDeviceBindingActivity context or productInfo is null");
            return;
        }
        String d = dcx.d(dczVar.t(), dczVar.n().b());
        List<msx> c = mst.a().c(dczVar.t());
        String d2 = dczVar.n().d();
        if (koq.c(c)) {
            d2 = c.get(0).c();
        }
        try {
            context.startActivity(Wv_(dczVar.t(), null, dczVar.l().name(), d, d2));
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PluginDevice_HealthUtils", "startThirdPartyDeviceBindingActivity startActivity catch e:", e2.getMessage());
        }
    }

    private static Intent Wv_(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("productId", str);
        intent.putExtra("arg1", "DeviceBindWaitingFragment");
        intent.putExtra("DeviceType", str3);
        intent.putExtra("DeviceName", str4);
        intent.putExtra("DeviceIconPath", str5);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        intent.putExtra("commonDeviceInfo", contentValues);
        return intent;
    }

    public static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_HealthUtils", "uuid is invalid");
            return false;
        }
        if (msn.c().e(str) == null) {
            LogUtil.h("PluginDevice_HealthUtils", "not download nfc task");
            return false;
        }
        LogUtil.a("PluginDevice_HealthUtils", "pullTask not null");
        return true;
    }

    private static double[] j() {
        double[] dArr = b;
        return new double[]{UnitUtil.a(dArr[0], 1), UnitUtil.a(dArr[1], 1)};
    }

    private static boolean e(double d) {
        return d(UnitUtil.a(d, 1), j());
    }

    public static double b(HiHealthData hiHealthData) {
        double d = hiHealthData.getDouble("protein");
        if (Double.compare(d, 0.0d) > 0) {
            return d;
        }
        double d2 = hiHealthData.getDouble("bodyWeight");
        double d3 = hiHealthData.getDouble(BleConstants.MOISTURE_RATE);
        double d4 = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
        double d5 = hiHealthData.getDouble(BleConstants.BONE_SALT);
        if (!e(d3)) {
            d3 = hiHealthData.getDouble(BleConstants.MOISTURE);
        }
        int c = c(hiHealthData);
        try {
            double a2 = UnitUtil.a(d2, c);
            LogUtil.a("PluginDevice_HealthUtils", "getProteinValue weight: ", Double.valueOf(a2), " hiWaterRate: ", Double.valueOf(d3));
            double a3 = (UnitUtil.a(a2, c) * d3) / 100.0d;
            LogUtil.a("PluginDevice_HealthUtils", "getProteinValue waterValue: ", Double.valueOf(a3));
            double a4 = UnitUtil.a(a3, c);
            double a5 = UnitUtil.a((UnitUtil.a(a2, c) * d4) / 100.0d, c);
            double a6 = UnitUtil.a(d5, c);
            if (Double.compare(a2, 0.0d) > 0 && Double.compare(a5, 0.0d) > 0 && Double.compare(a4, 0.0d) > 0 && Double.compare(a6, 0.0d) > 0) {
                return UnitUtil.a(((a2 - a4) - a5) - a6, c);
            }
            return 0.0d;
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginDevice_HealthUtils", "getProteinValue NumberFormatException");
            return d;
        }
    }

    private static int c(HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("trackdata_deviceType");
        LogUtil.a("PluginDevice_HealthUtils", "getFractionDigitByType type = ", Integer.valueOf(i));
        if (!((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiWspDataType(i)) {
            return 1;
        }
        double d = hiHealthData.getDouble("bodyWeight");
        if (UnitUtil.h()) {
            d = UnitUtil.h(d);
            LogUtil.a("PluginDevice_HealthUtils", "getFractionDigitByType type showImperialUnit, ", Double.valueOf(d));
        }
        return Math.round(Math.pow(10.0d, 2.0d) * d) % 10 == 0 ? 1 : 2;
    }

    public static void WB_(Activity activity, final String str, boolean z) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: dks.2
            @Override // java.lang.Runnable
            public void run() {
                nrh.d(BaseApplication.getContext(), str);
            }
        });
        if (z) {
            activity.finish();
        }
    }

    public static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "87c3421b-5975-49f9-8d6d-013481708278".equals(str) || "59532edb-41d1-4fa0-b587-04a265db8b1f".equals(str);
    }

    public static String e(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? "" : dcx.d(str, str2);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PluginDevice_HealthUtils", "getDeviceName productId is null");
            return "";
        }
        dcz d = ResourceManager.e().d(str);
        return d != null ? dcx.d(str, d.n().b()) : "";
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PluginDevice_HealthUtils", "getDeviceName productId is null");
            return "";
        }
        dcz d = ResourceManager.e().d(str);
        return d != null ? dcx.a(str, d.n().b(), str2) : "";
    }

    public static void c(List<bjf> list, int i) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "ecology_device_module", "ecology_device_bluetooth_name");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        bjf.a aVar = new bjf.a();
        aVar.e(i);
        aVar.a(b2.toUpperCase(Locale.ENGLISH));
        list.add(aVar.a());
    }

    public static boolean a(long j) {
        return Math.abs(System.currentTimeMillis() - j) < 3000;
    }

    public static void b() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(cos.b);
        arrayList.add(cos.f11394a);
        arrayList.add(cos.e);
        dbw.b((ArrayList<String>) arrayList);
    }

    public static int e(float f, float f2, int i) {
        return new BigDecimal(f).setScale(i, 4).compareTo(new BigDecimal(f2).setScale(i, 4));
    }

    public static int b(float f, float f2) {
        return e(f, f2, 1);
    }
}
