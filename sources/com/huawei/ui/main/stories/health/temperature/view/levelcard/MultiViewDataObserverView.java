package com.huawei.ui.main.stories.health.temperature.view.levelcard;

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
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class MultiViewDataObserverView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    protected List<ScrollCardParentView> f10252a;
    private int b;
    private int c;
    private b d;
    private int e;
    private int f;
    private HealthRecycleView g;
    private OnSelectListener h;
    private int i;
    private int j;
    private View m;
    private View n;

    public interface OnSelectListener {
        void onSelect(String str, boolean z);
    }

    public MultiViewDataObserverView(Context context) {
        super(context);
        this.f10252a = new ArrayList();
        this.c = 0;
        d();
    }

    private void d() {
        inflate(getContext(), R.layout.layout_blood_sugar_charteye, this);
        this.f10252a = new ArrayList();
        this.d = new b();
        View view = new View(getContext());
        this.m = view;
        view.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.m.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        LogUtil.c("MultiViewDataObserverView", "mViewDivider height ", Integer.valueOf(this.m.getMinimumHeight()), "mViewDivider width ", Integer.valueOf(this.m.getMinimumWidth()));
        View view2 = new View(getContext());
        this.n = view2;
        view2.setMinimumWidth((int) Utils.convertDpToPixel(0.5f));
        this.n.setMinimumHeight((int) Utils.convertDpToPixel(32.0f));
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.observer_view_container);
        this.g = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.g.setAdapter(this.d);
    }

    class b extends RecyclerView.Adapter {
        private b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, MultiViewDataObserverView.this.m.getMinimumHeight());
            if (i == 0) {
                View view = new View(MultiViewDataObserverView.this.getContext());
                view.setMinimumWidth(MultiViewDataObserverView.this.m.getMinimumWidth());
                view.setMinimumHeight(MultiViewDataObserverView.this.m.getMinimumHeight());
                view.setLayoutParams(layoutParams);
                return new c(view);
            }
            if (i == 2) {
                View view2 = new View(MultiViewDataObserverView.this.getContext());
                view2.setMinimumWidth(MultiViewDataObserverView.this.n.getMinimumWidth());
                view2.setMinimumHeight(MultiViewDataObserverView.this.n.getMinimumHeight());
                view2.setLayoutParams(layoutParams);
                return new e(view2);
            }
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bloodsugar_charteye_placeholder, viewGroup, false);
            MultiViewDataObserverView.this.dHm_(viewGroup, inflate);
            return new a(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int i2;
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                if (viewHolder instanceof c) {
                    viewHolder.itemView.setMinimumWidth(MultiViewDataObserverView.this.m.getMinimumWidth());
                    viewHolder.itemView.setMinimumHeight(MultiViewDataObserverView.this.m.getMinimumHeight());
                    return;
                }
                return;
            }
            if (itemViewType == 2) {
                if (viewHolder instanceof e) {
                    LogUtil.c("MultiViewDataObserverView", Integer.valueOf(i), "mViewEdge.getMinimumWidth() ", Integer.valueOf(MultiViewDataObserverView.this.n.getMinimumWidth()));
                    viewHolder.itemView.setMinimumWidth(MultiViewDataObserverView.this.n.getMinimumWidth() + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                    viewHolder.itemView.setMinimumHeight(MultiViewDataObserverView.this.n.getMinimumHeight());
                    return;
                }
                return;
            }
            if (!(viewHolder instanceof a) || (i2 = i / 2) >= MultiViewDataObserverView.this.f10252a.size()) {
                return;
            }
            ((a) viewHolder).d(MultiViewDataObserverView.this.f10252a.get(i2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MultiViewDataObserverView.this.f10252a.size() + MultiViewDataObserverView.this.f10252a.size() + 1;
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
        for (ScrollCardParentView scrollCardParentView : this.f10252a) {
            if (scrollCardParentView instanceof ScrollCardParentView) {
                scrollCardParentView.setClickable(true);
                ScrollCardParentView scrollCardParentView2 = scrollCardParentView;
                d(scrollCardParentView2, getDefaultTitleColor(), getDefaultColor(), getDefaultBackground());
                setViewListener(scrollCardParentView2);
            }
        }
        if (view instanceof ScrollCardParentView) {
            ScrollCardParentView scrollCardParentView3 = (ScrollCardParentView) view;
            d(scrollCardParentView3, getSelectColor(), getSelectColor(), getSelectBackground());
            scrollCardParentView3.setClickable(false);
            scrollCardParentView3.setOnClickListener(null);
            this.d.notifyDataSetChanged();
        }
    }

    private void d(ScrollCardParentView scrollCardParentView, int i, int i2, int i3) {
        if (scrollCardParentView == null) {
            LogUtil.h("MultiViewDataObserverView", "setViewResource cardView is null.");
            return;
        }
        scrollCardParentView.setBackgroundResource(i3);
        scrollCardParentView.setTextColor(scrollCardParentView.getTitle(), i);
        scrollCardParentView.setTextColor(scrollCardParentView.getDataTextView(), i2);
        scrollCardParentView.setTextColor(scrollCardParentView.getUnitTextView(), i2);
    }

    private void setViewListener(ScrollCardParentView scrollCardParentView) {
        scrollCardParentView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.temperature.view.levelcard.MultiViewDataObserverView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MultiViewDataObserverView.this.setSelectIndex(view);
                MultiViewDataObserverView.this.d.notifyDataSetChanged();
                if (MultiViewDataObserverView.this.h != null && (view instanceof ScrollCardParentView)) {
                    MultiViewDataObserverView.this.h.onSelect(((ScrollCardParentView) view).getType() + "", true);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dHm_(ViewGroup viewGroup, View view) {
        if (viewGroup == null || view == null) {
            LogUtil.h("MultiViewDataObserverView", "adaptView input parameters can't be null.");
        } else {
            view.setLayoutParams(new FrameLayout.LayoutParams(this.c, -2));
        }
    }

    static class a extends RecyclerView.ViewHolder {
        private LinearLayout e;

        a(View view) {
            super(view);
            this.e = null;
            this.e = (LinearLayout) view.findViewById(R.id.observer_view_item_place);
        }

        public void d(ScrollCardParentView scrollCardParentView) {
            if (scrollCardParentView != null && (scrollCardParentView.getParent() instanceof ViewGroup)) {
                ((ViewGroup) scrollCardParentView.getParent()).removeView(scrollCardParentView);
            }
            this.e.removeAllViews();
            this.e.addView(scrollCardParentView);
        }
    }

    static class c extends RecyclerView.ViewHolder {
        c(View view) {
            super(view);
        }
    }

    static class e extends RecyclerView.ViewHolder {
        e(View view) {
            super(view);
        }
    }

    public void setListener(OnSelectListener onSelectListener) {
        this.h = onSelectListener;
    }

    public void setCardWidth(int i) {
        this.c = i;
    }

    public View dHn_() {
        return this.m;
    }

    public View dHo_() {
        return this.n;
    }

    public void b(List<ScrollCardParentView> list) {
        this.f10252a.clear();
        this.f10252a.addAll(list);
        this.d.notifyDataSetChanged();
    }

    public int getDefaultColor() {
        return this.b;
    }

    public void setDefaultColor(int i) {
        this.b = i;
    }

    public int getDefaultTitleColor() {
        return this.f;
    }

    public void setDefaultTitleColor(int i) {
        this.f = i;
    }

    public int getSelectColor() {
        return this.i;
    }

    public void setSelectColor(int i) {
        this.i = i;
    }

    public int getDefaultBackground() {
        return this.e;
    }

    public void setDefaultBackground(int i) {
        this.e = i;
    }

    public int getSelectBackground() {
        return this.j;
    }

    public void setSelectBackground(int i) {
        this.j = i;
    }
}
