package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public final class f {
    private WeakReference<Activity> b;
    private FeedBackRequest c;
    private List<FeedBackResponse.ProblemEnity> d;
    private WeakReference<SdkFeedBackCallback> e;

    public void a(SdkFeedBackCallback sdkFeedBackCallback) {
        if (sdkFeedBackCallback != null) {
            this.e = new WeakReference<>(sdkFeedBackCallback);
        }
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        this.c = feedBackRequest;
        feedBackRequest.setAccessToken(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        this.c.setProblemSourceCode(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("channel"));
        this.c.setPageSize(50);
        this.c.setOrderType(2);
        this.c.setStartWith("");
        this.d = new ArrayList();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!com.huawei.phoneservice.faq.base.util.j.e().hadAddress()) {
            e(new ConnectException("Unable to connect to server"));
            return;
        }
        Activity cfC_ = cfC_();
        if (cfC_ != null) {
            com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(cfC_, this.c, new d(FeedBackResponse.class, null));
        }
    }

    private SdkFeedBackCallback a() {
        WeakReference<SdkFeedBackCallback> weakReference = this.e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<FeedBackResponse.ProblemEnity> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            FeedBackResponse.ProblemEnity problemEnity = list.get(size);
            if (problemEnity == null || TextUtils.isEmpty(problemEnity.getSrno())) {
                list.remove(size);
            }
        }
        SdkFeedBackCallback a2 = a();
        Activity cfC_ = cfC_();
        if (a2 == null || cfC_ == null) {
            return;
        }
        try {
            cfC_.runOnUiThread(new b(a2, list));
        } catch (Exception e) {
            com.huawei.phoneservice.faq.base.util.i.c("GetSrCodeDataTask", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Throwable th) {
        if (!com.huawei.phoneservice.faq.base.util.b.b(this.d)) {
            c(this.d);
            return;
        }
        SdkFeedBackCallback a2 = a();
        Activity cfC_ = cfC_();
        if (a2 == null || cfC_ == null) {
            return;
        }
        try {
            cfC_.runOnUiThread(new c(a2, th));
        } catch (Exception e) {
            com.huawei.phoneservice.faq.base.util.i.c("GetSrCodeDataTask", e.getMessage());
        }
    }

    class d extends FaqCallback<FeedBackResponse> {

        class e extends BaseSdkUpdateRequest<FeedBackRequest> {
            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    f.this.c.setAccessToken(str3);
                    Activity cfC_ = f.this.cfC_();
                    if (cfC_ != null) {
                        f.this.cfD_(cfC_);
                    }
                }
            }

            e(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            if (th != null || feedBackResponse == null) {
                if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 401 && com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                    com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(new e(f.this.c));
                    com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
                    return;
                } else {
                    f.this.e(th);
                    return;
                }
            }
            f.this.d.addAll(feedBackResponse.getDataList());
            if (!TextUtils.isEmpty(f.this.c.getStartWith()) || f.this.d.size() != 50) {
                f fVar = f.this;
                fVar.c((List<FeedBackResponse.ProblemEnity>) fVar.d);
            } else {
                f.this.c.setStartWith(((FeedBackResponse.ProblemEnity) f.this.d.get(f.this.d.size() - 1)).getUpdateTime());
                f.this.b();
            }
        }

        d(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cfD_(Activity activity) {
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(activity, this.c, new a(FeedBackResponse.class, null));
    }

    class a extends FaqCallback<FeedBackResponse> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            if (th != null || feedBackResponse == null) {
                f.this.e(th);
                return;
            }
            f.this.d.addAll(feedBackResponse.getDataList());
            if (!TextUtils.isEmpty(f.this.c.getStartWith()) || f.this.d.size() != 50) {
                f fVar = f.this;
                fVar.c((List<FeedBackResponse.ProblemEnity>) fVar.d);
            } else {
                f.this.c.setStartWith(((FeedBackResponse.ProblemEnity) f.this.d.get(f.this.d.size() - 1)).getUpdateTime());
                f.this.b();
            }
        }

        a(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    static class b implements Runnable {
        private final List<FeedBackResponse.ProblemEnity> d;
        private final SdkFeedBackCallback e;

        @Override // java.lang.Runnable
        public void run() {
            this.e.setListView(this.d);
        }

        b(SdkFeedBackCallback sdkFeedBackCallback, List<FeedBackResponse.ProblemEnity> list) {
            this.e = sdkFeedBackCallback;
            this.d = list;
        }
    }

    static class c implements Runnable {
        private final Throwable c;
        private final SdkFeedBackCallback e;

        @Override // java.lang.Runnable
        public void run() {
            this.e.setThrowableView(this.c);
        }

        c(SdkFeedBackCallback sdkFeedBackCallback, Throwable th) {
            this.e = sdkFeedBackCallback;
            this.c = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity cfC_() {
        WeakReference<Activity> weakReference = this.b;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public f(Activity activity) {
        if (activity != null) {
            this.b = new WeakReference<>(activity);
        }
    }
}
