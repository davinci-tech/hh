package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.text.TextUtils;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorItem;
import defpackage.dcz;
import health.compact.a.HuaweiHealth;
import health.compact.a.Services;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dja {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11681a;
    private static final String e;

    static {
        String d = drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", (String) null);
        f11681a = d;
        e = d + File.separator;
    }

    public static String c(ArrayList<dcz.d> arrayList, String str, int i) {
        if (koq.d(arrayList, i)) {
            return dcx.d(str, arrayList.get(i).c());
        }
        LogUtil.h("PluginDevice_PluginDeviceUtils", "getZipText index is out of bounds");
        return "";
    }

    public static Bitmap VF_(ArrayList<dcz.d> arrayList, String str, int i) {
        if (koq.d(arrayList, i)) {
            return dcx.TK_(dcq.b().a(str, arrayList.get(i).e()));
        }
        LogUtil.h("PluginDevice_PluginDeviceUtils", "getZipImage index is out of bounds");
        return null;
    }

    public static boolean VG_(Activity activity) {
        boolean z;
        boolean z2;
        if (activity != null) {
            Object systemService = BaseApplication.getContext().getSystemService("location");
            if (systemService instanceof LocationManager) {
                LocationManager locationManager = (LocationManager) systemService;
                z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
                LogUtil.a("PluginDevice_PluginDeviceUtils", "isGPSLocationEnable：" + z2);
                z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
                LogUtil.a("PluginDevice_PluginDeviceUtils", "isNetWorkLocationEnable：" + z);
                return z2 || z;
            }
        }
        z = true;
        z2 = true;
        if (z2) {
            return true;
        }
    }

    public static boolean c() {
        BluetoothAdapter adapter;
        Object systemService = HuaweiHealth.a().getSystemService("bluetooth");
        if (systemService == null || !(systemService instanceof BluetoothManager) || (adapter = ((BluetoothManager) systemService).getAdapter()) == null) {
            return false;
        }
        return adapter.isEnabled();
    }

    public static void VY_(Activity activity, boolean z, int i) {
        if (z) {
            activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), i);
        } else {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "skipSettingView HagridWiFiInfoConfirmFragment is not attached");
        }
    }

    public static void d(Fragment fragment, boolean z, int i) {
        if (z) {
            fragment.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), i);
        } else {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "skipSettingView HagridWiFiInfoConfirmFragment is not attached");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String a(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1744977625:
                if (str.equals("speeding_mode")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1623402023:
                if (str.equals("enhanced_fat_burning_jump_mode")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -750159515:
                if (str.equals("easy_fat_burning_jump_mode")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2120033999:
                if (str.equals("deep_fat_burning_jump_mode")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return e + "pic_speeding.webp";
        }
        if (c == 1) {
            return e + "pic_enhanced_fat_burning.webp";
        }
        if (c == 2) {
            return e + "pic_easy_fat_burning.webp";
        }
        if (c != 3) {
            return "";
        }
        return e + "pic_deep_fat_burning.webp";
    }

    public static void c(ActivityResultLauncher activityResultLauncher, String str) {
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(new Intent(str));
        }
    }

    public static void b(final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            return;
        }
        LogUtil.a("PluginDevice_PluginDeviceUtils", "checkWearStatus: start");
        ThreadPoolManager.d().execute(new Runnable() { // from class: djj
            @Override // java.lang.Runnable
            public final void run() {
                dja.e(ResponseCallback.this);
            }
        });
    }

    static /* synthetic */ void e(final ResponseCallback responseCallback) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "PluginDevice_PluginDeviceUtils");
        if (deviceList == null || deviceList.isEmpty()) {
            responseCallback.onResponse(-2, false);
            return;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceConnectState() == 2) {
                deviceInfo = next;
                break;
            }
        }
        if (deviceInfo == null) {
            responseCallback.onResponse(-2, false);
            return;
        }
        LogUtil.a("PluginDevice_PluginDeviceUtils", "checkWearStatus: deviceInfo = ", deviceInfo);
        if (!cux.e(deviceInfo.getProductType()) && !cus.e().d(deviceInfo.getProductType())) {
            responseCallback.onResponse(-3, false);
            return;
        }
        if (d(deviceInfo.getDeviceIdentify())) {
            responseCallback.onResponse(-4, false);
            return;
        }
        tph a2 = tnu.a(BaseApplication.getContext());
        Device d = tqy.d(deviceInfo);
        LogUtil.a("PluginDevice_PluginDeviceUtils", "checkWearStatus: query wear status start");
        a2.c(d, MonitorItem.MONITOR_ITEM_WEAR).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener() { // from class: dje
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                dja.c(ResponseCallback.this, (MonitorData) obj);
            }
        }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: djh
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                dja.c(ResponseCallback.this, exc);
            }
        });
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, MonitorData monitorData) {
        int asInt = monitorData.asInt();
        LogUtil.a("PluginDevice_PluginDeviceUtils", "checkWearStatus: wearStatus = ", Integer.valueOf(asInt));
        if (asInt == 2) {
            responseCallback.onResponse(asInt, false);
        } else {
            responseCallback.onResponse(asInt, true);
        }
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, Exception exc) {
        LogUtil.b("PluginDevice_PluginDeviceUtils", "checkWearStatus: call query api for wearStatus exception: ", exc);
        responseCallback.onResponse(-1, false);
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).isDeviceOtaUpdating(str);
    }

    public static void VT_(final Activity activity, final int i) {
        if (activity == null) {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNoConnectedDeviceDialog: activity is null");
        } else {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNoConnectedDeviceDialog");
            activity.runOnUiThread(new Runnable() { // from class: dji
                @Override // java.lang.Runnable
                public final void run() {
                    dja.VJ_(activity, i);
                }
            });
        }
    }

    static /* synthetic */ void VJ_(final Activity activity, int i) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string.IDS_no_connected_device_title);
        if (i == 265) {
            builder.d(R.string.IDS_no_connected_device_content_indoorbike);
        } else {
            builder.d(R.string.IDS_no_connected_device_content_treadmill);
        }
        builder.cyU_(R.string.IDS_device_go_to_pair, new View.OnClickListener() { // from class: djm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VH_(activity, view);
            }
        });
        builder.cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: djk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VI_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void VH_(Activity activity, View view) {
        diy.c(activity, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void VI_(View view) {
        LogUtil.a("PluginDevice_PluginDeviceUtils", "showNoConnectedDeviceDialog: click cancel btn");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void VU_(final Activity activity, final int i) {
        if (activity == null) {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotSupportDialog: activity is null");
        } else {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotSupportDialog");
            activity.runOnUiThread(new Runnable() { // from class: djc
                @Override // java.lang.Runnable
                public final void run() {
                    dja.VM_(activity, i);
                }
            });
        }
    }

    static /* synthetic */ void VM_(final Activity activity, int i) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string._2130850294_res_0x7f0231f6);
        if (i == 265) {
            builder.d(R.string._2130850292_res_0x7f0231f4);
        } else {
            builder.d(R.string._2130850293_res_0x7f0231f5);
        }
        builder.cyU_(R.string.IDS_device_go_to_pair, new View.OnClickListener() { // from class: djb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VK_(activity, view);
            }
        });
        builder.cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: djg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VL_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void VK_(Activity activity, View view) {
        diy.c(activity, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void VL_(View view) {
        LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotSupportDialog: click cancel btn");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void VV_(final Activity activity) {
        if (activity == null) {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotWearDialog: activity is null");
        } else {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotWearDialog");
            activity.runOnUiThread(new Runnable() { // from class: djn
                @Override // java.lang.Runnable
                public final void run() {
                    dja.VN_(activity);
                }
            });
        }
    }

    static /* synthetic */ void VN_(Activity activity) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string._2130850298_res_0x7f0231fa);
        builder.d(R.string._2130850297_res_0x7f0231f9);
        builder.cyU_(R.string.IDS_device_know, new View.OnClickListener() { // from class: djl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VO_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void VO_(View view) {
        LogUtil.a("PluginDevice_PluginDeviceUtils", "showNotWearDialog: click positive btn");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void VX_(final Activity activity) {
        if (activity == null) {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showQueryTimeoutDialog: activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: djp
                @Override // java.lang.Runnable
                public final void run() {
                    dja.VS_(activity);
                }
            });
        }
    }

    static /* synthetic */ void VS_(Activity activity) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string._2130850298_res_0x7f0231fa);
        builder.d(R.string._2130850361_res_0x7f023239);
        builder.cyU_(R.string.IDS_device_know, new View.OnClickListener() { // from class: djq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VR_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void VR_(View view) {
        LogUtil.a("PluginDevice_PluginDeviceUtils", "showQueryTimeoutDialog: click positive btn");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void VW_(final Activity activity) {
        if (activity == null) {
            LogUtil.a("PluginDevice_PluginDeviceUtils", "showOtaUpdatingDialog: activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: djd
                @Override // java.lang.Runnable
                public final void run() {
                    dja.VQ_(activity);
                }
            });
        }
    }

    static /* synthetic */ void VQ_(Activity activity) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R.string._2130850298_res_0x7f0231fa);
        builder.d(R.string.IDS_wear_device_ota_updating);
        builder.cyU_(R.string.IDS_device_know, new View.OnClickListener() { // from class: djf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dja.VP_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void VP_(View view) {
        LogUtil.a("PluginDevice_PluginDeviceUtils", "showOtaUpdatingDialog: click positive btn");
        ViewClickInstrumentation.clickOnView(view);
    }
}
