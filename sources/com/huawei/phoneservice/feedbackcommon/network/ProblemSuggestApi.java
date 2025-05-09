package com.huawei.phoneservice.feedbackcommon.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqRestClient;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.faq.base.util.r;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.f;
import com.huawei.phoneservice.feedbackcommon.utils.b;
import defpackage.uhy;
import defpackage.uib;
import java.io.File;

/* loaded from: classes5.dex */
public final class ProblemSuggestApi extends FaqRestClient {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ProblemSuggestApi f8335a;
    private static Context b;
    public static final e d = new e(null);
    private Context c;

    public final Submit e(FeedBackRequest feedBackRequest, Callback callback) {
        uhy.e((Object) feedBackRequest, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = b;
        String a2 = r.a(FeedbackWebConstants.HISTORY_FEEDBACK_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(feedBackRequest);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit c(File file, String str, String str2, Callback callback) {
        uhy.e((Object) file, "");
        uhy.e((Object) callback, "");
        Headers.Builder builder = new Headers.Builder();
        String str3 = uhy.e((Object) "2", (Object) b.h().getSdk(FaqConstants.FAQ_UPLOAD_FLAG)) ? FeedbackWebConstants.PROBLEM_SUGGEST_FILES_URL : FeedbackWebConstants.PROBLEM_SUGGEST_FILES_URL_V2;
        if (!l.e(str2)) {
            try {
                builder.add("accessToken", str2);
            } catch (Exception e2) {
                i.c("upload", e2.getMessage());
            }
        }
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = b;
        String str4 = r.g() + str3;
        Headers build = builder.build();
        uhy.a(build, "");
        return initRestClientAnno.uploadAttachs(context, str4, build, str, file, callback);
    }

    public final Submit b(f fVar, Callback callback) {
        uhy.e((Object) fVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = b;
        String a2 = r.a(FeedbackWebConstants.QUERY_FEEDBACK_FORHD);
        uhy.a(a2, "");
        String json = getGson().toJson(fVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit d(FeedBackRequest feedBackRequest, Callback callback) {
        uhy.e((Object) feedBackRequest, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = b;
        String a2 = r.a(FeedbackWebConstants.FEEDBACK_DETAIL_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(feedBackRequest);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit d(FeedBackRateRequest feedBackRateRequest, Callback callback) {
        uhy.e((Object) feedBackRateRequest, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.c);
        uhy.d(initRestClientAnno);
        Context context = b;
        String a2 = r.a(FeedbackWebConstants.RATE_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(feedBackRateRequest);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public static final class e {
        public final ProblemSuggestApi c(Context context) {
            ProblemSuggestApi.b = context != null ? context.getApplicationContext() : null;
            if (ProblemSuggestApi.f8335a == null) {
                ProblemSuggestApi.f8335a = new ProblemSuggestApi(ProblemSuggestApi.b);
            }
            return ProblemSuggestApi.f8335a;
        }

        public /* synthetic */ e(uib uibVar) {
            this();
        }

        private e() {
        }
    }

    public ProblemSuggestApi(Context context) {
        super(context);
        this.c = context;
    }
}
