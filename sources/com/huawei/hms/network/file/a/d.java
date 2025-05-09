package com.huawei.hms.network.file.a;

import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.download.api.GetRequest;

/* loaded from: classes4.dex */
public class d extends k<GetRequest> {
    private String v;
    long w;
    long x;
    private g y;

    public String z() {
        return this.v;
    }

    public long y() {
        return this.x;
    }

    @Override // com.huawei.hms.network.file.core.task.k
    public k x() {
        d dVar = new d(p(), (l) u(), this.r, this.w, this.x, this.v, this.t, f());
        dVar.y = this.y;
        dVar.b(k());
        dVar.a(b());
        return dVar;
    }

    @Override // com.huawei.hms.network.file.core.task.k
    public String toString() {
        return "DownloadTask{startPos=" + this.w + ", endPos=" + this.x + ", finishedSize=" + this.r + super.toString() + '}';
    }

    @Override // com.huawei.hms.network.file.core.task.k
    public g m() {
        return this.y;
    }

    public void f(String str) {
        this.v = str;
    }

    public void e(int i) {
        this.t = i;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public e.b d() {
        return e.b.DOWNLOAD;
    }

    public void b(long j) {
        this.q = j;
    }

    public void a(long j, long j2, int i) {
        this.y.a(j, j2, i);
    }

    public void a(long j, long j2) {
        this.w = j;
        this.x = j2;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public long a() {
        return (this.x - this.w) + 1;
    }

    public boolean C() {
        return this.t > 1 || this.w + this.r > 0 || this.x > 0;
    }

    public String B() {
        return a(p().getId(), this.v);
    }

    public long A() {
        return this.w;
    }

    public static String a(long j, String str) {
        return str + ".tmp" + j;
    }

    public d(GetRequest getRequest, l lVar, long j, long j2, long j3, String str, int i, long j4) {
        super(getRequest, j, j4);
        this.y = new g();
        this.t = i;
        this.w = j2;
        this.x = j3;
        this.v = str;
        if (getRequest != null) {
            this.q = getRequest.getFileSize();
        }
        a(lVar);
    }

    public d(GetRequest getRequest, long j, long j2, long j3, String str, int i, long j4) {
        super(getRequest, j, j4);
        this.y = new g();
        this.t = i;
        this.w = j2;
        this.x = j3;
        this.v = str;
        if (getRequest != null) {
            this.q = getRequest.getFileSize();
        }
    }

    public d(GetRequest getRequest, long j, long j2, long j3, String str, int i) {
        super(getRequest, j);
        this.y = new g();
        this.t = i;
        this.w = j2;
        this.x = j3;
        this.v = str;
        this.q = getRequest.getFileSize();
    }

    public d(long j, long j2, long j3, String str, long j4) {
        this(j, j2, j3, str, 1, j4);
    }

    public d(long j, long j2, long j3, String str, int i, long j4) {
        super(null, j, j4);
        this.y = new g();
        this.t = i;
        this.w = j2;
        this.x = j3;
        this.v = str;
    }
}
