package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.views.interfaces.k;

/* loaded from: classes5.dex */
public interface oe<V extends com.huawei.openalliance.ad.views.interfaces.k> {
    int a(ContentRecord contentRecord, int i);

    View a(IRemoteCreator iRemoteCreator, bv bvVar, ContentRecord contentRecord, com.huawei.openalliance.ad.inter.data.h hVar);

    String a(int i, int i2, com.huawei.openalliance.ad.inter.data.h hVar);

    void a();

    void a(long j);

    void a(long j, int i, int i2, int i3);

    void a(long j, int i, Integer num);

    void a(long j, long j2);

    void a(String str);

    void a(String str, ContentRecord contentRecord);

    void a(String str, com.huawei.openalliance.ad.inter.data.h hVar, IRewardAdStatusListener iRewardAdStatusListener);

    void a(boolean z);

    boolean a(int i, MaterialClickInfo materialClickInfo);

    boolean a(com.huawei.openalliance.ad.inter.data.h hVar);

    boolean a(String str, int i);

    void b();

    void b(String str);

    void b(String str, com.huawei.openalliance.ad.inter.data.h hVar, IRewardAdStatusListener iRewardAdStatusListener);

    boolean b(ContentRecord contentRecord);

    boolean b(com.huawei.openalliance.ad.inter.data.h hVar);

    void c(com.huawei.openalliance.ad.inter.data.h hVar);

    boolean c();
}
