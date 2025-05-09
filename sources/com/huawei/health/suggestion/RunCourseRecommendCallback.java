package com.huawei.health.suggestion;

import java.util.List;

/* loaded from: classes4.dex */
public interface RunCourseRecommendCallback {
    void onFailure(int i, String str);

    void onSuccess(String str);

    void onSuccess(List<String> list);
}
