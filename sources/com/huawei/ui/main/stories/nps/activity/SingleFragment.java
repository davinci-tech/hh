package com.huawei.ui.main.stories.nps.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyChooseResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import com.huawei.ui.main.stories.nps.interactors.util.ParamsUtils;
import com.huawei.ui.main.stories.nps.views.NpsAdapter;
import defpackage.nsy;
import defpackage.qjb;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class SingleFragment extends BaseFragment {
    private static final int ICON_ASTERISK_WIDTH = 7;
    private static final String TAG = "SingleFragment";
    private NpsAdapter mAdapter;
    private Handler mHandler;
    private ListView mListView;
    private int mNumber;
    private String[] mQuestionAnswers;
    private List<QuestionSurveyChooseResponse> mQuestionChooseResponses;
    private QuestionSureyResponse mQuestionSureyResponse;
    private HashMap<String, Boolean> mStates;
    private HealthTextView mTitle;

    public SingleFragment() {
        this(null, 0);
    }

    public SingleFragment(QuestionSureyResponse questionSureyResponse, int i) {
        this.mStates = new HashMap<>();
        this.mHandler = new Handler() { // from class: com.huawei.ui.main.stories.nps.activity.SingleFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                SingleFragment.this.mListView.setItemChecked(message.what, true);
                SingleFragment.this.saveData();
                super.handleMessage(message);
            }
        };
        this.mQuestionSureyResponse = questionSureyResponse;
        this.mNumber = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_nps_question_single, viewGroup, false);
        if (this.mQuestionSureyResponse != null && (getActivity() instanceof QuestionMainActivity)) {
            this.mQuestionChooseResponses = this.mQuestionSureyResponse.getChoose();
            this.mListView = (ListView) nsy.cMd_(inflate, R.id.single_listview);
            this.mStates.clear();
            List<QuestionSurveyChooseResponse> list = this.mQuestionChooseResponses;
            if (list != null) {
                this.mQuestionAnswers = new String[list.size()];
                for (int i = 0; i < this.mQuestionChooseResponses.size(); i++) {
                    if (this.mQuestionChooseResponses.get(i).getRemark() != null) {
                        this.mQuestionAnswers[i] = this.mQuestionChooseResponses.get(i).getName() + Constants.LEFT_BRACKET_ONLY + this.mQuestionChooseResponses.get(i).getRemark() + Constants.RIGHT_BRACKET_ONLY;
                    } else {
                        this.mQuestionAnswers[i] = this.mQuestionChooseResponses.get(i).getName();
                    }
                    checkDeviceType(i);
                }
            }
            NpsAdapter npsAdapter = new NpsAdapter(getContext(), this.mQuestionAnswers, this.mStates, this.mHandler, (QuestionMainActivity) getActivity());
            this.mAdapter = npsAdapter;
            npsAdapter.setSurveyChoose(this.mQuestionSureyResponse);
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
            this.mListView.setChoiceMode(1);
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.SingleFragment.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                    QuestionMainActivity questionMainActivity = (QuestionMainActivity) SingleFragment.this.getActivity();
                    questionMainActivity.setIsSelect(true);
                    questionMainActivity.setButtonClickable();
                    for (int i3 = 0; i3 < SingleFragment.this.mQuestionChooseResponses.size(); i3++) {
                        if (i2 == i3) {
                            SingleFragment.this.mStates.put(i3 + "", true);
                        } else {
                            SingleFragment.this.mStates.put(i3 + "", false);
                        }
                    }
                    SingleFragment.this.mAdapter.notifyDataSetChanged();
                    SingleFragment.this.saveData();
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                }
            });
            this.mTitle = (HealthTextView) nsy.cMd_(inflate, R.id.single_title);
            setTitleText();
        }
        return inflate;
    }

    private void setTitleText() {
        String str = this.mNumber + ". " + this.mQuestionSureyResponse.getQuestion() + ParamsUtils.getQuestionTypeParams(this.mQuestionSureyResponse.getQuestionType());
        if (!this.mQuestionSureyResponse.isRequired()) {
            this.mTitle.setText(str);
            return;
        }
        String str2 = str + " ";
        SpannableString spannableString = new SpannableString(str2);
        Drawable drawable = getResources().getDrawable(R.drawable._2131430590_res_0x7f0b0cbe);
        int dipToPix = dipToPix(7.0f);
        drawable.setBounds(0, 0, dipToPix, dipToPix);
        spannableString.setSpan(new qjb(drawable, 0, 0), str2.length() - 1, str2.length(), 1);
        this.mTitle.setText(spannableString);
    }

    private int dipToPix(float f) {
        return (int) ((f * BaseApplication.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void checkDeviceType(int i) {
        this.mStates.put(i + "", false);
    }

    public void saveData() {
        LogUtil.a(TAG, "Enter saveData");
        int checkedItemPosition = this.mListView.getCheckedItemPosition();
        List<QuestionSurveyChooseResponse> list = this.mQuestionChooseResponses;
        if (list == null || list.size() < checkedItemPosition + 1) {
            LogUtil.h(TAG, "saveData error1!");
            return;
        }
        if (this.mQuestionSureyResponse == null) {
            LogUtil.h(TAG, "saveData error2!");
            return;
        }
        if (this.mQuestionChooseResponses.get(checkedItemPosition).isFeedbackFlag()) {
            String str = Records.getOptionResult().get(this.mQuestionSureyResponse.getId());
            LogUtil.a(TAG, "saveData inputDatas:", str);
            if (TextUtils.isEmpty(str)) {
                Records.getOptionResult().put(this.mQuestionSureyResponse.getId(), this.mQuestionChooseResponses.get(checkedItemPosition).getName());
            }
        } else {
            Records.getOptionResult().put(this.mQuestionSureyResponse.getId(), this.mQuestionChooseResponses.get(checkedItemPosition).getName());
        }
        LogUtil.a(TAG, "finish single successful getOptionResult:", Records.getOptionResult());
    }
}
