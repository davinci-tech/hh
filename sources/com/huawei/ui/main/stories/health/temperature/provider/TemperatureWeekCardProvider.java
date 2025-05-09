package com.huawei.ui.main.stories.health.temperature.provider;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureWarningActivity;
import defpackage.gge;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qny;
import defpackage.qpg;
import defpackage.qpm;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class TemperatureWeekCardProvider extends MinorProvider<qpg> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10247a = "KnitHealthData_CardSelected_" + DataInfos.TemperatureWeekDetail;
    private Context b;
    private boolean e = Utils.o();

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        sectionBean.a(e(BaseApplication.getContext()));
    }

    private HashMap<String, Object> e(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        LogUtil.a("TemperatureWeekCardProvider", "generateDefaultViewMap");
        hashMap.put("REMIND_SHOW_MODEL", false);
        hashMap.put("REMIND_TEXT", context.getString(R$string.IDS_temperature_warning));
        hashMap.put("REMIND_HISTORY_TEXT", context.getString(R$string.IDS_temperature_history_warning));
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qpg qpgVar) {
        LogUtil.a("TemperatureWeekCardProvider", "parseParams");
        this.b = context;
        Map<String, SpannableStringBuilder> a2 = qpgVar.a();
        qny qnyVar = new qny();
        if (!a2.isEmpty()) {
            LogUtil.a("TemperatureWeekCardProvider", "putTemperature");
            a2.put("TEMPERATURE_MIN_MAX", a2.get("TEMPERATURE_MIN_MAX"));
            a2.put("SKIN_TEMPERATURE_MIN_MAX", a2.get("SKIN_TEMPERATURE_MIN_MAX"));
        }
        hashMap.put("TEMPERATURE_CARD_DATA", qnyVar.d(a2, nsf.h(R$string.IDS_settings_health_temperature), nsf.h(R$string.IDS_health_skin_temperature)));
        a(hashMap, qpgVar);
        e(hashMap);
    }

    private void a(HashMap<String, Object> hashMap, qpg qpgVar) {
        if (this.e) {
            hashMap.put("IS_REMIND_SHOW", false);
        } else if (qpm.d()) {
            hashMap.put("IS_REMIND_SHOW", true);
        } else {
            hashMap.put("IS_REMIND_SHOW", false);
        }
    }

    private void e(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureWeekCardProvider.4
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
                if ("TEMPERATURE_SELECT".equals(str)) {
                    LogUtil.a("TemperatureWeekCardProvider", "begin to sendBroadcast");
                    ObserverManagerUtil.c(TemperatureWeekCardProvider.f10247a, "TEMPERATURE_MIN_MAX");
                    return;
                }
                if ("SKIN_TEMPERATURE_SELECT".equals(str)) {
                    ObserverManagerUtil.c(TemperatureWeekCardProvider.f10247a, "SKIN_TEMPERATURE_MIN_MAX");
                    return;
                }
                if ("REMIND_HISTORY_VIEW".equals(str)) {
                    if (nsn.o()) {
                        LogUtil.b("TemperatureWeekCardProvider", "remind histroy text isFastClick");
                        return;
                    }
                    gge.e(AnalyticsValue.TEMPERATURE_REMIND_2060078.value());
                    TemperatureWeekCardProvider.this.b.startActivity(new Intent(TemperatureWeekCardProvider.this.b, (Class<?>) TemperatureWarningActivity.class));
                    return;
                }
                LogUtil.a("TemperatureWeekCardProvider", "useless click");
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qpg qpgVar) {
        LogUtil.a("TemperatureWeekCardProvider", "onSetSectionBeanData");
        if (qpgVar.a().isEmpty() || this.e) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(qpgVar);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "TemperatureWeekCardProvider";
    }
}
