package com.huawei.ui.main.stories.discover.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.discover.provider.MemberWeightCardReader;
import defpackage.cfi;
import defpackage.doj;
import defpackage.dqj;
import defpackage.grz;
import defpackage.koq;
import defpackage.npt;
import defpackage.nrf;
import defpackage.pgk;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class MemberWeightCardReader extends MemberCardReader {
    private RecommendResourceInfo f;
    private RecommendCardBean.Builder g;
    private double h;
    private Bitmap j;
    private final List<Integer> k;
    private String l;
    private List<FitnessPackageInfo> m;
    private RecommendCardBean n;
    private boolean o;
    private SpannableString q;
    private cfi s;
    private String t;

    public MemberWeightCardReader(Context context) {
        super(context);
        List<Integer> asList = Arrays.asList(Integer.valueOf(HiSubscribeType.c), 11, 101, 100);
        this.k = asList;
        this.h = 0.0d;
        this.s = new cfi();
        this.g = new RecommendCardBean.Builder();
        this.t = "";
        this.l = "";
        this.q = null;
        this.j = null;
        LogUtil.d("VIPCard_MemberWeightCardReader", "MemberWeightCardReader");
        this.o = pgk.d(this.d);
        a(asList, this);
        c("vipPageBMI");
        a(AnalyticsValue.VIP_INTRO_RECOMMEND_BMI_CARD.value());
        j("VIPCard_MemberWeightCardReader");
        pgk.b(new IBaseResponseCallback() { // from class: pge
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MemberWeightCardReader.this.a(i, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (koq.e(obj, FitnessPackageInfo.class)) {
            List<FitnessPackageInfo> list = (List) obj;
            this.m = list;
            LogUtil.d("VIPCard_MemberWeightCardReader", "queryLabel mRecommendedPlans = ", list);
        }
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void queryLabel() {
        LogUtil.d("VIPCard_MemberWeightCardReader", "queryLabel");
        d(e() && e("slim_dmp"));
        if (b() == 0) {
            LogUtil.d("VIPCard_MemberWeightCardReader", "weight card reader:no privacy");
            if (k()) {
                z();
                return;
            } else {
                LogUtil.d("VIPCard_MemberWeightCardReader", "privacy status is not changed");
                return;
            }
        }
        if (!TextUtils.isEmpty(h()) && !l() && this.o == pgk.d(this.d) && !k()) {
            LogUtil.d("VIPCard_MemberWeightCardReader", "queryLabel: labelName is not empty and isChanged() = ", Boolean.valueOf(l()), " and mLastTempIntPlan = ", Boolean.valueOf(this.o));
        } else {
            w();
            v();
        }
    }

    private void w() {
        r();
        this.o = pgk.d(this.d);
    }

    private void v() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        grz.a(new c(countDownLatch));
        pgk.c(this.d, new b(countDownLatch));
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            LogUtil.e("VIPCard_MemberWeightCardReader", "time out");
        }
        z();
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void initCardReader(List<RecommendResourceInfo> list) {
        LogUtil.d("VIPCard_MemberWeightCardReader", "initCardReader");
        super.initCardReader(list);
        z();
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void unRegisterListener() {
        super.unRegisterListener();
        r();
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        String str;
        synchronized (this) {
            char c2 = 1;
            if (!n()) {
                LogUtil.d("VIPCard_MemberWeightCardReader", "recommendResource or listener not ready");
                return;
            }
            if (e() && e("slim_dmp")) {
                str = pgk.c(this.s, this.h, doj.a(r0.m(), this.s.d()));
            } else {
                str = "NO_PRIVACY";
            }
            RecommendResourceInfo d = d(g(str));
            this.f = d;
            if (d == null) {
                this.f = d(g("addRecord"));
            }
            RecommendResourceInfo recommendResourceInfo = this.f;
            if (recommendResourceInfo == null) {
                LogUtil.d("VIPCard_MemberWeightCardReader", "the mLabelResource of labelName is null");
                return;
            }
            LogUtil.d("VIPCard_MemberWeightCardReader", "Show coverPicture = ", recommendResourceInfo.getCoverPicture());
            this.j = nrf.cHT_(this.d, this.f.getCoverPicture());
            switch (str.hashCode()) {
                case -2129411413:
                    if (str.equals("startPlan")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -2114826614:
                    if (str.equals("gainWeightGoal")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -736424214:
                    if (str.equals("NO_PRIVACY")) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -42068910:
                    if (str.equals("addRecord")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 123192992:
                    if (str.equals("loseWeightGoal")) {
                        break;
                    }
                    c2 = 65535;
                    break;
                case 849920957:
                    if (str.equals("todoGainWeight")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1183430355:
                    if (str.equals("todoLoseWeight")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    u();
                    break;
                case 5:
                    s();
                    break;
                case 6:
                    q();
                    break;
            }
            ab();
        }
    }

    private String g(String str) {
        String str2 = d() + Constants.LINK + str;
        LogUtil.d("VIPCard_MemberWeightCardReader", "configCurrentLabel: labelName is ", str2);
        i(str);
        h(str2);
        return str2;
    }

    private void u() {
        this.t = this.f.getRecommendDescription();
        this.l = this.f.getResourceName();
        this.q = doI_();
    }

    private void s() {
        this.t = this.f.getRecommendDescription();
        this.l = this.f.getResourceName();
        String string = this.d.getResources().getString(R$string.IDS_member_sub_title_range, UnitUtil.e(doj.d[0], 1, 1), UnitUtil.e(doj.d[1], 1, 1));
        this.q = pgk.doL_(this.d, string, string);
    }

    private void q() {
        this.l = this.f.getResourceName();
        x();
        RecommendResourceInfo recommendResourceInfo = this.f;
        if (recommendResourceInfo == null) {
            LogUtil.e("VIPCard_MemberWeightCardReader", "no resource on cloud even no data label");
            return;
        }
        this.t = recommendResourceInfo.getRecommendDescription();
        this.j = nrf.cHT_(this.d, this.f.getCoverPicture());
        String string = this.d.getResources().getString(R$string.IDS_member_sub_title_range, UnitUtil.e(doj.d[0], 1, 1), UnitUtil.e(doj.d[1], 1, 1));
        this.q = pgk.doL_(this.d, string, string);
    }

    private void x() {
        RecommendResourceInfo d = d(g(pgk.d(this.d) ? "startPlan" : "gainWeightGoal"));
        this.f = d;
        if (d == null) {
            this.f = d(g("addRecord"));
        }
    }

    private void ab() {
        LogUtil.d("VIPCard_MemberWeightCardReader", "updateCardBeanData");
        RecommendResourceInfo recommendResourceInfo = this.f;
        if (recommendResourceInfo == null) {
            LogUtil.e("VIPCard_MemberWeightCardReader", "cloud do not have resource of label=", h());
            return;
        }
        b(recommendResourceInfo);
        this.g.setTitle(this.t);
        this.g.setSubtitle(this.l);
        this.g.setSubtitleDataLine(this.q);
        this.g.setBackGround(this.j);
        this.g.setTitleColor(Integer.valueOf(this.d.getResources().getColor(R.color._2131299373_res_0x7f090c2d)));
        this.g.setButtonText(this.f.getButtonDescription());
        this.g.setButtonColor(Integer.valueOf(this.d.getResources().getColor(R.color._2131299373_res_0x7f090c2d)));
        this.g.setCardId(d());
        this.g.setCardStatus(i());
        this.g.setResourceId(this.f.getResourceId());
        this.g.setEventId(c());
        this.g.setClickSectionListener(new npt() { // from class: com.huawei.ui.main.stories.discover.provider.MemberWeightCardReader.4
            @Override // defpackage.npt, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                MemberWeightCardReader.this.b(i, (RecommendResourceInfo) null);
            }
        });
        this.g.setLabelWeights(f());
        this.g.setSceneShowArticles(d(this.f));
        this.n = new RecommendCardBean(this.g);
        if (this.c == null || !koq.c(f())) {
            return;
        }
        this.c.onResponse(f().get(f().size() - 1).intValue(), this.n);
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader
    protected void a(int i, RecommendResourceInfo recommendResourceInfo) {
        d(i(), i);
        y();
    }

    private SpannableString doI_() {
        String string;
        int b2 = doj.b(doj.a(this.s.m(), this.s.d() <= 0 ? 173 : this.s.d()), Utils.o(), this.s.c(), this.s.a());
        if (b2 == 1) {
            string = this.d.getResources().getString(R$string.IDS_details_sleep_grade_low);
        } else if (b2 == 2) {
            string = this.d.getResources().getString(R$string.IDS_hw_weight_details_grade_standard);
        } else if (b2 == 3) {
            string = this.d.getResources().getString(R$string.IDS_hw_weight_details_grade_super_weight);
        } else {
            string = this.d.getResources().getString(R$string.IDS_hw_weight_details_grade_fat);
        }
        return pgk.doL_(this.d, string, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Object obj) {
        ObserverManagerUtil.c(str, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str, int i) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("VIPCard_MemberWeightCardReader", "handleWeightLabel: the weightLabel is empty");
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -2129411413:
                if (str.equals("startPlan")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -2114826614:
                if (str.equals("gainWeightGoal")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -42068910:
                if (str.equals("addRecord")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 123192992:
                if (str.equals("loseWeightGoal")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 849920957:
                if (str.equals("todoGainWeight")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1183430355:
                if (str.equals("todoLoseWeight")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 != 0) {
            if (c2 != 1) {
                if (c2 == 2) {
                    b(this.d);
                    return;
                } else if (c2 != 3) {
                    if (c2 != 4) {
                        if (c2 != 5) {
                            return;
                        }
                    }
                }
            }
            pgk.d(this.d, d(), i(), this.t, i);
            return;
        }
        a(i);
    }

    private void y() {
        if (this.f != null) {
            dqj.a(this.d, 1, new RecommendCardBean.Builder().setCardId("vipPageBMI").setCardStatus(h()).setResourceId(this.f.getResourceId()).setTitle(this.t).setButtonText(this.f.getButtonDescription()).setEventId(AnalyticsValue.VIP_INTRO_RECOMMEND_BMI_CARD.value()).build());
        }
    }

    private void a(final int i) {
        LogUtil.d("VIPCard_MemberWeightCardReader", "enter handleGoToAiFitnessPlan");
        if (koq.b(this.m)) {
            pgk.b(new IBaseResponseCallback() { // from class: pgl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MemberWeightCardReader.this.b(i, i2, obj);
                }
            });
            return;
        }
        pgk.d(this.d, d("huaweischeme://healthapp/fitnesspage?skip_type=introduce_plan&id=1&planTempId=" + this.m.get(0).acquirePlanTempId(), this.t, i));
    }

    public /* synthetic */ void b(int i, int i2, Object obj) {
        String str;
        if (koq.e(obj, FitnessPackageInfo.class)) {
            List<FitnessPackageInfo> list = (List) obj;
            this.m = list;
            LogUtil.d("VIPCard_MemberWeightCardReader", "mRecommendedPlans = ", list);
            if (!koq.b(this.m)) {
                str = this.m.get(0).acquirePlanTempId();
                LogUtil.d("VIPCard_MemberWeightCardReader", "planId = ", str);
                pgk.d(this.d, d("huaweischeme://healthapp/fitnesspage?skip_type=introduce_plan&id=1&planTempId=" + str, this.t, i));
            }
        }
        str = "JZ001";
        pgk.d(this.d, d("huaweischeme://healthapp/fitnesspage?skip_type=introduce_plan&id=1&planTempId=" + str, this.t, i));
    }

    private void b(Context context) {
        final HealthNumberPicker healthNumberPicker;
        final HealthNumberPicker healthNumberPicker2;
        LogUtil.d("VIPCard_MemberWeightCardReader", "enter showInputDialog");
        final HealthDataInputDialog d = new HealthDataInputDialog(context).c(ContextCompat.getColor(context, R.color._2131299373_res_0x7f090c2d)).e(context.getResources().getString(R$string.IDS_member_bmi_my_height_and_weight)).a(context.getResources().getString(R$string.IDS_member_bmi_set_weight_height_tips, DateUtils.formatDateTime(context, System.currentTimeMillis(), 16))).d(pgk.e(this.s)).d(this.d.getResources().getString(R$string.confirm));
        if (UnitUtil.h()) {
            View doK_ = pgk.doK_(context, this.s);
            healthNumberPicker = (HealthNumberPicker) doK_.findViewById(R.id.height_ft_number_picker_v9);
            healthNumberPicker2 = (HealthNumberPicker) doK_.findViewById(R.id.height_inch_number_picker_v9);
            doK_.findViewById(R.id.height_inch_number_picker_v9);
            d.czn_(doK_);
        } else {
            d.d(pgk.a(this.s));
            healthNumberPicker = null;
            healthNumberPicker2 = null;
        }
        d.c(new HealthDataInputDialog.PositiveBtnClickListener() { // from class: pgf
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.PositiveBtnClickListener
            public final void onClick(List list) {
                MemberWeightCardReader.this.d(healthNumberPicker, healthNumberPicker2, d, list);
            }
        });
        d.czq_(new View.OnClickListener() { // from class: pgj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MemberWeightCardReader.this.doJ_(d, view);
            }
        });
        d.show();
        qsj.c(this.d, AnalyticsValue.WEIGHT_PAGE_INPUT_DATA_2160122.value(), 0);
    }

    public /* synthetic */ void d(HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2, HealthDataInputDialog healthDataInputDialog, List list) {
        d(healthNumberPicker, healthNumberPicker2, (List<Integer>) list);
        qsj.c(this.d, AnalyticsValue.WEIGHT_PAGE_INPUT_DATA_2160122.value(), 2);
        healthDataInputDialog.dismiss();
    }

    public /* synthetic */ void doJ_(HealthDataInputDialog healthDataInputDialog, View view) {
        qsj.c(this.d, AnalyticsValue.WEIGHT_PAGE_INPUT_DATA_2160122.value(), 1);
        healthDataInputDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2, List<Integer> list) {
        if (!UnitUtil.h()) {
            this.s.d((int) ((list.get(1).intValue() / 10.0d) + 50.0d));
        } else if (healthNumberPicker != null && healthNumberPicker2 != null) {
            this.s.d((int) Math.rint((UnitUtil.d(healthNumberPicker.getValue() + 1, 1) * 100.0d) + UnitUtil.d(healthNumberPicker2.getValue(), 0)));
        }
        float a2 = a(list);
        if (this.s.m() <= 0.0f) {
            dqj.e(this.d, "2");
        } else {
            dqj.e(this.d, "1");
        }
        this.s.b(a2);
        LogUtil.d("VIPCard_MemberWeightCardReader", "Under metric: weight = ", Float.valueOf(a2), " height = ", Integer.valueOf(this.s.d()));
        b(this.s);
        pgk.a(a2);
    }

    private float a(List<Integer> list) {
        double intValue;
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            intValue = UnitUtil.c((list.get(0).intValue() / 10.0d) + UnitUtil.b(10.0d));
        } else if (a2 == 3) {
            intValue = UnitUtil.i((list.get(0).intValue() / 10.0d) + 22.0d);
        } else {
            intValue = (list.get(0).intValue() / 10.0d) + 10.0d;
        }
        return (float) intValue;
    }

    public void b(cfi cfiVar) {
        HiUserInfo hiUserInfo = new HiUserInfo();
        hiUserInfo.setWeight(cfiVar.m());
        hiUserInfo.setHeight(cfiVar.d());
        hiUserInfo.setUser(1073741824);
        hiUserInfo.setModifiedIntent(536870912);
        HiHealthNativeApi.a(BaseApplication.e()).setUserData(hiUserInfo, new AnonymousClass1());
    }

    /* renamed from: com.huawei.ui.main.stories.discover.provider.MemberWeightCardReader$1, reason: invalid class name */
    public class AnonymousClass1 implements HiCommonListener {
        AnonymousClass1() {
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            LogUtil.d("VIPCard_MemberWeightCardReader", "saveUserInfo onSuccess ", obj);
            ThreadPoolManager.d().execute(new Runnable() { // from class: pgm
                @Override // java.lang.Runnable
                public final void run() {
                    MemberWeightCardReader.AnonymousClass1.this.c();
                }
            });
        }

        public /* synthetic */ void c() {
            MemberWeightCardReader.this.z();
            MemberWeightCardReader memberWeightCardReader = MemberWeightCardReader.this;
            memberWeightCardReader.a("MEMBER_OPEN_BMI_CHANGE", memberWeightCardReader.n);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.c("VIPCard_MemberWeightCardReader", "setUserData onFailure: ", Integer.valueOf(i), " ", obj);
        }
    }

    static class c implements ResponseCallback<Double> {
        private CountDownLatch b;
        private WeakReference<MemberWeightCardReader> c;

        private c(MemberWeightCardReader memberWeightCardReader, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(memberWeightCardReader);
            this.b = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Double d) {
            MemberWeightCardReader memberWeightCardReader = this.c.get();
            if (memberWeightCardReader != null) {
                memberWeightCardReader.h = d.doubleValue();
                LogUtil.d("VIPCard_MemberWeightCardReader", "mGoalWeight = ", Double.valueOf(memberWeightCardReader.h));
                this.b.countDown();
                return;
            }
            LogUtil.c("VIPCard_MemberWeightCardReader", "WeakTargetWeightCallback, the reader is null");
        }
    }

    static class b implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final CountDownLatch f9709a;
        private WeakReference<MemberWeightCardReader> d;

        private b(MemberWeightCardReader memberWeightCardReader, CountDownLatch countDownLatch) {
            this.d = new WeakReference<>(memberWeightCardReader);
            this.f9709a = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.d("VIPCard_MemberWeightCardReader", "errorCode is ", Integer.valueOf(i));
            MemberWeightCardReader memberWeightCardReader = this.d.get();
            if (memberWeightCardReader == null) {
                LogUtil.c("VIPCard_MemberWeightCardReader", "WeakBaseResponseCallback, the reader is null");
                this.f9709a.countDown();
                return;
            }
            if (obj instanceof cfi) {
                memberWeightCardReader.s = (cfi) obj;
            }
            if (memberWeightCardReader.s != null) {
                LogUtil.d("VIPCard_MemberWeightCardReader", "weight = ", Float.valueOf(memberWeightCardReader.s.m()), " height = ", Integer.valueOf(memberWeightCardReader.s.d()), " age = ", Integer.valueOf(memberWeightCardReader.s.a()), " gender() = ", Byte.valueOf(memberWeightCardReader.s.c()));
            }
            this.f9709a.countDown();
        }
    }
}
