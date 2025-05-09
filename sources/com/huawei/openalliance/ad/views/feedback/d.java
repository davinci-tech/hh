package com.huawei.openalliance.ad.views.feedback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.openalliance.ad.activity.AdComplainActivity;
import com.huawei.openalliance.ad.beans.feedback.AdditionalContext;
import com.huawei.openalliance.ad.beans.feedback.ComplainAddInfo;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.dd;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<g> f8071a;
    private ContentRecord b;
    private List<FeedbackInfo> c;
    private List<FeedbackInfo> d;
    private FeedbackInfo e;
    private Context f;
    private f g;

    public boolean f() {
        ho.b("FeedbackPresenter", "click complain");
        if (this.e == null) {
            return false;
        }
        try {
            Intent intent = new Intent(this.f, (Class<?>) AdComplainActivity.class);
            intent.putExtra(MapKeyNames.COMPLAIN_H5_TITLE, this.e.getLabel());
            AdComplainActivity.a(this.g);
            if (!(this.f instanceof Activity)) {
                intent.addFlags(268435456);
            }
            dd.a(this.f, intent);
            g();
            return true;
        } catch (Throwable th) {
            ho.c("FeedbackPresenter", "start ac failed: %s", th.getClass().getSimpleName());
            return true;
        }
    }

    public String e() {
        ContentRecord contentRecord = this.b;
        return contentRecord != null ? contentRecord.bb() : "";
    }

    public boolean d() {
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            return contentRecord.bc();
        }
        return false;
    }

    public FeedbackInfo c() {
        return this.e;
    }

    public List<FeedbackInfo> b() {
        return this.d;
    }

    public boolean a(Context context) {
        ContentRecord contentRecord = this.b;
        if (contentRecord == null) {
            return false;
        }
        return ao.a(context, contentRecord);
    }

    public void a(Context context, ContentRecord contentRecord, h hVar) {
        List<FeedbackInfo> list;
        this.f = context.getApplicationContext();
        if (contentRecord == null || bg.a(contentRecord.aL())) {
            return;
        }
        this.b = contentRecord;
        this.g = new a(context, contentRecord, hVar);
        List<FeedbackInfo> aL = this.b.aL();
        this.d = new ArrayList();
        this.c = new ArrayList();
        for (FeedbackInfo feedbackInfo : aL) {
            if (feedbackInfo != null) {
                int type = feedbackInfo.getType();
                if (type == 1) {
                    list = this.d;
                } else if (type == 2) {
                    list = this.c;
                } else if (type != 3) {
                    ho.a("FeedbackPresenter", "invalid feedback type");
                } else {
                    this.e = feedbackInfo;
                }
                list.add(feedbackInfo);
            }
        }
        g gVar = this.f8071a.get();
        if (gVar != null) {
            gVar.c();
        }
    }

    public List<FeedbackInfo> a() {
        return this.c;
    }

    private void g() {
        AdditionalContext a2;
        ComplainAddInfo a3 = this.g.a();
        if (a3 == null || (a2 = a3.a()) == null) {
            return;
        }
        ho.b("FeedbackPresenter", "add info: %s", a2.toString());
    }

    public d(g gVar) {
        this.f8071a = new WeakReference<>(gVar);
    }
}
