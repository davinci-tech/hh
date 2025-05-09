package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider;
import com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import defpackage.edr;
import defpackage.koq;
import defpackage.met;
import defpackage.nhj;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.phc;
import defpackage.pxp;
import defpackage.ryf;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class KnitActiveRecordActivity extends KnitHealthDetailActivity {

    /* renamed from: a, reason: collision with root package name */
    private GoalSettingDialog f9724a;

    public static /* synthetic */ void dpA_(DialogInterface dialogInterface) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 37;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        return null;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        return null;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_privacy_activity_records));
        customTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
        customTitleBar.setRightButtonVisibility(0);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                pxp.b("0", 1, -1);
                pxp.d(1, 0);
                return KnitActiveRecordActivity.this.e();
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: pgz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitActiveRecordActivity.dpB_(PopMenuManager.this, view);
            }
        });
        if (LanguageUtil.bc(this)) {
            customTitleBar.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R$string.accessibility_share));
        } else {
            customTitleBar.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
        }
        customTitleBar.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    KnitActiveRecordActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    public static /* synthetic */ void dpB_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (getSingleFragment() == null || getSingleFragment().getAdapter() == null) {
            LogUtil.c("SCUI_KnitActiveRecordActivity", "mSingleFragment or adapter is null");
            return;
        }
        List<SectionBean> b = getSingleFragment().getAdapter().b();
        if (koq.b(b)) {
            LogUtil.c("SCUI_KnitActiveRecordActivity", "sectionList is null or empty");
            return;
        }
        for (int i = 0; i < b.size(); i++) {
            SectionBean sectionBean = b.get(i);
            if (sectionBean.c() instanceof ActiveRecordCalendarProvider) {
                Object e = sectionBean.e();
                if (e instanceof edr) {
                    edr edrVar = (edr) e;
                    ReleaseLogUtil.e("SCUI_KnitActiveRecordActivity", "share activeRecordData is ", edrVar.b());
                    new phc(edrVar).c(0);
                    return;
                }
            }
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
        if (nsn.cLh_(this) || Utils.o() || !LanguageUtil.h(this)) {
            return;
        }
        if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
            ReleaseLogUtil.e("SCUI_KnitActiveRecordActivity", "is syncing...");
            return;
        }
        if (ThreeCircleConfigUtil.c()) {
            LogUtil.d("SCUI_KnitActiveRecordActivity", "configExtraView hasShow true");
            return;
        }
        GoalSettingDialog goalSettingDialog = new GoalSettingDialog(this);
        this.f9724a = goalSettingDialog;
        goalSettingDialog.setCancelable(false);
        this.f9724a.show();
        this.f9724a.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: phd
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                KnitActiveRecordActivity.dpA_(dialogInterface);
            }
        });
        ThreeCircleConfigUtil.b();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        GoalSettingDialog goalSettingDialog = this.f9724a;
        if (goalSettingDialog != null) {
            goalSettingDialog.dismiss();
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        Intent intent = getIntent();
        if (intent == null) {
            return true;
        }
        int intExtra = intent.getIntExtra("current_total_step", 0);
        int intExtra2 = intent.getIntExtra("current_total_intensity", 0);
        int intExtra3 = intent.getIntExtra("current_total_active", 0);
        int intExtra4 = intent.getIntExtra("current_total_distance", 0);
        int intExtra5 = intent.getIntExtra("current_total_calorie", 0);
        int intExtra6 = intent.getIntExtra("current_total_floor", 0);
        int intExtra7 = intent.getIntExtra("cur_step_goal", 10000);
        int intExtra8 = intent.getIntExtra("cur_intensity_goal", 25);
        int intExtra9 = intent.getIntExtra("cur_active_goal", 12);
        int intExtra10 = intent.getIntExtra("cur_calorie_goal", 270000);
        boolean booleanExtra = intent.getBooleanExtra("is_open_calendar", false);
        bundle.putInt("card_id", intent.getIntExtra("card_id", 0));
        bundle.putInt("current_total_step", intExtra);
        bundle.putInt("current_total_intensity", intExtra2);
        bundle.putInt("current_total_active", intExtra3);
        bundle.putInt("current_total_distance", intExtra4);
        bundle.putInt("current_total_calorie", intExtra5);
        bundle.putInt("current_total_floor", intExtra6);
        bundle.putInt("cur_step_goal", intExtra7);
        bundle.putInt("cur_calorie_goal", intExtra10);
        bundle.putInt("cur_intensity_goal", intExtra8);
        bundle.putInt("cur_active_goal", intExtra9);
        bundle.putBoolean("is_open_calendar", booleanExtra);
        Pair<Long, String> cuW_ = nhj.cuW_();
        if (cuW_ == null || !nhj.c((String) cuW_.second, "activityRings")) {
            return true;
        }
        bundle.putLong(BasePageResTrigger.KEY_DELAY_TIME_MILLIS, 2000L);
        return true;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        hideEmptyFragment();
        ReleaseLogUtil.e("SCUI_KnitActiveRecordActivity", "onResume");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ThreeCircleConfigUtil.b(false);
        ReleaseLogUtil.e("SCUI_KnitActiveRecordActivity", "onPause");
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setDetailBackGround(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
        met.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> e() {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_setting_active_target), new IPopMenuItemClick() { // from class: pgy
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public final void onItemClick() {
                KnitActiveRecordActivity.this.b();
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_reminder_setting), new IPopMenuItemClick() { // from class: phb
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public final void onItemClick() {
                KnitActiveRecordActivity.this.d();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>(2);
        arrayList.add(ryfVar);
        arrayList.add(ryfVar2);
        return arrayList;
    }

    public /* synthetic */ void b() {
        LogUtil.d("SCUI_KnitActiveRecordActivity", "onItemClick set alert");
        ActiveTargetActivity.b(this, 1);
    }

    public /* synthetic */ void d() {
        LogUtil.d("SCUI_KnitActiveRecordActivity", "onItemClick reminder setting");
        pxp.b("1", 1, -1);
        startActivity(new Intent(this, (Class<?>) ReminderSettingActivity.class));
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        nrh.e();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "SCUI_KnitActiveRecordActivity";
    }
}
