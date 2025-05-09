package com.huawei.openalliance.ad.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.hms.ads.uiengine.common.IInterstitialView;
import com.huawei.hms.ads.uiengine.common.InterstitialApi;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.constant.InterstitialMethods;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.mo;
import com.huawei.openalliance.ad.nc;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.ox;
import com.huawei.openalliance.ad.sz;
import com.huawei.openalliance.ad.ta;
import com.huawei.openalliance.ad.ua;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.PPSInterstitialView;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class r extends AutoScaleSizeRelativeLayout implements IInterstitialView {

    /* renamed from: a, reason: collision with root package name */
    private Context f8139a;
    private ContentRecord b;
    private InterstitialApi c;
    private IInterstitialView d;
    private IRemoteCreator e;
    private nc f;
    private ou g;
    private PPSInterstitialView.b h;
    private com.huawei.openalliance.ad.inter.listeners.a i;
    private com.huawei.openalliance.ad.inter.data.d j;
    private int k;
    private int l;
    private String m;
    private Dialog n;
    private lz o;
    private boolean p;

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void bindData(String str) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public ViewGroup getContentContainer() {
        return null;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onBtnClick(Bundle bundle) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onCallBack(String str, Bundle bundle) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void setIsNeedRemindData(boolean z) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void setProxy(InterstitialApi interstitialApi) {
    }

    public void setOnCloseListener(PPSInterstitialView.b bVar) {
        if (bVar == null) {
            return;
        }
        this.h = bVar;
    }

    public void setNonwifiDialog(Dialog dialog) {
        this.n = dialog;
    }

    public void setEventProcessor(ou ouVar) {
        this.g = ouVar;
    }

    public void setCompleted(boolean z) {
        this.p = z;
        if (z) {
            a(3, (Bundle) null);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void resumeVideoView() {
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.resumeVideoView();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void pauseView() {
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.pauseView();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onAppStatusChanged(String str) {
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.onAppStatusChanged(str);
        }
    }

    public Bundle h() {
        HashMap hashMap = new HashMap();
        com.huawei.openalliance.ad.inter.data.d dVar = this.j;
        if (dVar != null) {
            hashMap.put("appId", dVar.H());
            hashMap.put("thirdId", this.j.G());
        }
        ta a2 = sz.a(this.f8139a, this.b, hashMap);
        if (!a2.a()) {
            return null;
        }
        a(7, (Bundle) null);
        gk gkVar = new gk();
        gkVar.b("click_destination", a2.c());
        a((Integer) 1);
        return gkVar.a();
    }

    public Dialog getNonwifiDialog() {
        return this.n;
    }

    public Bundle g() {
        a(2, (Bundle) null);
        f();
        return h();
    }

    public void f() {
        lz lzVar = this.o;
        if (lzVar != null) {
            lzVar.e();
        }
    }

    public void e() {
        PPSInterstitialView.b bVar = this.h;
        if (bVar != null) {
            bVar.b();
        }
        nc ncVar = this.f;
        if (ncVar != null) {
            ncVar.b();
        }
        a((Integer) 3);
        f();
        a(4, (Bundle) null);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void destroyView() {
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.destroyView();
        }
    }

    public void d() {
        setNonwifiDialog(null);
        e();
    }

    public void c() {
        setNonwifiDialog(null);
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.setIsNeedRemindData(false);
        }
        resumeVideoView();
    }

    public void b() {
        dj.a(new ua(this));
    }

    public void a(Integer num) {
        if (this.o == null || num == null) {
            ho.c("PPSInterstitialViewContainer", "omPresent or impSource is null.");
            return;
        }
        int intValue = num.intValue();
        if (intValue == 1) {
            this.o.a(mo.CLICK);
        } else if (intValue == 3 && !this.p) {
            this.o.j();
            this.o.b();
        }
    }

    public void a(com.huawei.openalliance.ad.inter.listeners.a aVar) {
        if (aVar == null) {
            return;
        }
        this.i = aVar;
    }

    public void a(com.huawei.openalliance.ad.inter.data.d dVar, int i, String str) {
        this.j = dVar;
        this.k = i;
        this.b = ox.a(dVar);
        this.m = str;
        if (i()) {
            Bundle l = l();
            IRemoteCreator a2 = com.huawei.openalliance.ad.e.a(this.f8139a.getApplicationContext());
            this.e = a2;
            if (a2 == null) {
                ho.c("PPSInterstitialViewContainer", "remoteCreator is null.");
                return;
            }
            a(a2.newInterstitialTemplateView(l));
        } else {
            this.d = j();
        }
        if (this.d != null) {
            if (i()) {
                a("0");
            }
            ho.b("PPSInterstitialViewContainer", "mInterstitialView is %s", this.d.getClass().getSimpleName());
            nc ncVar = new nc(this.f8139a, this.d);
            this.f = ncVar;
            ncVar.a(this.j);
            addView((View) this.d);
            a(1, (Bundle) null);
            return;
        }
        if (i()) {
            a("1");
        }
        ho.b("PPSInterstitialViewContainer", "mInterstitialView is null, finish activity.");
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.i;
        if (aVar != null) {
            aVar.a(ErrorCode.ERROR_CODE_NULL_AD_VIEW, ErrorCode.ERROR_CODE_NULL_AD_VIEW);
        }
        PPSInterstitialView.b bVar = this.h;
        if (bVar != null) {
            bVar.b();
        }
    }

    public void a(Bundle bundle) {
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.onBtnClick(bundle);
        }
    }

    public void a(int i, Bundle bundle) {
        int i2;
        if (this.i == null) {
            ho.b("PPSInterstitialViewContainer", "onStatusChange listener is null.");
        }
        ho.b("PPSInterstitialViewContainer", "status is: %s.", Integer.valueOf(i));
        switch (i) {
            case 1:
                this.i.a();
                break;
            case 2:
                this.i.b();
                break;
            case 3:
                this.i.c();
                break;
            case 4:
                this.i.d();
                break;
            case 5:
                int i3 = -1;
                if (bundle != null) {
                    gk gkVar = new gk(bundle);
                    int a2 = gkVar.a("error_code", -1);
                    i2 = gkVar.a("error_extra", -1);
                    i3 = a2;
                } else {
                    i2 = -1;
                }
                this.i.a(i3, i2);
                break;
            case 6:
            default:
                ho.b("PPSInterstitialViewContainer", "on status change, fall to default.");
                break;
            case 7:
                this.i.e();
                break;
            case 8:
                this.i.f();
                break;
        }
    }

    public void a() {
        this.i = null;
    }

    private Bundle l() {
        gk gkVar = new gk();
        gkVar.b("content", be.b(AdContentData.a(this.f8139a, this.b)));
        gkVar.b("sdkVersion", 30474310);
        boolean p = dd.p(this.f8139a);
        if (ho.a()) {
            ho.a("PPSInterstitialViewContainer", "emui9Dark %s", Boolean.valueOf(p));
        }
        gkVar.b(ParamConstants.Param.EMUI9_DARK_MODE, p);
        gkVar.a(ParamConstants.Param.CONTEXT, (IBinder) ObjectWrapper.wrap(this.f8139a));
        gkVar.b("isMute", this.j.S());
        gkVar.b("alertSwitch", this.j.ac());
        return gkVar.a();
    }

    private void k() {
        gk gkVar = new gk();
        int bS = fh.b(this.f8139a).bS();
        int bP = fh.b(this.f8139a).bP();
        if (ho.a()) {
            ho.a("PPSInterstitialViewContainer", "iteAdCa is %s, iteAdCloseTm is %s", Integer.valueOf(bS), Integer.valueOf(bP));
        }
        gkVar.b("ite_ad_ca", bS);
        gkVar.b("ite_ad_close_tm", bP);
        IInterstitialView iInterstitialView = this.d;
        if (iInterstitialView != null) {
            iInterstitialView.onCallBack(InterstitialMethods.SEND_INIT_INFO, gkVar.a());
        }
    }

    private IInterstitialView j() {
        int bQ = fh.b(this.f8139a).bQ();
        ho.b("PPSInterstitialViewContainer", "ite is %s", Integer.valueOf(bQ));
        PPSInterstitialView pPSInterstitialView = new PPSInterstitialView(this.f8139a);
        pPSInterstitialView.setFullScreen(bQ);
        pPSInterstitialView.setOnCloseListener(this.h);
        pPSInterstitialView.addInterstitialAdStatusListener(this.i);
        pPSInterstitialView.a(this.j, this.k, this.m);
        return pPSInterstitialView;
    }

    private boolean i() {
        ContentRecord contentRecord = this.b;
        return contentRecord != null && contentRecord.aO() == 3;
    }

    private void a(String str) {
        new com.huawei.openalliance.ad.analysis.c(this.f8139a).a(str, com.huawei.openalliance.ad.e.a(), this.b);
    }

    private void a(IObjectWrapper iObjectWrapper) {
        String str;
        if (iObjectWrapper == null) {
            str = "wrapper from uiengine is null.";
        } else {
            KeyEvent.Callback callback = (View) ObjectWrapper.unwrap(iObjectWrapper);
            if (callback != null) {
                if (!(callback instanceof IInterstitialView)) {
                    ho.b("PPSInterstitialViewContainer", "unwrap view from uiengine is %s", callback.getClass().getSimpleName());
                    return;
                }
                this.d = (IInterstitialView) callback;
                k();
                com.huawei.hms.ads.uiengine.f fVar = new com.huawei.hms.ads.uiengine.f(this.f8139a, this, this.b, this.j);
                this.c = fVar;
                this.d.setProxy(fVar);
                this.d.bindData(be.b(AdContentData.a(this.f8139a, this.b)));
                return;
            }
            str = "template view is null.";
        }
        ho.c("PPSInterstitialViewContainer", str);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100125_res_0x7f0601dd})) != null) {
            try {
                int integer = obtainStyledAttributes.getInteger(0, 0);
                this.l = integer;
                ho.b("PPSInterstitialViewContainer", "mFullScreen %d", Integer.valueOf(integer));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f8139a = context;
    }

    public r(Context context) {
        super(context);
        this.l = 0;
        this.o = new lo();
        this.p = false;
        a(context, (AttributeSet) null);
    }
}
