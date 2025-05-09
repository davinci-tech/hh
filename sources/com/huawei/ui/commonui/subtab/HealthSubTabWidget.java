package com.huawei.ui.commonui.subtab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer;
import com.huawei.uikit.phone.hwsubtab.widget.HwSubTabWidget;
import defpackage.nqv;
import defpackage.nsc;
import defpackage.smy;

/* loaded from: classes6.dex */
public class HealthSubTabWidget extends HwSubTabWidget {
    public HealthSubTabWidget(Context context) {
        super(context);
    }

    public HealthSubTabWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthSubTabWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void b(int i) {
        if (this.b == null) {
            LogUtil.b("HealthSubTabWidget", "scrollTo tab at ", Integer.valueOf(i), " failed cause subTabContainer is null!");
            return;
        }
        HwSubTabViewContainer.SlidingTabStrip tabStrip = this.b.getTabStrip();
        if (tabStrip == null) {
            return;
        }
        int width = tabStrip.getWidth();
        int width2 = getWidth();
        if (i < 0 || i >= tabStrip.getChildCount()) {
            LogUtil.b("HealthSubTabWidget", "scrollTo tab at ", Integer.valueOf(i), " failed and tab count is: ", Integer.valueOf(tabStrip.getChildCount()));
            return;
        }
        View childAt = tabStrip.getChildAt(i);
        if (childAt == null) {
            LogUtil.b("HealthSubTabWidget", "scrollTo tab at ", Integer.valueOf(i), " failed cause tab is null!");
        } else {
            this.b.scrollTo(Math.min(childAt.getLeft(), width - width2), 0);
        }
    }

    public nqv cGu_(String str, Drawable drawable, Drawable drawable2, Drawable drawable3) {
        nqv nqvVar = new nqv(this, "");
        nqvVar.d(str);
        nqvVar.cGp_(drawable);
        nqvVar.cGq_(drawable2);
        nqvVar.cGr_(drawable3);
        return nqvVar;
    }

    @Override // com.huawei.uikit.phone.hwsubtab.widget.HwSubTabWidget, com.huawei.uikit.hwsubtab.widget.HwSubTabWidget
    /* renamed from: a */
    public HwSubTabWidget.SubTabView d(smy smyVar) {
        if (smyVar instanceof nqv) {
            return new HealthImgSubTabView(getContext(), (nqv) smyVar);
        }
        return super.d(smyVar);
    }

    protected class HealthImgSubTabView extends HwSubTabWidget.SubTabView {

        /* renamed from: a, reason: collision with root package name */
        private int f8955a;
        private float b;
        private int h;
        private int i;
        private int j;

        protected HealthImgSubTabView(Context context, final nqv nqvVar) {
            super(context, nqvVar);
            setVisibility(0);
            Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
            this.b = (int) (fontMetrics.bottom - fontMetrics.top);
            post(new Runnable() { // from class: com.huawei.ui.commonui.subtab.HealthSubTabWidget.HealthImgSubTabView.3
                @Override // java.lang.Runnable
                public void run() {
                    int height = HealthImgSubTabView.this.getHeight();
                    HealthImgSubTabView.this.cGx_(height, nqvVar.cGm_());
                    HealthImgSubTabView.this.cGy_(height, nqvVar.cGo_());
                    HealthImgSubTabView.this.invalidate();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cGx_(int i, Drawable drawable) {
            if (drawable == null) {
                return;
            }
            int i2 = (int) (i * 0.5f);
            this.f8955a = i2;
            this.j = (int) (i2 * (drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()));
            int measureText = (int) (this.j / getPaint().measureText(" "));
            StringBuilder sb = new StringBuilder(measureText);
            for (int i3 = 0; i3 < measureText; i3++) {
                sb.append(' ');
            }
            setText(sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cGy_(int i, Drawable drawable) {
            if (drawable == null) {
                return;
            }
            int i2 = (int) (i * 0.2f);
            this.h = i2;
            this.i = (int) (i2 * (drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()));
        }

        @Override // android.widget.TextView, android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            smy subTab = getSubTab();
            if (subTab instanceof nqv) {
                nqv nqvVar = (nqv) subTab;
                cGz_(canvas, nqvVar);
                cGA_(canvas, nqvVar);
            }
        }

        private void cGz_(Canvas canvas, nqv nqvVar) {
            Drawable cGn_ = HealthSubTabWidget.this.getSelectedSubTab() == nqvVar ? nqvVar.cGn_() : null;
            if (cGn_ == null) {
                cGn_ = nqvVar.cGm_();
            }
            if (cGn_ instanceof BitmapDrawable) {
                int width = getWidth();
                int height = getHeight();
                Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
                float f = (fontMetrics.bottom - fontMetrics.top) / this.b;
                int i = (int) (this.f8955a * f);
                int i2 = (int) (this.j * f);
                int i3 = (width / 2) - (i2 / 2);
                int i4 = ((height / 2) + (height / 12)) - (i / 2);
                cGn_.setBounds(i3, i4, i2 + i3, i + i4);
                cGn_.draw(canvas);
            }
        }

        private void cGA_(Canvas canvas, nqv nqvVar) {
            Drawable cGo_ = nqvVar.cGo_();
            if (cGo_ instanceof BitmapDrawable) {
                int width = getWidth() - this.i;
                TextPaint paint = getPaint();
                int height = ((int) (getHeight() - (paint.getFontMetrics().bottom - paint.getFontMetrics().top))) / 2;
                cGo_.setBounds(width, height, this.i + width, this.h + height);
                cGo_.draw(canvas);
            }
        }
    }

    public void b() {
        if (j()) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R$drawable.health_subtab_headline_underline);
            HwSubTabViewContainer.SlidingTabStrip subTabContentView = getSubTabContentView();
            nsc.e(subTabContentView, nsc.e(Drawable.class, subTabContentView)[0], drawable);
        }
    }

    private static boolean j() {
        return Build.MANUFACTURER.equalsIgnoreCase("meizu");
    }
}
