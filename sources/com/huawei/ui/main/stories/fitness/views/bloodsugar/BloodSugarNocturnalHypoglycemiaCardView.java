package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaCardView;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView;
import defpackage.deb;
import defpackage.ggl;
import defpackage.kor;
import defpackage.kpi;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class BloodSugarNocturnalHypoglycemiaCardView extends LinearLayout {
    private static float c;

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9962a;
    private final Context b;
    private final Handler d;
    private BloodSugarNocturnalHypoglycemiaChartView e;
    private HealthTextView f;
    private long g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;

    public BloodSugarNocturnalHypoglycemiaCardView(Context context) {
        this(context, null);
    }

    public BloodSugarNocturnalHypoglycemiaCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 0L;
        this.b = context;
        this.d = new c(this);
        e();
        d();
        setVisibility(8);
    }

    private void e() {
        LayoutInflater.from(this.b).inflate(R.layout.layout_blood_sugar_nocturnal_hypoglycemia_card_view, this);
        this.i = (LinearLayout) findViewById(R.id.chart_layout);
        this.f = (HealthTextView) findViewById(R.id.frequency_text);
        this.h = (HealthTextView) findViewById(R.id.duration_text);
        this.f9962a = (HealthTextView) findViewById(R.id.fall_time_text);
        this.j = (HealthTextView) findViewById(R.id.wakeup_time_text);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.hw_hsh_detection_distribution_title);
        healthSubHeader.setVisibility(0);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        healthSubHeader.setHeadTitleText(this.b.getString(R$string.IDS_nocturnal_hypoglycemia));
        healthSubHeader.setPaddingStartEnd(0.0f, 0.0f);
    }

    private void d() {
        float[] b = deb.b();
        if (b.length < 2) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaCardView", "get thresholds error");
            return;
        }
        float f = b[1];
        c = f;
        LogUtil.d("BloodSugarNocturnalHypoglycemiaCardView", "get thresholds mThresholdMin:", Float.valueOf(f));
    }

    public void c() {
        this.g = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setStatisticsData(BloodSugarNocturnalHypoglycemiaCardView bloodSugarNocturnalHypoglycemiaCardView) {
        BloodSugarNocturnalHypoglycemiaChartView bloodSugarNocturnalHypoglycemiaChartView = bloodSugarNocturnalHypoglycemiaCardView.e;
        if (bloodSugarNocturnalHypoglycemiaChartView == null) {
            LogUtil.c("BloodSugarNocturnalHypoglycemiaCardView", "setStatisticsData mBloodSugarNocturnalHypoglycemiaChartView is null");
            return;
        }
        long startTime = bloodSugarNocturnalHypoglycemiaChartView.getStartTime();
        long endTime = bloodSugarNocturnalHypoglycemiaCardView.e.getEndTime();
        String a2 = ggl.a(new Date(startTime), "M/d");
        String a3 = ggl.a(new Date(endTime), "M/d");
        bloodSugarNocturnalHypoglycemiaCardView.f9962a.setText(a2);
        bloodSugarNocturnalHypoglycemiaCardView.j.setText(a3);
        if (bloodSugarNocturnalHypoglycemiaCardView.e.getHypoglycemiaNum() == 0) {
            bloodSugarNocturnalHypoglycemiaCardView.setVisibility(8);
        }
        Resources resources = BaseApplication.getContext().getResources();
        String quantityString = resources.getQuantityString(R.plurals._2130903345_res_0x7f030131, bloodSugarNocturnalHypoglycemiaCardView.e.getHypoglycemiaNum(), Integer.valueOf(bloodSugarNocturnalHypoglycemiaCardView.e.getHypoglycemiaNum()));
        String quantityString2 = resources.getQuantityString(R.plurals._2130903041_res_0x7f030001, bloodSugarNocturnalHypoglycemiaCardView.e.getHypoglycemiaAverageDuration(), Integer.valueOf(bloodSugarNocturnalHypoglycemiaCardView.e.getHypoglycemiaAverageDuration()));
        LogUtil.d("BloodSugarNocturnalHypoglycemiaCardView", "setStatisticsData frequency:", quantityString, ",duration:", quantityString2);
        bloodSugarNocturnalHypoglycemiaCardView.f.setText(quantityString);
        bloodSugarNocturnalHypoglycemiaCardView.h.setText(quantityString2);
    }

    private void a(long j) {
        kpi.a().c(j / 1000, 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 1, new e(this));
    }

    public void setDate(long j) {
        long d = TimeUtil.d(j * 60000);
        if (this.g == d) {
            return;
        }
        setVisibility(8);
        LogUtil.d("BloodSugarNocturnalHypoglycemiaCardView", "setDate: ", ggl.a(new Date(d), "yyyy-MM-dd HH:mm:ss"));
        this.g = d;
        a(d);
    }

    /* loaded from: classes9.dex */
    public static class c extends BaseHandler<BloodSugarNocturnalHypoglycemiaCardView> {
        c(BloodSugarNocturnalHypoglycemiaCardView bloodSugarNocturnalHypoglycemiaCardView) {
            super(bloodSugarNocturnalHypoglycemiaCardView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dwk_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(final BloodSugarNocturnalHypoglycemiaCardView bloodSugarNocturnalHypoglycemiaCardView, Message message) {
            if (message.what == 0) {
                bloodSugarNocturnalHypoglycemiaCardView.i.removeAllViews();
                if (message.obj instanceof a) {
                    bloodSugarNocturnalHypoglycemiaCardView.e = new BloodSugarNocturnalHypoglycemiaChartView(bloodSugarNocturnalHypoglycemiaCardView.b);
                    bloodSugarNocturnalHypoglycemiaCardView.e.setBloodSugarStandardValue(BloodSugarNocturnalHypoglycemiaCardView.c);
                    a aVar = (a) message.obj;
                    bloodSugarNocturnalHypoglycemiaCardView.e.setStartTime(aVar.e().longValue());
                    bloodSugarNocturnalHypoglycemiaCardView.e.setEndTime(aVar.c().longValue());
                    bloodSugarNocturnalHypoglycemiaCardView.e.setData(aVar.b());
                    bloodSugarNocturnalHypoglycemiaCardView.e.setLoadingListener(new BloodSugarNocturnalHypoglycemiaChartView.LoadingListener() { // from class: pzs
                        @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView.LoadingListener
                        public final void onCompleted() {
                            BloodSugarNocturnalHypoglycemiaCardView.setStatisticsData(BloodSugarNocturnalHypoglycemiaCardView.this);
                        }
                    });
                    bloodSugarNocturnalHypoglycemiaCardView.i.addView(bloodSugarNocturnalHypoglycemiaCardView.e, new LinearLayout.LayoutParams(-1, -1));
                    bloodSugarNocturnalHypoglycemiaCardView.setVisibility(0);
                } else {
                    LogUtil.c("BloodSugarNocturnalHypoglycemiaCardView", "handleMessageWhenReferenceNotNull HANDLER_GET_CORE_SLEEP NocturnalHypoglycemiaBean is null");
                    return;
                }
            }
            if (message.what == 1) {
                bloodSugarNocturnalHypoglycemiaCardView.setVisibility(8);
            }
            if (message.what == 2) {
                BloodSugarNocturnalHypoglycemiaCardView.setStatisticsData(bloodSugarNocturnalHypoglycemiaCardView);
            }
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private Long f9963a;
        private Long b;
        private Map<Long, Float> e;

        private a() {
        }

        public Long e() {
            return this.f9963a;
        }

        public void a(Long l) {
            this.f9963a = l;
        }

        public Long c() {
            return this.b;
        }

        public void b(Long l) {
            this.b = l;
        }

        public Map<Long, Float> b() {
            return this.e;
        }

        public void d(Map<Long, Float> map) {
            this.e = map;
        }
    }

    public static class e implements IBaseResponseCallback {
        private WeakReference<BloodSugarNocturnalHypoglycemiaCardView> b;

        e(BloodSugarNocturnalHypoglycemiaCardView bloodSugarNocturnalHypoglycemiaCardView) {
            this.b = new WeakReference<>(bloodSugarNocturnalHypoglycemiaCardView);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0 && obj != null && (obj instanceof List)) {
                List list = (List) obj;
                LogUtil.b("BloodSugarNocturnalHypoglycemiaCardView", "onResponse: ", list.toString());
                final Date date = new Date(((SleepTotalData) list.get(0)).getCoreSleepFallTime());
                final Date date2 = new Date(((SleepTotalData) list.get(0)).getCoreSleepWakeupTime());
                if (date.getTime() == date2.getTime()) {
                    return;
                }
                kor.e(date.getTime(), date2.getTime(), 0, new IBaseResponseCallback() { // from class: pzt
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj2) {
                        BloodSugarNocturnalHypoglycemiaCardView.e.this.d(date, date2, i2, obj2);
                    }
                });
            }
        }

        public /* synthetic */ void d(Date date, Date date2, int i, Object obj) {
            BloodSugarNocturnalHypoglycemiaCardView bloodSugarNocturnalHypoglycemiaCardView = this.b.get();
            if (bloodSugarNocturnalHypoglycemiaCardView == null) {
                return;
            }
            Message obtain = Message.obtain();
            if (obj instanceof List) {
                List<HiHealthData> list = (List) obj;
                if (!list.isEmpty()) {
                    a aVar = new a();
                    aVar.a(Long.valueOf(date.getTime()));
                    aVar.b(Long.valueOf(date2.getTime()));
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData != null) {
                            linkedHashMap.put(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf(hiHealthData.getFloatValue()));
                        }
                    }
                    aVar.d(linkedHashMap);
                    obtain.obj = aVar;
                    obtain.what = 0;
                    bloodSugarNocturnalHypoglycemiaCardView.d.sendMessage(obtain);
                }
            }
            obtain.what = 1;
            bloodSugarNocturnalHypoglycemiaCardView.d.sendMessage(obtain);
        }
    }
}
