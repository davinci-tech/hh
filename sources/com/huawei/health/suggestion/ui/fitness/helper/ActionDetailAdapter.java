package com.huawei.health.suggestion.ui.fitness.helper;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.view.ActionDetailView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class ActionDetailAdapter extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ActionDetailViewClickEvent f3153a;
    private SparseArray<ActionDetailView> b;
    private List c;
    private ActionDetailView e;
    private Motion f;
    private String g;
    private boolean j = true;
    private boolean i = false;
    private String h = "";
    private int d = -1;

    public interface ActionDetailViewClickEvent {
        void onCloseImageClick();

        void onDownLoadClick(int i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ActionDetailAdapter(List list) {
        this.c = list;
        this.b = new SparseArray<>(list.size());
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return Math.min(this.c.size(), 1);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.b == null || viewGroup.getContext() == null) {
            return viewGroup;
        }
        ActionDetailView actionDetailView = this.b.get(i);
        this.e = actionDetailView;
        if (actionDetailView == null) {
            this.e = new ActionDetailView(viewGroup.getContext());
        }
        if (d(this.c, i)) {
            return this.e;
        }
        this.f = (Motion) this.c.get(i);
        if (this.i) {
            this.e.setLongVideoUrl(this.h);
            this.e.b(null, 2, this.f);
        } else if (i == 0) {
            this.e.setFitnessType(this.g);
            this.e.setShowMedia(this.j);
            this.e.b(null, 1, this.f);
        }
        this.b.put(i, this.e);
        ActionDetailView actionDetailView2 = this.e;
        if (actionDetailView2 != null && this.f3153a != null) {
            actionDetailView2.setOnDownLoadViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.ActionDetailAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ActionDetailAdapter.this.f3153a.onDownLoadClick(ActionDetailAdapter.this.d);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.e.setOnCloseImageClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.ActionDetailAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ActionDetailAdapter.this.f3153a.onCloseImageClick();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        viewGroup.addView(this.e);
        return this.e;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ActionDetailView actionDetailView;
        SparseArray<ActionDetailView> sparseArray = this.b;
        if (sparseArray == null || i >= sparseArray.size() || (actionDetailView = this.b.get(i)) == null) {
            return;
        }
        viewGroup.removeView(actionDetailView);
        actionDetailView.setSurfaceTextureListener(null);
        d();
        this.b.clear();
    }

    private boolean d(List list, int i) {
        return koq.b(list, i) || !(list.get(i) instanceof Motion);
    }

    public void d() {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.d();
            this.e.setSurfaceTextureListener(null);
            this.e = null;
        }
    }

    public void c(boolean z) {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.setShowMedia(z);
        }
        this.j = z;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void a(String str) {
        this.h = str;
    }

    public void e(String str) {
        this.g = str;
    }

    public void a(ActionDetailViewClickEvent actionDetailViewClickEvent) {
        this.f3153a = actionDetailViewClickEvent;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a() {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.e();
        }
    }

    public void c() {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.c();
        }
    }

    public ActionDetailView b() {
        return this.e;
    }

    public void b(String str) {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.setTvDownLoadingProgress(str);
        }
    }

    public void d(String str) {
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.setAudioSize(str);
        }
    }
}
