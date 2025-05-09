package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.reportchart.HwHealthReportBarChart;
import defpackage.koq;
import defpackage.mct;
import defpackage.mfm;
import defpackage.mjx;
import defpackage.mkn;
import defpackage.mkx;
import defpackage.mkz;
import defpackage.mlg;
import defpackage.mlh;
import defpackage.mli;
import defpackage.mlm;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AchieveWeekReportActivity extends BaseReportActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8457a;
    private HealthTextView b;
    private HwHealthReportBarChart e;

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getContentType() {
        return 2;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getLayoutId() {
        return R.layout.activity_achieve_week_report;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getReportType() {
        return 1;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCachedMinReportNo() {
        return mct.b(this.mContext, "_weekMinReportNo");
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCachedMaxReportNo() {
        return mct.b(this.mContext, "_weekReportNo");
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void initData() {
        this.mUsernameTextColor = this.mResource.getColor(R.color._2131296430_res_0x7f0900ae);
        super.initData();
        long d = mkx.d(1, 1, System.currentTimeMillis(), Utils.o());
        a(d, mkx.d(2, 1, System.currentTimeMillis(), Utils.o()));
        initBreakInfo(d, getReportType());
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshMedalView(WeekAndMonthRecord weekAndMonthRecord) {
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshMedalView currentRecord is null. return!");
            initMedalView(8);
            return;
        }
        RecentWeekRecordFromDB c = mli.c(weekAndMonthRecord);
        if (c == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshViewFromDb weekRecord is null. return!");
            initMedalView(8);
            return;
        }
        ArrayList<String> acquireMedalId = c.acquireMedalId();
        if (koq.b(acquireMedalId)) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshViewFromDb weekMedalIdList is empty. return!");
            initMedalView(8);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveWeekReportActivity", "refreshViewFromDb weekMedalIdListSize = ", Integer.valueOf(acquireMedalId.size()));
            initMedalView(0);
            setMedalData(acquireMedalId);
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getNoReportHint() {
        return this.mResource.getString(R.string._2130842615_res_0x7f0213f7);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshExerciseTypeView(WeekAndMonthRecord weekAndMonthRecord) {
        String string;
        String str;
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshExerciseTypeView currentRecord is null.");
            return;
        }
        List<mjx> b = mlm.b(this, weekAndMonthRecord, 1);
        int size = b.size();
        if (size <= 0) {
            this.mExerciseTypeItemLayout.setVisibility(8);
        } else {
            this.mExerciseTypeItemLayout.setVisibility(0);
        }
        if (size <= 1) {
            string = getString(R.string._2130840958_res_0x7f020d7e);
            str = "乐在其中";
        } else if (size <= 3) {
            string = getString(R.string._2130840959_res_0x7f020d7f);
            str = "不止一面";
        } else {
            string = getString(R.string._2130840960_res_0x7f020d80);
            str = "多种快乐";
        }
        if (LanguageUtil.m(this)) {
            this.mReportExerciseTypeDec.setText(mlh.d(string, str));
        } else {
            this.mReportExerciseTypeDec.setText(string);
        }
        this.mReportExerciseTypeTitle.setText(getString(R.string._2130840957_res_0x7f020d7d));
        this.mReportTypePieChart.setSportCardViewData(mlm.b(this, b), b, 1);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshExerciseView(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        if (mknVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshExerciseView parameter is null.");
        } else {
            mknVar.c(this.e);
            mli.d(context, weekAndMonthRecord, weekAndMonthRecord2, mknVar);
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshAchieveReportView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, Context context, mkn mknVar) {
        mli.c(context, weekAndMonthRecord, weekAndMonthRecord2, mknVar);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshBarChart(WeekAndMonthRecord weekAndMonthRecord) {
        RecentWeekRecordFromDB recentWeekRecordFromDB;
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "refreshBarChart weekAndMonthRecord is null");
            if (this.mReportStepBarChart != null) {
                this.mReportStepBarChart.setVisibility(8);
                return;
            }
            return;
        }
        try {
            recentWeekRecordFromDB = (RecentWeekRecordFromDB) HiJsonUtil.e(weekAndMonthRecord.getValue(), RecentWeekRecordFromDB.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("PLGACHIEVE_AchieveWeekReportActivity", "refreshBarChart JsonSyntaxException, e is ", e.getMessage());
            recentWeekRecordFromDB = null;
        }
        if (recentWeekRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "Report_refreshWeekBarChart recentWeekRecordFromDb is null");
        } else {
            d(recentWeekRecordFromDB);
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected CharSequence getMedalNumbersText(int i) {
        String e = UnitUtil.e(i, 1, 0);
        return mlh.d(this.mResource.getQuantityString(R.plurals._2130903183_res_0x7f03008f, i, e), e);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getExerciseAvgCaloriesTitle() {
        return this.mResource.getString(R.string._2130840955_res_0x7f020d7b);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getExerciseCaloriesTitleText() {
        return this.mResource.getString(R.string._2130847439_res_0x7f0226cf);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void startHistoricalActivityForResult() {
        LogUtil.a("PLGACHIEVE_AchieveWeekReportActivity", "startHistoricalActivityForResult");
        Intent intent = new Intent(this, (Class<?>) AchieveHistoricalWeekReportActivity.class);
        intent.putExtra(BaseHistoricalReportActivity.EXTRA_KEY_REPORT_MAX_NUMBER, this.mMaxReportNo);
        intent.putExtra(BaseHistoricalReportActivity.EXTRA_KEY_REPORT_MIN_NUMBER, this.mMinReportNo);
        startActivityForResult(intent, 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "onActivityResult resultCode = ", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveWeekReportActivity", "onActivityResult requestCode = ", Integer.valueOf(i));
        if (i != 100) {
            return;
        }
        if (intent == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "onActivityResult data is null.");
            return;
        }
        long longExtra = intent.getLongExtra(BaseHistoricalReportActivity.EXTRA_KEY_START_TIMESTAMP, System.currentTimeMillis());
        long longExtra2 = intent.getLongExtra(BaseHistoricalReportActivity.EXTRA_KEY_END_TIMESTAMP, System.currentTimeMillis());
        LogUtil.a("PLGACHIEVE_AchieveWeekReportActivity", "onActivityResult startTimestamp = ", Long.valueOf(longExtra), ", endTimestamp = ", Long.valueOf(longExtra2));
        e(longExtra, longExtra2);
    }

    private void e(long j, long j2) {
        long d = mkx.d(1, 0, j, Utils.o());
        long d2 = mkx.d(2, 0, j2, Utils.o());
        long d3 = mkx.d(1, 1, j, Utils.o());
        a(d, d2);
        initStepData(d3, d2);
        initBreakInfo(d, getReportType());
    }

    private void d(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        super.initStepBarChartSet(mkz.b(new ArrayList(8), recentWeekRecordFromDB, this.mIsRtlLanguage), mkx.d(recentWeekRecordFromDB.acquireMonday(), Utils.o()), 10, recentWeekRecordFromDB.acquireMaxSteps());
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getReportRecentType() {
        return String.valueOf(1);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected long getBarChartEndTime() {
        return mkx.b(2, 1, Utils.o());
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected long getBarChartStartTime() {
        return mkx.b(1, 2, Utils.o());
    }

    private void a(long j, long j2) {
        String str = mlg.a(j, 2) + " - " + mlg.a(j2, 3);
        this.mReportIntervalDateText.setTextColor(this.mUsernameTextColor);
        this.mReportIntervalDateText.setText(str);
        this.mReportIntervalDateNoText.setTextColor(this.mUsernameTextColor);
        this.mReportIntervalDateNoText.setText(str);
        if (LanguageUtil.m(this.mContext)) {
            this.b.setVisibility(8);
            this.f8457a.setVisibility(0);
        } else {
            this.f8457a.setVisibility(8);
            this.b.setVisibility(0);
            this.b.setText(this.mResource.getString(R.string._2130840701_res_0x7f020c7d));
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getComparedStepTitleText() {
        return this.mResource.getString(R.string._2130840911_res_0x7f020d4f);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getAvgDailyStepTitleText() {
        return this.mResource.getString(R.string._2130840936_res_0x7f020d68);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void setBreakInfoBg(ImageView imageView) {
        mkx.ckd_(imageView, "", "/sandbox/cch5/health/health-resource/achieve/report/img_report_week_break_info_big.png", this.mResource.getDrawable(R.drawable._2131430687_res_0x7f0b0d1f));
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void findViewsById() {
        this.f8457a = (ImageView) mfm.cgL_(this, R.id.report_week_title_img);
        this.b = (HealthTextView) mfm.cgL_(this, R.id.week_title_text);
        mkx.ckf_(this.mReportHeadRootBackground, "/sandbox/cch5/health/health-resource/achieve/report/img_weekly_report_bg.jpg", "/sandbox/cch5/health/health-resource/achieve/report/img_weekly_report_bg_big.jpg", this.mResource.getDrawable(R.drawable._2131430706_res_0x7f0b0d32));
        this.e = (HwHealthReportBarChart) mfm.cgL_(this, R.id.bar_chart_exercise_time);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void setMedalImageHeight(ImageView imageView, int i) {
        LogUtil.a("PLGACHIEVE_AchieveWeekReportActivity", "setMedalImageHeight() medalLength = ", Integer.valueOf(i));
        if (imageView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.removeRule(12);
            layoutParams2.addRule(10);
            layoutParams2.addRule(21);
            imageView.setLayoutParams(layoutParams2);
        }
        mkx.ckf_(imageView, "", "/sandbox/cch5/health/health-resource/achieve/report/achieve_week_report_more_medals_bg_low_big.png", this.mResource.getDrawable(R.drawable._2131427495_res_0x7f0b00a7));
        a();
    }

    private void a() {
        int h;
        if (this.mMedalDescTextView == null) {
            LogUtil.h("PLGACHIEVE_AchieveWeekReportActivity", "setWeekMedalDescLayout() medalDescView is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.mMedalDescTextView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (nsn.ag(this.mContext)) {
            h = nsn.c(this.mContext, 50.0f);
        } else {
            h = (((nsn.h(this.mContext) + this.mResource.getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)) + this.mResource.getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d)) + this.mResource.getDimensionPixelSize(R.dimen._2131362361_res_0x7f0a0239)) / 3;
        }
        if (layoutParams2 == null) {
            return;
        }
        if (LanguageUtil.bc(this.mContext)) {
            layoutParams2.leftMargin = h;
        } else {
            layoutParams2.rightMargin = h;
        }
        this.mMedalDescTextView.setLayoutParams(layoutParams2);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        a();
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getSuccessShareBiValue() {
        return AnalyticsValue.SUCCESSES_SHARE_1100012.value();
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCustomTitleBarText() {
        return this.mResource.getString(R.string._2130840701_res_0x7f020c7d);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getClassName() {
        return PersonalData.CLASS_NAME_ACHIEVE_WEEK_REPORT_ACTIVITY;
    }
}
