package com.huawei.ui.main.stories.health.fragment.rqpackage;

import android.content.Context;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelReq;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.health.interactors.healthdata.StateIndexDayData;
import defpackage.ggl;
import defpackage.jbs;
import defpackage.jec;
import defpackage.nnw;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes6.dex */
public class RqLineStateChartStorageHelper implements IChartStorageHelper {

    /* renamed from: a, reason: collision with root package name */
    private OnUserRunLevelDataChangeListener f10186a;
    private int c;

    public interface OnUserRunLevelDataChangeListener {
        void updateData(Map<Integer, RunLevelDetail> map, boolean z, int i, int i2);
    }

    public RqLineStateChartStorageHelper(int i) {
        this.c = i;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        a(context, j, j2, dataInfos, responseCallback);
    }

    private void a(Context context, long j, long j2, final DataInfos dataInfos, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        GetRunLevelReq getRunLevelReq = new GetRunLevelReq();
        if (j2 > System.currentTimeMillis()) {
            j2 = System.currentTimeMillis();
        }
        final int b = ggl.b(j);
        final int b2 = ggl.b(j2);
        getRunLevelReq.setStartDay(b);
        getRunLevelReq.setEndDay(b2);
        getRunLevelReq.setQueryType(0);
        HashSet hashSet = new HashSet();
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        getRunLevelReq.setTypes(hashSet);
        jbs.a(context.getApplicationContext()).d(getRunLevelReq, new ResultCallback<GetRunLevelRsp>() { // from class: com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineStateChartStorageHelper.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetRunLevelRsp getRunLevelRsp) {
                if (getRunLevelRsp == null) {
                    LogUtil.h("Track_RqLineStateChartStorageHelper", "onSuccess response is null");
                    return;
                }
                Map<Integer, RunLevelDetail> data = getRunLevelRsp.getData();
                RqLineStateChartStorageHelper.this.d(responseCallback, data);
                if (RqLineStateChartStorageHelper.this.f10186a != null) {
                    RqLineStateChartStorageHelper.this.f10186a.updateData(data, dataInfos.isWeekData(), b, b2);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("Track_RqLineStateChartStorageHelper", "getRunLevel onFailure:", th.getMessage());
                responseCallback.onResult(-1, Collections.emptyMap());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ResponseCallback<Map<Long, IStorageModel>> responseCallback, Map<Integer, RunLevelDetail> map) {
        responseCallback.onResult(0, b(map));
    }

    private Map<Long, IStorageModel> b(Map<Integer, RunLevelDetail> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry<Integer, RunLevelDetail> entry : map.entrySet()) {
                long d = d(entry.getKey().intValue());
                nnw nnwVar = new nnw(1.0f);
                int i = this.c;
                if (i == 0) {
                    nnwVar.d(e(entry.getValue()).getCondition());
                } else if (i == 1) {
                    nnwVar.d(e(entry.getValue()).getFitness());
                } else {
                    nnwVar.d(e(entry.getValue()).getFatigue());
                }
                hashMap.put(Long.valueOf(d), nnwVar);
            }
        }
        return hashMap;
    }

    private StateIndexDayData e(RunLevelDetail runLevelDetail) {
        StateIndexDayData stateIndexDayData = new StateIndexDayData();
        stateIndexDayData.setCondition(runLevelDetail.getCondition());
        stateIndexDayData.setFatigue(runLevelDetail.getFatigue());
        stateIndexDayData.setFitness(runLevelDetail.getFitness());
        return stateIndexDayData;
    }

    private long d(int i) {
        try {
            return jec.g(new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(i)).getTime());
        } catch (ParseException e) {
            LogUtil.b("Track_RqLineStateChartStorageHelper", e.getMessage());
            return 0L;
        }
    }

    public void a(OnUserRunLevelDataChangeListener onUserRunLevelDataChangeListener) {
        this.f10186a = onUserRunLevelDataChangeListener;
    }
}
