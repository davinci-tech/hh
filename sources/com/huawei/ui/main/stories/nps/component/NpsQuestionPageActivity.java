package com.huawei.ui.main.stories.nps.component;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.component.MultiLayoutAdapter;
import com.huawei.ui.main.stories.nps.component.QuestionContent;
import com.huawei.ui.main.stories.nps.https.HttpResCallback;
import com.huawei.ui.main.stories.nps.https.HttpUtils;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyCommitParam;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyRequestParam;
import com.huawei.ui.main.stories.nps.npsstate.NativeConfigBean;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class NpsQuestionPageActivity extends BaseActivity implements MultiLayoutAdapter.NpsBarProgressListener {
    private static final int HTTPS_OK = 200;
    private static final int ONE_BATCHS = 1;
    private static final String PROGRESS_DEFAULT_VALUE = "-1";
    private static final String RESULT_CODE = "resCode";
    private static final String STATUS_TRUE = "true";
    private static final String TAG = "NpsQuestionPageActivity";
    private static final int THREE_BATCHS = 3;
    private static final int TWO_BATCHS = 2;
    private MultiLayoutAdapter mAdapter;
    private HealthButton mCommitButton;
    private Context mContext;
    private ListView mListView;
    private NoTitleCustomAlertDialog mNoTitleDialog;
    private NpsUserShowController mShowController;
    private CustomTitleBar mTitleBar;
    private List<NpsQuestion> mList = new ArrayList();
    private int mSurveyTime = 0;
    private String mSurveyId = "";
    private Handler mHandler = new Handler();
    private boolean mIsCommitClicked = false;
    private Runnable mDialogRunnable = new Runnable() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.1
        @Override // java.lang.Runnable
        public void run() {
            LogUtil.d(NpsQuestionPageActivity.TAG, "setBIAnalytics:COMMIT_SUCCESS");
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            ixx.d().d(NpsQuestionPageActivity.this.mContext, AnalyticsValue.HEALTH_HOME_NPS_COMMIT_SUCCESS_2010096.value(), hashMap, 0);
            NpsQuestionPageActivity.this.showCommitOkDialog();
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nps_question_page);
        this.mContext = this;
        NpsUserShowController npsUserShowController = NpsUserShowController.getInstance(BaseApplication.getContext());
        this.mShowController = npsUserShowController;
        int surveyTime = npsUserShowController.getSurveyTime();
        this.mSurveyTime = surveyTime;
        LogUtil.d(TAG, "NpsQuestionPageActivity onCreate() mSurveyTime: ", Integer.valueOf(surveyTime));
        initQueryData(getTheSurveyContent(this.mSurveyTime));
        this.mListView = (ListView) findViewById(R.id.nps_question_lv);
        MultiLayoutAdapter multiLayoutAdapter = new MultiLayoutAdapter(this, this.mList, this);
        this.mAdapter = multiLayoutAdapter;
        this.mListView.setAdapter((ListAdapter) multiLayoutAdapter);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.nps_survey_title);
        this.mTitleBar = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d(NpsQuestionPageActivity.TAG, "click back left top");
                NpsQuestionPageActivity.this.setBiAnalytics();
                NpsQuestionPageActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthButton healthButton = (HealthButton) findViewById(R.id.nps_question_next_bt);
        this.mCommitButton = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NpsQuestionPageActivity.this.commit();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        openHealthNpsBiAnalytics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commit() {
        MultiLayoutAdapter multiLayoutAdapter = this.mAdapter;
        List<NpsQuestion> npsQuestionList = multiLayoutAdapter != null ? multiLayoutAdapter.getNpsQuestionList() : null;
        if (koq.b(npsQuestionList)) {
            Context context = this.mContext;
            nrh.d(context, context.getString(R$string.nps_user_survey_input_score_toast));
            return;
        }
        LogUtil.d(TAG, "npsAdd commitContent: ", npsQuestionList.toString());
        if (!isFinishAllRequest(npsQuestionList)) {
            Context context2 = this.mContext;
            nrh.d(context2, context2.getString(R$string.nps_user_survey_input_score_toast));
            return;
        }
        int i = this.mSurveyTime;
        if (i == 0) {
            return;
        }
        this.mShowController.setTheSurveyUnNeeded(i);
        if (CommonUtil.aa(BaseApplication.getContext())) {
            if (this.mIsCommitClicked) {
                return;
            }
            this.mIsCommitClicked = true;
            this.mCommitButton.setTextColor(getResources().getColor(R.color._2131296928_res_0x7f0902a0));
            this.mCommitButton.setEnabled(false);
            commitSurvey(this.mShowController.getHuidStr(), this.mSurveyTime, npsQuestionList);
            return;
        }
        showTheAlertDialog();
    }

    private boolean isFinishAllRequest(List<NpsQuestion> list) {
        if (koq.b(list)) {
            LogUtil.c(TAG, "isFinishAllRequest npsQuestionList isEmpty!");
            return false;
        }
        for (NpsQuestion npsQuestion : list) {
            if ("true".equals(npsQuestion.getRequired()) && "-1".equals(npsQuestion.getAnswer())) {
                return false;
            }
        }
        return true;
    }

    private void openHealthNpsBiAnalytics() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_NPS_ENTER_2010095.value(), hashMap, 0);
    }

    private void showTheAlertDialog() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).czC_(R$string.nps_user_survey_net_setting, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NpsQuestionPageActivity.this.mNoTitleDialog != null) {
                    NpsQuestionPageActivity.this.mNoTitleDialog.dismiss();
                }
                CommonUtil.q(NpsQuestionPageActivity.this.mContext);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R$string.nps_user_survey_grade_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NpsQuestionPageActivity.this.mNoTitleDialog != null) {
                    NpsQuestionPageActivity.this.mNoTitleDialog.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e(R$string.nps_user_survey_no_net).e();
        this.mNoTitleDialog = e;
        e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.mNoTitleDialog.show();
    }

    private String getTheSurveyContent(int i) {
        String accountInfo = LoginInit.getInstance(this).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.c(TAG, "getTheSurveyContent huidStr isEmpty.");
            return "";
        }
        if (i == 1 || i == 2) {
            NativeConfigBean nativeConfigBean = this.mShowController.getNewUserConfigInfo(accountInfo).get(Integer.valueOf(i));
            if (nativeConfigBean == null) {
                return "";
            }
            String questionContent = nativeConfigBean.getQuestionContent();
            LogUtil.d(TAG, "getTheSurveyContent new user content: ", questionContent);
            return questionContent;
        }
        NpsConfig readNpsConfig = this.mShowController.readNpsConfig();
        if (readNpsConfig == null || i != 3) {
            return "";
        }
        NativeConfigBean oldUserConfigInfo = this.mShowController.getOldUserConfigInfo(readNpsConfig.getVersion() + accountInfo);
        if (oldUserConfigInfo == null) {
            return "";
        }
        String questionContent2 = oldUserConfigInfo.getQuestionContent();
        LogUtil.d(TAG, "getTheSurveyContent old user content: ", questionContent2);
        return questionContent2;
    }

    private void initQueryData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        QuestionContent objectFromData = QuestionContent.objectFromData(str);
        this.mSurveyId = objectFromData.getSurveyId();
        List<QuestionContent.SurveyContentBean.QuestionsBean> questions = objectFromData.getSurveyContent().getQuestions();
        this.mList.clear();
        for (int i = 0; i < questions.size(); i++) {
            QuestionContent.SurveyContentBean.QuestionsBean questionsBean = questions.get(i);
            NpsQuestion npsQuestion = new NpsQuestion(questionsBean.getType(), questionsBean.getSubTitle(), questionsBean.getQuestion(), questionsBean.getPictureUrl());
            npsQuestion.setId(questionsBean.getId());
            if (TextUtils.isEmpty(questionsBean.getRequired())) {
                npsQuestion.setRequired("true");
            } else {
                npsQuestion.setRequired(questionsBean.getRequired());
            }
            this.mList.add(npsQuestion);
        }
    }

    public void commitSurvey(String str, int i, List<NpsQuestion> list) {
        HashMap<String, String> params = getParams(str, i);
        String url = GRSManager.a(this.mContext).getUrl("domainCcpceConsumerHuawei");
        if (TextUtils.isEmpty(url)) {
            LogUtil.e(TAG, "commitSurvey host is empty");
            url = "http:/";
        }
        String str2 = url + NpsConstantValue.COMMIT_NPS_URL;
        LogUtil.d(TAG, "commitSurvey url string: ..CCPC/EN/ccpcnps/submitSurveyV2/1");
        params.put(QuestionSurveyCommitParam.ANSWERS, getTheAnswerString(list));
        HttpUtils.postReq(this, str2, params, new HashMap(), new HttpResCallback() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.6
            /* JADX WARN: Removed duplicated region for block: B:11:0x0041  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0093 A[Catch: JSONException -> 0x00a3, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00a3, blocks: (B:4:0x000f, B:13:0x0046, B:16:0x0071, B:18:0x0093, B:20:0x002a, B:23:0x0034), top: B:3:0x000f }] */
            @Override // com.huawei.ui.main.stories.nps.https.HttpResCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onFinished(int r5, java.lang.String r6) {
                /*
                    r4 = this;
                    java.lang.String r0 = "commitSurvey() result: "
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r6}
                    java.lang.String r1 = "NpsQuestionPageActivity"
                    health.compact.a.util.LogUtil.d(r1, r0)
                    r0 = 200(0xc8, float:2.8E-43)
                    if (r5 != r0) goto Lb1
                    org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> La3
                    r5.<init>(r6)     // Catch: org.json.JSONException -> La3
                    java.lang.String r6 = "resCode"
                    java.lang.String r5 = r5.getString(r6)     // Catch: org.json.JSONException -> La3
                    int r6 = r5.hashCode()     // Catch: org.json.JSONException -> La3
                    r0 = 48
                    r2 = 0
                    r3 = 1
                    if (r6 == r0) goto L34
                    r0 = 1448635041(0x56586aa1, float:5.948812E13)
                    if (r6 == r0) goto L2a
                    goto L3e
                L2a:
                    java.lang.String r6 = "100002"
                    boolean r5 = r5.equals(r6)     // Catch: org.json.JSONException -> La3
                    if (r5 == 0) goto L3e
                    r5 = r3
                    goto L3f
                L34:
                    java.lang.String r6 = "0"
                    boolean r5 = r5.equals(r6)     // Catch: org.json.JSONException -> La3
                    if (r5 == 0) goto L3e
                    r5 = r2
                    goto L3f
                L3e:
                    r5 = -1
                L3f:
                    if (r5 == 0) goto L93
                    r6 = 2131296927(0x7f09029f, float:1.8211784E38)
                    if (r5 == r3) goto L71
                    java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: org.json.JSONException -> La3
                    java.lang.String r0 = "commitSurvey() result: default"
                    r5[r2] = r0     // Catch: org.json.JSONException -> La3
                    health.compact.a.util.LogUtil.d(r1, r5)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$702(r5, r2)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.commonui.button.HealthButton r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$800(r5)     // Catch: org.json.JSONException -> La3
                    r5.setEnabled(r3)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.commonui.button.HealthButton r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$800(r5)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r0 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    android.content.res.Resources r0 = r0.getResources()     // Catch: org.json.JSONException -> La3
                    int r6 = r0.getColor(r6)     // Catch: org.json.JSONException -> La3
                    r5.setTextColor(r6)     // Catch: org.json.JSONException -> La3
                    goto Lb1
                L71:
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$702(r5, r2)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.commonui.button.HealthButton r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$800(r5)     // Catch: org.json.JSONException -> La3
                    r5.setEnabled(r3)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.commonui.button.HealthButton r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$800(r5)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r0 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    android.content.res.Resources r0 = r0.getResources()     // Catch: org.json.JSONException -> La3
                    int r6 = r0.getColor(r6)     // Catch: org.json.JSONException -> La3
                    r5.setTextColor(r6)     // Catch: org.json.JSONException -> La3
                    goto Lb1
                L93:
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    android.os.Handler r5 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$600(r5)     // Catch: org.json.JSONException -> La3
                    com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity r6 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.this     // Catch: org.json.JSONException -> La3
                    java.lang.Runnable r6 = com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.access$500(r6)     // Catch: org.json.JSONException -> La3
                    r5.post(r6)     // Catch: org.json.JSONException -> La3
                    goto Lb1
                La3:
                    r5 = move-exception
                    java.lang.String r6 = "commitSurvey JsonSyntaxException, e is "
                    java.lang.String r5 = r5.getMessage()
                    java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
                    health.compact.a.util.LogUtil.e(r1, r5)
                Lb1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.AnonymousClass6.onFinished(int, java.lang.String):void");
            }
        });
    }

    private HashMap<String, String> getParams(String str, int i) {
        HashMap<String, String> hashMap = new HashMap<>(10);
        hashMap.put("sn", str);
        hashMap.put("appID", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY);
        hashMap.put("times", String.valueOf(i));
        hashMap.put(QuestionSurveyCommitParam.SURVEY_ID, this.mSurveyId);
        hashMap.put(FaqConstants.FAQ_EMUIVERSION, NpsCommonUtil.getEmuiVerion());
        hashMap.put("countryCode", NpsCommonUtil.getCountryCode());
        hashMap.put("firmware", NpsCommonUtil.getFirmRomVersion());
        hashMap.put(QuestionSurveyRequestParam.CVERISON, NpsCommonUtil.getClientVersionCode());
        hashMap.put("model", NpsCommonUtil.getInternalModel());
        hashMap.put("os", NpsCommonUtil.getAndroidVersion());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCommitOkDialog() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).czC_(R$string.nps_user_survey_grade_sure, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.component.NpsQuestionPageActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NpsQuestionPageActivity.this.mNoTitleDialog != null) {
                    NpsQuestionPageActivity.this.mNoTitleDialog.dismiss();
                }
                NpsQuestionPageActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e(R$string.nps_user_survey_commit_ok).e();
        this.mNoTitleDialog = e;
        e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.mNoTitleDialog.show();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            LogUtil.d(TAG, "key back）");
            setBiAnalytics();
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiAnalytics() {
        LogUtil.d(TAG, "setBiAnalytics:COMMIT_FAIL");
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_NPS_COMMIT_FAIL_2010097.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.nps.component.MultiLayoutAdapter.NpsBarProgressListener
    public void getProgress(int i, int i2) {
        LogUtil.d(TAG, Integer.valueOf(i), "--progress--", Integer.valueOf(i2), "--position");
    }

    private String getTheAnswerString(List<NpsQuestion> list) {
        if (koq.b(list)) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        for (NpsQuestion npsQuestion : list) {
            String answer = npsQuestion.getAnswer();
            if ("-1".equals(answer)) {
                answer = "";
            }
            arrayList.add(gson.toJson(new AnswerEntity(nsn.e(npsQuestion.getId()), answer)));
        }
        String obj = arrayList.toString();
        LogUtil.d(TAG, "npsAdd getTheAnswerString result: ", obj);
        return obj;
    }

    static class AnswerEntity {

        @SerializedName("answer")
        String mAnswer;

        @SerializedName("questionId")
        int mQuestionId;

        AnswerEntity(int i, String str) {
            this.mQuestionId = i;
            this.mAnswer = str;
        }

        public int getQuestionId() {
            return this.mQuestionId;
        }

        public void setQuestionId(int i) {
            this.mQuestionId = i;
        }

        public String getAnswer() {
            return this.mAnswer;
        }

        public void setAnswer(String str) {
            this.mAnswer = str;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
