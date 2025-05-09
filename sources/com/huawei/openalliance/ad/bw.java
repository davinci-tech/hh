package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import com.huawei.hms.ads.uiengine.ISplashApi;
import com.huawei.openalliance.ad.activity.SplashFeedbackActivity;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.UiEngineRTCMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class bw extends ISplashApi.a {

    /* renamed from: a, reason: collision with root package name */
    protected qq f6665a;
    private jb b;
    private ContentRecord c;
    private Context d;
    private WeakReference<Context> e;
    private String f;
    private com.huawei.openalliance.ad.analysis.c g;

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public Bundle callMethodForResult(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        return null;
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void updatePhyShowStart(long j) {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.a(j);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void toShowSpare(int i) {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.b(i);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void reportSplashEvent(Bundle bundle) {
        com.huawei.openalliance.ad.analysis.c cVar = this.g;
        if (cVar != null) {
            cVar.a(bundle, this.c);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void reportShowStartEvent() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.i();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void reportEvents(String str, Bundle bundle) {
        char c;
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1888605810:
                if (str.equals("playStart")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -493598457:
                if (str.equals("playEnd")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 886907255:
                if (str.equals(UiEngineRTCMethods.REPORT_SOUND_BUTTON_CLICK_EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1878759457:
                if (str.equals("playTime")) {
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
            b(bundle);
            return;
        }
        if (c == 1) {
            c(bundle);
        } else if (c == 2) {
            a(bundle);
        } else {
            if (c != 3) {
                return;
            }
            d(bundle);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void removeExSplashBlock() {
        com.huawei.openalliance.ad.utils.ao.i(this.d);
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public boolean processWhyEventUnified() {
        return com.huawei.openalliance.ad.utils.ao.a(this.d, this.c);
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public boolean onTouch(int i, int i2, long j, String str, int i3) {
        ho.b("SplashProxy", "onTouch");
        jb jbVar = this.b;
        if (jbVar != null) {
            return jbVar.a(i, i2, this.c, Long.valueOf(j), (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(str, MaterialClickInfo.class, new Class[0]), i3);
        }
        return false;
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onStartEasterEggFailed(Bundle bundle) {
        ho.a("SplashProxy", "onStartEasterEggFailed");
        pp.a(this.d).a(this.c.aa(), bundle);
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onSkipAd(int i, int i2) {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.a(i, i2);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onMaterialLoaded() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.s();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onMaterialLoadFailed() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.e(this.c);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onFeedback(int i) {
        ho.b("SplashProxy", "onFeedback");
        new com.huawei.openalliance.ad.analysis.h(this.d).a("145", "", "", "");
        Intent intent = new Intent(this.d, (Class<?>) SplashFeedbackActivity.class);
        intent.setPackage(this.d.getPackageName());
        intent.putExtra(MapKeyNames.SPLASH_CLICKABLE_TYPE, i);
        if (!(this.d instanceof Activity)) {
            intent.addFlags(268435456);
        }
        com.huawei.openalliance.ad.utils.dd.a(this.d, intent);
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.f();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onEasterEggPrepare() {
        ho.a("SplashProxy", "onEasterEggPrepare");
        pp.a(this.d).a(this.c.aa());
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onDisplayTimeUp() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.u();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onAdShowEnd(long j, int i) {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.a(this.c, j, i);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void onAdFailToDisplay() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.B();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public String notifyAdLoaded() {
        jb jbVar = this.b;
        if (jbVar == null) {
            return null;
        }
        jbVar.a(this.c);
        return this.b.y();
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void notifyAdFailedToLoad(int i) {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.c(i);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void notifyAdDismissed() {
        jb jbVar = this.b;
        if (jbVar != null) {
            jbVar.j();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public boolean isFinishing() {
        if (this.e.get() instanceof Activity) {
            return ((Activity) this.e.get()).isFinishing();
        }
        return false;
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public boolean isDestroyed() {
        if (this.e.get() instanceof Activity) {
            return ((Activity) this.e.get()).isDestroyed();
        }
        return false;
    }

    @Override // com.huawei.hms.ads.uiengine.ISplashApi
    public void callMethod(String str, IObjectWrapper iObjectWrapper, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.b("SplashProxy", "callMethod: %s", str);
        str.hashCode();
        if (str.equals("reportCommonEvent")) {
            new bp(this.d).a(bundle, this.c, this.f6665a);
        } else if (str.equals("onCommonAnalysis")) {
            new bo(this.d).a(bundle, this.c, this.f6665a);
        } else {
            ho.b("SplashProxy", "call method fall to default.");
        }
    }

    private void d(Bundle bundle) {
        ContentRecord contentRecord = this.c;
        if (contentRecord == null || this.f6665a == null) {
            ho.b("SplashProxy", "contentRecord or eventProcessor is null");
            return;
        }
        if (bundle != null) {
            try {
                if (contentRecord.j() != null && this.c.j().equals(this.f)) {
                    ho.b("SplashProxy", "Duplicate escalation videoTime event for %s", this.c.j());
                } else {
                    this.f6665a.a(bundle.getLong(ParamConstants.Param.VIDEO_PLAY_TIME));
                    this.f = this.c.j();
                }
            } catch (Throwable th) {
                ho.c("SplashProxy", "reportPlayTime err: %s", th.getClass().getSimpleName());
            }
        }
    }

    private void c(Bundle bundle) {
        try {
            this.f6665a.c(bundle.getLong("startTime"), bundle.getLong("endTime"), (int) bundle.getLong(ParamConstants.Param.START_PROGRESS), (int) bundle.getLong(ParamConstants.Param.END_PROGRESS));
        } catch (Throwable th) {
            ho.c("SplashProxy", "reportPlayEnd err: %s", th.getClass().getSimpleName());
        }
    }

    private void b(Bundle bundle) {
        this.f6665a.c();
    }

    private void a(Bundle bundle) {
        try {
            this.f6665a.b(bundle.getBoolean("isMute"));
        } catch (Throwable th) {
            ho.c("SplashProxy", "reportSoundClickEvent err: %s", th.getClass().getSimpleName());
        }
    }

    public bw(Context context, jb jbVar, ContentRecord contentRecord) {
        this.d = context.getApplicationContext();
        this.e = new WeakReference<>(context);
        this.b = jbVar;
        this.c = contentRecord;
        this.f6665a = new ou(context, new si(context, 1), contentRecord);
        this.g = new com.huawei.openalliance.ad.analysis.c(this.d);
    }
}
