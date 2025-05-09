package com.huawei.openalliance.ad.download;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.DownloadBlockInfo;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes5.dex */
public class DownloadTask implements Comparable<DownloadTask> {

    @com.huawei.openalliance.ad.annotations.f
    private static final AtomicLong SEQ = new AtomicLong(0);
    private boolean allowedMobileNetowrk;
    private ContentResource contentResource;

    @com.huawei.openalliance.ad.annotations.f
    private DownloadBlockInfo downloadBlockInfo;
    private boolean downloadFromSafeUrl;
    private long downloadStartSize;
    private long downloadedSize;
    private long fileTotalSize;

    @com.huawei.openalliance.ad.annotations.f
    private HttpConnection httpConnection;
    private int id;
    private String localPath;
    private int priority;
    private int progress;
    private int redirectCount;
    private String redirectUrl;
    private String safeUrl;
    private String sha256;
    private String tmpLocalPath;
    private String url;

    @com.huawei.openalliance.ad.annotations.f
    private h worker;

    @com.huawei.openalliance.ad.annotations.f
    private final byte[] lock = new byte[0];
    private boolean checkSha256 = true;

    @com.huawei.openalliance.ad.annotations.f
    private e status = e.IDLE;

    @com.huawei.openalliance.ad.annotations.f
    private b failedReason = b.NONE;

    @com.huawei.openalliance.ad.annotations.f
    private c pauseReason = c.NONE;

    @com.huawei.openalliance.ad.annotations.f
    private a downloadMode = a.DOWNLOAD_NOW;
    private boolean canceled = false;
    private final long seqNum = SEQ.getAndIncrement();

    public boolean z() {
        return this.checkSha256;
    }

    public void y() {
        DownloadBlockInfo downloadBlockInfo = this.downloadBlockInfo;
        if (downloadBlockInfo == null) {
            return;
        }
        downloadBlockInfo.a(this.downloadStartSize <= 0 && this.downloadedSize == this.fileTotalSize);
    }

    public DownloadBlockInfo x() {
        return this.downloadBlockInfo;
    }

    public void w() {
        h v = v();
        if (v != null) {
            v.a(this);
        }
    }

    public h v() {
        return this.worker;
    }

    public HttpConnection u() {
        return this.httpConnection;
    }

    public int t() {
        return this.redirectCount;
    }

    public String s() {
        return this.redirectUrl;
    }

    public boolean r() {
        return this.downloadFromSafeUrl;
    }

    public boolean q() {
        return this.canceled;
    }

    public c p() {
        return this.pauseReason;
    }

    public boolean o() {
        return this.allowedMobileNetowrk;
    }

    public String n() {
        return a();
    }

    public long m() {
        return this.seqNum;
    }

    public int l() {
        return this.progress;
    }

    public int k() {
        return this.priority;
    }

    public b j() {
        return this.failedReason;
    }

    public e i() {
        e eVar;
        synchronized (this.lock) {
            eVar = this.status;
        }
        return eVar;
    }

    public int hashCode() {
        return n() != null ? n().hashCode() : super.hashCode();
    }

    public a h() {
        return this.downloadMode;
    }

    public long g() {
        return this.downloadedSize;
    }

    public void f(String str) {
        this.redirectUrl = str;
    }

    public long f() {
        return this.fileTotalSize;
    }

    public boolean equals(Object obj) {
        return obj != null && (this == obj || ((obj instanceof DownloadTask) && TextUtils.equals(n(), ((DownloadTask) obj).n())));
    }

    public void e(String str) {
        this.tmpLocalPath = str;
    }

    public String e() {
        return this.tmpLocalPath;
    }

    public void d(boolean z) {
        this.checkSha256 = z;
    }

    public void d(String str) {
        this.localPath = str;
    }

    public String d() {
        return this.localPath;
    }

    void c(boolean z) {
        this.downloadFromSafeUrl = z;
    }

    public void c(String str) {
        this.sha256 = str;
    }

    public void c(long j) {
        this.downloadStartSize = j;
    }

    public void c(int i) {
        this.redirectCount = i;
    }

    public String c() {
        return this.sha256;
    }

    public void b(boolean z) {
        this.canceled = z;
    }

    public void b(String str) {
        this.safeUrl = str;
    }

    public void b(long j) {
        this.downloadedSize = j;
    }

    public void b(int i) {
        this.progress = i;
    }

    public String b() {
        return this.safeUrl;
    }

    public void a(boolean z) {
        this.allowedMobileNetowrk = z;
    }

    public void a(String str) {
        this.url = str;
    }

    public void a(h hVar) {
        this.worker = hVar;
    }

    public void a(e eVar) {
        synchronized (this.lock) {
            this.status = eVar;
        }
    }

    public void a(c cVar) {
        this.pauseReason = cVar;
    }

    public void a(b bVar) {
        this.failedReason = bVar;
    }

    public void a(a aVar) {
        this.downloadMode = aVar;
    }

    public void a(ContentResource contentResource) {
        this.contentResource = contentResource;
    }

    public void a(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    public void a(DownloadBlockInfo downloadBlockInfo) {
        this.downloadBlockInfo = downloadBlockInfo;
    }

    public enum c {
        NONE(0),
        USER_CLICK(1),
        NETWORK_CHANGED(2),
        WAITING_WIFI_DOWNLOAD(3),
        HW_VIDEO(100);

        private int f;

        public int a() {
            return this.f;
        }

        public static c a(int i) {
            for (c cVar : values()) {
                if (cVar.a() == i) {
                    return cVar;
                }
            }
            return NONE;
        }

        c(int i) {
            this.f = i;
        }
    }

    public void a(long j) {
        this.fileTotalSize = j;
    }

    public enum a {
        DOWNLOAD_NOW(0),
        WAITING_WIFI_DOWNLOAD(1);

        private int c;

        public int a() {
            return this.c;
        }

        a(int i) {
            this.c = i;
        }
    }

    public enum b {
        NONE(0),
        CONN_FAILED(1),
        NO_SPACE(2),
        FILE_SIZE_ERROR(3),
        FILE_SHA256_ERROR(4),
        NO_NETWORK(5),
        MOBILE_NETWORK(6),
        USER_CANCEL(7);

        int i;

        public int a() {
            return this.i;
        }

        b(int i) {
            this.i = i;
        }
    }

    public void a(int i) {
        this.priority = i;
    }

    public String a() {
        return this.url;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return -1;
        }
        int i = downloadTask.priority - this.priority;
        return i == 0 ? this.seqNum < downloadTask.seqNum ? -1 : 1 : i;
    }

    public ContentResource A() {
        return this.contentResource;
    }
}
