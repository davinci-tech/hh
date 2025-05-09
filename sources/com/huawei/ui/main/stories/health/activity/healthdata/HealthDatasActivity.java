package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.wearengine.rqmanager.RqDataPushReceiver;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwcloudmodel.model.unite.UserRunLevelData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.h5pro.jsmodules.MenstrualModule;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import com.huawei.up.model.UserInfomation;
import defpackage.bzs;
import defpackage.dss;
import defpackage.efb;
import defpackage.ffp;
import defpackage.ggl;
import defpackage.gnm;
import defpackage.gyg;
import defpackage.hqa;
import defpackage.ixx;
import defpackage.jct;
import defpackage.jdx;
import defpackage.jec;
import defpackage.koq;
import defpackage.mpf;
import defpackage.nqo;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qkg;
import defpackage.qkh;
import defpackage.qks;
import defpackage.qmf;
import defpackage.qpr;
import defpackage.qsj;
import defpackage.rag;
import defpackage.ruf;
import defpackage.scz;
import defpackage.sdh;
import defpackage.sqp;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HealthDatasActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f10041a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static boolean e = false;
    private static boolean g = false;
    private static boolean h = false;
    private RelativeLayout aa;
    private HealthTextView ab;
    private Context ac;
    private HealthTextView ad;
    private RelativeLayout ae;
    private HealthTextView af;
    private HealthTextView ag;
    private int ah;
    private int ai;
    private Handler aj;
    private HealthOpenSDK ak;
    private int al;
    private int am;
    private GetRunLevelRsp an;
    private RelativeLayout ao;
    private ab ap;
    private HealthTextView aq;
    private boolean ar;
    private HealthTextView as;
    private List<HiHealthData> at;
    private LastTimeHealthDataReader<HealthDatasActivity> au;
    private RelativeLayout av;
    private Map<Integer, Long> aw;
    private Map<View, ClickViewCallback> ax;
    private HealthTextView ay;
    private RunningStateIndexData az;
    private HealthTextView ba;
    private RelativeLayout bb;
    private long bc;
    private aa bd;
    private HealthTextView be;
    private RelativeLayout bf;
    private HealthTextView bg;
    private HealthTextView bh;
    private HealthTextView bj;
    private RelativeLayout bk;
    private int bl;
    private RelativeLayout bm;
    private ad bn;
    private RelativeLayout bo;
    private ae bp;
    private HealthTextView bq;
    private HealthTextView br;
    private HealthTextView bs;
    private RelativeLayout bt;
    private ab bu;
    private HealthTextView bv;
    private List<Integer> bw;
    private RelativeLayout bx;
    private HealthTextView by;
    private u bz;
    private HealthTextView ca;
    private long cb;
    private RelativeLayout cc;
    private HealthTextView cd;
    private qkh ce;
    private int cf;
    private ab cg;
    private HealthTextView i;
    private HealthTextView j;
    private ab k;
    private RelativeLayout l;
    private HealthTextView m;
    private ab n;
    private RelativeLayout o;
    private HealthTextView p;
    private RelativeLayout q;
    private HealthTextView r;
    private RelativeLayout s;
    private RelativeLayout t;
    private int u;
    private HealthTextView v;
    private HealthTextView w;
    private RelativeLayout x;
    private HealthTextView y;
    private HealthTextView z;
    private boolean f = false;
    private int bi = StepCounterSupportUtil.a(BaseApplication.getContext());

    /* loaded from: classes6.dex */
    public interface ClickViewCallback {
        void startActivity();
    }

    private void ab() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{44306});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        this.bp = new ae(this);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, this.bp);
    }

    /* loaded from: classes6.dex */
    static class ae implements HiDataReadResultListener {
        private WeakReference<HealthDatasActivity> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        ae(HealthDatasActivity healthDatasActivity) {
            this.c = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("UIHLH_HealthDatasActivity", "getStressStatisticsData errorCode = ", Integer.valueOf(i));
            HealthDatasActivity healthDatasActivity = this.c.get();
            if (healthDatasActivity == null) {
                LogUtil.h("UIHLH_HealthDatasActivity", "HealthDatasActivity is null");
                return;
            }
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "requestStressHistoryData data instanceof SparseArray is false");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                return;
            }
            Object obj2 = sparseArray.get(44306);
            if (!(obj2 instanceof List)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "requestStressHistoryData pressureMeasureValue instanceof List is false");
            } else if (((HiHealthData) ((List) obj2).get(0)) != null) {
                healthDatasActivity.f = true;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_listview_item);
        this.ar = Utils.o();
        this.ac = this;
        r();
        ab();
        this.aj = new t(this);
        this.cg = new ab(this, 0);
        this.k = new ab(this, 1);
        this.ap = new ab(this, 4);
        this.n = new ab(this, 7);
        this.bu = new ab(this, 8);
        this.ce = qkh.c();
        this.aw = new HashMap(8);
        s();
        this.ak = dss.c(this.ac).d();
        sqp.c("900300050", new w(this));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(103);
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new v(this));
        ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "onCreate typeList ", arrayList);
    }

    private void r() {
        StepCounterSupportUtil.c(new StepCounterSupportUtil.StepCounterClassCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.1
            @Override // com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.StepCounterClassCallback
            public void getDeviceClass(int i2) {
                ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "initStepCounterClass type", Integer.valueOf(i2));
                HealthDatasActivity.this.bi = i2;
            }
        });
    }

    private void s() {
        u();
        ((HealthScrollView) findViewById(R.id.personal_info_scroll)).setScrollViewVerticalDirectionEvent(true);
        this.ag.setText(UnitUtil.e(0.0d, 1, 2));
        if (UnitUtil.h()) {
            this.af.setText(this.ac.getResources().getString(R$string.IDS_band_data_sport_distance_unit_en));
        } else {
            this.af.setText(this.ac.getResources().getString(R$string.IDS_hw_show_sport_distance_unit));
        }
        this.bm.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.cc.setOnClickListener(this);
        this.bf.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.av.setOnClickListener(this);
        this.bt.setOnClickListener(this);
        this.bx.setOnClickListener(this);
        this.bb.setOnClickListener(this);
        this.bk.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.bo.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.ao.setOnClickListener(this);
        w();
        if (this.ar) {
            this.s.setVisibility(8);
        } else {
            LogUtil.c("UIHLH_HealthDatasActivity", "is not satisfy.");
        }
        k();
        q();
    }

    private void u() {
        this.br = e(R.id.health_data_item_step_layout, R.id.record_data);
        this.bq = e(R.id.health_data_item_step_layout, R.id.record_data_unit);
        e(R.id.health_data_item_step_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_settings_steps));
        dzY_(dzU_(R.id.health_data_item_step_layout, R.id.content_icon), R.mipmap._2131821025_res_0x7f1101e1);
        this.bq.setText(this.ac.getResources().getString(R$string.IDS_hw_show_main_home_page_steps));
        this.ag = e(R.id.health_data_item_dis_layout, R.id.record_data);
        this.af = e(R.id.health_data_item_dis_layout, R.id.record_data_unit);
        e(R.id.health_data_item_dis_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_sport_distance));
        dzU_(R.id.health_data_item_dis_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821018_res_0x7f1101da);
        this.ad = e(R.id.health_data_item_climb_layout, R.id.record_data);
        this.ab = e(R.id.health_data_item_climb_layout, R.id.record_data_unit);
        e(R.id.health_data_item_climb_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_motiontrack_climb_stairs_tip));
        dzY_(dzU_(R.id.health_data_item_climb_layout, R.id.content_icon), R.mipmap._2131821017_res_0x7f1101d9);
        this.w = e(R.id.health_data_item_calorie_layout, R.id.record_data);
        this.z = e(R.id.health_data_item_calorie_layout, R.id.record_data_unit);
        e(R.id.health_data_item_calorie_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_active_caloric));
        dzU_(R.id.health_data_item_calorie_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821020_res_0x7f1101dc);
        this.z.setText(this.ac.getResources().getString(R$string.IDS_hw_health_show_calorie_unit));
        p();
        o();
        ag();
        l();
        d(R.id.health_data_item_calorie_layout);
        d(R.id.health_data_item_physiologicalCycle_layout);
        d(R.id.health_data_item_lactic_layout);
    }

    private void ag() {
        if (nsn.s()) {
            nsn.b(this.br);
            nsn.b(this.bq);
            nsn.b(this.ag);
            nsn.b(this.af);
            nsn.b(this.ad);
            nsn.b(this.ab);
            nsn.b(this.w);
            nsn.b(this.z);
            nsn.b(this.by);
            nsn.b(this.ca);
            nsn.b(this.bh);
            nsn.b(this.ay);
            nsn.b(this.bj);
            nsn.b(this.bg);
            nsn.b(this.i);
            nsn.b(this.j);
            nsn.b(this.aq);
            nsn.b(this.as);
        }
    }

    private void o() {
        this.by = e(R.id.health_data_item_vo2max_layout, R.id.record_data);
        this.ca = e(R.id.health_data_item_vo2max_layout, R.id.record_data_unit);
        e(R.id.health_data_item_vo2max_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_hwh_health_vo2max));
        dzU_(R.id.health_data_item_vo2max_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821022_res_0x7f1101de);
        this.ca.setText(this.ac.getResources().getString(R$string.IDS_hwh_health_vo2max_unit));
        this.bh = e(R.id.health_data_item_running_layout, R.id.record_data);
        this.ay = e(R.id.health_data_item_running_layout, R.id.record_data_unit);
        e(R.id.health_data_item_running_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_data_running_index_title_outside));
        dzU_(R.id.health_data_item_running_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821077_res_0x7f110215);
        this.bj = e(R.id.health_data_item_state_index_layout, R.id.record_data);
        this.bg = e(R.id.health_data_item_state_index_layout, R.id.record_data_unit);
        e(R.id.health_data_item_state_index_layout, R.id.content_title).setText(this.ac.getResources().getString(R$string.IDS_data_state_index_title));
        dzU_(R.id.health_data_item_state_index_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821078_res_0x7f110216);
        this.i = e(R.id.health_data_item_achievement_layout, R.id.record_data);
        this.j = e(R.id.health_data_item_achievement_layout, R.id.record_data_unit);
        HealthTextView e2 = e(R.id.health_data_item_achievement_layout, R.id.content_title);
        e2.setText(this.ac.getResources().getString(R$string.IDS_hwh_health_vo2max_record_forecast));
        e(e2);
        dzU_(R.id.health_data_item_achievement_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821076_res_0x7f110214);
        this.i.setText("");
        this.j.setText("");
        this.aq = e(R.id.health_data_item_lactic_layout, R.id.record_data);
        this.as = e(R.id.health_data_item_lactic_layout, R.id.record_data_unit);
        e(R.id.health_data_item_lactic_layout, R.id.content_title).setText(R$string.IDS_running_lactate_threshold);
        dzU_(R.id.health_data_item_lactic_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821021_res_0x7f1101dd);
        this.as.setText("");
    }

    private void e(HealthTextView healthTextView) {
        View dzW_ = dzW_(R.id.health_data_item_achievement_layout, R.id.title_layout);
        if (dzW_ == null || healthTextView == null) {
            LogUtil.h("UIHLH_HealthDatasActivity", "changTextViewParams view == null || textView == null");
            return;
        }
        if (dzW_.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) dzW_.getLayoutParams();
            if (LanguageUtil.bq(this.ac)) {
                healthTextView.setMaxLines(1);
                layoutParams.weight = 2.0f;
                healthTextView.setEllipsize(TextUtils.TruncateAt.END);
            }
            dzW_.setLayoutParams(layoutParams);
        }
    }

    private void l() {
        this.bm = dzV_(R.id.health_data_item_step_layout, R.id.item_rl);
        this.ae = dzV_(R.id.health_data_item_dis_layout, R.id.item_rl);
        this.aa = dzV_(R.id.health_data_item_climb_layout, R.id.item_rl);
        this.x = dzV_(R.id.health_data_item_calorie_layout, R.id.item_rl);
        this.t = (RelativeLayout) findViewById(R.id.health_data_item_bmp_layout);
        this.cc = (RelativeLayout) findViewById(R.id.health_data_item_weight_layout);
        this.bf = (RelativeLayout) findViewById(R.id.health_data_item_sleep_layout);
        this.q = (RelativeLayout) findViewById(R.id.health_data_item_bloodpresure_layout);
        this.s = (RelativeLayout) findViewById(R.id.health_data_item_bloodsugar_layout);
        this.bt = (RelativeLayout) findViewById(R.id.health_data_item_temperature_layout);
        this.av = (RelativeLayout) findViewById(R.id.health_data_item_physiologicalCycle_layout);
        this.o = (RelativeLayout) findViewById(R.id.health_data_item_blood_oxygen_layout);
        this.bo = (RelativeLayout) findViewById(R.id.health_data_item_stress_layout);
        this.bx = dzV_(R.id.health_data_item_vo2max_layout, R.id.item_rl);
        this.bb = dzV_(R.id.health_data_item_running_layout, R.id.item_rl);
        this.bk = dzV_(R.id.health_data_item_state_index_layout, R.id.item_rl);
        this.l = dzV_(R.id.health_data_item_achievement_layout, R.id.item_rl);
        this.ao = (RelativeLayout) findViewById(R.id.health_data_item_lactic_layout);
        if (efb.i()) {
            return;
        }
        this.ao.setVisibility(8);
    }

    private void p() {
        String string;
        this.y = e(R.id.health_data_item_bmp_layout, R.id.record_data);
        this.v = e(R.id.health_data_item_bmp_layout, R.id.record_data_unit);
        dzX_(e(R.id.health_data_item_bmp_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_main_heart_health_string));
        dzU_(R.id.health_data_item_bmp_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821019_res_0x7f1101db);
        this.cd = e(R.id.health_data_item_weight_layout, R.id.record_data);
        HealthTextView e2 = e(R.id.health_data_item_weight_layout, R.id.content_title);
        if (Utils.o()) {
            string = this.ac.getResources().getString(R$string.IDS_hw_show_set_weight);
        } else {
            string = this.ac.getResources().getString(R$string.IDS_health_weight_manager);
        }
        dzX_(e2, string);
        dzU_(R.id.health_data_item_weight_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821027_res_0x7f1101e3);
        this.bv = e(R.id.health_data_item_stress_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_stress_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14));
        dzU_(R.id.health_data_item_stress_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821023_res_0x7f1101df);
        this.be = e(R.id.health_data_item_sleep_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_sleep_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_hw_show_main_home_page_sleep));
        dzU_(R.id.health_data_item_sleep_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821024_res_0x7f1101e0);
        this.p = e(R.id.health_data_item_bloodpresure_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_bloodpresure_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_hw_show_main_home_page_bloodpressure));
        dzU_(R.id.health_data_item_bloodpresure_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821015_res_0x7f1101d7);
        t();
    }

    private void t() {
        String string;
        this.m = e(R.id.health_data_item_blood_oxygen_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_blood_oxygen_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_hw_health_blood_oxygen));
        dzU_(R.id.health_data_item_blood_oxygen_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821014_res_0x7f1101d6);
        this.r = e(R.id.health_data_item_bloodsugar_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_bloodsugar_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_hw_show_main_home_page_bloodsugar));
        dzU_(R.id.health_data_item_bloodsugar_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821016_res_0x7f1101d8);
        this.bs = e(R.id.health_data_item_temperature_layout, R.id.record_data);
        HealthTextView e2 = e(R.id.health_data_item_temperature_layout, R.id.content_title);
        if (this.ar) {
            string = this.ac.getResources().getString(R$string.IDS_health_skin_temperature);
        } else {
            string = this.ac.getResources().getString(R$string.IDS_settings_health_temperature);
        }
        dzX_(e2, string);
        dzU_(R.id.health_data_item_temperature_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821026_res_0x7f1101e2);
        this.ba = e(R.id.health_data_item_physiologicalCycle_layout, R.id.record_data);
        dzX_(e(R.id.health_data_item_physiologicalCycle_layout, R.id.content_title), this.ac.getResources().getString(R$string.IDS_hw_physiological_cycle));
        dzU_(R.id.health_data_item_physiologicalCycle_layout, R.id.content_icon).setBackgroundResource(R.mipmap._2131821122_res_0x7f110242);
    }

    private void dzX_(final TextView textView, final String str) {
        textView.post(new Runnable() { // from class: qcr
            @Override // java.lang.Runnable
            public final void run() {
                HealthDatasActivity.this.dAa_(textView, str);
            }
        });
    }

    public /* synthetic */ void dAa_(final TextView textView, String str) {
        if (textView.getParent() instanceof View) {
            final ViewGroup viewGroup = (ViewGroup) textView.getParent();
            float width = viewGroup.getWidth() - com.github.mikephil.charting.utils.Utils.convertDpToPixel(16.0f);
            float f2 = width - (width / 3.0f);
            float measureText = textView.getPaint().measureText(str);
            if (measureText < f2) {
                textView.setText(str);
                return;
            }
            LogUtil.a("UIHLH_HealthDatasActivity", Integer.valueOf(textView.hashCode()), ", textViewMaxWidth=", Float.valueOf(f2), ", textViewWidth=", Float.valueOf(measureText));
            textView.setMaxWidth((int) f2);
            textView.setText(str);
            textView.post(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    TextView textView2 = textView;
                    if (textView2 == null || textView2.getLayout() == null) {
                        LogUtil.h("UIHLH_HealthDatasActivity", "setAutoWidthTextView textView is null");
                        return;
                    }
                    int lineCount = textView.getLayout().getLineCount();
                    if (lineCount > 1 && (viewGroup.getParent() instanceof View)) {
                        ((ViewGroup) viewGroup.getParent()).getLayoutParams().height = -2;
                    }
                    float f3 = 0.0f;
                    for (int i2 = 0; i2 < lineCount; i2++) {
                        float lineWidth = textView.getLayout().getLineWidth(i2);
                        if (lineWidth > f3) {
                            f3 = lineWidth;
                        }
                    }
                    LogUtil.a("UIHLH_HealthDatasActivity", Integer.valueOf(textView.hashCode()), ", childMaxWidth=", Float.valueOf(f3));
                    textView.setMaxWidth((int) f3);
                }
            });
        }
    }

    private void w() {
        b(R.id.health_data_item_step_layout, R.id.health_data_item_dis_layout, R.id.health_data_item_climb_layout, R.id.health_data_item_calorie_layout, R.id.health_data_item_bmp_layout, R.id.health_data_item_weight_layout, R.id.health_data_item_bloodpresure_layout, R.id.health_data_item_bloodsugar_layout, R.id.health_data_item_blood_oxygen_layout, R.id.health_data_item_vo2max_layout, R.id.health_data_item_stress_layout, R.id.health_data_item_running_layout, R.id.health_data_item_state_index_layout, R.id.health_data_item_temperature_layout, R.id.health_data_item_sleep_layout, R.id.health_data_item_lactic_layout, R.id.health_data_item_achievement_layout, R.id.health_data_item_physiologicalCycle_layout);
    }

    private void d(int i2) {
        View dzW_ = dzW_(i2, R.id.data_line);
        if (dzW_ == null) {
            return;
        }
        dzW_.setVisibility(8);
    }

    private void b(int... iArr) {
        if (LanguageUtil.bc(getApplication())) {
            for (int i2 : iArr) {
                ImageView dzU_ = dzU_(i2, R.id.record_arrow);
                if (dzU_ != null) {
                    dzU_.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
                }
            }
        }
    }

    private void q() {
        d(R.id.heanth_data_subheader_action, R.id.item_title).setHeadTitleText(this.ac.getResources().getString(R$string.IDS_settings_active_status));
        d(R.id.heanth_data_subheader_action, R.id.item_title).setSplitterVisible(8);
        d(R.id.heanth_data_subheader_condition, R.id.item_title).setHeadTitleText(this.ac.getResources().getString(R$string.IDS_settings_health_status));
        d(R.id.heanth_data_subheader_power, R.id.item_title).setHeadTitleText(this.ac.getResources().getString(R$string.IDS_data_rq_title));
    }

    private HealthTextView e(int i2, int i3) {
        View findViewById = nsy.cMc_(this, i2).findViewById(i3);
        if (findViewById instanceof HealthTextView) {
            return (HealthTextView) findViewById;
        }
        LogUtil.h("UIHLH_HealthDatasActivity", "getTextView view is not instanceof HealthTextView");
        return new HealthTextView(this);
    }

    private View dzW_(int i2, int i3) {
        return nsy.cMc_(this, i2).findViewById(i3);
    }

    private HealthSubHeader d(int i2, int i3) {
        return (HealthSubHeader) nsy.cMc_(this, i2).findViewById(i3);
    }

    private ImageView dzU_(int i2, int i3) {
        return (ImageView) nsy.cMc_(this, i2).findViewById(i3);
    }

    private RelativeLayout dzV_(int i2, int i3) {
        View findViewById = nsy.cMc_(this, i2).findViewById(i3);
        if (findViewById instanceof RelativeLayout) {
            return (RelativeLayout) findViewById;
        }
        LogUtil.h("UIHLH_HealthDatasActivity", "getRelativeLayoutView view is not instanceof RelativeLayout");
        return new RelativeLayout(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("UIHLH_HealthDatasActivity", "onResume begin");
        super.onResume();
        n();
        final x xVar = new x(this);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (HealthDatasActivity.this.ak != null) {
                    HealthDatasActivity.this.ak.b((IExecuteResult) xVar);
                }
            }
        });
        LogUtil.a("UIHLH_HealthDatasActivity", "onResume end");
    }

    /* loaded from: classes6.dex */
    public class l implements ClickViewCallback {
        public l() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for stepLayout begin");
            HealthDatasActivity.this.ah();
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for stepLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class h implements ClickViewCallback {
        public h() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_FITNESSDATA_DISTANCE_2030005.value());
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for disLayout begin");
            HealthDatasActivity.this.ak.a((IFlushResult) null);
            LogUtil.a("UIHLH_HealthDatasActivity", "Create FitnessDistanceDetailActivity and set it to mContext.");
            if (HealthDatasActivity.this.ac != null) {
                Intent intent = new Intent(HealthDatasActivity.this.ac, (Class<?>) FitnessDistanceDetailActivity.class);
                intent.putExtra("today_current_distance_total", HealthDatasActivity.this.ah);
                LogUtil.a("UIHLH_HealthDatasActivity", "mContext.startActivity");
                HealthDatasActivity.this.ac.startActivity(intent);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for disLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class f implements ClickViewCallback {
        public f() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for climbLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_CLIMB_2030046.value());
            HealthDatasActivity.this.ak.a((IFlushResult) null);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putLong("intent_key_query_start_time", jec.n(jec.b()));
            if (HealthDatasActivity.this.ac != null) {
                intent.setClass(HealthDatasActivity.this.ac, FitnessClimbDetailActivity.class);
                intent.putExtra("bundle_key_data", bundle);
                intent.putExtra("today_current_climb_total", HealthDatasActivity.this.al);
                HealthDatasActivity.this.ac.startActivity(intent);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for climbLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class g implements ClickViewCallback {
        public g() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for calorieLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_FITNESSDATA_KALORIES_2030006.value());
            HealthDatasActivity.this.ak.a((IFlushResult) null);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            long n = jec.n(jec.b());
            bundle.putLong("intent_key_query_start_time", n);
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity CALORIE mQueryStartTime = ", Long.valueOf(n));
            if (HealthDatasActivity.this.ac != null) {
                intent.setClass(HealthDatasActivity.this.ac, FitnessCalorieDetailActivity.class);
                intent.putExtra("bundle_key_data", bundle);
                intent.putExtra("today_current_colories_total", HealthDatasActivity.this.u);
                HealthDatasActivity.this.ac.startActivity(intent);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for calorieLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class i implements ClickViewCallback {
        public i() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bmpLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_HEARTRATE_2030047.value());
            if (HealthDatasActivity.this.ac != null) {
                if (!efb.f()) {
                    HeartRateDetailActivity.e(HealthDatasActivity.this.ac, HealthDatasActivity.this.e(4));
                } else {
                    Intent intent = new Intent();
                    intent.setClassName(HealthDatasActivity.this.ac.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
                    IntentParams build = IntentParams.builder().addPageId(ArkUIXConstants.HEART_RATE).addPageType("0").build();
                    LogUtil.a("UIHLH_HealthDatasActivity", "params.toString()", build.toString());
                    intent.putExtra(com.alipay.sdk.m.p.e.n, build.toString());
                    gnm.aPC_(HealthDatasActivity.this.ac, intent);
                }
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bmpLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class q implements ClickViewCallback {
        public q() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for weightLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_WEIGHT_2030035.value());
            if (HealthDatasActivity.this.ac != null) {
                WeightDataManager.INSTANCE.setInitFlag(true);
                Intent intent = new Intent();
                intent.putExtra("from", "7");
                rag.dJA_("7", intent);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for weightLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class o implements ClickViewCallback {
        public o() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for sleepLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_SLEEP_2030048.value());
            if (HealthDatasActivity.c && !HealthDatasActivity.this.ar) {
                KnitSleepDetailActivity.dqM_(HealthDatasActivity.this.ac, null);
            } else {
                KnitSleepDetailActivity.b(HealthDatasActivity.this.ac, HealthDatasActivity.e, HealthDatasActivity.f10041a, HealthDatasActivity.this.e(3), false);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for sleepLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class d implements ClickViewCallback {
        public d() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodPressureLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_BLOODPRESS_2030036.value());
            if (HealthDatasActivity.this.ac != null) {
                HealthDatasActivity.this.ac.startActivity(new Intent(HealthDatasActivity.this.ac, (Class<?>) KnitBloodPressureActivity.class));
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodPressureLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class a implements ClickViewCallback {
        public a() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodSugarLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_BLOODSUGAR_2030037.value());
            if (HealthDatasActivity.this.ac != null) {
                HealthDataDetailActivity.a(HealthDatasActivity.this.ac, "BloodSugarCardConstructor", 8);
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodSugarLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class c implements ClickViewCallback {
        public c() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for PhysiologicalCycle begin");
            LoginInit.getInstance(HealthDatasActivity.this.ac).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.c.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        HealthDatasActivity.this.v();
                    } else {
                        LogUtil.h("UIHLH_HealthDatasActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
                    }
                }
            }, AnalyticsValue.HEALTH_HOME_PHYSIOLOGICAL_CYCLE_CARD_2010102.value());
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for PhysiologicalCycle end");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        y();
    }

    private void y() {
        mpf.e().launchActivity(BaseApplication.getContext(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: qcw
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return HealthDatasActivity.this.dzZ_(context, intent);
            }
        });
    }

    public /* synthetic */ boolean dzZ_(Context context, Intent intent) {
        LogUtil.a("UIHLH_HealthDatasActivity", "InstallCallback");
        x();
        return false;
    }

    private void x() {
        StringBuilder sb = new StringBuilder("#/?from=1");
        if (g() == 1) {
            sb.append("&isActived=true");
        } else {
            sb.append("&isActived=false");
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(sb.toString());
        bzs.e().initH5Pro();
        builder.setImmerse().showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true).enableOnPauseCallback().enableOnResumeCallback().setNeedSoftInputAdapter();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("menstrual", MenstrualModule.class);
        bzs.e().loadH5ProApp(this.ac, "com.huawei.health.h5.cycle-calendar", builder);
    }

    private int g() {
        HiUserPreference userPreference = HiHealthManager.d(this.ac).getUserPreference("com.huawei.health.mc");
        if (userPreference == null || HiCommonUtil.b(userPreference.getValue())) {
            LogUtil.h("UIHLH_HealthDatasActivity", "MENSTRUAL_CONFIG_KEY is null!");
            return 0;
        }
        try {
            return new JSONObject(userPreference.getValue()).getInt("masterSwitch");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("UIHLH_HealthDatasActivity", "getMenstrualActiveState JSONException");
            return 0;
        }
    }

    /* loaded from: classes6.dex */
    public class p implements ClickViewCallback {
        public p() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for mTemperatureLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.TEMPERATURE_2060073.value(), 1);
            Intent intent = new Intent(HealthDatasActivity.this.ac, (Class<?>) KnitTemperatureActivity.class);
            intent.putExtra("key_bundle_health_last_data_time", HealthDatasActivity.this.e(8));
            gnm.aPB_(HealthDatasActivity.this.ac, intent);
            nsn.ai(HealthDatasActivity.this.ac);
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for mTemperatureLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class b implements ClickViewCallback {
        public b() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodOxygenLayout begin");
            HealthDatasActivity.this.c(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_BLOOD_OXYGEN_2030062.value());
            KnitBloodOxygenDetailActivity.a(HealthDatasActivity.this.ac, HealthDatasActivity.this.e(7));
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for bloodOxygenLayout end");
        }
    }

    /* loaded from: classes6.dex */
    public class r implements ClickViewCallback {
        public r() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for vo2MaxLayout begin");
            if (HealthDatasActivity.this.ac == null || nsn.a(500)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "startActivity for vo2MaxLayout mContext is null or fast click");
                return;
            }
            HealthDatasActivity.b(1, 2);
            Intent intent = new Intent();
            if (HealthDatasActivity.h) {
                intent.setClass(HealthDatasActivity.this.ac, Vo2maxActivity.class);
                if (HealthDatasActivity.this.cf != 0) {
                    intent.putExtra("vo2max_value", HealthDatasActivity.this.cf);
                    intent.putExtra("running_level_data", HealthDatasActivity.this.az);
                    intent.putExtra("vo2max_time", HealthDatasActivity.this.cb);
                }
            } else {
                intent.setClass(HealthDatasActivity.this.ac, AthleticAbilityEmptyActivity.class);
                intent.putExtra("athletic_ability_empty_flag", "vo_to_max_no_record");
            }
            HealthDatasActivity.this.ac.startActivity(intent);
        }
    }

    /* loaded from: classes6.dex */
    class n implements ClickViewCallback {
        private n() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for RunningDataLayout begin");
            if (HealthDatasActivity.this.ac == null || nsn.a(500)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "startActivity for RunningDataLayout mContext is null or fast click");
                return;
            }
            HealthDatasActivity.b(3, 2);
            if (!gyg.b(HealthDatasActivity.this.ac) || HealthDatasActivity.this.ai == 2) {
                Intent intent = new Intent(HealthDatasActivity.this.ac, (Class<?>) RqNoNetworkActivity.class);
                intent.putExtra("title_name", HealthDatasActivity.this.getString(R$string.IDS_data_running_index_title_outside));
                HealthDatasActivity.this.ac.startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(HealthDatasActivity.this.ac, (Class<?>) RunningDataActivity.class);
            if (HealthDatasActivity.d) {
                intent2.setClass(HealthDatasActivity.this.ac, RunningDataActivity.class);
                intent2.putExtra("running_level_data", HealthDatasActivity.this.az);
                intent2.putExtra("vo2max_value", HealthDatasActivity.this.cf);
            } else {
                intent2.setClass(HealthDatasActivity.this.ac, AthleticAbilityEmptyActivity.class);
                intent2.putExtra("athletic_ability_empty_flag", "running_no_record");
            }
            HealthDatasActivity.this.ac.startActivity(intent2);
        }
    }

    /* loaded from: classes6.dex */
    class k implements ClickViewCallback {
        private k() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for StateIndexLayout begin");
            if (HealthDatasActivity.this.ac == null || nsn.a(500)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "startActivity for StateIndexLayout mContext is null or fast click");
                return;
            }
            HealthDatasActivity.b(2, 2);
            if (!gyg.b(HealthDatasActivity.this.ac) || HealthDatasActivity.this.ai == 2) {
                Intent intent = new Intent(HealthDatasActivity.this.ac, (Class<?>) RqNoNetworkActivity.class);
                intent.putExtra("title_name", HealthDatasActivity.this.getString(R$string.IDS_data_state_index_title));
                HealthDatasActivity.this.ac.startActivity(intent);
                return;
            }
            Intent intent2 = new Intent();
            if (HealthDatasActivity.g) {
                intent2.setClass(HealthDatasActivity.this.ac, StateIndexActivity.class);
                intent2.putExtra("state_index_level_data", HealthDatasActivity.this.az);
            } else {
                intent2.setClass(HealthDatasActivity.this.ac, AthleticAbilityEmptyActivity.class);
                intent2.putExtra("athletic_ability_empty_flag", "state_no_record");
            }
            HealthDatasActivity.this.ac.startActivity(intent2);
        }
    }

    /* loaded from: classes6.dex */
    class e implements ClickViewCallback {
        private e() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for AchievementLayout begin");
            if (HealthDatasActivity.this.ac == null || nsn.a(500)) {
                LogUtil.h("UIHLH_HealthDatasActivity", "startActivity for AchievementLayout mContext is null or fast click");
                return;
            }
            Intent intent = new Intent(HealthDatasActivity.this.ac, (Class<?>) AchievementPredictionActivity.class);
            intent.putExtra("running_level_data", HealthDatasActivity.this.az);
            intent.putExtra("vo2max_value", HealthDatasActivity.this.cf);
            HealthDatasActivity.this.ac.startActivity(intent);
        }
    }

    /* loaded from: classes6.dex */
    public class j implements ClickViewCallback {
        public j() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for LacticLayout begin");
            if (HealthDatasActivity.this.ac != null && !nsn.a(500)) {
                if (HealthDatasActivity.b) {
                    LacticActivity.c(HealthDatasActivity.this.ac, HealthDatasActivity.this.e(10));
                    LogUtil.a("UIHLH_HealthDatasActivity", "startLacticActivity for LacticLayout end");
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(HealthDatasActivity.this.ac, AthleticAbilityEmptyActivity.class);
                intent.putExtra("athletic_ability_empty_flag", "running_lactic_no_record");
                HealthDatasActivity.this.ac.startActivity(intent);
                LogUtil.a("UIHLH_HealthDatasActivity", "startAthleticAbilityEmptyActivity for LacticLayout end");
                return;
            }
            LogUtil.h("UIHLH_HealthDatasActivity", "startActivity for LacticLayout mContext is null or fast click");
        }
    }

    /* loaded from: classes6.dex */
    public class m implements ClickViewCallback {
        public m() {
        }

        @Override // com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ClickViewCallback
        public void startActivity() {
            if (HealthDatasActivity.this.ac == null) {
                ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "startActivity for stressLayout mContext is null");
                return;
            }
            Bundle bundle = new Bundle();
            if (HealthDatasActivity.this.f) {
                bundle.putLong("lastDataTime", HealthDatasActivity.this.e(6));
            }
            bundle.putBoolean("pressure_is_have_datas", HealthDatasActivity.this.f);
            qmf.dFD_(HealthDatasActivity.this.ac, bundle);
            LogUtil.a("UIHLH_HealthDatasActivity", "startActivity for stressLayout isHaveStressData ", Boolean.valueOf(HealthDatasActivity.this.f));
        }
    }

    private void k() {
        LogUtil.a("UIHLH_HealthDatasActivity", "initClickViewMap begin");
        this.ax = new HashMap<View, ClickViewCallback>(16) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.3
            private static final long serialVersionUID = 6568366952987213870L;

            {
                put(HealthDatasActivity.this.bm, HealthDatasActivity.this.new l());
                put(HealthDatasActivity.this.ae, HealthDatasActivity.this.new h());
                put(HealthDatasActivity.this.aa, HealthDatasActivity.this.new f());
                put(HealthDatasActivity.this.x, HealthDatasActivity.this.new g());
                put(HealthDatasActivity.this.t, HealthDatasActivity.this.new i());
                put(HealthDatasActivity.this.cc, HealthDatasActivity.this.new q());
                put(HealthDatasActivity.this.bf, HealthDatasActivity.this.new o());
                put(HealthDatasActivity.this.q, HealthDatasActivity.this.new d());
                put(HealthDatasActivity.this.s, HealthDatasActivity.this.new a());
                put(HealthDatasActivity.this.av, HealthDatasActivity.this.new c());
                put(HealthDatasActivity.this.bt, HealthDatasActivity.this.new p());
                put(HealthDatasActivity.this.bx, HealthDatasActivity.this.new r());
                put(HealthDatasActivity.this.bb, new n());
                put(HealthDatasActivity.this.bk, new k());
                put(HealthDatasActivity.this.l, new e());
                put(HealthDatasActivity.this.ao, HealthDatasActivity.this.new j());
                put(HealthDatasActivity.this.bo, HealthDatasActivity.this.new m());
                put(HealthDatasActivity.this.o, HealthDatasActivity.this.new b());
            }
        };
        LogUtil.a("UIHLH_HealthDatasActivity", "initClickViewMap end");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("UIHLH_HealthDatasActivity", "onClick begin");
        if (this.ax.get(view) != null) {
            this.ax.get(view).startActivity();
        } else {
            LogUtil.b("UIHLH_HealthDatasActivity", "mMapClickCallback.get(view) is NULL");
        }
        LogUtil.a("UIHLH_HealthDatasActivity", "onClick end");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        LogUtil.a("UIHLH_HealthDatasActivity", "startStepsActivity begin");
        if (this.bl < 0.01d && this.bi == 3) {
            Toast.makeText(this.ac, this.ac.getString(R$string.IDS_hwh_home_hardware_limit_no_step_detail_data_tips), 0).show();
        } else {
            c(AnalyticsValue.HEALTH_HEALTH_FITNESSDATA_STEPS_2030004.value());
            this.ak.a((IFlushResult) null);
            Intent intent = new Intent(this.ac, (Class<?>) FitnessStepDetailActivity.class);
            intent.putExtra("today_current_steps_total", this.bl);
            this.ac.startActivity(intent);
        }
        LogUtil.a("UIHLH_HealthDatasActivity", "startStepsActivity end");
    }

    /* loaded from: classes6.dex */
    public static class t extends BaseHandler<HealthDatasActivity> {
        t(HealthDatasActivity healthDatasActivity) {
            super(healthDatasActivity);
        }

        private String b(String str, String str2) {
            return str + " " + str2;
        }

        private String d(Context context, String str) {
            if (!LanguageUtil.m(context)) {
                return str;
            }
            return str + " ";
        }

        private void dAk_(HealthDatasActivity healthDatasActivity, Message message) {
            healthDatasActivity.z.setVisibility(0);
            healthDatasActivity.bq.setVisibility(0);
            healthDatasActivity.ab.setVisibility(0);
            new DecimalFormat("#0").setRoundingMode(RoundingMode.HALF_UP);
            if (message.obj instanceof Bundle) {
                Bundle bundle = (Bundle) message.obj;
                healthDatasActivity.bl = bundle.getInt("step");
                healthDatasActivity.u = bundle.getInt("carior");
                healthDatasActivity.al = bundle.getInt("floor");
                healthDatasActivity.ah = bundle.getInt("distance");
            }
            healthDatasActivity.a(healthDatasActivity.ah);
            healthDatasActivity.c(healthDatasActivity.al);
            if (healthDatasActivity.bi != 3 || healthDatasActivity.bl >= 0.01d) {
                healthDatasActivity.bq.setVisibility(0);
                healthDatasActivity.br.setText(UnitUtil.e(healthDatasActivity.bl, 1, 0));
            } else {
                healthDatasActivity.br.setText("--");
                healthDatasActivity.bq.setVisibility(8);
            }
            healthDatasActivity.w.setText(UnitUtil.e(healthDatasActivity.u / 1000.0d, 1, 0));
            LogUtil.c("UIHLH_HealthDatasActivity", "get Steps calorie and floor", Integer.valueOf(healthDatasActivity.bl), Integer.valueOf(healthDatasActivity.u), Integer.valueOf(healthDatasActivity.al));
        }

        private void dAj_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof List)) {
                healthDatasActivity.cd.setText("--");
                return;
            }
            List list = (List) message.obj;
            if (koq.b(list)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshWeight no data");
                healthDatasActivity.cd.setText("--");
                return;
            }
            qkg qkgVar = (qkg) ((ArrayList) list.get(0)).get(0);
            int e = qkgVar.e();
            if (UnitUtil.h()) {
                double h = UnitUtil.h(qkgVar.o());
                healthDatasActivity.cd.setText(b(UnitUtil.e(h, 1, qsj.c(h, e)), healthDatasActivity.getString(R$string.IDS_lbs)));
            } else {
                double o = qkgVar.o();
                int c = qsj.c(o, e);
                healthDatasActivity.cd.setText(qsj.e(UnitUtil.c(o, c), c));
            }
        }

        private void dAc_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof List)) {
                healthDatasActivity.p.setText("--");
                return;
            }
            List list = (List) message.obj;
            if (koq.b(list)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshBloodPressure no data");
                healthDatasActivity.p.setText("--");
                return;
            }
            healthDatasActivity.p.setText(b(UnitUtil.e((int) ((HiHealthData) list.get(0)).getDouble("BLOOD_PRESSURE_SYSTOLIC"), 1, 0) + "/" + UnitUtil.e((int) ((HiHealthData) list.get(0)).getDouble("BLOOD_PRESSURE_DIASTOLIC"), 1, 0), healthDatasActivity.getString(R$string.IDS_device_measure_pressure_value_unit)));
        }

        private void dAd_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof HiHealthData)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshBloodSugar no data");
                healthDatasActivity.r.setText("--");
            } else {
                HiHealthData hiHealthData = (HiHealthData) message.obj;
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshBloodSugar: last time ", Long.valueOf(hiHealthData.getEndTime()));
                healthDatasActivity.r.setText(b(UnitUtil.e(hiHealthData.getValue(), 1, 1), healthDatasActivity.getString(R$string.IDS_device_measure_sugar_value_unit)));
            }
        }

        private void dAm_(HealthDatasActivity healthDatasActivity, Message message) {
            if (message.obj instanceof String) {
                healthDatasActivity.ba.setText((String) message.obj);
            } else {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshPhysiologicalCycle no data");
                healthDatasActivity.ba.setText("--");
            }
        }

        private void dAl_(HealthDatasActivity healthDatasActivity, Message message) {
            healthDatasActivity.av.setVisibility(8);
        }

        private void dAf_(HealthDatasActivity healthDatasActivity, Message message) {
            if (message.arg1 == 1) {
                boolean unused = HealthDatasActivity.c = true;
            } else {
                boolean unused2 = HealthDatasActivity.c = false;
            }
            if (message.obj != null) {
                boolean unused3 = HealthDatasActivity.e = true;
                int floor = (int) Math.floor(((Integer) message.obj).intValue() / 60.0f);
                int floor2 = (int) Math.floor(((Integer) message.obj).intValue() % 60.0f);
                String e = UnitUtil.e(floor, 1, 0);
                String e2 = UnitUtil.e(floor2, 1, 0);
                Resources resources = healthDatasActivity.getResources();
                String quantityString = !"0".equals(e) ? resources.getQuantityString(R.plurals._2130903199_res_0x7f03009f, floor, d(healthDatasActivity, e)) : null;
                String quantityString2 = resources.getQuantityString(R.plurals._2130903200_res_0x7f0300a0, floor2, d(healthDatasActivity, e2));
                if (StringUtils.g(quantityString)) {
                    healthDatasActivity.be.setText(quantityString2);
                    return;
                } else {
                    healthDatasActivity.be.setText(resources.getString(com.huawei.ui.commonui.R$string.IDS_two_parts, d(healthDatasActivity, quantityString), quantityString2));
                    return;
                }
            }
            LogUtil.a("UIHLH_HealthDatasActivity", "refreshSleep no data");
            boolean unused4 = HealthDatasActivity.e = false;
            healthDatasActivity.be.setText("--");
        }

        private void dAe_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof List)) {
                healthDatasActivity.v.setText("--");
                return;
            }
            List list = (List) message.obj;
            if (koq.b(list)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshHeartRate no data");
                healthDatasActivity.v.setText("--");
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            double d = hiHealthData.getDouble("heart_rate_last");
            healthDatasActivity.aw.put(4, Long.valueOf(hiHealthData.getStartTime()));
            if (d < 0.01d) {
                healthDatasActivity.v.setText("--");
                return;
            }
            String b = b(UnitUtil.e(d, 1, 0), healthDatasActivity.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            healthDatasActivity.y.setText(healthDatasActivity.getString(R$string.IDS_hw_health_show_healthdata_heart_bmp));
            healthDatasActivity.v.setText(b);
        }

        private void dAn_(HealthDatasActivity healthDatasActivity, Message message) {
            if (message.obj == null) {
                healthDatasActivity.aq.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.as.setVisibility(8);
                boolean unused = HealthDatasActivity.b = false;
            } else {
                healthDatasActivity.aq.setText(message.obj.toString());
                healthDatasActivity.as.setVisibility(0);
                boolean unused2 = HealthDatasActivity.b = true;
            }
        }

        private void dAo_(HealthDatasActivity healthDatasActivity, Message message) {
            if (message.obj == null) {
                healthDatasActivity.bh.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.ay.setVisibility(8);
                healthDatasActivity.bj.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.bg.setVisibility(8);
                boolean unused = HealthDatasActivity.d = false;
                boolean unused2 = HealthDatasActivity.g = false;
                return;
            }
            if (message.obj instanceof GetRunLevelRsp) {
                healthDatasActivity.an = (GetRunLevelRsp) message.obj;
                healthDatasActivity.az = ruf.c(healthDatasActivity.an);
                UserRunLevelData userRunLevelData = healthDatasActivity.an.getUserRunLevelData();
                e(healthDatasActivity, userRunLevelData);
                c(healthDatasActivity, userRunLevelData);
            }
        }

        private void e(HealthDatasActivity healthDatasActivity, UserRunLevelData userRunLevelData) {
            String e;
            if (userRunLevelData == null) {
                healthDatasActivity.bh.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.ay.setVisibility(8);
                boolean unused = HealthDatasActivity.d = false;
                return;
            }
            UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
            if (userInfo == null) {
                userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
            }
            if (userInfo.getGender() == 0) {
                e = ruf.f(userRunLevelData.getLastCurrentRunLevel());
            } else {
                e = ruf.e(userRunLevelData.getLastCurrentRunLevel());
            }
            if ("--".equals(e) || userRunLevelData.getLastCurrentRunLevel() == 0.0f) {
                healthDatasActivity.bh.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.ay.setVisibility(8);
                boolean unused2 = HealthDatasActivity.d = false;
            } else {
                healthDatasActivity.ay.setVisibility(0);
                healthDatasActivity.bh.setText(UnitUtil.e(userRunLevelData.getLastCurrentRunLevel(), 1, 1));
                healthDatasActivity.ay.setText(healthDatasActivity.getString(R$string.IDS_motiontrack_show_chart_unit_string, new Object[]{e}));
                boolean unused3 = HealthDatasActivity.d = true;
            }
        }

        private void c(HealthDatasActivity healthDatasActivity, UserRunLevelData userRunLevelData) {
            String e;
            if (userRunLevelData == null || userRunLevelData.getLastCurrentRunLevel() <= 0.0f) {
                healthDatasActivity.bj.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.bg.setVisibility(8);
                boolean unused = HealthDatasActivity.g = false;
                return;
            }
            float lastCondition = userRunLevelData.getLastCondition();
            if (LanguageUtil.y(BaseApplication.getContext()) && lastCondition < 0.0f) {
                LogUtil.a("UIHLH_HealthDatasActivity", "enter isFarsiLanguage");
                e = UnitUtil.e(-lastCondition, 1, 1) + Constants.LINK;
            } else {
                e = UnitUtil.e(lastCondition, 1, 1);
            }
            healthDatasActivity.bj.setText(e);
            healthDatasActivity.bg.setVisibility(0);
            healthDatasActivity.bg.setText(healthDatasActivity.getString(R$string.IDS_motiontrack_show_chart_unit_string, new Object[]{ruf.g(userRunLevelData.getLastCondition())}));
            boolean unused2 = HealthDatasActivity.g = true;
        }

        private void dAi_(HealthDatasActivity healthDatasActivity, Message message) {
            if (message.obj == null) {
                healthDatasActivity.by.setText(healthDatasActivity.getString(R$string.IDS_rq_no_record));
                healthDatasActivity.ca.setVisibility(8);
                boolean unused = HealthDatasActivity.h = false;
            } else {
                Vo2maxDetail vo2maxDetail = (Vo2maxDetail) message.obj;
                healthDatasActivity.cf = vo2maxDetail.getVo2maxValue();
                healthDatasActivity.cb = vo2maxDetail.getTimestamp();
                healthDatasActivity.by.setText(UnitUtil.e(healthDatasActivity.cf, 1, 0));
                healthDatasActivity.ca.setVisibility(0);
                boolean unused2 = HealthDatasActivity.h = true;
            }
        }

        private void dAg_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof HiStressMetaData)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshStress no data");
                healthDatasActivity.bv.setText("--");
                return;
            }
            HiStressMetaData hiStressMetaData = (HiStressMetaData) message.obj;
            healthDatasActivity.aw.put(6, Long.valueOf(hiStressMetaData.fetchStressStartTime()));
            int fetchStressScore = hiStressMetaData.fetchStressScore();
            boolean z = fetchStressScore > 0 && fetchStressScore < 100;
            String e = sdh.e(fetchStressScore);
            if (!z || StringUtils.g(e)) {
                healthDatasActivity.bv.setText("--");
            } else {
                healthDatasActivity.bv.setText(b(UnitUtil.e(fetchStressScore, 1, 0), e));
            }
        }

        private void dAb_(HealthDatasActivity healthDatasActivity, Message message) {
            if (!(message.obj instanceof List)) {
                healthDatasActivity.m.setText("--");
                return;
            }
            List list = (List) message.obj;
            if (koq.b(list)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshBloodOxygen no data");
                healthDatasActivity.m.setText("--");
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            double intValue = hiHealthData.getIntValue();
            healthDatasActivity.aw.put(7, Long.valueOf(hiHealthData.getStartTime()));
            if (intValue < 0.01d) {
                healthDatasActivity.m.setText("--");
            } else {
                healthDatasActivity.m.setText(UnitUtil.e(intValue, 2, 0));
            }
        }

        private void dAh_(HealthDatasActivity healthDatasActivity, Message message) {
            String string;
            String b;
            if (!(message.obj instanceof List)) {
                healthDatasActivity.bs.setText("--");
                return;
            }
            List list = (List) message.obj;
            LogUtil.a("UIHLH_HealthDatasActivity", "handleMessageMsgRefreshTemperature temperatureList ", list.toString());
            if (koq.b(list)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "refreshTemperatureView no data");
                healthDatasActivity.bs.setText("--");
                return;
            }
            boolean d = UnitUtil.d();
            if (d) {
                string = healthDatasActivity.getString(R$string.IDS_settings_health_temperature_unit, new Object[]{""});
            } else {
                string = healthDatasActivity.getString(R$string.IDS_temp_unit_fahrenheit, new Object[]{""});
            }
            Collections.sort(list, new Comparator() { // from class: qcz
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((qkg) obj2).h(), ((qkg) obj).h());
                    return compare;
                }
            });
            qkg qkgVar = (qkg) list.get(0);
            float o = (float) qkgVar.o();
            if (qkgVar.g() == qpr.c) {
                b = b(qpr.a(d, o), string);
            } else {
                if (!d) {
                    o = qpr.c(o);
                }
                b = b(UnitUtil.e(o, 1, 1), string);
            }
            healthDatasActivity.bs.setText(b);
            healthDatasActivity.aw.put(8, Long.valueOf(qkgVar.h()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAp_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthDatasActivity healthDatasActivity, Message message) {
            int i = message.what;
            if (i == 104) {
                dAk_(healthDatasActivity, message);
            }
            switch (i) {
                case 0:
                    dAj_(healthDatasActivity, message);
                    break;
                case 1:
                    dAc_(healthDatasActivity, message);
                    break;
                case 2:
                    dAd_(healthDatasActivity, message);
                    break;
                case 3:
                    dAf_(healthDatasActivity, message);
                    break;
                case 4:
                    dAe_(healthDatasActivity, message);
                    break;
                case 5:
                    dAi_(healthDatasActivity, message);
                    break;
                case 6:
                    dAg_(healthDatasActivity, message);
                    break;
                case 7:
                    dAb_(healthDatasActivity, message);
                    break;
                case 8:
                    dAh_(healthDatasActivity, message);
                    break;
                case 9:
                    dAo_(healthDatasActivity, message);
                    break;
                case 10:
                    dAn_(healthDatasActivity, message);
                    break;
                case 11:
                    dAm_(healthDatasActivity, message);
                    break;
                case 12:
                    dAl_(healthDatasActivity, message);
                    break;
                default:
                    LogUtil.a("UIHLH_HealthDatasActivity", "handleMessageWhenReferenceNotNull default");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        double d2 = i2 / 1000.0d;
        if (UnitUtil.h()) {
            d2 = UnitUtil.e(d2, 3);
            this.af.setText(this.ac.getResources().getString(R$string.IDS_band_data_sport_distance_unit_en));
        } else {
            this.af.setText(this.ac.getResources().getString(R$string.IDS_hw_show_sport_distance_unit));
        }
        this.ag.setText(UnitUtil.e(d2, 1, d2 < 1000.0d ? 2 : (d2 < 1000.0d || d2 >= 10000.0d) ? 0 : 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        double d2;
        LogUtil.c("UIHLH_HealthDatasActivity", "setFloorText floors = ", Integer.valueOf(i2));
        if (i2 < 0) {
            i2 = 0;
        }
        this.ab.setVisibility(0);
        if (UnitUtil.h()) {
            d2 = UnitUtil.j(i2 / 10.0d)[0];
            this.ab.setText(this.ac.getResources().getString(R$string.IDS_ft));
        } else {
            d2 = i2 / 10.0d;
            this.ab.setText(this.ac.getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit));
        }
        if (i2 > 0) {
            this.ad.setText(e(getApplication(), d2, 1, 1));
        } else if (jct.b() && CommonUtil.bh()) {
            this.ad.setText(e(getApplication(), d2, 1, 1));
        } else {
            this.ad.setText("--");
            this.ab.setVisibility(8);
        }
    }

    private void ac() {
        if (this.au == null) {
            this.au = new LastTimeHealthDataReader<>(this, new z(this, 2));
        }
        this.au.b(LastTimeHealthDataReader.CardData.BLOOD_SUGAR);
    }

    private void n() {
        long currentTimeMillis = System.currentTimeMillis();
        this.ce.c(getApplication(), new long[]{0, currentTimeMillis}, 20, this.cg);
        this.ce.d(this.k);
        ac();
        ad();
        aa();
        this.ce.a(getApplication(), 0L, currentTimeMillis, this.ap);
        u uVar = new u(this);
        this.bz = uVar;
        this.ce.c(uVar);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qcx
            @Override // java.lang.Runnable
            public final void run() {
                HealthDatasActivity.this.f();
            }
        });
        qks.b(new y(this));
        this.ce.d(getApplication(), 0L, currentTimeMillis, this.n);
        af();
        z();
    }

    public /* synthetic */ void f() {
        int a2 = ggl.a(new Date(System.currentTimeMillis()));
        new hqa().a(this, a2, a2, 0, new ac(this));
    }

    private void af() {
        this.ce.b(this.bu);
    }

    private void z() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        this.am = userInfo.getGender();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{47401});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiDataReadProOption e2 = HiDataReadProOption.builder().e(hiDataReadOption).d(HiJsonUtil.e(new HiDataFilter(new HiDataFilter.DataFilterExpression(47401, "=", 101.0d)))).e();
        LogUtil.a("UIHLH_HealthDatasActivity", "requestLastData : ", e2.toString());
        HiHealthNativeApi.a(this.ac).readHiHealthDataPro(e2, new s(this, 1));
    }

    /* loaded from: classes6.dex */
    static class y implements HiAggregateListener {
        private WeakReference<HealthDatasActivity> d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        public y(HealthDatasActivity healthDatasActivity) {
            this.d = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "lacticDataCallback onResult errorCode: ", Integer.valueOf(i), " anchor: ", Integer.valueOf(i2));
            HealthDatasActivity healthDatasActivity = this.d.get();
            if (healthDatasActivity == null) {
                return;
            }
            Message obtainMessage = healthDatasActivity.aj.obtainMessage(10);
            if (i != 0 || HiCommonUtil.d(list)) {
                LogUtil.b("UIHLH_HealthDatasActivity", "get lactic data error or data is empty");
                obtainMessage.obj = list;
                healthDatasActivity.aj.sendMessage(obtainMessage);
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            StringBuilder sb = new StringBuilder();
            healthDatasActivity.aw.put(10, Long.valueOf(hiHealthData.getStartTime()));
            int i3 = (int) hiHealthData.getFloat("lthrHr");
            float f = hiHealthData.getFloat("lthrPace");
            if (i3 == 0) {
                sb.append("--");
            } else {
                sb.append(UnitUtil.e(i3, 1, 0));
            }
            sb.append("/");
            if (f == 0.0f) {
                sb.append("--");
            } else {
                sb.append(nqo.d(f));
            }
            obtainMessage.obj = sb.toString();
            healthDatasActivity.aj.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes6.dex */
    static class ac implements IBaseResponseCallback {
        private WeakReference<HealthDatasActivity> c;

        ac(HealthDatasActivity healthDatasActivity) {
            this.c = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            HealthDatasActivity healthDatasActivity = this.c.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                return;
            }
            if (i == 200) {
                Message obtainMessage = healthDatasActivity.aj.obtainMessage(9);
                healthDatasActivity.ai = i;
                obtainMessage.obj = obj;
                healthDatasActivity.aj.sendMessage(obtainMessage);
                jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity.ac.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Object obj2 = obj;
                        if (obj2 instanceof GetRunLevelRsp) {
                            ac.this.e((GetRunLevelRsp) obj2);
                        }
                    }
                });
                return;
            }
            healthDatasActivity.ai = i;
            healthDatasActivity.aj.sendEmptyMessage(9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(GetRunLevelRsp getRunLevelRsp) {
            LogUtil.a("UIHLH_HealthDatasActivity", "enter sendRqDataToDevice");
            if (!RqDataPushReceiver.checkDeviceRqDataCapability()) {
                ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "sendRqDataToDevice failed with device not support");
                return;
            }
            ffp ffpVar = new ffp();
            Map<Integer, RunLevelDetail> data = getRunLevelRsp.getData();
            UserRunLevelData userRunLevelData = getRunLevelRsp.getUserRunLevelData();
            if (data == null || userRunLevelData == null) {
                LogUtil.b("UIHLH_HealthDatasActivity", "runLevelDetails or userRunLevelData is null");
                return;
            }
            ffpVar.b(data);
            ffpVar.c(userRunLevelData);
            ffpVar.c((int) (System.currentTimeMillis() / 1000));
            new hqa().c(BaseApplication.getContext(), ggl.a(new Date(System.currentTimeMillis())), ffpVar);
        }
    }

    /* loaded from: classes6.dex */
    static class u implements IBaseResponseCallback {
        private WeakReference<HealthDatasActivity> d;

        u(HealthDatasActivity healthDatasActivity) {
            this.d = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "myMaxReadComplete onResponse errorCode : ", Integer.valueOf(i));
            HealthDatasActivity healthDatasActivity = this.d.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                return;
            }
            if (i == 0) {
                Message obtainMessage = healthDatasActivity.aj.obtainMessage(5);
                obtainMessage.obj = obj;
                healthDatasActivity.aj.sendMessage(obtainMessage);
                return;
            }
            healthDatasActivity.aj.sendEmptyMessage(5);
        }
    }

    private void aa() {
        HiDataReadOption m2 = m();
        this.bn = new ad(this);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(m2, this.bn);
    }

    /* loaded from: classes6.dex */
    static class ad implements HiDataReadResultListener {
        private WeakReference<HealthDatasActivity> c;

        ad(HealthDatasActivity healthDatasActivity) {
            this.c = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "stressReadResult errorCode: ", Integer.valueOf(i), " anchor: ", Integer.valueOf(i2));
            HealthDatasActivity healthDatasActivity = this.c.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                LogUtil.h("UIHLH_HealthDatasActivity", "activity is null");
                return;
            }
            if (i == 0) {
                if (!(obj instanceof SparseArray)) {
                    healthDatasActivity.aj.obtainMessage(6, null).sendToTarget();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("UIHLH_HealthDatasActivity", "map.size() <= 0");
                    healthDatasActivity.aj.obtainMessage(6, null).sendToTarget();
                    return;
                }
                Object obj2 = sparseArray.get(2034);
                if (!(obj2 instanceof List)) {
                    healthDatasActivity.aj.obtainMessage(6, null).sendToTarget();
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) ((List) obj2).get(0);
                if (hiHealthData == null) {
                    LogUtil.h("UIHLH_HealthDatasActivity", "data is empty");
                    healthDatasActivity.aj.obtainMessage(6, null).sendToTarget();
                    return;
                }
                HiStressMetaData hiStressMetaData = (HiStressMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiStressMetaData.class);
                Message obtainMessage = healthDatasActivity.aj.obtainMessage();
                obtainMessage.what = 6;
                obtainMessage.obj = hiStressMetaData;
                healthDatasActivity.aj.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("UIHLH_HealthDatasActivity", "read failed errorCode is ", Integer.valueOf(i));
            healthDatasActivity.aj.obtainMessage(6, null).sendToTarget();
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("UIHLH_HealthDatasActivity", "onResultIntent", "read failed errorCode is ", Integer.valueOf(i2));
        }
    }

    private HiDataReadOption m() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(HiDateUtil.f(System.currentTimeMillis()));
        hiDataReadOption.setType(new int[]{2034});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        return hiDataReadOption;
    }

    private static String e(Context context, double d2, int i2, int i3) {
        if (LanguageUtil.m(context)) {
            return String.valueOf(d2);
        }
        return UnitUtil.e(d2, i2, i3);
    }

    private void ad() {
        LogUtil.a("UIHLH_HealthDatasActivity", "Enter requestCoreSleepSummary!");
        HiAggregateOption i2 = i();
        this.bd = new aa(this);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(i2, this.bd);
    }

    /* loaded from: classes6.dex */
    static class aa implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HealthDatasActivity> f10043a;

        aa(HealthDatasActivity healthDatasActivity) {
            this.f10043a = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "requestCoreSleepSummary aggregateHiHealthData onResult 1");
            HealthDatasActivity healthDatasActivity = this.f10043a.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                LogUtil.h("UIHLH_HealthDatasActivity", "activity is null");
                return;
            }
            boolean z = true;
            if (koq.b(list)) {
                Message obtainMessage = healthDatasActivity.aj.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.arg1 = 1;
                healthDatasActivity.aj.sendMessage(obtainMessage);
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            healthDatasActivity.aw.put(3, Long.valueOf(hiHealthData.getEndTime()));
            boolean z2 = hiHealthData.getInt("core_sleep_shallow_key") > 0 || hiHealthData.getInt("core_sleep_deep_key") > 0 || hiHealthData.getInt("core_sleep_wake_dream_key") > 0;
            int i3 = hiHealthData.getInt("sleep_deep_key");
            int i4 = hiHealthData.getInt("sleep_shallow_key");
            if (i3 <= 0 && i4 <= 0) {
                z = false;
            }
            int i5 = hiHealthData.getInt("sleep_core_sleep_noon_duration_key");
            int i6 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
            int i7 = hiHealthData.getInt("data_session_manual_sleep_bed_time_key");
            if (z2) {
                i5 += i6;
            } else if (z) {
                i5 = (int) ((i3 / 60.0f) + (i4 / 60.0f));
            } else if (i6 > 0) {
                i5 = i6;
            } else if (i7 > 0) {
                i5 = i7;
            }
            Message obtainMessage2 = healthDatasActivity.aj.obtainMessage();
            obtainMessage2.what = 3;
            obtainMessage2.obj = Integer.valueOf(i5);
            healthDatasActivity.aj.sendMessage(obtainMessage2);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.c("UIHLH_HealthDatasActivity", "requestCoreSleepSummary onResult datas = ", list);
        }
    }

    private HiAggregateOption i() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(HiDateUtil.f(System.currentTimeMillis()));
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setType(new int[]{44102, 44103, 44101, 44001, 44002, 44108, 44105, 44109});
        hiAggregateOption.setConstantsKey(new String[]{"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "sleep_deep_key", "sleep_shallow_key", "sleep_core_sleep_noon_duration_key", "core_sleep_total_sleep_time_key", "data_session_manual_sleep_bed_time_key"});
        return hiAggregateOption;
    }

    /* loaded from: classes6.dex */
    static class ab implements CommonUiBaseResponse {
        private WeakReference<HealthDatasActivity> c;
        private int d;

        ab(HealthDatasActivity healthDatasActivity, int i) {
            this.c = new WeakReference<>(healthDatasActivity);
            this.d = i;
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "ReadComplete onResponse type: ", Integer.valueOf(this.d));
            HealthDatasActivity healthDatasActivity = this.c.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                return;
            }
            Message obtainMessage = healthDatasActivity.aj.obtainMessage();
            obtainMessage.what = this.d;
            obtainMessage.obj = obj;
            healthDatasActivity.aj.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes6.dex */
    static class z extends ab implements IBaseResponseCallback {
        z(HealthDatasActivity healthDatasActivity, int i) {
            super(healthDatasActivity, i);
        }
    }

    /* loaded from: classes6.dex */
    static class x implements IExecuteResult {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HealthDatasActivity> f10048a;

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
        }

        x(HealthDatasActivity healthDatasActivity) {
            this.f10048a = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("UIHLH_HealthDatasActivity", "get today steps success");
            HealthDatasActivity healthDatasActivity = this.f10048a.get();
            if (healthDatasActivity == null || healthDatasActivity.aj == null) {
                LogUtil.h("UIHLH_HealthDatasActivity", "activity is null");
            } else if (obj instanceof Bundle) {
                Message obtainMessage = healthDatasActivity.aj.obtainMessage(104);
                obtainMessage.obj = obj;
                healthDatasActivity.aj.sendMessage(obtainMessage);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long e(int i2) {
        if (this.aw.containsKey(Integer.valueOf(i2))) {
            return this.aw.get(Integer.valueOf(i2)).longValue();
        }
        return 0L;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.aj;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (koq.c(this.bw)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.bw, new HiUnSubscribeListener() { // from class: qcs
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z2) {
                    ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(getApplicationContext(), str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i2));
        ixx.d().d(getApplicationContext(), str, hashMap, 0);
    }

    public static void b(int i2, int i3) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i3));
        hashMap.put("type", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SPORT_RECORD_ATHLETIC_ABILITY_CARD_2040192.value(), hashMap, 0);
    }

    private void dzY_(ImageView imageView, int i2) {
        if (LanguageUtil.bc(this.ac)) {
            imageView.setBackground(nrz.cKn_(this.ac, i2));
        } else {
            imageView.setBackgroundResource(i2);
        }
    }

    /* loaded from: classes6.dex */
    static class s implements HiDataReadResultListener {
        private WeakReference<HealthDatasActivity> b;
        private int c;

        s(HealthDatasActivity healthDatasActivity, int i) {
            this.b = new WeakReference<>(healthDatasActivity);
            this.c = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("UIHLH_HealthDatasActivity", "onResult, errorCode: ", Integer.valueOf(i), ", data: ", obj);
            ReleaseLogUtil.b("UIHLH_HealthDatasActivity", "onResult, mType: ", Integer.valueOf(this.c), " errorCode: ", Integer.valueOf(i));
            HealthDatasActivity healthDatasActivity = this.b.get();
            if (healthDatasActivity == null) {
                LogUtil.h("UIHLH_HealthDatasActivity", "reader is null!");
                return;
            }
            if (obj == null) {
                if (healthDatasActivity.am != 1) {
                    healthDatasActivity.aj.obtainMessage(12, null).sendToTarget();
                    return;
                } else {
                    LogUtil.h("UIHLH_HealthDatasActivity", "data is null!");
                    healthDatasActivity.aj.obtainMessage(11, null).sendToTarget();
                    return;
                }
            }
            if (!(obj instanceof SparseArray)) {
                if (healthDatasActivity.am != 1) {
                    healthDatasActivity.aj.obtainMessage(12, null).sendToTarget();
                    return;
                } else {
                    healthDatasActivity.aj.obtainMessage(11, null).sendToTarget();
                    return;
                }
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() == 0) {
                if (healthDatasActivity.am != 1) {
                    healthDatasActivity.aj.obtainMessage(12, null).sendToTarget();
                    return;
                } else {
                    healthDatasActivity.aj.obtainMessage(11, null).sendToTarget();
                    LogUtil.h("UIHLH_HealthDatasActivity", "map.size() == 0");
                    return;
                }
            }
            int i3 = this.c;
            if (i3 == 1) {
                d((HiHealthData) ((List) sparseArray.get(47401)).get(0), healthDatasActivity);
                return;
            }
            if (i3 == 0) {
                List list = (List) sparseArray.get(47401);
                if (HiCommonUtil.d(list)) {
                    return;
                }
                healthDatasActivity.at = list;
                healthDatasActivity.d((List<HiHealthData>) list);
                return;
            }
            LogUtil.h("UIHLH_HealthDatasActivity", "mType is error");
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("UIHLH_HealthDatasActivity", "onResultIntent : read failed errorCode is", Integer.valueOf(i2));
        }

        private void d(HiHealthData hiHealthData, HealthDatasActivity healthDatasActivity) {
            if (hiHealthData == null || !String.valueOf(hiHealthData.getDouble("point_value")).equals("101.0")) {
                LogUtil.h("UIHLH_HealthDatasActivity", "handleLastData : list is error");
                return;
            }
            long j = hiHealthData.getLong("start_time");
            healthDatasActivity.bc = j;
            healthDatasActivity.d(j + 31104000000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j2) {
        LogUtil.a("UIHLH_HealthDatasActivity", "requestData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{47401});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(720);
        LogUtil.a("UIHLH_HealthDatasActivity", "readHiHealthData : ", hiDataReadOption.toString());
        HiHealthNativeApi.a(this.ac).readHiHealthData(hiDataReadOption, new s(this, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list) {
        boolean z2;
        String str;
        boolean z3;
        int d2;
        LogUtil.a("UIHLH_HealthDatasActivity", "handleData data size ", Integer.valueOf(list.size()));
        e(list);
        long d3 = jec.d(System.currentTimeMillis());
        int i2 = 0;
        while (true) {
            z2 = true;
            if (i2 >= list.size()) {
                str = "101.0";
                i2 = -1;
                z3 = true;
                break;
            } else if (d3 == list.get(i2).getStartTime()) {
                str = String.valueOf(list.get(i2).getDouble("point_value"));
                z3 = false;
                break;
            } else {
                list.get(i2).getStartTime();
                i2++;
            }
        }
        if (z3 || (d2 = scz.d("UIHLH_HealthDatasActivity", list, i2, this.bc)) < 0) {
            z2 = z3;
        } else {
            LogUtil.a("UIHLH_HealthDatasActivity", "handleData lastIndexTemp ", Integer.valueOf(d2), " isLong ", Boolean.valueOf(z3));
        }
        Date date = new Date();
        if (z2) {
            this.aj.obtainMessage(11, null).sendToTarget();
            return;
        }
        date.setTime(System.currentTimeMillis());
        int a2 = (i2 - 21) + a(d3);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                LogUtil.a("UIHLH_HealthDatasActivity", "handleData, time: ", new Date(hiHealthData.getStartTime()), ", type: ", String.valueOf(hiHealthData.getDouble("point_value")));
            }
        }
        if (a2 < 0) {
            int i3 = 0;
            while (i3 < Math.abs(a2)) {
                HiHealthData hiHealthData2 = new HiHealthData();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(list.get(0).getStartTime());
                i3++;
                calendar.add(10, i3 * 24);
                hiHealthData2.setStartTime(calendar.getTimeInMillis());
                list.add(0, hiHealthData2);
            }
        }
        if (a2 < 0) {
            i2 -= a2;
        }
        Pair<Integer, CharSequence> dWi_ = scz.dWi_("UIHLH_HealthDatasActivity", str, list, i2);
        Message obtainMessage = this.aj.obtainMessage(11);
        obtainMessage.obj = String.valueOf(dWi_.second);
        this.aj.sendMessage(obtainMessage);
    }

    private int a(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        return calendar.get(7);
    }

    private void e(List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                LogUtil.a("UIHLH_HealthDatasActivity", "sortHealthData, time: ", new Date(hiHealthData.getStartTime()), ", type: ", String.valueOf(hiHealthData.getDouble("point_value")));
            }
        }
        if (list == null) {
            return;
        }
        Collections.reverse(list);
        for (int size = list.size() - 1; size > 0 && Math.abs(list.get(size).getDouble("point_value")) < 1.0E-6d; size--) {
            list.remove(size);
        }
        Collections.reverse(list);
    }

    /* loaded from: classes6.dex */
    public static class v implements HiSubscribeListener {
        private final WeakReference<HealthDatasActivity> e;

        v(HealthDatasActivity healthDatasActivity) {
            this.e = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            HealthDatasActivity healthDatasActivity = this.e.get();
            if (healthDatasActivity != null && !healthDatasActivity.isDestroyed() && !healthDatasActivity.isFinishing()) {
                healthDatasActivity.bw = list;
            } else {
                ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "InnerSubscribeListener onResult activity ", healthDatasActivity);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, final String str, HiHealthData hiHealthData, long j) {
            if (i == 103 && "900300050".equals(str)) {
                LogUtil.a("UIHLH_HealthDatasActivity", "InnerSubscribeListener onChange changeKey ", str);
                ThreadPoolManager.d().execute(new Runnable() { // from class: qcy
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthDatasActivity.v.this.a(str);
                    }
                });
            }
        }

        public /* synthetic */ void a(String str) {
            sqp.c(str, new w(this.e.get()));
        }
    }

    /* loaded from: classes6.dex */
    static class w implements HiDataReadResultListener {
        private final WeakReference<HealthDatasActivity> d;

        w(HealthDatasActivity healthDatasActivity) {
            this.d = new WeakReference<>(healthDatasActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("UIHLH_HealthDatasActivity", "InnerSampleConfigListener onResult errorCode ", Integer.valueOf(i), " object ", obj);
            if (i == 0 && scz.d("UIHLH_HealthDatasActivity", obj) != null) {
                HealthDatasActivity healthDatasActivity = this.d.get();
                if (healthDatasActivity != null && !healthDatasActivity.isDestroyed() && !healthDatasActivity.isFinishing()) {
                    if (!koq.b(healthDatasActivity.at)) {
                        healthDatasActivity.d((List<HiHealthData>) healthDatasActivity.at);
                        return;
                    } else {
                        ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "InnerSampleConfigListener onResult activity.mPhysiologicalCycleDataList ", healthDatasActivity.at);
                        return;
                    }
                }
                ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "InnerSampleConfigListener onResult activity ", healthDatasActivity);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            ReleaseLogUtil.a("UIHLH_HealthDatasActivity", "InnerSampleConfigListener onResultIntent errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3), " intentType ", Integer.valueOf(i), " object ", obj);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
