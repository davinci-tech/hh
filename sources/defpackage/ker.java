package defpackage;

import android.util.Pair;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.commonTask.base.TaskInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class ker {

    /* renamed from: a, reason: collision with root package name */
    private final String f14322a;
    private final List<kew> b;

    public void b() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ker() {
        this.f14322a = "CommonDataDictionaryManager";
        List<kew> asList = Arrays.asList(new kev("hw.unitedevice.emotion", "hw.device.emotion"));
        this.b = asList;
        ArrayList<Pair> arrayList = new ArrayList();
        for (kew kewVar : asList) {
            if (kewVar instanceof TaskInterface) {
                arrayList.add(Pair.create(kewVar.c(), (TaskInterface) kewVar));
            }
        }
        for (final Pair pair : arrayList) {
            cuk.b().registerDeviceSampleListener((String) pair.first, new DataReceiveCallback() { // from class: keo
                @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
                public final void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                    ker.this.bNo_(pair, i, deviceInfo, cvrVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bNn_, reason: merged with bridge method [inline-methods] */
    public void bNo_(Pair<String, TaskInterface> pair, int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("CommonDataDictionaryManager", "received sample message pkgName " + ((String) pair.first) + " errCode " + i);
        for (DeviceInfo deviceInfo2 : cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "CommonDataDictionaryManager")) {
            if (deviceInfo2.getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                if (cvrVar instanceof cvi) {
                    ((TaskInterface) pair.second).handlePoint(deviceInfo2, (cvi) cvrVar);
                }
                if (cvrVar instanceof cvp) {
                    ((TaskInterface) pair.second).handleEvent(deviceInfo2, (cvp) cvrVar);
                    return;
                }
                return;
            }
        }
        LogUtil.b("CommonDataDictionaryManager", "not found current connect device " + deviceInfo.getDeviceName());
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final ker f14323a = new ker();
    }

    public static ker a() {
        return d.f14323a;
    }
}
