package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes3.dex */
public class cpl {
    private static volatile cpl c;
    private String d;
    private static final byte[] b = new byte[1];
    private static final Set<String> e = new HashSet();
    private int g = -1;

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f11401a = new BroadcastReceiver() { // from class: cpl.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            if (!"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || (extras = intent.getExtras()) == null) {
                return;
            }
            int i = extras.getInt("BATTERY_LEVEL");
            DeviceInfo d = cpl.this.d();
            if (d == null || d.getDeviceConnectState() != 2) {
                return;
            }
            LogUtil.a("PluginDevice_DeviceInfoUtils", "has connected device battery:" + i);
            cpl.this.g = i;
        }
    };

    private static int h(int i) {
        if (i == 0) {
            return 1;
        }
        if (1 == i) {
            return 0;
        }
        return i;
    }

    private cpl() {
        if (CommonUtil.ce()) {
            LogUtil.a("PluginDevice_DeviceInfoUtils", "DeviceInfoUtils init");
            m();
        }
    }

    public static cpl c() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new cpl();
                }
            }
        }
        return c;
    }

    public void f(int i) {
        this.g = i;
    }

    public int c(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        if (deviceInfoUx != null) {
            return deviceInfoUx.d();
        }
        return -1;
    }

    public List<DeviceInfo> h() {
        return cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "PluginDevice_DeviceInfoUtils");
    }

    public DeviceInfo d() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "PluginDevice_DeviceInfoUtils");
    }

    public List<DeviceInfo> e() {
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "PluginDevice_DeviceInfoUtils");
        if (deviceList != null) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (1 == deviceInfo.getDeviceActiveState()) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<cpm> j() {
        ArrayList<cpm> arrayList = new ArrayList<>(16);
        List<DeviceInfo> h = h();
        if (h != null) {
            for (DeviceInfo deviceInfo : h) {
                if (deviceInfo != null) {
                    int d = d(deviceInfo.getProductType());
                    int e2 = e(deviceInfo.getProductType());
                    cpm cpmVar = new cpm();
                    cpmVar.a(deviceInfo, d, e2);
                    cpmVar.d(deviceInfo.getProductType());
                    cpmVar.c(deviceInfo.getSecurityUuid() + "#ANDROID21");
                    cpmVar.d(deviceInfo.getLastConnectedTime());
                    if (deviceInfo.getDeviceName() == null && TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                        if ((!TextUtils.isEmpty(deviceInfo.getDeviceName()) && TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN")) || (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
                            cpmVar.e("PORSCHE DESIGN");
                        } else {
                            cpmVar.e(i(deviceInfo.getProductType()));
                            LogUtil.a("PluginDevice_DeviceInfoUtils", "device name is null ,device name :", i(deviceInfo.getProductType()));
                        }
                    } else if ((!TextUtils.isEmpty(deviceInfo.getDeviceName()) && TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN")) || (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
                        cpmVar.e("PORSCHE DESIGN");
                    } else {
                        cpmVar.e(deviceInfo.getDeviceName());
                    }
                    arrayList.add(cpmVar);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<cpm> a(List<String> list) {
        ArrayList<cpm> arrayList = new ArrayList<>(16);
        List<DeviceInfo> h = h();
        if (h != null) {
            for (DeviceInfo deviceInfo : h) {
                if (deviceInfo != null) {
                    int productType = deviceInfo.getProductType();
                    if (cux.e(productType) || cus.e().d(productType)) {
                        if (list == null || list.size() <= 0 || !list.contains(deviceInfo.getUuid())) {
                            int d = d(productType);
                            int e2 = e(productType);
                            cpm cpmVar = new cpm();
                            cpmVar.a(deviceInfo, d, e2);
                            cpmVar.d(deviceInfo.getProductType());
                            d(deviceInfo, cpmVar);
                            arrayList.add(cpmVar);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private void d(DeviceInfo deviceInfo, cpm cpmVar) {
        if (deviceInfo.getDeviceName() == null && TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            if ((!TextUtils.isEmpty(deviceInfo.getDeviceName()) && TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN")) || (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
                cpmVar.e("PORSCHE DESIGN");
                return;
            }
            DeviceInfo d = d();
            if (d != null) {
                cpmVar.e(i(d.getProductType()));
                LogUtil.a("PluginDevice_DeviceInfoUtils", "device name is null ,device name :", i(d.getProductType()));
                return;
            }
            return;
        }
        if ((!TextUtils.isEmpty(deviceInfo.getDeviceName()) && TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN")) || (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
            cpmVar.e("PORSCHE DESIGN");
        } else {
            cpmVar.e(deviceInfo.getDeviceName());
        }
    }

    public int e(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        if (deviceInfoUx != null) {
            return deviceInfoUx.v();
        }
        return 0;
    }

    private String i(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        return deviceInfoUx != null ? deviceInfoUx.f() : "";
    }

    public int d(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        if (deviceInfoUx != null) {
            return deviceInfoUx.w();
        }
        return 0;
    }

    public boolean b(String str) {
        Iterator<DeviceInfo> it = e().iterator();
        while (it.hasNext()) {
            if (it.next().getDeviceIdentify().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public void f() {
        ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).resetBandUpdate("");
    }

    public boolean j(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        if (deviceInfoUx == null) {
            return false;
        }
        LogUtil.a("PluginDevice_DeviceInfoUtils", "device type is existence");
        if ("".equals(deviceInfoUx.ad())) {
            return false;
        }
        LogUtil.a("PluginDevice_DeviceInfoUtils", "uuid is not null");
        return true;
    }

    public String a(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        return deviceInfoUx != null ? deviceInfoUx.ad() : "";
    }

    public boolean g(int i) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "PluginDevice_DeviceInfoUtils");
        if (deviceInfoUx != null) {
            return deviceInfoUx.s();
        }
        return false;
    }

    private void m() {
        LogUtil.a("PluginDevice_DeviceInfoUtils", "enter registerBattery");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.f11401a, intentFilter, LocalBroadcast.c, null);
    }

    public View Kj_(int i) {
        String format;
        String str;
        String str2 = "";
        if (i == 7) {
            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_press_restart4), 5);
        } else if (i == 8) {
            format = BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_press_restart5);
        } else {
            if (i != 16) {
                if (i != 75) {
                    switch (i) {
                        case 18:
                        case 19:
                            format = BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_press_restart2);
                            break;
                        case 20:
                        case 21:
                            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_press_restart1), 5);
                            break;
                        default:
                            str = "";
                            break;
                    }
                } else {
                    str2 = BaseApplication.getContext().getString(R.string.IDS_bolt_awaken_device);
                    str = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string.IDS_bolt_awaken_device_second), 3);
                }
                return Ki_(str2, str, i);
            }
            format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_press_restart3), 5);
        }
        str2 = format;
        str = "";
        return Ki_(str2, str, i);
    }

    private View Ki_(String str, String str2, int i) {
        LogUtil.a("PluginDevice_DeviceInfoUtils", "enter showAlertDialog");
        String string = BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
        View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.show_notconnect_dialog_view, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.dialog_tite)).setText(string);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_content);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.dialog_content1);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.dialog_content2);
        HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.dialog_content3);
        HealthTextView healthTextView5 = (HealthTextView) inflate.findViewById(R.id.dialog_content4);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.third_tip_layout);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.forth_tip_layout);
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(8);
        String string2 = BaseApplication.getContext().getString(nsn.ae(cpp.a()) ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth);
        String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string._2130845964_res_0x7f02210c), 3);
        String format2 = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string._2130846464_res_0x7f022300), new Object[0]);
        if (!CommonUtil.bh()) {
            linearLayout2.setVisibility(0);
            healthTextView5.setText(format2);
        }
        if (i == 80) {
            linearLayout.setVisibility(0);
            healthTextView.setText(string2);
            healthTextView2.setText(BaseApplication.getContext().getString(R.string._2130846330_res_0x7f02227a));
            healthTextView3.setText(BaseApplication.getContext().getString(R.string._2130846331_res_0x7f02227b));
            healthTextView4.setText(format);
            return inflate;
        }
        if (StringUtils.g(str)) {
            healthTextView.setText(string2);
            healthTextView2.setText(format);
            ((LinearLayout) inflate.findViewById(R.id.liner2)).setVisibility(8);
        } else if (StringUtils.g(str2)) {
            healthTextView.setText(string2);
            healthTextView2.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R.string.IDS_device_mgr_pair_note_restart_device), str));
            healthTextView3.setText(format);
        } else {
            healthTextView.setText(str);
            healthTextView2.setText(string2);
            healthTextView3.setText(str2);
        }
        return inflate;
    }

    public void g() {
        cun.c().sendDeviceData(1, 33, null, null, "PluginDevice_DeviceInfoUtils");
    }

    public UniteDevice d(String str) {
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setDeviceMac(str);
        return new UniteDevice().build(str, deviceInfo, new ExternalDeviceCapability());
    }

    public boolean i() {
        boolean parseBoolean = Boolean.parseBoolean((TextUtils.equals("578d0675-cece-4426-bf28-43ce31eb7b5d", this.d) || TextUtils.equals("f2da0f49-aefb-4713-87a2-9fb89b491dac", this.d)) ? KeyValDbManager.b(BaseApplication.getContext()).e("use_unite_device_service_key") : "");
        Object[] objArr = new Object[1];
        objArr[0] = parseBoolean ? "isSupportUdsByProductId() now is new mode" : "isSupportUdsByProductId() now is old mode";
        LogUtil.a("PluginDevice_DeviceInfoUtils", objArr);
        return parseBoolean;
    }

    public void f(String str) {
        this.d = str;
    }

    public UniteDevice b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UniteDevice uniteDevice = new UniteDevice();
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setDeviceMac(str);
        deviceInfo.setDeviceBtType(i);
        return uniteDevice.build(str, deviceInfo, new ExternalDeviceCapability());
    }

    public void e(String str, String str2, String str3, boolean z) {
        UniteDevice b2 = c().b(str, 2);
        if (b2 != null) {
            ddw.c().b(b2, str2, str3, z);
        } else {
            LogUtil.h("PluginDevice_DeviceInfoUtils", "setCharacteristicNotifyByUds uniteDevice is null");
        }
    }

    public void a(biu biuVar, String str, String str2, String str3, CharacterOperationType characterOperationType) {
        if (biuVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h("PluginDevice_DeviceInfoUtils", "data is null || serviceUuid == null || characterUuid == null || mac == null");
            return;
        }
        synchronized (b) {
            UniteDevice b2 = c().b(str3, 2);
            if (b2 != null) {
                ddw.c().e(b2, ddw.c().c(biuVar, str, str2, characterOperationType));
                LogUtil.a("PluginDevice_DeviceInfoUtils", "setDateTime : data frames:", cvx.d(biuVar.a()));
            } else {
                LogUtil.h("PluginDevice_DeviceInfoUtils", "uniteDevice is null");
            }
        }
    }

    public <T> void a(String str, T t) {
        LogUtil.a("PluginDevice_DeviceInfoUtils", "enter putUdsClassMap");
        if (t instanceof ConnectStatusCallback) {
            cwq.e(str, (ConnectStatusCallback) t);
            LogUtil.a("PluginDevice_DeviceInfoUtils", "putUdsClassMap StatusChange size：", Integer.valueOf(cwq.e()));
        }
        if (t instanceof DataChangedCallback) {
            cwm.b(str, (DataChangedCallback) t);
            LogUtil.a("PluginDevice_DeviceInfoUtils", "putUdsClassMap MessageReceive size：", Integer.valueOf(cwm.d()));
        }
    }

    public void a() {
        LogUtil.a("PluginDevice_DeviceInfoUtils", "clearUdsClassMap");
        cwm.b();
        cwq.d();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r4, java.lang.String r5, com.huawei.hihealth.HiUserInfo r6) {
        /*
            boolean r0 = defpackage.cpa.ae(r4)
            r1 = 0
            if (r0 != 0) goto Ld
            boolean r0 = defpackage.cpa.au(r4)
            if (r0 == 0) goto L34
        Ld:
            ctk r4 = defpackage.ctq.e(r4)
            ceo r0 = defpackage.ceo.d()
            com.huawei.health.device.connectivity.comm.MeasurableDevice r0 = r0.d(r5, r1)
            if (r4 != 0) goto L2d
            if (r0 != 0) goto L2d
            java.lang.String r4 = "wiFiDevice is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "PluginDevice_DeviceInfoUtils"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r4)
            defpackage.dif.b()
            return
        L2d:
            if (r4 == 0) goto L34
            java.lang.String r4 = r4.d()
            goto L36
        L34:
            java.lang.String r4 = ""
        L36:
            java.util.HashMap r0 = new java.util.HashMap
            r2 = 16
            r0.<init>(r2)
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.login.ui.login.LoginInit r2 = com.huawei.login.ui.login.LoginInit.getInstance(r2)
            r3 = 1011(0x3f3, float:1.417E-42)
            java.lang.String r2 = r2.getAccountInfo(r3)
            java.lang.String r3 = "id"
            r0.put(r3, r2)
            java.lang.String r2 = "uid"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            r0.put(r2, r3)
            int r2 = r6.getGender()
            r3 = 2
            if (r2 != r3) goto L63
            r2 = 1
            goto L67
        L63:
            int r2 = r6.getGender()
        L67:
            java.lang.String r3 = "gender"
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.put(r3, r2)
            int r2 = r6.getBirthday()
            int r3 = r6.getAge()
            int r2 = defpackage.cgs.e(r2, r3)
            java.lang.String r3 = "age"
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.put(r3, r2)
            int r2 = r6.getHeight()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "height"
            r0.put(r3, r2)
            java.lang.String r2 = "isDelete"
            java.lang.String r3 = java.lang.String.valueOf(r1)
            r0.put(r2, r3)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r1 = defpackage.csf.e(r4, r1)
            if (r1 == 0) goto Lb4
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto Lb4
            java.lang.String r1 = defpackage.csf.d(r1)
            java.lang.String r2 = "bodyRes"
            r0.put(r2, r1)
        Lb4:
            float r1 = r6.getWeight()
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 == 0) goto Ld4
            float r1 = r6.getWeight()
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto Ld4
            float r1 = r6.getWeight()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "currentWeight"
            r0.put(r2, r1)
        Ld4:
            boolean r1 = defpackage.cgs.d(r5)
            if (r1 == 0) goto Leb
            int r6 = r6.getBirthday()
            int r6 = defpackage.cgs.a(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r1 = "month"
            r0.put(r1, r6)
        Leb:
            java.lang.String r6 = defpackage.cts.b
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.csf.a(r0, r4, r6, r1)
            d(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cpl.a(java.lang.String, java.lang.String, com.huawei.hihealth.HiUserInfo):void");
    }

    private static void d(String str, String str2) {
        if (!Utils.l()) {
            List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
            for (int i = 1; i < allUser.size(); i++) {
                HashMap hashMap = new HashMap(16);
                cfi cfiVar = allUser.get(i);
                hashMap.put("id", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
                hashMap.put("uid", cfiVar.i());
                hashMap.put(CommonConstant.KEY_GENDER, String.valueOf((int) (cfiVar.c() == 2 ? (byte) 1 : cfiVar.c())));
                hashMap.put("age", String.valueOf(cgs.e(cfiVar.g(), cfiVar.a())));
                hashMap.put("height", String.valueOf(cfiVar.d()));
                hashMap.put("isDelete", String.valueOf(0));
                String e2 = csf.e(str, cfiVar.i());
                if (e2 != null && !TextUtils.isEmpty(e2)) {
                    hashMap.put("bodyRes", csf.d(e2));
                }
                if (cfiVar.l() != 0.0f && !Float.isNaN(cfiVar.l())) {
                    hashMap.put("currentWeight", String.valueOf(cfiVar.l()));
                }
                if (cgs.d(str2)) {
                    hashMap.put("month", String.valueOf(cgs.a(cfiVar.g())));
                }
                csf.a(hashMap, str, cfiVar.j(), BaseApplication.getContext());
            }
            return;
        }
        LogUtil.a("PluginDevice_DeviceInfoUtils", "Oversea is no child user");
    }

    public static void e(int i, HiUserInfo hiUserInfo) {
        LogUtil.c("PluginDevice_DeviceInfoUtils", "gender==", Integer.valueOf(i));
        if (hiUserInfo != null) {
            hiUserInfo.setGender(i);
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "hw_health_gender_value", Integer.toString(h(i)), new StorageParams(1));
        b(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "3");
        String i2 = MultiUsersManager.INSTANCE.getMainUser().i();
        if (TextUtils.isEmpty(i2) || !i2.equals(MultiUsersManager.INSTANCE.getCurrentUser().i())) {
            return;
        }
        int i3 = i == 0 ? 0 : 1;
        LogUtil.c("PluginDevice_DeviceInfoUtils", "setUserGender reset user gender gender = ", Integer.valueOf(i3));
        MultiUsersManager.INSTANCE.getMainUser().a((byte) i3);
    }

    private static void b(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    public static boolean a(String str, boolean z) {
        LogUtil.a("PluginDevice_DeviceInfoUtils", "showConfirmUserInfo() enter");
        if (ctq.e(str) != null || z || cei.b().isMiniScaleDevice(str) || cpa.ab(str)) {
            return false;
        }
        dif.b();
        LogUtil.b("PluginDevice_DeviceInfoUtils", "showConfirmUserInfo wifi device is null");
        return true;
    }

    public static String e(String str, String str2) {
        if (cjx.e().h(str)) {
            HealthDevice c2 = cjx.e().c(str, str2);
            if (c2 == null) {
                return "";
            }
            LogUtil.c("PluginDevice_DeviceInfoUtils", "isBondedDevice: device = ", iyl.d().e(c2.getAddress()));
            return c2.getAddress();
        }
        com.huawei.health.device.model.HealthDevice c3 = cjx.e().c(str2);
        if (c3 == null) {
            return "";
        }
        LogUtil.c("PluginDevice_DeviceInfoUtils", "isBondedDevice: device = ", iyl.d().e(c3.getAddress()));
        return c3.getAddress();
    }

    public static boolean Kh_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("PluginDevice_DeviceInfoUtils", "isDualModelDevice bundle is null");
            return false;
        }
        String string = bundle.getString("devId");
        if (!TextUtils.isEmpty(string)) {
            return csf.g(string);
        }
        String string2 = bundle.getString("productId");
        if (TextUtils.isEmpty(string2)) {
            return false;
        }
        LogUtil.a("PluginDevice_DeviceInfoUtils", "isDualModelDevice productId: ", string2);
        return cpa.x(string2);
    }

    public static boolean b() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains("nubia");
    }

    public static void e(String str) {
        e.add(str);
    }

    public static void a(String str) {
        e.remove(str);
    }

    public static boolean c(String str) {
        return !e.contains(str);
    }
}
