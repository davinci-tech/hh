package com.huawei.phoneservice.feedbackcommon.utils;

import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import java.util.List;

/* loaded from: classes6.dex */
public interface NotifyUploadZipListener {
    void notifyUpload(List<FeedbackZipBean> list, boolean z);

    void notifyUploadAgain(long j);
}
