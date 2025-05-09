package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.IAdEvent;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import java.util.List;

/* loaded from: classes9.dex */
public class ok implements IAdEvent {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.openalliance.ad.inter.data.e f7348a;
    private qq b;
    private ContentRecord c;
    private String e;
    private int d = 0;
    private final byte[] f = new byte[0];

    @Override // com.huawei.openalliance.ad.inter.data.IAdEvent
    public void onAdShowed(View view) {
        if (this.f7348a == null || this.c == null) {
            ho.b("IAdEventProcessor", " ad is not valid");
            return;
        }
        if (!a(view)) {
            ho.b("IAdEventProcessor", " ad view is not visible!");
            return;
        }
        synchronized (this.f) {
            String valueOf = !TextUtils.isEmpty(this.e) ? this.e : String.valueOf(com.huawei.openalliance.ad.utils.ao.c());
            this.f7348a.h(valueOf);
            this.c.c(valueOf);
            ContentRecord contentRecord = this.c;
            int i = this.d + 1;
            this.d = i;
            contentRecord.d(i);
            this.e = null;
            this.b.b();
            int b = b(view);
            this.b.a(this.f7348a.getMinEffectiveShowTime(), b);
            a.C0207a c0207a = new a.C0207a();
            String e = com.huawei.openalliance.ad.utils.dd.e(view);
            com.huawei.openalliance.ad.inter.data.e eVar = this.f7348a;
            if (eVar != null) {
                ho.a("IAdEventProcessor", "slotId: %s, contentId: %s, slot pos: %s", eVar.getSlotId(), this.f7348a.getContentId(), e);
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(e)) {
                c0207a.d(e);
            }
            c0207a.a(Long.valueOf(this.f7348a.getMinEffectiveShowTime())).a(Integer.valueOf(b)).b((Integer) 7).a(com.huawei.openalliance.ad.utils.b.a(view.getContext())).e(vd.b(view)).c((Integer) 0);
            this.b.a(c0207a.a());
        }
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAdEvent
    public void onAdClosed(List<String> list) {
        if (this.f7348a == null || this.c == null) {
            ho.b("IAdEventProcessor", "  ad is not valid");
        } else if (a()) {
            this.b.a(0, 0, list);
        }
    }

    private int b(View view) {
        String str;
        if (view == null) {
            str = " ad view is null";
        } else if (view.isShown()) {
            int width = view.getWidth() * view.getHeight();
            if (width <= 0) {
                str = " ad viewArea is zero";
            } else {
                Rect rect = new Rect();
                if (view.getLocalVisibleRect(rect)) {
                    int width2 = rect.width() * rect.height();
                    if (width2 > 0) {
                        return (width2 * 100) / width;
                    }
                    str = " ad view is not visible, visibleArea is zero";
                } else {
                    str = " ad view is not visible";
                }
            }
        } else {
            str = " ad view is not shown";
        }
        ho.b("IAdEventProcessor", str);
        return 0;
    }

    private boolean a(View view) {
        String str;
        if (view == null) {
            str = " ad view is null";
        } else {
            if (view.isShown()) {
                return true;
            }
            str = " ad view is not shown";
        }
        ho.b("IAdEventProcessor", str);
        return false;
    }

    private boolean a() {
        if (this.f7348a == null || this.c == null) {
            ho.b("IAdEventProcessor", "  ad is not valid");
            return false;
        }
        synchronized (this.f) {
            String showId = this.f7348a.getShowId();
            if (TextUtils.isEmpty(showId)) {
                showId = String.valueOf(com.huawei.openalliance.ad.utils.ao.c());
            }
            this.e = showId;
            this.c.c(showId);
        }
        return true;
    }

    public ok(Context context, com.huawei.openalliance.ad.inter.data.e eVar) {
        this.f7348a = eVar;
        this.b = new ou(context, sc.a(context, eVar.e()));
        ContentRecord a2 = pd.a(eVar);
        this.c = a2;
        a2.c(eVar.getShowId());
        this.b.a(this.c);
    }
}
