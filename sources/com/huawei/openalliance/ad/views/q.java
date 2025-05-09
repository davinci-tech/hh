package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ni;

/* loaded from: classes9.dex */
public class q extends m implements com.huawei.openalliance.ad.views.interfaces.i {
    private ImageView f;

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public boolean e() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.i
    public void a(Drawable drawable) {
        ho.b("PPSImageView", "onAdImageLoaded - set image to view");
        this.f.setImageDrawable(drawable);
        this.f8116a.a(this.d);
        dc.a(drawable);
        dc.a((Bitmap) null);
    }

    private void a(Context context) {
        inflate(context, R.layout.hiad_view_image_ad, this);
        this.f = (ImageView) findViewById(R.id.iv_ad_content);
    }

    public q(Context context, int i) {
        super(context);
        a(context);
        this.f8116a = new ni(context, this, i);
    }
}
