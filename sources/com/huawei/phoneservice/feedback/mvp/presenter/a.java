package com.huawei.phoneservice.feedback.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigRequest;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigResponse;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.FaqHandler;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.h;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.r;
import com.huawei.phoneservice.feedback.ui.ProductSuggestionActivity;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackMediaData;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;
import com.huawei.phoneservice.feedbackcommon.utils.CancelInterface;
import com.huawei.phoneservice.feedbackcommon.utils.CompressTask;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class a extends com.huawei.phoneservice.feedback.mvp.base.d<com.huawei.phoneservice.feedback.mvp.contract.a> implements FaqHandler.CallBack {
    private List<com.huawei.phoneservice.feedback.entity.c> s;
    private LinkedList<MediaEntity> t;
    private List<String> u;
    private LinkedList<Pair<Integer, MediaEntity>> v;
    private long y;

    public void i() {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().zipCompress(this.j, this);
    }

    @Override // com.huawei.phoneservice.faq.base.util.FaqHandler.CallBack
    public void handleMessage(int i, Message message) {
        if (i == 100) {
            this.q = com.huawei.phoneservice.feedbackcommon.utils.b.e().reUploadZipWithCancel(this.j, this.b, ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getLogsSize(), ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getZipFileName(), this);
            return;
        }
        if (i == 3) {
            if (!com.huawei.phoneservice.faq.base.util.b.b(this.v)) {
                cex_(this.v.removeFirst());
                return;
            } else {
                ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a(false);
                ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().setFilesSize(this.y);
                return;
            }
        }
        if (i == 4) {
            String str = (String) message.obj;
            this.u.add(str);
            ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).b(str);
            int size = this.u.size();
            if (size < this.t.size()) {
                d(this.t.get(size));
                return;
            } else {
                a(this.u);
                return;
            }
        }
        if (i != 5) {
            if (i == 6) {
                this.l = 0;
                this.m = null;
                String str2 = (String) message.obj;
                ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a();
                ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a(str2);
                this.e = false;
                return;
            }
            if (i != 7) {
                return;
            }
        }
        String str3 = (String) message.obj;
        if (this.l == 0) {
            b(str3);
        } else {
            this.l = 3;
            this.m = str3;
        }
    }

    public void h() {
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        CancelInterface cancelInterface = this.q;
        if (cancelInterface != null) {
            cancelInterface.isCancel(true);
        }
        ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a();
        this.e = false;
        this.f = true;
        if (com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener() != null) {
            com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener().onSubmitResult(-1, null, ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getProblemId(), ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getSrCode(), "");
        }
        this.l = 0;
        CancelInterface cancelInterface2 = this.q;
        if (cancelInterface2 != null) {
            cancelInterface2.isCancel(false);
        }
    }

    public void g() {
        if (this.l == 1) {
            this.l = 0;
            return;
        }
        if (this.l == 2) {
            this.l = 0;
            a(this.u);
        } else {
            if (this.l != 3 || TextUtils.isEmpty(this.m)) {
                return;
            }
            this.l = 0;
            Handler handler = this.i;
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(7, this.m));
            }
        }
    }

    public void f() {
        if (this.l == 0) {
            this.l = 1;
        }
    }

    public List<com.huawei.phoneservice.feedback.entity.c> e() {
        return this.s;
    }

    public void c() {
        LinkedList<MediaEntity> linkedList = this.t;
        if (linkedList == null) {
            this.t = new LinkedList<>();
        } else {
            linkedList.clear();
        }
        LinkedList<Pair<Integer, MediaEntity>> linkedList2 = this.v;
        if (linkedList2 == null) {
            this.v = new LinkedList<>();
        } else {
            linkedList2.clear();
        }
        this.y = 0L;
    }

    public boolean d() {
        return !this.e;
    }

    public void d(Context context, boolean z) {
        if (this.j instanceof ProductSuggestionActivity) {
            ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).b((List<com.huawei.phoneservice.feedback.entity.c>) null);
        } else if (z || ModuleConfigUtils.isEmpty()) {
            e(context, new ModuleConfigRequest(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("country"), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("language"), r.e(), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("channel"), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FAQ_SHASN), com.huawei.phoneservice.faq.base.util.d.h(), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("appVersion"), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("model"), com.huawei.phoneservice.faq.base.util.d.b()), (String) null);
        } else {
            e(context, null);
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void b() {
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.i = null;
        }
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.c
    public void a(boolean z) {
        this.o = z;
        if (com.huawei.phoneservice.faq.base.util.b.b(this.t)) {
            a(this.u);
        } else {
            d(this.t.getFirst());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0086, code lost:
    
        a(r2.u);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x008b, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x007a, code lost:
    
        d(r2.t.getFirst());
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
    
        if (com.huawei.phoneservice.faq.base.util.b.b(r2.t) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x006f, code lost:
    
        if (com.huawei.phoneservice.faq.base.util.b.b(r2.t) == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(android.content.Context r3, boolean r4) {
        /*
            r2 = this;
            V r0 = r2.r
            com.huawei.phoneservice.feedback.mvp.contract.a r0 = (com.huawei.phoneservice.feedback.mvp.contract.a) r0
            r0.e()
            r0 = 1
            r2.e = r0
            r0 = 0
            r2.f = r0
            r2.l = r0
            java.util.List<java.lang.String> r0 = r2.u
            r0.clear()
            V r0 = r2.r
            com.huawei.phoneservice.feedback.mvp.contract.a r0 = (com.huawei.phoneservice.feedback.mvp.contract.a) r0
            com.huawei.phoneservice.feedback.entity.FeedbackBean r0 = r0.c()
            int r0 = r0.getFlag()
            r2.d = r0
            java.lang.String r3 = com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants.getZipFilePath(r3)
            r2.b = r3
            if (r4 == 0) goto L72
            int r3 = r2.d
            r4 = 2
            if (r3 != r4) goto L69
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r2.b
            r4.append(r0)
            java.lang.String r0 = java.io.File.separator
            r4.append(r0)
            V r0 = r2.r
            com.huawei.phoneservice.feedback.mvp.contract.a r0 = (com.huawei.phoneservice.feedback.mvp.contract.a) r0
            com.huawei.phoneservice.feedback.entity.FeedbackBean r0 = r0.c()
            java.lang.String r0 = r0.getZipFileName()
            r4.append(r0)
            java.lang.String r0 = ".zip"
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            java.lang.String r4 = r2.b
            long r0 = r3.length()
            java.lang.String r3 = r3.getName()
            r2.a(r4, r0, r3)
            goto L8b
        L69:
            java.util.LinkedList<com.huawei.phoneservice.feedbackcommon.entity.MediaEntity> r3 = r2.t
            boolean r3 = com.huawei.phoneservice.faq.base.util.b.b(r3)
            if (r3 != 0) goto L86
            goto L7a
        L72:
            java.util.LinkedList<com.huawei.phoneservice.feedbackcommon.entity.MediaEntity> r3 = r2.t
            boolean r3 = com.huawei.phoneservice.faq.base.util.b.b(r3)
            if (r3 != 0) goto L86
        L7a:
            java.util.LinkedList<com.huawei.phoneservice.feedbackcommon.entity.MediaEntity> r3 = r2.t
            java.lang.Object r3 = r3.getFirst()
            com.huawei.phoneservice.feedbackcommon.entity.MediaEntity r3 = (com.huawei.phoneservice.feedbackcommon.entity.MediaEntity) r3
            r2.d(r3)
            goto L8b
        L86:
            java.util.List<java.lang.String> r3 = r2.u
            r2.a(r3)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.mvp.presenter.a.c(android.content.Context, boolean):void");
    }

    public void d(Context context, String str, String str2, String str3) {
        try {
            a(context, str, Long.valueOf(str2).longValue(), str3);
        } catch (NumberFormatException unused) {
            i.d("FeedBackPresenter", "NumberFormatException");
        }
    }

    public void a(Context context, String str, long j, String str2) {
        com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.queryForHD(context, j, str2, new d(com.huawei.phoneservice.feedback.entity.b.class, (Activity) context, str, context));
    }

    public void e(Context context, String str) {
        a(context, str, new e(com.huawei.phoneservice.feedback.entity.e.class, (Activity) context));
    }

    public void b(Context context) {
        ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a(true);
        List<MediaItem> medias = ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getMedias();
        c();
        c(medias);
        if (!com.huawei.phoneservice.faq.base.util.b.b(this.v)) {
            cex_(this.v.removeFirst());
        } else {
            ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a(false);
            ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().setFilesSize(this.y);
        }
    }

    public void b(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    @Override // com.huawei.phoneservice.feedback.mvp.base.f
    public void a() {
        this.i = new FaqHandler(this);
    }

    private void c(List<MediaItem> list) {
        long j;
        long size;
        LinkedList<Pair<Integer, MediaEntity>> linkedList;
        Pair<Integer, MediaEntity> pair;
        int size2 = list.size();
        int i = 0;
        while (i < size2) {
            MediaItem mediaItem = list.get(i);
            String filePath = mediaItem.getFilePath();
            if (TextUtils.isEmpty(filePath)) {
                list.remove(i);
                size2--;
                i--;
            } else {
                MediaEntity d2 = FeedbackMediaData.a(this.j).d(filePath);
                if (!mediaItem.isImage() || mediaItem.getSize() <= com.huawei.phoneservice.feedbackcommon.utils.b.d()) {
                    if (d2 == null) {
                        d2 = new MediaEntity(mediaItem, filePath, mediaItem.getUri());
                    }
                    d2.cache = filePath;
                    d2.duration = Long.valueOf(mediaItem.isVideo() ? mediaItem.getDuration() : 0L);
                    j = this.y;
                    size = mediaItem.getSize();
                } else {
                    if (d2 == null) {
                        d2 = new MediaEntity(mediaItem, filePath, mediaItem.getUri());
                        linkedList = this.v;
                        pair = new Pair<>(Integer.valueOf(i), d2);
                    } else if (d2.noCache()) {
                        linkedList = this.v;
                        pair = new Pair<>(Integer.valueOf(i), d2);
                    } else {
                        File file = new File(d2.cache);
                        if (file.exists()) {
                            j = this.y;
                            size = file.length();
                        } else {
                            linkedList = this.v;
                            pair = new Pair<>(Integer.valueOf(i), d2);
                        }
                    }
                    linkedList.add(pair);
                    this.t.add(d2);
                }
                this.y = j + size;
                this.t.add(d2);
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, String str) {
        e(context, str);
    }

    private void b(String str) {
        this.l = 0;
        this.m = null;
        ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).a();
        ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c(str);
        this.e = false;
        if (com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener() != null) {
            com.huawei.phoneservice.feedbackcommon.utils.b.e().getSdkListener().onSubmitResult(-1, null, ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getProblemId(), ((com.huawei.phoneservice.feedback.mvp.contract.a) this.r).c().getSrCode(), str);
        }
    }

    private void d(MediaEntity mediaEntity) {
        if (this.f) {
            return;
        }
        this.q = com.huawei.phoneservice.feedbackcommon.utils.b.e().uploadAttachmentWithCancel(this.j, mediaEntity, this);
    }

    private void cex_(Pair<Integer, MediaEntity> pair) {
        String b = com.huawei.phoneservice.faq.base.util.e.b(this.j);
        Handler handler = this.i;
        if (handler != null) {
            handler.post(new CompressTask(this.j, ((MediaEntity) pair.second).path, b, new c(pair)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context, ModuleConfigRequest moduleConfigRequest, String str) {
        j.e().queryModuleList(context, moduleConfigRequest, new C0229a(ModuleConfigResponse.class, (Activity) context, context, str));
    }

    /* renamed from: com.huawei.phoneservice.feedback.mvp.presenter.a$a, reason: collision with other inner class name */
    class C0229a extends FaqCallback<ModuleConfigResponse> {
        final /* synthetic */ String b;
        final /* synthetic */ Context d;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, ModuleConfigResponse moduleConfigResponse) {
            Object obj;
            if (th == null && moduleConfigResponse != null) {
                ModuleConfigUtils.genSdkModuleList(this.d, moduleConfigResponse.getModuleList());
                a.this.c(this.d, this.b);
            } else {
                if (th == null) {
                    obj = a.this.r;
                } else {
                    obj = a.this.r;
                }
                ((com.huawei.phoneservice.feedback.mvp.contract.a) obj).a(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0229a(Class cls, Activity activity, Context context, String str) {
            super(cls, activity);
            this.d = context;
            this.b = str;
        }
    }

    class c implements CompressTask.TaskCallBack {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Pair f8269a;

        @Override // com.huawei.phoneservice.feedbackcommon.utils.CompressTask.TaskCallBack
        public void compressDone(Throwable th, String str) {
            MediaEntity mediaEntity = (MediaEntity) this.f8269a.second;
            mediaEntity.cache = str;
            mediaEntity.type = MimeType.JPEG;
            if (a.this.t.size() > ((Integer) this.f8269a.first).intValue()) {
                a.this.t.set(((Integer) this.f8269a.first).intValue(), (MediaEntity) this.f8269a.second);
            }
            a.b(a.this, new File(str).length());
            a.this.i.sendEmptyMessage(3);
        }

        c(Pair pair) {
            this.f8269a = pair;
        }
    }

    class d extends FaqCallback<com.huawei.phoneservice.feedback.entity.b> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8270a;
        final /* synthetic */ Context e;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedback.entity.b bVar) {
            if (th != null || bVar == null) {
                ((com.huawei.phoneservice.feedback.mvp.contract.a) a.this.r).c(th instanceof FaqWebServiceException ? ((FaqWebServiceException) th).errorCode : -1);
            } else {
                a.this.e(this.e, new ModuleConfigRequest(h.d(), h.a(), r.e(), this.f8270a, com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk(FaqConstants.FAQ_SHASN), com.huawei.phoneservice.faq.base.util.d.h(), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("appVersion"), com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("model"), com.huawei.phoneservice.faq.base.util.d.b()), this.f8270a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, String str, Context context) {
            super(cls, activity);
            this.f8270a = str;
            this.e = context;
        }
    }

    class e extends FaqCallback<com.huawei.phoneservice.feedback.entity.e> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedback.entity.e eVar) {
            if (th != null || eVar == null) {
                ((com.huawei.phoneservice.feedback.mvp.contract.a) a.this.r).a(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR);
                return;
            }
            a.this.s.clear();
            List<com.huawei.phoneservice.feedback.entity.c> list = eVar.c;
            if (list != null && !list.isEmpty()) {
                a.this.s.addAll(eVar.c);
            }
            ((com.huawei.phoneservice.feedback.mvp.contract.a) a.this.r).b(a.this.s);
        }

        e(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    static /* synthetic */ long b(a aVar, long j) {
        long j2 = aVar.y + j;
        aVar.y = j2;
        return j2;
    }

    public a(com.huawei.phoneservice.feedback.mvp.contract.a aVar, Context context, Activity activity) {
        super(aVar);
        this.s = new ArrayList();
        this.t = new LinkedList<>();
        this.y = 0L;
        this.v = new LinkedList<>();
        this.u = new ArrayList(com.huawei.phoneservice.feedbackcommon.utils.b.a());
        this.j = context;
        this.k = activity;
    }
}
