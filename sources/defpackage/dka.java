package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dka {

    /* renamed from: a, reason: collision with root package name */
    private CustomProgressDialog.Builder f11689a;
    private Activity b;
    private String c;
    private String d;
    private CustomProgressDialog e;
    private long f;
    private long g;
    private dkc h;
    private int j;
    private long l;
    private CommonDialog21 o;
    private List<msa> p = new ArrayList(10);
    private final List<msa> s = new ArrayList(10);
    private HashMap<String, List> q = new HashMap<>(16);
    private HashMap<String, List> t = new HashMap<>(16);
    private List<msa> k = new ArrayList(10);
    private boolean m = false;
    private PullListener i = new PullListener() { // from class: dka.3
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            int i = msoVar.i();
            LogUtil.a("DownloadDeviceResourceTool", "download index file status = ", Integer.valueOf(i));
            if (i == 1) {
                dka.this.n();
                if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(dka.this.d)) {
                    EzPluginManager.a().i(dka.this.d);
                } else {
                    EzPluginManager.a().e();
                }
                Message obtain = Message.obtain();
                obtain.what = 99;
                dka.this.n.sendMessage(obtain);
                return;
            }
            if (i == -11) {
                dka.this.n();
                Message obtain2 = Message.obtain();
                obtain2.what = 99;
                dka.this.n.sendMessage(obtain2);
                return;
            }
            if (i != 0) {
                dka.this.n();
                Message obtain3 = Message.obtain();
                obtain3.what = 101;
                dka.this.n.sendMessage(obtain3);
                return;
            }
            LogUtil.a("DownloadDeviceResourceTool", "download index file status = ", Integer.valueOf(i));
        }
    };
    private Handler n = new Handler(Looper.getMainLooper()) { // from class: dka.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (dka.this.b != null) {
                LogUtil.c("DownloadDeviceResourceTool", " handleMessage message what = ", Integer.valueOf(message.what));
                int i = message.what;
                if (i != 1) {
                    switch (i) {
                        case 98:
                            dka.this.Wg_(message);
                            break;
                        case 99:
                            dka dkaVar = dka.this;
                            dkaVar.a(dkaVar.d);
                            break;
                        case 100:
                            dka.this.c();
                            dka.this.f();
                            break;
                        case 101:
                            dka.this.k();
                            dka.this.j();
                            break;
                        case 102:
                            dka.this.l();
                            break;
                        case 103:
                            dka.this.e(message.arg1);
                            break;
                        case 104:
                            dka.this.q();
                            break;
                        case 105:
                            dka.this.q();
                            dka.this.k();
                            break;
                        case 106:
                            dka.this.m();
                            break;
                        case 107:
                            dka.this.h();
                            break;
                    }
                }
                dka.this.Wh_(message);
            }
        }
    };

    public dka(Activity activity, String str, int i, dkc dkcVar) {
        this.j = 1;
        this.c = null;
        if (activity == null || TextUtils.isEmpty(str) || dkcVar == null) {
            return;
        }
        this.b = activity;
        this.d = str;
        this.j = i;
        this.h = dkcVar;
        this.c = mrp.e(activity, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Wh_(Message message) {
        if (this.j == 1) {
            this.h.onRefresh(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.j == 2) {
            this.h.onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.j == 2) {
            this.h.onFailure(101);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Wg_(Message message) {
        Object obj = message.obj;
        if (obj == null || !(obj instanceof msa)) {
            return;
        }
        this.s.add((msa) obj);
    }

    public void b(HashMap<String, List> hashMap, HashMap<String, List> hashMap2) {
        if (hashMap != null) {
            this.q = hashMap;
        }
        if (hashMap2 != null) {
            this.t = hashMap2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (jbw.c(this.b)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dkg
                @Override // java.lang.Runnable
                public final void run() {
                    dka.this.a();
                }
            });
        }
    }

    /* synthetic */ void a() {
        jbw.d(this.b, 2);
    }

    public void e() {
        String str;
        if (this.b == null || TextUtils.isEmpty(this.d) || this.h == null) {
            return;
        }
        c(this.d, true);
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(this.d)) {
            if (CommonUtil.bv()) {
                str = msr.f15154a.get(this.d);
            } else {
                str = msr.b.get(this.d);
            }
            EzPluginManager.a().e(this.d, str, this.i);
        } else {
            EzPluginManager.a().b(this.i);
        }
        if (jbw.c(this.b)) {
            Message obtainMessage = this.n.obtainMessage();
            obtainMessage.what = 107;
            this.n.sendMessage(obtainMessage);
        }
    }

    private void c(String str, boolean z) {
        List<msa> e;
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(str)) {
            e = EzPluginManager.a().e(str);
        } else {
            e = EzPluginManager.a().b();
        }
        if (e != null && !e.isEmpty()) {
            LogUtil.a("DownloadDeviceResourceTool", "checkLocalDeviceResourceExists indexInfosCache.size > 0");
            b(e, z, str);
        } else if (z) {
            LogUtil.a("DownloadDeviceResourceTool", "checkLocalDeviceResourceExists indexInfosCache.size = 0");
            r();
        }
    }

    private boolean c(String str) {
        if (EzPluginManager.a().g(str)) {
            b(ResourceManager.e().d(str));
            return true;
        }
        LogUtil.a("DownloadDeviceResourceTool", "isCheckLocalDeviceResourceExists device not available");
        return false;
    }

    private void b(List<msa> list, boolean z, String str) {
        this.m = false;
        HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
        for (msa msaVar : list) {
            if (msaVar.k() != null && TextUtils.equals(msaVar.k(), str)) {
                LogUtil.a("DownloadDeviceResourceTool", "checkLocalDeviceResourceExists has band plugin info from cache, uuid :", msaVar.b());
                if (cwc.e(msaVar.f())) {
                    if (honourDeviceConstantsApi.isDualModeProduct(msaVar.b()) && !honourDeviceConstantsApi.isSupportWifiDevice()) {
                        LogUtil.h("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList has no cloud and is hagride device");
                    } else {
                        String n = msaVar.n();
                        String g = msaVar.g();
                        if ("10".equals(g)) {
                            d(n, msaVar);
                        } else {
                            d(g, msaVar);
                        }
                    }
                }
            }
        }
        if (this.m || !z) {
            return;
        }
        LogUtil.a("DownloadDeviceResourceTool", "checkLocalDeviceResourceExists have not done file");
        r();
    }

    private void d(String str, msa msaVar) {
        if (Utils.o()) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if ("3".equals(str) || "2".equals(str) || "4".equals(str)) {
                this.m = c(msaVar.b());
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("3".equals(str) || "1".equals(str) || "4".equals(str)) {
            this.m = c(msaVar.b());
        }
    }

    private void r() {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("DownloadDeviceResourceTool", "startLoading progress dialog exists");
            return;
        }
        if (this.o == null) {
            LogUtil.a("DownloadDeviceResourceTool", "startLoading is domestic app");
            new CommonDialog21(this.b, R.style.app_update_dialogActivity);
            this.o = CommonDialog21.a(this.b);
        }
        this.o.e(this.b.getString(R.string.IDS_device_plugin_download_loading));
        this.o.a();
        this.n.sendEmptyMessageDelayed(105, 8000L);
    }

    private void b(dcz dczVar) {
        if (dczVar == null) {
            LogUtil.h("DownloadDeviceResourceTool", "handlerToRefresh productInfo is null");
            return;
        }
        String t = dczVar.t();
        ResourceManager.e().h(t);
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isCorrectProductId(t)) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = dczVar;
            this.n.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Message obtain = Message.obtain();
        obtain.what = 104;
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.j == 1) {
            this.h.onShowErrorLayout();
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        List<msa> e;
        this.p.clear();
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(str)) {
            e = EzPluginManager.a().e(str);
        } else {
            e = EzPluginManager.a().b();
        }
        if (e == null || e.isEmpty()) {
            LogUtil.a("DownloadDeviceResourceTool", "getAllDeviceInfoFromIndexFile no index info");
            return;
        }
        b(str);
        if (this.p.isEmpty()) {
            e(str);
            return;
        }
        e(dke.e(this.p));
        if (CommonUtil.bo()) {
            return;
        }
        d(this.p);
    }

    public void b() {
        b(this.d);
        if (this.p.isEmpty()) {
            e(this.d);
        } else {
            e(dke.e(this.p));
        }
    }

    private void e(String str) {
        if (this.j == 2) {
            this.h.onSuccess();
            return;
        }
        LogUtil.a("DownloadDeviceResourceTool", "getAllDeviceInfoFromIndexFile no current device type devices");
        if (Utils.o() && CommonUtil.cg()) {
            e(dke.e(this.p));
        } else if (Utils.o()) {
            e((List<String>) this.t.get(str));
        } else {
            e((List<String>) this.q.get(str));
        }
    }

    private void e(List<String> list) {
        if (list != null) {
            for (String str : list) {
                LogUtil.c("DownloadDeviceResourceTool", "showPreSetDevice product id", str);
                b(ResourceManager.e().d(str));
            }
        }
    }

    private void b(String str) {
        List<msa> e;
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(str)) {
            e = EzPluginManager.a().e(str);
        } else {
            e = EzPluginManager.a().b();
        }
        if (e == null) {
            LogUtil.h("DownloadDeviceResourceTool", "buildDeviceListFromIndexFile deviceList == null");
            return;
        }
        HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
        LogUtil.a("DownloadDeviceResourceTool", "buildDeviceListFromIndexFile deviceList.size() = ", Integer.valueOf(e.size()));
        for (msa msaVar : e) {
            if (msaVar.k() != null && TextUtils.equals(msaVar.k(), str) && cwc.e(msaVar.f())) {
                if (honourDeviceConstantsApi.isDualModeProduct(msaVar.b()) && !honourDeviceConstantsApi.isSupportWifiDevice()) {
                    LogUtil.h("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList has no cloud and is hagride device");
                } else {
                    String g = msaVar.g();
                    String i = msaVar.i();
                    if ("2".equals(g) || "1".equals(g) || "3".equals(g)) {
                        a(msaVar);
                    } else if (TextUtils.isEmpty(i) || "0".equals(i)) {
                        a(msaVar);
                    } else {
                        d(msaVar, i);
                    }
                }
            }
        }
    }

    private void d(msa msaVar, String str) {
        if ("1".equals(str)) {
            String n = msaVar.n();
            if (Utils.o()) {
                if (TextUtils.isEmpty(n)) {
                    return;
                }
                if ("3".equals(n) || "2".equals(n) || "4".equals(n)) {
                    this.p.add(msaVar);
                    return;
                }
                return;
            }
            if (TextUtils.isEmpty(n)) {
                return;
            }
            if ("3".equals(n) || "1".equals(n) || "4".equals(n)) {
                this.p.add(msaVar);
            }
        }
    }

    private void a(msa msaVar) {
        if (Utils.o()) {
            if (TextUtils.equals(msaVar.g(), "2") || TextUtils.equals(msaVar.g(), "3")) {
                this.p.add(msaVar);
                return;
            }
            return;
        }
        if (TextUtils.equals(msaVar.g(), "1") || TextUtils.equals(msaVar.g(), "3")) {
            this.p.add(msaVar);
        }
    }

    private void d(List<msa> list) {
        LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList enter ");
        this.s.clear();
        if (list.isEmpty()) {
            LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList uuidList.isEmpty()");
            o();
            return;
        }
        final String b = list.get(list.size() - 1).b();
        for (final msa msaVar : list) {
            if (EzPluginManager.a().g(msaVar.b())) {
                EzPluginManager.a().b(msaVar.b(), new PullListener() { // from class: djy
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public final void onPullingChange(msq msqVar, mso msoVar) {
                        dka.this.b(msaVar, b, msqVar, msoVar);
                    }
                });
            } else if (b.equals(msaVar.b())) {
                LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList finish checking");
                o();
            }
        }
    }

    /* synthetic */ void b(msa msaVar, String str, msq msqVar, mso msoVar) {
        LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList result status = ", Integer.valueOf(msoVar.i()));
        a(msoVar, msaVar, str);
    }

    private void a(mso msoVar, msa msaVar, String str) {
        if (msoVar == null || msaVar == null || str == null) {
            LogUtil.h("DownloadDeviceResourceTool", "pullResultToOperation result ezPluginIndexInfo lastDeviceUuid null");
            Message obtain = Message.obtain();
            obtain.what = 101;
            this.n.sendMessage(obtain);
        }
        if (msoVar != null && msoVar.i() == 1) {
            LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList is already new");
            Message obtain2 = Message.obtain();
            obtain2.obj = msaVar;
            obtain2.what = 98;
            this.n.sendMessage(obtain2);
        }
        if (str == null || msaVar == null || !str.equals(msaVar.b())) {
            return;
        }
        LogUtil.a("DownloadDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList finish downloading last device");
        o();
    }

    private void o() {
        Message obtain = Message.obtain();
        obtain.what = 106;
        obtain.obj = this.s;
        this.n.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("DownloadDeviceResourceTool", "startDownLoadProgress exists");
            return;
        }
        this.e = new CustomProgressDialog(this.b);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.b);
        this.f11689a = builder;
        builder.d(this.b.getString(R.string._2130841851_res_0x7f0210fb)).cyH_(new View.OnClickListener() { // from class: dka.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DownloadDeviceResourceTool", "startDownLoadProgress onclick cancel");
                dka.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.f11689a.e();
        this.e = e;
        e.setCanceledOnTouchOutside(false);
        if (!this.b.isFinishing()) {
            this.e.show();
            this.f11689a.d(0);
            this.f11689a.e(UnitUtil.e(0.0d, 2, 0));
        }
        LogUtil.a("DownloadDeviceResourceTool", "mCustomProgressDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.l == 0) {
            return;
        }
        long j = i;
        LogUtil.c("DownloadDeviceResourceTool", "showDownloadProgress downloaded", Long.valueOf(this.f + j), "total", Long.valueOf(this.l));
        long j2 = ((j + this.f) * 100) / this.l;
        if (j2 > 99) {
            j2 = 99;
        }
        this.f11689a.d((int) j2);
        String e = UnitUtil.e(j2, 2, 0);
        this.f11689a.e(e);
        LogUtil.c("DownloadDeviceResourceTool", "showDownloadProgress percentNum", e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("DownloadDeviceResourceTool", "enter handleCancel");
        if (koq.b(this.p)) {
            return;
        }
        Iterator<msa> it = this.p.iterator();
        while (it.hasNext()) {
            msq e = msn.c().e(it.next().b());
            if (e != null) {
                EzPluginManager.a().a(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.p.removeAll(this.s);
        if (this.p.size() > 0) {
            b(this.p);
        } else {
            if (this.j == 2) {
                this.h.onSuccess();
                return;
            }
            LogUtil.a("DownloadDeviceResourceTool", "mDownLoadMode == " + this.j);
        }
    }

    private void b(List<msa> list) {
        LogUtil.a("DownloadDeviceResourceTool", "downLoadDescriptionJsonFile enter");
        q();
        Message obtain = Message.obtain();
        obtain.what = 102;
        this.n.sendMessage(obtain);
        this.l = 0L;
        this.g = 0L;
        for (final msa msaVar : list) {
            LogUtil.a("DownloadDeviceResourceTool", "downLoadDescriptionJsonFile for circle uuid:", msaVar.b());
            EzPluginManager.a().a(msaVar.b(), this.c, new PullListener() { // from class: dkf
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    dka.this.c(msaVar, msqVar, msoVar);
                }
            });
        }
    }

    /* synthetic */ void c(msa msaVar, msq msqVar, mso msoVar) {
        Object[] objArr = new Object[6];
        objArr[0] = "downLoadDescriptionJsonFile onPullingChange uuid:";
        objArr[1] = msaVar.b();
        objArr[2] = ", result:";
        objArr[3] = Integer.valueOf(msoVar.i());
        objArr[4] = ", Looper = ";
        objArr[5] = Boolean.valueOf(Looper.getMainLooper() == Looper.myLooper());
        LogUtil.a("DownloadDeviceResourceTool", objArr);
        c(msoVar, msqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.b == null) {
            LogUtil.a("DownloadDeviceResourceTool", "stopLoading mActivity == null");
            return;
        }
        CommonDialog21 commonDialog21 = this.o;
        if (commonDialog21 != null && commonDialog21.isShowing() && !this.b.isFinishing()) {
            this.o.dismiss();
            this.o = null;
        }
        this.n.removeMessages(105);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("DownloadDeviceResourceTool", "enter closeProgress");
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.b.isFinishing()) {
            return;
        }
        this.e.cancel();
        LogUtil.a("DownloadDeviceResourceTool", "enter closeProgress cancel");
    }

    private void c(mso msoVar, msq msqVar) {
        LogUtil.a("DownloadDeviceResourceTool", "handleAfterDownLoad total size is", Integer.valueOf(msoVar.j()), "pull size is ", Integer.valueOf(msoVar.b()), " and uuid is ", msqVar.o());
        if (msoVar.i() == 1) {
            this.l += msoVar.j();
            long j = this.g + 1;
            this.g = j;
            LogUtil.a("DownloadDeviceResourceTool", "handleAfterDownLoad has downloaded ", Long.valueOf(j), " description file");
            if (this.g == this.p.size()) {
                LogUtil.a("DownloadDeviceResourceTool", "handleAfterDownLoad completed and download byte = ", Long.valueOf(this.l));
                i();
                return;
            }
            return;
        }
        LogUtil.a("DownloadDeviceResourceTool", "handleAfterDownLoad fail");
        Message obtain = Message.obtain();
        obtain.what = 101;
        this.n.sendMessage(obtain);
    }

    private void i() {
        LogUtil.a("DownloadDeviceResourceTool", "enter downloadZipFile size：", Integer.valueOf(this.p.size()));
        this.k.clear();
        if (this.p.size() > 0) {
            c(0);
        }
    }

    private void c(final int i) {
        LogUtil.a("DownloadDeviceResourceTool", "enter downloadOneZipFile index = ", Integer.valueOf(i));
        LogUtil.a("DownloadDeviceResourceTool", "enter downloadOneZipFile mNeedDownloadDevices.size() = ", Integer.valueOf(this.p.size()));
        if (i >= this.p.size()) {
            return;
        }
        final msa msaVar = this.p.get(i);
        LogUtil.a("DownloadDeviceResourceTool", "enter downloadOneZipFile uuid = ", msaVar.b(), ", mDeviceType =", this.d);
        EzPluginManager.a().b(msaVar.b(), this.c, new PullListener() { // from class: dkh
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                dka.this.e(msaVar, i, msqVar, msoVar);
            }
        });
    }

    /* synthetic */ void e(msa msaVar, int i, msq msqVar, mso msoVar) {
        if (msqVar == null || msoVar == null) {
            LogUtil.a("DownloadDeviceResourceTool", "onPullingChange param is null");
            a(101);
            return;
        }
        int i2 = msoVar.i();
        ReleaseLogUtil.e("DEVMGR_DownloadDeviceResourceTool", "downloadOneZipFile result status = ", Integer.valueOf(i2), ",and uuid = ", msqVar.o());
        if (i2 != 1) {
            if (i2 == 0) {
                LogUtil.a("DownloadDeviceResourceTool", "downloadOneZipFile pull in progress");
                Message obtain = Message.obtain();
                obtain.what = 103;
                obtain.arg1 = msoVar.b();
                this.n.sendMessage(obtain);
                return;
            }
            ReleaseLogUtil.c("DEVMGR_DownloadDeviceResourceTool", "downloadOneZipFile failed");
            a(101);
            return;
        }
        ReleaseLogUtil.e("DEVMGR_DownloadDeviceResourceTool", "downloadOneZipFile one zip succeed");
        b(ResourceManager.e().d(msaVar.b()));
        dke.e(msaVar);
        this.k.add(msaVar);
        this.f += msoVar.j();
        int i3 = i + 1;
        if (i3 < this.p.size()) {
            LogUtil.a("DownloadDeviceResourceTool", "downloadOneZipFile all zip is loading");
            c(i3);
        } else {
            LogUtil.a("DownloadDeviceResourceTool", "downloadOneZipFile all zip succeed");
            a(100);
        }
    }

    private void a(int i) {
        Message obtain = Message.obtain();
        obtain.what = i;
        this.n.sendMessage(obtain);
    }

    public void d() {
        q();
        Handler handler = this.n;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        CustomProgressDialog customProgressDialog = this.e;
        if (customProgressDialog != null) {
            if (customProgressDialog.isShowing()) {
                this.e.dismiss();
            }
            this.e = null;
        }
        CustomProgressDialog.Builder builder = this.f11689a;
        if (builder != null) {
            builder.cyH_(null);
            this.f11689a = null;
        }
        this.b = null;
        this.i = null;
    }
}
