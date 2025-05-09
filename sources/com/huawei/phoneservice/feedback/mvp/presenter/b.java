package com.huawei.phoneservice.feedback.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.faq.base.util.VideoCallBack;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.feedback.mvp.base.g;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;

/* loaded from: classes5.dex */
public class b extends com.huawei.phoneservice.feedback.mvp.base.a<com.huawei.phoneservice.feedback.mvp.contract.c> implements com.huawei.phoneservice.feedback.mvp.contract.b {
    private Context b;
    protected Gson c;

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void a() {
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void b() {
    }

    public void c(FeedBackRequest feedBackRequest) {
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(this.b, feedBackRequest, new e(FeedBackResponse.ProblemEnity.class, (Activity) this.b, feedBackRequest));
    }

    public void b(String str, VideoCallBack videoCallBack, String str2, String str3) {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().downLoadFile((Activity) this.b, str, videoCallBack, str2);
    }

    public void b(FeedBackRequest feedBackRequest) {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().setRead(this.b, feedBackRequest.getProblemId(), null);
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(this.b, feedBackRequest, new c(FeedBackResponse.class, (Activity) this.b, feedBackRequest));
    }

    public void b(FeedBackRateRequest feedBackRateRequest, boolean z, com.huawei.phoneservice.feedback.entity.d dVar) {
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.postRate(this.b, feedBackRateRequest, new d(com.huawei.phoneservice.feedback.entity.b.class, (Activity) this.b, z, dVar, feedBackRateRequest));
    }

    public void c(Context context) {
        this.b = context;
    }

    /* loaded from: classes9.dex */
    class c extends FaqCallback<FeedBackResponse> {
        final /* synthetic */ FeedBackRequest d;

        /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$c$c, reason: collision with other inner class name */
        class C0230c extends BaseSdkUpdateRequest<FeedBackRequest> {

            /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$c$c$e */
            class e extends FaqCallback<FeedBackResponse> {
                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
                    if (th == null && feedBackResponse != null) {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).a(feedBackResponse.getDataList());
                    } else {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).d();
                    }
                }

                e(Class cls, Activity activity) {
                    super(cls, activity);
                }
            }

            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(b.this.b, feedBackRequest, new e(FeedBackResponse.class, (Activity) b.this.b));
                }
            }

            C0230c(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            g gVar;
            if (th == null) {
                if (com.huawei.phoneservice.faq.base.util.b.b(feedBackResponse.getDataList())) {
                    gVar = b.this.f8267a;
                } else {
                    ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).a(feedBackResponse.getDataList());
                    return;
                }
            } else {
                if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 401 && j.e().haveSdkErr("accessToken")) {
                    j.e().registerUpdateListener(new C0230c(this.d));
                    j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
                    return;
                }
                gVar = b.this.f8267a;
            }
            ((com.huawei.phoneservice.feedback.mvp.contract.c) gVar).d();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Class cls, Activity activity, FeedBackRequest feedBackRequest) {
            super(cls, activity);
            this.d = feedBackRequest;
        }
    }

    /* loaded from: classes9.dex */
    class d extends FaqCallback<com.huawei.phoneservice.feedback.entity.b> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.phoneservice.feedback.entity.d f8271a;
        final /* synthetic */ boolean c;
        final /* synthetic */ FeedBackRateRequest d;

        /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$d$b, reason: collision with other inner class name */
        class C0231b extends BaseSdkUpdateRequest<FeedBackRateRequest> {

            /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$d$b$b, reason: collision with other inner class name */
            class C0232b extends FaqCallback<com.huawei.phoneservice.feedback.entity.b> {
                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, com.huawei.phoneservice.feedback.entity.b bVar) {
                    if (th != null) {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).c(d.this.f8271a);
                        return;
                    }
                    com.huawei.phoneservice.feedback.mvp.contract.c cVar = (com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a;
                    d dVar = d.this;
                    cVar.a(dVar.c, dVar.f8271a);
                }

                C0232b(Class cls, Activity activity) {
                    super(cls, activity);
                }
            }

            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRateRequest feedBackRateRequest) {
                if ("accessToken".equals(str)) {
                    j.e().unregisterUpdateListener(this);
                    feedBackRateRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.postRate(b.this.b, feedBackRateRequest, new C0232b(com.huawei.phoneservice.feedback.entity.b.class, (Activity) b.this.b));
                }
            }

            C0231b(FeedBackRateRequest feedBackRateRequest) {
                super(feedBackRateRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedback.entity.b bVar) {
            if (th == null) {
                ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).a(this.c, this.f8271a);
                return;
            }
            if (!(th instanceof FaqWebServiceException) || ((FaqWebServiceException) th).errorCode != 401 || !j.e().haveSdkErr("accessToken")) {
                ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).c(this.f8271a);
            } else {
                j.e().registerUpdateListener(new C0231b(this.d));
                j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, boolean z, com.huawei.phoneservice.feedback.entity.d dVar, FeedBackRateRequest feedBackRateRequest) {
            super(cls, activity);
            this.c = z;
            this.f8271a = dVar;
            this.d = feedBackRateRequest;
        }
    }

    /* loaded from: classes9.dex */
    class e extends FaqCallback<FeedBackResponse.ProblemEnity> {
        final /* synthetic */ FeedBackRequest e;

        /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$e$b, reason: collision with other inner class name */
        class C0233b extends BaseSdkUpdateRequest<FeedBackRequest> {

            /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.b$e$b$c */
            class c extends FaqCallback<FeedBackResponse.ProblemEnity> {
                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
                    if (th != null) {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).setThrowableView(th);
                        return;
                    }
                    b bVar = b.this;
                    if (problemEnity != null) {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) bVar.f8267a).a(problemEnity);
                    } else {
                        ((com.huawei.phoneservice.feedback.mvp.contract.c) bVar.f8267a).setErrorView(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
                    }
                }

                c(Class cls, Activity activity) {
                    super(cls, activity);
                }
            }

            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(b.this.b, feedBackRequest, new c(FeedBackResponse.ProblemEnity.class, (Activity) b.this.b));
                }
            }

            C0233b(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
            if (th == null) {
                if (problemEnity != null && !l.e(problemEnity.getProblemId())) {
                    ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).a(problemEnity);
                    return;
                } else {
                    ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).setErrorView(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
                    return;
                }
            }
            if (!(th instanceof FaqWebServiceException) || ((FaqWebServiceException) th).errorCode != 401 || !j.e().haveSdkErr("accessToken")) {
                ((com.huawei.phoneservice.feedback.mvp.contract.c) b.this.f8267a).setThrowableView(th);
            } else {
                j.e().registerUpdateListener(new C0233b(this.e));
                j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Class cls, Activity activity, FeedBackRequest feedBackRequest) {
            super(cls, activity);
            this.e = feedBackRequest;
        }
    }

    public b(com.huawei.phoneservice.feedback.mvp.contract.c cVar) {
        super(cVar);
        this.c = new Gson();
    }
}
