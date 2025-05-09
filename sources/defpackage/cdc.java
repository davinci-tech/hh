package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullHealthBiListener;
import com.huawei.pluginmgr.filedownload.PullListener;
import defpackage.cdc;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cdc {
    private DownloadDeviceInfoCallBack d;
    private List<String> k;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f630a = new Object();
    private static final Object e = new Object();
    private static final Object b = new Object();
    private volatile List<String> l = new ArrayList(16);
    private List<cvk> c = new ArrayList(16);
    private List<cvk> h = new ArrayList(16);
    private volatile List<cvk> f = new ArrayList(16);
    private int o = 0;
    private int g = 0;
    private volatile int n = 0;
    private int m = 0;
    private int j = 0;
    private ExtendHandler i = HandlerCenter.yt_(new e(), "DownloadSeriesResourceManager");

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "message status:", Integer.valueOf(message.what));
            switch (message.what) {
                case 101:
                    cdc.this.r();
                    return true;
                case 102:
                default:
                    LogUtil.h("DownloadSeriesResourceManager", "other message");
                    return false;
                case 103:
                    if (message.obj instanceof Integer) {
                        synchronized (cdc.b) {
                            cdc.this.d(((Integer) message.obj).intValue());
                        }
                    }
                    return true;
                case 104:
                    cdc.this.Dd_(message);
                    return true;
                case 105:
                    HandlerExecutor.e(new Runnable() { // from class: cdf
                        @Override // java.lang.Runnable
                        public final void run() {
                            cdc.e.this.c();
                        }
                    });
                    cdc.this.m();
                    return true;
                case 106:
                    HandlerExecutor.e(new Runnable() { // from class: cdb
                        @Override // java.lang.Runnable
                        public final void run() {
                            cdc.e.this.a();
                        }
                    });
                    cdc.this.m();
                    msp.b(cuv.f11488a, BaseApplication.getContext().getFilesDir().getPath(), "plugin.zip");
                    return true;
            }
        }

        /* synthetic */ void a() {
            cdc.this.q();
        }

        /* synthetic */ void c() {
            cdc.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Dd_(final Message message) {
        ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "downloadErrorProcess");
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: ccz
            @Override // java.lang.Runnable
            public final void run() {
                cdc.this.De_(message);
            }
        });
        n();
        m();
    }

    /* synthetic */ void De_(Message message) {
        Object obj = message.obj;
        if (obj instanceof Integer) {
            e(((Integer) obj).intValue());
        } else {
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "downloadErrorProcess obj:", obj);
            e(-1);
        }
    }

    public cdc(List<String> list, DownloadDeviceInfoCallBack downloadDeviceInfoCallBack) {
        this.k = new ArrayList(16);
        this.k = list;
        this.d = downloadDeviceInfoCallBack;
    }

    private boolean c(String str) {
        boolean z = (jfu.e(str) == null && jfu.c(str, false) == null && jfu.c(str, true) == null) ? false : true;
        LogUtil.a("DownloadSeriesResourceManager", "isHasDevice is ", Boolean.valueOf(z));
        return z;
    }

    public void a() {
        if (this.d == null) {
            LogUtil.h("DownloadSeriesResourceManager", "mCallBack is null");
            return;
        }
        n();
        List<String> list = this.k;
        if (list == null || list.isEmpty()) {
            LogUtil.h("DownloadSeriesResourceManager", "uuidList is empty");
            b(-1);
            return;
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            Iterator<String> it = this.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!c(it.next())) {
                    if (!cdg.e()) {
                        o();
                        return;
                    }
                }
            }
            k();
            return;
        }
        j();
        this.c.clear();
        this.h.clear();
        this.j = 0;
        Message obtain = Message.obtain();
        obtain.what = 101;
        this.i.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if ((this.k.contains("96cea4f7-9a5e-4db4-9dda-244885c5446f") || this.k.contains("69a968a4-5db8-4d1e-a390-762cb8039784") || this.k.contains("c7d28238-fec2-4985-b2a4-f29c96637ac4") || this.k.contains("f5195256-bc1c-44b8-a4b7-52866f623d11")) && !this.k.contains("6ed99f86-c6b4-43e2-8339-52b2d72a9168")) {
            this.k.add("6ed99f86-c6b4-43e2-8339-52b2d72a9168");
        }
        for (String str : this.k) {
            if (jfu.c(str, false) == null) {
                e(str);
            }
        }
        if (koq.b(this.l)) {
            k();
        } else {
            g();
        }
    }

    private void g() {
        final ccx e2 = ccx.e();
        e2.d(new DownloadResultCallBack() { // from class: cdc.2
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
            public void setDownloadStatus(int i, int i2) {
                if (i == 0) {
                    return;
                }
                LogUtil.a("DownloadSeriesResourceManager", "downloadDeviceIndexFile status", Integer.valueOf(i));
                cdc.this.t();
                if (e2 != null) {
                    LogUtil.a("DownloadSeriesResourceManager", "downloadDeviceIndexFile removeCallBack");
                    e2.b(this);
                    e2.a();
                }
            }
        });
        e2.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        cdk.e().e(new PullListener() { // from class: cdc.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("DownloadSeriesResourceManager", "downloadDeviceIndexFile result is null.");
                    cdc.this.b(-1);
                    return;
                }
                int i = msoVar.i();
                LogUtil.a("DownloadSeriesResourceManager", "downloadDeviceIndexFile fetchStatus: ", Integer.valueOf(i));
                if (i == 1 || i == -11) {
                    cdk.e().b();
                    cdc.this.a(i);
                } else if (i == 0) {
                    LogUtil.a("DownloadSeriesResourceManager", "downloadDeviceIndexFile is downloading.");
                } else if (i == -9) {
                    cdc.this.b(-3);
                } else {
                    cdc.this.b(-1);
                }
            }
        });
    }

    private void e(int i) {
        DownloadDeviceInfoCallBack downloadDeviceInfoCallBack = this.d;
        if (downloadDeviceInfoCallBack != null) {
            downloadDeviceInfoCallBack.onFailure(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        DownloadDeviceInfoCallBack downloadDeviceInfoCallBack = this.d;
        if (downloadDeviceInfoCallBack != null) {
            downloadDeviceInfoCallBack.onSuccess();
        } else {
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "mCallBack is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        DownloadDeviceInfoCallBack downloadDeviceInfoCallBack = this.d;
        if (downloadDeviceInfoCallBack != null) {
            downloadDeviceInfoCallBack.netWorkError();
        }
    }

    private int f() {
        int size;
        synchronized (f630a) {
            size = this.l.size();
        }
        return size;
    }

    private void j() {
        synchronized (f630a) {
            this.l.clear();
        }
    }

    private void e(String str) {
        synchronized (f630a) {
            if (!this.l.contains(str)) {
                this.l.add(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        synchronized (f630a) {
            if (this.l.contains(str)) {
                this.l.remove(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        List<cvk> c = cdk.e().c();
        if (c == null || c.isEmpty()) {
            LogUtil.h("DownloadSeriesResourceManager", "checkTargetIndex indexInfoList is empty.");
            b(-1);
            if (i == -11) {
                ccv.d().a();
                return;
            }
            return;
        }
        Iterator<cvk> it = c.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
        if (koq.b(this.c)) {
            LogUtil.h("DownloadSeriesResourceManager", "mDevicePluginList is empty");
            b(-1);
        } else {
            s();
        }
    }

    private void b(cvk cvkVar) {
        if (!CommonUtil.bv() && (TextUtils.equals(cvkVar.g(), "SMART_WATCH") || TextUtils.equals(cvkVar.g(), "SMART_BAND"))) {
            LogUtil.c("DownloadSeriesResourceManager", "not release add...");
            this.c.add(cvkVar);
        } else {
            if (Utils.o()) {
                if (TextUtils.equals(cvkVar.f(), "2") || TextUtils.equals(cvkVar.f(), "3")) {
                    this.c.add(cvkVar);
                    return;
                }
                return;
            }
            if (TextUtils.equals(cvkVar.f(), "1") || TextUtils.equals(cvkVar.f(), "3")) {
                this.c.add(cvkVar);
            }
        }
    }

    private void s() {
        for (cvk cvkVar : this.c) {
            if (this.l.contains(cvkVar.e()) && !TextUtils.isEmpty(cvkVar.b())) {
                LogUtil.a("DownloadSeriesResourceManager", "updateNeedDownloadIndex, oobeid ", cvkVar.b());
                e(cvkVar.b());
            }
        }
        for (cvk cvkVar2 : this.c) {
            if (this.l.contains(cvkVar2.e())) {
                this.h.add(cvkVar2);
            }
        }
        LogUtil.a("DownloadSeriesResourceManager", "mEzPlugins.size(): ", Integer.valueOf(this.h.size()), " mWearPlugins.size(): ", Integer.valueOf(this.l.size()));
        if (this.h.size() == this.l.size()) {
            c();
            return;
        }
        sqo.k("updateNeedDownloadIndex not support.");
        LogUtil.h("DownloadSeriesResourceManager", "updateNeedDownloadIndex not support");
        b(-1);
    }

    private void c() {
        this.f.clear();
        LogUtil.a("DownloadSeriesResourceManager", "enter checkIsNeedUpdate");
        for (final cvk cvkVar : this.h) {
            if (cdk.e().h(cvkVar.e())) {
                cdk.e().b(cvkVar.e(), new PullListener() { // from class: cdc.5
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        if (msoVar == null) {
                            LogUtil.h("DownloadSeriesResourceManager", "onPullingChange result is null");
                            return;
                        }
                        LogUtil.c("DownloadSeriesResourceManager", "enter checkIsNeedUpdate: ", Integer.valueOf(msoVar.i()), " ", cvkVar.e());
                        if (msoVar.i() == 1) {
                            if (jfu.e(cvkVar.e()) == null) {
                                cdc.this.h(cvkVar.e());
                            }
                            cdc.this.c(cvkVar);
                            cdc.this.d(cvkVar.e());
                        }
                    }
                });
            }
        }
        if (f() > 0) {
            p();
        } else {
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(String str) {
        jfu.e(cdk.e().b(str));
    }

    private cvk a(String str) {
        for (cvk cvkVar : cdk.e().c()) {
            if (cvkVar.e().equals(str)) {
                return cvkVar;
            }
        }
        return null;
    }

    private void p() {
        LogUtil.a("DownloadSeriesResourceManager", "enter updateDescriptionForWear: ", Integer.valueOf(this.l.size()));
        synchronized (b) {
            this.o = 0;
            this.g = 0;
        }
        for (String str : this.l) {
            cvk a2 = a(str);
            DeviceDownloadSourceInfo c = a2 != null ? a2.c() : null;
            if (c != null && c.getSite() == 1) {
                LogUtil.a("DownloadSeriesResourceManager", "DownloadHealthCloudManager downloadSourceInfo");
                b(a2, c, str);
            } else {
                cdk.e().a(str, new PullListener() { // from class: cdc.4
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public void onPullingChange(msq msqVar, mso msoVar) {
                        cdc.this.e(msqVar, msoVar);
                    }
                });
            }
        }
    }

    private void b(cvk cvkVar, final DeviceDownloadSourceInfo deviceDownloadSourceInfo, final String str) {
        final String a2 = cvkVar.a();
        ccu.d().d(deviceDownloadSourceInfo, cvkVar.e(), new PullListener() { // from class: cdc.6
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                cdc.this.e(msqVar, msoVar);
                cvc b2 = cdk.e().b(str);
                String b3 = b2 != null ? b2.b() : "";
                LogUtil.a("DownloadSeriesResourceManager", "onPullingChange localVersion:", a2, "cacheVersion:", b3);
                cpq.a(cvy.c(deviceDownloadSourceInfo), a2, b3, msoVar, msqVar);
            }
        }, new PullHealthBiListener() { // from class: cdc.9
            @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
            public void onPullHealthBiCalling(int i, String str2, msq msqVar) {
                LogUtil.a("DownloadSeriesResourceManager", "updatePluginDescription onPullHealthBiCalling");
                cpq.e(i, str2, msqVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(msq msqVar, mso msoVar) {
        if (msqVar == null || msoVar == null) {
            LogUtil.h("DownloadSeriesResourceManager", "onPullingChange task or result is null");
            b(-1);
            return;
        }
        LogUtil.c("DownloadSeriesResourceManager", "updateDescriptionForWear status: ", Integer.valueOf(msoVar.i()));
        if (msoVar.i() == 1) {
            synchronized (b) {
                this.o += msoVar.j();
                this.g++;
                if (this.g == f()) {
                    LogUtil.a("DownloadSeriesResourceManager", "Updated successfully. The total size of the resource:", Integer.valueOf(this.o));
                    i();
                }
            }
            return;
        }
        if (msoVar.i() == -9) {
            b(-3);
        } else {
            b(-1);
        }
    }

    private void i() {
        if (this.l.size() <= this.j) {
            LogUtil.a("DownloadSeriesResourceManager", "downloadTargetPlugin mWearPlugins is null");
            return;
        }
        final String str = this.l.get(this.j);
        cvk a2 = a(str);
        DeviceDownloadSourceInfo c = a2 != null ? a2.c() : null;
        if (c != null && c.getSite() == 1) {
            LogUtil.a("DownloadSeriesResourceManager", "downloadTargetPlugin DownloadHealthCloudManager");
            ccu.d().b(c, str, new PullListener() { // from class: cdc.10
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    LogUtil.c("DownloadSeriesResourceManager", "downloadCloudPlugin onPullingChange result:", Integer.valueOf(msoVar.i()));
                    cdc.this.e(msoVar, str);
                }
            }, new PullHealthBiListener() { // from class: cdc.8
                @Override // com.huawei.pluginmgr.filedownload.PullHealthBiListener
                public void onPullHealthBiCalling(int i, String str2, msq msqVar) {
                    LogUtil.a("DownloadSeriesResourceManager", "downloadCloudPlugin onPullHealthBiCalling");
                    cpq.e(i, str2, msqVar);
                }
            });
        } else {
            cdk.e().c(str, new PullListener() { // from class: cdc.7
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public void onPullingChange(msq msqVar, mso msoVar) {
                    cdc.this.e(msoVar, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(mso msoVar, String str) {
        if (msoVar == null) {
            LogUtil.h("DownloadSeriesResourceManager", "downloadTargetPlugin result is null.");
            b(-1);
            return;
        }
        int i = msoVar.i();
        if (i == 1) {
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "parseZipResult success");
            synchronized (e) {
                this.n += msoVar.j();
            }
            jfu.e(cdk.e().c(str));
            b(str);
            e();
            return;
        }
        if (i == 0) {
            int b2 = msoVar.b();
            Message obtain = Message.obtain();
            obtain.what = 103;
            obtain.obj = Integer.valueOf(b2);
            this.i.sendMessage(obtain);
            return;
        }
        if (i == -9) {
            sqo.k("parseZipResult PULL_STORAGE_NOT_ENOUGH.");
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "parseZipResult PULL_STORAGE_NOT_ENOUGH");
            b(-3);
        } else {
            sqo.k("parseZipResult error.");
            ReleaseLogUtil.e("R_DownloadSeriesResourceManager", "parseZipResult error");
            b(-1);
        }
    }

    private void e() {
        int f = f();
        int i = this.j + 1;
        this.j = i;
        if (i < f) {
            i();
        } else {
            LogUtil.a("DownloadSeriesResourceManager", "Succeeded in downloading all the resource files");
            k();
        }
    }

    private void b(String str) {
        synchronized (f630a) {
            cvk e2 = cdk.e().e(str);
            if (e2 == null) {
                b(-1);
                return;
            }
            String str2 = e2.e() + "_version";
            String a2 = e2.a();
            StorageParams storageParams = new StorageParams();
            storageParams.d(0);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str2, a2, storageParams);
            c(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(cvk cvkVar) {
        synchronized (f630a) {
            this.f.add(cvkVar);
        }
    }

    private void o() {
        Message obtain = Message.obtain();
        obtain.what = 105;
        this.i.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        Message obtain = Message.obtain();
        obtain.what = 104;
        obtain.obj = Integer.valueOf(i);
        this.i.sendMessage(obtain);
    }

    private void k() {
        Message obtain = Message.obtain();
        obtain.what = 106;
        this.i.sendMessage(obtain);
    }

    private void d() {
        LogUtil.a("DownloadSeriesResourceManager", "mHasDownloadedPlugins size: ", Integer.valueOf(this.f.size()));
        for (cvk cvkVar : h()) {
            if (!this.f.contains(cvkVar)) {
                cdk.e().a(cvkVar.e());
            }
        }
    }

    private List<cvk> h() {
        ArrayList arrayList = new ArrayList(16);
        synchronized (f630a) {
            Iterator<cvk> it = this.h.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        final int i2;
        if (this.o == 0) {
            LogUtil.a("DownloadSeriesResourceManager", "showDownloadProgress, mTotalSize is zero");
            i2 = 0;
        } else {
            i2 = ((i + this.n) * 100) / this.o;
            if (i2 > 99) {
                i2 = 99;
            }
            if (this.m != i2) {
                this.m = i2;
                if (i2 % 10 == 0) {
                    LogUtil.a("DownloadSeriesResourceManager", "showDownloadProgress downloaded,mTempProgressBar: ", Integer.valueOf(this.n), " total : ", Integer.valueOf(this.o), " showDownloadProgress progress:", Integer.valueOf(this.m));
                }
            }
        }
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: cdc.1
            @Override // java.lang.Runnable
            public void run() {
                if (cdc.this.d != null) {
                    cdc.this.d.onDownload(i2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ExtendHandler extendHandler = this.i;
        if (extendHandler != null) {
            extendHandler.quit(false);
        }
        d();
    }

    private void n() {
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (next != null && this.k.contains(next.c())) {
                cdk.e().d(next);
            }
        }
    }
}
