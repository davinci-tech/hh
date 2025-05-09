package com.huawei.ui.main.stories.nps.views;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.activity.QuestionMainActivity;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyChooseResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class NpsAdapter extends BaseAdapter {
    private static final int MAX_TEXT = 500;
    private static final String TAG = "NpsAdapter";
    private QuestionMainActivity mActivity;
    private Context mContext;
    private String[] mDataInfos;
    private Handler mHandler;
    private String mInputDatas;
    private int mQuestionId;
    private HashMap<String, Boolean> mStateMap;
    private List<QuestionSurveyChooseResponse> mSurveyResponses;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public NpsAdapter(Context context, String[] strArr, HashMap<String, Boolean> hashMap, Handler handler, QuestionMainActivity questionMainActivity) {
        this.mStateMap = new HashMap<>(16);
        if (strArr != null && strArr.length > 0) {
            String[] strArr2 = new String[strArr.length];
            this.mDataInfos = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        this.mContext = context;
        this.mStateMap = hashMap;
        this.mHandler = handler;
        this.mActivity = questionMainActivity;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        String[] strArr = this.mDataInfos;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        String[] strArr = this.mDataInfos;
        if (strArr == null || i < 0 || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = this.mContext;
        if (context == null) {
            return view;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.nps_single, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.tv_device_name);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_edit_parent);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.single_content_et);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.single_text_num_tip);
        String[] strArr = this.mDataInfos;
        if (strArr != null && i >= 0 && i < strArr.length) {
            healthTextView.setText(strArr[i]);
            setTextTip(i, healthTextView2, relativeLayout, healthEditText);
        }
        handleCheckBoxState(inflate, i);
        return inflate;
    }

    private void handleCheckBoxState(View view, final int i) {
        boolean z;
        HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.rb_light);
        healthCheckBox.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.views.NpsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Iterator it = NpsAdapter.this.mStateMap.keySet().iterator();
                while (it.hasNext()) {
                    NpsAdapter.this.mStateMap.put((String) it.next(), false);
                }
                NpsAdapter.this.mStateMap.put(String.valueOf(i), true);
                if (NpsAdapter.this.mActivity != null) {
                    NpsAdapter.this.mActivity.setIsSelect(true);
                    NpsAdapter.this.mActivity.setButtonClickable();
                }
                if (NpsAdapter.this.mHandler != null) {
                    NpsAdapter.this.mHandler.sendEmptyMessage(i);
                }
                NpsAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        if (this.mStateMap.get(String.valueOf(i)) == null || !this.mStateMap.get(String.valueOf(i)).booleanValue()) {
            this.mStateMap.put(String.valueOf(i), false);
            z = false;
        } else {
            z = true;
        }
        healthCheckBox.setChecked(z);
    }

    private void setTextTip(int i, HealthTextView healthTextView, RelativeLayout relativeLayout, HealthEditText healthEditText) {
        List<QuestionSurveyChooseResponse> list = this.mSurveyResponses;
        if (list == null) {
            LogUtil.h(TAG, "setTextTip mSurveyResponses is null");
            return;
        }
        QuestionSurveyChooseResponse questionSurveyChooseResponse = list.get(i);
        if (questionSurveyChooseResponse == null) {
            LogUtil.h(TAG, "setTextTip questionSurvey is null");
            return;
        }
        if (questionSurveyChooseResponse.isFeedbackFlag()) {
            LogUtil.a(TAG, "setTextTip position: ", Integer.valueOf(i), " name:", questionSurveyChooseResponse.getName());
            CharSequence text = healthTextView.getText();
            if (text == null || text.length() < 1) {
                healthTextView.setText(String.format(Locale.ROOT, this.mContext.getString(R$string.IDS_nps_edit_text_limit), UnitUtil.e(0.0d, 1, 0), UnitUtil.e(500.0d, 1, 0)));
            }
            relativeLayout.setVisibility(this.mStateMap.get(String.valueOf(i)).booleanValue() ? 0 : 8);
            healthEditText.addTextChangedListener(new SingleTextWatcher(healthTextView));
            if (TextUtils.isEmpty(this.mInputDatas)) {
                return;
            }
            healthEditText.setText(this.mInputDatas);
            healthEditText.setSelection(this.mInputDatas.length());
        }
    }

    public void setSurveyChoose(QuestionSureyResponse questionSureyResponse) {
        if (questionSureyResponse == null) {
            return;
        }
        this.mSurveyResponses = questionSureyResponse.getChoose();
        this.mQuestionId = questionSureyResponse.getId().intValue();
    }

    class SingleTextWatcher implements TextWatcher {
        private HealthTextView mTextTip;

        SingleTextWatcher(HealthTextView healthTextView) {
            this.mTextTip = healthTextView;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            NpsAdapter.this.mInputDatas = charSequence.toString();
            int length = TextUtils.isEmpty(NpsAdapter.this.mInputDatas) ? 0 : NpsAdapter.this.mInputDatas.length();
            this.mTextTip.setText(String.format(Locale.ROOT, NpsAdapter.this.mContext.getString(R$string.IDS_nps_edit_text_limit), UnitUtil.e(length, 1, 0), UnitUtil.e(500.0d, 1, 0)));
            if (length >= 500) {
                this.mTextTip.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296671_res_0x7f09019f));
            } else {
                this.mTextTip.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296685_res_0x7f0901ad));
            }
            Records.getOptionResult().put(Integer.valueOf(NpsAdapter.this.mQuestionId), NpsAdapter.this.mInputDatas);
            LogUtil.a(NpsAdapter.TAG, "onTextChanged mInputDatas: ", NpsAdapter.this.mInputDatas);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.c(NpsAdapter.TAG, "beforeTextChanged");
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            LogUtil.c(NpsAdapter.TAG, "afterTextChanged");
        }
    }
}
