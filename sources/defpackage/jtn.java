package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.iconnect.IWearConnectService;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LocalBroadcast;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jtn {
    private static final Object e = new Object();
    private IWearConnectService d;
    private String i = "";
    private String b = "";
    private Map<String, jtj> c = new ConcurrentHashMap(2);
    private Map<String, VariableHandshakeGeneralCommandBase> j = new ConcurrentHashMap(2);

    /* renamed from: a, reason: collision with root package name */
    private ConnectFilter f14072a = new ConnectFilter() { // from class: jtn.4
        @Override // com.huawei.devicesdk.callback.ConnectFilter
        public int onFilter(UniteDevice uniteDevice, String str, bir birVar) {
            return jtn.this.b(uniteDevice, str, birVar);
        }

        @Override // com.huawei.devicesdk.callback.ConnectFilter
        public String preProcess(UniteDevice uniteDevice, String str) {
            LogUtil.a("VariableHandshakeMgr", "preProcess enter");
            if (uniteDevice != null && str != null && !TextUtils.isEmpty(uniteDevice.getIdentify())) {
                JSONObject c2 = jtn.this.c(str);
                jtj jtjVar = new jtj();
                jtn.this.c.put(uniteDevice.getIdentify(), jtjVar);
                if (jtjVar.b(c2)) {
                    synchronized (jtn.e) {
                        biw c3 = bjx.a().c(uniteDevice.getIdentify());
                        int i = c3 != null ? c3.i() : 0;
                        LogUtil.a("VariableHandshakeMgr", "preProcess supportAuthType: ", Integer.valueOf(i));
                        if (!jtn.this.i() || bjn.c(i) || bjk.d(i)) {
                            try {
                                jtn.this.i = Settings.Secure.getString(BaseApplication.getContext().getContentResolver(), "bluetooth_address");
                            } catch (SecurityException unused) {
                                LogUtil.b("VariableHandshakeMgr", "mPhoneIdentify exception");
                            }
                        } else {
                            jtn jtnVar = jtn.this;
                            jtnVar.i = jtnVar.c();
                        }
                    }
                }
                try {
                    if (TextUtils.isEmpty(jtn.this.i)) {
                        jtn.this.i = iyo.f();
                    }
                } catch (SocketException unused2) {
                    LogUtil.b("VariableHandshakeMgr", "mPhoneIdentify SocketException");
                }
                biz bizVar = new biz();
                bizVar.e(jtn.this.i);
                jtjVar.d(uniteDevice, bizVar, c2);
                bizVar.c(jtjVar.b(uniteDevice.getIdentify()));
                jtn.this.c.remove(uniteDevice.getIdentify());
                return new Gson().toJson(bizVar);
            }
            LogUtil.b("VariableHandshakeMgr", "preProcess error, device or data is null");
            return "";
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public int b(UniteDevice uniteDevice, String str, bir birVar) {
        if (uniteDevice == null || str == null || birVar == null) {
            LogUtil.b("VariableHandshakeMgr", "onFilter error, input parameter check failed");
            return 51;
        }
        this.b = uniteDevice.getIdentify();
        if ("service_capability_success".equalsIgnoreCase(str)) {
            ExternalDeviceCapability capability = uniteDevice.getCapability();
            if (capability == null) {
                LogUtil.b("VariableHandshakeMgr", "externalDeviceCapability is null");
                return 51;
            }
            DeviceCapability compatibleCapacity = capability.getCompatibleCapacity();
            if (compatibleCapacity == null) {
                LogUtil.b("VariableHandshakeMgr", "deviceCapability is null");
                return 51;
            }
            if (d(uniteDevice)) {
                return 51;
            }
            if (compatibleCapacity.isSupportAutoDetectMode()) {
                jti jtiVar = new jti();
                jtiVar.constructCommandMessage(birVar);
                this.j.put(uniteDevice.getIdentify(), jtiVar);
                return 50;
            }
            if (compatibleCapacity.isSupportAccountSwitch() || compatibleCapacity.isSupportChangePhonePair()) {
                jtk jtkVar = new jtk(uniteDevice);
                jtkVar.constructCommandMessage(birVar);
                this.j.put(uniteDevice.getIdentify(), jtkVar);
                return 50;
            }
            if (!compatibleCapacity.isSupportSettingRelated()) {
                return 52;
            }
            jtg jtgVar = new jtg();
            jtgVar.constructCommandMessage(birVar);
            this.j.put(uniteDevice.getIdentify(), jtgVar);
            return 50;
        }
        return c(uniteDevice, str, birVar);
    }

    private boolean d(UniteDevice uniteDevice) {
        boolean c2 = cwi.c(blc.c(uniteDevice), 110);
        LogUtil.a("VariableHandshakeMgr", "isDeviceIsNotSupport: ", Boolean.valueOf(c2));
        if (!c2 || !LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            return false;
        }
        LogUtil.a("VariableHandshakeMgr", "isKidAccount enter");
        if (e(uniteDevice)) {
            return false;
        }
        d(blc.c(uniteDevice));
        DeviceInfo deviceInfo = null;
        boolean z = false;
        for (DeviceInfo deviceInfo2 : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "VariableHandshakeMgr")) {
            if (deviceInfo2.getDeviceIdentify().equals(uniteDevice.getIdentify())) {
                deviceInfo = deviceInfo2;
                z = true;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(deviceInfo.getDeviceIdentify());
            jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
        } else {
            arrayList.add(blc.c(uniteDevice).getDeviceIdentify());
            jsz.b(BaseApplication.getContext()).unPair(arrayList, true);
        }
        return true;
    }

    private boolean e(final UniteDevice uniteDevice) {
        final boolean[] zArr = new boolean[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(HiDataSourceFetchOption.builder().a(0).e(), new HiDataClientListener() { // from class: jtn.2
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                String str;
                if (list == null) {
                    LogUtil.h("VariableHandshakeMgr", "clientList is null");
                    zArr[0] = false;
                    countDownLatch.countDown();
                    return;
                }
                String deviceName = uniteDevice.getDeviceInfo().getDeviceName();
                if (TextUtils.isEmpty(deviceName)) {
                    LogUtil.h("VariableHandshakeMgr", "currentDeviceName is empty.");
                    zArr[0] = true;
                    countDownLatch.countDown();
                    return;
                }
                String[] split = deviceName.split("\\-");
                if (split.length > 2) {
                    str = deviceName.replace(Constants.LINK + split[split.length - 1], "");
                } else if (split.length == 2) {
                    str = split[0];
                } else {
                    zArr[0] = true;
                    countDownLatch.countDown();
                    return;
                }
                if (jtn.this.d(str, list)) {
                    zArr[0] = true;
                } else {
                    zArr[0] = false;
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("VariableHandshakeMgr", "dealAidAccountConnect, InterruptedException");
        }
        return zArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str, List<HiHealthClient> list) {
        boolean z;
        Iterator<HiHealthClient> it = list.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            HiDeviceInfo hiDeviceInfo = it.next().getHiDeviceInfo();
            if (hiDeviceInfo != null) {
                String deviceName = hiDeviceInfo.getDeviceName();
                if (TextUtils.isEmpty(deviceName)) {
                    continue;
                } else {
                    String[] split = deviceName.split("\\-");
                    LogUtil.a("VariableHandshakeMgr", "deviceName:", deviceName);
                    if (split.length > 2) {
                        deviceName = deviceName.replace(Constants.LINK + split[split.length - 1], "");
                    }
                    if (split.length == 2) {
                        deviceName = split[0];
                    }
                    if (str.equalsIgnoreCase(deviceName)) {
                        LogUtil.a("VariableHandshakeMgr", "device equals deviceName:", deviceName, ", currentDeviceName:", str);
                        z = true;
                        break;
                    }
                }
            }
        }
        LogUtil.a("VariableHandshakeMgr", "isBindDevice：", Boolean.valueOf(z));
        return z;
    }

    private static void d(DeviceInfo deviceInfo) {
        deviceInfo.setDeviceConnectState(12);
        Intent bJq_ = bJq_(deviceInfo);
        LogUtil.a("VariableHandshakeMgr", "sendConnectStateBroadcast connectState is: ", 12);
        BaseApplication.getContext().sendOrderedBroadcast(bJq_, LocalBroadcast.c);
    }

    private static Intent bJq_(DeviceInfo deviceInfo) {
        LogUtil.a("VariableHandshakeMgr", "Enter createDeviceStateIntent.");
        Intent intent = new Intent("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intent.setExtrasClassLoader(DeviceInfo.class.getClassLoader());
        Bundle bundle = new Bundle();
        bundle.putParcelable("deviceinfo", deviceInfo);
        intent.putExtras(bundle);
        return intent;
    }

    private int c(UniteDevice uniteDevice, String str, bir birVar) {
        VariableHandshakeGeneralCommandBase variableHandshakeGeneralCommandBase = this.j.get(uniteDevice.getIdentify());
        if (variableHandshakeGeneralCommandBase == null) {
            LogUtil.h("VariableHandshakeMgr", "nextCommand is null");
            return 51;
        }
        int processReceivedData = variableHandshakeGeneralCommandBase.processReceivedData(uniteDevice, str);
        if (processReceivedData != 50) {
            return processReceivedData;
        }
        VariableHandshakeGeneralCommandBase nextCommand = variableHandshakeGeneralCommandBase.getNextCommand();
        this.j.put(uniteDevice.getIdentify(), nextCommand);
        return nextCommand.constructCommandMessage(birVar);
    }

    class d implements ServiceConnection {
        private CountDownLatch e;

        d(CountDownLatch countDownLatch) {
            this.e = countDownLatch;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.a("VariableHandshakeMgr", "iConnect service connected so start to set service handle");
            if (iBinder != null) {
                jtn.this.d = IWearConnectService.Stub.asInterface(iBinder);
                CountDownLatch countDownLatch = this.e;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    return;
                }
                return;
            }
            LogUtil.h("VariableHandshakeMgr", "service is null");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.h("VariableHandshakeMgr", "iConnect service disconnect so start to set service handle is null");
        }
    }

    public static jtn b() {
        return c.c;
    }

    public void e(boolean z, String str) {
        jtj jtjVar = this.c.get(str);
        if (jtjVar == null) {
            LogUtil.h("VariableHandshakeMgr", "notifyUserSelected creator is null");
        } else {
            jtjVar.c(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        return bld.d() && iyo.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        boolean z;
        LogUtil.a("VariableHandshakeMgr", "Enter bindIConnectService.");
        if (TextUtils.isEmpty(this.i)) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Intent intent = new Intent("com.huawei.iconnect.action.WEAR_CONNECT_SERVICE");
            intent.setPackage("com.huawei.iconnect");
            LogUtil.a("VariableHandshakeMgr", "start to bind iconnect service.");
            d dVar = new d(countDownLatch);
            try {
                z = BaseApplication.getContext().bindService(intent, dVar, 1);
            } catch (SecurityException unused) {
                LogUtil.b("VariableHandshakeMgr", "bindAndGetPhoneIndex SecurityException");
                z = false;
            }
            LogUtil.h("VariableHandshakeMgr", "bind iconnect result: ", Boolean.valueOf(z));
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused2) {
                LogUtil.b("VariableHandshakeMgr", "countDownLatch await exception");
            }
            this.i = d();
            bJr_(dVar);
        }
        return this.i;
    }

    private void bJr_(ServiceConnection serviceConnection) {
        LogUtil.a("VariableHandshakeMgr", "Enter unBindIConnectService.");
        try {
            this.d = null;
            BaseApplication.getContext().getApplicationContext().unbindService(serviceConnection);
        } catch (IllegalArgumentException unused) {
            LogUtil.h("VariableHandshakeMgr", "unBindIConnectService with IllegalArgumentException.");
        }
    }

    private String d() {
        IWearConnectService iWearConnectService = this.d;
        if (iWearConnectService != null) {
            try {
                return iWearConnectService.getHuaweiPhoneIndex();
            } catch (RemoteException e2) {
                LogUtil.h("VariableHandshakeMgr", "RemoteException: ", e2.getMessage());
            } catch (SecurityException e3) {
                LogUtil.h("VariableHandshakeMgr", "SecurityException: ", e3.getMessage());
            } finally {
                LogUtil.h("VariableHandshakeMgr", "finally");
            }
        } else {
            LogUtil.h("VariableHandshakeMgr", "mIConnectService is null.");
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            LogUtil.b("VariableHandshakeMgr", "json error");
            return null;
        }
    }

    public ConnectFilter a() {
        return this.f14072a;
    }

    static class c {
        private static jtn c = new jtn();
    }

    public VariableHandshakeGeneralCommandBase a(String str) {
        if (TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(this.b)) {
                VariableHandshakeGeneralCommandBase variableHandshakeGeneralCommandBase = this.j.get(this.b);
                Object[] objArr = new Object[2];
                objArr[0] = "getNextCommand identify is empty: ";
                objArr[1] = variableHandshakeGeneralCommandBase != null ? variableHandshakeGeneralCommandBase.toString() : "";
                LogUtil.b("VariableHandshakeMgr", objArr);
                return variableHandshakeGeneralCommandBase;
            }
            LogUtil.b("VariableHandshakeMgr", "mIdentify and identify are null");
            if (this.j.size() <= 0) {
                return null;
            }
            VariableHandshakeGeneralCommandBase value = this.j.entrySet().iterator().next().getValue();
            Object[] objArr2 = new Object[2];
            objArr2[0] = "getNextCommand mIdentify is empty: ";
            objArr2[1] = value != null ? value.toString() : "";
            LogUtil.b("VariableHandshakeMgr", objArr2);
            return value;
        }
        VariableHandshakeGeneralCommandBase variableHandshakeGeneralCommandBase2 = this.j.get(str);
        Object[] objArr3 = new Object[3];
        objArr3[0] = "getNextCommand: ";
        objArr3[1] = variableHandshakeGeneralCommandBase2 != null ? variableHandshakeGeneralCommandBase2.getCurrentCommandTag() : "";
        objArr3[2] = blt.a(str);
        LogUtil.a("VariableHandshakeMgr", objArr3);
        return variableHandshakeGeneralCommandBase2;
    }
}
