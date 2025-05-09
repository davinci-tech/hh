package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.AdCloseBtnClickListener;
import com.huawei.hms.ads.AdFeedbackListener;
import com.huawei.hms.ads.ChoicesView;
import com.huawei.hms.ads.DislikeAdListener;
import com.huawei.hms.ads.a;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.hms.ads.uiengine.common.IMediaStateApiTransfer;
import com.huawei.hms.ads.uiengine.common.MediaStateApi;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.activity.FeedbackActivity;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.bu;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.NativeSdkCallbackMethods;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.fm;
import com.huawei.openalliance.ad.hb;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.je;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.jm;
import com.huawei.openalliance.ad.jn;
import com.huawei.openalliance.ad.jo;
import com.huawei.openalliance.ad.jp;
import com.huawei.openalliance.ad.jr;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.mh;
import com.huawei.openalliance.ad.mi;
import com.huawei.openalliance.ad.mo;
import com.huawei.openalliance.ad.ne;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.sf;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton;
import com.huawei.openalliance.ad.views.interfaces.INativeVideoView;
import com.huawei.openalliance.ad.views.interfaces.INativeWindowImageView;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class PPSNativeView extends RelativeLayout implements je.a, lm, IPPSNativeView {
    private String A;
    private MaterialClickInfo B;
    private a.EnumC0073a C;
    private mi D;
    private IRemoteCreator E;
    private bu F;
    private View G;
    private jp H;
    private AtomicBoolean I;
    private ou J;
    private View.OnClickListener K;

    /* renamed from: a, reason: collision with root package name */
    protected lz f7904a;
    private ChoicesView b;
    private int c;
    private View d;
    private ImageView e;
    private com.huawei.hms.ads.a f;
    private boolean g;
    private boolean h;
    private ne i;
    private je j;
    private com.huawei.openalliance.ad.inter.data.e k;
    private OnNativeAdClickListener l;
    private OnNativeAdShowListener m;
    private OnNativeAdStatusChangedListener n;
    private b o;
    private INativeVideoView p;
    private INativeWindowImageView q;
    private IAppDownloadButton r;
    private List<View> s;
    private boolean t;
    private final String u;
    private boolean v;
    private DislikeAdListener w;
    private AdFeedbackListener x;
    private AdCloseBtnClickListener y;
    private String z;

    public interface OnNativeAdClickListener {
        void onClick(View view);
    }

    public interface OnNativeAdShowListener {
        void onAdShow();
    }

    public interface OnNativeAdStatusChangedListener {
        void onStatusChanged();
    }

    public interface b {
        void a();
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public void unregister(IAppDownloadButton iAppDownloadButton) {
        IAppDownloadButton iAppDownloadButton2;
        if (iAppDownloadButton == null || iAppDownloadButton != (iAppDownloadButton2 = this.r)) {
            return;
        }
        iAppDownloadButton2.setPpsNativeView(null);
        this.r.setNativeAd(null);
        this.r = null;
    }

    public void unregister() {
        q();
        hb.a(getContext()).b();
        if (!this.g) {
            a(this.d);
            this.d = null;
            this.b = null;
            a(this.f);
            this.f = null;
        }
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.b();
        }
    }

    public void showTransparencyDialog(View view, int[] iArr) {
        if (view == null) {
            ho.c("PPSNativeView", "anchorView is null");
        }
        try {
            com.huawei.openalliance.ad.inter.data.e eVar = this.k;
            if (eVar == null) {
                ho.c("PPSNativeView", "adInfo is null");
                return;
            }
            ContentRecord a2 = pd.a(eVar);
            if (a(iArr)) {
                bx.a(getContext(), view, iArr, a2);
            } else {
                bx.a(getContext(), view, a2);
            }
        } catch (Throwable th) {
            ho.c("PPSNativeView", "showTransparencyDialog has exception %s", th.getClass().getSimpleName());
        }
    }

    public void showTransparencyDialog(View view) {
        showTransparencyDialog(view, null);
    }

    public void showFeedback(View view) {
        a((Context) null, view);
    }

    public void showAdvertiserInfoDialog(View view, boolean z) {
        if (view == null) {
            ho.c("PPSNativeView", "anchorView is null");
        }
        try {
            com.huawei.openalliance.ad.inter.data.e eVar = this.k;
            if (eVar == null) {
                ho.c("PPSNativeView", "adInfo is null");
                return;
            }
            ContentRecord a2 = pd.a(eVar);
            if (bg.a(a2.aS())) {
                ho.c("PPSNativeView", "advertiser Info is null");
            } else {
                ComplianceActivity.a(getContext(), view, a2, z);
            }
        } catch (Throwable th) {
            ho.c("PPSNativeView", "showAdvertiserInfoDialog has exception %s", th.getClass().getSimpleName());
        }
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.i.a(videoInfo);
    }

    public void setVideoAutoPlayNet(int i) {
        fh.b(getContext().getApplicationContext()).l(i);
    }

    public void setOnNativeAdStatusChangedListener(OnNativeAdStatusChangedListener onNativeAdStatusChangedListener) {
        this.n = onNativeAdStatusChangedListener;
    }

    public void setOnNativeAdShowListener(OnNativeAdShowListener onNativeAdShowListener) {
        this.m = onNativeAdShowListener;
    }

    public void setOnNativeAdImpressionListener(b bVar) {
        this.o = bVar;
    }

    public void setOnNativeAdClickListener(OnNativeAdClickListener onNativeAdClickListener) {
        this.l = onNativeAdClickListener;
    }

    public void setMetaData(String str) {
        this.i.c(str);
    }

    public void setMaterialClickInfo(MaterialClickInfo materialClickInfo) {
        this.B = materialClickInfo;
    }

    public void setIsCustomDislikeThisAdEnabled(boolean z) {
        ho.a("PPSNativeView", "isCustomDislikeThisAdEnabled = " + z);
        if (this.g) {
            ho.c("PPSNativeView", "china rom should not call this method and isCustomDislikeThisAdEnabled = " + z);
            return;
        }
        this.v = z;
        if (z) {
            ho.a("PPSNativeView", "dont like default feedback!");
            return;
        }
        ho.a("PPSNativeView", "like default feedback!");
        ChoicesView choicesView = this.b;
        if (choicesView != null) {
            choicesView.a();
        }
    }

    public void setDislikeAdListener(DislikeAdListener dislikeAdListener) {
        if (this.g) {
            ho.c("PPSNativeView", "china rom should not call setChoiceViewPosition method");
        } else {
            this.w = dislikeAdListener;
        }
    }

    public void setChoiceViewPosition(int i) {
        ho.a("PPSNativeView", "setChoiceViewPosition option = %d", Integer.valueOf(i));
        if (this.k == null) {
            this.c = i;
        } else {
            a(i);
        }
    }

    public void setAdFeedbackListener(AdFeedbackListener adFeedbackListener) {
        this.x = adFeedbackListener;
    }

    public void setAdContainerSizeMatched(String str) {
        this.i.b(str);
    }

    public void setAdCloseBtnClickListener(AdCloseBtnClickListener adCloseBtnClickListener) {
        this.y = adCloseBtnClickListener;
    }

    public void resume() {
        try {
            com.huawei.hms.ads.uiengine.e b2 = com.huawei.openalliance.ad.e.b();
            View view = this.G;
            if (view == null || b2 == null) {
                return;
            }
            b2.b(ObjectWrapper.wrap(view), (Bundle) null);
        } catch (Throwable th) {
            ho.c("PPSNativeView", "resumeVideo err: %s", th.getClass().getSimpleName());
        }
    }

    public boolean register(IAppDownloadButton iAppDownloadButton) {
        boolean z;
        if (this.k == null) {
            throw new IllegalStateException("Register INativeAd first");
        }
        this.r = iAppDownloadButton;
        o();
        if (iAppDownloadButton != null) {
            iAppDownloadButton.setPpsNativeView(this);
            z = iAppDownloadButton.setNativeAd(this.k);
        } else {
            z = false;
        }
        if (ho.a()) {
            ho.a("PPSNativeView", "register downloadbutton, succ:%s", Boolean.valueOf(z));
        }
        return z;
    }

    public void register(INativeAd iNativeAd, List<View> list, INativeWindowImageView iNativeWindowImageView) {
        register(iNativeAd);
        this.q = iNativeWindowImageView;
        if (iNativeWindowImageView != null) {
            iNativeWindowImageView.setNativeAd(iNativeAd);
            setWindowImageViewClickable(this.q);
        }
        this.s = list;
        a(list);
    }

    public void register(INativeAd iNativeAd, List<View> list, INativeVideoView iNativeVideoView) {
        register(iNativeAd);
        this.p = iNativeVideoView;
        if (iNativeVideoView != null) {
            iNativeVideoView.setPpsNativeView(this);
            iNativeVideoView.setNativeAd(iNativeAd);
            setNativeVideoViewClickable(iNativeVideoView);
            jp jpVar = this.H;
            if (jpVar != null) {
                jpVar.a(iNativeVideoView.getVideoView());
            }
        }
        this.s = list;
        a(list);
    }

    public void register(INativeAd iNativeAd, List<View> list) {
        if (iNativeAd == null) {
            return;
        }
        this.k = (com.huawei.openalliance.ad.inter.data.e) iNativeAd;
        getMonitor();
        m();
        if (!l()) {
            this.z = iNativeAd.getAdChoiceUrl();
            this.A = iNativeAd.getAdChoiceIcon();
            i();
        }
        this.j.b(this.k.getMinEffectiveShowTime(), this.k.getMinEffectiveShowRatio());
        this.i.a(this.k);
        s();
        this.i.b();
        this.s = list;
        a(list);
        a(iNativeAd);
    }

    public void register(INativeAd iNativeAd, INativeWindowImageView iNativeWindowImageView) {
        register(iNativeAd);
        this.q = iNativeWindowImageView;
        if (iNativeWindowImageView != null) {
            iNativeWindowImageView.setNativeAd(iNativeAd);
            setWindowImageViewClickable(this.q);
        }
        u();
    }

    public void register(INativeAd iNativeAd, INativeVideoView iNativeVideoView) {
        register(iNativeAd);
        this.p = iNativeVideoView;
        if (iNativeVideoView != null) {
            iNativeVideoView.setPpsNativeView(this);
            iNativeVideoView.setNativeAd(iNativeAd);
            setNativeVideoViewClickable(iNativeVideoView);
            jp jpVar = this.H;
            if (jpVar != null) {
                jpVar.a(iNativeVideoView.getVideoView());
            }
        }
        u();
    }

    public void register(INativeAd iNativeAd) {
        if (iNativeAd == null) {
            return;
        }
        this.k = (com.huawei.openalliance.ad.inter.data.e) iNativeAd;
        getMonitor();
        m();
        if (!l()) {
            this.z = iNativeAd.getAdChoiceUrl();
            this.A = iNativeAd.getAdChoiceIcon();
            i();
        }
        this.j.b(this.k.getMinEffectiveShowTime(), this.k.getMinEffectiveShowRatio());
        this.i.a(this.k);
        s();
        this.i.b();
        u();
        a(iNativeAd);
    }

    public void pause() {
        try {
            com.huawei.hms.ads.uiengine.e b2 = com.huawei.openalliance.ad.e.b();
            View view = this.G;
            if (view == null || b2 == null) {
                return;
            }
            b2.a(ObjectWrapper.wrap(view), (Bundle) null);
        } catch (Throwable th) {
            ho.c("PPSNativeView", "pauseVideo err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        je jeVar = this.j;
        if (jeVar != null) {
            jeVar.j();
        }
    }

    public void onViewUpdate() {
        if (ho.a()) {
            ho.a("PPSNativeView", "manual updateView");
        }
        this.j.onGlobalLayout();
    }

    public void onFeedback(int i, List<FeedbackInfo> list) {
        ho.b("PPSNativeView", "onFeedback, type: %s", Integer.valueOf(i));
        if (1 == i || 3 == i) {
            this.i.b(list);
            v();
        } else if (i == 2) {
            this.i.c(list);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSNativeView", "onDetechedFromWindow");
        je jeVar = this.j;
        if (jeVar != null) {
            jeVar.i();
        }
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.b();
        }
    }

    public void onClose(List<String> list) {
        ho.b("PPSNativeView", "onClose with keyWords");
        this.i.a(list);
        v();
    }

    public void onClose() {
        ho.b("PPSNativeView", "onClose");
        onClose(null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        je jeVar = this.j;
        if (jeVar != null) {
            jeVar.h();
        }
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar != null) {
            a(eVar);
        }
        oq.a(getContext()).b(getContext());
    }

    public void hideTransparencyDialog() {
        hideFeedback();
    }

    public void hideAdvertiserInfoDialog() {
        hideFeedback();
    }

    public void gotoWhyThisAdPage() {
        if (this.k != null) {
            com.huawei.openalliance.ad.utils.d.a(getContext(), this.k);
        } else {
            ho.c("PPSNativeView", "gotoWhyThisAdPage nativeAd is null");
        }
    }

    public com.huawei.openalliance.ad.inter.data.e getNativeAd() {
        return this.k;
    }

    public View getFeedBackView() {
        try {
            com.huawei.hms.ads.uiengine.e b2 = com.huawei.openalliance.ad.e.b();
            View view = this.G;
            if (view == null || b2 == null) {
                return null;
            }
            return (View) ObjectWrapper.unwrap(b2.a(ObjectWrapper.wrap(view)));
        } catch (Throwable th) {
            ho.c("PPSNativeView", "get anchor view err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public MaterialClickInfo getClickInfo() {
        return this.B;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSNativeView
    public String getAdTag() {
        if (!r() || this.k.ab() == null) {
            return null;
        }
        return this.k.ab().a();
    }

    public mi getAdSessionAgent() {
        return this.D;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.f
    public IAd getAd() {
        return getNativeAd();
    }

    public void g() {
        AdCloseBtnClickListener adCloseBtnClickListener = this.y;
        if (adCloseBtnClickListener != null) {
            adCloseBtnClickListener.onCloseBtnClick();
        }
    }

    public void focusStop() {
        try {
            com.huawei.hms.ads.uiengine.e b2 = com.huawei.openalliance.ad.e.b();
            View view = this.G;
            if (view == null || b2 == null) {
                return;
            }
            b2.d(ObjectWrapper.wrap(view), null);
        } catch (Throwable th) {
            ho.c("PPSNativeView", "focusStop err: %s", th.getClass().getSimpleName());
        }
    }

    public void focusPlay() {
        try {
            com.huawei.hms.ads.uiengine.e b2 = com.huawei.openalliance.ad.e.b();
            View view = this.G;
            if (view == null || b2 == null) {
                return;
            }
            b2.c(ObjectWrapper.wrap(view), null);
        } catch (Throwable th) {
            ho.c("PPSNativeView", "focusPlay err: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSNativeView
    public void f() {
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.a(mo.CLICK);
        }
    }

    public void e() {
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                MaterialClickInfo a3 = th.a(this, motionEvent);
                this.B = a3;
                IAppDownloadButton iAppDownloadButton = this.r;
                if (iAppDownloadButton != null) {
                    ((AppDownloadButton) iAppDownloadButton).a(a3);
                }
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, this.B);
                IAppDownloadButton iAppDownloadButton2 = this.r;
                if (iAppDownloadButton2 != null) {
                    ((AppDownloadButton) iAppDownloadButton2).a(this.B);
                }
            }
        } catch (Throwable th) {
            ho.c("PPSNativeView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.f
    public boolean d() {
        je jeVar = this.j;
        if (jeVar != null) {
            return jeVar.n();
        }
        return false;
    }

    public boolean c() {
        if (this.v || this.f == null) {
            return false;
        }
        setWhyAdViewStatus(a.EnumC0073a.SHOWN);
        k();
        this.f.b();
        t();
        this.h = false;
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSNativeView
    public void c(View view) {
        OnNativeAdClickListener onNativeAdClickListener = this.l;
        if (onNativeAdClickListener != null) {
            onNativeAdClickListener.onClick(view);
        }
    }

    public void b(View view) {
        a(getContext(), view);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b(long j, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Object obj;
        dj.a(this.u);
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar == null) {
            return;
        }
        eVar.j(false);
        if (this.k.isVideoAd() && (obj = this.p) != null && (obj instanceof NativeMediaView)) {
            NativeMediaView nativeMediaView = (NativeMediaView) obj;
            i2 = nativeMediaView.getPlayedTime();
            i3 = nativeMediaView.getPlayedProgress();
        } else {
            i2 = -1;
            i3 = -1;
        }
        if (ho.a()) {
            ho.a("PPSNativeView", "onAdPhyShow  duration: %s  maxShowRatio: %s  playedTime: %s  playedProgress: %s", Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        }
        if (!this.k.isVideoAd()) {
            this.i.a(j, i);
            return;
        }
        if (i2 <= 0) {
            i5 = 0;
            i4 = 0;
        } else {
            i4 = i3;
            i5 = i2;
        }
        this.i.a(j, i, i5, i4);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b() {
        this.t = false;
        long c = ao.c();
        ho.a("PPSNativeView", "onViewPhysicalShowStart: %s", Long.valueOf(c));
        String valueOf = String.valueOf(c);
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar == null) {
            ho.b("PPSNativeView", "nativeAd is null! please register first");
            return;
        }
        eVar.h(valueOf);
        if (!this.k.isVideoAd() || this.k.q() == null || this.k.q().intValue() == 0) {
            this.k.h(false);
            this.k.a(false);
        }
        this.k.j(true);
        this.k.c(c);
        if (!this.k.S()) {
            this.k.g(true);
            if (this.n != null) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSNativeView.this.n != null) {
                            PPSNativeView.this.n.onStatusChanged();
                        }
                    }
                });
            }
        }
        this.i.a(valueOf);
        this.i.a(c);
        INativeVideoView iNativeVideoView = this.p;
        if (iNativeVideoView != null) {
            iNativeVideoView.updateContent(valueOf);
            this.p.updateStartShowTime(c);
        }
        IAppDownloadButton iAppDownloadButton = this.r;
        if (iAppDownloadButton != null) {
            iAppDownloadButton.updateContent(valueOf);
            this.r.updateStartShowTime(c);
        }
        bu buVar = this.F;
        if (buVar != null) {
            buVar.a(valueOf);
            this.F.a(c);
        }
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.f();
        }
        this.i.a();
        bu buVar2 = this.F;
        if (buVar2 != null) {
            buVar2.a(NativeSdkCallbackMethods.ATTACH_TO_WINDOW, (Bundle) null);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSNativeView
    public void a(Integer num) {
        a(Long.valueOf(System.currentTimeMillis() - this.j.d()), Integer.valueOf(this.j.c()), num);
    }

    public void a(IAd iAd) {
        lz lzVar;
        ho.b("PPSNativeView", "initOmsdkResource");
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.e) {
            com.huawei.openalliance.ad.inter.data.e eVar = (com.huawei.openalliance.ad.inter.data.e) iAd;
            ContentRecord a2 = pd.a(eVar);
            if (a(Integer.valueOf(a2.aO()), a2.D()) || (lzVar = this.f7904a) == null) {
                return;
            }
            lzVar.a(getContext(), a2, this, true);
            this.f7904a.a(false);
            this.f7904a.c();
            mi a3 = this.f7904a.a();
            this.D = a3;
            if (a3 != null) {
                a3.a(this.b, mh.OTHER, null);
                this.D.a(this.f, mh.OTHER, null);
                this.D.a(this.d, mh.OTHER, null);
            }
            a(this.f7904a, eVar);
        }
    }

    public void a(View view, int i, boolean z) {
        com.huawei.openalliance.ad.inter.data.e eVar;
        if (this.h) {
            this.h = false;
            hb.a(getContext()).a();
            ho.b("PPSNativeView", "onClick");
            a((Integer) 1);
            com.huawei.openalliance.ad.utils.ad.b();
            if (this.i.a(this.B, Integer.valueOf(i), getAdTag(), z, getBtnText())) {
                lz lzVar = this.f7904a;
                if (lzVar != null) {
                    lzVar.a(mo.CLICK);
                }
                jp jpVar = this.H;
                if (jpVar != null) {
                    jpVar.a();
                }
            } else {
                IAppDownloadButton iAppDownloadButton = this.r;
                if (iAppDownloadButton != null && (iAppDownloadButton instanceof AppDownloadButton)) {
                    if (AppStatus.DOWNLOAD == ((AppDownloadButton) iAppDownloadButton).getStatus() && (eVar = this.k) != null && eVar.isAutoDownloadApp() && os.h(this.k.getCtrlSwitchs())) {
                        ho.b("PPSNativeView", "download app directly");
                        ((AppDownloadButton) this.r).performClick();
                    }
                }
            }
            this.B = null;
            OnNativeAdClickListener onNativeAdClickListener = this.l;
            if (onNativeAdClickListener != null) {
                onNativeAdClickListener.onClick(view);
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.7
                @Override // java.lang.Runnable
                public void run() {
                    PPSNativeView.this.h = true;
                }
            }, 500L);
        }
    }

    public void a(View view, int i) {
        a(view, i, true);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a(long j, int i) {
        dj.a(this.u);
        if (!this.j.a(j) || this.t) {
            return;
        }
        this.t = true;
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar != null) {
            if (!eVar.isVideoAd() || this.k.q() == null || this.k.q().intValue() == 0) {
                a(Long.valueOf(j), Integer.valueOf(i), (Integer) null);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a() {
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.4
                @Override // java.lang.Runnable
                public void run() {
                    com.huawei.openalliance.ad.inter.data.e eVar2 = PPSNativeView.this.k;
                    if (eVar2 != null) {
                        if (!eVar2.isVideoAd() || eVar2.q() == null || eVar2.q().intValue() == 0) {
                            PPSNativeView.this.a(Long.valueOf(eVar2.getMinEffectiveShowTime()), Integer.valueOf(PPSNativeView.this.j.c()), (Integer) null);
                        }
                    }
                }
            }, this.u, eVar.getMinEffectiveShowTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        w();
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.j();
            this.f7904a.b();
        }
        INativeVideoView iNativeVideoView = this.p;
        if (iNativeVideoView != null) {
            iNativeVideoView.stop();
        }
        DislikeAdListener dislikeAdListener = this.w;
        if (dislikeAdListener != null) {
            dislikeAdListener.onAdDisliked();
        }
        q();
    }

    private void w() {
        if (this.r != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.8
                @Override // java.lang.Runnable
                public void run() {
                    PPSNativeView.this.r.cancel();
                }
            });
        }
    }

    private void v() {
        w();
        a((Integer) 3);
        lz lzVar = this.f7904a;
        if (lzVar != null) {
            lzVar.j();
            this.f7904a.b();
        }
        INativeVideoView iNativeVideoView = this.p;
        if (iNativeVideoView != null) {
            iNativeVideoView.stop();
        }
        DislikeAdListener dislikeAdListener = this.w;
        if (dislikeAdListener != null) {
            dislikeAdListener.onAdDisliked();
        }
        jp jpVar = this.H;
        if (jpVar != null) {
            jpVar.b();
        }
        q();
    }

    private void u() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        this.s = arrayList;
        a(arrayList);
    }

    private void t() {
        List<View> list = this.s;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : this.s) {
            if (view != null) {
                view.setOnClickListener(null);
            }
        }
        setOnClickListener(null);
    }

    private void setWindowImageViewClickable(INativeWindowImageView iNativeWindowImageView) {
        if (iNativeWindowImageView instanceof NativeWindowImageView) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((NativeWindowImageView) iNativeWindowImageView);
            a(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWhyAdViewStatus(a.EnumC0073a enumC0073a) {
        this.C = enumC0073a;
    }

    private void setViewBackgroundColor(int i) {
        setBackgroundColor(i);
    }

    private void setNativeVideoViewClickable(INativeVideoView iNativeVideoView) {
        if (iNativeVideoView instanceof NativeVideoView) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((NativeVideoView) iNativeVideoView);
            a(arrayList);
        }
    }

    private void s() {
        com.huawei.openalliance.ad.inter.data.e eVar;
        if (!d() || (eVar = this.k) == null || eVar.ag()) {
            return;
        }
        ho.b("PPSNativeView", " maybe report show start.");
        b();
    }

    private boolean r() {
        com.huawei.openalliance.ad.inter.data.e eVar;
        return (this.G == null || (eVar = this.k) == null || (eVar.aa() != null && 3 == this.k.aa().intValue())) ? false : true;
    }

    private void q() {
        this.j.b();
        hb.a(getContext()).b();
        INativeVideoView iNativeVideoView = this.p;
        if (iNativeVideoView != null) {
            iNativeVideoView.setPpsNativeView(null);
        }
        this.p = null;
        this.q = null;
        this.w = null;
        this.x = null;
        t();
        p();
    }

    private void p() {
        View view;
        IRemoteCreator iRemoteCreator = this.E;
        if (iRemoteCreator != null && (view = this.G) != null) {
            try {
                iRemoteCreator.destroyView(ObjectWrapper.wrap(view));
            } catch (Throwable th) {
                ho.b("PPSNativeView", "destory remote view err: %s", th.getClass().getSimpleName());
            }
        }
        if (this.k != null) {
            db.a().b(this.k.getUniqueId());
        }
        this.E = null;
        this.G = null;
        bu buVar = this.F;
        if (buVar != null) {
            buVar.f();
            this.F = null;
        }
    }

    private void o() {
        jp jpVar = this.H;
        if (jpVar != null) {
            IAppDownloadButton iAppDownloadButton = this.r;
            if (iAppDownloadButton instanceof AppDownloadButton) {
                ((AppDownloadButton) iAppDownloadButton).setMonitorEp(jpVar instanceof jo ? null : this.J);
                ((AppDownloadButton) this.r).setCustMonitor(this.H);
                return;
            }
        }
        ho.b("PPSNativeView", "setDownBtnMonitorEp, not register monitor or appDownloadButton invalid");
    }

    private boolean n() {
        return getWhyAdViewStatus() != a.EnumC0073a.NONE && getWhyAdViewStatus() == a.EnumC0073a.INIT;
    }

    private void m() {
        a(this.c);
        d(this.b);
        if (this.g || !n()) {
            return;
        }
        setWhyAdViewStatus(a.EnumC0073a.NONE);
        this.h = true;
        b(this, 0);
    }

    private boolean l() {
        if (!a(this.k.aa(), this.k.getCreativeType())) {
            return false;
        }
        Context applicationContext = getContext().getApplicationContext();
        a(applicationContext, AdContentData.a(applicationContext, this.k));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        com.huawei.hms.ads.a aVar = this.f;
        if (aVar != null) {
            ViewGroup viewGroup = (ViewGroup) aVar.getParent();
            if (viewGroup != null && (viewGroup instanceof ViewGroup)) {
                b(viewGroup, 4);
            }
            this.f.setVisibility(0);
            setViewBackgroundColor(getResources().getColor(R.color._2131298015_res_0x7f0906df));
        }
    }

    private void j() {
        if (this.f == null || getWhyAdViewStatus() != a.EnumC0073a.INIT) {
            com.huawei.hms.ads.a aVar = this.f;
            if (aVar != null) {
                a(aVar);
                this.f = null;
            }
            setWhyAdViewStatus(a.EnumC0073a.INIT);
            com.huawei.hms.ads.a aVar2 = new com.huawei.hms.ads.a(getContext(), this);
            this.f = aVar2;
            addView(aVar2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f.getLayoutParams());
            layoutParams.addRule(13);
            this.f.setLayoutParams(layoutParams);
        }
        this.f.setOnCloseCallBack(new com.huawei.hms.ads.b() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.3
            @Override // com.huawei.hms.ads.b
            public List<String> c() {
                if (PPSNativeView.this.k != null) {
                    return PPSNativeView.this.k.getAdCloseKeyWords();
                }
                ho.c("PPSNativeView", "getKeyWords nativaAd is null");
                return null;
            }

            @Override // com.huawei.hms.ads.b
            public void b() {
                if (PPSNativeView.this.k != null) {
                    com.huawei.openalliance.ad.utils.d.a(PPSNativeView.this.getContext(), PPSNativeView.this.k);
                } else {
                    ho.c("PPSNativeView", "processWhyThisAdEvent nativaAd is null");
                }
            }

            @Override // com.huawei.hms.ads.b
            public void a(String str) {
                PPSNativeView.this.k();
                ArrayList arrayList = new ArrayList();
                if (str == null || str.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList.add(str);
                }
                PPSNativeView.this.setWhyAdViewStatus(a.EnumC0073a.DISLIKED);
                PPSNativeView.this.onClose(arrayList);
            }

            @Override // com.huawei.hms.ads.b
            public void a() {
                PPSNativeView.this.k();
            }
        });
    }

    private void i() {
        ho.a("PPSNativeView", "update choiceView start.");
        if (this.b == null) {
            ho.a("PPSNativeView", "do not need update choiceView");
            return;
        }
        if (this.f == null) {
            j();
        }
        if (!this.v && this.f != null) {
            ho.a("PPSNativeView", "cusWhyView is not null, set choiceView as close.");
            this.b.a();
        } else {
            if (TextUtils.isEmpty(this.z)) {
                return;
            }
            ho.a("PPSNativeView", "update choiceView.");
            if (TextUtils.isEmpty(this.A)) {
                this.b.b();
            } else {
                this.b.setAdChoiceIcon(this.A);
            }
        }
    }

    public static void hideFeedback() {
        ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, new Intent("com.huawei.ads.feedback.action.FINISH_FEEDBACK_ACTIVITY"));
    }

    public static void hideComplain() {
        ji.a().a(NotifyMessageNames.AD_COMPLAIN_MESSAGE_NAME, new Intent(NotifyMessageNames.AD_COMPLAIN_ACTION));
    }

    private void h() {
        ho.a("PPSNativeView", "initChoicesView start");
        if (this.b == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hiad_choices_wrapper, (ViewGroup) null);
            this.d = inflate;
            this.b = (ChoicesView) inflate.findViewById(R.id.hiad_choices_icon);
            this.e = (ImageView) this.d.findViewById(R.id.compliance_icon);
            addView(this.d);
            View view = this.d;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        setChoiceViewPosition(1);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!PPSNativeView.this.c()) {
                    if (PPSNativeView.this.k == null) {
                        ho.b("PPSNativeView", "adInfo is null");
                    } else if (bg.a(PPSNativeView.this.k.getCompliance())) {
                        com.huawei.openalliance.ad.utils.d.a(PPSNativeView.this.getContext(), PPSNativeView.this.k);
                    } else {
                        ComplianceActivity.a(PPSNativeView.this.getContext(), view2, pd.a(PPSNativeView.this.k), true);
                    }
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (PPSNativeView.this.v || PPSNativeView.this.k == null) {
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    ComplianceActivity.a(PPSNativeView.this.getContext(), view2, pd.a(PPSNativeView.this.k), false);
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
    }

    private a.EnumC0073a getWhyAdViewStatus() {
        return this.C;
    }

    private void getMonitor() {
        if (this.I.get() || this.k == null) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.I.get());
            objArr[1] = this.k == null ? Constants.NULL : "not null";
            ho.b("PPSNativeView", "hasRegister %s, nativeAd %s", objArr);
            return;
        }
        ho.b("PPSNativeView", "getMonitor");
        this.I.set(true);
        ContentRecord a2 = pd.a(this.k);
        this.J.a(a2.a());
        this.J.a(a2);
        jp a3 = jn.a(getContext(), true ^ this.k.isVideoAd(), this.J, a2, a(this.k.aa(), this.k.getCreativeType()));
        this.H = a3;
        a3.a(this);
        jn.a(a2.m(), this.H);
        ou ouVar = this.J;
        if (this.H instanceof jo) {
            ouVar = null;
        }
        IAppDownloadButton iAppDownloadButton = this.r;
        if (iAppDownloadButton instanceof AppDownloadButton) {
            ((AppDownloadButton) iAppDownloadButton).setMonitorEp(ouVar);
            ((AppDownloadButton) this.r).setCustMonitor(this.H);
        }
        ne neVar = this.i;
        if (neVar != null) {
            neVar.a(ouVar);
        }
    }

    private MediaStateApi getMediaProxy() {
        if (!(this.H instanceof jr)) {
            return null;
        }
        ho.a("PPSNativeView", "custMonitor is VideoMzMonitor");
        return new jm((jr) this.H);
    }

    private HashMap<String, String> getBtnText() {
        AppInfo appInfo;
        IAppDownloadButton iAppDownloadButton = this.r;
        if (!(iAppDownloadButton instanceof AppDownloadButton) || (appInfo = ((AppDownloadButton) iAppDownloadButton).getAppInfo()) == null) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(com.huawei.openalliance.ad.constant.Constants.BF_DOWNLOAD_TXT, appInfo.i());
        hashMap.put(com.huawei.openalliance.ad.constant.Constants.AF_DOWNLOAD_TXT, appInfo.j());
        return hashMap;
    }

    private void d(View view) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        view.bringToFront();
    }

    private void b(View view, int i) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                viewGroup.getChildAt(i2).setVisibility(i);
            }
        }
    }

    private boolean a(int[] iArr) {
        return iArr != null && iArr.length == 2;
    }

    private boolean a(Integer num, int i) {
        return (num != null && 3 == num.intValue()) || 99 == i;
    }

    private void a(List<View> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : list) {
            if (view instanceof NativeVideoView) {
                ((NativeVideoView) view).setCoverClickListener(this.K);
            } else if (view != null) {
                view.setOnClickListener(this.K);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Long l, Integer num, Integer num2) {
        b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        com.huawei.openalliance.ad.inter.data.e eVar = this.k;
        if (eVar == null) {
            return;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(eVar.d(), num2);
        if (!this.k.V() || (a2 && !this.k.b())) {
            ho.b("PPSNativeView", "reportAdShowEvent, customExposureType： real onAdShow");
            this.i.a(l, num, num2, getAdTag());
            if (a2) {
                this.k.a(true);
            }
            if (this.k.V()) {
                return;
            }
            this.k.h(true);
            lz lzVar = this.f7904a;
            if (lzVar != null) {
                lzVar.e();
            }
            OnNativeAdShowListener onNativeAdShowListener = this.m;
            if (onNativeAdShowListener != null) {
                onNativeAdShowListener.onAdShow();
            }
        }
    }

    private void a(lz lzVar, com.huawei.openalliance.ad.inter.data.e eVar) {
        INativeVideoView iNativeVideoView = this.p;
        if (iNativeVideoView instanceof NativeVideoView) {
            ((NativeVideoView) iNativeVideoView).a(lzVar, eVar);
        }
    }

    public static void a(View view) {
        ViewGroup viewGroup;
        if (view == null || (viewGroup = (ViewGroup) view.getParent()) == null || !(viewGroup instanceof ViewGroup)) {
            return;
        }
        viewGroup.removeView(view);
    }

    private void a(Context context, AdContentData adContentData) {
        ho.b("PPSNativeView", "showV3Ad");
        IRemoteCreator a2 = com.huawei.openalliance.ad.e.a(getContext().getApplicationContext());
        this.E = a2;
        if (a2 == null) {
            ho.b("PPSNativeView", "Creator is null");
            return;
        }
        this.F = new bu(context, this, this.k);
        String b2 = be.b(adContentData);
        ho.a("PPSNativeView", "showV3Ad contentJson: %s", b2);
        Bundle bundle = new Bundle();
        bundle.putBinder(ParamConstants.Param.CONTEXT, (IBinder) ObjectWrapper.wrap(getContext()));
        bundle.putString("content", b2);
        bundle.putInt("sdkVersion", 30474310);
        boolean p = dd.p(getContext().getApplicationContext());
        if (ho.a()) {
            ho.a("PPSNativeView", "emui9Dark %s", Boolean.valueOf(p));
        }
        bundle.putBoolean(ParamConstants.Param.EMUI9_DARK_MODE, p);
        bundle.putBoolean(ParamConstants.Param.SHOW_V2_TPT, com.huawei.openalliance.ad.utils.c.a(getContext(), this.k.ab(), this.k.getSlotId(), this.k.e()));
        try {
            View view = (View) ObjectWrapper.unwrap(this.E.newNativeTemplateView(bundle, this.F));
            this.G = view;
            if (view == null) {
                ho.c("PPSNativeView", "templateView is null");
                return;
            }
            this.f7904a = null;
            removeAllViews();
            addView(this.G);
            this.E.bindData(ObjectWrapper.wrap(this.G), b2);
            KeyEvent.Callback callback = this.G;
            if (callback instanceof IMediaStateApiTransfer) {
                ((IMediaStateApiTransfer) callback).setProxy(getMediaProxy());
            } else {
                ho.c("PPSNativeView", "Native template view is not a IMediaStateApiTransfer instance");
            }
            ho.b("PPSNativeView", "binddata end, slotid: %s", this.k.getSlotId());
            if (r()) {
                fm.a(getContext().getApplicationContext()).a(this.k.getSlotId(), System.currentTimeMillis());
            }
        } catch (Throwable th) {
            ho.c("PPSNativeView", "create newNativeTemplateView err: %s", th.getClass().getSimpleName());
        }
    }

    private void a(Context context, View view) {
        com.huawei.openalliance.ad.views.feedback.c cVar = new com.huawei.openalliance.ad.views.feedback.c();
        cVar.a(context);
        cVar.a(view);
        cVar.b(this.x);
        cVar.a(new a(this));
        dc.a(this.k);
        FeedbackActivity.a(getContext(), cVar);
    }

    private void a(Context context) {
        this.i = new ne(context, this);
        this.j = new je(this, this);
        this.g = bz.a(context).d();
        this.J = new ou(context, new sf(context, 3));
        ho.a("PPSNativeView", "isChinaRom = %s", Boolean.valueOf(this.g));
        if (this.g) {
            return;
        }
        h();
    }

    private void a(int i) {
        com.huawei.openalliance.ad.inter.data.e eVar;
        ho.a("PPSNativeView", "changeChoiceViewPosition option = %d", Integer.valueOf(i));
        if (this.g) {
            ho.c("PPSNativeView", "china rom should not call setChoiceViewPosition method");
            return;
        }
        if (this.d == null) {
            ho.a("PPSNativeView", "choicesView is null, error");
            return;
        }
        if (!this.v && this.e != null && (eVar = this.k) != null && !bg.a(eVar.getCompliance())) {
            this.e.setVisibility(0);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.d.getLayoutParams());
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363250_res_0x7f0a05b2);
        if (i != 0) {
            if (i == 2) {
                layoutParams.addRule(12);
            } else if (i == 3) {
                layoutParams.addRule(12);
            } else {
                if (i == 4) {
                    if (this.v) {
                        ho.a("PPSNativeView", "ADCHOICES_INVISIBLE is called and not default feedback!");
                        b(this.d, 8);
                        return;
                    }
                    this.d.setVisibility(0);
                    this.d.setLayoutParams(layoutParams);
                    this.d.bringToFront();
                }
                layoutParams.addRule(10);
            }
            layoutParams.addRule(21);
            layoutParams.setMargins(0, 0, dimensionPixelOffset, 0);
            layoutParams.setMarginEnd(dimensionPixelOffset);
            this.d.setVisibility(0);
            this.d.setLayoutParams(layoutParams);
            this.d.bringToFront();
        }
        layoutParams.addRule(10);
        layoutParams.addRule(20);
        layoutParams.setMargins(dimensionPixelOffset, 0, 0, 0);
        layoutParams.setMarginStart(dimensionPixelOffset);
        this.d.setScaleX(-1.0f);
        this.b.setScaleX(-1.0f);
        this.d.setVisibility(0);
        this.d.setLayoutParams(layoutParams);
        this.d.bringToFront();
    }

    public PPSNativeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7904a = new lo();
        this.g = true;
        this.h = true;
        this.t = false;
        this.u = com.huawei.openalliance.ad.constant.Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.C = a.EnumC0073a.NONE;
        this.I = new AtomicBoolean();
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSNativeView.this.a(view, 7);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }

    static class a implements AdFeedbackListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<PPSNativeView> f7913a;

        @Override // com.huawei.hms.ads.AdFeedbackListener
        public void onAdFeedbackShowFailed() {
        }

        @Override // com.huawei.hms.ads.AdFeedbackListener
        public void onAdLiked() {
        }

        @Override // com.huawei.hms.ads.AdFeedbackListener
        public void onAdDisliked() {
            PPSNativeView pPSNativeView = this.f7913a.get();
            if (pPSNativeView != null) {
                pPSNativeView.x();
            }
        }

        public a(PPSNativeView pPSNativeView) {
            this.f7913a = new WeakReference<>(pPSNativeView);
        }
    }

    public PPSNativeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7904a = new lo();
        this.g = true;
        this.h = true;
        this.t = false;
        this.u = com.huawei.openalliance.ad.constant.Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.C = a.EnumC0073a.NONE;
        this.I = new AtomicBoolean();
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSNativeView.this.a(view, 7);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }

    public PPSNativeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7904a = new lo();
        this.g = true;
        this.h = true;
        this.t = false;
        this.u = com.huawei.openalliance.ad.constant.Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.C = a.EnumC0073a.NONE;
        this.I = new AtomicBoolean();
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSNativeView.this.a(view, 7);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }

    public PPSNativeView(Context context) {
        super(context);
        this.f7904a = new lo();
        this.g = true;
        this.h = true;
        this.t = false;
        this.u = com.huawei.openalliance.ad.constant.Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.C = a.EnumC0073a.NONE;
        this.I = new AtomicBoolean();
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSNativeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSNativeView.this.a(view, 7);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        a(context);
    }
}
