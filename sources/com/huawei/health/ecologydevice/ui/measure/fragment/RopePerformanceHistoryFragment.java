package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.core.content.ContextCompat;
import com.google.json.JsonSanitizer;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.adapter.RopePerformanceHistoryAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import defpackage.cei;
import defpackage.ddr;
import defpackage.dei;
import defpackage.diy;
import defpackage.dko;
import defpackage.dks;
import defpackage.gib;
import defpackage.knu;
import defpackage.koq;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class RopePerformanceHistoryFragment extends BaseFragment implements View.OnClickListener {
    private static final int MESSAGE_BEST_OF_HISTORY_DATA_EMPTY = 7;
    private static final int MESSAGE_BEST_OF_MONTH_DATA_EMPTY = 5;
    private static final int MESSAGE_HISTORY_DATA_EMPTY = 6;
    private static final int MESSAGE_JUMP_TO_BEST_OF_HISTORY = 3;
    private static final int MESSAGE_JUMP_TO_BEST_OF_MONTH = 4;
    private static final int MESSAGE_QUERY_DATA_FINISH = 1;
    private static final int MESSAGE_QUERY_HISTORY_BEST_FINISH = 2;
    private static final String TAG = "RopePerformanceHistoryFragment";
    private Context mContext;
    private Handler mHandler = new RopePerformanceHandler(this);
    private HealthColumnRelativeLayout mHistoryLayout;
    private HealthColumnRelativeLayout mMonthLayout;
    private HealthColumnRelativeLayout mNoDataLayout;
    private RopePerformanceHistoryAdapter mPerformanceHistoryAdapter;
    private HealthExpandableListView mPerformanceHistoryList;
    private List<List<HiHealthData>> mRopeDataLists;
    private HealthColumnLinearLayout mTitleLayout;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "onCreate");
        super.onCreate(bundle);
        this.mContext = requireContext();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.h(TAG, "onCreateView inflater is null!");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_rope_performance_history, viewGroup, false);
        if (inflate != null && onCreateView != null) {
            initView(inflate);
            if (onCreateView instanceof ViewGroup) {
                ((ViewGroup) onCreateView).addView(inflate);
            }
            initData();
        } else {
            LogUtil.h(TAG, "onCreateView child is null or view is null!");
        }
        return onCreateView;
    }

    private void initView(View view) {
        this.mCustomTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this.mContext, R.color._2131296690_res_0x7f0901b2));
        setTitle(getString(R.string._2130847651_res_0x7f0227a3));
        this.mPerformanceHistoryList = (HealthExpandableListView) view.findViewById(R.id.rope_performance_history_list);
        this.mNoDataLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.rope_performance_no_data_layout);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.item_rope_performance_headerview, (ViewGroup) null);
        this.mHistoryLayout = (HealthColumnRelativeLayout) nsy.cMd_(inflate, R.id.rope_performance_header_view_history);
        this.mMonthLayout = (HealthColumnRelativeLayout) nsy.cMd_(inflate, R.id.rope_performance_header_view_month);
        this.mTitleLayout = (HealthColumnLinearLayout) nsy.cMd_(inflate, R.id.rope_performance_header_view_title_layout);
        this.mPerformanceHistoryList.addHeaderView(inflate);
        if (this.mPerformanceHistoryAdapter == null) {
            this.mPerformanceHistoryAdapter = new RopePerformanceHistoryAdapter(this.mContext);
        }
        this.mPerformanceHistoryList.setAdapter(this.mPerformanceHistoryAdapter);
        this.mPerformanceHistoryList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda2
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view2, int i, int i2, long j) {
                return RopePerformanceHistoryFragment.this.m324x646fca29(expandableListView, view2, i, i2, j);
            }
        });
        this.mHistoryLayout.setOnClickListener(this);
        this.mMonthLayout.setOnClickListener(this);
    }

    /* renamed from: lambda$initView$0$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ boolean m324x646fca29(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        handleChildClick(i, i2);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
        return false;
    }

    private void handleChildClick(int i, int i2) {
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
        } else {
            dei.c().a(getRopeData(i, i2), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda5
                @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
                public final void onResponse(int i3, Object obj) {
                    RopePerformanceHistoryFragment.this.m322x58175ca9(i3, (knu) obj);
                }
            });
        }
    }

    /* renamed from: lambda$handleChildClick$1$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ void m322x58175ca9(int i, knu knuVar) {
        diy.e(this.mContext, "fromRopePerformanceHistoryFragment", knuVar);
        biEventForRopePerformance(this.mContext, "rope_performance_detail");
    }

    private void initData() {
        dei.c().e(new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                RopePerformanceHistoryFragment.this.m323xfd183f02(i, (Pair) obj);
            }
        });
    }

    /* renamed from: lambda$initData$2$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ void m323xfd183f02(int i, Pair pair) {
        if (i != 0) {
            LogUtil.h(TAG, "Failed to queryRopeData, errorCode = ", Integer.valueOf(i));
            dks.Wz_(this.mHandler, 6, null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Map map = (Map) pair.second;
        for (Long l : (Set) pair.first) {
            arrayList.add(handleRopeMonthData(l.longValue(), gib.e(l.longValue()), (List) map.get(l)));
        }
        sortRopeMonthData(arrayList);
        dks.Wz_(this.mHandler, 1, new androidx.core.util.Pair(arrayList, new ArrayList(map.values())));
    }

    private HiHealthData handleRopeMonthData(long j, long j2, List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.a(TAG, "handleRopeMonthData dataList is null");
            return null;
        }
        LogUtil.a(TAG, "handleRopeMonthData startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2));
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.putDouble("Track_Count_Sum", list.size());
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        long j3 = 0;
        while (it.hasNext()) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) nrv.b(JsonSanitizer.sanitize(it.next().getMetaData()), HiTrackMetaData.class);
            if (hiTrackMetaData != null) {
                j3 += hiTrackMetaData.getTotalTime();
                i += hiTrackMetaData.getTotalCalories();
            }
        }
        hiHealthData.setTimeInterval(j, j2);
        hiHealthData.putLong("Track_Duration_Sum", j3);
        hiHealthData.putInt("Track_Calorie_Sum", i);
        return hiHealthData;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == null) {
            LogUtil.h(TAG, "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.mHistoryLayout) {
            jumpToBestOfHistory();
            biEventForRopePerformance(this.mContext, "rope_performance_best_of_history");
        } else if (view == this.mMonthLayout) {
            jumpToBestOfMonth();
            biEventForRopePerformance(this.mContext, "rope_performance_best_of_month");
        } else {
            LogUtil.h(TAG, "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void biEventForRopePerformance(Context context, String str) {
        new dko.c().i(cei.b().getProductId()).e(cei.b().getCurrentMacAddress()).a(str).b().e(context);
    }

    private void jumpToBestOfHistory() {
        ddr.d().e(new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopePerformanceHistoryFragment.this.m325x39c2fe5(i, obj);
            }
        }, DateType.DATE_ALL);
    }

    /* renamed from: lambda$jumpToBestOfHistory$3$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ void m325x39c2fe5(int i, Object obj) {
        if (i != 0) {
            LogUtil.a(TAG, "jumpToBestOfHistory failed to query best of history.");
            dks.Wz_(this.mHandler, 7, null);
        } else {
            dks.Wz_(this.mHandler, 3, obj);
        }
    }

    private void jumpToBestOfMonth() {
        final long currentTimeMillis = System.currentTimeMillis();
        ddr.d().e(new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopePerformanceHistoryFragment.this.m326x35f6e018(currentTimeMillis, i, obj);
            }
        }, DateType.DATE_ALL);
    }

    /* renamed from: lambda$jumpToBestOfMonth$4$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ void m326x35f6e018(long j, int i, Object obj) {
        LogUtil.a(TAG, "jumpToBestOfMonth query performance best of history time = ", Long.valueOf(System.currentTimeMillis() - j));
        if (i != 0) {
            LogUtil.a(TAG, "jumpToBestOfMonth failed to query best of history.");
            dks.Wz_(this.mHandler, 5, null);
        } else {
            dks.Wz_(this.mHandler, 2, obj);
            queryPerformanceBestOfMonth();
        }
    }

    private void queryPerformanceBestOfMonth() {
        final long currentTimeMillis = System.currentTimeMillis();
        ddr.d().e(new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopePerformanceHistoryFragment.this.m327xd703b936(currentTimeMillis, i, obj);
            }
        }, DateType.DATE_MONTH);
    }

    /* renamed from: lambda$queryPerformanceBestOfMonth$5$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceHistoryFragment, reason: not valid java name */
    /* synthetic */ void m327xd703b936(long j, int i, Object obj) {
        LogUtil.a(TAG, "jumpToBestOfMonth query performance best of month time = ", Long.valueOf(System.currentTimeMillis() - j));
        if (i != 0) {
            LogUtil.a(TAG, "jumpToBestOfMonth failed to query best of month.");
            dks.Wz_(this.mHandler, 5, null);
        } else {
            dks.Wz_(this.mHandler, 4, obj);
        }
    }

    public void switchRopePerformanceBestFragment(androidx.core.util.Pair<float[], float[]> pair, androidx.core.util.Pair<float[], float[]> pair2) {
        Bundle bundle = new Bundle();
        if (pair2 != null) {
            putBestOfMonthData(bundle, pair2);
        }
        if (pair != null) {
            putBestOfHistoryData(bundle, pair, pair2);
        }
        switchRopePerformanceBestFragment(bundle);
    }

    public void switchRopePerformanceBestFragment(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("skipping_performance_title", getString(i));
        switchRopePerformanceBestFragment(bundle);
    }

    public void switchRopePerformanceBestFragment(Bundle bundle) {
        RopePerformanceBestFragment ropePerformanceBestFragment = new RopePerformanceBestFragment();
        ropePerformanceBestFragment.setArguments(bundle);
        addFragment(ropePerformanceBestFragment);
    }

    private void putBestOfMonthData(Bundle bundle, androidx.core.util.Pair<float[], float[]> pair) {
        bundle.putFloatArray("skipping_performance_value", pair.first);
        bundle.putFloatArray("skipping_performance_rank", pair.second);
        bundle.putString("skipping_performance_title", getString(R.string._2130847656_res_0x7f0227a8));
    }

    private void putBestOfHistoryData(Bundle bundle, androidx.core.util.Pair<float[], float[]> pair, androidx.core.util.Pair<float[], float[]> pair2) {
        bundle.putFloatArray("skipping_performance_history_value", pair.first);
        bundle.putFloatArray("skipping_performance_history_rank", pair.second);
        if (pair2 == null) {
            bundle.putString("skipping_performance_title", getString(R.string._2130847657_res_0x7f0227a9));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPerformanceHistoryAdapter.a();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
        if (koq.c(this.mRopeDataLists)) {
            this.mRopeDataLists.clear();
        }
        HealthExpandableListView healthExpandableListView = this.mPerformanceHistoryList;
        if (healthExpandableListView != null) {
            healthExpandableListView.setOnChildClickListener(null);
            this.mPerformanceHistoryList = null;
        }
    }

    private void sortRopeMonthData(List<HiHealthData> list) {
        Collections.sort(list, new Comparator<HiHealthData>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceHistoryFragment.1
            @Override // java.util.Comparator
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return Long.compare(hiHealthData2.getStartTime(), hiHealthData.getStartTime());
            }
        });
    }

    public void doRefresh(List<HiHealthData> list, List<List<HiHealthData>> list2) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.a(TAG, "groupList or childList is null");
            dks.Wz_(this.mHandler, 6, null);
        } else {
            this.mRopeDataLists = list2;
            this.mPerformanceHistoryAdapter.e(list, list2);
            this.mPerformanceHistoryAdapter.notifyDataSetChanged();
            this.mPerformanceHistoryList.expandGroup(0);
        }
    }

    public HiHealthData getRopeData(int i, int i2) {
        if (koq.d(this.mRopeDataLists, i) && koq.d(this.mRopeDataLists.get(i), i2)) {
            return this.mRopeDataLists.get(i).get(i2);
        }
        return null;
    }

    public void showEmpty() {
        this.mTitleLayout.setVisibility(8);
        this.mNoDataLayout.setVisibility(0);
    }

    static class RopePerformanceHandler extends BaseHandler<RopePerformanceHistoryFragment> {
        private androidx.core.util.Pair<float[], float[]> mBestOfHistory;

        RopePerformanceHandler(RopePerformanceHistoryFragment ropePerformanceHistoryFragment) {
            super(ropePerformanceHistoryFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(RopePerformanceHistoryFragment ropePerformanceHistoryFragment, Message message) {
            if (!(message.obj instanceof androidx.core.util.Pair)) {
                LogUtil.a(RopePerformanceHistoryFragment.TAG, "obj is not instance of Pair");
                handleOtherMessage(ropePerformanceHistoryFragment, message.what);
                return;
            }
            if (message.what == 1) {
                queryDataFinish(ropePerformanceHistoryFragment, message.obj);
                return;
            }
            if (message.what == 3) {
                ropePerformanceHistoryFragment.switchRopePerformanceBestFragment((androidx.core.util.Pair) message.obj, null);
                return;
            }
            if (message.what == 2) {
                this.mBestOfHistory = (androidx.core.util.Pair) message.obj;
            } else if (message.what == 4) {
                ropePerformanceHistoryFragment.switchRopePerformanceBestFragment(this.mBestOfHistory, (androidx.core.util.Pair) message.obj);
            } else {
                LogUtil.h(RopePerformanceHistoryFragment.TAG, "undefined");
            }
        }

        private void queryDataFinish(RopePerformanceHistoryFragment ropePerformanceHistoryFragment, Object obj) {
            androidx.core.util.Pair pair = (androidx.core.util.Pair) obj;
            ropePerformanceHistoryFragment.doRefresh(pair.first instanceof List ? (List) pair.first : null, pair.second instanceof List ? (List) pair.second : null);
        }

        private void handleOtherMessage(RopePerformanceHistoryFragment ropePerformanceHistoryFragment, int i) {
            if (i == 6) {
                ropePerformanceHistoryFragment.showEmpty();
                return;
            }
            if (i == 7) {
                ropePerformanceHistoryFragment.switchRopePerformanceBestFragment(R.string._2130847657_res_0x7f0227a9);
            } else if (i == 5) {
                ropePerformanceHistoryFragment.switchRopePerformanceBestFragment(R.string._2130847656_res_0x7f0227a8);
            } else {
                LogUtil.c(RopePerformanceHistoryFragment.TAG, "other");
            }
        }
    }
}
