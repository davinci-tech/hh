package com.huawei.phoneservice.feedbackcommon.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.network.FaqCallback;
import com.huawei.phoneservice.faq.base.network.FaqWebServiceException;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.feedbackcommon.db.FeedbackProblemData;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemEntity;
import com.huawei.phoneservice.feedbackcommon.entity.o;
import com.huawei.phoneservice.feedbackcommon.entity.y;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8353a;
    private String b;
    private WeakReference<Context> c;
    private WeakReference<OnReadListener> d;
    private WeakReference<OnReadListener> e;
    private LinkedList<ProblemEntity> g;

    public void e(OnReadListener onReadListener) {
        if (onReadListener != null) {
            this.e = new WeakReference<>(onReadListener);
        }
        List<ProblemEntity> list = null;
        if (!com.huawei.phoneservice.faq.base.util.j.e().hadAddress() || !ModuleConfigUtils.isNativeFeedback() || TextUtils.isEmpty(com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"))) {
            com.huawei.phoneservice.faq.base.util.i.c("GetUnreadTask", " ERROR");
            e(null, 0);
            return;
        }
        if (TextUtils.isEmpty(this.b)) {
            try {
                list = FeedbackProblemData.a(this.c.get()).a();
            } catch (Exception e) {
                com.huawei.phoneservice.faq.base.util.i.c("GetUnreadTask", e.getMessage());
            }
            if (list != null && !list.isEmpty()) {
                this.g = new LinkedList<>(list);
                d();
                return;
            }
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        String a2 = this.g.getLast().a();
        a.f8341a.setRead(this.c.get(), com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"), a2, new d(y.class, null, a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.huawei.phoneservice.feedbackcommon.entity.l lVar = new com.huawei.phoneservice.feedbackcommon.entity.l(com.huawei.phoneservice.faq.base.util.j.c().getSdk("accessToken"), com.huawei.phoneservice.faq.base.util.j.c().getSdk("channel"), this.b);
        lVar.a(this.f8353a ? 2 : 1);
        lVar.c(50);
        a.f8341a.getUnread(this.c.get(), lVar, new c(com.huawei.phoneservice.feedbackcommon.entity.o.class, null));
    }

    private OnReadListener a() {
        WeakReference<OnReadListener> weakReference = this.d;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Throwable th, int i) {
        OnReadListener e = e();
        if (e == null && (e = a()) == null) {
            return;
        }
        e.unread(th, this.b, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        OnReadListener a2 = a();
        if (a2 != null) {
            a2.read(null, str);
        }
    }

    class c extends FaqCallback<com.huawei.phoneservice.feedbackcommon.entity.o> {
        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, com.huawei.phoneservice.feedbackcommon.entity.o oVar) {
            int i;
            if (oVar == null || oVar.d() == null) {
                i = 0;
            } else {
                i = 0;
                for (o.d dVar : oVar.d()) {
                    if (dVar != null && !dVar.e() && j.this.f8353a == dVar.d()) {
                        i++;
                    }
                }
            }
            j.this.e(th, i > 0 ? 1 : 0);
        }

        c(Class cls, Activity activity) {
            super(cls, activity);
        }
    }

    class d extends FaqCallback<y> {
        final /* synthetic */ String e;

        @Override // com.huawei.phoneservice.faq.base.network.FaqCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th, y yVar) {
            boolean z = th == null;
            boolean z2 = (th instanceof FaqWebServiceException) && ((FaqWebServiceException) th).errorCode == 400;
            if (z || z2) {
                try {
                    FeedbackProblemData.a((Context) j.this.c.get()).d(this.e);
                } catch (Exception e) {
                    com.huawei.phoneservice.faq.base.util.i.c("GetUnreadTask", e.getMessage());
                }
                j.this.a(this.e);
                j.this.g.removeLast();
                if (!j.this.g.isEmpty()) {
                    j.this.d();
                    return;
                } else {
                    j.this.c();
                    return;
                }
            }
            j.this.e(th, 0);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, Activity activity, String str) {
            super(cls, activity);
            this.e = str;
        }
    }

    private OnReadListener e() {
        WeakReference<OnReadListener> weakReference = this.e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public j(Context context, String str, boolean z, OnReadListener onReadListener) {
        this.b = str;
        this.f8353a = z;
        this.c = new WeakReference<>(context);
        if (onReadListener != null) {
            this.d = new WeakReference<>(onReadListener);
        }
    }
}
