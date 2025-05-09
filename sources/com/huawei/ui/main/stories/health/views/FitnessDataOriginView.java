package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.fitnessdata.DataOriginListAdapter;
import com.huawei.ui.main.stories.health.views.FitnessDataOriginView;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pwb;
import defpackage.pwj;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessDataOriginView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f10261a;
    private Handler b;
    private int c;
    private ListView d;
    private DataOriginListAdapter e;
    private CommonSingleCallback<Boolean> f;
    private List<pwb> g;
    private boolean h;
    private HealthTextView i;
    private HealthTextView j;
    private String l;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FitnessDataOriginView(Context context) {
        this(context, (AttributeSet) null);
    }

    private FitnessDataOriginView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new ArrayList();
        this.h = false;
        this.b = new Handler() { // from class: com.huawei.ui.main.stories.health.views.FitnessDataOriginView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("Step_FitnessDataOriginView", "handleMessage msg == null");
                    return;
                }
                super.handleMessage(message);
                if (message.what != 1000) {
                    return;
                }
                LogUtil.a("Step_FitnessDataOriginView", "handleMessage mListData.size = ", Integer.valueOf(FitnessDataOriginView.this.g.size()));
                FitnessDataOriginView.this.e.c(FitnessDataOriginView.this.g);
            }
        };
        d(context, false);
    }

    public FitnessDataOriginView(Context context, Boolean bool) {
        super(context, null);
        this.g = new ArrayList();
        this.h = false;
        this.b = new Handler() { // from class: com.huawei.ui.main.stories.health.views.FitnessDataOriginView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("Step_FitnessDataOriginView", "handleMessage msg == null");
                    return;
                }
                super.handleMessage(message);
                if (message.what != 1000) {
                    return;
                }
                LogUtil.a("Step_FitnessDataOriginView", "handleMessage mListData.size = ", Integer.valueOf(FitnessDataOriginView.this.g.size()));
                FitnessDataOriginView.this.e.c(FitnessDataOriginView.this.g);
            }
        };
        d(context, bool);
    }

    public void setmListdata(List<pwb> list) {
        if (koq.c(list)) {
            this.g = list;
            this.e.c(list);
        }
    }

    public void setIsOnlyStepSource(boolean z) {
        this.h = z;
        this.e.d(true);
        d();
    }

    public void setFromType(int i) {
        this.c = i;
    }

    public void setIsSleepType() {
        DataOriginListAdapter dataOriginListAdapter = this.e;
        if (dataOriginListAdapter == null) {
            LogUtil.b("Step_FitnessDataOriginView", "mDataListAdapter == null");
        } else {
            dataOriginListAdapter.e(true);
        }
    }

    private void d() {
        Context context;
        HealthTextView healthTextView = this.j;
        if (healthTextView == null || (context = this.f10261a) == null) {
            LogUtil.h("Step_FitnessDataOriginView", "buildStepViewContent: context or textView is null");
        } else {
            if (this.h) {
                return;
            }
            healthTextView.setText(context.getResources().getString(R$string.IDS_hw_home_steps_data_source_merge_tip));
        }
    }

    private void d(Context context, Boolean bool) {
        View inflate = View.inflate(context, R.layout.fitness_data_origin_view, this);
        this.j = (HealthTextView) inflate.findViewById(R.id.info_text);
        this.i = (HealthTextView) inflate.findViewById(R.id.inaccurate_step_count);
        this.f10261a = BaseApplication.getContext();
        if (!bool.booleanValue()) {
            if (this.f == null) {
                this.f = new CommonSingleCallback() { // from class: qso
                    @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                    public final void callback(Object obj) {
                        FitnessDataOriginView.this.e((Boolean) obj);
                    }
                };
                pwj.e().d(this.f);
            }
            pwj.e().c();
        }
        this.d = (ListView) nsy.cMd_(this, R.id.lv_fitness_data_origin_list);
        DataOriginListAdapter dataOriginListAdapter = new DataOriginListAdapter(this.f10261a);
        this.e = dataOriginListAdapter;
        this.d.setAdapter((ListAdapter) dataOriginListAdapter);
        if ((LanguageUtil.j(context) || LanguageUtil.p(context)) && !bool.booleanValue()) {
            this.i.setVisibility(0);
        }
        if (!(context instanceof BaseActivity) || bool.booleanValue()) {
            return;
        }
        this.i.setOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.views.FitnessDataOriginView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FitnessDataOriginView.this.dIn_(view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) context, true, AnalyticsValue.STEP_FAQ_CLICKED_2040126.value()));
    }

    public /* synthetic */ void e(Boolean bool) {
        this.g = pwj.e().d();
        this.b.sendMessage(this.b.obtainMessage(1000));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dIn_(View view) {
        if (nsn.cLk_(view)) {
            return;
        }
        if (this.f10261a != null) {
            setBiCollect(AnalyticsValue.STEP_FAQ_CLICKED_2040126.value());
            b();
        } else {
            LogUtil.h("Step_FitnessDataOriginView", "startStepFaq onClick context is null!");
        }
    }

    public void setInfoText(String str) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void setInfoTextSpannableString(SpannableString spannableString) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setText(spannableString);
            this.j.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void b() {
        GRSManager.a(this.f10261a).e("helpCustomerUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.health.views.FitnessDataOriginView.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    FitnessDataOriginView.this.l = str;
                    FitnessDataOriginView.this.b.post(new Runnable() { // from class: com.huawei.ui.main.stories.health.views.FitnessDataOriginView.5.5
                        @Override // java.lang.Runnable
                        public void run() {
                            FitnessDataOriginView.this.a();
                        }
                    });
                } else {
                    LogUtil.h("Step_FitnessDataOriginView", "obtainUrlDomain urlDomain is empty");
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("Step_FitnessDataOriginView", "obtainUrlDomain onCallBackFail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Intent intent = new Intent(this.f10261a, (Class<?>) WebViewActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("url", e(LoginInit.getInstance(this.f10261a).getCountryCode(null)));
        gnm.aPB_(this.f10261a, intent);
    }

    private String e(String str) {
        StringBuilder sb;
        String str2;
        LogUtil.a("Step_FitnessDataOriginView", "getStepFaqUrl() countryCode:", str);
        if (LanguageUtil.h(this.f10261a)) {
            sb = new StringBuilder();
            sb.append(this.l);
            str2 = "/hwtips/help/health_help_all/zh-CN/index.html#/card/ug009";
        } else {
            sb = new StringBuilder();
            sb.append(this.l);
            str2 = "/hwtips/help/health_help_all/en-US/index.html#/card/ug009";
        }
        sb.append(str2);
        return sb.toString();
    }

    private void setBiCollect(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", "0");
        hashMap.put("from", Integer.valueOf(this.c));
        ixx.d().d(this.f10261a, str, hashMap, 0);
    }
}
