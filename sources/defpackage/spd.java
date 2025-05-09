package defpackage;

import android.os.RemoteException;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.P2pReceiver;
import defpackage.spn;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.List;
import java.util.UUID;

/* loaded from: classes7.dex */
public class spd {

    /* renamed from: a, reason: collision with root package name */
    private final MessageReceiveCallback f17193a;
    private P2pReceiver b;
    private IdentityInfo c;
    private String d;
    private bmn e;

    public spd(IdentityInfo identityInfo, P2pReceiver p2pReceiver) {
        MessageReceiveCallback messageReceiveCallback = new MessageReceiveCallback() { // from class: spd.1
            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
            }

            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
                if (biuVar != null) {
                    byte[] a2 = biuVar.a();
                    if (a2 == null || a2.length <= 2) {
                        LogUtil.a("FileBaseManager", "frames is error.");
                        return;
                    }
                    byte b = a2[0];
                    byte b2 = a2[1];
                    LogUtil.d("FileBaseManager", "serviceId: ", Integer.valueOf(b));
                    if (b == 44 && b2 == 7) {
                        String d = blq.d(a2);
                        LogUtil.c("FileBaseManager", "5.44.7 handleDeviceSendNotice: ", d);
                        if (d == null) {
                            return;
                        }
                        String substring = d.substring(4);
                        sol solVar = new sol();
                        try {
                            spd.this.e(solVar, spd.this.e.c(substring).b());
                        } catch (bmk unused) {
                            LogUtil.e("FileBaseManager", "handleDeviceSendNotice error");
                        }
                        LogUtil.c("FileBaseManager", "enter registerReceiver: ", solVar.g());
                        if (spd.this.c.equals(new IdentityInfo(solVar.g(), solVar.h()))) {
                            spd.this.e(solVar, deviceInfo);
                        }
                    }
                }
            }
        };
        this.f17193a = messageReceiveCallback;
        this.e = new bmn();
        this.b = p2pReceiver;
        this.c = identityInfo;
        this.d = UUID.randomUUID().toString();
        snq.c().registerDeviceMessageListener(this.d, messageReceiveCallback);
    }

    public void c() {
        snq.c().unregisterDeviceMessageListener(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(sol solVar, List<bmi> list) {
        for (bmi bmiVar : list) {
            int d = bli.d(bmiVar.e(), 16);
            String c = bmiVar.c();
            switch (d) {
                case 1:
                    solVar.a(blq.d(c));
                    break;
                case 2:
                    solVar.m(bli.e(c));
                    break;
                case 3:
                    solVar.c(bli.e(c));
                    break;
                case 4:
                    solVar.i(bli.e(c));
                    break;
                case 5:
                case 6:
                default:
                    LogUtil.d("FileBaseManager", "5.44.7 parseDeviceReportTlv default");
                    break;
                case 7:
                    solVar.c(blq.d(c));
                    break;
                case 8:
                    solVar.h(blq.d(c));
                    break;
                case 9:
                    solVar.d(blq.d(c));
                    break;
                case 10:
                    solVar.f(blq.d(c));
                    break;
                case 11:
                    solVar.b(blq.d(c));
                    break;
                case 12:
                    if (bli.e(c) == 1) {
                        solVar.c(true);
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(sol solVar, final DeviceInfo deviceInfo) {
        snq.c().startReceiveFileFromWear(c(solVar, deviceInfo), new ITransferFileCallback.Stub() { // from class: spd.4
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.c("FileBaseManager", "ITransferFileCallback onSuccess");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.c("FileBaseManager", "ITransferFileCallback onFailure");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.c("FileBaseManager", "ITransferFileCallback onProgress");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                if (spd.this.b != null) {
                    CommonFileInfoParcel commonFileInfoParcel = (CommonFileInfoParcel) new Gson().fromJson(JsonSanitizer.sanitize(str), CommonFileInfoParcel.class);
                    if (commonFileInfoParcel == null) {
                        LogUtil.a("FileBaseManager", "onResponse FileInfo is null");
                        return;
                    }
                    spn.b bVar = new spn.b();
                    bVar.e(commonFileInfoParcel.getDescription());
                    bVar.a(new File(commonFileInfoParcel.getFilePath()));
                    spd.this.b.onReceiveMessage(deviceInfo, bVar.e());
                }
            }
        });
    }

    private RequestFileInfo c(sol solVar, DeviceInfo deviceInfo) {
        RequestFileInfo requestFileInfo = new RequestFileInfo();
        requestFileInfo.setFileName(solVar.m());
        requestFileInfo.setFileType(solVar.u());
        requestFileInfo.setFileId(solVar.l());
        requestFileInfo.setFileSize(solVar.v());
        requestFileInfo.setDescription(solVar.j());
        requestFileInfo.setSourcePackageName(solVar.ae());
        requestFileInfo.setDestinationPackageName(solVar.g());
        requestFileInfo.setSourceCertificate(solVar.ah());
        requestFileInfo.setDestinationCertificate(solVar.h());
        requestFileInfo.setCancelTransmission(solVar.ao());
        requestFileInfo.setNeedVerify(true);
        requestFileInfo.setTimes(new int[]{solVar.ak(), solVar.k()});
        requestFileInfo.setKit(false);
        requestFileInfo.setDeviceReport(true);
        if (deviceInfo != null) {
            requestFileInfo.setDeviceMac(deviceInfo.getDeviceMac());
        }
        return requestFileInfo;
    }
}
