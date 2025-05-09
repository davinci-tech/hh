package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.hihealthkit.AuthSupport;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager;
import com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IDeviceStateAIDLCallback;
import health.compact.a.HEXUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class jsm {
    private static Map<Integer, ParserInterface> b = new ConcurrentHashMap(32);
    private long c = 0;
    private Context e;

    private boolean a(int i, int i2) {
        return i == 7 && (i2 == 30 || i2 == 38 || i2 == 39);
    }

    private boolean d(int i, int i2) {
        return i == 7 && (i2 == 3 || i2 == 4 || i2 == 5 || i2 == 8 || i2 == 10 || i2 == 11 || i2 == 12 || i2 == 13 || i2 == 14 || i2 == 15 || i2 == 31 || i2 == 32);
    }

    private boolean f(int i, int i2) {
        return (i == 2 && (i2 == 5 || i2 == 2 || i2 == 16 || i2 == 17)) || (i == 33 && i2 == 2);
    }

    private boolean g(int i, int i2) {
        if (i == 22) {
            return i2 == 12 || i2 == 13;
        }
        return false;
    }

    private boolean h(int i, int i2) {
        return i == 37 && (i2 == 1 || i2 == 2 || i2 == 3);
    }

    private boolean i(int i, int i2) {
        return i == 26 && i2 == 7;
    }

    public void e(Context context) {
        this.e = context;
        HwExerciseAdviceManager hwExerciseAdviceManager = HwExerciseAdviceManager.getInstance();
        HwWorkoutServiceManager hwWorkoutServiceManager = HwWorkoutServiceManager.getInstance();
        HwHeartRateManager hwHeartRateManager = HwHeartRateManager.getInstance();
        jvi a2 = jvi.a();
        kcg b2 = kcg.b();
        jve c = jve.c();
        jyx a3 = jyx.a();
        jyp c2 = jyp.c();
        kcy e = kcy.e(BaseApplication.getContext());
        kdh d = kdh.d();
        jva b3 = jva.b();
        mbo a4 = mbo.a();
        kfi d2 = kfi.d();
        tnv d3 = tnv.d();
        tns b4 = tns.b();
        kff c3 = kff.c();
        b.put(31, b3);
        b.put(43, c3);
        b.put(49, d2);
        b.put(22, hwExerciseAdviceManager);
        b.put(23, hwWorkoutServiceManager);
        b.put(25, hwHeartRateManager);
        b.put(1014, a2);
        b.put(24, b2);
        b.put(28, c);
        b.put(40, a3);
        b.put(35, e);
        b.put(45, d);
        b.put(44, c2);
        b.put(48, a4);
        b.put(52, d3);
        b.put(53, b4);
    }

    public void a(DeviceInfo deviceInfo, int i, byte[] bArr, IDeviceStateAIDLCallback iDeviceStateAIDLCallback, Map<String, IDeviceStateAIDLCallback> map) {
        if (b(bArr)) {
            b(deviceInfo, bArr);
        }
        if (!jsp.d(bArr, deviceInfo)) {
            LogUtil.a("PhoneService-LayerDataOrder", "onDataReceived, but don't need process");
            return;
        }
        if (b == null || bArr == null || bArr.length < 2) {
            LogUtil.h("PhoneService-LayerDataOrder", "sManagerMap is null");
            return;
        }
        boolean c = cvt.c(deviceInfo.getProductType());
        byte b2 = bArr[0];
        byte b3 = bArr[1];
        b(b2, b3);
        if (b2 == 27 || b2 == 39 || b2 == 37 || b2 == 42) {
            Object[] objArr = new Object[6];
            objArr[0] = "response sid: ";
            objArr[1] = Integer.valueOf(b2);
            objArr[2] = " cid: ";
            objArr[3] = Integer.valueOf(b3);
            objArr[4] = " deviceStateAidlCallback is empty: ";
            objArr[5] = Boolean.valueOf(iDeviceStateAIDLCallback == null);
            ReleaseLogUtil.b("DEVMGR_PhoneService-LayerDataOrder", objArr);
        }
        if (b2 == 8 && b3 == 10) {
            ReleaseLogUtil.b("DEVMGR_PhoneService-LayerDataOrder", "handle alarm clock escape.");
            String a2 = HEXUtils.a(bArr);
            LogUtil.a("PhoneService-LayerDataOrder", "handle alarm clock escape tlvHex=", a2);
            jwo.e().a(a2);
            return;
        }
        ParserInterface c2 = c(b2, b3, c);
        if (c2 != null && !g(b2, b3)) {
            c2.getResult(deviceInfo, bArr);
        } else {
            e(deviceInfo, i, bArr, iDeviceStateAIDLCallback, map);
        }
        a(deviceInfo, bArr);
        e(deviceInfo, bArr);
    }

    private boolean b(byte[] bArr) {
        if (bArr != null && bArr.length >= 2) {
            byte b2 = bArr[0];
            byte b3 = bArr[1];
            if (b2 == 7 && b3 == 15) {
                return true;
            }
        }
        return false;
    }

    private void b(DeviceInfo deviceInfo, byte[] bArr) {
        try {
            int a2 = jro.a(bArr);
            LogUtil.a("PhoneService-LayerDataOrder", "dealIsBtProxySleepActionReport action: ", Integer.valueOf(a2));
            if (a2 == 4 || a2 == 16) {
                jue.b().b(deviceInfo.getDeviceIdentify(), a2);
            }
        } catch (cwg unused) {
            LogUtil.b("PhoneService-LayerDataOrder", "dealIsBtProxySleepActionReport occur TlvException");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0020, code lost:
    
        if (r6 != 12) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r6 != 15) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:?, code lost:
    
        return defpackage.jsm.b.get(1014);
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hwdevice.phoneprocess.binder.ParserInterface c(int r5, int r6, boolean r7) {
        /*
            r4 = this;
            boolean r0 = health.compact.a.CommonUtil.bv()
            r1 = 15
            r2 = 10
            if (r0 == 0) goto Lf
            if (r5 != r2) goto L32
            if (r6 == r1) goto L32
            goto L22
        Lf:
            java.lang.String r0 = "getProcessManager beta"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "PhoneService-LayerDataOrder"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r0)
            if (r5 != r2) goto L32
            if (r6 == r1) goto L32
            r0 = 12
            if (r6 == r0) goto L32
        L22:
            java.util.Map<java.lang.Integer, com.huawei.hwdevice.phoneprocess.binder.ParserInterface> r5 = defpackage.jsm.b
            r6 = 1014(0x3f6, float:1.421E-42)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r5 = (com.huawei.hwdevice.phoneprocess.binder.ParserInterface) r5
            goto L9a
        L32:
            r0 = 1
            if (r5 != r0) goto L4c
            r1 = 41
            if (r6 == r1) goto L3d
            r1 = 42
            if (r6 != r1) goto L4c
        L3d:
            java.util.Map<java.lang.Integer, com.huawei.hwdevice.phoneprocess.binder.ParserInterface> r5 = defpackage.jsm.b
            r6 = 2001(0x7d1, float:2.804E-42)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r5 = (com.huawei.hwdevice.phoneprocess.binder.ParserInterface) r5
            goto L9a
        L4c:
            boolean r1 = r4.h(r5, r6)
            if (r1 == 0) goto L61
            java.util.Map<java.lang.Integer, com.huawei.hwdevice.phoneprocess.binder.ParserInterface> r5 = defpackage.jsm.b
            r6 = 2037(0x7f5, float:2.854E-42)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r5 = (com.huawei.hwdevice.phoneprocess.binder.ParserInterface) r5
            goto L9a
        L61:
            r1 = 23
            if (r5 != r1) goto L78
            r2 = 16
            if (r6 != r2) goto L78
            java.util.Map<java.lang.Integer, com.huawei.hwdevice.phoneprocess.binder.ParserInterface> r5 = defpackage.jsm.b
            r6 = 25
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r5 = (com.huawei.hwdevice.phoneprocess.binder.ParserInterface) r5
            goto L9a
        L78:
            if (r5 != r0) goto L83
            if (r6 != r1) goto L83
            android.content.Context r5 = r4.e
            kfp r5 = defpackage.kfp.d(r5)
            goto L9a
        L83:
            if (r5 != r0) goto L8e
            r0 = 47
            if (r6 != r0) goto L8e
            kfv r5 = defpackage.kfv.d()
            goto L9a
        L8e:
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r0 = r4.c(r5, r6)
            if (r0 != 0) goto L99
            com.huawei.hwdevice.phoneprocess.binder.ParserInterface r5 = r4.b(r5, r6, r7)
            goto L9a
        L99:
            r5 = r0
        L9a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsm.c(int, int, boolean):com.huawei.hwdevice.phoneprocess.binder.ParserInterface");
    }

    private ParserInterface c(int i, int i2) {
        if (i == 32) {
            if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
                return kdq.c();
            }
            LogUtil.h("PhoneService-LayerDataOrder", "getStressRelaxDataManager not stress relax data.");
        }
        return null;
    }

    private ParserInterface b(int i, int i2, boolean z) {
        if (i == 24 && i2 == 6) {
            return kjl.b();
        }
        if (i == 3 && (i2 == 4 || i2 == 3)) {
            return jza.c();
        }
        if (i == 7 && i2 == 42) {
            return kiw.c();
        }
        if (f(i, i2)) {
            return khu.a(this.e);
        }
        if (i == 42 && i2 == 3) {
            return kau.b();
        }
        if (i == 1 && (i2 == 61 || i2 == 62)) {
            return jub.b();
        }
        if (a(i, i2)) {
            return kdz.b();
        }
        if (z && d(i, i2)) {
            return jxc.b();
        }
        if (d(i, i2)) {
            return jwy.b();
        }
        return b.get(Integer.valueOf(i));
    }

    private void e(DeviceInfo deviceInfo, int i, byte[] bArr, IDeviceStateAIDLCallback iDeviceStateAIDLCallback, Map<String, IDeviceStateAIDLCallback> map) {
        if (iDeviceStateAIDLCallback != null) {
            try {
                iDeviceStateAIDLCallback.onDataReceived(deviceInfo, i, bArr);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("DEVMGR_PhoneService-LayerDataOrder", "onDataReceived remoteException RemoteException");
                b(bArr, deviceInfo);
            } catch (Exception unused2) {
                ReleaseLogUtil.c("DEVMGR_PhoneService-LayerDataOrder", "mDeviceStateCallback onDataReceived Exception");
            }
            if (c(bArr) || a(bArr)) {
                c(deviceInfo, i, bArr, iDeviceStateAIDLCallback, map);
                return;
            }
            return;
        }
        b(bArr, deviceInfo);
    }

    private boolean c(byte[] bArr) {
        byte b2 = bArr[0];
        return b2 == 27 || b2 == 39 || b2 == 9;
    }

    private boolean a(byte[] bArr) {
        byte b2 = bArr[0];
        return b2 == 37 || b2 == 42;
    }

    private static void c(DeviceInfo deviceInfo, int i, byte[] bArr, IDeviceStateAIDLCallback iDeviceStateAIDLCallback, Map<String, IDeviceStateAIDLCallback> map) {
        IDeviceStateAIDLCallback iDeviceStateAIDLCallback2 = map.get(BaseApplication.getAppPackage());
        if (iDeviceStateAIDLCallback2 != null && !iDeviceStateAIDLCallback2.equals(iDeviceStateAIDLCallback)) {
            try {
                iDeviceStateAIDLCallback2.onDataReceived(deviceInfo, i, bArr);
                return;
            } catch (Exception unused) {
                ReleaseLogUtil.c("DEVMGR_PhoneService-LayerDataOrder", "callbackAgain onDataReceived Exception");
                return;
            }
        }
        ReleaseLogUtil.b("DEVMGR_PhoneService-LayerDataOrder", "mainProcessCallback is ", iDeviceStateAIDLCallback2);
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("PhoneService-LayerDataOrder", "sendBroadcastWhenMainProcessDead");
        if (d(bArr)) {
            return;
        }
        Intent intent = new Intent("com.huawei.bone.ACTION_RECEIVE_SEND_SPORT_GOAL");
        intent.putExtra("synchronize_data_deviceinfo_key", deviceInfo);
        intent.setPackage(BaseApplication.getContext().getPackageName());
        if (e(bArr[0], bArr[1])) {
            intent.putExtra("alarm_data_key", bArr);
        } else if (i(bArr[0], bArr[1])) {
            intent.putExtra("synchronize_data_key", bArr);
        } else {
            LogUtil.h("PhoneService-LayerDataOrder", "sendBroadcastWhenMainProcessDead is return.");
            return;
        }
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    private boolean d(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("PhoneService-LayerDataOrder", "sendBroadcastWhenMainProcessDead error datas");
            return true;
        }
        if (!e(bArr[0], bArr[1]) && !i(bArr[0], bArr[1])) {
            LogUtil.h("PhoneService-LayerDataOrder", "sendBroadcastWhenMainProcessDead no match data");
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!e(bArr[0], bArr[1]) && currentTimeMillis - this.c > 900000 && !i(bArr[0], bArr[1])) {
            LogUtil.h("PhoneService-LayerDataOrder", "The user is not moving.");
            this.c = currentTimeMillis;
            return true;
        }
        this.c = System.currentTimeMillis();
        return false;
    }

    private boolean e(int i, int i2) {
        LogUtil.a("isAlarmCommand command: ", Integer.valueOf(i2));
        return i == 8 && (i2 == 5 || i2 == 8);
    }

    private void b(int i, int i2) {
        try {
            if (j(i, 50)) {
                b.put(50, kcu.d());
            }
            if (j(i, 43)) {
                b.put(43, kff.c());
            }
            if (j(i, 46)) {
                b.put(46, jtr.b());
            }
            if (i == 1 && ((i2 == 41 || i2 == 42) && b.get(2001) == null)) {
                b.put(2001, kin.b());
            }
            if (h(i, i2) && b.get(2037) == null) {
                b.put(2037, khf.c());
            }
            if (j(i, 4)) {
                b.put(4, kis.d(this.e));
            }
            if (j(i, 15)) {
                b.put(15, kjl.b());
            }
            if (j(i, 51)) {
                b.put(51, kdk.a());
            }
        } catch (IndexOutOfBoundsException e) {
            LogUtil.b("PhoneService-LayerDataOrder", "processOtherService IndexOutOfBoundsException:", ExceptionUtils.d(e));
        }
    }

    private boolean j(int i, int i2) {
        return i == i2 && b.get(Integer.valueOf(i2)) == null;
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        byte b2 = bArr[0];
        if (b2 == 1 && bArr[1] == 8) {
            jtq.e().e(deviceInfo, bArr);
        } else if (b2 == 37 && bArr[1] == 15) {
            AuthSupport.d().b(deviceInfo, bArr);
        }
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        byte b2 = bArr[0];
        if ((b2 == 1 && bArr[1] == 8) || b2 == 46) {
            tns.b().b(deviceInfo, bArr);
        }
    }
}
