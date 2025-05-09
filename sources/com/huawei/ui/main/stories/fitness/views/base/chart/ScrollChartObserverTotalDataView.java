package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize;
import defpackage.nom;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class ScrollChartObserverTotalDataView extends ScrollChartObserverView {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<OnDataChange> f9948a;
    private AsyncSelectorSerialize b;
    private ValueCapture c;
    private HwHealthBaseScrollBarLineChart.DataRatioProvider d;
    private ICustomCalculator e;
    private AtomicBoolean g;

    public interface OnDataChange {
        void onChange(float f);
    }

    public interface ValueCapture {

        public interface Callback {
            void onResult(float f);
        }

        void filter(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, Callback callback);

        boolean isAcceptable(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);
    }

    public ScrollChartObserverTotalDataView(Context context, ObserveredClassifiedView observeredClassifiedView, String str, String str2, HwHealthBaseScrollBarLineChart.DataRatioProvider dataRatioProvider) {
        super(context, observeredClassifiedView, str, str2);
        this.g = new AtomicBoolean(false);
        this.b = null;
        this.f9948a = new ArrayList<>(16);
        this.d = dataRatioProvider;
        if (observeredClassifiedView.getStepDataType().isTimeStrengthData() && observeredClassifiedView.getStepDataType().isWeekData()) {
            this.mExplain.setVisibility(0);
        }
    }

    public void setCustomCalculator(ICustomCalculator iCustomCalculator) {
        if (iCustomCalculator == null) {
            LogUtil.b("ScrollChartObserverTotalDataView", "sumCalculator == null");
        } else {
            this.e = iCustomCalculator;
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        if (!this.g.get()) {
            a(hwHealthBaseScrollBarLineChart);
        } else {
            b(hwHealthBaseScrollBarLineChart);
        }
    }

    public void b(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.2
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onSuccess(Map map) {
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onFailed(int i) {
                LogUtil.c("ScrollChartObserverTotalDataView", "process onRangeShow end onFailed");
            }
        };
        this.b = asyncSelectorSerialize;
        d(asyncSelectorSerialize, hwHealthBaseScrollBarLineChart);
        asyncSelectorSerialize.run();
    }

    private void d(final AsyncSelectorSerialize asyncSelectorSerialize, final HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.4
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.BaseAction, com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                final HashMap hashMap = new HashMap();
                float showDataByRatio = hwHealthBaseScrollBarLineChart.getShowDataByRatio(ScrollChartObserverTotalDataView.this.d);
                if (ScrollChartObserverTotalDataView.this.c == null || !ScrollChartObserverTotalDataView.this.c.isAcceptable(hwHealthBaseScrollBarLineChart)) {
                    if (ScrollChartObserverTotalDataView.this.e != null) {
                        hashMap.put("value", Float.valueOf(ScrollChartObserverTotalDataView.this.e.calculate(hwHealthBaseScrollBarLineChart, ScrollChartObserverTotalDataView.this.mHost.getStepDataType())));
                        asyncSelectorSerialize.next(hashMap);
                        return;
                    } else {
                        hashMap.put("value", Float.valueOf(showDataByRatio));
                        asyncSelectorSerialize.next(hashMap);
                        return;
                    }
                }
                ScrollChartObserverTotalDataView.this.c.filter(hwHealthBaseScrollBarLineChart, showDataByRatio, new ValueCapture.Callback() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.4.5
                    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.ValueCapture.Callback
                    public void onResult(float f) {
                        hashMap.put("value", Float.valueOf(f));
                        asyncSelectorSerialize.next(hashMap);
                    }
                });
            }
        });
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.3
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.BaseAction, com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map != null) {
                    AsyncSelectorSerialize asyncSelectorSerialize2 = ScrollChartObserverTotalDataView.this.b;
                    AsyncSelectorSerialize asyncSelectorSerialize3 = asyncSelectorSerialize;
                    if (asyncSelectorSerialize2 != asyncSelectorSerialize3) {
                        asyncSelectorSerialize3.postError();
                        return;
                    }
                    if (map.get("value") instanceof Float) {
                        float floatValue = ((Float) map.get("value")).floatValue();
                        LogUtil.a("ScrollChartObserverTotalDataView", "mValueCapture total" + floatValue);
                        ScrollChartObserverTotalDataView.this.c(floatValue);
                    }
                    asyncSelectorSerialize.next(null);
                    return;
                }
                asyncSelectorSerialize.postError();
            }
        });
    }

    public void a(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        float showDataByRatio = hwHealthBaseScrollBarLineChart.getShowDataByRatio(this.d);
        ICustomCalculator iCustomCalculator = this.e;
        if (iCustomCalculator != null) {
            c(iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType()));
        } else {
            c(showDataByRatio);
        }
    }

    protected void c(float f) {
        setContentText(this.mHost.convertFloat2TextShow(f));
        Iterator<OnDataChange> it = this.f9948a.iterator();
        while (it.hasNext()) {
            OnDataChange next = it.next();
            if (next != null) {
                next.onChange(f);
            }
        }
    }

    public void setOnDataChange(OnDataChange onDataChange) {
        this.f9948a.add(onDataChange);
    }

    public void b(ValueCapture valueCapture) {
        this.g.set(true);
        this.c = valueCapture;
    }

    public static class b implements ValueCapture {

        /* renamed from: a, reason: collision with root package name */
        private float f9950a;
        private float b;
        private int e;

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        
            if (r6.isCaloriesData() != false) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public b(float r4, int r5, com.huawei.ui.commonui.linechart.common.DataInfos r6) {
            /*
                r3 = this;
                r3.<init>()
                r0 = 1065353216(0x3f800000, float:1.0)
                r3.f9950a = r0
                r3.b = r4
                r3.e = r5
                boolean r4 = r6.isDistanceData()
                r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                r5 = 981668463(0x3a83126f, float:0.001)
                if (r4 == 0) goto L25
                boolean r4 = health.compact.a.UnitUtil.h()
                if (r4 == 0) goto L42
                r4 = 3
                double r0 = health.compact.a.UnitUtil.e(r1, r4)
                float r4 = (float) r0
                float r0 = r4 * r5
                goto L43
            L25:
                boolean r4 = r6.isClimbData()
                if (r4 == 0) goto L3c
                boolean r4 = health.compact.a.UnitUtil.h()
                r0 = 1036831949(0x3dcccccd, float:0.1)
                if (r4 == 0) goto L43
                r4 = 1
                double r4 = health.compact.a.UnitUtil.e(r1, r4)
                float r4 = (float) r4
                float r0 = r0 * r4
                goto L43
            L3c:
                boolean r4 = r6.isCaloriesData()
                if (r4 == 0) goto L43
            L42:
                r0 = r5
            L43:
                r3.f9950a = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.<init>(float, int, com.huawei.ui.commonui.linechart.common.DataInfos):void");
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.ValueCapture
        public boolean isAcceptable(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
            return hwHealthBaseScrollBarLineChart.isInPagerPosition();
        }

        @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.ValueCapture
        public void filter(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, final float f, ValueCapture.Callback callback) {
            long l = nom.l(TimeUnit.MINUTES.toMillis((long) hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum()));
            if (HiDateUtil.c(l) == HiDateUtil.c(hwHealthBaseScrollBarLineChart.getDefaultTimeMillis())) {
                float f2 = this.b;
                if (f2 > -1.0f) {
                    if (f < f2) {
                        callback.onResult(f2);
                        return;
                    } else {
                        callback.onResult(f);
                        return;
                    }
                }
            }
            HiDataReadOption hiDataReadOption = new HiDataReadOption();
            hiDataReadOption.setTimeInterval(l, HiDateUtil.f(l));
            hiDataReadOption.setType(new int[]{this.e});
            HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new ValueCaptureReadListener(callback) { // from class: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.5
                /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
                /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
                @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.ValueCaptureReadListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResultData(java.lang.Object r3) {
                    /*
                        r2 = this;
                        boolean r0 = r3 instanceof android.util.SparseArray
                        if (r0 == 0) goto L37
                        android.util.SparseArray r3 = (android.util.SparseArray) r3
                        int r0 = r3.size()
                        if (r0 <= 0) goto L37
                        com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView$b r0 = com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.this
                        int r0 = com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.e(r0)
                        java.lang.Object r3 = r3.get(r0)
                        java.util.List r3 = (java.util.List) r3
                        int r0 = r3.size()
                        if (r0 == 0) goto L37
                        r0 = 0
                        java.lang.Object r1 = r3.get(r0)
                        if (r1 == 0) goto L37
                        java.lang.Object r3 = r3.get(r0)
                        com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3
                        float r3 = r3.getFloatValue()
                        com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView$b r0 = com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.this
                        float r0 = com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.b(r0)
                        float r3 = r3 * r0
                        goto L38
                    L37:
                        r3 = 0
                    L38:
                        float r0 = r3
                        int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                        if (r0 >= 0) goto L44
                        com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView$ValueCapture$Callback r0 = r2.mCallback
                        r0.onResult(r3)
                        return
                    L44:
                        com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView$ValueCapture$Callback r3 = r2.mCallback
                        float r0 = r3
                        r3.onResult(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.b.AnonymousClass5.onResultData(java.lang.Object):void");
                }
            });
        }
    }

    public static abstract class ValueCaptureReadListener implements HiDataReadResultListener {
        protected ValueCapture.Callback mCallback;

        public abstract void onResultData(Object obj);

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        protected ValueCaptureReadListener(ValueCapture.Callback callback) {
            this.mCallback = callback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("ScrollChartObserverTotalDataView", "ValueCaptureReadListener onResult", "anchor", Integer.valueOf(i2));
            onResultData(obj);
            if (i2 == 2 || i == 1) {
                this.mCallback = null;
            }
        }
    }
}
