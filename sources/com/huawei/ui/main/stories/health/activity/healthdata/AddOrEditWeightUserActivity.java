package com.huawei.ui.main.stories.health.activity.healthdata;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity;
import defpackage.cfi;
import defpackage.cgs;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.csf;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.qjx;
import defpackage.qjz;
import defpackage.qkn;
import defpackage.qpz;
import defpackage.qsj;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.CompileParameterUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class AddOrEditWeightUserActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f10008a;
    private int aa;
    private LinearLayout ab;
    private RelativeLayout ac;
    private qkn ad;
    private RelativeLayout ae;
    private CustomTitleBar af;
    private SocialShareCenterApi ag;
    private RelativeLayout ah;
    private RelativeLayout ai;
    private cfi aj;
    private HealthTextView ak;
    private HealthTextView al;
    private ImageView am;
    private ImageView an;
    private ImageView ao;
    private HealthTextView ap;
    private String aq;
    private ImageView ar;
    private HealthTextView as;
    private ImageView at;
    private LinearLayout au;
    private HealthTextView av;
    private ImageView aw;
    private float ax;
    private int ay;
    private LinearLayout b;
    private Bitmap d;
    private HealthTextView e;
    private int f;
    private qjz h;
    private Context i;
    private HealthEditText j;
    private LinearLayout k;
    private HealthRadioButton l;
    private HealthRadioButton n;
    private HealthRadioButton o;
    private LinearLayout p;
    private qjx q;
    private ImageView r;
    private int s;
    private boolean t;
    private boolean v;
    private Dialog g = null;
    private boolean w = true;
    private boolean u = true;
    private boolean x = true;
    private boolean y = true;
    private Toast z = null;
    private int m = -1;
    private Handler c = new d(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weight_user_edit);
        this.i = this;
        final Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "intent is null");
            return;
        }
        a(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i != 0 || AddOrEditWeightUserActivity.this.c == null) {
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = intent;
                AddOrEditWeightUserActivity.this.c.sendMessage(obtain);
            }
        });
        View findViewById = findViewById(R.id.hw_show_settings_info_layout);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById);
    }

    private void a(IBaseResponseCallback iBaseResponseCallback) {
        if (MultiUsersManager.INSTANCE.getEmptyUser() == null) {
            MultiUsersManager.INSTANCE.getOtherUserData(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(0, "");
        }
    }

    /* loaded from: classes8.dex */
    public static class d extends BaseHandler<AddOrEditWeightUserActivity> {
        d(AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            super(addOrEditWeightUserActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dyE_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AddOrEditWeightUserActivity addOrEditWeightUserActivity, Message message) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "handleMessageWhenReferenceNotNull()");
            if (addOrEditWeightUserActivity == null) {
                LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "handleMessageWhenReferenceNotNull obj == null !");
            } else {
                if (message.what != 0) {
                    return;
                }
                dyD_(addOrEditWeightUserActivity, message);
            }
        }

        private CustomPermissionAction c(final AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            return new CustomPermissionAction(addOrEditWeightUserActivity) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.d.4
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "onGranted: MEDIA_VIDEO_IMAGES");
                    if (Build.VERSION.SDK_INT > 29) {
                        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "is up Android 11 version");
                        addOrEditWeightUserActivity.a();
                    } else {
                        qkn.dET_(addOrEditWeightUserActivity);
                    }
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    super.onForeverDenied(permissionType);
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "onForeverDenied: MEDIA_VIDEO_IMAGES");
                }
            };
        }

        private CustomPermissionAction d(final AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            return new CustomPermissionAction(addOrEditWeightUserActivity) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.d.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "onGranted: CAMERA_IMAGE");
                    if (Build.VERSION.SDK_INT > 29) {
                        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "is up Android 11 version");
                        addOrEditWeightUserActivity.z();
                    } else {
                        qkn.dER_(addOrEditWeightUserActivity);
                    }
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    super.onForeverDenied(permissionType);
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "onForeverDenied: CAMERA_IMAGE");
                }
            };
        }

        private void dyD_(AddOrEditWeightUserActivity addOrEditWeightUserActivity, Message message) {
            if (addOrEditWeightUserActivity == null || message == null) {
                LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getUserInfo obj = null or msg = null");
                return;
            }
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                addOrEditWeightUserActivity.aq = intent.getStringExtra("weight_user_id_key");
                if (addOrEditWeightUserActivity.aq != null) {
                    addOrEditWeightUserActivity.aj = MultiUsersManager.INSTANCE.getSingleUserById(addOrEditWeightUserActivity.aq);
                } else {
                    LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "handleMessageWhenReferenceNotNull obj.mUserId is null");
                }
                addOrEditWeightUserActivity.ag = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
                addOrEditWeightUserActivity.q = new qjx(addOrEditWeightUserActivity);
                addOrEditWeightUserActivity.ad = new qkn(addOrEditWeightUserActivity, c(addOrEditWeightUserActivity), d(addOrEditWeightUserActivity));
                addOrEditWeightUserActivity.t = intent.getBooleanExtra("claimWeightData", false);
                addOrEditWeightUserActivity.v = CommonUtils.h(intent.getStringExtra("from")) == 1;
                addOrEditWeightUserActivity.m();
                addOrEditWeightUserActivity.l();
                addOrEditWeightUserActivity.f();
                return;
            }
            LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getUserInfo msg.obj not instanceof intent");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        this.ag.takePhoto((Activity) this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        nsn.cKT_((Activity) this.i, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.fitness_detail_titlebar);
        this.af = customTitleBar;
        this.q.c(this.aq, customTitleBar);
        this.af.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AddOrEditWeightUserActivity.this.z != null) {
                    AddOrEditWeightUserActivity.this.z.cancel();
                }
                if (AddOrEditWeightUserActivity.this.v) {
                    qsj.dIi_(AddOrEditWeightUserActivity.this, false);
                    AddOrEditWeightUserActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("isChange", false);
                    AddOrEditWeightUserActivity.this.setResult(-1, intent);
                    AddOrEditWeightUserActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.af.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "click save user");
                if (!AddOrEditWeightUserActivity.this.n() || !AddOrEditWeightUserActivity.this.k()) {
                    AddOrEditWeightUserActivity.this.s();
                } else {
                    AddOrEditWeightUserActivity.this.x();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.r = (ImageView) findViewById(R.id.weight_edit_user_photo);
        this.k = (LinearLayout) findViewById(R.id.hw_show_userinfo_gender_layout);
        this.p = (LinearLayout) findViewById(R.id.hw_show_userinfo_height_layout);
        this.b = (LinearLayout) findViewById(R.id.hw_show_userinfo_birthday_layout);
        this.au = (LinearLayout) findViewById(R.id.hw_show_userinfo_weight_layout);
        this.ab = (LinearLayout) findViewById(R.id.hw_show_userinfo_nickname_layout);
        this.al = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender);
        this.as = (HealthTextView) findViewById(R.id.hw_show_userinfo_height);
        this.ak = (HealthTextView) findViewById(R.id.hw_show_userinfo_birthday);
        this.e = (HealthTextView) findViewById(R.id.hw_show_userinfo_age_hint);
        this.ap = (HealthTextView) findViewById(R.id.hw_show_userinfo_nickname);
        this.av = (HealthTextView) findViewById(R.id.hw_show_userinfo_weight);
        this.an = (ImageView) findViewById(R.id.user_info_fragment_set_gender_image);
        this.ao = (ImageView) findViewById(R.id.user_info_fragment_set_height_image);
        this.am = (ImageView) findViewById(R.id.user_info_fragment_set_birthday_image);
        this.ar = (ImageView) findViewById(R.id.user_info_fragment_set_nickname_image);
        this.at = (ImageView) findViewById(R.id.user_info_fragment_set_user_photo_image);
        this.aw = (ImageView) findViewById(R.id.user_info_fragment_set_weight_image);
        this.f10008a = (HealthButton) findViewById(R.id.btn_edit_user_delete);
        this.ac = (RelativeLayout) findViewById(R.id.hw_show_userinfo_user_photo_layout);
        if (qsj.e(this)) {
            this.f10008a.setText(getString(R$string.IDS_hw_base_health_weight_delete_user).toUpperCase(Locale.ROOT));
        }
        p();
        this.q.dEG_(this.aj, this.f10008a);
    }

    private void p() {
        if (LanguageUtil.bc(this.i)) {
            this.ar.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.at.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.an.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ao.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.am.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.aw.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            return;
        }
        this.ar.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.at.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.an.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.ao.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.am.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.aw.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.k.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.au.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f10008a.setOnClickListener(this);
        this.ac.setOnClickListener(this);
        this.ab.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.q.d(this.aj, this.as, this.u);
        j();
        cfi cfiVar = this.aj;
        if (cfiVar != null) {
            this.d = cfiVar.Ex_();
            this.ap.setText(this.aj.h());
            if (this.aj.d() != 0) {
                this.s = this.aj.d();
            }
            if (this.aj.l() > 0.0f && !Float.isNaN(this.aj.l())) {
                this.ax = this.aj.l();
            }
            g();
            qpz.dHr_(this.aj, null, this.r);
        } else {
            this.ay = 1992;
            this.f = 1;
            this.aa = 1;
            this.s = 160;
            this.ax = 56.3f;
            r();
            Drawable drawable = BaseApplication.getContext().getResources().getDrawable(R.drawable._2131428686_res_0x7f0b054e);
            if (drawable instanceof BitmapDrawable) {
                this.r.setImageBitmap(nrf.cHX_(((BitmapDrawable) drawable).getBitmap()));
            } else {
                LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "initData drawable instanceof BitmapDrawable is false");
            }
        }
        o();
        if (CommonUtil.aa(this.i.getApplicationContext())) {
            return;
        }
        v();
    }

    private void g() {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "parseInt2BirthdayDate()");
        this.ay = 1992;
        this.f = 1;
        this.aa = 1;
        if (this.aj.g() != 0) {
            String valueOf = String.valueOf(this.aj.g());
            if (valueOf.length() < 4) {
                LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "get year out of bounds, length is ", Integer.valueOf(valueOf.length()));
            } else {
                this.ay = nsn.e(valueOf.subSequence(0, 4).toString());
            }
            if (valueOf.length() < 6) {
                LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "get month out of bounds, length is ", Integer.valueOf(valueOf.length()));
            } else {
                this.aa = nsn.e(valueOf.subSequence(4, 6).toString());
            }
            if (valueOf.length() <= 6) {
                LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "get day out of bounds, length is ", Integer.valueOf(valueOf.length()));
            } else {
                this.f = nsn.e(valueOf.subSequence(6, valueOf.length()).toString());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(this.ay, this.aa - 1, this.f);
            this.ak.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yMd")).format(calendar.getTime()));
            if (!Utils.o() && qpz.b(this.ay, this.aa - 1, this.f)) {
                this.e.setVisibility(0);
                return;
            } else {
                this.e.setVisibility(8);
                return;
            }
        }
        r();
    }

    private void j() {
        cfi cfiVar = this.aj;
        byte c2 = cfiVar == null ? (byte) 1 : cfiVar.c();
        if (this.w && this.aj == null) {
            this.al.setText(R$string.IDS_device_wifi_userinfo_please_select);
            return;
        }
        if (c2 == 0) {
            this.m = 0;
            this.al.setText(R$string.IDS_hw_show_set_default_gender_female);
        } else if (c2 == 2) {
            this.m = 2;
            this.al.setText(R$string.IDS_hw_me_userinfo_secret);
        } else {
            this.m = 1;
            this.al.setText(R$string.IDS_hw_show_set_default_gender_male);
        }
    }

    private void o() {
        if (this.aj == null) {
            float f = this.s / 100.0f;
            this.ax = b(22.0f, b(f, f, 3), 3);
            LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "userHeight:", Float.valueOf(f), "|weight:", Float.valueOf(this.ax));
            return;
        }
        d();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.k) {
            w();
        } else if (view == this.p) {
            this.u = false;
            this.as.setTextColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.q.d(this.aj, this.s, c());
        } else if (view == this.b) {
            this.x = false;
            this.ak.setTextColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            t();
        } else if (view == this.f10008a) {
            u();
        } else if (view == this.ac) {
            this.ad.dEX_((Activity) this.i);
        } else if (view == this.au) {
            ac();
        } else if (view == this.ab) {
            this.ap.setTextColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            c(this.aj);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.20
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                AddOrEditWeightUserActivity.this.ay = i;
                AddOrEditWeightUserActivity.this.aa = i2 + 1;
                AddOrEditWeightUserActivity.this.f = i3;
                if (qpz.a(i, i2, i3)) {
                    Toast.makeText(AddOrEditWeightUserActivity.this.i, AddOrEditWeightUserActivity.this.getString(R$string.IDS_hw_show_no_choose_birthday_after_now), 1).show();
                    return;
                }
                if (Utils.o() || !qpz.b(AddOrEditWeightUserActivity.this.ay, i2, AddOrEditWeightUserActivity.this.f)) {
                    AddOrEditWeightUserActivity.this.e.setVisibility(8);
                } else {
                    AddOrEditWeightUserActivity.this.e.setVisibility(0);
                }
                AddOrEditWeightUserActivity.this.r();
            }
        }, new GregorianCalendar(this.ay, this.aa - 1, this.f));
        Calendar calendar = Calendar.getInstance();
        healthDatePickerDialog.d(new GregorianCalendar(calendar.get(1) - 130, calendar.get(2), calendar.get(5)), new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5)));
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.show();
    }

    private void w() {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "showGenderPickerDialog()");
        this.w = false;
        this.al.setTextColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        View inflate = ((LayoutInflater) this.i.getSystemService("layout_inflater")).inflate(R.layout.hw_show_settings_gender_view, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.i);
        builder.a(getString(R$string.IDS_hw_show_set_gender)).czg_(inflate).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddOrEditWeightUserActivity.this.w = true;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.g = builder.e();
        if (!dyz_(inflate)) {
            LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "showGenderPickerDialog() dialog layout fail");
            this.g = null;
        } else {
            this.g.show();
        }
    }

    private boolean dyz_(View view) {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "initializeGenderDialogLayout()");
        if (view == null) {
            return false;
        }
        this.o = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview1);
        this.l = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview2);
        this.n = (HealthRadioButton) view.findViewById(R.id.settings_gender_secrect);
        this.ai = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout1);
        this.ah = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout2);
        this.ae = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout_secrect);
        this.ai.setOnClickListener(new c(this));
        this.ah.setOnClickListener(new a(this));
        this.ae.setOnClickListener(new e(this));
        a(this.m);
        return true;
    }

    private void a(int i) {
        if (i == 0) {
            this.o.setChecked(false);
            this.l.setChecked(true);
            this.n.setChecked(false);
        } else if (i == 1) {
            this.o.setChecked(true);
            this.l.setChecked(false);
            this.n.setChecked(false);
        } else if (i == 2) {
            this.o.setChecked(false);
            this.l.setChecked(false);
            this.n.setChecked(true);
        } else {
            this.o.setChecked(false);
            this.l.setChecked(false);
            this.n.setChecked(false);
        }
    }

    /* loaded from: classes8.dex */
    static class c implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<AddOrEditWeightUserActivity> f10015a;

        c(AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            this.f10015a = new WeakReference<>(addOrEditWeightUserActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "MyMaleOnClickListener onClick()");
            AddOrEditWeightUserActivity addOrEditWeightUserActivity = this.f10015a.get();
            if (addOrEditWeightUserActivity != null) {
                addOrEditWeightUserActivity.m = 1;
                addOrEditWeightUserActivity.o.setChecked(true);
                addOrEditWeightUserActivity.l.setChecked(false);
                addOrEditWeightUserActivity.n.setChecked(false);
                if (addOrEditWeightUserActivity.g != null) {
                    addOrEditWeightUserActivity.g.dismiss();
                }
                addOrEditWeightUserActivity.al.setText(R$string.IDS_hw_show_set_default_gender_male);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "MyMaleOnClickListener UserInfoActivity =null");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* loaded from: classes8.dex */
    static class a implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<AddOrEditWeightUserActivity> f10014a;

        a(AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            this.f10014a = new WeakReference<>(addOrEditWeightUserActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "MyFemaleOnClickListener onClick()");
            AddOrEditWeightUserActivity addOrEditWeightUserActivity = this.f10014a.get();
            if (addOrEditWeightUserActivity != null) {
                addOrEditWeightUserActivity.m = 0;
                addOrEditWeightUserActivity.o.setChecked(false);
                addOrEditWeightUserActivity.l.setChecked(true);
                addOrEditWeightUserActivity.n.setChecked(false);
                if (addOrEditWeightUserActivity.g != null) {
                    addOrEditWeightUserActivity.g.dismiss();
                }
                addOrEditWeightUserActivity.al.setText(R$string.IDS_hw_show_set_default_gender_female);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "MyFemaleOnClickListener UserInfoActivity is null");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* loaded from: classes8.dex */
    static class e implements View.OnClickListener {
        private final WeakReference<AddOrEditWeightUserActivity> e;

        e(AddOrEditWeightUserActivity addOrEditWeightUserActivity) {
            this.e = new WeakReference<>(addOrEditWeightUserActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "MySecretOnClickListener onClick()");
            AddOrEditWeightUserActivity addOrEditWeightUserActivity = this.e.get();
            if (addOrEditWeightUserActivity != null) {
                addOrEditWeightUserActivity.m = 2;
                addOrEditWeightUserActivity.o.setChecked(false);
                addOrEditWeightUserActivity.l.setChecked(false);
                addOrEditWeightUserActivity.n.setChecked(true);
                if (addOrEditWeightUserActivity.g != null) {
                    addOrEditWeightUserActivity.g.dismiss();
                }
                addOrEditWeightUserActivity.al.setText(R$string.IDS_hw_me_userinfo_secret);
                addOrEditWeightUserActivity.y();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "MySecretOnClickListener UserInfoActivity =null");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.i);
        builder.b(this.i.getString(R$string.IDS_hw_health_show_common_dialog_title)).e(this.i.getString(R$string.IDS_hwh_home_gender_change_remind_content)).cyV_(this.i.getString(R$string.IDS_common_notification_know_tips), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.x && this.aj == null) {
            this.ak.setText(R$string.IDS_device_wifi_userinfo_please_select);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.ay, this.aa - 1, this.f);
        this.ak.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yMd")).format(calendar.getTime()));
    }

    private IBaseResponseCallback c() {
        return new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.25
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getHeightListener onResponse object is null");
                    return;
                }
                if (i == 0) {
                    try {
                        AddOrEditWeightUserActivity.this.c(((Integer) obj).intValue());
                        return;
                    } catch (ClassCastException e2) {
                        LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", e2.getMessage());
                        return;
                    }
                }
                if (i == 1) {
                    try {
                        int[] iArr = (int[]) obj;
                        int length = iArr.length;
                        if (length < 2) {
                            LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getHeightListener onResponse length ", Integer.valueOf(length));
                        } else {
                            int i2 = iArr[0];
                            int i3 = iArr[1];
                            StringBuilder sb = new StringBuilder();
                            double d2 = i2;
                            sb.append(AddOrEditWeightUserActivity.this.getString(R$string.IDS_ft_string, new Object[]{UnitUtil.e(d2, 1, 0)}));
                            sb.append(" ");
                            double d3 = i3;
                            sb.append(AddOrEditWeightUserActivity.this.getString(R$string.IDS_ins_string, new Object[]{UnitUtil.e(d3, 1, 0)}));
                            AddOrEditWeightUserActivity.this.as.setText(sb.toString());
                            AddOrEditWeightUserActivity.this.s = (int) Math.rint((UnitUtil.d(d2, 1) * 100.0d) + UnitUtil.d(d3, 0));
                        }
                        return;
                    } catch (ClassCastException e3) {
                        LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", e3.getMessage());
                        return;
                    }
                }
                LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getHeightListener onResponse callbackCode ", Integer.valueOf(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        int i2 = i + 50;
        this.as.setText(getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, new Object[]{UnitUtil.e(i2, 1, 0)}));
        this.s = i2;
    }

    private boolean q() {
        boolean z;
        boolean z2;
        boolean z3;
        String trim = this.ap.getText().toString().trim();
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "editName is:", trim);
        boolean z4 = !trim.equals(this.i.getString(R$string.IDS_hw_base_health_pls_input_nick_name));
        if (this.aj == null) {
            z = !this.al.getText().toString().trim().equals(this.i.getString(R$string.IDS_device_wifi_userinfo_please_select));
            z2 = !this.as.getText().toString().trim().equals(this.i.getString(R$string.IDS_device_wifi_userinfo_please_select));
            z3 = !this.ak.getText().toString().trim().equals(this.i.getString(R$string.IDS_device_wifi_userinfo_please_select));
        } else {
            z = true;
            z2 = true;
            z3 = true;
        }
        if (z4 && z && z2 && z3) {
            return true;
        }
        Toast makeText = Toast.makeText(this.i, getString(R$string.IDS_hw_show_complete_privacy_wifi_tip), 0);
        this.z = makeText;
        makeText.show();
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "isCompleteName:", Boolean.valueOf(z4), "|isCompleteGender:", Boolean.valueOf(z), "|isCompleteHeight:", Boolean.valueOf(z2), "|isCompleteBirth", Boolean.valueOf(z3));
        if (!z4) {
            this.ap.setTextColor(this.i.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        if (!z) {
            this.al.setTextColor(this.i.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        if (!z2) {
            this.as.setTextColor(this.i.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        if (!z3) {
            this.ak.setTextColor(this.i.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        String trim = this.ap.getText().toString().trim();
        if (!q()) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "userinfo is not complete");
            return;
        }
        cfi d2 = this.q.d(this.aj, this.m);
        List<cfi> mainAllUser = MultiUsersManager.INSTANCE.getMainAllUser();
        for (int i = 0; i < mainAllUser.size(); i++) {
            if (trim.equals(mainAllUser.get(i).h()) && !trim.equals(d2.h())) {
                Toast makeText = Toast.makeText(BaseApplication.getContext(), getString(R$string.IDS_hw_base_health_occupied_nick_name), 0);
                this.z = makeText;
                makeText.show();
                return;
            }
        }
        cfi e2 = this.q.e(d2);
        e(MultiUsersManager.INSTANCE.getMainAllUser().size() + 1);
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "user is current ", Boolean.valueOf(e2.r()));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.ay).append(String.format(Locale.ROOT, "%02d", Integer.valueOf(this.aa))).append(String.format(Locale.ROOT, "%02d", Integer.valueOf(this.f)));
        e2.b(nsn.e(stringBuffer.toString()));
        e2.b(trim);
        e2.d(this.s);
        e2.Ey_(this.d);
        if (!this.y) {
            e2.b(this.ax);
            e2.e(this.ax);
        }
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "setWeight:", Float.valueOf(this.ax), "user.getUserInfoWeight():", Float.valueOf(e2.l()));
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            HashMap hashMap = new HashMap();
            hashMap.put("id", LoginInit.getInstance(this.i).getAccountInfo(1011));
            hashMap.put("uid", e2.i());
            hashMap.put(CommonConstant.KEY_GENDER, String.valueOf((int) (e2.c() != 2 ? e2.c() : (byte) 1)));
            hashMap.put("age", String.valueOf(cgs.e(e2.g(), e2.a())));
            hashMap.put("height", String.valueOf(e2.d()));
            hashMap.put("isDelete", String.valueOf(0));
            hashMap.put("currentWeight", String.valueOf(e2.l()));
            hashMap.put("month", String.valueOf(cgs.a(e2.g())));
            csf.c(hashMap, e2.j(), this.i);
        }
        b(e2.a(), e2.c());
        this.af.setRightButtonClickable(false);
        b(e2);
    }

    private void b(int i, byte b) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", 3);
        hashMap.put("age", (i < 14 || i > 17) ? (i < 18 || i > 23) ? (i < 24 || i > 34) ? (i < 35 || i > 44) ? (i < 45 || i > 54) ? (i < 55 || i > 64) ? i >= 65 ? "[65,+)" : "(-,14]" : "[55,64]" : "[45,54]" : "[35,44]" : "[24,34]" : "[18,23]" : "[14,17]");
        if (b == 2) {
            hashMap.put(CommonConstant.KEY_GENDER, "Prefer not to say");
        } else if (b == 1) {
            hashMap.put(CommonConstant.KEY_GENDER, "male");
        } else {
            hashMap.put(CommonConstant.KEY_GENDER, "female");
        }
        ixx.d().d(this.i, AnalyticsValue.WEIGHT_PAGE_CKICK_MANAGE_MEMBERS_2160120.value(), hashMap, 0);
    }

    private void b(final cfi cfiVar) {
        MultiUsersManager.INSTANCE.saveUser(cfiVar, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.23
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "saveUser onResponse");
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "update_user_weight_time" + cfiVar.i(), "" + System.currentTimeMillis(), (StorageParams) null);
                Intent intent = new Intent();
                if (!AddOrEditWeightUserActivity.this.t) {
                    if (AddOrEditWeightUserActivity.this.v) {
                        qsj.dIi_(AddOrEditWeightUserActivity.this, true);
                    } else {
                        intent.putExtra("isChange", true);
                        AddOrEditWeightUserActivity.this.setResult(-1, intent);
                    }
                } else {
                    intent.putExtra(HwPayConstant.KEY_USER_ID, cfiVar.i());
                    AddOrEditWeightUserActivity.this.setResult(1, intent);
                }
                if (AddOrEditWeightUserActivity.this.d(cfiVar)) {
                    AddOrEditWeightUserActivity.this.a(cfiVar);
                }
                AddOrEditWeightUserActivity.this.ab();
                AddOrEditWeightUserActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qbi
            @Override // java.lang.Runnable
            public final void run() {
                AddOrEditWeightUserActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).synCloud(hiSyncOption, new HiCommonListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.22
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                ReleaseLogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "syncUser onSuccess errorCode ", Integer.valueOf(i), " object ", obj);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "syncUser onFailure errorCode ", Integer.valueOf(i), " object ", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(cfi cfiVar) {
        if (cfiVar == null || !cfiVar.p()) {
            return false;
        }
        int g = cfiVar.g();
        return qpz.b(g / 10000, ((g % 10000) / 100) - 1, g % 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cfi cfiVar) {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "update_child_height_time" + cfiVar.i()))) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "update_child_height_time" + cfiVar.i(), "" + System.currentTimeMillis(), (StorageParams) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("userCount", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_WEIGHT_USERCOUNT_2030051.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2) {
            dyB_(intent);
            return;
        }
        if (i == 3) {
            dyx_(intent);
            return;
        }
        if (i != 4) {
            switch (i) {
                case 11:
                    if (this.ad != null) {
                        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "data=", intent);
                        this.ad.dEW_(i2, intent);
                        break;
                    } else {
                        LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "mSelectPhotoInteractor is null");
                        break;
                    }
                case 12:
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "take complete,preprare to cutting,data =", intent, ", requestCode = ", Integer.valueOf(i));
                    dyC_(intent);
                    break;
                case 13:
                    if (intent != null && this.ad != null) {
                        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "select data=", intent);
                        this.ad.dEV_(intent.getData());
                        break;
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = "data is null ";
                        objArr[1] = Boolean.valueOf(intent == null);
                        LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", objArr);
                        break;
                    }
                case 14:
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "pick complete,preprare to cutting,data =", intent, ", requestCode = ", Integer.valueOf(i));
                    if (intent == null) {
                        qkn.dET_((Activity) this.i);
                        break;
                    } else {
                        dyC_(intent);
                        break;
                    }
                default:
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "default");
                    break;
            }
            return;
        }
        this.ag.startCrop((Activity) this.i);
    }

    private void dyB_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.3
            @Override // java.lang.Runnable
            public void run() {
                AddOrEditWeightUserActivity.this.ag.startCrop(AddOrEditWeightUserActivity.this, intent);
            }
        });
    }

    private void dyx_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.4
            @Override // java.lang.Runnable
            public void run() {
                AddOrEditWeightUserActivity.this.dyw_(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dyw_(Intent intent) {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "dealCropResult");
        if (intent == null) {
            LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "data = null");
            return;
        }
        String stringExtra = intent.getStringExtra("bitmap");
        if (stringExtra == null) {
            LogUtil.b("HealthWeight_AddOrEditWeightUserActivity", "dealCropResult:bitmapPath from intent is null!");
            return;
        }
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "dealCropResult bitmapPath = ", stringExtra);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap cHX_ = nrf.cHX_(nrf.cJx_(nrf.cHC_(stringExtra, options), 300, 300));
        this.d = cHX_;
        if (cHX_ != null) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "mBitmap != null");
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "refresh mBitmap");
                    AddOrEditWeightUserActivity.this.r.setImageDrawable(new BitmapDrawable(AddOrEditWeightUserActivity.this.i.getResources(), AddOrEditWeightUserActivity.this.d));
                }
            });
            d(stringExtra);
            return;
        }
        LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "dealCropResult:bitmap is null");
    }

    private void d(String str) {
        FileUtils.d(new File(str));
    }

    public void dyC_(Intent intent) {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "setPicToView");
        if (intent == null || this.ad.a() == null) {
            return;
        }
        Bitmap dyy_ = dyy_(this.ad.a().getPath());
        this.d = dyy_;
        if (dyy_ != null) {
            LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "setPicToView mBitmap != null");
            Bitmap cJx_ = nrf.cJx_(this.d, 300, 300);
            this.d = cJx_;
            this.r.setImageBitmap(nrf.cHX_(cJx_));
        }
    }

    private Bitmap dyy_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "path is null");
            return null;
        }
        if (!new File(str).exists()) {
            return null;
        }
        Bitmap cIi_ = nrf.cIi_(str, 200);
        if (cIi_ == null) {
            LogUtil.h("HealthWeight_AddOrEditWeightUserActivity", "getThumbnailImage is null");
            return cIi_;
        }
        return nrf.cHX_(cIi_);
    }

    private void u() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.i);
        builder.e(this.i.getString(R$string.IDS_hw_base_health_weight_confirm_delete_user)).czA_(this.i.getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(this.i.getString(R$string.IDS_hw_common_ui_dialog_confirm), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("id", LoginInit.getInstance(AddOrEditWeightUserActivity.this.i).getAccountInfo(1011));
                    hashMap.put("uid", AddOrEditWeightUserActivity.this.aj.i());
                    hashMap.put("isDelete", String.valueOf(1));
                    csf.c(hashMap, AddOrEditWeightUserActivity.this.aj.j(), AddOrEditWeightUserActivity.this.i);
                }
                if (AddOrEditWeightUserActivity.this.aj.j() != null && AddOrEditWeightUserActivity.this.aj.j().length() != 0) {
                    MultiUsersManager.INSTANCE.setUuidBySid(AddOrEditWeightUserActivity.this.aj.j(), "delete");
                }
                AddOrEditWeightUserActivity.this.e(MultiUsersManager.INSTANCE.getMainAllUser().size() - 1);
                WeightDataManager.INSTANCE.getUserWeightDataMap().remove(AddOrEditWeightUserActivity.this.aj.i());
                WeightDataManager.INSTANCE.setInitFlag(true);
                if (AddOrEditWeightUserActivity.this.c != null) {
                    AddOrEditWeightUserActivity addOrEditWeightUserActivity = AddOrEditWeightUserActivity.this;
                    addOrEditWeightUserActivity.h = new qjz((Activity) addOrEditWeightUserActivity.i, AddOrEditWeightUserActivity.this.c);
                }
                AddOrEditWeightUserActivity.this.h.b(AddOrEditWeightUserActivity.this.q.e(AddOrEditWeightUserActivity.this.aj.i()));
                MultiUsersManager.INSTANCE.deleteUser(AddOrEditWeightUserActivity.this.aj.i(), AddOrEditWeightUserActivity.this.aj.r(), new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.5.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (AddOrEditWeightUserActivity.this.v) {
                            qsj.dIi_(AddOrEditWeightUserActivity.this, true);
                            AddOrEditWeightUserActivity.this.finish();
                            return;
                        }
                        Intent intent = new Intent();
                        intent.putExtra("isDelUser", true);
                        EventBus.d(new EventBus.b("evebus_weight_measure_notification", intent));
                        intent.putExtra("isChange", true);
                        AddOrEditWeightUserActivity.this.setResult(-1, intent);
                        AddOrEditWeightUserActivity.this.finish();
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "showModifyConfirmDialog() enter");
        View inflate = View.inflate(this, R.layout.dialog_confirm_user_info, null);
        ((HealthCheckBox) inflate.findViewById(R.id.confirm_userinfo_dialog_box)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "modifyConfirmDialog onClick not remind");
                StorageParams storageParams = new StorageParams();
                if (z) {
                    SharedPreferenceManager.e(AddOrEditWeightUserActivity.this.i, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", String.valueOf(false), storageParams);
                } else {
                    SharedPreferenceManager.e(AddOrEditWeightUserActivity.this.i, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", "true", storageParams);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.i);
        builder.czg_(inflate).czd_(this.i.getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(this.i.getString(R$string.IDS_hw_common_ui_dialog_confirm), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddOrEditWeightUserActivity.this.s();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        ArrayList<String> c2 = cjx.e().c();
        boolean z = (c2 == null || c2.size() == 0) ? false : true;
        if (cpa.h()) {
            z = true;
        }
        return z && (String.valueOf(false).equals(SharedPreferenceManager.b(this.i, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree")) ^ true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        cfi cfiVar = this.aj;
        if (cfiVar == null) {
            return false;
        }
        byte c2 = cfiVar.c();
        int d2 = this.aj.d();
        int g = this.aj.g();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.ay).append(String.format(Locale.ROOT, "%02d", Integer.valueOf(this.aa))).append(String.format(Locale.ROOT, "%02d", Integer.valueOf(this.f)));
        return (c2 == this.m && d2 == this.s && g == nsn.e(stringBuffer.toString())) ? false : true;
    }

    private void v() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.i);
        builder.b(getString(R$string.IDS_hw_health_show_common_dialog_title)).e(getString(R$string.IDS_hw_show_set_about_privacy_connectting_error)).cyV_(getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddOrEditWeightUserActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCanceledOnTouchOutside(false);
        a2.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Toast toast = this.z;
        if (toast != null) {
            toast.cancel();
        }
        if (this.v) {
            qsj.dIi_(this, false);
        } else {
            Intent intent = new Intent();
            intent.putExtra("isChange", false);
            setResult(-1, intent);
        }
        finish();
        super.onBackPressed();
    }

    private void c(cfi cfiVar) {
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "showEditNickNameDialog enter");
        View inflate = View.inflate(this, R.layout.commonui_dialog_single_edit, null);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        this.j = healthEditText;
        healthEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        if (cfiVar != null) {
            this.j.setText(cfiVar.h());
            this.j.setSelection(cfiVar.h().length());
        }
        this.j.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.14
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4 || i == 6) {
                    return true;
                }
                return keyEvent != null && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0;
            }
        });
        dyA_(inflate);
        Handler handler = this.c;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    AddOrEditWeightUserActivity.this.ad();
                    LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "setFocusable");
                    AddOrEditWeightUserActivity.this.j.setFocusable(true);
                    AddOrEditWeightUserActivity.this.j.setFocusableInTouchMode(true);
                    AddOrEditWeightUserActivity.this.j.requestFocus();
                }
            }, 100L);
        }
    }

    private void dyA_(View view) {
        CustomViewDialog e2 = new CustomViewDialog.Builder(this.i).d(R$string.IDS_activity_personal_information_name).czg_(view).czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).cze_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!TextUtils.isEmpty(AddOrEditWeightUserActivity.this.j.getText().toString().trim())) {
                    AddOrEditWeightUserActivity.this.ap.setText(AddOrEditWeightUserActivity.this.j.getText().toString().trim());
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).e();
        e2.setCanceledOnTouchOutside(false);
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        ((InputMethodManager) this.i.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    private void d() {
        if (this.aj == null) {
            i();
            return;
        }
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "muser weight is:", Float.valueOf(this.ax), "mUser.getUserInfoWeight():", Float.valueOf(this.aj.l()));
        if (this.aj.l() <= 0.0f || Float.isNaN(this.aj.l())) {
            float f = this.s / 100.0f;
            this.ax = b(22.0f, b(f, f, 3), 3);
            return;
        }
        float l = this.aj.l();
        this.ax = l;
        String e2 = qsj.e(UnitUtil.a(l), 1);
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "muser user weightStr is:", e2);
        this.av.setText(e2);
    }

    private void i() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "endTime:", Long.valueOf(currentTimeMillis));
        String[] strArr = {BleConstants.WEIGHT_KEY};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(HiDateUtil.t(0L), HiDateUtil.f(currentTimeMillis));
        hiAggregateOption.setTimeRange(0L, currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter(this.aj.i());
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(this.i).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.19
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (list == null || list.size() <= 0) {
                    LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "datas is null");
                    float d2 = AddOrEditWeightUserActivity.this.aj.d() / 100.0f;
                    float b = AddOrEditWeightUserActivity.this.b(d2, d2, 3);
                    AddOrEditWeightUserActivity addOrEditWeightUserActivity = AddOrEditWeightUserActivity.this;
                    addOrEditWeightUserActivity.ax = addOrEditWeightUserActivity.b(22.0f, b, 3);
                    LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "user weight is:", new DecimalFormat("#.0").format(AddOrEditWeightUserActivity.this.ax));
                    return;
                }
                float f = (float) list.get(0).getDouble("bodyWeight");
                AddOrEditWeightUserActivity.this.ax = f;
                if (0.0f != AddOrEditWeightUserActivity.this.ax) {
                    AddOrEditWeightUserActivity.this.aj.b(AddOrEditWeightUserActivity.this.ax);
                }
                LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "aggregateHiHealthData user weight is:", Float.valueOf(f));
                AddOrEditWeightUserActivity.this.a(f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        String e2 = qsj.e(UnitUtil.a(f), 1);
        this.av.setText(e2);
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "muser user weightStr is:", e2);
    }

    private void h() {
        if (this.aj == null) {
            float f = this.s / 100.0f;
            this.ax = b(22.0f, b(f, f, 3), 3);
        }
    }

    private void ac() {
        h();
        View inflate = View.inflate(this, R.layout.health_healthdata_userinfo_multiuserweight_dialog, null);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_multiuserWeight_dialog_tips)).setText(this.i.getString(R$string.IDS_device_wifi_multiuserweight_dialog_tips));
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.multiuserweight_number_picker);
        c(healthNumberPicker, b());
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.i);
        builder.a(this.i.getString(R$string.IDS_hw_health_show_healthdata_weight)).czg_(inflate).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HealthWeight_AddOrEditWeightUserActivity", "get weight wselect=", Float.valueOf(healthNumberPicker.getValue() / 10.0f));
                AddOrEditWeightUserActivity.this.av.setText(qsj.e(AddOrEditWeightUserActivity.this.b(healthNumberPicker.getValue() / 10.0f, (float) UnitUtil.a(10.0f), 1), 1));
                int a2 = UnitUtil.a();
                if (a2 == 1) {
                    AddOrEditWeightUserActivity.this.ax = (float) UnitUtil.c(r0.b(healthNumberPicker.getValue() / 10.0f, (float) UnitUtil.b(10.0d), 1));
                } else if (a2 == 3) {
                    AddOrEditWeightUserActivity.this.ax = (float) UnitUtil.i(r0.b(healthNumberPicker.getValue() / 10.0f, (float) UnitUtil.h(10.0d), 1));
                } else {
                    AddOrEditWeightUserActivity addOrEditWeightUserActivity = AddOrEditWeightUserActivity.this;
                    addOrEditWeightUserActivity.ax = addOrEditWeightUserActivity.b(healthNumberPicker.getValue() / 10.0f, 10.0f, 1);
                }
                AddOrEditWeightUserActivity.this.y = false;
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    private void c(HealthNumberPicker healthNumberPicker, ArrayList<String> arrayList) {
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        float b = b(b((float) UnitUtil.a(this.ax), (float) UnitUtil.a(10.0f), 2), 10.0f, 3);
        Float valueOf = Float.valueOf(this.ax);
        Float valueOf2 = Float.valueOf(b);
        int i = (int) b;
        LogUtil.c("HealthWeight_AddOrEditWeightUserActivity", "get weight:", valueOf, "|floatValue:", valueOf2, "|value:", Integer.valueOf(i));
        healthNumberPicker.setValue(i);
    }

    private ArrayList<String> b() {
        ArrayList<String> arrayList = new ArrayList<>();
        float f = 10;
        int a2 = (int) (((float) UnitUtil.a(250.0d)) * f);
        for (int a3 = (int) (((float) UnitUtil.a(10.0d)) * f); a3 < a2; a3++) {
            arrayList.add(qsj.e(a3 / f, 1));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(float f, float f2, int i) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(f2));
        if (i == 1) {
            return bigDecimal.add(bigDecimal2).floatValue();
        }
        if (i == 2) {
            return bigDecimal.subtract(bigDecimal2).floatValue();
        }
        if (i == 3) {
            return bigDecimal.multiply(bigDecimal2).floatValue();
        }
        if (i != 4) {
            return -1.0f;
        }
        return bigDecimal.divide(bigDecimal2, 10, 4).floatValue();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
