package com.huawei.openalliance.ad.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hms.ads.uiengine.common.IProgressButton;
import com.huawei.openalliance.ad.Cdo;
import com.huawei.openalliance.ad.activity.LandingDetailsActivity;
import com.huawei.openalliance.ad.activity.PPSInstallAuthorActivity;
import com.huawei.openalliance.ad.activity.PPSInterstitialAdActivity;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.TextState;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.download.app.AppStatusV1;
import com.huawei.openalliance.ad.download.app.h;
import com.huawei.openalliance.ad.dp;
import com.huawei.openalliance.ad.dq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.jp;
import com.huawei.openalliance.ad.my;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.ow;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.pi;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.sn;
import com.huawei.openalliance.ad.sp;
import com.huawei.openalliance.ad.sr;
import com.huawei.openalliance.ad.ss;
import com.huawei.openalliance.ad.su;
import com.huawei.openalliance.ad.sw;
import com.huawei.openalliance.ad.sz;
import com.huawei.openalliance.ad.ta;
import com.huawei.openalliance.ad.tf;
import com.huawei.openalliance.ad.utils.am;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bb;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.AppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton;
import com.huawei.openalliance.ad.views.interfaces.IPPSLinkedView;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;
import com.huawei.openalliance.ad.views.interfaces.IPPSPlacementView;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class AppDownloadButton extends com.huawei.openalliance.ad.views.a implements com.huawei.openalliance.ad.download.l, IAppDownloadButton {
    private d A;
    private boolean B;
    private boolean C;
    private boolean D;
    private String E;
    private boolean F;
    private int G;
    private f H;
    private AppDownloadListenerV1 I;
    private MaterialClickInfo J;
    private boolean K;
    private b L;
    private boolean M;
    private ou N;
    private jp O;
    private com.huawei.openalliance.ad.inter.data.e d;
    private AppInfo e;
    private boolean f;
    private OnDownloadStatusChangedListener g;
    private OnNonWifiDownloadListener h;
    private e i;
    private AppStatus j;
    private AppStatus k;
    private int l;
    private ContentRecord m;
    private boolean n;
    private int o;
    private Integer p;
    private boolean q;
    private int r;
    private List<TextState> s;
    private IPPSNativeView t;
    private IPPSLinkedView u;
    private IPPSPlacementView v;
    private PPSAppDetailView w;
    private boolean x;
    private com.huawei.openalliance.ad.views.interfaces.x y;
    private View.OnClickListener z;

    /* loaded from: classes9.dex */
    public interface ButtonTextWatcher {
        CharSequence beforeTextChanged(CharSequence charSequence, AppStatus appStatus);
    }

    public interface OnDownloadStatusChangedListener {
        void onStatusChanged(AppStatus appStatus);

        void onUserCancel(AppInfo appInfo);
    }

    public interface OnNonWifiDownloadListener {
        boolean onNonWifiDownload(AppInfo appInfo, long j);
    }

    public interface OnResolutionRequiredListener {
        void onResolutionRequired(AppInfo appInfo, Bundle bundle);
    }

    public interface OnStatusRefreshedListener {
        void onRefreshed(AppStatus appStatus);
    }

    public interface b {
        void a();
    }

    public interface e {
        CharSequence a(CharSequence charSequence, AppStatus appStatus);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public int getCancelBtnHeight(int i) {
        return i;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public View getProgressBtn() {
        return this;
    }

    @Deprecated
    public void setButtonTextWatcher(ButtonTextWatcher buttonTextWatcher) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setCancelBtnHeight(int i) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setResetListener(IProgressButton.ProgressButtonResetListener progressButtonResetListener) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setShowCancelBtn(boolean z) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextInner(CharSequence charSequence, boolean z) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void updateStartShowTime(long j) {
        ContentRecord contentRecord = this.m;
        if (contentRecord != null) {
            contentRecord.f(j);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void updateContent(String str) {
        ContentRecord contentRecord = this.m;
        if (contentRecord != null) {
            contentRecord.c(str);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        super.setVisibilityInner(i);
    }

    public void setVenusExt(String str) {
        this.E = str;
    }

    public void setSource(int i) {
        this.o = i;
        ho.b(this.f8027a, "setSource: %s", Integer.valueOf(i));
    }

    public void setShowPermissionDialog(boolean z) {
        this.n = z;
    }

    @Override // com.huawei.openalliance.ad.views.a, com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setResetWidth(boolean z) {
        super.setResetWidth(z);
    }

    public void setRemoteBfDlBtnTxt(String str) {
        AppInfo appInfo = this.e;
        if (appInfo != null) {
            appInfo.v(str);
        }
    }

    public void setRemoteAfDlBtnText(String str) {
        AppInfo appInfo = this.e;
        if (appInfo != null) {
            appInfo.w(str);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setPpsPlacementView(IPPSPlacementView iPPSPlacementView) {
        this.v = iPPSPlacementView;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setPpsNativeView(IPPSNativeView iPPSNativeView) {
        this.t = iPPSNativeView;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setPpsLinkedView(IPPSLinkedView iPPSLinkedView) {
        this.u = iPPSLinkedView;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public boolean setPlacementAd(IPlacementAd iPlacementAd) {
        if (iPlacementAd == null) {
            return false;
        }
        try {
            this.o = 1;
            if (iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g) {
                this.m = pi.a((com.huawei.openalliance.ad.inter.data.g) iPlacementAd);
            }
            if (this.m != null) {
                AppInfo appInfo = iPlacementAd.getAppInfo();
                if (appInfo != null) {
                    setShowPermissionDialog(appInfo.isPermPromptForCard());
                }
                setAppInfo(appInfo);
                MetaData h = this.m.h();
                if (h != null) {
                    this.s = h.v();
                }
                this.B = os.m(this.m.V());
                return true;
            }
        } catch (Throwable th) {
            ho.c(this.f8027a, "register placementAd ex: %s", th.getClass().getSimpleName());
        }
        return false;
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.b(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.a(i, i2, i3, i4);
    }

    public void setOnceSource(int i) {
        this.q = false;
        this.p = Integer.valueOf(this.o);
        this.o = i;
    }

    public void setOnResolutionRequiredListener(OnResolutionRequiredListener onResolutionRequiredListener) {
        this.H = new f(onResolutionRequiredListener);
    }

    public void setOnNonWifiDownloadListener(OnNonWifiDownloadListener onNonWifiDownloadListener) {
        this.h = onNonWifiDownloadListener;
    }

    public void setOnDownloadStatusChangedListener(OnDownloadStatusChangedListener onDownloadStatusChangedListener) {
        this.g = onDownloadStatusChangedListener;
    }

    public void setNeedShowPermision(boolean z) {
        this.x = z;
    }

    public void setNeedShowConfirmDialog(boolean z) {
        this.C = z;
    }

    public void setNeedAppendProgress(boolean z) {
        this.K = z;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public boolean setNativeAd(INativeAd iNativeAd) {
        String str;
        StringBuilder sb;
        if (iNativeAd == null) {
            setAppInfo(null);
            this.m = null;
            this.d = null;
            return false;
        }
        if (iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e) {
            this.d = (com.huawei.openalliance.ad.inter.data.e) iNativeAd;
        }
        try {
            this.o = 1;
            this.m = pd.a(this.d);
            AppInfo appInfo = iNativeAd.getAppInfo();
            setAppInfo(appInfo);
            com.huawei.openalliance.ad.inter.data.e eVar = this.d;
            if (eVar != null) {
                MetaData metaData = (MetaData) be.b(eVar.O(), MetaData.class, new Class[0]);
                if (metaData != null) {
                    this.s = metaData.v();
                }
                this.B = os.m(this.d.getCtrlSwitchs());
            }
            if (appInfo != null) {
                setShowPermissionDialog(appInfo.isPermPromptForCard());
                return true;
            }
        } catch (RuntimeException e2) {
            e = e2;
            str = this.f8027a;
            sb = new StringBuilder("setNativeAd RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.d(str, sb.toString());
            return false;
        } catch (Exception e3) {
            e = e3;
            str = this.f8027a;
            sb = new StringBuilder("setNativeAd Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d(str, sb.toString());
            return false;
        }
        return false;
    }

    public void setMonitorEp(ou ouVar) {
        Object[] objArr = new Object[1];
        objArr[0] = ouVar == null ? Constants.NULL : "not null";
        ho.a("AppDownloadButton", "eventprocessor is %s", objArr);
        this.N = ouVar;
    }

    public void setLinkedCoverClickListener(View.OnClickListener onClickListener) {
        this.z = onClickListener;
    }

    public void setDlBtnText(String str) {
        if (this.e == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.e.t(str);
    }

    public void setCustMonitor(jp jpVar) {
        this.O = jpVar;
    }

    public void setClickActionListener(com.huawei.openalliance.ad.views.interfaces.x xVar) {
        this.y = xVar;
    }

    public void setButtonTextWatcherInner(e eVar) {
        this.i = eVar;
    }

    public void setBfDlBtnTxt(String str) {
        AppInfo appInfo = this.e;
        if (appInfo != null) {
            appInfo.t(str);
        }
    }

    public void setAppInfo(AppInfo appInfo) {
        ho.b(this.f8027a, "setAppInfo appInfo is " + cz.b(appInfo));
        this.e = appInfo;
        if (appInfo != null) {
            com.huawei.openalliance.ad.download.app.e.h().a(appInfo, this.A);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setAppDownloadButtonStyle(AppDownloadButtonStyle appDownloadButtonStyle) {
        this.b = appDownloadButtonStyle;
        refreshStatusAsync(null);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setAppDetailView(PPSAppDetailView pPSAppDetailView) {
        this.w = pPSAppDetailView;
    }

    public void setAllowedNonWifiNetwork(boolean z) {
        this.f = z;
    }

    public void setAfDlBtnText(String str) {
        if (this.e == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.e.u(str);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void setAdLandingPageData(AdLandingPageData adLandingPageData) {
        String str;
        String str2;
        try {
            if (adLandingPageData == null) {
                setAppInfo(null);
                this.m = null;
                return;
            }
            this.m = adLandingPageData.x();
            AppInfo appInfo = adLandingPageData.getAppInfo();
            if (appInfo != null) {
                setAppInfo(appInfo);
                setShowPermissionDialog(appInfo.isPermPromptForLanding());
            }
            updateContent(adLandingPageData.c());
            updateStartShowTime(adLandingPageData.D());
            this.o = 2;
            this.s = adLandingPageData.g();
            this.B = os.m(this.m.V());
        } catch (IllegalArgumentException unused) {
            str = this.f8027a;
            str2 = "setAdLandingPageData IllegalArgumentException";
            ho.c(str, str2);
        } catch (Exception unused2) {
            str = this.f8027a;
            str2 = "setAdLandingPageData error";
            ho.c(str, str2);
        }
    }

    public void removeResolutionRequiredListener() {
        com.huawei.openalliance.ad.download.app.e.h().b(this.e, this.H);
        this.H = null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void refreshStatusAsync(OnStatusRefreshedListener onStatusRefreshedListener) {
        c((AppDownloadTask) null);
        com.huawei.openalliance.ad.utils.m.f(new g(this, onStatusRefreshedListener));
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public AppStatus refreshStatus() {
        if (ho.a()) {
            ho.a(this.f8027a, "refreshStatus starts:%d", Long.valueOf(ao.c()));
        }
        c(k());
        if (ho.a()) {
            ho.a(this.f8027a, "refreshStatus ends:%d", Long.valueOf(ao.c()));
        }
        return this.j;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        if (ho.a()) {
            String str = this.f8027a;
            Object[] objArr = new Object[1];
            AppStatus appStatus = this.j;
            objArr[0] = appStatus == null ? null : appStatus.toString();
            ho.a(str, "onVisibilityChanged, status:%s", objArr);
        }
        super.onVisibilityChanged(view, i);
        this.M = i == 0;
        if (this.F) {
            refreshStatusAsync(null);
        } else {
            ho.c(this.f8027a, "not attached to window, return.");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        String str;
        String str2;
        super.onDetachedFromWindow();
        this.F = false;
        try {
            if (ho.a()) {
                String str3 = this.f8027a;
                Object[] objArr = new Object[1];
                AppInfo appInfo = this.e;
                objArr[0] = appInfo == null ? null : appInfo.getPackageName();
                ho.a(str3, "onDetachedFromWindow, packageName:%s", objArr);
            } else {
                ho.b(this.f8027a, "onDetachedFromWindow appinfo is " + cz.b(this.e));
            }
            com.huawei.openalliance.ad.download.app.e.h().b(this.e, this.A);
            com.huawei.openalliance.ad.download.app.e.h().b(this.e, this.H);
        } catch (RuntimeException unused) {
            str = this.f8027a;
            str2 = "onDetachedFromWindow RuntimeException";
            ho.c(str, str2);
        } catch (Exception unused2) {
            str = this.f8027a;
            str2 = "onDetachedFromWindow Exception";
            ho.c(str, str2);
        }
    }

    @Override // com.huawei.openalliance.ad.views.a, android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            n();
            b bVar = this.L;
            if (bVar != null) {
                bVar.a();
            }
            IPPSPlacementView iPPSPlacementView = this.v;
            if (iPPSPlacementView != null) {
                updateContent(iPPSPlacementView.getShowId());
            }
        } catch (Throwable th) {
            ho.c(this.f8027a, "ApDownLoadBut click err: " + th.getClass().getSimpleName());
        }
        if (a(false)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (isFastClick()) {
            ho.b(this.f8027a, "fast click, ignore");
            ViewClickInstrumentation.clickOnView(view);
        } else if (g()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            t();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        String str;
        String str2;
        super.onAttachedToWindow();
        this.F = true;
        try {
            if (ho.a()) {
                String str3 = this.f8027a;
                Object[] objArr = new Object[1];
                AppInfo appInfo = this.e;
                objArr[0] = appInfo == null ? null : appInfo.getPackageName();
                ho.a(str3, "onAttachedToWindow, packageName:%s", objArr);
            } else {
                ho.b(this.f8027a, "onAttachedToWindow appinfo is " + cz.b(this.e));
            }
            com.huawei.openalliance.ad.download.app.e.h().a(this.e, this.A);
            com.huawei.openalliance.ad.download.app.e.h().a(this.e, this.H);
            refreshStatusAsync(null);
        } catch (RuntimeException unused) {
            str = this.f8027a;
            str2 = "onAttachedToWindow RuntimeException";
            ho.c(str, str2);
        } catch (Exception unused2) {
            str = this.f8027a;
            str2 = "onAttachedToWindow Exception";
            ho.c(str, str2);
        }
    }

    public AppDownloadButtonStyle getStyle() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.views.a, com.huawei.hms.ads.uiengine.common.IProgressButton
    public AppStatus getStatus() {
        AppStatus appStatus = this.j;
        return appStatus == null ? AppStatus.DOWNLOAD : appStatus;
    }

    public int getSource() {
        return this.o;
    }

    public int getRoundRadius() {
        return getStyle().d();
    }

    public AppInfo getAppInfo() {
        return this.e;
    }

    public void f() {
        a(true);
    }

    public void e() {
        if (ho.a()) {
            ho.a(this.f8027a, "downloadApp, status:%s", getStatus());
        }
        if ((getStatus() == AppStatus.DOWNLOAD || getStatus() == AppStatus.PAUSE || getStatus() == AppStatus.WAITING_FOR_WIFI) && this.e != null) {
            AppDownloadTask task = getTask();
            if (task == null) {
                AppDownloadTask D = D();
                if (D != null) {
                    D.a(this.f);
                }
                com.huawei.openalliance.ad.download.app.e.h().c(D);
                return;
            }
            task.a(Integer.valueOf(this.o));
            task.c(Integer.valueOf(this.r));
            task.i(this.E);
            task.a(this.f);
            com.huawei.openalliance.ad.download.app.e.h().a(task, true);
        }
    }

    public boolean d() {
        return this.D;
    }

    public void d(String str) {
        if (this.m == null) {
            ho.b(this.f8027a, "reportClickEvent, record is null.");
            return;
        }
        ou ouVar = new ou(getContext(), sc.a(getContext(), this.m.e()));
        ouVar.a(this.m);
        z();
        if (y()) {
            ho.b(this.f8027a, "reportClick event.");
            b.a e2 = e(str);
            ouVar.a(e2.a());
            ou ouVar2 = this.N;
            if (ouVar2 != null) {
                ouVar2.a(e2.a());
            }
            jp jpVar = this.O;
            if (jpVar != null) {
                jpVar.a();
            }
            G();
            this.J = null;
            A();
        }
    }

    public void continueDownload() {
        if (g()) {
            return;
        }
        e();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton
    public void cancel() {
        j();
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void c(String str) {
        if (ho.a()) {
            String str2 = this.f8027a;
            Object[] objArr = new Object[2];
            objArr[0] = str;
            AppInfo appInfo = this.e;
            objArr[1] = appInfo == null ? null : appInfo.getPackageName();
            ho.a(str2, "onStatusChanged, packageName:%s, packageName:%s", objArr);
        }
        AppInfo appInfo2 = this.e;
        if (appInfo2 == null || !appInfo2.getPackageName().equals(str)) {
            return;
        }
        E();
    }

    public void c() {
        Context context = getContext();
        if (!(context instanceof Activity) || getStatus() != AppStatus.DOWNLOAD || !this.B || !this.C) {
            e();
            return;
        }
        dp dpVar = new dp(context);
        dpVar.a(new Cdo.a() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.7
            @Override // com.huawei.openalliance.ad.Cdo.a
            public void c(AppInfo appInfo) {
            }

            @Override // com.huawei.openalliance.ad.Cdo.a
            public void b(AppInfo appInfo) {
                if (AppDownloadButton.this.I != null) {
                    AppDownloadButton.this.I.onNewStatusChanged(AppStatusV1.PRE_CHECK_FAILED, 1002, appInfo);
                }
            }

            @Override // com.huawei.openalliance.ad.Cdo.a
            public void a(AppInfo appInfo) {
                AppDownloadButton.this.setNeedShowConfirmDialog(false);
                AppDownloadButton.this.e();
            }
        });
        this.D = true;
        dpVar.a(this.e, this.m, getLeftSize());
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void b(String str) {
        a(str);
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void b(AppDownloadTask appDownloadTask) {
        if (ho.a()) {
            String str = this.f8027a;
            Object[] objArr = new Object[3];
            objArr[0] = appDownloadTask.n();
            AppInfo appInfo = this.e;
            objArr[1] = appInfo == null ? null : appInfo.getPackageName();
            objArr[2] = appDownloadTask.i();
            ho.a(str, "onStatusChanged, taskId:%s, packageName:%s, status:%s", objArr);
        }
        AppInfo appInfo2 = this.e;
        if (appInfo2 == null || !appInfo2.getPackageName().equals(appDownloadTask.n())) {
            return;
        }
        if (!"11".equals(appDownloadTask.H()) || appDownloadTask.i() != com.huawei.openalliance.ad.download.e.WAITING) {
            E();
        } else if (this.M) {
            d(appDownloadTask);
        } else {
            ho.a(this.f8027a, "not visible");
        }
    }

    public void b() {
        AppInfo appInfo = this.e;
        if (appInfo != null && !appInfo.a(Integer.valueOf(this.o)) && !l()) {
            AppDownloadTask task = getTask();
            if ((task == null || !task.o()) && !this.f) {
                dq dqVar = new dq(getContext());
                dqVar.a(new Cdo.a() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.6
                    @Override // com.huawei.openalliance.ad.Cdo.a
                    public void c(AppInfo appInfo2) {
                        AppDownloadButton.this.C();
                    }

                    @Override // com.huawei.openalliance.ad.Cdo.a
                    public void b(AppInfo appInfo2) {
                        if (AppDownloadButton.this.I != null) {
                            AppDownloadButton.this.I.onNewStatusChanged(AppStatusV1.PRE_CHECK_FAILED, 1003, appInfo2);
                        }
                    }

                    @Override // com.huawei.openalliance.ad.Cdo.a
                    public void a(AppInfo appInfo2) {
                        AppDownloadButton.this.setAllowedNonWifiNetwork(true);
                        AppDownloadButton.this.setNeedShowConfirmDialog(false);
                        AppDownloadButton.this.c();
                    }
                });
                dqVar.a(this.e, this.m, getLeftSize());
                return;
            }
            setAllowedNonWifiNetwork(true);
        }
        c();
    }

    @Override // com.huawei.openalliance.ad.download.k
    public void a(String str, final int i) {
        if (ho.a()) {
            ho.a(this.f8027a, "status %s, packageName:%s", Integer.valueOf(i), str);
        }
        if (com.huawei.openalliance.ad.utils.j.a(this.e)) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.9
                @Override // java.lang.Runnable
                public void run() {
                    AppDownloadButton.this.G = i;
                    AppDownloadButton.this.refreshStatus();
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void a(String str) {
        AppInfo appInfo = this.e;
        if (appInfo == null || str == null || !str.equals(appInfo.getPackageName())) {
            return;
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.10
            @Override // java.lang.Runnable
            public void run() {
                AppDownloadButton.this.refreshStatus();
                if (AppDownloadButton.this.g != null) {
                    AppDownloadButton.this.g.onStatusChanged(AppDownloadButton.this.j);
                }
            }
        });
    }

    public void a(CharSequence charSequence, boolean z) {
        e eVar = this.i;
        if (eVar != null && z) {
            charSequence = eVar.a(charSequence, this.j);
        }
        super.setText(charSequence);
    }

    public void a(b bVar) {
        this.L = bVar;
    }

    public void a(MaterialClickInfo materialClickInfo) {
        this.J = materialClickInfo;
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void a(AppDownloadTask appDownloadTask) {
        if (ho.a()) {
            String str = this.f8027a;
            Object[] objArr = new Object[3];
            objArr[0] = appDownloadTask.n();
            AppInfo appInfo = this.e;
            objArr[1] = appInfo == null ? null : appInfo.getPackageName();
            objArr[2] = Integer.valueOf(appDownloadTask.l());
            ho.a(str, "onProgressChanged, taskId:%s, packageName:%s, progress:%s", objArr);
        }
        AppInfo appInfo2 = this.e;
        if (appInfo2 == null || !appInfo2.getPackageName().equals(appDownloadTask.n())) {
            return;
        }
        E();
    }

    public void a() {
        if (j()) {
            com.huawei.openalliance.ad.download.app.l.b(getContext(), this.e);
            OnDownloadStatusChangedListener onDownloadStatusChangedListener = this.g;
            if (onDownloadStatusChangedListener != null) {
                onDownloadStatusChangedListener.onUserCancel(this.e);
            }
        }
    }

    private void z() {
        IPPSNativeView iPPSNativeView = this.t;
        if (iPPSNativeView != null) {
            iPPSNativeView.a(2);
        }
        IPPSLinkedView iPPSLinkedView = this.u;
        if (iPPSLinkedView != null) {
            iPPSLinkedView.a(2);
        }
        PPSAppDetailView pPSAppDetailView = this.w;
        if (pPSAppDetailView != null) {
            pPSAppDetailView.a((Integer) 2);
        }
        IPPSPlacementView iPPSPlacementView = this.v;
        if (iPPSPlacementView != null) {
            iPPSPlacementView.a(2);
        }
        if (getContext() instanceof LandingDetailsActivity) {
            new my(getContext(), this.m, this.J).a();
        }
    }

    private boolean y() {
        if (this.o == 1 || (getContext() instanceof LandingDetailsActivity) || this.m.e() == 7) {
            return true;
        }
        return this.m.e() == 12 && (getContext() instanceof PPSInterstitialAdActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (this.m == null) {
            return;
        }
        Context context = getContext();
        String packageName = this.e.getPackageName();
        ou ouVar = new ou(context, sc.a(context, this.m.e()));
        ouVar.a(this.m);
        if (am.b(context, packageName)) {
            a(context, ouVar);
        } else {
            a(context, packageName, ouVar);
        }
    }

    private boolean w() {
        return os.F(this.m.V()) && com.huawei.openalliance.ad.utils.j.a(this.e);
    }

    private void v() {
        Context context = getContext();
        AppDownloadTask D = D();
        if (context == null || D == null) {
            return;
        }
        com.huawei.openalliance.ad.download.ag.e.a(context).a(D);
    }

    private void u() {
        if (!w() || this.G == 1) {
            x();
        } else {
            v();
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.3
                @Override // java.lang.Runnable
                public void run() {
                    AppDownloadButton.this.x();
                }
            }, 600L);
        }
    }

    private void t() {
        AppStatus status = getStatus();
        ho.b(this.f8027a, "onClick, status:" + status);
        switch (AnonymousClass2.b[status.ordinal()]) {
            case 1:
                b(this.x);
                d(l() ? "restore" : "download");
                break;
            case 2:
            case 3:
                b(false);
                break;
            case 4:
                AppDownloadTask task = getTask();
                if (task != null) {
                    com.huawei.openalliance.ad.download.app.e.h().b(task);
                    break;
                }
                break;
            case 5:
                u();
                break;
            case 6:
                AppDownloadTask task2 = getTask();
                if (task2 != null) {
                    e(task2);
                    break;
                }
                break;
        }
    }

    private void s() {
        com.huawei.openalliance.ad.views.interfaces.x xVar = this.y;
        if (xVar != null) {
            xVar.a(this);
        }
    }

    private boolean r() {
        if (!m()) {
            return false;
        }
        sw swVar = new sw(getContext(), this.m, null);
        swVar.a(this.o);
        swVar.a(this.E);
        swVar.a();
        d(ClickDestination.AGMINIMARKET);
        return true;
    }

    private boolean q() {
        if (!"9".equals(this.e.b(Integer.valueOf(this.o))) || TextUtils.isEmpty(this.e.getPackageName()) || TextUtils.isEmpty(this.e.y())) {
            return false;
        }
        ss ssVar = new ss(getContext(), this.m);
        if (ssVar.a()) {
            d(ssVar.c());
            return true;
        }
        s();
        return false;
    }

    private boolean p() {
        String b2 = this.e.b(Integer.valueOf(this.o));
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(this.e.r()) || !b2.equals("7")) {
            return false;
        }
        if (new sn(getContext(), this.m).a()) {
            d(ClickDestination.APPMARKET);
            return true;
        }
        s();
        return false;
    }

    private boolean o() {
        if (this.e == null) {
            s();
            ho.b(this.f8027a, "appInfo is empty");
            return false;
        }
        if (this.j == AppStatus.INSTALLED || l() || this.e.a(Integer.valueOf(this.o))) {
            return true;
        }
        String b2 = this.e.b(Integer.valueOf(this.o));
        if (!TextUtils.isEmpty(b2)) {
            if (b2.equals("7") && !TextUtils.isEmpty(this.e.r())) {
                return true;
            }
            if (b2.equals("9") && !TextUtils.isEmpty(this.e.getPackageName()) && !TextUtils.isEmpty(this.e.y())) {
                return true;
            }
        }
        if (!TextUtils.isEmpty(this.e.getDownloadUrl())) {
            return true;
        }
        s();
        return false;
    }

    private void n() {
        Integer num = this.p;
        if (num != null) {
            if (this.q) {
                this.o = num.intValue();
                this.p = null;
            } else {
                this.q = true;
            }
        }
        ho.b(this.f8027a, "clickSource:%s", Integer.valueOf(this.o));
    }

    private boolean m() {
        AppInfo appInfo = this.e;
        if (appInfo == null) {
            return false;
        }
        String b2 = appInfo.b(Integer.valueOf(this.o));
        return (TextUtils.isEmpty(b2) || TextUtils.isEmpty(this.e.getPackageName()) || !b2.equals("6")) ? false : true;
    }

    private boolean l() {
        AppInfo appInfo = this.e;
        return appInfo != null && "11".equals(appInfo.getPriorInstallWay());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppDownloadTask k() {
        AppDownloadTask appDownloadTask;
        if (ho.a()) {
            ho.a(this.f8027a, "refresAppStatus, start.");
        }
        AppStatus appStatus = AppStatus.DOWNLOAD;
        AppInfo appInfo = this.e;
        AppDownloadTask appDownloadTask2 = null;
        if (appInfo != null) {
            String packageName = appInfo.getPackageName();
            if (com.huawei.openalliance.ad.utils.i.b(getContext(), this.e.getPackageName()) != null) {
                appStatus = AppStatus.INSTALLED;
            } else {
                appDownloadTask2 = getTask();
                if (appDownloadTask2 != null) {
                    appStatus = a(appDownloadTask2, packageName, false);
                }
            }
            AppDownloadTask appDownloadTask3 = appDownloadTask2;
            appDownloadTask2 = packageName;
            appDownloadTask = appDownloadTask3;
        } else {
            appDownloadTask = null;
        }
        this.k = this.j;
        this.j = appStatus;
        if (ho.a()) {
            ho.a(this.f8027a, "refresAppStatus, status:%s, packageName:%s", this.j, appDownloadTask2);
        }
        return appDownloadTask;
    }

    private boolean j() {
        boolean b2 = com.huawei.openalliance.ad.download.app.e.h().b(this.e);
        refreshStatus();
        setOnNonWifiDownloadListener(null);
        setNeedShowConfirmDialog(true);
        setNeedShowConfirmDialog(true);
        return b2;
    }

    private boolean i() {
        AppInfo appInfo;
        if (this.o != 1 || (appInfo = this.e) == null) {
            return false;
        }
        return "21".equalsIgnoreCase(appInfo.a());
    }

    private boolean h() {
        if (this.m == null) {
            return false;
        }
        com.huawei.openalliance.ad.inter.data.e eVar = this.d;
        boolean a2 = new su(getContext(), this.m, true, eVar != null ? eVar.ah() : null).a();
        if (a2) {
            ho.b(this.f8027a, "list page btn openLandingPage");
            d(ClickDestination.WEB);
        }
        return a2;
    }

    private AppDownloadTask getTask() {
        ContentRecord contentRecord;
        AppDownloadTask c2 = com.huawei.openalliance.ad.download.app.e.h().c(this.e);
        if (c2 != null && (contentRecord = this.m) != null) {
            c2.k(contentRecord.l());
            c2.l(this.m.Y());
            if (!c2.K()) {
                c2.m(this.m.m());
            }
            c2.n(this.m.j());
            c2.e(this.m.aO());
            c2.q(this.m.aN());
            c2.h(this.m.aB());
            c2.g(this.m.aA());
            if (c2.C() == null) {
                ou ouVar = new ou(getContext(), sc.a(getContext(), this.m.e()));
                ouVar.a(this.m);
                c2.a(ouVar);
            } else {
                ContentRecord a2 = c2.C().a();
                if (a2 != null) {
                    a2.G(this.m.aA());
                    a2.H(this.m.aB());
                    a2.c(this.m.j());
                }
            }
        }
        return c2;
    }

    private long getLeftSize() {
        if (this.e == null) {
            return 0L;
        }
        AppDownloadTask task = getTask();
        long fileSize = this.e.getFileSize();
        if (task == null) {
            return fileSize;
        }
        long fileSize2 = this.e.getFileSize() - task.g();
        return fileSize2 <= 0 ? fileSize : fileSize2;
    }

    private String getAdTag() {
        IPPSNativeView iPPSNativeView = this.t;
        if (iPPSNativeView != null) {
            return iPPSNativeView.getAdTag();
        }
        return null;
    }

    private boolean g() {
        String str;
        String str2;
        if (o()) {
            F();
            if (q()) {
                str = this.f8027a;
                str2 = "open harmony service";
            } else {
                if (this.j == AppStatus.INSTALLED) {
                    t();
                    return true;
                }
                if (i()) {
                    return h();
                }
                if (p()) {
                    str = this.f8027a;
                    str2 = "open Ag detail";
                } else {
                    if (!r()) {
                        return false;
                    }
                    str = this.f8027a;
                    str2 = "open Ag mini detail";
                }
            }
        } else {
            str = this.f8027a;
            str2 = "click action invalid.";
        }
        ho.b(str, str2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(AppDownloadTask appDownloadTask) {
        String str;
        String str2;
        if (this.e == null || this.m == null) {
            str = this.f8027a;
            str2 = "installApk, appinfo or content record is null";
        } else {
            if (appDownloadTask != null) {
                if (appDownloadTask.K()) {
                    com.huawei.openalliance.ad.download.app.e.h().c(appDownloadTask);
                    return;
                } else {
                    ow.a(getContext()).a(this.e, appDownloadTask, new a(this, appDownloadTask, this.I));
                    return;
                }
            }
            str = this.f8027a;
            str2 = "installApk, task is null";
        }
        ho.c(str, str2);
    }

    private void e(AppDownloadTask appDownloadTask) {
        ho.a(this.f8027a, "installApk");
        ContentRecord a2 = appDownloadTask.C().a();
        if (a2 == null) {
            ho.b(this.f8027a, "contentRecord is null! continue install.");
            f(appDownloadTask);
            return;
        }
        if (bb.a(appDownloadTask, getContext())) {
            if (dd.o(getContext())) {
                ho.b(this.f8027a, "start InstallAuthorActivity!");
                a(a2);
                if (a2.ae().C() == 1) {
                    appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                    b(appDownloadTask);
                    return;
                }
            } else if (os.D(a2.V())) {
                ho.b(this.f8027a, "app is background, stop install!");
                appDownloadTask.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
                b(appDownloadTask);
                return;
            }
        }
        f(appDownloadTask);
    }

    private b.a e(String str) {
        b.a aVar = new b.a();
        if (ho.a()) {
            ho.a(this.f8027a, "prepare click info, source: %s, clickInfo: %s", Integer.valueOf(this.o), this.J);
        }
        aVar.b(str).a(Integer.valueOf(this.o)).a(this.J).d(com.huawei.openalliance.ad.utils.b.a(getContext())).e(getAdTag());
        return aVar;
    }

    private void d(AppDownloadTask appDownloadTask) {
        if (!appDownloadTask.J()) {
            appDownloadTask.a(com.huawei.openalliance.ad.download.e.FAILED);
            E();
        } else {
            ho.a(this.f8027a, "switch to nextInstallWay");
            appDownloadTask.a(com.huawei.openalliance.ad.download.e.IDLE);
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.1
                @Override // java.lang.Runnable
                public void run() {
                    AppDownloadButton.this.j = AppStatus.DOWNLOAD;
                    AppDownloadButton.this.b(false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AppDownloadTask appDownloadTask) {
        if (ho.a()) {
            String str = this.f8027a;
            Object[] objArr = new Object[3];
            objArr[0] = this.j;
            objArr[1] = this.k;
            AppInfo appInfo = this.e;
            objArr[2] = appInfo == null ? null : appInfo.getPackageName();
            ho.a(str, "processStatus, status:%s, preStatus:%s, packageName:%s", objArr);
        }
        if (m() && this.j != AppStatus.INSTALLED) {
            b(AppStatus.DOWNLOAD);
            return;
        }
        Context context = getContext();
        AppStatus appStatus = this.j;
        if (appStatus == null) {
            appStatus = AppStatus.DOWNLOAD;
        }
        AppDownloadButtonStyle.Style a2 = this.b.a(getContext(), appStatus, this.o);
        setTextColor(a2.textColor);
        int i = this.l;
        Drawable drawable = a2.background;
        if (i != -1) {
            setProgressDrawable(drawable, this.l);
        } else {
            setProgressDrawable(drawable);
        }
        a(appStatus);
        switch (AnonymousClass2.b[appStatus.ordinal()]) {
            case 1:
                a(context, this.o, AppStatus.DOWNLOAD);
                return;
            case 2:
            case 3:
                a(context, this.o, AppStatus.PAUSE);
                if (this.o == 11) {
                    return;
                }
                break;
            case 4:
                a(context, this.o, AppStatus.DOWNLOADING);
                if (this.o == 11) {
                    return;
                }
                break;
            case 5:
                a(context);
                return;
            case 6:
                if (appDownloadTask != null) {
                    a(appDownloadTask, context);
                    return;
                }
                return;
            case 7:
                if (appDownloadTask != null) {
                    b(appDownloadTask, context);
                    return;
                }
                return;
            default:
                return;
        }
        setProgress(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!bv.e(getContext())) {
            Toast.makeText(getContext(), R.string._2130851114_res_0x7f02352a, 0).show();
            AppDownloadListenerV1 appDownloadListenerV1 = this.I;
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewStatusChanged(AppStatusV1.PRE_CHECK_FAILED, 1004, this.e);
                return;
            }
            return;
        }
        if (this.e.u() && this.n && z) {
            com.huawei.openalliance.ad.download.app.h.a(getContext(), this.e, new h.a() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.5
                @Override // com.huawei.openalliance.ad.download.app.h.a
                public void a(int i) {
                    if (AppDownloadButton.this.I != null) {
                        AppDownloadButton.this.I.onNewStatusChanged(AppStatusV1.PRE_CHECK_FAILED, i, AppDownloadButton.this.e);
                    }
                }

                @Override // com.huawei.openalliance.ad.download.app.h.a
                public void a() {
                    AppDownloadButton.this.setNeedShowConfirmDialog(false);
                    AppDownloadButton.this.B();
                }
            });
        } else {
            B();
        }
    }

    private void b(AppStatus appStatus) {
        AppDownloadButtonStyle.Style a2 = this.b.a(getContext(), appStatus, this.o);
        setTextColor(a2.textColor);
        setProgressDrawable(a2.background);
        a(getContext(), this.o, appStatus);
    }

    private void b(AppDownloadTask appDownloadTask, Context context) {
        if (appDownloadTask != null && (com.huawei.openalliance.ad.utils.ae.b(appDownloadTask.d()) || appDownloadTask.K() || "11".equals(appDownloadTask.H()))) {
            a(context, this.o, AppStatus.INSTALLING);
            return;
        }
        a(context, this.o, AppStatus.DOWNLOAD);
        this.j = AppStatus.DOWNLOAD;
        com.huawei.openalliance.ad.download.app.e.h().a(this.e);
    }

    private ta b(Context context) {
        if (this.m == null || context == null) {
            ho.b(this.f8027a, "getClickAction, invalid param");
            return null;
        }
        HashMap hashMap = new HashMap();
        MetaData h = this.m.h();
        if (h != null) {
            hashMap.put("appId", h.m());
            hashMap.put("thirdId", h.l());
        }
        return sz.a(context, this.m, hashMap);
    }

    private boolean a(boolean z) {
        AppDownloadListenerV1 appDownloadListenerV1;
        if (getStatus() == AppStatus.DOWNLOAD && (appDownloadListenerV1 = this.I) != null) {
            appDownloadListenerV1.onNewStatusChanged(AppStatusV1.FIRST_CLICK_BUTTON, 1000, this.e);
        }
        if (this.e != null && !z) {
            return false;
        }
        AppDownloadListenerV1 appDownloadListenerV12 = this.I;
        if (appDownloadListenerV12 != null) {
            appDownloadListenerV12.onNewStatusChanged(AppStatusV1.PRE_CHECK_FAILED, 1002, this.e);
        }
        ho.b(this.f8027a, "cancel download");
        return true;
    }

    private void a(AppDownloadTask appDownloadTask, Context context) {
        if (appDownloadTask != null && (com.huawei.openalliance.ad.utils.ae.b(appDownloadTask.d()) || appDownloadTask.K())) {
            a(context, this.o, AppStatus.INSTALL);
            return;
        }
        a(context, this.o, AppStatus.DOWNLOAD);
        this.j = AppStatus.DOWNLOAD;
        com.huawei.openalliance.ad.download.app.e.h().a(this.e);
    }

    private void a(ContentRecord contentRecord) {
        ho.b(this.f8027a, "startInstallAuthorActivity");
        Intent intent = new Intent(getContext(), (Class<?>) PPSInstallAuthorActivity.class);
        intent.setFlags(HiUserInfo.DATA_CLOUD);
        intent.putExtra(com.huawei.openalliance.ad.constant.Constants.CONTENT_KEY, be.b(contentRecord));
        PPSInstallAuthorActivity.a(new com.huawei.openalliance.ad.views.interfaces.g() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.4
            @Override // com.huawei.openalliance.ad.views.interfaces.g
            public void a(AppDownloadTask appDownloadTask) {
                if (appDownloadTask != null) {
                    AppDownloadButton.this.f(appDownloadTask);
                }
            }
        });
        intent.setClipData(com.huawei.openalliance.ad.constant.Constants.CLIP_DATA);
        getContext().startActivity(intent);
    }

    private void a(Context context, String str, ou ouVar) {
        b.a e2 = e(com.huawei.openalliance.ad.utils.j.a(str, this.e));
        ta b2 = b(context);
        if ((b2 instanceof sp) && b2.a()) {
            ho.b(this.f8027a, "open fast app via sdk.");
            a(context, ouVar, e2, false);
            return;
        }
        tf.a aVar = new tf.a();
        aVar.a(this.e).c(str).a(this.m);
        boolean a2 = com.huawei.openalliance.ad.utils.i.a(context, str, this.e.getIntentUri(), aVar.a());
        ho.b(this.f8027a, "handle android click, ret: %s", Boolean.valueOf(a2));
        if (a2) {
            a(context, ouVar, e2);
            return;
        }
        ho.b(this.f8027a, "handleClick, openAppIntent failed");
        ouVar.a(EventType.INTENTFAIL, Integer.valueOf(com.huawei.openalliance.ad.utils.j.b(str, this.e)), Integer.valueOf(com.huawei.openalliance.ad.utils.i.a(context, str) ? 2 : 1));
        if (!com.huawei.openalliance.ad.utils.i.a(context, str, aVar.a())) {
            ho.b(this.f8027a, "handleClick, openAppMainPage failed");
            return;
        }
        ouVar.a(Integer.valueOf(this.o));
        com.huawei.openalliance.ad.download.app.l.a(context, this.e);
        z();
        ouVar.a(e2.a());
        ou ouVar2 = this.N;
        if (ouVar2 != null) {
            ouVar2.a(e2.a());
        }
        jp jpVar = this.O;
        if (jpVar != null) {
            jpVar.a();
        }
        G();
        this.J = null;
        A();
    }

    private void a(Context context, ou ouVar, b.a aVar, boolean z) {
        com.huawei.openalliance.ad.download.app.l.a(context, this.e);
        if (z) {
            ouVar.a(EventType.INTENTSUCCESS, Integer.valueOf(com.huawei.openalliance.ad.utils.j.b(this.e.getPackageName(), this.e)), (Integer) null);
        }
        z();
        ouVar.a(aVar.a());
        ou ouVar2 = this.N;
        if (ouVar2 != null) {
            ouVar2.a(aVar.a());
        }
        jp jpVar = this.O;
        if (jpVar != null) {
            jpVar.a();
        }
        G();
        this.J = null;
        A();
    }

    private void a(Context context, ou ouVar, b.a aVar) {
        a(context, ouVar, aVar, true);
    }

    private void a(Context context, ou ouVar) {
        AppInfo appInfo;
        if (this.m != null && (appInfo = this.e) != null && !TextUtils.isEmpty(appInfo.getUniqueId())) {
            this.m.v(this.e.getUniqueId());
        }
        boolean a2 = new sr(context, this.m).a();
        ho.b(this.f8027a, "handle harmony click, ret: %s", Boolean.valueOf(a2));
        if (a2) {
            z();
            b.a e2 = e(ClickDestination.HARMONY_APP);
            ouVar.a(e2.a());
            ou ouVar2 = this.N;
            if (ouVar2 != null) {
                ouVar2.a(e2.a());
            }
            jp jpVar = this.O;
            if (jpVar != null) {
                jpVar.a();
            }
            G();
            this.J = null;
            A();
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        try {
            super.setPadding(0, 0, 0, 0);
            this.b = new AppDownloadButtonStyle(context);
            setOnClickListener(this);
            setCancelBtnClickListener(new c(this));
            this.A = new d(this);
            com.huawei.openalliance.ad.download.app.e.a(context.getApplicationContext());
            com.huawei.openalliance.ad.download.app.i.a(context);
            this.I = com.huawei.openalliance.ad.download.app.e.h().i();
        } catch (Throwable unused) {
            ho.c(this.f8027a, "AppDownloadButton init failed");
        }
    }

    private void a(Context context, int i, AppStatus appStatus) {
        String a2 = a(i, appStatus);
        if (TextUtils.isEmpty(a2)) {
            a((CharSequence) a(context, appStatus), true);
            return;
        }
        if (this.K && i == 1 && appStatus == AppStatus.DOWNLOADING) {
            a2 = a2 + NumberFormat.getPercentInstance().format((this.l * 1.0f) / 100.0f);
        }
        a((CharSequence) a2, false);
    }

    private void a(Context context) {
        a(context, this.o, AppStatus.INSTALLED);
    }

    private String a(Context context, AppStatus appStatus) {
        int i;
        if (context == null || appStatus == null || this.e == null) {
            return "";
        }
        int i2 = AnonymousClass2.b[appStatus.ordinal()];
        if (i2 == 1) {
            return com.huawei.openalliance.ad.utils.j.a(context, this.e);
        }
        if (i2 == 3) {
            i = R.string._2130851085_res_0x7f02350d;
        } else if (i2 != 4) {
            if (i2 == 5) {
                return com.huawei.openalliance.ad.utils.j.a(context, this.e, this.G);
            }
            if (i2 == 6) {
                i = R.string._2130851081_res_0x7f023509;
            } else {
                if (i2 != 7) {
                    return null;
                }
                i = R.string._2130851082_res_0x7f02350a;
            }
        } else {
            if (this.o != 11) {
                return NumberFormat.getPercentInstance().format((this.l * 1.0f) / 100.0f);
            }
            i = R.string._2130851077_res_0x7f023505;
        }
        return context.getString(i);
    }

    private String a(int i, AppStatus appStatus) {
        String str = null;
        if (l() || bg.a(this.s)) {
            return null;
        }
        int i2 = i != 1 ? 1 : 2;
        int a2 = TextState.a(appStatus);
        String c2 = com.huawei.openalliance.ad.utils.d.c();
        Iterator<TextState> it = this.s.iterator();
        String str2 = null;
        String str3 = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TextState next = it.next();
            ho.a(this.f8027a, "state.getShowPosition() is %d", Integer.valueOf(next.a()));
            if (next != null && i2 == next.a()) {
                if (a2 == next.b()) {
                    if (c2.equalsIgnoreCase(new Locale(next.c()).getLanguage())) {
                        str = next.d();
                        break;
                    }
                    if (1 == next.e()) {
                        str2 = next.d();
                    }
                }
                if (next.b() == 0) {
                    str3 = next.d();
                }
            }
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            str3 = str2;
        }
        return cz.c(str3);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
    
        if (r5.l <= 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006f, code lost:
    
        if (r6 > 0) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.openalliance.ad.download.app.AppStatus a(com.huawei.openalliance.ad.download.app.AppDownloadTask r6, java.lang.String r7, boolean r8) {
        /*
            r5 = this;
            com.huawei.openalliance.ad.download.e r0 = r6.i()
            boolean r1 = com.huawei.openalliance.ad.ho.a()
            if (r1 == 0) goto L26
            java.lang.String r1 = r5.f8027a
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            if (r0 == 0) goto L16
            int r3 = r0.a()
            goto L17
        L16:
            r3 = -1
        L17:
            r4 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r4] = r3
            r3 = 1
            r2[r3] = r7
            java.lang.String r7 = "refreshStatus, downloadStatus:%d, packageName:%s"
            com.huawei.openalliance.ad.ho.a(r1, r7, r2)
        L26:
            int[] r7 = com.huawei.openalliance.ad.views.AppDownloadButton.AnonymousClass2.f7774a
            int r0 = r0.ordinal()
            r7 = r7[r0]
            switch(r7) {
                case 1: goto L69;
                case 2: goto L5f;
                case 3: goto L5c;
                case 4: goto L5c;
                case 5: goto L59;
                case 6: goto L56;
                case 7: goto L45;
                case 8: goto L32;
                default: goto L31;
            }
        L31:
            goto L74
        L32:
            com.huawei.openalliance.ad.download.DownloadTask$c r7 = r6.p()
            int r6 = r6.l()
            r5.l = r6
            com.huawei.openalliance.ad.download.DownloadTask$c r6 = com.huawei.openalliance.ad.download.DownloadTask.c.NONE
            if (r7 != r6) goto L71
            int r6 = r5.l
            if (r6 <= 0) goto L74
            goto L71
        L45:
            if (r8 != 0) goto L53
            com.huawei.openalliance.ad.download.app.AppStatus r6 = com.huawei.openalliance.ad.download.app.AppStatus.DOWNLOAD
            com.huawei.openalliance.ad.download.app.e r7 = com.huawei.openalliance.ad.download.app.e.h()
            com.huawei.openalliance.ad.inter.data.AppInfo r8 = r5.e
            r7.a(r8)
            goto L76
        L53:
            com.huawei.openalliance.ad.download.app.AppStatus r6 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALLED
            goto L76
        L56:
            com.huawei.openalliance.ad.download.app.AppStatus r7 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALLING
            goto L61
        L59:
            com.huawei.openalliance.ad.download.app.AppStatus r6 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALL
            goto L76
        L5c:
            com.huawei.openalliance.ad.download.app.AppStatus r7 = com.huawei.openalliance.ad.download.app.AppStatus.DOWNLOADING
            goto L61
        L5f:
            com.huawei.openalliance.ad.download.app.AppStatus r7 = com.huawei.openalliance.ad.download.app.AppStatus.WAITING_FOR_WIFI
        L61:
            int r6 = r6.l()
            r5.l = r6
            r6 = r7
            goto L76
        L69:
            int r6 = r6.l()
            r5.l = r6
            if (r6 <= 0) goto L74
        L71:
            com.huawei.openalliance.ad.download.app.AppStatus r6 = com.huawei.openalliance.ad.download.app.AppStatus.PAUSE
            goto L76
        L74:
            com.huawei.openalliance.ad.download.app.AppStatus r6 = com.huawei.openalliance.ad.download.app.AppStatus.DOWNLOAD
        L76:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.AppDownloadButton.a(com.huawei.openalliance.ad.download.app.AppDownloadTask, java.lang.String, boolean):com.huawei.openalliance.ad.download.app.AppStatus");
    }

    private void G() {
        IPPSNativeView iPPSNativeView = this.t;
        if (iPPSNativeView != null) {
            iPPSNativeView.c(this);
        }
    }

    private void F() {
        com.huawei.openalliance.ad.views.interfaces.x xVar = this.y;
        if (xVar != null) {
            xVar.b(this);
        }
        View.OnClickListener onClickListener = this.z;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    private void E() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.8
            @Override // java.lang.Runnable
            public void run() {
                AppDownloadButton.this.refreshStatus();
                if (AppDownloadButton.this.g == null || AppDownloadButton.this.k == AppDownloadButton.this.getStatus()) {
                    return;
                }
                AppDownloadButton.this.g.onStatusChanged(AppDownloadButton.this.getStatus());
            }
        });
    }

    private AppDownloadTask D() {
        ou ouVar;
        if (this.m != null) {
            ouVar = new ou(getContext(), sc.a(getContext(), this.m.e()));
            ouVar.a(this.m);
        } else {
            ouVar = null;
        }
        AppDownloadTask a2 = new AppDownloadTask.a().a(this.f).a(this.e).a(ouVar).a(com.huawei.openalliance.ad.download.app.e.h().d(this.e)).b(com.huawei.openalliance.ad.download.app.e.h().e(this.e)).a();
        if (a2 != null) {
            a2.a(Integer.valueOf(this.o));
            a2.c(Integer.valueOf(this.r));
            a2.i(this.E);
            ContentRecord contentRecord = this.m;
            if (contentRecord != null) {
                a2.l(contentRecord.Y());
                a2.k(this.m.l());
                a2.m(this.m.m());
                a2.n(this.m.j());
                a2.g(this.m.aA());
                a2.h(this.m.aB());
            }
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        ho.b(this.f8027a, "preWifiDownload");
        if (this.e != null) {
            AppDownloadTask task = getTask();
            if (task == null) {
                task = D();
                com.huawei.openalliance.ad.download.app.e.h().b((com.huawei.openalliance.ad.download.app.e) task);
            }
            if (task != null) {
                task.a(DownloadTask.a.WAITING_WIFI_DOWNLOAD);
            }
            if (getStatus() == AppStatus.DOWNLOAD || getStatus() == AppStatus.WAITING_FOR_WIFI || getStatus() == AppStatus.PAUSE) {
                com.huawei.openalliance.ad.download.app.e.h().e((com.huawei.openalliance.ad.download.app.e) task);
            }
        }
    }

    static class a implements ow.a {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<AppDownloadButton> f7782a;
        AppDownloadTask b;
        AppDownloadListenerV1 c;

        @Override // com.huawei.openalliance.ad.ow.a
        public void b() {
            ho.b("AppInsResCallback", "onSystemInstallStart");
            this.b.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
            d();
        }

        @Override // com.huawei.openalliance.ad.ow.a
        public void a(int i) {
            ho.c("AppInsResCallback", "install app failed.");
            this.b.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
            d();
            AppDownloadListenerV1 appDownloadListenerV1 = this.c;
            if (appDownloadListenerV1 != null) {
                appDownloadListenerV1.onNewStatusChanged(AppStatusV1.INSTALL_FAILED, 1002, this.b.B());
            }
        }

        @Override // com.huawei.openalliance.ad.ow.a
        public void a() {
            ho.b("AppInsResCallback", "onSilentInstallStart");
            this.b.a(com.huawei.openalliance.ad.download.e.INSTALLING);
            d();
        }

        private void d() {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.c();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            AppDownloadButton appDownloadButton = this.f7782a.get();
            if (appDownloadButton != null) {
                appDownloadButton.refreshStatus();
            }
        }

        a(AppDownloadButton appDownloadButton, AppDownloadTask appDownloadTask, AppDownloadListenerV1 appDownloadListenerV1) {
            this.f7782a = new WeakReference<>(appDownloadButton);
            this.b = appDownloadTask;
            this.c = appDownloadListenerV1;
        }
    }

    static class d implements com.huawei.openalliance.ad.download.l {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<com.huawei.openalliance.ad.download.l> f7785a;

        @Override // com.huawei.openalliance.ad.download.l
        public void c(String str) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.c(str);
            }
        }

        @Override // com.huawei.openalliance.ad.download.l
        public void b(String str) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.b(str);
            }
        }

        @Override // com.huawei.openalliance.ad.download.l
        public void b(AppDownloadTask appDownloadTask) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.b(appDownloadTask);
            }
        }

        @Override // com.huawei.openalliance.ad.download.k
        public void a(String str, int i) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.a(str, i);
            }
        }

        @Override // com.huawei.openalliance.ad.download.l
        public void a(String str) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.a(str);
            }
        }

        @Override // com.huawei.openalliance.ad.download.l
        public void a(AppDownloadTask appDownloadTask) {
            com.huawei.openalliance.ad.download.l lVar = this.f7785a.get();
            if (lVar != null) {
                lVar.a(appDownloadTask);
            }
        }

        public d(com.huawei.openalliance.ad.download.l lVar) {
            this.f7785a = new WeakReference<>(lVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        if (!bv.e(getContext())) {
            Toast.makeText(getContext(), R.string._2130851114_res_0x7f02352a, 0).show();
            return;
        }
        if (!bv.c(getContext())) {
            long leftSize = getLeftSize();
            OnNonWifiDownloadListener onNonWifiDownloadListener = this.h;
            if (onNonWifiDownloadListener == null) {
                b();
                return;
            } else if (!onNonWifiDownloadListener.onNonWifiDownload(this.e, leftSize)) {
                return;
            }
        }
        c();
    }

    private void A() {
        IPPSNativeView iPPSNativeView = this.t;
        if (iPPSNativeView != null) {
            iPPSNativeView.f();
        }
    }

    public AppDownloadButton(Context context, Boolean bool, IProgressButton iProgressButton) {
        super(context, bool, iProgressButton);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, (AttributeSet) null, -1, -1);
    }

    public AppDownloadButton(Context context, Boolean bool) {
        super(context, bool);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, (AttributeSet) null, -1, -1);
    }

    static class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final OnStatusRefreshedListener f7787a;
        private WeakReference<AppDownloadButton> b;

        @Override // java.lang.Runnable
        public void run() {
            final AppDownloadButton appDownloadButton = this.b.get();
            if (appDownloadButton != null) {
                final AppDownloadTask k = appDownloadButton.k();
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.AppDownloadButton.g.1
                    @Override // java.lang.Runnable
                    public void run() {
                        appDownloadButton.c(k);
                        if (g.this.f7787a != null) {
                            g.this.f7787a.onRefreshed(appDownloadButton.j);
                        }
                    }
                });
            }
        }

        public g(AppDownloadButton appDownloadButton, OnStatusRefreshedListener onStatusRefreshedListener) {
            this.f7787a = onStatusRefreshedListener;
            this.b = new WeakReference<>(appDownloadButton);
        }
    }

    public AppDownloadButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, attributeSet, i, i2);
    }

    static class c implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<AppDownloadButton> f7784a;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppDownloadButton appDownloadButton = this.f7784a.get();
            if (appDownloadButton == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ho.b(appDownloadButton.f8027a, "on cancel btn click.");
            appDownloadButton.a();
            ViewClickInstrumentation.clickOnView(view);
        }

        public c(AppDownloadButton appDownloadButton) {
            this.f7784a = new WeakReference<>(appDownloadButton);
        }
    }

    static class f implements OnResolutionRequiredListener {

        /* renamed from: a, reason: collision with root package name */
        OnResolutionRequiredListener f7786a;

        @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnResolutionRequiredListener
        public void onResolutionRequired(AppInfo appInfo, Bundle bundle) {
            ho.b("ResReqListener", "onResolutionRequired");
            OnResolutionRequiredListener onResolutionRequiredListener = this.f7786a;
            if (onResolutionRequiredListener != null) {
                onResolutionRequiredListener.onResolutionRequired(appInfo, bundle);
            }
        }

        public f(OnResolutionRequiredListener onResolutionRequiredListener) {
            this.f7786a = onResolutionRequiredListener;
        }
    }

    public AppDownloadButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, attributeSet, i, -1);
    }

    /* renamed from: com.huawei.openalliance.ad.views.AppDownloadButton$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7774a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[AppStatus.values().length];
            b = iArr;
            try {
                iArr[AppStatus.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[AppStatus.WAITING_FOR_WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[AppStatus.PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[AppStatus.DOWNLOADING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[AppStatus.INSTALLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[AppStatus.INSTALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[AppStatus.INSTALLING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[com.huawei.openalliance.ad.download.e.values().length];
            f7774a = iArr2;
            try {
                iArr2[com.huawei.openalliance.ad.download.e.FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.WAITING_FOR_WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.WAITING.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.DOWNLOADING.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.DOWNLOADED.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.INSTALLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.INSTALLED.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f7774a[com.huawei.openalliance.ad.download.e.IDLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public AppDownloadButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, attributeSet, -1, -1);
    }

    public AppDownloadButton(Context context) {
        super(context);
        this.j = AppStatus.DOWNLOAD;
        this.l = -1;
        this.n = true;
        this.o = 1;
        this.q = false;
        this.r = 2;
        this.x = true;
        this.C = true;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.K = false;
        a(context, (AttributeSet) null, -1, -1);
    }
}
