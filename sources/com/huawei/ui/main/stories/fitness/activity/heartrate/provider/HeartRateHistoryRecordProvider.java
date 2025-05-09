package com.huawei.ui.main.stories.fitness.activity.heartrate.provider;

import android.content.Context;
import android.view.View;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.BradycardiaAlarmActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.WarningHRActivity;
import defpackage.pvy;
import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class HeartRateHistoryRecordProvider extends MinorProvider<pvy> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(final Context context, HashMap<String, Object> hashMap, pvy pvyVar) {
        LogUtil.d("HeartRateHistoryRecordButtonProvider", "parseParams");
        hashMap.put("SINGLE_BUTTON", context.getString(R$string.IDS_hw_base_health_data_history));
        hashMap.put("CARD_INDEX", Integer.valueOf(pvyVar.a()));
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.provider.HeartRateHistoryRecordProvider.2
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
                if (i == 2) {
                    WarningHRActivity.e(context);
                } else if (i == 3) {
                    BradycardiaAlarmActivity.b(context);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, pvy pvyVar) {
        LogUtil.d("HeartRateHistoryRecordButtonProvider", "onSetSectionBeanData");
        sectionBean.e(pvyVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "HeartRateHistoryRecordButtonProvider";
    }
}
