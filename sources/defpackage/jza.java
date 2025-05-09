package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class jza implements ParserInterface {
    private static final Object b = new Object();
    private static jza c;

    /* renamed from: a, reason: collision with root package name */
    private jzg f14213a;
    private Context d;
    private jzp e;
    private cwl g;
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: jza.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwContactSyncMgr", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("HwContactSyncMgr", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.a("HwContactSyncMgr", "mDeviceListChangedReceiver onReceive action :", action);
            if ("com.huawei.bone.action.DEVICE_LIST_CHANGED".equals(action)) {
                jza.this.e.a();
            }
        }
    };

    private jza() {
        LogUtil.a("HwContactSyncMgr", "constructor method");
        d();
    }

    private void d() {
        this.g = new cwl();
        this.f14213a = new jzg();
        this.e = new jzp();
        this.d = BaseApplication.getContext();
        b();
    }

    private void b() {
        BroadcastManagerUtil.bFC_(this.d, this.i, new IntentFilter("com.huawei.bone.action.DEVICE_LIST_CHANGED"), LocalBroadcast.c, null);
    }

    public static jza c() {
        jza jzaVar;
        synchronized (b) {
            if (c == null) {
                c = new jza();
            }
            jzaVar = c;
        }
        return jzaVar;
    }

    private static void e() {
        synchronized (b) {
            c = null;
        }
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length < 5 || deviceInfo == null) {
            LogUtil.h("HwContactSyncMgr", "handleSyncContacts: byte array is null or invalid length.");
            return;
        }
        LogUtil.a("HwContactSyncMgr", "handleSyncContacts: start, bytes: ", Arrays.toString(bArr));
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwContactSyncMgr");
        DeviceCapability deviceCapability = (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) ? null : queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null) {
            LogUtil.h("HwContactSyncMgr", "capability is null.");
            return;
        }
        if (!deviceCapability.isSupportSyncContacts()) {
            LogUtil.a("HwContactSyncMgr", "isSupportSyncContacts: the capability set is not supported.");
            return;
        }
        LogUtil.a("HwContactSyncMgr", "isSupportSyncContacts: the capability set is supported.");
        if (!kae.c()) {
            b(deviceInfo);
            LogUtil.a("HwContactSyncMgr", "handleSyncContacts: has no read contacts permissions");
            return;
        }
        if (!deviceCapability.isSupportSyncHiCall()) {
            LogUtil.a("HwContactSyncMgr", "isNeedSyncExtras: the capability set is not supported.");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 4) {
            LogUtil.a("HwContactSyncMgr", "handleSyncContacts: response 5.3.4");
            c(bArr, deviceInfo);
        } else if (b2 == 3) {
            LogUtil.a("HwContactSyncMgr", "handleSyncContacts: response 5.3.3");
            b(bArr, deviceInfo);
        }
    }

    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwContactSyncMgr", "onDeviceConnected: deviceInfo is null");
        } else {
            this.f14213a.c(deviceInfo);
        }
    }

    public void c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwContactSyncMgr", "onDeviceDisconnected: deviceInfo is null");
        } else {
            this.f14213a.d(deviceInfo);
        }
    }

    public void a() {
        jze.a().e(BaseApplication.getContext());
        jze.a().c();
        e();
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        jzc b2 = b(bArr);
        ReleaseLogUtil.e("R_HwContactSyncMgr", "responseContactsSyncState: errorCode/isNeedSync", Integer.valueOf(b2.d()), Boolean.valueOf(b2.e()));
        e(b2.d(), deviceInfo);
        jze a2 = jze.a();
        String a3 = kal.a();
        jzy.a(a3, b2.e());
        if (b2.e()) {
            a2.b(a3, 0);
            a2.e(deviceInfo);
        }
        a2.b(BaseApplication.getContext(), a3);
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        int c2 = c(bArr);
        ReleaseLogUtil.e("R_HwContactSyncMgr", "responseExtrasResult: errorCode:", Integer.valueOf(c2));
        if (c2 == 400000) {
            jze.a().e();
        } else {
            jze.a().b(deviceInfo);
        }
    }

    private void b(DeviceInfo deviceInfo) {
        LogUtil.a("HwContactSyncMgr", "responseNoPermissions: report to device with no contacts permission");
        ContactsDataSender.e().e(4, b(100004), deviceInfo);
    }

    private jzc b(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            return new jzc(100006, false);
        }
        if (bArr[1] != 4) {
            return new jzc(100003, false);
        }
        if (bArr[2] != 1) {
            return new jzc(100007, false);
        }
        return new jzc(100000, bArr[4] == 1);
    }

    private int c(byte[] bArr) {
        LogUtil.a("HwContactSyncMgr", "parseExtraDataStateResult: start");
        int e = e(bArr);
        if (e != 0) {
            return e;
        }
        try {
            List<cwd> e2 = this.g.a(cvx.d(Arrays.copyOfRange(bArr, 2, bArr.length))).e();
            if (e2 != null && e2.size() >= 1) {
                cwd cwdVar = e2.get(0);
                int e3 = CommonUtil.e(cwdVar.e(), Integer.MIN_VALUE);
                if (e3 == Integer.MIN_VALUE) {
                    return 100011;
                }
                if (e3 != 127) {
                    return 100007;
                }
                int e4 = CommonUtil.e(cwdVar.c(), Integer.MIN_VALUE);
                if (e4 == Integer.MIN_VALUE) {
                    return 100011;
                }
                return e4 == 100000 ? 400000 : 400001;
            }
            LogUtil.h("HwContactSyncMgr", "parseSyncHiCallStateResult: invalid tlv ");
            return 100006;
        } catch (cwg unused) {
            LogUtil.b("HwContactSyncMgr", "parseSyncHiCallStateResult: TlvException occurred.");
            sqo.j("parseSyncHiCallStateResult: TlvException occurred");
            return 100011;
        }
    }

    private int e(byte[] bArr) {
        if (bArr != null && bArr.length >= 6) {
            return bArr[1] != 7 ? 100003 : 0;
        }
        LogUtil.h("HwContactSyncMgr", "checkSyncResultByteArray: bytes is null or invalid.");
        return 100006;
    }

    private void e(int i, DeviceInfo deviceInfo) {
        ContactsDataSender.e().e(4, b(i), deviceInfo);
    }

    private byte[] b(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(Byte.MAX_VALUE);
        allocate.put((byte) 4);
        allocate.putInt(i);
        return allocate.array();
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        a(bArr, deviceInfo);
    }
}
