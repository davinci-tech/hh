package defpackage;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.util.EventBus;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository;
import defpackage.qvf;
import health.compact.a.DeviceUtil;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class qlc {
    private static final quv c;
    private static final qvc d;
    private volatile qvf e;

    static {
        qvc qvcVar = new qvc(k.b.m, 36000L, 0);
        d = qvcVar;
        c = new quv(qvcVar);
    }

    private qlc() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qld
                @Override // java.lang.Runnable
                public final void run() {
                    qlc.this.i();
                }
            });
        } else {
            b((qui) null);
        }
    }

    /* synthetic */ void i() {
        b((qui) null);
    }

    public static qlc b() {
        return b.f16466a;
    }

    private boolean c(qui quiVar) {
        if (quiVar == null || !quiVar.c() || quiVar.d() == null) {
            LogUtil.h("WeightCardDataManager", "FastingLiteAppSetting is not legal");
            return false;
        }
        qvc b2 = quiVar.d().b();
        if (b2 != null && b2.c() != 0 && b2.a() != 0) {
            return true;
        }
        LogUtil.h("WeightCardDataManager", "fastingLiteSetting is not legal");
        return false;
    }

    private void b(qui quiVar) {
        if (c(quiVar)) {
            this.e = new qvf(quiVar.d());
        }
        if (this.e == null) {
            d();
        }
        l();
    }

    public void j() {
        qve.a("custom.weight_fasting_lite_current_task");
        qve.a("custom.weight_fasting_lite_plan_task");
        qve.a();
        if (this.e != null && this.e.r()) {
            this.e.t();
            if (koq.b(this.e.g())) {
                LogUtil.h("WeightCardDataManager", "fasting phase is empty");
                return;
            } else {
                FastingLiteRepository.saveFastingLiteRecord(new qva(this.e), new IBaseResponseCallback() { // from class: qla
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        LogUtil.a("WeightCardDataManager", "save task record ", Integer.valueOf(i), " ", obj);
                    }
                });
                qve.c(this.e, new IBaseResponseCallback() { // from class: qkz
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        LogUtil.a("WeightCardDataManager", "saveFastingLitePhaseList errorCode= ", Integer.valueOf(i));
                    }
                });
                return;
            }
        }
        LogUtil.h("WeightCardDataManager", "stop task fail , mFastingLiteTask is null or not running");
    }

    public void e(List<qva> list) {
        if (list == null) {
            LogUtil.h("WeightCardDataManager", "currentTask2Record records is null");
            return;
        }
        List<qvf.d> g = this.e.g();
        if (this.e == null || !this.e.r() || koq.b(g)) {
            return;
        }
        qva qvaVar = new qva(this.e);
        qvaVar.c(g);
        qvaVar.c();
        list.add(0, qvaVar);
    }

    public void e(String str) {
        if (this.e == null || !this.e.r()) {
            return;
        }
        this.e.e(str);
        FastingLiteRepository.saveCurrentTask(this.e);
    }

    public boolean h() {
        return b(g());
    }

    public boolean b(qlh qlhVar) {
        CardConstants.CardType cardType;
        if (qlhVar == null) {
            return false;
        }
        if (qlhVar.e() != 0 && qlhVar.e() == qlhVar.a()) {
            LogUtil.h("WeightCardDataManager", "viewBean startTime = endTime");
            return false;
        }
        LogUtil.a("WeightCardDataManager", "refreshFastingLiteTask viewBean = ", qlhVar);
        if (qlhVar.b() == 2) {
            cardType = CardConstants.CardType.INTERMITTENT_PERIOD;
        } else {
            cardType = qlhVar.b() == 0 ? CardConstants.CardType.FASTING_CARD : CardConstants.CardType.DIET_CARD;
        }
        qlg qlgVar = new qlg(cardType, qlhVar);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productProtoType", qlgVar);
        EventBus.d(new EventBus.b("weight_message_from_producer", bundle));
        return true;
    }

    public boolean e() {
        return this.e != null && this.e.q();
    }

    public qvf a() {
        return this.e;
    }

    public boolean c() {
        if (this.e == null) {
            return true;
        }
        return this.e.o();
    }

    public void c(FastingLiteCbk<Object> fastingLiteCbk) {
        if (this.e == null || !this.e.r()) {
            Object[] objArr = new Object[2];
            objArr[0] = "changeWindowWhenClicked task is null or not run";
            objArr[1] = Boolean.valueOf(this.e == null);
            LogUtil.h("WeightCardDataManager", objArr);
            return;
        }
        this.e.s();
        h();
        if (DeviceUtil.a()) {
            quq.a().c("changeWindow", null);
        }
        FastingLiteRepository.saveCurrentTask(this.e, fastingLiteCbk);
    }

    public void a(long j) {
        if (this.e == null || !this.e.r()) {
            Object[] objArr = new Object[2];
            objArr[0] = "changeWindowWhenClicked task is null or not run";
            objArr[1] = Boolean.valueOf(this.e == null);
            LogUtil.h("WeightCardDataManager", objArr);
            return;
        }
        this.e.d(j);
        h();
        FastingLiteRepository.saveCurrentTask(this.e);
        if (DeviceUtil.a()) {
            quq.a().c("fromDialogSetting", null);
        }
    }

    public void d(qui quiVar) {
        if (quiVar == null || this.e == null) {
            return;
        }
        if (quiVar.c() && !this.e.r()) {
            LogUtil.a("WeightCardDataManager", "setting changed and start task");
            b(quiVar);
            return;
        }
        if (!quiVar.c() && this.e.r()) {
            LogUtil.a("WeightCardDataManager", "setting changed and stop task");
            this.e.v();
            j();
            return;
        }
        quv d2 = quiVar.d();
        quv h = this.e.h();
        if (d2 == null || h == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "fasting lite mode is null ";
            objArr[1] = Boolean.valueOf(d2 == null);
            LogUtil.h("WeightCardDataManager", objArr);
            return;
        }
        if (d2.c() != h.c()) {
            LogUtil.a("WeightCardDataManager", "changed fasting lite mode ", Integer.valueOf(d2.c()));
            this.e.v();
            j();
            b(quiVar);
            return;
        }
        e(d2.b(), h.b());
    }

    private void e(qvc qvcVar, qvc qvcVar2) {
        if (qvcVar == null || qvcVar2 == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "fasting lite setting is null ";
            objArr[1] = Boolean.valueOf(qvcVar == null);
            LogUtil.h("WeightCardDataManager", objArr);
            return;
        }
        if (qvcVar.c() != qvcVar2.c() || qvcVar.a() != qvcVar2.a()) {
            this.e.d(qvcVar.c(), qvcVar.a());
        }
        if (qvcVar.b() != qvcVar2.b()) {
            this.e.a(qvcVar.b() * 1000);
        }
        if (qvcVar.j() != qvcVar2.j()) {
            this.e.h().b().a(qvcVar.j());
        }
        if (!qvcVar.h().equals(qvcVar2.h())) {
            this.e.h().b().c(qvcVar.h());
        }
        if (qvcVar.f() != qvcVar2.f()) {
            this.e.h().b().b(qvcVar.f());
        }
        this.e.v();
        if (this.e.k()) {
            j();
        } else {
            this.e.a();
            FastingLiteRepository.saveCurrentTask(this.e);
        }
    }

    public qlh g() {
        if (this.e == null) {
            LogUtil.h("WeightCardDataManager", "makeFastingLiteViewBean mFastingLiteTask = null");
            return null;
        }
        this.e.v();
        if (this.e.k()) {
            j();
            return null;
        }
        if (!this.e.r()) {
            LogUtil.h("WeightCardDataManager", "makeFastingLiteViewBean mFastingLiteTask is not running");
            return null;
        }
        qlh qlhVar = new qlh();
        Resources resources = BaseApplication.getContext().getResources();
        String dFf_ = dFf_(resources, this.e.h().c());
        if (!TextUtils.isEmpty(dFf_)) {
            qlhVar.a(dFf_);
        }
        String string = resources.getString(R$string.IDS_wl_food_f_b_interval);
        if (this.e.o()) {
            LogUtil.a("WeightCardDataManager", "makeFastingLiteViewBean is intermittent period time");
            qlhVar.d(2);
            qlhVar.c(string);
            qlhVar.e(this.e.c());
            return qlhVar;
        }
        String string2 = resources.getString(R$string.IDS_wl_food_f_window);
        String string3 = resources.getString(R$string.IDS_wl_food_diet_window);
        boolean n = this.e.n();
        qlhVar.d(n ? 1 : 0);
        if (n) {
            string2 = string3;
        }
        qlhVar.c(string2);
        qlhVar.a(this.e.b());
        qlhVar.e(this.e.c());
        qlhVar.c(this.e.l());
        return qlhVar;
    }

    private String dFf_(Resources resources, int i) {
        if (i == 0) {
            return resources.getString(R$string.IDS_light_fasting_start_mode, 14, 10);
        }
        if (i == 1) {
            return resources.getString(R$string.IDS_light_fasting_advanced_mode, 16, 8);
        }
        if (i == 2) {
            return resources.getString(R$string.IDS_light_fasting_high_mode, 18, 6);
        }
        if (i != 3) {
            return i != 4 ? "" : resources.getString(R$string.IDS_light_fasting_custom_mode, Integer.valueOf(this.e.f()), Integer.valueOf(this.e.e()));
        }
        return resources.getString(R$string.IDS_light_fasting_challenge_mode, 20, 4);
    }

    private void l() {
        if (this.e == null) {
            qui c2 = qve.c();
            if (c(c2)) {
                this.e = new qvf(c2.d());
            }
        }
        if (this.e != null) {
            if (this.e.k()) {
                j();
                return;
            } else {
                FastingLiteRepository.saveCurrentTask(this.e);
                return;
            }
        }
        LogUtil.h("WeightCardDataManager", "init default task , task is not running");
        this.e = new qvf(c);
        this.e.a(false);
    }

    public void d() {
        qvf qvfVar;
        HiUserPreference f = f();
        if (f == null) {
            LogUtil.h("WeightCardDataManager", "initTaskFromSharePreference hiUserPreference is null");
            return;
        }
        try {
            LogUtil.a("WeightCardDataManager", "getFastingLiteTask key=", f.getKey(), "getFastingLiteTask task= ", f.getValue());
            qvfVar = (qvf) new Gson().fromJson(f.getValue(), qvf.class);
        } catch (JsonSyntaxException | NumberFormatException unused) {
            LogUtil.b("WeightCardDataManager", "parse FastingLiteAppSetting from json fail");
            qvfVar = null;
        }
        if (qvfVar != null) {
            this.e = qvfVar;
            m();
            this.e.p();
            return;
        }
        LogUtil.h("WeightCardDataManager", "initTaskFromSharePreference parse FastingLiteTask from json fail");
    }

    private HiUserPreference f() {
        qui e = qve.e("custom.weight_fasting_lite_setting");
        HiUserPreference userPreference = (e == null || !e.c()) ? null : HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_fasting_lite_current_task");
        if ((userPreference == null || TextUtils.isEmpty(userPreference.getValue())) && ((userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_fasting_lite_plan_task")) == null || TextUtils.isEmpty(userPreference.getValue()))) {
            LogUtil.h("WeightCardDataManager", "initTaskFromSharePreference get hiUserPreference is fail");
        }
        return userPreference;
    }

    private void m() {
        LogUtil.a("WeightCardDataManager", "parse fastingLiteWindows from database");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        qve.d(this.e, new FastingLiteCbk<List<qvf.d>>() { // from class: qlc.5
            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<qvf.d> list) {
                LogUtil.a("WeightCardDataManager", "readFastingLitePhase result: ", list);
                qlc.this.e.a(list);
                countDownLatch.countDown();
            }

            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            public void onFailure(int i, String str) {
                LogUtil.b("WeightCardDataManager", "readFastingLitePhase onFailure, ", str);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("WeightCardDataManager", "interrupted while waiting for getWeightControlSetting data");
        }
    }

    /* loaded from: classes6.dex */
    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private static final qlc f16466a = new qlc();
    }
}
