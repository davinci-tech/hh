package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.List;

/* loaded from: classes5.dex */
public interface fs {
    ContentRecord a(String str);

    ContentRecord a(String str, int i, long j, int i2);

    ContentRecord a(String str, String str2);

    ContentRecord a(String str, String str2, String str3);

    ContentRecord a(String str, String str2, String str3, int i, long j);

    List<ContentRecord> a(int i);

    void a(ContentRecord contentRecord);

    void a(ContentRecord contentRecord, List<String> list);

    void a(ContentRecord contentRecord, List<String> list, String str);

    void a(String str, long j);

    void a(String str, String str2, String str3, String str4);

    void a(List<ContentRecord> list);

    List<ContentRecord> b(String str);

    List<ContentRecord> b(String str, int i, long j, int i2);

    void b();

    void b(ContentRecord contentRecord);

    void b(ContentRecord contentRecord, List<String> list);

    void b(ContentRecord contentRecord, List<String> list, String str);

    void b(String str, String str2);

    List<ContentRecord> c();

    void c(ContentRecord contentRecord);

    void c(String str);

    long d(String str);
}
