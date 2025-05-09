package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.SectionMessageWindow;
import com.huawei.health.vip.api.VipApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureMeasurePlanActivity;
import com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureMessageProvider;
import defpackage.bzs;
import defpackage.drx;
import defpackage.dsb;
import defpackage.dsl;
import defpackage.dso;
import defpackage.gib;
import defpackage.gnm;
import defpackage.gpn;
import defpackage.gpp;
import defpackage.ixx;
import defpackage.koq;
import defpackage.njn;
import defpackage.nsn;
import defpackage.pug;
import defpackage.qqt;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class BloodPressureMessageProvider extends BaseKnitDataProvider {
    private static int b = -1;
    private static boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private DeviceInboxInfo f10157a;
    private int c;
    private int e;
    private String g;
    private String h;
    private JSONObject i;
    private boolean k;
    private String l;
    private boolean m;
    private String o;
    private boolean q;
    private boolean r;
    private long s;
    private boolean t;
    private SectionMessageWindow.d v;
    private String w;
    private SectionBean x;
    private PayApi y;
    private int j = 0;
    private int u = 2;
    private volatile drx n = new drx();
    private boolean f = false;
    private boolean p = false;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("BloodPressureMessageProvider", "loadData");
        this.x = sectionBean;
        if (isActive(context)) {
            a();
        }
        this.x = sectionBean;
        j();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("BloodPressureMessageProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.a(e(BaseApplication.getContext()));
        sectionBean.e(new Object());
    }

    private void j() {
        b = -1;
        this.y = (PayApi) Services.a("TradeService", PayApi.class);
        List<DeviceBenefitQueryParam> e = njn.e("BloodPressureMessageProvider", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_INBOX);
        PayApi payApi = this.y;
        if (payApi != null) {
            payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: qhn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    BloodPressureMessageProvider.this.a(i, obj);
                }
            });
            if (!koq.b(e)) {
                this.y.getAllDeviceBenefits(e, new c(this));
                return;
            } else {
                LogUtil.h("BloodPressureMessageProvider", "getData() queryParamList isEmpty");
                return;
            }
        }
        LogUtil.h("BloodPressureMessageProvider", "payApi is null");
        d = false;
    }

    public /* synthetic */ void a(int i, Object obj) {
        LogUtil.b("BloodPressureMessageProvider", "errorCode " + i);
        if (i == 0 && (obj instanceof RepeatResourceBenefitInfo)) {
            LogUtil.b("BloodPressureMessageProvider", "objData instanceof RepeatResourceBenefitInfo");
            RepeatResourceBenefitInfo repeatResourceBenefitInfo = (RepeatResourceBenefitInfo) obj;
            d = d(repeatResourceBenefitInfo);
            e(repeatResourceBenefitInfo);
            return;
        }
        LogUtil.b("BloodPressureMessageProvider", "objData ! instanceof RepeatResourceBenefitInfo");
        d = false;
    }

    private void e(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        if (TextUtils.isEmpty(repeatResourceBenefitInfo.getExtendInfo())) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(repeatResourceBenefitInfo.getExtendInfo());
            if (jSONObject.has("benefitType")) {
                int i = jSONObject.getInt("benefitType");
                if (i == 1) {
                    b = 1;
                } else if (i == 0) {
                    b = 2;
                } else {
                    b = -1;
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureMessageProvider", "JSONException");
        }
    }

    private boolean d(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        return repeatResourceBenefitInfo.getExpireTime().longValue() >= c(repeatResourceBenefitInfo);
    }

    private long c(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        return repeatResourceBenefitInfo.getNowTime() != 0 ? repeatResourceBenefitInfo.getNowTime() : System.currentTimeMillis();
    }

    private HashMap<String, Object> e(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("RIGHT_ICON_IMAGE", ContextCompat.getDrawable(context, R.drawable.ic_doctor_icon));
        hashMap.put("MIDDLE_ICON_IMAGE", ContextCompat.getDrawable(context, R.drawable.ic_doctor_message_green));
        hashMap.put("ITEM_BUTTON_TEXT", context.getResources().getString(R$string.IDS_hw_doctor_button));
        hashMap.put("GUIDE_BUBBLE_TEXT", context.getResources().getString(R$string.IDS_hw_doctor_dialogue));
        hashMap.put("DOCTOR_ITEM_DESCRIPTION", context.getResources().getString(R$string.IDS_hw_doctor_message));
        hashMap.put("LEFT_BUTTON_VISIBILITY", Boolean.valueOf(Utils.i() && CommonUtil.aa(context) && Utils.k()));
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("BloodPressureMessageProvider", "parseParams");
        d(context, hashMap, obj);
        e(context, hashMap);
        e(hashMap);
    }

    private void d(Context context, HashMap hashMap, Object obj) {
        StringBuilder sb = new StringBuilder("setData, setBindingResource, data is instance of HealthLifeChallengeBean: ");
        boolean z = obj instanceof drx;
        sb.append(z);
        LogUtil.a("BloodPressureMessageProvider", sb.toString());
        hashMap.put("TITLE", context.getResources().getString(R$string.IDS_bloodpressure_manager));
        Resources resources = context.getResources();
        int i = this.j;
        hashMap.put("RIGHT_ICON_TEXT", resources.getQuantityString(R.plurals._2130903097_res_0x7f030039, i, Integer.valueOf(i)));
        hashMap.put("LEFT_ICON_IMAGE", ContextCompat.getDrawable(context, R.drawable._2131427618_res_0x7f0b0122));
        hashMap.put("RIGHT_BUTTON_TOP_DES", context.getResources().getString(R$string.IDS_blood_pressure_measure_plan));
        hashMap.put("RIGHT_BUTTON_BOTTOM_DES", context.getResources().getString(R$string.IDS_hw_challenge_timed_reminder_measurement));
        hashMap.put("RIGHT_BUTTON_IMAGE", ContextCompat.getDrawable(context, R.drawable._2131427624_res_0x7f0b0128));
        d(hashMap);
        if (z) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
            hashMap.put("ITEM_DESCRIPTION", context.getResources().getString(R$string.IDS_guardian_health_management));
            d(context, hashMap, (drx) obj);
        } else {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", true);
            hashMap.put("ITEM_DESCRIPTION", context.getResources().getString(R$string.IDS_guardian_health_management));
            hashMap.put("ITEM_DESCRIPTION_BOTTOM", context.getResources().getString(R$string.IDS_blood_pressure_protection));
        }
        if (TextUtils.isEmpty(this.o)) {
            return;
        }
        hashMap.put("ITEM_DESCRIPTION_BOTTOM", this.o);
    }

    private void e(Context context, HashMap hashMap) {
        if (hashMap == null || context == null) {
            return;
        }
        hashMap.put("IS_REMIND_SHOW", Boolean.valueOf(this.p));
        hashMap.put("BANNER_CARD_CONTENT", this.w);
        hashMap.put("BANNER_CARD_SUBMIT", BaseApplication.getContext().getString(R$string.IDS_h5_vip_active_now));
    }

    private void d(final HashMap hashMap) {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.s) <= 5000) {
            if (this.t) {
                d(hashMap, this.i);
            }
        } else {
            this.s = currentTimeMillis;
            pug.a().getDoctorBasicInfo(new DataCallback() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureMessageProvider.4
                @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("BloodPressureMessageProvider", "fail to get doctor message");
                    BloodPressureMessageProvider.this.t = true;
                    BloodPressureMessageProvider.this.i = null;
                    BloodPressureMessageProvider bloodPressureMessageProvider = BloodPressureMessageProvider.this;
                    bloodPressureMessageProvider.d(hashMap, bloodPressureMessageProvider.i);
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    BloodPressureMessageProvider.this.t = true;
                    BloodPressureMessageProvider.this.i = jSONObject;
                    BloodPressureMessageProvider.this.d(hashMap, jSONObject);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HashMap hashMap, JSONObject jSONObject) {
        SectionMessageWindow.d dVar = new SectionMessageWindow.d();
        if (jSONObject == null || jSONObject.optInt("resultCode") == 1 || TextUtils.isEmpty(jSONObject.optString("doctorId")) || "0".equals(jSONObject.optString("doctorId"))) {
            LogUtil.a("BloodPressureMessageProvider", "don‘t has doctor info , privilege is： ", Boolean.valueOf(d));
            dVar.e(d);
            dVar.c(false);
            dVar.d("");
            dVar.e("");
            dVar.b("");
            hashMap.put("TOP_CARD_BEAN", dVar);
            if (dVar.a(this.v)) {
                LogUtil.a("BloodPressureMessageProvider", "same topCardBean");
                return;
            } else {
                this.v = dVar;
                this.x.e(new Object());
                return;
            }
        }
        LogUtil.a("BloodPressureMessageProvider", "doctor info is " + jSONObject);
        this.l = String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_doctor_name), jSONObject.optString("doctorName"));
        this.g = String.format(Locale.ROOT, "%s %s", jSONObject.optString("doctorDepartment"), jSONObject.optString("doctorLevel"));
        this.h = jSONObject.optString("doctorHeadIcon");
        dVar.d(this.l);
        dVar.e(this.g);
        dVar.b(this.h);
        LogUtil.a("BloodPressureMessageProvider", "if not join privilege " + d);
        if (d) {
            LogUtil.a("BloodPressureMessageProvider", "has join privilege");
            dVar.e(true);
            dVar.c(true);
            this.q = true;
        } else {
            LogUtil.a("BloodPressureMessageProvider", "has not join privilege");
            dVar.e(false);
            dVar.c(true);
            this.q = false;
        }
        hashMap.put("TOP_CARD_BEAN", dVar);
        LogUtil.a("BloodPressureMessageProvider", "doctor name:" + this.l + "doctor describe:" + this.g + "doctor img url:" + this.h + "is show:" + this.q);
        if (dVar.a(this.v)) {
            LogUtil.a("BloodPressureMessageProvider", "same topCardBean");
        } else {
            this.v = dVar;
            this.x.e(new Object());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CopyOnWriteArrayList<DeviceBenefits> copyOnWriteArrayList) {
        LogUtil.a("BloodPressureMessageProvider", "processingData enter");
        if (!koq.b(copyOnWriteArrayList)) {
            Iterator<DeviceBenefits> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                List<DeviceInboxInfo> inboxInfos = it.next().getInboxInfos();
                LogUtil.a("BloodPressureMessageProvider", "inboxInfos  = ", inboxInfos);
                if (!koq.b(inboxInfos)) {
                    Iterator<DeviceInboxInfo> it2 = inboxInfos.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            DeviceInboxInfo next = it2.next();
                            boolean e = qqt.e(next);
                            this.p = e;
                            LogUtil.a("BloodPressureMessageProvider", "mIsShowTips  = ", Boolean.valueOf(e));
                            if (this.p) {
                                this.f10157a = next;
                                String e2 = gpp.e(next);
                                if (TextUtils.isEmpty(e2)) {
                                    e2 = Product.YEAR_DURATION_FLAG;
                                }
                                String a2 = gpn.a(e2);
                                LogUtil.a("BloodPressureMessageProvider", "timeStr  = ", a2);
                                String d2 = qqt.d(R$string.IDS_user_service_has_pick_tip, a2);
                                this.w = d2;
                                LogUtil.a("BloodPressureMessageProvider", "mTipText  = ", d2);
                            }
                        }
                    }
                } else {
                    LogUtil.h("BloodPressureMessageProvider", "processingData() deviceBenefits.getInboxInfos() isEmpty");
                }
            }
        } else {
            LogUtil.h("BloodPressureMessageProvider", "processingData() deviceBenefitsList isEmpty");
        }
        this.x.e(new Object());
    }

    private void h() {
        Context context = BaseApplication.getContext();
        int i = this.u;
        if (i == 1) {
            LogUtil.a("BloodPressureMessageProvider", "Ask the doctor by h5");
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addPath("#/ServiceRedirection?scene=3&from=4&pageType=1&packageType=" + b);
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.vip", builder);
            return;
        }
        if (i == 2) {
            LogUtil.a("BloodPressureMessageProvider", "Ask the doctor by deeplink");
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?h5pro=true&isNeedLogin=true&path=ServiceRedirection&scene=3&from=4&pageType=1&packageType=" + b));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            gnm.aPB_(context, intent);
            return;
        }
        LogUtil.h("BloodPressureMessageProvider", "clickDeviceInboxInfo error linkType");
    }

    private void d(Context context, HashMap hashMap, drx drxVar) {
        int d2 = drxVar.d();
        if (d2 == 0) {
            LogUtil.h("BloodPressureMessageProvider", "setChallengeBean failed startDay is invalid");
            return;
        }
        try {
            int b2 = HiDateUtil.b(d2, HiDateUtil.c(System.currentTimeMillis()), "yyyyMMdd");
            SpannableString spannableString = new SpannableString(context.getResources().getQuantityString(R.plurals._2130903121_res_0x7f030051, b2, Integer.valueOf(b2)));
            String valueOf = String.valueOf(b2);
            int indexOf = spannableString.toString().indexOf(valueOf);
            if (indexOf != -1) {
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color._2131296651_res_0x7f09018b)), indexOf, valueOf.length() + indexOf, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen._2131363396_res_0x7f0a0644)), indexOf, valueOf.length() + indexOf, 33);
            }
            this.o = spannableString.toString();
            if (this.f) {
                hashMap.put("BUTTON_TEXT", context.getResources().getString(R$string.IDS_hw_challenge_weekly_report));
            } else {
                hashMap.put("BUTTON_TEXT", "");
            }
        } catch (ParseException e) {
            LogUtil.b("BloodPressureMessageProvider", "parseParams parseException ", LogAnonymous.b((Throwable) e));
        }
    }

    private void e(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureMessageProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(final String str) {
                if (nsn.a(500)) {
                    LogUtil.a("BloodPressureMessageProvider", "onClick isFastClick");
                } else {
                    LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureMessageProvider.1.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i != -1) {
                                BloodPressureMessageProvider.this.b(str);
                            } else {
                                LogUtil.a("BloodPressureMessageProvider", "has not login");
                            }
                        }
                    }, "");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LogUtil.a("BloodPressureMessageProvider", "setOnClickListener, subView: " + str);
        if ("ALL_CLICK_EVENT".equals(str)) {
            o();
            return;
        }
        if ("BUTTON_CLICK_EVENT".equals(str)) {
            f();
            return;
        }
        if ("DOCTOR_BUTTON_CLICK_EVENT".equals(str)) {
            LogUtil.a("BloodPressureMessageProvider", "Ask the doctor");
            h();
            return;
        }
        if ("BANNER_CARD_SUBMIT_CLICK_EVENT".equals(str)) {
            k();
            return;
        }
        if ("MEASURE_PLAN_BUTTON_CLICK_EVENT".equals(str)) {
            LogUtil.a("BloodPressureMessageProvider", "Not the first time in the Blood Pressure measure plan.");
            q();
        } else if ("RIGHT_BOTTOM_TEXT".equals(str)) {
            LogUtil.a("BloodPressureMessageProvider", "startBloodPressureToast");
            l();
        } else {
            LogUtil.a("BloodPressureMessageProvider", "useless click");
        }
    }

    private void l() {
        LogUtil.h("BloodPressureMessageProvider", "startBloodPressureToast.");
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi != null) {
            vipApi.startEquityDialogActivity();
        } else {
            LogUtil.h("BloodPressureMessageProvider", "vipApi is null.");
        }
    }

    private void o() {
        LogUtil.a("BloodPressureMessageProvider", "showToastOrJumpToHealthLife mIsJoinPlan ", Boolean.valueOf(this.r), " mIsJoinDoctorPlan ", Boolean.valueOf(this.k), " mIsJoinHealthLife ", Boolean.valueOf(this.m));
        if (this.r || this.k || d()) {
            LogUtil.a("BloodPressureMessageProvider", "has joined plan");
            a(1);
            dsl.ZK_(com.huawei.haf.application.BaseApplication.wa_(), Uri.parse("").buildUpon().appendQueryParameter("from", "/Main/KnitBloodPressureActivity").build());
        } else if (this.m) {
            LogUtil.a("BloodPressureMessageProvider", "has other plan");
            n();
        } else {
            LogUtil.a("BloodPressureMessageProvider", "no plan");
            m();
        }
    }

    public boolean d() {
        try {
            JSONArray jSONArray = new JSONArray(dsl.e(HiDateUtil.c(System.currentTimeMillis())));
            if (jSONArray.length() != 0) {
                return ((JSONObject) jSONArray.get(0)).getInt("dataSource") == 1;
            }
            LogUtil.a("BloodPressureMessageProvider", "jsonObject length is 0");
            return false;
        } catch (JSONException unused) {
            LogUtil.a("BloodPressureMessageProvider", "get doctor plan fail");
            return false;
        }
    }

    private void f() {
        LogUtil.a("BloodPressureMessageProvider", "jumpToWeeklyReport mIsJoinPlan ", Boolean.valueOf(this.r), " mIsJoinDoctorPlan ", Boolean.valueOf(this.k));
        if (this.r || this.k) {
            a(2);
            d(this.e);
        }
    }

    private void k() {
        LogUtil.a("BloodPressureMessageProvider", "GuardianBanner is clicked");
        DeviceInboxInfo deviceInboxInfo = this.f10157a;
        if (deviceInboxInfo == null) {
            LogUtil.h("BloodPressureMessageProvider", "exclusiveGuardianInboxInfo is null");
            return;
        }
        String newPathConcat = OperationUtils.newPathConcat(deviceInboxInfo.getLinkValue(), "from", "8");
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("BloodPressureMessageProvider", "setBannerClickEvent activity is null");
        } else {
            qqt.e(wa_, this.f10157a.getLinkType(), newPathConcat);
        }
    }

    public void d(int i) {
        g();
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/ReportDetail?from=0&startTime=" + i);
        builder.setImmerse();
        builder.setForceDarkMode(1);
        builder.showStatusBar();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("BloodPressureMessageProvider", "jumpWeekReport activity is null");
        } else {
            bzs.e().loadH5ProApp(wa_, "com.huawei.health.h5.blood-pressure", builder);
        }
    }

    private void m() {
        HandlerExecutor.e(new Runnable() { // from class: qhw
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureMessageProvider.b();
            }
        });
    }

    public static /* synthetic */ void b() {
        final Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("BloodPressureMessageProvider", "showJoinHealthLifeDialog activity is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(R$string.IDS_hw_challenge_select_join);
        builder.czC_(R$string.IDS_hw_challenge_goto_join, new View.OnClickListener() { // from class: qhv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMessageProvider.dDK_(wa_, view);
            }
        });
        builder.czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: qht
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void dDK_(Activity activity, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("from", "BloodPressure");
        AppRouter.b("/PluginHealthModel/HealthModelGuideActivity").zF_(bundle).c(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        HandlerExecutor.e(new Runnable() { // from class: qhu
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureMessageProvider.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        final Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("BloodPressureMessageProvider", "showJoinedChallenge activity is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(wa_);
        builder.b(com.huawei.ui.commonui.R$string.IDS_health_clover_title);
        builder.e(i());
        builder.cyU_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: qia
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMessageProvider.this.dDN_(wa_, view);
            }
        });
        builder.cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: qhx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dDN_(Activity activity, View view) {
        g();
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/BasicSurvey?from=0");
        builder.setImmerse();
        builder.setForceDarkMode(1);
        builder.showStatusBar();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        bzs.e().loadH5ProApp(activity, "com.huawei.health.h5.blood-pressure", builder);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        dsl.o();
    }

    private void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qhz
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureMessageProvider.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        boolean n = dsl.n();
        this.m = n;
        if (n) {
            CountDownLatch countDownLatch = new CountDownLatch(3);
            drx drxVar = new drx();
            a(countDownLatch, drxVar);
            drx drxVar2 = new drx();
            d(countDownLatch, drxVar2);
            d(countDownLatch);
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
                if (drxVar2.d() != 0) {
                    this.n.e(drxVar2.d());
                }
                if (drxVar.d() != 0) {
                    this.n.e(drxVar.d());
                }
                if (this.n.d() == 0) {
                    this.x.e(new Object());
                    return;
                } else {
                    this.x.e(this.n);
                    return;
                }
            } catch (InterruptedException unused) {
                LogUtil.a("BloodPressureMessageProvider", "getBloodPressureChallengeInfo failed");
                return;
            }
        }
        LogUtil.a("BloodPressureMessageProvider", "not blood pressure plan");
        this.x.e(new Object());
    }

    private void d(final CountDownLatch countDownLatch, final drx drxVar) {
        dsl.b("/Main/KnitBloodPressureActivity", (ResponseCallback<drx>) new ResponseCallback() { // from class: qhy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                BloodPressureMessageProvider.this.a(countDownLatch, drxVar, i, (drx) obj);
            }
        });
    }

    public /* synthetic */ void a(CountDownLatch countDownLatch, drx drxVar, int i, drx drxVar2) {
        LogUtil.a("BloodPressureMessageProvider", "getBloodPressureChallengeInfo data ", drxVar2);
        if (this.n != null && drxVar2 != null && TextUtils.equals(this.n.toString(), drxVar2.toString())) {
            LogUtil.a("BloodPressureMessageProvider", "same HealthLifeChallengeBean");
            countDownLatch.countDown();
            return;
        }
        if (drxVar2 != null) {
            this.r = dsl.b().contains(Integer.valueOf(drxVar2.c()));
            drxVar.e(drxVar2.d());
        } else if (i == 0) {
            this.r = false;
        }
        countDownLatch.countDown();
    }

    private void d(final CountDownLatch countDownLatch) {
        this.e = HiDateUtil.c(gib.b(System.currentTimeMillis(), -1));
        LogUtil.a("BloodPressureMessageProvider", "getBloodPressureChallengeInfo mIsJoinHealthLife ", Boolean.valueOf(this.m));
        dsl.e(this.e, (ResponseCallback<dsb>) new ResponseCallback() { // from class: qhs
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                BloodPressureMessageProvider.this.b(countDownLatch, i, (dsb) obj);
            }
        });
    }

    public /* synthetic */ void b(CountDownLatch countDownLatch, int i, dsb dsbVar) {
        if (dsbVar != null) {
            this.c = CommonUtils.h(dsbVar.d());
            this.f = dsl.b().contains(Integer.valueOf(this.c));
        }
        countDownLatch.countDown();
    }

    private void a(final CountDownLatch countDownLatch, final drx drxVar) {
        dsl.c("/Main/KnitBloodPressureActivity", (ResponseCallback<dso>) new ResponseCallback() { // from class: qhr
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                BloodPressureMessageProvider.this.c(drxVar, countDownLatch, i, (dso) obj);
            }
        });
    }

    public /* synthetic */ void c(drx drxVar, CountDownLatch countDownLatch, int i, dso dsoVar) {
        if (drxVar != null && dsoVar != null) {
            int b2 = (int) dsoVar.b();
            this.k = b2 > 0;
            drxVar.e(b2);
        }
        countDownLatch.countDown();
    }

    private String i() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hw_challenge_free);
    }

    private void q() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", 2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_MORE_2030088.value(), hashMap, 0);
        Activity wa_ = com.huawei.haf.application.BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("BloodPressureMessageProvider", "startMeasurePlan activity is null");
            return;
        }
        try {
            wa_.startActivity(new Intent(wa_, (Class<?>) BloodPressureMeasurePlanActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtil.b("BloodPressureMessageProvider", "ActivityNotFoundException", e.getMessage());
        }
    }

    private void a(int i) {
        if (i == 1) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            hashMap.put("from", "1");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BLOOD_PRESSURE_JUMP_TO_HEALTH_LIFE_2040207.value(), hashMap, 0);
            return;
        }
        if (i == 2) {
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put("click", "1");
            hashMap2.put("type", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_MANAGE_PLAN_WEEKLY_2030089.value(), hashMap2, 0);
            return;
        }
        LogUtil.a("BloodPressureMessageProvider", "default biEvent");
    }

    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<BloodPressureMessageProvider> f10159a;

        c(BloodPressureMessageProvider bloodPressureMessageProvider) {
            this.f10159a = new WeakReference<>(bloodPressureMessageProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("BloodPressureMessageProvider", "GetAllDeviceBenefitsCallback errorCode = ", Integer.valueOf(i));
            BloodPressureMessageProvider bloodPressureMessageProvider = this.f10159a.get();
            if (bloodPressureMessageProvider == null) {
                LogUtil.h("BloodPressureMessageProvider", "guardianBannerProvider is null");
            } else if (i == 0 && (obj instanceof CopyOnWriteArrayList)) {
                bloodPressureMessageProvider.e((CopyOnWriteArrayList<DeviceBenefits>) obj);
            } else {
                LogUtil.h("BloodPressureMessageProvider", "errorCode not success");
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodPressureMessageProvider";
    }
}
