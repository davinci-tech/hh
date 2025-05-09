package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.jiv;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jiv {

    /* renamed from: a, reason: collision with root package name */
    private static jiv f13881a;
    private static final Object e = new Object();
    private ExtendHandler b;
    private String j = null;
    private boolean d = false;
    private jja c = jja.b();
    private Map<String, Boolean> f = new HashMap(16);

    private jiv() {
    }

    public boolean d(String str) {
        Boolean bool = this.f.get(str);
        boolean booleanValue = bool != null ? bool.booleanValue() : false;
        LogUtil.a("HwSportAppMarketManger", "getRedDotStatus deviceIdentity : ", jja.d(str), "; status : ", Boolean.valueOf(booleanValue));
        return booleanValue;
    }

    public void b(String str, boolean z) {
        a();
        LogUtil.a("HwSportAppMarketManger", "setRedDotStatus deviceIdentity : ", jja.d(str), "; status : ", Boolean.valueOf(z));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f.put(str, Boolean.valueOf(z));
    }

    public void a() {
        this.f.clear();
        LogUtil.a("HwSportAppMarketManger", "clearRedDotStatus");
    }

    public void c(String str) {
        ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "installWatchFaceApp");
    }

    public static jiv e() {
        jiv jivVar;
        LogUtil.a("HwSportAppMarketManger", "getInstance()");
        synchronized (e) {
            if (f13881a == null) {
                f13881a = new jiv();
            }
            jivVar = f13881a;
        }
        return jivVar;
    }

    public void b() {
        this.d = false;
        i();
    }

    public void e(boolean z) {
        this.d = z;
        i();
    }

    private void i() {
        this.c.e(new IBaseResponseCallback() { // from class: jiv.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "showAppMarketViewHelper is not agree user agreement");
                    jiv.this.e(1, 0);
                } else {
                    ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "showAppMarketViewHelper is agree user agreement");
                    jiv.this.e(2, 0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "startAppMarket");
        jiw.a().d(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Activity activity = BaseApplication.getActivity();
        if (activity == null || activity.isFinishing() || !this.d) {
            return;
        }
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "showMarketPrivacyDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(R$string.IDS_application_market_dialog_title);
        builder.cyQ_(this.c.bHu_(bHr_()));
        builder.cyR_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: jiv.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                jiv.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cyU_(R$string.IDS_user_watchFace_permission_ok, new View.OnClickListener() { // from class: jiv.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwSportAppMarketManger", "showMarketPrivacyDialog is true");
                SharedPreferenceManager.a(BaseApplication.getContext(), true);
                jqi.a().setSwitchSetting("app_market_privacy_service_status", "1", null);
                jiv.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* renamed from: jiv$3, reason: invalid class name */
    class AnonymousClass3 extends ClickableSpan {
        AnonymousClass3() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            LogUtil.a("HwSportAppMarketManger", "getClickableSpan onClick");
            final Activity activity = BaseApplication.getActivity();
            if (activity != null) {
                if (jiv.this.j != null) {
                    LogUtil.a("HwSportAppMarketManger", "getClickableSpan url :", CommonUtil.s(jiv.this.j));
                    activity.runOnUiThread(new Runnable() { // from class: jix
                        @Override // java.lang.Runnable
                        public final void run() {
                            jiv.AnonymousClass3.this.bHs_(activity);
                        }
                    });
                } else {
                    LogUtil.h("HwSportAppMarketManger", "getClickableSpan mUrl or context is null");
                }
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.h("HwSportAppMarketManger", "getClickableSpan onClick topActivity is null");
            ViewClickInstrumentation.clickOnView(view);
        }

        /* synthetic */ void bHs_(Activity activity) {
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", jiv.this.f());
            jja.bHt_(activity, intent, "HwSportAppMarketManger");
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(Color.parseColor("#fb6522"));
            textPaint.setUnderlineText(false);
        }
    }

    private ClickableSpan bHr_() {
        return new AnonymousClass3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String f() {
        return this.j + ("/minisite/cloudservice/health/appgallery-terms.htm?country=" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010) + Constants.LANGUAGE + LanguageUtil.e());
    }

    public void c() {
        ThreadPoolManager.d().d("HwSportAppMarketManger", new Runnable() { // from class: jiv.2
            @Override // java.lang.Runnable
            public void run() {
                jiv.this.j = GRSManager.a(BaseApplication.getContext()).getUrl("domainConsumerHuawei");
            }
        });
    }

    public void e(DeviceCapability deviceCapability, IBaseResponseCallback iBaseResponseCallback) {
        this.c.a(deviceCapability, iBaseResponseCallback);
    }

    public void e(int i, Object obj) {
        if (i == 8) {
            jqi.a().setSwitchSetting("app_market_privacy_service_status", "2", null);
        } else {
            LogUtil.h("HwSportAppMarketManger", "setAppMarketParameter type is error");
        }
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("HwSportAppMarketManger", "handleMessage message what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    jiv.this.j();
                    return true;
                }
                LogUtil.h("HwSportAppMarketManger", "handleMessage default");
                return false;
            }
            final Activity activity = BaseApplication.getActivity();
            if (activity == null) {
                ReleaseLogUtil.e("DEVMGR_HwSportAppMarketManger", "handleMessage topActivity is null");
                return true;
            }
            activity.runOnUiThread(new Runnable() { // from class: jiv.e.1
                @Override // java.lang.Runnable
                public void run() {
                    jiv.this.c(activity);
                }
            });
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        this.b = HandlerCenter.yt_(new e(), "HwSportAppMarketManger");
        Message obtain = Message.obtain();
        obtain.what = i;
        this.b.sendMessage(obtain, i2);
    }
}
