package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import defpackage.djo;
import health.compact.a.HuaweiHealth;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class lbq {

    /* renamed from: a, reason: collision with root package name */
    private static CopyOnWriteArrayList<String> f14750a = new CopyOnWriteArrayList<>();

    public static void b(final String str, final QrCodeOrNfcInfo qrCodeOrNfcInfo, final DeviceInformation deviceInformation) {
        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "bindEquipDevice");
        ThreadPoolManager.d().execute(new Runnable() { // from class: lbw
            @Override // java.lang.Runnable
            public final void run() {
                lbq.b(QrCodeOrNfcInfo.this, deviceInformation, str);
            }
        });
    }

    static /* synthetic */ void b(QrCodeOrNfcInfo qrCodeOrNfcInfo, DeviceInformation deviceInformation, String str) {
        MeasurableDevice bondedDeviceByUniqueId;
        String b = lau.d().b();
        if (!c(qrCodeOrNfcInfo, deviceInformation)) {
            if (TextUtils.isEmpty(str) && (bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(b, false)) != null) {
                str = bondedDeviceByUniqueId.getProductId();
            }
            if (dij.h(str)) {
                e(str, b);
                return;
            }
            return;
        }
        f14750a.add(b);
        d(qrCodeOrNfcInfo, deviceInformation, b);
    }

    private static void d(final QrCodeOrNfcInfo qrCodeOrNfcInfo, final DeviceInformation deviceInformation, final String str) {
        ReleaseLogUtil.e("Track_IDEQ_BindIndoorEquipUtils", "down index_all.json");
        DownloadManagerApi downloadManagerApi = (DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class);
        downloadManagerApi.addDownloadIndexAllCallBack(new DownloadResultCallBack() { // from class: lbz
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
            public final void setDownloadStatus(int i, int i2) {
                lbq.b(QrCodeOrNfcInfo.this, str, deviceInformation, i, i2);
            }
        });
        downloadManagerApi.downloadIndexAll();
    }

    static /* synthetic */ void b(QrCodeOrNfcInfo qrCodeOrNfcInfo, String str, DeviceInformation deviceInformation, int i, int i2) {
        if (i == 0) {
            return;
        }
        if (i == 1) {
            mst.a().b();
            b(qrCodeOrNfcInfo, str, deviceInformation);
        } else {
            LogUtil.h("Track_IDEQ_BindIndoorEquipUtils", "down index_all fail");
            f14750a.remove(str);
        }
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
    }

    private static void b(QrCodeOrNfcInfo qrCodeOrNfcInfo, String str, DeviceInformation deviceInformation) {
        String c;
        if (TextUtils.isEmpty(qrCodeOrNfcInfo.getDeviceType())) {
            LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "deviceType error");
            f14750a.remove(str);
            return;
        }
        HealthDevice.HealthDeviceKind healthDeviceKind = HealthDevice.HealthDeviceKind.getHealthDeviceKind(qrCodeOrNfcInfo.getDeviceType());
        String name = healthDeviceKind.name();
        if (!TextUtils.isEmpty(qrCodeOrNfcInfo.getPid())) {
            ProductMapInfo d = ProductMap.d(qrCodeOrNfcInfo.getPid());
            if (d != null) {
                c = d.h();
            } else {
                e(qrCodeOrNfcInfo, deviceInformation, str, healthDeviceKind);
                return;
            }
        } else {
            c = c(name, deviceInformation.getBleDeviceName());
        }
        a(deviceInformation, str, c, healthDeviceKind);
    }

    private static void e(final QrCodeOrNfcInfo qrCodeOrNfcInfo, final DeviceInformation deviceInformation, final String str, final HealthDevice.HealthDeviceKind healthDeviceKind) {
        cwt.a().b(healthDeviceKind.name(), qrCodeOrNfcInfo.getPid(), new DownloadDeviceInfoCallBack() { // from class: lbq.3
            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onSuccess() {
                ProductMapInfo d = ProductMap.d(QrCodeOrNfcInfo.this.getPid());
                if (d == null || !TextUtils.isEmpty(d.h())) {
                    lbq.f14750a.remove(str);
                } else {
                    lbq.b(d.h(), lbq.b(deviceInformation, healthDeviceKind, d.h(), str), str);
                }
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                lbq.f14750a.remove(str);
            }

            @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
            public void onNetworkError() {
                lbq.f14750a.remove(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static cex b(DeviceInformation deviceInformation, HealthDevice.HealthDeviceKind healthDeviceKind, String str, String str2) {
        cex cexVar = new cex();
        cexVar.setDeviceName(deviceInformation.getBleDeviceName());
        cexVar.setSerialNumber(deviceInformation.getSerialNumber());
        cexVar.setProductId(str);
        cexVar.d(str2);
        cexVar.setKind(healthDeviceKind);
        return cexVar;
    }

    private static void a(DeviceInformation deviceInformation, final String str, final String str2, HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.b("Track_IDEQ_BindIndoorEquipUtils", "productId error");
            f14750a.remove(str);
            return;
        }
        final cex b = b(deviceInformation, healthDeviceKind, str2, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        dkd dkdVar = new dkd(HuaweiHealth.a(), healthDeviceKind.name(), 1, arrayList, new dkc() { // from class: lbq.1
            @Override // defpackage.dkb
            public void onSuccess() {
                LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "startDownloadThirdPartyDevicePlugin onSuccess");
                lbq.b(str2, b, str);
            }

            @Override // defpackage.dkb
            public void onDownload(int i) {
                super.onDownload(i);
            }

            @Override // defpackage.dkb
            public void onFailure(int i) {
                LogUtil.h("Track_IDEQ_BindIndoorEquipUtils", str, " download failure, errorCode is ", Integer.valueOf(i));
                lbq.f14750a.remove(str);
            }
        });
        List<msx> c = mst.a().c(str2);
        if (!koq.b(c)) {
            LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "DeviceDownloadSourceInfo ");
            dkdVar.a(c.get(0).j());
        }
        dkdVar.b();
    }

    private static boolean c(QrCodeOrNfcInfo qrCodeOrNfcInfo, DeviceInformation deviceInformation) {
        if (qrCodeOrNfcInfo == null || deviceInformation == null) {
            LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "bindEquipDevice info error");
            return false;
        }
        String b = lau.d().b();
        if (TextUtils.isEmpty(b) || ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDeviceByUniqueId(b) != null) {
            return false;
        }
        if (!f14750a.contains(b)) {
            return true;
        }
        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "binding Device");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final String str, final HealthDevice healthDevice, final String str2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: lby
            @Override // java.lang.Runnable
            public final void run() {
                ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(r0, healthDevice, new IDeviceEventHandler() { // from class: lbq.5
                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onDeviceFound(HealthDevice healthDevice2) {
                        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "onDeviceFound");
                    }

                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onScanFailed(int i) {
                        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "onScanFailed code: ", Integer.valueOf(i));
                    }

                    @Override // com.huawei.health.device.callback.IDeviceEventHandler
                    public void onStateChanged(int i) {
                        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "onStateChanged code: ", Integer.valueOf(i));
                        if (i == 7) {
                            String str3 = r1;
                            String str4 = r2;
                            dko.b(str3, str4, str4);
                            Intent intent = new Intent();
                            intent.setAction("com.huawei.bone.action.UI_DEVICE_LIST_CHANGED");
                            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
                            if (dij.h(r1)) {
                                lbq.e(r1, r2);
                            }
                        }
                        lbq.f14750a.remove(r2);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2) {
        LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "registerDataClient");
        if (!lau.d().j()) {
            LogUtil.h("Track_IDEQ_BindIndoorEquipUtils", "registerDataClient isDeviceBtConnected false");
        } else {
            djo djoVar = new djo();
            djoVar.a(djoVar.e(new djo.c(str, str2).d(lau.d().e())), null);
        }
    }

    public static String c(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ArrayList<cve> arrayList = new ArrayList(16);
            arrayList.addAll(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList());
            if (koq.b(arrayList)) {
                LogUtil.a("Track_IDEQ_BindIndoorEquipUtils", "getDeviceInfoByBluetooth mAllDeviceList is empty");
                return "";
            }
            cve cveVar = null;
            String str3 = "";
            char c = 0;
            for (cve cveVar2 : arrayList) {
                if (cveVar2 != null && str.equals(cveVar2.r())) {
                    List<String> e = cveVar2.e();
                    if (!koq.b(e)) {
                        for (String str4 : e) {
                            if (!TextUtils.isEmpty(str4) && c("PREFIX", str2, str4)) {
                                if (str4.length() > str3.length()) {
                                    cveVar = cveVar2;
                                    c = 1;
                                    str3 = str4;
                                } else if (str4.length() == str3.length()) {
                                    c = 2;
                                }
                            }
                        }
                    }
                }
            }
            if (cveVar != null && c == 1) {
                List<String> ac = cveVar.ac();
                if (!koq.b(ac) && ac.size() == 1) {
                    return ac.get(0);
                }
            }
        }
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        if (r6 != 4) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean c(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L83
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L15
            goto L83
        L15:
            r6.hashCode()
            int r0 = r6.hashCode()
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case -1926781294: goto L50;
                case -1838093487: goto L45;
                case 66096429: goto L3a;
                case 66409183: goto L2f;
                case 1669509300: goto L24;
                default: goto L23;
            }
        L23:
            goto L5b
        L24:
            java.lang.String r0 = "CONTAIN"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L2d
            goto L5b
        L2d:
            r6 = r2
            goto L5c
        L2f:
            java.lang.String r0 = "EXACT"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L38
            goto L5b
        L38:
            r6 = r3
            goto L5c
        L3a:
            java.lang.String r0 = "EMPTY"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L43
            goto L5b
        L43:
            r6 = r4
            goto L5c
        L45:
            java.lang.String r0 = "SUFFIX"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L4e
            goto L5b
        L4e:
            r6 = r5
            goto L5c
        L50:
            java.lang.String r0 = "PREFIX"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L59
            goto L5b
        L59:
            r6 = r1
            goto L5c
        L5b:
            r6 = -1
        L5c:
            if (r6 == 0) goto L7c
            if (r6 == r5) goto L75
            if (r6 == r4) goto L6e
            if (r6 == r3) goto L67
            if (r6 == r2) goto L6e
            goto L83
        L67:
            boolean r6 = java.util.Objects.equals(r7, r8)
            if (r6 == 0) goto L83
            return r5
        L6e:
            boolean r6 = r7.contains(r8)
            if (r6 == 0) goto L83
            return r5
        L75:
            boolean r6 = r7.endsWith(r8)
            if (r6 == 0) goto L83
            return r5
        L7c:
            boolean r6 = r7.startsWith(r8)
            if (r6 == 0) goto L83
            return r5
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lbq.c(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
