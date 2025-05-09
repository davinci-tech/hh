package com.huawei.hms.network.file.b;

import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import java.util.Map;

/* loaded from: classes4.dex */
public class f extends k<BodyRequest> {
    long v;
    public long w;
    public long x;
    public long y;
    public long z;

    @Override // com.huawei.hms.network.file.core.task.k
    public com.huawei.hms.network.file.a.g m() {
        return null;
    }

    public long z() {
        return this.x;
    }

    public long y() {
        return this.w;
    }

    @Override // com.huawei.hms.network.file.core.task.k
    public k x() {
        return new f(p(), f());
    }

    public void f(long j) {
        this.z = j;
    }

    public void e(long j) {
        this.v = j;
    }

    public void d(long j) {
        this.y = j;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public e.b d() {
        return e.b.UPLOAD;
    }

    public void c(long j) {
        this.x = j;
    }

    public void b(long j) {
        this.w = j;
    }

    public void a(Map<String, Object> map) {
        map.put(Utils.REQUEST_TIME, String.valueOf(z()));
        map.put(Utils.RESPONSE_TIME, String.valueOf(A()));
        map.put("totalTime", String.valueOf(B()));
        FLogger.d("UploadTask", "timeinfo, requestTime:" + z() + ",responseTime:" + A() + ",totalTime:" + B(), new Object[0]);
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public long a() {
        return this.v;
    }

    public long B() {
        return this.z;
    }

    public long A() {
        return this.y;
    }

    public f(BodyRequest bodyRequest, long j) {
        super(bodyRequest, 0L, j);
    }

    public f(BodyRequest bodyRequest) {
        super(bodyRequest, 0L);
    }
}
