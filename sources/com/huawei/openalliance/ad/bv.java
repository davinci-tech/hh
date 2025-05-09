package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.template.downloadbuttonstyle.RemoteButtonStyleAttr;
import com.huawei.hms.ads.uiengine.IPPSUiEngineCallback;
import com.huawei.hms.ads.uiengine.d;
import com.huawei.openalliance.ad.constant.InterstitialKeys;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.RewardKeys;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.constant.StatusChangeMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class bv extends d.a {

    /* renamed from: a, reason: collision with root package name */
    private a f6658a;
    private Context b;
    private ContentRecord c;
    private com.huawei.openalliance.ad.inter.data.h d;
    private ou e;
    private WeakReference<com.huawei.openalliance.ad.views.interfaces.j> f;
    private IPPSUiEngineCallback g;
    private MaterialClickInfo h;
    private String i;

    @Override // com.huawei.hms.ads.uiengine.d
    public Bundle b(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        return null;
    }

    public void f(String str, Bundle bundle) {
        if (this.g == null) {
            ho.c("RewardProxy", "on call back, call back is null");
            return;
        }
        ho.b("RewardProxy", "onCallback, method: %s", str);
        try {
            this.g.onCallResult(str, bundle);
        } catch (Throwable th) {
            ho.c("RewardProxy", "onCallback ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void e(String str, Bundle bundle) {
        Integer h = com.huawei.openalliance.ad.utils.cz.h(str);
        if (h == null) {
            ho.c("RewardProxy", "invalid status, skip handle.");
            return;
        }
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar == null) {
            ho.c("RewardProxy", "rewardView is null, skip status handle.");
        } else {
            jVar.a(h.intValue(), bundle);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void d(String str, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.b("RewardProxy", "showDialog, type: %s", str);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1777593019) {
            if (str.equals(RewardMethods.SHOW_CLOSE_CONFIRM)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -789771623) {
            if (hashCode == 672362587 && str.equals(RewardMethods.SHOW_DOWNLOAD_CONFIRM)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("showNonWifiPlay")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            h(bundle);
            return;
        }
        if (c == 1) {
            e();
        } else if (c != 2) {
            ho.b("RewardProxy", "showDialog, fall to default.");
        } else {
            f();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void c(String str, Bundle bundle) {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar != null) {
            jVar.a(str, (RewardItem) com.huawei.openalliance.ad.utils.be.b(new gk(bundle).d(RewardKeys.REWARD_ITEM), RewardItem.class, new Class[0]));
        }
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public boolean b(Bundle bundle) {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar == null || bundle == null) {
            return false;
        }
        gk gkVar = new gk(bundle);
        return jVar.a(gkVar.a("click_source", 7), (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(gkVar.d("click_info"), MaterialClickInfo.class, new Class[0]));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.d
    public void b(String str, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str)) {
        }
        ho.b("RewardProxy", "reportEvent, type: %s", str);
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
            case -1884864635:
                if (str.equals(InterstitialKeys.RPT_SHOW_START)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1322926856:
                if (str.equals("onSoundClick")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -988777845:
                if (str.equals(InterstitialKeys.RPT_PHYIMP)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -493598457:
                if (str.equals("playEnd")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 104396:
                if (str.equals("imp")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 94750088:
                if (str.equals("click")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1540819073:
                if (str.equals(InterstitialKeys.RPT_PLAY_RESUME)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1878759457:
                if (str.equals("playTime")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 5:
                g(str, bundle);
                break;
            case 1:
                this.e.c();
                break;
            case 2:
                d();
                break;
            case 3:
                f(bundle);
                break;
            case 4:
                d(bundle);
                break;
            case 6:
                g(bundle);
                break;
            case 7:
                e(bundle);
                break;
            case '\b':
                this.e.f();
                break;
            case '\t':
                c(bundle);
                break;
            default:
                ho.b("RewardProxy", "reportEvent, fall to default");
                break;
        }
    }

    public void b() {
        com.huawei.openalliance.ad.download.app.d.a().c(this.f6658a);
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public boolean a() {
        return com.huawei.openalliance.ad.utils.ao.a(this.b, this.c);
    }

    public void a(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(RewardKeys.DIALOG_TYPE, str);
        bundle.putBoolean(RewardKeys.IS_DISPLAYING, z);
        bundle.putString(RewardKeys.DIALOG_CLICK_TYPE, str2);
        try {
            f(RewardMethods.NOTIFY_DIALOG_STATUS, bundle);
        } catch (Throwable th) {
            ho.c("RewardProxy", "notify dialog status ex: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.d
    public void a(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.b("RewardProxy", "callMethod: %s", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -881549925:
                if (str.equals("reportCommonEvent")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -406271339:
                if (str.equals("showAppDesc")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 73660875:
                if (str.equals("showPrivacy")) {
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
            case 1076470956:
                if (str.equals("showPermission")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1400016483:
                if (str.equals(RewardMethods.UPDATE_BTN_DATA)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            new bp(this.b).a(bundle, this.c, this.e);
            return;
        }
        if (c != 1 && c != 2) {
            if (c == 3) {
                b(iObjectWrapper, bundle);
                return;
            } else if (c != 4) {
                if (c != 5) {
                    ho.b("RewardProxy", "call method fall to default.");
                    return;
                } else {
                    a(iObjectWrapper, bundle);
                    return;
                }
            }
        }
        a(str);
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void a(String str, Bundle bundle) {
        new bo(this.b).a(str, bundle, this.c, this.e);
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void a(IPPSUiEngineCallback iPPSUiEngineCallback) {
        this.g = iPPSUiEngineCallback;
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void a(IObjectWrapper iObjectWrapper, final String str, Bundle bundle) {
        if (iObjectWrapper == null || bundle == null) {
            return;
        }
        gk gkVar = new gk(bundle);
        int b = gkVar.b(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE);
        boolean a2 = gkVar.a(ParamConstants.BtnParams.APP_RELATED, false);
        String a3 = gkVar.a("download_text", (String) null);
        String a4 = gkVar.a("installed_text", (String) null);
        final int a5 = gkVar.a("btn_source", 5);
        RemoteButtonStyleAttr a6 = 4 == b ? br.a(bundle) : null;
        if (a6 == null) {
            b = 2;
        }
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        if (view instanceof AppDownloadButton) {
            if (this.c.B() == 0) {
                view.setVisibility(8);
                return;
            }
            final com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
            if (jVar == null) {
                ho.c("RewardProxy", "regBtn, rewardView ref is null.");
                return;
            }
            ho.b("RewardProxy", "registerDownloadBtn");
            final AppDownloadButton appDownloadButton = (AppDownloadButton) view;
            br.b(this.b, appDownloadButton, b, a6);
            if (a2) {
                ho.b("RewardProxy", "register btn");
                appDownloadButton.setAdLandingPageData(new AdLandingPageData(this.c, this.b, true));
                if (!TextUtils.isEmpty(a4)) {
                    appDownloadButton.setAfDlBtnText(a4);
                }
                if (!TextUtils.isEmpty(a3)) {
                    appDownloadButton.setDlBtnText(a3);
                }
                a(appDownloadButton, jVar);
            } else {
                ho.a("RewardProxy", "show btn");
                appDownloadButton.a(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.bv.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        bv.this.a(appDownloadButton, a5, false);
                        jVar.a(2, (Bundle) null);
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                appDownloadButton.setButtonTextWatcherInner(new AppDownloadButton.e() { // from class: com.huawei.openalliance.ad.bv.2
                    @Override // com.huawei.openalliance.ad.views.AppDownloadButton.e
                    public CharSequence a(CharSequence charSequence, AppStatus appStatus) {
                        return com.huawei.openalliance.ad.utils.j.a(str, bv.this.b.getString(R.string._2130851066_res_0x7f0234fa));
                    }
                });
            }
            appDownloadButton.setSource(a5);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.d
    public void a(Bundle bundle) {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar != null) {
            jVar.j();
        }
    }

    public void a(long j) {
        ContentRecord contentRecord;
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar != null) {
            hVar.c(j);
            this.d.h(String.valueOf(j));
        }
        if (this.e == null || (contentRecord = this.c) == null) {
            return;
        }
        contentRecord.c(String.valueOf(j));
        this.c.f(j);
        this.e.a(this.c);
    }

    private void h(Bundle bundle) {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        int a2 = bundle != null ? new gk(bundle).a(RewardKeys.RWD_GN_TIME, -1) : -1;
        if (jVar == null) {
            ho.c("RewardProxy", "handle close confirm, view is null.");
        } else {
            jVar.a(a2);
            a(RewardMethods.SHOW_CLOSE_CONFIRM, true, (String) null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[Catch: all -> 0x0066, TRY_LEAVE, TryCatch #0 {all -> 0x0066, blocks: (B:6:0x000a, B:14:0x0054, B:17:0x005a, B:19:0x0060, B:21:0x003b, B:24:0x0045), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g(java.lang.String r10, android.os.Bundle r11) {
        /*
            r9 = this;
            java.lang.String r0 = "RewardProxy"
            if (r11 == 0) goto L78
            com.huawei.openalliance.ad.ou r1 = r9.e
            if (r1 != 0) goto La
            goto L78
        La:
            com.huawei.openalliance.ad.gk r1 = new com.huawei.openalliance.ad.gk     // Catch: java.lang.Throwable -> L66
            r1.<init>(r11)     // Catch: java.lang.Throwable -> L66
            java.lang.String r11 = "video_start_ts"
            long r3 = r1.c(r11)     // Catch: java.lang.Throwable -> L66
            java.lang.String r11 = "video_end_ts"
            long r5 = r1.c(r11)     // Catch: java.lang.Throwable -> L66
            java.lang.String r11 = "video_start_time"
            int r7 = r1.b(r11)     // Catch: java.lang.Throwable -> L66
            java.lang.String r11 = "video_end_time"
            int r8 = r1.b(r11)     // Catch: java.lang.Throwable -> L66
            int r11 = r10.hashCode()     // Catch: java.lang.Throwable -> L66
            r1 = -1891923166(0xffffffff8f3b8b22, float:-9.246608E-30)
            r2 = 1
            if (r11 == r1) goto L45
            r1 = -493598457(0xffffffffe2944907, float:-1.3676901E21)
            if (r11 == r1) goto L3b
            goto L4f
        L3b:
            java.lang.String r11 = "playEnd"
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> L66
            if (r10 == 0) goto L4f
            r10 = 0
            goto L50
        L45:
            java.lang.String r11 = "playPause"
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> L66
            if (r10 == 0) goto L4f
            r10 = r2
            goto L50
        L4f:
            r10 = -1
        L50:
            if (r10 == 0) goto L60
            if (r10 == r2) goto L5a
            java.lang.String r10 = "report end or pause fall to default."
            com.huawei.openalliance.ad.ho.b(r0, r10)     // Catch: java.lang.Throwable -> L66
            goto L78
        L5a:
            com.huawei.openalliance.ad.ou r2 = r9.e     // Catch: java.lang.Throwable -> L66
            r2.b(r3, r5, r7, r8)     // Catch: java.lang.Throwable -> L66
            goto L78
        L60:
            com.huawei.openalliance.ad.ou r2 = r9.e     // Catch: java.lang.Throwable -> L66
            r2.c(r3, r5, r7, r8)     // Catch: java.lang.Throwable -> L66
            goto L78
        L66:
            r10 = move-exception
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getSimpleName()
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            java.lang.String r11 = "reportPauseOrEnd err: %s"
            com.huawei.openalliance.ad.ho.c(r0, r11, r10)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.bv.g(java.lang.String, android.os.Bundle):void");
    }

    private void g(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        gk gkVar = new gk(bundle);
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(gkVar.c("show_duration"))).a(Integer.valueOf(gkVar.b("show_ratio"))).e(gkVar.d(MapKeyNames.CREATIVE_SIZE)).d(gkVar.d("show_position")).c(gkVar.a("isInteractiveImp"));
        int b = gkVar.b("imp_source");
        if (-1 != b) {
            c0207a.b(Integer.valueOf(b));
        }
        this.e.a(c0207a.a());
    }

    private void f(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.e.b(new gk(bundle).a("is_mute"));
    }

    private void f() {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar != null) {
            a(RewardMethods.SHOW_DOWNLOAD_CONFIRM, jVar.c(false), (String) null);
        } else {
            ho.c("RewardProxy", "handle download confirm, view is null");
        }
    }

    private void e(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        gk gkVar = new gk(bundle);
        int b = gkVar.b("click_source");
        String d = gkVar.d("click_destination");
        String d2 = gkVar.d("click_info");
        ho.b("RewardProxy", "reportClickEvent.");
        if (ho.a()) {
            ho.a("RewardProxy", "materiaClickInfo: %s", d2);
        }
        b.a aVar = new b.a();
        aVar.b(d).a(Integer.valueOf(b)).a((MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(d2, MaterialClickInfo.class, new Class[0]));
        this.e.a(aVar.a());
    }

    private void e() {
        com.huawei.openalliance.ad.views.interfaces.j jVar = this.f.get();
        if (jVar == null) {
            ho.c("RewardProxy", "handle show nonWifi, view is null.");
        } else {
            jVar.i();
            a("showNonWifiPlay", true, (String) null);
        }
    }

    private void d(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        gk gkVar = new gk(bundle);
        long c = gkVar.c("show_duration");
        int b = gkVar.b("show_ratio");
        int b2 = gkVar.b("video_played_time");
        int b3 = gkVar.b("video_progress");
        ot otVar = new ot();
        otVar.b(Integer.valueOf(b2));
        otVar.a(Integer.valueOf(b3));
        this.e.a(c, b, otVar);
    }

    private void d() {
        this.e.b();
    }

    private void c(AppDownloadButton appDownloadButton, Bundle bundle) {
        String str;
        if (appDownloadButton == null || bundle == null) {
            return;
        }
        ho.a("RewardProxy", "update btn clickInfo start");
        String d = new gk(bundle).d("click_info");
        if (com.huawei.openalliance.ad.utils.cz.b(d)) {
            str = "update btn clickInfo failed:  clickInfoStr is empty";
        } else {
            MaterialClickInfo materialClickInfo = (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(d, MaterialClickInfo.class, new Class[0]);
            if (materialClickInfo != null) {
                this.h = materialClickInfo;
                appDownloadButton.a(materialClickInfo);
                return;
            }
            str = "update btn clickInfo failed:  clickInfo Object is null";
        }
        ho.c("RewardProxy", str);
    }

    private void c(Bundle bundle) {
        ContentRecord contentRecord = this.c;
        if (contentRecord == null || this.e == null) {
            ho.b("RewardProxy", "contentRecord or eventProcessor is null");
            return;
        }
        if (bundle != null) {
            try {
                if (contentRecord.j() != null && this.c.j().equals(this.i)) {
                    ho.b("RewardProxy", "Duplicate escalation videoTime event for %s", this.c.j());
                } else {
                    this.e.a(bundle.getLong(ParamConstants.Param.VIDEO_PLAY_TIME));
                    this.i = this.c.j();
                }
            } catch (Throwable th) {
                ho.c("RewardProxy", "reportPlayTime err: %s", th.getClass().getSimpleName());
            }
        }
    }

    private void b(AppDownloadButton appDownloadButton, Bundle bundle) {
        if (appDownloadButton == null || bundle == null) {
            return;
        }
        ho.a("RewardProxy", "update btn content start");
        Long valueOf = Long.valueOf(new gk(bundle).c(RewardKeys.SHOW_TIME));
        if (valueOf.longValue() == 0) {
            ho.c("RewardProxy", "update btn content failed: showTime is empty");
        } else {
            appDownloadButton.updateStartShowTime(valueOf.longValue());
            appDownloadButton.updateContent(String.valueOf(valueOf));
        }
    }

    private void b(IObjectWrapper iObjectWrapper, Bundle bundle) {
        if (iObjectWrapper == null || bundle == null) {
            return;
        }
        ho.b("RewardProxy", "update btn style");
        int b = new gk(bundle).b(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE);
        if (4 == b) {
            RemoteButtonStyleAttr a2 = br.a(bundle);
            if (a2 == null) {
                ho.c("RewardProxy", "attr null");
            } else {
                a(iObjectWrapper, b, a2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("app_status_method", str);
        if (str2 != null) {
            bundle.putString("app_status", str2);
        }
        f("notifyAppStatus", bundle);
    }

    private void a(String str) {
        com.huawei.openalliance.ad.inter.data.h hVar;
        AppInfo appInfo;
        char c;
        if (TextUtils.isEmpty(str) || (hVar = this.d) == null || (appInfo = hVar.getAppInfo()) == null) {
            return;
        }
        ho.b("RewardProxy", "handleAppElementClick, type: %s", str);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -406271339) {
            if (str.equals("showAppDesc")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 73660875) {
            if (hashCode == 1076470956 && str.equals("showPermission")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("showPrivacy")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            MaterialClickInfo materialClickInfo = this.h;
            if (materialClickInfo == null || !com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c()) || this.h.a() == null) {
                this.d.showAppDetailPage(this.b);
                return;
            } else {
                this.d.a(this.b, this.h);
                return;
            }
        }
        if (c == 1) {
            appInfo.showPrivacyPolicy(this.b);
            return;
        }
        if (c != 2) {
            ho.b("RewardProxy", "handle app element click, fall to default");
        } else if (TextUtils.isEmpty(appInfo.getPermissionUrl())) {
            com.huawei.openalliance.ad.download.app.h.a(this.b, appInfo);
        } else {
            appInfo.showPermissionPage(this.b);
        }
    }

    private void a(AppDownloadButton appDownloadButton, final com.huawei.openalliance.ad.views.interfaces.j jVar) {
        appDownloadButton.setClickActionListener(new com.huawei.openalliance.ad.views.interfaces.x() { // from class: com.huawei.openalliance.ad.bv.3
            @Override // com.huawei.openalliance.ad.views.interfaces.x
            public void b(AppDownloadButton appDownloadButton2) {
                bv.this.a(appDownloadButton2, appDownloadButton2.getSource(), true);
            }

            @Override // com.huawei.openalliance.ad.views.interfaces.x
            public void a(AppDownloadButton appDownloadButton2) {
                bv.this.a(appDownloadButton2, appDownloadButton2.getSource(), false);
            }
        });
        appDownloadButton.a(new AppDownloadButton.b() { // from class: com.huawei.openalliance.ad.bv.4
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.b
            public void a() {
                jVar.a(2, (Bundle) null);
            }
        });
        appDownloadButton.setOnDownloadStatusChangedListener(new AppDownloadButton.OnDownloadStatusChangedListener() { // from class: com.huawei.openalliance.ad.bv.5
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onUserCancel(AppInfo appInfo) {
                bv.this.a(StatusChangeMethods.CANCEL_DOWN, (String) null);
            }

            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onStatusChanged(AppStatus appStatus) {
                ho.a("RewardProxy", "onStatusChanged:%s", appStatus);
                bv.this.a(StatusChangeMethods.STATUS_CHANGE, appStatus.name());
            }
        });
    }

    private void a(AppDownloadButton appDownloadButton, Bundle bundle) {
        if (ho.a()) {
            ho.a("RewardProxy", "update btn click source");
        }
        gk gkVar = new gk(bundle);
        int a2 = gkVar.a("btn_source", -111111);
        boolean a3 = gkVar.a(RewardKeys.IS_BTN_ONCE_SOURCE, false);
        if (a2 == -111111) {
            ho.b("RewardProxy", "no need update source");
        } else if (a3) {
            appDownloadButton.setOnceSource(a2);
        } else {
            appDownloadButton.setSource(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppDownloadButton appDownloadButton, int i, boolean z) {
        String str = AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : "";
        Bundle bundle = new Bundle();
        bundle.putInt("btn_source", i);
        bundle.putBoolean("is_handled", z);
        bundle.putString("click_destination", str);
        f(RewardMethods.ON_BTN_CLICK, bundle);
    }

    private void a(IObjectWrapper iObjectWrapper, Bundle bundle) {
        ho.b("RewardProxy", "update btn data start");
        if (iObjectWrapper == null || bundle == null) {
            return;
        }
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        if (!(view instanceof AppDownloadButton)) {
            ho.c("RewardProxy", "update btn data failed: btn's type is not AppDownloadButton");
            return;
        }
        AppDownloadButton appDownloadButton = (AppDownloadButton) view;
        b(appDownloadButton, bundle);
        c(appDownloadButton, bundle);
        a(appDownloadButton, bundle);
    }

    static class a implements AppDownloadListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<bv> f6664a;

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
            bv bvVar = this.f6664a.get();
            if (bvVar == null) {
                return;
            }
            bvVar.a(StatusChangeMethods.APP_OPEN, (String) null);
        }

        public a(bv bvVar) {
            this.f6664a = new WeakReference<>(bvVar);
        }
    }

    private void a(IObjectWrapper iObjectWrapper, int i, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        if (iObjectWrapper == null) {
            return;
        }
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        if (view instanceof AppDownloadButton) {
            ho.b("RewardProxy", "update btn start");
            AppDownloadButton appDownloadButton = (AppDownloadButton) view;
            if (this.f.get() == null) {
                return;
            }
            br.a(this.b, appDownloadButton, i, remoteButtonStyleAttr);
        }
    }

    public bv(Context context, com.huawei.openalliance.ad.inter.data.h hVar, com.huawei.openalliance.ad.views.interfaces.j jVar) {
        this.b = context;
        this.d = hVar;
        this.c = pn.a(hVar);
        this.f = new WeakReference<>(jVar);
        Context context2 = this.b;
        ou ouVar = new ou(context2, new sh(context2));
        this.e = ouVar;
        ouVar.a(this.c);
        if (hVar == null || hVar.getAppInfo() == null) {
            return;
        }
        this.f6658a = new a(this);
        com.huawei.openalliance.ad.download.app.d.a().b(this.f6658a);
    }
}
