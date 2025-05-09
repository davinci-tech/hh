package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.g;
import com.huawei.hms.network.file.core.task.k;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public interface c<R extends Request, T extends k, P extends g> {
    Result a(R r);

    ITaskResult a(R r, List<T> list);

    j a();

    List<T> a(R r, List<T> list, long j);

    List<R> a(boolean z);

    Set<Long> a(int i);

    void a(long j);

    void a(long j, boolean z);

    void a(R r, e.a aVar);

    boolean a(List<T> list);

    P b();

    List<T> b(long j);

    void b(R r);

    void b(R r, e.a aVar);

    void b(List<T> list);

    com.huawei.hms.network.file.core.e<R> c(long j);

    List<T> c(R r);

    void c();
}
