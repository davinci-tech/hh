package com.huawei.phoneservice.feedback.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.feedback.ui.FeedUploadActivity;
import com.huawei.phoneservice.feedback.ui.FeedbackDisabledActivity;
import com.huawei.phoneservice.feedback.ui.ProblemSuggestActivity;
import com.huawei.phoneservice.feedback.ui.ProblemSuggestForDeepLinkActivity;
import com.huawei.phoneservice.feedback.ui.ProductSuggestionActivity;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import java.util.List;

/* loaded from: classes5.dex */
public class SdkFeedbackProblemManager implements IFeedbackProblemManager {
    private static final int HWID_FEED_BACK_REQUESTCODE = 1;
    private static final String TAG = "SdkFeedbackProblemManager";

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoUploadFile(Activity activity) {
        if (activity == null) {
            i.c(TAG, "go to upload activity is null");
        } else if (j.e().hadAddress()) {
            activity.startActivity(new Intent(activity, (Class<?>) FeedUploadActivity.class));
        }
    }

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoSelectedPreview(Activity activity, List<MediaItem> list, int i) {
        if (activity == null || list == null) {
            return;
        }
        list.size();
    }

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoProductSuggest(Activity activity, ProblemInfo problemInfo, int i) {
        if (activity == null) {
            i.e(TAG, "activity is null");
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) ProductSuggestionActivity.class);
        if (problemInfo != null) {
            intent.putExtra("problem_info", problemInfo);
        }
        if (i >= 0) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivity(intent);
        }
    }

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoFeedbackForHwId(Activity activity, ProblemInfo problemInfo, int i) {
        if (activity == null) {
            i.e(TAG, "activity is null");
            return;
        }
        if (!j.e().hadAddress() || !ModuleConfigUtils.newFeedbackEnabled()) {
            Intent intent = new Intent(activity, (Class<?>) FeedbackDisabledActivity.class);
            if (problemInfo != null) {
                intent.putExtra("problem_info", problemInfo);
            }
            intent.putExtra("REQUEST_CODE", i);
            activity.startActivityForResult(intent, 1);
            return;
        }
        Intent intent2 = new Intent(activity, (Class<?>) ProblemSuggestActivity.class);
        if (problemInfo != null) {
            intent2.putExtra("problem_info", problemInfo);
        }
        if (i >= 0) {
            activity.startActivityForResult(intent2, i);
        } else {
            activity.startActivity(intent2);
        }
    }

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoFeedbackByDeepLink(Activity activity, Bundle bundle) {
        if (activity == null) {
            i.e(TAG, "activity is null");
        } else {
            if (bundle == null) {
                i.e(TAG, "bundle is null");
                return;
            }
            Intent intent = j.e().hadAddress() ? new Intent(activity, (Class<?>) ProblemSuggestForDeepLinkActivity.class) : new Intent(activity, (Class<?>) FeedbackDisabledActivity.class);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }

    static class a {
        private static final SdkFeedbackProblemManager e = new SdkFeedbackProblemManager();
    }

    @Override // com.huawei.phoneservice.feedback.utils.IFeedbackProblemManager
    public void gotoFeedback(Activity activity, ProblemInfo problemInfo, int i) {
        Intent intent;
        String str;
        if (activity != null) {
            if (j.e().hadAddress() && ModuleConfigUtils.newFeedbackEnabled(activity)) {
                intent = new Intent(activity, (Class<?>) ProblemSuggestActivity.class);
                if (problemInfo != null) {
                    intent.putExtra("problem_info", problemInfo);
                }
                if (i >= 0) {
                    if (((-65536) & i) == 0) {
                        activity.startActivityForResult(intent, i);
                        return;
                    } else {
                        str = "Can only use lower 16 bits for requestCode: " + i;
                    }
                }
            } else {
                intent = new Intent(activity, (Class<?>) FeedbackDisabledActivity.class);
                if (problemInfo != null) {
                    intent.putExtra("problem_info", problemInfo);
                }
                intent.putExtra("REQUEST_CODE", i);
            }
            activity.startActivity(intent);
            return;
        }
        str = "activity is null";
        i.e(TAG, str);
    }

    public static IFeedbackProblemManager getManager() {
        return a.e;
    }
}
