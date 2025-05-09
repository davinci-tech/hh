package defpackage;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.device.callback.BindHealthDeviceCallback;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.kit.hwwsp.hagrid.bean.MiniUserDataInfo;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.secure.android.common.util.HexUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.cjq;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class cha {
    private byte[] e;
    private BleTaskQueueUtil f;
    private TimerTask g;
    private Timer h;
    private String j;
    private cfu m;
    private UniteDevice n;
    private ArrayList<bir> o;
    private static final Object d = new Object();
    private static final Object c = new Object();
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private CopyOnWriteArrayList<IHealthDeviceCallback> f714a = new CopyOnWriteArrayList<>();
    private ArrayList<cjq> i = new ArrayList<>(16);

    private byte b(int i) {
        return (byte) (i & 255);
    }

    public cha(BleTaskQueueUtil bleTaskQueueUtil) {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.e = bArr;
        this.f = bleTaskQueueUtil;
        try {
            this.j = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.h("WspCommandUtils", "mSupplement is error");
        }
    }

    public void p() {
        ReleaseLogUtil.e("WspCommandUtils", "Begin to sendRequestAuth");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.REQUEST_AUTH, new byte[0], false, 5000));
        this.f.e();
    }

    public void d(cgz cgzVar) {
        if (cgzVar == null) {
            return;
        }
        String accountInfo = Utils.i() ? LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) : "0";
        LogUtil.c("WspCommandUtils", "Begin to sending huid", accountInfo);
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.BIND_REQUEST, cgzVar.d(accountInfo), true));
        this.f.e();
    }

    public void y() {
        int b2;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        if (cpa.x(cgt.e().f())) {
            b2 = w();
        } else {
            b2 = b(calendar);
        }
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_SYNC_TIME, new byte[]{b(i), b(i >> 8), b(i2 + 1), b(i3), b(i4), b(i5), b(i6), b(b2)}, false, 5000));
        this.f.e();
    }

    public void c(byte[] bArr) {
        ReleaseLogUtil.e("R_Weight_WspCommandUtils", "Begin to sendAuthToken");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.AUTH_TOKEN, bArr, false, 5000));
        this.f.e();
    }

    public void i(byte[] bArr) {
        ReleaseLogUtil.e("R_Weight_WspCommandUtils", "Begin to work key");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_WORK_KEY, bArr, true, 5000));
        this.f.e();
    }

    public void o() {
        LogUtil.a("WspCommandUtils", "Begin to device reset");
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.SEND_DEVICE_RESET, new byte[0], false, 5000);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public void b(byte[] bArr) {
        LogUtil.a("WspCommandUtils", "begin to delete user data");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_DELETE_USER_DATA, bArr, true, 5000));
        this.f.e();
    }

    public void Fh_(Bundle bundle) {
        byte[] bArr;
        if (bundle == null) {
            LogUtil.h("WspCommandUtils", "bundle is null when get user data");
            return;
        }
        LogUtil.a("WspCommandUtils", "begin to get user data");
        try {
            bArr = bundle.getByteArray(JsbMapKeyNames.H5_USER_ID);
        } catch (Exception unused) {
            LogUtil.b("WspCommandUtils", "getByteArray exception");
            bArr = null;
        }
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA, bArr, true, 5000);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public void f() {
        LogUtil.a("WspCommandUtils", "begin to get next user data");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA, new byte[1], false, 5000));
        this.f.e();
    }

    public void l() {
        LogUtil.a("WspCommandUtils", "begin to get next user data again");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA, new byte[]{1}, false, 5000));
        this.f.e();
    }

    public void q() {
        LogUtil.a("WspCommandUtils", "enter sendWakeupScale");
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.SEND_WAKE_UP, new byte[]{1}, false);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public void f(byte[] bArr) {
        LogUtil.a("WspCommandUtils", "begin to get next user data send user again");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_GET_USER_DATA, bArr, true, 5000));
        this.f.e();
    }

    public void a(String str) {
        LogUtil.a("WspCommandUtils", "enter sendWifiSsid");
        if (!TextUtils.isEmpty(str)) {
            byte[] bArr = new byte[0];
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("WspCommandUtils", "sendWifiSsid getBytes Exception");
            }
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_SSID, bArr, true));
            this.f.e();
            return;
        }
        LogUtil.h("WspCommandUtils", "ssid is empty");
    }

    public void d(String str) {
        LogUtil.a("WspCommandUtils", "enter sendWifiPassword");
        if (!TextUtils.isEmpty(str)) {
            byte[] bArr = new byte[0];
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("WspCommandUtils", "sendWifiPassword getBytes Exception");
            }
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_WIFI_PASSWORD, bArr, true));
            this.f.e();
            return;
        }
        LogUtil.h("WspCommandUtils", "mWifiPwd is empty");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_WIFI_PASSWORD, new byte[0], true));
        this.f.e();
    }

    public void b(String str) {
        if (!cgt.e().d(chb.ai)) {
            LogUtil.a("WspCommandUtils", "is noSupportCharacteristic.");
            return;
        }
        LogUtil.a("WspCommandUtils", "[grs] event handled. begin send grsUrl to devices,url:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WspCommandUtils", "[grs] grsUrl is empty");
            return;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 2);
        allocate.put(new byte[]{3});
        allocate.put((byte) bytes.length);
        allocate.put(bytes);
        byte[] array = allocate.array();
        LogUtil.a("WspCommandUtils", "[grs] grsBytes:", HexUtil.byteArray2HexStr(array));
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_OTA_URL, array, false));
        this.f.e();
    }

    public void j() {
        if (!this.f.e(1)) {
            this.f.d(1);
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_MANAGER_INFO, new byte[0], false, 5000));
            this.f.e();
            return;
        }
        LogUtil.h("WspCommandUtils", "GET_MANAGER_INFO task is doing.");
    }

    public void c(boolean z) {
        if (z) {
            if (!this.f.e(2)) {
                this.f.d(2);
                this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_MANAGER_INFO, new byte[]{1}, false, 5000));
                this.f.e();
                return;
            }
            LogUtil.h("WspCommandUtils", "GET_MANAGER_INFO with account task is doing.");
            return;
        }
        j();
    }

    public void r() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_WEIGHT_UNIT, new byte[0], false, 5000));
        this.f.e();
    }

    public void h(byte[] bArr) {
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.SET_USER_INFO, bArr, true, 5000);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public void e(String str, String str2, cgz cgzVar) {
        if (cgzVar == null) {
            return;
        }
        if (str == null || str2 == null) {
            LogUtil.h("WspCommandUtils", "Set manager info failed, just skip");
            return;
        }
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_MANAGER_INFO, cgzVar.d(str, str2), true, 5000));
        this.f.e();
    }

    public void b(String str, String str2, String str3, cgz cgzVar) {
        LogUtil.a("WspCommandUtils", "account Info:", str3);
        LogUtil.a("WspCommandUtils", "Begin to set manager info");
        if (cgzVar == null) {
            return;
        }
        if (str == null || str2 == null) {
            LogUtil.h("WspCommandUtils", "Set manager info failed, just skip");
            return;
        }
        if (str3.length() > 20) {
            LogUtil.h("WspCommandUtils", "account info is too long");
            return;
        }
        try {
            byte[] d2 = cgzVar.d(str, (str2 + this.j).substring(0, 40));
            ByteBuffer allocate = ByteBuffer.allocate(d2.length + str3.getBytes("UTF-8").length + 1);
            allocate.put(d2);
            allocate.put((byte) str3.length());
            allocate.put(str3.getBytes("UTF-8"));
            LogUtil.a("WspCommandUtils", cvx.d(allocate.array()));
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_MANAGER_INFO, allocate.array(), true, 5000));
            this.f.e();
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("WspCommandUtils", "account string is wrong");
        }
    }

    public void s() {
        LogUtil.a("WspCommandUtils", "Begin to sendRealTimeWeight.");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_WEIGHT_REAL_TIME_DATA, new byte[0], false));
        this.f.e();
    }

    public void e(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("WspCommandUtils", "sending getHistoryWeight values is null");
        } else {
            LogUtil.a("WspCommandUtils", "sending getHistoryWeight values is ", cvx.d(bArr));
        }
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.GET_WEIGHT_HISTORY_DATA, bArr, false);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public String a(byte[] bArr) {
        return cvx.d(bArr);
    }

    public void g() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_DEVICE_SSID, new byte[0], false, 5000));
        this.f.e();
    }

    public void i() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.GET_ALLOW_RESET_WIFI, new byte[0], false, 5000));
        this.f.e();
    }

    public void Fg_(Bundle bundle) {
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.DISABLE_NOTIFICATION, new byte[0], false, -1);
        cjqVar.FO_(bundle);
        cjqVar.c(new cjq.a(cjqVar, false) { // from class: cha.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(r3);
                Objects.requireNonNull(cjqVar);
                e(new byte[][]{BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE});
            }
        });
        this.f.b(cjqVar);
        this.f.e();
    }

    public void d(BleTaskQueueUtil.TaskType taskType, byte[] bArr) {
        cjq cjqVar = new cjq(taskType, bArr);
        if (cgt.e().i()) {
            this.f.b(cjqVar);
            this.f.e();
        } else {
            d(cjqVar);
        }
    }

    public void c(String str) {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO_ENCRYPTED, e(str), true, 5000));
        this.f.e();
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        byte[] bArr;
        int i2;
        byte[] bArr2;
        LogUtil.a("WspCommandUtils", "AssembleDataPackage enter");
        byte[] a2 = ctx.a();
        String d2 = ctx.d();
        LogUtil.a("WspCommandUtils", "AssembleDataPackage aes128Key  ", cpw.d(d2));
        byte[] e = e(str2);
        byte[] b2 = ctu.b(e, d2, a2);
        if (TextUtils.isEmpty(str4)) {
            LogUtil.a("WspCommandUtils", "AssembleDataPackage mRegMessage is null");
            bArr = null;
            i2 = 0;
        } else {
            byte[] b3 = ctu.b(e(str4), d2, a2);
            int length = b3.length;
            LogUtil.c("WspCommandUtils", "AssembleDataPackage encodeRegisterMsg: len:", Integer.valueOf(b3.length));
            bArr = b3;
            i2 = length;
        }
        byte[] d3 = d(b2);
        byte[] e2 = e(str);
        byte[] e3 = e(str3);
        int length2 = e != null ? e.length : 0;
        if (e2 == null) {
            LogUtil.a("WspCommandUtils", "sendWifiConfigInfo ssidArrays is null");
            return;
        }
        LogUtil.c("WspCommandUtils", "AssembleDataPackage passworddArrays:", Integer.valueOf(length2), " ssidArrays:", Integer.valueOf(e2.length), " encodedPasswords:", Integer.valueOf(b2.length), " scaleKeyVectors:", Integer.valueOf(d3.length), " deviceSsid: ", cpw.d(str3), " ssid:", cpw.d(str), " infos:", e3, " registerMsg:", str4, " registerMsgLength:", Integer.valueOf(i2));
        int length3 = e2.length + 20 + b2.length + 1 + i2;
        LogUtil.a("WspCommandUtils", "AssembleDataPackage dataPakageLen: ", Integer.valueOf(length3));
        if ((length3 & 1) == 1) {
            bArr2 = new byte[length3 + 1];
            bArr2[length3] = 0;
        } else {
            bArr2 = new byte[length3];
        }
        byte[] bArr3 = bArr2;
        byte[] a3 = ctv.a(i, d3, d2);
        b(bArr3, e3, e(str3, i));
        c(bArr3, e2, b2, bArr, a3);
        bArr3[1] = e(bArr3, 2, length3);
        g(bArr3);
    }

    private void g(byte[] bArr) {
        if (cgt.e().d(chb.g)) {
            LogUtil.a("WspCommandUtils", "send hilink config info on encrypted mode");
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO_ENCRYPTED, bArr, true, 5000));
        } else {
            this.f.b(new cjq(BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO, bArr, false, 5000));
        }
        this.f.e();
    }

    public cfu m() {
        if (this.m == null) {
            this.m = new cfu(this.f);
        }
        return this.m;
    }

    public static byte[] e(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("WspCommandUtils", "UnsupportedEncodingException");
            }
        }
        return null;
    }

    private static byte e(byte[] bArr, int i, int i2) {
        int i3 = 255;
        while (i < i2) {
            i3 ^= bArr[i] & 255;
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = i3 & 1;
                i3 >>= 1;
                if (i5 != 0) {
                    i3 ^= 184;
                }
            }
            i++;
        }
        return (byte) (i3 & 255);
    }

    public byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        if (bArr != null && bArr.length >= 16) {
            System.arraycopy(bArr, 0, bArr2, 0, 16);
        }
        return bArr2;
    }

    private void b(byte[] bArr, byte[] bArr2, byte b2) {
        if (bArr == null) {
            return;
        }
        bArr[0] = (byte) (ctx.c(bArr2) & 255);
        LogUtil.c("WspCommandUtils", "AssembleDataPackage dataPackages:", ctv.a(bArr));
        bArr[2] = b2;
    }

    private void c(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr5 == null) {
            return;
        }
        int i = 0;
        int i2 = 3;
        int i3 = 0;
        while (i3 < 16) {
            bArr[i2] = bArr5[i3];
            i3++;
            i2++;
        }
        int i4 = i2 + 1;
        bArr[i2] = (byte) (ctx.a(bArr2.length, bArr3.length) & 255);
        int i5 = 0;
        while (i5 < bArr2.length) {
            bArr[i4] = bArr2[i5];
            i5++;
            i4++;
        }
        int i6 = 0;
        while (i6 < bArr3.length) {
            bArr[i4] = bArr3[i6];
            i6++;
            i4++;
        }
        if (bArr4 != null) {
            int i7 = i4 + 1;
            bArr[i4] = (byte) ((bArr4.length >> 4) & 255);
            while (i < bArr4.length) {
                bArr[i7] = bArr4[i];
                i++;
                i7++;
            }
            return;
        }
        bArr[i4] = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
    
        if (r5 != 110) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte e(java.lang.String r4, int r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.String r0 = "-"
            java.lang.String[] r4 = r4.split(r0)
            int r0 = r4.length
            r2 = 3
            if (r0 <= r2) goto L17
            r4 = r4[r2]
            r4.charAt(r1)
        L17:
            java.lang.String r4 = "AssembleDataPackageget VersionMode: "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r0}
            java.lang.String r0 = "WspCommandUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            r4 = 100
            if (r5 == r4) goto L35
            r4 = 101(0x65, float:1.42E-43)
            if (r5 == r4) goto L33
            r4 = 110(0x6e, float:1.54E-43)
            if (r5 == r4) goto L36
            goto L35
        L33:
            r2 = 2
            goto L36
        L35:
            r2 = 1
        L36:
            int r4 = r2 << 4
            byte r4 = (byte) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cha.e(java.lang.String, int):byte");
    }

    public void c(String str, String str2, String str3, String str4) {
        LogUtil.a("WspCommandUtils", "checkBandNewVersionService bind");
        ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).checkScaleNewVersionService(str3, str, str2, cpa.ah(str3), str4);
    }

    public void j(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        MiniUserDataInfo miniUserDataInfo = new MiniUserDataInfo();
        miniUserDataInfo.setWeight(cgf.b(bArr, 0, 2) / 100.0f);
        miniUserDataInfo.setResis(cgf.b(bArr, 2, 12));
        byte[] bArr2 = new byte[32];
        System.arraycopy(bArr, 14, bArr2, 0, 32);
        if (cgf.b(bArr, 14, 32) == 0) {
            miniUserDataInfo.setHuid("0");
        } else {
            miniUserDataInfo.setHuid(cvx.e(cvx.d(bArr2)));
        }
        miniUserDataInfo.setSex(cgf.b(bArr, 46, 1));
        miniUserDataInfo.setAge(cgf.b(bArr, 47, 1));
        miniUserDataInfo.setHeight(cgf.b(bArr, 48, 2));
        miniUserDataInfo.setIsOver(cgf.b(bArr, 50, 1));
        miniUserDataInfo.setIsMainId(cgf.b(bArr, 51, 1));
        miniUserDataInfo.setTime(cgf.b(bArr, 52, 8));
        Bundle bundle = new Bundle();
        bundle.putParcelable("userData", miniUserDataInfo);
        EventBus.d(new EventBus.b("get_user_data_result", bundle));
    }

    public void c(IHealthDeviceCallback iHealthDeviceCallback) {
        LogUtil.a("WspCommandUtils", "registerCallBack start");
        synchronized (d) {
            if (iHealthDeviceCallback == null) {
                LogUtil.h("WspCommandUtils", "registerCallBack callback is null, size = ", Integer.valueOf(this.f714a.size()));
            } else if (!this.f714a.contains(iHealthDeviceCallback)) {
                this.f714a.add(iHealthDeviceCallback);
                LogUtil.a("WspCommandUtils", "registerCallBack add success, size = ", Integer.valueOf(this.f714a.size()));
            }
        }
    }

    public void a(IHealthDeviceCallback iHealthDeviceCallback) {
        LogUtil.a("WspCommandUtils", "HwWspMeasureController unRegisterCallback !");
        synchronized (d) {
            if (iHealthDeviceCallback != null) {
                if (this.f714a.contains(iHealthDeviceCallback)) {
                    this.f714a.remove(iHealthDeviceCallback);
                    LogUtil.a("WspCommandUtils", "remove callback, size = ", Integer.valueOf(this.f714a.size()));
                }
            }
            Object[] objArr = new Object[6];
            objArr[0] = "callback != null ";
            objArr[1] = Boolean.valueOf(iHealthDeviceCallback != null);
            objArr[2] = " mCallBacks.contains(callback) ";
            objArr[3] = Boolean.valueOf(this.f714a.contains(iHealthDeviceCallback));
            objArr[4] = " size =";
            objArr[5] = Integer.valueOf(this.f714a.size());
            LogUtil.h("WspCommandUtils", objArr);
        }
    }

    public void a(HealthDevice healthDevice, int i) {
        synchronized (d) {
            Iterator<IHealthDeviceCallback> it = this.f714a.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    next.onStatusChanged(healthDevice, i);
                } else {
                    LogUtil.h("WspCommandUtils", "onStatusChanged error: callback is null");
                }
            }
        }
    }

    public void Fi_(HealthDevice healthDevice, int i, Bundle bundle) {
        synchronized (d) {
            Iterator<IHealthDeviceCallback> it = this.f714a.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    if (bundle != null && (next instanceof BindHealthDeviceCallback)) {
                        ((BindHealthDeviceCallback) next).onStatusChanged(healthDevice, i, bundle);
                    }
                } else {
                    LogUtil.h("WspCommandUtils", "onStatusChanged fail: callback is null");
                }
            }
        }
    }

    public void a(HealthDevice healthDevice, HealthData healthData) {
        synchronized (d) {
            Iterator<IHealthDeviceCallback> it = this.f714a.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    next.onDataChanged(healthDevice, healthData);
                } else {
                    LogUtil.h("WspCommandUtils", "callback is null");
                }
            }
        }
    }

    public void e(HealthDevice healthDevice, int i) {
        synchronized (d) {
            Iterator<IHealthDeviceCallback> it = this.f714a.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    next.onFailed(healthDevice, i);
                } else {
                    LogUtil.h("WspCommandUtils", "callback is null");
                }
            }
        }
    }

    public void b() {
        synchronized (d) {
            this.f714a.clear();
        }
    }

    public void c(UUID uuid, cgz cgzVar, byte[] bArr, boolean z, int i) {
        if (uuid == null || cgzVar == null) {
            return;
        }
        if (chb.p.equals(uuid)) {
            int e = cgzVar.e(bArr);
            LogUtil.a("WspCommandUtils", "HwWspMeasureController scale status report : ", Integer.valueOf(e), "isSyncHistoryData:", Boolean.valueOf(z));
            if (e == 2 || e == 3) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", e);
                EventBus.d(new EventBus.b("update_wifi_device_cloud_status", bundle));
                return;
            } else {
                if (e != 0 || z || !cgt.e().s() || i == -2) {
                    return;
                }
                EventBus.d(new EventBus.b("send_history_weight_info", new Bundle()));
                return;
            }
        }
        if (chb.at.equals(uuid)) {
            int e2 = cgzVar.e(bArr);
            LogUtil.a("WspCommandUtils", "HwWspMeasureController update ssid resultAck:", Integer.valueOf(e2));
            if (e2 == 0) {
                EventBus.d(new EventBus.b("send_wifi_password"));
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt("result", e2);
            EventBus.d(new EventBus.b("send_wifi_password_result", bundle2));
            return;
        }
        if (chb.ah.equals(uuid)) {
            int e3 = cgzVar.e(bArr);
            LogUtil.a("WspCommandUtils", "HwWspMeasureController send wifi password resultAck:", Integer.valueOf(e3));
            Bundle bundle3 = new Bundle();
            bundle3.putInt("result", e3);
            EventBus.d(new EventBus.b("send_wifi_password_result", bundle3));
            return;
        }
        LogUtil.c("WspCommandUtils", "HwWspMeasureController handlerReceiverDataOne end");
    }

    public void a(UUID uuid, cgz cgzVar, byte[] bArr) {
        if (uuid == null || cgzVar == null) {
            return;
        }
        if (uuid.equals(chb.y)) {
            int e = cgzVar.e(bArr);
            Bundle bundle = new Bundle();
            bundle.putInt("ret", e);
            EventBus.d(new EventBus.b("set_manager_info_result", bundle));
            EventBus.d(new EventBus.b("event_bus_send_ota_url", bundle));
            return;
        }
        if (uuid.equals(chb.i) || uuid.equals(chb.g)) {
            int e2 = cgzVar.e(bArr);
            d(e2);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("ret", e2);
            EventBus.d(new EventBus.b("config_info_result", bundle2));
            return;
        }
        if (uuid.equals(chb.ac)) {
            int e3 = cgzVar.e(bArr);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("ret", e3);
            EventBus.d(new EventBus.b("workkey_info", bundle3));
            return;
        }
        if (chb.v.equals(uuid)) {
            ciy.c().a(bArr);
            return;
        }
        if (chb.w.equals(uuid)) {
            ciy.c().b(bArr);
            return;
        }
        if (chb.x.equals(uuid)) {
            ciy.c().c(bArr);
            return;
        }
        if (chb.u.equals(uuid)) {
            ciy.c().d(bArr);
            return;
        }
        if (chb.j.equals(uuid)) {
            int e4 = cgzVar.e(bArr);
            Bundle bundle4 = new Bundle();
            bundle4.putInt("ret", e4);
            EventBus.d(new EventBus.b("device_reset_result", bundle4));
            return;
        }
        if (chb.h.equals(uuid)) {
            int e5 = cgzVar.e(bArr);
            Bundle bundle5 = new Bundle();
            bundle5.putInt("ret", e5);
            EventBus.d(new EventBus.b("delete_user_data_result", bundle5));
            return;
        }
        LogUtil.c("WspCommandUtils", "HwWspMeasureController handlerReceiverDataTwo end");
    }

    public void Fj_(int i, int i2, boolean z, Bundle bundle, Handler handler) {
        if (handler == null || bundle == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("ret", i);
        if (i2 == -4) {
            EventBus.d(new EventBus.b("send_real_time_weight_info", bundle2));
            return;
        }
        if (i2 == -1 && z) {
            LogUtil.a("WspCommandUtils", "has set user info ,send real weight data cmd");
            EventBus.d(new EventBus.b("send_real_time_weight_info", bundle2));
            return;
        }
        if (i2 == -2) {
            EventBus.d(new EventBus.b("set_user_info_result", bundle2));
            return;
        }
        String string = bundle.getString("productId", null);
        boolean z2 = bundle.getBoolean("isConnectAction", false);
        if (cpa.ap(string) && z2) {
            EventBus.d(new EventBus.b("send_history_weight_info", new Bundle()));
        }
        if (!cpa.ah(string) && !cpa.r(string)) {
            EventBus.d(new EventBus.b("get_device_ssid"));
        } else {
            handler.sendEmptyMessageDelayed(7, 200L);
        }
    }

    public void Fl_(Handler handler, int i, String str, Bundle bundle) {
        if (handler == null) {
            return;
        }
        handler.removeMessages(4);
        u();
        if (i == -4 || i == -1) {
            if (bundle != null && bundle.getInt("guestUser", 0) == 1) {
                EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
                return;
            } else {
                EventBus.d(new EventBus.b("weight_measure_set_user"));
                return;
            }
        }
        if (i == -2) {
            if (cpa.ah(str) || cpa.r(str)) {
                EventBus.d(new EventBus.b("bind_process_completed", new Bundle()));
                handler.sendEmptyMessageDelayed(7, 200L);
                return;
            } else {
                EventBus.d(new EventBus.b("get_device_ssid"));
                return;
            }
        }
        LogUtil.h("WspCommandUtils", "HwWspMeasureController syncCurrentTimeComplete end");
    }

    public void Fk_(Handler handler, int i, String str) {
        Fl_(handler, i, str, null);
    }

    private void d(cjq cjqVar) {
        synchronized (b) {
            this.i.add(cjqVar);
        }
    }

    private void u() {
        synchronized (b) {
            Iterator<cjq> it = this.i.iterator();
            while (it.hasNext()) {
                this.f.b(it.next());
            }
            this.i.clear();
        }
    }

    public void c() {
        synchronized (b) {
            this.i.clear();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:3|4|(1:6)|7|(1:9)|10|(2:15|16)|20|21|22|16) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("WspCommandUtils", "startTimeTask schedule IllegalStateException");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(boolean r10, int r11) {
        /*
            r9 = this;
            java.lang.Object r0 = defpackage.cha.c
            monitor-enter(r0)
            java.util.Timer r1 = r9.h     // Catch: java.lang.Throwable -> L52
            r2 = 0
            if (r1 == 0) goto Ld
            r1.cancel()     // Catch: java.lang.Throwable -> L52
            r9.h = r2     // Catch: java.lang.Throwable -> L52
        Ld:
            java.util.TimerTask r1 = r9.g     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L16
            r1.cancel()     // Catch: java.lang.Throwable -> L52
            r9.g = r2     // Catch: java.lang.Throwable -> L52
        L16:
            r1 = 0
            r2 = 1
            if (r10 != 0) goto L2a
            r10 = -1
            if (r11 != r10) goto L1e
            goto L2a
        L1e:
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = "not start timer"
            r10[r1] = r11     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = "WspCommandUtils"
            com.huawei.hwlogsmodel.LogUtil.h(r11, r10)     // Catch: java.lang.Throwable -> L52
            goto L50
        L2a:
            java.util.Timer r10 = new java.util.Timer     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = "WspCommandUtils"
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L52
            r9.h = r10     // Catch: java.lang.Throwable -> L52
            cha$5 r4 = new cha$5     // Catch: java.lang.Throwable -> L52
            r4.<init>()     // Catch: java.lang.Throwable -> L52
            r9.g = r4     // Catch: java.lang.Throwable -> L52
            java.util.Timer r3 = r9.h     // Catch: java.lang.IllegalStateException -> L44 java.lang.Throwable -> L52
            r5 = 3000(0xbb8, double:1.482E-320)
            r7 = 3000(0xbb8, double:1.482E-320)
            r3.schedule(r4, r5, r7)     // Catch: java.lang.IllegalStateException -> L44 java.lang.Throwable -> L52
            goto L50
        L44:
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = "startTimeTask schedule IllegalStateException"
            r10[r1] = r11     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = "WspCommandUtils"
            com.huawei.hwlogsmodel.LogUtil.b(r11, r10)     // Catch: java.lang.Throwable -> L52
        L50:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            return
        L52:
            r10 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cha.d(boolean, int):void");
    }

    public void x() {
        LogUtil.a("WspCommandUtils", "stopTimeTask");
        synchronized (c) {
            Timer timer = this.h;
            if (timer != null) {
                timer.cancel();
                this.h = null;
            }
            TimerTask timerTask = this.g;
            if (timerTask != null) {
                timerTask.cancel();
                this.g = null;
            }
        }
    }

    private int w() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(15) + calendar.get(16);
        LogUtil.a("WspCommandUtils", "date Zone minute offset", Integer.valueOf(i));
        int i2 = (i * 4) / 3600000;
        int i3 = i2 > 0 ? (i2 & 63) | 128 : ((-i2) & 63) | 64;
        LogUtil.a("WspCommandUtils", "getTimeZoneOffsetHour offSet", Integer.valueOf(i3));
        return i3;
    }

    private int b(Calendar calendar) {
        if (calendar == null) {
            LogUtil.h("WspCommandUtils", "getWeekDay calendar is null");
            return 1;
        }
        boolean z = calendar.getFirstDayOfWeek() == 1;
        LogUtil.c("WspCommandUtils", "HwWspMeasureController syncCurrentTime isFirstSunday:", Boolean.valueOf(z));
        int i = calendar.get(7);
        int i2 = (!z || (i = i + (-1)) >= 1) ? i : 7;
        LogUtil.c("WspCommandUtils", "HwWspMeasureController syncCurrentTime weekDay:", Integer.valueOf(i2));
        return i2;
    }

    public void c(ArrayList<bir> arrayList) {
        this.o = arrayList;
    }

    public void d(UniteDevice uniteDevice) {
        this.n = uniteDevice;
    }

    private void d(int i) {
        ArrayList<bir> arrayList;
        if (i == 0) {
            if (this.n != null && (arrayList = this.o) != null && arrayList.size() > 0) {
                cjg.d().c(this.n, new bir[]{this.o.get(0)});
                this.o.remove(0);
                if (this.o.size() == 0) {
                    LogUtil.a("WspCommandUtils", "sendWifiConfigCacheCommands cache is send end.");
                    this.o = null;
                    this.n = null;
                    return;
                }
                return;
            }
            this.o = null;
            this.n = null;
            return;
        }
        LogUtil.h("WspCommandUtils", "resultAck is not success");
        this.o = null;
        this.n = null;
    }

    public void d() {
        LogUtil.a("WspCommandUtils", "HwWspMeasureController enableScaleStatusCharacter");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.OPEN_STATUS, new byte[0], true));
        this.f.e();
    }

    public void a(byte[] bArr, boolean z) {
        BleTaskQueueUtil bleTaskQueueUtil = this.f;
        if (bleTaskQueueUtil != null) {
            bleTaskQueueUtil.b(new cjq(BleTaskQueueUtil.TaskType.WRITE_BLE_FILE, bArr, z));
            this.f.e();
        }
    }

    public void k() {
        cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.NOTIFY_BLE_FILE, new byte[0], false);
        cjqVar.b().e(new byte[][]{BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE});
        this.f.b(cjqVar);
        this.f.e();
    }

    public void h() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.ENABLE_WEIGHT_SCALE, null));
        this.f.e();
    }

    public void a() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.ENABLE_BODY_MEASUREMENT, null));
        this.f.e();
    }

    public void e() {
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.ENABLE_CLEAR_USER_INFO, null));
        this.f.e();
    }

    public void n() {
        LogUtil.a("WspCommandUtils", "Enter sendClearUserInfo");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.CLEAR_USER_INFO, cgg.a()));
        this.f.e();
    }

    public void a(byte b2, byte b3, byte b4) {
        LogUtil.a("WspCommandUtils", "synCurrentUser is begin...");
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_AGE, new byte[]{b2}));
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_GENDER, new byte[]{b3}));
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_HEIGHT, new byte[]{b4, 0}));
        this.f.e();
    }

    public void t() {
        Calendar calendar = Calendar.getInstance();
        boolean z = calendar.getFirstDayOfWeek() == 1;
        LogUtil.c("WspCommandUtils", "syncCurrentTime isFirstSunday:", Boolean.valueOf(z));
        int i = calendar.get(7);
        if (z && i - 1 == 0) {
            i = 7;
        }
        LogUtil.c("WspCommandUtils", "syncCurrentTime weekDay:", Integer.valueOf(i));
        int i2 = calendar.get(1);
        this.f.b(new cjq(BleTaskQueueUtil.TaskType.SET_TIME, new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((calendar.get(2) + 1) & 255), (byte) (calendar.get(5) & 255), (byte) (calendar.get(11) & 255), (byte) (calendar.get(12) & 255), (byte) (calendar.get(13) & 255), (byte) (i & 255), 0, 0}));
        this.f.e();
    }
}
