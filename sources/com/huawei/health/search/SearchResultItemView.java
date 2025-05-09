package com.huawei.health.search;

import android.content.Context;
import com.huawei.health.knit.section.view.BaseBiItemView;
import defpackage.fbh;

/* loaded from: classes3.dex */
public class SearchResultItemView extends BaseBiItemView {
    public SearchResultItemView(Context context, int i) {
        super(context, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseBiItemView
    public void e() {
        if (this.d != null) {
            fbh.e(this.c, this.d);
            this.d = null;
        }
    }
}
