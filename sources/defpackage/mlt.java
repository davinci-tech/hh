package defpackage;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.ui.views.CardLinearSnapHelper;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes6.dex */
public class mlt {
    private List<MedalInfoDesc> d;
    private float b = 0.0f;
    private CardLinearSnapHelper e = new CardLinearSnapHelper();

    public void ckI_(RecyclerView recyclerView, final ImageView imageView, final HealthTextView healthTextView, final HealthTextView healthTextView2, List<MedalInfoDesc> list) {
        this.d = list;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: mlt.5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                View findSnapView;
                super.onScrollStateChanged(recyclerView2, i);
                mlt.this.e.c(true);
                if (i != 0 || koq.b(mlt.this.d) || recyclerView2.getChildCount() <= 0 || (findSnapView = mlt.this.e.findSnapView(recyclerView2.getLayoutManager())) == null) {
                    return;
                }
                int viewAdapterPosition = ((RecyclerView.LayoutParams) findSnapView.getLayoutParams()).getViewAdapterPosition() % mlt.this.d.size();
                if (koq.d(mlt.this.d, viewAdapterPosition)) {
                    healthTextView.setText(((MedalInfoDesc) mlt.this.d.get(viewAdapterPosition)).acquireText());
                    healthTextView2.setText(mlb.m(((MedalInfoDesc) mlt.this.d.get(viewAdapterPosition)).acquireGainTime()));
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                super.onScrolled(recyclerView2, i, i2);
                mlt.this.ckG_(recyclerView2, i, imageView);
            }
        });
        this.e.attachToRecyclerView(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0038, code lost:
    
        if ((r7 instanceof android.widget.ImageView) != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void ckG_(androidx.recyclerview.widget.RecyclerView r11, int r12, android.widget.ImageView r13) {
        /*
            r10 = this;
            int r0 = r11.getChildCount()
            r1 = 0
            android.view.View r2 = r11.getChildAt(r1)
            int r2 = r2.getWidth()
            int r3 = r11.getWidth()
            int r3 = r3 - r2
            int r3 = r3 / 2
            r2 = r1
        L15:
            if (r2 >= r0) goto L51
            android.view.View r6 = r11.getChildAt(r2)
            boolean r4 = r6 instanceof android.widget.LinearLayout
            r5 = 0
            if (r4 == 0) goto L44
            r4 = r6
            android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
            android.view.View r7 = r4.getChildAt(r1)
            r8 = 1
            android.view.View r4 = r4.getChildAt(r8)
            boolean r8 = r7 instanceof android.widget.LinearLayout
            if (r8 == 0) goto L3b
            android.widget.LinearLayout r7 = (android.widget.LinearLayout) r7
            android.view.View r7 = r7.getChildAt(r1)
            boolean r8 = r7 instanceof android.widget.ImageView
            if (r8 == 0) goto L3b
            goto L3c
        L3b:
            r7 = r6
        L3c:
            boolean r8 = r4 instanceof android.widget.ImageView
            if (r8 == 0) goto L42
            r8 = r4
            goto L46
        L42:
            r8 = r5
            goto L46
        L44:
            r8 = r5
            r7 = r6
        L46:
            if (r8 == 0) goto L4e
            r4 = r10
            r5 = r11
            r9 = r3
            r4.ckH_(r5, r6, r7, r8, r9)
        L4e:
            int r2 = r2 + 1
            goto L15
        L51:
            r11 = 1056964608(0x3f000000, float:0.5)
            if (r12 <= 0) goto L5b
            float r12 = r10.b
            float r12 = r12 + r11
            r10.b = r12
            goto L60
        L5b:
            float r12 = r10.b
            float r12 = r12 - r11
            r10.b = r12
        L60:
            float r11 = r10.b
            r13.setRotation(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mlt.ckG_(androidx.recyclerview.widget.RecyclerView, int, android.widget.ImageView):void");
    }

    private void ckH_(RecyclerView recyclerView, View view, View view2, View view3, int i) {
        if (recyclerView == null || view == null || view2 == null || view3 == null) {
            return;
        }
        if (view.getLeft() <= i) {
            float left = view.getLeft() >= i - view.getWidth() ? ((i - view.getLeft()) * 1.0f) / view.getWidth() : 1.0f;
            float f = 1.0f - (0.4f * left);
            view2.setScaleY(f);
            view2.setScaleX(f);
            view2.setAlpha(f);
            float f2 = 1.0f - left;
            view3.setScaleX(f2);
            view3.setScaleY(f2);
            view3.setAlpha(f2);
            return;
        }
        float width = view.getLeft() <= recyclerView.getWidth() - i ? (((recyclerView.getWidth() - i) - view.getLeft()) * 1.0f) / view.getWidth() : 0.0f;
        float f3 = (0.4f * width) + 0.6f;
        view2.setScaleY(f3);
        view2.setScaleX(f3);
        view2.setAlpha(f3);
        view3.setAlpha(width);
        view3.setScaleX(width);
        view3.setScaleY(width);
    }

    public void e(List<MedalInfoDesc> list, HealthTextView healthTextView, HealthTextView healthTextView2) {
        this.d = list;
        if (koq.b(list)) {
            return;
        }
        healthTextView.setText(this.d.get(0).acquireText());
        healthTextView2.setText(mlb.m(this.d.get(0).acquireGainTime()));
    }
}
