package com.huawei.phoneservice.feedback.mvp.contract;

import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.feedback.mvp.base.g;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import java.util.List;

/* loaded from: classes5.dex */
public interface e extends g {
    void a(List<FeedBackResponse.ProblemEnity> list, List<FeedBackResponse.ProblemEnity> list2);

    void setErrorView(FaqConstants.FaqErrorCode faqErrorCode);

    void setThrowableView(Throwable th);
}
