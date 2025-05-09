package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.template.downloadbuttonstyle.RemoteButtonStyleAttr;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;
import com.huawei.hms.ads.uiengine.c;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.InterstitialKeys;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.NativeMethods;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.StatusChangeMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSNativeView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class bu extends c.a {

    /* renamed from: a, reason: collision with root package name */
    com.huawei.hms.ads.template.downloadbuttonstyle.a f6653a;
    private Context b;
    private WeakReference<PPSNativeView> c;
    private ContentRecord d;
    private qq e;
    private com.huawei.openalliance.ad.inter.data.e f;
    private IPPSUiEngineCallback g;
    private String h;
    private a i;

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(Bundle bundle) {
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void c() {
    }

    public void f() {
        com.huawei.hms.ads.template.downloadbuttonstyle.a aVar = this.f6653a;
        if (aVar != null) {
            aVar.b();
            this.f6653a = null;
        }
        com.huawei.openalliance.ad.download.app.d.a().c(this.i);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public boolean e() {
        if (com.huawei.openalliance.ad.utils.c.a()) {
            throw new RemoteException("Use engine down!");
        }
        return bq.a(this.b, this.d);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public boolean d() {
        return this.f.V();
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void b(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        str.hashCode();
        if (str.equals("playTime")) {
            c(bundle);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void b(String str, long j, long j2, int i, int i2) {
        this.e.a(j, j2, i, i2, str);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public String b() {
        ContentRecord contentRecord = this.d;
        if (contentRecord != null) {
            return contentRecord.j();
        }
        return null;
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(boolean z) {
        this.e.b(z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.c
    public void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.b("NativeProxy", "callMethod: %s", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -924653417:
                if (str.equals(NativeMethods.UPDATE_BTN_TXT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -881549925:
                if (str.equals("reportCommonEvent")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 108023206:
                if (str.equals("onCommonAnalysis")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 465262104:
                if (str.equals("update_btn_style")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            b(iObjectWrapper, bundle);
            return;
        }
        if (c == 1) {
            new bp(this.b).a(bundle, this.d, this.e);
            return;
        }
        if (c == 2) {
            new bo(this.b).a(bundle, this.d, this.e);
        } else if (c != 3) {
            ho.b("NativeProxy", "call method fall to default.");
        } else {
            c(iObjectWrapper, bundle);
        }
    }

    public void a(String str, Bundle bundle) {
        if (this.g == null) {
            ho.c("NativeProxy", "on call back, call back is null");
            return;
        }
        ho.b("NativeProxy", "onCallback, method: %s", str);
        try {
            this.g.onCallResult(str, bundle);
        } catch (Throwable th) {
            ho.c("NativeProxy", "onCallback ex: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.c
    public void a(String str, long j, long j2, int i, int i2) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1891923166:
                if (str.equals(InterstitialKeys.RPT_PLAY_PAUSE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1888605810:
                if (str.equals("playStart")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -493598457:
                if (str.equals("playEnd")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1540819073:
                if (str.equals(InterstitialKeys.RPT_PLAY_RESUME)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.e.b(j, j2, i, i2);
            return;
        }
        if (c == 1) {
            this.e.c();
        } else if (c == 2) {
            this.e.c(j, j2, i, i2);
        } else {
            if (c != 3) {
                return;
            }
            this.e.f();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(String str, int i) {
        PPSNativeView pPSNativeView = this.c.get();
        if (!EventType.INTERACTSHOW.value().equals(str)) {
            if (pPSNativeView != null) {
                pPSNativeView.a(Integer.valueOf(i));
                return;
            }
            return;
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.b(Integer.valueOf(i));
        String a2 = com.huawei.openalliance.ad.utils.dd.a((jk) pPSNativeView);
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar != null) {
            ho.a("NativeProxy", "slotId: %s, contentId: %s, slot pos: %s", eVar.getSlotId(), this.f.getContentId(), a2);
        }
        if (!com.huawei.openalliance.ad.utils.cz.b(a2)) {
            c0207a.d(a2);
        }
        if (pPSNativeView != null) {
            c0207a.a(com.huawei.openalliance.ad.utils.b.a(pPSNativeView));
        }
        this.e.c(c0207a.a());
    }

    public void a(String str) {
        ho.a("NativeProxy", "updateShowId: %s", str);
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar != null) {
            eVar.h(str);
        }
        ContentRecord contentRecord = this.d;
        if (contentRecord == null) {
            return;
        }
        contentRecord.c(str);
        this.e.a(this.d);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IPPSUiEngineCallback iPPSUiEngineCallback) {
        this.g = iPPSUiEngineCallback;
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IObjectWrapper iObjectWrapper, String str, Bundle bundle) {
        int i = bundle.getInt(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE);
        if (3 == i) {
            RemoteButtonStyleAttr a2 = br.a(bundle);
            if (a2 != null) {
                a(iObjectWrapper, str, i, a2);
                return;
            }
            i = 2;
        }
        a(iObjectWrapper, str, i, (RemoteButtonStyleAttr) null);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IObjectWrapper iObjectWrapper, String str) {
        a(iObjectWrapper, str, 0, (RemoteButtonStyleAttr) null);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IObjectWrapper iObjectWrapper, Bundle bundle) {
        ho.b("NativeProxy", "onAdClickWithParam");
        if (iObjectWrapper == null || this.f == null) {
            return;
        }
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        PPSNativeView pPSNativeView = this.c.get();
        int i = bundle.getInt("imp_source");
        String string = bundle.getString("click_info");
        boolean z = bundle.getBoolean(ParamConstants.Param.OPEN_LANDING, true);
        int i2 = bundle.getInt(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS);
        String string2 = bundle.getString(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, "n");
        a(i2, string2, this.f.getVideoInfo());
        MaterialClickInfo materialClickInfo = (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(string, MaterialClickInfo.class, new Class[0]);
        if (pPSNativeView != null) {
            a(bundle, pPSNativeView, i2, string2);
            pPSNativeView.setMaterialClickInfo(materialClickInfo);
            pPSNativeView.a(view, i, z);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IObjectWrapper iObjectWrapper, int i) {
        if (iObjectWrapper != null) {
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            PPSNativeView pPSNativeView = this.c.get();
            if (pPSNativeView != null) {
                pPSNativeView.a(view, i);
            }
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            PPSNativeView pPSNativeView = this.c.get();
            if (view == null || pPSNativeView == null) {
                return;
            }
            pPSNativeView.b(view);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(long j, long j2) {
        new com.huawei.openalliance.ad.analysis.c(this.b).a(this.d, j, j2);
    }

    public void a(long j) {
        ho.a("NativeProxy", "updateStartShowTime: %s", Long.valueOf(j));
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar != null) {
            eVar.a(j);
        }
        ContentRecord contentRecord = this.d;
        if (contentRecord == null) {
            return;
        }
        contentRecord.f(j);
        this.e.a(this.d);
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a(int i) {
        com.huawei.openalliance.ad.inter.data.e eVar = this.f;
        if (eVar == null || eVar.getAppInfo() == null) {
            return;
        }
        if (i == 0) {
            ho.b("NativeProxy", " hiadsdk's AIDL: onAdClickSixElementsBtn: DESC");
            PPSNativeView pPSNativeView = this.c.get();
            if (pPSNativeView == null || pPSNativeView.getClickInfo() == null) {
                this.f.showAppDetailPage(this.b);
                return;
            } else {
                ho.a("NativeProxy", pPSNativeView.getClickInfo().toString());
                this.f.a(this.b, pPSNativeView.getClickInfo());
                return;
            }
        }
        if (i == 1) {
            ho.b("NativeProxy", " hiadsdk's AIDL: onAdClickSixElementsBtn: PRIVACY");
            this.f.getAppInfo().showPrivacyPolicyInWeb(this.b);
        } else {
            if (i != 2) {
                return;
            }
            ho.b("NativeProxy", " hiadsdk's AIDL: onAdClickSixElementsBtn: PERMISSION");
            if (!TextUtils.isEmpty(this.f.getAppInfo().getPermissionUrl())) {
                this.f.getAppInfo().showPermissionPageInWeb(this.b);
                return;
            }
            PPSNativeView pPSNativeView2 = this.c.get();
            if (pPSNativeView2 != null) {
                com.huawei.openalliance.ad.download.app.h.a(pPSNativeView2.getContext(), this.f.getAppInfo());
            }
        }
    }

    @Override // com.huawei.hms.ads.uiengine.c
    public void a() {
        PPSNativeView pPSNativeView = this.c.get();
        if (pPSNativeView != null) {
            pPSNativeView.g();
        }
    }

    private boolean h() {
        return e.a() != null && Integer.parseInt(e.a()) >= 30468100;
    }

    private void c(IObjectWrapper iObjectWrapper, Bundle bundle) {
        if (iObjectWrapper == null || bundle == null) {
            return;
        }
        ho.b("NativeProxy", "update btn style");
        RemoteButtonStyleAttr b = b(bundle);
        if (b == null) {
            ho.c("NativeProxy", "attr null");
        } else {
            a(iObjectWrapper, 3, b);
        }
    }

    private void c(Bundle bundle) {
        ContentRecord contentRecord = this.d;
        if (contentRecord == null || this.e == null) {
            ho.b("NativeProxy", "contentRecord or eventProcessor is null");
            return;
        }
        if (bundle != null) {
            try {
                if (contentRecord.j() != null && this.d.j().equals(this.h)) {
                    ho.b("NativeProxy", "Duplicate escalation videoTime event for %s", this.d.j());
                } else {
                    this.e.a(bundle.getLong(ParamConstants.Param.VIDEO_PLAY_TIME));
                    this.h = this.d.j();
                }
            } catch (Throwable th) {
                ho.c("NativeProxy", "reportPlayTime err: %s", th.getClass().getSimpleName());
            }
        }
    }

    private void b(IObjectWrapper iObjectWrapper, Bundle bundle) {
        String str;
        if (iObjectWrapper == null || bundle == null) {
            return;
        }
        ho.b("NativeProxy", "update btn text");
        RemoteButtonStyleAttr b = b(bundle);
        if (b == null) {
            str = "attr null";
        } else {
            AppDownloadButton b2 = b(iObjectWrapper);
            if (b2 != null) {
                a(b, b2);
                return;
            }
            str = "btn null";
        }
        ho.c("NativeProxy", str);
    }

    private AppDownloadButton b(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return null;
        }
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        if (!(view instanceof AppDownloadButton)) {
            return null;
        }
        AppDownloadButton appDownloadButton = (AppDownloadButton) view;
        if (this.c.get() == null) {
            return null;
        }
        return appDownloadButton;
    }

    private RemoteButtonStyleAttr b(Bundle bundle) {
        if (new gk(bundle).b(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE) != 3) {
            return null;
        }
        return br.a(bundle);
    }

    private boolean a(boolean z, boolean z2) {
        if (z) {
            return z2;
        }
        int interActionType = this.f.getInterActionType();
        return interActionType == 2 || interActionType == 5 || interActionType == 4 || interActionType == 8 || (interActionType == 3 && this.f.getAppInfo() != null);
    }

    private void a(boolean z, RemoteButtonStyleAttr remoteButtonStyleAttr, AppDownloadButton appDownloadButton, String str) {
        int interActionType = this.f.getInterActionType();
        if (remoteButtonStyleAttr == null) {
            if (interActionType == 2 || interActionType == 5) {
                return;
            }
            appDownloadButton.setAfDlBtnText(str);
            return;
        }
        if (z) {
            a(remoteButtonStyleAttr, appDownloadButton);
            return;
        }
        if (!TextUtils.isEmpty(remoteButtonStyleAttr.A())) {
            appDownloadButton.setBfDlBtnTxt(remoteButtonStyleAttr.A());
        }
        if (interActionType != 8 && !TextUtils.isEmpty(remoteButtonStyleAttr.B())) {
            appDownloadButton.setAfDlBtnText(remoteButtonStyleAttr.B());
        } else {
            if (interActionType == 2 || interActionType == 5) {
                return;
            }
            appDownloadButton.setAfDlBtnText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("app_status_method", str);
        if (str2 != null) {
            bundle.putString("app_status", str2);
        }
        a("notifyAppStatus", bundle);
    }

    private void a(AppDownloadButton appDownloadButton) {
        appDownloadButton.setOnDownloadStatusChangedListener(new AppDownloadButton.OnDownloadStatusChangedListener() { // from class: com.huawei.openalliance.ad.bu.3
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onUserCancel(AppInfo appInfo) {
                bu.this.a(StatusChangeMethods.CANCEL_DOWN, (String) null);
            }

            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onStatusChanged(AppStatus appStatus) {
                ho.a("NativeProxy", "onStatusChanged: %s", appStatus);
                if (appStatus == null) {
                    appStatus = AppStatus.DOWNLOAD;
                }
                bu.this.a(StatusChangeMethods.STATUS_CHANGE, appStatus.name());
            }
        });
    }

    private void a(RemoteButtonStyleAttr remoteButtonStyleAttr, AppDownloadButton appDownloadButton) {
        if (remoteButtonStyleAttr == null) {
            ho.c("NativeProxy", "reset err, attr is null");
            return;
        }
        if (!TextUtils.isEmpty(remoteButtonStyleAttr.A())) {
            appDownloadButton.setBfDlBtnTxt(remoteButtonStyleAttr.A());
        }
        if (!TextUtils.isEmpty(remoteButtonStyleAttr.B())) {
            appDownloadButton.setAfDlBtnText(remoteButtonStyleAttr.B());
        }
        if (!TextUtils.isEmpty(remoteButtonStyleAttr.C())) {
            appDownloadButton.setRemoteBfDlBtnTxt(remoteButtonStyleAttr.C());
        }
        if (TextUtils.isEmpty(remoteButtonStyleAttr.D())) {
            return;
        }
        appDownloadButton.setRemoteAfDlBtnText(remoteButtonStyleAttr.D());
    }

    private void a(IObjectWrapper iObjectWrapper, String str, int i, final RemoteButtonStyleAttr remoteButtonStyleAttr) {
        if (iObjectWrapper != null) {
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            if (view instanceof AppDownloadButton) {
                ho.b("NativeProxy", "registerDownloadBtn");
                final AppDownloadButton appDownloadButton = (AppDownloadButton) view;
                final PPSNativeView pPSNativeView = this.c.get();
                boolean h = h();
                if (pPSNativeView != null) {
                    if (a(h, remoteButtonStyleAttr != null && remoteButtonStyleAttr.F())) {
                        if (pPSNativeView.register(appDownloadButton)) {
                            ho.a("NativeProxy", "register succ");
                            a(h, remoteButtonStyleAttr, appDownloadButton, str);
                            appDownloadButton.setNeedAppendProgress(true);
                            br.b(this.b, appDownloadButton, i, remoteButtonStyleAttr);
                        } else {
                            view.setVisibility(8);
                        }
                        a(appDownloadButton);
                        return;
                    }
                    if (this.f.getInterActionType() == 0) {
                        view.setVisibility(8);
                        return;
                    }
                    ho.a("NativeProxy", "show btn");
                    appDownloadButton.a(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.bu.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            PPSNativeView pPSNativeView2 = pPSNativeView;
                            if (pPSNativeView2 != null) {
                                pPSNativeView2.a(appDownloadButton, 1);
                            }
                            ViewClickInstrumentation.clickOnView(view2);
                        }
                    });
                    final String a2 = a(h, str, remoteButtonStyleAttr);
                    br.b(this.b, appDownloadButton, i, remoteButtonStyleAttr);
                    appDownloadButton.setButtonTextWatcherInner(new AppDownloadButton.e() { // from class: com.huawei.openalliance.ad.bu.2
                        @Override // com.huawei.openalliance.ad.views.AppDownloadButton.e
                        public CharSequence a(CharSequence charSequence, AppStatus appStatus) {
                            return bu.this.a(remoteButtonStyleAttr != null ? !TextUtils.isEmpty(r2.G()) : false, a2);
                        }
                    });
                }
            }
        }
    }

    private void a(IObjectWrapper iObjectWrapper, int i, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        AppDownloadButton b = b(iObjectWrapper);
        if (b == null) {
            ho.c("NativeProxy", "btn null");
        } else {
            ho.b("NativeProxy", "update btn style start");
            br.a(this.b, b, i, remoteButtonStyleAttr);
        }
    }

    private void a(Bundle bundle, PPSNativeView pPSNativeView, int i, String str) {
        MetaData h;
        if (this.f.aa() == null || this.f.aa().intValue() != 3 || (h = this.d.h()) == null) {
            return;
        }
        try {
            if (ho.a()) {
                ho.a("NativeProxy", "video_info: %s, preview_image_info: %s", bundle.getString(MapKeyNames.VIDEO_INFO), bundle.getString(MapKeyNames.PREVIEW_IMAGE_INFO));
            }
            ImageInfo imageInfo = (ImageInfo) com.huawei.openalliance.ad.utils.be.b(bundle.getString(MapKeyNames.PREVIEW_IMAGE_INFO), ImageInfo.class, new Class[0]);
            if (imageInfo != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(imageInfo);
                h.b(arrayList);
            }
            VideoInfo videoInfo = (VideoInfo) com.huawei.openalliance.ad.utils.be.b(bundle.getString(MapKeyNames.VIDEO_INFO), VideoInfo.class, new Class[0]);
            if (videoInfo != null) {
                h.a(videoInfo);
            }
            com.huawei.openalliance.ad.inter.data.VideoInfo videoInfo2 = new com.huawei.openalliance.ad.inter.data.VideoInfo(videoInfo);
            pPSNativeView.setMetaData(com.huawei.openalliance.ad.utils.be.b(h));
            a(i, str, videoInfo2);
            pPSNativeView.setVideoInfo(videoInfo2);
        } catch (Throwable th) {
            ho.c("NativeProxy", "resetMetaDataAndProgress ex: %s", th.getClass().getSimpleName());
        }
    }

    private void a(int i, String str, com.huawei.openalliance.ad.inter.data.VideoInfo videoInfo) {
        if (videoInfo != null) {
            videoInfo.g(Boolean.TRUE.toString());
            videoInfo.e(i);
            videoInfo.e(str);
        }
    }

    private String a(boolean z, String str, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        if (remoteButtonStyleAttr == null) {
            return str;
        }
        if (z) {
            return remoteButtonStyleAttr.E();
        }
        int interActionType = this.f.getInterActionType();
        return ((interActionType == 1 || interActionType == 3) && !TextUtils.isEmpty(remoteButtonStyleAttr.E())) ? remoteButtonStyleAttr.E() : str;
    }

    static class a implements AppDownloadListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<bu> f6657a;

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(String str) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onDownloadProgress(AppInfo appInfo, int i) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onStatusChanged(AppStatus appStatus, AppInfo appInfo) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onUserCancel(AppInfo appInfo) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(AppInfo appInfo) {
            bu buVar = this.f6657a.get();
            if (buVar == null) {
                return;
            }
            buVar.a(StatusChangeMethods.APP_OPEN, (String) null);
        }

        public a(bu buVar) {
            this.f6657a = new WeakReference<>(buVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(boolean z, String str) {
        return z ? str : com.huawei.openalliance.ad.utils.j.a(str, this.b.getString(R.string._2130851066_res_0x7f0234fa));
    }

    public bu(Context context, PPSNativeView pPSNativeView, com.huawei.openalliance.ad.inter.data.e eVar) {
        this.b = context;
        this.c = new WeakReference<>(pPSNativeView);
        this.f = eVar;
        this.d = pd.a(eVar);
        this.e = new ou(context, new sf(context, eVar == null ? 3 : eVar.e()), this.d);
        if (eVar == null || eVar.getAppInfo() == null) {
            return;
        }
        this.i = new a(this);
        com.huawei.openalliance.ad.download.app.d.a().b(this.i);
    }
}
