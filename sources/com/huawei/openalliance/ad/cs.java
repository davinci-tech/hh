package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.List;

/* loaded from: classes5.dex */
public interface cs {
    void a(int i, ContentRecord contentRecord);

    void a(int i, Integer num, Integer num2);

    void a(int i, String str, String str2, String str3, ContentRecord contentRecord, String str4);

    void a(com.huawei.openalliance.ad.analysis.a aVar, int i, String str, String str2);

    void a(ContentRecord contentRecord, int i, int i2);

    void a(ContentRecord contentRecord, int i, int i2, String str);

    void a(ContentRecord contentRecord, long j, long j2);

    void a(ContentRecord contentRecord, long j, long j2, com.huawei.openalliance.ad.analysis.a aVar);

    void a(ContentRecord contentRecord, String str, com.huawei.openalliance.ad.analysis.a aVar);

    void a(tf tfVar, String str);

    void a(String str, int i, int i2, int i3, String str2);

    void a(String str, int i, ContentRecord contentRecord);

    void a(String str, Integer num, String str2, ContentRecord contentRecord, boolean z);

    void a(String str, String str2, DelayInfo delayInfo, int i, int i2);

    void a(String str, String str2, ContentRecord contentRecord);

    void a(String str, List<String> list, List<String> list2);

    void a(Throwable th);

    void a(List<ContentRecord> list);

    void a(defpackage.vf vfVar);

    void b(ContentRecord contentRecord, int i);

    void b(ContentRecord contentRecord, String str);

    void b(ContentRecord contentRecord, String str, String str2);

    void b(String str, String str2, ContentRecord contentRecord);

    void c(ContentRecord contentRecord);

    void d(ContentRecord contentRecord, int i);
}
