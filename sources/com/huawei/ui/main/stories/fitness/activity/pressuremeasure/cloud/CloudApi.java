package com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud;

import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import java.util.Map;

/* loaded from: classes6.dex */
public interface CloudApi {
    void downloadFile(String str, String str2, DataCallback dataCallback);

    void getDoctorBasicInfo(DataCallback dataCallback);

    void getDoctorImInfo(DataCallback dataCallback);

    void queryExclusiveGuardianStatus(Map<String, Integer> map, DataCallback dataCallback);
}
