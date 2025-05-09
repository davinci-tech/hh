package com.huawei.openalliance.ad.download.app;

import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.qq;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes5.dex */
public class AppDownloadTask extends DownloadTask {
    private static final String TAG = "AppDownloadTask";

    @com.huawei.openalliance.ad.annotations.f
    private String agVerifyCode;
    private Integer agdDownloadSource;
    private int apiVer;

    @com.huawei.openalliance.ad.annotations.f
    private AppInfo appInfo;
    private String apptaskInfo;
    private String contentId;
    private String curInstallWay;
    private String customData;
    private Integer downloadSource;
    private Integer downloadSourceMutable;

    @com.huawei.openalliance.ad.annotations.f
    private qq eventProcessor;
    private int installSource;

    @com.huawei.openalliance.ad.annotations.f
    private String installType;
    private boolean isTaskStarter;
    private String showId;
    private String slotId;

    @com.huawei.openalliance.ad.annotations.f
    private long startTime;
    private String templateId;
    private String userId;
    private String venusExt;

    @com.huawei.openalliance.ad.annotations.f
    private Queue<String> installWayQueue = new ConcurrentLinkedQueue();
    private int agRealFailedReason = 0;

    public enum b {
        DOWN_LOAD_MODE_FROM_SELF,
        DOWN_LOAD_MODE_FROM_AG,
        DOWN_LOAD_MINI_FROM_AG,
        DOWN_LOAD_MODE_FROM_AG_SPECIFIED
    }

    public void q(String str) {
        this.templateId = str;
    }

    public void p(String str) {
        this.installType = str;
    }

    public void o(String str) {
        this.agVerifyCode = str;
    }

    public void n(String str) {
        this.showId = str;
    }

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public String n() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getPackageName();
        }
        return null;
    }

    public void m(String str) {
        this.contentId = str;
    }

    public void l(String str) {
        this.apptaskInfo = str;
    }

    public void k(String str) {
        this.slotId = str;
    }

    public void j(String str) {
        this.curInstallWay = str;
    }

    public void i(String str) {
        if (TextUtils.isEmpty(this.venusExt)) {
            this.venusExt = str;
        }
    }

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public int hashCode() {
        return super.hashCode();
    }

    public void h(String str) {
        this.userId = str;
    }

    public void g(String str) {
        this.customData = str;
    }

    public void f(int i) {
        this.agRealFailedReason = i;
    }

    @Override // com.huawei.openalliance.ad.download.DownloadTask
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void e(boolean z) {
        this.isTaskStarter = z;
    }

    public void e(int i) {
        this.apiVer = i;
    }

    public void d(long j) {
        this.startTime = j;
    }

    public void d(int i) {
        this.installSource = i;
    }

    public void c(Integer num) {
        this.agdDownloadSource = num;
    }

    public void b(Integer num) {
        this.downloadSourceMutable = num;
    }

    public void a(Integer num) {
        if (this.downloadSource == null) {
            this.downloadSource = num;
        }
        this.downloadSourceMutable = num;
    }

    public void a(qq qqVar) {
        this.eventProcessor = qqVar;
    }

    public void a(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public boolean T() {
        return this.isTaskStarter;
    }

    public int S() {
        return this.agRealFailedReason;
    }

    public ContentRecord R() {
        qq qqVar = this.eventProcessor;
        if (qqVar != null) {
            return qqVar.a();
        }
        return null;
    }

    public long Q() {
        return this.startTime;
    }

    public String P() {
        return this.installType;
    }

    public String O() {
        return this.agVerifyCode;
    }

    public String N() {
        return this.apptaskInfo;
    }

    public String M() {
        return this.slotId;
    }

    public b L() {
        String E = E();
        return (this.appInfo == null || !"5".equals(E)) ? (this.appInfo == null || !"6".equals(E)) ? (this.appInfo == null || !"8".equals(E)) ? b.DOWN_LOAD_MODE_FROM_SELF : b.DOWN_LOAD_MODE_FROM_AG_SPECIFIED : b.DOWN_LOAD_MINI_FROM_AG : b.DOWN_LOAD_MODE_FROM_AG;
    }

    public boolean K() {
        AppInfo appInfo = this.appInfo;
        return (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName()) || !s(E())) ? false : true;
    }

    public boolean J() {
        boolean z = false;
        if (!V()) {
            return false;
        }
        if (this.installWayQueue.poll() != null && !this.installWayQueue.isEmpty()) {
            z = true;
        }
        j(this.installWayQueue.peek());
        return z;
    }

    public boolean I() {
        return "7".equals(E());
    }

    public String H() {
        return this.curInstallWay;
    }

    public String G() {
        return this.venusExt;
    }

    public Integer F() {
        return this.downloadSource;
    }

    public String E() {
        String H = H();
        if (!TextUtils.isEmpty(H)) {
            return H;
        }
        AppInfo appInfo = this.appInfo;
        return appInfo != null ? appInfo.b(this.downloadSource) : "4";
    }

    public int D() {
        return this.installSource;
    }

    public qq C() {
        return this.eventProcessor;
    }

    public AppInfo B() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null && TextUtils.isEmpty(appInfo.getUniqueId())) {
            this.appInfo.s(UUID.randomUUID().toString());
        }
        return this.appInfo;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private AppInfo f6737a;
        private String b;
        private String c;
        private boolean d;
        private qq e;

        public a b(String str) {
            this.c = str;
            return this;
        }

        public AppDownloadTask a() {
            if (this.f6737a == null) {
                return null;
            }
            AppDownloadTask appDownloadTask = new AppDownloadTask();
            appDownloadTask.a(this.d);
            appDownloadTask.d(this.b);
            appDownloadTask.a(this.e);
            appDownloadTask.a(this.f6737a);
            appDownloadTask.e(this.c);
            appDownloadTask.a(this.f6737a.getDownloadUrl());
            appDownloadTask.b(this.f6737a.getSafeDownloadUrl());
            appDownloadTask.c(this.f6737a.getSha256());
            appDownloadTask.d(this.f6737a.isCheckSha256());
            appDownloadTask.a(this.f6737a.getFileSize());
            appDownloadTask.a(0);
            appDownloadTask.b(this.f6737a);
            return appDownloadTask;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(qq qqVar) {
            this.e = qqVar;
            return this;
        }

        public a a(AppInfo appInfo) {
            this.f6737a = appInfo;
            return this;
        }
    }

    private boolean s(String str) {
        return !TextUtils.isEmpty(str) && (str.equals("8") || str.equals("6") || str.equals("5"));
    }

    private boolean r(String str) {
        AppInfo appInfo;
        return (!"7".equals(str) || (appInfo = this.appInfo) == null || TextUtils.isEmpty(appInfo.r())) ? false : true;
    }

    private boolean c(AppInfo appInfo) {
        if (appInfo == null) {
            return true;
        }
        return appInfo.isCheckSha256() && TextUtils.isEmpty(appInfo.getSha256());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AppInfo appInfo) {
        if (appInfo == null) {
            return;
        }
        try {
            this.installWayQueue.clear();
            String b2 = appInfo.b(this.downloadSource);
            if (!TextUtils.isEmpty(b2)) {
                this.installWayQueue.offer(b2);
            }
            String t = appInfo.t();
            if (!TextUtils.isEmpty(t)) {
                String[] split = t.split(",");
                if (split.length > 0) {
                    for (String str : split) {
                        if (s(str) || r(str) || !U()) {
                            this.installWayQueue.offer(str);
                        }
                    }
                }
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    private boolean V() {
        Integer num;
        return this.installWayQueue.size() > 1 && ((num = this.agdDownloadSource) == null || num.intValue() == 2);
    }

    private boolean U() {
        AppInfo appInfo = this.appInfo;
        if (appInfo == null) {
            return true;
        }
        if ("11".equals(appInfo.getPriorInstallWay())) {
            return false;
        }
        return TextUtils.isEmpty(this.appInfo.getPackageName()) || TextUtils.isEmpty(this.appInfo.getDownloadUrl()) || c(this.appInfo) || this.appInfo.getFileSize() <= 0;
    }
}
