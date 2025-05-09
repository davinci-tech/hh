package com.huawei.health.pluginsleeplabelalgorithm;

import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.pluginsleeplabel.SleepLabelApi;
import com.huawei.sleep.sleepmgmt.SleepMgmtInterface;

@ApiDefine(uri = SleepLabelApi.class)
@Singleton
/* loaded from: classes3.dex */
public class SleepLabelAlgorithmImpl implements SleepLabelApi {
    @Override // com.huawei.pluginsleeplabel.SleepLabelApi
    public String getSleepMgmtVersion() {
        return SleepMgmtInterface.GetSleepMgmtVersion();
    }

    @Override // com.huawei.pluginsleeplabel.SleepLabelApi
    public String getDailySleepLabel(String str) {
        return SleepMgmtInterface.DailyProcessInterface(str);
    }

    @Override // com.huawei.pluginsleeplabel.SleepLabelApi
    public String getMonthlySleepLabel(String str) {
        return SleepMgmtInterface.MonthlyProcessInterface(str);
    }

    @Override // com.huawei.pluginsleeplabel.SleepLabelApi
    public String getQuestionnaireAnalysis(String str) {
        return SleepMgmtInterface.QuestionnaireProcessInterface(str);
    }

    @Override // com.huawei.pluginsleeplabel.SleepLabelApi
    public String getSleepAdviceOriginalInterface(String str, String str2) {
        return SleepMgmtInterface.SleepAdviceOriginalInterface(str, str2);
    }
}
