package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackMediaData;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import com.huawei.phoneservice.feedbackcommon.entity.w;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/* loaded from: classes6.dex */
public class m extends c {
    private MediaEntity h;
    private Context i;
    private WeakReference<NotifyUploadFileListener> j;

    public void d(NotifyUploadFileListener notifyUploadFileListener) {
        boolean z;
        if (notifyUploadFileListener != null) {
            this.j = new WeakReference<>(notifyUploadFileListener);
        }
        if (com.huawei.phoneservice.faq.base.util.l.e(this.h.cache)) {
            return;
        }
        File file = new File(this.h.cache);
        if (file.canRead()) {
            z = false;
        } else {
            String str = this.h.cache;
            z = true;
            file = e.cfp_(this.i, Uri.parse(this.h.strUri), str.substring(str.lastIndexOf("/") + 1));
        }
        a(this.h, file, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MediaEntity mediaEntity) {
        Submit uploadFile = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.uploadFile(this.i, new File(mediaEntity.cache), mediaEntity.type, com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"), new d(w.class, null, mediaEntity));
        if (uploadFile == null) {
            return;
        }
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(uploadFile);
    }

    private NotifyUploadFileListener e() {
        WeakReference<NotifyUploadFileListener> weakReference = this.j;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, File file) {
        if (z && file.exists()) {
            file.delete();
        }
    }

    private void a(MediaEntity mediaEntity, File file, boolean z) {
        Submit uploadFile = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a.uploadFile(this.i, file, mediaEntity.type, com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"), new b(w.class, null, mediaEntity, z, file));
        WeakReference<Submit> weakReference = this.f8350a;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f8350a = new WeakReference<>(uploadFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MediaEntity mediaEntity) {
        a aVar = new a(mediaEntity);
        WeakReference<BaseSdkUpdateRequest> weakReference = this.b;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.b = new WeakReference<>(aVar);
        com.huawei.phoneservice.faq.base.util.j.e().registerUpdateListener(aVar);
        com.huawei.phoneservice.faq.base.util.j.e().onSdkErr("accessToken", com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("accessToken"));
    }

    class a extends BaseSdkUpdateRequest<MediaEntity> {
        @Override // com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onCallback(String str, String str2, String str3, MediaEntity mediaEntity) {
            if ("accessToken".equals(str)) {
                com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(this);
                WeakReference<BaseSdkUpdateRequest> weakReference = m.this.b;
                if (weakReference != null) {
                    weakReference.clear();
                    m.this.b = null;
                }
                m.this.b(mediaEntity);
            }
        }

        a(MediaEntity mediaEntity) {
            super(mediaEntity);
        }
    }

    class b extends FaqCallback<w> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f8359a;
        final /* synthetic */ boolean d;
        final /* synthetic */ MediaEntity e;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, w wVar) {
            m mVar;
            int i;
            if (th != null || wVar == null) {
                boolean z = th instanceof FaqWebServiceException;
                if (z && ((FaqWebServiceException) th).errorCode == 401 && com.huawei.phoneservice.faq.base.util.j.e().haveSdkErr("accessToken")) {
                    m.this.c(this.e);
                } else {
                    if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                        mVar = m.this;
                        i = 1;
                    } else if (z && ((FaqWebServiceException) th).errorCode == 408) {
                        mVar = m.this;
                        i = 2;
                    } else {
                        mVar = m.this;
                        if (mVar.f8350a != null) {
                            i = 3;
                        }
                    }
                    mVar.c(5, i, null);
                }
            } else {
                String b = wVar.b();
                MediaEntity mediaEntity = this.e;
                mediaEntity.attach = b;
                mediaEntity.updateTime = Long.valueOf(System.currentTimeMillis());
                FeedbackMediaData.a(m.this.i).c(this.e);
                m.this.c(4, -1, b);
            }
            m.this.c(this.d, this.f8359a);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Class cls, Activity activity, MediaEntity mediaEntity, boolean z, File file) {
            super(cls, activity);
            this.e = mediaEntity;
            this.d = z;
            this.f8359a = file;
        }
    }

    class d extends FaqCallback<w> {
        final /* synthetic */ MediaEntity d;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, w wVar) {
            m mVar;
            int i;
            if (th != null || wVar == null) {
                if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                    mVar = m.this;
                    i = 1;
                } else {
                    mVar = m.this;
                    i = 3;
                }
                mVar.c(5, i, null);
                return;
            }
            String b = wVar.b();
            MediaEntity mediaEntity = this.d;
            mediaEntity.attach = b;
            mediaEntity.updateTime = Long.valueOf(System.currentTimeMillis());
            FeedbackMediaData.a(m.this.i).c(this.d);
            m.this.c(4, -1, b);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, MediaEntity mediaEntity) {
            super(cls, activity);
            this.d = mediaEntity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2, String str) {
        NotifyUploadFileListener e = e();
        if (e != null) {
            e.uploadNotify(i, i2, str);
        }
    }

    public m(Context context, MediaEntity mediaEntity) {
        this.h = mediaEntity;
        this.i = context;
    }
}
