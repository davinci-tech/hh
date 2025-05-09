package com.huawei.openalliance.ad.views.gif;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes5.dex */
public class GifPlayView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private d f8075a;

    public void setPlayerCallback(d dVar) {
        this.f8075a = dVar;
    }

    public void setGifDrawable(b bVar) {
        bVar.a(this.f8075a);
        setImageDrawable(bVar);
    }

    public GifPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GifPlayView(Context context) {
        super(context);
    }
}
