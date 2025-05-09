package com.huawei.phoneservice.feedbackcommon.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.network.FaqRestClient;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.r;
import com.huawei.phoneservice.feedbackcommon.entity.q;
import com.huawei.phoneservice.feedbackcommon.entity.u;
import com.huawei.phoneservice.feedbackcommon.entity.v;
import defpackage.uhy;
import defpackage.uib;
import java.io.File;
import java.util.Map;

/* loaded from: classes5.dex */
public final class FeedbackUploadApi extends FaqRestClient {

    /* renamed from: a, reason: collision with root package name */
    private static Context f8333a;
    public static final d d = new d(null);
    private static volatile FeedbackUploadApi e;
    private Context c;

    public final Submit c(Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "getFeedbackUploadInfo header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str2 = r.g() + FeedbackWebConstants.FEEDBACK_UPLOAD_INFO;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWithJson(context, str2, str, of, callback);
    }

    public final Submit b(Map<String, String> map, String str, String str2, String str3, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "getUploadInfo header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str4 = str3 + FeedbackWebConstants.UPLOAD_INFO + str2;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWitHead(context, str4, of, str, callback);
    }

    public final Submit e(Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "getFeedbackNewUploadInfo header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str2 = r.g() + FeedbackWebConstants.FEEDBACK_NEW_UPLOAD_INFO;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWithJson(context, str2, str, of, callback);
    }

    public final Submit e(String str, Callback callback) {
        uhy.e((Object) callback, "");
        com.huawei.phoneservice.feedbackcommon.entity.r rVar = new com.huawei.phoneservice.feedbackcommon.entity.r(j.c().getSdk("country"), str);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String a2 = r.a(FeedbackWebConstants.REQUEST_PRIVACY_NOTICE_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(rVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit a(u uVar, Callback callback) {
        uhy.e((Object) uVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String a2 = r.a(FeedbackWebConstants.QUESTION_FEEDBACK_SUBMIT_FORHD);
        uhy.a(a2, "");
        String json = getGson().toJson(uVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit a(Map<String, String> map, String str, String str2, String str3, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "getNotifyUploadSucc header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str4 = str3 + FeedbackWebConstants.NOTIFY_UPLOAD_SUCC + str2;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWitHead(context, str4, of, str, callback);
    }

    public final Submit c(Map<String, String> map, String str, String str2, String str3) {
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        i.b("XCallback", "getNewUploadInfo header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        return initRestClientAnno.uploadZipFile(f8333a, str3 + FeedbackWebConstants.NEW_UPLOAD_INFO + str2, map, null, null, str);
    }

    public final Submit c(Map<String, String> map, String str, String str2, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "getServerDomain header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str3 = r.b() + FeedbackWebConstants.SERVER_DOMAIN + str2;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWitHead(context, str3, of, str, callback);
    }

    public final Submit b(Map<String, String> map, String str, Callback callback) {
        uhy.e((Object) map, "");
        uhy.e((Object) str, "");
        uhy.e((Object) callback, "");
        i.b("XCallback", "feedbackNotifySuccess header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String str2 = r.g() + FeedbackWebConstants.FEEDBACK_NOTIFY_SUCCESS;
        Headers of = Headers.of(map);
        uhy.a(of, "");
        return initRestClientAnno.asyncRequestWithJson(context, str2, str, of, callback);
    }

    public final Submit e(String str, Map<String, String> map, File file, String str2, String str3) {
        uhy.e((Object) str, "");
        uhy.e((Object) map, "");
        uhy.e((Object) file, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        i.b("XCallback", "getFileUploadToService header is : " + map, new Object[0]);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        return initRestClientAnno.uploadZipFileToService(f8333a, str, map, str3, file, str2);
    }

    public final Submit e(String str, String str2, String str3, String str4, Callback callback) {
        uhy.e((Object) callback, "");
        v vVar = new v(str4, str, str2, str3);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String a2 = r.a(FeedbackWebConstants.REQUEST_QUESTION_TYPE_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(vVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit c(String str, Callback callback) {
        uhy.e((Object) callback, "");
        q qVar = new q(str);
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String a2 = r.a(FeedbackWebConstants.REQUEST_ISO_LANGUAGE_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(qVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit b(u uVar, Callback callback) {
        uhy.e((Object) uVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = f8333a;
        String a2 = r.a(FeedbackWebConstants.QUESTION_FEEDBACK_SUBMIT);
        uhy.a(a2, "");
        String json = getGson().toJson(uVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public static final class d {
        public final FeedbackUploadApi e(Context context) {
            FeedbackUploadApi.f8333a = context != null ? context.getApplicationContext() : null;
            if (FeedbackUploadApi.e == null) {
                FeedbackUploadApi.e = new FeedbackUploadApi(FeedbackUploadApi.f8333a);
            }
            return FeedbackUploadApi.e;
        }

        public /* synthetic */ d(uib uibVar) {
            this();
        }

        private d() {
        }
    }

    public FeedbackUploadApi(Context context) {
        super(context);
        this.c = context;
    }
}
