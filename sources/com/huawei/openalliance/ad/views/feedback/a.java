package com.huawei.openalliance.ad.views.feedback;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.webkit.JavascriptInterface;
import com.huawei.openalliance.ad.beans.feedback.AdditionalContext;
import com.huawei.openalliance.ad.beans.feedback.ComplainAddInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cu;
import com.huawei.openalliance.ad.utils.cz;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class a implements f {

    /* renamed from: a, reason: collision with root package name */
    private ComplainAddInfo f8069a = new ComplainAddInfo();
    private WeakReference<h> b;

    @Override // com.huawei.openalliance.ad.views.feedback.f
    @JavascriptInterface
    public String complainAddInfo() {
        String b = be.b(this.f8069a);
        return cz.b(b) ? "" : b;
    }

    @Override // com.huawei.openalliance.ad.views.feedback.f
    public void b() {
        h hVar;
        ho.b("ComplainJS", "no submit");
        WeakReference<h> weakReference = this.b;
        if (weakReference == null || (hVar = weakReference.get()) == null) {
            return;
        }
        hVar.b();
    }

    @Override // com.huawei.openalliance.ad.views.feedback.f
    @JavascriptInterface
    public void afterSubmit(String str) {
        h hVar;
        ho.b("ComplainJS", "submit success");
        WeakReference<h> weakReference = this.b;
        if (weakReference == null || (hVar = weakReference.get()) == null) {
            return;
        }
        hVar.a();
        ji.a().a(NotifyMessageNames.AD_COMPLAIN_MESSAGE_NAME, new Intent(NotifyMessageNames.AD_COMPLAIN_ACTION));
    }

    @Override // com.huawei.openalliance.ad.views.feedback.f
    public ComplainAddInfo a() {
        return this.f8069a;
    }

    private void a(h hVar) {
        if (hVar != null) {
            this.b = new WeakReference<>(hVar);
        }
    }

    private void a(ContentRecord contentRecord, AdditionalContext additionalContext) {
        if (contentRecord != null) {
            MetaData h = contentRecord.h();
            if (h != null && 3 != contentRecord.aO()) {
                if (!cz.b(h.e())) {
                    additionalContext.a(cz.c(h.e()));
                }
                if (!bg.a(h.M())) {
                    additionalContext.a(h.M());
                }
                if (!bg.a(h.L())) {
                    additionalContext.b(h.L());
                }
            }
            if (3 != contentRecord.aO() && !cz.b(h.h())) {
                additionalContext.d(h.h());
            }
            additionalContext.b(contentRecord.m());
        }
    }

    private void a(Context context, ContentRecord contentRecord) {
        try {
            ho.b("ComplainJS", "config add info");
            AdditionalContext additionalContext = new AdditionalContext();
            Pair<String, Boolean> b = com.huawei.openalliance.ad.utils.d.b(context, true);
            if (b != null) {
                this.f8069a.a((String) b.first);
            } else {
                this.f8069a.a(ao.a());
            }
            a(contentRecord, additionalContext);
            additionalContext.e(cu.a(be.b(additionalContext)));
            if (contentRecord != null) {
                additionalContext.c(contentRecord.l());
            }
            this.f8069a.a(additionalContext);
        } catch (Throwable th) {
            ho.b("ComplainJS", "config add info err: %s", th.getClass().getSimpleName());
        }
    }

    public a(Context context, ContentRecord contentRecord, h hVar) {
        a(hVar);
        a(context, contentRecord);
    }
}
