package com.huawei.ui.main.stories.fitness.activity.heartrate.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.api.CombineChartRangeShowCallback;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HorizontalHeartRateDayActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder;
import com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.HwHealthDetailCombinedChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.heartrate.linechart.RestHeartRateLineChart;
import defpackage.eah;
import defpackage.ebt;
import defpackage.eer;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.nom;
import defpackage.noy;
import defpackage.psk;
import defpackage.pvy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class HeartRateChartProvider extends MajorProvider<pvy> implements CombineChartRangeShowCallback {

    /* renamed from: a, reason: collision with root package name */
    private Observer f9858a;
    private HwHealthBaseScrollBarLineChart b;
    private Context c;
    protected SectionBean e;
    private int j;
    private Observer n;
    private DataInfos d = DataInfos.NoDataPlaceHolder;
    private long h = 0;
    private boolean f = true;
    private pvy i = new pvy();
    private final List<Integer> g = new ArrayList();

    private HashMap<String, Object> d(final Context context) {
        long currentTimeMillis;
        if (this.f9858a == null) {
            this.f9858a = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.2
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (objArr != null) {
                        Object obj = objArr[0];
                        if (obj instanceof HwHealthBaseScrollBarLineChart) {
                            HeartRateChartProvider.this.b = (HwHealthBaseScrollBarLineChart) obj;
                        }
                    }
                }
            };
        }
        ObserverManagerUtil.d(this.f9858a, "SECTION_CHART");
        if (this.n == null) {
            this.n = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.5
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (objArr != null) {
                        Object obj = objArr[0];
                        if (obj instanceof Integer) {
                            HeartRateChartProvider.this.j = ((Integer) obj).intValue();
                        }
                    }
                }
            };
        }
        ObserverManagerUtil.d(this.n, "CARD_ITEM_INDEX");
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        this.d = serializable instanceof DataInfos ? (DataInfos) serializable : DataInfos.NoDataPlaceHolder;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("DATA_INFO", this.d);
        if (extra != null) {
            currentTimeMillis = extra.getLong("key_bundle_health_last_data_time", System.currentTimeMillis());
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.h = currentTimeMillis;
        hashMap.put("START_TIME", Long.valueOf(currentTimeMillis));
        hashMap.put("CURSOR_UP_AVERAGE_UNIT", context.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        hashMap.put("HORIZONTAL_BTN", Integer.valueOf(R.drawable._2131429958_res_0x7f0b0a46));
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (str.equals("HORIZONTAL_BTN")) {
                    LogUtil.a("HeartRateGradualLineChartProvider", "horizontal jump onclick");
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = HeartRateChartProvider.this.b;
                    if (hwHealthBaseScrollBarLineChart == null) {
                        LogUtil.h("HeartRateGradualLineChartProvider", "horizontal jump failed, the context or chart is null");
                        return;
                    }
                    long queryMarkerViewTimeRangeMin = hwHealthBaseScrollBarLineChart.queryMarkerViewTimeRangeMin();
                    HashMap hashMap2 = new HashMap(1);
                    hashMap2.put("click", 1);
                    ixx.d().d(HeartRateChartProvider.this.c, AnalyticsValue.HEALTH_HEART_RATE_DAY_HORIZONTAL_2090011.value(), hashMap2, 0);
                    Intent intent = new Intent(context, (Class<?>) HorizontalHeartRateDayActivity.class);
                    intent.putExtra(ObserveredClassifiedView.JUMP_TIME_ID, nom.l(queryMarkerViewTimeRangeMin));
                    HeartRateChartProvider heartRateChartProvider = HeartRateChartProvider.this;
                    intent.putExtra(ObserveredClassifiedView.JUMP_DATA_LAYER_ID, heartRateChartProvider.b(heartRateChartProvider.j));
                    intent.putExtra(ObserveredClassifiedView.JUMP_DATA_TYPE, HeartRateChartProvider.this.d);
                    try {
                        HeartRateChartProvider.this.c.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b("HeartRateGradualLineChartProvider", "HorizontalHeartRateDayActivity", e.getMessage());
                    }
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (DataInfos.HeartRateDayDetail.equals(this.d)) {
            HeartRateLineChartHolder heartRateLineChartHolder = new HeartRateLineChartHolder(context);
            hashMap.put("HEALTH_CHART_HOLDER", heartRateLineChartHolder);
            hashMap.put("CUSTOM_CALCULATOR", new ArrayList<ICustomCalculator>(heartRateLineChartHolder.b(), heartRateLineChartHolder.a(), heartRateLineChartHolder.e(), heartRateLineChartHolder.d(), heartRateLineChartHolder.c()) { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.1
                final /* synthetic */ ICustomCalculator b;
                final /* synthetic */ ICustomCalculator c;
                final /* synthetic */ ICustomCalculator d;
                final /* synthetic */ ICustomCalculator e;
                final /* synthetic */ ICustomCalculator h;

                {
                    this.h = r2;
                    this.c = r3;
                    this.d = r4;
                    this.e = r5;
                    this.b = r6;
                    add(r2);
                    add(r3);
                    add(r4);
                    add(r5);
                    add(r6);
                }
            });
        } else {
            HeartRateBarChartHolder heartRateBarChartHolder = new HeartRateBarChartHolder(context);
            hashMap.put("HEALTH_CHART_HOLDER", heartRateBarChartHolder);
            hashMap.put("CUSTOM_CALCULATOR", new ArrayList<ICustomCalculator>(heartRateBarChartHolder.b(), heartRateBarChartHolder.a(), heartRateBarChartHolder.f(), heartRateBarChartHolder.h()) { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.4

                /* renamed from: a, reason: collision with root package name */
                final /* synthetic */ ICustomCalculator f9861a;
                final /* synthetic */ ICustomCalculator b;
                final /* synthetic */ ICustomCalculator c;
                final /* synthetic */ ICustomCalculator e;

                {
                    this.c = r2;
                    this.b = r3;
                    this.f9861a = r4;
                    this.e = r5;
                    add(r2);
                    add(r3);
                    add(r4);
                    add(r5);
                }
            });
        }
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
        hashMap.put("COMBINED_RANGE_SHOW_CALL_BACK", this);
        ArrayList arrayList = new ArrayList();
        if (this.d == DataInfos.HeartRateDayDetail) {
            arrayList.add(new RestHeartRateLineChart(context));
            arrayList.add(new RestHeartRateLineChart(context));
            arrayList.add(new RestHeartRateLineChart(context));
            arrayList.add(new RestHeartRateLineChart(context));
        } else {
            arrayList.add(new HwHealthDetailCombinedChart(context));
            arrayList.add(new HwHealthDetailCombinedChart(context));
            arrayList.add(new HwHealthDetailCombinedChart(context));
            arrayList.add(new HwHealthDetailCombinedChart(context));
        }
        hashMap.put("SECTION_CHART", arrayList);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(int i) {
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
        LogUtil.a("HeartRateGradualLineChartProvider", "acquireLayerId fail");
        return HwHealthChartHolder.LAYER_ID_NORMAL_HR;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.e = sectionBean;
        HashMap<String, Object> d = d(BaseApplication.getContext());
        sectionBean.a(d);
        this.i.c(((Long) d.get("START_TIME")).longValue());
        sectionBean.e(this.i);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.c = context;
    }

    public void b(Map<Long, IStorageModel> map) {
        if (map == null || map.size() == 0) {
            this.i.d("--");
            return;
        }
        float f = -2.1474836E9f;
        float f2 = 2.1474836E9f;
        for (IStorageModel iStorageModel : map.values()) {
            if (iStorageModel instanceof StorageGenericModel) {
                StorageGenericModel storageGenericModel = (StorageGenericModel) iStorageModel;
                if (this.d == DataInfos.HeartRateDayDetail) {
                    float i = noy.i(storageGenericModel);
                    if (i > f) {
                        f = i;
                    }
                    if (i < f2) {
                        f2 = i;
                    }
                } else {
                    f = noy.h(storageGenericModel);
                    f2 = noy.a(storageGenericModel);
                }
            }
        }
        this.i.d(((int) f2) + Constants.LINK + ((int) f));
        notifyMinorProviders(this.i);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pvy pvyVar) {
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.f));
        if (!this.f) {
            hashMap.put("START_TIME", Long.valueOf(pvyVar.c()));
        }
        this.f = false;
        long t = jdl.t(this.h);
        long e = jdl.e(this.h);
        HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
        bVar.e(this.d);
        bVar.e(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        eer.b().queryStepDayData(context, t, e, this.d, bVar, new ResponseCallback<Map<Long, IStorageModel>>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.9
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Map<Long, IStorageModel> map) {
                HeartRateChartProvider.this.b(map);
            }
        });
        bVar.e(HwHealthChartHolder.LAYER_ID_REST_HR);
        eer.b().queryStepDayData(context, t, e, this.d, bVar, new ResponseCallback<Map<Long, IStorageModel>>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.8
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Map<Long, IStorageModel> map) {
                HeartRateChartProvider.this.d(map);
            }
        });
        bVar.e(HwHealthChartHolder.LAYER_ID_WARNING_HR);
        eer.b().queryStepDayData(context, t, e, this.d, bVar, new ResponseCallback<Map<Long, IStorageModel>>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.10
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Map<Long, IStorageModel> map) {
                HeartRateChartProvider.this.e(map);
            }
        });
        bVar.e(HwHealthChartHolder.LAYER_ID_BRADYCARDIA);
        eer.b().queryStepDayData(context, t, e, this.d, bVar, new ResponseCallback<Map<Long, IStorageModel>>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateChartProvider.6
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Map<Long, IStorageModel> map) {
                HeartRateChartProvider.this.a(map);
            }
        });
    }

    public void d(Map<Long, IStorageModel> map) {
        if (map == null || map.size() == 0) {
            this.i.a("--");
            return;
        }
        float f = 0.0f;
        for (IStorageModel iStorageModel : map.values()) {
            if (iStorageModel instanceof StorageGenericModel) {
                f += noy.i((StorageGenericModel) iStorageModel);
            }
        }
        this.i.a(String.valueOf((int) (f / map.size())));
        notifyMinorProviders(this.i);
    }

    public void a(Map<Long, IStorageModel> map) {
        if (map == null || map.size() == 0) {
            this.i.c("--");
            return;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (IStorageModel iStorageModel : map.values()) {
            if (iStorageModel instanceof StorageGenericModel) {
                for (Object obj : ((StorageGenericModel) iStorageModel).e("BRADYCARDIA_DETAIL")) {
                    if (obj instanceof psk) {
                        psk pskVar = (psk) obj;
                        if (pskVar.e() > i) {
                            i = pskVar.e();
                        }
                        if (pskVar.c() < i2) {
                            i2 = pskVar.c();
                        }
                    }
                }
            }
        }
        this.i.e(i2 + Constants.LINK + i);
        notifyMinorProviders(this.i);
    }

    public void e(Map<Long, IStorageModel> map) {
        if (map == null || map.size() == 0) {
            this.i.c("--");
            return;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (IStorageModel iStorageModel : map.values()) {
            if (iStorageModel instanceof StorageGenericModel) {
                for (Object obj : ((StorageGenericModel) iStorageModel).e("HR_WARNING_DETAIL")) {
                    if (obj instanceof psk) {
                        psk pskVar = (psk) obj;
                        if (pskVar.e() > i) {
                            i = pskVar.e();
                        }
                        if (pskVar.c() < i2) {
                            i2 = pskVar.c();
                        }
                    }
                }
            }
        }
        this.i.c(i2 + Constants.LINK + i);
        notifyMinorProviders(this.i);
    }

    @Override // com.huawei.health.knit.api.CombineChartRangeShowCallback
    public void onRangeChange(ebt ebtVar) {
        this.i.b(ebtVar.b());
        notifyMinorProviders(this.i);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.g, new eah("HeartRateGradualLineChartProvider_" + this.d));
    }
}
