package com.huawei.ui.homehealth.runcard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.runcard.PaceRangeShowActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.AthleticAbilityEmptyActivity;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.caj;
import defpackage.cam;
import defpackage.fgc;
import defpackage.ggl;
import defpackage.gvv;
import defpackage.hqa;
import defpackage.nsn;
import defpackage.ruf;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Date;

/* loaded from: classes6.dex */
public class PaceRangeShowActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f9512a;
    private HealthTextView aa;
    private HealthTextView ac;
    private HealthTextView ad;
    private HealthCardView b;
    private HealthImageView c;
    private boolean e;
    private HealthImageView f;
    private HealthImageView g;
    private HealthTextView h;
    private HealthImageView i;
    private HealthImageView j;
    private HealthTextView k;
    private HealthTextView l;
    private boolean n;
    private HealthTextView o;
    private double p;
    private RunningStateIndexData q;
    private HealthTextView r;
    private HealthTextView s;
    private int[] t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthTextView z;
    private boolean m = true;
    private Handler d = new d(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pace_range_show);
        l();
        h();
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b();
    }

    private void l() {
        getWindow().setBackgroundDrawable(null);
        ((CustomTitleBar) findViewById(R.id.ctb_pace_range_show_title)).setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.b = (HealthCardView) findViewById(R.id.hcv_top_prompt_container);
        this.k = (HealthTextView) findViewById(R.id.htv_knows_btn);
        this.o = (HealthTextView) findViewById(R.id.htv_learn_btn);
        HealthCardView healthCardView = (HealthCardView) findViewById(R.id.hcv_pace_range_list_container);
        b(healthCardView);
        this.u = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_easy_run_label);
        this.z = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_marathon_label);
        this.ad = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_lactic_acid_label);
        this.x = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_anaerobic_label);
        this.ac = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_take_oxygen_label);
        this.f = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_easy_run);
        this.g = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_marathon);
        this.i = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_lactic_acid);
        this.c = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_anaerobic);
        this.j = (HealthImageView) healthCardView.findViewById(R.id.hiv_range_take_oxygen);
        this.aa = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_one);
        this.v = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_two);
        this.r = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_three);
        this.s = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_four);
        this.y = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_five);
        this.w = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_six);
        this.l = (HealthTextView) findViewById(R.id.htv_learn_more);
        this.h = (HealthTextView) findViewById(R.id.htv_guide_bubbles);
        this.f9512a = (HealthButton) findViewById(R.id.hbt_modify_pace_range);
    }

    private void b(HealthCardView healthCardView) {
        dfb_((HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_title), 36.0f);
        dfb_((ConstraintLayout) healthCardView.findViewById(R.id.cl_pace_range_title), 36.0f);
        dfb_((HealthTextView) healthCardView.findViewById(R.id.tv_current_pace), 26.0f);
        dfb_((ConstraintLayout) healthCardView.findViewById(R.id.cl_current_pace), 26.0f);
    }

    private void dfb_(View view, float f) {
        view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, f));
    }

    private void h() {
        m();
        k();
        p();
        o();
    }

    private void j() {
        this.k.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.f9512a.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("Track_PaceRangeShowActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.h("Track_PaceRangeShowActivity", "click button is too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.htv_knows_btn) {
            f();
        }
        if (id == R.id.htv_learn_btn) {
            e();
        }
        if (id == R.id.htv_learn_more) {
            c();
        }
        if (id == R.id.hbt_modify_pace_range) {
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.m) {
            this.m = false;
            if (!SharedPreferenceManager.b("pace_zone_module_id", "guide_showed_bottom_modify")) {
                i();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void m() {
        if (UnitUtil.h()) {
            this.p = 1.609344d;
        } else {
            this.p = 1.0d;
        }
    }

    private void g() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeShowActivity.4
            @Override // java.lang.Runnable
            public void run() {
                int[] e = fgc.e();
                if (e.length <= 1) {
                    LogUtil.h("Track_PaceRangeShowActivity", "getEachPaceRangeValue paceZoneArray is default");
                    e = fgc.d();
                }
                LogUtil.a("Track_PaceRangeShowActivity", "paceZoneArray is ", Arrays.toString(e));
                Message obtain = Message.obtain();
                obtain.what = 103;
                obtain.obj = e;
                if (PaceRangeShowActivity.this.d != null) {
                    PaceRangeShowActivity.this.d.sendMessage(obtain);
                } else {
                    LogUtil.h("Track_PaceRangeShowActivity", "getEachPaceRangeValue mHandler is null");
                }
            }
        });
    }

    private void k() {
        this.u.setText(c(1));
        this.z.setText(c(2));
        this.ad.setText(c(3));
        this.x.setText(c(4));
        this.ac.setText(c(5));
    }

    private void p() {
        if (LanguageUtil.bc(this)) {
            this.f.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.g.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.i.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.c.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.j.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            return;
        }
        this.f.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.g.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.i.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.c.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.j.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
    }

    private void q() {
        this.b.setVisibility(this.n && !SharedPreferenceManager.b("pace_zone_module_id", "guide_showed_top_know") && !this.e ? 0 : 8);
    }

    private void o() {
        this.h.setVisibility(SharedPreferenceManager.b("pace_zone_module_id", "guide_showed_bottom_modify") ? 8 : 0);
    }

    private void b() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: opn
            @Override // java.lang.Runnable
            public final void run() {
                PaceRangeShowActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        this.n = cam.a();
        int a2 = ggl.a(new Date(System.currentTimeMillis()));
        new hqa().a(this, a2, a2, 0, new b(this));
    }

    private void f() {
        this.b.setVisibility(8);
        SharedPreferenceManager.d("pace_zone_module_id", "guide_showed_top_know", true);
    }

    private void e() {
        Intent intent = new Intent(this, (Class<?>) AthleticAbilityEmptyActivity.class);
        intent.putExtra("athletic_ability_empty_flag", "running_no_record");
        startActivity(intent);
    }

    private void c() {
        caj.a().a("PACE_ZONE");
    }

    private void i() {
        this.h.setVisibility(8);
        SharedPreferenceManager.d("pace_zone_module_id", "guide_showed_bottom_modify", true);
    }

    private void d() {
        Intent intent = new Intent(this, (Class<?>) PaceRangeActivity.class);
        intent.putExtra("running_level_data", this.q);
        intent.putExtra("setting_auto_calc_key_status", this.n);
        intent.putExtra("intent_pace_zone", this.t);
        startActivityForResult(intent, 1);
    }

    private boolean e(RunningStateIndexData runningStateIndexData) {
        if (runningStateIndexData == null) {
            LogUtil.h("Track_PaceRangeShowActivity", "hasRunningDataFromCloud data is null");
            return false;
        }
        RunningLevelCurrentData runningLevelCurrentData = runningStateIndexData.getRunningLevelCurrentData();
        if (runningLevelCurrentData == null) {
            LogUtil.h("Track_PaceRangeShowActivity", "hasRunningDataFromCloud runningLevelCurrentData is null");
            return false;
        }
        if (runningLevelCurrentData.getLastCurrentRunLevel() != 0.0f) {
            return true;
        }
        LogUtil.h("Track_PaceRangeShowActivity", "hasRunningDataFromCloud currentRunLevel is zero");
        return false;
    }

    private String c(int i) {
        return getResources().getString(R$string.IDS_pace_range_label_number, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.aa.setText(gvv.a((float) (this.t[0] * this.p)));
        this.v.setText(gvv.a((float) (this.t[1] * this.p)));
        this.r.setText(gvv.a((float) (this.t[2] * this.p)));
        this.s.setText(gvv.a((float) (this.t[3] * this.p)));
        this.y.setText(gvv.a((float) (this.t[4] * this.p)));
        this.w.setText(gvv.a((float) (this.t[5] * this.p)));
    }

    private void c(float f) {
        this.aa.setText(cam.c(this, 0, f, this.p));
        this.v.setText(cam.c(this, 1, f, this.p));
        this.r.setText(cam.c(this, 2, f, this.p));
        this.s.setText(cam.c(this, 3, f, this.p));
        this.y.setText(cam.c(this, 4, f, this.p));
        this.w.setText(cam.c(this, 5, f, this.p));
    }

    private void d(float f) {
        final int[] iArr = {cam.e(0, f), cam.e(1, f), cam.e(2, f), cam.e(3, f), cam.e(4, f), cam.e(5, f)};
        this.t = iArr;
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeShowActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Track_PaceRangeShowActivity", "savePaceZoneValue currentPaceZone is ", Arrays.toString(iArr));
                cam.c(iArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        if (obj instanceof GetRunLevelRsp) {
            RunningStateIndexData c = ruf.c((GetRunLevelRsp) obj);
            if (e(c)) {
                this.e = true;
                this.q = c;
            } else {
                this.e = false;
            }
        } else {
            this.e = false;
        }
        q();
        if (this.e && this.n) {
            float a2 = (float) UnitUtil.a(this.q.getRunningLevelCurrentData().getLastCurrentRunLevel(), 1);
            c(a2);
            d(a2);
            return;
        }
        g();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 2 && intent != null) {
            try {
                int[] intArrayExtra = intent.getIntArrayExtra("current_pace_zone_value");
                if (intArrayExtra == null || intArrayExtra.length != 6) {
                    return;
                }
                LogUtil.a("Track_PaceRangeShowActivity", "onActivityResult currentPaceZone is ", Arrays.toString(intArrayExtra));
                this.t = intArrayExtra;
                n();
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("Track_PaceRangeShowActivity", "onActivityResult ArrayIndexOutOfBoundsException");
            }
        }
    }

    static class d extends BaseHandler<PaceRangeShowActivity> {
        d(PaceRangeShowActivity paceRangeShowActivity) {
            super(paceRangeShowActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dfc_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PaceRangeShowActivity paceRangeShowActivity, Message message) {
            Object obj = message.obj;
            switch (message.what) {
                case 101:
                    paceRangeShowActivity.b(obj);
                    break;
                case 102:
                    paceRangeShowActivity.e = false;
                    paceRangeShowActivity.b((Object) null);
                    break;
                case 103:
                    if (obj instanceof int[]) {
                        paceRangeShowActivity.t = (int[]) obj;
                        paceRangeShowActivity.n();
                        break;
                    }
                    break;
                default:
                    LogUtil.h("Track_PaceRangeShowActivity", "PaceRangeShowActivityHandler handleMessageWhenReferenceNotNull switch default");
                    break;
            }
        }
    }

    static class b implements IBaseResponseCallback {
        private WeakReference<PaceRangeShowActivity> c;

        b(PaceRangeShowActivity paceRangeShowActivity) {
            this.c = new WeakReference<>(paceRangeShowActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            PaceRangeShowActivity paceRangeShowActivity = this.c.get();
            if (paceRangeShowActivity == null || paceRangeShowActivity.d == null) {
                LogUtil.h("Track_PaceRangeShowActivity", "MyRunningData onResponse activity or mHandler is null");
            } else {
                if (i == 200) {
                    Message obtainMessage = paceRangeShowActivity.d.obtainMessage(101);
                    obtainMessage.obj = obj;
                    paceRangeShowActivity.d.sendMessage(obtainMessage);
                    return;
                }
                paceRangeShowActivity.d.sendEmptyMessage(102);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
