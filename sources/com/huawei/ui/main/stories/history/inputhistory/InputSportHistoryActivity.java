package com.huawei.ui.main.stories.history.inputhistory;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.ChooseSportTypeActivity;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.up.model.UserInfomation;
import defpackage.gso;
import defpackage.hkc;
import defpackage.hln;
import defpackage.hpn;
import defpackage.ixx;
import defpackage.nrz;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.rda;
import defpackage.rds;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CalculateCaloriesUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class InputSportHistoryActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f10314a;
    private HealthTextView aa;
    private ImageView ab;
    private HealthTextView ac;
    private ImageView ad;
    private HealthTextView ae;
    private RelativeLayout af;
    private RelativeLayout ag;
    private ImageView ah;
    private ImageView ai;
    private HealthTextView aj;
    private ImageView ak;
    private HealthTextView am;
    private RelativeLayout an;
    private Toast ao;
    private int as;
    private HealthButton b;
    private RelativeLayout c;
    private HealthButton d;
    private LinearLayout e;
    private int f;
    private Context g;
    private Dialog h;
    private CustomTitleBar j;
    private int k;
    private int t;
    private RelativeLayout u;
    private ImageView v;
    private int w;
    private RelativeLayout z;
    private Runnable x = null;
    private int l = 0;
    private int n = 0;
    private int o = 0;
    private int ar = 264;
    private boolean i = false;
    private boolean s = false;
    private float q = 0.0f;
    private String al = "BASE_SPORT";
    private float aq = 0.0f;
    private boolean p = true;
    private boolean r = false;
    private MotionPathSimplify y = new MotionPathSimplify();
    private Handler m = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Track_InputSportHistoryActivity", "mHandler msg is null");
            } else {
                super.handleMessage(message);
                InputSportHistoryActivity.this.dLW_(message);
            }
        }
    };

    private boolean e(int i) {
        return i == 262 || i == 266;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = this;
        l();
        i();
        j();
    }

    private void l() {
        if (nsn.s()) {
            setContentView(R.layout.layout_input_sport_history_large);
        } else {
            setContentView(R.layout.layout_input_sport_history);
        }
        m();
        if (LanguageUtil.bc(this.g)) {
            this.ak.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ad.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.v.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ah.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ai.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        y();
        if (LanguageUtil.f(this.g) || LanguageUtil.v(this.g) || LanguageUtil.aw(this.g)) {
            ((HealthTextView) findViewById(R.id.hw_add_sport_data_sport_type_name)).setTextSize(0, this.g.getResources().getDimension(R.dimen._2131363717_res_0x7f0a0785));
        }
        if (Utils.o()) {
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_add_sport_data_tips);
            if (Utils.i()) {
                healthTextView.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_input_history_data_oversea_tips));
            } else {
                healthTextView.setVisibility(4);
            }
        }
        initViewTahiti();
    }

    private void m() {
        this.c = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_data_img_top);
        this.f10314a = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_data_img_bottom);
        this.e = (LinearLayout) findViewById(R.id.hw_add_sport_info);
        this.ab = (ImageView) findViewById(R.id.hw_add_sport_data_sport_data_img);
        this.an = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_type);
        this.aj = (HealthTextView) findViewById(R.id.hw_add_sport_data_sport_type_value);
        this.ak = (ImageView) findViewById(R.id.hw_add_sport_data_sport_type_right_button);
        this.z = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_duration);
        this.aa = (HealthTextView) findViewById(R.id.hw_add_sport_data_sport_duration_value);
        this.ad = (ImageView) findViewById(R.id.hw_add_sport_data_sport_duration_right_button);
        this.u = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_distance);
        this.ac = (HealthTextView) findViewById(R.id.hw_add_sport_data_sport_distance_value);
        this.v = (ImageView) findViewById(R.id.hw_add_sport_data_sport_distance_right_button);
        this.af = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_time);
        this.am = (HealthTextView) findViewById(R.id.hw_add_sport_data_sport_time_value);
        this.ah = (ImageView) findViewById(R.id.hw_add_sport_data_sport_time_right_button);
        this.ag = (RelativeLayout) findViewById(R.id.hw_add_sport_data_sport_start_time);
        this.ae = (HealthTextView) findViewById(R.id.hw_add_sport_data_sport_start_time_value);
        this.ai = (ImageView) findViewById(R.id.hw_add_sport_data_sport_start_time_right_button);
        this.j = (CustomTitleBar) findViewById(R.id.input_sport_history_title);
        this.b = (HealthButton) findViewById(R.id.cancel_add_button);
        this.d = (HealthButton) findViewById(R.id.acknowledgement_add_button);
        BaseActivity.cancelLayoutById(this.e);
        BaseActivity.setViewSafeRegion(false, this.e);
    }

    private void g() {
        if (this.i) {
            this.u.setVisibility(0);
            if (n()) {
                this.ac.setText("");
                this.y.saveTotalDistance(0);
                this.s = !this.s;
                return;
            }
            return;
        }
        this.u.setVisibility(8);
        this.y.saveTotalDistance(0);
    }

    private boolean n() {
        return e(this.ar) != this.s;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout.LayoutParams layoutParams2;
        LinearLayout.LayoutParams layoutParams3;
        boolean ag = nsn.ag(getApplicationContext());
        super.initViewTahiti();
        if (ag) {
            layoutParams = new LinearLayout.LayoutParams(-2, 0, 0.5f);
            layoutParams2 = new LinearLayout.LayoutParams(-2, 0, 0.0f);
            layoutParams3 = new LinearLayout.LayoutParams(-2, 0, 0.0f);
            layoutParams.gravity = 1;
        } else {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2 = new LinearLayout.LayoutParams(-2, nsn.c(this, 34.0f));
            layoutParams3 = new LinearLayout.LayoutParams(-2, nsn.c(this, 24.0f));
            layoutParams.gravity = 1;
        }
        h();
        this.e.setLayoutParams(layoutParams);
        this.c.setLayoutParams(layoutParams2);
        this.f10314a.setLayoutParams(layoutParams3);
    }

    private void h() {
        nsn.cLG_(this, true, true, this.an, this.af, this.u, this.z, this.ag);
        nsn.cLF_(this, true, true, findViewById(R.id.hw_add_sport_data_tips));
    }

    private void y() {
        this.ab.setOnClickListener(this);
        this.an.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.af.setOnClickListener(this);
        this.ag.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.j.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputSportHistoryActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.r) {
            LogUtil.a("Track_InputSportHistoryActivity", "close window(), onClick()  mIsClickCheck is true");
        } else {
            setResult(0);
            finish();
        }
    }

    private void i() {
        this.y.saveSportDataSource(2);
        f();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        this.as = calendar.get(1);
        this.w = calendar.get(2) + 1;
        this.f = calendar.get(5);
        this.k = calendar.get(11);
        this.t = calendar.get(12);
        this.am.setText(b(calendar.getTime()));
        this.ae.setText(nsj.c(this.g, calendar.getTimeInMillis(), 1));
        calendar.clear();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dLY_(view);
        if (this.r) {
            LogUtil.a("Track_InputSportHistoryActivity", "onClick()  mIsClickCheck is true");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        v();
        if (view == this.ab || view == this.an) {
            o();
        } else if (view == this.z) {
            c();
        } else if (view == this.u) {
            a();
        } else if (view == this.af) {
            u();
        } else if (view == this.ag) {
            w();
        } else {
            LogUtil.h("Track_InputSportHistoryActivity", "click is error");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dLY_(View view) {
        if (view == this.b) {
            e();
        } else if (view == this.d) {
            p();
        } else {
            LogUtil.h("Track_InputSportHistoryActivity", "click is error");
        }
    }

    private void v() {
        this.r = true;
        this.m.removeCallbacks(this.x);
        Runnable runnable = new Runnable() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (InputSportHistoryActivity.this.m == null) {
                    return;
                }
                InputSportHistoryActivity.this.r = false;
            }
        };
        this.x = runnable;
        this.m.postDelayed(runnable, 500L);
    }

    private void o() {
        LogUtil.a("Track_InputSportHistoryActivity", "jumpSportMotionType()");
        Intent intent = new Intent(this, (Class<?>) ChooseSportTypeActivity.class);
        intent.putExtra("sportTypeId", this.ar);
        intent.putExtra("sportGroupTypeId", this.al);
        startActivityForResult(intent, 10106);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10106) {
            LogUtil.h("Track_InputSportHistoryActivity", "onActivityResult is default");
            return;
        }
        if (i2 == -1) {
            dLX_(this.ab, hpn.bog_(intent.getStringExtra("resIconName"), R.drawable.ic_health_list_outdoor_running));
            this.aj.setText(this.g.getResources().getString(intent.getIntExtra("itemNameId", 0)));
            int intExtra = intent.getIntExtra("sportTypeId", 0);
            this.ar = intExtra;
            this.y.saveSportType(intExtra);
            this.al = intent.getStringExtra("sportGroupTypeId");
            this.i = intent.getBooleanExtra("distanceFlag", false);
            g();
            this.q = intent.getFloatExtra("typeMet", 0.0f);
        }
    }

    private void a() {
        LogUtil.a("Track_InputSportHistoryActivity", "createSportDistanceDialog()");
        rda.c cVar = new rda.c(this.g, this.m);
        cVar.e(this.y.requestSportType());
        rda e = cVar.e();
        if (e != null) {
            e.show();
        }
        this.h = e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLW_(Message message) {
        if (message.what == 10002) {
            dLV_(message);
        }
    }

    private void dLX_(ImageView imageView, Drawable drawable) {
        imageView.setPadding(4, 4, 4, 4);
        if (LanguageUtil.bc(this.g)) {
            BitmapDrawable cKm_ = nrz.cKm_(this.g, drawable);
            rds.dMw_(cKm_, this.g.getResources().getColor(R.color._2131299386_res_0x7f090c3a));
            imageView.setBackground(cKm_);
        } else {
            rds.dMw_(drawable, this.g.getResources().getColor(R.color._2131299386_res_0x7f090c3a));
            imageView.setBackground(drawable);
        }
    }

    private void dLV_(Message message) {
        String e;
        int e2;
        float f = message.arg1 / 100.0f;
        if (f % 100.0f == 0.0f) {
            e = UnitUtil.e(f, 1, 0);
        } else {
            e = UnitUtil.e(f, 1, 2);
        }
        int requestSportType = this.y.requestSportType();
        if (requestSportType == 262 || requestSportType == 266) {
            e2 = e(f);
        } else {
            e2 = c(f);
        }
        this.y.saveTotalDistance(e2);
        if (message.obj instanceof String) {
            String str = (String) message.obj;
            this.ac.setText(e + str);
        }
    }

    private int e(float f) {
        return UnitUtil.h() ? (int) Math.round(UnitUtil.d(f, 2)) : (int) f;
    }

    private int c(float f) {
        return UnitUtil.h() ? (int) (UnitUtil.d(f, 3) * 1000.0d) : (int) (f * 1000.0f);
    }

    private void f() {
        HwSportTypeInfo d = hln.c(this.g).d(this.ar);
        int e = rds.e(d.getSportTypeRes());
        dLX_(this.ab, d.getHistoryList().getItemDrawable());
        this.i = hkc.d(d.getSportTypeId());
        this.q = d.getMet();
        String string = this.g.getResources().getString(e);
        LogUtil.a("Track_InputSportHistoryActivity", "initSupplementingRecords() mSportTypeId is", Integer.valueOf(this.ar), "sportType is", string);
        this.y.saveSportType(this.ar);
        this.aj.setText(string);
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i, int i2, int i3, int i4, int i5) {
        if (i == -1) {
            i = this.as;
        }
        if (i2 == -1) {
            i2 = this.w;
        }
        if (i3 == -1) {
            i3 = this.f;
        }
        if (i4 == -1) {
            i4 = this.k;
        }
        if (i5 == -1) {
            i5 = this.t;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, i3);
        calendar.set(11, i4);
        calendar.set(12, i5);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.clear();
        if (timeInMillis <= System.currentTimeMillis() && timeInMillis + this.y.requestTotalTime() <= System.currentTimeMillis()) {
            return false;
        }
        a(this.g.getResources().getString(R$string.IDS_hwh_input_history_data_start_time_toast));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long d() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, this.as);
        calendar.set(2, this.w - 1);
        calendar.set(5, this.f);
        calendar.set(11, this.k);
        calendar.set(12, this.t);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.clear();
        return timeInMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        calendar.set(11, i3 - i);
        calendar.set(12, i4 - (i2 + 1));
        this.as = calendar.get(1);
        this.w = calendar.get(2) + 1;
        this.f = calendar.get(5);
        this.k = calendar.get(11);
        this.t = calendar.get(12);
        a(d());
        this.am.setText(b(calendar.getTime()));
        this.ae.setText(nsj.c(this.g, calendar.getTimeInMillis(), 1));
        calendar.clear();
    }

    private void j() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            this.aq = userInfo.getWeightOrDefaultValue();
        } else {
            this.aq = 60.0f;
            LogUtil.h("Track_InputSportHistoryActivity", "accountInfo is null");
        }
    }

    private float e(int i, float f, long j) {
        double e;
        float f2 = f / 1000.0f;
        float f3 = (float) ((j * 1.0d) / 60000.0d);
        switch (i) {
            case 257:
            case OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE /* 275 */:
            case 281:
            case 282:
                e = CalculateCaloriesUtils.e(f2, this.aq);
                break;
            case 258:
            case 264:
            case OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE /* 280 */:
                e = CalculateCaloriesUtils.b(f2, this.aq);
                break;
            case 259:
            case OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE /* 265 */:
                e = CalculateCaloriesUtils.b(this.q, f3, this.aq);
                break;
            case 260:
                e = CalculateCaloriesUtils.d(f2, this.aq);
                break;
            case 261:
            case 263:
            case OldToNewMotionPath.SPORT_TYPE_PINGPONG /* 267 */:
            case 268:
            case 269:
            case 270:
            case OldToNewMotionPath.SPORT_TYPE_BASKETBALL /* 271 */:
            case 272:
            case 276:
            case 277:
            case OldToNewMotionPath.SPORT_TYPE_BODY_BUILDING /* 278 */:
            case OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT /* 279 */:
            default:
                e = CalculateCaloriesUtils.d(this.q, f3, this.aq);
                break;
            case 262:
            case OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM /* 266 */:
                e = CalculateCaloriesUtils.c(f2, this.aq);
                break;
            case OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER /* 273 */:
            case OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE /* 274 */:
                e = CalculateCaloriesUtils.e(this.q, f3, this.aq);
                break;
        }
        float f4 = (float) e;
        if (f4 < 0.0f) {
            return 0.0f;
        }
        return f4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        this.y.saveStartTime(j + (System.currentTimeMillis() % 1000));
    }

    private void r() {
        if (this.y.requestSportType() == 512 || this.y.requestSportType() == 208 || this.y.requestSportType() == 279) {
            return;
        }
        this.y.saveTotalCalories((int) (e(this.y.requestSportType(), this.y.requestTotalDistance(), this.y.requestTotalTime()) * 1000.0f));
    }

    private void q() {
        if (this.y.requestTotalDistance() > 0) {
            float requestTotalTime = ((this.y.requestTotalTime() / 1000) * (this.y.requestSportType() == 274 ? 500.0f : 1000.0f)) / this.y.requestTotalDistance();
            LogUtil.a("Track_InputSportHistoryActivity", "avg pace is ", Float.valueOf(requestTotalTime));
            this.y.saveAvgPace(requestTotalTime);
        }
    }

    private void t() {
        if (this.i) {
            this.y.saveChiefSportDataType(0);
        } else {
            this.y.saveChiefSportDataType(1);
        }
    }

    private void p() {
        if (this.r) {
            LogUtil.a("Track_InputSportHistoryActivity", "close window(), onClick()  mIsClickCheck is true");
            return;
        }
        if ("".equals(this.aa.getText())) {
            a(this.g.getResources().getString(R$string.IDS_hwh_input_history_data_fill_complete_information_toast));
            return;
        }
        if (k() && this.i) {
            a(this.g.getResources().getString(R$string.IDS_hwh_input_history_data_fill_complete_information_toast));
            return;
        }
        if (d() > System.currentTimeMillis() || d() + this.y.requestTotalTime() > System.currentTimeMillis()) {
            a(this.g.getResources().getString(R$string.IDS_hwh_input_history_data_start_time_toast));
            return;
        }
        this.y.saveHasTrackPoint(false);
        r();
        t();
        q();
        MotionPathSimplify motionPathSimplify = this.y;
        motionPathSimplify.saveEndTime(motionPathSimplify.requestStartTime() + this.y.requestTotalTime());
        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(Long.valueOf(this.y.requestStartTime()));
        this.y.saveSportId("gps_maptracking_" + format);
        this.y.saveDeviceType(32);
        MotionPath motionPath = new MotionPath();
        if (gso.e().c(this.y, motionPath) == 0) {
            LogUtil.a("Track_InputSportHistoryActivity", "saveTrackDataToDatabase success");
        } else {
            LogUtil.a("Track_InputSportHistoryActivity", "saveTrackDataToDatabase failed");
        }
        e(motionPath);
    }

    private void e(MotionPath motionPath) {
        LogUtil.a("Track_InputSportHistoryActivity", "saveTrackDataToDatabase() StartTime: ", Long.valueOf(this.y.requestStartTime()), ", EndTime: ", Long.valueOf(this.y.requestEndTime()));
        gso.e().init(getApplicationContext());
        gso.e().c(motionPath, this.y);
        rds.a(this.ar);
        x();
        s();
        setResult(0);
        finish();
    }

    private boolean k() {
        return this.y.requestTotalDistance() <= 0 || this.y.requestTotalTime() <= 0;
    }

    private void x() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("sportId", this.y.requestSportId());
        if (!Utils.o()) {
            hashMap.put("startTime", Long.valueOf(this.y.requestStartTime()));
            hashMap.put("endTime", Long.valueOf(this.y.requestEndTime()));
            hashMap.put("distances", Integer.valueOf(this.y.requestTotalDistance()));
            hashMap.put("calories", Integer.valueOf(this.y.requestTotalCalories()));
        }
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.y.requestSportType()));
        hashMap.put("trackType", Integer.valueOf(this.y.requestTrackType()));
        hashMap.put("sportDataSource", Integer.valueOf(this.y.requestSportDataSource()));
        ixx.d().d(this.g, AnalyticsValue.BI_TRACK_SPORT_ADD_SPORT_KEY.value(), hashMap, 0);
    }

    private void s() {
        Intent intent = new Intent("input_sport_history_start_time");
        intent.setClass(this.g, SportHistoryActivity.class);
        intent.putExtra("startTime", this.y.requestStartTime());
        intent.putExtra("endTime", this.y.requestEndTime());
        intent.putExtra("distance", this.y.requestTotalDistance());
        intent.putExtra("pace", this.y.requestAvgPace());
        intent.putExtra("trackType", this.y.requestTrackType());
        intent.putExtra(BleConstants.SPORT_TYPE, this.y.requestSportType());
        intent.putExtra("duration", this.y.requestTotalTime());
        intent.putExtra("calorie", this.y.requestTotalCalories());
        intent.putExtra("chiefType", this.y.requestChiefSportDataType());
        BroadcastManagerUtil.bFI_(this.g, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(Date date) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(date);
    }

    private void a(String str) {
        Toast toast = this.ao;
        if (toast == null) {
            this.ao = Toast.makeText(this.g, str, 1);
        } else {
            toast.setText(str);
            this.ao.setDuration(1);
        }
        this.ao.show();
    }

    public void b() {
        Toast toast = this.ao;
        if (toast != null) {
            toast.cancel();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.r) {
            LogUtil.a("Track_InputSportHistoryActivity", "onBackPressed() ,close window(), onClick()  mIsClickCheck is true");
        } else {
            b();
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.dismiss();
        }
        b();
    }

    private void c() {
        LogUtil.a("Track_InputSportHistoryActivity", "createSportDurationDialog()");
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        HashMap hashMap = new HashMap(3);
        hashMap.put(0, healthMultiNumberPicker.d(0, 23, this.g.getResources().getString(R$string.IDS_band_data_sleep_unit_h)));
        hashMap.put(1, healthMultiNumberPicker.d(0, 59, this.g.getResources().getString(R$string.IDS_band_data_sleep_unit_m)));
        hashMap.put(2, healthMultiNumberPicker.d(0, 59, this.g.getResources().getString(R$string.IDS_second)));
        healthMultiNumberPicker.setDataContent(3, hashMap, new boolean[]{true, true, true}, new int[]{this.l, this.n, this.o});
        healthMultiNumberPicker.setColonAndUnit(3);
        c(healthMultiNumberPicker);
    }

    private void c(final HealthMultiNumberPicker healthMultiNumberPicker) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.g);
        builder.a(getString(R$string.IDS_hwh_input_history_data_duration)).czg_(healthMultiNumberPicker).czc_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                InputSportHistoryActivity.this.l = selectedLocations[0];
                InputSportHistoryActivity.this.n = selectedLocations[1];
                InputSportHistoryActivity.this.o = selectedLocations[2];
                LogUtil.c("Track_InputSportHistoryActivity", "createSportDurationDialog() hour=", Integer.valueOf(InputSportHistoryActivity.this.l), ", minute=", Integer.valueOf(InputSportHistoryActivity.this.n), ", second=", Integer.valueOf(InputSportHistoryActivity.this.o));
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, InputSportHistoryActivity.this.l);
                calendar.set(12, InputSportHistoryActivity.this.n);
                calendar.set(13, InputSportHistoryActivity.this.o);
                Date time = calendar.getTime();
                if (InputSportHistoryActivity.this.l > 0 || InputSportHistoryActivity.this.n > 0 || InputSportHistoryActivity.this.o > 0) {
                    long j = (InputSportHistoryActivity.this.o * 1000) + (InputSportHistoryActivity.this.n * 60000) + (InputSportHistoryActivity.this.l * 3600000);
                    LogUtil.c("Track_InputSportHistoryActivity", "createSportDurationDialog() TotalTime=", Long.valueOf(j));
                    InputSportHistoryActivity.this.y.saveTotalTime(j);
                    InputSportHistoryActivity.this.aa.setText(new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).format(time));
                } else {
                    InputSportHistoryActivity.this.y.saveTotalTime(0L);
                    InputSportHistoryActivity.this.aa.setText("");
                }
                if (InputSportHistoryActivity.this.p) {
                    InputSportHistoryActivity inputSportHistoryActivity = InputSportHistoryActivity.this;
                    inputSportHistoryActivity.a(inputSportHistoryActivity.l, InputSportHistoryActivity.this.n);
                }
                calendar.clear();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.show();
        this.h = e;
    }

    private void u() {
        LogUtil.a("Track_InputSportHistoryActivity", "showInputSportDateChooseDialog()");
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.6
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                int i4 = i2 + 1;
                if (InputSportHistoryActivity.this.e(i, i4, i3, -1, -1)) {
                    return;
                }
                InputSportHistoryActivity.this.as = i;
                InputSportHistoryActivity.this.w = i4;
                InputSportHistoryActivity.this.f = i3;
                LogUtil.c("Track_InputSportHistoryActivity", "showInputSportDateChooseDialog year=", Integer.valueOf(InputSportHistoryActivity.this.as), ", month=", Integer.valueOf(InputSportHistoryActivity.this.w), ", day=", Integer.valueOf(InputSportHistoryActivity.this.f));
                Calendar calendar = Calendar.getInstance();
                calendar.set(1, InputSportHistoryActivity.this.as);
                calendar.set(2, InputSportHistoryActivity.this.w - 1);
                calendar.set(5, InputSportHistoryActivity.this.f);
                InputSportHistoryActivity.this.am.setText(InputSportHistoryActivity.this.b(calendar.getTime()));
                InputSportHistoryActivity inputSportHistoryActivity = InputSportHistoryActivity.this;
                inputSportHistoryActivity.a(inputSportHistoryActivity.d());
                InputSportHistoryActivity.this.y.saveEndTime(InputSportHistoryActivity.this.d() + InputSportHistoryActivity.this.y.requestTotalTime());
                InputSportHistoryActivity.this.p = false;
                calendar.clear();
            }
        }, new GregorianCalendar(this.as, this.w - 1, this.f));
        healthDatePickerDialog.a(true, getString(R$string.IDS_hwh_input_history_data_workout_date));
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.show();
        this.h = healthDatePickerDialog;
    }

    private void w() {
        LogUtil.a("Track_InputSportHistoryActivity", "showInputSportStartTimeChooseDialog()");
        if (!(this.g.getSystemService("layout_inflater") instanceof LayoutInflater)) {
            LogUtil.a("Track_InputSportHistoryActivity", "showInputSportStartTimeChooseDialog ", "object is invalid type");
            return;
        }
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity.8
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                if (InputSportHistoryActivity.this.e(-1, -1, -1, i, i2)) {
                    return;
                }
                InputSportHistoryActivity.this.k = i;
                InputSportHistoryActivity.this.t = i2;
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, InputSportHistoryActivity.this.k);
                calendar.set(12, InputSportHistoryActivity.this.t);
                InputSportHistoryActivity.this.ae.setText(nsj.c(InputSportHistoryActivity.this.g, calendar.getTimeInMillis(), 1));
                InputSportHistoryActivity.this.p = false;
                InputSportHistoryActivity inputSportHistoryActivity = InputSportHistoryActivity.this;
                inputSportHistoryActivity.a(inputSportHistoryActivity.d());
                calendar.clear();
            }
        });
        healthTimePickerDialog.e(0, 0, 0, this.k, this.t);
        healthTimePickerDialog.b(getString(R$string.IDS_settings_seat_long_starttime));
        healthTimePickerDialog.show();
        this.h = healthTimePickerDialog;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.dismiss();
        }
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.g = null;
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
