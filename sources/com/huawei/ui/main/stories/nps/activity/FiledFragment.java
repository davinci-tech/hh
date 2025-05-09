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
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import com.huawei.ui.main.stories.nps.interactors.util.ParamsUtils;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes7.dex */
public class FiledFragment extends BaseFragment {
    private static final int MAX_TEXT = 3000;
    private static final String TAG = "FiledFragment";
    private EditText mContent;
    private HealthTextView mEditTipView;
    private String mFiledData;
    private int mNumber;
    private int mQuestionId;
    private QuestionSureyResponse mQuestionSurveyResponse;
    private HealthTextView mTitle;
    private TextWatcher watcher = new TextWatcher() { // from class: com.huawei.ui.main.stories.nps.activity.FiledFragment.1
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            FiledFragment.this.mFiledData = charSequence.toString();
            if (Records.queryInt(Integer.valueOf(FiledFragment.this.mQuestionId))) {
                Records.getStringDataCenter().remove(Integer.valueOf(FiledFragment.this.mQuestionId));
                Records.getStringDataCenter().put(Integer.valueOf(FiledFragment.this.mQuestionId), FiledFragment.this.mFiledData);
            } else {
                Records.getStringDataCenter().put(Integer.valueOf(FiledFragment.this.mQuestionId), FiledFragment.this.mFiledData);
            }
            FiledFragment.this.mEditTipView.setText(String.format(Locale.ROOT, FiledFragment.this.getString(R$string.IDS_nps_edit_text_limit), FiledFragment.this.formateWordsNumber(FiledFragment.this.mFiledData.length()), FiledFragment.this.formateWordsNumber(3000)));
            LogUtil.a(FiledFragment.TAG, "finish Filed question successful: ", Records.getStringDataCenter());
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() >= 3000) {
                FiledFragment.this.mEditTipView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296671_res_0x7f09019f));
            } else {
                FiledFragment.this.mEditTipView.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296685_res_0x7f0901ad));
            }
        }
    };

    public FiledFragment() {
    }

    public FiledFragment(QuestionSureyResponse questionSureyResponse, int i) {
        this.mQuestionSurveyResponse = questionSureyResponse;
        this.mNumber = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_nps_question_filed, viewGroup, false);
        this.mTitle = (HealthTextView) nsy.cMd_(inflate, R.id.field_title);
        this.mContent = (EditText) nsy.cMd_(inflate, R.id.field_content_et);
        this.mEditTipView = (HealthTextView) nsy.cMd_(inflate, R.id.text_num_tip);
        this.mContent.addTextChangedListener(this.watcher);
        if (this.mQuestionSurveyResponse != null) {
            this.mTitle.setText(this.mNumber + ". " + this.mQuestionSurveyResponse.getQuestion() + ParamsUtils.getQuestionTypeParams(this.mQuestionSurveyResponse.getQuestionType()));
            this.mQuestionId = this.mQuestionSurveyResponse.getId().intValue();
            this.mFiledData = Records.getStringDataCenter().get(Integer.valueOf(this.mQuestionId));
            Records.getStringDataCenter().put(Integer.valueOf(this.mQuestionId), "");
        }
        this.mEditTipView.setText(String.format(Locale.ROOT, getString(R$string.IDS_nps_edit_text_limit), formateWordsNumber(0), formateWordsNumber(3000)));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formateWordsNumber(int i) {
        if (LanguageUtil.aw(BaseApplication.getContext()) || LanguageUtil.ay(BaseApplication.getContext())) {
            return i + "";
        }
        return UnitUtil.e(i, 1, 0);
    }
}
