package defpackage;

import android.content.Context;
import android.view.View;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.CommonLibUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class nks implements BrowsingBiEvent {
    private static Context b;
    private static volatile nks c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f15351a = null;
    private String d = null;

    private nks() {
    }

    public static nks e(Context context) {
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new nks();
                    b = context;
                    if (context == null) {
                        b = BaseApplication.getContext();
                    }
                    LoginInit.getInstance(b);
                    LoginInit.setBrowsingBiEvent(c);
                }
            }
        }
        return c;
    }

    @Override // com.huawei.browse.BrowsingBiEvent
    public void updateCountryCodeAndUserId() {
        ixx.d().a(LoginInit.getInstance(b).getUsetId());
        ixx.d().e(LoginInit.getInstance(b).getCountryCode(null));
    }

    @Override // com.huawei.browse.BrowsingBiEvent
    public void loginBeforeBiEvent(String str) {
        updateCountryCodeAndUserId();
        this.f15351a = LoginInit.getInstance(b).getCountryCode(null);
        this.d = str;
        HashMap hashMap = new HashMap();
        hashMap.put("triggerPos", str);
        ixx.d().d(b, AnalyticsValue.HEALTH_TRIGGER_LOGIN_APP_2050004.value(), hashMap, 0);
    }

    @Override // com.huawei.browse.BrowsingBiEvent
    public void loginSuccessBiEvent() {
        ixx.d().e(LoginInit.getInstance(b).getCountryCode(null));
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("lastCountry", this.f15351a);
        hashMap.put("triggerPos", this.d);
        ixx.d().d(b, AnalyticsValue.HEALTH_LOGIN_APP_2050001.value(), hashMap, 0);
        ixx.d().a(LoginInit.getInstance(b).getUsetId());
    }

    @Override // com.huawei.browse.BrowsingBiEvent
    public void showFullServiceDialog(final Context context) {
        LogUtil.a("BrowseBi", "showFullServiceDialog in");
        String string = context.getString(R$string.IDS_hw_health_full_service);
        CustomTextAlertDialog.Builder c2 = new CustomTextAlertDialog.Builder(com.huawei.haf.application.BaseApplication.wa_()).b(string).e(context.getString(R$string.IDS_hw_health_full_service_label)).cyU_(R$string.IDS_user_permission_ok, new View.OnClickListener() { // from class: nks.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
                SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_agree_full_service_model", true);
                CommonLibUtil.d(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: nks.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("BrowseBi", "showFullServiceDialog onClick to cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).c(true);
        CustomTextAlertDialog a2 = c2.a();
        c2.e().setBackground(context.getDrawable(R$drawable.button_background_emphasize));
        c2.e().setTextColor(context.getColor(R$color.common_color_white));
        a2.setCancelable(false);
        a2.show();
    }
}
