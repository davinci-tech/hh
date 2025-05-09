package com.huawei.openalliance.ad.views.viewpager;

import android.view.View;
import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes5.dex */
class j implements Serializable, Comparator<View> {
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(View view, View view2) {
        i iVar = (i) view.getLayoutParams();
        i iVar2 = (i) view2.getLayoutParams();
        return iVar.b != iVar2.b ? iVar.b ? 1 : -1 : iVar.f - iVar2.f;
    }

    j() {
    }
}
