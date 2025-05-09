package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.trackview.SleepLineChartHolder;
import defpackage.ecz;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionEnvNoiseLineChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2686a;
    private LineChart b;
    private Context c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView f;
    private String g;
    private HealthTextView h;
    private SleepLineChartHolder i;
    private HealthTextView j;
    private HealthTextView k;
    private View l;
    private ImageView m;
    private HealthTextView n;
    private LinearLayout o;

    private int b(int i) {
        if (i <= 20) {
            return 0;
        }
        if (i <= 27) {
            return 1;
        }
        if (i <= 35) {
            return 2;
        }
        if (i <= 40) {
            return 3;
        }
        if (i <= 50) {
            return 4;
        }
        if (i <= 60) {
            return 5;
        }
        return i <= 74 ? 6 : 7;
    }

    public SectionEnvNoiseLineChart(Context context) {
        this(context, null);
    }

    public SectionEnvNoiseLineChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionEnvNoiseLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2686a = new ArrayList();
        this.i = null;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionEnvNoiseLineChart", "onCreateView");
        this.c = context;
        this.i = new SleepLineChartHolder(context);
        c();
        return this.l;
    }

    private void c() {
        if (this.l == null) {
            LogUtil.h("SectionEnvNoiseLineChart", "initView mainView is null, start to inflate");
            this.l = LayoutInflater.from(this.c).inflate(R.layout.section_envnoise_linechart, (ViewGroup) this, false);
        }
        this.o = (LinearLayout) this.l.findViewById(R.id.section_envnoise);
        this.b = (LineChart) this.l.findViewById(R.id.envnoise_linechart);
        if (LanguageUtil.bc(this.c)) {
            this.b.setRotationY(180.0f);
            LineChart lineChart = this.b;
            lineChart.setRendererLeftYAxis(new ecz(lineChart.getViewPortHandler(), this.b.getAxisLeft(), this.b.getTransformer(YAxis.AxisDependency.LEFT), true));
        }
        this.b.setNoDataText(this.c.getResources().getString(R$string.IDS_device_wifi_no_record));
        this.k = (HealthTextView) this.l.findViewById(R.id.env_noise_title);
        this.n = (HealthTextView) this.l.findViewById(R.id.unit_db);
        this.e = (ImageView) this.l.findViewById(R.id.moon);
        this.m = (ImageView) this.l.findViewById(R.id.sun);
        if (nsn.v(this.c)) {
            this.e.setColorFilter(this.c.getResources().getColor(R.color._2131297006_res_0x7f0902ee));
            this.m.setColorFilter(this.c.getResources().getColor(R.color._2131297006_res_0x7f0902ee));
        }
        this.h = (HealthTextView) this.l.findViewById(R.id.sleep_start_time);
        this.j = (HealthTextView) this.l.findViewById(R.id.sleep_end_time);
        this.f = (HealthTextView) this.l.findViewById(R.id.region_definition);
        this.d = (HealthTextView) this.l.findViewById(R.id.env_noise_description);
        if (LanguageUtil.af(this.c)) {
            this.d.setBreakStrategy(2);
            this.d.setHyphenationFrequency(2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionEnvNoiseLineChart", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionEnvNoiseLineChart", "no need to bind");
            return;
        }
        long d = nru.d((Map) hashMap, "SLEEP_START_TIME", 0L);
        LogUtil.a("SectionEnvNoiseLineChart", "start to set sleepStartTime");
        nsy.cMr_(this.h, b(d));
        LogUtil.a("SectionEnvNoiseLineChart", "start to set sleepEndTime");
        nsy.cMr_(this.j, b(nru.d((Map) hashMap, "SLEEP_END_TIME", 0L)));
        nsy.cMm_(this.e, (Drawable) nru.c(hashMap, "MOON_IMAGE", Drawable.class, null));
        nsy.cMm_(this.m, (Drawable) nru.c(hashMap, "SUN_IMAGE", Drawable.class, null));
        nsy.cMr_(this.k, nru.b(hashMap, "ENV_NOISE_TITLE", ""));
        nsy.cMr_(this.n, nru.b(hashMap, "ENV_NOISE_UNIT", ""));
        nsy.cMr_(this.f, nru.b(hashMap, "ENV_NOISE_REGION_DEFINITION", ""));
        LogUtil.a("SectionEnvNoiseLineChart", "start to set envNoiseDescription");
        int d2 = nru.d((Map) hashMap, "ENVIRONMENTAL_NOISE_AVERAGEDB", 20);
        String e = UnitUtil.e(d2, 1, 0);
        this.g = getResources().getStringArray(R.array._2130968625_res_0x7f040031)[b(d2)];
        nsy.cMr_(this.d, String.format(Locale.ENGLISH, this.g, e));
        LogUtil.a("SectionEnvNoiseLineChart", "start to set envNoiseDataList");
        String b = nru.b(hashMap, "ENVIRONMENTAL_NOISE_DATA_LIST", "");
        if (TextUtils.isEmpty(b)) {
            this.o.setVisibility(8);
            return;
        }
        this.o.setVisibility(0);
        this.f2686a.clear();
        c(b);
        this.i.a(this.f2686a);
        b();
    }

    private String b(long j) {
        return DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE);
    }

    private void c(String str) {
        if (str == null || str.length() < 3) {
            LogUtil.b("SectionEnvNoiseLineChart", "environmental noise data is empty!");
            return;
        }
        for (String str2 : str.substring(1, str.length() - 1).split(",")) {
            try {
                this.f2686a.add(Integer.valueOf(Integer.parseInt(str2)));
            } catch (NumberFormatException e) {
                LogUtil.b("SectionEnvNoiseLineChart", "mEnvNoiseDataList add exception", e.getMessage());
            }
        }
        LogUtil.a("SectionEnvNoiseLineChart", Integer.valueOf(this.f2686a.size()));
    }

    private void b() {
        this.i.e(this.b, new SleepLineChartHolder.a().e(!nrt.a(this.c)));
    }

    public void setRootLayoutVisibility(int i) {
        LinearLayout linearLayout = this.o;
        if (linearLayout != null) {
            linearLayout.setVisibility(i);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionEnvNoiseLineChart";
    }
}
