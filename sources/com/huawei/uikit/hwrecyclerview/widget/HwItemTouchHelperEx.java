package com.huawei.uikit.hwrecyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchUIUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx;
import defpackage.slq;
import defpackage.smk;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class HwItemTouchHelperEx extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    RecyclerView.ViewHolder f10707a;
    private float aa;
    private List<Integer> ab;
    private int ac;
    private List<RecyclerView.ViewHolder> ad;
    final List<View> b;
    int c;
    float d;
    float e;
    int f;
    RecyclerView g;
    List<b> h;
    VelocityTracker i;
    Callback j;
    private boolean k;
    View l;
    private long m;
    private ValueAnimator n;
    int o;
    private slq p;
    private long q;
    private boolean r;
    private boolean s;
    private float t;
    private float u;
    private float v;
    private long w;
    private final float[] x;
    private float y;
    private RecyclerView.ChildDrawingOrderCallback z;

    public static abstract class Callback {
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        static final int f = 3158064;
        private static final int g = 789516;
        private static final Interpolator h = new Interpolator() { // from class: smc
            @Override // android.animation.TimeInterpolator
            public final float getInterpolation(float f2) {
                float a2;
                a2 = HwItemTouchHelperEx.Callback.a(f2);
                return a2;
            }
        };
        private static final Interpolator i = new Interpolator() { // from class: smh
            @Override // android.animation.TimeInterpolator
            public final float getInterpolation(float f2) {
                float b;
                b = HwItemTouchHelperEx.Callback.b(f2);
                return b;
            }
        };
        private static final long j = 2000;

        /* renamed from: a, reason: collision with root package name */
        private int f10708a = -1;
        private float b = 1.0f;
        private boolean c = false;
        private int d = 0;
        private int e = 0;

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ float a(float f2) {
            return f2 * f2 * f2 * f2 * f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ float b(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }

        public static int convertToRelativeDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & g;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 << 2;
            } else {
                int i7 = i5 << 1;
                i6 |= (-789517) & i7;
                i4 = (i7 & g) << 2;
            }
            return i6 | i4;
        }

        public static ItemTouchUIUtil getDefaultUIUtil() {
            return smk.e;
        }

        public static int makeFlag(int i2, int i3) {
            return i3 << (i2 * 8);
        }

        public static int makeMovementFlags(int i2, int i3) {
            int makeFlag = makeFlag(0, i3 | i2);
            return makeFlag(2, i2) | makeFlag(1, i3) | makeFlag;
        }

        boolean c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) != 0;
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public boolean canScaleOverlappedItem(RecyclerView.ViewHolder viewHolder) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, int i2, int i3) {
            c cVar = new c();
            cVar.d = -1;
            cVar.c = i2;
            cVar.f10712a = i3;
            RecyclerView.ViewHolder a2 = a(viewHolder, list, cVar);
            cVar.e = a2;
            if (a2 != null && !this.c && canScaleOverlappedItem(a2)) {
                b(cVar.e.itemView);
            }
            return cVar.e;
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            smk.e.clearView(viewHolder.itemView);
            viewHolder.itemView.setScaleX(1.0f);
            viewHolder.itemView.setScaleY(1.0f);
        }

        public int convertToAbsoluteDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & f;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (~i5);
            if (i3 == 0) {
                i4 = i5 >> 2;
            } else {
                int i7 = i5 >> 1;
                i6 |= (-3158065) & i7;
                i4 = (i7 & f) >> 2;
            }
            return i6 | i4;
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i2, float f2, float f3) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i2 == 8 ? 200L : 250L : i2 == 8 ? itemAnimator.getMoveDuration() : itemAnimator.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.0f;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);

        public float getSwipeEscapeVelocity(float f2) {
            return f2;
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f2) {
            return f2;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i2, int i3, int i4, long j2) {
            int signum = (int) (((int) (((int) Math.signum(i3)) * a(recyclerView) * i.getInterpolation(Math.min(1.0f, Math.abs(i3) / i2)))) * h.getInterpolation(j2 <= j ? j2 / 2000.0f : 1.0f));
            return signum == 0 ? i3 > 0 ? 1 : -1 : signum;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            smk.e.onDraw(canvas, recyclerView, viewHolder.itemView, f2, f3, i2, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
            smk.e.onDrawOver(canvas, recyclerView, viewHolder.itemView, f2, f3, i2, z);
        }

        public boolean onMove(RecyclerView recyclerView, int i2, int i3) {
            return false;
        }

        public abstract boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ItemTouchHelper.ViewDropHandler) {
                ((ItemTouchHelper.ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i4, i5);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder != null) {
                smk.e.onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(RecyclerView.ViewHolder viewHolder, int i2);

        public void updateSelectedScale(float f2) {
            this.b = f2;
        }

        boolean b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & HwColorPicker.MASK_RESULT_STATE) != 0;
        }

        private void b(RecyclerView.ViewHolder viewHolder, c cVar, int i2, int i3, RecyclerView.ViewHolder viewHolder2) {
            int left;
            int abs;
            int right;
            int abs2;
            if (i3 > 0 && (right = viewHolder2.itemView.getRight() - i2) < 0 && viewHolder2.itemView.getRight() > viewHolder.itemView.getRight() && (abs2 = Math.abs(right)) > cVar.d) {
                cVar.d = abs2;
                cVar.e = viewHolder2;
            }
            if (i3 >= 0 || (left = viewHolder2.itemView.getLeft() - cVar.c) <= 0 || viewHolder2.itemView.getLeft() >= viewHolder.itemView.getLeft() || (abs = Math.abs(left)) <= cVar.d) {
                return;
            }
            cVar.d = abs;
            cVar.e = viewHolder2;
        }

        final int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        private RecyclerView.ViewHolder a(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, c cVar) {
            int i2 = cVar.c;
            int width = viewHolder.itemView.getWidth();
            int i3 = cVar.f10712a;
            int height = viewHolder.itemView.getHeight();
            int i4 = cVar.c;
            int left = viewHolder.itemView.getLeft();
            int top = cVar.f10712a - viewHolder.itemView.getTop();
            int i5 = 0;
            for (int size = list.size(); i5 < size; size = size) {
                RecyclerView.ViewHolder viewHolder2 = list.get(i5);
                if (this.c) {
                    cVar.d = (int) (viewHolder2.itemView.getHeight() * 0.1f);
                }
                b(viewHolder, cVar, i2 + width, i4 - left, viewHolder2);
                a(viewHolder, cVar, top, viewHolder2);
                a(viewHolder, cVar, i3 + height, top, viewHolder2);
                i5++;
            }
            return cVar.e;
        }

        private void b(View view) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.95f, 1.0f);
            ofFloat.setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), R.interpolator._2131689478_res_0x7f0f0006));
            ofFloat.setDuration(200L);
            ofFloat.addUpdateListener(a(view));
            ofFloat.start();
        }

        private void a(RecyclerView.ViewHolder viewHolder, c cVar, int i2, int i3, RecyclerView.ViewHolder viewHolder2) {
            int abs;
            int abs2;
            if (i3 <= 0) {
                return;
            }
            int bottom = viewHolder2.itemView.getBottom() - i2;
            if (this.c && this.d < this.e && (bottom = viewHolder2.itemView.getTop() - i2) < 0 && (abs2 = Math.abs(bottom)) > cVar.d) {
                cVar.d = abs2;
                cVar.e = viewHolder2;
            }
            if (bottom >= 0 || viewHolder2.itemView.getBottom() <= viewHolder.itemView.getBottom() || (abs = Math.abs(bottom)) <= cVar.d) {
                return;
            }
            cVar.d = abs;
            cVar.e = viewHolder2;
        }

        private void a(RecyclerView.ViewHolder viewHolder, c cVar, int i2, RecyclerView.ViewHolder viewHolder2) {
            int abs;
            int abs2;
            if (i2 >= 0) {
                return;
            }
            int top = viewHolder2.itemView.getTop() - cVar.f10712a;
            if (this.c && this.d > this.e) {
                int bottom = viewHolder2.itemView.getBottom() - cVar.f10712a;
                if (bottom <= 0 || (abs2 = Math.abs(bottom)) <= cVar.d) {
                    return;
                }
                cVar.d = abs2;
                cVar.e = viewHolder2;
                return;
            }
            if (top <= 0 || viewHolder2.itemView.getTop() >= viewHolder.itemView.getTop() || (abs = Math.abs(top)) <= cVar.d) {
                return;
            }
            cVar.d = abs;
            cVar.e = viewHolder2;
        }

        private ValueAnimator.AnimatorUpdateListener a(final View view) {
            return new ValueAnimator.AnimatorUpdateListener() { // from class: smj
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    HwItemTouchHelperEx.Callback.a(view, valueAnimator);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(View view, ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwItemTouchHelper", "getAlphaListener: onAnimationUpdate: animation is null");
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            view.setScaleX(floatValue);
            view.setScaleY(floatValue);
        }

        private int a(RecyclerView recyclerView) {
            if (this.f10708a == -1) {
                this.f10708a = recyclerView.getResources().getDimensionPixelSize(R.dimen._2131364581_res_0x7f0a0ae5);
            }
            return this.f10708a;
        }

        void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<b> list, e eVar) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                b bVar = list.get(i2);
                bVar.b();
                int save = canvas.save();
                bVar.e();
                onChildDraw(canvas, recyclerView, bVar.e, bVar.j, bVar.g, bVar.d, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                viewHolder.itemView.setScaleX(this.b);
                viewHolder.itemView.setScaleY(this.b);
                onChildDraw(canvas, recyclerView, viewHolder, eVar.f10713a, eVar.d, eVar.e, true);
                canvas.restoreToCount(save2);
            }
        }

        void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<b> list, int i2, float f2, float f3) {
            int size = list.size();
            boolean z = false;
            for (int i3 = 0; i3 < size; i3++) {
                b bVar = list.get(i3);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, bVar.e, bVar.j, bVar.g, bVar.d, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f2, f3, i2, true);
                canvas.restoreToCount(save2);
            }
            for (int i4 = size - 1; i4 >= 0; i4--) {
                b bVar2 = list.get(i4);
                boolean z2 = bVar2.l;
                if (z2 && !bVar2.h) {
                    list.remove(i4);
                } else if (!z2) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }
    }

    public static abstract class SimpleCallback extends Callback {
        private int k;
        private int l;

        public SimpleCallback(int i, int i2) {
            this.l = i;
            this.k = i2;
        }

        public int getDragDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.k;
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx.Callback
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }

        public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.l;
        }

        public void setDefaultDragDirs(int i) {
            this.k = i;
        }

        public void setDefaultSwipeDirs(int i) {
            this.l = i;
        }
    }

    class a extends b {
        final /* synthetic */ RecyclerView.ViewHolder r;
        final /* synthetic */ int s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(RecyclerView.ViewHolder viewHolder, int i, int i2, float f, float f2, float f3, float f4, int i3, RecyclerView.ViewHolder viewHolder2) {
            super(viewHolder, i, i2, f, f2, f3, f4);
            this.s = i3;
            this.r = viewHolder2;
        }

        private void c() {
            int i = this.s;
            if (i > 0) {
                HwItemTouchHelperEx.this.d(this, i);
            }
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx.b, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.n) {
                return;
            }
            if (this.s <= 0) {
                HwItemTouchHelperEx hwItemTouchHelperEx = HwItemTouchHelperEx.this;
                hwItemTouchHelperEx.j.clearView(hwItemTouchHelperEx.g, this.r);
            } else {
                HwItemTouchHelperEx.this.b.add(this.r.itemView);
                this.h = true;
                c();
            }
            HwItemTouchHelperEx hwItemTouchHelperEx2 = HwItemTouchHelperEx.this;
            View view = hwItemTouchHelperEx2.l;
            View view2 = this.r.itemView;
            if (view == view2) {
                hwItemTouchHelperEx2.efe_(view2);
            }
        }
    }

    class b implements Animator.AnimatorListener {

        /* renamed from: a, reason: collision with root package name */
        final float f10709a;
        final float b;
        final float c;
        final int d;
        final RecyclerView.ViewHolder e;
        final int f;
        float g;
        boolean h;
        final float i;
        float j;
        private final ValueAnimator p;
        private float q;
        private List<c> r;
        private slq s;
        boolean l = false;
        boolean n = false;
        boolean o = false;
        boolean m = true;

        /* renamed from: com.huawei.uikit.hwrecyclerview.widget.HwItemTouchHelperEx$b$b, reason: collision with other inner class name */
        class C0273b implements ValueAnimator.AnimatorUpdateListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ HwItemTouchHelperEx f10710a;

            C0273b(HwItemTouchHelperEx hwItemTouchHelperEx) {
                this.f10710a = hwItemTouchHelperEx;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                b.this.d(valueAnimator.getAnimatedFraction());
            }
        }

        class c {
            private float b;
            private float c;
            private float d;
            RecyclerView.ViewHolder e;
            private float f;

            c(RecyclerView.ViewHolder viewHolder) {
                this.e = viewHolder;
                this.b = viewHolder.itemView.getScaleX();
                this.c = this.e.itemView.getScaleY();
            }
        }

        b(RecyclerView.ViewHolder viewHolder, int i, int i2, float f, float f2, float f3, float f4) {
            this.b = f4;
            this.f10709a = f3;
            this.c = f2;
            this.i = f;
            this.e = viewHolder;
            this.f = i;
            this.d = i2;
            this.s = new slq(viewHolder.itemView.getContext(), viewHolder.itemView, 1, 0);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.p = ofFloat;
            ofFloat.addUpdateListener(new C0273b(HwItemTouchHelperEx.this));
            ofFloat.setTarget(viewHolder.itemView);
            ofFloat.setInterpolator(AnimationUtils.loadInterpolator(viewHolder.itemView.getContext(), android.R.anim.linear_interpolator));
            ofFloat.addListener(this);
            b(0.0f);
        }

        private boolean c(float f) {
            return f < Float.MAX_VALUE && f > -3.4028235E38f;
        }

        public void a() {
            this.e.setIsRecyclable(false);
            this.p.start();
        }

        public void b() {
            float f = this.q / 0.36363637f;
            Interpolator loadInterpolator = AnimationUtils.loadInterpolator(this.e.itemView.getContext(), R.interpolator._2131689480_res_0x7f0f0008);
            if (this.q < 0.36363637f) {
                float interpolation = loadInterpolator.getInterpolation(f);
                float f2 = this.i;
                float f3 = this.f10709a;
                if (f2 == f3) {
                    this.j = this.e.itemView.getTranslationX();
                } else {
                    this.j = f2 + ((f3 - f2) * interpolation);
                }
                float f4 = this.c;
                float f5 = this.b;
                if (f4 == f5) {
                    this.g = this.e.itemView.getTranslationY();
                } else {
                    this.g = f4 + ((f5 - f4) * interpolation);
                }
                if (this.o) {
                    for (c cVar : this.r) {
                        cVar.d = cVar.b + ((1.0f - cVar.b) * interpolation);
                        cVar.f = cVar.c + ((1.0f - cVar.c) * interpolation);
                    }
                }
            }
            if (this.q > 0.27272728f) {
                float f6 = this.i;
                float f7 = this.f10709a;
                if (f6 == f7) {
                    this.j = this.e.itemView.getTranslationX();
                } else {
                    this.j = f7;
                }
                float f8 = this.c;
                float f9 = this.b;
                if (f8 == f9) {
                    this.g = this.e.itemView.getTranslationY();
                } else {
                    this.g = f9;
                }
                if (this.o) {
                    for (c cVar2 : this.r) {
                        cVar2.d = 1.0f;
                        cVar2.f = 1.0f;
                    }
                }
                e(this.e, 0.27272728f);
            }
        }

        public void d(float f) {
            b(f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            List<c> list = this.r;
            if (list != null) {
                for (c cVar : list) {
                    cVar.e.itemView.setScaleX(1.0f);
                    cVar.e.itemView.setScaleY(1.0f);
                }
            }
            d(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.e.setIsRecyclable(true);
            }
            RecyclerView.ViewHolder viewHolder = this.e;
            if (viewHolder != null) {
                viewHolder.itemView.setScaleX(1.0f);
                this.e.itemView.setScaleY(1.0f);
                this.e.itemView.setTranslationY(0.0f);
            }
            this.l = true;
            HwItemTouchHelperEx.this.s = false;
            HwItemTouchHelperEx.this.r = false;
            HwItemTouchHelperEx.this.k = false;
            HwItemTouchHelperEx hwItemTouchHelperEx = HwItemTouchHelperEx.this;
            hwItemTouchHelperEx.m = hwItemTouchHelperEx.q;
            if (this.m) {
                this.s.a(true);
                this.s.a(false);
                this.m = false;
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (HwItemTouchHelperEx.this.r) {
                for (c cVar : this.r) {
                    cVar.e.itemView.setScaleX(1.0f);
                    cVar.e.itemView.setScaleY(1.0f);
                }
            }
            if (!this.o || HwItemTouchHelperEx.this.r) {
                return;
            }
            for (c cVar2 : this.r) {
                if (cVar2.b < 1.0f) {
                    cVar2.e.itemView.setScaleX(cVar2.d);
                    cVar2.e.itemView.setScaleY(cVar2.f);
                }
            }
        }

        private void b(float f) {
            this.q = f;
        }

        public void d(long j) {
            this.p.setDuration(j);
        }

        public void d() {
            this.p.cancel();
        }

        private void e(RecyclerView.ViewHolder viewHolder, float f) {
            if (this.d != 2) {
                return;
            }
            HwItemTouchHelperEx.this.t = 1.05f - (AnimationUtils.loadInterpolator(this.e.itemView.getContext(), R.interpolator._2131689482_res_0x7f0f000a).getInterpolation((this.q - f) / (1.0f - f)) * 0.05f);
            if (this.m) {
                this.s.a(true);
                this.s.a(false);
                this.m = false;
            }
            if (viewHolder == null || !c(HwItemTouchHelperEx.this.t)) {
                return;
            }
            viewHolder.itemView.setScaleX(HwItemTouchHelperEx.this.t);
            viewHolder.itemView.setScaleY(HwItemTouchHelperEx.this.t);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(boolean z) {
            this.o = z;
            if (z) {
                ArrayList arrayList = new ArrayList();
                this.r = arrayList;
                arrayList.add(new c(this.e));
                Iterator it = HwItemTouchHelperEx.this.c(this.e).iterator();
                while (it.hasNext()) {
                    this.r.add(new c((RecyclerView.ViewHolder) it.next()));
                }
                HwItemTouchHelperEx.this.ad.clear();
                HwItemTouchHelperEx.this.ab.clear();
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        int f10712a;
        int c;
        int d;
        RecyclerView.ViewHolder e;

        c() {
        }
    }

    class d implements Runnable {
        final /* synthetic */ int b;
        final /* synthetic */ b d;

        d(b bVar, int i) {
            this.d = bVar;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = HwItemTouchHelperEx.this.g;
            if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            b bVar = this.d;
            if (bVar.n || bVar.e.getAdapterPosition() == -1) {
                return;
            }
            RecyclerView.ItemAnimator itemAnimator = HwItemTouchHelperEx.this.g.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !HwItemTouchHelperEx.this.e()) {
                HwItemTouchHelperEx.this.j.onSwiped(this.d.e, this.b);
            } else {
                HwItemTouchHelperEx.this.g.post(this);
            }
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        float f10713a;
        float d;
        int e;

        e() {
        }
    }

    private int a(int i, int i2) {
        if (i == 2) {
            return 8;
        }
        return i2 > 0 ? 2 : 4;
    }

    private void a() {
    }

    private void d() {
        VelocityTracker velocityTracker = this.i;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.i = null;
        }
    }

    boolean e() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            if (!this.h.get(i).l) {
                return true;
            }
        }
        return false;
    }

    public void eff_(View view, Callback callback, slq slqVar) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(View view) {
        efe_(view);
        RecyclerView.ViewHolder childViewHolder = this.g.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.f10707a;
        if (viewHolder != null && childViewHolder == viewHolder) {
            this.r = true;
            b((RecyclerView.ViewHolder) null, 0);
        } else {
            d(childViewHolder, false);
            if (this.b.remove(childViewHolder.itemView)) {
                this.j.clearView(this.g, childViewHolder);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f2;
        this.o = -1;
        if (this.f10707a != null) {
            e(this.x);
            float[] fArr = this.x;
            f = fArr[0];
            f2 = fArr[1];
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        e eVar = new e();
        eVar.e = this.ac;
        eVar.f10713a = f;
        eVar.d = f2;
        this.j.a(canvas, recyclerView, this.f10707a, this.h, eVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f2;
        if (this.f10707a != null) {
            e(this.x);
            float[] fArr = this.x;
            float f3 = fArr[0];
            f2 = fArr[1];
            f = f3;
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        this.j.a(canvas, recyclerView, this.f10707a, this.h, this.ac, f, f2);
    }

    private void c(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2) {
            if (viewHolder != null) {
                slq slqVar = new slq(viewHolder.itemView.getContext(), viewHolder.itemView, 1, 0);
                this.p = slqVar;
                eff_(viewHolder.itemView, this.j, slqVar);
                this.l = viewHolder.itemView;
                a();
                return;
            }
            throw new IllegalArgumentException("ViewHolder must be passed when dragging");
        }
    }

    private void e(float[] fArr) {
        if ((this.f & 12) != 0) {
            fArr[0] = (this.u + this.d) - this.f10707a.itemView.getLeft();
        } else {
            fArr[0] = this.f10707a.itemView.getTranslationX();
        }
        if ((this.f & 3) != 0) {
            fArr[1] = (this.v + this.e) - this.f10707a.itemView.getTop();
        } else {
            fArr[1] = this.f10707a.itemView.getTranslationY();
        }
    }

    private boolean c(int i, boolean z) {
        float signum;
        RecyclerView.ViewHolder viewHolder = this.f10707a;
        if (viewHolder != null) {
            if (viewHolder.itemView.getParent() != null) {
                int a2 = i == 2 ? 0 : a(viewHolder);
                d();
                float f = 0.0f;
                if (a2 == 1 || a2 == 2) {
                    signum = Math.signum(this.e) * this.g.getHeight();
                } else if (a2 == 4 || a2 == 8 || a2 == 16 || a2 == 32) {
                    signum = 0.0f;
                    f = Math.signum(this.d) * this.g.getWidth();
                } else {
                    signum = 0.0f;
                }
                int a3 = a(i, a2);
                e(this.x);
                b d2 = d(i, f, signum, a3);
                d2.d(this.m + 150);
                if (i == 2) {
                    d2.d(true);
                }
                this.h.add(d2);
                d2.a();
                z = true;
            } else {
                efe_(viewHolder.itemView);
                this.j.clearView(this.g, viewHolder);
            }
            this.f10707a = null;
        }
        return z;
    }

    void b(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == this.f10707a && i == this.ac) {
            return;
        }
        ValueAnimator valueAnimator = this.n;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            return;
        }
        this.w = Long.MIN_VALUE;
        int i2 = this.ac;
        d(viewHolder, true);
        this.ac = i;
        c(viewHolder, i);
        boolean c2 = c(i2, false);
        if (viewHolder != null) {
            Log.d("HwItemTouchHelper", "selected is not empty.");
            this.f = (this.j.a(this.g, viewHolder) & ((1 << ((i * 8) + 8)) - 1)) >> (this.ac * 8);
            this.u = viewHolder.itemView.getLeft();
            this.v = viewHolder.itemView.getTop();
            this.f10707a = viewHolder;
            if (i == 2 && !this.k) {
                viewHolder.itemView.performHapticFeedback(0);
            }
        }
        ViewParent parent = this.g.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(this.f10707a != null);
        }
        if (!c2) {
            this.g.getLayoutManager().requestSimpleAnimationsInNextLayout();
        }
        this.j.onSelectedChanged(this.f10707a, this.ac);
        this.g.invalidate();
    }

    private int a(RecyclerView.ViewHolder viewHolder) {
        if (this.ac == 2) {
            return 0;
        }
        int movementFlags = this.j.getMovementFlags(this.g, viewHolder);
        int convertToAbsoluteDirection = (this.j.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.g)) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i = (movementFlags & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (Math.abs(this.d) > Math.abs(this.e)) {
            int e2 = e(viewHolder, convertToAbsoluteDirection);
            if (e2 > 0) {
                return (i & e2) == 0 ? Callback.convertToRelativeDirection(e2, ViewCompat.getLayoutDirection(this.g)) : e2;
            }
            int a2 = a(viewHolder, convertToAbsoluteDirection);
            if (a2 > 0) {
                return a2;
            }
        } else {
            int a3 = a(viewHolder, convertToAbsoluteDirection);
            if (a3 > 0) {
                return a3;
            }
            int e3 = e(viewHolder, convertToAbsoluteDirection);
            if (e3 > 0) {
                return (i & e3) == 0 ? Callback.convertToRelativeDirection(e3, ViewCompat.getLayoutDirection(this.g)) : e3;
            }
        }
        return 0;
    }

    private int e(RecyclerView.ViewHolder viewHolder, int i) {
        if ((i & 12) == 0) {
            return 0;
        }
        int i2 = ((int) this.d) > 0 ? 8 : 4;
        VelocityTracker velocityTracker = this.i;
        if (velocityTracker != null && this.c > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.j.getSwipeVelocityThreshold(this.aa));
            float xVelocity = this.i.getXVelocity(this.c);
            float yVelocity = this.i.getYVelocity(this.c);
            int i3 = xVelocity > 0.0f ? 8 : 4;
            float abs = Math.abs(xVelocity);
            if ((i3 & i) != 0 && i2 == i3 && abs >= this.j.getSwipeEscapeVelocity(this.y) && abs > Math.abs(yVelocity)) {
                return i3;
            }
        }
        float width = this.g.getWidth();
        float swipeThreshold = this.j.getSwipeThreshold(viewHolder);
        if ((i & i2) == 0 || Math.abs(this.d) <= width * swipeThreshold) {
            return 0;
        }
        return i2;
    }

    private b d(int i, float f, float f2, int i2) {
        RecyclerView.ViewHolder viewHolder = this.f10707a;
        float[] fArr = this.x;
        return new a(viewHolder, i2, i, fArr[0], fArr[1], f, f2, i == 2 ? 0 : a(viewHolder), viewHolder);
    }

    private int a(RecyclerView.ViewHolder viewHolder, int i) {
        if ((i & 3) == 0) {
            return 0;
        }
        int i2 = this.e > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.i;
        if (velocityTracker != null && this.c > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.j.getSwipeVelocityThreshold(this.aa));
            float xVelocity = this.i.getXVelocity(this.c);
            float yVelocity = this.i.getYVelocity(this.c);
            int i3 = yVelocity > 0.0f ? 2 : 1;
            float abs = Math.abs(yVelocity);
            if ((i3 & i) != 0 && i3 == i2 && abs >= this.j.getSwipeEscapeVelocity(this.y) && abs > Math.abs(xVelocity)) {
                return i3;
            }
        }
        float height = this.g.getHeight();
        float swipeThreshold = this.j.getSwipeThreshold(viewHolder);
        if ((i & i2) == 0 || Math.abs(this.e) <= height * swipeThreshold) {
            return 0;
        }
        return i2;
    }

    void d(b bVar, int i) {
        this.g.post(new d(bVar, i));
    }

    void efe_(View view) {
        if (view == this.l) {
            this.l = null;
            Callback callback = this.j;
            if (callback != null) {
                callback.updateSelectedScale(1.0f);
            }
            if (this.z != null) {
                this.g.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<RecyclerView.ViewHolder> c(RecyclerView.ViewHolder viewHolder) {
        List<RecyclerView.ViewHolder> list = this.ad;
        if (list == null) {
            this.ad = new ArrayList();
            this.ab = new ArrayList();
        } else {
            list.clear();
            this.ab.clear();
        }
        int boundingBoxMargin = this.j.getBoundingBoxMargin();
        int round = Math.round(this.u + this.d) - boundingBoxMargin;
        int round2 = Math.round(this.v + this.e) - boundingBoxMargin;
        int i = boundingBoxMargin * 2;
        int width = viewHolder.itemView.getWidth() + round + i;
        int height = viewHolder.itemView.getHeight() + round2 + i;
        int i2 = (round + width) / 2;
        int i3 = (round2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.g.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = layoutManager.getChildAt(i4);
            if (childAt != viewHolder.itemView) {
                if (childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                    efd_(i2, i3, childAt);
                } else {
                    childAt.setScaleX(1.0f);
                    childAt.setScaleY(1.0f);
                }
            }
        }
        return this.ad;
    }

    private void efd_(int i, int i2, View view) {
        RecyclerView.ViewHolder childViewHolder = this.g.getChildViewHolder(view);
        if (this.j.canDropOver(this.g, this.f10707a, childViewHolder)) {
            int abs = Math.abs(i - ((view.getLeft() + view.getRight()) / 2));
            int abs2 = Math.abs(i2 - ((view.getTop() + view.getBottom()) / 2));
            int i3 = (abs * abs) + (abs2 * abs2);
            int size = this.ad.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size && i3 > this.ab.get(i5).intValue(); i5++) {
                i4++;
            }
            this.ad.add(i4, childViewHolder);
            this.ab.add(i4, Integer.valueOf(i3));
        }
    }

    void d(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            b bVar = this.h.get(size);
            if (bVar.e == viewHolder) {
                bVar.n |= z;
                if (!bVar.l) {
                    bVar.d();
                }
                this.h.remove(size);
                return;
            }
        }
    }
}
