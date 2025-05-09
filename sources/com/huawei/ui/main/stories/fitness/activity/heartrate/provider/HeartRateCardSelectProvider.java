package com.huawei.ui.main.stories.fitness.activity.heartrate.provider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import defpackage.pvy;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class HeartRateCardSelectProvider extends MinorProvider<pvy> {
    private DataInfos d = DataInfos.NoDataPlaceHolder;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.a(d(BaseApplication.getContext()));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pvy pvyVar) {
        LogUtil.d("HeartRateCardSelectProvider", "parseParams");
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(pvyVar.d());
        arrayList.add(pvyVar.e());
        arrayList.add(pvyVar.b());
        arrayList.add(pvyVar.i());
        hashMap.put("CARD_DATA_TEXT", arrayList);
    }

    private void b() {
        Bundle extra = getExtra();
        DataInfos dataInfos = DataInfos.NoDataPlaceHolder;
        if (extra != null) {
            Serializable serializable = extra.getSerializable("key_bundle_health_line_chart_data_infos");
            if (serializable instanceof DataInfos) {
                dataInfos = (DataInfos) serializable;
            }
        }
        this.d = dataInfos;
    }

    private HashMap<String, Object> d(Context context) {
        b();
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(context.getResources().getString(R$string.IDS_hw_health_show_healthdata_heartrate_range));
        if (this.d == DataInfos.HeartRateDayDetail) {
            arrayList.add(context.getResources().getString(R$string.IDS_resting_heart_rate_string));
        } else {
            arrayList.add(context.getResources().getString(R$string.IDS_hw_health_show_healthdata_avg_rest_heartrate));
        }
        arrayList.add(context.getResources().getString(R$string.IDS_heartrate_raise_alarm));
        arrayList.add(context.getResources().getString(R$string.IDS_heartrate_bradycardia_alarm));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("CARD_TITLE_TEXT", arrayList);
        ArrayList arrayList2 = new ArrayList(4);
        arrayList2.add(context.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        arrayList2.add(context.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        arrayList2.add(context.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        arrayList2.add(context.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
        hashMap.put("CARD_PIECE_TEXT", arrayList2);
        hashMap.put("RECYCLERVIEW_STYLE", 1);
        hashMap.put("SELECTED_BACKGROUND", Integer.valueOf(R.drawable._2131427582_res_0x7f0b00fe));
        hashMap.put("UNSELECTED_BACKGROUND", Integer.valueOf(R.drawable._2131427568_res_0x7f0b00f0));
        hashMap.put("SELECTED_TEXT_COLOR", Integer.valueOf(context.getColor(R.color._2131299238_res_0x7f090ba6)));
        hashMap.put("UNSELECTED_TEXT_COLOR", Integer.valueOf(context.getColor(R.color._2131299241_res_0x7f090ba9)));
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateCardSelectProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                ObserverManagerUtil.c("CARD_ITEM_INDEX", Integer.valueOf(i));
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, pvy pvyVar) {
        LogUtil.d("HeartRateCardSelectProvider", "onSetSectionBeanData");
        sectionBean.e(pvyVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "HeartRateCardSelectProvider";
    }
}
