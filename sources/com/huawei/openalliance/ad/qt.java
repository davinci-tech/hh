package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.List;

/* loaded from: classes5.dex */
public interface qt {
    CheckResult a(ContentRecord contentRecord);

    ContentRecord a(long j, int i, int i2);

    ContentRecord a(ContentRecord contentRecord, int i, long j);

    ContentRecord a(ContentRecord contentRecord, int i, long j, byte[] bArr, int i2);

    void a();

    void a(ImageInfo imageInfo, ContentRecord contentRecord, long j);

    void a(ContentRecord contentRecord, String str);

    void a(Integer num);

    void a(List<String> list);

    void b();

    void b(List<String> list);

    void c();

    void d();
}
