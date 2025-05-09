package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.api.CombineChartRangeShowCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eal;
import defpackage.ebt;
import defpackage.koq;
import defpackage.nnl;
import defpackage.noy;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionHeartRateCombinedChart<ChartT extends HwHealthBaseScrollBarLineChart> extends BaseSectionLineChart<ChartT> {

    /* renamed from: a, reason: collision with root package name */
    private ChartT f2690a;
    private List<ChartT> b;
    private Observer c;
    private List<nnl> d;
    private ChartT e;
    private ChartT g;
    private ChartT h;
    private List<nnl> j;

    public SectionHeartRateCombinedChart(Context context) {
        this(context, null);
    }

    public SectionHeartRateCombinedChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionHeartRateCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new ArrayList(16);
        this.d = new ArrayList(16);
        this.b = new ArrayList();
        this.c = new Observer() { // from class: com.huawei.health.knit.section.view.SectionHeartRateCombinedChart.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                Object obj;
                LogUtil.d("SectionHeartRateCombinedChart", "refresh chart notify");
                if (!SectionHeartRateCombinedChart.this.getObserverLabel().equals(str) || objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                    return;
                }
                SectionHeartRateCombinedChart.this.mCardIndex = ((Integer) obj).intValue();
                ebt ebtVar = new ebt();
                ebtVar.a(SectionHeartRateCombinedChart.this.mCardIndex);
                SectionHeartRateCombinedChart.this.mCombineChartRangeShowCallback.onRangeChange(ebtVar);
                SectionHeartRateCombinedChart sectionHeartRateCombinedChart = SectionHeartRateCombinedChart.this;
                sectionHeartRateCombinedChart.mCurrentChart = (ChartT) sectionHeartRateCombinedChart.acquireCurrentChart(sectionHeartRateCombinedChart.mCardIndex);
                if (DataInfos.HeartRateDayDetail == SectionHeartRateCombinedChart.this.mDataInfos) {
                    SectionHeartRateCombinedChart sectionHeartRateCombinedChart2 = SectionHeartRateCombinedChart.this;
                    sectionHeartRateCombinedChart2.a(sectionHeartRateCombinedChart2.mCardIndex);
                } else if (DataInfos.HeartRateWeekDetail == SectionHeartRateCombinedChart.this.mDataInfos) {
                    SectionHeartRateCombinedChart sectionHeartRateCombinedChart3 = SectionHeartRateCombinedChart.this;
                    sectionHeartRateCombinedChart3.d(sectionHeartRateCombinedChart3.mCardIndex);
                } else if (DataInfos.HeartRateMonthDetail == SectionHeartRateCombinedChart.this.mDataInfos || DataInfos.HeartRateYearDetail == SectionHeartRateCombinedChart.this.mDataInfos) {
                    SectionHeartRateCombinedChart sectionHeartRateCombinedChart4 = SectionHeartRateCombinedChart.this;
                    sectionHeartRateCombinedChart4.b(sectionHeartRateCombinedChart4.mCardIndex);
                } else {
                    LogUtil.d("SectionHeartRateCombinedChart", "mDataInfos is invalid");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0) {
            this.e.disableManualReferenceLine();
            attachChartToViewPager(this.e);
            return;
        }
        if (i == 1) {
            Paint paint = new Paint();
            paint.setStrokeWidth(nsn.c(BaseApplication.getContext(), 2.0f));
            paint.setColor(Color.argb(255, 252, 49, 89));
            paint.setStyle(Paint.Style.STROKE);
            this.f2690a.enableManualReferenceLine(0, paint, true);
            this.f2690a.reCalculateDynamicBoardForManualRefLine();
            attachChartToViewPager(this.f2690a);
            return;
        }
        if (i == 2) {
            this.h.disableManualReferenceLine();
            this.h.focusArea(this.j);
            attachChartToViewPager(this.h);
        } else {
            if (i == 3) {
                this.g.disableManualReferenceLine();
                this.g.focusArea(this.d);
                attachChartToViewPager(this.g);
                return;
            }
            LogUtil.d("SectionHeartRateCombinedChart", "cardIndex is invalid");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (i == 0) {
            attachChartToViewPager(this.e);
            return;
        }
        if (i == 1) {
            ChartT chartt = this.f2690a;
            if (chartt instanceof HwHealthCombinedChart) {
                ((HwHealthCombinedChart) chartt).a();
            }
            attachChartToViewPager(this.f2690a);
            return;
        }
        if (i == 2) {
            ChartT chartt2 = this.h;
            if (chartt2 instanceof HwHealthCombinedChart) {
                ((HwHealthCombinedChart) chartt2).c();
                ((HwHealthCombinedChart) this.h).e();
            }
            attachChartToViewPager(this.h);
            return;
        }
        if (i == 3) {
            ChartT chartt3 = this.g;
            if (chartt3 instanceof HwHealthCombinedChart) {
                ((HwHealthCombinedChart) chartt3).c();
                ((HwHealthCombinedChart) this.g).e();
            }
            attachChartToViewPager(this.g);
            return;
        }
        LogUtil.d("SectionHeartRateCombinedChart", "cardIndex is invalid");
    }

    private void d(ChartT chartt, int i) {
        HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
        bVar.e(this.mDataInfos);
        bVar.e(c(i));
        this.mChartHolder.addDataLayer((IChartLayerHolder) chartt, bVar);
        if (chartt != null) {
            chartt.setWillNotDraw(false);
            HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = chartt.acquireScrollAdapter();
            acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
            chartt.resetYaxisAnimateValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 0) {
            attachChartToViewPager(this.e);
            return;
        }
        if (i == 1) {
            ChartT chartt = this.f2690a;
            if (chartt instanceof HwHealthCombinedChart) {
                ((HwHealthCombinedChart) chartt).a();
            }
            attachChartToViewPager(this.f2690a);
            return;
        }
        if (i == 2) {
            ChartT chartt2 = this.h;
            if (chartt2 instanceof HwHealthCombinedChart) {
                chartt2.focusArea(this.j);
                ((HwHealthCombinedChart) this.h).c();
                ((HwHealthCombinedChart) this.h).e();
            }
            attachChartToViewPager(this.h);
            return;
        }
        if (i == 3) {
            ChartT chartt3 = this.g;
            if (chartt3 instanceof HwHealthCombinedChart) {
                chartt3.focusArea(this.d);
                ((HwHealthCombinedChart) this.g).c();
                ((HwHealthCombinedChart) this.g).e();
            }
            attachChartToViewPager(this.g);
            return;
        }
        LogUtil.d("SectionHeartRateCombinedChart", "cardIndex is invalid");
    }

    protected String getObserverLabel() {
        return eal.e(this.mDataInfos);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected ChartT acquireCurrentChart(int i) {
        LogUtil.d("SectionHeartRateCombinedChart", "acquireCurrentChart");
        if (koq.d(this.b, i)) {
            return this.b.get(i);
        }
        return null;
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    public void bindExtraParamsToView(HashMap<String, Object> hashMap) {
        nsy.cMp_(this.mHorizontalBtn, nru.d((Map) hashMap, "HORIZONTAL_BTN", R.drawable._2131430277_res_0x7f0b0b85));
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        if (this.mDataInfos != null && this.mDataInfos == DataInfos.HeartRateDayDetail) {
            nsy.cMA_(this.mHorizontalBtn, 0);
            this.mHorizontalBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionHeartRateCombinedChart.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (a2 != null) {
                        ObserverManagerUtil.c("SECTION_CHART", SectionHeartRateCombinedChart.this.acquireCurrentChart(0));
                        a2.onClick("HORIZONTAL_BTN");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        ObserverManagerUtil.d(this.c, getObserverLabel());
        List<ChartT> list = (List) nru.c(hashMap, "SECTION_CHART", List.class, null);
        this.b = list;
        c(list);
        initChart(acquireCurrentChart(1), 1);
        d((SectionHeartRateCombinedChart<ChartT>) acquireCurrentChart(1), 1);
        initChart(acquireCurrentChart(2), 2);
        d((SectionHeartRateCombinedChart<ChartT>) acquireCurrentChart(2), 2);
        initChart(acquireCurrentChart(3), 3);
        d((SectionHeartRateCombinedChart<ChartT>) acquireCurrentChart(3), 3);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void addDataLayer(IChartLayerHolder iChartLayerHolder, ChartT chartt, int i) {
        HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
        bVar.e(this.mDataInfos);
        bVar.e(c(i));
        iChartLayerHolder.addDataLayer((IChartLayerHolder) chartt, bVar);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    public void onCardRangeShow(ChartT chartt, CombineChartRangeShowCallback combineChartRangeShowCallback, long j) {
        float f;
        float f2;
        int i = 0;
        Object obj = chartt.getData().getDataSets().get(0);
        int i2 = 2;
        int i3 = 1;
        if (obj instanceof HwHealthLineDataSet) {
            List<T> acquireEntryVals = ((HwHealthLineDataSet) obj).acquireEntryVals();
            float acquireShowRangeMinimum = chartt.acquireShowRangeMinimum();
            float acquireShowRangeMaximum = chartt.acquireShowRangeMaximum();
            ArrayList arrayList = new ArrayList();
            for (T t : acquireEntryVals) {
                if (t != null) {
                    if (acquireShowRangeMaximum - acquireShowRangeMinimum < 1440.0f) {
                        Date date = new Date((((long) acquireShowRangeMinimum) * 60000) + 1388505600000L);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int i4 = calendar.get(i3);
                        int i5 = calendar.get(i2);
                        int i6 = calendar.get(5);
                        Date date2 = new Date((((long) t.getX()) * 60000) + 1388505600000L);
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTime(date2);
                        int i7 = calendar2.get(1);
                        int i8 = calendar2.get(2);
                        int i9 = calendar2.get(5);
                        if (i4 == i7 && i5 == i8 && i6 == i9) {
                            arrayList.add(t);
                        }
                    } else if (t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                        arrayList.add(t);
                    }
                    i = 0;
                    i2 = 2;
                    i3 = 1;
                }
            }
        }
        int i10 = i;
        float[] fArr = new float[i10];
        if (this.mDataInfos == DataInfos.HeartRateDayDetail) {
            fArr = chartt.getShowMinMaxRangeByRatio();
        }
        if (fArr == null || fArr.length != 2) {
            f = Float.MIN_VALUE;
            f2 = Float.MAX_VALUE;
        } else {
            f2 = fArr[i10];
            f = fArr[1];
        }
        LogUtil.d("SectionHeartRateCombinedChart", "Min: " + f2 + ", Max: " + f);
        String str = (f < f2 || f2 <= 0.0f) ? "--" : UnitUtil.e(f2, 1, 0) + Constants.LINK + UnitUtil.e(f, 1, 0);
        if (combineChartRangeShowCallback != null) {
            ebt ebtVar = new ebt();
            ebtVar.a(this.mCardIndex);
            ebtVar.a(str);
            combineChartRangeShowCallback.onRangeChange(ebtVar);
        }
    }

    private void c(List<ChartT> list) {
        this.e = koq.d(list, 0) ? list.get(0) : null;
        this.f2690a = koq.d(list, 1) ? list.get(1) : null;
        this.h = koq.d(list, 2) ? list.get(2) : null;
        this.g = koq.d(list, 3) ? list.get(3) : null;
    }

    private String c(int i) {
        if (i == 0) {
            return HwHealthChartHolder.LAYER_ID_NORMAL_HR;
        }
        if (i == 1) {
            return HwHealthChartHolder.LAYER_ID_REST_HR;
        }
        if (i == 2) {
            return HwHealthChartHolder.LAYER_ID_WARNING_HR;
        }
        if (i == 3) {
            return HwHealthChartHolder.LAYER_ID_BRADYCARDIA;
        }
        LogUtil.e("SectionHeartRateCombinedChart", "layer id is wrong");
        throw new IllegalArgumentException("layer id is wrong");
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected String parseEntry(HwHealthBaseEntry hwHealthBaseEntry) {
        if (hwHealthBaseEntry == null) {
            return "--";
        }
        if (!(hwHealthBaseEntry instanceof HwHealthBarEntry)) {
            return hwHealthBaseEntry.getY() == 0.0f ? "--" : c(hwHealthBaseEntry.getY());
        }
        HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
        float a2 = noy.a(hwHealthBarEntry.acquireModel());
        float c = noy.c(hwHealthBarEntry.acquireModel());
        return (a2 == 0.0f || c == 0.0f) ? "--" : e(a2, c);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected void updateDateAndTime(String str, List<HwHealthMarkerView.a> list) {
        notifyCursorDateAndTime(str, list);
    }

    @Override // com.huawei.health.knit.section.view.BaseSectionLineChart
    protected DataInfos getJudgeDataInfos() {
        return DataInfos.HeartRateDayDetail;
    }

    public String e(float f, float f2) {
        return ((int) f) + Constants.LINK + ((int) f2);
    }

    public String c(float f) {
        return String.valueOf((int) f);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionHeartRateCombinedChart";
    }
}
