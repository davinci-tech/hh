package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ccx {
    private static ccx c;
    private static final Object e = new Object();
    private static final Object b = new Object();
    private static final Object d = new Object();
    private volatile int h = 0;
    private volatile int k = 0;
    private volatile int j = 0;
    private boolean g = false;
    private int n = 0;
    private List<cvg> i = new CopyOnWriteArrayList();
    private cuy f = new cuy();

    /* renamed from: a, reason: collision with root package name */
    private List<DownloadResultCallBack> f627a = new CopyOnWriteArrayList();

    static /* synthetic */ int b(ccx ccxVar) {
        int i = ccxVar.j;
        ccxVar.j = i + 1;
        return i;
    }

    private ccx() {
    }

    public static ccx e() {
        ccx ccxVar;
        synchronized (e) {
            if (c == null) {
                c = new ccx();
            }
            ccxVar = c;
        }
        return ccxVar;
    }

    public void d() {
        m();
        ccp.b().d(new ResponseCallback() { // from class: cdd
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ccx.b(i, (List) obj);
            }
        });
    }

    static /* synthetic */ void b(final int i, List list) {
        if (i != 0 || koq.b(list)) {
            ReleaseLogUtil.c("R_DownloadIndexAllManager", "downloadCountryCodeListConfig error:", Integer.valueOf(i));
            ccp.b().d(new ResponseCallback() { // from class: cda
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    ReleaseLogUtil.e("R_DownloadIndexAllManager", "downloadCountryCodeListConfig retry. status: " + i);
                }
            });
        } else {
            LogUtil.a("DownloadIndexAllManager", "downloadCountryCodeListConfig exist config.");
        }
    }

    public void d(DownloadResultCallBack downloadResultCallBack) {
        synchronized (d) {
            List<DownloadResultCallBack> list = this.f627a;
            if (list != null && !list.contains(downloadResultCallBack)) {
                this.f627a.add(downloadResultCallBack);
            }
        }
    }

    public void b(DownloadResultCallBack downloadResultCallBack) {
        synchronized (d) {
            List<DownloadResultCallBack> list = this.f627a;
            if (list != null && list.contains(downloadResultCallBack)) {
                this.f627a.remove(downloadResultCallBack);
            }
        }
    }

    private void m() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            if (cdg.e()) {
                i();
                return;
            }
            this.g = false;
            synchronized (d) {
                List<DownloadResultCallBack> list = this.f627a;
                if (list == null) {
                    return;
                }
                Iterator<DownloadResultCallBack> it = list.iterator();
                while (it.hasNext()) {
                    it.next().setDownloadStatus(-2, 0);
                }
                return;
            }
        }
        if (this.g) {
            return;
        }
        this.g = true;
        this.i.clear();
        this.j = 0;
        this.h = 0;
        this.n = 0;
        String c2 = cvy.c();
        cdk.e().c("device_index_all", c(), c2, new PullListener() { // from class: ccx.3
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("DownloadIndexAllManager", "downloadDeviceIndexFile result is null.");
                    return;
                }
                int i = msoVar.i();
                if (i == 1 || i == -11) {
                    ReleaseLogUtil.e("R_DownloadIndexAllManager", "download file:", msoVar.toString());
                    ccx.this.a(msqVar);
                } else {
                    if (i == 0) {
                        LogUtil.c("DownloadIndexAllManager", "downloadDeviceIndexFile is downloading.");
                        return;
                    }
                    if (i == -9) {
                        sqo.k("downloadDeviceIndexFile storage is not enough.");
                        ccx.this.a(-3);
                    } else {
                        sqo.k("downloadDeviceIndexFile is error.");
                        ccx.this.a(-1);
                        ReleaseLogUtil.d("R_DownloadIndexAllManager", "downloadDeviceIndexFile is error.");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(msq msqVar) {
        File file = new File(cvy.c());
        boolean exists = file.exists();
        LogUtil.a("DownloadIndexAllManager", "updateIndexCacheForWear isExistThisIndex is = ", Boolean.valueOf(exists));
        if (exists) {
            c(file);
            if (this.f == null) {
                c(cvy.c());
                b(msqVar);
                a(-1);
                LogUtil.h("DownloadIndexAllManager", "updateIndexCacheForWear, indexAllBean is null");
                return;
            }
            ReleaseLogUtil.e("R_DownloadIndexAllManager", "updateIndexCacheForWear mIndexAll version");
            if (t()) {
                sqo.k("updateIndexCacheForWear, zip path is null");
                c(cvy.c());
                b(msqVar);
                a(-1);
                LogUtil.h("DownloadIndexAllManager", "updateIndexCacheForWear, zip path is null");
                return;
            }
            n();
            return;
        }
        sqo.k("index file is not exists");
        b(msqVar);
        a(-1);
        LogUtil.h("DownloadIndexAllManager", "index file is not exists");
    }

    private void b(msq msqVar) {
        msn.c().d(msqVar.j() + "device_index_all", "");
        msn.c().a("device_index_all", "");
    }

    private void n() {
        ReleaseLogUtil.e("R_DownloadIndexAllManager", "checkZipResource to enter");
        if (h()) {
            j();
            f();
            g();
        } else {
            ReleaseLogUtil.e("R_DownloadIndexAllManager", "checkZipResource done file is not exists");
            x();
        }
    }

    private void j() {
        LogUtil.a("DownloadIndexAllManager", "checkImageResource");
        String a2 = this.f.a();
        boolean d2 = d("img_index_all", a2);
        boolean c2 = c("img_index_all", a2);
        if (d2 || c2) {
            List<cvg> c3 = this.f.c();
            this.i.addAll(c3);
            a(c3);
            return;
        }
        LogUtil.a("DownloadIndexAllManager", "image resource is exists");
    }

    private void a(List<cvg> list) {
        Iterator<cvg> it = list.iterator();
        while (it.hasNext()) {
            this.n += it.next().a();
        }
        ReleaseLogUtil.e("R_DownloadIndexAllManager", "setTotalSize total size");
    }

    private void f() {
        LogUtil.a("DownloadIndexAllManager", "checkLangResource");
        String d2 = this.f.d();
        boolean d3 = d("lang_index_all", d2);
        boolean c2 = c("lang_index_all", d2);
        if (d3 || c2) {
            List<cvg> b2 = this.f.b();
            this.i.addAll(b2);
            a(b2);
            return;
        }
        LogUtil.a("DownloadIndexAllManager", "lang resource is exists");
    }

    private void g() {
        LogUtil.a("DownloadIndexAllManager", "checkResource");
        if (!this.i.isEmpty()) {
            c(cvy.a());
            l();
        } else {
            r();
            a();
            LogUtil.h("DownloadIndexAllManager", "checkResource, mDownLoadResourceList is empty");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("DownloadIndexAllManager", "downLoadResource");
        String str = cvy.b("img_index_all") + this.f.a() + File.separator;
        if (this.j < 0 || this.j >= this.i.size()) {
            LogUtil.h("DownloadIndexAllManager", "downLoadResource, mIndex is out of bounds");
            a(-1);
            return;
        }
        cvg cvgVar = this.i.get(this.j);
        if (cvgVar == null || cvgVar.c() == null) {
            sqo.k("downLoadResource info or config is null.");
            a(-1);
            return;
        }
        if (cvgVar.c().contains("lang")) {
            LogUtil.a("DownloadIndexAllManager", "start download lang resource");
            str = cvy.b("lang_index_all") + this.f.d() + File.separator;
        }
        int a2 = cvgVar.a();
        if (cdv.d() > a2) {
            c(cvgVar, str);
            return;
        }
        sqo.k("downLoadResource Insufficient storage, availCount: " + cdv.d() + ", size: " + a2);
        a(-3);
        LogUtil.h("DownloadIndexAllManager", "Insufficient storage: ", Long.valueOf(cdv.d()), " ", Integer.valueOf(a2));
    }

    private void x() {
        LogUtil.a("DownloadIndexAllManager", "startDownload");
        this.i.clear();
        e("img_index_all");
        e("lang_index_all");
        List<cvg> c2 = this.f.c();
        this.i.addAll(c2);
        a(c2);
        List<cvg> b2 = this.f.b();
        this.i.addAll(b2);
        if (koq.b(this.i)) {
            sqo.k("mDownLoadResourceList is empty.");
            a(-1);
        } else {
            a(b2);
            LogUtil.a("DownloadIndexAllManager", "mDownLoadResourceList size: ", Integer.valueOf(this.i.size()));
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cuy c(File file) {
        String str;
        String a2 = cdm.a(file);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("DownloadIndexAllManager", "parseVersionIndexFile indexJson is null");
            return this.f;
        }
        try {
            cuy cuyVar = this.f;
            if (cuyVar == null) {
                str = "";
            } else {
                str = cuyVar.i();
            }
        } catch (JSONException unused) {
            LogUtil.b("DownloadIndexAllManager", "getIndexBean JSONException");
        }
        if (TextUtils.isEmpty(str)) {
            cuy b2 = cdl.b(a2);
            this.f = b2;
            return b2;
        }
        if (!str.equals(new JSONObject(a2).optString("version"))) {
            this.f = cdl.b(a2);
        }
        return this.f;
    }

    private boolean t() {
        List<cvg> c2 = this.f.c();
        List<cvg> b2 = this.f.b();
        return c2 == null || c2.isEmpty() || b2 == null || b2.isEmpty();
    }

    private boolean d(String str, String str2) {
        File file = new File(cvy.b(str) + str2 + File.separator);
        if (file.exists() && file.isDirectory()) {
            String[] list = file.list();
            if (list == null || list.length == 0) {
                return true;
            }
            LogUtil.h("DownloadIndexAllManager", "downloadLangFile() file exists");
            return false;
        }
        e(str);
        return true;
    }

    private boolean c(String str, String str2) {
        String str3 = str + "_version";
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), str3);
        LogUtil.a("DownloadIndexAllManager", "checkSharePrefsZip,key: ", str3, " version: ", str2, " spVersion: ", b2);
        return TextUtils.isEmpty(b2) || !TextUtils.equals(str2, b2);
    }

    private boolean h() {
        return new File(cvy.a()).exists();
    }

    private void c(cvg cvgVar, String str) {
        String c2 = c();
        String c3 = cdk.e().c((String) null, true);
        if (!TextUtils.isEmpty(c3)) {
            e(cvgVar, c3 + c2, str);
            return;
        }
        sqo.k("downloadLangFile() baseDownloadUrl is empty");
        a(-1);
        LogUtil.h("DownloadIndexAllManager", "downloadLangFile() baseDownloadUrl is empty");
    }

    private void e(cvg cvgVar, String str, String str2) {
        msq msqVar = new msq();
        msqVar.a(str2);
        msqVar.h(str);
        msqVar.c(cvgVar.b());
        msqVar.b(cvgVar.c());
        msqVar.b(cvgVar.a());
        e(msqVar);
    }

    private void e(msq msqVar) {
        cdv.b().d(msqVar, new PullListener() { // from class: ccx.4
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar2, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("DownloadIndexAllManager", "downloadZipFile result is null.");
                    return;
                }
                int i = msoVar.i();
                if (i == 1 || i == -11) {
                    synchronized (ccx.b) {
                        ccx.this.h += msqVar2.k();
                        ccx.b(ccx.this);
                        if (ccx.this.j >= ccx.this.i.size()) {
                            ccx.this.o();
                            ccx.this.p();
                            ccx.this.r();
                            return;
                        } else {
                            ccx.this.l();
                            ReleaseLogUtil.e("R_DownloadIndexAllManager", "downloadZipFile success");
                            return;
                        }
                    }
                }
                if (i == 0) {
                    ccx.this.c(msoVar);
                    return;
                }
                if (i == -9) {
                    sqo.k("downloadZipFile zip storage not enough.");
                    ccx.this.c(msqVar2.e());
                    ccx.this.a(-3);
                    LogUtil.a("DownloadIndexAllManager", "downloadZipFile zip error");
                    return;
                }
                sqo.k("downloadZipFile zip error.");
                ccx.this.c(msqVar2.e());
                ccx.this.a(-1);
                ReleaseLogUtil.e("R_DownloadIndexAllManager", "downloadZipFile zip error");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(mso msoVar) {
        int b2 = b(msoVar.b());
        synchronized (d) {
            List<DownloadResultCallBack> list = this.f627a;
            if (list == null) {
                return;
            }
            Iterator<DownloadResultCallBack> it = list.iterator();
            while (it.hasNext()) {
                it.next().setDownloadStatus(0, b2);
            }
        }
    }

    private int b(int i) {
        if (this.n == 0) {
            return 0;
        }
        int i2 = ((this.h + i) * 100) / this.n;
        if (i2 > 99) {
            i2 = 99;
        }
        if (this.k != i2) {
            this.k = i2;
            if (this.k % 10 == 0) {
                ReleaseLogUtil.e("DownloadIndexAllManager", "showDownloadProgress downloaded ：", Integer.valueOf(this.h + i), " total : ", Integer.valueOf(this.n), " showDownloadProgress progress:", Integer.valueOf(this.k));
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        w();
        this.g = false;
        synchronized (d) {
            List<DownloadResultCallBack> list = this.f627a;
            if (list == null) {
                return;
            }
            Iterator<DownloadResultCallBack> it = list.iterator();
            while (it.hasNext()) {
                it.next().setDownloadStatus(1, 100);
            }
            a();
            ReleaseLogUtil.e("R_DownloadIndexAllManager", "setDownloadSuccess download all file is success");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.g = false;
        synchronized (d) {
            List<DownloadResultCallBack> list = this.f627a;
            if (list == null) {
                return;
            }
            Iterator<DownloadResultCallBack> it = list.iterator();
            while (it.hasNext()) {
                it.next().setDownloadStatus(i, -1);
            }
            LogUtil.h("DownloadIndexAllManager", "updateIndexCacheForWear, indexAllBean is null");
        }
    }

    private void w() {
        ccs.d().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        String a2 = this.f.a();
        String d2 = this.f.d();
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), "img_index_all_version", a2, storageParams);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), "lang_index_all_version", d2, storageParams);
        LogUtil.a("DownloadIndexAllManager", "imageKey is ", "img_index_all_version", ",imageVersion is ", a2, ",langKey: ", "lang_index_all_version", ",langVersion: ", d2);
        ReleaseLogUtil.e("R_DownloadIndexAllManager", "setZipSharedVersion");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        cvy.a(new File(str));
    }

    private void e(String str) {
        cvy.a(new File(cvy.b(str)));
    }

    protected String c() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "key_download_config");
        return !TextUtils.isEmpty(b2) ? b2 : (CommonUtil.ag(BaseApplication.getContext()) || CommonUtil.bu() || s()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        cvy.a(cvy.a());
    }

    public void a() {
        q();
        this.g = false;
        k();
        this.f627a = null;
    }

    private static void k() {
        synchronized (e) {
            c = null;
        }
    }

    private void q() {
        Iterator<msq> it = msn.c().b().iterator();
        while (it.hasNext()) {
            msq next = it.next();
            if (next != null && c(next)) {
                cdk.e().d(next);
            }
        }
    }

    private boolean c(msq msqVar) {
        String c2 = msqVar.c();
        if (TextUtils.equals("device_index_all", c2)) {
            return true;
        }
        for (cvg cvgVar : this.i) {
            if (cvgVar != null && TextUtils.equals(c2, cvgVar.c())) {
                return true;
            }
        }
        return false;
    }

    private static boolean s() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_SITE", "app_test_site_type");
        LogUtil.a("DownloadIndexAllManager", "deviceSite:", b3);
        return CommonUtil.as() && "1".equals(b2) && "release".equals(b3);
    }

    private void i() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ccx.5
            @Override // java.lang.Runnable
            public void run() {
                cdg.a();
                File file = new File(cvy.c());
                boolean exists = file.exists();
                LogUtil.a("DownloadIndexAllManager", "downLoadIndex: ", Boolean.valueOf(exists));
                if (exists) {
                    ccx.this.c(file);
                }
                if (ccx.this.f != null) {
                    cdg.e(ccx.this.f);
                    cdg.d(ccx.this.f);
                    ccx.this.o();
                    ccx.this.p();
                    ccx.this.r();
                    cdg.d();
                }
                jfu.n();
            }
        });
    }
}
