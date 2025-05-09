package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class dij {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, ProductMapInfo> f11675a = new ConcurrentHashMap();

    public static int d(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean o(String str) {
        return Locale.getDefault().getLanguage().equalsIgnoreCase(str);
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        LogUtil.c("DeviceUtils", "ProductIntroductionFragment langIsAr() currentLanguage is ", language);
        return (language.endsWith("iw") || language.endsWith("ar")) || (language.endsWith("fa") || language.endsWith(Constants.URDU_LANG));
    }

    public static int Va_(ListView listView) {
        ListAdapter adapter;
        if (listView == null || (adapter = listView.getAdapter()) == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            View view = adapter.getView(i2, null, listView);
            if (view != null) {
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
        }
        return i + (listView.getDividerHeight() * (adapter.getCount() - 1));
    }

    public static void Vc_(ImageView imageView, String str) {
        dcz d;
        if (imageView == null || TextUtils.isEmpty(str) || (d = ResourceManager.e().d(str)) == null || d.n() == null) {
            return;
        }
        if (koq.b(d.e())) {
            imageView.setImageResource(dcx.a(d.n().d()));
        } else {
            imageView.setImageBitmap(dcx.TK_(dcq.b().a(d.t(), d.n().d())));
        }
    }

    public static void UZ_(Activity activity) {
        if (activity == null) {
            LogUtil.h("DeviceUtils", "enterToMainActivity activity is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(com.huawei.operation.utils.Constants.HOME_TAB_NAME, com.huawei.operation.utils.Constants.HOME);
        activity.startActivity(intent);
        activity.finish();
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceUtils", "getDeviceIdentification identification is null");
            return "";
        }
        if (str.replace(":", "").length() < 3) {
            LogUtil.a("DeviceUtils", "identification's length less than 3");
            return Constants.LINK + str.replace(":", "");
        }
        return Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3);
    }

    public static boolean c(String str) {
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (remoteDevice.getBondState() == 12) {
                Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
                Boolean bool = method.invoke(remoteDevice, new Object[0]) instanceof Boolean ? (Boolean) method.invoke(remoteDevice, new Object[0]) : null;
                if (bool == null) {
                    return false;
                }
                return bool.booleanValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LogUtil.b("DeviceUtils", "disconnectBluetooth Exception:", ExceptionUtils.d(e));
        }
        return false;
    }

    public static void e(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("DeviceUtils", "saveDeviceKind");
        if (HealthDevice.HealthDeviceKind.HDK_WEIGHT.equals(healthDeviceKind)) {
            k("BIND_WEIGHT");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.equals(healthDeviceKind)) {
            k("BIND_BLOOD_PRESSURE");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.equals(healthDeviceKind)) {
            k("BIND_BLOOD_SUGAR");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_HEART_RATE.equals(healthDeviceKind)) {
            k("BIND_HEART_RATE");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE.equals(healthDeviceKind)) {
            k("BIND_BODY_TEMPERATURE");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN.equals(healthDeviceKind)) {
            k("BIND_BLOOD_OXYGEN");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(healthDeviceKind)) {
            k("BIND_ROPE_SKIPPING");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_TREADMILL.equals(healthDeviceKind)) {
            k("BIND_TREADMILL");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.equals(healthDeviceKind)) {
            k("BIND_EXERCISE_BIKE");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE.equals(healthDeviceKind)) {
            k("BIND_ROWING_MACHINE");
            return;
        }
        if (HealthDevice.HealthDeviceKind.HDK_ELLIPTICAL_MACHINE.equals(healthDeviceKind)) {
            k("BIND_ELLIPTICAL_MACHINE");
        } else if (HealthDevice.HealthDeviceKind.HDK_WALKING_MACHINE.equals(healthDeviceKind)) {
            k("BIND_WALKING_MACHINE");
        } else {
            LogUtil.h("DeviceUtils", "saveDeviceKind else");
        }
    }

    private static void k(String str) {
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), str, "1", new StorageParams());
    }

    public static void UX_(Context context, View view, double d, boolean z) {
        if (context != null && (context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int min = (int) (Math.min(i, displayMetrics.heightPixels * 0.5d) * d);
            if (z) {
                i = min;
            }
            view.setLayoutParams(new LinearLayout.LayoutParams(i, min));
        }
    }

    public static void UY_(Activity activity) {
        if (activity == null) {
            return;
        }
        new dhy(activity, null, null, "167P").d("HDK_BLOOD_SUGAR", true);
    }

    public static String d(long j) {
        return d(j / 1000.0d, 0);
    }

    public static String b(Context context, long j) {
        if (context == null) {
            return "";
        }
        return context.getResources().getQuantityString(R.plurals._2130903506_res_0x7f0301d2, j < 2 ? 1 : 2, "");
    }

    public static String b(double d) {
        return d((d / 1000.0d) / 60.0d, 2);
    }

    private static String d(double d, int i) {
        return UnitUtil.e(d, 1, i);
    }

    public static boolean j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceUtils", "isSecondRope productId is null");
            return false;
        }
        return cez.ac.contains(e(str));
    }

    public static boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceUtils", "isSecondRope productId is null");
            return false;
        }
        return cez.ak.contains(e(str));
    }

    public static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceUtils", "isHighRopeType productId is null");
            return false;
        }
        return "2G98".equals(e(str));
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceUtils", "isAthleticsRopeType productId is empty");
            return false;
        }
        return "2IX6".equals(e(str));
    }

    public static boolean c(int i, int i2) {
        if (i == 283) {
            return cez.am.contains(Integer.valueOf(i2));
        }
        return false;
    }

    public static boolean i(String str) {
        return cez.ak.contains(e(str));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String c(String str, String str2) {
        char c;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        dcz d = ResourceManager.e().d(str2);
        if (d == null) {
            LogUtil.h("DeviceUtils", "isSupportIntermit productInfo is null");
            return "";
        }
        String c2 = d.c(str);
        str.hashCode();
        switch (str.hashCode()) {
            case -2054444593:
                if (str.equals("isSupportAutoPauseSuprression")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1691944104:
                if (str.equals("marketing_position_id")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -814800055:
                if (str.equals("pageVersion")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 110364:
                if (str.equals("ota")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 712563297:
                if (str.equals("isSupportIntermit")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1291234845:
                if (str.equals("isSupportWeight")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1977567821:
                if (str.equals("isSupportScore")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 4:
            case 5:
            case 6:
                str3 = "true";
                if (!"true".equals(c2) && !m(str2)) {
                    str3 = "false";
                    break;
                }
                break;
            case 1:
                return c2;
            case 2:
                str3 = "1";
                if (!"1".equals(c2) && !j(str2)) {
                    str3 = "0";
                    break;
                }
                break;
            case 3:
                str3 = "yes";
                if (!"yes".equals(c2) && !m(str2)) {
                    str3 = "no";
                    break;
                }
                break;
            default:
                LogUtil.a("DeviceUtils", "getProductInfoExtraData extraKey is invalids");
                return "";
        }
        return str3;
    }

    public static void UW_(final Context context, final TextView textView) {
        final int[] iArr = {15};
        for (int i = 0; i < 6; i++) {
            textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: dij.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (textView.getLineCount() > 1) {
                        Context context2 = context;
                        if (context2 == null) {
                            LogUtil.a("DeviceUtils", "adapterTextFonts, context is null!");
                            textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            return;
                        }
                        int width = context2.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager ? ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getWidth() : 0;
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        if (width == layoutParams.width) {
                            int[] iArr2 = iArr;
                            int i2 = iArr2[0] - 1;
                            iArr2[0] = i2;
                            textView.setTextSize(1, i2);
                            return;
                        }
                        layoutParams.width = width - dij.d(context, 64.0f);
                        textView.setLayoutParams(layoutParams);
                    }
                }
            });
        }
    }

    public static ProductMapInfo a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map<String, ProductMapInfo> map = f11675a;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b = ProductMap.b("productId", str);
        if (!koq.c(b)) {
            return null;
        }
        ProductMapInfo productMapInfo = b.get(0);
        map.put(str, productMapInfo);
        return productMapInfo;
    }

    public static String e(String str) {
        ProductMapInfo a2 = a(str);
        if (a2 != null) {
            LogUtil.c("DeviceUtils", "prodId = ", a2.f());
            if (!TextUtils.isEmpty(a2.f())) {
                return a2.f();
            }
        }
        return "";
    }

    public static void a(Context context, String str, String str2) {
        String j = dds.c().j();
        String d = dds.c().d();
        if (TextUtils.isEmpty(j)) {
            LogUtil.a("DeviceUtils", "productId is null");
            return;
        }
        dcz d2 = ResourceManager.e().d(j);
        if (d2 == null) {
            LogUtil.a("DeviceUtils", "productInfo is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", j);
        contentValues.put("uniqueId", d);
        contentValues.put("name", d2.n().b());
        contentValues.put("deviceType", d2.l().name());
        contentValues.put(BleConstants.KEY_BR_MAC, str);
        new BleJsInteractionCompact().startH5Pro(context, "com.huawei.health.device." + j, contentValues, str2);
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains("proto=2")) {
            return true;
        }
        return str.contains("ftmp=1");
    }

    public static boolean Vb_(ContentValues contentValues) {
        if (contentValues == null) {
            LogUtil.h("DeviceUtils", "deviceInfo is null");
            return false;
        }
        return "0".equals(contentValues.getAsString(EventMonitorRecord.ADD_TIME));
    }

    public static boolean h(String str) {
        LogUtil.a("DeviceUtils", "isIntelligentDevice");
        return !TextUtils.isEmpty(str) && mst.a().d(str);
    }
}
