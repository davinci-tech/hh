package com.huawei.hms.ads.uiengine;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hms.ads.template.downloadbuttonstyle.RemoteButtonStyleAttr;
import com.huawei.hms.ads.uiengine.common.InterstitialApi;
import com.huawei.openalliance.ad.bo;
import com.huawei.openalliance.ad.bp;
import com.huawei.openalliance.ad.br;
import com.huawei.openalliance.ad.constant.InterstitialKeys;
import com.huawei.openalliance.ad.constant.InterstitialMethods;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.download.app.h;
import com.huawei.openalliance.ad.gk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ie;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.iq;
import com.huawei.openalliance.ad.ot;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.sd;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.j;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.interfaces.x;
import com.huawei.openalliance.ad.views.r;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class f implements InterstitialApi {

    /* renamed from: a, reason: collision with root package name */
    private String f4356a = "";
    private Context b;
    private ContentRecord c;
    private ou d;
    private com.huawei.openalliance.ad.inter.data.d e;
    private WeakReference<r> f;
    private AppDownloadButton g;

    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public void updateShowId(long j) {
        if (this.c == null || this.d == null) {
            ho.b("InterstitialProxy", "updateShowId, contentRecord or eventProcessor is null");
            return;
        }
        ho.a("InterstitialProxy", "updateShowId");
        com.huawei.openalliance.ad.inter.data.d dVar = this.e;
        if (dVar != null) {
            dVar.c(j);
            this.e.h(String.valueOf(j));
        }
        this.c.f(j);
        this.c.c(String.valueOf(j));
        this.d.a(this.c);
        AppDownloadButton appDownloadButton = this.g;
        if (appDownloadButton != null) {
            appDownloadButton.updateStartShowTime(j);
            this.g.updateContent(String.valueOf(j));
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public void setIsVideoCompleted(boolean z) {
        r rVar = this.f.get();
        if (rVar != null) {
            rVar.setCompleted(z);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public void reportEvent(String str, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str) || this.d == null) {
            ho.c("InterstitialProxy", "eventType or eventProcessor is null.");
            return;
        }
        ho.b("InterstitialProxy", "reportEvent, type: %s", str);
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
                b(str, bundle);
                break;
            case 1:
                this.d.c();
                break;
            case 2:
                this.d.b();
                break;
            case 3:
                d(bundle);
                break;
            case 4:
                b(bundle);
                break;
            case 6:
                e(bundle);
                break;
            case 7:
                c(bundle);
                break;
            case '\b':
                this.d.f();
                break;
            case '\t':
                a(bundle);
                break;
            default:
                ho.b("InterstitialProxy", "reportEvent, fail to default");
                break;
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public void registerBtn(View view, Bundle bundle) {
        int i;
        final String str;
        if (view == null || bundle == null) {
            ho.c("InterstitialProxy", "btn or param is null.");
            return;
        }
        gk gkVar = new gk(bundle);
        int b = gkVar.b(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE);
        boolean a2 = gkVar.a(ParamConstants.BtnParams.APP_RELATED, false);
        String a3 = gkVar.a("download_text", (String) null);
        String a4 = gkVar.a("installed_text", (String) null);
        String a5 = gkVar.a(InterstitialKeys.BTN_TEXT, "");
        int a6 = gkVar.a("btn_source", 5);
        RemoteButtonStyleAttr a7 = 4 == b ? br.a(bundle) : null;
        if (a7 == null) {
            b = 2;
        }
        if (ho.a()) {
            Integer valueOf = Integer.valueOf(b);
            Boolean valueOf2 = Boolean.valueOf(a2);
            Integer valueOf3 = Integer.valueOf(a6);
            i = a6;
            str = a5;
            ho.a("InterstitialProxy", "btnStyle is %s, isAppRelated is %s, downloadText is %s, installedText is %s, btnText is %s, btnSource is %s.", valueOf, valueOf2, a3, a4, a5, valueOf3);
        } else {
            i = a6;
            str = a5;
        }
        if (!(view instanceof AppDownloadButton)) {
            ho.b("InterstitialProxy", "btn is %s, not AppDownloadButton.", view.getClass().getSimpleName());
            return;
        }
        ContentRecord contentRecord = this.c;
        if (contentRecord != null && contentRecord.B() == 0) {
            view.setVisibility(8);
            return;
        }
        if (this.f.get() == null) {
            ho.c("InterstitialProxy", "register button, interstitial container is null.");
            return;
        }
        ho.b("InterstitialProxy", "registerDownloadBtn");
        AppDownloadButton appDownloadButton = (AppDownloadButton) view;
        this.g = appDownloadButton;
        br.b(this.b, appDownloadButton, b, a7);
        if (a2) {
            ho.b("InterstitialProxy", "register btn");
            this.g.setAdLandingPageData(new AdLandingPageData(this.c, this.b, true));
            if (!TextUtils.isEmpty(a4)) {
                this.g.setAfDlBtnText(a4);
            }
            if (!TextUtils.isEmpty(a3)) {
                this.g.setDlBtnText(a3);
            }
            a(this.g);
        } else {
            ho.a("InterstitialProxy", "show btn.");
            this.g.a(new View.OnClickListener() { // from class: com.huawei.hms.ads.uiengine.f.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    f fVar = f.this;
                    fVar.a(fVar.g, false);
                }
            });
            this.g.setButtonTextWatcherInner(new AppDownloadButton.e() { // from class: com.huawei.hms.ads.uiengine.f.2
                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.e
                public CharSequence a(CharSequence charSequence, AppStatus appStatus) {
                    return j.a(str, f.this.b.getString(R.string._2130851066_res_0x7f0234fa));
                }
            });
        }
        this.g.setSource(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public boolean processWhyEventUnified() {
        return ao.a(this.b, this.c);
    }

    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public void onAnalysis(String str, Bundle bundle) {
        new bo(this.b).a(str, bundle, this.c, this.d);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.common.InterstitialApi
    public Bundle callMethod(String str, Bundle bundle) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ho.b("InterstitialProxy", "callMethod: %s", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -881549925:
                if (str.equals("reportCommonEvent")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -789771623:
                if (str.equals("showNonWifiPlay")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -406271339:
                if (str.equals("showAppDesc")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 73660875:
                if (str.equals("showPrivacy")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 157935686:
                if (str.equals(InterstitialMethods.ON_AD_CLICK)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 465262104:
                if (str.equals("update_btn_style")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 697649859:
                if (str.equals(InterstitialMethods.UPDATE_BTN_CLICK_INFO)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 812611849:
                if (str.equals(InterstitialMethods.QUERY_PROXY_URL)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1076470956:
                if (str.equals("showPermission")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1467422896:
                if (str.equals("handleClose")) {
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
                new bp(this.b).a(bundle, this.c, this.d);
                return null;
            case 1:
                c();
                return null;
            case 2:
            case 3:
            case '\b':
                c(str, bundle);
                return null;
            case 4:
                return a();
            case 5:
                f(bundle);
                return null;
            case 6:
                g(bundle);
                return null;
            case 7:
                return i(bundle);
            case '\t':
                b();
                return null;
            default:
                ho.b("InterstitialProxy", "call method default.");
                return null;
        }
    }

    public void a(String str, Bundle bundle) {
        r rVar = this.f.get();
        if (rVar != null) {
            rVar.onCallBack(str, bundle);
        }
    }

    private Bundle i(Bundle bundle) {
        if (bundle == null) {
            ho.c("InterstitialProxy", "getProxyUrl, param is null");
            return null;
        }
        gk gkVar = new gk(bundle);
        String d = gkVar.d(InterstitialKeys.ORI_URL);
        String d2 = gkVar.d("sha256");
        ho.b("InterstitialProxy", "getProxyUrl, videoUrl is %s, sha256 is %s.", d, d2);
        if (!cz.j(d) || cz.b(d2)) {
            ho.b("InterstitialProxy", "videoUrl or sha256 is invalid.");
            return null;
        }
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.f(d);
        videoInfo.d(d2);
        String a2 = ao.a(this.b, videoInfo, new a(this, videoInfo));
        ho.b("InterstitialProxy", "proxyUrl is %s.", a2);
        gk gkVar2 = new gk();
        gkVar2.b(InterstitialKeys.PROXY_URL, a2);
        return gkVar2.a();
    }

    private void h(Bundle bundle) {
        if (this.f.get() == null || this.e == null) {
            ho.c("InterstitialProxy", "showAppDesc, container or interstitial is null.");
            return;
        }
        MaterialClickInfo materialClickInfo = (MaterialClickInfo) be.b(this.b, new gk(bundle).d("click_info"), MaterialClickInfo.class, new Class[0]);
        if (materialClickInfo == null || !cz.p(materialClickInfo.c()) || materialClickInfo.a() == null) {
            this.e.showAppDetailPage(this.b);
        } else {
            ho.a("InterstitialProxy", "showAppDesc with clickInfo.");
            this.e.a(this.b, materialClickInfo);
        }
    }

    private void g(Bundle bundle) {
        MaterialClickInfo materialClickInfo = (MaterialClickInfo) be.b(this.b, new gk(bundle).d("click_info"), MaterialClickInfo.class, new Class[0]);
        if (ho.a()) {
            ho.a("InterstitialProxy", "updateBtnClickInfo, clickInfo is %s.", materialClickInfo);
        }
        AppDownloadButton appDownloadButton = this.g;
        if (appDownloadButton != null) {
            appDownloadButton.a(materialClickInfo);
        }
    }

    private void f(Bundle bundle) {
        String str;
        if (this.g == null || bundle == null) {
            str = "null btn or param";
        } else {
            gk gkVar = new gk(bundle);
            ho.b("InterstitialProxy", "update btn");
            int b = gkVar.b(ParamConstants.BtnParams.DOWNLOAD_BUTTON_STYLE);
            if (4 != b) {
                return;
            }
            RemoteButtonStyleAttr a2 = br.a(bundle);
            if (a2 != null) {
                br.a(this.b, this.g, b, a2);
                return;
            }
            str = "attr null";
        }
        ho.c("InterstitialProxy", str);
    }

    private void e(Bundle bundle) {
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
        this.d.a(c0207a.a());
    }

    private void d(Bundle bundle) {
        if (bundle == null) {
            ho.c("InterstitialProxy", "reportSoundClick, param is null.");
        } else {
            this.d.b(new gk(bundle).a("is_mute"));
        }
    }

    private void c(String str, Bundle bundle) {
        char c;
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "method is null.";
        } else {
            AppInfo appInfo = this.e.getAppInfo();
            if (appInfo != null) {
                ho.b("InterstitialProxy", "handleAppElementClick, method is %s.", str);
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
                    h(bundle);
                    return;
                }
                if (c == 1) {
                    appInfo.showPrivacyPolicy(this.b);
                    return;
                } else if (c != 2) {
                    ho.b("InterstitialProxy", "handleAppElementClick, fail to default");
                    return;
                } else {
                    a(appInfo);
                    return;
                }
            }
            str2 = "appInfo is null.";
        }
        ho.c("InterstitialProxy", str2);
    }

    private void c(Bundle bundle) {
        if (bundle == null) {
            ho.c("InterstitialProxy", "reportClickEvent, param is null.");
            return;
        }
        gk gkVar = new gk(bundle);
        int b = gkVar.b("click_source");
        String d = gkVar.d("click_destination");
        String d2 = gkVar.d("click_info");
        if (ho.a()) {
            ho.a("InterstitialProxy", "reportClickEvent, clickSource is %s, clickDestination is %s, materialClickInfoStr is %s.", Integer.valueOf(b), d, d2);
        }
        b.a aVar = new b.a();
        aVar.a(Integer.valueOf(b)).b(d).a((MaterialClickInfo) be.b(d2, MaterialClickInfo.class, new Class[0]));
        this.d.a(aVar.a());
    }

    private void c() {
        r rVar = this.f.get();
        if (rVar != null) {
            rVar.pauseView();
            rVar.b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {all -> 0x0062, blocks: (B:6:0x000a, B:14:0x0050, B:17:0x0056, B:19:0x005c, B:21:0x0037, B:24:0x0041), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.lang.String r10, android.os.Bundle r11) {
        /*
            r9 = this;
            java.lang.String r0 = "InterstitialProxy"
            if (r11 == 0) goto L75
            com.huawei.openalliance.ad.ou r1 = r9.d
            if (r1 != 0) goto La
            goto L75
        La:
            com.huawei.openalliance.ad.gk r1 = new com.huawei.openalliance.ad.gk     // Catch: java.lang.Throwable -> L62
            r1.<init>(r11)     // Catch: java.lang.Throwable -> L62
            java.lang.String r11 = "video_start_ts"
            long r3 = r1.c(r11)     // Catch: java.lang.Throwable -> L62
            java.lang.String r11 = "video_end_ts"
            long r5 = r1.c(r11)     // Catch: java.lang.Throwable -> L62
            java.lang.String r11 = "video_start_time"
            int r7 = r1.b(r11)     // Catch: java.lang.Throwable -> L62
            java.lang.String r11 = "video_end_time"
            int r8 = r1.b(r11)     // Catch: java.lang.Throwable -> L62
            int r11 = r10.hashCode()     // Catch: java.lang.Throwable -> L62
            r1 = -1891923166(0xffffffff8f3b8b22, float:-9.246608E-30)
            r2 = 1
            if (r11 == r1) goto L41
            r1 = -493598457(0xffffffffe2944907, float:-1.3676901E21)
            if (r11 == r1) goto L37
            goto L4b
        L37:
            java.lang.String r11 = "playEnd"
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> L62
            if (r10 == 0) goto L4b
            r10 = 0
            goto L4c
        L41:
            java.lang.String r11 = "playPause"
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> L62
            if (r10 == 0) goto L4b
            r10 = r2
            goto L4c
        L4b:
            r10 = -1
        L4c:
            if (r10 == 0) goto L5c
            if (r10 == r2) goto L56
            java.lang.String r10 = "report end or pause fall to default."
            com.huawei.openalliance.ad.ho.b(r0, r10)     // Catch: java.lang.Throwable -> L62
            goto L74
        L56:
            com.huawei.openalliance.ad.ou r2 = r9.d     // Catch: java.lang.Throwable -> L62
            r2.b(r3, r5, r7, r8)     // Catch: java.lang.Throwable -> L62
            goto L74
        L5c:
            com.huawei.openalliance.ad.ou r2 = r9.d     // Catch: java.lang.Throwable -> L62
            r2.c(r3, r5, r7, r8)     // Catch: java.lang.Throwable -> L62
            goto L74
        L62:
            r10 = move-exception
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getSimpleName()
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            java.lang.String r11 = "report pause or end err: %s"
            com.huawei.openalliance.ad.ho.c(r0, r11, r10)
        L74:
            return
        L75:
            java.lang.String r10 = "param or process is null, can't report pause or end"
            com.huawei.openalliance.ad.ho.c(r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengine.f.b(java.lang.String, android.os.Bundle):void");
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            ho.c("InterstitialProxy", "reportPhyShow, param is null.");
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
        this.d.a(c, b, otVar);
    }

    private void b() {
        r rVar = this.f.get();
        if (rVar == null) {
            ho.c("InterstitialProxy", "handleClose, container is null.");
        } else {
            rVar.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppDownloadButton appDownloadButton, boolean z) {
        r rVar = this.f.get();
        if (rVar == null) {
            ho.c("InterstitialProxy", "handleBtnClick, container is null.");
            return;
        }
        String str = AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : "";
        gk gkVar = new gk();
        gkVar.b(InterstitialKeys.IS_BTN_HANDLED, z);
        if (!z) {
            str = new gk(rVar.h()).d("click_destination");
        }
        if (cz.b(str)) {
            rVar.a((Bundle) null);
        } else {
            gkVar.b("click_destination", str);
            rVar.a(gkVar.a());
        }
    }

    private void a(AppDownloadButton appDownloadButton) {
        appDownloadButton.setClickActionListener(new x() { // from class: com.huawei.hms.ads.uiengine.f.3
            @Override // com.huawei.openalliance.ad.views.interfaces.x
            public void b(AppDownloadButton appDownloadButton2) {
                ho.b("InterstitialProxy", "DownloadButton onClickActionValid.");
                f.this.a(appDownloadButton2, true);
            }

            @Override // com.huawei.openalliance.ad.views.interfaces.x
            public void a(AppDownloadButton appDownloadButton2) {
                f.this.a(appDownloadButton2, false);
            }
        });
        appDownloadButton.a(new AppDownloadButton.b() { // from class: com.huawei.hms.ads.uiengine.f.4
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.b
            public void a() {
                r rVar = (r) f.this.f.get();
                if (rVar != null) {
                    rVar.a(2, (Bundle) null);
                }
            }
        });
        appDownloadButton.setOnDownloadStatusChangedListener(new AppDownloadButton.OnDownloadStatusChangedListener() { // from class: com.huawei.hms.ads.uiengine.f.5
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onUserCancel(AppInfo appInfo) {
            }

            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onStatusChanged(AppStatus appStatus) {
                if (appStatus == null || f.this.f.get() == null) {
                    return;
                }
                ho.a("InterstitialProxy", "onStatusChanged, status:%s", appStatus.toString());
                ((r) f.this.f.get()).onAppStatusChanged(appStatus.toString());
            }
        });
    }

    private void a(AppInfo appInfo) {
        if (TextUtils.isEmpty(appInfo.getPermissionUrl())) {
            h.a(this.b, appInfo);
        } else {
            appInfo.showPermissionPage(this.b);
        }
    }

    private void a(Bundle bundle) {
        if (bundle == null || this.c == null) {
            ho.c("InterstitialProxy", "param or contentRecord is null.");
            return;
        }
        gk gkVar = new gk(bundle);
        String str = this.f4356a;
        if (str != null && str.equals(this.c.j())) {
            ho.b("InterstitialProxy", "Duplicate escalation videoTime event for %s", this.c.j());
            return;
        }
        this.d.a(gkVar.c(ParamConstants.Param.VIDEO_PLAY_TIME));
        this.f4356a = this.c.j();
    }

    /* loaded from: classes9.dex */
    static class a implements iq {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<f> f4362a;
        private VideoInfo b;

        @Override // com.huawei.openalliance.ad.iq
        public void a(int i) {
            ho.c("InterstitialProxy", "InterstitialStreamListener onError, code is %s.", Integer.valueOf(i));
            f fVar = this.f4362a.get();
            if (fVar == null) {
                ho.c("InterstitialProxy", "proxy is null.");
                return;
            }
            gk gkVar = new gk();
            gkVar.b(InterstitialKeys.STREAM_ERROR_CODE, i);
            VideoInfo videoInfo = this.b;
            if (videoInfo != null && !TextUtils.isEmpty(videoInfo.g())) {
                gkVar.b(InterstitialKeys.STREAM_DATA_CONSUME, ie.a().a(this.b.g()));
            }
            fVar.a(InterstitialMethods.NOTIFY_STREAM_ERROR, gkVar.a());
        }

        public a(f fVar, VideoInfo videoInfo) {
            this.f4362a = new WeakReference<>(fVar);
            this.b = videoInfo;
        }
    }

    private Bundle a() {
        r rVar = this.f.get();
        if (rVar != null) {
            return rVar.g();
        }
        ho.c("InterstitialProxy", "handleVideoClick, container is null.");
        return null;
    }

    public f(Context context, r rVar, ContentRecord contentRecord, com.huawei.openalliance.ad.inter.data.d dVar) {
        this.b = context;
        this.f = new WeakReference<>(rVar);
        this.c = contentRecord;
        this.e = dVar;
        ou ouVar = new ou(context, new sd(context));
        this.d = ouVar;
        ouVar.a(contentRecord);
    }
}
