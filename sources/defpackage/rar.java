package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.weight.viewholder.WeightTrendLineChart;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes7.dex */
public class rar {

    /* renamed from: a, reason: collision with root package name */
    private View f16683a;
    private WeightTrendLineChart b;
    private int c;
    private TextView d;
    private String e;
    private HealthTextView f;
    private List<Pair<Long, Float>> g;
    private int h;
    private HealthTextView i;
    private HealthTextView j;
    private cfe l;
    private LinearLayout m;
    private Handler n = new b(this);
    private View o;

    public rar(View view, cfe cfeVar) {
        this.l = cfeVar;
        this.o = view.findViewById(R.id.weight_goal_line_chart);
        this.j = (HealthTextView) view.findViewById(R.id.now_time);
        this.f = (HealthTextView) view.findViewById(R.id.now_value);
        this.i = (HealthTextView) view.findViewById(R.id.value_Unit);
        this.b = (WeightTrendLineChart) view.findViewById(R.id.weight_line_chart);
        this.d = (TextView) view.findViewById(R.id.avg_text);
        this.m = (LinearLayout) view.findViewById(R.id.ll_value_unit);
        this.o.setBackground(ContextCompat.getDrawable(BaseApplication.e(), R.drawable._2131431985_res_0x7f0b1231));
    }

    public void dJC_(View view) {
        this.f16683a = view;
    }

    public void a(final List<Pair<Long, Float>> list) {
        HandlerExecutor.e(new Runnable() { // from class: rap
            @Override // java.lang.Runnable
            public final void run() {
                rar.this.d(list);
            }
        });
    }

    /* synthetic */ void d(List list) {
        this.g = list;
        if (this.b == null || koq.b(list) || list.size() <= 1) {
            LogUtil.h("WeightTrendChartViewHolder", "refreshCardView : mLineChart is null or mLineChartDataList is empty");
            return;
        }
        ArrayList arrayList = new ArrayList(this.g.size());
        float f = Float.MAX_VALUE;
        float f2 = Float.MIN_VALUE;
        for (int i = 0; i < this.g.size(); i++) {
            Pair<Long, Float> pair = this.g.get(i);
            if (pair != null) {
                arrayList.add(new HwHealthBaseEntry(i, ((Float) pair.second).floatValue()));
                f = Math.min(f, ((Float) pair.second).floatValue());
                f2 = Math.max(f2, ((Float) pair.second).floatValue());
            }
        }
        if (arrayList.size() <= 0) {
            return;
        }
        b(0);
        this.d.setVisibility(0);
        g();
        c(arrayList, this.g, f, f2);
    }

    private void c(List<HwHealthBaseEntry> list, List<Pair<Long, Float>> list2, float f, float f2) {
        float c = c(list.size());
        e(0, list.size() - 1, c);
        d(f, f2);
        List<IHwHealthLineDataSet> c2 = c(list);
        e();
        this.b.setData(new now(c2));
        d(list, c);
        e(list2);
        a();
        b();
        this.b.refresh();
    }

    private void b() {
        this.b.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: rav
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public final void onRangeShow(int i, int i2) {
                rar.this.b(i, i2);
            }
        });
    }

    /* synthetic */ void b(int i, int i2) {
        this.h = i;
        if (this.c == i2) {
            return;
        }
        this.c = i2;
        Handler handler = this.n;
        if (handler != null) {
            handler.removeMessages(13);
            this.n.sendEmptyMessageDelayed(13, 500L);
        }
    }

    public static class b extends BaseHandler<rar> {
        b(rar rarVar) {
            super(rarVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dJD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(rar rarVar, Message message) {
            if (rarVar == null || message == null) {
                LogUtil.h("WeightTrendChartViewHolder", "handleMessageWhenReferenceNotNull chartViewHolder == null !");
            } else if (message.what == 13) {
                rarVar.f();
            } else {
                LogUtil.h("WeightTrendChartViewHolder", "unknown msg");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        int i = this.h;
        int i2 = this.c;
        LogUtil.a("WeightTrendChartViewHolder", "updateDate startX = ", Integer.valueOf(i), " endX = ", Integer.valueOf(i2), " mTrendDataList.size() = ", Integer.valueOf(this.g.size()), " mBodyDataKey = ", this.e);
        if (this.g.size() > 0 && i < i2 && koq.d(this.g, i) && koq.d(this.g, i2)) {
            float f = Float.MAX_VALUE;
            float f2 = Float.MIN_VALUE;
            while (i <= i2) {
                Pair<Long, Float> pair = this.g.get(i);
                if (pair != null) {
                    f = Math.min(f, ((Float) pair.second).floatValue());
                    f2 = Math.max(f2, ((Float) pair.second).floatValue());
                }
                i++;
            }
            LogUtil.a("WeightTrendChartViewHolder", "updateDate yMin = ", Float.valueOf(f), " yMax = ", Float.valueOf(f2));
            d(f, f2);
            this.b.refresh();
            return;
        }
        LogUtil.h("WeightTrendChartViewHolder", "updateDate data is error");
    }

    private void d(List<HwHealthBaseEntry> list, float f) {
        this.b.setPadding(0, 0, 0, 0);
        this.b.setVisibleXRange(0.0f, Math.min(6, list.size() - 1) + (2.0f * f));
        if (!LanguageUtil.bc(BaseApplication.e())) {
            this.b.moveViewToX(((list.size() - 1) - Math.min(6, list.size() - 1)) - f);
        }
        this.b.highlightValue(list.size() - 1, false);
    }

    private void e() {
        DataRenderer renderer = this.b.getRenderer();
        if (renderer instanceof nov) {
            ((nov) renderer).a(1);
        }
        this.b.enableMarkerView(true);
        this.b.fillChartBackground(nrn.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.color._2131297061_res_0x7f090325), nrn.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.color._2131297061_res_0x7f090325));
        this.b.setBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131298121_res_0x7f090749));
        this.b.makeReverse(true);
        this.b.setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        this.b.enableSpacePreserveForDataOverlay(true);
        this.b.setLayerType(1, null);
        this.b.getLegend().setEnabled(false);
        this.b.getDescription().setEnabled(false);
        this.b.setEnabled(false);
    }

    private List<IHwHealthLineDataSet> c(List<HwHealthBaseEntry> list) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.e(), list, "", "", "", 1);
        int d = nrn.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.color._2131299374_res_0x7f090c2e);
        hwHealthLineDataSet.setColor(d);
        hwHealthLineDataSet.b(nrn.c(d, 0.5f), nrn.c(d, 0.0f), true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        hwHealthLineDataSet.setLabelCount(5, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hwHealthLineDataSet);
        return arrayList;
    }

    private float c(int i) {
        return Math.min(i, 7) * 0.014f;
    }

    private void e(final List<Pair<Long, Float>> list) {
        this.b.getXAxis().setValueFormatter(new HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter() { // from class: rar.3
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
            public void enableMarkerViewShowRange(boolean z) {
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
            public void setHealthType(HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
            public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
                if (!koq.b(list)) {
                    Date a2 = rar.this.a((int) f, list);
                    return a2 == null ? "" : UnitUtil.a(a2, 24);
                }
                LogUtil.h("WeightTrendChartViewHolder", "setXAxisValues() startTimes is empty");
                return "";
            }

            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                float round = f > 0.0f ? Math.round(f) : 0.0f;
                if (!koq.b(list)) {
                    Date a2 = rar.this.a((int) round, list);
                    return a2 == null ? "" : UnitUtil.a(a2, 131080);
                }
                LogUtil.h("WeightTrendChartViewHolder", "setXAxisValues() startTimes is empty");
                return "";
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
            public String getRangeText(double d, double d2) {
                return "";
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Date a(int i, List<Pair<Long, Float>> list) {
        if (!koq.d(list, i) || list.get(i) == null) {
            return null;
        }
        return new Date(((Long) list.get(i).first).longValue());
    }

    private void a() {
        this.b.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: ran
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list) {
                rar.this.d(str, list);
            }
        });
    }

    /* synthetic */ void d(String str, List list) {
        String e;
        if (!TextUtils.isEmpty(str)) {
            this.j.setText(str);
            LogUtil.a("WeightTrendChartViewHolder", "onListerMarkerTextChanged() timeStr = ", str);
        }
        if (koq.b(list)) {
            this.f.setText("--");
            LogUtil.h("WeightTrendChartViewHolder", "onListerMarkerTextChanged() data is empty timeStr = ", str);
            return;
        }
        HwHealthBaseEntry hwHealthBaseEntry = ((HwHealthMarkerView.a) list.get(list.size() - 1)).b;
        if (hwHealthBaseEntry == null) {
            this.f.setText("--");
            LogUtil.h("WeightTrendChartViewHolder", "onListerMarkerTextChanged() entry is null timeStr = ", str);
            return;
        }
        float y = hwHealthBaseEntry.getY();
        LogUtil.a("WeightTrendChartViewHolder", "onListerMarkerTextChanged() value = ", Float.valueOf(y));
        if (this.e.equals("waistHipRatio") || this.e.equals("waistHipRatioUser") || this.e.equals(BleConstants.BONE_SALT) || this.e.equals("waistHipRatio")) {
            e = UnitUtil.e(y, 1, 2);
        } else if (this.e.equals("bodyWeight")) {
            e = a(y);
        } else if (this.e.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
            e = UnitUtil.e(y, 1, 0);
        } else if (this.e.equals(BleConstants.BODY_FAT_RATE) || this.e.equals(BleConstants.MOISTURE_RATE) || this.e.equals("protein")) {
            e = UnitUtil.e(y, 2, 1);
        } else {
            e = UnitUtil.e(y, 1, 1);
        }
        this.f.setText(e);
        g();
    }

    private String a(float f) {
        cfe cfeVar = this.l;
        if (cfeVar == null) {
            return UnitUtil.e(f, 1, 1);
        }
        return UnitUtil.e(f, 1, cfeVar.getFractionDigitByType(0));
    }

    private void d(float f, float f2) {
        float c;
        float e;
        IAxisValueFormatter valueFormatter;
        WeightTrendLineChart weightTrendLineChart = this.b;
        if (weightTrendLineChart == null) {
            LogUtil.h("WeightTrendChartViewHolder", "initAxis mLineChart is null");
            return;
        }
        HwHealthYAxis axisFirstParty = weightTrendLineChart.getAxisFirstParty();
        axisFirstParty.setDrawLabels(true);
        axisFirstParty.setDrawAxisLine(true);
        axisFirstParty.setDrawGridLines(true);
        if (f < 0.0f) {
            f = 0.0f;
        }
        LogUtil.a("WeightTrendChartViewHolder", "initYAxis : axisMinimum = ", Float.valueOf(f), " - axisMaximum = ", Float.valueOf(f2), " mBodyDataKey = ", this.e);
        if (this.e.equals("bodyWeight") || this.e.equals(BleConstants.BASAL_METABOLISM) || this.e.equals(BleConstants.MOISTURE_RATE) || this.e.equals("weight_fat_free") || this.e.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
            c = rag.c(f2, 4.0d, 0);
            e = rag.e(f, 4.0d, 0);
            valueFormatter = axisFirstParty.getValueFormatter();
        } else if (this.e.equals(BleConstants.BONE_SALT) || this.e.equals("waistHipRatioUser")) {
            c = rag.c(f2, 0.04d, 2);
            e = rag.e(f, 0.04d, 2);
            valueFormatter = a(2);
        } else {
            c = rag.c(f2, 0.4d, 1);
            e = rag.e(f, 0.4d, 1);
            valueFormatter = a(1);
        }
        LogUtil.a("WeightTrendChartViewHolder", "initYAxis : yMin = ", Float.valueOf(e), " - yMax = ", Float.valueOf(c), " mBodyDataKey = ", this.e);
        axisFirstParty.setAxisMinimum(e);
        axisFirstParty.setAxisMaximum(c * 1.05f);
        axisFirstParty.setValueFormatter(valueFormatter);
    }

    private IAxisValueFormatter a(final int i) {
        return new IAxisValueFormatter() { // from class: rar.2
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                int i2 = i;
                return new DecimalFormat(i2 == 1 ? "0.0" : i2 == 2 ? "0.00" : "0").format(f);
            }
        };
    }

    private void e(int i, float f, float f2) {
        WeightTrendLineChart weightTrendLineChart = this.b;
        if (weightTrendLineChart == null) {
            LogUtil.h("WeightTrendChartViewHolder", "initAxis mLineChart is null");
            return;
        }
        XAxis xAxis = weightTrendLineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawLabels(true);
        if (Math.min(Math.round(f) + 1, 7) < 7) {
            xAxis.setLabelCount(Math.min(Math.round(f) + 1, 7), true);
            xAxis.setAvoidFirstLastClipping(true);
        }
        xAxis.setAxisMinimum(i - f2);
        xAxis.setAxisMaximum(f + f2);
    }

    public void b(int i) {
        View view = this.o;
        if (view != null) {
            view.setVisibility(i);
        }
        View view2 = this.f16683a;
        if (view2 != null) {
            view2.setVisibility(i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void g() {
        char c;
        if (this.i == null) {
            LogUtil.a("WeightTrendChartViewHolder", "setWeightValueUnit() setWeightValueUnit is null");
        }
        d();
        int a2 = UnitUtil.a();
        String str = this.e;
        str.hashCode();
        switch (str.hashCode()) {
            case -2036100146:
                if (str.equals("thighGirth")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1634436792:
                if (str.equals(BleConstants.BASAL_METABOLISM)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1548018683:
                if (str.equals(BleConstants.MUSCLE_MASS)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1367765738:
                if (str.equals("calves")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -964620525:
                if (str.equals("armCircumference")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -912998224:
                if (str.equals("bustGirth")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -454701916:
                if (str.equals("waistGirth")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -54057286:
                if (str.equals("bodyWeight")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 3493065:
                if (str.equals("rasm")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 200416838:
                if (str.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 745974358:
                if (str.equals("skeletalMusclelMass")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 923919363:
                if (str.equals("hipline")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 944244070:
                if (str.equals(BleConstants.VISCERAL_FAT_LEVEL)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1000112569:
                if (str.equals("weight_fat_free")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1970433338:
                if (str.equals(BleConstants.BONE_SALT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 11:
                c();
                break;
            case 1:
                this.i.setText(BaseApplication.e().getString(R$string.IDS_hw_show_sport_cal_unit_new));
                break;
            case 2:
            case 7:
            case '\n':
            case '\r':
            case 14:
                d(a2);
                break;
            case '\b':
                this.i.setText(BaseApplication.e().getString(R$string.IDS_weight_kg_square));
                break;
            case '\t':
                this.i.setText(BaseApplication.e().getString(R$string.IDS_main_watch_heart_rate_unit_string));
                break;
            case '\f':
                this.i.setText(BaseApplication.e().getString(R$string.IDS_hw_show_haslet_unit));
                break;
            default:
                this.i.setVisibility(8);
                break;
        }
    }

    private void d() {
        if (LanguageUtil.w(BaseApplication.e()) && (BleConstants.BODY_FAT_RATE.equals(this.e) || BleConstants.MOISTURE_RATE.equals(this.e) || "protein".equals(this.e))) {
            this.m.setLayoutDirection(1);
        }
        if (LanguageUtil.ac(BaseApplication.e())) {
            if (BleConstants.BODY_FAT_RATE.equals(this.e) || BleConstants.MOISTURE_RATE.equals(this.e) || "protein".equals(this.e)) {
                this.m.setLayoutDirection(0);
            }
        }
    }

    private void c() {
        if (UnitUtil.h()) {
            this.i.setText(BaseApplication.e().getString(R$string.IDS_ins));
        } else {
            this.i.setText(BaseApplication.e().getString(R$string.IDS_cm));
        }
    }

    private void d(int i) {
        if (i == 1) {
            this.i.setText(BaseApplication.e().getString(R$string.IDS_device_weight_value_g));
        } else if (i == 3) {
            this.i.setText(BaseApplication.e().getString(R$string.IDS_device_measure_weight_value_unit_eng));
        } else {
            this.i.setText(BaseApplication.e().getString(R$string.IDS_unit_kg));
        }
    }

    public void e(String str) {
        this.e = str;
    }
}
