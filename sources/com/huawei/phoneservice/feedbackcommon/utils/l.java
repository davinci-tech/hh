package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import com.huawei.phoneservice.feedbackcommon.entity.u;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

/* loaded from: classes6.dex */
public class l extends com.huawei.phoneservice.feedbackcommon.utils.c {
    private List<String> h;
    private WeakReference<SubmitListener> i;
    private FeedbackInfo j;

    protected void d() {
        if (this.j == null) {
            return;
        }
        u uVar = new u();
        uVar.i(this.j.getProblemId());
        uVar.l(com.huawei.phoneservice.faq.base.util.j.c().getSdk("channel"));
        uVar.a(com.huawei.phoneservice.faq.base.util.j.c().getSdk("country"));
        uVar.f(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LANGUAGE));
        uVar.e(com.huawei.phoneservice.faq.base.util.j.c().getSdk("language"));
        uVar.o(this.j.getProblemDesc());
        uVar.d(this.j.getContact());
        List<String> list = this.h;
        if (list != null) {
            uVar.d(list);
        }
        uVar.k(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN));
        uVar.h(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION));
        uVar.c(com.huawei.phoneservice.faq.base.util.j.c().getSdk("appVersion"));
        uVar.j(com.huawei.phoneservice.faq.base.util.j.c().getSdk("model"));
        uVar.b(com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"));
        String sdk = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SOFT_VERSION);
        if (!TextUtils.isEmpty(sdk)) {
            uVar.m(sdk);
        }
        if (!TextUtils.isEmpty(this.j.getSrCode())) {
            uVar.r(this.j.getSrCode());
        }
        uVar.n(this.j.getProblemType());
        if (!TextUtils.isEmpty(String.valueOf(this.j.getAssociatedId()))) {
            uVar.b(Long.valueOf(this.j.getAssociatedId()));
            uVar.s(this.j.getUniqueCode());
        }
        if (this.c) {
            uVar.g(this.j.getZipFileName() + ".zip");
            uVar.b(this.e);
        }
        if ("Y".equals(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_IS_DEEPLICK))) {
            e(uVar);
        } else {
            b(uVar);
        }
    }

    public void e(SubmitListener submitListener) {
        if (submitListener != null) {
            this.i = new WeakReference<>(submitListener);
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(u uVar) {
        Submit callFeedBackService = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.callFeedBackService(this.d, uVar, new a(com.huawei.phoneservice.feedbackcommon.entity.d.class, null));
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(callFeedBackService);
    }

    private void e(u uVar) {
        Submit updateFeedBackInfo = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.updateFeedBackInfo(this.d, uVar, new d(com.huawei.phoneservice.feedbackcommon.entity.d.class, null, uVar));
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(updateFeedBackInfo);
    }

    private void b(u uVar) {
        Submit callFeedBackService = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.callFeedBackService(this.d, uVar, new c(com.huawei.phoneservice.feedbackcommon.entity.d.class, null, uVar));
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(callFeedBackService);
    }

    private SubmitListener b() {
        WeakReference<SubmitListener> weakReference = this.i;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* loaded from: classes9.dex */
    class c extends FaqCallback<com.huawei.phoneservice.feedbackcommon.entity.d> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ u f8356a;

        /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.l$c$c, reason: collision with other inner class name */
        class C0241c extends BaseSdkUpdateRequest<u> {
            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, u uVar) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    WeakReference<BaseSdkUpdateRequest> weakReference = l.this.b;
                    if (weakReference != null) {
                        weakReference.clear();
                        l.this.b = null;
                    }
                    uVar.b(str3);
                    l.this.c(uVar);
                }
            }

            C0241c(u uVar) {
                super(uVar);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedbackcommon.entity.d dVar) {
            if (th == null && dVar != null) {
                ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(l.this.d)));
                l.this.e(6, -1, dVar.d);
                return;
            }
            boolean z = th instanceof FaqWebServiceException;
            if (z && ((FaqWebServiceException) th).errorCode == 401 && com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                C0241c c0241c = new C0241c(this.f8356a);
                WeakReference<BaseSdkUpdateRequest> weakReference = l.this.b;
                if (weakReference != null) {
                    weakReference.clear();
                }
                l.this.b = new WeakReference<>(c0241c);
                com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(c0241c);
                com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"));
                return;
            }
            if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                l.this.e(7, 1, null);
            } else if (z) {
                l.this.c((FaqWebServiceException) th);
            } else {
                l.this.c((FaqWebServiceException) null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Class cls, Activity activity, u uVar) {
            super(cls, activity);
            this.f8356a = uVar;
        }
    }

    /* loaded from: classes9.dex */
    class d extends FaqCallback<com.huawei.phoneservice.feedbackcommon.entity.d> {
        final /* synthetic */ u c;

        class e extends BaseSdkUpdateRequest<u> {
            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, u uVar) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    WeakReference<BaseSdkUpdateRequest> weakReference = l.this.b;
                    if (weakReference != null) {
                        weakReference.clear();
                        l.this.b = null;
                    }
                    uVar.b(str3);
                    l.this.a(uVar);
                }
            }

            e(u uVar) {
                super(uVar);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedbackcommon.entity.d dVar) {
            if (th == null && dVar != null) {
                l.this.e(6, -1, dVar.d);
                return;
            }
            boolean z = th instanceof FaqWebServiceException;
            if (!z || ((FaqWebServiceException) th).errorCode != 401 || !com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                    l.this.e(7, 1, null);
                    return;
                } else {
                    if (z) {
                        l.this.c((FaqWebServiceException) th);
                        return;
                    }
                    return;
                }
            }
            e eVar = new e(this.c);
            WeakReference<BaseSdkUpdateRequest> weakReference = l.this.b;
            if (weakReference != null) {
                weakReference.clear();
            }
            l.this.b = new WeakReference<>(eVar);
            com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(eVar);
            com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, u uVar) {
            super(cls, activity);
            this.c = uVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(u uVar) {
        Submit callFeedBackService = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.callFeedBackService(this.d, uVar, new e(com.huawei.phoneservice.feedbackcommon.entity.d.class, null));
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(callFeedBackService);
    }

    class a extends FaqCallback<com.huawei.phoneservice.feedbackcommon.entity.d> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedbackcommon.entity.d dVar) {
            if (th == null && dVar != null) {
                ZipUtil.deleteFile(new File(FeedbackWebConstants.getZipFilePath(l.this.d)));
                l.this.e(6, -1, dVar.d);
            } else if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                l.this.e(7, 1, null);
            } else if (th instanceof FaqWebServiceException) {
                l.this.c((FaqWebServiceException) th);
            }
        }

        a(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    class e extends FaqCallback<com.huawei.phoneservice.feedbackcommon.entity.d> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedbackcommon.entity.d dVar) {
            if (th == null && dVar != null) {
                l.this.e(6, -1, dVar.d);
                return;
            }
            if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                l.this.e(7, 1, null);
            } else if (th instanceof FaqWebServiceException) {
                l.this.c((FaqWebServiceException) th);
            }
        }

        e(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(FaqWebServiceException faqWebServiceException) {
        int i;
        if (faqWebServiceException == null || !"Y".equals(com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_IS_DEEPLICK)) || ((i = faqWebServiceException.errorCode) != 401 && i != 405)) {
            i = 3;
        }
        e(7, i, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2, String str) {
        SubmitListener b = b();
        if (b != null) {
            b.submitNotify(i, i2, str);
        }
    }

    public l(Context context, boolean z, List<String> list, List<FeedbackZipBean> list2, FeedbackInfo feedbackInfo) {
        this.d = context;
        this.h = list;
        this.j = feedbackInfo;
        this.c = z;
        this.e = list2;
    }
}
