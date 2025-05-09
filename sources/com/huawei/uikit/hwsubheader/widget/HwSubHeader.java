package com.huawei.uikit.hwsubheader.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManager;
import com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;
import defpackage.snj;

/* loaded from: classes7.dex */
public class HwSubHeader extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private SubHeaderRecyclerAdapter f10749a;
    private FrameLayout b;
    private View c;
    private HwRecyclerView d;
    private Context e;
    private RecyclerView.LayoutManager f;
    private int g;
    private View h;
    private SparseArray<View> i;
    private int j;
    private int k;
    private int l;
    private float m;
    private float n;
    private snj o;
    private boolean q;
    private HwRecyclerView.DeleteAnimatorCallback r;
    private HwSubHeaderOverScrollListener t;

    public static abstract class SubHeaderRecyclerAdapter extends RecyclerView.Adapter {
        public static final int NO_POSITION = -1;
        public static final int TYPE_HEADER = 1;
        public static final int TYPE_ITEM = 0;

        public abstract View getHeaderViewAsPos(int i, Context context);

        public abstract int getItemType(int i);

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            if (i < 0 || i >= getItemCount()) {
                return -1;
            }
            return getItemType(i);
        }
    }

    /* loaded from: classes9.dex */
    class a extends RecyclerView.OnScrollListener {
        private a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int d;
            int c;
            super.onScrolled(recyclerView, i, i2);
            if (!HwSubHeader.this.q) {
                Log.w("HwSubHeader", "no use the stick function");
                return;
            }
            if (HwSubHeader.this.f == null || !((HwSubHeader.this.f instanceof LinearLayoutManager) || (HwSubHeader.this.f instanceof HwLinearLayoutManager))) {
                Log.w("HwSubHeader", "The currently bound LayoutManager " + HwSubHeader.this.f);
                return;
            }
            if (HwSubHeader.this.f instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) HwSubHeader.this.f;
                d = linearLayoutManager.findFirstVisibleItemPosition();
                c = linearLayoutManager.findLastVisibleItemPosition();
            } else {
                HwLinearLayoutManager hwLinearLayoutManager = (HwLinearLayoutManager) HwSubHeader.this.f;
                d = hwLinearLayoutManager.d();
                c = hwLinearLayoutManager.c();
            }
            HwSubHeader.this.j = d;
            if (HwSubHeader.this.f10749a == null) {
                return;
            }
            while (d < c + 1) {
                if (HwSubHeader.this.f10749a.getItemViewType(d) == 1) {
                    HwSubHeader.this.c();
                    View findViewByPosition = HwSubHeader.this.f.findViewByPosition(d);
                    HwSubHeader hwSubHeader = HwSubHeader.this;
                    hwSubHeader.g = hwSubHeader.b.getMeasuredHeight();
                    if (findViewByPosition != null) {
                        if (findViewByPosition.getTop() > HwSubHeader.this.g || findViewByPosition.getTop() <= 0) {
                            HwSubHeader.this.n = -r2.getTop();
                            HwSubHeader.this.e();
                            return;
                        } else {
                            HwSubHeader.this.n = (-(r3.g - findViewByPosition.getTop())) - HwSubHeader.this.getTop();
                            HwSubHeader.this.e();
                            return;
                        }
                    }
                    return;
                }
                d++;
            }
        }

        /* synthetic */ a(HwSubHeader hwSubHeader, b bVar) {
            this();
        }
    }

    class b implements HwOnOverScrollListener {
        b() {
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrollEnd() {
            HwSubHeader.this.m = 0.0f;
            HwSubHeader.this.e();
            if (HwSubHeader.this.t != null) {
                HwSubHeader.this.t.onTopOverScroll(0.0f);
            }
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrollStart() {
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrolled(float f) {
            HwSubHeader.this.m = f;
            HwSubHeader.this.e();
            if (HwSubHeader.this.t != null) {
                HwSubHeader.this.t.onTopOverScroll(f);
            }
        }
    }

    class c extends RecyclerView.AdapterDataObserver {
        c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            if (HwSubHeader.this.i != null) {
                HwSubHeader.this.i.clear();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            if (HwSubHeader.this.i != null) {
                HwSubHeader.this.i.clear();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            if (HwSubHeader.this.i != null) {
                HwSubHeader.this.i.clear();
            }
        }
    }

    /* loaded from: classes9.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int positionByView;
            if (HwSubHeader.this.f != null) {
                if (((HwSubHeader.this.f instanceof LinearLayoutManager) || (HwSubHeader.this.f instanceof HwLinearLayoutManager)) && HwSubHeader.this.r != null) {
                    int childCount = HwSubHeader.this.f.getChildCount();
                    for (int firstVisibleViewIndex = HwSubHeader.this.d.getFirstVisibleViewIndex(); firstVisibleViewIndex < childCount; firstVisibleViewIndex++) {
                        View childAt = HwSubHeader.this.f.getChildAt(firstVisibleViewIndex);
                        if (childAt != null && !childAt.isDirty() && (positionByView = HwSubHeader.this.r.getPositionByView(childAt)) >= 0) {
                            if (firstVisibleViewIndex == 0) {
                                HwSubHeader.this.j = positionByView;
                            }
                            if (HwSubHeader.this.f10749a != null && HwSubHeader.this.f10749a.getItemViewType(positionByView) == 1) {
                                HwSubHeader.this.c();
                                HwSubHeader hwSubHeader = HwSubHeader.this;
                                hwSubHeader.g = hwSubHeader.b.getMeasuredHeight();
                                int top = childAt.getTop();
                                if (top > HwSubHeader.this.g || top <= 0) {
                                    return;
                                }
                                HwSubHeader.this.n = (-(r1.g - top)) - HwSubHeader.this.getTop();
                                HwSubHeader.this.e();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public HwSubHeader(Context context) {
        this(context, null);
    }

    public void a() {
        this.i.clear();
        this.b.removeAllViews();
        this.h = null;
        this.c = null;
        this.k = -1;
        this.l = -1;
        this.j = 0;
        c();
    }

    public View getCurrentHeaderView() {
        return this.c;
    }

    public HwSubHeaderOverScrollListener getOverScrollListener() {
        return this.t;
    }

    public HwRecyclerView getRecyclerView() {
        return this.d;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.o.ejb_(windowInsets);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View view = this.c;
        if (view != null) {
            ehm_(view);
        }
    }

    public void setAdapter(SubHeaderRecyclerAdapter subHeaderRecyclerAdapter) {
        if (subHeaderRecyclerAdapter == null) {
            Log.w("HwSubHeader", "the adapter is null");
            return;
        }
        this.f10749a = subHeaderRecyclerAdapter;
        this.d.setAdapter(subHeaderRecyclerAdapter);
        a();
        this.f10749a.registerAdapterDataObserver(new c());
    }

    public void setIsStick(boolean z) {
        this.q = z;
        this.b.removeAllViews();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            Log.w("HwSubHeader", "the layoutManager is null");
        } else {
            this.f = layoutManager;
            this.d.setLayoutManager(layoutManager);
        }
    }

    public void setOverScrollListener(HwSubHeaderOverScrollListener hwSubHeaderOverScrollListener) {
        this.t = hwSubHeaderOverScrollListener;
    }

    public HwSubHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void setViewLayoutDirection(View view) {
        view.setLayoutDirection(getLayoutDirection());
    }

    public HwSubHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 0;
        this.k = -1;
        this.l = -1;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = new snj(this);
        ehl_(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!this.q) {
            Log.w("HwSubHeader", "no use the stick function");
            return;
        }
        SubHeaderRecyclerAdapter subHeaderRecyclerAdapter = this.f10749a;
        if (subHeaderRecyclerAdapter != null && subHeaderRecyclerAdapter.getItemCount() > 0) {
            View view = this.i.get(this.j);
            this.c = view;
            if (view == null) {
                View headerViewAsPos = this.f10749a.getHeaderViewAsPos(this.j, this.e);
                this.c = headerViewAsPos;
                this.i.put(this.j, headerViewAsPos);
            }
            View view2 = this.c;
            if (view2 == null) {
                Log.w("HwSubHeader", "the mCurrentView is null");
                return;
            }
            if (view2 != this.h) {
                this.b.removeAllViews();
                if (this.c.getParent() == null) {
                    this.b.addView(this.c);
                    this.n = -getTop();
                    e();
                } else {
                    Log.w("HwSubHeader", "the mCurrentView has Parent");
                }
                this.h = this.c;
                return;
            }
            return;
        }
        Log.w("HwSubHeader", "adapter is null or itemCount <= 0 !");
    }

    private void ehl_(Context context, AttributeSet attributeSet) {
        this.e = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100539_res_0x7f06037b});
        this.q = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(context).inflate(R.layout.hwsubheader_layout, (ViewGroup) this, true);
        this.d = (HwRecyclerView) findViewById(R.id.hwsubheader_recyclerview);
        this.b = (FrameLayout) findViewById(R.id.hwsubheader_header);
        this.d.addOnScrollListener(new a(this, null));
        this.d.setSubHeaderDeleteUpdate(new d());
        this.i = new SparseArray<>(0);
        this.o.eiZ_(context, attributeSet);
        this.d.b(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.setTranslationY(this.m + this.n);
        }
    }

    private void ehm_(View view) {
        if (this.l == -1 || this.k == -1) {
            setViewLayoutDirection(view);
            this.k = view.getPaddingLeft();
            this.l = view.getPaddingRight();
        }
        Rect eiX_ = this.o.eiX_(this, new Rect(this.k, view.getPaddingTop(), this.l, view.getPaddingBottom()));
        if (eiX_.left == 0 && eiX_.right == 0) {
            return;
        }
        this.o.eiV_(view, new Rect(eiX_.left, view.getPaddingTop(), eiX_.right, view.getPaddingBottom()), false);
    }
}
