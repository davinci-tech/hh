package com.huawei.sim.multisim.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.view.BaseOpenSimCardActivity;
import com.huawei.sim.esim.view.adapter.MultiSimAdapter;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listviewforscroll.ListViewForScroll;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncd;
import defpackage.ncf;
import defpackage.nrh;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class EsimSelectCardActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private Context k;
    private HealthButton l;
    private MultiSimAdapter m;
    private int o;
    private HealthTextView p;
    private HealthTextView q;
    private LinearLayout r;
    private LinearLayout s;
    private CustomTitleBar u;
    private List<Map<String, Object>> v;
    private RelativeLayout x;
    private int n = 30;
    private MultiSimAdapter.OnRadioButtonClickListener t = new MultiSimAdapter.OnRadioButtonClickListener() { // from class: com.huawei.sim.multisim.activity.EsimSelectCardActivity.2
        @Override // com.huawei.sim.esim.view.adapter.MultiSimAdapter.OnRadioButtonClickListener
        public void onClick(int i) {
            if (EsimSelectCardActivity.this.o != i) {
                EsimSelectCardActivity.this.s.setVisibility(8);
            }
            EsimSelectCardActivity.this.o = i;
            LogUtil.a("EsimSelectCardActivity", "mSelectCardAdapterListener mCardIndex = ", Integer.valueOf(EsimSelectCardActivity.this.o));
        }
    };

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.k = this;
        this.e = new a(this);
        setContentView(R.layout.activity_esim_select_card);
        e();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.select_card_title_bar);
        this.u = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.x = (RelativeLayout) findViewById(R.id.select_card_waiting_content);
        this.l = (HealthButton) findViewById(R.id.esim_confirm_btn);
        this.p = (HealthTextView) findViewById(R.id.select_card_tips_tv);
        this.s = (LinearLayout) findViewById(R.id.select_card_error_tips_ll);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.select_card_set_net_ll);
        this.r = linearLayout;
        linearLayout.setOnClickListener(this);
        this.q = (HealthTextView) findViewById(R.id.select_card_error_tips_tv);
        f();
        this.x.setVisibility(8);
        this.l.setOnClickListener(this);
        ListViewForScroll listViewForScroll = (ListViewForScroll) findViewById(R.id.select_card_listview);
        this.v = ncf.c(this.k);
        MultiSimAdapter multiSimAdapter = new MultiSimAdapter(this.k, this.v);
        this.m = multiSimAdapter;
        multiSimAdapter.b(this.t);
        listViewForScroll.setAdapter((ListAdapter) this.m);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.select_card_subheader_title);
        TextView textView = (TextView) healthSubHeader.findViewById(R.id.hwsubheader_title_left);
        if (textView != null) {
            textView.setAllCaps(false);
        }
        healthSubHeader.setSplitterVisibility(4);
        healthSubHeader.setSubHeaderBackgroundColor(this.k.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
    }

    private void f() {
        this.p.setText(String.format(Locale.getDefault(), this.k.getString(R.string._2130848074_res_0x7f02294a), 1, 2, 3, 4, 5));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.esim_confirm_btn) {
            this.x.setVisibility(0);
            b();
        } else if (view.getId() == R.id.select_card_set_net_ll) {
            CommonUtil.q(this.k);
        } else {
            LogUtil.c("EsimSelectCardActivity", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        ktx.e().d(new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.activity.EsimSelectCardActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimSelectCardActivity", "getBatteryThresholdStatus onResponse errorCode :", Integer.valueOf(i));
                Message obtainMessage = EsimSelectCardActivity.this.e.obtainMessage();
                if (i == 0) {
                    obtainMessage.what = 10002;
                } else if (i == 129001) {
                    obtainMessage.what = 10003;
                } else {
                    obtainMessage.what = 10004;
                }
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue > 0 && intValue <= 100) {
                        EsimSelectCardActivity.this.n = intValue;
                    }
                } else {
                    LogUtil.h("EsimSelectCardActivity", "getBatteryThresholdStatus onResponse objectData is not Integer");
                }
                EsimSelectCardActivity.this.e.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.m.b(this.t);
        this.x.setVisibility(8);
        nrh.d(this.k, String.format(Locale.ENGLISH, this.k.getString(R.string.IDS_plugin_multi_esim_device_low_power), UnitUtil.e(this.n, 2, 0)));
    }

    static class a extends BaseHandler<EsimSelectCardActivity> {
        a(EsimSelectCardActivity esimSelectCardActivity) {
            super(esimSelectCardActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimSelectCardActivity esimSelectCardActivity, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 10001:
                        esimSelectCardActivity.h();
                        break;
                    case 10002:
                        esimSelectCardActivity.g();
                        break;
                    case 10003:
                        esimSelectCardActivity.j();
                        break;
                    case 10004:
                        esimSelectCardActivity.i();
                        sqo.o("MSG_BATTERY_THRESHOLD_STATUS_FAIL");
                        break;
                    default:
                        LogUtil.h("EsimSelectCardActivity", "handleMessageWhenReferenceNotNull default");
                        break;
                }
            }
            LogUtil.h("EsimSelectCardActivity", "handleMessageWhenReferenceNotNull msg is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!ncd.a(this.k)) {
            this.m.b((MultiSimAdapter.OnRadioButtonClickListener) null);
            this.l.setEnabled(false);
            this.s.setVisibility(0);
            this.q.setText(this.k.getResources().getString(R.string._2130848067_res_0x7f022943));
            this.r.setVisibility(0);
            return;
        }
        if (!ncd.c() || !ncd.e(this.k)) {
            this.m.b((MultiSimAdapter.OnRadioButtonClickListener) null);
            this.l.setEnabled(false);
            this.s.setVisibility(0);
            this.q.setText(this.k.getResources().getString(R.string._2130848008_res_0x7f022908));
            this.r.setVisibility(8);
            return;
        }
        this.m.b(this.t);
        this.l.setEnabled(true);
        this.s.setVisibility(8);
        this.r.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        List<Map<String, Object>> list = this.v;
        if (list == null || list.isEmpty()) {
            LogUtil.h("EsimSelectCardActivity", "startEsimAccountManagePage not support esim account management");
            return;
        }
        this.m.b(this.t);
        this.x.setVisibility(8);
        int e = this.m.e();
        this.o = e;
        this.g = e;
        b(this.v.get(this.o));
        if (ncd.b(this.i, this.c)) {
            this.s.setVisibility(0);
            this.q.setText(this.k.getResources().getString(R.string._2130848055_res_0x7f022937));
        } else {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.m.b(this.t);
        this.x.setVisibility(8);
        Context context = this.k;
        nrh.d(context, context.getResources().getString(R.string._2130847980_res_0x7f0228ec));
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.removeMessages(10002);
            this.e.removeMessages(10003);
            this.e.removeMessages(10004);
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
