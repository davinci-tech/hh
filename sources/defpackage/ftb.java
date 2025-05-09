package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessTopicActivity;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import defpackage.mmr;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class ftb {

    /* renamed from: a, reason: collision with root package name */
    private Uri f12639a;
    private Context b;
    private String c;
    private mmr d;
    private Bundle e;
    private String g;

    private static boolean c(int i) {
        return i >= 4062 && i <= 4120;
    }

    public ftb(Context context, Uri uri, Bundle bundle) {
        this.f12639a = null;
        this.b = context;
        this.e = bundle;
        if (bundle != null) {
            LogUtil.a("Suggestion_FitnessJumpManager", "extra:", bundle.toString());
        }
        if (uri == null || uri.getQuery() == null) {
            return;
        }
        LogUtil.a("Suggestion_FitnessJumpManager", "fitness scheme query = ", uri.getQuery());
        this.f12639a = uri;
        this.g = uri.getQueryParameter("skip_type");
        this.d = aGm_(uri);
        aGn_(uri);
    }

    private void i() {
        if (this.b == null) {
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(335544320);
        if (!TextUtils.isEmpty(this.d.f())) {
            intent.putExtra("fitness", this.d.f());
        }
        intent.setClass(this.b, FitnessResultActivity.class);
        gnm.aPB_(this.b, intent);
    }

    public void b() {
        if (this.b == null) {
            return;
        }
        int b = fhp.c().b();
        if (b == 2) {
            AppRouter.b("/PluginFitnessAdvice/CoachActivity").c(this.b);
            return;
        }
        if (b == 5) {
            gnm.aPB_(this.b, new Intent(this.b, (Class<?>) LongCoachActivity.class));
            return;
        }
        if (this.d == null) {
            this.d = new mmr.d().o("0").g("0").c();
            e("FITNESS_COURSE");
        } else {
            f();
        }
        this.b = null;
    }

    private boolean h() {
        Uri uri;
        Bundle bundle = this.e;
        if (bundle == null) {
            return false;
        }
        if (bundle.get("android.intent.extra.REFERRER") != null && (this.e.get("android.intent.extra.REFERRER") instanceof Uri) && (uri = (Uri) this.e.get("android.intent.extra.REFERRER")) != null) {
            LogUtil.a("Suggestion_FitnessJumpManager", "referrer:", uri.toString());
            return uri.toString().contains("com.huawei.ohos.health.device");
        }
        return this.e.toString().contains("com.huawei.ohos.health.device");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x005c, code lost:
    
        if (r0.equals("all_fitness_run") == false) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f() {
        /*
            r8 = this;
            java.lang.String r0 = r8.g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 == 0) goto Ld
            r8.c(r1)
            return
        Ld:
            java.lang.String r0 = r8.g
            r0.hashCode()
            int r2 = r0.hashCode()
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r2) {
                case -797062634: goto L56;
                case -251149995: goto L4b;
                case 1080327289: goto L40;
                case 1213636282: goto L35;
                case 1484532308: goto L2a;
                case 1620088138: goto L1f;
                default: goto L1e;
            }
        L1e:
            goto L5e
        L1f:
            java.lang.String r1 = "all_fitness"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L28
            goto L5e
        L28:
            r1 = r3
            goto L5f
        L2a:
            java.lang.String r1 = "fitness_result"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L33
            goto L5e
        L33:
            r1 = r4
            goto L5f
        L35:
            java.lang.String r1 = "all_health"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3e
            goto L5e
        L3e:
            r1 = r5
            goto L5f
        L40:
            java.lang.String r1 = "all_course"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L49
            goto L5e
        L49:
            r1 = r6
            goto L5f
        L4b:
            java.lang.String r1 = "main_page"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L54
            goto L5e
        L54:
            r1 = r7
            goto L5f
        L56:
            java.lang.String r2 = "all_fitness_run"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L5f
        L5e:
            r1 = -1
        L5f:
            java.lang.String r0 = "RUNNING_COURSE"
            if (r1 == 0) goto L9e
            if (r1 == r7) goto L9a
            if (r1 == r6) goto L94
            if (r1 == r5) goto L90
            if (r1 == r4) goto L8c
            if (r1 == r3) goto L71
            r8.k()
            goto La1
        L71:
            boolean r1 = com.huawei.health.sport.utils.SportSupportUtil.a()
            if (r1 == 0) goto L7d
            java.lang.String r0 = "FITNESS_COURSE"
            r8.e(r0)
            goto La1
        L7d:
            java.lang.String r1 = "gotoTargetFitnessPage not fitness open"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "Suggestion_FitnessJumpManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            r8.e(r0)
            goto La1
        L8c:
            r8.i()
            goto La1
        L90:
            r8.e()
            goto La1
        L94:
            java.lang.String r0 = ""
            r8.e(r0)
            goto La1
        L9a:
            r8.q()
            goto La1
        L9e:
            r8.e(r0)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ftb.f():void");
    }

    private void e(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", CommonUtil.e(this.d.k(), 0));
        bundle.putString("SKIP_ALL_COURSE_KEY", str);
        bundle.putString("KEY_SECOND_CATEGORY_INDEX", this.d.i());
        bundle.putString("COURSE_PAGE_TYPE", this.d.m());
        bundle.putBoolean("moveTaskToBack", h());
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.b);
        ggr.c(gge.c(str), (TextUtils.isEmpty(this.c) || !"fa".equals(this.c)) ? 7 : 9);
    }

    private void e() {
        Bundle bundle = new Bundle();
        bundle.putInt("index", CommonUtil.e(this.d.k(), 0));
        bundle.putString("SKIP_ALL_COURSE_KEY", "HEALTH_COURSE");
        bundle.putString("COURSE_PAGE_TYPE", this.d.m());
        bundle.putString(WebViewHelp.BI_KEY_PULL_FROM, this.d.q());
        bundle.putString("resourceId", this.d.r());
        bundle.putString("resourceName", this.d.t());
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).c(this.b);
    }

    private void c(boolean z) {
        if (this.d.k() == null || this.d.y() == null) {
            LogUtil.h("Suggestion_FitnessJumpManager", "id == null || version == null");
            return;
        }
        String a2 = a();
        mmp mmpVar = new mmp(this.d.k());
        mmpVar.c(true);
        mmpVar.r(this.d.y());
        mmpVar.a(this.d.g());
        mmpVar.d(a2);
        mmpVar.q(this.d.w());
        mmpVar.g(this.d.p());
        mmpVar.j(this.d.q());
        mmpVar.o(this.d.r());
        mmpVar.k(this.d.t());
        mmpVar.l(this.d.s());
        mmpVar.b(this.d.c());
        mmpVar.f(this.d.t());
        mmpVar.e(this.d.n());
        mmpVar.c(this.d.d());
        mmpVar.f(h());
        if (z) {
            mmpVar.g(1);
            mmpVar.i(true);
        }
        mod.c(BaseApplication.getContext(), mmpVar);
    }

    private void d() {
        boolean a2 = ggx.a();
        boolean d = fyc.d();
        boolean a3 = gij.a();
        LogUtil.a("Suggestion_FitnessJumpManager", "gotoCustomCourse isBlueToothEnable:", Boolean.valueOf(a2), " isConnectWearDevice:", Boolean.valueOf(d), " isSupportCustomCourse:", Boolean.valueOf(a3));
        if (a2 && d && a3) {
            c(true);
        } else {
            JumpUtil.d(this.b, 3);
        }
    }

    private String a() {
        if ("operationPushMsg".equals(this.d.o())) {
            return "11";
        }
        if (c(CommonUtil.h(this.d.q()))) {
            return "6";
        }
        String g = this.d.g();
        return "1".equals(g) ? "7" : g;
    }

    private void q() {
        Intent intent = new Intent();
        intent.setClassName(this.b, "com.huawei.health.MainActivity");
        if (nsn.ae(BaseApplication.getContext())) {
            Context context = this.b;
            if ((context instanceof Activity) && ((Activity) context).getWindowManager().getDefaultDisplay().getRotation() == 1) {
                intent.setFlags(131072);
                intent.putExtra(BleConstants.SPORT_TYPE, CommonUtil.b(this.b, this.d.j(), 10001));
                intent.putExtra("isToSportTab", true);
                intent.putExtra("mLaunchSource", 6);
                gnm.aPB_(this.b, intent);
            }
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(BleConstants.SPORT_TYPE, CommonUtil.b(this.b, this.d.j(), 10001));
        intent.putExtra("isToSportTab", true);
        intent.putExtra("mLaunchSource", 6);
        gnm.aPB_(this.b, intent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void k() {
        char c;
        String str = this.g;
        str.hashCode();
        switch (str.hashCode()) {
            case -1791977281:
                if (str.equals("serise_course")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1354571749:
                if (str.equals("course")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1157419490:
                if (str.equals("course_details")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1139670261:
                if (str.equals("train_details")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -357990087:
                if (str.equals("action_details")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 367587885:
                if (str.equals("ai_action")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 408454643:
                if (str.equals("coach_sporting")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 706658473:
                if (str.equals("custom_course")) {
                    c = 7;
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
                Intent intent = new Intent(this.b, (Class<?>) FitnessSeriesCourseActivity.class);
                intent.setFlags(268435456);
                gnm.aPB_(this.b, intent);
                break;
            case 1:
                m();
                break;
            case 2:
                n();
                break;
            case 3:
                c(false);
                break;
            case 4:
            case 5:
                g();
                break;
            case 6:
                dod.b(this.b, this.d.k());
                break;
            case 7:
                d();
                break;
            default:
                o();
                break;
        }
    }

    private void g() {
        Bundle bundle = new Bundle();
        bundle.putString("action_id", this.d.k());
        bundle.putString("action_version", this.d.y());
        bundle.putString(WebViewHelp.BI_KEY_PULL_FROM, this.d.q());
        bundle.putString("resourceId", this.d.r());
        bundle.putString("resourceName", this.d.t());
        bundle.putString("pullOrder", this.d.s());
        bundle.putString("courseAttr", this.d.a());
        AppRouter.b("/PluginFitnessAdvice/FitnessActionDetailActivity").zF_(bundle).c(this.b);
    }

    private void m() {
        int e = CommonUtil.e(this.d.k(), 4);
        Intent intent = new Intent(this.b, (Class<?>) FitnessTopicActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("intent_key_topicid", e);
        gnm.aPB_(this.b, intent);
    }

    private void n() {
        if (this.d.k() == null) {
            LogUtil.h("Suggestion_FitnessJumpManager", "id == null");
            return;
        }
        Intent intent = new Intent(this.b, (Class<?>) FitnessSeriesCourseDetailsActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("intent_key_topicid", CommonUtil.h(this.d.k()));
        intent.putExtra("intent_key_topicname", this.d.e());
        intent.putExtra("intent_key_description", this.d.b());
        gnm.aPB_(this.b, intent);
    }

    private void o() {
        char c;
        s();
        String str = this.g;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1642698313) {
            if (str.equals("introduce_plan")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1798153991) {
            if (hashCode == 1834428549 && str.equals("goto_plan")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("all_plan")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            j();
            return;
        }
        if (c == 1) {
            l();
        } else if (c == 2) {
            c();
        } else {
            r();
        }
    }

    private void l() {
        HashMap hashMap = new HashMap();
        hashMap.put("target", Integer.toString(IntPlan.PlanType.NA_PLAN.getType()));
        JumpUtil.b(this.b, hashMap);
    }

    private void j() {
        if (this.d.k() == null) {
            LogUtil.h("Suggestion_FitnessJumpManager", "id == null");
        } else {
            final int e = CommonUtil.e(this.d.k(), -1);
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: fte
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ftb.this.a(e, i, obj);
                }
            }, "");
        }
    }

    /* synthetic */ void a(int i, int i2, Object obj) {
        if (i2 == 0) {
            JumpUtil.d(i, this.d.p(), this.d.q(), this.b);
        } else {
            LogUtil.a("Suggestion_FitnessJumpManager", "need to login.", Integer.valueOf(i2));
        }
    }

    private void c() {
        if (this.d.k() == null) {
            LogUtil.h("Suggestion_FitnessJumpManager", "id == null");
            return;
        }
        final int h = CommonUtil.h(this.d.k());
        if (this.f12639a != null && h == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            bzs.e().putBiEventFromH5Deeplink(this.f12639a.toString(), "com.huawei.health.h5.ai-weight");
        }
        final int b = CommonUtil.b(this.b, this.d.h(), 0);
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ftf
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ftb.this.c(h, b, i, obj);
            }
        }, "");
    }

    /* synthetic */ void c(int i, int i2, int i3, Object obj) {
        if (i3 == 0) {
            JumpUtil.d(i, this.d.p(), i2, this.d.q(), this.d.l(), this.b);
        } else {
            LogUtil.a("Suggestion_FitnessJumpManager", "need to login.", Integer.valueOf(i3));
        }
    }

    private void r() {
        if (this.g.equals("history_plan_report")) {
            d(2);
        } else if (this.g.equals("week_report")) {
            d(1);
        } else {
            LogUtil.b("Suggestion_FitnessJumpManager", "skip type error:", this.g);
        }
    }

    private void d(final int i) {
        if (this.d.k() == null) {
            LogUtil.h("Suggestion_FitnessJumpManager", "id == null");
            return;
        }
        final int e = CommonUtil.e(this.d.b(), -1);
        final int e2 = CommonUtil.e(this.d.h(), IntPlan.PlanType.FIT_PLAN.getType());
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: fth
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                ftb.this.a(i, e, e2, i2, obj);
            }
        }, "");
    }

    /* synthetic */ void a(int i, int i2, int i3, int i4, Object obj) {
        if (i4 == 0) {
            JumpUtil.c(i, this.b, i2, this.d.k(), i3);
        } else {
            LogUtil.a("Suggestion_FitnessJumpManager", "need to login.", Integer.valueOf(i4));
        }
    }

    private mmr aGm_(Uri uri) {
        String str;
        String str2 = "Suggestion_FitnessJumpManager";
        try {
            String queryParameter = uri.getQueryParameter("id");
            String queryParameter2 = uri.getQueryParameter("version");
            String queryParameter3 = uri.getQueryParameter("acquire_name");
            String queryParameter4 = uri.getQueryParameter("description");
            String queryParameter5 = uri.getQueryParameter("fitness_type");
            String queryParameter6 = uri.getQueryParameter("planTempId");
            String queryParameter7 = uri.getQueryParameter("equipments");
            String queryParameter8 = uri.getQueryParameter("train_detail_entrance_type");
            String queryParameter9 = uri.getQueryParameter("workoutPackageId");
            String queryParameter10 = uri.getQueryParameter("resourceType");
            String queryParameter11 = uri.getQueryParameter("displayStyle");
            String queryParameter12 = uri.getQueryParameter("fitnessresult");
            String queryParameter13 = uri.getQueryParameter("fromPageTitle");
            String queryParameter14 = uri.getQueryParameter("from");
            str = "Suggestion_FitnessJumpManager";
            try {
                String queryParameter15 = uri.getQueryParameter("pageTypeId");
                String queryParameter16 = uri.getQueryParameter("courseAttr");
                Map<String, String> aGl_ = aGl_(uri);
                return new mmr.d().o(queryParameter).w(queryParameter2).a(queryParameter3).b(queryParameter4).i(queryParameter5).q(queryParameter6).g(queryParameter7).f(queryParameter8).x(queryParameter9).y(queryParameter10).j(queryParameter11).h(queryParameter12).r(aGl_.get(WebViewHelp.BI_KEY_PULL_FROM)).k(queryParameter13).t(aGl_.get("resourceId")).s(aGl_.get("resourceName")).p(aGl_.get("pullOrder")).c(aGl_.get("algId")).l(queryParameter14).n(queryParameter15).m(aGl_.get("itemId")).e(aGl_.get(x2.AB_INFO)).d(queryParameter16).c();
            } catch (IllegalArgumentException unused) {
                str2 = str;
                LogUtil.b(str2, "parseFitnessJumpParams IllegalArgumentException");
                return new mmr.d().c();
            } catch (UnsupportedOperationException unused2) {
                LogUtil.b(str, "parseFitnessJumpParams UnsupportedOperationException");
                return new mmr.d().c();
            }
        } catch (IllegalArgumentException unused3) {
        } catch (UnsupportedOperationException unused4) {
            str = "Suggestion_FitnessJumpManager";
        }
    }

    private void s() {
        fhu.e().d(this.d.q());
        fhu.e().e(this.d.r());
        fhu.e().a(this.d.s());
        fhu.e().c(this.d.t());
    }

    private void aGn_(final Uri uri) {
        Context context = BaseApplication.getContext();
        String queryParameter = uri.getQueryParameter("from");
        this.c = queryParameter;
        LogUtil.a("Suggestion_FitnessJumpManager", "setIntentBi:", queryParameter);
        if (AuthorizationUtils.a(context) && "fa".equals(this.c)) {
            ixx.d().a(LoginInit.getInstance(context).getAccountInfo(1011));
            ixx.d().e(LoginInit.getInstance(context).getAccountInfo(1010));
            OpAnalyticsUtil.getInstance().init(context, new IBaseResponseCallback() { // from class: ftc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ftb.this.aGo_(uri, i, obj);
                }
            });
        }
    }

    /* synthetic */ void aGo_(Uri uri, int i, Object obj) {
        d(uri.getQueryParameter("FAPackageName"));
    }

    private void d(final String str) {
        LogUtil.a("Suggestion_FitnessJumpManager", "doBiFromFaStartApp");
        ThreadPoolManager.d().execute(new Runnable() { // from class: ftg
            @Override // java.lang.Runnable
            public final void run() {
                ftb.b(str);
            }
        });
    }

    static /* synthetic */ void b(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("from", 1);
        hashMap.put("click", 1);
        if (str != null) {
            hashMap.put("FAPackageName", str);
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    private Map<String, String> aGl_(Uri uri) {
        Map<String, String> globalParams = Analyzer.getGlobalParams();
        LogUtil.a("Suggestion_FitnessJumpManager", "globalParams:", globalParams.toString());
        HashMap hashMap = new HashMap(7);
        String queryParameter = uri.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        if (TextUtils.isEmpty(queryParameter)) {
            queryParameter = globalParams.get(WebViewHelp.BI_KEY_PULL_FROM);
        }
        String queryParameter2 = uri.getQueryParameter("pullOrder");
        if (TextUtils.isEmpty(queryParameter2)) {
            queryParameter2 = globalParams.get("pullOrder");
        }
        String queryParameter3 = uri.getQueryParameter("resourceId");
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = globalParams.get("resourceId");
        }
        String queryParameter4 = uri.getQueryParameter("resourceName");
        if (TextUtils.isEmpty(queryParameter4)) {
            queryParameter4 = globalParams.get("resourceName");
        }
        String queryParameter5 = uri.getQueryParameter("algId");
        if (TextUtils.isEmpty(queryParameter5)) {
            queryParameter5 = globalParams.get("algId");
        }
        String queryParameter6 = uri.getQueryParameter("itemId");
        if (TextUtils.isEmpty(queryParameter6)) {
            queryParameter6 = globalParams.get("itemId");
        }
        String queryParameter7 = uri.getQueryParameter(x2.AB_INFO);
        if (TextUtils.isEmpty(queryParameter7)) {
            queryParameter7 = globalParams.get(x2.AB_INFO);
        }
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, queryParameter);
        hashMap.put("pullOrder", queryParameter2);
        hashMap.put("resourceId", queryParameter3);
        hashMap.put("resourceName", queryParameter4);
        hashMap.put("algId", queryParameter5);
        hashMap.put("itemId", queryParameter6);
        hashMap.put(x2.AB_INFO, queryParameter7);
        return hashMap;
    }

    public static int c(String str) {
        return "weight".equals(str) ? 1 : 0;
    }
}
