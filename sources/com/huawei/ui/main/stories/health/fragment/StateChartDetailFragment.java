package com.huawei.ui.main.stories.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.StateIndexHorizontalActivity;
import com.huawei.ui.main.stories.health.adapter.StateIndexHorizontalAdapter;
import com.huawei.ui.main.stories.health.basehealth.SpaceItemDecoration;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineStateChartStorageHelper;
import defpackage.koq;
import defpackage.qkk;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes6.dex */
public abstract class StateChartDetailFragment extends BaseStateIndexDetailFragment implements RqLineStateChartStorageHelper.OnUserRunLevelDataChangeListener {
    private static final int FATIGUE_INDEX_POSITION = 2;
    private static final int FITNESS_INDEX_POSITION = 1;
    protected static final String KEY_MONTH = "key_month";
    protected static final String KEY_WEEK = "key_week";
    private static final int LIST_DEFAULT_SIZE = 3;
    private static final int MAP_DEFAULT_SIZE = 2;
    private static final int MSG_GET_EMPTY_DATA = 103;
    private static final int MSG_GET_MONTH_DATA = 102;
    private static final int MSG_GET_WEEK_DATA = 101;
    private static final int ONE_WEEK_DAY = 7;
    private static final int STATE_INDEX_POSITION = 0;
    private static final String TAG = "StateChartDetailFragment";
    private int mCurrentPosition;
    private int mPortraitToLandscapeFlag;
    private RqLineChartHolderView mRqLineChartHolderView;
    private StateIndexHorizontalAdapter mStateIndexHorizontalAdapter;
    protected Map<String, List<qkk>> mRecyclerDataMap = new HashMap(2);
    private List<qkk> mRecyclerViewData = new ArrayList(3);
    private Handler mHandler = new b();

    public abstract void updateIndexData(List<qkk> list);

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected void initView() {
        super.initView();
        this.mJumpHorizontalImg = (ImageView) this.mView.findViewById(R.id.state_horizontal_icon);
        this.mJumpHorizontalImg.setImageResource(R.drawable._2131429959_res_0x7f0b0a47);
        this.mCurrentRawLayout.setVisibility(8);
        this.mDayViewLinearLayout.setVisibility(8);
        this.mPolyLineDetailLayout.setVisibility(0);
        this.mHealthRecyclerView.setVisibility(0);
        this.mHealthLevelIndicator.setVisibility(8);
        FragmentActivity activity = getActivity();
        if (activity == null || !activity.isInMultiWindowMode()) {
            return;
        }
        this.mJumpHorizontalImg.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        LogUtil.a(TAG, "onMultiWindowModeChanged enter..");
        if (this.mJumpHorizontalImg == null) {
            LogUtil.h(TAG, "onMultiWindowModeChanged mJumpHorizontalImg is null.");
        } else {
            this.mJumpHorizontalImg.setVisibility(z ? 8 : 0);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected void initFragmentView(View view, int i) {
        this.mStateIndexDayTextView.setVisibility(8);
        initChartConfig(i);
        initData();
    }

    protected void initChartConfig(int i) {
        this.mDataInfo = getDataInfo();
        RqLineChartHolderView rqLineChartHolderView = (RqLineChartHolderView) this.mView.findViewById(R.id.rq_chart_line_view_holder);
        this.mRqLineChartHolderView = rqLineChartHolderView;
        rqLineChartHolderView.d(getDataInfo(), 1, i);
        setUserRunLevelDataChangeListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChartConfig(int i) {
        this.mDataInfo = getDataInfo();
        this.mRqLineChartHolderView.c(getDataInfo(), 1, i);
        setUserRunLevelDataChangeListener();
    }

    private void setUserRunLevelDataChangeListener() {
        IChartStorageHelper acquireStorageHelper = this.mRqLineChartHolderView.getRqChartLineViewHolder().acquireStorageHelper();
        if (acquireStorageHelper instanceof RqLineStateChartStorageHelper) {
            ((RqLineStateChartStorageHelper) acquireStorageHelper).a(this);
        } else {
            LogUtil.a(TAG, "setUserRunLevelDataChangeListener chartStorageHelper is not RqLineStateChartStorageHelper");
        }
    }

    private void initData() {
        this.mJumpHorizontalImg.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(StateChartDetailFragment.this.getActivity(), (Class<?>) StateIndexHorizontalActivity.class);
                intent.putExtra("ChartType", StateChartDetailFragment.this.getDataInfo().isWeekData() ? 1 : 2);
                intent.putExtra("portrait_to_landscape", StateChartDetailFragment.this.mPortraitToLandscapeFlag);
                intent.putExtra("rq_jump_to_horizontal_time", StateChartDetailFragment.this.mRqLineChartHolderView.getStartTime());
                StateChartDetailFragment.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        initHorizontalLayout();
    }

    private void initHorizontalLayout() {
        this.mStateIndexHorizontalAdapter = new StateIndexHorizontalAdapter(this.mRecyclerViewData, getContext());
        this.mHealthRecyclerView.setAdapter(this.mStateIndexHorizontalAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(0);
        this.mHealthRecyclerView.setLayoutManager(linearLayoutManager);
        this.mHealthRecyclerView.addItemDecoration(new SpaceItemDecoration(20, 0));
        this.mHealthRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
            }
        });
        this.mStateIndexHorizontalAdapter.e(new StateIndexHorizontalAdapter.OnItemClickListener() { // from class: com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment.5
            @Override // com.huawei.ui.main.stories.health.adapter.StateIndexHorizontalAdapter.OnItemClickListener
            public void onItemClick(String str, int i) {
                StateChartDetailFragment.this.mCurrentPosition = i;
                if (i != StateChartDetailFragment.this.mStateIndexHorizontalAdapter.c()) {
                    if (TextUtils.equals(str, StateChartDetailFragment.this.getString(R$string.IDS_data_state_index_title))) {
                        StateChartDetailFragment.this.mStateMainCard.setHeadTitleText(StateChartDetailFragment.this.getString(R$string.IDS_data_what_is_train_condition));
                        StateChartDetailFragment.this.mStateMainCardExplain.setText(R$string.IDS_data_train_condition_explain);
                        StateChartDetailFragment.this.mPortraitToLandscapeFlag = 0;
                        StateChartDetailFragment.this.updateChartConfig(0);
                    } else if (TextUtils.equals(str, StateChartDetailFragment.this.getString(R$string.IDS_data_fitness_condition))) {
                        StateChartDetailFragment.this.mStateMainCard.setHeadTitleText(StateChartDetailFragment.this.getString(R$string.IDS_data_what_is_physical_fitness_index));
                        StateChartDetailFragment.this.mStateMainCardExplain.setText(R$string.IDS_data_physical_fitness_index_explain);
                        StateChartDetailFragment.this.mPortraitToLandscapeFlag = 1;
                        StateChartDetailFragment.this.updateChartConfig(1);
                    } else {
                        StateChartDetailFragment.this.mStateMainCard.setHeadTitleText(StateChartDetailFragment.this.getString(R$string.IDS_data_what_is_fatigue_index));
                        StateChartDetailFragment.this.mStateMainCardExplain.setText(R$string.IDS_data_fatigue_index_explain);
                        StateChartDetailFragment.this.mPortraitToLandscapeFlag = 2;
                        StateChartDetailFragment.this.updateChartConfig(2);
                    }
                    StateChartDetailFragment.this.mStateIndexHorizontalAdapter.c(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<qkk> getEmptyItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new qkk(getString(R$string.IDS_data_state_index_title), "--", this.mCurrentPosition == 0));
        arrayList.add(new qkk(getString(R$string.IDS_data_fitness_condition), "--", this.mCurrentPosition == 1));
        arrayList.add(new qkk(getString(R$string.IDS_data_fatigue_condition), "--", this.mCurrentPosition == 2));
        return arrayList;
    }

    private String getStateValue(float f) {
        return UnitUtil.e(f, 1, 1);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected DataInfos getDataInfo() {
        return DataInfos.StateIndexMonthDetail;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineStateChartStorageHelper.OnUserRunLevelDataChangeListener
    public void updateData(Map<Integer, RunLevelDetail> map, boolean z, int i, int i2) {
        Message obtain = Message.obtain();
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "updateData map is null or size is empty");
            obtain.what = 103;
            this.mHandler.sendMessage(obtain);
            return;
        }
        List<qkk> stateIndexItemList = getStateIndexItemList(map, i, i2, z);
        if (stateIndexItemList.isEmpty()) {
            LogUtil.h(TAG, "updateData stateIndexItemList is empty");
            obtain.what = 103;
            this.mHandler.sendMessage(obtain);
        } else {
            obtain.obj = stateIndexItemList;
            if (z) {
                obtain.what = 101;
            } else {
                obtain.what = 102;
            }
            this.mHandler.sendMessage(obtain);
        }
    }

    private List<qkk> getStateIndexItemList(Map<Integer, RunLevelDetail> map, int i, int i2, boolean z) {
        TreeSet treeSet = new TreeSet();
        TreeSet treeSet2 = new TreeSet();
        TreeSet treeSet3 = new TreeSet();
        for (Map.Entry<Integer, RunLevelDetail> entry : map.entrySet()) {
            if (entry.getKey().intValue() >= i && entry.getKey().intValue() <= i2 && (!z || i2 - i != 7 || entry.getKey().intValue() != i2)) {
                RunLevelDetail value = entry.getValue();
                float condition = value.getCondition();
                float fitness = value.getFitness();
                float fatigue = value.getFatigue();
                treeSet.add(Float.valueOf(condition));
                treeSet2.add(Float.valueOf(fitness));
                treeSet3.add(Float.valueOf(fatigue));
            }
        }
        if (treeSet.isEmpty() || treeSet2.isEmpty() || treeSet3.isEmpty()) {
            LogUtil.h(TAG, "getStateIndexItemList Sets is empty");
            return Collections.emptyList();
        }
        String string = getString(R$string.IDS_track_heart_rate_range, getStateValue(((Float) treeSet.first()).floatValue()), getStateValue(((Float) treeSet.last()).floatValue()));
        String string2 = getString(R$string.IDS_track_heart_rate_range, getStateValue(((Float) treeSet2.first()).floatValue()), getStateValue(((Float) treeSet2.last()).floatValue()));
        String string3 = getString(R$string.IDS_track_heart_rate_range, getStateValue(((Float) treeSet3.first()).floatValue()), getStateValue(((Float) treeSet3.last()).floatValue()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new qkk(getString(R$string.IDS_data_state_index_title), string, this.mCurrentPosition == 0));
        arrayList.add(new qkk(getString(R$string.IDS_data_fitness_condition), string2, this.mCurrentPosition == 1));
        arrayList.add(new qkk(getString(R$string.IDS_data_fatigue_condition), string3, this.mCurrentPosition == 2));
        return arrayList;
    }

    protected void refreshRecyclerViewData(List<qkk> list, boolean z) {
        List<qkk> list2;
        if (koq.b(list)) {
            LogUtil.h(TAG, "refreshRecyclerViewData data is empty");
            return;
        }
        if (!z || (list2 = this.mRecyclerViewData) == null || this.mStateIndexHorizontalAdapter == null) {
            return;
        }
        list2.clear();
        this.mRecyclerViewData.addAll(list);
        this.mStateIndexHorizontalAdapter.notifyDataSetChanged();
    }

    static class b extends BaseHandler<StateChartDetailFragment> {
        private b(StateChartDetailFragment stateChartDetailFragment) {
            super(stateChartDetailFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dEi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(StateChartDetailFragment stateChartDetailFragment, Message message) {
            if (message.what == 103) {
                stateChartDetailFragment.mRecyclerViewData.clear();
                stateChartDetailFragment.mRecyclerViewData.addAll(stateChartDetailFragment.getEmptyItemList());
                stateChartDetailFragment.mStateIndexHorizontalAdapter.notifyDataSetChanged();
                return;
            }
            Object obj = message.obj;
            if (!(obj instanceof List)) {
                LogUtil.h(StateChartDetailFragment.TAG, "handleMessageWhenReferenceNotNull obj is not instanceof List");
                return;
            }
            List<qkk> list = (List) obj;
            int i = message.what;
            if (i == 101) {
                stateChartDetailFragment.mRecyclerDataMap.put(StateChartDetailFragment.KEY_WEEK, list);
            } else if (i == 102) {
                stateChartDetailFragment.mRecyclerDataMap.put(StateChartDetailFragment.KEY_MONTH, list);
            } else {
                LogUtil.h(StateChartDetailFragment.TAG, "handleMessageWhenReferenceNotNull switch default");
            }
            stateChartDetailFragment.updateIndexData(list);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
