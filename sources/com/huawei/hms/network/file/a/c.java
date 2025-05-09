package com.huawei.hms.network.file.a;

import com.huawei.hms.network.file.core.task.j;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.util.List;

/* loaded from: classes4.dex */
public class c extends j<GetRequest, d> {
    @Override // com.huawei.hms.network.file.core.task.j
    public void a(GetRequest getRequest, List<d> list) {
        super.a((c) getRequest, (List) list);
        if (this.b <= 0) {
            this.b = c(list);
        }
    }

    @Override // com.huawei.hms.network.file.core.task.j, com.huawei.hms.network.file.core.task.d
    public long a(List<d> list) {
        long b = b(list);
        long j = this.f;
        long a2 = a();
        if (a2 == 0) {
            return 0L;
        }
        return (long) (((b - j) / a2) * 1000.0d);
    }

    public c(com.huawei.hms.network.file.core.task.c cVar) {
        super(cVar);
    }
}
