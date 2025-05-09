package com.huawei.openalliance.ad.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.constraintlayout.motion.widget.Key;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class l extends j {
    protected ImageView f;

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ho.b(getViewTag(), "w=%s, h=%s, oldw=%s, oldh=%s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        this.f.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.l.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b(l.this.getViewTag(), "imageView %s %s", Integer.valueOf(l.this.f.getWidth()), Integer.valueOf(l.this.f.getHeight()));
                l.this.f.setPivotX(l.this.f.getWidth() / 2.0f);
                l.this.f.setPivotY(l.this.f.getHeight() + ao.a(l.this.getContext(), 80.0f));
                l.this.b();
            }
        });
    }

    protected String getViewTag() {
        return "PPSBaseTwistView";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new gq(0.33f, 0.0f, 0.67f, 1.0f));
        ArrayList arrayList = new ArrayList(4);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f, Key.ROTATION, 0.0f, -7.0f);
        ofFloat.setDuration(150L);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f, Key.ROTATION, -7.0f, 7.0f);
        ofFloat2.setDuration(400L);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f, Key.ROTATION, 7.0f, -4.5f);
        ofFloat3.setDuration(350L);
        arrayList.add(ofFloat3);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f, Key.ROTATION, -4.5f, 2.0f);
        ofFloat4.setDuration(350L);
        arrayList.add(ofFloat4);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f, Key.ROTATION, 2.0f, 0.0f);
        ofFloat5.setDuration(250L);
        arrayList.add(ofFloat5);
        this.f.invalidate();
        animatorSet.playSequentially(arrayList);
        animatorSet.start();
    }

    public l(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public l(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public l(Context context) {
        super(context);
    }
}
