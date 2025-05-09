package com.huawei.hwdevice.mainprocess.mgr.hwpaymgr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.PayApduInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.WalletResponseListener;
import com.huawei.operation.utils.Constants;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jfq;
import defpackage.jku;
import defpackage.jkx;
import defpackage.jky;
import defpackage.jkz;
import defpackage.jla;
import defpackage.jlb;
import defpackage.jlc;
import defpackage.jlf;
import defpackage.jlh;
import defpackage.jqx;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class HwPayManager extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static HwPayManager c;

    /* renamed from: a, reason: collision with root package name */
    private ConnectStateChangeCallback f6312a;
    private Context e;
    private WalletResponseListener f;
    private cwl g;
    private final BroadcastReceiver h;
    private jfq j;
    private static Map<Integer, List<IBaseResponseCallback>> d = new HashMap(0);
    private static List<Integer> b = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24));

    public interface ConnectStateChangeCallback {
        void onConnectStateChange(int i);
    }

    private HwPayManager(Context context) {
        super(context);
        this.f6312a = null;
        this.g = new cwl();
        this.h = new BroadcastReceiver() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HwPayManager", "mConnectStateChangedReceiver intent is null");
                    return;
                }
                LogUtil.a("HwPayManager", "HwPayManager connectedChanged mNonLocalBroadcastReceiver: intent : ", intent.getAction());
                String action = intent.getAction();
                if (action == null) {
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    if (parcelableExtra == null || !(parcelableExtra instanceof DeviceInfo)) {
                        LogUtil.h("HwPayManager", "deviceInfo is null!");
                        return;
                    }
                    DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                    if (!TextUtils.isEmpty(deviceInfo.getRelationship()) && !"main_relationship".equals(deviceInfo.getRelationship())) {
                        LogUtil.a("HwPayManager", "This device does not have the correspond capability.");
                        return;
                    }
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    LogUtil.c("HwPayManager", "connectedChanged: ", deviceInfo.getDeviceName(), ",state : ", Integer.valueOf(deviceConnectState));
                    HwPayManager.this.c(deviceConnectState);
                    if (HwPayManager.this.f6312a != null) {
                        HwPayManager.this.f6312a.onConnectStateChange(deviceConnectState);
                    }
                    WalletResponseListener walletResponseListener = HwPayManager.this.f;
                    if (walletResponseListener == null || !jqx.e(deviceInfo)) {
                        return;
                    }
                    LogUtil.a("HwPayManager", "connectedChanged response to aar,state:", Integer.valueOf(deviceConnectState));
                    walletResponseListener.onResponse(2, deviceConnectState, Integer.valueOf(deviceInfo.getProductType()));
                    return;
                }
                LogUtil.h("HwPayManager", "deviceInfo is null!");
            }
        };
        this.e = context;
        jfq c2 = jfq.c();
        this.j = c2;
        Object[] objArr = new Object[2];
        objArr[0] = "HwPayManager(), mHwDeviceConfigManager : ";
        objArr[1] = c2 == null ? Constants.NULL : c2;
        LogUtil.a("HwPayManager", objArr);
        if (this.j != null) {
            ReleaseLogUtil.e("R_HwPayManager", "registerIBaseResponseCallback ");
            this.j.e(27, this);
        } else {
            ReleaseLogUtil.d("R_HwPayManager", "HwPayManager mHwDeviceConfigManager is null");
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i != 2) {
            Iterator<Integer> it = b.iterator();
            while (it.hasNext()) {
                c(it.next().intValue(), 100001, (Object) (-1));
            }
        }
    }

    public static HwPayManager a() {
        HwPayManager hwPayManager;
        LogUtil.a("HwPayManager", "getInstance(), instance : ", c);
        synchronized (d()) {
            if (d.size() == 0) {
                Iterator<Integer> it = b.iterator();
                while (it.hasNext()) {
                    d.put(it.next(), new ArrayList(0));
                }
            }
            if (c == null) {
                c = new HwPayManager(BaseApplication.getContext());
            }
            hwPayManager = c;
        }
        return hwPayManager;
    }

    public void b(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        String str2;
        String str3;
        String str4;
        b();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(1);
        if (str != null) {
            String d2 = cvx.d(str.length() / 2);
            str4 = cvx.e(1);
            str3 = d2;
            str2 = str;
        } else {
            str2 = "";
            str3 = "";
            str4 = str3;
        }
        String e = cvx.e(i);
        String d3 = cvx.d(e.length() / 2);
        String e2 = cvx.e(2);
        int length = str4.length() / 2;
        int length2 = str3.length() / 2;
        int length3 = str2.length() / 2;
        int length4 = e2.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (d3.length() / 2) + (e.length() / 2));
        allocate.put(cvx.a(str4));
        allocate.put(cvx.a(str3));
        allocate.put(cvx.a(str2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e));
        d(deviceCommand, allocate, 1, iBaseResponseCallback);
    }

    public void d(PayApduInfo payApduInfo, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HwPayManager", "executeApdu: ", payApduInfo.toString());
        b();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(2);
        String b2 = cvx.b(payApduInfo.getReqid());
        String d2 = cvx.d(b2.length() / 2);
        String e = cvx.e(1);
        String apdu = payApduInfo.getApdu();
        String d3 = cvx.d(apdu.length() / 2);
        String e2 = cvx.e(2);
        String e3 = cvx.e(payApduInfo.getChannelId());
        String d4 = cvx.d(e3.length() / 2);
        String e4 = cvx.e(3);
        int length = e.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = b2.length() / 2;
        int length4 = e2.length() / 2;
        int length5 = d3.length() / 2;
        int length6 = apdu.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + length5 + length6 + (e4.length() / 2) + (d4.length() / 2) + (e3.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(b2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(apdu));
        allocate.put(cvx.a(e4));
        allocate.put(cvx.a(d4));
        allocate.put(cvx.a(e3));
        LogUtil.c("HwPayManager", "executeApdu byteBufferAll: ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 2, iBaseResponseCallback);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HwPayManager", "closeChannel callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(3);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((e.length() / 2) + (d2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        LogUtil.c("HwPayManager", "closeChannel byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 3, iBaseResponseCallback);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwPayManager", "getCplc callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(4);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((e.length() / 2) + (d2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        LogUtil.c("HwPayManager", "getCplc byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 4, iBaseResponseCallback);
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HwPayManager", "getBtcInfo callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(5);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((e.length() / 2) + (d2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        LogUtil.c("HwPayManager", "getBtcInfo byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 5, iBaseResponseCallback);
    }

    public void e(List<jlb> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HwPayManager", "transmitFile callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(7);
        StringBuffer stringBuffer = new StringBuffer(0);
        for (int i = 1; i <= list.size(); i++) {
            int i2 = i - 1;
            String e = list.get(i2).e();
            int b2 = list.get(i2).b();
            String c2 = cvx.c(e);
            String d2 = cvx.d(c2.length() / 2);
            String e2 = cvx.e(4);
            String e3 = cvx.e(b2);
            String d3 = cvx.d(e3.length() / 2);
            String e4 = cvx.e(3);
            String str = e4 + d3 + e3 + e2 + d2 + c2;
            String d4 = cvx.d((((((e4.length() + d3.length()) + e3.length()) + e2.length()) + d2.length()) + c2.length()) / 2);
            stringBuffer = stringBuffer.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + d4 + str);
        }
        String stringBuffer2 = stringBuffer.toString();
        String d5 = cvx.d(stringBuffer2.length() / 2);
        String e5 = cvx.e(129);
        ByteBuffer allocate = ByteBuffer.allocate((e5.length() / 2) + (d5.length() / 2) + (stringBuffer2.length() / 2));
        allocate.put(cvx.a(e5 + d5 + stringBuffer2));
        LogUtil.c("HwPayManager", "transmitFile , byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 7, iBaseResponseCallback);
    }

    public void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwPayManager", "addCard, payCardInfo: ", CommonUtil.l(str));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(8);
        String a2 = a(str);
        ByteBuffer allocate = ByteBuffer.allocate(a2.length() / 2);
        allocate.put(cvx.a(a2));
        d(deviceCommand, allocate, 8, iBaseResponseCallback);
    }

    private String a(String str) {
        LogUtil.c("HwPayManager", "cardInfoTlv, payCardInfo: ", str);
        jlf jlfVar = new jlf(str);
        String c2 = jlfVar.c();
        String str2 = "";
        if (!c(c2)) {
            String c3 = cvx.c(c2);
            String d2 = cvx.d(c3.length() / 2);
            str2 = "" + cvx.e(1) + d2 + c3;
        } else {
            LogUtil.h("HwPayManager", "aid is null");
        }
        String n = jlfVar.n();
        if (c(n)) {
            n = "productId";
        }
        String c4 = cvx.c(n);
        String d3 = cvx.d(c4.length() / 2);
        String str3 = str2 + cvx.e(2) + d3 + c4;
        String o = jlfVar.o();
        LogUtil.c("HwPayManager", "cardInfoTlv, name: ", o);
        String c5 = cvx.c(o);
        String d4 = cvx.d(c5.length() / 2);
        String str4 = str3 + cvx.e(3) + d4 + c5;
        String l = jlfVar.l();
        if (c(l)) {
            l = "issuerId";
        }
        String c6 = cvx.c(l);
        String d5 = cvx.d(c6.length() / 2);
        String str5 = str4 + cvx.e(4) + d5 + c6;
        int a2 = jlfVar.a();
        if (a2 == 2) {
            a2 = 0;
        }
        String e = cvx.e(a2);
        String d6 = cvx.d(e.length() / 2);
        return c(jlfVar, e(jlfVar, d(jlfVar, str5 + cvx.e(5) + d6 + e)));
    }

    private String d(jlf jlfVar, String str) {
        String e = cvx.e(jlfVar.s() ? 1 : 0);
        String d2 = cvx.d(e.length() / 2);
        String str2 = str + cvx.e(6) + d2 + e;
        String g = jlfVar.g();
        if (c(g)) {
            g = "";
        }
        String c2 = cvx.c(g);
        String d3 = cvx.d(c2.length() / 2);
        String str3 = str2 + cvx.e(7) + d3 + c2;
        String f = jlfVar.f();
        if (c(f)) {
            f = "";
        }
        String c3 = cvx.c(f);
        String d4 = cvx.d(c3.length() / 2);
        String str4 = str3 + cvx.e(8) + d4 + c3;
        String j = jlfVar.j();
        LogUtil.c("HwPayManager", "cardInfoTlvNumber, dpanDigest: ", j);
        if (c(j)) {
            j = jlfVar.c();
        }
        String c4 = cvx.c(j);
        String d5 = cvx.d(c4.length() / 2);
        String str5 = str4 + cvx.e(9) + d5 + c4;
        String h = jlfVar.h();
        String c5 = cvx.c(c(h) ? "" : h);
        String d6 = cvx.d(c5.length() / 2);
        return str5 + cvx.e(10) + d6 + c5;
    }

    private String e(jlf jlfVar, String str) {
        int i = jlfVar.i();
        String e = cvx.e(jlfVar.e());
        String d2 = cvx.d(e.length() / 2);
        String str2 = str + cvx.e(11) + d2 + e;
        String b2 = cvx.b(jlfVar.t());
        String d3 = cvx.d(b2.length() / 2);
        String str3 = str2 + cvx.e(12) + d3 + b2;
        String e2 = cvx.e(i);
        String d4 = cvx.d(e2.length() / 2);
        String str4 = str3 + cvx.e(13) + d4 + e2;
        String b3 = jlfVar.b();
        if (c(b3)) {
            b3 = "background_file_name";
        }
        String c2 = cvx.c(b3);
        String d5 = cvx.d(c2.length() / 2);
        String str5 = str4 + cvx.e(14) + d5 + c2;
        String b4 = cvx.b(jlfVar.d());
        String d6 = cvx.d(b4.length() / 2);
        String str6 = str5 + cvx.e(15) + d6 + b4;
        String k = jlfVar.k();
        if (c(k)) {
            k = "Rf_file_name";
        }
        String c3 = cvx.c(k);
        String d7 = cvx.d(c3.length() / 2);
        String str7 = str6 + cvx.e(16) + d7 + c3;
        String b5 = cvx.b(jlfVar.m());
        String d8 = cvx.d(b5.length() / 2);
        return str7 + cvx.e(17) + d8 + b5;
    }

    private String c(jlf jlfVar, String str) {
        if (jlfVar.a() != 2) {
            return str;
        }
        String r = jlfVar.r();
        if (c(r)) {
            return str;
        }
        String c2 = cvx.c(r);
        String d2 = cvx.d(c2.length() / 2);
        String str2 = str + cvx.e(18) + d2 + c2;
        LogUtil.a("HwPayManager", "cardInfoTlv, Bluetooth command : ", str2);
        return str2;
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwPayManager", "updateCard, payCardInfo: ", CommonUtil.l(str));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(9);
        String a2 = a(str);
        ByteBuffer allocate = ByteBuffer.allocate(a2.length() / 2);
        allocate.put(cvx.a(a2));
        d(deviceCommand, allocate, 9, iBaseResponseCallback);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(10);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((d2.length() / 2) + (e.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        d(deviceCommand, allocate, 10, iBaseResponseCallback);
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        String str2;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(11);
        jlf jlfVar = new jlf(str);
        String c2 = jlfVar.c();
        if (jlfVar.a() == 1) {
            c2 = jlfVar.j();
            LogUtil.h("HwPayManager", "bank card, refId : ", c2);
        }
        if (!c(c2)) {
            String c3 = cvx.c(c2);
            String d2 = cvx.d(c3.length() / 2);
            str2 = cvx.e(1) + d2 + c3;
        } else {
            LogUtil.h("HwPayManager", "refId is null");
            str2 = "";
        }
        ByteBuffer allocate = ByteBuffer.allocate(str2.length() / 2);
        allocate.put(cvx.a(str2));
        d(deviceCommand, allocate, 11, iBaseResponseCallback);
    }

    public void d(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(13);
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        String e = cvx.e(1);
        String e2 = cvx.e(i);
        String d3 = cvx.d(e2.length() / 2);
        String e3 = cvx.e(2);
        int length = c2.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = e.length() / 2;
        int length4 = e2.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (d3.length() / 2) + (e3.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(c2));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e2));
        d(deviceCommand, allocate, 13, iBaseResponseCallback);
    }

    public void c(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(5);
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        String e = cvx.e(3);
        String c3 = cvx.c(str2);
        String d3 = cvx.d(c3.length() / 2);
        String e2 = cvx.e(7);
        String c4 = cvx.c(str3);
        String d4 = cvx.d(c4.length() / 2);
        String e3 = cvx.e(8);
        int length = e.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = c2.length() / 2;
        int length4 = e2.length() / 2;
        int length5 = d3.length() / 2;
        int length6 = c3.length() / 2;
        int length7 = e3.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + length5 + length6 + length7 + (d4.length() / 2) + (c4.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(c2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(c3));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d4));
        allocate.put(cvx.a(c4));
        d(deviceCommand, allocate, 5, iBaseResponseCallback);
    }

    private void d(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (d()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = d.get(Integer.valueOf(i));
                if (list != null) {
                    ReleaseLogUtil.e("R_HwPayManager", "addToList, callbacks is not null");
                    list.add(iBaseResponseCallback);
                } else {
                    ReleaseLogUtil.d("R_HwPayManager", "addToList callbacks is null");
                }
            } else {
                ReleaseLogUtil.d("R_HwPayManager", "addToList callback is null");
            }
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        this.j.b(deviceCommand);
    }

    private void b(byte[] bArr) {
        Object e;
        Object obj;
        int d2 = d(bArr);
        int i = d2 == 100000 ? 0 : -1;
        ReleaseLogUtil.e("R_HwPayManager", "getResult id", Byte.valueOf(bArr[0]));
        if (bArr[1] == 3) {
            i();
        }
        switch (bArr[1]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 10:
            case 15:
                jky d3 = d(i, bArr, d2);
                i = d3.d();
                e = d3.e();
                int i2 = i;
                obj = e;
                d2 = i2;
                break;
            case 6:
            case 17:
            case 18:
            default:
                e = b(bArr, d2);
                int i22 = i;
                obj = e;
                d2 = i22;
                break;
            case 7:
            case 8:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
                e = Integer.valueOf(d2);
                int i222 = i;
                obj = e;
                d2 = i222;
                break;
            case 12:
                e = j(bArr);
                if (e != null) {
                    obj = e;
                    d2 = 0;
                    break;
                }
                int i2222 = i;
                obj = e;
                d2 = i2222;
                break;
            case 19:
                obj = Integer.valueOf(d2);
                break;
        }
        c(bArr[1], d2, obj);
    }

    private jky d(int i, byte[] bArr, int i2) {
        byte b2 = bArr[1];
        if (b2 == 1) {
            return new jkx().a(i, bArr, i2);
        }
        if (b2 == 2) {
            return new jkx().d(i, bArr, i2);
        }
        if (b2 == 3) {
            return new jkx().b(i, bArr, i2);
        }
        if (b2 == 4) {
            return c(i, bArr, i2);
        }
        if (b2 == 5) {
            return b(i, bArr, i2);
        }
        if (b2 == 10) {
            return c(i, bArr);
        }
        if (b2 == 15) {
            return a(i, bArr);
        }
        jky jkyVar = new jky();
        jkyVar.e(-1);
        jkyVar.e("");
        return jkyVar;
    }

    private jky c(int i, byte[] bArr, int i2) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseGetCplc length is outOfIndex");
            return jkyVar;
        }
        try {
            for (cwd cwdVar : this.g.a(d2.substring(4, d2.length())).e()) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 1) {
                    jkyVar.e(0);
                    jkyVar.e(cwdVar.c());
                } else if (a2 == 127) {
                    jkyVar.e(Integer.valueOf(i2));
                } else {
                    LogUtil.h("HwPayManager", "parseGetCplc default.");
                }
            }
        } catch (cwg e) {
            LogUtil.b("HwPayManager", "parseGetCplc error : ", e.getMessage());
        }
        return jkyVar;
    }

    private jky b(int i, byte[] bArr, int i2) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseGetBtc length is outOfIndex");
            return jkyVar;
        }
        try {
            for (cwd cwdVar : this.g.a(d2.substring(4, d2.length())).e()) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 1) {
                    jkyVar.e(0);
                    jkyVar.e(cwdVar.c());
                } else if (a2 == 127) {
                    jkyVar.e(Integer.valueOf(i2));
                } else {
                    LogUtil.h("HwPayManager", "parseGetBtc default.");
                }
            }
        } catch (cwg e) {
            LogUtil.b("HwPayManager", "parseGetBtc error : ", e.getMessage());
        }
        return jkyVar;
    }

    private jky a(int i, byte[] bArr) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseGetTagStatus length is outOfIndex");
            return jkyVar;
        }
        try {
            for (cwd cwdVar : this.g.a(d2.substring(4)).e()) {
                if (CommonUtil.a(cwdVar.e(), 16) == 1) {
                    jkyVar.e(0);
                    jkyVar.e(cwdVar.c());
                    LogUtil.a("HwPayManager", "parseGetTagStatus status : ", cwdVar.c());
                } else {
                    LogUtil.h("HwPayManager", "parseGetTagStatus status default");
                }
            }
        } catch (cwg e) {
            LogUtil.b("HwPayManager", "parseGetTagStatus error : ", e.getMessage());
        }
        return jkyVar;
    }

    private jky c(int i, byte[] bArr) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d2 = cvx.d(bArr);
        LogUtil.c("HwPayManager", "List,messageHex : ", d2);
        if (d2 != null && d2.length() > 4) {
            try {
                List<cwe> a2 = new cwl().a(d2.substring(4, d2.length())).a();
                ArrayList arrayList = new ArrayList(0);
                jkyVar.e(0);
                Iterator<cwe> it = a2.iterator();
                while (it.hasNext()) {
                    Iterator<cwe> it2 = it.next().a().iterator();
                    while (it2.hasNext()) {
                        List<cwd> e = it2.next().e();
                        LogUtil.c("HwPayManager", "List,childtlv : ", Integer.valueOf(e.size()));
                        arrayList.add(e(e).p());
                    }
                }
                jkyVar.e(arrayList);
            } catch (cwg unused) {
                LogUtil.b("HwPayManager", "cardList TLV error");
            }
        }
        return jkyVar;
    }

    private jlf e(List<cwd> list) {
        jlf jlfVar = new jlf();
        for (cwd cwdVar : list) {
            LogUtil.c("HwPayManager", "List getTag : ", cwdVar.e());
            switch (CommonUtil.a(cwdVar.e(), 16)) {
                case 3:
                    jlfVar.b(cvx.e(cwdVar.c()));
                    break;
                case 4:
                    jlfVar.g(cvx.e(cwdVar.c()));
                    break;
                case 5:
                    jlfVar.f(cvx.e(cwdVar.c()));
                    break;
                case 6:
                    jlfVar.h(cvx.e(cwdVar.c()));
                    break;
                case 7:
                    jlfVar.b(CommonUtil.a(cwdVar.c(), 16));
                    if (jlfVar.a() == 0) {
                        jlfVar.b(2);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (CommonUtil.a(cwdVar.c(), 16) == 1) {
                        jlfVar.d(true);
                        break;
                    } else {
                        jlfVar.d(false);
                        break;
                    }
                case 9:
                    jlfVar.c(cvx.e(cwdVar.c()));
                    break;
                default:
                    a(cwdVar, jlfVar);
                    break;
            }
        }
        return jlfVar;
    }

    private void a(cwd cwdVar, jlf jlfVar) {
        switch (CommonUtil.a(cwdVar.e(), 16)) {
            case 10:
                jlfVar.i(cvx.e(cwdVar.c()));
                break;
            case 11:
                jlfVar.a(cvx.e(cwdVar.c()));
                break;
            case 12:
                jlfVar.e(cvx.e(cwdVar.c()));
                break;
            case 13:
                jlfVar.d(CommonUtil.a(cwdVar.c(), 16));
                break;
            case 14:
                jlfVar.b(CommonUtil.y(cwdVar.c()));
                break;
            case 15:
                jlfVar.a(CommonUtil.a(cwdVar.c(), 16));
                break;
            case 16:
                jlfVar.d(cvx.e(cwdVar.c()));
                break;
            case 17:
                jlfVar.a(CommonUtil.b(cwdVar.c(), 16L));
                break;
            case 18:
                jlfVar.j(cvx.e(cwdVar.c()));
                break;
            case 19:
                jlfVar.c(CommonUtil.y(cwdVar.c()));
                break;
            case 20:
                if (!jku.a(cwdVar)) {
                    jlfVar.l(cvx.e(cwdVar.c()));
                    break;
                }
                break;
            default:
                LogUtil.h("HwPayManager", "parseOtherCardInfo default.");
                break;
        }
    }

    private void c(int i, int i2, Object obj) {
        synchronized (d()) {
            List<IBaseResponseCallback> list = d.get(Integer.valueOf(i));
            if (list != null) {
                if (obj != null && list.size() != 0) {
                    ReleaseLogUtil.e("R_HwPayManager", "processCallback callback, commandID: ", Integer.valueOf(i));
                    list.get(0).d(i2, obj);
                    list.remove(0);
                } else if (list.size() != 0) {
                    list.get(0).d(100001, "UNKNOWN_ERROR");
                    list.remove(0);
                }
            } else {
                ReleaseLogUtil.e("R_HwPayManager", "processCallback callback, callbackList is null");
            }
        }
    }

    private int d(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 8) {
            LogUtil.h("HwPayManager", "getInt length is outOfIndex");
            return 0;
        }
        try {
            return CommonUtil.a(d2.substring(8, d2.length()), 16);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 27;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.j.d(27);
        ReleaseLogUtil.e("R_HwPayManager", "onDestroy unRegisterManagerResponseCallback");
        synchronized (d()) {
            for (int i = 0; i < b.size(); i++) {
                if (d.get(b.get(i)) != null) {
                    d.get(b.get(i)).clear();
                }
            }
        }
        j();
        ReleaseLogUtil.e("R_HwPayManager", "onDestroy");
        this.f6312a = null;
        c = null;
        ReleaseLogUtil.e("R_HwPayManager", "onDestroy complete");
    }

    private static Object d() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (HwPayManager.class) {
            map = d;
        }
        return map;
    }

    public void g(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(12);
        String e = cvx.e(1);
        String d2 = cvx.d(0);
        String e2 = cvx.e(1);
        String d3 = cvx.d(e2.length() / 2);
        String e3 = cvx.e(7);
        int length = d2.length() / 2;
        int length2 = e.length() / 2;
        int length3 = e3.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + (d3.length() / 2) + (e2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e2));
        d(deviceCommand, allocate, 12, iBaseResponseCallback);
    }

    private boolean c(String str) {
        return TextUtils.isEmpty(str);
    }

    public void b(ConnectStateChangeCallback connectStateChangeCallback) {
        this.f6312a = connectStateChangeCallback;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr != null) {
            ReleaseLogUtil.e("R_HwPayManager", "onDataReceived serviceId is,", Integer.valueOf(bArr[0]), "commandId is,", Integer.valueOf(bArr[1]));
            LogUtil.a("HwPayManager", "Pay manager receive data : ", cvx.d(bArr));
            ReleaseLogUtil.e("R_HwPayManager", "Pay manager receive data is ok");
            if (jqx.e(deviceInfo) && jqx.e(bArr)) {
                ReleaseLogUtil.e("R_HwPayManager", "isWalletOpenCard and isWalletAppIdCommand");
                c(i, (Object) bArr);
                return;
            } else {
                b(bArr);
                return;
            }
        }
        ReleaseLogUtil.e("R_HwPayManager", "data is null");
    }

    private void j() {
        try {
            LogUtil.a("HwPayManager", "Enter unregisterNonLocalBroadcast!");
            this.e.unregisterReceiver(this.h);
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwPayManager", e.getMessage());
        }
    }

    private void e() {
        BroadcastManagerUtil.bFC_(this.e, this.h, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public void a(String str, Map<String, String> map, List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(14);
        String str2 = b(str) + a(map) + c(list);
        ByteBuffer allocate = ByteBuffer.allocate(str2.length() / 2);
        allocate.put(cvx.a(str2));
        d(deviceCommand, allocate, 14, iBaseResponseCallback);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String a(Map<String, String> map) {
        char c2;
        String str = "";
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String c3 = cvx.c(entry.getValue());
                String d2 = cvx.d(c3.length() / 2);
                key.hashCode();
                switch (key.hashCode()) {
                    case -1733378447:
                        if (key.equals("trans_type")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -1181248900:
                        if (key.equals("terminal")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -339185956:
                        if (key.equals("balance")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 96712:
                        if (key.equals("amt")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 351608024:
                        if (key.equals("version")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                if (c2 == 0) {
                    str = str + (cvx.e(3) + d2 + c3);
                } else if (c2 == 1) {
                    str = str + (cvx.e(6) + d2 + c3);
                } else if (c2 == 2) {
                    str = str + (cvx.e(5) + d2 + c3);
                } else if (c2 == 3) {
                    str = str + (cvx.e(4) + d2 + c3);
                } else if (c2 == 4) {
                    str = str + (cvx.e(2) + d2 + c3);
                } else {
                    LogUtil.h("hci does not have this field:", key);
                }
            }
        }
        return str;
    }

    private String b(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        return cvx.e(1) + d2 + c2;
    }

    private String c(List<String> list) {
        String str = "";
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(0);
        for (int i = 0; i < list.size() - 1; i++) {
            if (i == 0 || i == list.size() - 1) {
                stringBuffer.append(list.get(i));
            } else {
                stringBuffer.append("|");
                stringBuffer.append(list.get(i));
            }
        }
        String c2 = cvx.c(stringBuffer.toString());
        String d2 = cvx.d(c2.length() / 2);
        String str2 = cvx.e(7) + d2 + c2;
        if (list.size() > 1) {
            String c3 = cvx.c(list.get(list.size() - 1));
            String d3 = cvx.d(c3.length() / 2);
            str = cvx.e(8) + d3 + c3;
        }
        return str2 + str;
    }

    private String a(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "getParsePayAbilityMessage length is outOfIndex");
            return "";
        }
        return d2.substring(4, d2.length());
    }

    private Object j(byte[] bArr) {
        try {
            cwe a2 = this.g.a(a(bArr));
            jlh jlhVar = new jlh();
            for (cwd cwdVar : a2.e()) {
                switch (CommonUtil.a(cwdVar.e(), 16)) {
                    case 1:
                        jlhVar.a(cvx.e(cwdVar.c()));
                        break;
                    case 2:
                        jlhVar.b(cvx.e(cwdVar.c()));
                        break;
                    case 3:
                        jlhVar.a(CommonUtil.a(cwdVar.c(), 16));
                        break;
                    case 4:
                        jlhVar.d(CommonUtil.a(cwdVar.c(), 16));
                        break;
                    case 5:
                        jlhVar.e(CommonUtil.a(cwdVar.c(), 16));
                        break;
                    case 6:
                        jlhVar.b(CommonUtil.a(cwdVar.c(), 16));
                        break;
                    case 7:
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "parsePayAbility default.";
                        LogUtil.h("HwPayManager", objArr);
                        break;
                    case 8:
                        jlhVar.c(cwdVar.c());
                        break;
                    case 9:
                        jlhVar.e(cvx.e(cwdVar.c()));
                        break;
                }
            }
            return jlhVar.b();
        } catch (cwg | NumberFormatException e) {
            LogUtil.b("HwPayManager", "getResult COMMAND_ID_PAY_ABILITY error : ", e.getMessage());
            return null;
        }
    }

    public void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(16);
        d(deviceCommand, ByteBuffer.wrap(cvx.a(cvx.e(1) + cvx.e(1) + cvx.e(i))), 16, iBaseResponseCallback);
    }

    public void j(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(17);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((e.length() / 2) + (d2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        LogUtil.c("HwPayManager", "getNfcTagInfo byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 17, iBaseResponseCallback);
    }

    private Object b(byte[] bArr, int i) {
        byte b2 = bArr[1];
        switch (b2) {
            case 17:
                return g(bArr);
            case 18:
                return c(bArr);
            case 19:
            default:
                LogUtil.h("HwPayManager", "not support command Type:", Byte.valueOf(b2));
                return null;
            case 20:
                return e(bArr);
            case 21:
            case 22:
                return Integer.valueOf(i);
            case 23:
                return jku.a(bArr);
            case 24:
                return jku.c(bArr);
        }
    }

    private Object g(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseNfcTagInfo length is outOfIndex");
            return "";
        }
        try {
            cwe a2 = this.g.a(d2.substring(4, d2.length()));
            jlc jlcVar = new jlc();
            for (cwd cwdVar : a2.e()) {
                LogUtil.a("HwPayManager", "[parseNfcTagInfo]tlv:", cwdVar.toString());
                e(CommonUtil.a(cwdVar.e(), 16), jlcVar, cwdVar);
            }
            return jlcVar.c();
        } catch (cwg | NumberFormatException e) {
            LogUtil.b("HwPayManager", "parseNfcTagInfo errorMsg : ", e.getMessage());
            return null;
        }
    }

    private void e(int i, jlc jlcVar, cwd cwdVar) {
        LogUtil.c("HwPayManager", "parseNfcTagResponse tlvType : ", Integer.valueOf(i), ", tlvValue:", cwdVar.c());
        if (i != 127) {
            switch (i) {
                case 2:
                    jlcVar.e(cvx.e(cwdVar.c()));
                    break;
                case 3:
                    jlcVar.b(CommonUtil.a(cwdVar.c(), 16));
                    break;
                case 4:
                    jlcVar.e(CommonUtil.a(cwdVar.c(), 16));
                    break;
                case 5:
                    jlcVar.c(CommonUtil.a(cwdVar.c(), 16));
                    break;
                case 6:
                    jlcVar.h(cwdVar.c());
                    break;
                case 7:
                    jlcVar.c(cwdVar.c());
                    break;
                case 8:
                    jlcVar.a(CommonUtil.a(cwdVar.c(), 16));
                    break;
                case 9:
                    jlcVar.b(cwdVar.c());
                    break;
                case 10:
                    jlcVar.a(cwdVar.c());
                    break;
                case 11:
                    jlcVar.d(cwdVar.c());
                    break;
                default:
                    LogUtil.h("HwPayManager", "not support Type:", Integer.valueOf(i));
                    break;
            }
        }
        jlcVar.d(CommonUtil.a(cwdVar.c(), 16));
    }

    public void d(int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (str == null || str.isEmpty()) {
            LogUtil.h("HwPayManager", "sendNfcCommand command is null or empty");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(18);
        String e = cvx.e(i);
        String d2 = cvx.d(e.length() / 2);
        String e2 = cvx.e(1);
        String d3 = cvx.d(str.length() / 2);
        String e3 = cvx.e(2);
        int length = e2.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = e.length() / 2;
        int length4 = e3.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (d3.length() / 2) + (str.length() / 2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(str));
        LogUtil.c("HwPayManager", "sendNfcCommand byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 18, iBaseResponseCallback);
    }

    private Object c(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseNfcCommand length is outOfIndex");
            return null;
        }
        try {
            cwe a2 = this.g.a(d2.substring(4, d2.length()));
            jkz jkzVar = new jkz();
            for (cwd cwdVar : a2.e()) {
                LogUtil.c("HwPayManager", "parseNfcCommand tlvType : ", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
                int a3 = CommonUtil.a(cwdVar.e(), 16);
                if (a3 == 3) {
                    jkzVar.e(cwdVar.c());
                } else if (a3 == 127) {
                    jkzVar.c(CommonUtil.a(cwdVar.c(), 16));
                } else {
                    LogUtil.h("not support tlbType!", new Object[0]);
                }
            }
            return jkzVar.a();
        } catch (cwg | NumberFormatException e) {
            LogUtil.b("HwPayManager", "parseNfcCommand error : ", e.getMessage());
            return null;
        }
    }

    public void b(byte[] bArr, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HwPayManager", "aulthenticateMifareSector callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(19);
        String d2 = cvx.d(bArr);
        if (d2 == null) {
            return;
        }
        String d3 = cvx.d(d2.length() / 2);
        String e = cvx.e(1);
        String e2 = cvx.e(i);
        String d4 = cvx.d(e2.length() / 2);
        String e3 = cvx.e(2);
        String e4 = cvx.e(i2);
        String d5 = cvx.d(e4.length() / 2);
        String e5 = cvx.e(3);
        int length = e.length() / 2;
        int length2 = d3.length() / 2;
        int length3 = d2.length() / 2;
        int length4 = e3.length() / 2;
        int length5 = d4.length() / 2;
        int length6 = e2.length() / 2;
        int length7 = e5.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + length5 + length6 + length7 + (d5.length() / 2) + (e4.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d4));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(e5));
        allocate.put(cvx.a(d5));
        allocate.put(cvx.a(e4));
        LogUtil.c("HwPayManager", "aulthenticateMifareSector byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 19, iBaseResponseCallback);
    }

    public void d(byte[] bArr, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        String str;
        String str2;
        String str3;
        LogUtil.c("HwPayManager", "getBlockData callbacks : ", iBaseResponseCallback);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(20);
        if (bArr != null) {
            str = cvx.d(bArr);
            str2 = cvx.d(str.length() / 2);
            str3 = cvx.e(1);
        } else {
            str = "";
            str2 = "";
            str3 = str2;
        }
        String e = cvx.e(i);
        String d2 = cvx.d(e.length() / 2);
        String e2 = cvx.e(2);
        String e3 = cvx.e(i2);
        String d3 = cvx.d(e3.length() / 2);
        String e4 = cvx.e(3);
        int length = e2.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = length + length2 + (e.length() / 2) + (e4.length() / 2) + (d3.length() / 2) + (e3.length() / 2);
        if (bArr != null) {
            length3 = length3 + (str3.length() / 2) + (str2.length() / 2) + (str.length() / 2);
        }
        ByteBuffer allocate = ByteBuffer.allocate(length3);
        if (bArr != null) {
            allocate.put(cvx.a(str3));
            allocate.put(cvx.a(str2));
            allocate.put(cvx.a(str));
        }
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(e4));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e3));
        LogUtil.c("HwPayManager", "getBlockData byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 20, iBaseResponseCallback);
    }

    private Object e(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwPayManager", "parseMiFareBlockInfo length is outOfIndex");
            return null;
        }
        String substring = d2.substring(4, d2.length());
        try {
            jla jlaVar = new jla();
            cwe a2 = this.g.a(substring);
            jlaVar.e(b(a2.e()));
            Iterator<cwe> it = a2.a().iterator();
            while (it.hasNext()) {
                for (cwd cwdVar : it.next().e()) {
                    LogUtil.c("HwPayManager", "parseMiFareBlockInfo tlvType : ", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
                    if (CommonUtil.a(cwdVar.e(), 16) == 5) {
                        jlaVar.b(cwdVar.c());
                    } else {
                        LogUtil.h("not support tlbType!", new Object[0]);
                    }
                }
            }
            return jlaVar.a();
        } catch (cwg | NumberFormatException e) {
            LogUtil.b("HwPayManager", "getResult parseMiFareBlockInfo error : ", e.getMessage());
            return null;
        }
    }

    public void b(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(21);
        if (str2 == null || str2.isEmpty()) {
            LogUtil.h("HwPayManager", "[sendAccessCardRfInfo] rfinfo is null or emtpy");
            return;
        }
        if (str == null || str.isEmpty()) {
            LogUtil.h("HwPayManager", "[sendAccessCardRfInfo] aid is null or emtpy");
            return;
        }
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        String e = cvx.e(1);
        String d3 = cvx.d(str2.length() / 2);
        String e2 = cvx.e(2);
        int length = e.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = c2.length() / 2;
        int length4 = e2.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (d3.length() / 2) + (str2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(c2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(str2));
        LogUtil.c("HwPayManager", "sendAccessCardRfInfo byteBufferAll : ", cvx.d(allocate.array()));
        d(deviceCommand, allocate, 21, iBaseResponseCallback);
    }

    public void e(int i, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(22);
        String e = cvx.e(i);
        String d2 = cvx.d(e.length() / 2);
        String str = cvx.e(1) + d2 + e;
        ByteBuffer allocate = ByteBuffer.allocate(str.length() / 2);
        allocate.put(cvx.a(str));
        d(deviceCommand, allocate, 22, iBaseResponseCallback);
    }

    private int b(List<cwd> list) {
        int i = 0;
        if (list == null) {
            return 0;
        }
        for (cwd cwdVar : list) {
            if (CommonUtil.a(cwdVar.e(), 16) == 127) {
                i = CommonUtil.a(cwdVar.c(), 16);
            }
        }
        return i;
    }

    public static void a(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(23);
        String e = cvx.e(i);
        String d2 = cvx.d(e.length() / 2);
        String e2 = cvx.e(2);
        String e3 = cvx.e(i2);
        String d3 = cvx.d(e3.length() / 2);
        String e4 = cvx.e(3);
        int length = e2.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = e.length() / 2;
        int length4 = e4.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (d3.length() / 2) + (e3.length() / 2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(e4));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e3));
        a().d(deviceCommand, allocate, 23, iBaseResponseCallback);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(27);
        deviceCommand.setCommandID(24);
        String d2 = cvx.d(0);
        String e = cvx.e(1);
        String d3 = cvx.d(0);
        String e2 = cvx.e(2);
        String d4 = cvx.d(0);
        String e3 = cvx.e(3);
        int length = e.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = e2.length() / 2;
        int length4 = d3.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + (e3.length() / 2) + (d4.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d3));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(d4));
        d(deviceCommand, allocate, 24, iBaseResponseCallback);
    }

    public void e(int i, int i2, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            LogUtil.h("HwPayManager", "sendBluetoothCommand myByteBuffer is null");
            return;
        }
        ReleaseLogUtil.e("R_HwPayManager", "sendWalletTlv");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        this.j.b(deviceCommand);
    }

    private void c(int i, Object obj) {
        WalletResponseListener walletResponseListener = this.f;
        if (walletResponseListener != null) {
            walletResponseListener.onResponse(1, i, obj);
        }
    }

    public void e(WalletResponseListener walletResponseListener) {
        ReleaseLogUtil.e("R_HwPayManager", "registerHealthResponseListener");
        this.f = walletResponseListener;
    }

    public void c() {
        LogUtil.a("HwPayManager", "unRegisterHealthResponseListener");
        this.f = null;
    }

    private void b() {
        if (Build.VERSION.SDK_INT > 30) {
            PowerKitManager.e().d("HwPayManager", 512, 600000L, "user-WalletEseManagement");
        }
    }

    private void i() {
        if (Build.VERSION.SDK_INT > 30) {
            PowerKitManager.e().e("HwPayManager", 512, "user-WalletEseManagement");
        }
    }
}
