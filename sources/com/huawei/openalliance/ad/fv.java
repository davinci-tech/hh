package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.db.bean.ThirdPartyEventRecord;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface fv {
    long a(Class<? extends EventRecord> cls, EventRecord eventRecord);

    Map<String, EventRecord> a(Class<? extends EventRecord> cls, int i);

    void a(long j, int i);

    void a(long j, List<String> list);

    void a(ThirdPartyEventRecord thirdPartyEventRecord);

    void a(Class<? extends EventRecord> cls, String str, String str2, long j, List<String> list);

    void a(String str, String str2);

    void a(List<String> list);

    List<ThirdPartyEventRecord> b(long j, int i);

    void b(long j, List<String> list);

    void b(Class<? extends EventRecord> cls, List<String> list);
}
