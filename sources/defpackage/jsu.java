package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class jsu {

    /* renamed from: a, reason: collision with root package name */
    private static jsx f14056a;
    private static jsu b;
    private static final Object d = new Object();
    private izy e;
    private volatile boolean f = false;
    private Context c = BaseApplication.getContext();

    private int a(int i) {
        if (i == 2) {
            return 1;
        }
        return i == 1 ? 2 : 3;
    }

    private jsu() {
        this.e = null;
        LogUtil.h("HwDeviceConnectChangeMgr", "Init HwDeviceConnectChangeMgr.");
        this.e = izy.b(this.c);
        f14056a = jsx.c();
    }

    public void d(boolean z) {
        this.f = z;
    }

    public static jsu c() {
        jsu jsuVar;
        synchronized (d) {
            if (b == null) {
                b = new jsu();
            }
            jsuVar = b;
        }
        return jsuVar;
    }

    public void b(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo) {
        LogUtil.a("HwDeviceConnectChangeMgr", "enter onDeviceConnectionStateChanged() with state:", Integer.valueOf(i));
        if (i == 1) {
            izn.c().b(deviceInfo, System.currentTimeMillis());
            LogUtil.a("HwDeviceConnectChangeMgr", "mDeviceStateCallback device start connecting, time:", Long.valueOf(System.currentTimeMillis()));
        }
        if (i == 4) {
            c(operationDeviceInfo);
        }
        if (i == 2 && operationDeviceInfo != null) {
            operationDeviceInfo.setEndConnectTime(System.currentTimeMillis());
            operationDeviceInfo.setErrorMeathod("success");
            c(operationDeviceInfo);
        }
        c(deviceInfo, i);
    }

    private void c(OperationDeviceInfo operationDeviceInfo) {
        if (operationDeviceInfo == null) {
            LogUtil.a("HwDeviceConnectChangeMgr", "sendConnectFailedEvent operationDeviceInfo is null");
        } else {
            e(operationDeviceInfo.getDeviceIdentify(), operationDeviceInfo);
        }
    }

    private void c(DeviceInfo deviceInfo, int i) {
        synchronized (jsz.b().a()) {
            List<DeviceInfo> d2 = jsz.b().d();
            if (d2 != null && deviceInfo != null && deviceInfo.getDeviceBluetoothType() == 0 && ((i == 2 || i == 1) && !f14056a.b(deviceInfo, d2))) {
                LogUtil.a("HwDeviceConnectChangeMgr", "Enter onDeviceConnectionStateChanged() aw not need report");
                return;
            }
            if (deviceInfo == null) {
                return;
            }
            if (i != 2) {
                jst.d(deviceInfo.getDeviceIdentify(), false);
            }
            if (i != 2) {
                jst.e(deviceInfo.getDeviceIdentify(), i);
                deviceInfo.setDeviceConnectState(i);
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                synchronized (jsz.b().a()) {
                    List<DeviceInfo> d3 = jsz.b().d();
                    int b2 = f14056a.b(deviceIdentify, d3);
                    if (b2 != -1) {
                        d3.get(b2).setDeviceConnectState(i);
                        if (deviceInfo.getDeviceBluetoothType() == 5 || deviceInfo.getDeviceBluetoothType() == 0) {
                            deviceInfo.setDeviceName(d3.get(b2).getDeviceName());
                        }
                        LogUtil.a("HwDeviceConnectChangeMgr", "update deviceInfo state with index:", Integer.valueOf(b2));
                    }
                }
                jst.a(deviceInfo.getDeviceIdentify(), false);
                a(deviceInfo);
            } else {
                e(deviceInfo);
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        if (jst.e(deviceInfo.getDeviceIdentify()) == 2) {
            if (deviceInfo.getDeviceProtocol() == -1) {
                LogUtil.a("HwDeviceConnectChangeMgr", "already finish handshake and repeat report connected.", ", the device user choose already active and connect so report.");
                List<DeviceInfo> d2 = jsz.b().d();
                int b2 = f14056a.b(deviceInfo.getDeviceIdentify(), d2);
                if (b2 != -1) {
                    deviceInfo.setDeviceActiveState(d2.get(b2).getDeviceActiveState());
                    deviceInfo.setProductType(d2.get(b2).getProductType());
                    deviceInfo.setDeviceName(d2.get(b2).getDeviceName());
                    deviceInfo.setDeviceProtocol(d2.get(b2).getDeviceProtocol());
                    a(deviceInfo);
                    return;
                }
                return;
            }
            LogUtil.a("HwDeviceConnectChangeMgr", "already finish handshake and repeat report connected.", ", Already has the active device so report connected state.");
            a(deviceInfo);
            return;
        }
        if (jst.k(deviceInfo.getDeviceIdentify()) && iym.l()) {
            LogUtil.a("HwDeviceConnectChangeMgr", "Already Start handshake.");
            return;
        }
        jst.a(deviceInfo.getDeviceIdentify(), true);
        if (deviceInfo.getDeviceProtocol() == 2) {
            jst.l(deviceInfo.getDeviceIdentify(), "");
            jst.h(deviceInfo.getDeviceIdentify(), "");
            int c = f14056a.c(iym.c(deviceInfo.getDeviceName()), deviceInfo.getDeviceIdentify(), jsz.b().d());
            LogUtil.a("HwDeviceConnectChangeMgr", "start to get product type.", " device product:", Integer.valueOf(c));
            izf a2 = iyo.a(deviceInfo.getDeviceBluetoothType(), c);
            a2.e(deviceInfo.getDeviceIdentify());
            izy izyVar = this.e;
            if (izyVar != null) {
                izyVar.c(deviceInfo.getDeviceBluetoothType(), a2);
            }
        }
    }

    public void a(DeviceInfo deviceInfo) {
        LogUtil.a("R_HwDeviceConnectChangeMgr", "Enter sendConnectStateBroadcast(). ", Integer.valueOf(ize.c()));
        if (deviceInfo == null) {
            return;
        }
        if (jsz.b().c != null) {
            jsz.b().c.onConnectStatusChanged(deviceInfo);
        }
        kkj.d(deviceInfo);
        if (CommonUtil.ce()) {
            LogUtil.a("HwDeviceConnectChangeMgr", "sendConnectStateBroadcast is support wearProduct.");
            cvw.c(cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwDeviceConnectChangeMgr"), "HwDeviceConnectChangeMgr");
        }
        jqh.c(deviceInfo);
        jtu.a(deviceInfo);
        if (deviceInfo.getDeviceConnectState() == 2) {
            jrp.e(deviceInfo.getDeviceIdentify(), 1);
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "sendConnectStateBroadcast");
        DeviceInfo deviceInfo2 = deviceList.size() > 0 ? deviceList.get(0) : null;
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        Intent bJl_ = bJl_(deviceInfo);
        if (deviceInfo2 != null && deviceIdentify.equals(deviceInfo2.getDeviceIdentify())) {
            this.c.sendOrderedBroadcast(bJl_, LocalBroadcast.c);
            f14056a.a(deviceInfo, jsz.b().d());
            String string = this.c.getSharedPreferences("deviceUpdateSharedPreferences", 0).getString("aw70UpdateCheckTime", "");
            LogUtil.a("HwDeviceConnectChangeMgr", "Enter aw70 sendConnectStateBroadcast(). aw70 mAppCheckTime:", string);
            if (jsx.b(string)) {
                return;
            }
            Intent intent = new Intent("com.huawei.bone.action.UPDATE_DEVICE");
            intent.putExtra("is_current_device_aw70", true);
            this.c.sendBroadcast(intent, LocalBroadcast.c);
            return;
        }
        this.c.sendOrderedBroadcast(bJl_, LocalBroadcast.c);
        f14056a.a(deviceInfo, jsz.b().d());
        if (deviceInfo.getDeviceConnectState() == 2) {
            ize.e(1000000);
        }
    }

    private Intent bJl_(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceConnectChangeMgr", "Enter createDeviceStateIntent deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", connectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        Intent intent = new Intent("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intent.setExtrasClassLoader(DeviceInfo.class.getClassLoader());
        intent.putExtra("connect_error_code", ize.c());
        if (this.f) {
            intent.putExtra("isDeviceConnectDirectly", true);
        } else {
            intent.putExtra("isDeviceConnectDirectly", false);
        }
        if (deviceInfo.getDeviceConnectState() != 1) {
            LogUtil.a("HwDeviceConnectChangeMgr", "createDeviceStateIntent mIsDeviceConnectDirectly reset.");
            this.f = false;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("deviceinfo", deviceInfo);
        if (jsz.b().d() != null) {
            bundle.putInt("wear_device_list_size", jsz.b().d().size());
        }
        intent.putExtras(bundle);
        return intent;
    }

    public void c(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo != null ? deviceInfo.getDeviceIdentify() : null;
        LogUtil.a("HwDeviceConnectChangeMgr", "disconnectBluetooth enter, goingDisconnectDevice", CommonUtil.l(deviceIdentify));
        if (this.e == null || TextUtils.isEmpty(deviceIdentify)) {
            return;
        }
        LogUtil.a("HwDeviceConnectChangeMgr", "addDevice goingDisconnectDevice is not null.");
        this.e.e(false, deviceIdentify);
        this.e.c(deviceIdentify);
        this.e.o(deviceIdentify);
        List<Integer> d2 = f14056a.d(jsz.b().d());
        ArrayList arrayList = new ArrayList(16);
        if (d2 != null && !d2.isEmpty()) {
            Iterator<Integer> it = d2.iterator();
            while (it.hasNext()) {
                DeviceInfo deviceInfo2 = jsz.b().d().get(it.next().intValue());
                if (deviceInfo2 == null) {
                    LogUtil.h("HwDeviceConnectChangeMgr", "addDevice active device info is null");
                } else {
                    LogUtil.c("HwDeviceConnectChangeMgr", "addDevice active device", deviceInfo2);
                    if (deviceIdentify.equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                        LogUtil.a("HwDeviceConnectChangeMgr", "addDevice find target device");
                        arrayList.add(deviceInfo2);
                    }
                }
            }
        }
        LogUtil.a("HwDeviceConnectChangeMgr", "Start to send disconnect broadcast to EMUI.");
        f14056a.e(arrayList);
        LogUtil.a("HwDeviceConnectChangeMgr", "Start to disable corresponding device.");
        jsz.b().e(deviceIdentify);
    }

    public void e(String str, OperationDeviceInfo operationDeviceInfo) {
        Object[] objArr = new Object[4];
        objArr[0] = "sendConnectFailedEvent operationDeviceInfo: ";
        objArr[1] = Boolean.valueOf(operationDeviceInfo == null);
        objArr[2] = " mac: ";
        objArr[3] = Boolean.valueOf(TextUtils.isEmpty(str));
        LogUtil.a("HwDeviceConnectChangeMgr", objArr);
        if (operationDeviceInfo == null || TextUtils.isEmpty(str) || operationDeviceInfo.getProductType() == -1) {
            return;
        }
        int b2 = cwc.b(operationDeviceInfo.getProductType());
        if (b2 == -1) {
            LogUtil.h("HwDeviceConnectChangeMgr", "sendConnectFailedEvent() deviceClassification is unknown.");
            return;
        }
        long endConnectTime = operationDeviceInfo.getEndConnectTime() - operationDeviceInfo.getStartConnectTime();
        if (operationDeviceInfo.getStartConnectTime() <= 0 || endConnectTime <= 0) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("device_classfication", String.valueOf(b2));
        linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(operationDeviceInfo.getProductType()));
        linkedHashMap.put("connect_duration", String.valueOf(endConnectTime));
        linkedHashMap.put("failed_bt_type", String.valueOf(a(operationDeviceInfo.getDeviceBluetoothType())));
        if ("success".equals(operationDeviceInfo.getErrorMeathod())) {
            linkedHashMap.put("failed_classification", "3");
        } else {
            linkedHashMap.put("failed_classification", AgdConstant.INSTALL_TYPE_DEFAULT.equals(operationDeviceInfo.getErrorMeathod()) ? "1" : "2");
            linkedHashMap.put("failed_method", String.valueOf(operationDeviceInfo.getErrorMeathod()));
            linkedHashMap.put("failed_code", String.valueOf(operationDeviceInfo.getErrorCode()));
        }
        iyv.d("80020003", linkedHashMap);
    }
}
