package com.huawei.sim.esim.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jpt;
import defpackage.ktx;
import defpackage.ncf;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class EsimCardStatusGetActivity extends BaseOpenSimCardActivity implements View.OnClickListener {
    private HealthTextView aa;
    private HealthProgressBar ab;
    private RelativeLayout ac;
    private int k;
    private LinearLayout l;
    private Context m;
    private int n;
    private HealthButton o;
    private HealthImageView p;
    private HealthTextView q;
    private RelativeLayout r;
    private HealthImageView s;
    private MultiSimDeviceInfo t;
    private CustomTitleBar u;
    private HealthImageView v;
    private HealthTextView w;
    private RelativeLayout x;
    private LinearLayout y;

    static /* synthetic */ int h(EsimCardStatusGetActivity esimCardStatusGetActivity) {
        int i = esimCardStatusGetActivity.k;
        esimCardStatusGetActivity.k = i + 1;
        return i;
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_esim_card_status_get);
        this.m = this;
        this.e = new a(this);
        LogUtil.a("EsimCardStatusGetActivity", "onCreate.");
        ktx.e().n();
        e();
        j();
        b();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.esim_title_bar);
        this.u = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.ac = (RelativeLayout) findViewById(R.id.card_type_status_waiting_layout);
        this.ab = (HealthProgressBar) findViewById(R.id.card_type_status_waiting_image);
        this.aa = (HealthTextView) findViewById(R.id.card_type_status_waiting_text);
        this.w = (HealthTextView) findViewById(R.id.textView_recommend);
        this.r = (RelativeLayout) findViewById(R.id.card_type_status_fail_layout);
        this.s = (HealthImageView) findViewById(R.id.card_type_status_fail_image);
        this.q = (HealthTextView) findViewById(R.id.card_type_status_fail_text);
        this.o = (HealthButton) findViewById(R.id.card_type_status_retry_btn);
        this.p = (HealthImageView) findViewById(R.id.multi_sim_image);
        this.v = (HealthImageView) findViewById(R.id.standalone_num_image);
        this.l = (LinearLayout) findViewById(R.id.card_type_select_layout);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.multi_sim_layout);
        this.x = relativeLayout;
        relativeLayout.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.standalone_num_layout);
        this.y = linearLayout;
        linearLayout.setOnClickListener(this);
        if (LanguageUtil.bc(this.m)) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131427605_res_0x7f0b0115);
            this.p.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.v.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.w.setBackground(drawable);
            return;
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131427604_res_0x7f0b0114);
        this.p.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        this.v.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        this.w.setBackground(drawable2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        if (view.getId() == R.id.multi_sim_layout) {
            i = 1;
        } else {
            if (view.getId() != R.id.standalone_num_layout) {
                LogUtil.h("EsimCardStatusGetActivity", "onClick else branch.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            i = 2;
        }
        LogUtil.a("EsimCardStatusGetActivity", "onClick cardType = ", Integer.valueOf(i));
        ktx.e().a(jpt.a("EsimCardStatusGetActivity"), i);
        try {
            Intent intent = new Intent();
            intent.setClass(this, EsimManagerActivity.class);
            intent.putExtra("esim_card_type_key", i);
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("EsimCardStatusGetActivity", "onClick ActivityNotFoundException.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        this.r.setVisibility(0);
        this.ac.setVisibility(8);
        this.l.setVisibility(8);
        HealthTextView healthTextView = this.q;
        if (healthTextView != null) {
            healthTextView.setText(str);
            this.q.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.sim.esim.view.EsimCardStatusGetActivity.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    EsimCardStatusGetActivity.this.q.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (EsimCardStatusGetActivity.this.q.getLineCount() >= 2) {
                        EsimCardStatusGetActivity.this.q.setGravity(GravityCompat.START);
                    }
                }
            });
        }
        HealthImageView healthImageView = this.s;
        if (healthImageView != null) {
            Context context = this.m;
            ncf.csP_(context, healthImageView, nsn.c(context, 120.0f));
            Drawable drawable = getResources().getDrawable(R.drawable._2131427756_res_0x7f0b01ac);
            drawable.setAutoMirrored(true);
            this.s.setImageDrawable(drawable);
        }
        if (z) {
            this.o.setText(R.string._2130847924_res_0x7f0228b4);
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimCardStatusGetActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("EsimCardStatusGetActivity", "initFailView click retry button.");
                    EsimCardStatusGetActivity.this.j();
                    EsimCardStatusGetActivity.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            this.o.setText(R.string._2130847966_res_0x7f0228de);
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimCardStatusGetActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("EsimCardStatusGetActivity", "initFailView click known button.");
                    EsimCardStatusGetActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.r.setVisibility(8);
        this.ac.setVisibility(0);
        this.l.setVisibility(8);
        HealthTextView healthTextView = this.aa;
        if (healthTextView != null) {
            healthTextView.setText(this.m.getString(R.string._2130847997_res_0x7f0228fd));
        }
        HealthProgressBar healthProgressBar = this.ab;
        if (healthProgressBar != null) {
            Context context = this.m;
            ncf.csP_(context, healthProgressBar, nsn.c(context, 66.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!ktx.e().g()) {
            this.e.sendEmptyMessage(10005);
            nrh.b(this.m, R.string._2130848008_res_0x7f022908);
        } else {
            ktx.e().b(new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimCardStatusGetActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("EsimCardStatusGetActivity", "getEsimCardStatus onResponse errorCode = ", Integer.valueOf(i));
                    ncf.c(i, false);
                    if (i != 1 || EsimCardStatusGetActivity.this.k >= 5) {
                        EsimCardStatusGetActivity.this.e(i, obj);
                        return;
                    }
                    EsimCardStatusGetActivity.h(EsimCardStatusGetActivity.this);
                    LogUtil.h("EsimCardStatusGetActivity", "power down, retry ");
                    EsimCardStatusGetActivity.this.e.sendEmptyMessageDelayed(10002, 1000L);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj) {
        if (i == 0 && (obj instanceof MultiSimDeviceInfo)) {
            MultiSimDeviceInfo multiSimDeviceInfo = (MultiSimDeviceInfo) obj;
            this.t = multiSimDeviceInfo;
            LogUtil.a("EsimCardStatusGetActivity", "processResultData mMultiSimDeviceInfo = ", multiSimDeviceInfo);
            g();
            return;
        }
        sqo.o("GET_CARD_STATUS_FAIL");
        this.e.sendEmptyMessage(10005);
    }

    private void g() {
        MultiSimDeviceInfo multiSimDeviceInfo = this.t;
        if (multiSimDeviceInfo != null && !TextUtils.isEmpty(multiSimDeviceInfo.getEID())) {
            List<SimInfo> simInfoList = this.t.getSimInfoList();
            if (simInfoList == null || simInfoList.isEmpty()) {
                this.e.sendEmptyMessage(10003);
                return;
            } else {
                if (simInfoList.size() == 1) {
                    h();
                    return;
                }
                this.e.sendEmptyMessage(10006);
                LogUtil.h("EsimCardStatusGetActivity", "There are multiple profile files.");
                sqo.o("There are multiple profile files.");
                return;
            }
        }
        int i = this.k;
        if (i < 5) {
            this.k = i + 1;
            LogUtil.a("EsimCardStatusGetActivity", "retry refreshDeviceStatus.");
            this.e.sendEmptyMessageDelayed(10002, 1000L);
        } else {
            this.e.sendEmptyMessage(10005);
            sqo.o("MSG_GET_CARD_STATUS_FAIL.");
        }
    }

    private void h() {
        this.e.sendEmptyMessageDelayed(10007, PreConnectManager.CONNECT_INTERNAL);
        ktx.e().c(jpt.a("EsimCardStatusGetActivity"), new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimCardStatusGetActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof Integer) {
                    EsimCardStatusGetActivity.this.n = ((Integer) obj).intValue();
                }
                LogUtil.a("EsimCardStatusGetActivity", "sendGetCardTypeCommand mCardType = ", Integer.valueOf(EsimCardStatusGetActivity.this.n));
                EsimCardStatusGetActivity.this.e.sendEmptyMessage(10004);
            }
        });
    }

    static class a extends BaseHandler<EsimCardStatusGetActivity> {
        a(EsimCardStatusGetActivity esimCardStatusGetActivity) {
            super(esimCardStatusGetActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: crT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimCardStatusGetActivity esimCardStatusGetActivity, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 10002:
                        esimCardStatusGetActivity.b();
                        break;
                    case 10003:
                        esimCardStatusGetActivity.ac.setVisibility(8);
                        esimCardStatusGetActivity.r.setVisibility(8);
                        esimCardStatusGetActivity.l.setVisibility(0);
                        break;
                    case 10004:
                        if (esimCardStatusGetActivity.e.hasMessages(10007)) {
                            esimCardStatusGetActivity.e.removeMessages(10007);
                        }
                        esimCardStatusGetActivity.f();
                        esimCardStatusGetActivity.finish();
                        break;
                    case 10005:
                        esimCardStatusGetActivity.b(esimCardStatusGetActivity.m.getString(R.string.IDS_plugin_device_info_fail), true);
                        break;
                    case 10006:
                        esimCardStatusGetActivity.b(esimCardStatusGetActivity.m.getString(R.string._2130848093_res_0x7f02295d), false);
                        break;
                    case 10007:
                        esimCardStatusGetActivity.f();
                        esimCardStatusGetActivity.finish();
                        break;
                    default:
                        LogUtil.h("EsimCardStatusGetActivity", "handleMessageWhenReferenceNotNull default");
                        break;
                }
            }
            LogUtil.a("EsimCardStatusGetActivity", "handleMessageWhenReferenceNotNull msg is null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("EsimCardStatusGetActivity", "onResume isOperatorOpenSuccess = ", Boolean.valueOf(j));
        if (j) {
            ktx.e().a(false);
            finish();
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.removeMessages(10002);
            this.e.removeMessages(10003);
            this.e.removeMessages(10004);
            this.e.removeMessages(10005);
            this.e.removeMessages(10006);
            this.e.removeMessages(10007);
        }
        ktx.e().p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            Intent intent = new Intent(this.m, (Class<?>) EsimManagerActivity.class);
            intent.putExtra("esim_card_type_key", this.n);
            intent.putExtra("esim_is_open", true);
            intent.putExtra("esiim_multi_sim_deviceinfo", this.t);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("EsimCardStatusGetActivity", "startEsimManagerActivity ActivityNotFoundException");
        }
    }

    @Override // com.huawei.sim.esim.view.BaseOpenSimCardActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
