package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
class cdh {
    private static cdh c;
    private static final Object d = new Object();
    private Handler e;
    private cvk b = null;
    private int h = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f637a = 0;

    private cdh() {
        this.e = null;
        this.e = new Handler(BaseApplication.getContext().getMainLooper());
    }

    public static cdh d() {
        cdh cdhVar;
        synchronized (d) {
            if (c == null) {
                c = new cdh();
            }
            cdhVar = c;
        }
        return cdhVar;
    }

    private void e() {
        LogUtil.a("DownloadSingleResourceManager", "Enter destroy.");
        if (this.b != null) {
            this.b = null;
        }
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        a();
    }

    private static void a() {
        synchronized (d) {
            c = null;
        }
    }

    public void c(final String str) {
        if (cdg.e()) {
            return;
        }
        Handler handler = this.e;
        if (handler == null) {
            LogUtil.h("DownloadSingleResourceManager", "startDownloadTargetPlugin mHandler is null.");
            b(str);
            return;
        }
        if (handler.hasMessages(20000)) {
            this.e.removeMessages(20000);
        }
        e(str);
        this.e.postDelayed(new Runnable() { // from class: cdh.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DownloadSingleResourceManager", "startDownloadTargetPlugin timeout.");
                cdh.this.b(str);
            }
        }, 20000L);
        a(str);
    }

    private void e(String str) {
        msq e;
        if (TextUtils.isEmpty(str) || (e = msn.c().e(str)) == null) {
            return;
        }
        LogUtil.a("DownloadSingleResourceManager", "cancelDownloading uuid:", str);
        cdv.b().c(e);
    }

    private void a(final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DownloadSingleResourceManager", "downloadDeviceIndexFile uuid is empty.");
            b(str);
        } else {
            final ccx e = ccx.e();
            e.d(new DownloadResultCallBack() { // from class: cdh.1
                @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
                public void setDownloadStatus(int i, int i2) {
                    if (i == 0) {
                        return;
                    }
                    LogUtil.a("DownloadSingleResourceManager", "downloadDeviceIndexFile status", Integer.valueOf(i));
                    cdh.this.h(str);
                    if (e != null) {
                        LogUtil.a("DownloadSingleResourceManager", "downloadDeviceIndexFile removeCallBack");
                        e.b(this);
                        e.a();
                    }
                }
            });
            e.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(final String str) {
        cdk.e().e(new PullListener() { // from class: cdh.4
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("DownloadSingleResourceManager", "downloadDeviceIndexFile result is null.");
                    cdh.this.b(str);
                    return;
                }
                int i = msoVar.i();
                LogUtil.a("DownloadSingleResourceManager", "downloadDeviceIndexFile fetchStatus: ", Integer.valueOf(i));
                if (i == 1 || i == -11) {
                    cdk.e().b();
                    cdh.this.e(str, i);
                } else if (i != 0) {
                    cdh.this.b(str);
                } else {
                    LogUtil.a("DownloadSingleResourceManager", "downloadDeviceIndexFile is downloading.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        List<cvk> c2 = cdk.e().c();
        if (c2 == null || c2.isEmpty()) {
            LogUtil.h("DownloadSingleResourceManager", "checkTargetIndex indexInfoList is empty.");
            b(str);
            if (i == -11) {
                ccv.d().a();
                return;
            }
            return;
        }
        Iterator<cvk> it = c2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            cvk next = it.next();
            if (str.equals(next.e())) {
                LogUtil.a("DownloadSingleResourceManager", "checkTargetIndex exist target index.");
                c(next);
                break;
            }
        }
        cvk cvkVar = this.b;
        if (cvkVar == null) {
            d(str);
        } else {
            d(cvkVar);
        }
    }

    private void d(cvk cvkVar) {
        final String e = cvkVar.e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.a("DownloadSingleResourceManager", "checkPluginDescription uuid is empty.");
            b(e);
            return;
        }
        if (cdk.e().h(e)) {
            cdk.e().b(e, new PullListener() { // from class: cdh.2
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    if (msoVar == null) {
                        LogUtil.h("DownloadSingleResourceManager", "updatePluginDescriptionInfo result is null.");
                        cdh.this.b(e);
                        return;
                    }
                    int i = msoVar.i();
                    LogUtil.a("DownloadSingleResourceManager", "result status :", Integer.valueOf(i));
                    if (i == 1) {
                        cdh.this.b = null;
                    }
                }
            });
        }
        if (this.b != null) {
            f(cvkVar);
        } else {
            i(e);
            d(e);
        }
    }

    private void i(String str) {
        if (jfu.e(str) == null) {
            LogUtil.a("DownloadSingleResourceManager", "updateDeviceInfo uuid: ", str);
            jfu.e(cdk.e().b(str));
        }
    }

    private void f(final cvk cvkVar) {
        final DeviceDownloadSourceInfo c2 = cvkVar.c();
        Object[] objArr = new Object[2];
        objArr[0] = "updatePluginDescription :";
        objArr[1] = c2 == null ? Constants.NULL : c2.toString();
        LogUtil.a("DownloadSingleResourceManager", objArr);
        if (c2 != null && c2.getSite() == 1) {
            LogUtil.a("DownloadSingleResourceManager", "DownloadHealthCloudManager downloadSourceInfo");
            ccu.d().d(c2, cvkVar.e(), new PullListener() { // from class: cdh.7
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    LogUtil.a("DownloadSingleResourceManager", "onPullingChange result:", Integer.valueOf(msoVar.i()));
                    cdh.this.e(cvkVar, msoVar);
                    cvc b = cdk.e().b(cvkVar.e());
                    String b2 = b != null ? b.b() : "";
                    LogUtil.a("DownloadSingleResourceManager", "onPullingChange localVersion:", cvkVar.a(), "cacheVersion:", b2);
                    cpq.a(cvy.c(c2), cvkVar.a(), b2, msoVar, msqVar);
                }
            }, new PullHealthBiListener() { // from class: cdh.8
                @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
                public void onPullHealthBiCalling(int i, String str, msq msqVar) {
                    LogUtil.a("DownloadSingleResourceManager", "updatePluginDescription onPullHealthBiCalling");
                    cpq.e(i, str, msqVar);
                }
            });
        } else {
            cdk.e().a(cvkVar.e(), new PullListener() { // from class: cdh.9
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    cdh.this.e(cvkVar, msoVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(cvk cvkVar, mso msoVar) {
        if (msoVar == null) {
            LogUtil.h("DownloadSingleResourceManager", "updatePluginDescription result is null.");
            d(-1);
            return;
        }
        LogUtil.a("DownloadSingleResourceManager", "updatePluginDescription totalSize :", Integer.valueOf(msoVar.j()), ", pullSize :", Integer.valueOf(msoVar.b()));
        if (msoVar.i() == 1) {
            this.h = msoVar.j();
            DeviceDownloadSourceInfo c2 = cvkVar.c();
            if (c2 != null && c2.getSite() == 1) {
                e(cvkVar);
                return;
            } else {
                b(cvkVar);
                return;
            }
        }
        b(cvkVar.e());
    }

    private void e(final cvk cvkVar) {
        LogUtil.a("DownloadSingleResourceManager", "downloadCloudPlugin");
        ccu.d().b(cvkVar.c(), cvkVar.e(), new PullListener() { // from class: cdh.10
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                LogUtil.c("DownloadSingleResourceManager", "downloadCloudPlugin onPullingChange result:", Integer.valueOf(msoVar.i()));
                cdh.this.d(cvkVar, msoVar);
            }
        }, new PullHealthBiListener() { // from class: cdh.6
            @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
            public void onPullHealthBiCalling(int i, String str, msq msqVar) {
                LogUtil.a("DownloadSingleResourceManager", "downloadCloudPlugin onPullHealthBiCalling");
                cpq.e(i, str, msqVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cvk cvkVar, mso msoVar) {
        if (msoVar == null) {
            LogUtil.h("DownloadSingleResourceManager", "downloadTargetPlugin result is null.");
            b(cvkVar.e());
            return;
        }
        int i = msoVar.i();
        LogUtil.a("DownloadSingleResourceManager", "downloadTargetPlugin status :", Integer.valueOf(i));
        if (i == 1) {
            jfu.e(cdk.e().b(cvkVar.e()));
            a(cvkVar);
            d(cvkVar.e());
        } else if (i == 0) {
            d(a(msoVar.b()));
        } else {
            b(cvkVar.e());
        }
    }

    private void b(final cvk cvkVar) {
        cdk.e().c(cvkVar.e(), new PullListener() { // from class: cdh.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                cdh.this.d(cvkVar, msoVar);
            }
        });
    }

    private void a(cvk cvkVar) {
        String str = cvkVar.e() + "_version";
        String a2 = cvkVar.a();
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str, a2, storageParams);
        LogUtil.a("DownloadSingleResourceManager", "saveFetchVersionInfo fetchVersionKey: ", str, ", fetchVersion: ", a2);
    }

    private void c(cvk cvkVar) {
        if (CommonUtil.as() && (TextUtils.equals(cvkVar.g(), "SMART_WATCH") || TextUtils.equals(cvkVar.g(), "SMART_BAND"))) {
            this.b = cvkVar;
        }
        if (Utils.o()) {
            if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                this.b = cvkVar;
                return;
            }
            return;
        }
        if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
            this.b = cvkVar;
        }
    }

    private int a(int i) {
        int i2 = this.h;
        if (i2 == 0) {
            return 0;
        }
        int i3 = (i * 100) / i2;
        if (i3 > 99) {
            i3 = 99;
        }
        if (this.f637a != i3) {
            LogUtil.a("DownloadSingleResourceManager", "calculateProgress downloaded :", Integer.valueOf(i), ", mTotalSize :", Integer.valueOf(this.h), ", progress:", Integer.valueOf(i3));
            this.f637a = i3;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        d(-1);
        e(str);
        e();
    }

    private void d(String str) {
        String pluginIndexOobe = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(str);
        LogUtil.a("DownloadSingleResourceManager", "sendDownloadDoneBroadcast: ", pluginIndexOobe);
        if (!TextUtils.isEmpty(pluginIndexOobe)) {
            c(pluginIndexOobe);
            return;
        }
        if (cdk.e().h(str)) {
            Intent intent = new Intent();
            intent.setAction("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
            msp.b(cuv.f11488a, BaseApplication.getContext().getFilesDir().getPath(), "plugin.zip");
        } else {
            b(str);
        }
        e();
    }

    private void d(int i) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS");
        intent.putExtra("progress", i);
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }
}
