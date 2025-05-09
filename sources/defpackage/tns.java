package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.EnumMonitorType;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.MonitorMessage;
import com.huawei.wearengine.monitor.QueryDataCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class tns extends HwBaseManager implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f17268a = new Object();
    private Map<String, Map<String, List<QueryDataCallback>>> b;
    private MonitorCallback c;
    private tpm d;
    private Map<Integer, Integer> e;

    static class b {
        private static final tns c = new tns();
    }

    private tns() {
        super(BaseApplication.getContext());
        this.b = new HashMap(16);
        this.c = null;
        this.e = new HashMap(16);
        LogUtil.a("WearEngine_HwWearEngineManager", "enter HwWearEngineManager");
        this.b.clear();
        this.d = new tpm(50);
        a();
        tpb.a().e();
    }

    private void a() {
        this.e.put(3, Integer.valueOf(EnumMonitorType.EVENT_WEAR_APP_INSTALLATION_REPORT.getValue()));
    }

    public static tns b() {
        return b.c;
    }

    public void e(MonitorCallback monitorCallback) {
        synchronized (this) {
            if (monitorCallback != null) {
                LogUtil.a("WearEngine_HwWearEngineManager", "subscribeMonitorReceiver enter");
                this.c = monitorCallback;
            }
        }
    }

    public void d() {
        synchronized (this) {
            LogUtil.a("WearEngine_HwWearEngineManager", "unsubscribeMonitorReceiver enter");
            this.c = null;
        }
    }

    public MonitorCallback e() {
        return this.c;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 53;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("WearEngine_HwWearEngineManager", "getResult");
        if (deviceInfo == null || bArr == null || bArr.length < 2) {
            LogUtil.h("WearEngine_HwWearEngineManager", "device or dataContents is null");
            return;
        }
        blt.d("WearEngine_HwWearEngineManager", bArr, "HwWearEngineManager getResult ");
        byte b2 = bArr[1];
        if (b2 == 2) {
            e(deviceInfo, bArr);
        } else if (b2 == 3) {
            tpr.e().e(bArr);
        } else {
            LogUtil.a("WearEngine_HwWearEngineManager", "other commandID");
        }
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        int e;
        String str;
        List<cwd> a2 = tqy.a(bArr);
        if (a2 != null && a2.size() > 0) {
            int e2 = tqy.e(a2, 1);
            int e3 = tqy.e(a2, 2);
            if (e(e2, e3)) {
                str = tqy.c(tqy.d(a2, 3));
                e = -1;
            } else {
                e = tqy.e(a2, 3);
                str = "";
            }
            if (e2 == -1) {
                LogUtil.b("WearEngine_HwWearEngineManager", "onResponseCallback subCmd is invalid");
                return;
            } else {
                c(e2, e3, e, str, deviceInfo);
                return;
            }
        }
        LogUtil.b("WearEngine_HwWearEngineManager", "onResponseCallback tlv is invalid");
    }

    private boolean e(int i, int i2) {
        Integer num = this.e.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue() == i2;
        }
        LogUtil.h("WearEngine_HwWearEngineManager", "isEventValueForString eventStringType not exist");
        return false;
    }

    private void c(int i, int i2, int i3, String str, DeviceInfo deviceInfo) {
        if (e(i, i2)) {
            b(i, i2, str, deviceInfo);
        } else {
            c(i, i2, i3, deviceInfo);
        }
    }

    private void c(int i, int i2, int i3, DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwWearEngineManager", "handleEventResultCallback eventValue is", Integer.valueOf(i3));
        Device c = tqy.c(deviceInfo);
        if (c == null) {
            LogUtil.b("WearEngine_HwWearEngineManager", "handleEventResultCallback device is empty");
            return;
        }
        String descByValue = EnumMonitorType.getDescByValue(i2);
        if (descByValue == null) {
            return;
        }
        if (i == 1 || i == 2) {
            tpo.d().b(descByValue, i, i3);
            return;
        }
        if (i != 3) {
            if (i == 5) {
                e(i3, deviceInfo, c, descByValue);
                return;
            } else {
                LogUtil.b("WearEngine_HwWearEngineManager", "handleEventResultCallback subCmd is invalid");
                return;
            }
        }
        b(deviceInfo.getDeviceIdentify(), descByValue, i3);
        MonitorMessage monitorMessage = new MonitorMessage(descByValue, c.getUuid(), i3);
        MonitorCallback monitorCallback = this.c;
        if (monitorCallback == null) {
            LogUtil.h("WearEngine_HwWearEngineManager", "handleEventResultCallback mMonitorCallback is null");
            return;
        }
        try {
            monitorCallback.onChanged(a.w, monitorMessage);
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwWearEngineManager", "send EventReportMessage failed.");
        }
    }

    private void b(int i, int i2, String str, DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwWearEngineManager", "handleEventResultCallback eventValue is ", str);
        if (i == 3) {
            d(i2, str, deviceInfo);
        } else {
            LogUtil.b("WearEngine_HwWearEngineManager", "handleEventResultCallback subCmd is invalid");
        }
    }

    private void d(int i, String str, DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwWearEngineManager", "handleEventStringResult eventType is " + i);
        if (i == EnumMonitorType.EVENT_WEAR_APP_INSTALLATION_REPORT.getValue()) {
            c(str, deviceInfo);
        }
    }

    private void c(String str, DeviceInfo deviceInfo) {
        toc e = toc.e();
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        tod todVar = new tod();
        todVar.b(1);
        todVar.b(str);
        e.b(deviceIdentify, todVar);
    }

    private void e(int i, DeviceInfo deviceInfo, Device device, String str) {
        Map<String, List<QueryDataCallback>> remove;
        b(deviceInfo.getDeviceIdentify(), str, i);
        MonitorMessage monitorMessage = new MonitorMessage(str, device.getUuid(), i);
        synchronized (f17268a) {
            remove = this.b.remove(deviceInfo.getDeviceIdentify());
        }
        if (remove == null) {
            LogUtil.b("WearEngine_HwWearEngineManager", "handleQueryCallback deviceCallback is null");
            return;
        }
        List<QueryDataCallback> list = remove.get(str);
        if (list == null) {
            LogUtil.b("WearEngine_HwWearEngineManager", "handleQueryCallback callbacks is null");
            return;
        }
        try {
            Iterator<QueryDataCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().onDataReceived(0, monitorMessage);
            }
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwWearEngineManager", "handle QueryDataResultCallback RemoteException");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void e(String str, String str2, QueryDataCallback queryDataCallback) {
        char c;
        if (str == null) {
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1943109551:
                if (str.equals("wearStatus")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -615154554:
                if (str.equals("sportStatus")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 440773975:
                if (str.equals("powerStatus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 628829609:
                if (str.equals(KnitHealthDetailActivity.KEY_SLEEP_STATUS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1267337158:
                if (str.equals("chargeStatus")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1623401406:
                if (str.equals("userAvailableKbytes")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            tpl.d().b(str2, queryDataCallback);
            return;
        }
        if (c != 1) {
            if (c == 2) {
                tpj.c().a(str2, queryDataCallback);
                return;
            } else if (c != 3 && c != 4 && c != 5) {
                LogUtil.b("WearEngine_HwWearEngineManager", "not supported eventType", str);
                return;
            }
        }
        b(str, str2, queryDataCallback);
    }

    private void b(String str, String str2, QueryDataCallback queryDataCallback) {
        List<QueryDataCallback> list;
        synchronized (f17268a) {
            Map<String, List<QueryDataCallback>> map = this.b.get(str2);
            if (map == null) {
                map = new HashMap<>(16);
                list = new ArrayList<>(16);
            } else {
                list = map.get(str);
                if (list == null) {
                    list = new ArrayList<>(16);
                }
            }
            list.add(queryDataCallback);
            map.put(str, list);
            this.b.put(str2, map);
        }
        LogUtil.a("WearEngine_HwWearEngineManager", "registerQueryDataListener callback ok");
    }

    public void b(DeviceInfo deviceInfo, byte[] bArr) {
        tpl.d().e(deviceInfo, bArr);
        tpj.c().c(deviceInfo, bArr);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void b(String str, String str2, int i) {
        char c;
        if (str == null || str2 == null) {
            return;
        }
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1943109551:
                if (str2.equals("wearStatus")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -615154554:
                if (str2.equals("sportStatus")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 440773975:
                if (str2.equals("powerStatus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 628829609:
                if (str2.equals(KnitHealthDetailActivity.KEY_SLEEP_STATUS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1206544299:
                if (str2.equals("heartRateAlarm")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1267337158:
                if (str2.equals("chargeStatus")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            d(str, str2, i);
            return;
        }
        if (c == 1) {
            e(this.d, str, str2, i);
            return;
        }
        if (c == 2) {
            a(str, str2, i);
            return;
        }
        if (c == 3) {
            d(this.d, str, str2, i);
            return;
        }
        if (c == 4) {
            c(this.d, str, str2, i);
        } else if (c == 5) {
            a(this.d, str, str2, i);
        } else {
            LogUtil.b("WearEngine_HwWearEngineManager", "not supported eventType", str2);
        }
    }

    public void a(String str, String str2, QueryDataCallback queryDataCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || queryDataCallback == null) {
            LogUtil.b("WearEngine_HwWearEngineManager", "sendQueryCommand has null param");
            return;
        }
        DeviceCommand b2 = tqu.b(str2);
        if (b2 == null) {
            LogUtil.b("WearEngine_HwWearEngineManager", "sendQueryCommand command is null");
            try {
                queryDataCallback.onDataReceived(12, new MonitorMessage());
            } catch (RemoteException unused) {
                LogUtil.b("WearEngine_HwWearEngineManager", "sendQueryCommand RemoteException");
            }
            throw new IllegalStateException(String.valueOf(12));
        }
        LogUtil.a("WearEngine_HwWearEngineManager", "sendQueryCommand command is:", b2.toString());
        b2.setmIdentify(str);
        e(str2, str, queryDataCallback);
        tqy.a(b2);
    }

    private void a(String str, String str2, int i) {
        int b2 = tpj.c().b(i, -1);
        if (b2 != -1) {
            tpj.c().d(str, str2, String.valueOf(b2));
        }
    }

    private void d(String str, String str2, int i) {
        int c = tpl.d().c(i, -1);
        if (c != -1) {
            tpl.d().e(str, str2, String.valueOf(c));
        }
    }

    private void e(tpm tpmVar, String str, String str2, int i) {
        if (i < 1 || i > 255) {
            i = -1;
        }
        if (i != -1) {
            tpmVar.b(str, str2, String.valueOf(i));
        }
    }

    private void d(tpm tpmVar, String str, String str2, int i) {
        if (i != 1 && i != 2) {
            i = -1;
        }
        if (i != -1) {
            tpmVar.b(str, str2, String.valueOf(i));
        }
    }

    private void c(tpm tpmVar, String str, String str2, int i) {
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            i = -1;
        }
        if (i != -1) {
            tpmVar.b(str, str2, String.valueOf(i));
        }
    }

    private void a(tpm tpmVar, String str, String str2, int i) {
        if (i != 1 && i != 2 && i != 3) {
            i = -1;
        }
        if (i != -1) {
            tpmVar.b(str, str2, String.valueOf(i));
        }
    }
}
