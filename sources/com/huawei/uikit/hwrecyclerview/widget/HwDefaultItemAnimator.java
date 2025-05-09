package com.huawei.uikit.hwrecyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.huawei.health.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class HwDefaultItemAnimator extends SimpleItemAnimator {
    private static TimeInterpolator e;

    /* renamed from: a, reason: collision with root package name */
    private List<RecyclerView.ViewHolder> f10694a = new ArrayList(10);
    private List<RecyclerView.ViewHolder> d = new ArrayList(10);
    private List<l> b = new ArrayList(10);
    private List<n> c = new ArrayList(10);
    private List<List<RecyclerView.ViewHolder>> f = new ArrayList(10);
    private List<List<l>> h = new ArrayList(10);
    private List<List<n>> j = new ArrayList(10);
    private List<RecyclerView.ViewHolder> i = new ArrayList(10);
    private List<RecyclerView.ViewHolder> g = new ArrayList(10);
    private List<RecyclerView.ViewHolder> l = new ArrayList(10);
    private List<RecyclerView.ViewHolder> o = new ArrayList(10);
    private int n = 1;
    private ItemDeleteCallBack k = null;
    private Animator m = null;

    public interface ItemDeleteCallBack {
        Animator playDisappearAnimator();
    }

    class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f10695a;
        final /* synthetic */ Animator b;
        final /* synthetic */ View d;
        final /* synthetic */ ViewPropertyAnimator e;

        a(RecyclerView.ViewHolder viewHolder, Animator animator, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f10695a = viewHolder;
            this.b = animator;
            this.e = viewPropertyAnimator;
            this.d = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.b.cancel();
            if (HwDefaultItemAnimator.this.m != null) {
                HwDefaultItemAnimator.this.m.cancel();
                HwDefaultItemAnimator.this.m = null;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwDefaultItemAnimator.this.m = null;
            this.e.setListener(null);
            this.d.setAlpha(1.0f);
            this.d.setScaleX(1.0f);
            this.d.setScaleY(1.0f);
            HwDefaultItemAnimator.this.dispatchRemoveFinished(this.f10695a);
            HwDefaultItemAnimator.this.l.remove(this.f10695a);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchRemoveStarting(this.f10695a);
        }
    }

    class b implements Runnable {
        final /* synthetic */ List b;

        b(List list) {
            this.b = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (RecyclerView.ViewHolder viewHolder : this.b) {
                if (HwDefaultItemAnimator.this.n == 3) {
                    HwDefaultItemAnimator.this.b(viewHolder, false, null);
                } else {
                    HwDefaultItemAnimator.this.b(viewHolder);
                }
            }
            this.b.clear();
            HwDefaultItemAnimator.this.f.remove(this.b);
        }
    }

    class c extends BitmapDrawable {

        /* renamed from: a, reason: collision with root package name */
        private int f10696a;
        private int b;

        /* synthetic */ c(HwDefaultItemAnimator hwDefaultItemAnimator, Resources resources, Bitmap bitmap, o oVar) {
            this(resources, bitmap);
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (canvas == null) {
                Log.e("DefaultItemAnimator", "draw: canvas is null");
                return;
            }
            canvas.save();
            canvas.translate(this.b, this.f10696a);
            super.draw(canvas);
            canvas.restore();
        }

        private c(Resources resources, Bitmap bitmap) {
            super(resources, bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(int i, int i2) {
            this.b = i;
            this.f10696a = i2;
        }
    }

    class d extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimator b;
        final /* synthetic */ View c;
        final /* synthetic */ n e;

        d(n nVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.e = nVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            HwDefaultItemAnimator.this.dispatchChangeFinished(this.e.c, true);
            HwDefaultItemAnimator.this.o.remove(this.e.c);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchChangeStarting(this.e.c, true);
        }
    }

    class e implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ View d;

        e(View view) {
            this.d = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("DefaultItemAnimator", "animatorScale: onAnimationUpdate: animation is null");
                return;
            }
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue instanceof Float) {
                float floatValue = ((Float) animatedValue).floatValue();
                this.d.setScaleX(floatValue);
                this.d.setScaleY(floatValue);
            }
        }
    }

    class f extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f10697a;
        final /* synthetic */ View c;
        final /* synthetic */ ViewPropertyAnimator e;

        f(RecyclerView.ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f10697a = viewHolder;
            this.e = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.e.setListener(null);
            this.c.setAlpha(1.0f);
            HwDefaultItemAnimator.this.dispatchRemoveFinished(this.f10697a);
            HwDefaultItemAnimator.this.l.remove(this.f10697a);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchRemoveStarting(this.f10697a);
        }
    }

    class g implements Runnable {
        final /* synthetic */ List d;

        g(List list) {
            this.d = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                HwDefaultItemAnimator.this.d((n) it.next());
            }
            this.d.clear();
            HwDefaultItemAnimator.this.j.remove(this.d);
        }
    }

    class h implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BitmapDrawable c;
        final /* synthetic */ ViewParent e;

        h(BitmapDrawable bitmapDrawable, ViewParent viewParent) {
            this.c = bitmapDrawable;
            this.e = viewParent;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("DefaultItemAnimator", "animateMoveImplPre: onAnimationUpdate: animation is null");
                return;
            }
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue instanceof Integer) {
                this.c.setAlpha(((Integer) animatedValue).intValue());
                ((ViewGroup) this.e).invalidate();
            }
        }
    }

    class i extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimator b;
        final /* synthetic */ n c;
        final /* synthetic */ View e;

        i(n nVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.c = nVar;
            this.b = viewPropertyAnimator;
            this.e = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.e.setAlpha(1.0f);
            this.e.setTranslationX(0.0f);
            this.e.setTranslationY(0.0f);
            HwDefaultItemAnimator.this.dispatchChangeFinished(this.c.b, false);
            HwDefaultItemAnimator.this.o.remove(this.c.b);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchChangeStarting(this.c.b, false);
        }
    }

    class j extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f10699a;
        final /* synthetic */ ViewPropertyAnimator b;
        final /* synthetic */ View d;

        j(RecyclerView.ViewHolder viewHolder, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f10699a = viewHolder;
            this.d = view;
            this.b = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.d.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            HwDefaultItemAnimator.this.dispatchAddFinished(this.f10699a);
            HwDefaultItemAnimator.this.i.remove(this.f10699a);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchAddStarting(this.f10699a);
        }
    }

    class k extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView.ViewHolder f10700a;
        final /* synthetic */ boolean b;
        final /* synthetic */ Animator c;
        final /* synthetic */ List d;
        final /* synthetic */ View e;
        final /* synthetic */ ViewPropertyAnimator g;

        k(RecyclerView.ViewHolder viewHolder, Animator animator, boolean z, List list, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f10700a = viewHolder;
            this.c = animator;
            this.b = z;
            this.d = list;
            this.e = view;
            this.g = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            List list;
            this.c.cancel();
            if (this.b && (list = this.d) != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((Animator) it.next()).cancel();
                }
            }
            this.e.setAlpha(1.0f);
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.g.setListener(null);
            if (this.b) {
                HwDefaultItemAnimator.this.dispatchMoveFinished(this.f10700a);
                HwDefaultItemAnimator.this.g.remove(this.f10700a);
                HwDefaultItemAnimator.this.d();
            } else {
                HwDefaultItemAnimator.this.dispatchAddFinished(this.f10700a);
                HwDefaultItemAnimator.this.i.remove(this.f10700a);
                HwDefaultItemAnimator.this.d();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchMoveStarting(this.f10700a);
        }
    }

    static class l {

        /* renamed from: a, reason: collision with root package name */
        private RecyclerView.ViewHolder f10701a;
        private int b;
        private int c;
        private int d;
        private int e;

        l(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.f10701a = viewHolder;
            this.c = i;
            this.b = i2;
            this.e = i3;
            this.d = i4;
        }
    }

    class m extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroupOverlay b;
        final /* synthetic */ BitmapDrawable d;

        m(ViewGroupOverlay viewGroupOverlay, BitmapDrawable bitmapDrawable) {
            this.b = viewGroupOverlay;
            this.d = bitmapDrawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.remove(this.d);
        }
    }

    static class n {

        /* renamed from: a, reason: collision with root package name */
        private int f10703a;
        private RecyclerView.ViewHolder b;
        private RecyclerView.ViewHolder c;
        private int d;
        private int e;
        private int h;

        /* synthetic */ n(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, o oVar) {
            this(viewHolder, viewHolder2);
        }

        public String toString() {
            return "ChangeInfo{mOldHolder=" + this.c + ", mNewHolder=" + this.b + ", mFromX=" + this.e + ", mFromY=" + this.d + ", mToX=" + this.f10703a + ", mToY=" + this.h + '}';
        }

        private n(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.c = viewHolder;
            this.b = viewHolder2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, int i2, int i3, int i4) {
            this.e = i;
            this.d = i2;
            this.f10703a = i3;
            this.h = i4;
        }
    }

    class o implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f10704a;
        final /* synthetic */ List c;

        o(List list, List list2) {
            this.c = list;
            this.f10704a = list2;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = true;
            for (Object obj : this.c) {
                if (obj instanceof l) {
                    l lVar = (l) obj;
                    if (HwDefaultItemAnimator.this.n != 1) {
                        HwDefaultItemAnimator.this.b(lVar.f10701a, true, z ? this.f10704a : null);
                    } else {
                        HwDefaultItemAnimator.this.c(lVar.f10701a, lVar.c, lVar.b, lVar.e, lVar.d);
                    }
                    z = false;
                }
            }
            this.c.clear();
            HwDefaultItemAnimator.this.h.remove(this.c);
        }
    }

    class r implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ float c;
        final /* synthetic */ View d;
        final /* synthetic */ float e;

        r(float f, float f2, View view) {
            this.c = f;
            this.e = f2;
            this.d = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("DefaultItemAnimator", "animateRemoveImplEx: animatorScale: animation is null");
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f = this.c;
            float f2 = this.e;
            this.d.setScaleX(f - ((f - 0.3f) * floatValue));
            this.d.setScaleY(f2 - ((f2 - 0.3f) * floatValue));
        }
    }

    class s extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewPropertyAnimator f10706a;
        final /* synthetic */ View b;
        final /* synthetic */ RecyclerView.ViewHolder c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        s(RecyclerView.ViewHolder viewHolder, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.c = viewHolder;
            this.e = i;
            this.b = view;
            this.d = i2;
            this.f10706a = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.e != 0) {
                this.b.setTranslationX(0.0f);
            }
            if (this.d != 0) {
                this.b.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f10706a.setListener(null);
            HwDefaultItemAnimator.this.dispatchMoveFinished(this.c);
            HwDefaultItemAnimator.this.g.remove(this.c);
            HwDefaultItemAnimator.this.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwDefaultItemAnimator.this.dispatchMoveStarting(this.c);
        }
    }

    private boolean b(boolean z, boolean z2, boolean z3, boolean z4) {
        return z || z2 || z3 || z4;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        e(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.d.add(viewHolder);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4, int i5) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i2, i3, i4, i5);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        e(viewHolder);
        viewHolder.itemView.setAlpha(alpha);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        if (viewHolder2 != null) {
            e(viewHolder2);
            viewHolder2.itemView.setTranslationX(-i6);
            viewHolder2.itemView.setTranslationY(-i7);
            viewHolder2.itemView.setAlpha(0.0f);
        }
        n nVar = new n(viewHolder, viewHolder2, null);
        nVar.a(i2, i3, i4, i5);
        this.c.add(nVar);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        View view = viewHolder.itemView;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) viewHolder.itemView.getTranslationY());
        e(viewHolder);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.b.add(new l(viewHolder, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        e(viewHolder);
        this.f10694a.add(viewHolder);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<Object> list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list);
    }

    protected void eeZ_(ViewPropertyAnimator viewPropertyAnimator) {
    }

    protected void efa_(int i2, ViewPropertyAnimator viewPropertyAnimator) {
    }

    protected void efb_(ViewPropertyAnimator viewPropertyAnimator) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (this.b.get(size).f10701a == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchMoveFinished(viewHolder);
                this.b.remove(size);
            }
        }
        a(this.c, viewHolder);
        if (this.f10694a.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchRemoveFinished(viewHolder);
        }
        if (this.d.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
        }
        for (int size2 = this.j.size() - 1; size2 >= 0; size2--) {
            List<n> list = this.j.get(size2);
            a(list, viewHolder);
            if (list.isEmpty()) {
                this.j.remove(size2);
            }
        }
        eeW_(view, viewHolder);
        eeT_(view, viewHolder);
        this.l.remove(viewHolder);
        this.i.remove(viewHolder);
        this.o.remove(viewHolder);
        this.g.remove(viewHolder);
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            l lVar = this.b.get(size);
            View view = lVar.f10701a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchMoveFinished(lVar.f10701a);
            this.b.remove(size);
        }
        for (int size2 = this.f10694a.size() - 1; size2 >= 0; size2--) {
            dispatchRemoveFinished(this.f10694a.get(size2));
            this.f10694a.remove(size2);
        }
        for (int size3 = this.d.size() - 1; size3 >= 0; size3--) {
            RecyclerView.ViewHolder viewHolder = this.d.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAddFinished(viewHolder);
            this.d.remove(size3);
        }
        for (int size4 = this.c.size() - 1; size4 >= 0; size4--) {
            e(this.c.get(size4));
        }
        this.c.clear();
        if (isRunning()) {
            a();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        if (this.d.isEmpty() && this.c.isEmpty() && this.b.isEmpty() && this.f10694a.isEmpty() && this.g.isEmpty() && this.l.isEmpty() && this.i.isEmpty() && this.o.isEmpty() && this.f.isEmpty() && this.j.isEmpty()) {
            return !this.h.isEmpty();
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        boolean z = !this.f10694a.isEmpty();
        boolean z2 = !this.b.isEmpty();
        boolean z3 = !this.c.isEmpty();
        boolean z4 = !this.d.isEmpty();
        if (b(z, z2, z4, z3)) {
            c();
            if (z2) {
                b(z);
            }
            if (z3) {
                e(z);
            }
            if (z4) {
                a(z, z2, z3);
            }
        }
    }

    private void b(boolean z) {
        ArrayList<l> arrayList = new ArrayList(this.b.size());
        arrayList.addAll(this.b);
        this.h.add(arrayList);
        this.b.clear();
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        if (this.n != 1) {
            for (l lVar : arrayList) {
                Animator eeV_ = eeV_(lVar.f10701a, lVar.c, lVar.b, lVar.e, lVar.d);
                if (eeV_ != null) {
                    arrayList2.add(eeV_);
                }
            }
        }
        o oVar = new o(arrayList, arrayList2);
        if (!z) {
            oVar.run();
            return;
        }
        View view = ((l) arrayList.get(0)).f10701a.itemView;
        if (this.n == 1) {
            ViewCompat.postOnAnimationDelayed(view, oVar, getRemoveDuration());
        } else {
            ViewCompat.postOnAnimationDelayed(view, oVar, 100L);
        }
    }

    private void c() {
        ItemDeleteCallBack itemDeleteCallBack;
        for (RecyclerView.ViewHolder viewHolder : this.f10694a) {
            if (this.n == 1) {
                c(viewHolder);
            } else {
                a(viewHolder);
            }
        }
        if (this.n == 3 && (itemDeleteCallBack = this.k) != null) {
            this.m = itemDeleteCallBack.playDisappearAnimator();
        }
        this.f10694a.clear();
    }

    private void e(RecyclerView.ViewHolder viewHolder) {
        if (e == null) {
            e = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(e);
        endAnimation(viewHolder);
    }

    private void e(boolean z) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.addAll(this.c);
        this.j.add(arrayList);
        this.c.clear();
        g gVar = new g(arrayList);
        if (z) {
            ViewCompat.postOnAnimationDelayed(((n) arrayList.get(0)).c.itemView, gVar, getRemoveDuration());
        } else {
            gVar.run();
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        float scaleX = view.getScaleX();
        float scaleY = view.getScaleY();
        ViewPropertyAnimator animate = view.animate();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.l.add(viewHolder);
        animate.setDuration(150L).alpha(0.0f).setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), R.interpolator._2131689480_res_0x7f0f0008)).setListener(a(viewHolder, ofFloat, animate)).start();
        ofFloat.setDuration(150L);
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), 17563663));
        ofFloat.addUpdateListener(new r(scaleX, scaleY, view));
        ofFloat.start();
    }

    private void a(boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.addAll(this.d);
        this.f.add(arrayList);
        this.d.clear();
        b bVar = new b(arrayList);
        if (!z && !z2 && !z3) {
            bVar.run();
            return;
        }
        long removeDuration = z ? getRemoveDuration() : 0L;
        long moveDuration = z2 ? getMoveDuration() : 0L;
        if (this.n != 1 && z) {
            removeDuration = 100;
            moveDuration = 0;
        }
        long changeDuration = z3 ? getChangeDuration() : 0L;
        ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder) arrayList.get(0)).itemView, bVar, moveDuration > changeDuration ? removeDuration + moveDuration : removeDuration + changeDuration);
    }

    private void c(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.l.add(viewHolder);
        animate.setDuration(getRemoveDuration());
        efb_(animate);
        animate.alpha(0.0f).setListener(new f(viewHolder, animate, view)).start();
    }

    private void eeX_(View view) {
        view.setAlpha(0.0f);
        view.setScaleX(0.85f);
        view.setScaleY(0.85f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    private Animator.AnimatorListener a(RecyclerView.ViewHolder viewHolder, Animator animator, ViewPropertyAnimator viewPropertyAnimator) {
        return new a(viewHolder, animator, viewPropertyAnimator, viewHolder.itemView);
    }

    private Animator eeV_(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        View view = viewHolder.itemView;
        BitmapDrawable eeS_ = eeS_(view, i4 - i2, i5 - i3);
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup)) {
            Log.e("DefaultItemAnimator", "animateMoveImplPre: viewParent is not instance of ViewGroup");
            return null;
        }
        ViewGroupOverlay overlay = ((ViewGroup) parent).getOverlay();
        if (overlay != null && eeS_ != null) {
            overlay.add(eeS_);
            view.setAlpha(0.0f);
            ValueAnimator ofInt = ValueAnimator.ofInt(255, 0);
            ofInt.setDuration(150L);
            ofInt.setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), R.interpolator._2131689480_res_0x7f0f0008));
            ofInt.addListener(new m(overlay, eeS_));
            ofInt.addUpdateListener(new h(eeS_, parent));
            ofInt.start();
            return ofInt;
        }
        Log.e("DefaultItemAnimator", "animateMoveImplPre: overlayView or drawable is null");
        return null;
    }

    private BitmapDrawable eeS_(View view, int i2, int i3) {
        int width = view.getWidth();
        int height = view.getHeight();
        o oVar = null;
        if (width > 0 && height > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            c cVar = new c(this, view.getResources(), createBitmap, oVar);
            cVar.setBounds(0, 0, width, height);
            cVar.e(view.getLeft() - i2, view.getTop() - i3);
            return cVar;
        }
        Log.w("DefaultItemAnimator", "getDrawableByView: width or height is invalid.");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return;
        }
        View view = viewHolder.itemView;
        ViewPropertyAnimator animate = view.animate();
        this.i.add(viewHolder);
        animate.setDuration(getAddDuration());
        eeZ_(animate);
        animate.alpha(1.0f).setListener(new j(viewHolder, view, animate)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        View view = viewHolder.itemView;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator animate = view.animate();
        this.g.add(viewHolder);
        animate.setDuration(getMoveDuration());
        efa_(i7, animate);
        animate.setListener(new s(viewHolder, i6, view, i7, animate)).start();
    }

    private void e(n nVar) {
        if (nVar.c != null) {
            d(nVar, nVar.c);
        }
        if (nVar.b != null) {
            d(nVar, nVar.b);
        }
    }

    private void eeW_(View view, RecyclerView.ViewHolder viewHolder) {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            List<l> list = this.h.get(size);
            int size2 = list.size() - 1;
            while (true) {
                if (size2 < 0) {
                    break;
                }
                if (list.get(size2).f10701a == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchMoveFinished(viewHolder);
                    list.remove(size2);
                    if (list.isEmpty()) {
                        this.h.remove(size);
                    }
                } else {
                    size2--;
                }
            }
        }
    }

    private void a() {
        for (int size = this.h.size() - 1; size >= 0; size--) {
            List<l> list = this.h.get(size);
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                l lVar = list.get(size2);
                View view = lVar.f10701a.itemView;
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchMoveFinished(lVar.f10701a);
                list.remove(size2);
                if (list.isEmpty()) {
                    this.h.remove(list);
                }
            }
        }
        for (int size3 = this.f.size() - 1; size3 >= 0; size3--) {
            List<RecyclerView.ViewHolder> list2 = this.f.get(size3);
            for (int size4 = list2.size() - 1; size4 >= 0; size4--) {
                RecyclerView.ViewHolder viewHolder = list2.get(size4);
                viewHolder.itemView.setAlpha(1.0f);
                dispatchAddFinished(viewHolder);
                list2.remove(size4);
                if (list2.isEmpty()) {
                    this.f.remove(list2);
                }
            }
        }
        for (int size5 = this.j.size() - 1; size5 >= 0; size5--) {
            List<n> list3 = this.j.get(size5);
            for (int size6 = list3.size() - 1; size6 >= 0; size6--) {
                e(list3.get(size6));
                if (list3.isEmpty()) {
                    this.j.remove(list3);
                }
            }
        }
        c(this.l);
        c(this.g);
        c(this.i);
        c(this.o);
        dispatchAnimationsFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RecyclerView.ViewHolder viewHolder, boolean z, List<Animator> list) {
        View view = viewHolder.itemView;
        eeX_(view);
        if (z) {
            this.g.add(viewHolder);
        } else {
            this.i.add(viewHolder);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.85f, 1.0f);
        ViewPropertyAnimator animate = view.animate();
        Animator.AnimatorListener a2 = a(viewHolder, z, animate, ofFloat, list);
        animate.alpha(1.0f).setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), R.interpolator._2131689480_res_0x7f0f0008));
        animate.setDuration(200L).setListener(a2).start();
        ofFloat.setDuration(200L);
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(view.getContext(), 17563661));
        ofFloat.addUpdateListener(new e(view));
        ofFloat.start();
    }

    private Animator.AnimatorListener a(RecyclerView.ViewHolder viewHolder, boolean z, ViewPropertyAnimator viewPropertyAnimator, Animator animator, List<Animator> list) {
        return new k(viewHolder, animator, z, list, viewHolder.itemView, viewPropertyAnimator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(n nVar) {
        RecyclerView.ViewHolder viewHolder = nVar.c;
        View view = viewHolder == null ? null : viewHolder.itemView;
        RecyclerView.ViewHolder viewHolder2 = nVar.b;
        View view2 = viewHolder2 != null ? viewHolder2.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(getChangeDuration());
            this.o.add(nVar.c);
            duration.translationX(nVar.f10703a - nVar.e);
            duration.translationY(nVar.h - nVar.d);
            duration.alpha(0.0f).setListener(new d(nVar, duration, view)).start();
        }
        if (view2 != null) {
            eeU_(view2, nVar);
        }
    }

    private void eeU_(View view, n nVar) {
        ViewPropertyAnimator animate = view.animate();
        this.o.add(nVar.b);
        animate.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new i(nVar, animate, view)).start();
    }

    private void a(List<n> list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            n nVar = list.get(size);
            if (d(nVar, viewHolder) && nVar.c == null && nVar.b == null) {
                list.remove(nVar);
            }
        }
    }

    private boolean d(n nVar, RecyclerView.ViewHolder viewHolder) {
        boolean z = false;
        if (nVar.b != viewHolder) {
            if (nVar.c != viewHolder) {
                return false;
            }
            nVar.c = null;
            z = true;
        } else {
            nVar.b = null;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchChangeFinished(viewHolder, z);
        return true;
    }

    private void eeT_(View view, RecyclerView.ViewHolder viewHolder) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            List<RecyclerView.ViewHolder> list = this.f.get(size);
            if (list.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAddFinished(viewHolder);
                if (list.isEmpty()) {
                    this.f.remove(size);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (isRunning()) {
            return;
        }
        dispatchAnimationsFinished();
    }

    private void c(List<RecyclerView.ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
        }
    }
}
