package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class sq extends ta {
    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        ho.b("FeatureAbilityAction", "The current SDK does not support opening HARMONY SERVICE");
        return b();
    }

    public sq(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
    }
}
