package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.u;
import com.huawei.phoneservice.feedbackcommon.entity.x;
import com.huawei.phoneservice.feedbackcommon.network.FaqDownloadManager;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackUploadApi;
import com.huawei.phoneservice.feedbackcommon.network.ProblemApi;
import com.huawei.phoneservice.feedbackcommon.network.ProblemSuggestApi;
import defpackage.uhy;
import java.io.File;
import java.util.Map;

/* loaded from: classes6.dex */
public final class a implements IFeedbackCommonManager {

    /* renamed from: a, reason: collision with root package name */
    public static final a f8341a = new a();

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit uploadFile(Context context, File file, String str, String str2, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) file, "");
        uhy.e((Object) callback, "");
        ProblemSuggestApi c = ProblemSuggestApi.d.c(context);
        uhy.d(c);
        return c.c(file, str, str2, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit updateFeedBackInfo(Context context, u uVar, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) uVar, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.a(uVar, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit setRead(Context context, String str, String str2, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        x xVar = new x(str, str2);
        ProblemApi d = ProblemApi.d.d(context);
        uhy.d(d);
        return d.a(xVar, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit queryNotice(Context context, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.e(str, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit queryIsoLanguage(Context context, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.c(str, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit queryForHD(Context context, long j, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        com.huawei.phoneservice.feedbackcommon.entity.f fVar = new com.huawei.phoneservice.feedbackcommon.entity.f();
        fVar.a(j);
        fVar.b(str);
        ProblemSuggestApi c = ProblemSuggestApi.d.c(context);
        uhy.d(c);
        return c.b(fVar, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit postRate(Context context, FeedBackRateRequest feedBackRateRequest, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) feedBackRateRequest, "");
        uhy.e((Object) callback, "");
        ProblemSuggestApi c = ProblemSuggestApi.d.c(context);
        uhy.d(c);
        return c.d(feedBackRateRequest, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getUploadInfo(Context context, Map<String, String> map, String str, String str2, String str3, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.b(map, str, str2, str3, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getUnread(Context context, com.huawei.phoneservice.feedbackcommon.entity.l lVar, Callback callback) {
        uhy.e((Object) lVar, "");
        uhy.e((Object) callback, "");
        ProblemApi d = ProblemApi.d.d(context);
        uhy.d(d);
        return d.d(lVar, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getServerDomain(Context context, Map<String, String> map, String str, String str2, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.c(map, str, str2, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getNotifyUploadSucc(Context context, Map<String, String> map, String str, String str2, String str3, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.a(map, str, str2, str3, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getNewUploadInfo(Context context, Map<String, String> map, String str, String str2, String str3) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.c(map, str, str2, str3);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getFileUploadToService(Context context, String str, Map<String, String> map, File file, String str2, String str3) {
        uhy.e((Object) context, "");
        uhy.e((Object) str, "");
        uhy.e((Object) map, "");
        uhy.e((Object) file, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.e(str, map, file, str2, str3);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getFeedbackUploadInfo(Context context, Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.c(map, str, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getFeedbackNewUploadInfo(Context context, Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.e(map, str, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getFeedBackList(Context context, FeedBackRequest feedBackRequest, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) feedBackRequest, "");
        uhy.e((Object) callback, "");
        ProblemSuggestApi c = ProblemSuggestApi.d.c(context);
        uhy.d(c);
        return c.e(feedBackRequest, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit getDataFromDetail(Context context, FeedBackRequest feedBackRequest, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) feedBackRequest, "");
        uhy.e((Object) callback, "");
        ProblemSuggestApi c = ProblemSuggestApi.d.c(context);
        uhy.d(c);
        return c.d(feedBackRequest, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit feedbackNotifySuccess(Context context, Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.b(map, str, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit downloadFile(Context context, String str, String str2, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        FaqDownloadManager e = FaqDownloadManager.c.e(context);
        uhy.d(e);
        return e.a(str, str2, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit callService(Context context, String str, String str2, String str3, String str4, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.e(str, str2, str3, str4, callback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IFeedbackCommonManager
    public Submit callFeedBackService(Context context, u uVar, Callback callback) {
        uhy.e((Object) context, "");
        uhy.e((Object) uVar, "");
        uhy.e((Object) callback, "");
        FeedbackUploadApi e = FeedbackUploadApi.d.e(context);
        uhy.d(e);
        return e.b(uVar, callback);
    }

    private a() {
    }
}
