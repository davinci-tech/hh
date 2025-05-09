package com.huawei.openalliance.ad.views.interfaces;

import android.content.Context;
import android.view.View;
import com.huawei.hms.ads.uiengine.common.MediaStateApi;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.hl;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.ou;

/* loaded from: classes5.dex */
public interface l extends jk, lm {
    n a(int i);

    Integer a(ContentRecord contentRecord);

    void a();

    void a(int i, int i2, String str, boolean z, Integer num);

    void a(int i, boolean z);

    void a(View view, ContentRecord contentRecord);

    void a(AdLoadMode adLoadMode);

    void a(ContentRecord contentRecord, int i);

    void a(hl hlVar);

    void a(n nVar, ContentRecord contentRecord);

    void b();

    void b(int i);

    void c();

    AdSlotParam getAdSlotParam();

    int getAdType();

    int getAudioFocusType();

    Context getContext();

    MediaStateApi getMediaProxy();

    ou getMonitorEp();
}
