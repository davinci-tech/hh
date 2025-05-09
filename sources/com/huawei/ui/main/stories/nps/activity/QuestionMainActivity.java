package com.huawei.ui.main.stories.nps.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.AndroidBugWorkaround;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import com.huawei.ui.main.stories.nps.interactors.HwNpsCallback;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.interactors.cache.QuestionCache;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyTable;
import com.huawei.ui.main.stories.nps.interactors.mode.CommitResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.CreateCommitAnswer;
import com.huawei.ui.main.stories.nps.interactors.mode.CreateQuestionAnswer;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import com.huawei.ui.main.stories.nps.interactors.util.DeviceNpsDelayUtils;
import com.huawei.ui.main.stories.nps.interactors.util.TaskUtils;
import com.huawei.ui.main.stories.nps.views.NpsBackAlertDialog;
import defpackage.cvz;
import defpackage.ixx;
import defpackage.jcx;
import defpackage.jpt;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class QuestionMainActivity extends BaseActivity implements View.OnClickListener {
    private static final String CLICK_BACK_BI = "5";
    private static final String CLICK_COMMIT_BI = "3";
    private static final String CLICK_NEXT_BI = "4";
    private static final String CLICK_PART_COMMIT_BI = "6";
    private static final int DEFAULT_VALUE = -1;
    private static final String FAILED_BI = "2";
    private static final String FROM = "from";
    private static final String HOME = "home";
    private static final String NOTIFY = "notify";
    private static final String SUCCESS_BI = "1";
    private static final String TAG = "QuestionMainActivity";
    private static final String TAG_RELEASE = "Nps_QuestionMainActivity";
    private static final int ZERO_MARGIN = 0;
    private String mActivityFrom;
    private ClickHomeReceiver mClickHomeReceiver;
    private String mCommitAnswerGsonString;
    private CommitResponse mCommitResponse;
    private Context mContext;
    private FiledFragment mLastFragment;
    private LinearLayout mLayoutViewPager;
    private HealthButton mNextButton;
    private ScreenSlidePagerAdapter mPagerAdapter;
    private QuestionSurveyTable mQuestionSurveyTable;
    private String mQuestionType;
    private CustomTitleBar mTitleTextView;
    private HealthViewPager mViewPager;
    private boolean mIsSelected = false;
    private Type mQuestionDetailType = new TypeToken<QuestionSurveyDetailResponse>() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.1
    }.getType();
    private CustomTextAlertDialog mCommitSuccessDialog = null;
    private List<QuestionSureyResponse> mQuestions = new ArrayList(16);
    private int mViewPagerIndex = 0;
    private int mMaxPosition = -1;
    private Type mCommitResponseType = new TypeToken<CommitResponse>() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.2
    }.getType();
    private boolean mIsPosting = false;
    private HwNpsManager mHwNpsManager = null;
    private boolean mIsComplete = false;
    private HwNpsCallback mCommitCallback = new HwNpsCallback() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.8
        @Override // com.huawei.ui.main.stories.nps.interactors.HwNpsCallback
        public void onSuccess(final String str) {
            LogUtil.a(QuestionMainActivity.TAG, "nps upload answers successful response: ", str);
            QuestionMainActivity.this.mIsPosting = false;
            QuestionMainActivity.this.mHwNpsManager.setIsGoSubmitSurveyState(false);
            if (str == null) {
                return;
            }
            TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.8.1
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    Gson gson = new Gson();
                    try {
                        QuestionMainActivity.this.mCommitResponse = (CommitResponse) gson.fromJson(str, QuestionMainActivity.this.mCommitResponseType);
                        return null;
                    } catch (JsonSyntaxException unused) {
                        ReleaseLogUtil.c(QuestionMainActivity.TAG_RELEASE, "nps json error");
                        return null;
                    }
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                    Records.clearAllResult();
                    if (QuestionMainActivity.this.mCommitResponse != null && QuestionMainActivity.this.mCommitResponse.getResCode() == 0) {
                        ReleaseLogUtil.e(QuestionMainActivity.TAG_RELEASE, "nps upload answers successful");
                        QuestionMainActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_WEAR_MODEL, "", null);
                        QuestionMainActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_WEAR_NAME, "", null);
                        QuestionMainActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_WEAR_SN, "", null);
                        QuestionMainActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_WEAR_FIRMWARE, "", null);
                        QuestionMainActivity.this.mHwNpsManager.cancelNotify();
                        QuestionMainActivity.this.setBiCommitNpsEvent("1");
                        QuestionMainActivity.this.updateQuestionSurveyTable();
                        DeviceNpsDelayUtils.updateNpsCommitStatus(true);
                        QuestionMainActivity.this.showCommitSureDialog();
                        return;
                    }
                    LogUtil.h(QuestionMainActivity.TAG, "nps upload answers fail");
                }
            }, new Object[0]);
        }

        @Override // com.huawei.ui.main.stories.nps.interactors.HwNpsCallback
        public void onError() {
            QuestionMainActivity.this.mIsPosting = false;
            QuestionMainActivity.this.mHwNpsManager.setIsGoSubmitSurveyState(false);
            QuestionMainActivity.this.setBiCommitNpsEvent("2");
            LogUtil.a(QuestionMainActivity.TAG, "nps upload answers error");
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().clearFlags(AppRouterExtras.COLDSTART);
        setContentView(R.layout.activity_nps_question_main);
        if (!nsn.aa(BaseApplication.getContext())) {
            AndroidBugWorkaround.assistActivity(this);
        }
        this.mContext = this;
        this.mHwNpsManager = HwNpsManager.getInstance();
        if (bundle != null) {
            this.mViewPagerIndex = bundle.getInt("mViewPagerIndex") - 1;
        }
        handlerIntent();
        String value = AnalyticsValue.NPS_OPEN_QUESTION_1090038.value();
        HwNpsManager hwNpsManager = this.mHwNpsManager;
        String str = this.mActivityFrom;
        String str2 = NOTIFY;
        if (!NOTIFY.equals(str)) {
            str2 = HOME;
        }
        hwNpsManager.setBiNpsEvent(value, str2);
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter QuestionMainActivity onCreate!");
        LogUtil.c(TAG, "nps Enter QuestionMainActivity onCreate init!");
        initView();
        List<QuestionSureyResponse> questions = QuestionCache.getQuestions();
        this.mQuestions = questions;
        if (questions.size() < 1) {
            initQuestionsCache();
        }
        if (this.mQuestions.size() < 1) {
            ReleaseLogUtil.e(TAG, "nps QuestionMainActivity mQuestions is null finish!");
            finish();
            return;
        }
        if (this.mViewPagerIndex == 0) {
            cleanData();
        }
        registerHomeReceiver();
        this.mMaxPosition = this.mQuestions.size() - 1;
        this.mPagerAdapter.notifyDataSetChanged();
        changePage(this.mViewPagerIndex);
        updateQuestionSurveyTable();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        if (nsn.aa(BaseApplication.getContext())) {
            return;
        }
        AndroidBugWorkaround.assistActivity(this);
    }

    private void cleanData() {
        if (HagridNpsManager.getInstance().isWeightDeviceNps() || !CommonUtil.bh()) {
            return;
        }
        for (int i = 0; i < this.mQuestions.size(); i++) {
            Integer id = this.mQuestions.get(i).getId();
            Records.getStringDataCenter().remove(id);
            Records.getOptionResult().remove(id);
        }
    }

    private void registerHomeReceiver() {
        this.mClickHomeReceiver = new ClickHomeReceiver();
        BroadcastManagerUtil.bFC_(this.mContext, this.mClickHomeReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), LocalBroadcast.c, null);
    }

    private void initView() {
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.layout_viewpager);
        this.mLayoutViewPager = linearLayout;
        setOnGlobalLayoutListener(linearLayout);
        initLayoutViewPager();
        this.mViewPager = (HealthViewPager) nsy.cMc_(this, R.id.pager);
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        this.mPagerAdapter = screenSlidePagerAdapter;
        this.mViewPager.setAdapter(screenSlidePagerAdapter);
        this.mViewPager.setIsScroll(false);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.question_next);
        this.mNextButton = healthButton;
        healthButton.setVisibility(0);
        this.mNextButton.setOnClickListener(this);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.question_main_title);
        this.mTitleTextView = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(this.mContext.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        setMainTitle();
        this.mTitleTextView.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(QuestionMainActivity.TAG, "mTitleTextView onClick mViewPagerIndex:", Integer.valueOf(QuestionMainActivity.this.mViewPagerIndex));
                QuestionMainActivity.this.showBackCommitDialog();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBackCommitDialog() {
        boolean z = !HwNpsManager.getInstance().isRecycleQuestionNps(jcx.k());
        if (HagridNpsManager.getInstance().isWeightDeviceNps() || z) {
            ReleaseLogUtil.d(TAG, "showBackCommitDialog isWeightDeviceNps:", Boolean.valueOf(HagridNpsManager.getInstance().isWeightDeviceNps()), ", isHuaweiSystem:", Boolean.valueOf(CommonUtil.bh()), ", isHideDevice:", Boolean.valueOf(z));
            setBiCommitNpsEvent("5");
            finish();
            return;
        }
        initPostData();
        if (TextUtils.isEmpty(this.mCommitAnswerGsonString)) {
            LogUtil.h(TAG, "showBackCommitDialog mCommitAnswerGsonString isEmpty");
            setBiCommitNpsEvent("5");
            finish();
        } else {
            String string = getResources().getString(R$string.IDS_device_nps_dialog_title);
            String string2 = getResources().getString(R$string.IDS_device_nps_dialog_commit);
            new NpsBackAlertDialog.Builder(this.mContext).setTitle(string).setCommitButton(string2, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(QuestionMainActivity.TAG, "showBackCommitDialog click commitButton");
                    QuestionMainActivity questionMainActivity = QuestionMainActivity.this;
                    questionMainActivity.setBiCommitNpsEvent(questionMainActivity.mIsComplete ? "3" : "6");
                    QuestionMainActivity.this.postData();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).setContinueButton(getResources().getString(R$string.IDS_device_nps_dialog_continue), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(QuestionMainActivity.TAG, "showBackCommitDialog click continueButton");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).setExitButton(getResources().getString(R$string.IDS_device_nps_dialog_exit), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(QuestionMainActivity.TAG, "showBackCommitDialog click exitButton");
                    QuestionMainActivity.this.setBiCommitNpsEvent("5");
                    QuestionMainActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).create().show();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a(TAG, "onBackPressed mViewPagerIndex:", Integer.valueOf(this.mViewPagerIndex));
        showBackCommitDialog();
    }

    /* loaded from: classes7.dex */
    class ClickHomeReceiver extends BroadcastReceiver {
        static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
        static final String SYSTEM_DIALOG_REASON_KEY = "reason";

        private ClickHomeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) && SYSTEM_DIALOG_REASON_HOME_KEY.equals(intent.getStringExtra("reason"))) {
                LogUtil.a(QuestionMainActivity.TAG, "ClickHomeReceiver homekey");
                QuestionMainActivity.this.setBiCommitNpsEvent("5");
            }
        }
    }

    private void setOnGlobalLayoutListener(final LinearLayout linearLayout) {
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.7
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                linearLayout.setMinimumHeight(linearLayout.getHeight());
            }
        });
    }

    private void initLayoutViewPager() {
        ViewGroup.LayoutParams layoutParams = this.mLayoutViewPager.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMargins(0, getResources().getDimensionPixelOffset(R.dimen._2131363799_res_0x7f0a07d7), 0, 0);
            this.mLayoutViewPager.setLayoutParams(marginLayoutParams);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initQuestionsCache() {
        /*
            r6 = this;
            java.lang.String r0 = "Nps_QuestionMainActivity"
            com.huawei.ui.main.stories.nps.interactors.HwNpsManager r1 = r6.mHwNpsManager
            if (r1 != 0) goto L7
            return
        L7:
            java.util.List<com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse> r1 = r6.mQuestions
            if (r1 != 0) goto L14
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 16
            r1.<init>(r2)
            r6.mQuestions = r1
        L14:
            com.huawei.ui.main.stories.nps.interactors.HwNpsManager r1 = r6.mHwNpsManager
            java.lang.String r1 = r1.getQuestionDetail()
            java.lang.String r2 = "nps initQuestionsCache getQuestionDetail response: "
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r1}
            java.lang.String r3 = "QuestionMainActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse r2 = new com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse
            r2.<init>()
            com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch: com.google.gson.JsonSyntaxException -> L46
            r4.<init>()     // Catch: com.google.gson.JsonSyntaxException -> L46
            java.lang.reflect.Type r5 = r6.mQuestionDetailType     // Catch: com.google.gson.JsonSyntaxException -> L46
            java.lang.Object r1 = r4.fromJson(r1, r5)     // Catch: com.google.gson.JsonSyntaxException -> L46
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse r1 = (com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse) r1     // Catch: com.google.gson.JsonSyntaxException -> L46
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: com.google.gson.JsonSyntaxException -> L45
            java.lang.String r4 = "nps initQuestionsCache detailResponse"
            r5 = 0
            r2[r5] = r4     // Catch: com.google.gson.JsonSyntaxException -> L45
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r2)     // Catch: com.google.gson.JsonSyntaxException -> L45
            goto L51
        L45:
            r2 = r1
        L46:
            java.lang.String r1 = "nps initQuestionsCache json error"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r1)
            r1 = r2
        L51:
            if (r1 == 0) goto L62
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionnaireInfo r0 = r1.getSurveyContent()
            if (r0 == 0) goto L62
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionnaireInfo r0 = r1.getSurveyContent()
            java.util.List r0 = r0.getQuestions()
            goto L63
        L62:
            r0 = 0
        L63:
            if (r0 != 0) goto L70
            java.lang.String r0 = "nps initQuestionsCache questionList == null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.c(r3, r0)
            return
        L70:
            java.util.Iterator r0 = r0.iterator()
        L74:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L86
            java.lang.Object r1 = r0.next()
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse r1 = (com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse) r1
            java.util.List<com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse> r2 = r6.mQuestions
            r2.add(r1)
            goto L74
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.initQuestionsCache():void");
    }

    private void handlerIntent() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter handlerIntent");
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "handlerIntent intent== null");
            return;
        }
        String stringExtra = intent.getStringExtra("from");
        this.mActivityFrom = stringExtra;
        if (NOTIFY.equals(stringExtra)) {
            this.mHwNpsManager.cancelNotify();
        }
        QuestionSurveyTable questionSurveyTable = this.mHwNpsManager.getQuestionSurveyTable();
        this.mQuestionSurveyTable = questionSurveyTable;
        if (questionSurveyTable != null) {
            LogUtil.a(TAG, "nps mQuestionSurveyTable:", questionSurveyTable.toString());
        }
    }

    /* loaded from: classes7.dex */
    class ScreenSlidePagerAdapter extends HealthFragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
        public Fragment getItem(int i) {
            QuestionMainActivity questionMainActivity = QuestionMainActivity.this;
            questionMainActivity.mQuestionType = ((QuestionSureyResponse) questionMainActivity.mQuestions.get(i)).getQuestionType();
            if (!TypeParams.QUESTION_FIELD.equals(QuestionMainActivity.this.mQuestionType)) {
                if ("option".equals(QuestionMainActivity.this.mQuestionType)) {
                    SingleFragment singleFragment = new SingleFragment((QuestionSureyResponse) QuestionMainActivity.this.mQuestions.get(i), i + 1);
                    singleFragment.setRetainInstance(true);
                    return singleFragment;
                }
                ReleaseLogUtil.d(QuestionMainActivity.TAG_RELEASE, "getItem no this questionType");
                SingleFragment singleFragment2 = new SingleFragment((QuestionSureyResponse) QuestionMainActivity.this.mQuestions.get(i), i + 1);
                singleFragment2.setRetainInstance(true);
                return singleFragment2;
            }
            QuestionMainActivity.this.mLastFragment = new FiledFragment((QuestionSureyResponse) QuestionMainActivity.this.mQuestions.get(i), i + 1);
            QuestionMainActivity.this.mLastFragment.setRetainInstance(true);
            return QuestionMainActivity.this.mLastFragment;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return QuestionMainActivity.this.mQuestions.size();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.question_next) {
            if (this.mViewPagerIndex <= this.mMaxPosition && !this.mIsSelected && HagridNpsManager.getInstance().isWeightDeviceNps()) {
                nrh.b(this.mContext, R$string.nps_user_survey_input_score_toast);
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                setBiCommitNpsEvent("4");
                changePage(this.mViewPagerIndex);
                this.mIsSelected = false;
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void putQuestionAnswer(Map<String, Object> map) {
        int i = this.mViewPagerIndex - 1;
        if (i <= this.mMaxPosition) {
            QuestionSureyResponse questionSureyResponse = this.mQuestions.get(i);
            String questionType = questionSureyResponse.getQuestionType();
            Integer id = questionSureyResponse.getId();
            String str = (!"option".equals(questionType) || Records.getOptionResult().get(id) == null) ? "" : Records.getOptionResult().get(id);
            if (TypeParams.QUESTION_FIELD.equals(questionType) && Records.getStringDataCenter().get(id) != null) {
                str = Records.getStringDataCenter().get(id);
            }
            map.put(String.valueOf(id), str);
        }
    }

    public void changePage(int i) {
        String string;
        LogUtil.a(TAG, "nps Enter changePage index=", Integer.valueOf(i));
        this.mViewPager.setCurrentItem(i, true);
        this.mNextButton.setEnabled(false);
        int i2 = this.mMaxPosition;
        if (i < i2) {
            string = String.format(Locale.ROOT, getString(R$string.IDS_nps_question_next), UnitUtil.e(i + 1, 1, 0), UnitUtil.e(this.mQuestions.size(), 1, 0));
        } else if (i == i2) {
            string = getString(R$string.IDS_nps_submit);
            if (TypeParams.QUESTION_FIELD.equals(this.mQuestionType)) {
                this.mNextButton.setEnabled(true);
            }
        } else if (i == i2 + 1) {
            commitCenterData();
            string = getString(R$string.IDS_nps_submit);
        } else {
            string = getString(R$string.IDS_nps_submit);
            commitCenterData();
        }
        this.mNextButton.setText(string);
        List<QuestionSureyResponse> list = this.mQuestions;
        if (list != null && i < list.size() && TypeParams.QUESTION_FIELD.equals(this.mQuestions.get(i).getQuestionType())) {
            this.mNextButton.setEnabled(true);
        }
        hideSoftInputWindow();
        this.mViewPagerIndex++;
    }

    private void setMainTitle() {
        String string;
        if (HagridNpsManager.getInstance().isWeightDeviceNps()) {
            string = getResources().getString(R$string.IDS_hw_nps_bodyfat_device_main_title);
        } else if (cvz.c(jpt.d(TAG))) {
            LogUtil.a(TAG, "isHonorDevice");
            string = getResources().getString(R$string.IDS_hw_nps_honor_main_title);
        } else {
            string = getResources().getString(R$string.IDS_hw_nps_identify_main_title);
        }
        this.mTitleTextView.setTitleText(string);
    }

    private void hideSoftInputWindow() {
        Object systemService = getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            View peekDecorView = getWindow().peekDecorView();
            if (peekDecorView != null) {
                inputMethodManager.hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
            }
        }
    }

    public void commitCenterData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter commitCenterData");
        if (CommonUtil.aa(this)) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps ready commit");
            setBiCommitNpsEvent("3");
            initPostData();
            postData();
            return;
        }
        nrh.b(this.mContext, R$string.IDS_network_connect_error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiCommitNpsEvent(String str) {
        QuestionSurveyDetailResponse detailResponse;
        ReleaseLogUtil.e(TAG_RELEASE, "setBiCommitNpsEvent");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (str != null) {
            hashMap.put("type", str);
        }
        if ("4".equals(str)) {
            hashMap.put("page", String.valueOf(this.mViewPagerIndex));
            putQuestionAnswer(hashMap);
        }
        if ("5".equals(str)) {
            hashMap.put("page", String.valueOf(this.mViewPagerIndex));
        }
        if (("1".equals(str) || "2".equals(str) || "5".equals(str)) && (detailResponse = this.mHwNpsManager.getDetailResponse()) != null) {
            hashMap.put("queryTime", Integer.valueOf(detailResponse.getQueryTimes()));
            hashMap.put("questionType", this.mHwNpsManager.getQuestionType(detailResponse.getSurveyID()));
        }
        hashMap.put("showDay", Integer.valueOf(this.mHwNpsManager.getShowDay()));
        String str2 = this.mActivityFrom;
        String str3 = NOTIFY;
        if (!NOTIFY.equals(str2)) {
            str3 = HOME;
        }
        hashMap.put("from", str3);
        hashMap.put("deviceType", jcx.f());
        hashMap.put("deviceMac", jcx.j());
        LogUtil.a(TAG, "setBiCommitNpsEvent map:", hashMap);
        ixx.d().d(this.mContext, AnalyticsValue.NPS_COMMIT_QUESTION_1090039.value(), hashMap, 0);
    }

    private void initPostData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter initPostData");
        this.mIsComplete = false;
        ArrayList arrayList = new ArrayList(16);
        int i = this.mViewPagerIndex;
        if (i > this.mQuestions.size()) {
            i = this.mQuestions.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            CreateQuestionAnswer createQuestionAnswer = new CreateQuestionAnswer();
            QuestionSureyResponse questionSureyResponse = this.mQuestions.get(i2);
            Integer id = questionSureyResponse.getId();
            String questionType = questionSureyResponse.getQuestionType();
            createQuestionAnswer.setQuestionId(id);
            createQuestionAnswer.setQuestionType(questionType);
            if (TypeParams.QUESTION_FIELD.equals(questionType)) {
                if (Records.getStringDataCenter().get(id) != null) {
                    CreateCommitAnswer createCommitAnswer = new CreateCommitAnswer();
                    createCommitAnswer.setQuestionId(id.intValue());
                    String str = Records.getStringDataCenter().get(id);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(Build.BRAND).append(":").append(Build.MODEL).append("@//").append(str);
                    createCommitAnswer.setAnswer(stringBuffer.toString());
                    arrayList.add(createCommitAnswer);
                    setIsComplete(i2);
                }
            } else if ("option".equals(questionType)) {
                if (Records.getOptionResult().get(id) != null) {
                    CreateCommitAnswer createCommitAnswer2 = new CreateCommitAnswer();
                    createCommitAnswer2.setQuestionId(id.intValue());
                    String str2 = Records.getOptionResult().get(id);
                    LogUtil.c(TAG, "nps answer to commit:", str2);
                    createCommitAnswer2.setAnswer(str2);
                    arrayList.add(createCommitAnswer2);
                    setIsComplete(i2);
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "nps unsupport type");
            }
            createQuestionAnswer.setAnswers(arrayList);
            LogUtil.c(TAG, "initPostData questionAnswer.questionId=", createQuestionAnswer.getQuestionId());
        }
        toAnswerJson(arrayList);
    }

    private void toAnswerJson(List<CreateCommitAnswer> list) {
        this.mCommitAnswerGsonString = new Gson().toJson(list);
        if (list.isEmpty()) {
            LogUtil.a(TAG, "initPostData commitAnswerList isEmpty");
            this.mCommitAnswerGsonString = "";
        }
        LogUtil.a(TAG, "nps upload answer core data: ", this.mCommitAnswerGsonString);
    }

    private void setIsComplete(int i) {
        if (i == this.mQuestions.size() - 1) {
            this.mIsComplete = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter postData");
        if (this.mIsPosting) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps mIsPosting is true,return");
            return;
        }
        this.mIsPosting = true;
        HwNpsManager hwNpsManager = this.mHwNpsManager;
        if (hwNpsManager != null) {
            hwNpsManager.submitSurveyQuestion(this.mCommitAnswerGsonString, this.mCommitCallback);
            LogUtil.a(TAG, "mCommitAnswerGsonString: ", this.mCommitAnswerGsonString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCommitSureDialog() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter showCommitSureDialog");
        if (this.mCommitSuccessDialog != null) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps mCommitSuccessDialog is showing, return");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).d(R$string.IDS_nps_success_message_1).cyV_(getString(R$string.IDS_settings_button_nps_ok).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.QuestionMainActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (QuestionMainActivity.this.isFinishing()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.KEY_NPS_SHOW))) {
                    LogUtil.a(QuestionMainActivity.TAG, "KEY_NPS_SHOW result = ", Integer.valueOf(HwNpsManager.getInstance().setSharedPreference(HwNpsManager.KEY_NPS_SHOW, "")));
                    if (!TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID))) {
                        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).setMessageRead(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID));
                    }
                }
                if (QuestionMainActivity.this.mCommitSuccessDialog != null && QuestionMainActivity.this.mCommitSuccessDialog.isShowing()) {
                    ReleaseLogUtil.e(QuestionMainActivity.TAG_RELEASE, "nps close mCommitSuccessDialog");
                    QuestionMainActivity.this.mCommitSuccessDialog.cancel();
                    QuestionMainActivity.this.mCommitSuccessDialog = null;
                }
                QuestionMainActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.mCommitSuccessDialog = a2;
        a2.setCanceledOnTouchOutside(false);
        if (isFinishing()) {
            return;
        }
        this.mCommitSuccessDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateQuestionSurveyTable() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter updateQuestionSurveyTable");
        QuestionSurveyTable questionSurveyTable = this.mQuestionSurveyTable;
        if (questionSurveyTable == null) {
            return;
        }
        questionSurveyTable.setLastSurveyTime(new Date().getTime());
        HwNpsManager hwNpsManager = this.mHwNpsManager;
        if (hwNpsManager != null) {
            hwNpsManager.updateQuestionSurveyTable(this.mQuestionSurveyTable);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ClickHomeReceiver clickHomeReceiver;
        super.onDestroy();
        HealthViewPager healthViewPager = this.mViewPager;
        if (healthViewPager != null) {
            healthViewPager.setAdapter(null);
            this.mViewPager = null;
        }
        Context context = this.mContext;
        if (context == null || (clickHomeReceiver = this.mClickHomeReceiver) == null) {
            return;
        }
        try {
            context.unregisterReceiver(clickHomeReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "unregisterReceiver IllegalArgumentException");
        }
        this.mClickHomeReceiver = null;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mViewPagerIndex", this.mViewPagerIndex);
    }

    public void setIsSelect(boolean z) {
        this.mIsSelected = z;
    }

    public void setButtonClickable() {
        this.mNextButton.setEnabled(true);
    }
}
