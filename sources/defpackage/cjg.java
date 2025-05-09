package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import defpackage.chb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cjg {
    private Context b;
    private snq d;
    private static final byte[] e = {0, 0};

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, SendMode> f742a = Collections.unmodifiableMap(new HashMap() { // from class: cjg.4
        {
            put(chb.ax.toString(), SendMode.PROTOCOL_TYPE_5A);
            put(chb.g.toString(), SendMode.PROTOCOL_TYPE_FRAGMENT);
        }
    });

    private cjg() {
        this.b = BaseApplication.getContext();
        snq c = snq.c();
        this.d = c;
        c.a(this.b);
    }

    public static cjg d() {
        return a.d;
    }

    public void e(String str, DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.d.registerDeviceStateListener(str, deviceStatusChangeCallback);
    }

    public void e(String str, MessageReceiveCallback messageReceiveCallback) {
        this.d.registerDeviceMessageListener(str, messageReceiveCallback);
    }

    public void a(String str) {
        this.d.unregisterDeviceStateListener(str);
    }

    public void b(String str) {
        this.d.unregisterDeviceMessageListener(str);
    }

    public void c(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        this.d.scanDevice(scanMode, list, deviceScanCallback);
    }

    public void a(UniteDevice uniteDevice, ConnectMode connectMode) {
        this.d.connectDevice(uniteDevice, false, connectMode);
    }

    public void e(UniteDevice uniteDevice, bir birVar) {
        LogUtil.a("MeasureLogicBaseManager", "sendCommand : uuid = ", birVar.b(), " data = ", cvx.d(birVar.e()), " SendMode = ", Integer.valueOf(birVar.g().value()));
        this.d.sendCommand(uniteDevice, birVar);
    }

    public void c(UniteDevice uniteDevice, bir[] birVarArr) {
        if (birVarArr == null || birVarArr.length <= 0) {
            LogUtil.h("MeasureLogicBaseManager", "sendCommand : commands is empty");
            return;
        }
        for (bir birVar : birVarArr) {
            e(uniteDevice, birVar);
        }
    }

    public void a(UniteDevice uniteDevice, String str, String str2, boolean z) {
        SendMode sendMode = f742a.get(str2);
        if (sendMode == null) {
            sendMode = SendMode.PROTOCOL_TYPE_D;
        }
        this.d.setCharacteristicNotify(uniteDevice, str, str2, sendMode, z);
        LogUtil.a("MeasureLogicBaseManager", "setCharacteristicNotify :", str2, sendMode.toString(), Boolean.valueOf(z));
    }

    public Map<String, UniteDevice> e() {
        return this.d.getDeviceList();
    }

    public void d(UniteDevice uniteDevice) {
        this.d.disconnect(uniteDevice);
    }

    public static bir[] d(cjq cjqVar) {
        if (cjqVar == null) {
            LogUtil.h("MeasureLogicBaseManager", "getUdsCommand : data==null");
            return null;
        }
        UUID uuid = chb.an;
        UUID uuid2 = chb.f;
        chb.c cVar = chb.c().get(cjqVar.f());
        if (cVar != null) {
            uuid = cVar.e();
            uuid2 = cVar.c();
        }
        SendMode sendMode = f742a.get(uuid2.toString());
        if (sendMode == null) {
            sendMode = SendMode.PROTOCOL_TYPE_D;
        }
        byte[] c = cjqVar.c();
        boolean i = cjqVar.i();
        bir.a aVar = new bir.a();
        aVar.d(CharacterOperationType.WRITE).c(i);
        if (sendMode == SendMode.PROTOCOL_TYPE_FRAGMENT) {
            ArrayList<byte[]> c2 = c(c, 222);
            bir[] birVarArr = new bir[c2.size()];
            for (int i2 = 0; i2 < c2.size(); i2++) {
                birVarArr[i2] = aVar.b(c(uuid, uuid2, sendMode, c2.get(i2)));
            }
            return birVarArr;
        }
        return new bir[]{aVar.b(c(uuid, uuid2, sendMode, c))};
    }

    private static bir c(UUID uuid, UUID uuid2, SendMode sendMode, byte[] bArr) {
        bir birVar = new bir();
        birVar.b(sendMode);
        birVar.c(uuid.toString());
        birVar.b(uuid2.toString());
        birVar.e(bArr);
        return birVar;
    }

    private static ArrayList<byte[]> c(byte[] bArr, int i) {
        if (bArr == null || i <= 0 || bArr.length <= i) {
            return new ArrayList<byte[]>(bArr) { // from class: cjg.2
                final /* synthetic */ byte[] d;

                {
                    this.d = bArr;
                    add(ArrayUtils.concatByteArrays(cjg.e, bArr));
                }
            };
        }
        int ceil = (int) Math.ceil(bArr.length / i);
        int i2 = ceil - 1;
        ArrayList<byte[]> arrayList = new ArrayList<>(ceil);
        for (int i3 = 0; i3 < ceil; i3++) {
            byte[] bArr2 = {(byte) i2, (byte) i3};
            int i4 = i * i3;
            int i5 = i4 + i;
            if (i3 == i2) {
                i5 = bArr.length;
            }
            arrayList.add(i3, ArrayUtils.concatByteArrays(bArr2, Arrays.copyOfRange(bArr, i4, i5)));
        }
        return arrayList;
    }

    public boolean a(UniteDevice uniteDevice, String str, String str2) {
        return this.d.isSupportCharactor(uniteDevice, str, str2);
    }

    public void e(UniteDevice uniteDevice) {
        a("registerDeviceStateListener");
        b("registerDeviceMessageListener");
        d(uniteDevice);
    }

    static class a {
        private static cjg d = new cjg();
    }

    public void e(String str, BleTaskQueueUtil bleTaskQueueUtil) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MeasureLogicBaseManager", "registerUdsCallback, productId is null");
        }
        if (cpa.ar(str)) {
            LogUtil.a("MeasureLogicBaseManager", "registerUdsCallback by uds");
            e("registerDeviceStateListener", new cem());
            e("registerDeviceMessageListener", new ceq(bleTaskQueueUtil));
        }
    }
}
