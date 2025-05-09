package com.huawei.ui.homehealth.healthheadlinecard;

import android.os.Looper;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInfoManager;
import defpackage.eil;
import defpackage.jec;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.mle;
import defpackage.mtj;
import defpackage.nsn;
import defpackage.ola;
import defpackage.olb;
import defpackage.olc;
import defpackage.ole;
import defpackage.olf;
import defpackage.olg;
import defpackage.olh;
import defpackage.oli;
import defpackage.oly;
import defpackage.olz;
import defpackage.oma;
import defpackage.omb;
import defpackage.omc;
import defpackage.omg;
import defpackage.omh;
import defpackage.omj;
import defpackage.omk;
import defpackage.omm;
import health.compact.a.ReleaseLogUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class HealthHeadLinesInfoManager {
    private static volatile HealthHeadLinesInfoManager c;
    private final HealthHeadLinesApi e = (HealthHeadLinesApi) lqi.d().b(HealthHeadLinesApi.class);
    private final ParamsFactory d = new HealthHeadlinesFactory(BaseApplication.getContext());

    public interface RequestCallBack<T, V> {
        void onFailure(int i, String str);

        void onSuccess(T t, V v);
    }

    private HealthHeadLinesInfoManager() {
    }

    public static HealthHeadLinesInfoManager d() {
        if (c == null) {
            synchronized (HealthHeadLinesInfoManager.class) {
                if (c == null) {
                    c = new HealthHeadLinesInfoManager();
                }
            }
        }
        return c;
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final String str, final long j, final long j2) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "setWorkOutPost");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okt
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.e(str, j, j2);
                }
            });
            return;
        }
        olf olfVar = new olf();
        olfVar.e(j);
        olfVar.d(str);
        olfVar.c(System.currentTimeMillis());
        olfVar.e(0);
        olfVar.a(mle.e());
        olfVar.a(j2);
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(olfVar));
        LogUtil.a("HealthHeadLinesInfoManager", "setWorkOutPost apply url: ", olfVar.getUrl(), " apply headers:", this.d.getHeaders().toString(), " apply body: ", b);
        try {
            Response<omh> execute = this.e.setWorkoutPlay(olfVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is: ", Integer.valueOf(execute.getCode()));
            } else {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "setWorkOutPost response is OK.");
                omh body = execute.getBody();
                if (body == null) {
                    return;
                }
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "setWorkOutPost apply resultCode: ", body.a());
                if ("0".equals(body.a())) {
                    oli.a().aa();
                }
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "setWorkOutPost exception");
        }
    }

    public void d(final UiCallback<List<oma>> uiCallback, final long j, final long j2) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getWorkoutListHistoryRsp");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okw
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.b(uiCallback, j, j2);
                }
            });
            return;
        }
        olh olhVar = new olh();
        String b = DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        String b2 = DateFormatUtil.b(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD);
        olhVar.c(b);
        olhVar.e(b2);
        olhVar.b(mle.e());
        Map<String, String> headers = this.d.getHeaders();
        String b3 = lql.b(this.d.getBody(olhVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getWorkoutListHistory startDate = ", b, ", endDate = ", b2, ", apply url: ", olhVar.getUrl(), " getWorkoutListHistory apply headers:", this.d.getHeaders().toString(), " getWorkoutListHistory apply body: ", b3);
        try {
            Response<omg> execute = this.e.getWorkoutListHistory(olhVar.getUrl(), headers, b3).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getWorkoutListHistory response is OK.");
            omg body = execute.getBody();
            if (body != null) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getWorkoutListHistory apply resultCode: ", body.e());
                if (!"0".equals(body.e())) {
                    LogUtil.h("HealthHeadLinesInfoManager", "getWorkoutListHistory apply resultDesc: ", body.a());
                    uiCallback.onFailure(1, "getWorkoutListHistory responseBody is error");
                    return;
                }
                List<oma> c2 = body.c();
                if (!koq.b(c2)) {
                    ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "workoutHistoryList size =", Integer.valueOf(c2.size()));
                    uiCallback.onSuccess(c2);
                    return;
                } else {
                    ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "workoutHistoryList is empty");
                    uiCallback.onFailure(1, "workoutHistoryList is empty");
                    return;
                }
            }
            uiCallback.onFailure(1, "getWorkoutListHistory responseBody is null");
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getWorkoutListHistory exception");
        }
    }

    public /* synthetic */ void b(UiCallback uiCallback, long j, long j2) {
        d((UiCallback<List<oma>>) uiCallback, j, j2);
    }

    public void e(final String str, final RequestCallBack<List<omb>, Integer> requestCallBack, final int i) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getWorkoutListHistoryRsp");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okr
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.b(str, requestCallBack, i);
                }
            });
            return;
        }
        ole oleVar = new ole();
        boolean isTv = BaseApplication.isTv();
        oleVar.a(str);
        oleVar.a(isTv ? 1 : 0);
        oleVar.e(i);
        oleVar.e("5");
        oleVar.d(nsn.e(eil.a()));
        oleVar.d(mtj.e(null));
        oleVar.e(System.currentTimeMillis());
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(oleVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getWorkoutsByTopicId apply url: ", oleVar.getUrl(), " getWorkoutsByTopicId apply headers:", this.d.getHeaders().toString(), " getWorkoutsByTopicId apply body: ", b);
        try {
            Response<olz> execute = this.e.getWorkoutsByTopicId(oleVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getWorkoutsByTopicId response is OK.");
            olz body = execute.getBody();
            if (body == null) {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "getWorkoutsByTopicId responseBody is empty");
                requestCallBack.onFailure(1, "getWorkoutsByTopicId responseBody is null");
                return;
            }
            LogUtil.a("HealthHeadLinesInfoManager", "getWorkoutsByTopicId apply resultCode: ", body.d(), " getWorkoutsByTopicId apply resultDesc: ", body.a());
            if (!"0".equals(body.d())) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getWorkoutsByTopicId apply resultCode: ", body.d());
                requestCallBack.onFailure(1, "getWorkoutListHistory responseBody is error");
                return;
            }
            oli.a().i(body.b());
            List<omb> e = body.e();
            if (!koq.b(e)) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "workoutByTopicList size", Integer.valueOf(e.size()));
                requestCallBack.onSuccess(e, Integer.valueOf(body.c()));
            } else {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "getWorkoutsByTopicId workoutByTopicList is empty");
                requestCallBack.onFailure(1, "workoutByTopicList is empty");
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getWorkoutsByTopicId exception");
        }
    }

    public /* synthetic */ void b(String str, RequestCallBack requestCallBack, int i) {
        e(str, (RequestCallBack<List<omb>, Integer>) requestCallBack, i);
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void b(final String str, final UiCallback<List<oly>> uiCallback) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getLecturerInfo");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okq
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.b(str, uiCallback);
                }
            });
            return;
        }
        olg olgVar = new olg();
        olgVar.a(str);
        olgVar.b(System.currentTimeMillis());
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(olgVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getLecturerInfoRsp apply url: ", olgVar.getUrl(), " getLecturerInfoRsp apply headers:", this.d.getHeaders().toString(), " getLecturerInfoRsp apply body: ", b);
        try {
            Response<omc> execute = this.e.getLecturerInfo(olgVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getLecturerInfoRsp response is OK.");
            omc body = execute.getBody();
            if (body == null) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getLecturerInfoRsp responseBody is null");
                uiCallback.onFailure(1, "getLecturerInfoRsp responseBody is null");
                return;
            }
            LogUtil.a("HealthHeadLinesInfoManager", "getLecturerInfoRsp apply resultCode: ", body.a(), " getLecturerInfoRsp apply resultDesc: ", body.c());
            if (!"0".equals(body.a())) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getLecturerInfoRsp apply resultCode: ", body.a());
                uiCallback.onFailure(1, "getLecturerInfoRsp responseBody is error");
                return;
            }
            String d = body.d();
            String e = body.e();
            List<oly> b2 = body.b();
            for (oly olyVar : b2) {
                olyVar.e(d);
                olyVar.a(e);
            }
            if (!koq.b(b2)) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "lectureDetail size ", Integer.valueOf(b2.size()));
                uiCallback.onSuccess(b2);
            } else {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getLecturerInfoRsp lecturerWorks is empty");
                uiCallback.onFailure(1, "lectureDetail is empty");
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getLecturerInfoRsp exception");
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void b(final UiCallback<List<oly>> uiCallback) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserBehaviorListRsp");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okp
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.b(uiCallback);
                }
            });
            return;
        }
        olb olbVar = new olb();
        olbVar.e(System.currentTimeMillis());
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(olbVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getUserBehaviorListRsp apply url: ", olbVar.getUrl(), " getUserBehaviorListRsp apply headers:", this.d.getHeaders().toString(), " getUserBehaviorListRsp apply body: ", b);
        try {
            Response<omj> execute = this.e.userBehaviorList(olbVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserBehaviorListRsp response is OK.");
            omj body = execute.getBody();
            if (body == null) {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "getUserBehaviorListRsp responseBody is null");
                uiCallback.onFailure(1, "getUserBehaviorListRsp responseBody is null");
                return;
            }
            LogUtil.a("HealthHeadLinesInfoManager", "getUserBehaviorListRsp apply resultCode: ", body.c(), " getUserBehaviorListRsp apply resultDesc: ", body.e());
            if (!"0".equals(body.c())) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserBehaviorListRsp apply resultCode: ", body.c());
                uiCallback.onFailure(1, "getUserBehaviorListRsp responseBody is error");
                return;
            }
            List<oly> b2 = body.b();
            if (!koq.b(b2)) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "audioWorkoutLists size ", Integer.valueOf(b2.size()));
                uiCallback.onSuccess(b2);
            } else {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "audioWorkoutLists lectureDetail is empty");
                uiCallback.onFailure(1, "lectureDetail is empty");
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserBehaviorListRsp exception");
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void f(final UiCallback<List<oly>> uiCallback) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserPlayRecordRsp");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: okv
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.f(uiCallback);
                }
            });
            return;
        }
        olc olcVar = new olc();
        olcVar.e(System.currentTimeMillis());
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(olcVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getUserPlayRecordRsp apply url: ", olcVar.getUrl(), " getUserPlayRecordRsp apply headers:", this.d.getHeaders().toString(), " getUserPlayRecordRsp apply body: ", b);
        try {
            Response<omm> execute = this.e.userPlayRecord(olcVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserPlayRecordRsp response is OK.");
            omm body = execute.getBody();
            if (body == null) {
                uiCallback.onFailure(1, "getUserPlayRecordRsp responseBody is null");
                return;
            }
            LogUtil.a("HealthHeadLinesInfoManager", "getUserPlayRecordRsp apply resultCode: ", body.b(), " getUserPlayRecordRsp apply resultDesc: ", body.c());
            if (!"0".equals(body.b())) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserPlayRecordRsp apply resultCode: ", body.b());
                uiCallback.onFailure(1, "getUserPlayRecordRsp responseBody is error");
                return;
            }
            List<oly> e = body.e();
            if (!koq.b(e)) {
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "audioWorkoutLists size=", Integer.valueOf(e.size()));
                uiCallback.onSuccess(e);
            } else {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "audioWorkoutLists is empty ");
                uiCallback.onFailure(1, "audioWorkoutLists is empty");
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserPlayRecordRsp exception");
        }
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(final UiCallback<Long> uiCallback) {
        ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: oku
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesInfoManager.this.a(uiCallback);
                }
            });
            return;
        }
        ola olaVar = new ola();
        olaVar.a(jec.n(new Date()) * 1000);
        olaVar.d(System.currentTimeMillis());
        Map<String, String> headers = this.d.getHeaders();
        String b = lql.b(this.d.getBody(olaVar));
        LogUtil.a("HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration apply url: ", olaVar.getUrl(), " apply headers:", this.d.getHeaders().toString(), " apply body: ", b);
        try {
            Response<omk> execute = this.e.userAccumulatedPlayDuration(olaVar.getUrl(), headers, b).execute();
            if (!execute.isOK()) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration response code is ", Integer.valueOf(execute.getCode()));
                return;
            }
            ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration response is OK.");
            omk body = execute.getBody();
            if (body == null) {
                ReleaseLogUtil.a("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration responseBody is null");
                uiCallback.onFailure(1, "getUserAccumulatedPlayDuration responseBody is null");
                return;
            }
            LogUtil.a("HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration apply resultCode: ", body.b(), " getUserAccumulatedPlayDuration apply resultDesc: ", body.c());
            if (!"0".equals(body.b())) {
                ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration apply resultCode: ", body.b());
                uiCallback.onFailure(1, "getUserAccumulatedPlayDuration responseBody is error");
            } else {
                long a2 = body.a();
                ReleaseLogUtil.b("R_HealthHeadLinesInfoManager", "User accumulated duration is: ", Long.valueOf(a2));
                uiCallback.onSuccess(Long.valueOf(a2));
            }
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_HealthHeadLinesInfoManager", "getUserAccumulatedPlayDuration exception");
        }
    }
}
