package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.nsf;
import defpackage.pxp;
import defpackage.ryf;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class KnitActiveHoursActivity extends KnitHealthDetailActivity {
    private final Context d = BaseApplication.e();
    private PopMenuManager e;

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_active_hours;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 34;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        return null;
    }

    public static void e(Context context) {
        e(context, System.currentTimeMillis());
    }

    public static void e(Context context, long j) {
        LogUtil.d("SCUI_KnitActiveHoursActivity", "start activity ", context, " timeMillis ", Long.valueOf(j));
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) KnitActiveHoursActivity.class);
        intent.putExtra("default_time_millis", j);
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
    }

    private void e() {
        String d = d();
        LogUtil.d("SCUI_KnitActiveHoursActivity", "fromType = ", d);
        if (TextUtils.isEmpty(d) || !"1".equals(d)) {
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put("from", 3);
        hashMap.put("event", 1);
        ixx.d().d(this.d, AnalyticsValue.HEALTH_HOME_ACTIVE_HOUR_1040092.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.ActiveHoursDayDetail;
        }
        if (i == 1) {
            return DataInfos.ActiveHoursWeekDetail;
        }
        if (i == 2) {
            return DataInfos.ActiveHoursMonthDetail;
        }
        if (i == 3) {
            return DataInfos.ActiveHoursYearDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_three_circle_card_activity_hours));
        customTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        this.e = new PopMenuManager() { // from class: com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitActiveHoursActivity.this.c();
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: pgx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitActiveHoursActivity.this.dpz_(view);
            }
        });
    }

    public /* synthetic */ void dpz_(View view) {
        this.e.showPopMenu();
        d("0");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void d(String str) {
        pxp.b(str, 2, 1);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        if (bundle == null) {
            ReleaseLogUtil.a("SCUI_KnitActiveHoursActivity", "getExtra bundle is null");
            return true;
        }
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.a("SCUI_KnitActiveHoursActivity", "getExtra intent is null bundle ", bundle);
            bundle.putLong("default_time_millis", System.currentTimeMillis());
        } else {
            bundle.putLong("default_time_millis", intent.getLongExtra("default_time_millis", System.currentTimeMillis()));
            if ("1".equals(d())) {
                bundle.putInt("from", 2);
            } else {
                bundle.putInt("from", 1);
            }
        }
        return true;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        hideEmptyFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> c() {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_temperature_warning_set), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity.4
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                LogUtil.d("SCUI_KnitActiveHoursActivity", "onItemClick set alert");
                KnitActiveHoursActivity.d("1");
                ActiveHoursSetAlertActivity.a(KnitActiveHoursActivity.this);
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>(1);
        arrayList.add(ryfVar);
        return arrayList;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void onKnitPageSelected(int i) {
        super.onKnitPageSelected(i);
        d(i);
    }

    private void d(int i) {
        String str;
        if (i == 1) {
            str = "week";
        } else if (i == 2) {
            str = "month";
        } else if (i != 3) {
            return;
        } else {
            str = "year";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", str);
        ixx.d().d(this.d, AnalyticsValue.SWITCH_TO_THE_ACTIVE_HOUR_PAGE_1040093.value(), hashMap, 0);
    }

    private String d() {
        Uri zs_ = AppRouterUtils.zs_(getIntent());
        if (zs_ == null) {
            LogUtil.c("SCUI_KnitActiveHoursActivity", "mSchemeData == null");
            return "";
        }
        return zs_.getQueryParameter(ArkUIXConstants.FROM_TYPE);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        PopMenuManager popMenuManager = this.e;
        if (popMenuManager != null) {
            popMenuManager.dismissPopMenu();
            this.e = null;
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "SCUI_KnitActiveHoursActivity";
    }
}
