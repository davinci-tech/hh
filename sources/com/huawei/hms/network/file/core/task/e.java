package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.httpclient.Submit;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public interface e<R extends Request> {

    public enum a {
        INIT,
        PROCESS,
        PAUSE,
        CANCEL,
        INVALID
    }

    public enum b {
        DOWNLOAD,
        UPLOAD
    }

    long a();

    void a(R r);

    void a(String str);

    void a(Future<?> future);

    void a(boolean z);

    void b(boolean z);

    boolean b();

    long c();

    b d();

    Future<?> e();

    long f();

    Submit g();

    boolean h();
}
