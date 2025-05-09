package com.huawei.ui.main.stories.health.lactatethreshold.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.utils.CalendarChangeCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.LacticHorizontalActivity;
import com.huawei.ui.main.stories.health.lactatethreshold.chart.LacticChartHolder;
import defpackage.eal;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class LacticLineChartProvider extends BaseKnitDataProvider implements CalendarChangeCallBack {
    private Context e;
    private DataInfos c = DataInfos.NoDataPlaceHolder;
    private long d = 0;
    private String h = "lthrHr";
    private boolean b = true;

    /* renamed from: a, reason: collision with root package name */
    private Observer f10205a = new Observer() { // from class: com.huawei.ui.main.stories.health.lactatethreshold.provider.LacticLineChartProvider.3
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            Object obj;
            if (!LacticLineChartProvider.this.c().equals(str) || objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                return;
            }
            int intValue = ((Integer) obj).intValue();
            LacticLineChartProvider.this.h = intValue == 0 ? "lthrHr" : "lthrPace";
        }
    };

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        super.parseParams(context, hashMap, obj);
        hashMap.put("FIRST_TIME_BIND", Boolean.valueOf(this.b));
        this.b = false;
    }

    private HashMap<String, Object> e(Context context) {
        d();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("CURSOR_UP_AVERAGE_UNIT", Integer.valueOf(R$string.IDS_main_watch_heart_rate_unit_string));
        hashMap.put("DATA_INFO", this.c);
        hashMap.put("START_TIME", Long.valueOf(this.d));
        hashMap.put("RANGE_SHOW_CALL_BACK", this);
        hashMap.put("HEALTH_CHART_HOLDER", new LacticChartHolder(context));
        hashMap.put("CalendarChangeCallBack", this);
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.lactatethreshold.provider.LacticLineChartProvider.4
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
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LacticLineChartProvider.this.e == null) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                Intent intent = new Intent(LacticLineChartProvider.this.e, (Class<?>) LacticHorizontalActivity.class);
                Bundle extra = LacticLineChartProvider.this.getExtra();
                extra.putLong("key_bundle_health_last_data_time", LacticLineChartProvider.this.d);
                extra.putString("key_bundle_health_line_chart_show_data_type", LacticLineChartProvider.this.h);
                intent.putExtras(extra);
                try {
                    LacticLineChartProvider.this.e.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("TAG", " to LacticHorizontalActivity ", e.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return hashMap;
    }

    @Override // com.huawei.health.knit.section.utils.CalendarChangeCallBack
    public void onChange(long j) {
        this.d = j;
    }

    private void d() {
        Bundle extra = getExtra();
        DataInfos dataInfos = DataInfos.NoDataPlaceHolder;
        long j = 0;
        if (extra != null) {
            long j2 = extra.getLong("key_bundle_health_last_data_time", 0L);
            j = j2 == 0 ? System.currentTimeMillis() : j2;
            Serializable serializable = extra.getSerializable("key_bundle_health_line_chart_data_infos");
            if (serializable instanceof DataInfos) {
                dataInfos = (DataInfos) serializable;
            }
        }
        this.c = dataInfos;
        this.d = j;
        ObserverManagerUtil.d(this.f10205a, c());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.a(e(BaseApplication.getContext()));
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        super.loadData(context, sectionBean);
        this.e = context;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        ObserverManagerUtil.e(this.f10205a, c());
    }

    protected String c() {
        return eal.e(this.c);
    }
}
