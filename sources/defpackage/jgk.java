package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.BusinessBaseClient;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jgk extends BusinessBaseClient {
    private String d;

    /* renamed from: a, reason: collision with root package name */
    private final Object f13820a = new Object();
    private Map<Integer, List<IBaseResponseCallback>> e = new HashMap(16);

    public jgk(String str) {
        this.d = str;
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceModeClient", "getModeStatus enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(38);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(this.d);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        jfq.c().b(deviceCommand);
        synchronized (this.f13820a) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = this.e.get(1);
                if (list == null) {
                    LogUtil.h("DeviceModeClient", "getModeStatus, callbackList is null");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    this.e.put(1, arrayList);
                } else {
                    LogUtil.a("DeviceModeClient", "getModeStatus, have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void e(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceModeClient", "sendAutoModeDetectSwitchStatus enter");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(38);
        deviceCommand.setCommandID(2);
        deviceCommand.setmIdentify(this.d);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put(cvx.a(cvx.e(i)));
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        jfq.c().b(deviceCommand);
        synchronized (this.f13820a) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = this.e.get(2);
                if (list == null) {
                    LogUtil.h("DeviceModeClient", "sendAutoModeDetectSwitchStatus, callbackList is null");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    this.e.put(2, arrayList);
                } else {
                    LogUtil.a("DeviceModeClient", "sendAutoModeDetectSwitchStatus, have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.BusinessBaseClient
    public void onReceivedData(int i, byte[] bArr) {
        synchronized (this.f13820a) {
            if (i == 0) {
                byte b = bArr[1];
                if (b == 1 || b == 2 || b == 3 || b == 4) {
                    c(i, b, bArr);
                } else {
                    LogUtil.h("DeviceModeClient", "onResponse, switch default");
                }
            }
        }
    }

    private void c(int i, int i2, byte[] bArr) {
        List<IBaseResponseCallback> list = this.e.get(Integer.valueOf(i2));
        if (list != null && list.size() > 0) {
            Iterator<IBaseResponseCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().d(i, bArr);
            }
            this.e.remove(Integer.valueOf(i2));
            return;
        }
        LogUtil.h("DeviceModeClient", "onResponse, callbackList is null or size is zero");
    }
}
