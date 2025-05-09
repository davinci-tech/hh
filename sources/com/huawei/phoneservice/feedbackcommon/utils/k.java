package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackProblemData;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemEntity;
import com.huawei.phoneservice.feedbackcommon.entity.y;
import java.lang.ref.WeakReference;
import java.net.ConnectException;

/* loaded from: classes6.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private String f8354a;
    private WeakReference<Context> b;
    private WeakReference<OnReadListener> c;
    private WeakReference<OnReadListener> d;

    public void e(OnReadListener onReadListener) {
        if (onReadListener != null) {
            this.c = new WeakReference<>(onReadListener);
        }
        if (TextUtils.isEmpty(this.f8354a)) {
            a(new Throwable("problemId is Empty"));
        } else if (com.huawei.phoneservice.faq.base.util.j.e().hadAddress()) {
            c();
        } else {
            a(new ConnectException("Unable to connect to server"));
            FeedbackProblemData.a(this.b.get()).a(new ProblemEntity(this.f8354a));
        }
    }

    private void c() {
        a.f8341a.setRead(this.b.get(), com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"), this.f8354a, new c(y.class, null));
    }

    private OnReadListener d() {
        WeakReference<OnReadListener> weakReference = this.d;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Throwable th) {
        OnReadListener e = e();
        if (e == null && (e = d()) == null) {
            return;
        }
        e.read(th, this.f8354a);
    }

    class c extends FaqCallback<y> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, y yVar) {
            boolean z = false;
            boolean z2 = th == null;
            if ((th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 400) {
                z = true;
            }
            if (z2 || z) {
                FeedbackProblemData.a((Context) k.this.b.get()).d(k.this.f8354a);
                k.this.a(null);
            } else {
                FeedbackProblemData.a((Context) k.this.b.get()).a(new ProblemEntity(k.this.f8354a));
                k.this.a(th);
            }
        }

        c(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    private OnReadListener e() {
        WeakReference<OnReadListener> weakReference = this.c;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public k(Context context, String str, OnReadListener onReadListener) {
        this.f8354a = str;
        this.b = new WeakReference<>(context);
        if (onReadListener != null) {
            this.d = new WeakReference<>(onReadListener);
        }
    }
}
