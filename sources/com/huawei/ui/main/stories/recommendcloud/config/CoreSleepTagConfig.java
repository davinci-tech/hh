package com.huawei.ui.main.stories.recommendcloud.config;

import com.huawei.ui.main.stories.recommendcloud.data.CoreSleepNoticationData;
import com.huawei.ui.main.stories.recommendcloud.data.SleepRecommendData;
import java.util.List;

/* loaded from: classes7.dex */
public class CoreSleepTagConfig {
    private List<SleepRecommendData> coreSleepTagList;
    private String coreSleepTime;
    private String module_name;
    private List<CoreSleepNoticationData> notificationTagList;
    private String recommondSleepTime;
    private String recommondSleepTitle;

    private CoreSleepTagConfig() {
    }

    public String getCoreSleepTime() {
        return this.coreSleepTime;
    }

    public String getModulename() {
        return this.module_name;
    }

    public List<SleepRecommendData> getCoreSleepTagList() {
        return this.coreSleepTagList;
    }
}
