package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.util.BaseSdkUpdateRequest;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    protected WeakReference<Submit> f8350a;
    protected WeakReference<BaseSdkUpdateRequest> b;
    protected Context d;
    protected boolean c = false;
    protected List<FeedbackZipBean> e = new ArrayList(20);
    protected CancelInterface f = new e();

    class e implements CancelInterface {
        @Override // com.huawei.phoneservice.feedbackcommon.utils.CancelInterface
        public void isCancel(boolean z) {
            if (z) {
                WeakReference<Submit> weakReference = c.this.f8350a;
                if (weakReference != null && weakReference.get() != null) {
                    c.this.f8350a.get().cancel();
                    c.this.f8350a.clear();
                    c.this.f8350a = null;
                }
                WeakReference<BaseSdkUpdateRequest> weakReference2 = c.this.b;
                if (weakReference2 == null || weakReference2.get() == null) {
                    return;
                }
                com.huawei.phoneservice.faq.base.util.j.e().unregisterUpdateListener(c.this.b.get());
                c.this.b.clear();
                c.this.b = null;
            }
        }

        e() {
        }
    }

    public CancelInterface a() {
        return this.f;
    }
}
