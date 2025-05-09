package com.huawei.openalliance.ad.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.data.RewardEvent;
import com.huawei.openalliance.ad.inter.data.h;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.utils.av;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSRewardTemplateView;
import com.huawei.openalliance.ad.views.interfaces.ab;
import com.huawei.openalliance.ad.views.interfaces.k;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class PPSRewardActivity extends e {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6597a = new byte[0];
    private static final ConcurrentHashMap<String, IRewardAdStatusListener> h = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, INonwifiActionListener> i = new ConcurrentHashMap<>();
    private k b;
    private Integer c;
    private h d;
    private String e;
    private boolean f = false;
    private av g;

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    protected void onStop() {
        super.onStop();
        k kVar = this.b;
        if (kVar != null) {
            kVar.g();
            this.b.b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        k kVar = this.b;
        if (kVar != null) {
            kVar.b(true);
            this.b.resumeView();
        }
        super.onResume();
        b();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        ho.a("PPSRewardActivity", "requestPermissions, requestCode=%d, result= %s", Integer.valueOf(i2), Arrays.toString(iArr));
        a(i2, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onPause() {
        ho.a("PPSRewardActivity", "onPause");
        k kVar = this.b;
        if (kVar != null) {
            kVar.b(false);
            this.b.pauseView();
        }
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSRewardActivity.1
            @Override // java.lang.Runnable
            public void run() {
                k kVar = PPSRewardActivity.this.b;
                if (kVar != null) {
                    kVar.removeRewardAdStatusListener();
                    kVar.destroyView();
                }
                PPSRewardActivity.this.d = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        dd.h(this);
        super.onCreate(bundle);
        try {
            ho.b("PPSRewardActivity", "onCreate");
            h b2 = dc.b();
            this.d = b2;
            if (b2 == null) {
                ho.c("PPSRewardActivity", "reward ad is null, finish, this should not happen");
                finish();
                return;
            }
            this.e = b2.getUniqueId();
            int i2 = !"2".equals(this.d.aa()) ? 1 : 0;
            setRequestedOrientation(i2);
            dd.a(this, i2);
            k kVar = (k) a(this.d.aj());
            this.b = kVar;
            kVar.setVisibility(0);
            this.b.setOrientation(i2);
            this.b.addRewardAdStatusListener(new b());
            this.b.addNonwifiActionListener(new a());
            this.b.setTemplateErrorListener(new c());
            this.b.a((IRewardAd) this.d, true);
            this.g = this.b.getAppointJs();
            Resources resources = getResources();
            if (resources != null) {
                if (resources.getConfiguration() != null) {
                    this.c = Integer.valueOf(resources.getConfiguration().screenWidthDp);
                }
                onConfigurationChanged(resources.getConfiguration());
            }
        } catch (Throwable th) {
            ho.c("PPSRewardActivity", "onCreateEx: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        if (configuration == null) {
            return;
        }
        super.onConfigurationChanged(configuration);
        int i2 = configuration.uiMode & 48;
        ho.b("PPSRewardActivity", "currentNightMode=" + i2);
        a(32 == i2 ? 2 : 0);
        a(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onBackPressed() {
        k kVar;
        if (this.f) {
            kVar = this.b;
            if (!(kVar instanceof PPSRewardTemplateView)) {
                IRewardAdStatusListener iRewardAdStatusListener = h.get(this.e);
                if (iRewardAdStatusListener != null) {
                    iRewardAdStatusListener.onAdClosed();
                }
                super.onBackPressed();
                return;
            }
        } else {
            kVar = this.b;
            if (kVar == null) {
                return;
            }
        }
        kVar.onEvent(RewardEvent.BACKPRESSED);
    }

    public void b() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.activity.PPSRewardActivity.4
            @Override // java.lang.Runnable
            public void run() {
                AppDownloadButton appDownloadButton;
                try {
                    if (PPSRewardActivity.this.b == null || !PPSRewardActivity.this.b.h() || (appDownloadButton = PPSRewardActivity.this.b.getAppDownloadButton()) == null) {
                        return;
                    }
                    ho.a("PPSRewardActivity", "reSetButtonWidth");
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) appDownloadButton.getLayoutParams();
                    layoutParams.width = -1;
                    appDownloadButton.setLayoutParamsSkipSizeReset(layoutParams);
                } catch (Throwable th) {
                    ho.d("PPSRewardActivity", "resetButtonWidth exception: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setContentView(R.layout.hiad_activity_reward);
        this.p = (ViewGroup) findViewById(R.id.hiad_reward_layout);
    }

    public static void a(String str, IRewardAdStatusListener iRewardAdStatusListener) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "registerIRewardAdStatusListener key is null";
        } else {
            if (iRewardAdStatusListener != null) {
                synchronized (f6597a) {
                    h.put(str, iRewardAdStatusListener);
                }
                return;
            }
            str2 = "registerIRewardAdStatusListener listner is null";
        }
        ho.c("PPSRewardActivity", str2);
    }

    public static void a(String str, INonwifiActionListener iNonwifiActionListener) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "registerNonwifiActionListener key is null";
        } else {
            if (iNonwifiActionListener != null) {
                synchronized (f6597a) {
                    i.put(str, iNonwifiActionListener);
                }
                return;
            }
            str2 = "registerNonwifiActionListener listener is null";
        }
        ho.c("PPSRewardActivity", str2);
    }

    class b implements IRewardAdStatusListener {
        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onRewarded() {
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onRewarded();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdShown() {
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdShown();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdError(int i, int i2) {
            PPSRewardActivity.this.f = true;
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdError(i, i2);
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdCompleted() {
            PPSRewardActivity.this.f = true;
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdCompleted();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdClosed() {
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdClosed();
            }
            PPSRewardActivity.this.finish();
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdClicked() {
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdClicked();
            }
        }

        private b() {
        }
    }

    private void a(Configuration configuration) {
        ho.a("PPSRewardActivity", "onConfigurationChanged newConfig.screenWidthDp=" + configuration.screenWidthDp + ", this.screenWidthDp=" + this.c);
        if (this.c == null || configuration.screenWidthDp != this.c.intValue()) {
            ho.a("PPSRewardActivity", "onConfigurationChanged resetButtonWidth()");
            this.c = Integer.valueOf(configuration.screenWidthDp);
            b();
        }
    }

    private void a(int i2, int[] iArr) {
        if (i2 == 11 || i2 == 12) {
            if (iArr.length > 0 && iArr[0] == 0) {
                av avVar = this.g;
                if (avVar != null) {
                    if (i2 == 11) {
                        avVar.a(true, true);
                        return;
                    } else {
                        avVar.b(true, true);
                        return;
                    }
                }
                return;
            }
            if (!shouldShowRequestPermissionRationale("android.permission.WRITE_CALENDAR")) {
                a(i2, i2 == 11 ? R.string._2130851024_res_0x7f0234d0 : R.string._2130851025_res_0x7f0234d1);
                return;
            }
            av avVar2 = this.g;
            if (avVar2 != null) {
                if (i2 == 11) {
                    avVar2.a(false, true);
                } else {
                    avVar2.b(false, true);
                }
            }
        }
    }

    private void a(final int i2, int i3) {
        new AlertDialog.Builder(this).setTitle(R.string._2130851026_res_0x7f0234d2).setMessage(i3).setPositiveButton(R.string._2130851027_res_0x7f0234d3, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSRewardActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", PPSRewardActivity.this.getPackageName(), null));
                PPSRewardActivity.this.startActivity(intent);
                dialogInterface.dismiss();
                if (PPSRewardActivity.this.g != null) {
                    if (i2 == 11) {
                        PPSRewardActivity.this.g.a(false, false);
                    } else {
                        PPSRewardActivity.this.g.b(false, false);
                    }
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i4);
            }
        }).setNegativeButton(R.string._2130851070_res_0x7f0234fe, new DialogInterface.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.PPSRewardActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                dialogInterface.dismiss();
                if (PPSRewardActivity.this.g != null) {
                    if (i2 == 11) {
                        PPSRewardActivity.this.g.a(false, true);
                    } else {
                        PPSRewardActivity.this.g.b(false, true);
                    }
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i4);
            }
        }).show();
    }

    private void a(int i2) {
        k kVar;
        WebSettings webViewSettings;
        if (Build.VERSION.SDK_INT < 29 || (kVar = this.b) == null || (webViewSettings = kVar.getWebViewSettings()) == null) {
            return;
        }
        webViewSettings.setForceDark(i2);
    }

    class a implements INonwifiActionListener {
        @Override // com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener
        public boolean onVideoPlay(long j) {
            INonwifiActionListener iNonwifiActionListener = (INonwifiActionListener) PPSRewardActivity.i.get(PPSRewardActivity.this.e);
            if (iNonwifiActionListener != null) {
                return iNonwifiActionListener.onVideoPlay(j);
            }
            return false;
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener
        public boolean onAppDownload(AppInfo appInfo, long j) {
            INonwifiActionListener iNonwifiActionListener = (INonwifiActionListener) PPSRewardActivity.i.get(PPSRewardActivity.this.e);
            if (iNonwifiActionListener != null) {
                return iNonwifiActionListener.onAppDownload(appInfo, j);
            }
            return false;
        }

        private a() {
        }
    }

    class c implements ab {
        @Override // com.huawei.openalliance.ad.views.interfaces.ab
        public void a(int i) {
            ho.c("PPSRewardActivity", "template init error, extra: %s", Integer.valueOf(i));
            IRewardAdStatusListener iRewardAdStatusListener = (IRewardAdStatusListener) PPSRewardActivity.h.get(PPSRewardActivity.this.e);
            if (iRewardAdStatusListener != null) {
                iRewardAdStatusListener.onAdError(-2, i);
            }
            PPSRewardActivity.this.finish();
        }

        private c() {
        }
    }

    private View a(Integer num) {
        ho.b("PPSRewardActivity", "apiVer: %s", num);
        View inflate = LayoutInflater.from(this).inflate((num == null || num.intValue() != 3) ? R.layout.hiad_reward_view : R.layout.hiad_reward_template_view, this.p, false);
        this.p.addView(inflate, new RelativeLayout.LayoutParams(-1, -1));
        return inflate;
    }
}
