package com.huawei.phoneservice.feedbackcommon.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.network.FaqRestClient;
import com.huawei.phoneservice.faq.base.util.r;
import com.huawei.phoneservice.feedbackcommon.entity.l;
import com.huawei.phoneservice.feedbackcommon.entity.x;
import defpackage.uhy;
import defpackage.uib;

/* loaded from: classes5.dex */
public final class ProblemApi extends FaqRestClient {
    private static volatile ProblemApi c;
    public static final d d = new d(null);
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private Context f8334a;

    public final Submit a(x xVar, Callback callback) {
        uhy.e((Object) xVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.f8334a);
        uhy.d(initRestClientAnno);
        Context context = e;
        String a2 = r.a(FeedbackWebConstants.SET_READ_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(xVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public final Submit d(l lVar, Callback callback) {
        uhy.e((Object) lVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.f8334a);
        uhy.d(initRestClientAnno);
        Context context = e;
        String a2 = r.a(FeedbackWebConstants.HISTORY_FEEDBACK_URL);
        uhy.a(a2, "");
        String json = getGson().toJson(lVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    public static final class d {
        public final ProblemApi d(Context context) {
            ProblemApi.e = context != null ? context.getApplicationContext() : null;
            if (ProblemApi.c == null) {
                ProblemApi.c = new ProblemApi(ProblemApi.e);
            }
            return ProblemApi.c;
        }

        public /* synthetic */ d(uib uibVar) {
            this();
        }

        private d() {
        }
    }

    public ProblemApi(Context context) {
        super(context);
        this.f8334a = context;
    }
}
