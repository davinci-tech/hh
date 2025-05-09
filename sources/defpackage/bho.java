package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.iconnect.IWearConnectService;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class bho extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private String f373a = "";
    private final biw b;
    private int c;
    private final String d;
    private IWearConnectService e;

    private boolean c(int i) {
        return (i ^ 1) == 4 || (i ^ 2) == 4;
    }

    private byte[] d() {
        return new byte[]{3, 1, 1};
    }

    private byte[] e() {
        return new byte[]{13, 1, 1};
    }

    public bho(int i, String str, biw biwVar) {
        d(i);
        this.d = str;
        this.b = biwVar;
    }

    class b implements ServiceConnection {
        private CountDownLatch c;

        b(CountDownLatch countDownLatch) {
            this.c = countDownLatch;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.c("SecurityNegotiationCommand", "iConnect service connected so start to set service handle");
            if (iBinder != null) {
                bho.this.e = IWearConnectService.Stub.asInterface(iBinder);
                CountDownLatch countDownLatch = this.c;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    return;
                }
                return;
            }
            LogUtil.a("SecurityNegotiationCommand", "service is null");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.a("SecurityNegotiationCommand", "iConnect service disconnect so start to set service handle is null");
        }
    }

    private void d(int i) {
        boolean z = bjn.c() && bjn.c(i);
        LogUtil.c("SecurityNegotiationCommand", "setAuthenticateMode:", Boolean.valueOf(z), ",deviceSupportType:", Integer.valueOf(i));
        if (z) {
            this.c = 1;
        } else {
            this.c = bjk.d(i) ? 4 : 2;
        }
        LogUtil.c("SecurityNegotiationCommand", "setAuthenticateMode :", Integer.valueOf(this.c));
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("SecurityNegotiationCommand", "mTransmitData is null");
            return getUnencryptedDeviceCommand(deviceInfo);
        }
        byte[] f = f();
        bmw.e(100091, bmh.b(deviceInfo.getDeviceName()), "", String.valueOf(this.c));
        String a2 = bjr.a(deviceInfo.getDeviceMac(), 2);
        LogUtil.c("SecurityNegotiationCommand", "deviceIsReconnect: ", Boolean.valueOf(deviceInfo.isReconnect()), " secretKeyIsNotEmpty: ", Boolean.valueOf(!TextUtils.isEmpty(a2)), " updateKeySuccessLabelIsEmpty: ", Boolean.valueOf(TextUtils.isEmpty(bmf.a(deviceInfo.getDeviceMac()))));
        boolean z = deviceInfo.isReconnect() && !TextUtils.isEmpty(a2) && TextUtils.isEmpty(bmf.a(deviceInfo.getDeviceMac()));
        bmf.a(deviceInfo.getDeviceMac(), "");
        byte[] d = d(z);
        byte[] g = g();
        byte[] d2 = d();
        byte[] a3 = a();
        byte[] j = j();
        byte[] h = h();
        biw biwVar = this.b;
        byte[] e = (biwVar == null || biwVar.c() != 1) ? null : e();
        int length = f.length + 2 + d.length + g.length + d2.length + a3.length + j.length + h.length;
        if (e != null) {
            length += e.length;
        }
        byte[] c = c();
        ByteBuffer allocate = ByteBuffer.allocate(length + c.length);
        allocate.put((byte) 1).put((byte) 51);
        allocate.put(f).put(d).put(g).put(d2).put(a3).put(j).put(h).put(c);
        if (e != null) {
            allocate.put(e);
        }
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_SecurityNegotiationCommand", bhh.c("0133"));
        return unencryptedDeviceCommand;
    }

    private byte[] f() {
        byte[] g = blq.g(this.c);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private byte[] d(boolean z) {
        int i = this.c;
        if (i != 2 && i != 4) {
            return new byte[0];
        }
        LogUtil.c("SecurityNegotiationCommand", "isReconnect:", Boolean.valueOf(z));
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = 1;
        bArr[2] = (byte) (z ? 2 : 1);
        return bArr;
    }

    private byte[] g() {
        String b2;
        if (this.c == 4) {
            if (bky.a()) {
                b2 = blq.b(bli.c());
            } else {
                b2 = blq.b(bli.d());
            }
        } else {
            String k = CommonUtil.k(BaseApplication.e());
            if (TextUtils.isEmpty(k)) {
                return new byte[0];
            }
            b2 = blq.b(k);
        }
        String d = d(b2);
        if (TextUtils.isEmpty(d)) {
            return new byte[0];
        }
        byte[] a2 = blq.a(d);
        byte[] d2 = blq.d(a2.length);
        byte[] bArr = new byte[a2.length + 1 + d2.length];
        bArr[0] = 5;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(a2, 0, bArr, d2.length + 1, a2.length);
        return bArr;
    }

    private String d(String str) {
        int length = str.length() / 2;
        if (TextUtils.isEmpty(str) || bky.a() || length <= 64) {
            return str;
        }
        LogUtil.c("SecurityNegotiationCommand", "checkThirdPartyPhone over max length");
        try {
            String k = CommonUtil.k(BaseApplication.e());
            String b2 = blq.b(k);
            if (b2.length() / 2 <= 64) {
                LogUtil.c("SecurityNegotiationCommand", "newId : ", blt.b(k));
                if (this.c == 4) {
                    LogUtil.c("SecurityNegotiationCommand", "savePhoneUdidToSharedPreferences phoneUdid");
                    bmf.d(k);
                }
                return b2;
            }
            String d = blq.d(bmv.e(k.getBytes("UTF-8")));
            if (d.length() > 64) {
                LogUtil.e("SecurityNegotiationCommand", "changeId bigger than 64");
                d = d.substring(0, 64);
            }
            LogUtil.c("SecurityNegotiationCommand", "sha256 Id : ", blt.b(d));
            String b3 = blq.b(d);
            if (this.c == 4) {
                LogUtil.c("SecurityNegotiationCommand", "savePhoneUdidToSharedPreferences phoneUdid");
                bmf.d(d);
            }
            return b3;
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("SecurityNegotiationCommand", "UnsupportedEncodingException");
            LogUtil.c("SecurityNegotiationCommand", "checkThirdPartyPhone end");
            if (str.length() < 64) {
                LogUtil.c("SecurityNegotiationCommand", "udidHex length is : ", Integer.valueOf(str.length()));
                return "";
            }
            return str.substring(0, 64);
        }
    }

    private byte[] a() {
        byte[] g = blq.g(EmuiBuild.f13113a);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 4;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private byte[] j() {
        if (this.c != 4) {
            return new byte[0];
        }
        LogUtil.c("SecurityNegotiationCommand", "getPhoneMac");
        if (!bky.f() && n()) {
            this.f373a = b();
        } else {
            try {
                this.f373a = Settings.Secure.getString(BaseApplication.e().getContentResolver(), "bluetooth_address");
            } catch (SecurityException unused) {
                LogUtil.e("SecurityNegotiationCommand", "mPhoneIdentify exception");
            }
        }
        byte[] c = blq.c(this.f373a);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 6;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private byte[] h() {
        try {
            if (this.c == 4) {
                LogUtil.c("SecurityNegotiationCommand", "getPhoneModel");
                BluetoothAdapter sM_ = bkw.d().sM_();
                if (sM_ != null) {
                    byte[] c = blq.c(sM_.getName());
                    byte[] d = blq.d(c.length);
                    byte[] bArr = new byte[c.length + 1 + d.length];
                    bArr[0] = 7;
                    System.arraycopy(d, 0, bArr, 1, d.length);
                    System.arraycopy(c, 0, bArr, d.length + 1, c.length);
                    return bArr;
                }
            }
        } catch (SecurityException e) {
            LogUtil.e("SecurityNegotiationCommand", "getPhoneModel SecurityException:", ExceptionUtils.d(e));
        }
        return new byte[0];
    }

    private byte[] c() {
        if (this.c == 4) {
            LogUtil.c("SecurityNegotiationCommand", "getDeviceTypeId");
            if (bky.f()) {
                byte[] c = blq.c(blj.d(Build.MODEL));
                byte[] d = blq.d(c.length);
                byte[] bArr = new byte[c.length + 1 + d.length];
                bArr[0] = 10;
                System.arraycopy(d, 0, bArr, 1, d.length);
                System.arraycopy(c, 0, bArr, d.length + 1, c.length);
                return bArr;
            }
        }
        return new byte[0];
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.c("SecurityNegotiationCommand", "Parameter Check Failed");
            biyVar.c(13);
            biyVar.a(50151);
            return biyVar;
        }
        LogUtil.c("SecurityNegotiationCommand", "deal 5.1.51 message");
        int e = bjn.e(deviceInfo.getDeviceMac(), biuVar.a());
        if (e == 100000) {
            ReleaseLogUtil.b("DEVMGR_SecurityNegotiationCommand", "start hiChain auth");
            if (bkw.c()) {
                LogUtil.a("SecurityNegotiationCommand", "enter subUser");
                biyVar.c(13);
                biyVar.a(50151);
                bih.e(1003003);
                return biyVar;
            }
            this.mNextCommand = HiChainAuthManager.b(deviceInfo.getDeviceMac(), this.b);
            if (this.mNextCommand != null) {
                biyVar.c(12);
                biyVar.a(100000);
            } else {
                biyVar.c(13);
                biyVar.a(50151);
            }
            return biyVar;
        }
        if (c(e)) {
            b(biyVar, e, deviceInfo);
            return biyVar;
        }
        if (e == 1 || e == 2) {
            String deviceMac = deviceInfo.getDeviceMac();
            bjp.d().a(deviceMac, e);
            return e(biyVar, deviceMac);
        }
        LogUtil.c("SecurityNegotiationCommand", "resolve 5.1.51 failure !");
        biyVar.c(13);
        biyVar.a(50151);
        return biyVar;
    }

    private void b(biy biyVar, int i, DeviceInfo deviceInfo) {
        LogUtil.c("SecurityNegotiationCommand", "isHiChain3 authenticateTypeCode:", Integer.valueOf(i));
        this.mNextCommand = bjl.e().e(deviceInfo.getDeviceName(), deviceInfo.getDeviceMac(), this.b, (i ^ 4) == 1);
        if (this.mNextCommand != null) {
            biyVar.c(12);
            biyVar.a(100000);
        } else {
            biyVar.c(13);
            biyVar.a(50151);
        }
    }

    private biy e(biy biyVar, String str) {
        if (this.b == null) {
            LogUtil.e("SecurityNegotiationCommand", "HiChainLite Authentic is error, mParameter is null");
            biyVar.c(13);
            biyVar.a(50151);
            return biyVar;
        }
        LogUtil.c("SecurityNegotiationCommand", "start hiChainLite authentic");
        String a2 = bjr.a(str, 2);
        bjp.d().a(a2, str);
        String str2 = this.b.j() + this.d;
        if (bjp.d().h(str)) {
            LogUtil.c("SecurityNegotiationCommand", "device is reconnect");
            if (TextUtils.isEmpty(a2)) {
                LogUtil.c("SecurityNegotiationCommand", "secretKey is empty");
                bmf.e();
                biyVar.c(14);
                biyVar.a(50151);
                return biyVar;
            }
            this.mNextCommand = new bgs(blq.d(bjr.c(a2 + "0100", str2)), this.d, this.b);
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        if (TextUtils.isEmpty(bhh.e(str))) {
            LogUtil.c("SecurityNegotiationCommand", "pin is empty");
            this.mNextCommand = new bhb(this.d, this.b);
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        this.mNextCommand = new bgs(bjr.b("0100", this.b.a(), str2, str), this.d, this.b);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private String b() {
        boolean z;
        if (TextUtils.isEmpty(this.f373a)) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Intent intent = new Intent("com.huawei.iconnect.action.WEAR_CONNECT_SERVICE");
            intent.setPackage("com.huawei.iconnect");
            b bVar = new b(countDownLatch);
            try {
                z = BaseApplication.e().bindService(intent, bVar, 1);
            } catch (SecurityException unused) {
                LogUtil.e("SecurityNegotiationCommand", "bindAndGetPhoneIndex SecurityException");
                z = false;
            }
            LogUtil.a("SecurityNegotiationCommand", "bind iconnect result: ", Boolean.valueOf(z));
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused2) {
                LogUtil.e("SecurityNegotiationCommand", "countDownLatch await exception");
            }
            this.f373a = i();
            ro_(bVar);
        } else {
            LogUtil.c("SecurityNegotiationCommand", "mPhoneIdentify is not empty");
        }
        return this.f373a;
    }

    private void ro_(ServiceConnection serviceConnection) {
        LogUtil.c("SecurityNegotiationCommand", "Enter unBindIConnectService.");
        try {
            this.e = null;
            BaseApplication.e().getApplicationContext().unbindService(serviceConnection);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("SecurityNegotiationCommand", "unBindIConnectService with IllegalArgumentException.");
        }
    }

    private String i() {
        IWearConnectService iWearConnectService = this.e;
        if (iWearConnectService != null) {
            try {
                return iWearConnectService.getHuaweiPhoneIndex();
            } catch (SecurityException unused) {
                LogUtil.e("SecurityNegotiationCommand", "SecurityException");
            } catch (RemoteException unused2) {
                LogUtil.e("SecurityNegotiationCommand", "RemoteException");
            } finally {
                LogUtil.a("SecurityNegotiationCommand", "finally");
            }
        } else {
            LogUtil.a("SecurityNegotiationCommand", "mIConnectService is null.");
        }
        return "";
    }

    private boolean n() {
        return bld.d() && bli.a();
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0133";
    }
}
