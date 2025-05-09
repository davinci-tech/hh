package com.huawei.ui.main.stories.fitness.activity.heartrate.provider;

import android.content.Context;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.ui.main.R$string;
import defpackage.pvy;
import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class HeartRateIntroduceProvider extends MinorProvider<pvy> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pvy pvyVar) {
        String string;
        if (pvyVar != null) {
            int a2 = pvyVar.a();
            if (a2 == 1) {
                string = context.getResources().getString(R$string.IDS_resting_heart_rate_details_string_new, 60, 100, 60);
            } else if (a2 == 2) {
                string = context.getResources().getString(R$string.IDS_resting_heart_rate_high_string, 50, 100, 10);
            } else if (a2 == 3) {
                string = context.getResources().getString(R$string.IDS_resting_heart_rate_low_string, 50, 100, 10);
            } else {
                LogUtil.d("HeartRateIntroduceProvider", "no heart rate reminder");
                string = "";
            }
            hashMap.put("REPORT_TEXT", string);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "HeartRateIntroduceProvider";
    }
}
