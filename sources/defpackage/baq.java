package defpackage;

import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class baq {
    public static void b(final ResponseCallback<aur> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthModelCardReaderInfo", "getHealthModelStatus callback is null");
            return;
        }
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelGetHealthModelStatus", new Runnable() { // from class: bau
                @Override // java.lang.Runnable
                public final void run() {
                    baq.b(ResponseCallback.this);
                }
            });
        } else if (!Utils.i()) {
            ReleaseLogUtil.b("R_HealthLife_HealthModelCardReaderInfo", "getHealthModelStatus ifAllowLogin false");
            c(responseCallback);
        } else {
            aug.d().e(new IBaseResponseCallback() { // from class: bav
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    baq.a(ResponseCallback.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void a(ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "getHealthModelStatus errorCode ", Integer.valueOf(i), " object ", obj);
        if (i == -1) {
            responseCallback.onResponse(i, null);
        } else if (obj instanceof aur) {
            c((aur) obj, responseCallback, true);
        }
    }

    private static void c(ResponseCallback<aur> responseCallback) {
        aur aurVar = new aur();
        aurVar.b(azi.t());
        aurVar.a(azi.q());
        aurVar.c(azi.aa() ? 1 : 0);
        aurVar.e(bat.e() ? 1 : 0);
        c(aurVar, responseCallback, false);
    }

    private static void c(aur aurVar, ResponseCallback<aur> responseCallback, boolean z) {
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "getHealthModelStatus data onSuccess");
        if (aurVar == null || aurVar.a() != 0) {
            ReleaseLogUtil.a("R_HealthLife_HealthModelCardReaderInfo", "processResult data ", aurVar);
            responseCallback.onResponse(-1, null);
            return;
        }
        int c = aurVar.c();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        String p = azi.p();
        if (c == b) {
            awq.e().kE_(p, c, z);
            d(p, c);
        }
        boolean z2 = c > b;
        if (z2) {
            azi.af();
        }
        bao.e("health_model_is_join_future", String.valueOf(z2));
        bao.e(c, true);
        int e = aurVar.e();
        bao.a(e, true);
        bao.e("health_model_challenge_join", String.valueOf(aurVar.b()));
        boolean aa = azi.aa();
        c(aa);
        if (aa) {
            bam.e();
        } else if (!z2) {
            a(false, false, (ResponseCallback<Object>) new ResponseCallback() { // from class: baw
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    LogUtil.a("HealthLife_HealthModelCardReaderInfo", "processResult onResponse resultCode ", Integer.valueOf(i));
                }
            });
            responseCallback.onResponse(0, aurVar);
            return;
        }
        a(p, e, c, responseCallback, aurVar);
    }

    private static void a(String str, int i, int i2, ResponseCallback<aur> responseCallback, aur aurVar) {
        int i3;
        if (i < i2 || i <= 0) {
            i3 = 0;
        } else {
            HealthLifeBean b = aus.b(str, 1, i);
            if (!azi.j(b)) {
                LogUtil.h("HealthLife_HealthModelCardReaderInfo", "processResult recordDbBean is null ");
                responseCallback.onResponse(0, aurVar);
                return;
            }
            i3 = bcm.d(str, i, b.getTarget());
        }
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "processResult joinTime = ", Integer.valueOf(i2), ", upgradeTime = ", Integer.valueOf(i), " codeDelete=", Integer.valueOf(i3));
        responseCallback.onResponse(0, aurVar);
    }

    private static void c(boolean z) {
        UserLabelServiceApi userLabelServiceApi = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (z) {
            userLabelServiceApi.initLabel("health_sport_health_mode_user", "HealthModeUser", accountInfo);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("health_sport_health_mode_active");
        userLabelServiceApi.initLabel("health_sport_health_mode_user", !userLabelServiceApi.getLabels(arrayList, accountInfo).contains("HealthModeActive_360+") ? "HealthModeUser_Quit" : "HealthModeUser_NotJoined", accountInfo);
    }

    private static void d(String str, int i) {
        HealthLifeBean b = aus.b(str, 1, i);
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "deleteBeforeJoinData join today and delete before data code: ", Integer.valueOf(auz.d(str, i)));
        if (azi.j(b)) {
            String target = b.getTarget();
            if (TextUtils.isEmpty(target)) {
                return;
            }
            bcm.d(str, i, target);
            return;
        }
        LogUtil.h("HealthLife_HealthModelCardReaderInfo", "subscriptionDbBean is null");
    }

    public static void a(final boolean z, final boolean z2, final ResponseCallback<Object> responseCallback) {
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "clearHealthModelLocalData");
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bas
                @Override // java.lang.Runnable
                public final void run() {
                    baq.a(z, z2, (ResponseCallback<Object>) responseCallback);
                }
            });
            return;
        }
        bcm.d("");
        int d = azi.d(z);
        if (d != 0 && responseCallback != null) {
            responseCallback.onResponse(d, "");
            return;
        }
        int c = azi.c();
        if (c != 0 && responseCallback != null) {
            responseCallback.onResponse(c, "");
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        HiHealthApi d2 = HiHealthManager.d(BaseApplication.getContext());
        hiUserPreference.setKey("health_model_is_support_health_model_new");
        hiUserPreference.setValue("");
        LogUtil.a("HealthLife_HealthModelCardReaderInfo", "clearHealthModelLocalData isSuccess key = isSupportNewHealthModel ", Boolean.valueOf(d2.setUserPreference(hiUserPreference)));
        Iterator<Integer> it = dsl.j().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            hiUserPreference.setKey("health_model_target_adjustment_date_" + intValue);
            hiUserPreference.setValue("");
            LogUtil.c("HealthLife_HealthModelCardReaderInfo", "clearHealthModelLocalData accept isSuccess ", Boolean.valueOf(d2.setUserPreference(hiUserPreference)));
            hiUserPreference.setKey("health_model_ignored_target_adjustment_date_" + intValue);
            hiUserPreference.setValue("");
            LogUtil.c("HealthLife_HealthModelCardReaderInfo", "clearHealthModelLocalData ignore isSuccess ", Boolean.valueOf(d2.setUserPreference(hiUserPreference)));
        }
        if (z2) {
            azi.an();
        }
        if (responseCallback != null) {
            responseCallback.onResponse(0, "");
        }
    }
}
