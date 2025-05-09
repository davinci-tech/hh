package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.inter.AdParseConfig;
import com.huawei.openalliance.ad.inter.listeners.ContentIdListener;

/* loaded from: classes5.dex */
public interface qs {
    void a(int i);

    void a(AdContentRsp adContentRsp, long j, AdParseConfig adParseConfig);

    void a(ContentIdListener contentIdListener);

    void a(boolean z);

    boolean a();

    void b(boolean z);

    boolean b();

    void c(boolean z);
}
