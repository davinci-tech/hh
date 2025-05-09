package com.huawei.openalliance.ad.download.app;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: classes5.dex */
public class o extends com.huawei.openalliance.ad.views.viewpager.f {

    /* renamed from: a, reason: collision with root package name */
    List<View> f6802a;

    @Override // com.huawei.openalliance.ad.views.viewpager.f
    public boolean a(View view, Object obj) {
        return view == obj;
    }

    @Override // com.huawei.openalliance.ad.views.viewpager.f
    public void a(ViewGroup viewGroup, int i, Object obj) {
        List<View> list = this.f6802a;
        if (list != null) {
            viewGroup.removeView(list.get(i));
        }
    }

    @Override // com.huawei.openalliance.ad.views.viewpager.f
    public Object a(ViewGroup viewGroup, int i) {
        List<View> list = this.f6802a;
        if (list == null) {
            return null;
        }
        viewGroup.addView(list.get(i));
        return this.f6802a.get(i);
    }

    @Override // com.huawei.openalliance.ad.views.viewpager.f
    public int a() {
        List<View> list = this.f6802a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public o(List<View> list) {
        this.f6802a = list;
    }
}
