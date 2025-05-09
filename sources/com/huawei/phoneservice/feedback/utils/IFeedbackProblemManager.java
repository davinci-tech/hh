package com.huawei.phoneservice.feedback.utils;

import android.app.Activity;
import android.os.Bundle;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import java.util.List;

/* loaded from: classes5.dex */
public interface IFeedbackProblemManager {
    void gotoFeedback(Activity activity, ProblemInfo problemInfo, int i);

    void gotoFeedbackByDeepLink(Activity activity, Bundle bundle);

    void gotoFeedbackForHwId(Activity activity, ProblemInfo problemInfo, int i);

    void gotoProductSuggest(Activity activity, ProblemInfo problemInfo, int i);

    void gotoSelectedPreview(Activity activity, List<MediaItem> list, int i);

    void gotoUploadFile(Activity activity);
}
