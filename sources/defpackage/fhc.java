package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sportservice.download.common.BaseDownloader;
import com.huawei.health.sportservice.download.listener.ResDownloadCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fhc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class fhc extends BaseDownloader {

    /* renamed from: a, reason: collision with root package name */
    private fhd f12506a;
    private float b;
    private float c;
    private ResDownloadCallback e;
    private Context h;
    private String i;
    private String j;
    private String l;
    private String m;
    private int o = 0;
    private int k = 0;
    private View.OnClickListener g = new View.OnClickListener() { // from class: fhe
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            fhc.this.axD_(view);
        }
    };
    private ResDownloadCallback f = new AnonymousClass2();
    private fgq d = new fgq();

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public int getMaxNumberOfDownload() {
        return 2;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void onCancelDownload() {
    }

    /* synthetic */ void axD_(View view) {
        onStartDownload();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: fhc$2, reason: invalid class name */
    class AnonymousClass2 implements ResDownloadCallback {
        AnonymousClass2() {
        }

        @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
        public void onProgress(int i, String str) {
            int i2;
            if ("commonAudioDownloadState".equals(str)) {
                int i3 = (int) ((i - fhc.this.o) * fhc.this.c);
                if (i3 < 1) {
                    return;
                }
                fhc.this.o = i;
                fhc.this.e.onProgress(i3, str);
            }
            if (!"audioBusinessCompleteDownloadState".equals(str) || (i2 = (int) ((i - fhc.this.k) * fhc.this.b)) < 1) {
                return;
            }
            fhc.this.k = i;
            fhc.this.e.onProgress(i2, str);
        }

        @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
        public void onQueryCloudResult(HashMap<String, Boolean> hashMap, boolean z, long j) {
            LogUtil.a("SportService_LanguageAudioDownload", "onQueryDownloadResult", Long.valueOf(j));
            Iterator<String> it = hashMap.keySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                if (hashMap.get(it.next()).booleanValue()) {
                    i++;
                }
            }
            long size = j / (i == 0 ? hashMap.size() : i);
            ArrayList arrayList = new ArrayList();
            arrayList.add(fhc.this.i);
            int i2 = 0;
            for (String str : hashMap.keySet()) {
                boolean equals = Boolean.TRUE.equals(hashMap.get(str));
                if (equals) {
                    z = false;
                }
                i2++;
                if (i2 == hashMap.size() && !z && (fhc.this.h instanceof Activity)) {
                    ((Activity) fhc.this.h).runOnUiThread(new Runnable() { // from class: fhb
                        @Override // java.lang.Runnable
                        public final void run() {
                            fhc.AnonymousClass2.this.c();
                        }
                    });
                }
                fhc.this.e.onQueryResult(str, equals, z, size, arrayList);
            }
        }

        /* synthetic */ void c() {
            if (fhc.this.f12506a != null) {
                fhc.this.f12506a.startQueryState();
            }
        }

        @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
        public void onCompleteDownload(String str) {
            if ("commonAudioDownloadState".equals(str) || "audioBusinessCompleteDownloadState".equals(str)) {
                fhc.this.e.onCompleteDownload(str);
            }
        }

        @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
        public void onDownloadFail(int i, String str) {
            fhc.this.e.onDownloadFail(i, str);
        }

        @Override // com.huawei.health.sportservice.download.listener.ResDownloadCallback
        public void onInstallSuccess(String str) {
            fhc.this.e.onInstallSuccess(str);
        }
    }

    public fhc(Context context, String str, String str2, String str3, String str4) {
        this.h = context;
        this.j = str;
        this.m = str2;
        this.l = str3;
        this.i = str4;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void startQueryState() {
        this.d.downAudioResource(this.h, this.j, this.m, this.l, this.f);
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void onStartDownload() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fhc.5
            @Override // java.lang.Runnable
            public void run() {
                fhc.this.d.startDownload();
            }
        });
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public View.OnClickListener cancelListener() {
        return this.d.axz_();
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public View.OnClickListener confirmListener() {
        return this.g;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void setDownloadListener(ResDownloadCallback resDownloadCallback) {
        this.e = resDownloadCallback;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void setProgressRatio(String str, float f) {
        if ("commonAudioDownloadState".equals(str)) {
            this.c = f;
        }
        if ("audioBusinessCompleteDownloadState".equals(str)) {
            this.b = f;
        }
    }

    public void e(fhd fhdVar) {
        this.f12506a = fhdVar;
    }
}
