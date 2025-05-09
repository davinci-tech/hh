package com.huawei.sim.multisim.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.sim.esim.view.BaseOpenSimCardActivity;
import com.huawei.sim.multisim.adapter.EsimOpenMethodAdapter;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.knl;
import defpackage.ktx;
import defpackage.ncd;
import defpackage.ncf;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes6.dex */
public class EsimOpenMethodActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private LinearLayout k;
    private HealthButton l;
    private int m;
    private HealthTextView n;
    private Context o;
    private String q;
    private EsimOpenMethodAdapter r;
    private MultiSimDeviceInfo t;
    private String u;
    private HealthTextView v;
    private CustomTitleBar w;
    private LinearLayout y;
    private ArrayList<String> x = new ArrayList<>(16);
    private String s = "";
    private EsimOpenMethodAdapter.OnRadioButtonClickListener p = new EsimOpenMethodAdapter.OnRadioButtonClickListener() { // from class: com.huawei.sim.multisim.activity.EsimOpenMethodActivity.2
        @Override // com.huawei.sim.multisim.adapter.EsimOpenMethodAdapter.OnRadioButtonClickListener
        public void onClick(int i, String str) {
            if (!EsimOpenMethodActivity.this.s.equals(str)) {
                EsimOpenMethodActivity.this.k.setVisibility(8);
            }
            EsimOpenMethodActivity.this.s = str;
            LogUtil.a("EsimOpenMethodActivity", "mListener mOpenMethod = ", EsimOpenMethodActivity.this.s);
        }
    };

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = this;
        this.e = new d(this);
        setContentView(R.layout.activity_esim_open_method);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.i = intent.getStringExtra("esim_operator_key");
                this.c = intent.getIntExtra("esim_auth_method", 0);
                this.q = intent.getStringExtra("simImsi");
                this.g = intent.getIntExtra("MultiSimSlotId", 0);
                this.x = intent.getStringArrayListExtra("openMethodList");
                this.j = intent.getStringExtra("esim_default_url");
                this.m = intent.getIntExtra("esim_card_type_key", 1);
                this.u = intent.getStringExtra("esim_operator_name_key");
                this.t = (MultiSimDeviceInfo) intent.getParcelableExtra("multi_sim_device_info");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("EsimOpenMethodActivity", "onCreate ArrayIndexOutOfBoundsException");
                sqo.o("onCreate ArrayIndexOutOfBoundsException : " + e.getMessage());
            }
        }
        ArrayList<String> arrayList = this.x;
        if (arrayList != null && arrayList.size() > 0) {
            this.s = this.x.get(0);
        }
        j();
    }

    private void j() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.esim_open_method_title_bar);
        this.w = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.k = (LinearLayout) findViewById(R.id.ll_error_tips);
        this.v = (HealthTextView) findViewById(R.id.textview_tips);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_set_net_layout);
        this.y = linearLayout;
        linearLayout.setOnClickListener(this);
        this.n = (HealthTextView) findViewById(R.id.tv_error_tips);
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_esim_continue);
        this.l = healthButton;
        healthButton.setOnClickListener(this);
        ListView listView = (ListView) findViewById(R.id.esim_open_method_listview);
        EsimOpenMethodAdapter esimOpenMethodAdapter = new EsimOpenMethodAdapter(this.o, this.x);
        this.r = esimOpenMethodAdapter;
        esimOpenMethodAdapter.e(this.p);
        this.r.a(this.m);
        listView.setAdapter((ListAdapter) this.r);
        if (Utils.o()) {
            this.v.setVisibility(8);
            return;
        }
        this.v.setVisibility(0);
        this.v.setText(String.format(Locale.getDefault(), this.o.getString(R.string._2130848138_res_0x7f02298a), this.u, ncf.c(this.o, this.u)));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_esim_continue) {
            if (this.m == 2) {
                b();
            } else {
                e();
            }
        } else if (view.getId() == R.id.ll_set_net_layout) {
            CommonUtil.q(this.o);
        } else {
            LogUtil.c("EsimOpenMethodActivity", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        if (TextUtils.isEmpty(this.q)) {
            LogUtil.h("EsimOpenMethodActivity", "choiceContentProvider mImsi is null");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "imsi", knl.d(this.q), (StorageParams) null);
        ncf.b(this.s, true);
        if (this.s.equals(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            d();
            return;
        }
        if (this.s.equals(a.t)) {
            b(this.q, this.m);
            return;
        }
        if (this.s.equals("03")) {
            b(this.s, this.q, false);
            return;
        }
        if (this.s.equals("04")) {
            LogUtil.a("EsimOpenMethodActivity", "choiceContentProvider TS.43 ESs branch");
            if (ncd.b(this.i, this.c)) {
                this.k.setVisibility(0);
                this.n.setText(this.o.getResources().getString(R.string._2130848056_res_0x7f022938));
                return;
            } else {
                b(this.s, this.q, false);
                return;
            }
        }
        if (this.s.equals("05")) {
            LogUtil.a("EsimOpenMethodActivity", "choiceContentProvider H5 branch");
            this.k.setVisibility(0);
            this.n.setText(this.o.getResources().getString(R.string._2130848041_res_0x7f022929));
        } else if (this.s.equals("06")) {
            c();
            LogUtil.a("EsimOpenMethodActivity", "choiceContentProvider download branch");
        } else {
            LogUtil.h("EsimOpenMethodActivity", "choiceContentProvider else branch");
            sqo.o("choiceContentProvider else branch");
            this.k.setVisibility(0);
            this.n.setText(this.o.getResources().getString(R.string._2130848041_res_0x7f022929));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("EsimOpenMethodActivity", "onResume isOperatorOpenSuccess = ", Boolean.valueOf(j));
        if (j) {
            finish();
        }
    }

    static class d extends BaseHandler<EsimOpenMethodActivity> {
        d(EsimOpenMethodActivity esimOpenMethodActivity) {
            super(esimOpenMethodActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csz_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimOpenMethodActivity esimOpenMethodActivity, Message message) {
            if (message == null) {
                LogUtil.h("EsimOpenMethodActivity", "handleMessageWhenReferenceNotNull msg is null");
            } else if (message.what == 10001) {
                esimOpenMethodActivity.g();
            } else {
                LogUtil.h("EsimOpenMethodActivity", "handleMessageWhenReferenceNotNull default");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!ncd.a(this.o)) {
            this.r.e(null);
            this.l.setEnabled(false);
            this.l.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.k.setVisibility(0);
            this.n.setText(this.o.getResources().getString(R.string._2130848067_res_0x7f022943));
            this.y.setVisibility(0);
            return;
        }
        if (!ncd.c() || !ncd.e(this.o)) {
            this.r.e(null);
            this.l.setEnabled(false);
            this.l.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.k.setVisibility(0);
            this.n.setText(this.o.getResources().getString(R.string._2130848008_res_0x7f022908));
            this.y.setVisibility(8);
            return;
        }
        this.r.e(this.p);
        this.l.setEnabled(true);
        this.l.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
        this.k.setVisibility(8);
        this.y.setVisibility(8);
    }

    private void b() {
        if (this.s.equals(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            b(this.t);
            return;
        }
        if (this.s.equals("07")) {
            b(this.q, this.m);
            LogUtil.a("EsimOpenMethodActivity", "choiceStandaloneNumCardType ESIM_OPEN_METHOD_STANDALONE_NUM_HAND_HALL");
        } else {
            LogUtil.h("EsimOpenMethodActivity", "choiceStandaloneNumCardType else branch");
            sqo.o("choiceStandaloneNumCardType else branch");
            this.k.setVisibility(0);
            this.n.setText(this.o.getResources().getString(R.string._2130848041_res_0x7f022929));
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
