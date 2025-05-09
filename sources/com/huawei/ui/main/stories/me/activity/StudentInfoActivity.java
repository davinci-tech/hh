package com.huawei.ui.main.stories.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.up.model.UserInfomation;
import defpackage.gmc;
import defpackage.jhb;
import defpackage.kox;
import defpackage.kpk;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Calendar;

/* loaded from: classes9.dex */
public class StudentInfoActivity extends BaseActivity implements View.OnClickListener {
    private Context i = null;
    private RelativeLayout m = null;
    private RelativeLayout h = null;
    private RelativeLayout r = null;
    private RelativeLayout ag = null;
    private RelativeLayout p = null;
    private HealthDivider q = null;
    private RelativeLayout v = null;
    private HealthDivider x = null;
    private CustomTitleBar b = null;
    private HealthTextView k = null;
    private HealthTextView g = null;
    private HealthTextView s = null;
    private HealthTextView af = null;
    private ImageView j = null;

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10349a = null;
    private ImageView l = null;
    private ImageView ah = null;
    private String n = "";
    private String f = "";
    private String u = "";
    private CommonDialog21 y = null;
    private int d = 0;
    private int c = 0;
    private int e = 0;
    private int o = 0;
    private int t = 0;
    private UserInfomation ad = new UserInfomation(0);
    private int ab = 1;
    private int aa = 0;
    private int ac = 0;
    private float ae = 0.0f;
    private int z = 0;
    private d w = new d(this);

    private int b(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        return i;
    }

    static class d implements IBaseResponseCallback {
        WeakReference<StudentInfoActivity> d;

        d(StudentInfoActivity studentInfoActivity) {
            this.d = new WeakReference<>(studentInfoActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            StudentInfoActivity studentInfoActivity = this.d.get();
            if (studentInfoActivity == null) {
                LogUtil.h("StudentInfoActivity", "onResponse activity null.");
                return;
            }
            if (!(obj instanceof UserInfomation)) {
                LogUtil.h("StudentInfoActivity", "onResponse objData is not UserInformation.");
                return;
            }
            LogUtil.a("StudentInfoActivity", "onResponse errorCode: ", Integer.valueOf(i));
            UserInfomation userInfomation = (UserInfomation) obj;
            if (studentInfoActivity.c(i, userInfomation)) {
                studentInfoActivity.o();
                if (i == 0) {
                    int height = userInfomation.getHeight();
                    studentInfoActivity.a(height);
                    studentInfoActivity.j(height);
                } else if (i == 1) {
                    float weight = userInfomation.getWeight();
                    studentInfoActivity.e(userInfomation.getWeight());
                    studentInfoActivity.c(weight);
                } else if (i == 3) {
                    int a2 = CommonUtil.a(userInfomation.getBirthday(), 10);
                    studentInfoActivity.e(a2);
                    studentInfoActivity.aa = a2;
                } else if (i == 19) {
                    int gender = userInfomation.getGender();
                    studentInfoActivity.c(gender);
                    studentInfoActivity.ab = gender;
                } else {
                    LogUtil.h("StudentInfoActivity", "errorCode is unknown.");
                    return;
                }
                studentInfoActivity.g();
                return;
            }
            LogUtil.h("StudentInfoActivity", "onResponse birthday is error.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = this;
        setContentView(R.layout.hw_show_studentinfo_activity);
        i();
        d();
    }

    private void i() {
        a();
        f();
        e();
    }

    private void a() {
        this.m = (RelativeLayout) findViewById(R.id.hw_show_userinfo_gender_layout);
        this.h = (RelativeLayout) findViewById(R.id.hw_show_userinfo_birthday_layout);
        this.r = (RelativeLayout) findViewById(R.id.hw_show_userinfo_height_layout);
        this.ag = (RelativeLayout) findViewById(R.id.hw_show_userinfo_weight_layout);
        this.b = (CustomTitleBar) findViewById(R.id.me_userInfo_titlebar);
        this.q = (HealthDivider) findViewById(R.id.divide_line_before_interest);
        this.p = (RelativeLayout) findViewById(R.id.hw_show_userinfo_interest_layout);
        this.x = (HealthDivider) findViewById(R.id.divide_line_before_sos);
        this.v = (RelativeLayout) findViewById(R.id.hw_show_userinfo_sos_layout);
        this.q.setVisibility(8);
        this.p.setVisibility(8);
        this.x.setVisibility(8);
        this.v.setVisibility(8);
        this.m.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.ag.setOnClickListener(this);
        this.b.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.StudentInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("StudentInfoActivity", "exist activity.");
                StudentInfoActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void f() {
        this.k = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender);
        this.g = (HealthTextView) findViewById(R.id.hw_show_userinfo_birthday);
        this.s = (HealthTextView) findViewById(R.id.hw_show_userinfo_height);
        this.af = (HealthTextView) findViewById(R.id.hw_show_userinfo_weight);
        this.k.setVisibility(0);
        this.g.setVisibility(0);
        this.s.setVisibility(0);
        this.af.setVisibility(0);
        this.n = this.i.getResources().getString(R$string.IDS_hw_show_set_default_gender_male);
        this.f = this.i.getResources().getString(R$string.IDS_hw_show_set_default_gender_female);
        this.u = this.i.getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
        if (LanguageUtil.b(this.i.getApplicationContext())) {
            this.k.setWidth(nsn.c(this.i.getApplicationContext(), 60.0f));
            this.k.setGravity(GravityCompat.START);
        }
    }

    private void e() {
        this.j = (ImageView) findViewById(R.id.user_info_fragment_set_gender_image);
        this.f10349a = (ImageView) findViewById(R.id.user_info_fragment_set_birthday_image);
        this.l = (ImageView) findViewById(R.id.user_info_fragment_set_height_image);
        this.ah = (ImageView) findViewById(R.id.user_info_fragment_set_weight_image);
        if (LanguageUtil.bc(this.i)) {
            this.j.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.f10349a.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.l.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.ah.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            return;
        }
        this.j.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        this.f10349a.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        this.l.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        this.ah.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
    }

    private void d() {
        UserInfomation b = jhb.b();
        this.ad = b;
        this.z = b.getAge();
        h();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("StudentInfoActivity", "onClick view is null.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.m) {
            k();
        } else if (view == this.h) {
            m();
        } else if (view == this.r) {
            n();
        } else if (view == this.ag) {
            t();
        } else {
            LogUtil.h("StudentInfoActivity", "onClick unknown view.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void k() {
        LogUtil.a("StudentInfoActivity", "Enter showGenderSelectDialog.");
        UserInfomation userInfomation = new UserInfomation(0);
        int i = this.ab;
        if (i != 1 && i != 0) {
            userInfomation.setGender(Integer.valueOf(b(1)));
        } else {
            userInfomation.setGender(Integer.valueOf(b(i)));
        }
        gmc.aPa_(this, 19, userInfomation, this.w);
    }

    private void m() {
        LogUtil.a("StudentInfoActivity", "Enter showBirthdaySelectDialog.");
        int a2 = CommonUtil.a(this.ad.getBirthday(), 10);
        if (a2 == -100) {
            Calendar calendar = Calendar.getInstance();
            this.d = calendar.get(1) - 15;
            this.c = calendar.get(2);
            this.e = calendar.get(5);
        } else {
            this.d = a2 / 10000;
            this.c = ((a2 % 10000) / 100) - 1;
            this.e = a2 % 100;
        }
        gmc.aOZ_(this, this.d, this.c, this.e, this.w);
    }

    private void n() {
        LogUtil.a("StudentInfoActivity", "Enter showHeightSelectDialog.");
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.setHeight(this.ac);
        gmc.aPb_(this, userInfomation, this.w);
    }

    private void t() {
        LogUtil.a("StudentInfoActivity", "Enter showWeightSelectDialog.");
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.setWeight(this.ae);
        gmc.aPd_(this, userInfomation, this.w);
    }

    private void h() {
        o();
        this.ab = b(this.ad.getGender());
        this.aa = CommonUtil.a(this.ad.getBirthday(), 10);
        this.ac = this.ad.getHeight();
        this.ae = this.ad.getWeight();
        LogUtil.c("StudentInfoActivity", "initStudentInfoView mStudentGender: ", Integer.valueOf(this.ab), ", mStudentBirthday: ", Integer.valueOf(this.aa), ", mStudentHeight: ", Integer.valueOf(this.ac), ", mStudentWeight: ", Float.valueOf(this.ae));
        c(this.ab);
        e(this.aa);
        d(this.ac);
        a(this.ae);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.c("StudentInfoActivity", "Enter refreshGenderView gender: ", Integer.valueOf(i));
        if (i == 0) {
            this.k.setText(this.f);
        } else if (i == 1) {
            this.k.setText(this.n);
        } else {
            this.k.setText(this.u);
        }
        this.k.setTextColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.c("StudentInfoActivity", "Enter refreshBirthdayView birthday: ", Integer.valueOf(i));
        if (i == -100) {
            this.g.setText(this.u);
            return;
        }
        this.d = i / 10000;
        this.c = ((i % 10000) / 100) - 1;
        this.e = i % 100;
        l();
    }

    private void l() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.d, this.c, this.e);
        this.g.setText(UnitUtil.a(calendar.getTime(), 20));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.c("StudentInfoActivity", "Enter refreshHeightView height: ", Integer.valueOf(i));
        if (i <= 0) {
            this.s.setText(this.u);
            return;
        }
        if (UnitUtil.h()) {
            int[] iArr = {5, 7};
            if (i > 30) {
                int[] j = UnitUtil.j(i / 100.0d);
                if (j[0] > 0 && j[1] >= 0) {
                    iArr = j;
                }
            }
            this.o = iArr[0];
            this.t = iArr[1];
            this.s.setText(UnitUtil.e(this.o, 1, 0) + " " + this.i.getResources().getString(R$string.IDS_ft) + " " + UnitUtil.e(this.t, 1, 0) + " " + this.i.getResources().getString(R$string.IDS_ins));
            return;
        }
        this.s.setText(this.i.getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(i + 50, 1, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(float f) {
        LogUtil.c("StudentInfoActivity", "Enter refreshWeightView weight: ", Float.valueOf(f));
        if (f <= 0.0f) {
            this.af.setText(this.u);
        } else if (UnitUtil.h()) {
            this.af.setText(this.i.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, UnitUtil.e(f + 22.0f, 1, 1)));
        } else {
            this.af.setText(this.i.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, UnitUtil.e(f + 10.0f, 1, 1)));
        }
    }

    private void d(int i) {
        LogUtil.c("StudentInfoActivity", "Enter initHeightView height: ", Integer.valueOf(i));
        if (i <= 0) {
            this.s.setText(this.u);
            return;
        }
        if (UnitUtil.h()) {
            int[] iArr = {5, 7};
            if (i > 30) {
                iArr = UnitUtil.j(i / 100.0d);
            }
            this.o = iArr[0];
            this.t = iArr[1];
            this.s.setText(UnitUtil.e(this.o, 1, 0) + " " + this.i.getResources().getString(R$string.IDS_ft) + " " + UnitUtil.e(this.t, 1, 0) + " " + this.i.getResources().getString(R$string.IDS_ins));
            return;
        }
        this.s.setText(getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(i, 1, 0)));
    }

    private void a(float f) {
        LogUtil.c("StudentInfoActivity", "Enter initWeightView height: ", Float.valueOf(f));
        if (f <= 0.0f) {
            this.af.setText(this.u);
            return;
        }
        if (UnitUtil.h()) {
            this.af.setText(this.i.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, UnitUtil.e(UnitUtil.h(f), 1, 1)));
        } else {
            this.af.setText(this.i.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, UnitUtil.e(CommonUtil.a(String.valueOf(f)), 1, 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.ad == null) {
            LogUtil.a("StudentInfoActivity", "saveStudentInfo student info is error.");
            c();
            return;
        }
        int b = b();
        LogUtil.a("StudentInfoActivity", "saveStudentInfo studentInfoModifyType: ", Integer.valueOf(b));
        if (b == -1) {
            c();
            return;
        }
        jhb.d(b, this.ad);
        LocalBroadcastManager.getInstance(this.i).sendBroadcast(new Intent("com.huawei.bone.action.STUDENT_INFO_UPDATE"));
        if (j()) {
            LogUtil.a("StudentInfoActivity", "saveStudentInfo student age change.");
            kpk a2 = kox.e().a();
            if (a2 != null) {
                a2.c(this.ad.getAge());
            }
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        if (UnitUtil.h()) {
            int[] iArr = {5, 7};
            if (i > 30) {
                int[] j = UnitUtil.j(i / 100.0d);
                if (j[0] > 0 && j[1] >= 0) {
                    iArr = j;
                }
            }
            int i2 = iArr[0];
            this.o = i2;
            this.t = iArr[1];
            this.ac = (int) Math.rint((UnitUtil.d(i2, 1) * 100.0d) + UnitUtil.d(this.t, 0));
            return;
        }
        this.ac = i + 50;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        if (UnitUtil.h()) {
            this.ae = (float) UnitUtil.i(f + 22.0f);
        } else {
            this.ae = f + 10.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CommonDialog21 commonDialog21;
        if (this.y == null) {
            LogUtil.a("StudentInfoActivity", "showLoadingDialog is domestic app.");
            new CommonDialog21(this.i, R.style.app_update_dialogActivity);
            this.y = CommonDialog21.a(this.i);
        }
        if (isFinishing() || (commonDialog21 = this.y) == null) {
            return;
        }
        commonDialog21.e(this.i.getString(R$string.IDS_sns_waiting));
        this.y.setCancelable(false);
        this.y.a();
        this.y.show();
    }

    private void c() {
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.me.activity.StudentInfoActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (StudentInfoActivity.this.y == null || !StudentInfoActivity.this.y.isShowing()) {
                    return;
                }
                StudentInfoActivity.this.y.dismiss();
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i, UserInfomation userInfomation) {
        if (i != 3) {
            return true;
        }
        int a2 = CommonUtil.a(userInfomation.getBirthday(), 10);
        if (!e(a2 / 10000, ((a2 % 10000) / 100) - 1, a2 % 100)) {
            return true;
        }
        nrh.c(this.i.getApplicationContext(), this.i.getResources().getString(R$string.IDS_hw_show_no_choose_birthday_after_now));
        return false;
    }

    private boolean e(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        if (i > i4) {
            return true;
        }
        if (i != i4 || i2 <= i5) {
            return i == i4 && i2 == i5 && i3 > i6;
        }
        return true;
    }

    private int b() {
        int i;
        int b = b(this.ad.getGender());
        int i2 = this.ab;
        if (b != i2) {
            this.ad.setGender(Integer.valueOf(b(i2)));
            i = 1;
        } else {
            i = -1;
        }
        int a2 = CommonUtil.a(this.ad.getBirthday(), 10);
        int i3 = this.aa;
        if (a2 != i3) {
            this.ad.setBirthday(String.valueOf(i3));
            i = 2;
        }
        int height = this.ad.getHeight();
        int i4 = this.ac;
        if (height != i4) {
            this.ad.setHeight(i4);
            i = 3;
        }
        float weight = this.ad.getWeight();
        float f = this.ae;
        if (weight == f) {
            return i;
        }
        this.ad.setWeight(f);
        return 4;
    }

    private boolean j() {
        int age = this.ad.getAge();
        if (this.z == age) {
            return false;
        }
        this.z = age;
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
