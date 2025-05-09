package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import com.huawei.pluginachievement.impl.PersonalDataCallback;
import com.huawei.pluginachievement.jsmodule.AchieveUtil;
import com.huawei.pluginachievement.jsmodule.CloudUtil;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.service.LanguageResReceiver;
import com.huawei.pluginachievement.manager.service.OnceMovementReceiver;
import com.huawei.pluginachievement.report.datahlr.AchieveAnnualDataHlr;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.ui.commonui.base.BaseDialog;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class mcv extends mml {
    private static volatile mcv e;

    /* renamed from: a, reason: collision with root package name */
    private final AchieveNavigationApi f14886a;
    private PluginAchieveAdapter b;
    private Context c;

    private mcv(Context context) {
        if (context != null) {
            this.c = context.getApplicationContext();
        } else {
            this.c = BaseApplication.getContext();
        }
        this.f14886a = (AchieveNavigationApi) Services.a("PluginAchievement", AchieveNavigationApi.class);
    }

    public static mcv d(Context context) {
        if (e == null) {
            synchronized (mcv.class) {
                if (e == null) {
                    e = new mcv(context);
                }
            }
        }
        if (e.getAdapter() == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "mPluginForAchieveAdapter == null ");
            r();
        }
        return e;
    }

    @Override // defpackage.mml
    public void finish() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "finish()");
        s();
        super.finish();
    }

    @Override // defpackage.mml
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        super.setAdapter(pluginBaseAdapter);
        if (pluginBaseAdapter instanceof PluginAchieveAdapter) {
            this.b = (PluginAchieveAdapter) pluginBaseAdapter;
        }
    }

    @Override // defpackage.mml
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public PluginAchieveAdapter getAdapter() {
        return this.b;
    }

    private static void r() {
        try {
            Object newInstance = Class.forName("com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl").newInstance();
            if (newInstance instanceof PluginAchieveAdapter) {
                e.setAdapter((PluginAchieveAdapter) newInstance);
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException e2) {
            LogUtil.b("PLGACHIEVE_PluginAchieve", e2.getMessage());
        }
    }

    private void s() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "release()");
        meh.a();
    }

    public LanguageResReceiver l() {
        meh c = meh.c(this.c);
        if (c != null) {
            return c.n();
        }
        return null;
    }

    public OnceMovementReceiver h() {
        meh c = meh.c(this.c);
        if (c != null) {
            return c.q();
        }
        return null;
    }

    public void t() {
        meh c = meh.c(this.c);
        if (c != null) {
            c.v();
        }
    }

    public void c(boolean z) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "popAchieveDialog()");
        if (p()) {
            return;
        }
        final meh c = meh.c(this.c);
        if (c == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "achieveDataManager is null");
        }
        if (z) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mcv.5
                @Override // java.lang.Runnable
                public void run() {
                    c.x();
                }
            }, 5000L);
        } else {
            c.x();
        }
    }

    public boolean k() {
        Activity activity = BaseApplication.getActivity();
        if (BaseDialog.sSplashAdViewId == 0 || BaseDialog.sSplashAdViewId == -1 || activity == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "Ad is not showing, exception.");
            return false;
        }
        if (activity.findViewById(BaseDialog.sSplashAdViewId) != null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "Ad is showing, can not show dialog");
            return true;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "Ad is not showing, show dialog");
        return false;
    }

    private boolean p() {
        if (this.c == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "popAchieveDialog() Context is null");
            return true;
        }
        if (a()) {
            return false;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "popAchieveDialog() does not support Achieve!");
        return true;
    }

    public void n() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "noticeAchieveDialog()");
        if (p()) {
            return;
        }
        meh c = meh.c(this.c);
        if (c != null) {
            c.u();
        } else {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "achieveDataManager is null");
        }
    }

    public void i() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "generateReportMessage()");
        if (this.c == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateReportMessage() Context is null");
        } else if (!a()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateReportMessage() does not support Achieve!");
        } else {
            meh.c(this.c).j();
        }
    }

    public void b() {
        if (this.c == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "generateReportHealthData() Context is null");
            return;
        }
        long d = mkx.d(-4, System.currentTimeMillis(), 1);
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showReport_generateReportHealthData() startTime-now", Long.valueOf(d));
        mex.b(this.c).b(d, System.currentTimeMillis(), 11, null);
    }

    public void d() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "generateKakaMessage()");
        if (this.c == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateKakaMessage() Context is null");
        } else if (Utils.o()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateKakaMessage() does not support Achieve!");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mcu
                @Override // java.lang.Runnable
                public final void run() {
                    mcv.this.m();
                }
            });
        }
    }

    /* synthetic */ void m() {
        meh.c(this.c).i();
    }

    public void c() {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "generateHistoryBestMessage()");
        if (this.c == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateHistoryBestMessage() Context is null");
        } else if (!a()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "generateHistoryBestMessage() does not support Achieve!");
        } else {
            meh.c(this.c).h();
        }
    }

    public PersonalData c(PersonalDataCallback personalDataCallback, Context context) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "getPersonalData()");
        if (context != null && personalDataCallback != null && a()) {
            return meh.c(context).e(personalDataCallback, context);
        }
        LogUtil.h("PLGACHIEVE_PluginAchieve", "getPersonalData: context/callback is null or does not support Achieve");
        return null;
    }

    public PersonalData e(Context context) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "getPersonalDataFromSql");
        if (context != null && a()) {
            return meh.c(context).e(context);
        }
        LogUtil.h("PLGACHIEVE_PluginAchieve", "getPersonalDataFromSQL: context is null or does not support Achieve");
        return null;
    }

    public ArrayList<String> c(Context context) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "getPersonalMedalData()");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "context == null");
            return new ArrayList<>();
        }
        if (!a()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "getPersonalMedalData() does not support Achieve!");
            return new ArrayList<>();
        }
        return meh.c(context).r();
    }

    public void i(Context context) {
        this.f14886a.showAchieveReport(context);
    }

    public void h(Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "showExchangePointActivity context is null.");
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showExchangePointActivity enter");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT);
        context.startActivity(intent);
    }

    public void n(Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "showKakaGiftMorePage context is null.");
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showKakaGiftMorePage enter");
        bzs.e().initH5Pro();
        H5ProClient.startH5MiniProgram(context, KakaConstants.KAKA_H5_PACKAGE_NAME, new H5ProLaunchOption.Builder().addPath("#/gift").setImmerse().showStatusBar().addCustomizeJsModule("CloudUtil", CloudUtil.class).addCustomizeJsModule("AchieveUtil", AchieveUtil.class).setBackgroundColor(Color.rgb(252, 203, OldToNewMotionPath.SPORT_TYPE_TENNIS)).build());
    }

    public void a(Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "initKaka context = null");
            return;
        }
        meh c = meh.c(context.getApplicationContext());
        if (c.t() == null || c.t().getHuid() == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "showAchieveKaka userProfile or uid is null!");
            c.ad();
        }
    }

    public void b(Context context) {
        this.f14886a.showAchieveKaka(context);
    }

    public void m(Context context) {
        this.f14886a.showMyAwardPage(context);
    }

    public void f(Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "showAnnualReport context null");
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAnnualReport()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_WEEK_AND_MONTH);
        context.startActivity(intent);
        mfg.c(context);
    }

    public void j(Context context) {
        this.f14886a.showAchieveMedal(context);
    }

    public void c(Context context, String str, String str2) {
        if (c(context, str)) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveMedalById()");
            Intent intent = new Intent();
            intent.putExtra("medal_res_id", str);
            intent.putExtra("from", 1);
            intent.putExtra("real_from", str2);
            intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_MEDAL_DETAIL);
            gnm.aPB_(context, intent);
        }
    }

    private boolean c(Context context, String str) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "checkjumpMedalDetailProbability context null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "checkjumpMedalDetailProbability medalId null");
            j(context);
            return false;
        }
        if (mfg.a(context, str)) {
            return true;
        }
        LogUtil.h("PLGACHIEVE_PluginAchieve", "checkjumpMedalDetailProbability ResourceIsComplete return");
        j(context);
        return false;
    }

    public void g(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showAchieveLevel()");
        Intent intent = new Intent();
        if (CommonUtil.bu()) {
            intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_LEVEL);
        } else {
            intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_NEW_LEVEL);
        }
        context.startActivity(intent);
    }

    public void d(Context context, int i, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "getAnnualReportData context null!");
        } else {
            AchieveAnnualDataHlr.getInstance(context).getAnnualReportJson(i, iBaseResponseCallback);
        }
    }

    public void b(Context context, int i, int i2, int i3) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showReportActivity()");
        Intent intent = new Intent();
        if (i3 == 0) {
            intent.setClassName(context, PersonalData.CLASS_NAME_ACHIEVE_MONTH_REPORT_ACTIVITY);
        } else {
            intent.setClassName(context, PersonalData.CLASS_NAME_ACHIEVE_WEEK_REPORT_ACTIVITY);
        }
        intent.putExtra("max_report_no", String.valueOf(i2));
        intent.putExtra("min_report_no", String.valueOf(i));
        intent.putExtra("report_stype", String.valueOf(i3));
        intent.setFlags(276824064);
        context.startActivity(intent);
    }

    public void b(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "showLotteryShareActivity()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_LOTTERY_SHARE);
        intent.putExtra("imgUrl", str);
        intent.putExtra("awardName", str2);
        context.startActivity(intent);
    }

    public static boolean a() {
        return Utils.i();
    }

    public static boolean e(int i) {
        if (!Utils.o()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "Not Oversea");
            return true;
        }
        if (Utils.i()) {
            if (i == 7 || i == 8) {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "isSupportRequest is AllowLogin!(medal type)");
                return true;
            }
            if (i <= 4 && i >= 0 && i != 1) {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "isSupportRequest is AllowLogin!");
                return true;
            }
        }
        LogUtil.a("PLGACHIEVE_PluginAchieve", "Not AllowLogin!");
        return false;
    }

    public static boolean e() {
        return Utils.i() && Utils.o();
    }

    public int c(Context context, String str, Map<String, Object> map) {
        if (context == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        if (Utils.o()) {
            LogUtil.a("PLGACHIEVE_PluginAchieve", "setEvent does not support Achieve!");
            b(context, str, map);
            e(context, str, map);
            return -1;
        }
        mer.b(context).c(str, map);
        return 0;
    }

    private void e(Context context, String str, Map<String, Object> map) {
        if (context == null || TextUtils.isEmpty(str) || map == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "dealDeleteRunTrack context or map or key is empty");
            return;
        }
        if (String.valueOf(1).equals(str)) {
            try {
                int parseInt = Integer.parseInt(String.valueOf(map.get("type")));
                LogUtil.a("PLGACHIEVE_PluginAchieve", "dealDeleteTrackData type=", Integer.valueOf(parseInt));
                if (mfg.d(parseInt)) {
                    mer.b(context).c(str, map);
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_PluginAchieve", "dealDeleteRunTrack NumberFormatException");
            }
        }
    }

    private void b(Context context, String str, Map<String, Object> map) {
        if (context == null || TextUtils.isEmpty(str) || map == null) {
            LogUtil.h("PLGACHIEVE_PluginAchieve", "dealDeleteRunTrack context or map or key is empty");
            return;
        }
        if (String.valueOf(1).equals(str)) {
            try {
                int parseInt = Integer.parseInt(String.valueOf(map.get("type")));
                LogUtil.a("PLGACHIEVE_PluginAchieve", "dealDeleteTrackData type=", Integer.valueOf(parseInt));
                if (mfg.b(parseInt)) {
                    LogUtil.a("PLGACHIEVE_PluginAchieve", "getSyncChangeResult dealDeleteTrackData SYNC_SPORT_DELETE_INFO = DELETE");
                    mct.b(context, "_syncDeleteData", ProfileRequestConstants.DELETE_TYPE);
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_PluginAchieve", "dealDeleteRunTrack NumberFormatException");
            }
        }
    }

    public Task<List<mdf>> b(final int i, final int i2, final int[] iArr) {
        return Tasks.callInBackground(new Callable<List<mdf>>() { // from class: mcv.2
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public List<mdf> call() throws IllegalStateException {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "getTaskInfoList");
                return mer.b(BaseApplication.getContext()).a(i, i2, iArr);
            }
        });
    }

    public Task<mde> a(final List<mdg> list) {
        return Tasks.callInBackground(new Callable<mde>() { // from class: mcv.1
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public mde call() throws IllegalStateException {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "receiveKakaTask");
                return mer.b(BaseApplication.getContext()).b(list, 2, 1);
            }
        });
    }

    public Task<mde> c(final int i, final String[] strArr) {
        return Tasks.callInBackground(new Callable<mde>() { // from class: mcv.4
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public mde call() throws IllegalStateException {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "receiveKakaTask");
                return mer.b(BaseApplication.getContext()).d(i, strArr);
            }
        });
    }

    public Task<Integer> o() {
        return Tasks.callInBackground(new Callable<Integer>() { // from class: mcv.3
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Integer call() throws IllegalStateException {
                PersonalData c = mcv.this.c(new PersonalDataCallback() { // from class: mcv.3.5
                    @Override // com.huawei.pluginachievement.impl.PersonalDataCallback
                    public void onPersonalDataChange(PersonalData personalData) {
                    }
                }, BaseApplication.getContext());
                if (c != null) {
                    return Integer.valueOf(c.getSumKakaNum());
                }
                throw new IllegalStateException("Current user is not supported achieve");
            }
        });
    }

    public Task<ArrayList<mfs>> g() {
        return Tasks.callInBackground(new Callable<ArrayList<mfs>>() { // from class: mcv.6
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public ArrayList<mfs> call() {
                return meh.c(BaseApplication.getContext()).k();
            }
        });
    }

    public Map<String, mfo> f() {
        return meh.c(BaseApplication.getContext()).m();
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        meh.c(BaseApplication.getContext()).b(iBaseResponseCallback);
    }

    public void b(String str, JSONObject jSONObject, HashMap<String, String> hashMap, HttpResCallBack httpResCallBack) {
        mdk.c(str, jSONObject, hashMap, httpResCallBack);
    }

    public void a(Context context, mdf mdfVar) {
        mer.b(BaseApplication.getContext()).b(mji.c(mdfVar), context);
    }

    public Task<Boolean> d(final int[] iArr) {
        return Tasks.callInBackground(new Callable<Boolean>() { // from class: mcv.10
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Boolean call() throws IllegalStateException {
                LogUtil.a("PLGACHIEVE_PluginAchieve", "checkKakaTaskIsFinished");
                int[] iArr2 = iArr;
                int i = 0;
                if (iArr2 == null || iArr2.length == 0) {
                    LogUtil.h("PLGACHIEVE_PluginAchieve", "checkKakaTaskIsFinished taskRuleIds is empty");
                    return false;
                }
                CountDownLatch countDownLatch = new CountDownLatch(iArr.length);
                while (true) {
                    int[] iArr3 = iArr;
                    if (i < iArr3.length) {
                        mcv.this.a(iArr3[i], countDownLatch);
                        i++;
                    } else {
                        try {
                            break;
                        } catch (InterruptedException unused) {
                            LogUtil.b("PLGACHIEVE_PluginAchieve", "checkKakaTaskIsFinished is interruputed");
                        }
                    }
                }
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, CountDownLatch countDownLatch) {
        LogUtil.a("PLGACHIEVE_PluginAchieve", "checkKakaTaskIsFinished taskRuleId = ", Integer.valueOf(i));
        if (i == 30002) {
            mer.b(BaseApplication.getContext()).j(countDownLatch);
            return;
        }
        if (i == 30006) {
            mer.b(BaseApplication.getContext()).a(countDownLatch, 2103);
            return;
        }
        if (i == 30010) {
            mer.b(BaseApplication.getContext()).d(countDownLatch);
            return;
        }
        if (i == 30012) {
            mer.b(BaseApplication.getContext()).b(countDownLatch);
            return;
        }
        if (i != 30015) {
            switch (i) {
                case 20001:
                    meh.c(BaseApplication.getContext()).b(countDownLatch);
                    break;
                case 20002:
                case 20003:
                case 20005:
                case 20006:
                    meh.c(BaseApplication.getContext()).e(countDownLatch);
                    break;
                case 20004:
                    mer.b(BaseApplication.getContext()).e(countDownLatch);
                    break;
                case 20007:
                    mer.b(BaseApplication.getContext()).c(countDownLatch);
                    break;
                default:
                    LogUtil.h("PLGACHIEVE_PluginAchieve", "checkKakaTaskIsFinished invalid taskRuleId");
                    mle.b(countDownLatch);
                    break;
            }
            return;
        }
        mer.b(BaseApplication.getContext()).a(countDownLatch);
    }
}
