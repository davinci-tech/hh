package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class ccv {
    private static ccv c;
    private Context d;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f623a = new Object();
    private static final Object e = new Object();
    private static final Object b = new Object();
    private int f = 0;
    private volatile int m = 0;
    private volatile List<cvk> n = new ArrayList(10);
    private boolean j = false;
    private boolean h = false;
    private volatile int o = 0;
    private int g = 0;
    private final d i = new d(BaseApplication.getContext().getMainLooper());

    private ccv(Context context) {
        this.d = context;
    }

    public static ccv d() {
        ccv ccvVar;
        synchronized (f623a) {
            if (c == null) {
                c = new ccv(BaseApplication.getContext());
            }
            ccvVar = c;
        }
        return ccvVar;
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 10) {
                LogUtil.a("DownloadDeviceResourceManager", "RESET_PLUGIN_DOWN");
                ccv.this.c();
            } else if (i == 20) {
                ccv.this.a(-1);
            } else {
                LogUtil.a("DownloadDeviceResourceManager", "enter default branch");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("DownloadDeviceResourceManager", "resetDownloadingTag");
        this.j = false;
        this.i.removeMessages(10);
    }

    public void a() {
        String str = cdk.e().c((String) null, true) + ((LogUtil.c() || b()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta");
        LogUtil.c("DownloadDeviceResourceManager", "resetDownloadIndex downloadUrl:", str);
        msn.c().d(str, "");
        msn.c().a("plugin_index", "");
    }

    public void d(String str) {
        boolean z = this.j;
        if (!z) {
            this.j = true;
            this.i.sendEmptyMessageDelayed(10, 300000L);
            b(str);
            return;
        }
        LogUtil.a("DownloadDeviceResourceManager", "startDownloadPlugin", Boolean.valueOf(z));
    }

    public void e(String str) {
        cdh.d().c(str);
    }

    private void b(final String str) {
        final ccx e2 = ccx.e();
        e2.d(new DownloadResultCallBack() { // from class: ccv.5
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
            public void setDownloadStatus(int i, int i2) {
                if (i == 0) {
                    return;
                }
                LogUtil.a("DownloadDeviceResourceManager", "downloadDeviceIndexFile status", Integer.valueOf(i));
                ccv.this.c(str);
                if (e2 != null) {
                    LogUtil.a("DownloadDeviceResourceManager", "downloadDeviceIndexFile removeCallBack");
                    e2.b(this);
                    e2.a();
                }
            }
        });
        e2.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        cdk.e().e(new PullListener() { // from class: ccv.2
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar != null) {
                    ccv.this.i.removeMessages(20);
                    int i = msoVar.i();
                    LogUtil.a("DownloadDeviceResourceManager", "update index file status:" + i);
                    if (i == 1 || i == -11) {
                        cdk.e().b();
                        ccv.this.e(str, i);
                        return;
                    }
                    if (i == -1) {
                        LogUtil.a("DownloadDeviceResourceManager", "update index fail do not update UI");
                        ccv.this.c();
                        ccv.this.a(-1);
                        return;
                    } else {
                        if (i == -5) {
                            LogUtil.a("DownloadDeviceResourceManager", "can not find service source");
                            ccv.this.c();
                            ccv.this.a(-1);
                            return;
                        }
                        LogUtil.a("DownloadDeviceResourceManager", "status is error");
                        return;
                    }
                }
                LogUtil.h("DownloadDeviceResourceManager", "status is error null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x002d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.util.List<cvk> r0 = r4.n
            r0.clear()
            cdk r0 = defpackage.cdk.e()
            java.util.List r0 = r0.c()
            boolean r1 = defpackage.koq.b(r0)
            java.lang.String r2 = "DownloadDeviceResourceManager"
            if (r1 == 0) goto L29
            java.lang.String r5 = "have no index info"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r5)
            r4.c()
            r5 = -11
            if (r6 != r5) goto L28
            r4.a()
        L28:
            return
        L29:
            java.util.Iterator r6 = r0.iterator()
        L2d:
            boolean r0 = r6.hasNext()
            r1 = 0
            if (r0 == 0) goto Lb7
            java.lang.Object r0 = r6.next()
            cvk r0 = (defpackage.cvk) r0
            java.lang.String r3 = "SMART_ALL"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L5c
            java.lang.String r1 = r0.g()
            if (r1 == 0) goto L52
            java.lang.String r1 = r0.g()
            boolean r1 = android.text.TextUtils.equals(r1, r5)
            if (r1 != 0) goto L6f
        L52:
            java.lang.String r0 = "ezPluginIndexInfo wearKind is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            goto L2d
        L5c:
            java.lang.String r3 = r0.g()
            if (r3 == 0) goto Lac
            java.lang.String r3 = r0.g()
            boolean r3 = r4.a(r3)
            if (r3 != 0) goto L6d
            goto Lac
        L6d:
            r4.h = r1
        L6f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "has band plugin info, uuid :"
            r1.<init>(r3)
            java.lang.String r3 = r0.e()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "publish mode :"
            r1.<init>(r3)
            java.lang.String r3 = r0.f()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            boolean r1 = r4.a(r0)
            if (r1 == 0) goto La8
            goto L2d
        La8:
            r4.d(r0)
            goto L2d
        Lac:
            java.lang.String r0 = "ezPluginIndexInfo is not watchOrBand"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            goto L2d
        Lb7:
            java.util.List<cvk> r5 = r4.n
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto Ld8
            java.lang.String r5 = "have no band plugin info"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r5)
            boolean r5 = r4.h
            if (r5 == 0) goto Ld4
            r4.h = r1
            java.lang.String r5 = "SMART_WATCH"
            r4.b(r5)
            goto Ldf
        Ld4:
            r4.c()
            goto Ldf
        Ld8:
            java.util.List r5 = r4.e()
            r4.a(r5)
        Ldf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ccv.e(java.lang.String, int):void");
    }

    private boolean a(String str) {
        return TextUtils.equals(str, "SMART_BAND") || TextUtils.equals(str, "SMART_WATCH");
    }

    private void d(cvk cvkVar) {
        if (Utils.o()) {
            if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                c(cvkVar);
                return;
            }
            return;
        }
        if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
            c(cvkVar);
        }
    }

    private boolean a(cvk cvkVar) {
        if (!CommonUtil.as() || (!cvkVar.g().equals("SMART_WATCH") && !cvkVar.g().equals("SMART_BAND"))) {
            return false;
        }
        c(cvkVar);
        return true;
    }

    private void a(List<cvk> list) {
        LogUtil.a("DownloadDeviceResourceManager", "enter checkIsNeedUpdate");
        for (final cvk cvkVar : list) {
            if (cdk.e().h(cvkVar.e())) {
                cdk.e().b(cvkVar.e(), new PullListener() { // from class: ccv.3
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        LogUtil.a("DownloadDeviceResourceManager", "result status " + msoVar.i());
                        if (msoVar.i() == 1) {
                            LogUtil.a("DownloadDeviceResourceManager", "description file is not need update,do not update ui.");
                            ccv.this.b(cvkVar);
                        }
                    }
                });
            }
        }
        if (e().size() > 0) {
            e(e());
        } else {
            c();
        }
    }

    private void e(List<cvk> list) {
        LogUtil.a("DownloadDeviceResourceManager", "enter updateDescriptionForWear");
        this.f = 0;
        synchronized (b) {
            this.m = 0;
        }
        for (final cvk cvkVar : list) {
            final DeviceDownloadSourceInfo c2 = cvkVar.c();
            Object[] objArr = new Object[2];
            objArr[0] = "updatePluginDescription :";
            objArr[1] = c2 == null ? Constants.NULL : c2.toString();
            LogUtil.a("DownloadDeviceResourceManager", objArr);
            final String a2 = cvkVar.a();
            if (c2 != null && c2.getSite() == 1) {
                LogUtil.a("DownloadDeviceResourceManager", "DownloadHealthCloudManager downloadSourceInfo");
                ccu.d().d(c2, cvkVar.e(), new PullListener() { // from class: ccv.4
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        LogUtil.a("DownloadDeviceResourceManager", "onPullingChange result:", Integer.valueOf(msoVar.i()));
                        ccv.this.d(cvkVar, msoVar);
                        cvc b2 = cdk.e().b(cvkVar.e());
                        String b3 = b2 != null ? b2.b() : "";
                        LogUtil.a("DownloadDeviceResourceManager", "onPullingChange localVersion:", a2, "cacheVersion:", b3);
                        cpq.a(cvy.c(c2), a2, b3, msoVar, msqVar);
                    }
                }, new PullHealthBiListener() { // from class: ccv.1
                    @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
                    public void onPullHealthBiCalling(int i, String str, msq msqVar) {
                        LogUtil.a("DownloadDeviceResourceManager", "updatePluginDescription onPullHealthBiCalling");
                        cpq.e(i, str, msqVar);
                    }
                });
            } else {
                cdk.e().a(cvkVar.e(), new PullListener() { // from class: ccv.6
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        LogUtil.a("DownloadDeviceResourceManager", "update Description total size is :", Integer.valueOf(msoVar.j()), "pull size is :", Integer.valueOf(msoVar.b()), "uuid is :", msqVar.o());
                        ccv.this.d(cvkVar, msoVar);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cvk cvkVar, mso msoVar) {
        if (msoVar.i() == 1) {
            LogUtil.a("DownloadDeviceResourceManager", "updateDescriptionForWear success");
            this.f++;
            synchronized (b) {
                this.m += msoVar.j();
            }
            if (this.f == this.n.size()) {
                f();
                return;
            }
            return;
        }
        b(cvkVar);
        LogUtil.a("DownloadDeviceResourceManager", "update index fail do not update UI");
    }

    private void f() {
        LogUtil.a("DownloadDeviceResourceManager", "enter updatePluginForWear size：", Integer.valueOf(this.n.size()));
        if (this.n.size() > 0) {
            DeviceDownloadSourceInfo c2 = this.n.get(0).c();
            if (c2 != null && c2.getSite() == 1) {
                e(c2, 0);
            } else {
                a(c2, 0);
            }
        }
    }

    private String e(int i) {
        String e2 = (i >= this.n.size() || i < 0) ? "" : this.n.get(i).e();
        LogUtil.a("DownloadDeviceResourceManager", "getFetchUuid size, index：", Integer.valueOf(this.n.size()), Integer.valueOf(i));
        return e2;
    }

    private void e(DeviceDownloadSourceInfo deviceDownloadSourceInfo, final int i) {
        LogUtil.a("DownloadDeviceResourceManager", "downloadCloudPlugin");
        String e2 = e(i);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("DownloadDeviceResourceManager", "downloadCloudPlugin fetchUuid empty");
        } else {
            ccu.d().b(deviceDownloadSourceInfo, e2, new PullListener() { // from class: ccv.7
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    LogUtil.c("DownloadDeviceResourceManager", "downloadCloudPlugin onPullingChange result:", Integer.valueOf(msoVar.i()));
                    ccv.this.d(msoVar, i);
                }
            }, new PullHealthBiListener() { // from class: ccv.10
                @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
                public void onPullHealthBiCalling(int i2, String str, msq msqVar) {
                    LogUtil.a("DownloadDeviceResourceManager", "downloadCloudPlugin onPullHealthBiCalling");
                    cpq.e(i2, str, msqVar);
                }
            });
        }
    }

    private void a(DeviceDownloadSourceInfo deviceDownloadSourceInfo, final int i) {
        String e2 = e(i);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("DownloadDeviceResourceManager", "downloadOnePlugin fetchUuid empty");
        } else {
            LogUtil.a("DownloadDeviceResourceManager", "enter downloadOnePlugin uuid:", e2);
            cdk.e().c(e2, new PullListener() { // from class: ccv.9
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    ccv.this.d(msoVar, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(mso msoVar, int i) {
        if (msoVar.i() == 1) {
            LogUtil.a("DownloadDeviceResourceManager", "single file downloaded successfully");
            if (i > this.n.size() - 1 || i < 0) {
                LogUtil.a("DownloadDeviceResourceManager", "index is out wearPluginSize.");
                c();
                return;
            } else {
                jfu.e(cdk.e().b(this.n.get(i).e()));
                a(i, msoVar);
                return;
            }
        }
        if (msoVar.i() == 0) {
            a(b(msoVar.b()));
            return;
        }
        LogUtil.h("DownloadDeviceResourceManager", "downloadOnePlugin failed");
        synchronized (e) {
            this.o = 0;
        }
        a(-1);
    }

    private void a(int i, mso msoVar) {
        if (i > this.n.size() - 1 || i < 0) {
            LogUtil.a("DownloadDeviceResourceManager", "downloadOneData_index is out wearPluginSize.");
            return;
        }
        String str = this.n.get(i).e() + "_version";
        String a2 = this.n.get(i).a();
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(this.d, String.valueOf(1003), str, a2, storageParams);
        Object obj = e;
        synchronized (obj) {
            this.o += msoVar.j();
        }
        int i2 = i + 1;
        if (this.n.size() > i2) {
            DeviceDownloadSourceInfo c2 = this.n.get(i2).c();
            if (c2 != null && c2.getSite() == 1) {
                e(c2, i2);
                return;
            } else {
                a(c2, i2);
                return;
            }
        }
        if (this.h) {
            this.h = false;
            b("SMART_WATCH");
            return;
        }
        c();
        LogUtil.a("DownloadDeviceResourceManager", "multiple files downloaded successfully");
        synchronized (obj) {
            this.o = 0;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE");
        LocalBroadcastManager.getInstance(this.d).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.ACTION_DOWNLOAD_BACK_PROCESS");
        intent.putExtra("progress", i);
        LocalBroadcastManager.getInstance(this.d).sendBroadcast(intent);
    }

    private int b(int i) {
        if (this.m == 0) {
            return 0;
        }
        int i2 = ((this.o + i) * 100) / this.m;
        if (i2 > 99) {
            i2 = 99;
        }
        if (this.g != i2) {
            LogUtil.a("DownloadDeviceResourceManager", "showDownloadProgress downloaded : ", Integer.valueOf(this.o + i), " total : ", Integer.valueOf(this.m), " showDownloadProgress progress:", Integer.valueOf(i2));
            this.g = i2;
        }
        return i2;
    }

    private void c(cvk cvkVar) {
        List<DeviceInfo> b2 = jfv.b();
        if (b2 == null || b2.size() <= 0) {
            return;
        }
        HashMap hashMap = new HashMap(10);
        for (DeviceInfo deviceInfo : b2) {
            hashMap.put(Integer.valueOf(deviceInfo.getProductType()), deviceInfo);
            LogUtil.c("DownloadDeviceResourceManager", "updateStorageDevices mapValue:", deviceInfo.getDeviceName());
        }
        if (cup.b().get(cvkVar.e()) == null || !hashMap.containsKey(Integer.valueOf(cup.b().get(cvkVar.e()).intValue()))) {
            return;
        }
        this.n.add(cvkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cvk cvkVar) {
        if (this.n.size() <= 0 || !this.n.contains(cvkVar)) {
            return;
        }
        this.n.remove(cvkVar);
    }

    private List<cvk> e() {
        ArrayList arrayList = new ArrayList(10);
        Iterator<cvk> it = this.n.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    private static boolean b() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_SITE", "app_test_site_type");
        LogUtil.a("DownloadDeviceResourceManager", "deviceSite:", b3);
        return CommonUtil.as() && "1".equals(b2) && "release".equals(b3);
    }
}
