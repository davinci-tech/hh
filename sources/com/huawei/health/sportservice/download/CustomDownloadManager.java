package com.huawei.health.sportservice.download;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.health.sportservice.download.CustomDownloadManager;
import com.huawei.health.sportservice.download.common.BaseDownloader;
import com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation;
import com.huawei.health.sportservice.download.listener.ResDownloadCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.ui.commonui.R$string;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class CustomDownloadManager implements ResDownloadCallback {
    private AudioResDownloadListener e;
    private Context f;
    private int g;
    private ResDownloadUserConfirmation i;
    private boolean j;
    private int m;
    private int o;
    private long r;

    /* renamed from: a, reason: collision with root package name */
    private List<View.OnClickListener> f3005a = new ArrayList();
    private List<View.OnClickListener> b = new ArrayList();
    private List<BaseDownloader> h = new ArrayList();
    private HashMap<String, Boolean> n = new HashMap<>();
    private HashMap<String, Long> k = new HashMap<>();
    private int c = 0;
    private List<String> l = new ArrayList();
    private boolean d = false;

    public CustomDownloadManager(Context context) {
        this.f = context;
    }

    public void e(List<BaseDownloader> list, AudioResDownloadListener audioResDownloadListener) {
        if (koq.b(list)) {
            LogUtil.a("SportService_CustomDownloadManager", "downloaderList is empty");
            audioResDownloadListener.onResult(0);
            return;
        }
        if (Utils.l()) {
            LogUtil.a("SportService_CustomDownloadManager", "isOverseaNoCloudVersion");
            audioResDownloadListener.onResult(0);
            return;
        }
        this.h = list;
        this.e = audioResDownloadListener;
        for (BaseDownloader baseDownloader : list) {
            this.o += baseDownloader.getMaxNumberOfDownload();
            baseDownloader.setDownloadListener(this);
        }
    }

    @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
    public void onQueryResult(String str, boolean z, boolean z2, long j, List<String> list) {
        LogUtil.c("SportService_CustomDownloadManager", "type= ", str, " isDownload= ", Boolean.valueOf(z), " isCancelAllDownload = ", Boolean.valueOf(z2), " size= ", Long.valueOf(j));
        this.k.put(str, Long.valueOf(j));
        c(list);
        if (z) {
            this.r += j;
            this.c++;
            this.j = z;
        }
        if (z2) {
            LogUtil.c("SportService_CustomDownloadManager", "isCancelAllDownload");
            Iterator<BaseDownloader> it = this.h.iterator();
            while (it.hasNext()) {
                it.next().onCancelDownload();
            }
            this.e.onResult(0);
            return;
        }
        int size = this.k.size();
        int i = this.o;
        if (size != i) {
            LogUtil.c("SportService_CustomDownloadManager", "check statue...", Integer.valueOf(i));
            return;
        }
        if (!this.j) {
            LogUtil.c("SportService_CustomDownloadManager", "!mIsNeedDownload");
            this.e.onResult(0);
            return;
        }
        if (this.r == 0) {
            LogUtil.a("SportService_CustomDownloadManager", "mTotalSize == 0");
            this.e.onResult(0);
            return;
        }
        e();
        for (BaseDownloader baseDownloader : this.h) {
            Iterator<String> it2 = this.k.keySet().iterator();
            while (it2.hasNext()) {
                baseDownloader.setProgressRatio(it2.next(), this.k.get(r12).longValue() / this.r);
            }
        }
        c();
        this.k.clear();
    }

    private void c(List<String> list) {
        if (koq.b(list)) {
            LogUtil.a("SportService_CustomDownloadManager", "moduleNames is null");
            return;
        }
        for (String str : list) {
            if (!this.l.contains(str)) {
                this.l.add(str);
            }
        }
    }

    private void c() {
        ResDownloadUserConfirmation.axE_(this.f).runOnUiThread(new Runnable() { // from class: fgo
            @Override // java.lang.Runnable
            public final void run() {
                CustomDownloadManager.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        ResDownloadUserConfirmation resDownloadUserConfirmation = new ResDownloadUserConfirmation(this.f);
        this.i = resDownloadUserConfirmation;
        resDownloadUserConfirmation.axI_(new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.CustomDownloadManager.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomDownloadManager.this.e.onResult(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, this.l, this.r, this.f3005a, this.b);
    }

    private void e() {
        for (BaseDownloader baseDownloader : this.h) {
            this.f3005a.add(baseDownloader.confirmListener());
            this.b.add(baseDownloader.cancelListener());
        }
    }

    @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
    public void onProgress(int i, String str) {
        int i2 = this.m + i;
        this.m = i2;
        this.i.d("", i2, this.b);
    }

    @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
    public void onCompleteDownload(String str) {
        this.n.put(str, true);
    }

    @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
    public void onInstallSuccess(String str) {
        LogUtil.c("SportService_CustomDownloadManager", "mActualDownload= ", Integer.valueOf(this.c));
        int i = this.g + 1;
        this.g = i;
        if (i < this.h.size()) {
            LogUtil.c("SportService_CustomDownloadManager", "mDownloadSuccessNum= ", Integer.valueOf(this.g));
        } else if (this.n.size() == this.c) {
            ResDownloadUserConfirmation resDownloadUserConfirmation = this.i;
            if (resDownloadUserConfirmation != null) {
                resDownloadUserConfirmation.b();
            }
            this.e.onResult(1);
        }
    }

    @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
    public void onDownloadFail(int i, String str) {
        LogUtil.c("SportService_CustomDownloadManager", "errorCode ", Integer.valueOf(i), " errorInfo= ", str, "onDownloadFail ", Integer.valueOf(this.c));
        Activity axE_ = ResDownloadUserConfirmation.axE_(this.f);
        if (this.d) {
            LogUtil.c("SportService_CustomDownloadManager", "error dialog statue is open");
        } else {
            this.d = true;
            axE_.runOnUiThread(new Runnable() { // from class: fgl
                @Override // java.lang.Runnable
                public final void run() {
                    CustomDownloadManager.this.b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void b() {
        ResDownloadUserConfirmation resDownloadUserConfirmation = this.i;
        if (resDownloadUserConfirmation == null) {
            LogUtil.a("SportService_CustomDownloadManager", "show Error Dialog == null");
        } else {
            resDownloadUserConfirmation.b();
            this.i.axJ_(this.f.getString(R$string.IDS_update_download_failed), new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.CustomDownloadManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CustomDownloadManager.this.e.onResult(0);
                    CustomDownloadManager.this.d = false;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }, new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.CustomDownloadManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CustomDownloadManager.this.d = false;
                    CustomDownloadManager.this.a();
                    if (CommonUtil.aa(CustomDownloadManager.this.f)) {
                        Iterator it = CustomDownloadManager.this.h.iterator();
                        while (it.hasNext()) {
                            ((BaseDownloader) it.next()).startQueryState();
                        }
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.a("SportService_CustomDownloadManager", "The network is not connected.");
                    CustomDownloadManager.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.r = 0L;
        this.k.clear();
        this.c = 0;
        this.j = false;
        this.g = 0;
    }
}
