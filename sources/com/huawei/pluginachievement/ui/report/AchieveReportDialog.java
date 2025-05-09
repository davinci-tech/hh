package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.ixx;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mkx;
import defpackage.mlg;
import defpackage.nqf;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveReportDialog extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f8455a;
    private ImageView b;
    private HealthTextView c;
    private LinearLayout d;
    private ImageView e;
    private HealthTextView f;
    private String g;
    private HealthTextView h;
    private HealthTextView i;
    private Context j;
    private HealthTextView k;
    private HealthTextView l;
    private HiUserInfo o;
    private View q;
    private String t;
    private int n = 0;
    private Handler m = new b(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        setContentView(R.layout.achieve_report_message_dialog);
        cancelAdaptRingRegion();
        this.j = this;
        d();
        nqf.d(this.j);
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "intent is null");
            finish();
            return;
        }
        int intExtra = intent.getIntExtra("dialogType", 0);
        this.n = intExtra;
        if (intExtra == 0) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("dialogValue");
        if (stringExtra == null) {
            stringExtra = "";
        }
        a(AnalyticsValue.PB_MESSAGE_1100031.value());
        b();
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "enter initView mType=", Integer.valueOf(this.n), "value=", stringExtra);
        this.d = (LinearLayout) mfm.cgL_(this, R.id.achieve_pb_share_ll);
        this.t = stringExtra;
        this.i = (HealthTextView) mfm.cgL_(this, R.id.achieve_breakdown_mgs_tiltle);
        this.f = (HealthTextView) mfm.cgL_(this, R.id.content_value);
        this.h = (HealthTextView) mfm.cgL_(this, R.id.content_unit);
        this.l = (HealthTextView) mfm.cgM_(this.d, R.id.content_unit);
        this.c = (HealthTextView) mfm.cgL_(this, R.id.achieve_breakdown_dialog_date);
        HealthButton healthButton = (HealthButton) mfm.cgL_(this, R.id.achieve_pb_dialog_share_button);
        this.f8455a = healthButton;
        healthButton.setOnClickListener(this);
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.achieve_breakdown_dialog_title_ImageView);
        this.b = imageView;
        imageView.setOnClickListener(this);
        setViewSafeRegion(false, (RelativeLayout) mfm.cgL_(this, R.id.achieve_breakdown_dialog_view));
        this.i.setText(mlg.d(this.n, this.j));
        String b2 = mlg.b(String.valueOf(intent.getLongExtra("dialogDate", System.currentTimeMillis())));
        this.g = b2;
        this.c.setText(b2);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.f.setText(b(stringExtra));
        } else {
            this.f.setText(stringExtra);
        }
        c();
        e();
    }

    private String b(String str) {
        int i = this.n;
        if (i == 2) {
            return e(str);
        }
        return (i == 1 || i == 3 || i == 10) ? d(str) : str;
    }

    private String d(String str) {
        float f = nsn.f(str);
        return f == 0.0f ? str : UnitUtil.e(f, 1, 2);
    }

    private String e(String str) {
        int e = nsn.e(str);
        return e == 0 ? str : UnitUtil.e(e, 1, 0);
    }

    private void e() {
        int i = this.n;
        if (i == 12 || i == 13) {
            String quantityString = this.j.getResources().getQuantityString(R.plurals._2130903273_res_0x7f0300e9, nsn.e(this.t));
            this.h.setText(String.format(Locale.ENGLISH, "(%s)", quantityString));
            this.l.setText(String.format(Locale.ENGLISH, "(%s)", quantityString));
            return;
        }
        if (i == 14) {
            String string = this.j.getString(R.string._2130843710_res_0x7f02183e);
            this.h.setText(String.format(Locale.ENGLISH, "(%s)", string));
            this.l.setText(String.format(Locale.ENGLISH, "(%s)", string));
            return;
        }
        if (i == 15) {
            String string2 = this.j.getString(R.string._2130843202_res_0x7f021642);
            this.h.setText(String.format(Locale.ENGLISH, "(%s)", string2));
            this.l.setText(String.format(Locale.ENGLISH, "(%s)", string2));
        } else if (!mlg.m(i) || this.n == 2) {
            this.h.setVisibility(8);
            this.l.setVisibility(8);
        } else if (UnitUtil.h()) {
            this.h.setText(String.format(Locale.ENGLISH, "(%s)", mlg.a(this.n, this.j)));
            this.l.setText(String.format(Locale.ENGLISH, "(%s)", mlg.a(this.n, this.j)));
        } else {
            this.h.setText(String.format(Locale.ENGLISH, "(%s)", mlg.c(this.n, this.j)));
            this.l.setText(String.format(Locale.ENGLISH, "(%s)", mlg.c(this.n, this.j)));
        }
    }

    private void b() {
        ((ImageView) mfm.cgL_(this, R.id.achieve_pb_image_type)).setImageResource(mlg.b(this.n));
        ((HealthTextView) mfm.cgL_(this, R.id.achieve_pb_img_text)).setText(mlg.c(this.n));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.achieve_breakdown_dialog_title_ImageView == view.getId()) {
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "onClick: finish");
            finish();
        } else if (R.id.achieve_pb_dialog_share_button == view.getId()) {
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "onClick: share");
            if (!mcx.d(this.j)) {
                mlg.e(this.j);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            View view2 = this.q;
            if (view2 != null) {
                view2.setVisibility(mcx.e() ? 0 : 4);
            }
            if (mcv.d(this.j).getAdapter() != null) {
                this.k.setText(mcx.b());
                HashMap hashMap = new HashMap(4);
                hashMap.put("click", "1");
                if (!Utils.o()) {
                    hashMap.put("pb", String.valueOf(this.n));
                }
                Bitmap cgJ_ = mfp.cgJ_(this.d);
                if (cgJ_ != null) {
                    mcx.cfN_(this.j, cgJ_, AnalyticsValue.PB_MESSAGE_SHARE_1100032.value(), hashMap);
                    a(AnalyticsValue.PB_MESSAGE_SHARE_1100032.value());
                }
            } else {
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "adapter is null");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("type", Integer.valueOf(this.n));
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    private void c() {
        ((ImageView) mfm.cgM_(this.d, R.id.achieve_pb_image_type)).setImageResource(mlg.b(this.n));
        ((HealthTextView) mfm.cgM_(this.d, R.id.achieve_pb_img_text)).setText(mlg.c(this.n));
        ((HealthTextView) mfm.cgM_(this.d, R.id.achieve_pb_share_date)).setText(this.g);
        ((HealthTextView) mfm.cgM_(this.d, R.id.achieve_pb_share_text)).setText(this.t);
        ((HealthTextView) mfm.cgM_(this.d, R.id.achieve_breakdown_mgs_tiltle)).setText(mlg.d(this.n, this.j));
        this.e = (ImageView) mfm.cgM_(this.d, R.id.nick_img);
        this.k = (HealthTextView) mfm.cgM_(this.d, R.id.nick_name);
        this.q = mfm.cgM_(this.d, R.id.achieve_pb_share_app_name);
        j();
    }

    private void j() {
        if ("1".equals(SharedPreferenceManager.b(this.j, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "accountMigrate: isThirdLogin == 1 and return!");
            i();
        } else {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: com.huawei.pluginachievement.ui.report.AchieveReportDialog.4
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onResponse(int i, UserInfomation userInfomation) {
                    if (i != 0) {
                        AchieveReportDialog.this.m.sendEmptyMessage(1);
                        return;
                    }
                    if (userInfomation == null) {
                        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "get userInfo success but obtain null obj.");
                        return;
                    }
                    Message obtain = Message.obtain();
                    obtain.obj = userInfomation;
                    obtain.what = 0;
                    AchieveReportDialog.this.m.sendMessage(obtain);
                }
            });
        }
    }

    private void i() {
        HiHealthManager.d(this.j).fetchUserData(new HiCommonListener() { // from class: com.huawei.pluginachievement.ui.report.AchieveReportDialog.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                AchieveReportDialog achieveReportDialog = AchieveReportDialog.this;
                achieveReportDialog.o = mkx.ckg_(obj, achieveReportDialog.o, AchieveReportDialog.this.m, 4);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "fetchUserData onFailure");
            }
        });
    }

    static class b extends BaseHandler<AchieveReportDialog> {
        b(AchieveReportDialog achieveReportDialog) {
            super(achieveReportDialog);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cjS_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveReportDialog achieveReportDialog, Message message) {
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof UserInfomation) {
                    achieveReportDialog.a((UserInfomation) message.obj);
                }
            } else if (i == 1) {
                LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "MSG_GET_USER_INFO_FAIL");
            } else {
                if (i != 4) {
                    return;
                }
                achieveReportDialog.a();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "enter onDestroy");
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(UserInfomation userInfomation) {
        if (userInfomation != null) {
            String name = userInfomation.getName();
            if (name == null || name.equals("")) {
                String accountName = new UpApi(this.j).getAccountName();
                if (accountName != null) {
                    this.k.setText(accountName);
                }
            } else {
                this.k.setText(name);
            }
            String picPath = userInfomation.getPicPath();
            if (!TextUtils.isEmpty(picPath)) {
                Bitmap cIe_ = nrf.cIe_(this.j, picPath);
                if (cIe_ != null) {
                    this.e.setImageBitmap(cIe_);
                    return;
                } else {
                    d(userInfomation);
                    LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "handleWhenGetUserInfoSuccess() bitmap is null");
                    return;
                }
            }
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "handle", "WhenGetUserInfoSuccess()! headImgPath is null! ");
            this.e.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
            return;
        }
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "handleWhenGetUserInfoSuccess()! userInfo is null! ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HiUserInfo hiUserInfo = this.o;
        if (hiUserInfo == null) {
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "accountMigrate: setUserNameFromLocal mHiUserInfo == null");
            return;
        }
        this.k.setText(hiUserInfo.getName());
        String c = c(this.o.getHeadImgUrl());
        if (!TextUtils.isEmpty(c)) {
            Bitmap cIe_ = nrf.cIe_(this.j, c);
            if (cIe_ != null) {
                this.e.setImageBitmap(cIe_);
                return;
            }
            return;
        }
        this.e.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "accountMigrate: setUserNameFromLocal() headImgPath is null! ");
    }

    private void d(UserInfomation userInfomation) {
        if (userInfomation.getPortraitUrl() == null) {
            LogUtil.h("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "it's so terrible, as missing the headImage url, we can do nothing!");
        }
    }

    private String c(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] split = str.split("/");
        if (split.length > 0) {
            str2 = this.j.getFilesDir() + "/photos/headimage/" + split[split.length - 1];
        } else {
            str2 = this.j.getFilesDir() + "/photos/headimage/" + str;
        }
        if (new File(str2).exists()) {
            LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "accountMigrate: getHeadImageFromLocal file.exists() yes");
            return str2;
        }
        LogUtil.a("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "accountMigrate: getHeadImageFromLocal file.exists() no");
        File[] listFiles = new File(this.j.getFilesDir() + "/photos/headimage").listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return str2;
        }
        try {
            return listFiles[listFiles.length - 1].getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_PLGACHIEVE_AchieveReportDialog", "IOException : ");
            return str2;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
