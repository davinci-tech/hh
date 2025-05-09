package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionRingChartAdapter;
import com.huawei.health.knit.section.adapter.SectionRingChartData;
import com.huawei.health.knit.section.view.SectionRingChart;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthChart;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eet;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionRingChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f2724a;
    private HealthTextView b;
    private LinearLayout c;
    private HealthChart d;
    private Context e;
    private SectionRingChartAdapter f;

    public SectionRingChart(Context context) {
        this(context, null);
    }

    public SectionRingChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionRingChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(final Context context) {
        LogUtil.a("Section_Ring_Chart", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_ring_chart_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.c = linearLayout;
        linearLayout.post(new Runnable() { // from class: ehi
            @Override // java.lang.Runnable
            public final void run() {
                SectionRingChart.this.a(context);
            }
        });
        this.d = (HealthChart) inflate.findViewById(R.id.section_ring_chart);
        this.b = (HealthTextView) inflate.findViewById(R.id.section_ring_chart_desc);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section_ring_chart_recycler_view);
        this.f2724a = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context));
        this.f2724a.setHasFixedSize(true);
        this.f2724a.setNestedScrollingEnabled(false);
        this.f2724a.a(false);
        this.f2724a.d(false);
        return inflate;
    }

    public /* synthetic */ void a(Context context) {
        this.c.setMinimumWidth(nsn.h(context));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        char c;
        LogUtil.a("Section_Ring_Chart", "bindParamsToView");
        Set<Map.Entry<String, Object>> entrySet = hashMap.entrySet();
        SectionRingChartData sectionRingChartData = new SectionRingChartData();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            key.hashCode();
            switch (key.hashCode()) {
                case -1765745265:
                    if (key.equals("SHOW_VALUE")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1279648541:
                    if (key.equals("SHOW_PERCENT")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1266091731:
                    if (key.equals("CHART_ITEM_TITLE")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1264490426:
                    if (key.equals("CHART_ITEM_VALUE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -928338975:
                    if (key.equals("RING_CHART_DESC")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -464202942:
                    if (key.equals("CHART_ITEM_BEGIN_COLOR")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 327535368:
                    if (key.equals("CHART_ITEM_EDN_COLOR")) {
                        c = 6;
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
                    Object value = entry.getValue();
                    if (value instanceof Boolean) {
                        sectionRingChartData.d(((Boolean) value).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case 1:
                    Object value2 = entry.getValue();
                    if (value2 instanceof Boolean) {
                        sectionRingChartData.a(((Boolean) value2).booleanValue());
                        break;
                    } else {
                        break;
                    }
                case 2:
                    Object value3 = entry.getValue();
                    if (eet.e(value3)) {
                        sectionRingChartData.a((List<String>) value3);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    Object value4 = entry.getValue();
                    if (eet.e(value4)) {
                        sectionRingChartData.c((List) value4);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    Object value5 = entry.getValue();
                    if (eet.f(value5)) {
                        this.b.setText(e((String) value5));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    Object value6 = entry.getValue();
                    if (eet.e(value6)) {
                        sectionRingChartData.d((List<Integer>) value6);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    Object value7 = entry.getValue();
                    if (eet.e(value7)) {
                        sectionRingChartData.b((List) value7);
                        break;
                    } else {
                        break;
                    }
            }
        }
        setRecycleViewData(sectionRingChartData);
    }

    public String e(String str) {
        if (str.length() < 4) {
            return str;
        }
        int length = str.length();
        int i = length / 2;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(str.charAt(i2));
            if (i2 == i - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public void setRootLayoutVisibility(int i) {
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            linearLayout.setVisibility(i);
        }
    }

    private void setRecycleViewData(SectionRingChartData sectionRingChartData) {
        int size = sectionRingChartData.a().size();
        int[] iArr = new int[size];
        int[] iArr2 = new int[sectionRingChartData.c().size()];
        int[] iArr3 = new int[sectionRingChartData.d().size()];
        for (int i = 0; i < size; i++) {
            iArr[i] = sectionRingChartData.a().get(i).intValue();
            iArr2[i] = sectionRingChartData.c().get(i).intValue();
            iArr3[i] = sectionRingChartData.d().get(i).intValue();
        }
        this.d.setChartData(sectionRingChartData.e(), iArr, iArr2, iArr3);
        SectionRingChartAdapter sectionRingChartAdapter = new SectionRingChartAdapter(this.e, sectionRingChartData);
        this.f = sectionRingChartAdapter;
        this.f2724a.setAdapter(sectionRingChartAdapter);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Ring_Chart";
    }
}
