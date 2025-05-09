package com.huawei.ui.main.stories.nps.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import defpackage.nsy;

/* loaded from: classes7.dex */
public class EcologyFiledFragment extends BaseFragment {
    private static final int DEFAULT_NUMBER = 0;
    private static final String HIND_TEXT = "/100";
    private static final String TAG = "EcologyFiledFragment";
    private EditText mContent;
    private HealthTextView mEditTipView;
    private String mFiledData;
    private int mNumber;
    private int mQuestionId;
    private QuestionSureyResponse mQuestionSurveyResponse;
    private HealthSubHeader mQuestionTextView;
    private TextWatcher watcher = new TextWatcher() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyFiledFragment.1
        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence)) {
                EcologyFiledFragment.this.mEditTipView.setText("0/100");
                return;
            }
            EcologyFiledFragment.this.mFiledData = charSequence.toString();
            if (Records.queryInt(Integer.valueOf(EcologyFiledFragment.this.mQuestionId))) {
                Records.getStringDataCenter().remove(Integer.valueOf(EcologyFiledFragment.this.mQuestionId));
                Records.getStringDataCenter().put(Integer.valueOf(EcologyFiledFragment.this.mQuestionId), EcologyFiledFragment.this.mFiledData);
            } else {
                Records.getStringDataCenter().put(Integer.valueOf(EcologyFiledFragment.this.mQuestionId), EcologyFiledFragment.this.mFiledData);
            }
            int length = EcologyFiledFragment.this.mFiledData.length();
            EcologyFiledFragment.this.mEditTipView.setText(length + EcologyFiledFragment.HIND_TEXT);
            LogUtil.a(EcologyFiledFragment.TAG, "finish Filed question successful: ", Records.getStringDataCenter());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.a(EcologyFiledFragment.TAG, "beforeTextChanged");
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            LogUtil.a(EcologyFiledFragment.TAG, "afterTextChanged");
        }
    };

    public EcologyFiledFragment() {
    }

    public EcologyFiledFragment(QuestionSureyResponse questionSureyResponse, int i) {
        this.mQuestionSurveyResponse = questionSureyResponse;
        this.mNumber = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ecology_nps_filed, viewGroup, false);
        if (this.mQuestionSurveyResponse == null) {
            return inflate;
        }
        this.mQuestionTextView = (HealthSubHeader) nsy.cMd_(inflate, R.id.ecology_file_question);
        this.mQuestionTextView.setHeadTitleText(this.mNumber + "." + this.mQuestionSurveyResponse.getQuestion());
        this.mQuestionTextView.setSubHeaderBackgroundColor(getActivity().getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.mContent = (EditText) nsy.cMd_(inflate, R.id.field_content_et);
        this.mEditTipView = (HealthTextView) nsy.cMd_(inflate, R.id.text_num_tip);
        this.mContent.addTextChangedListener(this.watcher);
        this.mQuestionId = this.mQuestionSurveyResponse.getId().intValue();
        this.mFiledData = Records.getStringDataCenter().get(Integer.valueOf(this.mQuestionId));
        Records.getStringDataCenter().put(Integer.valueOf(this.mQuestionId), "");
        return inflate;
    }
}
