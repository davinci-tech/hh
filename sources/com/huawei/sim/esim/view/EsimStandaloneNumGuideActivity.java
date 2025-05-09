package com.huawei.sim.esim.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncd;
import defpackage.nrh;
import defpackage.nrs;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class EsimStandaloneNumGuideActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private int k = 30;
    private HealthTextView l;
    private Context m;
    private HealthTextView n;
    private LinearLayout o;
    private RelativeLayout p;
    private MultiSimDeviceInfo q;
    private HealthTextView r;
    private HealthImageView s;
    private HealthTextView t;
    private CustomTitleBar u;
    private RelativeLayout v;
    private HealthTextView w;
    private HealthButton x;
    private LinearLayout y;

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = this;
        this.e = new b(this);
        setContentView(R.layout.activity_esim_standalone_num_guide);
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getIntExtra("BatteryThreshold", this.k);
            this.q = (MultiSimDeviceInfo) intent.getParcelableExtra("multi_sim_device_info");
        }
        e();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.standalone_num_title_bar);
        this.u = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.v = (RelativeLayout) findViewById(R.id.standalone_num_waiting_content);
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_qrcode);
        this.x = healthButton;
        healthButton.setOnClickListener(this);
        this.n = (HealthTextView) findViewById(R.id.tv_standalone_num_guide1);
        this.r = (HealthTextView) findViewById(R.id.tv_standalone_num_guide2);
        this.t = (HealthTextView) findViewById(R.id.tv_standalone_num_eid_and_emie);
        this.w = (HealthTextView) findViewById(R.id.tv_standalone_num_tips);
        h();
        this.o = (LinearLayout) findViewById(R.id.ll_error_tips);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_set_net_layout);
        this.y = linearLayout;
        linearLayout.setOnClickListener(this);
        this.l = (HealthTextView) findViewById(R.id.tv_error_tips);
        this.v.setVisibility(8);
        this.p = (RelativeLayout) findViewById(R.id.rl_standalone_num_icon);
        int d = nrs.d(this.m);
        ViewGroup.LayoutParams layoutParams = this.p.getLayoutParams();
        layoutParams.height = d / 3;
        this.p.setLayoutParams(layoutParams);
        HealthImageView healthImageView = (HealthImageView) findViewById(R.id.iv_standalone_num);
        this.s = healthImageView;
        healthImageView.setImageDrawable(this.m.getResources().getDrawable(R.drawable._2131431573_res_0x7f0b1095));
        f();
    }

    private void h() {
        String e = UnitUtil.e(1.0d, 1, 0);
        String e2 = UnitUtil.e(2.0d, 1, 0);
        this.w.setText(String.format(Locale.ENGLISH, this.m.getString(R.string._2130848063_res_0x7f02293f), e, e2, UnitUtil.e(3.0d, 1, 0), UnitUtil.e(4.0d, 1, 0)));
        this.n.setText(String.format(Locale.ENGLISH, this.m.getString(R.string._2130848085_res_0x7f022955), e));
        this.r.setText(String.format(Locale.ENGLISH, this.m.getString(R.string._2130848104_res_0x7f022968), e2));
        if (this.q != null) {
            this.t.setText(String.format(Locale.ENGLISH, this.m.getString(R.string._2130848105_res_0x7f022969), this.q.getEID(), this.q.getDeviceIMEI()));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_qrcode) {
            if (!ktx.e().g()) {
                nrh.b(this.m, R.string._2130848008_res_0x7f022908);
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                this.v.setVisibility(0);
                b();
            }
        } else if (view.getId() == R.id.ll_set_net_layout) {
            CommonUtil.q(this.m);
        } else {
            LogUtil.c("EsimStandaloneNumGuideActivity", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        ktx.e().d(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimStandaloneNumGuideActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimStandaloneNumGuideActivity", "getBatteryStatus onResponse errorCode :", Integer.valueOf(i));
                EsimStandaloneNumGuideActivity.this.e.sendMessage(EsimStandaloneNumGuideActivity.this.e.obtainMessage(10002));
                Message obtainMessage = EsimStandaloneNumGuideActivity.this.e.obtainMessage();
                if (i == 0) {
                    obtainMessage.what = 10003;
                } else if (i == 129001) {
                    obtainMessage.what = 10004;
                } else {
                    obtainMessage.what = 10005;
                }
                EsimStandaloneNumGuideActivity.this.e.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        nrh.d(this.m, String.format(Locale.ENGLISH, this.m.getString(R.string.IDS_plugin_multi_esim_device_low_power), UnitUtil.e(i, 2, 0)));
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.removeMessages(10002);
            this.e.removeMessages(10003);
            this.e.removeMessages(10004);
            this.e.removeMessages(10005);
        }
    }

    static class b extends BaseHandler<EsimStandaloneNumGuideActivity> {
        b(EsimStandaloneNumGuideActivity esimStandaloneNumGuideActivity) {
            super(esimStandaloneNumGuideActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimStandaloneNumGuideActivity esimStandaloneNumGuideActivity, Message message) {
            if (message == null) {
                LogUtil.h("EsimStandaloneNumGuideActivity", "handleMessageWhenReferenceNotNull msg is null");
            }
            LogUtil.a("EsimStandaloneNumGuideActivity", "handleMessageWhenReferenceNotNull message = ", message);
            switch (message.what) {
                case 10001:
                    esimStandaloneNumGuideActivity.f();
                    break;
                case 10002:
                    esimStandaloneNumGuideActivity.v.setVisibility(8);
                    break;
                case 10003:
                    esimStandaloneNumGuideActivity.d();
                    break;
                case 10004:
                    esimStandaloneNumGuideActivity.d(esimStandaloneNumGuideActivity.k);
                    sqo.o("MSG_BATTERY_DATA_ERROR");
                    break;
                case 10005:
                    esimStandaloneNumGuideActivity.g();
                    sqo.o("MSG_BATTERY_DATA_FAIL");
                    break;
                default:
                    LogUtil.h("EsimStandaloneNumGuideActivity", "handleMessageWhenReferenceNotNull default");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!ncd.a(this.m)) {
            this.x.setEnabled(false);
            this.x.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.o.setVisibility(0);
            this.l.setText(this.m.getResources().getString(R.string._2130848067_res_0x7f022943));
            this.y.setVisibility(0);
            return;
        }
        if (!ncd.c() || !ncd.e(this.m)) {
            this.x.setEnabled(false);
            this.x.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.o.setVisibility(0);
            this.l.setText(this.m.getResources().getString(R.string._2130848008_res_0x7f022908));
            this.y.setVisibility(8);
            return;
        }
        this.x.setEnabled(true);
        this.x.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
        this.o.setVisibility(8);
        this.y.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Context context = this.m;
        nrh.d(context, context.getResources().getString(R.string._2130847980_res_0x7f0228ec));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("EsimStandaloneNumGuideActivity", "onResume isOperatorOpenSuccess = ", Boolean.valueOf(j));
        if (j) {
            finish();
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
