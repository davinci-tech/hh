package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.openalliance.ad.hl;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.nr;
import com.huawei.openalliance.ad.og;
import com.huawei.openalliance.ad.utils.dd;

/* loaded from: classes5.dex */
public class ad extends RelativeLayout implements com.huawei.openalliance.ad.views.interfaces.s {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8029a = "ad";
    private og b;
    private int c;
    private int d;
    private int e;
    private View f;
    private float g;

    public void setWideSloganResId(int i) {
        this.d = i;
    }

    public void setSloganShowListener(hl hlVar) {
        this.b.a(hlVar);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ho.a(f8029a, "onSizeChanged w: %d h: %d oldw: %d oldh: %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        a(i, i2);
    }

    public int getOrientation() {
        return this.e;
    }

    public void b() {
        setVisibility(8);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.s
    public void a(int i) {
        ImageView imageView;
        if (dd.c(getContext())) {
            ho.c(f8029a, "showImageView - activity finished, not add view");
            return;
        }
        View view = this.f;
        if (view instanceof ImageView) {
            imageView = (ImageView) view;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.f = imageView;
            addView(imageView, new RelativeLayout.LayoutParams(-1, -1));
        }
        imageView.setImageResource(i);
    }

    public void a() {
        setVisibility(0);
        if (this.f == null) {
            this.b.a(this.c, true);
        }
    }

    private void b(int i) {
        this.b = new nr(getContext(), this, i);
    }

    private void a(int i, int i2) {
        int i3;
        String str;
        if (i <= 0 || i2 <= 0) {
            return;
        }
        float f = (i * 1.0f) / i2;
        float abs = Math.abs(this.g - f);
        String str2 = f8029a;
        ho.a(str2, "ratio: %s diff:%s", Float.valueOf(f), Float.valueOf(abs));
        if (abs > 0.01f) {
            this.g = f;
            if (f <= 0.9f || (i3 = this.d) <= 0) {
                i3 = this.c;
                str = "pick defaultSloganResId";
            } else {
                str = "pick wideSloganResId";
            }
            ho.a(str2, str);
            this.b.a(i3, false);
        }
    }

    public ad(Context context, int i, int i2, int i3) {
        super(context);
        this.d = 0;
        this.e = i;
        this.c = i2;
        b(i3);
    }
}
