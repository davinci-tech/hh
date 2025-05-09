package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dkd {

    /* renamed from: a, reason: collision with root package name */
    private String f11693a;
    private String b;
    private Context c;
    private int d;
    private String e;
    private long f;
    private dkc h;
    private long i;
    private DeviceDownloadSourceInfo j;
    private String k;
    private String m;
    private String q;
    private long s;
    private List<String> u;
    private int y;
    private boolean t = false;
    private List<msa> p = new ArrayList(10);
    private final List<msa> r = new ArrayList(10);
    private List<msa> l = new ArrayList(10);
    private PullListener g = new PullListener() { // from class: dkd.3
        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            int i = msoVar.i();
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "download index file status = ", Integer.valueOf(i));
            if (i == 1) {
                dkd.this.c();
                return;
            }
            if (i == -11) {
                Message obtain = Message.obtain();
                obtain.what = 99;
                dkd.this.o.sendMessage(obtain);
            } else {
                if (i == 0) {
                    LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "download index file status = ", Integer.valueOf(i));
                    return;
                }
                Message obtain2 = Message.obtain();
                obtain2.what = 101;
                dkd.this.o.sendMessage(obtain2);
            }
        }
    };
    private DownloadCallback<dqi> n = new DownloadCallback<dqi>() { // from class: dkd.1
        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onFinish(dqi dqiVar) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "Index File Download Finish.");
            dkd.this.c();
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "Index File Download onProgress");
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            ReleaseLogUtil.c("DEVMGR_EcologyDevice_DownloadSingleDeviceResourceTool", "Index File Download Fail, errCode:", Integer.valueOf(i), "errMsg:", th.getMessage());
            Message obtain = Message.obtain();
            obtain.what = 101;
            dkd.this.o.sendMessage(obtain);
        }
    };
    private Handler o = new Handler(Looper.getMainLooper()) { // from class: dkd.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (dkd.this.c != null) {
                LogUtil.c("EcologyDevice_DownloadSingleDeviceResourceTool", " handleMessage message what = ", Integer.valueOf(message.what));
                switch (message.what) {
                    case 98:
                        dkd.this.Wk_(message);
                        break;
                    case 99:
                        dkd dkdVar = dkd.this;
                        dkdVar.e(dkdVar.b);
                        break;
                    case 100:
                        dkd.this.h();
                        break;
                    case 101:
                        dkd.this.d(10);
                        break;
                    case 102:
                        dkd.this.e(0);
                        break;
                    case 103:
                        if (dkd.this.s != 0) {
                            dkd dkdVar2 = dkd.this;
                            dkdVar2.e(dkdVar2.c(message.arg1));
                            break;
                        }
                        break;
                    case 106:
                        dkd.this.g();
                        break;
                    case 107:
                        dkd.this.f();
                        break;
                }
            }
        }
    };

    public dkd(Context context, String str, int i, List<String> list, dkc dkcVar) {
        this.d = 1;
        this.f11693a = null;
        if (context == null || TextUtils.isEmpty(str) || dkcVar == null) {
            return;
        }
        this.c = context;
        this.b = str;
        this.d = i;
        this.h = dkcVar;
        this.f11693a = mrp.e(context, str);
        this.u = list;
    }

    public void a(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "setDownloadSource");
        if (deviceDownloadSourceInfo == null) {
            LogUtil.h("EcologyDevice_DownloadSingleDeviceResourceTool", "setDownloadSource downloadSourceInfo is null");
            return;
        }
        this.j = deviceDownloadSourceInfo.m775clone();
        this.t = true;
        this.y = deviceDownloadSourceInfo.getSite();
        this.q = deviceDownloadSourceInfo.getIndexName();
        String configurationPoint = deviceDownloadSourceInfo.getConfigurationPoint();
        this.e = configurationPoint;
        if (this.y != 1) {
            configurationPoint = mrp.b(configurationPoint, this.b);
        }
        this.f11693a = configurationPoint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(this.b)) {
            if (!this.t) {
                EzPluginManager.a().i(this.b);
            } else {
                cxl.b().b(this.q);
            }
        } else {
            EzPluginManager.a().e();
        }
        Message obtain = Message.obtain();
        obtain.what = 99;
        this.o.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        dkc dkcVar = this.h;
        if (dkcVar != null) {
            dkcVar.onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        dkc dkcVar = this.h;
        if (dkcVar != null) {
            dkcVar.onFailure(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        dkc dkcVar = this.h;
        if (dkcVar != null) {
            dkcVar.onDownload(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Wk_(Message message) {
        Object obj = message.obj;
        if (obj instanceof msa) {
            this.r.add((msa) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (jbw.c(this.c)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dkn
                @Override // java.lang.Runnable
                public final void run() {
                    dkd.this.a();
                }
            });
        }
    }

    /* synthetic */ void a() {
        jbw.d(this.c, 2);
    }

    public void b() {
        String str;
        if (this.c == null || TextUtils.isEmpty(this.b) || this.h == null) {
            return;
        }
        ReleaseLogUtil.e("EcologyDevice_DownloadSingleDeviceResourceTool", "start downloadIndexFile");
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(this.b)) {
            if (CommonUtil.bv()) {
                str = msr.f15154a.get(this.b);
            } else {
                str = msr.b.get(this.b);
            }
            if (!this.t) {
                EzPluginManager.a().e(this.b, str, this.g);
            } else {
                cxl.b().d(this.j, this.n, new dkl());
            }
        } else {
            EzPluginManager.a().b(this.g);
        }
        if (jbw.c(this.c)) {
            Message obtainMessage = this.o.obtainMessage();
            obtainMessage.what = 107;
            this.o.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        List<msa> d;
        msa b;
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "getAllDeviceInfoFromIndexFile deviceType : ", str);
        this.p.clear();
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey(str)) {
            if (!this.t) {
                d = EzPluginManager.a().e(str);
            } else {
                d = cxl.b().d(this.q);
            }
        } else {
            d = EzPluginManager.a().b();
        }
        if (d == null || d.isEmpty()) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "getAllDeviceInfoFromIndexFile no index info");
            return;
        }
        if (bkz.e(this.u)) {
            d(10);
            return;
        }
        for (String str2 : this.u) {
            if (!this.t) {
                b = EzPluginManager.a().d(str2);
            } else {
                b = cxl.b().b(this.q, str2);
            }
            if (b != null) {
                this.p.add(b);
            }
        }
        if (this.p.isEmpty()) {
            d(11);
        } else {
            e(this.p);
        }
    }

    private void e(List<msa> list) {
        boolean a2;
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList enter ");
        this.r.clear();
        final String b = list.get(list.size() - 1).b();
        for (final msa msaVar : list) {
            if (!cwc.e(msaVar.f()) && ResourceManager.e().d(msaVar.b()) != null) {
                mso msoVar = new mso();
                msoVar.b(1);
                b(msoVar, msaVar, b);
            } else {
                if (!this.t) {
                    a2 = EzPluginManager.a().g(msaVar.b());
                } else {
                    a2 = cxl.b().a(this.q, msaVar.b());
                }
                if (a2) {
                    if (!this.t) {
                        EzPluginManager.a().b(msaVar.b(), new PullListener() { // from class: dkk
                            @Override // com.huawei.pluginmgr.filedownload.PullListener
                            public final void onPullingChange(msq msqVar, mso msoVar2) {
                                dkd.this.a(msaVar, b, msqVar, msoVar2);
                            }
                        });
                    } else {
                        cxl.b().c(this.q, msaVar.b(), new PullListener() { // from class: dkj
                            @Override // com.huawei.pluginmgr.filedownload.PullListener
                            public final void onPullingChange(msq msqVar, mso msoVar2) {
                                dkd.this.e(msaVar, b, msqVar, msoVar2);
                            }
                        });
                    }
                } else if (b.equals(msaVar.b())) {
                    LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList finish checking");
                    j();
                }
            }
        }
    }

    /* synthetic */ void a(msa msaVar, String str, msq msqVar, mso msoVar) {
        b(msoVar, msaVar, str);
    }

    /* synthetic */ void e(msa msaVar, String str, msq msqVar, mso msoVar) {
        b(msoVar, msaVar, str);
    }

    private void b(mso msoVar, msa msaVar, String str) {
        if (msoVar == null || msaVar == null || str == null) {
            LogUtil.h("EcologyDevice_DownloadSingleDeviceResourceTool", "pullResultToOperation result ezPluginIndexInfo lastDeviceUuid null");
            Message obtain = Message.obtain();
            obtain.what = 101;
            this.o.sendMessage(obtain);
        }
        if (msoVar != null && msoVar.i() == 1) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList is already new");
            Message obtain2 = Message.obtain();
            obtain2.obj = msaVar;
            obtain2.what = 98;
            this.o.sendMessage(obtain2);
        }
        if (str == null || msaVar == null || !str.equals(msaVar.b())) {
            return;
        }
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "buildNeedUpdateDownLoadDeviceList finish downloading last device");
        j();
    }

    private void j() {
        Message obtain = Message.obtain();
        obtain.what = 106;
        obtain.obj = this.r;
        this.o.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(int i) {
        long j = ((i + this.i) * 100) / this.s;
        if (j > 99) {
            j = 99;
        }
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.p.removeAll(this.r);
        if (this.p.size() > 0) {
            c(this.p);
            return;
        }
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "mDownLoadMode == " + this.d);
        h();
    }

    private void c(List<msa> list) {
        ReleaseLogUtil.e("EcologyDevice_DownloadSingleDeviceResourceTool", "downLoadDescriptionJsonFile enter");
        Message obtain = Message.obtain();
        obtain.what = 102;
        this.o.sendMessage(obtain);
        this.s = 0L;
        this.f = 0L;
        for (final msa msaVar : list) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "downLoadDescriptionJsonFile for circle uuid:", msaVar.b());
            if (!this.t) {
                EzPluginManager.a().a(msaVar.b(), this.f11693a, new PullListener() { // from class: dkr
                    @Override // com.huawei.pluginmgr.filedownload.PullListener
                    public final void onPullingChange(msq msqVar, mso msoVar) {
                        dkd.this.a(msaVar, msqVar, msoVar);
                    }
                });
            } else {
                b(msaVar);
            }
        }
    }

    /* synthetic */ void a(msa msaVar, msq msqVar, mso msoVar) {
        Object[] objArr = new Object[6];
        objArr[0] = "downLoadDescriptionJsonFile onPullingChange uuid:";
        objArr[1] = msaVar.b();
        objArr[2] = ", result:";
        objArr[3] = Integer.valueOf(msoVar.i());
        objArr[4] = ", Looper = ";
        objArr[5] = Boolean.valueOf(Looper.getMainLooper() == Looper.myLooper());
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", objArr);
        c(msoVar, msqVar);
    }

    private void b(msa msaVar) {
        final String b = msaVar.b();
        if (this.j.getSite() == 1) {
            String indexName = this.j.getIndexName();
            msa b2 = cxl.b().b(indexName, b);
            msc e = cxl.b().e(indexName, b);
            if (b2 != null) {
                this.k = b2.h();
            }
            if (e != null) {
                this.m = e.i();
            }
        }
        this.j.setConfigurationPoint(this.f11693a);
        cxl.b().b(this.j, msaVar.b(), new PullListener() { // from class: dkm
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public final void onPullingChange(msq msqVar, mso msoVar) {
                dkd.this.a(b, msqVar, msoVar);
            }
        }, new dkl());
    }

    /* synthetic */ void a(String str, msq msqVar, mso msoVar) {
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "updateDescriptionForThirdDevice uuid: ", str, ", result: ", Integer.valueOf(msoVar.i()));
        c(msoVar, msqVar);
        cpq.a(this.j, this.k, this.m, msoVar, msqVar);
    }

    private void c(mso msoVar, msq msqVar) {
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "handleAfterDownLoad total size is", Integer.valueOf(msoVar.j()), "pull size is ", Integer.valueOf(msoVar.b()), " and uuid is ", msqVar.o());
        if (msoVar.i() == 1) {
            long j = this.s + msoVar.j();
            this.s = j;
            if (j == 0) {
                ReleaseLogUtil.e("DEVMGR_EcologyDevice_DownloadSingleDeviceResourceTool", "handleAfterDownLoad mNeedDownloadByte is 0");
                b(101);
                return;
            }
            long j2 = this.f + 1;
            this.f = j2;
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "handleAfterDownLoad has downloaded ", Long.valueOf(j2), " description file");
            if (this.f == this.p.size()) {
                LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "handleAfterDownLoad completed and download byte = ", Long.valueOf(this.s));
                d();
                return;
            }
            return;
        }
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "handleAfterDownLoad fail");
        Message obtain = Message.obtain();
        obtain.what = 101;
        this.o.sendMessage(obtain);
    }

    private void d() {
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "enter downloadZipFile size：", Integer.valueOf(this.p.size()));
        this.l.clear();
        if (this.p.size() > 0) {
            a(0);
        }
    }

    private void a(final int i) {
        final msa msaVar = this.p.get(i);
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "enter downloadOneZipFile uuid = ", msaVar.b(), ", mDeviceType =", this.b);
        if (!this.t) {
            EzPluginManager.a().b(msaVar.b(), this.f11693a, new PullListener() { // from class: dki
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    dkd.this.c(i, msaVar, msqVar, msoVar);
                }
            });
        } else {
            cxl.b().e(this.j, msaVar.b(), new PullListener() { // from class: dkp
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    dkd.this.e(i, msaVar, msqVar, msoVar);
                }
            }, new dkl());
        }
    }

    /* synthetic */ void e(int i, msa msaVar, msq msqVar, mso msoVar) {
        c(i, msaVar, msqVar, msoVar);
        cpq.a(this.j, this.k, this.m, msoVar, msqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(int i, msa msaVar, msq msqVar, mso msoVar) {
        if (msqVar == null || msoVar == null) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "onPullingChange param is null");
            b(101);
            return;
        }
        int i2 = msoVar.i();
        LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile result status = ", Integer.valueOf(i2), ",and uuid = ", msqVar.o());
        if (i2 != 1) {
            if (i2 == 0) {
                LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile pull in progress");
                Message obtain = Message.obtain();
                obtain.what = 103;
                obtain.arg1 = msoVar.b();
                this.o.sendMessage(obtain);
                return;
            }
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile failed");
            b(101);
            return;
        }
        ReleaseLogUtil.e("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile one zip succeed");
        dke.e(msaVar);
        ResourceManager.e().h(msqVar.o());
        this.l.add(msaVar);
        this.i += msoVar.j();
        int i3 = i + 1;
        if (i3 < this.p.size()) {
            LogUtil.a("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile all zip is loading");
            a(i3);
        } else {
            ReleaseLogUtil.e("EcologyDevice_DownloadSingleDeviceResourceTool", "downloadOneZipFile all zip succeed");
            b(100);
        }
    }

    private void b(int i) {
        Message obtain = Message.obtain();
        obtain.what = i;
        this.o.sendMessage(obtain);
    }

    public void e() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.c = null;
        this.h = null;
    }
}
