package com.huawei.featureuserprofile.sos.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.huawei.featureuserprofile.sos.fragment.EditEmergencyInfoFragment;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.btz;
import defpackage.nsf;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class EditInfoActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private NoTitleCustomAlertDialog f2021a;
    private ArrayList<String> b = new ArrayList<>(16);
    private Context c;
    private CustomTitleBar d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(8192);
        setContentView(R.layout.edit_activity_layout);
        this.c = this;
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c(this.f2021a);
    }

    private void c(NoTitleCustomAlertDialog noTitleCustomAlertDialog) {
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        noTitleCustomAlertDialog.dismiss();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        if (getTheme() == null) {
            LogUtil.h("EditInfoActivity", "loadApplicationTheme theme is null");
            return;
        }
        int identifier = getResources().getIdentifier("HealthTheme", TemplateStyleRecord.STYLE, getPackageName());
        if (identifier == 0) {
            LogUtil.a("EditInfoActivity", "styleId is DEFAULT_THEME_ID");
        } else {
            LogUtil.a("EditInfoActivity", "styleId is ", Integer.valueOf(identifier));
            setTheme(identifier);
        }
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.edit_info_title_bar);
        this.d = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        this.d.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.d.setLeftButtonVisibility(0);
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditInfoActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e();
    }

    private void e() {
        this.b.add(BaseApplication.getContext().getString(R.string._2130848880_res_0x7f022c70));
        this.d.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditInfoActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new PopViewList(EditInfoActivity.this.c, EditInfoActivity.this.d, EditInfoActivity.this.b).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditInfoActivity.5.3
                    @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                    public void setOnClick(int i) {
                        if (i == 0) {
                            EditInfoActivity.this.b();
                        } else {
                            LogUtil.h("EditInfoActivity", "position not match");
                        }
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        Window window;
        if (btz.c(this) && (window = getWindow()) != null) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 5890);
        }
        super.onWindowFocusChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("EditInfoActivity", "showClearAllDialog ", "enter");
        if (this.f2021a == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.c);
            builder.e(R.string._2130848879_res_0x7f022c6f).czC_(R.string._2130848878_res_0x7f022c6e, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditInfoActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    EditInfoActivity.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string._2130848866_res_0x7f022c62, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditInfoActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("EditInfoActivity", "Canceling the deletion of all information");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f2021a = builder.e();
        }
        this.f2021a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        EmergencyInfoManager.c().b();
        EditEmergencyInfoFragment editEmergencyInfoFragment = (EditEmergencyInfoFragment) getFragmentManager().findFragmentById(R.id.editinfofragment);
        if (editEmergencyInfoFragment != null) {
            editEmergencyInfoFragment.e();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
