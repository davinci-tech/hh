package com.huawei.sim.esim.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.sim.esim.view.adapter.MultiSimAdapter;
import com.huawei.sim.multisim.activity.EsimOpenMethodActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listviewforscroll.ListViewForScroll;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.knl;
import defpackage.ktx;
import defpackage.lop;
import defpackage.nca;
import defpackage.ncd;
import defpackage.nce;
import defpackage.ncf;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class MultiSimCardActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private LinearLayout ab;
    private MultiSimAdapter ac;
    private MultiSimDeviceInfo ad;
    private List<Map<String, Object>> ae;
    private CustomTitleBar af;
    private HealthTextView ag;
    private RelativeLayout ah;
    private HealthTextView ai;
    private LinearLayout l;
    private int m;
    private FrameLayout n;
    private ImageView o;
    private LinearLayout p;
    private ImageView q;
    private ImageView r;
    private Context s;
    private LinearLayout t;
    private LinearLayout u;
    private HealthButton v;
    private HealthTextView w;
    private int y;
    private FrameLayout z;
    private String[] aa = {BaseApplication.getContext().getString(R.string._2130847989_res_0x7f0228f5), BaseApplication.getContext().getString(R.string._2130848145_res_0x7f022991), BaseApplication.getContext().getString(R.string._2130848142_res_0x7f02298e)};
    private int k = 30;
    private MultiSimAdapter.OnRadioButtonClickListener x = new MultiSimAdapter.OnRadioButtonClickListener() { // from class: com.huawei.sim.esim.view.MultiSimCardActivity.2
        @Override // com.huawei.sim.esim.view.adapter.MultiSimAdapter.OnRadioButtonClickListener
        public void onClick(int i) {
            if (MultiSimCardActivity.this.y != i) {
                MultiSimCardActivity.this.u.setVisibility(8);
            }
            MultiSimCardActivity.this.y = i;
            LogUtil.a("MultiSimCardActivity", "Mulit Sim Index is:", Integer.valueOf(MultiSimCardActivity.this.y));
        }
    };

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.s = this;
        this.e = new b(this);
        setContentView(R.layout.activity_multi_sim_card);
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getIntExtra("BatteryThreshold", this.k);
            this.m = intent.getIntExtra("esim_card_type_key", 1);
            this.ad = (MultiSimDeviceInfo) intent.getParcelableExtra("multi_sim_device_info");
        }
        LogUtil.a("MultiSimCardActivity", "onCreate mCardType = ", Integer.valueOf(this.m));
        g();
    }

    private void g() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.multi_sim_title_bar);
        this.af = customTitleBar;
        if (this.m == 2) {
            customTitleBar.setTitleText(getString(R.string._2130848080_res_0x7f022950));
        }
        this.af.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.ah = (RelativeLayout) findViewById(R.id.multi_sim_waiting_content);
        this.v = (HealthButton) findViewById(R.id.btn_esim_confirm);
        this.ai = (HealthTextView) findViewById(R.id.tv_multisim_tips);
        this.u = (LinearLayout) findViewById(R.id.ll_error_tips);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_set_net_layout);
        this.ab = linearLayout;
        linearLayout.setOnClickListener(this);
        this.w = (HealthTextView) findViewById(R.id.tv_error_tips);
        l();
        this.ah.setVisibility(8);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_esim_user_guide);
        this.ag = healthTextView;
        healthTextView.setText(ncf.e(this.s.getString(R.string._2130848023_res_0x7f022917)));
        this.z = (FrameLayout) findViewById(R.id.multi_sim_card_list_layout);
        j();
        this.ag.setOnClickListener(this);
        this.v.setOnClickListener(this);
        ListViewForScroll listViewForScroll = (ListViewForScroll) findViewById(R.id.multi_sim_listview);
        if (this.m == 2) {
            this.ac = new MultiSimAdapter(this.s, this.aa);
        } else {
            this.ae = ncf.a(this.s);
            this.ac = new MultiSimAdapter(this.s, this.ae);
        }
        this.ac.b(this.x);
        listViewForScroll.setAdapter((ListAdapter) this.ac);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.multi_esim_subheader_title);
        TextView textView = (TextView) healthSubHeader.findViewById(R.id.hwsubheader_title_left);
        if (textView != null) {
            if (this.m == 2) {
                textView.setText(R.string._2130848083_res_0x7f022953);
            }
            textView.setAllCaps(false);
        }
        healthSubHeader.setSplitterVisibility(4);
        healthSubHeader.setSubHeaderBackgroundColor(this.s.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        n();
    }

    private void n() {
        if (this.m == 2) {
            this.n.setVisibility(8);
            this.z.setVisibility(0);
        } else {
            this.n.setVisibility(8);
            this.z.setVisibility(0);
        }
        if (nrt.a(this.s)) {
            this.u.setBackgroundResource(R.drawable.bg_error_layout_dark_mode_background);
        } else {
            this.u.setBackgroundResource(R.drawable.bg_error_layout_background);
        }
    }

    private void j() {
        this.n = (FrameLayout) findViewById(R.id.android_verson_below_seven_layout);
        this.o = (ImageView) findViewById(R.id.cmcc_arrow);
        this.r = (ImageView) findViewById(R.id.cucc_arrow);
        this.q = (ImageView) findViewById(R.id.ctcc_arrow);
        if (LanguageUtil.bc(this.s)) {
            this.o.setBackground(this.s.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.r.setBackground(this.s.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.q.setBackground(this.s.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.o.setBackground(this.s.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.r.setBackground(this.s.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.q.setBackground(this.s.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.l = (LinearLayout) findViewById(R.id.cmcc_layout);
        this.p = (LinearLayout) findViewById(R.id.cucc_layout);
        this.t = (LinearLayout) findViewById(R.id.ctcc_layout);
        this.l.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.t.setOnClickListener(this);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.select_carrier_subheader);
        TextView textView = (TextView) healthSubHeader.findViewById(R.id.hwsubheader_title_left);
        if (textView != null) {
            textView.setAllCaps(false);
        }
        healthSubHeader.setSubHeaderBackgroundColor(this.s.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
    }

    private void l() {
        String format;
        String e = UnitUtil.e(1.0d, 1, 0);
        String e2 = UnitUtil.e(2.0d, 1, 0);
        String e3 = UnitUtil.e(3.0d, 1, 0);
        String e4 = UnitUtil.e(4.0d, 1, 0);
        if (!Utils.o()) {
            if (this.m == 1) {
                String e5 = UnitUtil.e(5.0d, 1, 0);
                format = String.format(Locale.ENGLISH, this.s.getString(R.string._2130848139_res_0x7f02298b), e, e2, UnitUtil.e(20.0d, 1, 0), e3, e4, e5);
            } else {
                format = String.format(Locale.ENGLISH, this.s.getString(R.string._2130848140_res_0x7f02298c), e, UnitUtil.e(5.0d, 1, 0), e2, e3, e4);
            }
        } else {
            format = String.format(Locale.ENGLISH, this.s.getString(R.string._2130848063_res_0x7f02293f), e, e2, e3, e4);
        }
        this.ai.setText(format);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_esim_confirm) {
            if (!ktx.e().g()) {
                nrh.b(this.s, R.string._2130848008_res_0x7f022908);
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                this.ah.setVisibility(0);
                h();
            }
        } else if (view.getId() == R.id.tv_esim_user_guide) {
            c(this.s.getString(R.string._2130848023_res_0x7f022917), ncf.d(this.s));
        } else if (view.getId() == R.id.cmcc_layout) {
            b("46000000", this.m);
        } else if (view.getId() == R.id.cucc_layout) {
            b("46001000", this.m);
        } else if (view.getId() == R.id.ctcc_layout) {
            b("46003000", this.m);
        } else if (view.getId() == R.id.ll_set_net_layout) {
            CommonUtil.q(this.s);
        } else {
            LogUtil.c("MultiSimCardActivity", "onClick else branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        ktx.e().d(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.MultiSimCardActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MultiSimCardActivity", "getMultiSimBatteryThreshold onResponse errorCode :", Integer.valueOf(i));
                MultiSimCardActivity.this.e.sendMessage(MultiSimCardActivity.this.e.obtainMessage(10003));
                Message obtainMessage = MultiSimCardActivity.this.e.obtainMessage();
                if (i == 0) {
                    obtainMessage.what = 10004;
                } else if (i == 129001) {
                    obtainMessage.what = 10005;
                    sqo.o("MSG_BATTERY_DATA_ERROR");
                } else {
                    obtainMessage.what = 10006;
                    sqo.o("MSG_BATTERY_DATA_FAIL");
                }
                MultiSimCardActivity.this.e.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        nrh.d(this.s, String.format(Locale.ENGLISH, this.s.getString(R.string.IDS_plugin_multi_esim_device_low_power), UnitUtil.e(i, 2, 0)));
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.removeMessages(10003);
            this.e.removeMessages(10004);
            this.e.removeMessages(10005);
            this.e.removeMessages(10006);
        }
    }

    static class b extends BaseHandler<MultiSimCardActivity> {
        b(MultiSimCardActivity multiSimCardActivity) {
            super(multiSimCardActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csk_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MultiSimCardActivity multiSimCardActivity, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 10001:
                        multiSimCardActivity.o();
                        break;
                    case 10002:
                    default:
                        LogUtil.h("MultiSimCardActivity", "handleMessageWhenReferenceNotNull default");
                        break;
                    case 10003:
                        multiSimCardActivity.m();
                        break;
                    case 10004:
                        if (multiSimCardActivity.m == 2) {
                            multiSimCardActivity.b();
                            break;
                        } else {
                            multiSimCardActivity.e();
                            break;
                        }
                    case 10005:
                        multiSimCardActivity.d(multiSimCardActivity.k);
                        break;
                    case 10006:
                        multiSimCardActivity.s();
                        break;
                }
            }
            LogUtil.h("MultiSimCardActivity", "handleMessageWhenReferenceNotNull msg is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (!ncd.a(this.s)) {
            this.ac.b((MultiSimAdapter.OnRadioButtonClickListener) null);
            this.v.setEnabled(false);
            this.v.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.u.setVisibility(0);
            this.w.setText(this.s.getResources().getString(R.string._2130848067_res_0x7f022943));
            this.ab.setVisibility(0);
            return;
        }
        if (!ncd.c() || !ncd.e(this.s)) {
            this.ac.b((MultiSimAdapter.OnRadioButtonClickListener) null);
            this.v.setEnabled(false);
            this.v.setBackgroundResource(R.drawable._2131427591_res_0x7f0b0107);
            this.u.setVisibility(0);
            this.w.setText(this.s.getResources().getString(R.string._2130848008_res_0x7f022908));
            this.ab.setVisibility(8);
            return;
        }
        this.ac.b(this.x);
        this.v.setEnabled(true);
        this.v.setBackgroundResource(R.drawable._2131430895_res_0x7f0b0def);
        this.u.setVisibility(8);
        this.ab.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.ac.b(this.x);
        this.ah.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        List<Map<String, Object>> list = this.ae;
        if (list == null || list.isEmpty()) {
            LogUtil.h("MultiSimCardActivity", "sim cards is none");
            Context context = this.s;
            nrh.d(context, context.getResources().getString(R.string._2130847998_res_0x7f0228fe));
            return;
        }
        int e = this.ac.e();
        this.y = e;
        Object obj = this.ae.get(e).get("imsi");
        String str = obj instanceof String ? (String) obj : "";
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimCardActivity", "sim provider is null");
            sqo.o("sim provider is null");
            return;
        }
        k();
        if (nce.d()) {
            b(str);
            return;
        }
        if (!Utils.o()) {
            if ((CommonUtil.bh() || ncf.e()) && ncf.b(str)) {
                ncf.b("03", true);
                b("", str, true);
                return;
            } else if (!ncf.i(str)) {
                nrh.d(this.s, getResources().getString(R.string._2130847991_res_0x7f0228f7));
                sqo.o("Your SIM card doesn't support multi-device services");
                return;
            } else {
                ncf.b(a.t, true);
                b(str, this.m);
                return;
            }
        }
        LogUtil.b("MultiSimCardActivity", "Oversea only support scan code activation");
    }

    private void k() {
        this.g = this.y;
        Map<String, Object> map = this.ae.get(this.y);
        if (map == null || !map.containsKey("slotId")) {
            return;
        }
        Object obj = this.ae.get(this.y).get("slotId");
        if (obj instanceof Integer) {
            this.g = ((Integer) obj).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Context context = this.s;
        nrh.d(context, context.getResources().getString(R.string._2130847980_res_0x7f0228ec));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("MultiSimCardActivity", "onResume isOperatorOpenSuccess = ", Boolean.valueOf(j));
        if (j) {
            finish();
        }
    }

    private ArrayList<String> e(nca ncaVar) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        String a2 = ncaVar.a();
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("MultiSimCardActivity", "openMethod is null");
            return arrayList;
        }
        LogUtil.a("MultiSimCardActivity", "getOpenMethod openMethod = ", a2);
        if (a2.contains("|")) {
            for (String str : a2.split("\\|")) {
                arrayList.add(str);
            }
        } else {
            arrayList.add(a2);
        }
        return arrayList;
    }

    private void b(String str) {
        LogUtil.a("MultiSimCardActivity", "enterOpenMethodProcess enter");
        int i = this.y;
        if (i < 0 || i >= this.ae.size()) {
            LogUtil.h("MultiSimCardActivity", "mMulitSimIndex is out of range");
            return;
        }
        nca e = nce.e(this.ae.get(this.y));
        if (e == null) {
            LogUtil.h("MultiSimCardActivity", "esimOpenMethod is null");
            sqo.o("esimOpenMethod is null");
            return;
        }
        b(e, str);
        String b2 = ncf.b(this.s, str);
        this.j = e.d();
        this.c = e.c();
        this.i = e.e();
        ArrayList<String> e2 = e(e);
        if (e2.isEmpty()) {
            LogUtil.h("MultiSimCardActivity", "openMethodList is empty.");
            sqo.o("openMethodList is empty.");
            return;
        }
        if (e2.size() >= 2) {
            if (e2.contains("04") && TextUtils.isEmpty(lop.e(BaseApplication.getContext(), "ODSA_ESURL", this.g, ""))) {
                i();
                sqo.o("isSupport is Empty");
                return;
            }
            try {
                Intent intent = new Intent(this.s, (Class<?>) EsimOpenMethodActivity.class);
                intent.putExtra("esim_operator_key", this.i);
                intent.putExtra("esim_auth_method", this.c);
                intent.putExtra("simImsi", str);
                intent.putExtra("MultiSimSlotId", this.g);
                intent.putExtra("esim_default_url", this.j);
                intent.putExtra("esim_operator_name_key", b2);
                intent.putExtra("multi_sim_device_info", this.ad);
                intent.putStringArrayListExtra("openMethodList", e2);
                this.s.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("MultiSimCardActivity", "enterOpenMethodProcess ActivityNotFoundException");
                return;
            }
        }
        if (e2.size() == 1) {
            a(e2, str);
        } else {
            LogUtil.h("MultiSimCardActivity", "enterOpenMethodProcess else branch");
        }
    }

    private void b(nca ncaVar, String str) {
        if (ncf.b(str)) {
            if (CommonUtil.bh() || ncf.e()) {
                ncaVar.b("03");
            } else {
                ncaVar.b(a.t);
            }
        }
    }

    private void a(ArrayList<String> arrayList, String str) {
        String str2 = arrayList.get(0);
        ncf.b(str2, true);
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "imsi", knl.d(str), (StorageParams) null);
        if (str2.equals(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            d();
            return;
        }
        if (str2.equals(a.t)) {
            b(str, this.m);
            return;
        }
        if (str2.equals("03")) {
            b(str2, str, true);
            return;
        }
        if (str2.equals("04")) {
            String e = lop.e(BaseApplication.getContext(), "ODSA_ESURL", this.g, "");
            LogUtil.a("MultiSimCardActivity", "handleOneOpenMethod TS.43 ESs branch");
            if (ncd.b(this.i, this.c)) {
                this.u.setVisibility(0);
                this.w.setText(this.s.getResources().getString(R.string._2130848055_res_0x7f022937));
                return;
            } else if (TextUtils.isEmpty(e)) {
                i();
                return;
            } else {
                b(str2, str, true);
                return;
            }
        }
        if (str2.equals("05")) {
            LogUtil.a("MultiSimCardActivity", "handleOneOpenMethod H5 branch");
            this.u.setVisibility(0);
            this.w.setText(this.s.getResources().getString(R.string._2130848049_res_0x7f022931));
        } else {
            if (str2.equals("06")) {
                c();
                return;
            }
            LogUtil.h("MultiSimCardActivity", "handleOneOpenMethod else branch");
            this.u.setVisibility(0);
            this.w.setText(this.s.getResources().getString(R.string._2130848049_res_0x7f022931));
            sqo.o("handleOneOpenMethod else branch");
        }
    }

    private void i() {
        ReleaseLogUtil.e("DEVMGR_MultiSimCardActivity", "sdk return param isSupport is false");
        this.u.setVisibility(0);
        this.w.setText(this.s.getResources().getString(R.string._2130848049_res_0x7f022931));
    }

    private void c(final String str, final String str2) {
        if (nsn.o()) {
            return;
        }
        LogUtil.a("MultiSimCardActivity", "startWebView is enter");
        ThreadPoolManager.d().d("MultiSimCardActivity", new Runnable() { // from class: com.huawei.sim.esim.view.MultiSimCardActivity.3
            @Override // java.lang.Runnable
            public void run() {
                final String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdn");
                if (TextUtils.isEmpty(url)) {
                    return;
                }
                MultiSimCardActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.esim.view.MultiSimCardActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ncf.e(MultiSimCardActivity.this.s, str, url + str2);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("MultiSimCardActivity", "enterStandaloneNumOpenMethod enter");
        int e = this.ac.e();
        this.y = e;
        if (e >= 0) {
            String[] strArr = this.aa;
            if (e < strArr.length) {
                String str = strArr[e];
                String f = f();
                if (TextUtils.isEmpty(f)) {
                    LogUtil.h("MultiSimCardActivity", "enterStandaloneNumOpenMethod imsi is empty");
                    sqo.o("enterStandaloneNumOpenMethod imsi is empty");
                    return;
                }
                ArrayList<String> d = ncf.d(this.s, str);
                if (d == null || d.size() == 0) {
                    LogUtil.h("MultiSimCardActivity", "enterStandaloneNumOpenMethod openMethodList is empty.");
                    sqo.o("enterStandaloneNumOpenMethod openMethodList is empty.");
                    return;
                }
                if (d.size() >= 2) {
                    try {
                        Intent intent = new Intent(this.s, (Class<?>) EsimOpenMethodActivity.class);
                        intent.putExtra("esim_card_type_key", this.m);
                        intent.putExtra("esim_operator_name_key", str);
                        intent.putStringArrayListExtra("openMethodList", d);
                        intent.putExtra("simImsi", f);
                        intent.putExtra("multi_sim_device_info", this.ad);
                        this.s.startActivity(intent);
                        return;
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("MultiSimCardActivity", "enterStandaloneNumOpenMethod ActivityNotFoundException");
                        return;
                    }
                }
                if (d.size() == 1) {
                    d(d, f);
                    return;
                } else {
                    LogUtil.h("MultiSimCardActivity", "enterStandaloneNumOpenMethod else branch");
                    return;
                }
            }
        }
        LogUtil.h("MultiSimCardActivity", "enterStandaloneNumOpenMethod mMulitSimIndex is out of range.");
    }

    private String f() {
        int i = this.y;
        return i == 0 ? "46000" : i == 1 ? "46001" : i == 2 ? "46003" : "";
    }

    private void d(ArrayList<String> arrayList, String str) {
        String str2 = arrayList.get(0);
        if (str2.equals(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            b(this.ad);
            return;
        }
        if (str2.equals("07")) {
            LogUtil.a("MultiSimCardActivity", "handleStandaloneNumOpenMethod hand hall method");
            b(str, this.m);
        } else {
            LogUtil.h("MultiSimCardActivity", "handleStandaloneNumOpenMethod else branch");
            sqo.o("handleStandaloneNumOpenMethod else branch");
            this.u.setVisibility(0);
            this.w.setText(this.s.getResources().getString(R.string._2130848049_res_0x7f022931));
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
