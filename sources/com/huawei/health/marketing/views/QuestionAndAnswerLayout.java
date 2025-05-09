package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.QuestionnaireTemplate;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.marketing.request.QuestionOptionReq;
import com.huawei.health.marketing.request.QuestionResultReq;
import com.huawei.health.marketing.request.QuestionResultRsp;
import com.huawei.health.marketing.views.QuestionAndAnswerLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eie;
import defpackage.eiv;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class QuestionAndAnswerLayout extends LinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f2884a;
    private LinearLayout aa;
    private HealthRecycleView ab;
    private HealthTextView ac;
    private HealthTextView ad;
    private HealthImageView ae;
    private LinearLayout af;
    private HealthImageView ag;
    private HealthTextView ah;
    private LinearLayout ai;
    private HealthTextView aj;
    private View al;
    private HealthTextView am;
    private QuestionnaireTemplate an;
    private LinearLayout b;
    private ImageView c;
    private LinearLayout d;
    private SingleArticleAdapter e;
    private HealthTextView f;
    private HealthImageView g;
    private LinearLayout h;
    private Context i;
    private HealthImageView j;
    private HealthTextView k;
    private String l;
    private HealthButton m;
    private HealthSubHeader n;
    private Handler o;
    private HealthButton p;
    private HealthTextView q;
    private HealthTextView r;
    private Pair<Integer, Integer> s;
    private RelativeLayout t;
    private LinearLayout u;
    private int v;
    private View w;
    private View x;
    private RelativeLayout y;
    private HealthTextView z;

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.f2884a == null || !resourceBriefInfo.getResourceId().equals(this.f2884a.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.f2884a;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    static class a extends BaseHandler<QuestionAndAnswerLayout> {
        a(QuestionAndAnswerLayout questionAndAnswerLayout) {
            super(questionAndAnswerLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: apM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(QuestionAndAnswerLayout questionAndAnswerLayout, Message message) {
            if (message.what == 1000) {
                questionAndAnswerLayout.c(questionAndAnswerLayout.an);
            } else {
                LogUtil.h("QuestionAndAnswerLayout", "handleMessageWhenReferenceNotNull not match ");
            }
        }
    }

    public QuestionAndAnswerLayout(Context context) {
        super(context);
        this.s = BaseActivity.getSafeRegionWidth();
        this.v = 0;
        this.i = context;
        this.o = new a(this);
    }

    public void d(int i, ResourceBriefInfo resourceBriefInfo, QuestionnaireTemplate questionnaireTemplate) {
        if (questionnaireTemplate == null) {
            LogUtil.h("QuestionAndAnswerLayout", "template = null");
            return;
        }
        this.f2884a = resourceBriefInfo;
        this.v = i;
        this.an = questionnaireTemplate;
        LogUtil.a("QuestionAndAnswerLayout", "template:", questionnaireTemplate.toString());
        a();
        c(0);
        e();
    }

    private void a() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.layout_marketing_question_and_answer, this);
        this.al = inflate.findViewById(R.id.layout_question_and_answer);
        this.n = (HealthSubHeader) inflate.findViewById(R.id.marketing_question_sub_header);
        this.c = (ImageView) inflate.findViewById(R.id.img_background);
        this.aj = (HealthTextView) inflate.findViewById(R.id.tv_answer_title);
        this.k = (HealthTextView) inflate.findViewById(R.id.tv_join_number);
        this.p = (HealthButton) inflate.findViewById(R.id.btn_ok);
        this.m = (HealthButton) inflate.findViewById(R.id.btn_not_ok);
        this.aa = (LinearLayout) inflate.findViewById(R.id.lin_btn_select);
        this.x = inflate.findViewById(R.id.view_progress_ok);
        this.w = inflate.findViewById(R.id.view_progress_not_ok);
        this.u = (LinearLayout) inflate.findViewById(R.id.lin_progress_view);
        this.z = (HealthTextView) inflate.findViewById(R.id.tv_proportion_ok);
        this.am = (HealthTextView) inflate.findViewById(R.id.tv_select_ok);
        this.ad = (HealthTextView) inflate.findViewById(R.id.tv_proportion_not_ok);
        this.ah = (HealthTextView) inflate.findViewById(R.id.tv_select_not_ok);
        this.y = (RelativeLayout) inflate.findViewById(R.id.lin_proportion_view);
        this.ac = (HealthTextView) inflate.findViewById(R.id.tv_answer_reason);
        this.ab = (HealthRecycleView) inflate.findViewById(R.id.recycle_recommend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(1);
        this.ab.setLayoutManager(linearLayoutManager);
        this.ab.setNestedScrollingEnabled(false);
        this.ab.setHasFixedSize(true);
        this.ab.a(false);
        this.ab.d(false);
        this.d = (LinearLayout) inflate.findViewById(R.id.lin_answer_layout);
        this.j = (HealthImageView) inflate.findViewById(R.id.img_connection);
        this.f = (HealthTextView) inflate.findViewById(R.id.tv_collapse);
        this.b = (LinearLayout) inflate.findViewById(R.id.answer_detail);
        this.ai = (LinearLayout) inflate.findViewById(R.id.lin_select_ok);
        this.af = (LinearLayout) inflate.findViewById(R.id.lin_select_not_ok);
        this.ag = (HealthImageView) inflate.findViewById(R.id.img_select_ok);
        this.ae = (HealthImageView) inflate.findViewById(R.id.img_select_not_ok);
        this.r = (HealthTextView) inflate.findViewById(R.id.tv_option_one);
        this.q = (HealthTextView) inflate.findViewById(R.id.tv_option_two);
        this.t = (RelativeLayout) inflate.findViewById(R.id.rt_option_layout);
        this.g = (HealthImageView) inflate.findViewById(R.id.img_collapse_arrow);
        this.h = (LinearLayout) inflate.findViewById(R.id.lin_collapse_layout);
        if (eie.e(this.v)) {
            return;
        }
        inflate.setPadding(((Integer) this.s.first).intValue(), 0, ((Integer) this.s.second).intValue(), 0);
    }

    private void e() {
        final QuestionResultReq questionResultReq = new QuestionResultReq();
        questionResultReq.setResourceId(this.f2884a.getResourceId());
        questionResultReq.setTheme(this.an.getTheme());
        questionResultReq.setThemeId(this.an.getThemeId());
        final d dVar = new d(this, 11);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.QuestionAndAnswerLayout.4
            @Override // java.lang.Runnable
            public void run() {
                ColumnRequestUtils.requestAnswer(questionResultReq, dVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(QuestionnaireTemplate questionnaireTemplate) {
        long j;
        long j2;
        long j3;
        if (questionnaireTemplate == null) {
            LogUtil.h("QuestionAndAnswerLayout", "showAnswerAndQuestion template is null");
            return;
        }
        setSubHeaderUi(questionnaireTemplate);
        eiv.d(this.aj, questionnaireTemplate.getTheme(), questionnaireTemplate.isThemeVisibility());
        setBackground(questionnaireTemplate);
        if (questionnaireTemplate.getQuestionnaireResults() != null) {
            Map<String, Long> questionnaireResults = questionnaireTemplate.getQuestionnaireResults();
            long longValue = questionnaireResults.containsKey("optionOne") ? questionnaireResults.get("optionOne").longValue() : 0L;
            long longValue2 = questionnaireResults.containsKey("optionTwo") ? questionnaireResults.get("optionTwo").longValue() : 0L;
            LogUtil.a("QuestionAndAnswerLayout", "selectOne:", Long.valueOf(longValue), " selectTwo:", Long.valueOf(longValue2));
            j3 = longValue + longValue2;
            j2 = longValue2;
            j = longValue;
        } else {
            j = 0;
            j2 = 0;
            j3 = 0;
        }
        if (TextUtils.isEmpty(questionnaireTemplate.getOptionContent())) {
            this.k.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903123_res_0x7f030053, (int) j3, UnitUtil.e(j3, 1, 0)));
            this.aa.setVisibility(0);
            this.u.setVisibility(8);
            this.y.setVisibility(8);
            this.d.setVisibility(8);
            this.t.setVisibility(8);
            this.p.setText(questionnaireTemplate.getOptionOne());
            this.m.setText(questionnaireTemplate.getOptionTwo());
            this.p.setOnClickListener(this);
            this.m.setOnClickListener(this);
            this.al.setVisibility(0);
            return;
        }
        c(j, j2, j3, questionnaireTemplate);
    }

    private void c(long j, long j2, long j3, QuestionnaireTemplate questionnaireTemplate) {
        long j4;
        long j5;
        long j6;
        if (j3 <= 0) {
            LogUtil.a("QuestionAndAnswerLayout", "join = 0");
            j4 = 1;
            if ("optionOne".equals(questionnaireTemplate.getOptionContent())) {
                j5 = j2;
                j6 = 1;
            } else {
                j5 = 1;
                j6 = 1;
                j4 = j;
            }
        } else {
            j4 = j;
            j5 = j2;
            j6 = j3;
        }
        this.k.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903123_res_0x7f030053, (int) j6, UnitUtil.e(j6, 1, 0)));
        if (koq.c(questionnaireTemplate.getGridContents())) {
            SingleArticleAdapter singleArticleAdapter = new SingleArticleAdapter(this.i, new ArrayList(), this.v, this.f2884a);
            this.e = singleArticleAdapter;
            this.ab.setAdapter(singleArticleAdapter);
            this.e.c(questionnaireTemplate.getGridContents());
        } else {
            LogUtil.a("QuestionAndAnswerLayout", "gridContents is empty, not need show");
        }
        d(j4, j5, j6);
        this.u.setVisibility(0);
        this.y.setVisibility(0);
        this.d.setVisibility(0);
        this.aa.setVisibility(8);
        this.t.setVisibility(0);
        this.r.setText(questionnaireTemplate.getOptionOne());
        this.q.setText(questionnaireTemplate.getOptionTwo());
        if (nsn.p()) {
            this.r.setTextSize(1, 16.0f);
            this.q.setTextSize(1, 16.0f);
        }
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_ICE), Integer.toString(this.v).concat("_isQAExpand"), true)) {
            this.b.setVisibility(0);
            this.f.setText(R$string.IDS_device_health_retract);
            this.g.setImageResource(R.drawable._2131429718_res_0x7f0b0956);
        } else {
            this.b.setVisibility(8);
            this.f.setText(R$string.IDS_device_health_expand);
            this.g.setImageResource(R.drawable._2131429714_res_0x7f0b0952);
        }
        this.h.setOnClickListener(this);
        this.al.setVisibility(0);
    }

    private void setBackground(final QuestionnaireTemplate questionnaireTemplate) {
        if (TextUtils.isEmpty(questionnaireTemplate.getPicture())) {
            LogUtil.h("QuestionAndAnswerLayout", "background is empty");
        } else {
            final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.c.getLayoutParams();
            this.aj.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.marketing.views.QuestionAndAnswerLayout.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (QuestionAndAnswerLayout.this.aj.getLineCount() > 1) {
                        layoutParams.height = nsn.c(QuestionAndAnswerLayout.this.i, 164.0f);
                    } else {
                        layoutParams.height = nsn.c(QuestionAndAnswerLayout.this.i, 140.0f);
                    }
                    QuestionAndAnswerLayout.this.c.setLayoutParams(layoutParams);
                    nrf.cIS_(QuestionAndAnswerLayout.this.c, questionnaireTemplate.getPicture(), nrf.d, 1, 0);
                    QuestionAndAnswerLayout.this.aj.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(long r20, long r22, long r24) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.QuestionAndAnswerLayout.d(long, long, long):void");
    }

    private void d() {
        int i;
        int i2;
        if (this.an.getOptionContent().equals(this.an.getCorrectOption())) {
            i = R$string.IDS_select_right;
            i2 = R.drawable._2131430629_res_0x7f0b0ce5;
        } else {
            i = R$string.IDS_select_error;
            i2 = R.drawable._2131430634_res_0x7f0b0cea;
        }
        if ("optionOne".equals(this.an.getOptionContent())) {
            this.ai.setVisibility(0);
            this.ag.setImageResource(i2);
            this.am.setText(i);
            this.af.setVisibility(8);
        } else {
            this.ai.setVisibility(8);
            this.af.setVisibility(0);
            this.ae.setImageResource(i2);
            this.ah.setText(i);
        }
        if (TextUtils.isEmpty(this.an.getDescription())) {
            return;
        }
        this.ac.setText(this.an.getDescription());
        this.ac.setVisibility(0);
    }

    private void apL_(View view, double d2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = (float) d2;
        view.setLayoutParams(layoutParams);
    }

    private void setSubHeaderUi(QuestionnaireTemplate questionnaireTemplate) {
        if (questionnaireTemplate == null) {
            return;
        }
        boolean isNameVisibility = questionnaireTemplate.isNameVisibility();
        String name = questionnaireTemplate.getName();
        eiv.e(isNameVisibility && !TextUtils.isEmpty(name), this.n);
        this.n.setHeadTitleText(name);
        String linkValue = questionnaireTemplate.getLinkValue();
        this.l = linkValue;
        if (!TextUtils.isEmpty(linkValue)) {
            this.n.setMoreLayoutVisibility(0);
            this.n.setMoreViewClickListener(this);
        } else {
            this.n.setMoreLayoutVisibility(8);
        }
        this.n.setSubHeaderBackgroundColor(0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.h("QuestionAndAnswerLayout", "view = null or isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_ok) {
            if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                c();
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                b(view.getId());
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
        }
        if (view.getId() == R.id.btn_not_ok) {
            if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                b();
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                b(view.getId());
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
        }
        if (view.getId() == R.id.hwsubheader_more_container) {
            if (TextUtils.isEmpty(this.l)) {
                LogUtil.h("QuestionAndAnswerLayout", "mMoreUrl is empty");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (!eie.b(this.l)) {
                e(this.l);
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                b(view.getId());
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
        }
        if (view.getId() == R.id.lin_collapse_layout) {
            String concat = Integer.toString(this.v).concat("_isQAExpand");
            if (this.b.getVisibility() == 0) {
                this.b.setVisibility(8);
                this.f.setText(R$string.IDS_device_health_expand);
                this.g.setImageResource(R.drawable._2131429714_res_0x7f0b0952);
                SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_ICE), concat, false);
                c(5);
            } else {
                this.b.setVisibility(0);
                this.f.setText(R$string.IDS_device_health_retract);
                this.g.setImageResource(R.drawable._2131429718_res_0x7f0b0956);
                SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_ICE), concat, true);
                c(4);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(final int i) {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: eki
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                QuestionAndAnswerLayout.this.d(i, i2, obj);
            }
        }, AnalyticsValue.MARKETING_RESOURCE.value());
    }

    public /* synthetic */ void d(int i, int i2, Object obj) {
        LogUtil.h("QuestionAndAnswerLayout", "browsingToLogin errorCode =", Integer.valueOf(i2));
        if (i2 != 0) {
            return;
        }
        if (i == R.id.btn_ok) {
            c();
        } else if (i == R.id.btn_not_ok) {
            b();
        } else if (i == R.id.hwsubheader_more_container) {
            e(this.l);
        }
    }

    private void c() {
        if (!CommonUtil.aa(this.i)) {
            nrh.b(this.i, R$string.IDS_connect_network);
            return;
        }
        this.an.setOptionContent("optionOne");
        Map<String, Long> questionnaireResults = this.an.getQuestionnaireResults();
        if (questionnaireResults == null) {
            questionnaireResults = new HashMap<>(1);
            questionnaireResults.put("optionOne", 1L);
        } else if (questionnaireResults.containsKey("optionOne")) {
            questionnaireResults.put("optionOne", Long.valueOf(questionnaireResults.get("optionOne").longValue() + 1));
        } else {
            questionnaireResults.put("optionOne", 1L);
        }
        this.an.setQuestionnaireResults(questionnaireResults);
        c(this.an);
        b("optionOne", this.an.getOptionOne());
        c(1);
    }

    private void b() {
        if (!CommonUtil.aa(this.i)) {
            nrh.b(this.i, R$string.IDS_connect_network);
            return;
        }
        this.an.setOptionContent("optionTwo");
        Map<String, Long> questionnaireResults = this.an.getQuestionnaireResults();
        if (questionnaireResults == null) {
            questionnaireResults = new HashMap<>(1);
            questionnaireResults.put("optionTwo", 1L);
        } else if (questionnaireResults.containsKey("optionTwo")) {
            questionnaireResults.put("optionTwo", Long.valueOf(questionnaireResults.get("optionTwo").longValue() + 1));
        } else {
            questionnaireResults.put("optionTwo", 1L);
        }
        this.an.setQuestionnaireResults(questionnaireResults);
        c(this.an);
        b("optionTwo", this.an.getOptionTwo());
        c(2);
    }

    private void e(String str) {
        LogUtil.a("QuestionAndAnswerLayout", "more linkValue: ", str);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
        c(3);
    }

    private void b(String str, String str2) {
        final QuestionOptionReq questionOptionReq = new QuestionOptionReq();
        questionOptionReq.setOptionId(str);
        questionOptionReq.setOptionContent(str2);
        questionOptionReq.setResourceId(this.f2884a.getResourceId());
        questionOptionReq.setTheme(this.an.getTheme());
        questionOptionReq.setThemeId(this.an.getThemeId());
        final d dVar = new d(this, 10);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.marketing.views.QuestionAndAnswerLayout.5
            @Override // java.lang.Runnable
            public void run() {
                ColumnRequestUtils.submitAnswer(questionOptionReq, dVar);
            }
        });
    }

    private void c(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceName", this.an.getTheme());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MARKETING_QUESTION_AND_ANSWER.value(), hashMap, 0);
    }

    static class d implements ResultCallback<QuestionResultRsp> {
        private final WeakReference<QuestionAndAnswerLayout> b;
        private int d;

        d(QuestionAndAnswerLayout questionAndAnswerLayout, int i) {
            this.b = new WeakReference<>(questionAndAnswerLayout);
            this.d = i;
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(QuestionResultRsp questionResultRsp) {
            int i = this.d;
            if (i == 10) {
                if (questionResultRsp.getResultCode().intValue() == 0) {
                    LogUtil.a("QuestionAndAnswerLayout", "submitAnswer success");
                    return;
                }
                LogUtil.h("QuestionAndAnswerLayout", "submitAnswer error code:", questionResultRsp.getResultCode());
                if (questionResultRsp.getResultCode().intValue() == 2207002) {
                    LogUtil.a("QuestionAndAnswerLayout", "already answer");
                    return;
                } else {
                    nrh.b(BaseApplication.getContext(), R$string.IDS_submit_error);
                    return;
                }
            }
            if (i == 11) {
                if (questionResultRsp.getResultCode().intValue() == 0) {
                    QuestionAndAnswerLayout questionAndAnswerLayout = this.b.get();
                    LogUtil.a("QuestionAndAnswerLayout", "request answer success");
                    questionAndAnswerLayout.an.setOptionContent(questionResultRsp.getUserOptionId());
                    questionAndAnswerLayout.an.setQuestionnaireResults(questionResultRsp.getQuestionSums());
                    questionAndAnswerLayout.o.sendEmptyMessage(1000);
                    return;
                }
                LogUtil.h("QuestionAndAnswerLayout", "request Answer error code:", questionResultRsp.getResultCode(), questionResultRsp.getResultDesc());
                return;
            }
            LogUtil.h("QuestionAndAnswerLayout", "onSuccess not match");
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            int i = this.d;
            if (i == 10) {
                LogUtil.h("QuestionAndAnswerLayout", "submitAnswer onFailure", th.toString());
                nrh.b(BaseApplication.getContext(), R$string.IDS_submit_error);
            } else if (i == 11) {
                LogUtil.h("QuestionAndAnswerLayout", "request answer fail");
            } else {
                LogUtil.h("QuestionAndAnswerLayout", "onSuccess not match");
            }
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        LogUtil.a("QuestionAndAnswerLayout", "onConfigurationChanged");
        setBackground(this.an);
    }
}
