package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.core.task.k;
import java.util.List;

/* loaded from: classes4.dex */
public interface d<R extends Request, T extends k> {
    long a();

    long a(List<T> list);

    long b();

    long b(List<T> list);
}
