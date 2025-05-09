package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;

/* loaded from: classes5.dex */
public interface qf {
    void a(EventRecord eventRecord, ContentRecord contentRecord);

    void a(String str, EventRecord eventRecord);

    void a(String str, EventRecord eventRecord, ContentRecord contentRecord);

    void a(String str, EventRecord eventRecord, boolean z, ContentRecord contentRecord);

    void b(String str, EventRecord eventRecord, ContentRecord contentRecord);

    void b(String str, EventRecord eventRecord, boolean z, ContentRecord contentRecord);

    void c(String str, EventRecord eventRecord, boolean z, ContentRecord contentRecord);

    void e();

    void f();
}
