package com.huawei.ui.commonui.illustration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes6.dex */
public class IllustrationView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8840a;
    private int b;
    private ImageView c;
    private RelativeLayout d;
    private Context e;
    private int h;
    private String j;

    public IllustrationView(Context context) {
        this(context, null);
    }

    public IllustrationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IllustrationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = -1;
        this.e = context;
        e();
        cAO_(attributeSet);
    }

    private void e() {
        LayoutInflater.from(this.e).inflate(R.layout.health_common_illustration_view, (ViewGroup) this, true);
        this.d = (RelativeLayout) findViewById(R.id.illustration_view);
        this.c = (ImageView) findViewById(R.id.illustration_background);
        this.f8840a = (HealthTextView) findViewById(R.id.illustration_title);
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
        if (identifier > 0) {
            this.h = getResources().getDimensionPixelSize(identifier);
        }
        this.d.getLayoutParams().height = -2;
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -1;
    }

    private void cAO_(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.e.obtainStyledAttributes(attributeSet, R$styleable.IllustrationView);
        this.j = obtainStyledAttributes.getString(R$styleable.IllustrationView_title);
        this.b = obtainStyledAttributes.getResourceId(R$styleable.IllustrationView_illustration, -1);
        obtainStyledAttributes.recycle();
        String str = this.j;
        if (str != null) {
            setSubHeader(str);
        }
        int i = this.b;
        if (i != -1) {
            setIllustration(i);
        }
    }

    public void setSubHeader(String str) {
        HealthTextView healthTextView = this.f8840a;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void setIllustration(int i) {
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
    }

    public void setIllustration(Bitmap bitmap) {
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setSubHeaderVisibility(int i) {
        HealthTextView healthTextView = this.f8840a;
        if (healthTextView != null) {
            healthTextView.setVisibility(i);
        }
    }
}
