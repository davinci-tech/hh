package com.huawei.ui.main.stories.health.views.charteye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.health.views.charteye.MultiViewDataObserverView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class MultiViewDataObserverView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f10269a;
    protected List<ScrollChartParentView> b;
    private int c;
    private a d;
    private int e;
    private OnSelectListener f;
    private int g;
    private HealthRecycleView h;
    private boolean i;
    private int j;
    private View k;
    private View l;
    private String o;

    public interface OnSelectListener {
        void onSelect(String str);
    }

    public MultiViewDataObserverView(Context context, boolean z) {
        super(context);
        this.b = new ArrayList();
        this.e = 0;
        this.i = z;
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.layout_blood_sugar_charteye, this);
        this.b = new ArrayList();
        this.d = new a();
        View view = new View(getContext());
        this.l = view;
        view.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.l.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        View view2 = new View(getContext());
        this.k = view2;
        view2.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.k.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        this.h = (HealthRecycleView) findViewById(R.id.observer_view_container);
        LogUtil.c("MultiViewDataObserverView", "mRecyclerView is scroll ", Boolean.valueOf(this.i));
        if (!this.i) {
            this.h.setNestedScrollingEnabled(false);
            this.h.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            this.h.setIsScroll(false);
        } else {
            this.h.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        }
        this.h.setAdapter(this.d);
    }

    class a extends RecyclerView.Adapter {
        private a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, MultiViewDataObserverView.this.l.getMinimumHeight());
            if (i == 0) {
                View view = new View(MultiViewDataObserverView.this.getContext());
                view.setMinimumWidth(MultiViewDataObserverView.this.l.getMinimumWidth());
                view.setMinimumHeight(MultiViewDataObserverView.this.l.getMinimumHeight());
                view.setLayoutParams(layoutParams);
                return new d(view);
            }
            if (i == 2) {
                View view2 = new View(MultiViewDataObserverView.this.getContext());
                view2.setMinimumWidth(MultiViewDataObserverView.this.k.getMinimumWidth());
                view2.setMinimumHeight(MultiViewDataObserverView.this.k.getMinimumHeight());
                view2.setLayoutParams(layoutParams);
                return new b(view2);
            }
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bloodsugar_charteye_placeholder, viewGroup, false);
            MultiViewDataObserverView.this.dIG_(viewGroup, inflate);
            return new e(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int i2;
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                if (viewHolder instanceof d) {
                    viewHolder.itemView.setMinimumWidth(MultiViewDataObserverView.this.l.getMinimumWidth());
                    viewHolder.itemView.setMinimumHeight(MultiViewDataObserverView.this.l.getMinimumHeight());
                    return;
                }
                return;
            }
            if (itemViewType == 2) {
                if (viewHolder instanceof b) {
                    viewHolder.itemView.setMinimumWidth(MultiViewDataObserverView.this.k.getMinimumWidth());
                    viewHolder.itemView.setMinimumHeight(MultiViewDataObserverView.this.k.getMinimumHeight());
                    return;
                }
                return;
            }
            if (!(viewHolder instanceof e) || (i2 = i / 2) >= MultiViewDataObserverView.this.b.size()) {
                return;
            }
            ((e) viewHolder).a(MultiViewDataObserverView.this.b.get(i2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MultiViewDataObserverView.this.b.size() + MultiViewDataObserverView.this.b.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            if (i == 0 || i == getItemCount() - 1) {
                return 2;
            }
            return (i < 0 || i % 2 != 1) ? 0 : 1;
        }
    }

    public void setSelectIndex(View view) {
        for (ScrollChartParentView scrollChartParentView : this.b) {
            if (scrollChartParentView instanceof ScrollChartParentView) {
                scrollChartParentView.setClickable(true);
                ScrollChartParentView scrollChartParentView2 = scrollChartParentView;
                c(scrollChartParentView2, getDefaultColor(), getDefaultBackground());
                setViewListener(scrollChartParentView2);
                if ("BLOOD_SUGAR_FINGER_TIP".equals(scrollChartParentView2.getType()) && scrollChartParentView2.getArrowView() != null) {
                    scrollChartParentView2.getArrowView().setImageResource(R.drawable._2131427651_res_0x7f0b0143);
                }
            }
        }
        if (view instanceof ScrollChartParentView) {
            ScrollChartParentView scrollChartParentView3 = (ScrollChartParentView) view;
            LogUtil.c("MultiViewDataObserverView", "setSelectIndex ", scrollChartParentView3.getType());
            setSelectedType(scrollChartParentView3.getType());
            c(scrollChartParentView3, getSelectColor(), getSelectBackground());
            scrollChartParentView3.setClickable(false);
            scrollChartParentView3.setOnClickListener(null);
            e(scrollChartParentView3);
            if ("BLOOD_SUGAR_FINGER_TIP".equals(scrollChartParentView3.getType()) && scrollChartParentView3.getArrowView() != null) {
                scrollChartParentView3.getArrowView().setImageResource(R.drawable._2131427650_res_0x7f0b0142);
            }
            this.d.notifyDataSetChanged();
        }
    }

    private void setViewListener(ScrollChartParentView scrollChartParentView) {
        scrollChartParentView.setOnClickListener(new View.OnClickListener() { // from class: qsx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiViewDataObserverView.this.dIJ_(view);
            }
        });
    }

    public /* synthetic */ void dIJ_(View view) {
        setSelectIndex(view);
        this.d.notifyDataSetChanged();
        OnSelectListener onSelectListener = this.f;
        if (onSelectListener != null && (view instanceof ScrollChartParentView)) {
            onSelectListener.onSelect(((ScrollChartParentView) view).getType() + "");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(ScrollChartParentView scrollChartParentView) {
        if (this.f != null) {
            LogUtil.c("MultiViewDataObserverView", "refreshSubView ", scrollChartParentView.getType());
            this.f.onSelect(scrollChartParentView.getType() + "");
        }
    }

    private void c(ScrollChartParentView scrollChartParentView, int i, int i2) {
        if (scrollChartParentView == null) {
            LogUtil.h("MultiViewDataObserverView", "setViewResource cardView is null.");
            return;
        }
        scrollChartParentView.setBackgroundResource(i2);
        scrollChartParentView.setTextColor(scrollChartParentView.getTitle(), i);
        scrollChartParentView.setTextColor(scrollChartParentView.getDataView(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dIG_(ViewGroup viewGroup, View view) {
        if (viewGroup == null || view == null) {
            LogUtil.h("MultiViewDataObserverView", "adaptView input parameters can't be null.");
        } else {
            view.setLayoutParams(new FrameLayout.LayoutParams(this.e, -2));
        }
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f10271a;

        e(View view) {
            super(view);
            this.f10271a = null;
            this.f10271a = (LinearLayout) view.findViewById(R.id.observer_view_item_place);
        }

        public void a(ScrollChartParentView scrollChartParentView) {
            if (scrollChartParentView != null && (scrollChartParentView.getParent() instanceof ViewGroup)) {
                ((ViewGroup) scrollChartParentView.getParent()).removeView(scrollChartParentView);
            }
            this.f10271a.removeAllViews();
            this.f10271a.addView(scrollChartParentView);
        }
    }

    static class d extends RecyclerView.ViewHolder {
        d(View view) {
            super(view);
        }
    }

    static class b extends RecyclerView.ViewHolder {
        b(View view) {
            super(view);
        }
    }

    public void setListener(OnSelectListener onSelectListener) {
        this.f = onSelectListener;
    }

    public void setCardWidth(int i) {
        this.e = i;
    }

    public View dIH_() {
        return this.l;
    }

    public View dII_() {
        return this.k;
    }

    public void b(List<ScrollChartParentView> list) {
        this.b.clear();
        this.b.addAll(list);
        this.d.notifyDataSetChanged();
    }

    public int getDefaultColor() {
        return this.f10269a;
    }

    public void setDefaultColor(int i) {
        this.f10269a = i;
    }

    public int getSelectColor() {
        return this.g;
    }

    public void setSelectColor(int i) {
        this.g = i;
    }

    public int getDefaultBackground() {
        return this.c;
    }

    public void setDefaultBackground(int i) {
        this.c = i;
    }

    public int getSelectBackground() {
        return this.j;
    }

    public void setSelectBackground(int i) {
        this.j = i;
    }

    public String getSelectedType() {
        return this.o;
    }

    public void setSelectedType(String str) {
        this.o = str;
    }
}
