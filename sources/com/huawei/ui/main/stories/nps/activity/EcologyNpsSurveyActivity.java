package com.huawei.ui.main.stories.nps.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.interactors.HwNpsCallback;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.interactors.cache.QuestionCache;
import com.huawei.ui.main.stories.nps.interactors.mode.CommitResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.CreateCommitAnswer;
import com.huawei.ui.main.stories.nps.interactors.mode.CreateQuestionAnswer;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import com.huawei.ui.main.stories.nps.interactors.util.TaskUtils;
import com.huawei.ui.main.stories.nps.views.OnCheckedChangeListener;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class EcologyNpsSurveyActivity extends BaseActivity implements View.OnClickListener, OnCheckedChangeListener {
    private static final int DEFAULT_VALUE = -1;
    private static final String FAILED_BI = "2";
    private static final int NUMBER_ONE = 1;
    private static final String SUCCESS_BI = "1";
    private static final String TAG = "EcologyNpsSurveyActivity";
    private static final String TAG_RELEASE = "Nps_EcologyNpsSurveyActivity";
    private static final int ZERO_MARGIN = 0;
    private LinearLayout container;
    private HealthColumnSystem mColumnSystem;
    private String mCommitAnswerGsonString;
    private CommitResponse mCommitResponse;
    private Context mContext;
    private HealthButton mNextButton;
    private CustomTitleBar mTitleTextView;
    private final Type mQuestionDetailType = new TypeToken<QuestionSurveyDetailResponse>() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.1
    }.getType();
    private final Type mCommitResponseType = new TypeToken<CommitResponse>() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.2
    }.getType();
    private CustomTextAlertDialog mCommitSuccessDialog = null;
    private List<QuestionSureyResponse> mQuestions = new ArrayList(16);
    private int mViewPagerIndex = 0;
    private int mMaxPosition = -1;
    private boolean mIsPosting = false;
    private HwNpsManager mHwNpsManager = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ecology_nps_survey);
        this.mContext = this;
        this.mHwNpsManager = HwNpsManager.getInstance();
        this.mHwNpsManager.setBiNpsEvent(AnalyticsValue.NPS_OPEN_QUESTION_1090038.value(), "");
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter EcologyNpsSurveyActivity onCreate!");
        initLayout();
        List<QuestionSureyResponse> questions = QuestionCache.getQuestions();
        this.mQuestions = questions;
        if (questions.size() < 1) {
            initQuestionsCache();
        }
        if (this.mQuestions.size() < 1) {
            LogUtil.c(TAG, "nps EcologyNpsSurveyActivity mQuestions is null finish!");
            finish();
        } else {
            this.mMaxPosition = this.mQuestions.size() - 1;
            changePage(this.mViewPagerIndex);
        }
    }

    private void initLayout() {
        this.mNextButton = (HealthButton) findViewById(R.id.question_next);
        if (nsn.ag(this.mContext)) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ecology_fragment_container);
            this.container = linearLayout;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.mContext);
            this.mColumnSystem = healthColumnSystem;
            int d = ((int) healthColumnSystem.d(1)) + this.mColumnSystem.a();
            marginLayoutParams.setMargins(d, 0, d, 0);
            this.container.setLayoutParams(marginLayoutParams);
        }
        String sharedPreference = this.mHwNpsManager.getSharedPreference(HwNpsManager.KEY_ECOLOGY_DEVICE_UUID);
        dcz d2 = ResourceManager.e().d(sharedPreference);
        if (d2 == null) {
            return;
        }
        this.mNextButton.setVisibility(0);
        this.mNextButton.setOnClickListener(this);
        this.mTitleTextView = (CustomTitleBar) nsy.cMc_(this, R.id.question_main_title);
        String format = String.format(Locale.ENGLISH, this.mContext.getResources().getString(R$string.IDS_hw_ecology_survey_title), dcx.d(sharedPreference, d2.n().b()));
        this.mTitleTextView.setTitleBarBackgroundColor(this.mContext.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.mTitleTextView.setTitleText(format);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initQuestionsCache() {
        /*
            r6 = this;
            java.lang.String r0 = "Nps_EcologyNpsSurveyActivity"
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
            java.lang.String r3 = "EcologyNpsSurveyActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse r2 = new com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse
            r2.<init>()
            com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch: com.google.gson.JsonSyntaxException -> L44
            r4.<init>()     // Catch: com.google.gson.JsonSyntaxException -> L44
            java.lang.reflect.Type r5 = r6.mQuestionDetailType     // Catch: com.google.gson.JsonSyntaxException -> L44
            java.lang.Object r1 = r4.fromJson(r1, r5)     // Catch: com.google.gson.JsonSyntaxException -> L44
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse r1 = (com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse) r1     // Catch: com.google.gson.JsonSyntaxException -> L44
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: com.google.gson.JsonSyntaxException -> L43
            java.lang.String r4 = "nps initQuestionsCache detailResponse"
            r5 = 0
            r2[r5] = r4     // Catch: com.google.gson.JsonSyntaxException -> L43
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r2)     // Catch: com.google.gson.JsonSyntaxException -> L43
            goto L4e
        L43:
            r2 = r1
        L44:
            java.lang.String r1 = "nps initQuestionsCache json error"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r1)
            r1 = r2
        L4e:
            if (r1 == 0) goto L5f
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionnaireInfo r0 = r1.getSurveyContent()
            if (r0 == 0) goto L5f
            com.huawei.ui.main.stories.nps.interactors.mode.QuestionnaireInfo r0 = r1.getSurveyContent()
            java.util.List r0 = r0.getQuestions()
            goto L60
        L5f:
            r0 = 0
        L60:
            if (r0 != 0) goto L6c
            java.lang.String r0 = "nps initQuestionsCache questionList == null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.c(r3, r0)
            return
        L6c:
            java.util.List<com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse> r1 = r6.mQuestions
            r1.addAll(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.initQuestionsCache():void");
    }

    @Override // com.huawei.ui.main.stories.nps.views.OnCheckedChangeListener
    public void onClick(boolean z) {
        this.mNextButton.setEnabled(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.question_next) {
            changePage(this.mViewPagerIndex);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void changePage(int i) {
        String string;
        Fragment ecologyMultipleFragment;
        LogUtil.a(TAG, "nps Enter changePage index=", Integer.valueOf(i));
        String questionType = i == this.mMaxPosition + 1 ? "" : this.mQuestions.get(i).getQuestionType();
        int i2 = this.mMaxPosition;
        if (i < i2) {
            string = String.format(Locale.ENGLISH, getString(R$string.IDS_nps_question_survey_next), Integer.valueOf(i + 1), Integer.valueOf(this.mQuestions.size()));
            if ("option".equals(questionType)) {
                this.mNextButton.setEnabled(false);
            }
        } else {
            if (i != i2) {
                if (i == i2 + 1) {
                    commitCenterData();
                    return;
                } else {
                    LogUtil.b(TAG, "index is no right");
                    return;
                }
            }
            string = getString(R$string.IDS_nps_submit);
            if ("option".equals(questionType)) {
                this.mNextButton.setEnabled(false);
            }
        }
        if (TypeParams.QUESTION_FIELD.equals(questionType)) {
            ecologyMultipleFragment = new EcologyFiledFragment(this.mQuestions.get(i), i + 1);
        } else if ("option".equals(questionType)) {
            ecologyMultipleFragment = new EcologySingleFragment(this.mQuestions.get(i), i + 1, this);
        } else {
            ecologyMultipleFragment = new EcologyMultipleFragment(this.mQuestions.get(i), i + 1, this);
        }
        this.mNextButton.setText(string.toUpperCase(Locale.ENGLISH));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (LanguageUtil.bc(this)) {
            beginTransaction.setCustomAnimations(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f, R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        } else {
            beginTransaction.setCustomAnimations(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d, R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
        }
        beginTransaction.replace(R.id.ecology_fragment_container, ecologyMultipleFragment);
        beginTransaction.commitAllowingStateLoss();
        this.mViewPagerIndex++;
    }

    public void commitCenterData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter commitCenterData");
        if (CommonUtil.aa(this)) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps ready commit");
            initPostData();
            postData();
            return;
        }
        nrh.b(this.mContext, R$string.IDS_network_connect_error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiCommitNpsEvent(String str) {
        ReleaseLogUtil.e(TAG_RELEASE, "setBiCommitNpsEvent");
        String value = AnalyticsValue.NPS_COMMIT_QUESTION_1090039.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (str != null) {
            hashMap.put("type", str);
        }
        ixx.d().d(this.mContext, value, hashMap, 0);
    }

    private void initPostData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter initPostData");
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < this.mQuestions.size(); i++) {
            CreateQuestionAnswer createQuestionAnswer = new CreateQuestionAnswer();
            QuestionSureyResponse questionSureyResponse = this.mQuestions.get(i);
            Integer id = questionSureyResponse.getId();
            String questionType = questionSureyResponse.getQuestionType();
            createQuestionAnswer.setQuestionId(id);
            createQuestionAnswer.setQuestionType(questionType);
            if (TypeParams.QUESTION_FIELD.equals(questionType)) {
                if (Records.getStringDataCenter().get(id) != null) {
                    CreateCommitAnswer createCommitAnswer = new CreateCommitAnswer();
                    createCommitAnswer.setQuestionId(id.intValue());
                    createCommitAnswer.setAnswer(Records.getStringDataCenter().get(id));
                    arrayList.add(createCommitAnswer);
                }
            } else if ("option".equals(questionType) || "M".equals(questionType)) {
                if (Records.getOptionResult().get(id) != null) {
                    CreateCommitAnswer createCommitAnswer2 = new CreateCommitAnswer();
                    createCommitAnswer2.setQuestionId(id.intValue());
                    String str = Records.getOptionResult().get(id);
                    LogUtil.c(TAG, "nps answer to commit:", str);
                    createCommitAnswer2.setAnswer(str);
                    arrayList.add(createCommitAnswer2);
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "nps unsupport type");
            }
            createQuestionAnswer.setAnswers(arrayList);
            LogUtil.c(TAG, "initPostData questionAnswer.questionId=", createQuestionAnswer.getQuestionId());
        }
        String json = new Gson().toJson(arrayList);
        this.mCommitAnswerGsonString = json;
        LogUtil.a(TAG, "nps upload answer core data: ", json);
    }

    private void postData() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter postData");
        if (this.mIsPosting) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps mIsPosting is true,return");
            return;
        }
        this.mIsPosting = true;
        HwNpsManager hwNpsManager = this.mHwNpsManager;
        if (hwNpsManager != null) {
            hwNpsManager.submitSurveyQuestion(this.mCommitAnswerGsonString, commitCallback());
            LogUtil.a(TAG, "mCommitAnswerGsonString: ", this.mCommitAnswerGsonString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResponse(final String str) {
        LogUtil.a(TAG, "nps upload answers successful response: ", str);
        this.mIsPosting = false;
        TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.3
            @Override // android.os.AsyncTask
            protected Object doInBackground(Object... objArr) {
                Gson gson = new Gson();
                try {
                    EcologyNpsSurveyActivity ecologyNpsSurveyActivity = EcologyNpsSurveyActivity.this;
                    ecologyNpsSurveyActivity.mCommitResponse = (CommitResponse) gson.fromJson(str, ecologyNpsSurveyActivity.mCommitResponseType);
                    return null;
                } catch (JsonSyntaxException unused) {
                    ReleaseLogUtil.c(EcologyNpsSurveyActivity.TAG_RELEASE, "nps json error");
                    return null;
                }
            }

            @Override // android.os.AsyncTask
            protected void onPostExecute(Object obj) {
                super.onPostExecute(obj);
                Records.clearAllResult();
                if (EcologyNpsSurveyActivity.this.mCommitResponse != null && EcologyNpsSurveyActivity.this.mCommitResponse.getResCode() == 0) {
                    ReleaseLogUtil.e(EcologyNpsSurveyActivity.TAG_RELEASE, "nps upload answers successful");
                    EcologyNpsSurveyActivity.this.setBiCommitNpsEvent("1");
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_ECOLOGY_NPS_REQUEST, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_ECOLOGY_DEVICE_UUID, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.NPS_ECOLOGY_QUESTION_CONTENT_KEY, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_ECOLOGY_NPS_ID, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_NPS_SHOW, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_ECOLOGY_MODEL, "", null);
                    EcologyNpsSurveyActivity.this.mHwNpsManager.setSharedPreference(HwNpsManager.KEY_NPS_LAST_TIME, String.valueOf(System.currentTimeMillis()), null);
                    EcologyNpsSurveyActivity.this.showCommitSureDialog();
                    return;
                }
                LogUtil.h(EcologyNpsSurveyActivity.TAG, "nps upload answers fail");
            }
        }, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCommitSureDialog() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter showCommitSureDialog");
        if (this.mCommitSuccessDialog != null) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps mCommitSuccessDialog is showing, return");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R$string.IDS_nps_success_title).d(R$string.IDS_nps_success_message_1).cyV_(getString(R$string.IDS_settings_button_nps_ok).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EcologyNpsSurveyActivity.this.isFinishing()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.KEY_NPS_SHOW))) {
                    LogUtil.a(EcologyNpsSurveyActivity.TAG, "KEY_NPS_SHOW result = ", Integer.valueOf(HwNpsManager.getInstance().setSharedPreference(HwNpsManager.KEY_NPS_SHOW, "", null)));
                    if (!TextUtils.isEmpty(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID))) {
                        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).setMessageRead(HwNpsManager.getInstance().getSharedPreference(HwNpsManager.MESSAGE_NPS_ID));
                    }
                }
                if (EcologyNpsSurveyActivity.this.mCommitSuccessDialog != null && EcologyNpsSurveyActivity.this.mCommitSuccessDialog.isShowing()) {
                    ReleaseLogUtil.e(EcologyNpsSurveyActivity.TAG_RELEASE, "nps close mCommitSuccessDialog");
                    EcologyNpsSurveyActivity.this.mCommitSuccessDialog.cancel();
                    EcologyNpsSurveyActivity.this.mCommitSuccessDialog = null;
                }
                EcologyNpsSurveyActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.mCommitSuccessDialog = a2;
        a2.setCanceledOnTouchOutside(false);
        this.mCommitSuccessDialog.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.mCommitSuccessDialog.show();
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
        super.onDestroy();
    }

    private HwNpsCallback commitCallback() {
        return new HwNpsCallback() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyNpsSurveyActivity.5
            @Override // com.huawei.ui.main.stories.nps.interactors.HwNpsCallback
            public void onSuccess(String str) {
                LogUtil.a(EcologyNpsSurveyActivity.TAG, "nps upload answers successful response: ", str);
                if (str == null) {
                    return;
                }
                EcologyNpsSurveyActivity.this.handleResponse(str);
            }

            @Override // com.huawei.ui.main.stories.nps.interactors.HwNpsCallback
            public void onError() {
                EcologyNpsSurveyActivity.this.mIsPosting = false;
                EcologyNpsSurveyActivity.this.setBiCommitNpsEvent("2");
                LogUtil.a(EcologyNpsSurveyActivity.TAG, "nps upload answers error ");
            }
        };
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
