package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.CheckResult;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public interface qp {
    ContentRecord a(ContentRecord contentRecord, int i, long j);

    ContentRecord a(ContentRecord contentRecord, int i, long j, byte[] bArr, int i2);

    void a();

    void a(ImageInfo imageInfo, ContentRecord contentRecord, long j);

    void a(Integer num);

    void a(String str);

    CheckResult b(ContentRecord contentRecord);

    void b();
}
