package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class jus {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14100a = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
    private Map<String, Integer> b;
    private boolean c;
    private DeviceInfo d;
    private Map<String, Integer> e;

    private jus() {
        this.e = new HashMap();
        this.b = new HashMap();
        this.d = null;
        this.c = false;
        a();
        d();
    }

    public static final jus e() {
        return a.b;
    }

    private void a() {
        this.b.put("000002", 16);
        this.b.put("000108", 34);
        this.b.put("000109", 58);
        this.b.put("00M004", 34);
        this.b.put("00M008", 34);
        this.b.put("00N004", 65);
        this.b.put("00005W", 34);
        this.b.put("00005X", 34);
        this.b.put("00005Y", 35);
        this.b.put("00005Z", 35);
        this.b.put("00M00E", 66);
        this.b.put("00M0A2", 72);
        this.b.put("00M0A3", 72);
        this.b.put("00M0A4", 72);
        this.b.put("00M0A5", 72);
        this.b.put("00M0AS", 82);
        this.b.put("00M0AN", 72);
    }

    private void d() {
        this.e.put("00000A", 20);
        this.e.put("00000B", 21);
        this.e.put("00000C", 18);
        this.e.put("00000D", 19);
        this.e.put("00010A", 60);
        this.e.put("00010B", 61);
        this.e.put("00N006", 64);
        this.e.put("00M0A0", 71);
        this.e.put("00M0A1", 71);
        this.e.put("00M0A9", 71);
        this.e.put("00M0AD", 71);
        this.e.put("00M0AE", 71);
        this.e.put("00M0AF", 61);
        this.e.put("00M0AG", 61);
        this.e.put("00M0A7", 73);
        this.e.put("00M0A8", 73);
        this.e.put("00M0B2", 75);
    }

    public void b(String str, String str2, String str3) {
        int i;
        int i2;
        int intValue;
        int i3;
        if (this.b.get(str) != null) {
            intValue = this.b.get(str).intValue();
            i3 = 1;
        } else if (this.e.get(str) != null) {
            intValue = this.e.get(str).intValue();
            i3 = 2;
        } else {
            i = -1;
            i2 = -1;
            if (Build.VERSION.SDK_INT > 30 && !PermissionUtil.e(BaseApplication.getContext(), f14100a)) {
                sqo.ac("connectDeviceDirectly no permission.");
                c();
            }
            a(str, str2, str3, i, i2);
        }
        i = intValue;
        i2 = i3;
        if (Build.VERSION.SDK_INT > 30) {
            sqo.ac("connectDeviceDirectly no permission.");
            c();
        }
        a(str, str2, str3, i, i2);
    }

    private void c() {
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_device_reconnect_remind);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentText(string);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setStyle(new Notification.BigTextStyle().bigText(string));
        xf_.setContentIntent(bJA_(BaseApplication.getContext()));
        xf_.setAutoCancel(true);
        jdh.d().xh_(10001, xf_.build());
    }

    private PendingIntent bJA_(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(536870912);
        intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        return PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, AppRouterExtras.COLDSTART);
    }

    private void a(final String str, final String str2, final String str3, final int i, final int i2) {
        final String c = iyg.c(str3);
        final int d = d(c);
        LogUtil.a("AutoDiscoverManager", "continueConnectDeviceDirectly mDeviceName :", CommonUtil.l(str2), " ,mProductType :", Integer.valueOf(i), " ,mBluetoothType :", Integer.valueOf(i2), ", deviceIdentify :", CommonUtil.l(c), " ,connectState :", Integer.valueOf(d));
        if (b(str2, c) && this.c) {
            LogUtil.h("AutoDiscoverManager", "continueConnectDeviceDirectly stop retry.");
            this.c = false;
        } else if (!b(i)) {
            this.c = true;
            d(str2, str, str3, i);
        } else if (!Utils.i()) {
            LogUtil.a("AutoDiscoverManager", "continueConnectDeviceDirectly no need login.");
            c(d, str2, c, i, i2);
        } else {
            HiHealthManager.d(BaseApplication.getContext()).fetchAccountInfo(new HiCommonListener() { // from class: jus.2
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i3, Object obj) {
                    if (!TextUtils.isEmpty(obj instanceof HiAccountInfo ? ((HiAccountInfo) obj).getHuid() : "")) {
                        jus.this.c(d, str2, c, i, i2);
                        return;
                    }
                    LogUtil.h("AutoDiscoverManager", "AccountLoginReceiver userId is empty.");
                    jus.this.c = true;
                    jus.this.d(str2, str, str3, i);
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i3, Object obj) {
                    sqo.ac("continueConnectDeviceDirectly get account info fail.");
                    LogUtil.h("AutoDiscoverManager", "get account info fail.");
                    jus.this.c(d, str2, c, i, i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str, String str2, int i2, int i3) {
        if (i == 1 || i == 2) {
            LogUtil.h("AutoDiscoverManager", "handleDirectConnectDevice device connecting or connected.");
            return;
        }
        if (!jsn.d(i2)) {
            jsu.c().d(true);
            return;
        }
        bmw.e(100009, str, "", "");
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setDeviceBtType(i3);
        deviceInfo.setDeviceName(str);
        deviceInfo.setDeviceMac(str2);
        deviceInfo.setDeviceType(i2);
        jsz.b(BaseApplication.getContext()).connectDevice(deviceInfo, 0);
    }

    private int d(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "AutoDiscoverManager");
        if (bkz.e(deviceList)) {
            LogUtil.h("AutoDiscoverManager", "getCurrentDeviceConnectState deviceInfoList is empty.");
            return 0;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                return deviceInfo.getDeviceConnectState();
            }
        }
        return 0;
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("AutoDiscoverManager", "isDeviceReconnect deviceName or deviceIdentify is empty.");
            return false;
        }
        DeviceInfo deviceInfo = this.d;
        if (deviceInfo != null && str.equalsIgnoreCase(deviceInfo.getDeviceName()) && str2.equalsIgnoreCase(this.d.getDeviceIdentify())) {
            long currentTimeMillis = System.currentTimeMillis() - this.d.getLastConnectedTime();
            if (currentTimeMillis > 15000 && currentTimeMillis < 25000) {
                LogUtil.a("AutoDiscoverManager", "isDeviceReconnect is retry.");
                return true;
            }
        }
        LogUtil.a("AutoDiscoverManager", "isDeviceReconnect is not retry.");
        this.d = null;
        DeviceInfo deviceInfo2 = new DeviceInfo();
        this.d = deviceInfo2;
        deviceInfo2.setDeviceName(str);
        this.d.setDeviceIdentify(str2);
        this.d.setLastConnectedTime(System.currentTimeMillis());
        return false;
    }

    public boolean b(int i) {
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.h("AutoDiscoverManager", "isDirectConnect user no agree privacy.");
            return false;
        }
        if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(juu.d(i))) {
            return true;
        }
        LogUtil.h("AutoDiscoverManager", "isDirectConnect no device resource.");
        return false;
    }

    public void d(String str, String str2, String str3, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "Enter openHealthApp param is ";
        objArr[1] = Boolean.valueOf(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3));
        LogUtil.a("AutoDiscoverManager", objArr);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/oneKeyDirect?device=supportNearDiscovery&destination=nearDiscovery"));
        intent.setAction("com.huawei.health.action.ONE_KEY_DIRECT");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.putExtra("DEVICE_NAME", str);
        intent.putExtra("DEVICE_ID", str3);
        intent.putExtra("DEVICE_PRODUCT_TYPE", i);
        intent.putExtra("DEVICE_MODULE_ID", str2);
        intent.putExtra("IS_FROM_PHONE_SERVICE", true);
        intent.addFlags(268435456);
        try {
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a("startJumpActivity not find JumpActivity", new Object[0]);
        }
    }

    /* loaded from: classes5.dex */
    static class a {
        private static final jus b = new jus();
    }
}
