package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.PersonalDataCallback;
import com.huawei.pluginachievement.manager.model.PersonalData;
import defpackage.meh;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class sas {
    public static void a(Context context, int i) {
        if (context == null) {
            LogUtil.h("UIME_AchieveInteractors", "dispatchClickEvent context null");
            return;
        }
        LogUtil.a("UIME_AchieveInteractors", "dispatchClickEvent key=", Integer.valueOf(i));
        b(context, i);
        if (i == 1) {
            mcv.d(context).g(context);
            return;
        }
        if (i == 2) {
            c(context);
            mcv.d(context).j(context);
            return;
        }
        if (i == 3) {
            mcv.d(context).b(context);
            return;
        }
        if (i != 4) {
            if (i != 5) {
                return;
            }
            mcv.d(context).f(context);
        } else {
            mcv.d(context).i(context);
            LogUtil.h("UIME_AchieveInteractors", "showAchieveReport isOversea.");
            mct.b(context, "generateAchieveTime", "");
            mgx.e(context).j(context);
        }
    }

    private static void b(Context context, int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        if (i == 1) {
            ixx.d().d(context, AnalyticsValue.HEALTH_MINE_MY_RANK_2040005.value(), hashMap, 0);
            return;
        }
        if (i == 2) {
            ixx.d().d(context, AnalyticsValue.HEALTH_MINE_MY_MEDAL_2040012.value(), hashMap, 0);
            return;
        }
        if (i == 3) {
            ixx.d().d(context, AnalyticsValue.HEALTH_MINE_MY_KAKA_2040011.value(), hashMap, 0);
        } else if (i == 4) {
            ixx.d().d(context, AnalyticsValue.HEALTH_MINE_MY_REPORT_2040010.value(), hashMap, 0);
        } else {
            if (i != 5) {
                return;
            }
            ixx.d().d(context, AnalyticsValue.HEALTH_MINE_ACHIEVE_REPORT_2040062.value(), hashMap, 0);
        }
    }

    private static void c(Context context) {
        if (!Utils.i()) {
            LogUtil.h("UIME_AchieveInteractors", "doMedalPageStartEvent return");
            return;
        }
        String b = mct.b(context, "_medalPngStatusDownloadDoing");
        if (TextUtils.isEmpty(b) || "done".equals(b)) {
            meh.c(context).y();
        }
        if (Utils.o()) {
            LogUtil.h("UIME_AchieveInteractors", "showAchieveMedal isOversea.");
            mct.b(context, "generateAchieveMedalTime", "");
        }
        meh.c(context).f();
        mgx.e(context).j(context);
    }

    public static void e(final Context context) {
        if (context == null) {
            LogUtil.h("UIME_AchieveInteractors", "judgeWechatIsBind context null");
        } else if (mer.b(context).j()) {
            LogUtil.h("UIME_AchieveInteractors", "judgeWechatIsBind return");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: sas.2
                @Override // java.lang.Runnable
                public void run() {
                    if ("true".equals(sbk.a(context).i(context))) {
                        LogUtil.a("UIME_AchieveInteractors", "judgeWechatIsBind done");
                        mer.b(context).e();
                    }
                }
            });
        }
    }

    public static void dUK_(Handler handler) {
        final Context context = BaseApplication.getContext();
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.interactors.AchieveInteractors$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                meh.c(context).g();
            }
        });
        dUJ_(handler);
        mgx.e(context).a(context);
        mgx.e(context).d(context);
        mgx.e(context).c(context);
    }

    public static void dUJ_(Handler handler) {
        dUN_(handler);
        dUM_(handler);
    }

    private static void dUN_(final Handler handler) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: sas.3
            @Override // java.lang.Runnable
            public void run() {
                mcv d2 = mcv.d(BaseApplication.getContext());
                PersonalData e = d2.e(BaseApplication.getContext());
                d dVar = new d(e, null);
                if (e != null) {
                    mct.b(BaseApplication.getContext(), "_personalCenterStatus", "[" + e.getSumKakaNum() + "," + e.getPersonalLevel() + "]");
                }
                Handler handler2 = handler;
                if (handler2 != null) {
                    handler.sendMessage(handler2.obtainMessage(80, dVar));
                } else {
                    LogUtil.h("UIME_AchieveInteractors", "doRefreshPersonalDataFromSQL innerHandler null");
                }
                d2.c(new PersonalDataCallback() { // from class: sas.3.3
                    @Override // com.huawei.pluginachievement.impl.PersonalDataCallback
                    public void onPersonalDataChange(PersonalData personalData) {
                        LogUtil.a("UIME_AchieveInteractors", "getPersonalData() back.");
                        d dVar2 = new d(personalData, null);
                        sas.b(sbc.c(personalData));
                        if (handler != null) {
                            handler.sendMessage(handler.obtainMessage(80, dVar2));
                        } else {
                            LogUtil.h("UIME_AchieveInteractors", "cloud innerHandler null");
                        }
                    }
                }, BaseApplication.getContext());
                sas.dUL_(BaseApplication.getContext(), handler, d2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("value", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KAKA_STATISTIC_1100089.value(), hashMap, 0);
    }

    private static void dUM_(final Handler handler) {
        if (handler == null) {
            LogUtil.h("UIME_AchieveInteractors", "doRefreshPersonalDataFromSQL handler null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: sas.5
                @Override // java.lang.Runnable
                public void run() {
                    mcv d2 = mcv.d(BaseApplication.getContext());
                    String b = mct.b(BaseApplication.getContext(), "_medalGainStatus");
                    if (!TextUtils.isEmpty(b) && handler != null) {
                        handler.sendMessage(handler.obtainMessage(80, new d(null, sas.a(b))));
                    }
                    ArrayList<String> c = d2.c(BaseApplication.getContext());
                    if (c != null) {
                        mct.b(BaseApplication.getContext(), "_medalGainStatus", c.toString());
                    }
                    if (handler != null) {
                        handler.sendMessage(handler.obtainMessage(80, new d(null, c)));
                        return;
                    }
                    LogUtil.h("UIME_AchieveInteractors", "refreshMedalLayout innerHandler null");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<String> a(String str) {
        String[] split;
        ArrayList<String> arrayList = new ArrayList<>(10);
        if (!TextUtils.isEmpty(str) && (split = str.replace("[", "").replace("]", "").replace(" ", "").split(",")) != null && split.length != 0) {
            Collections.addAll(arrayList, split);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dUL_(Context context, final Handler handler, mcv mcvVar) {
        String b = mct.b(context, "kakaLastCheckInTime");
        if (TextUtils.isEmpty(b)) {
            mcvVar.c(new IBaseResponseCallback() { // from class: sas.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("UIME_AchieveInteractors", "getKakaCheckStatus == ", obj);
                    Handler handler2 = handler;
                    if (handler2 != null) {
                        handler.sendMessage(handler2.obtainMessage(81, obj));
                    } else {
                        LogUtil.h("UIME_AchieveInteractors", "cloud innerHandler null");
                    }
                }
            });
        } else if (handler != null) {
            handler.sendMessage(handler.obtainMessage(81, Boolean.valueOf(mcx.d(b))));
        }
    }

    public static boolean d(final Context context) {
        if (context == null || CommonUtil.l(context) != 1 || !TextUtils.isEmpty(mct.b(context, "_medalTextureDownload"))) {
            return true;
        }
        LogUtil.c("UIME_AchieveInteractors", "init downloadTexture!");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.interactors.AchieveInteractors$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                meh.c(context).w();
            }
        });
        return false;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private ArrayList<String> f16990a;
        private PersonalData e;

        public d(PersonalData personalData, ArrayList<String> arrayList) {
            this.e = personalData;
            this.f16990a = arrayList;
        }

        public PersonalData e() {
            return this.e;
        }

        public ArrayList<String> c() {
            return this.f16990a;
        }
    }
}
