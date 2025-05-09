package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class kca {
    private cwl e;

    private kca() {
        this.e = new cwl();
    }

    public void e(DeviceInfo deviceInfo) {
        d();
    }

    private void d() {
        LogUtil.a("DataDictionarySingleManager", "registerDataReceiveCallback enter");
        DataReceiveCallback dataReceiveCallback = new DataReceiveCallback() { // from class: kca.2
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                if (cvrVar == null || deviceInfo == null) {
                    LogUtil.h("DataDictionarySingleManager", "onDataReceived message or device is null,errorCode:", Integer.valueOf(i));
                    return;
                }
                if (!(cvrVar instanceof cvp)) {
                    LogUtil.h("DataDictionarySingleManager", "onDataReceived message not SampleEvent");
                    return;
                }
                cvp cvpVar = (cvp) cvrVar;
                long e = cvpVar.e();
                if (e != 800100004) {
                    LogUtil.h("DataDictionarySingleManager", "onDataReceived eventId not support:", Long.valueOf(e));
                    return;
                }
                byte[] c2 = cvpVar.c();
                if (c2 == null) {
                    LogUtil.h("DataDictionarySingleManager", "onDataReceived eventData is null");
                    return;
                }
                String d = cvx.d(c2);
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                LogUtil.a("DataDictionarySingleManager", "onDataReceived data:", d);
                kca.this.e(deviceIdentify, d);
            }
        };
        LogUtil.a("DataDictionarySingleManager", "DataDictionarySingleManager constructor srcName:", "hw.unitedevice.dic.event", ",mDataReceiveCallback:", dataReceiveCallback);
        cuk.b().registerDeviceSampleListener("hw.unitedevice.dic.event", dataReceiveCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        LinkedList<kbm> linkedList = new LinkedList<>();
        a(str2, linkedList);
        if (linkedList.isEmpty()) {
            LogUtil.h("DataDictionarySingleManager", "onDataReceived singleInfosList is Empty");
        } else {
            LogUtil.a("DataDictionarySingleManager", "parseParamsToSync:", Integer.valueOf(linkedList.get(0).d()));
            kcb.e(str, linkedList);
        }
    }

    private void a(String str, LinkedList<kbm> linkedList) {
        try {
            Iterator<cwe> it = this.e.a(str).a().iterator();
            while (it.hasNext()) {
                b(it.next(), linkedList);
            }
        } catch (cwg unused) {
            LogUtil.b("DataDictionarySingleManager", "onDataReceived processTlvData TlvException");
        }
    }

    private void b(cwe cweVar, LinkedList<kbm> linkedList) {
        if (cweVar == null) {
            return;
        }
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            d(it.next(), linkedList);
        }
    }

    private void d(cwe cweVar, LinkedList<kbm> linkedList) {
        if (cweVar == null) {
            return;
        }
        List<cwd> e = cweVar.e();
        LogUtil.a("DataDictionarySingleManager", "parseFieldTlv.size:", Integer.valueOf(e.size()));
        for (cwd cwdVar : e) {
            int w = CommonUtil.w(cwdVar.e());
            String c2 = cwdVar.c();
            LogUtil.a("DataDictionarySingleManager", "TLV-getTag:", Integer.valueOf(w), "TLV-getValue:", c2);
            if (w == 3) {
                kbm kbmVar = new kbm();
                kbmVar.d(CommonUtil.w(c2));
                linkedList.add(kbmVar);
            }
        }
    }

    public static kca c() {
        return c.f14267a;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final kca f14267a = new kca();
    }
}
