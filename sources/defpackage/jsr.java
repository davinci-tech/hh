package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
class jsr {

    /* renamed from: a, reason: collision with root package name */
    private static jsr f14055a;
    private static final Object d = new Object();
    private static jsx e;
    private Context b = BaseApplication.getContext();
    private izy c;
    private kig f;

    private boolean d(int i, int i2, int i3, int i4) {
        return i == i3 && i2 == i4;
    }

    private jsr() {
        this.c = null;
        LogUtil.h("HwDeviceHandleBtStateMg", "Init HwDeviceDataReceiveMgr.");
        this.c = izy.b(this.b);
        this.f = new kig();
        e = jsx.c();
    }

    public static jsr c() {
        jsr jsrVar;
        synchronized (d) {
            if (f14055a == null) {
                f14055a = new jsr();
            }
            jsrVar = f14055a;
        }
        return jsrVar;
    }

    public void d(DeviceInfo deviceInfo, int i, byte[] bArr) {
        boolean k = jst.k(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwDeviceHandleBtStateMg", "Enter onDataReceived() with handShakeStatus:", Boolean.valueOf(k));
        byte b = bArr[0];
        byte b2 = bArr[1];
        if (b == 1 && b2 == 52) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Enter onDataReceived() key lost 5.1.52:");
        }
        if (k) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Enter onDataReceived() Handshake with dataContents:", cvx.d(bArr));
            d(deviceInfo, b, b2, bArr, i);
        } else {
            a(deviceInfo, i, bArr);
        }
    }

    private void d(DeviceInfo deviceInfo, int i, int i2, byte[] bArr, int i3) {
        if (d(i, i2, 1, 7)) {
            j(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 1, 5)) {
            e.e(deviceInfo, bArr, jsz.b().d());
            return;
        }
        if (d(i, i2, 1, 2)) {
            e.c(deviceInfo, bArr, jsz.b().d());
            return;
        }
        if (d(i, i2, 1, 3)) {
            c(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 1, 18)) {
            m(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 2, 5)) {
            d(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 1, 20)) {
            e.e(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 38, 1)) {
            a(deviceInfo, bArr);
            return;
        }
        if (d(i, i2, 1, 50)) {
            h(deviceInfo, bArr);
        } else if (d(i, i2, 1, 49)) {
            f(deviceInfo, bArr);
        } else {
            c(deviceInfo, i3, bArr);
        }
    }

    private void j(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo.getDeviceProtocol() == 2) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Start to check Version Response.");
            if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
                LogUtil.a("HwDeviceHandleBtStateMg", "Get Version Command send timeout. reSendTimes is :", Integer.valueOf(iym.o()));
                if (iym.o() < 1) {
                    b(deviceInfo);
                    return;
                } else {
                    e.b(deviceInfo, bArr, jsz.b().d());
                    return;
                }
            }
            i(deviceInfo, bArr);
        }
    }

    private void b(DeviceInfo deviceInfo) {
        izf bDm_ = iyo.bDm_(this.b, null, deviceInfo, true);
        if (bDm_ == null) {
            LogUtil.h("HwDeviceHandleBtStateMg", "handleGetProductTypeCommandTimeout btDeviceCommand is null");
            return;
        }
        bDm_.e(deviceInfo.getDeviceIdentify());
        if (this.c != null) {
            iym.c(false);
            LogUtil.a("HwDeviceHandleBtStateMg", "Start to set device time.");
            this.c.c(deviceInfo.getDeviceBluetoothType(), bDm_);
        }
    }

    private void i(DeviceInfo deviceInfo, byte[] bArr) {
        try {
            iym.c(true);
            JSONObject b = iyo.b(this.b, bArr);
            int i = b.getInt("type");
            jst.d(deviceInfo.getDeviceIdentify(), i);
            if (b.has(PluginPayAdapter.KEY_DEVICE_INFO_MODEL)) {
                jst.g(deviceInfo.getDeviceIdentify(), b.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL));
            }
            if (b.has("cert_model")) {
                String string = b.getString("cert_model");
                LogUtil.a("HwDeviceHandleBtStateMg", "cert_model is ", string);
                jst.b(deviceInfo.getDeviceIdentify(), string);
            }
            int optInt = b.optInt("device_version_type", -1);
            LogUtil.a("HwDeviceHandleBtStateMg", "device version type is ", Integer.valueOf(optInt));
            jst.a(deviceInfo.getDeviceIdentify(), optInt);
            if (b.has("device_manufacture")) {
                String string2 = b.getString("device_manufacture");
                LogUtil.a("HwDeviceHandleBtStateMg", "device_manufacture is", string2);
                jst.m(deviceInfo.getDeviceIdentify(), string2);
            }
            if (b.has(PluginPayAdapter.KEY_DEVICE_INFO_BT_VERSION)) {
                jst.a(deviceInfo.getDeviceIdentify(), b.getString(PluginPayAdapter.KEY_DEVICE_INFO_BT_VERSION));
            }
            if (b.has("device_version")) {
                jst.l(deviceInfo.getDeviceIdentify(), b.getString("device_version"));
            }
            if (b.has(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION)) {
                jst.h(deviceInfo.getDeviceIdentify(), b.getString(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION));
            }
            c(deviceInfo, b);
            e.c(deviceInfo, bArr, b, i, jsz.b().d());
        } catch (JSONException unused) {
            LogUtil.b("HwDeviceHandleBtStateMg", "0xA0200008", "handleGetProductTypeDetails jsonException.");
        }
    }

    private void c(DeviceInfo deviceInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(PluginPayAdapter.KEY_DEVICE_INFO_NAME)) {
            jst.i(deviceInfo.getDeviceIdentify(), jSONObject.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME));
        }
        if (jSONObject.has("hilink_device_id")) {
            String d2 = bmz.d(deviceInfo.getProductType(), jSONObject.getString("hilink_device_id"));
            jst.n(deviceInfo.getDeviceIdentify(), d2);
            deviceInfo.setHiLinkDeviceId(d2);
        }
        if (jSONObject.has("device_ota_package_name")) {
            deviceInfo.setDeviceOtaPackageName(jSONObject.getString("device_ota_package_name"));
        }
        if (jSONObject.has("power_save")) {
            jst.c(deviceInfo.getDeviceIdentify(), jSONObject.getInt("power_save"));
        } else {
            jst.c(deviceInfo.getDeviceIdentify(), 0);
        }
        if (jSONObject.has("udid_from_device")) {
            jst.o(deviceInfo.getDeviceIdentify(), jSONObject.getString("udid_from_device"));
        }
        if (jSONObject.has("unconverted_udid")) {
            jst.p(deviceInfo.getDeviceIdentify(), jSONObject.getString("unconverted_udid"));
        }
        if (jSONObject.has("device_factory_reset")) {
            jst.b(deviceInfo.getDeviceIdentify(), jSONObject.getInt("device_factory_reset"));
            deviceInfo.setDeviceFactoryReset(jSONObject.getInt("device_factory_reset"));
        }
        if (jSONObject.has("device_country_code")) {
            jst.d(deviceInfo.getDeviceIdentify(), jSONObject.getString("device_country_code"));
            deviceInfo.setCountryCode(jSONObject.getString("device_country_code"));
        }
        if (jSONObject.has("device_emui_version")) {
            jst.e(deviceInfo.getDeviceIdentify(), jSONObject.getString("device_emui_version"));
            deviceInfo.setEmuiVersion(jSONObject.getString("device_emui_version"));
        }
        if (jSONObject.has("device_multi_link_ble_mac")) {
            jst.j(deviceInfo.getDeviceIdentify(), jSONObject.getString("device_multi_link_ble_mac"));
            deviceInfo.setMultiLinkBleMac(jSONObject.getString("device_multi_link_ble_mac"));
        }
    }

    private void c(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Get device command id list info send timeout.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        iyz.d(deviceInfo.getProductType(), d2);
        if (iyo.a().contains(3)) {
            d2.configureContacts(false);
        }
        if (!iyo.e(this.b, bArr, d2)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Get device command id list info fail.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle V2 protocol other capability.");
        int r = jst.r(deviceInfo.getDeviceIdentify());
        String l = jst.l(deviceInfo.getDeviceIdentify());
        String f = jst.f(deviceInfo.getDeviceIdentify());
        if (r == 10 && "73617766697368".equals(l) && "372E312E31".equals(f)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "leo problem version, reset capability");
            e.a(d2);
        }
        if (r == 10 && "736177736861726B".equals(l) && ("4E5847313250".equals(f) || "4E4C4731334E".equals(f))) {
            LogUtil.a("HwDeviceHandleBtStateMg", "leo problem version, reset heart rate capability");
            d2.configureSupportHeartRateInfo(false);
        }
        a(deviceInfo, bArr, d2);
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr, DeviceCapability deviceCapability) {
        LogUtil.a("HwDeviceHandleBtStateMg", "Enter sendExpandCapability");
        if (deviceCapability != null && deviceCapability.isSupportExpandCapability()) {
            izf i = iyo.i();
            i.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to send expand capability");
                this.c.c(deviceInfo.getDeviceBluetoothType(), i);
                return;
            }
            return;
        }
        e(deviceInfo, deviceCapability);
    }

    private void e(DeviceInfo deviceInfo, DeviceCapability deviceCapability) {
        if (deviceCapability != null && (deviceCapability.isSupportAccountSwitch() || deviceCapability.isSupportChangePhonePair())) {
            LogUtil.a("HwDeviceHandleBtStateMg", "change phone pair start get account judgment result");
            String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
            if (e2 == null || "0".equals(e2) || "com.huawei.health".equals(e2)) {
                LogUtil.h("HwDeviceHandleBtStateMg", "AccountLoginReceiver userId is empty");
                e2 = "";
            }
            izf c = iyo.c(e2);
            c.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to get account judgment result.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), c);
                return;
            }
            return;
        }
        LogUtil.h("HwDeviceHandleBtStateMg", "change phone pair start not supported change phone pair");
        a(deviceInfo, deviceCapability);
    }

    private void a(DeviceInfo deviceInfo, DeviceCapability deviceCapability) {
        if (deviceCapability != null && deviceCapability.isGoldCard()) {
            LogUtil.a("HwDeviceHandleBtStateMg", "is support gold card");
            izf h = iyo.h();
            h.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to get is support gold card");
                this.c.c(deviceInfo.getDeviceBluetoothType(), h);
            }
        }
        if (deviceCapability != null && deviceCapability.isSupportAutoDetectMode()) {
            LogUtil.a("HwDeviceHandleBtStateMg", "is support auto detect switch status and work mode");
            izf j = iyo.j();
            j.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to get auto detect switch status and work mode");
                this.c.c(deviceInfo.getDeviceBluetoothType(), j);
            }
        }
        if (deviceCapability != null && deviceCapability.isSupportSettingRelated()) {
            j(deviceInfo);
            return;
        }
        if (deviceCapability != null && deviceCapability.isSupportZoneId()) {
            jsz.b().d(deviceInfo);
            return;
        }
        if (deviceCapability != null && deviceCapability.isSupportActivityType()) {
            deviceCapability.configureSupportActivityType(true);
            izf g = iyo.g();
            g.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "Start to get device activity type info.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), g);
                return;
            }
            return;
        }
        if (deviceCapability != null && deviceCapability.isSupportMessageSupportInfo()) {
            deviceCapability.configureSupportMessageSupportInfo(true);
            izf l = iyo.l();
            l.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to get device notification type info.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), l);
                return;
            }
            return;
        }
        LogUtil.h("HwDeviceHandleBtStateMg", "not supported activity and notification type");
        i(deviceInfo);
    }

    private void j(DeviceInfo deviceInfo) {
        izf k = iyo.k();
        k.e(deviceInfo.getDeviceIdentify());
        if (this.c != null) {
            LogUtil.a("HwDeviceHandleBtStateMg", "start to get device setting type info.");
            this.c.c(deviceInfo.getDeviceBluetoothType(), k);
        }
    }

    private void i(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceHandleBtStateMg", "Enter handleHandshakeSuccess().");
        izy izyVar = this.c;
        if (izyVar != null && !TextUtils.isEmpty(izyVar.a(deviceInfo.getDeviceIdentify()))) {
            LogUtil.a("HwDeviceHandleBtStateMg", "handleHandshakeSuccess add cancel");
            this.c.d(deviceInfo.getDeviceIdentify());
            this.c.e(deviceInfo.getDeviceIdentify());
            return;
        }
        jst.a(deviceInfo.getDeviceIdentify(), false);
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceInfo.getUuid())) {
            deviceInfo.setUuid(deviceIdentify);
        }
        a(deviceInfo, deviceIdentify);
        String i = jst.i(deviceIdentify);
        if (!TextUtils.isEmpty(i) && deviceInfo.getProductType() == 10) {
            LogUtil.a("HwDeviceHandleBtStateMg", "enter handleHandshakeSuccess() set device model:", i);
            if (TextUtils.equals(i, "PORSCHE DESIGN")) {
                deviceInfo.setDeviceName("PORSCHE DESIGN");
            }
        }
        b(deviceInfo, deviceIdentify);
    }

    private void b(DeviceInfo deviceInfo, String str) {
        int b = e.b(str, jsz.b().d());
        if (b == -1) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Device List do not has this device.");
            String b2 = b();
            deviceInfo.setWearEngineDeviceId(b2);
            LogUtil.a("HwDeviceHandleBtStateMg", "handleHandshakeSuccess wearEngineDeviceId: " + b2);
            c(deviceInfo);
            return;
        }
        if (e.c(str, jsz.b().d())) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Device List has this device. current Mac Address equals active device Mac address.");
            b(deviceInfo, b);
        } else {
            LogUtil.a("HwDeviceHandleBtStateMg", "Device List has this device. pre MacAddress do not equals current Mac address.");
            c(deviceInfo, b);
        }
    }

    private void a(DeviceInfo deviceInfo, String str) {
        deviceInfo.setDeviceModel(jst.j(str));
        deviceInfo.setCertModel(jst.a(str));
        deviceInfo.setVersionType(jst.o(str));
        deviceInfo.setBluetoothVersion(jst.b(str));
        deviceInfo.setSoftVersion(jst.f(str));
        deviceInfo.setUuid(jst.u(str));
        deviceInfo.setPowerSaveModel(jst.p(str));
        deviceInfo.setUdidFromDevice(jst.q(str));
        deviceInfo.setUnConvertedUdid(jst.t(str));
        DeviceCapability d2 = jst.d(str);
        if (d2 != null) {
            deviceInfo.setSupportAccountSwitch(d2.isSupportAccountSwitch() ? 1 : 0);
        }
        deviceInfo.setManufacture(jst.n(str));
        deviceInfo.setDeviceUdid(jst.g(str));
        deviceInfo.setHiLinkDeviceId(jst.m(str));
        deviceInfo.setDeviceFactoryReset(jst.h(str));
        String c = jst.c(str);
        LogUtil.a("HwDeviceHandleBtStateMg", "enter handleHandshakeSuccess() set device expandCapability:", c);
        deviceInfo.setExpandCapability(c);
    }

    public static String b() {
        return UUID.randomUUID().toString().replace(Constants.LINK, "").toUpperCase(Locale.ROOT);
    }

    private void c(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceHandleBtStateMg", "Enter addDeviceInfoToList().");
        if (f(deviceInfo)) {
            LogUtil.h("HwDeviceHandleBtStateMg", "addDeviceInfoToList bluetoothSwitch off, return");
            return;
        }
        if (e.b(deviceInfo.getDeviceIdentify(), jsz.b().d()) != -1) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Find the same device during add device.");
            return;
        }
        if (!jsz.b().d().isEmpty()) {
            for (DeviceInfo deviceInfo2 : jsz.b().d()) {
                if (deviceInfo2.getDeviceActiveState() == 1 && !cvt.c(deviceInfo2.getProductType()) && !cvt.c(deviceInfo.getProductType()) && deviceInfo2.getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a("HwDeviceHandleBtStateMg", "already has active device, and is not from user change,so return");
                    return;
                }
            }
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.a("HwDeviceHandleBtStateMg", "addDeviceIdentify:", iyl.d().e(deviceIdentify), ",device model:", iyl.d().e(deviceInfo.getDeviceModel()));
        jst.f(deviceIdentify, deviceIdentify);
        jst.e(deviceIdentify, 2);
        deviceInfo.setDeviceActiveState(1);
        deviceInfo.setDeviceConnectState(2);
        deviceInfo.setLastConnectedTime(System.currentTimeMillis());
        synchronized (jsz.b().a()) {
            if (e.b(deviceIdentify, jsz.b().d()) != -1) {
                LogUtil.h("HwDeviceHandleBtStateMg", "already has the device,so return");
            } else {
                jsz.b().d().add(deviceInfo);
                d(deviceInfo);
            }
        }
    }

    private void d(DeviceInfo deviceInfo) {
        izy izyVar = this.c;
        if (izyVar != null) {
            izyVar.c(deviceInfo.getDeviceIdentify(), deviceInfo.getNodeId(), deviceInfo.getDeviceName(), deviceInfo.getDeviceBluetoothType());
            this.c.e(deviceInfo);
            this.c.a(deviceInfo.getNodeId(), deviceInfo.getDeviceIdentify(), deviceInfo.getDeviceName(), deviceInfo.getDeviceBluetoothType());
        }
        g(deviceInfo);
        jsz.b().j();
        kct.c().d(deviceInfo, false);
        d();
        jsu.c().a(deviceInfo);
    }

    public void d() {
        LogUtil.a("HwDeviceHandleBtStateMg", "sendDeviceListChangeBroadcast.");
        this.b.sendBroadcast(new Intent("com.huawei.bone.action.DEVICE_LIST_CHANGED"), LocalBroadcast.c);
    }

    private void g(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceHandleBtStateMg", "Enter sendConnectStatus");
        if (jst.d(deviceInfo.getDeviceIdentify()).isSupportConnectStatus()) {
            izf e2 = iyo.e();
            e2.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to send connect status.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), e2);
                return;
            }
            return;
        }
        LogUtil.h("HwDeviceHandleBtStateMg", "not support connect status");
    }

    private void b(DeviceInfo deviceInfo, int i) {
        int b;
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.a("HwDeviceHandleBtStateMg", "handleDeviceEqualActiveDevice handleDeviceIdentify:", iyl.d().e(deviceIdentify));
        jst.f(deviceIdentify, deviceIdentify);
        int r = jst.r(deviceIdentify);
        List<DeviceInfo> d2 = jsz.b().d();
        if (r == -1 && (b = jsz.b().b(deviceInfo.getDeviceIdentify())) != -1) {
            DeviceInfo deviceInfo2 = d2.get(b);
            int productType = deviceInfo2.getProductType();
            jst.d(deviceInfo2.getDeviceIdentify(), productType);
            if (productType == -1) {
                LogUtil.h("HwDeviceHandleBtStateMg", "list has this device but the product type is unknown.");
                return;
            }
            deviceInfo.setProductType(productType);
        }
        if (i < 0 || i >= d2.size()) {
            return;
        }
        String deviceName = d2.get(i).getDeviceName();
        if ("".equals(deviceName)) {
            String deviceName2 = deviceInfo.getDeviceName();
            LogUtil.c("HwDeviceHandleBtStateMg", "deviceName:", deviceName2);
            if (deviceName2 != null && !"".equals(deviceName2)) {
                d2.get(i).setDeviceName(deviceInfo.getDeviceName());
            } else {
                d2.get(i).setDeviceName(deviceInfo.getDeviceIdentify());
            }
        }
        if (deviceInfo.getDeviceName() != null && !deviceInfo.getDeviceName().equalsIgnoreCase(deviceName)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "device name changed with name:", deviceInfo.getDeviceName());
            d2.get(i).setDeviceName(deviceInfo.getDeviceName());
            jsz.b().j();
        }
        if (f(deviceInfo)) {
            LogUtil.h("HwDeviceHandleBtStateMg", "handleDeviceEqualActiveDevice bluetoothSwitch off, return");
        } else {
            a(deviceInfo, i, deviceIdentify);
        }
    }

    private void c(DeviceInfo deviceInfo, int i) {
        int b = jsz.b().b(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwDeviceHandleBtStateMg", "handleDeviceUnequalActiveDevice activeDeviceIndex:", Integer.valueOf(b));
        if (i != -1) {
            int productType = deviceInfo.getProductType();
            jst.d(deviceInfo.getDeviceIdentify(), productType);
            if (productType == -1 && b != -1) {
                LogUtil.a("HwDeviceHandleBtStateMg", "currentProductType is unknown.");
                int productType2 = jsz.b().d().get(b).getProductType();
                jst.d(deviceInfo.getDeviceIdentify(), productType2);
                if (productType2 == -1) {
                    LogUtil.h("HwDeviceHandleBtStateMg", "list has this device but the product type is unknown.");
                    return;
                }
                deviceInfo.setProductType(productType2);
            }
            if (deviceInfo.getDeviceName() != null) {
                if (!deviceInfo.getDeviceName().equalsIgnoreCase(jsz.b().d().get(i).getDeviceName())) {
                    LogUtil.a("HwDeviceHandleBtStateMg", "device name changed with name:", deviceInfo.getDeviceName());
                    jsz.b().d().get(i).setDeviceName(deviceInfo.getDeviceName());
                    jsz.b().j();
                }
            }
            if (f(deviceInfo)) {
                LogUtil.h("HwDeviceHandleBtStateMg", "handleDeviceUnequalActiveDevice bluetoothSwitch off, return");
                return;
            } else {
                a(deviceInfo, i, b);
                return;
            }
        }
        LogUtil.h("HwDeviceHandleBtStateMg", "List do not has this device.");
    }

    private void a(DeviceInfo deviceInfo, int i, int i2) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.a("HwDeviceHandleBtStateMg", "Wanted Device Identify:", iyl.d().e(deviceIdentify));
        List<DeviceInfo> d2 = jsz.b().d();
        if (i2 == -1 && i != -1) {
            LogUtil.a("HwDeviceHandleBtStateMg", "activeDeviceIndex do not exist and deviceInfoIndex exist.");
            d2.get(i).setDeviceActiveState(1);
            e.e(deviceInfo, d2);
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "Start to set active device. Start to set current device.");
                this.c.c(deviceInfo.getDeviceIdentify(), deviceInfo.getNodeId(), deviceInfo.getDeviceName(), deviceInfo.getDeviceBluetoothType());
                this.c.e(deviceInfo);
            }
        } else {
            String deviceIdentify2 = d2.get(i2).getDeviceIdentify();
            LogUtil.a("HwDeviceHandleBtStateMg", "Active Device Identify:", iyl.d().e(deviceIdentify2));
            if (!deviceIdentify2.equalsIgnoreCase(deviceIdentify)) {
                LogUtil.a("HwDeviceHandleBtStateMg", "active is unequal with wanted device so set active info.");
                d2.get(i2).setDeviceActiveState(0);
                d2.get(i).setDeviceActiveState(1);
            }
        }
        jst.f(deviceIdentify, deviceIdentify);
        jst.e(deviceIdentify, 2);
        deviceInfo.setDeviceConnectState(2);
        deviceInfo.setDeviceActiveState(1);
        deviceInfo.setLastConnectedTime(System.currentTimeMillis());
        a(deviceInfo, i);
        g(deviceInfo);
        jsz.b().j();
        kct.c().d(deviceInfo, false);
        d();
        jsu.c().a(deviceInfo);
    }

    private void a(DeviceInfo deviceInfo, int i) {
        List<DeviceInfo> d2 = jsz.b().d();
        d2.get(i).setDeviceModel(deviceInfo.getDeviceModel());
        d2.get(i).setCertModel(deviceInfo.getCertModel());
        d2.get(i).setVersionType(deviceInfo.getVersionType());
        d2.get(i).setBluetoothVersion(deviceInfo.getBluetoothVersion());
        d2.get(i).setSoftVersion(deviceInfo.getSoftVersion());
        d2.get(i).setUuid(deviceInfo.getUuid());
        d2.get(i).setDeviceIdType(deviceInfo.getDeviceIdType());
        d2.get(i).setPowerSaveModel(deviceInfo.getPowerSaveModel());
        d2.get(i).setUdidFromDevice(deviceInfo.getUdidFromDevice());
        d2.get(i).setUnConvertedUdid(deviceInfo.getUnConvertedUdid());
        d2.get(i).setSupportAccountSwitch(deviceInfo.getSupportAccountSwitch());
        d2.get(i).setDeviceConnectState(2);
        d2.get(i).setManufacture(deviceInfo.getManufacture());
        d2.get(i).setDeviceUdid(deviceInfo.getDeviceUdid());
        d2.get(i).setHiLinkDeviceId(deviceInfo.getHiLinkDeviceId());
        d2.get(i).setDeviceFactoryReset(deviceInfo.getDeviceFactoryReset());
        d2.get(i).setLastConnectedTime(System.currentTimeMillis());
        d2.get(i).setDeviceOtaPackageName(deviceInfo.getDeviceOtaPackageName());
        d2.get(i).setExpandCapability(deviceInfo.getExpandCapability());
        d2.get(i).setCountryCode(deviceInfo.getCountryCode());
        d2.get(i).setEmuiVersion(deviceInfo.getEmuiVersion());
        d2.get(i).setMultiLinkBleMac(deviceInfo.getMultiLinkBleMac());
    }

    private boolean f(DeviceInfo deviceInfo) {
        jsx jsxVar;
        if (jsz.b().b.g() == 3 || (jsxVar = e) == null) {
            return false;
        }
        jsxVar.b(deviceInfo, null, jsz.b().d());
        return true;
    }

    private void a(DeviceInfo deviceInfo, int i, String str) {
        if (jsz.b().d().get(i).getProductType() != deviceInfo.getProductType() && (deviceInfo.getProductType() == 3 || deviceInfo.getProductType() == 10)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "change type:", deviceInfo.getDeviceName());
            jsz.b().d().get(i).setProductType(deviceInfo.getProductType());
            jsz.b().j();
        }
        izy izyVar = this.c;
        if (izyVar != null) {
            izyVar.c(deviceInfo.getDeviceIdentify(), deviceInfo.getNodeId(), deviceInfo.getDeviceName(), deviceInfo.getDeviceBluetoothType());
        }
        jsz.b().d().get(i).setDeviceActiveState(1);
        a(deviceInfo, i);
        deviceInfo.setDeviceConnectState(2);
        deviceInfo.setLastConnectedTime(System.currentTimeMillis());
        jst.e(str, 2);
        g(deviceInfo);
        jsz.b().j();
        kct.c().d(deviceInfo, false);
        jsu.c().a(deviceInfo);
    }

    private void m(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Get device activity type info send timeout.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle BT device supported activity type.");
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (iyo.b(bArr, d2)) {
            b(deviceInfo, bArr, d2);
        } else {
            LogUtil.h("HwDeviceHandleBtStateMg", "Get device activity type info fail.");
            e.b(deviceInfo, bArr, jsz.b().d());
        }
    }

    private void b(DeviceInfo deviceInfo, byte[] bArr, DeviceCapability deviceCapability) {
        if (iyo.e(2, 5, bArr)) {
            if (deviceCapability != null) {
                deviceCapability.configureSupportMessageSupportInfo(true);
            }
            izf l = iyo.l();
            l.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "Start to get device notification type info.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), l);
                return;
            }
            return;
        }
        i(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Get device notification type info send timeout.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle BT device supported notification type.");
        if (iyo.a(bArr, jst.d(deviceInfo.getDeviceIdentify()))) {
            i(deviceInfo);
        } else {
            LogUtil.h("HwDeviceHandleBtStateMg", "Get device notification type info fail.");
            e.b(deviceInfo, bArr, jsz.b().d());
        }
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "handleAutoDetectSwitchStatusAndWorkMode isCommandTimeout true.");
            return;
        }
        boolean[] c = iyo.c(deviceInfo, bArr);
        LogUtil.a("HwDeviceHandleBtStateMg", "handleAutoDetectSwitchStatusAndWorkMode workMode :", Integer.valueOf(deviceInfo.getAutoDetectSwitchStatus()));
        if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
            if (e(deviceInfo)) {
                return;
            } else {
                LogUtil.a("HwDeviceHandleBtStateMg", "handleAutoDetectSwitchStatusAndWorkMode workMode after caseForAw70BandMode:", Integer.valueOf(deviceInfo.getAutoDetectSwitchStatus()));
            }
        } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
            if (a(deviceInfo)) {
                return;
            } else {
                LogUtil.a("HwDeviceHandleBtStateMg", "handleAutoDetectSwitchStatusAndWorkMode workMode after caseForAw70RunMode:", Integer.valueOf(deviceInfo.getAutoDetectSwitchStatus()));
            }
        } else {
            LogUtil.c("HwDeviceHandleBtStateMg", "else branch.");
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (c[0]) {
            LogUtil.a("HwDeviceHandleBtStateMg", "AutoDetectSwitchStatusAndWorkMode support auto detect!");
            d2.configureSupportAutoDetectMode(true);
        } else {
            LogUtil.a("HwDeviceHandleBtStateMg", "AutoDetectSwitchStatusAndWorkMode Not support auto detect!");
            d2.configureSupportAutoDetectMode(false);
        }
        if (c[1]) {
            LogUtil.a("HwDeviceHandleBtStateMg", "AutoDetectSwitchStatusAndWorkMode support foot wear!");
            d2.configureSupportFootWear(true);
        } else {
            LogUtil.a("HwDeviceHandleBtStateMg", "AutoDetectSwitchStatusAndWorkMode not support foot wear");
            d2.configureSupportFootWear(false);
        }
    }

    private boolean e(DeviceInfo deviceInfo) {
        synchronized (jsz.b().a()) {
            for (DeviceInfo deviceInfo2 : jsz.b().d()) {
                LogUtil.c("HwDeviceHandleBtStateMg", "caseForAw70BandMode info :", deviceInfo2);
                if (deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a("HwDeviceHandleBtStateMg", "caseForAw70BandMode update work mode.");
                    deviceInfo2.setAutoDetectSwitchStatus(0);
                }
            }
            kcw a2 = kcw.a();
            for (DeviceInfo deviceInfo3 : jsz.b().d()) {
                if (deviceInfo3.getDeviceActiveState() == 1 && !deviceInfo3.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && !a2.c(deviceInfo3.getProductType(), deviceInfo3.getDeviceIdentify())) {
                    e.a(deviceInfo3.getDeviceIdentify());
                    return e.c(deviceInfo3, jsz.b().d());
                }
            }
            return jte.e(deviceInfo);
        }
    }

    private boolean a(DeviceInfo deviceInfo) {
        synchronized (jsz.b().a()) {
            for (DeviceInfo deviceInfo2 : jsz.b().d()) {
                LogUtil.c("HwDeviceHandleBtStateMg", "caseForAw70RunMode info :", deviceInfo2);
                if (deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a("HwDeviceHandleBtStateMg", "caseForAw70RunMode update work mode in caseForAw70RunMode");
                    deviceInfo2.setAutoDetectSwitchStatus(1);
                }
            }
            e.d(deviceInfo, jsz.b().d());
            kcw a2 = kcw.a();
            for (DeviceInfo deviceInfo3 : jsz.b().d()) {
                if (deviceInfo3.getDeviceActiveState() == 1 && deviceInfo3.getAutoDetectSwitchStatus() == 1 && !deviceInfo3.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && !a2.c(deviceInfo3.getProductType(), deviceInfo3.getDeviceIdentify())) {
                    return e.c(deviceInfo3, jsz.b().d());
                }
            }
            return false;
        }
    }

    private void h(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null) {
            LogUtil.h("HwDeviceHandleBtStateMg", "btDeviceInfo dataContents is null");
        } else {
            if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
                LogUtil.a("HwDeviceHandleBtStateMg", "handleGetSetZoneId timeout.");
                return;
            }
            LogUtil.a("HwDeviceHandleBtStateMg", "Start to handleGetSetZoneId.");
            iyo.f(bArr);
            b(deviceInfo, jst.d(deviceInfo.getDeviceIdentify()));
        }
    }

    private void b(DeviceInfo deviceInfo, DeviceCapability deviceCapability) {
        if (deviceCapability != null && deviceCapability.isSupportActivityType()) {
            izf g = iyo.g();
            g.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "Start to get device activity type info.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), g);
                return;
            }
            return;
        }
        if (deviceCapability != null && deviceCapability.isSupportMessageSupportInfo()) {
            izf l = iyo.l();
            l.e(deviceInfo.getDeviceIdentify());
            if (this.c != null) {
                LogUtil.a("HwDeviceHandleBtStateMg", "start to get device notification type info.");
                this.c.c(deviceInfo.getDeviceBluetoothType(), l);
                return;
            }
            return;
        }
        i(deviceInfo);
    }

    private void f(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "handleGetSetRelated timeout.");
            return;
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        iyo.d(bArr, d2);
        if (deviceInfo.getDeviceFactoryReset() == 1) {
            deviceInfo.setDeviceConnectState(11);
            jsu.c().a(deviceInfo);
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle handleGetSetRelated.");
        if (d2 != null && d2.isSupportZoneId()) {
            jsz.b().d(deviceInfo);
        } else {
            b(deviceInfo, d2);
        }
    }

    private void c(DeviceInfo deviceInfo, int i, byte[] bArr) {
        byte b = bArr[0];
        if (b == 26 && bArr[1] == 5) {
            e(deviceInfo, bArr);
            return;
        }
        if (b == 26 && bArr[1] == 6) {
            l(deviceInfo, bArr);
            return;
        }
        if (b == 1 && bArr[1] == 55) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Enter 5.1.55");
            b(deviceInfo, bArr);
            return;
        }
        if (b == 1 && bArr[1] == 48) {
            LogUtil.a("HwDeviceHandleBtStateMg", "Enter 5.1.48");
            g(deviceInfo, bArr);
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "handshake report data.");
        if (KitWearBinderUtil.d(deviceInfo, bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "isForThemeData");
            return;
        }
        if (jsz.b().c != null) {
            jsz.b().c.onDataReceived(deviceInfo, i, bArr);
        } else {
            LogUtil.h("HwDeviceHandleBtStateMg", "mDeviceStateClientCallback is null.");
        }
        kih.e().c(deviceInfo, bArr);
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "account judgment result time out.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        boolean a2 = iyo.a(bArr);
        LogUtil.a("HwDeviceHandleBtStateMg", "start handle account judgment result. account judgment result:", Boolean.valueOf(a2));
        if (a2) {
            a(deviceInfo, d2);
        } else {
            deviceInfo.setDeviceConnectState(9);
            jsu.c().a(deviceInfo);
        }
    }

    private void l(DeviceInfo deviceInfo, byte[] bArr) {
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "user select dialog time out.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "start handle user select restore factory.");
        if (iyo.g(bArr)) {
            deviceInfo.setDeviceConnectState(10);
            jsu.c().a(deviceInfo);
        } else {
            LogUtil.h("HwDeviceHandleBtStateMg", "user cancel restore factory.");
            e.b(deviceInfo, bArr, jsz.b().d());
        }
    }

    private void b(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwDeviceHandleBtStateMg", "enter handleGetExpandCapability.");
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "handleGetSendUserSetting timeout.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle handleGetExpandCapability.");
        String d2 = blp.d(iyo.e(bArr), jst.j(deviceInfo.getDeviceIdentify()));
        LogUtil.a("HwDeviceHandleBtStateMg", "handleGetExpandCapability :", d2);
        jst.c(deviceInfo.getDeviceIdentify(), d2);
        deviceInfo.setExpandCapability(d2);
        e(deviceInfo, jst.d(deviceInfo.getDeviceIdentify()));
    }

    private void g(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwDeviceHandleBtStateMg", "enter handleGetSendUserSetting.");
        if (iyo.c(this.b, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "handleGetSendUserSetting timeout.");
            e.b(deviceInfo, bArr, jsz.b().d());
            return;
        }
        LogUtil.a("HwDeviceHandleBtStateMg", "Start to handle handleGetSendUserSetting.");
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 != null && d2.isSupportZoneId()) {
            jsz.b().d(deviceInfo);
        } else {
            b(deviceInfo, d2);
        }
    }

    private void a(DeviceInfo deviceInfo, int i, byte[] bArr) {
        LogUtil.a("HwDeviceHandleBtStateMg", "not handshake report data.");
        if (KitWearBinderUtil.d(deviceInfo, bArr)) {
            LogUtil.a("HwDeviceHandleBtStateMg", "isForThemeData");
            return;
        }
        if (e.b(deviceInfo, bArr)) {
            LogUtil.h("HwDeviceHandleBtStateMg", "otherCommandReceivedMethod retry 1.5");
            return;
        }
        if (jsz.b().c != null) {
            jsz.b().c.onDataReceived(deviceInfo, i, bArr);
        } else {
            LogUtil.h("HwDeviceHandleBtStateMg", "mDeviceStateClientCallback is null.");
        }
        kih.e().c(deviceInfo, bArr);
        kig kigVar = this.f;
        if (kigVar != null) {
            kigVar.b(deviceInfo, bArr);
        }
    }
}
