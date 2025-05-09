package com.huawei.ui.commonui.flowlayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.SportTagTextViewBuilder;
import defpackage.nmd;
import defpackage.nmk;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthFlowLayout extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private Context f8834a;
    private int b;
    private nmd c;
    private boolean d;
    private boolean e;
    private ViewGroup.LayoutParams f;
    private List<nmk> g;
    private SparseArray<ArrayList<nmk>> h;
    private int i;
    private AnimatorSet j;
    private ITagTextViewBuilder k;
    private int m;
    private int o;

    public interface OnGetTagListener {
        void onGetTag(int i, nmk nmkVar);
    }

    public List<nmk> getTagInfos() {
        return this.g;
    }

    public SparseArray<ArrayList<nmk>> getRowSparseArray() {
        return this.h;
    }

    public HealthFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        this.f8834a = context;
        this.m = nsn.c(context, 8.0f);
        this.o = nsn.c(context, 8.0f);
        this.i = nsn.c(context, 28.0f);
        this.b = nsn.c(context, 24.0f);
        if (nsn.r()) {
            this.f = new LinearLayout.LayoutParams(-2, -2);
            this.i = nsn.c(context, 50.0f);
        } else {
            this.f = new LinearLayout.LayoutParams(-2, this.i);
        }
        this.k = new SportTagTextViewBuilder(getContext());
    }

    public HealthFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthFlowLayout(Context context) {
        this(context, null);
    }

    public void setTextViewSpacing(int i) {
        this.m = i;
    }

    public void setVerticalSpacing(int i) {
        this.o = i;
    }

    public void setTagHeight(int i) {
        this.i = i;
    }

    public void setChildViewPadding(int i) {
        this.b = i;
    }

    public void setTagLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.f = layoutParams;
    }

    public void setTextViewBuilder(ITagTextViewBuilder iTagTextViewBuilder) {
        this.k = iTagTextViewBuilder;
    }

    private void a(List<nmk> list) {
        this.g = list;
        for (int i = 0; i < list.size(); i++) {
            a(list, i);
        }
        if (getChildCount() > list.size()) {
            removeViews(list.size(), getChildCount() - list.size());
        }
    }

    private void a(List<nmk> list, int i) {
        if (list == null || i < 0 || i >= list.size()) {
            return;
        }
        addView(this.k.build(list.get(i)), this.f);
    }

    void cAC_(View view, final int i) {
        if (this.c == null || ((nmk) getChildAt(i).getTag()).j() != 0) {
            return;
        }
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.ui.commonui.flowlayout.HealthFlowLayout.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                if (HealthFlowLayout.this.c == null) {
                    return true;
                }
                HealthFlowLayout.this.c.b(i);
                return true;
            }
        });
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        if (!this.d && this.g != null) {
            final int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.i, 1073741824);
            this.h = cAB_(this.g, size, new OnGetTagListener() { // from class: com.huawei.ui.commonui.flowlayout.HealthFlowLayout.2
                @Override // com.huawei.ui.commonui.flowlayout.HealthFlowLayout.OnGetTagListener
                public void onGetTag(int i3, nmk nmkVar) {
                    HealthFlowLayout.this.getChildAt(i3).measure(View.MeasureSpec.makeMeasureSpec(nmkVar.cAD_().width(), 1073741824), makeMeasureSpec);
                }
            });
        }
        SparseArray<ArrayList<nmk>> sparseArray = this.h;
        if (sparseArray != null && sparseArray.size() > 0) {
            ArrayList<nmk> arrayList = this.h.get(r4.size() - 1);
            if (arrayList != null && arrayList.size() > 0) {
                setMeasuredDimension(size, arrayList.get(arrayList.size() - 1).cAD_().bottom);
                return;
            } else {
                setMeasuredDimension(size, 0);
                return;
            }
        }
        setMeasuredDimension(size, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.d || this.g == null) {
            return;
        }
        for (int i5 = 0; i5 < this.g.size(); i5++) {
            nmk nmkVar = getTagInfos().get(i5);
            getChildAt(i5).layout(nmkVar.cAD_().left, nmkVar.cAD_().top, nmkVar.cAD_().right, nmkVar.cAD_().bottom);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        nmd nmdVar = this.c;
        if (nmdVar != null) {
            nmdVar.cAy_(canvas);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nmd nmdVar = this.c;
        if (nmdVar != null) {
            nmdVar.cAy_(canvas);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        nmd nmdVar = this.c;
        if (nmdVar != null) {
            return nmdVar.cAz_(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        nmd nmdVar = this.c;
        if (nmdVar != null) {
            nmdVar.cAz_(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void d() {
        this.c = new nmd(this);
    }

    private void c() {
        this.h = cAB_(this.g, getMeasuredWidth(), null);
    }

    public void c(nmk nmkVar) {
        if (this.e) {
            return;
        }
        nmkVar.b(nmkVar.c());
        c();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.g.size(); i++) {
            Rect rect = new Rect();
            nmk nmkVar2 = (nmk) getChildAt(i).getTag();
            getChildAt(i).getHitRect(rect);
            if (getChildAt(i).isShown()) {
                if (rect.left != nmkVar2.cAD_().left) {
                    arrayList.add(cAA_(nmkVar2.cAD_().left, "x", getChildAt(i), 250L));
                }
                if (rect.top != nmkVar2.cAD_().top) {
                    arrayList.add(cAA_(nmkVar2.cAD_().top, "y", getChildAt(i), 250L));
                }
            } else {
                arrayList.add(cAA_(nmkVar2.cAD_().left, "x", getChildAt(i), 0L));
                arrayList.add(cAA_(nmkVar2.cAD_().top, "y", getChildAt(i), 0L));
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.j = animatorSet;
        animatorSet.playTogether(arrayList);
        a(nmkVar);
    }

    private ObjectAnimator cAA_(int i, String str, View view, long j) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, i);
        ofFloat.setDuration(j);
        return ofFloat;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        nmd nmdVar = this.c;
        if (nmdVar != null && nmdVar.c()) {
            requestDisallowInterceptTouchEvent(true);
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void e(ArrayList<nmk> arrayList, boolean z) {
        if (this.g != null) {
            removeAllViews();
            this.g.clear();
        }
        this.d = false;
        a(arrayList);
        requestLayout();
        b(z);
    }

    private void b(boolean z) {
        d();
        if (z && this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                cAC_(getChildAt(i), i);
            }
            requestLayout();
        }
    }

    private SparseArray<ArrayList<nmk>> cAB_(List<nmk> list, int i, OnGetTagListener onGetTagListener) {
        SparseArray<ArrayList<nmk>> sparseArray = new SparseArray<>();
        int i2 = LanguageUtil.bc(this.f8834a) ? i : 0;
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            nmk nmkVar = list.get(i4);
            int textViewWidth = (int) (this.k.textViewWidth(nmkVar) + this.b);
            if (LanguageUtil.bc(this.f8834a)) {
                i2 = i2 == i ? i2 - textViewWidth : i2 - (this.m + textViewWidth);
                if (i2 < 0) {
                    i3++;
                    i2 = i - textViewWidth;
                }
                Rect cAD_ = nmkVar.cAD_();
                int i5 = this.i;
                int i6 = (this.o + i5) * i3;
                cAD_.set(i2, i6, textViewWidth + i2, i5 + i6);
            } else {
                i2 = i2 == 0 ? i2 + textViewWidth : i2 + this.m + textViewWidth;
                if (i2 > i) {
                    i3++;
                    i2 = textViewWidth;
                }
                Rect cAD_2 = nmkVar.cAD_();
                int i7 = this.i;
                int i8 = (this.o + i7) * i3;
                cAD_2.set(i2 - textViewWidth, i8, i2, i7 + i8);
            }
            ArrayList<nmk> arrayList = sparseArray.get(i3);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                sparseArray.put(i3, arrayList);
            }
            arrayList.add(list.get(i4));
            if (onGetTagListener != null) {
                onGetTagListener.onGetTag(i4, nmkVar);
            }
        }
        return sparseArray;
    }

    private void a(final nmk nmkVar) {
        this.j.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ui.commonui.flowlayout.HealthFlowLayout.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                HealthFlowLayout.this.e = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HealthFlowLayout.this.e = false;
                if (HealthFlowLayout.this.c.b() != null && (nmkVar != HealthFlowLayout.this.c.b() || nmkVar.b() != HealthFlowLayout.this.c.b().c())) {
                    HealthFlowLayout healthFlowLayout = HealthFlowLayout.this;
                    healthFlowLayout.c(healthFlowLayout.c.b());
                } else if (((nmk) HealthFlowLayout.this.g.get(HealthFlowLayout.this.g.size() - 1)).cAD_().bottom != HealthFlowLayout.this.getMeasuredHeight()) {
                    HealthFlowLayout.this.d = true;
                    HealthFlowLayout.this.requestLayout();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                HealthFlowLayout.this.e = false;
            }
        });
        this.j.start();
    }
}
