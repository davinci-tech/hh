package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 2, name = ComponentName.RESULT_CODE_MAP_PRODUCER)
/* loaded from: classes8.dex */
public class ResultCodeMapProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_ResultCodeMapProducer";
    private HashMap<Integer, Integer> mResultCodeMap = new HashMap<>(3);
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        initResultCodeMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add("AI_TRAIN_RESULT_CODE");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.ResultCodeMapProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                ResultCodeMapProducer.this.m442xdbf6297d(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-ResultCodeMapProducer, reason: not valid java name */
    /* synthetic */ void m442xdbf6297d(List list, Map map) {
        if (list.contains("AI_TRAIN_RESULT_CODE")) {
            Object obj = map.get("AI_TRAIN_RESULT_CODE");
            if (obj instanceof Integer) {
                calculateResultCodeList(((Integer) obj).intValue());
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("AI_TRAIN_RESULT_CODE_LIST", this.mResultCodeMap);
    }

    private void initResultCodeMap() {
        this.mResultCodeMap.put(0, 0);
        this.mResultCodeMap.put(1, 0);
        this.mResultCodeMap.put(2, 0);
    }

    private void calculateResultCodeList(int i) {
        if (i == 1) {
            this.mResultCodeMap.put(0, Integer.valueOf(getValue(0) + 1));
        } else if (i == 2 || i == 3) {
            this.mResultCodeMap.put(1, Integer.valueOf(getValue(1) + 1));
        } else if (i == 4) {
            this.mResultCodeMap.put(2, Integer.valueOf(getValue(2) + 1));
        }
        LogUtil.a(TAG, "mResultCodeMap ", this.mResultCodeMap);
        onStagingAndNotification();
    }

    private int getValue(int i) {
        if (this.mResultCodeMap.containsKey(Integer.valueOf(i))) {
            return this.mResultCodeMap.get(Integer.valueOf(i)).intValue();
        }
        this.mResultCodeMap.put(Integer.valueOf(i), 0);
        return 0;
    }
}
