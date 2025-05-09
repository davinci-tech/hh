package defpackage;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.browse.BrowsingClickListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bdx implements BrowsingClickListener {

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f342a;
    private View b;
    private String c;
    private Long d;
    private boolean e;

    public bdx(View.OnClickListener onClickListener, boolean z, String str) {
        this.f342a = onClickListener;
        this.e = z;
        this.c = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            ReleaseLogUtil.d("R_PLGLOGIN_BrowsingClickListenerImpl", "onClick showFullServiceDialog");
            browsingBiEvent.showFullServiceDialog(BaseApplication.e());
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (!isBrowsingMode() || !this.e) {
                this.f342a.onClick(view);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ReleaseLogUtil.d("R_PLGLOGIN_BrowsingClickListenerImpl", "onClick isBrowsingMode: ", Boolean.valueOf(isBrowsingMode()), "  mIsNeedLoginUrl: ", Boolean.valueOf(this.e));
            this.b = view;
            this.d = Long.valueOf(SystemClock.elapsedRealtime());
            Intent intent = new Intent();
            intent.setAction("com.huawei.plugin.trigger.checklogin");
            LocalBroadcastManager.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).sendBroadcast(intent);
            if (browsingBiEvent != null) {
                browsingBiEvent.loginBeforeBiEvent(this.c);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.browse.BrowsingClickListener
    public void onNext() {
        if (this.b != null) {
            LogUtil.c("PLGLOGIN_BrowsingClickListenerImpl", "OnClickListener onNext.");
            this.f342a.onClick(this.b);
            this.b = null;
        }
    }

    @Override // com.huawei.browse.BrowsingClickListener
    public boolean isBrowsingMode() {
        return LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).isBrowseMode();
    }

    public boolean c(bdx bdxVar) {
        return d().longValue() > bdxVar.d().longValue();
    }

    public Long d() {
        return this.d;
    }

    public View pv_() {
        return this.b;
    }

    public void e() {
        this.b = null;
    }
}
