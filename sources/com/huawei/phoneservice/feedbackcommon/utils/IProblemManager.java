package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import com.huawei.phoneservice.faq.base.util.VideoCallBack;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import java.util.List;

/* loaded from: classes6.dex */
public interface IProblemManager {
    void addReadListener(OnReadListener onReadListener);

    void downLoadFile(Activity activity, String str, VideoCallBack videoCallBack, String str2);

    void getDataFromSdk(Activity activity, String str, String str2, int i, String str3, int i2, SdkFeedBackCallback sdkFeedBackCallback);

    void getFeedBackList(Context context, String str, int i, String str2, int i2, OnHistoryListener onHistoryListener);

    void getReadState(Activity activity, String str, SdkFeedBackCallback sdkFeedBackCallback);

    SdkProblemListener getSdkListener();

    String getSdkVersion();

    void getSrCodeData(Activity activity, SdkFeedBackCallback sdkFeedBackCallback);

    void getUnread(Context context, String str, OnReadListener onReadListener);

    void getUnread(Context context, String str, boolean z, OnReadListener onReadListener);

    void reUploadZip(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener);

    CancelInterface reUploadZipWithCancel(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener);

    void removeReadListener(OnReadListener onReadListener);

    void setRead(Context context, String str, OnReadListener onReadListener);

    void setSdkListener(SdkProblemListener sdkProblemListener);

    void submit(Context context, boolean z, List<String> list, List<FeedbackZipBean> list2, FeedbackInfo feedbackInfo, SubmitListener submitListener);

    CancelInterface submitWithCancel(Context context, boolean z, List<String> list, List<FeedbackZipBean> list2, FeedbackInfo feedbackInfo, SubmitListener submitListener);

    void uploadAttachment(Context context, MediaEntity mediaEntity, NotifyUploadFileListener notifyUploadFileListener);

    CancelInterface uploadAttachmentWithCancel(Context context, MediaEntity mediaEntity, NotifyUploadFileListener notifyUploadFileListener);

    void uploadZip(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener);

    CancelInterface uploadZipWithCancel(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener);

    void zipCompress(Context context, ZipCompressListener zipCompressListener);

    void zipCompressAgain(Context context, long j, ZipCompressListener zipCompressListener);
}
