package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleMonth;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
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
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AchieveMonthReportActivity extends BaseReportActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f8454a;
    private HealthTextView b;
    private LinearLayout c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private LinearLayout l;
    private HwHealthReportBarChart m;

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getContentType() {
        return 3;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getLayoutId() {
        return R.layout.activity_achieve_month_report;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected int getReportType() {
        return 0;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void findViewsById() {
        this.f8454a = (LinearLayout) mfm.cgL_(this, R.id.report_month_title);
        this.i = (HealthTextView) mfm.cgL_(this, R.id.month_text);
        this.g = (ImageView) mfm.cgL_(this, R.id.month_title_image);
        this.h = (HealthTextView) mfm.cgL_(this, R.id.month_title_text);
        this.j = (HealthTextView) mfm.cgL_(this, R.id.report_month_desc);
        HealthTextView healthTextView = (HealthTextView) mfm.cgL_(this, R.id.report_compared_exercise_total_time);
        this.d = healthTextView;
        healthTextView.setText(this.mResource.getString(R.string._2130840937_res_0x7f020d69));
        HealthTextView healthTextView2 = (HealthTextView) mfm.cgL_(this, R.id.report_weekly_distribution_title);
        this.e = healthTextView2;
        healthTextView2.setText(this.mResource.getString(R.string._2130840953_res_0x7f020d79, this.mResource.getString(R.string._2130841436_res_0x7f020f5c)));
        this.l = (LinearLayout) mfm.cgL_(this, R.id.report_weekly_distribution_percentage_view);
        this.b = (HealthTextView) mfm.cgL_(this, R.id.report_avg_compared_calorie);
        HealthTextView healthTextView3 = (HealthTextView) mfm.cgL_(this, R.id.report_weekly_kcal_distribution_title);
        this.k = healthTextView3;
        healthTextView3.setText(this.mResource.getString(R.string._2130840953_res_0x7f020d79, this.mResource.getString(R.string._2130841384_res_0x7f020f28)));
        this.m = (HwHealthReportBarChart) mfm.cgL_(this, R.id.report_weekly_kcal_distribution_barchart);
        this.f = (HealthTextView) mfm.cgL_(this, R.id.report_calorie_extension_layout_title);
        this.c = (LinearLayout) mfm.cgL_(this, R.id.hw_health_report_extension_layout);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCachedMinReportNo() {
        return mct.b(this.mContext, "_monthMinReportNo");
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCachedMaxReportNo() {
        return mct.b(this.mContext, "_monthReportNo");
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void initData() {
        long d = mkx.d(-1, System.currentTimeMillis(), 1);
        long d2 = mkx.d(-1, System.currentTimeMillis(), 2);
        this.mUsernameTextColor = b(d);
        super.initData();
        a(d, d2);
        initBreakInfo(d, getReportType());
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshMedalView(WeekAndMonthRecord weekAndMonthRecord) {
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshMedalView currentRecord is null. return!");
            initMedalView(8);
            return;
        }
        RecentMonthRecordFromDB a2 = mli.a(weekAndMonthRecord);
        if (a2 == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshMedalView monthRecord is null. return!");
            initMedalView(8);
            return;
        }
        ArrayList<String> acquireMedalId = a2.acquireMedalId();
        if (koq.b(acquireMedalId)) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshMedalView monthMedalIdList is empty. return!");
            initMedalView(8);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "refreshMedalView monthMedalIdListSize = ", Integer.valueOf(acquireMedalId.size()));
            initMedalView(0);
            setMedalData(acquireMedalId);
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getNoReportHint() {
        return this.mResource.getString(R.string._2130842616_res_0x7f0213f8);
    }

    private int b(long j) {
        int a2 = mkx.a(j);
        if (a2 != 1 && a2 != 2) {
            switch (a2) {
                case 6:
                case 7:
                case 8:
                    return this.mResource.getColor(R.color._2131296387_res_0x7f090083);
                case 9:
                case 10:
                case 11:
                    return this.mResource.getColor(R.color._2131296385_res_0x7f090081);
                case 12:
                    break;
                default:
                    return this.mResource.getColor(R.color._2131296386_res_0x7f090082);
            }
        }
        return this.mResource.getColor(R.color._2131296388_res_0x7f090084);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshExerciseTypeView(WeekAndMonthRecord weekAndMonthRecord) {
        String string;
        String str;
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshExerciseTypeView currentRecord is null.");
            return;
        }
        List<mjx> b = mlm.b(this, weekAndMonthRecord, 0);
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
        this.mReportExerciseTypeTitle.setText(getString(R.string._2130840956_res_0x7f020d7c));
        this.mReportTypePieChart.setSportCardViewData(mlm.b(this, b), b, 0);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshExerciseView(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        if (mknVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshExerciseView parameter is null!");
            return;
        }
        mknVar.cjL_(this.l);
        mknVar.b(this.b);
        mknVar.n(this.k);
        mknVar.e(this.m);
        mknVar.e(this.f);
        mknVar.cjH_(this.c);
        mli.a(context, weekAndMonthRecord, weekAndMonthRecord2, mknVar);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshAchieveReportView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, Context context, mkn mknVar) {
        mli.e(context, weekAndMonthRecord, weekAndMonthRecord2, mknVar);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void refreshBarChart(WeekAndMonthRecord weekAndMonthRecord) {
        RecentMonthRecordFromDB recentMonthRecordFromDB;
        if (weekAndMonthRecord == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshBarChart weekAndMonthRecord is null");
            this.mReportStepBarChart.setVisibility(8);
            if (this.mReportStepGoalText == null || this.mReportStepGoalText.getVisibility() == 8) {
                return;
            }
            this.mReportStepGoalText.setVisibility(8);
            return;
        }
        try {
            recentMonthRecordFromDB = (RecentMonthRecordFromDB) HiJsonUtil.e(weekAndMonthRecord.getValue(), RecentMonthRecordFromDB.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("PLGACHIEVE_AchieveMonthReportActivity", "refreshBarChart JsonSyntaxException, exception is ", e.getMessage());
            recentMonthRecordFromDB = null;
        }
        if (recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "refreshBarChart recentMonthRecordFromDb is null");
        } else {
            b(recentMonthRecordFromDB);
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected CharSequence getMedalNumbersText(int i) {
        String e = UnitUtil.e(i, 1, 0);
        return mlh.d(this.mResource.getQuantityString(R.plurals._2130903182_res_0x7f03008e, i, e), e);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getExerciseAvgCaloriesTitle() {
        return this.mResource.getString(R.string._2130841857_res_0x7f021101);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getExerciseCaloriesTitleText() {
        return this.mResource.getString(R.string._2130847439_res_0x7f0226cf);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void setBreakInfoBg(ImageView imageView) {
        mkx.ckd_(imageView, "", "/sandbox/cch5/health/health-resource/achieve/report/img_report_month_break_info_big.png", this.mResource.getDrawable(R.drawable._2131430685_res_0x7f0b0d1d));
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void startHistoricalActivityForResult() {
        LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "startHistoricalActivityForResult");
        Intent intent = new Intent(this, (Class<?>) AchieveHistoricalMonthReportActivity.class);
        intent.putExtra(BaseHistoricalReportActivity.EXTRA_KEY_REPORT_MAX_NUMBER, this.mMaxReportNo);
        intent.putExtra(BaseHistoricalReportActivity.EXTRA_KEY_REPORT_MIN_NUMBER, this.mMinReportNo);
        startActivityForResult(intent, 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "onActivityResult resultCode ", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "onActivityResult requestCode ", Integer.valueOf(i));
        if (i != 100) {
            return;
        }
        if (intent == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "onActivityResult data is null.");
            return;
        }
        long longExtra = intent.getLongExtra(BaseHistoricalReportActivity.EXTRA_KEY_START_TIMESTAMP, System.currentTimeMillis());
        LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "onActivityResult startTimestamp = ", Long.valueOf(longExtra));
        c(longExtra);
    }

    private void c(long j) {
        long d = mkx.d(0, j, 1);
        long d2 = mkx.d(0, j, 2);
        long d3 = mkx.d(-1, j, 1);
        this.mUsernameTextColor = b(d);
        if (this.mUsernameText != null) {
            this.mUsernameText.setTextColor(this.mUsernameTextColor);
        }
        a(d, d2);
        initStepData(d3, d2);
        initBreakInfo(d, getReportType());
    }

    private void b(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        List<BarEntry> a2 = mkz.a(new ArrayList(31), recentMonthRecordFromDB, this.mIsRtlLanguage);
        long acquireFirstday = recentMonthRecordFromDB.acquireFirstday();
        long acquireLastDay = recentMonthRecordFromDB.acquireLastDay();
        super.initStepBarChartSet(a2, mkx.b(acquireFirstday, acquireLastDay), 4, recentMonthRecordFromDB.acquireMaxSteps());
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getReportRecentType() {
        return String.valueOf(2);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected long getBarChartEndTime() {
        return mkx.d(-1, System.currentTimeMillis(), 2);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected long getBarChartStartTime() {
        return mkx.d(-2, System.currentTimeMillis(), 1);
    }

    private void a(long j, long j2) {
        String str = mlg.a(j, 2) + " - " + mlg.a(j2, 3);
        this.mReportIntervalDateText.setTextColor(this.mUsernameTextColor);
        this.mReportIntervalDateText.setText(str);
        this.mReportIntervalDateNoText.setTextColor(this.mUsernameTextColor);
        this.mReportIntervalDateNoText.setText(str);
        if (LanguageUtil.m(this.mContext)) {
            this.f8454a.setVisibility(0);
            this.h.setVisibility(8);
            this.g.setImageDrawable(cjM_(j));
            this.i.setTextColor(this.mUsernameTextColor);
            this.i.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/hw-italic.ttf"));
            this.i.setText(String.valueOf(mkx.a(j)) + " ");
        } else {
            this.f8454a.setVisibility(8);
            this.h.setVisibility(0);
            String str2 = mlg.a(j, 5) + " | " + this.mContext.getString(R.string._2130840702_res_0x7f020c7e) + " ";
            this.h.setTextColor(this.mUsernameTextColor);
            this.h.setText(str2);
        }
        List<SingleMonth> c = mkx.c();
        final int a2 = mkx.a(j);
        if (koq.c(c)) {
            LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "singleMonths is isNotEmpty.");
            cjO_(this.mReportHeadRootBackground, this.j, c, a2);
        } else {
            mkx.e(new AchieveCallback() { // from class: com.huawei.pluginachievement.ui.report.AchieveMonthReportActivity.4
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, final Object obj) {
                    AchieveMonthReportActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.AchieveMonthReportActivity.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AchieveMonthReportActivity.this.e(a2, obj);
                        }
                    });
                }
            });
        }
        this.j.setVisibility(0);
        this.j.setTextColor(this.mUsernameTextColor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void e(int i, T t) {
        if (this.j == null) {
            return;
        }
        if (!(t instanceof List)) {
            b(i);
            return;
        }
        List<SingleMonth> list = (List) t;
        if (koq.b(list)) {
            b(i);
        } else {
            cjO_(this.mReportHeadRootBackground, this.j, list, i);
        }
    }

    private void b(int i) {
        LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "singleMonths is initBackgroundDefault.");
        cjQ_(this.mReportHeadRootBackground, i);
        this.j.setText(mlh.d(this.mContext, i));
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getComparedStepTitleText() {
        return this.mResource.getString(R.string._2130840937_res_0x7f020d69);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getAvgDailyStepTitleText() {
        return this.mResource.getString(R.string._2130840935_res_0x7f020d67);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected void setMedalImageHeight(ImageView imageView, int i) {
        LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "setMedalImageHeight() medalLength ", Integer.valueOf(i));
        if (imageView == null) {
            LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "medalItemBackground is null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.removeRule(10);
            layoutParams2.addRule(12);
            imageView.setLayoutParams(layoutParams2);
        }
        mkx.ckd_(imageView, "", "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_more_medals_bg_low_big.png", this.mResource.getDrawable(R.drawable._2131427485_res_0x7f0b009d));
        ViewGroup.LayoutParams layoutParams3 = this.mMedalRecyclerView.getLayoutParams();
        if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
            if (i >= 4) {
                layoutParams4.bottomMargin = nsn.c(this.mContext, 160.0f);
                this.mMedalRecyclerView.setLayoutParams(layoutParams4);
            } else {
                layoutParams4.bottomMargin = nsn.c(this.mContext, 80.0f);
                this.mMedalRecyclerView.setLayoutParams(layoutParams4);
            }
        }
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getSuccessShareBiValue() {
        return AnalyticsValue.SUCCESSES_SHARE_1100013.value();
    }

    private Drawable cjM_(long j) {
        int a2 = mkx.a(j);
        if (a2 != 1 && a2 != 2) {
            switch (a2) {
                case 6:
                case 7:
                case 8:
                    return this.mResource.getDrawable(R.drawable._2131427489_res_0x7f0b00a1);
                case 9:
                case 10:
                case 11:
                    return this.mResource.getDrawable(R.drawable._2131427484_res_0x7f0b009c);
                case 12:
                    break;
                default:
                    return this.mResource.getDrawable(R.drawable._2131427487_res_0x7f0b009f);
            }
        }
        return this.mResource.getDrawable(R.drawable._2131427491_res_0x7f0b00a3);
    }

    private void cjQ_(ImageView imageView, int i) {
        if (i != 1 && i != 2) {
            switch (i) {
                case 6:
                case 7:
                case 8:
                    mkx.ckf_(imageView, "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_summer_bg.png", "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_summer_bg_big.jpg", this.mResource.getDrawable(R.drawable._2131427488_res_0x7f0b00a0));
                    break;
                case 9:
                case 10:
                case 11:
                    mkx.ckf_(imageView, "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_autumn_bg.png", "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_autumn_bg_big.jpg", this.mResource.getDrawable(R.drawable._2131427483_res_0x7f0b009b));
                    break;
                case 12:
                    break;
                default:
                    mkx.ckf_(imageView, "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_spring_bg.png", "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_spring_bg_big.jpg", this.mResource.getDrawable(R.drawable._2131427486_res_0x7f0b009e));
                    break;
            }
        }
        mkx.ckf_(imageView, "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_winter_bg.png", "/sandbox/cch5/health/health-resource/achieve/report/achieve_month_report_winter_bg_big.jpg", this.mResource.getDrawable(R.drawable._2131427490_res_0x7f0b00a2));
    }

    private void cjO_(ImageView imageView, HealthTextView healthTextView, List<SingleMonth> list, int i) {
        if (koq.b(list) || cjN_(imageView, healthTextView, list, i)) {
            return;
        }
        b(i);
    }

    private boolean cjN_(ImageView imageView, HealthTextView healthTextView, List<SingleMonth> list, int i) {
        if (koq.b(list)) {
            return false;
        }
        for (SingleMonth singleMonth : list) {
            if (i == singleMonth.getMonth()) {
                LogUtil.a("PLGACHIEVE_AchieveMonthReportActivity", "singleMonths isContainMonth month ", Integer.valueOf(i));
                if (TextUtils.isEmpty(singleMonth.getPicture()) && TextUtils.isEmpty(singleMonth.getTheme())) {
                    return false;
                }
                if (TextUtils.isEmpty(singleMonth.getPicture())) {
                    LogUtil.h("PLGACHIEVE_AchieveMonthReportActivity", "singleMonths getPicture || getTahitiPicture || getTheme isEmpty!");
                    cjQ_(this.mReportHeadRootBackground, i);
                } else {
                    cjP_(imageView, i, singleMonth);
                }
                if (TextUtils.isEmpty(singleMonth.getTheme())) {
                    healthTextView.setText(mlh.d(this.mContext, i));
                    return true;
                }
                healthTextView.setText(singleMonth.getTheme());
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void cjP_(android.widget.ImageView r4, int r5, com.huawei.health.marketing.datatype.SingleMonth r6) {
        /*
            r3 = this;
            r0 = 1
            if (r5 == r0) goto L27
            r0 = 2
            if (r5 == r0) goto L27
            switch(r5) {
                case 6: goto L1d;
                case 7: goto L1d;
                case 8: goto L1d;
                case 9: goto L13;
                case 10: goto L13;
                case 11: goto L13;
                case 12: goto L27;
                default: goto L9;
            }
        L9:
            android.content.Context r5 = r3.mContext
            r0 = 2131427486(0x7f0b009e, float:1.847659E38)
            android.graphics.drawable.Drawable r5 = androidx.core.content.ContextCompat.getDrawable(r5, r0)
            goto L30
        L13:
            android.content.Context r5 = r3.mContext
            r0 = 2131427483(0x7f0b009b, float:1.8476584E38)
            android.graphics.drawable.Drawable r5 = androidx.core.content.ContextCompat.getDrawable(r5, r0)
            goto L30
        L1d:
            android.content.Context r5 = r3.mContext
            r0 = 2131427488(0x7f0b00a0, float:1.8476594E38)
            android.graphics.drawable.Drawable r5 = androidx.core.content.ContextCompat.getDrawable(r5, r0)
            goto L30
        L27:
            android.content.Context r5 = r3.mContext
            r0 = 2131427490(0x7f0b00a2, float:1.8476598E38)
            android.graphics.drawable.Drawable r5 = androidx.core.content.ContextCompat.getDrawable(r5, r0)
        L30:
            java.lang.String r0 = r6.getTahitiPicture()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L3e
            java.lang.String r0 = r6.getPicture()
        L3e:
            java.lang.String r1 = "singleMonths isContainMonth picture "
            java.lang.String r2 = r6.getPicture()
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            java.lang.String r2 = "PLGACHIEVE_AchieveMonthReportActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.lang.String r6 = r6.getPicture()
            defpackage.mkx.cke_(r4, r6, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginachievement.ui.report.AchieveMonthReportActivity.cjP_(android.widget.ImageView, int, com.huawei.health.marketing.datatype.SingleMonth):void");
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getCustomTitleBarText() {
        return this.mResource.getString(R.string._2130840702_res_0x7f020c7e);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseReportActivity
    protected String getClassName() {
        return PersonalData.CLASS_NAME_ACHIEVE_MONTH_REPORT_ACTIVITY;
    }
}
