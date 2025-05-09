package com.huawei.ui.main.stories.nps.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyChooseResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Records;
import com.huawei.ui.main.stories.nps.views.EcologyNpsAdapter;
import com.huawei.ui.main.stories.nps.views.NpsAdapterParams;
import com.huawei.ui.main.stories.nps.views.OnCheckedChangeListener;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class EcologyMultipleFragment extends BaseFragment {
    private static final String TAG = "EcologyMultipleFragment";
    private OnCheckedChangeListener checkedChangeListener;
    private EcologyNpsAdapter mAdapter;
    private ListView mListView;
    private int mNumber;
    private String[] mQuestionAnswers;
    private List<QuestionSurveyChooseResponse> mQuestionChooseResponses;
    private QuestionSureyResponse mQuestionSureyResponse;
    private HealthSubHeader mQuestionTextView;
    private HashMap<String, Boolean> mStates = new HashMap<>();
    private Handler mHandler = new Handler() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyMultipleFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            EcologyMultipleFragment.this.mListView.setItemChecked(message.what, true);
            EcologyMultipleFragment.this.saveData();
            super.handleMessage(message);
        }
    };

    public EcologyMultipleFragment() {
    }

    public EcologyMultipleFragment(QuestionSureyResponse questionSureyResponse, int i, OnCheckedChangeListener onCheckedChangeListener) {
        this.mQuestionSureyResponse = questionSureyResponse;
        this.checkedChangeListener = onCheckedChangeListener;
        this.mNumber = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ecology_nps_single, viewGroup, false);
        QuestionSureyResponse questionSureyResponse = this.mQuestionSureyResponse;
        if (questionSureyResponse == null) {
            return inflate;
        }
        this.mQuestionChooseResponses = questionSureyResponse.getChoose();
        this.mListView = (ListView) nsy.cMd_(inflate, R.id.single_listview);
        this.mQuestionTextView = (HealthSubHeader) nsy.cMd_(inflate, R.id.ecology_question_title);
        String str = this.mNumber + "." + this.mQuestionSureyResponse.getQuestion();
        this.mQuestionTextView.setSubHeaderBackgroundColor(getActivity().getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.mQuestionTextView.setHeadTitleText(str);
        this.mQuestionTextView.setVisibility(0);
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
                this.mStates.put(i + "", false);
            }
        }
        EcologyNpsAdapter ecologyNpsAdapter = new EcologyNpsAdapter(getContext(), this.mHandler, getNosAdapterParams());
        this.mAdapter = ecologyNpsAdapter;
        this.mListView.setAdapter((ListAdapter) ecologyNpsAdapter);
        this.mListView.setChoiceMode(2);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.EcologyMultipleFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (((Boolean) EcologyMultipleFragment.this.mStates.get(String.valueOf(i2))).booleanValue()) {
                    EcologyMultipleFragment.this.mStates.put(String.valueOf(i2), false);
                } else {
                    EcologyMultipleFragment.this.mStates.put(String.valueOf(i2), true);
                }
                if (EcologyMultipleFragment.this.mStates.values().contains(true)) {
                    EcologyMultipleFragment.this.checkedChangeListener.onClick(true);
                } else {
                    EcologyMultipleFragment.this.checkedChangeListener.onClick(false);
                }
                EcologyMultipleFragment.this.mAdapter.notifyDataSetChanged();
                EcologyMultipleFragment.this.saveData();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
            }
        });
        return inflate;
    }

    private NpsAdapterParams getNosAdapterParams() {
        NpsAdapterParams npsAdapterParams = new NpsAdapterParams();
        npsAdapterParams.setDataInfos(this.mQuestionAnswers);
        npsAdapterParams.setIsMultiple(true);
        npsAdapterParams.setStateMap(this.mStates);
        npsAdapterParams.setCheckedChangeListener(this.checkedChangeListener);
        return npsAdapterParams;
    }

    public void saveData() {
        LogUtil.a(TAG, "Enter saveData");
        SparseBooleanArray checkedItemPositions = this.mListView.getCheckedItemPositions();
        if (checkedItemPositions == null || checkedItemPositions.size() == 0) {
            LogUtil.a(TAG, "no choose item");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            int keyAt = checkedItemPositions.keyAt(i);
            if (checkedItemPositions.get(keyAt)) {
                arrayList.add(Integer.valueOf(i));
                LogUtil.c(TAG, "" + keyAt + ": true");
            }
        }
        if (this.mQuestionChooseResponses == null || this.mQuestionSureyResponse == null) {
            LogUtil.h(TAG, "saveData error!");
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(this.mQuestionChooseResponses.get(((Integer) it.next()).intValue()).getName());
        }
        Records.getOptionResult().put(this.mQuestionSureyResponse.getId(), arrayList2.toString());
        LogUtil.a(TAG, "finish multiple choose question successful getOptionResult:", Records.getOptionResult());
    }
}
