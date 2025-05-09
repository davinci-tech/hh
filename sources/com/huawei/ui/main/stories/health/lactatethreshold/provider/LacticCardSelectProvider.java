package com.huawei.ui.main.stories.health.lactatethreshold.provider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import defpackage.eal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class LacticCardSelectProvider extends BaseKnitDataProvider {
    private DataInfos d = DataInfos.NoDataPlaceHolder;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.a(b(BaseApplication.getContext()));
        sectionBean.e(new Object());
    }

    private void c() {
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

    private HashMap<String, Object> b(Context context) {
        c();
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(context.getResources().getString(R$string.IDS_lactate_threshold_heart_rate));
        arrayList.add(context.getResources().getString(R$string.IDS_lactate_threshold_pace));
        hashMap.put("LEFT_MIDDLE_TEXT", arrayList);
        hashMap.put("SELECTED_BACKGROUND", Integer.valueOf(R.drawable._2131431315_res_0x7f0b0f93));
        hashMap.put("UNSELECTED_BACKGROUND", Integer.valueOf(R.drawable._2131431314_res_0x7f0b0f92));
        hashMap.put("SELECTED_TEXT_COLOR", Integer.valueOf(context.getColor(R.color._2131299238_res_0x7f090ba6)));
        hashMap.put("UNSELECTED_TEXT_COLOR", Integer.valueOf(context.getColor(R.color._2131299241_res_0x7f090ba9)));
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.lactatethreshold.provider.LacticCardSelectProvider.5
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
                ObserverManagerUtil.c(eal.e(LacticCardSelectProvider.this.d), Integer.valueOf(i));
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "LacticCardSelectProvider";
    }
}
