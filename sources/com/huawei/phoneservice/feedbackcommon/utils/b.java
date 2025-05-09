package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.faq.base.util.Sdk;
import com.huawei.phoneservice.faq.base.util.VideoCallBack;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackMediaData;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedBackResponse;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class b implements OnReadListener, IProblemManager {

    /* renamed from: a, reason: collision with root package name */
    private static String f8342a = null;
    private static long b = 512000;
    private static int c = 4;
    private static int d = 52428800;
    private WeakReference<SdkProblemListener> e;
    private List<OnReadListener> i;

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void zipCompressAgain(Context context, long j, ZipCompressListener zipCompressListener) {
        new r(context, j, zipCompressListener).execute(new Object[0]);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void zipCompress(Context context, ZipCompressListener zipCompressListener) {
        new r(context, zipCompressListener).execute(new Object[0]);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public CancelInterface uploadZipWithCancel(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener) {
        n nVar = new n(context, str, str2);
        nVar.c(notifyUploadZipListener);
        return nVar.a();
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void uploadZip(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener) {
        new n(context, str, str2).c(notifyUploadZipListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public CancelInterface uploadAttachmentWithCancel(Context context, MediaEntity mediaEntity, NotifyUploadFileListener notifyUploadFileListener) {
        m mVar = new m(context, mediaEntity);
        mVar.d(notifyUploadFileListener);
        return mVar.a();
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void uploadAttachment(Context context, MediaEntity mediaEntity, NotifyUploadFileListener notifyUploadFileListener) {
        new m(context, mediaEntity).d(notifyUploadFileListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
    public void unread(Throwable th, String str, int i2) {
        List<OnReadListener> list = this.i;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.i.get(size).unread(th, str, i2);
            }
        }
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public CancelInterface submitWithCancel(Context context, boolean z, List<String> list, List<FeedbackZipBean> list2, FeedbackInfo feedbackInfo, SubmitListener submitListener) {
        l lVar = new l(context, z, list, list2, feedbackInfo);
        lVar.e(submitListener);
        return lVar.a();
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void submit(Context context, boolean z, List<String> list, List<FeedbackZipBean> list2, FeedbackInfo feedbackInfo, SubmitListener submitListener) {
        new l(context, z, list, list2, feedbackInfo).e(submitListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void setSdkListener(SdkProblemListener sdkProblemListener) {
        if (sdkProblemListener != null) {
            WeakReference<SdkProblemListener> weakReference = this.e;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.e = new WeakReference<>(sdkProblemListener);
            return;
        }
        WeakReference<SdkProblemListener> weakReference2 = this.e;
        if (weakReference2 != null) {
            weakReference2.clear();
            this.e = null;
        }
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void setRead(Context context, String str, OnReadListener onReadListener) {
        new k(context, str, this).e(onReadListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void removeReadListener(OnReadListener onReadListener) {
        List<OnReadListener> list = this.i;
        if (list != null) {
            list.remove(onReadListener);
        }
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.OnReadListener
    public void read(Throwable th, String str) {
        List<OnReadListener> list = this.i;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.i.get(size).read(th, str);
            }
        }
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public CancelInterface reUploadZipWithCancel(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener) {
        n nVar = new n(context, str, str2);
        nVar.a(notifyUploadZipListener);
        return nVar.a();
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void reUploadZip(Context context, String str, long j, String str2, NotifyUploadZipListener notifyUploadZipListener) {
        new n(context, str, str2).a(notifyUploadZipListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getUnread(Context context, String str, boolean z, OnReadListener onReadListener) {
        new j(context, str, z, this).e(onReadListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getUnread(Context context, String str, OnReadListener onReadListener) {
        getUnread(context, str, false, onReadListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getSrCodeData(Activity activity, SdkFeedBackCallback sdkFeedBackCallback) {
        new com.huawei.phoneservice.feedbackcommon.utils.f(activity).a(sdkFeedBackCallback);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public String getSdkVersion() {
        return "24.5.0.2";
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public SdkProblemListener getSdkListener() {
        WeakReference<SdkProblemListener> weakReference = this.e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getReadState(Activity activity, String str, SdkFeedBackCallback sdkFeedBackCallback) {
        if (!com.huawei.phoneservice.faq.base.util.j.e().hadAddress()) {
            sdkFeedBackCallback.setThrowableView(new FaqWebServiceException(-1, "No URL Address"));
            return;
        }
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setAccessToken(com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"));
        feedBackRequest.setProblemId(str);
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(activity, feedBackRequest, new C0236b(FeedBackResponse.ProblemEnity.class, activity, sdkFeedBackCallback, feedBackRequest, activity));
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getFeedBackList(Context context, String str, int i2, String str2, int i3, OnHistoryListener onHistoryListener) {
        new o(context, str, i2, str2, i3).e(onHistoryListener);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void getDataFromSdk(Activity activity, String str, String str2, int i2, String str3, int i3, SdkFeedBackCallback sdkFeedBackCallback) {
        if (!com.huawei.phoneservice.faq.base.util.j.e().hadAddress()) {
            sdkFeedBackCallback.setThrowableView(new FaqWebServiceException(-1, "No URL Address"));
            return;
        }
        ArrayList arrayList = new ArrayList();
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setProblemId(str2);
        feedBackRequest.setAccessToken(h().getSdk("accessToken"));
        feedBackRequest.setStartWith(str);
        feedBackRequest.setPageSize(i2);
        feedBackRequest.setProblemSourceCode(str3);
        feedBackRequest.setOrderType(i3);
        com.huawei.phoneservice.faq.base.util.i.c("SdkProblemManager", "getDataFromSDK");
        if (TextUtils.isEmpty(str)) {
            cfB_(feedBackRequest, activity, sdkFeedBackCallback, arrayList);
        } else {
            cft_(feedBackRequest, activity, sdkFeedBackCallback, arrayList);
        }
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void downLoadFile(Activity activity, String str, VideoCallBack videoCallBack, String str2) {
        a(activity, str, str2, false, videoCallBack);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.IProblemManager
    public void addReadListener(OnReadListener onReadListener) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(onReadListener);
    }

    public static Sdk h() {
        return com.huawei.phoneservice.faq.base.util.j.c();
    }

    public static long d() {
        return b;
    }

    public static int b() {
        return d;
    }

    public static int a() {
        return c;
    }

    public static IProblemManager e() {
        return f.c;
    }

    public static String c() {
        return f8342a;
    }

    private void cfB_(FeedBackRequest feedBackRequest, Activity activity, SdkFeedBackCallback sdkFeedBackCallback, List<FeedBackResponse.ProblemEnity> list) {
        com.huawei.phoneservice.faq.base.util.i.c("SdkProblemManager", "doRequestForParent");
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(activity, feedBackRequest, new i(FeedBackResponse.ProblemEnity.class, activity, list, feedBackRequest, activity, sdkFeedBackCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<FeedBackResponse.ProblemEnity> list, SdkFeedBackCallback sdkFeedBackCallback) {
        if (com.huawei.phoneservice.faq.base.util.b.b(list)) {
            list = new ArrayList<>();
        }
        sdkFeedBackCallback.setListView(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void cfA_(java.io.File r10, com.huawei.phoneservice.faq.base.util.VideoCallBack r11, android.app.Activity r12, java.lang.String r13, java.lang.String r14) {
        /*
            r9 = this;
            java.lang.String r0 = "accessToken"
            java.lang.String r1 = "SdkProblemManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L83 java.io.FileNotFoundException -> L87
            r4.<init>(r10)     // Catch: java.lang.Throwable -> L83 java.io.FileNotFoundException -> L87
            java.util.Scanner r10 = new java.util.Scanner     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L88
            r10.<init>(r4)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L88
        L14:
            boolean r3 = r10.hasNextLine()     // Catch: java.lang.Throwable -> L7c java.io.FileNotFoundException -> L7e
            if (r3 == 0) goto L22
            java.lang.String r3 = r10.nextLine()     // Catch: java.lang.Throwable -> L7c java.io.FileNotFoundException -> L7e
            r2.append(r3)     // Catch: java.lang.Throwable -> L7c java.io.FileNotFoundException -> L7e
            goto L14
        L22:
            r10.close()
            r4.close()     // Catch: java.io.IOException -> L29
            goto L31
        L29:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r1, r10)
        L31:
            java.lang.String r10 = r2.toString()     // Catch: java.lang.Throwable -> L78
            com.google.gson.JsonElement r10 = com.google.gson.JsonParser.parseString(r10)     // Catch: java.lang.Throwable -> L78
            com.google.gson.JsonObject r10 = r10.getAsJsonObject()     // Catch: java.lang.Throwable -> L78
            java.lang.String r1 = "responseCode"
            com.google.gson.JsonElement r10 = r10.get(r1)     // Catch: java.lang.Throwable -> L78
            if (r10 != 0) goto L49
            r9.c(r11)     // Catch: java.lang.Throwable -> L78
            return
        L49:
            int r10 = r10.getAsInt()     // Catch: java.lang.Throwable -> L78
            r1 = 401(0x191, float:5.62E-43)
            if (r10 != r1) goto L74
            com.huawei.phoneservice.faq.base.util.ISdk r10 = com.huawei.phoneservice.faq.base.util.j.e()     // Catch: java.lang.Throwable -> L78
            com.huawei.phoneservice.feedbackcommon.utils.b$e r8 = new com.huawei.phoneservice.feedbackcommon.utils.b$e     // Catch: java.lang.Throwable -> L78
            r3 = 0
            r1 = r8
            r2 = r9
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r11
            r1.<init>(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L78
            r10.registerUpdateListener(r8)     // Catch: java.lang.Throwable -> L78
            com.huawei.phoneservice.faq.base.util.ISdk r10 = com.huawei.phoneservice.faq.base.util.j.e()     // Catch: java.lang.Throwable -> L78
            com.huawei.phoneservice.faq.base.util.Sdk r12 = h()     // Catch: java.lang.Throwable -> L78
            java.lang.String r12 = r12.getSdk(r0)     // Catch: java.lang.Throwable -> L78
            r10.onSdkErr(r0, r12)     // Catch: java.lang.Throwable -> L78
            goto L7b
        L74:
            r9.c(r11)     // Catch: java.lang.Throwable -> L78
            goto L7b
        L78:
            r9.c(r11)
        L7b:
            return
        L7c:
            r11 = move-exception
            goto La4
        L7e:
            r3 = r10
            goto L88
        L80:
            r10 = move-exception
            r11 = r10
            goto La5
        L83:
            r10 = move-exception
            r11 = r10
            r10 = r3
            goto La3
        L87:
            r4 = r3
        L88:
            r9.c(r11)     // Catch: java.lang.Throwable -> L9f
            if (r3 == 0) goto L90
            r3.close()
        L90:
            if (r4 == 0) goto L9e
            r4.close()     // Catch: java.io.IOException -> L96
            goto L9e
        L96:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r1, r10)
        L9e:
            return
        L9f:
            r10 = move-exception
            r11 = r10
            r10 = r3
            r3 = r4
        La3:
            r4 = r3
        La4:
            r3 = r10
        La5:
            if (r3 == 0) goto Laa
            r3.close()
        Laa:
            if (r4 == 0) goto Lb8
            r4.close()     // Catch: java.io.IOException -> Lb0
            goto Lb8
        Lb0:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            com.huawei.phoneservice.faq.base.util.i.c(r1, r10)
        Lb8:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.b.cfA_(java.io.File, com.huawei.phoneservice.faq.base.util.VideoCallBack, android.app.Activity, java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cfz_(File file, Activity activity, String str, String str2, VideoCallBack videoCallBack) {
        new Thread(new c(activity, str, str2, file, videoCallBack)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FeedBackResponse.ProblemEnity problemEnity, SdkFeedBackCallback sdkFeedBackCallback) {
        if (problemEnity == null) {
            sdkFeedBackCallback.setListView(null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(problemEnity);
        sdkFeedBackCallback.setListView(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cft_(FeedBackRequest feedBackRequest, Activity activity, SdkFeedBackCallback sdkFeedBackCallback, List<FeedBackResponse.ProblemEnity> list) {
        com.huawei.phoneservice.faq.base.util.i.c("SdkProblemManager", "doRequestForChild");
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(activity, feedBackRequest, new g(FeedBackResponse.class, activity, list, sdkFeedBackCallback, feedBackRequest, activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(VideoCallBack videoCallBack) {
        if (videoCallBack != null) {
            videoCallBack.setChangeImage(null, false);
        }
    }

    /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.b$b, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    class C0236b extends FaqCallback<FeedBackResponse.ProblemEnity> {
        final /* synthetic */ Activity c;
        final /* synthetic */ SdkFeedBackCallback d;
        final /* synthetic */ FeedBackRequest e;

        /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.b$b$b, reason: collision with other inner class name */
        class C0237b extends BaseSdkUpdateRequest<FeedBackRequest> {

            /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.b$b$b$b, reason: collision with other inner class name */
            class C0238b extends FaqCallback<FeedBackResponse.ProblemEnity> {
                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
                    if (th != null) {
                        C0236b.this.d.setThrowableView(th);
                    } else {
                        C0236b c0236b = C0236b.this;
                        b.this.e(problemEnity, c0236b.d);
                    }
                }

                C0238b(Class cls, Activity activity) {
                    super(cls, activity);
                }
            }

            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(C0236b.this.c, feedBackRequest, new C0238b(FeedBackResponse.ProblemEnity.class, C0236b.this.c));
                }
            }

            C0237b(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
            if (th == null) {
                b.this.e(problemEnity, this.d);
                return;
            }
            if (!(th instanceof FaqWebServiceException) || ((FaqWebServiceException) th).errorCode != 401 || !com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                this.d.setThrowableView(th);
            } else {
                com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(new C0237b(this.e));
                com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", b.h().getSdk("accessToken"));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0236b(Class cls, Activity activity, SdkFeedBackCallback sdkFeedBackCallback, FeedBackRequest feedBackRequest, Activity activity2) {
            super(cls, activity);
            this.d = sdkFeedBackCallback;
            this.e = feedBackRequest;
            this.c = activity2;
        }
    }

    /* loaded from: classes9.dex */
    class g extends FaqCallback<FeedBackResponse> {
        final /* synthetic */ FeedBackRequest b;
        final /* synthetic */ Activity c;
        final /* synthetic */ SdkFeedBackCallback d;
        final /* synthetic */ List e;

        class d extends BaseSdkUpdateRequest<FeedBackRequest> {

            class e extends FaqCallback<FeedBackResponse> {
                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
                    if (th == null) {
                        g.this.e.addAll(feedBackResponse.getDataList());
                        g gVar = g.this;
                        b.this.b(gVar.e, gVar.d);
                    } else if (com.huawei.phoneservice.faq.base.util.b.b(g.this.e)) {
                        g.this.d.setThrowableView(th);
                    } else {
                        g gVar2 = g.this;
                        gVar2.d.setListView(gVar2.e);
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
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getFeedBackList(g.this.c, feedBackRequest, new e(FeedBackResponse.class, g.this.c));
                }
            }

            d(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse feedBackResponse) {
            if (th == null) {
                this.e.addAll(feedBackResponse.getDataList());
                b.this.b(this.e, this.d);
            } else if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 401 && com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(new d(this.b));
                com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", b.h().getSdk("accessToken"));
            } else if (com.huawei.phoneservice.faq.base.util.b.b(this.e)) {
                this.d.setThrowableView(th);
            } else {
                this.d.setListView(this.e);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(Class cls, Activity activity, List list, SdkFeedBackCallback sdkFeedBackCallback, FeedBackRequest feedBackRequest, Activity activity2) {
            super(cls, activity);
            this.e = list;
            this.d = sdkFeedBackCallback;
            this.b = feedBackRequest;
            this.c = activity2;
        }
    }

    /* loaded from: classes9.dex */
    class i extends FaqCallback<FeedBackResponse.ProblemEnity> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f8349a;
        final /* synthetic */ FeedBackRequest b;
        final /* synthetic */ SdkFeedBackCallback c;
        final /* synthetic */ List d;

        /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.b$i$b, reason: collision with other inner class name */
        class C0239b extends BaseSdkUpdateRequest<FeedBackRequest> {

            /* renamed from: com.huawei.phoneservice.feedbackcommon.utils.b$i$b$b, reason: collision with other inner class name */
            class C0240b extends FaqCallback<FeedBackResponse.ProblemEnity> {
                final /* synthetic */ FeedBackRequest e;

                @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
                    if (th != null) {
                        i.this.c.setThrowableView(th);
                        return;
                    }
                    i iVar = i.this;
                    if (problemEnity == null) {
                        b.this.b(null, iVar.c);
                        return;
                    }
                    iVar.d.add(problemEnity);
                    i iVar2 = i.this;
                    b.this.cft_(this.e, iVar2.f8349a, iVar2.c, iVar2.d);
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0240b(Class cls, Activity activity, FeedBackRequest feedBackRequest) {
                    super(cls, activity);
                    this.e = feedBackRequest;
                }
            }

            @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onCallback(String str, String str2, String str3, FeedBackRequest feedBackRequest) {
                if ("accessToken".equals(str)) {
                    com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                    feedBackRequest.setAccessToken(str3);
                    com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.getDataFromDetail(i.this.f8349a, feedBackRequest, new C0240b(FeedBackResponse.ProblemEnity.class, i.this.f8349a, feedBackRequest));
                }
            }

            C0239b(FeedBackRequest feedBackRequest) {
                super(feedBackRequest);
            }
        }

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, FeedBackResponse.ProblemEnity problemEnity) {
            if (th == null) {
                if (problemEnity == null) {
                    b.this.b(null, this.c);
                    return;
                } else {
                    this.d.add(problemEnity);
                    b.this.cft_(this.b, this.f8349a, this.c, this.d);
                    return;
                }
            }
            if (!(th instanceof FaqWebServiceException) || ((FaqWebServiceException) th).errorCode != 401 || !com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                this.c.setThrowableView(th);
            } else {
                com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(new C0239b(this.b));
                com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", b.h().getSdk("accessToken"));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(Class cls, Activity activity, List list, FeedBackRequest feedBackRequest, Activity activity2, SdkFeedBackCallback sdkFeedBackCallback) {
            super(cls, activity);
            this.d = list;
            this.b = feedBackRequest;
            this.f8349a = activity2;
            this.c = sdkFeedBackCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, String str, String str2, boolean z, VideoCallBack videoCallBack) {
        String sdk = h().getSdk("accessToken");
        if (activity == null || TextUtils.isEmpty(sdk) || TextUtils.isEmpty(str)) {
            c(videoCallBack);
        } else {
            Glide.with(activity.getApplicationContext()).asFile().load((Object) new GlideUrl(str, new LazyHeaders.Builder().addHeader("accessToken", sdk).build())).skipMemoryCache(true).listener(new a(videoCallBack)).into((RequestBuilder) new d(z, videoCallBack, activity, str, str2));
        }
    }

    class a implements RequestListener<File> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ VideoCallBack f8343a;

        @Override // com.bumptech.glide.request.RequestListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean onResourceReady(File file, Object obj, Target<File> target, DataSource dataSource, boolean z) {
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target<File> target, boolean z) {
            b.this.c(this.f8343a);
            return false;
        }

        a(VideoCallBack videoCallBack) {
            this.f8343a = videoCallBack;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cfs_(Activity activity, String str, String str2, File file, VideoCallBack videoCallBack) {
        long j;
        try {
            if (FeedbackMediaData.a(activity).b(str2) == null) {
                MediaEntity mediaEntity = new MediaEntity();
                if (MimeType.isVideoFromUrl(str)) {
                    mediaEntity.type = MimeType.getMimeType(str);
                    j = b(file.getCanonicalPath());
                } else {
                    mediaEntity.type = MimeType.getMimeType(str);
                    j = 0L;
                }
                mediaEntity.duration = j;
                mediaEntity.attach = str2;
                mediaEntity.cache = file.getCanonicalPath();
                mediaEntity.path = file.getCanonicalPath();
                mediaEntity.updateTime = Long.valueOf(System.currentTimeMillis());
                mediaEntity.createTime = Long.valueOf(System.currentTimeMillis());
                mediaEntity.url = str;
                FeedbackMediaData.a(activity).c(mediaEntity);
            } else {
                FeedbackMediaData.a(activity).e(str2, file.getCanonicalPath());
                if (MimeType.isVideoFromUrl(str)) {
                    FeedbackMediaData.a(activity).a(str2, b(file.getCanonicalPath()));
                }
            }
        } catch (IOException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("SdkProblemManager", e2.getMessage());
        }
        if (videoCallBack != null) {
            videoCallBack.setChangeImage(file, MimeType.isVideoFromUrl(str));
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f8345a;
        final /* synthetic */ String b;
        final /* synthetic */ VideoCallBack c;
        final /* synthetic */ Activity d;
        final /* synthetic */ String e;

        @Override // java.lang.Runnable
        public void run() {
            Activity activity = this.d;
            if (activity == null || activity.isFinishing() || this.d.isDestroyed()) {
                return;
            }
            b.this.cfs_(this.d, this.e, this.b, this.f8345a, this.c);
        }

        c(Activity activity, String str, String str2, File file, VideoCallBack videoCallBack) {
            this.d = activity;
            this.e = str;
            this.b = str2;
            this.f8345a = file;
            this.c = videoCallBack;
        }
    }

    class d extends SimpleTarget<File> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ VideoCallBack f8346a;
        final /* synthetic */ Activity b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;
        final /* synthetic */ String e;

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(File file, Transition<? super File> transition) {
            if (file.length() >= 100) {
                b.this.cfz_(file, this.b, this.e, this.c, this.f8346a);
                return;
            }
            if (!this.d) {
                b.this.cfA_(file, this.f8346a, this.b, this.e, this.c);
            } else {
                b.this.c(this.f8346a);
            }
            file.delete();
        }

        d(boolean z, VideoCallBack videoCallBack, Activity activity, String str, String str2) {
            this.d = z;
            this.f8346a = videoCallBack;
            this.b = activity;
            this.e = str;
            this.c = str2;
        }
    }

    class e extends BaseSdkUpdateRequest<Object> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8347a;
        final /* synthetic */ Activity b;
        final /* synthetic */ String c;
        final /* synthetic */ VideoCallBack d;

        @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
        public void onCallback(String str, String str2, String str3, Object obj) {
            b.this.a(this.b, this.c, this.f8347a, true, this.d);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Object obj, Activity activity, String str, String str2, VideoCallBack videoCallBack) {
            super(obj);
            this.b = activity;
            this.c = str;
            this.f8347a = str2;
            this.d = videoCallBack;
        }
    }

    static class f {
        private static final b c = new b(null);
    }

    private Long b(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        return Long.valueOf(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)));
    }

    /* synthetic */ b(d dVar) {
        this();
    }

    private b() {
    }
}
