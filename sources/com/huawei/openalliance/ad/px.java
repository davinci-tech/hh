package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Content;

/* loaded from: classes5.dex */
public abstract class px implements pz {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7460a;

    protected abstract String a();

    protected abstract boolean a(Content content, int i);

    @Override // com.huawei.openalliance.ad.pz
    public boolean a(String str, int i, int i2, Content content) {
        if (content == null) {
            return true;
        }
        if (ho.a()) {
            ho.a(a(), "filterContents adType: %d contentid: %s requestType: %d", Integer.valueOf(i), content.g(), Integer.valueOf(i2));
        }
        boolean a2 = a(content, i);
        if (a2) {
            ho.c(a(), "contentid %s is discarded", content.g());
        }
        return a2;
    }

    px(Context context) {
        this.f7460a = context.getApplicationContext();
    }
}
