package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.healthcloud.plugintrack.ui.viewholder.ExplodeAnimationHelper;
import defpackage.hmg;
import defpackage.koq;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ExplodeAnimationHelper {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<hmg> f3816a = new ArrayList<>();
    private ViewGroup b;
    private Size c;
    private PointF d;
    private final ExplodeAnimationCallback e;
    private boolean h;
    private List<List<Bitmap>> j;

    public interface ExplodeAnimationCallback {
        void onAnimationComplete();
    }

    public ExplodeAnimationHelper(List<List<Bitmap>> list, ViewGroup viewGroup, PointF pointF, ExplodeAnimationCallback explodeAnimationCallback) {
        this.e = explodeAnimationCallback;
        this.j = list;
        this.b = viewGroup;
        this.d = pointF;
    }

    public void bkE_(Size size) {
        this.c = size;
    }

    public void a() {
        ViewGroup viewGroup;
        Bitmap bitmap;
        if (koq.b(this.j) || (viewGroup = this.b) == null || this.d == null) {
            return;
        }
        viewGroup.removeAllViews();
        this.f3816a.clear();
        for (List<Bitmap> list : this.j) {
            if (!koq.b(list) && (bitmap = list.get(new SecureRandom().nextInt(list.size()))) != null) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Size size = this.c;
                if (size != null) {
                    width = Math.min(width, size.getWidth());
                    height = Math.min(height, this.c.getHeight());
                }
                bkD_(this.b, bitmap, width, height, this.d);
            }
        }
    }

    private ImageView bkC_(Context context) {
        return new ImageView(context);
    }

    private void bkD_(ViewGroup viewGroup, Bitmap bitmap, int i, int i2, PointF pointF) {
        for (int i3 = 0; i3 < 31; i3++) {
            ImageView bkC_ = bkC_(viewGroup.getContext());
            bkC_.setImageBitmap(bitmap);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i2);
            float f = i / 2.0f;
            layoutParams.leftMargin = Math.round(pointF.x - f);
            float f2 = i2 / 2.0f;
            layoutParams.topMargin = Math.round(pointF.y - f2);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(10, -1);
            viewGroup.addView(bkC_, layoutParams);
            this.f3816a.add(new hmg(bkC_, new PointF(pointF.x - f, pointF.y - f2)));
        }
    }

    public void bkF_(View view) {
        this.h = true;
        for (int i = 0; i < this.f3816a.size(); i++) {
            this.f3816a.get(i).b();
        }
        view.postDelayed(new Runnable() { // from class: hme
            @Override // java.lang.Runnable
            public final void run() {
                ExplodeAnimationHelper.this.e();
            }
        }, 3200L);
    }

    public /* synthetic */ void e() {
        this.h = false;
        ExplodeAnimationCallback explodeAnimationCallback = this.e;
        if (explodeAnimationCallback != null) {
            explodeAnimationCallback.onAnimationComplete();
        }
    }
}
