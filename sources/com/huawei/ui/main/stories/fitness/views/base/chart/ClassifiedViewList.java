package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.chartanimation.TweenAnimatiionMgr;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ClassifiedViewList extends LinearLayout {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private d f9932a;
    private ClassifiedButtonList b;
    private ObserveredClassifiedView c;
    private OnClassifiedViewChangeListener d;
    private HealthViewPager f;
    private TweenAnimatiionMgr g;
    private HealthPagerAdapter h;
    private List<ClassifiedView> i;
    private IOnDataShowListener j;

    public interface ClassifiedView {
        String acquireDataLayerIndex();

        void enableObserverView(ScrollChartObserverView scrollChartObserverView);

        String getClassStr();

        DataInfos getStepDataType();

        void init(IChartLayerHolder iChartLayerHolder);

        void loadTweenAnimatiionMgr(TweenAnimatiionMgr tweenAnimatiionMgr);

        ObserveredClassifiedView onCreateView();

        void onSelected();

        void selectDataLayerId(String str);

        void setOnDataShowListener(IOnDataShowListener iOnDataShowListener);
    }

    public interface IChartLayerHolderAdapter {
        IChartLayerHolder acquireAdapter(ClassifiedView classifiedView);
    }

    public interface OnClassifiedViewChangeListener {
        void onClassifiedViewSelected(View view, int i);
    }

    public ClassifiedViewList(Context context) {
        super(context);
        this.i = new ArrayList();
        this.f9932a = null;
        this.g = null;
        this.j = null;
        b();
        h();
    }

    public ClassifiedViewList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new ArrayList();
        this.f9932a = null;
        this.g = null;
        this.j = null;
        b();
        h();
    }

    public ClassifiedViewList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new ArrayList();
        this.f9932a = null;
        this.g = null;
        this.j = null;
        b();
        h();
    }

    public ClassifiedViewList(Context context, ClassifiedButtonList classifiedButtonList, HealthViewPager healthViewPager) {
        super(context);
        this.i = new ArrayList();
        this.f9932a = null;
        this.g = null;
        this.j = null;
        this.b = classifiedButtonList;
        this.f = healthViewPager;
        h();
    }

    public Bitmap dvv_() {
        ObserveredClassifiedView observeredClassifiedView = this.c;
        if (observeredClassifiedView == null) {
            return null;
        }
        return observeredClassifiedView.drawChart();
    }

    public IFocusObserverItem c() {
        ObserveredClassifiedView observeredClassifiedView = this.c;
        if (observeredClassifiedView == null) {
            return null;
        }
        ScrollChartObserverView acquireScrollChartObserverView = observeredClassifiedView.acquireScrollChartObserverView();
        if (acquireScrollChartObserverView instanceof CardContainerView) {
            return acquireScrollChartObserverView.acquireFocusItem();
        }
        throw new RuntimeException("current not support " + acquireScrollChartObserverView + " for focus view");
    }

    private void b() {
        inflate(getContext(), R.layout.view_classified_list, this);
        this.b = (ClassifiedButtonList) findViewById(R.id.classified_button_list);
        this.f = (HealthViewPager) findViewById(R.id.classified_view_place);
    }

    private void h() {
        e eVar = new e();
        this.h = eVar;
        this.f.setAdapter(eVar);
        this.f.setIsAutoCurrentItemHeight(true);
        this.f.setIsScroll(false);
        this.f.setOffscreenPageLimit(4);
        TweenAnimatiionMgr tweenAnimatiionMgr = new TweenAnimatiionMgr();
        this.g = tweenAnimatiionMgr;
        tweenAnimatiionMgr.e(TweenAnimatiionMgr.UserLevelView.CHART_DAY);
        this.g.d(new TweenAnimatiionMgr.OnSwitchLevelView() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.3
            @Override // com.huawei.ui.commonui.chartanimation.TweenAnimatiionMgr.OnSwitchLevelView
            public void onNotifySwitchStatus(TweenAnimatiionMgr.UserLevelView userLevelView, TweenAnimatiionMgr.UserLevelView userLevelView2, float f) {
                if (f == 0.0f) {
                    ClassifiedViewList.this.b.e(ClassifiedViewList.this.e(userLevelView2));
                    ClassifiedViewList.this.f.setCurrentItem(ClassifiedViewList.this.e(userLevelView2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(TweenAnimatiionMgr.UserLevelView userLevelView) {
        for (int i = 0; i < this.i.size(); i++) {
            if (TweenAnimatiionMgr.UserLevelView.query(this.i.get(i).getStepDataType()) == userLevelView) {
                return i;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TweenAnimatiionMgr.UserLevelView e(int i) {
        return TweenAnimatiionMgr.UserLevelView.query(this.i.get(i).getStepDataType());
    }

    public void a(List<ClassifiedView> list, IChartLayerHolder iChartLayerHolder, ClassifiedView classifiedView) {
        d(list, iChartLayerHolder);
        this.g.e(TweenAnimatiionMgr.UserLevelView.query(classifiedView.getStepDataType()));
    }

    public void d(List<ClassifiedView> list, IChartLayerHolder iChartLayerHolder) {
        this.f.removeAllViews();
        this.f.clearOnPageChangeListeners();
        this.i.clear();
        this.i.addAll(list);
        d dVar = this.f9932a;
        if (dVar != null) {
            dVar.c();
        }
        if (this.f9932a == null) {
            this.f9932a = new d();
        }
        this.f9932a.d();
        if (this.i.size() > 0) {
            this.f9932a.e(this.i.get(0).getStepDataType());
            if (this.i.get(0) instanceof ObserveredClassifiedView) {
                this.c = (ObserveredClassifiedView) this.i.get(0);
            }
        }
        this.b.a(this.i, new ClassifiedButtonList.OnSwitchClassifies() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.1
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList.OnSwitchClassifies
            public void onSwitchClassifies(ClassifiedView classifiedView) {
                ClassifiedViewList.this.f.setCurrentItem(ClassifiedViewList.this.i.indexOf(classifiedView));
                TweenAnimatiionMgr tweenAnimatiionMgr = ClassifiedViewList.this.g;
                ClassifiedViewList classifiedViewList = ClassifiedViewList.this;
                tweenAnimatiionMgr.e(classifiedViewList.e(classifiedViewList.i.indexOf(classifiedView)));
            }
        });
        this.f.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ClassifiedViewList.this.b.e(i);
                if (!koq.d(ClassifiedViewList.this.i, i) || ClassifiedViewList.this.i.get(i) == null) {
                    return;
                }
                if (ClassifiedViewList.this.d != null) {
                    ClassifiedViewList.this.d.onClassifiedViewSelected((View) ClassifiedViewList.this.i.get(i), i);
                }
                if (ClassifiedViewList.this.i.get(i) instanceof ObserveredClassifiedView) {
                    ClassifiedViewList classifiedViewList = ClassifiedViewList.this;
                    classifiedViewList.c = (ObserveredClassifiedView) classifiedViewList.i.get(i);
                }
                ((ClassifiedView) ClassifiedViewList.this.i.get(i)).onSelected();
            }
        });
        setViewForAdapter(iChartLayerHolder);
    }

    private void setViewForAdapter(IChartLayerHolder iChartLayerHolder) {
        for (ClassifiedView classifiedView : this.i) {
            if (classifiedView != null) {
                classifiedView.init(iChartLayerHolder);
            }
        }
        this.h.notifyDataSetChanged();
        this.f.setCurrentItem(0);
        for (ClassifiedView classifiedView2 : this.i) {
            if (classifiedView2 != null) {
                classifiedView2.setOnDataShowListener(this.f9932a);
                classifiedView2.loadTweenAnimatiionMgr(this.g);
            }
        }
        this.h.notifyDataSetChanged();
        for (ClassifiedView classifiedView3 : this.i) {
            if (classifiedView3 != null) {
                classifiedView3.onCreateView().openJumpableFeature(this.g);
            }
        }
    }

    public void e(List<ClassifiedView> list, IChartLayerHolderAdapter iChartLayerHolderAdapter) {
        this.f.clearOnPageChangeListeners();
        this.i.clear();
        this.i.addAll(list);
        d dVar = this.f9932a;
        if (dVar != null) {
            dVar.c();
        }
        if (this.f9932a == null) {
            this.f9932a = new d();
        }
        this.f9932a.d();
        if (this.i.size() > 0) {
            this.f9932a.e(this.i.get(0).getStepDataType());
            if (this.i.get(0) instanceof ObserveredClassifiedView) {
                this.c = (ObserveredClassifiedView) this.i.get(0);
            }
        }
        this.b.a(this.i, new ClassifiedButtonList.OnSwitchClassifies() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList.OnSwitchClassifies
            public void onSwitchClassifies(ClassifiedView classifiedView) {
                ClassifiedViewList.this.f.setCurrentItem(ClassifiedViewList.this.i.indexOf(classifiedView));
                TweenAnimatiionMgr tweenAnimatiionMgr = ClassifiedViewList.this.g;
                ClassifiedViewList classifiedViewList = ClassifiedViewList.this;
                tweenAnimatiionMgr.e(classifiedViewList.e(classifiedViewList.i.indexOf(classifiedView)));
            }
        });
        this.f.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.5
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ClassifiedViewList.this.b.e(i);
                if (!koq.d(ClassifiedViewList.this.i, i) || ClassifiedViewList.this.i.get(i) == null) {
                    return;
                }
                if (ClassifiedViewList.this.i.get(i) instanceof ObserveredClassifiedView) {
                    ClassifiedViewList classifiedViewList = ClassifiedViewList.this;
                    classifiedViewList.c = (ObserveredClassifiedView) classifiedViewList.i.get(i);
                }
                if (ClassifiedViewList.this.d != null) {
                    ClassifiedViewList.this.d.onClassifiedViewSelected((View) ClassifiedViewList.this.i.get(i), i);
                }
                ((ClassifiedView) ClassifiedViewList.this.i.get(i)).onSelected();
            }
        });
        c(list, iChartLayerHolderAdapter);
    }

    private void c(List<ClassifiedView> list, IChartLayerHolderAdapter iChartLayerHolderAdapter) {
        IChartLayerHolder acquireAdapter;
        for (ClassifiedView classifiedView : list) {
            if (classifiedView != null && (acquireAdapter = iChartLayerHolderAdapter.acquireAdapter(classifiedView)) != null) {
                classifiedView.init(acquireAdapter);
            }
        }
        this.h.notifyDataSetChanged();
        this.f.setCurrentItem(0);
        for (ClassifiedView classifiedView2 : this.i) {
            if (classifiedView2 != null) {
                classifiedView2.setOnDataShowListener(this.f9932a);
                classifiedView2.loadTweenAnimatiionMgr(this.g);
            }
        }
        this.h.notifyDataSetChanged();
        for (ClassifiedView classifiedView3 : this.i) {
            if (classifiedView3 != null) {
                classifiedView3.onCreateView().openJumpableFeature(this.g);
            }
        }
    }

    class e extends HealthPagerAdapter {
        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private e() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return ClassifiedViewList.this.i.size();
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (koq.b(ClassifiedViewList.this.i, i) || ClassifiedViewList.this.i.get(i) == null) {
                return null;
            }
            ObserveredClassifiedView onCreateView = ((ClassifiedView) ClassifiedViewList.this.i.get(i)).onCreateView();
            ViewParent parent = onCreateView.getParent();
            if (parent == null) {
                viewGroup.addView(onCreateView, -1, -2);
                return onCreateView;
            }
            if (parent == viewGroup) {
                return onCreateView;
            }
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(onCreateView);
            }
            viewGroup.addView(onCreateView, -1, -2);
            return onCreateView;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getItemPosition(Object obj) {
            return ClassifiedViewList.this.i.contains(obj) ? -1 : -2;
        }
    }

    public void b(IOnDataShowListener iOnDataShowListener) {
        synchronized (e) {
            this.j = iOnDataShowListener;
        }
    }

    class d implements IOnDataShowListener {
        private final Map<DataInfos, c> e = new HashMap();
        private DataInfos c = null;

        /* renamed from: a, reason: collision with root package name */
        private DataInfos f9934a = null;

        d() {
        }

        public void d() {
            ClassifiedViewList.this.f.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.d.1
                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    if (koq.b(ClassifiedViewList.this.i, i) || ClassifiedViewList.this.i.get(i) == null) {
                        return;
                    }
                    DataInfos stepDataType = ((ClassifiedView) ClassifiedViewList.this.i.get(i)).getStepDataType();
                    synchronized (ClassifiedViewList.e) {
                        d.this.f9934a = stepDataType;
                        if (stepDataType == d.this.c) {
                            c cVar = (c) d.this.e.get(stepDataType);
                            if (cVar == null) {
                                return;
                            }
                            synchronized (ClassifiedViewList.e) {
                                if (ClassifiedViewList.this.j != null) {
                                    ClassifiedViewList.this.j.onDataShowChanged(cVar.c, cVar.f9933a, cVar.b, cVar.d);
                                }
                            }
                        }
                    }
                }
            });
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
        public void onUserEvent(UserEvent userEvent) {
            synchronized (ClassifiedViewList.e) {
                if (ClassifiedViewList.this.j != null) {
                    ClassifiedViewList.this.j.onUserEvent(userEvent);
                }
            }
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
        public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
            synchronized (ClassifiedViewList.e) {
                this.e.put(dataInfos, new c(dataInfos, i, i2, hwHealthBaseBarLineChart));
                this.c = dataInfos;
                if (this.f9934a == dataInfos && ClassifiedViewList.this.j != null) {
                    ClassifiedViewList.this.j.onDataShowChanged(dataInfos, i, i2, hwHealthBaseBarLineChart);
                }
            }
        }

        public void c() {
            synchronized (ClassifiedViewList.e) {
                this.e.clear();
                this.f9934a = null;
                this.c = null;
            }
        }

        public void e(DataInfos dataInfos) {
            synchronized (ClassifiedViewList.e) {
                this.f9934a = dataInfos;
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        int f9933a;
        int b;
        DataInfos c;
        HwHealthBaseBarLineChart d;

        c(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
            this.c = dataInfos;
            this.f9933a = i;
            this.b = i2;
            this.d = hwHealthBaseBarLineChart;
        }
    }

    public String d() {
        ObserveredClassifiedView observeredClassifiedView = this.c;
        return observeredClassifiedView == null ? "" : observeredClassifiedView.acquireTimeRangeText();
    }

    public void setOnClassifiedViewChangeListener(OnClassifiedViewChangeListener onClassifiedViewChangeListener) {
        this.d = onClassifiedViewChangeListener;
    }
}
