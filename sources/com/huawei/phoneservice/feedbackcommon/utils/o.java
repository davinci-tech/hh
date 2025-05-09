package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class o {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<OnHistoryListener> f8364a;
    private String b;
    private Context c;
    private List<FeedBackResponse.ProblemEnity> d = new ArrayList();
    private int e;
    private String g;
    private int i;

    public void e(OnHistoryListener onHistoryListener) {
        if (onHistoryListener != null) {
            this.f8364a = new WeakReference<>(onHistoryListener);
        }
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setAccessToken(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
        feedBackRequest.setProblemId(this.g);
        feedBackRequest.setStartWith(this.b);
        feedBackRequest.setPageSize(this.e);
        feedBackRequest.setProblemSourceCode(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("channel"));
        feedBackRequest.setOrderType(this.i);
        c(feedBackRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<FeedBackResponse.ProblemEnity> list, List<FeedBackResponse.ProblemEnity> list2) {
        OnHistoryListener b2 = b();
        if (b2 != null) {
            b2.showData(list, list2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Throwable th) {
        OnHistoryListener b2 = b();
        if (b2 != null) {
            b2.setThrowableView(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        if (com.huawei.phoneservice.faq.base.util.b.b(r2.d) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005d, code lost:
    
        r4.addAll(r2.d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:
    
        if (com.huawei.phoneservice.faq.base.util.b.b(r2.d) == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse r3, java.lang.String r4, com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest r5) {
        /*
            r2 = this;
            java.util.List r0 = r3.getDataList()
            java.util.List r0 = r2.e(r0)
            boolean r1 = com.huawei.phoneservice.faq.base.util.b.b(r0)
            if (r1 != 0) goto L44
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L36
            java.lang.String r4 = r2.b
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L36
            java.util.List r4 = r3.getDataList()
            int r4 = r4.size()
            r1 = 50
            if (r4 != r1) goto L36
            int r4 = r0.size()
            int r1 = r2.e
            if (r4 > r1) goto L36
            java.util.List<com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse$ProblemEnity> r3 = r2.d
            r3.addAll(r0)
            goto L7b
        L36:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List<com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse$ProblemEnity> r5 = r2.d
            boolean r5 = com.huawei.phoneservice.faq.base.util.b.b(r5)
            if (r5 != 0) goto L62
            goto L5d
        L44:
            boolean r1 = com.huawei.phoneservice.faq.base.util.b.b(r0)
            if (r1 == 0) goto L6d
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L6d
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List<com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse$ProblemEnity> r5 = r2.d
            boolean r5 = com.huawei.phoneservice.faq.base.util.b.b(r5)
            if (r5 != 0) goto L62
        L5d:
            java.util.List<com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse$ProblemEnity> r5 = r2.d
            r4.addAll(r5)
        L62:
            r4.addAll(r0)
            java.util.List r3 = r3.getDataList()
            r2.a(r3, r4)
            goto L89
        L6d:
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 == 0) goto L84
            java.lang.String r3 = r2.b
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L84
        L7b:
            java.lang.String r3 = r2.b
            r5.setStartWith(r3)
            r2.c(r5)
            goto L89
        L84:
            com.huawei.phoneservice.faq.base.constants.FaqConstants$FaqErrorCode r3 = com.huawei.phoneservice.faq.base.constants.FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR
            r2.b(r3)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.o.c(com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse, java.lang.String, com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FeedBackRequest feedBackRequest, String str) {
        a.f8341a.getFeedBackList((Activity) this.c, feedBackRequest, new d(FeedBackResponse.class, (Activity) this.c, str, feedBackRequest));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(FeedBackRequest feedBackRequest) {
        a.f8341a.getFeedBackList((Activity) this.c, feedBackRequest, new b(FeedBackResponse.class, (Activity) this.c, feedBackRequest.getStartWith(), feedBackRequest));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FaqConstants.FaqErrorCode faqErrorCode) {
        OnHistoryListener b2 = b();
        if (b2 != null) {
            b2.setErrorView(faqErrorCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FeedBackResponse.ProblemEnity> e(List<FeedBackResponse.ProblemEnity> list) {
        ArrayList arrayList = new ArrayList();
        if (!com.huawei.phoneservice.faq.base.util.b.b(list)) {
            this.b = list.get(list.size() - 1).getProblemId();
            for (FeedBackResponse.ProblemEnity problemEnity : list) {
                if (TextUtils.isEmpty(problemEnity.getSrno())) {
                    arrayList.add(problemEnity);
                }
            }
        }
        return arrayList;
    }

    /* loaded from: classes9.dex */
    class b extends FaqCallback<FeedBackResponse> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ FeedBackRequest f8365a;
        final /* synthetic */ String e;

        class a extends BaseSdkUpdateRequest<FeedBackRequest> {
            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    b bVar = b.this;
                    o.this.b(feedBackRequest, bVar.e);
                }
            }

            a(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            if (th == null) {
                o.this.c(feedBackResponse, this.e, this.f8365a);
            } else if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 401 && com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(new a(this.f8365a));
                com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
            } else {
                o.this.c(th);
            }
            if (!com.huawei.phoneservice.faq.base.util.b.b(o.this.d) || TextUtils.isEmpty(this.e)) {
                return;
            }
            o.this.d.clear();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Class cls, Activity activity, String str, FeedBackRequest feedBackRequest) {
            super(cls, activity);
            this.e = str;
            this.f8365a = feedBackRequest;
        }
    }

    class d extends FaqCallback<FeedBackResponse> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8366a;
        final /* synthetic */ FeedBackRequest d;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            if (th != null) {
                o.this.c(th);
                return;
            }
            List e = o.this.e(feedBackResponse.getDataList());
            if (!com.huawei.phoneservice.faq.base.util.b.b(e)) {
                o.this.a(feedBackResponse.getDataList(), (List<FeedBackResponse.ProblemEnity>) e);
            } else if (TextUtils.isEmpty(this.f8366a)) {
                this.d.setStartWith(o.this.b);
                o.this.c(this.d);
            } else {
                o.this.b(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, String str, FeedBackRequest feedBackRequest) {
            super(cls, activity);
            this.f8366a = str;
            this.d = feedBackRequest;
        }
    }

    private OnHistoryListener b() {
        WeakReference<OnHistoryListener> weakReference = this.f8364a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public o(Context context, String str, int i, String str2, int i2) {
        this.c = context;
        this.e = i;
        this.b = str2;
        this.g = str;
        this.i = i2;
    }
}
