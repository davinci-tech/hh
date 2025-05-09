package com.huawei.phoneservice.feedbackcommon.utils;

import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import java.util.List;

/* loaded from: classes9.dex */
public interface SdkFeedBackCallback {
    void setListView(List<FeedBackResponse.ProblemEnity> list);

    void setThrowableView(Throwable th);
}
