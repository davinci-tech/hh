package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.os.Bundle;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.healthcloud.plugintrack.ui.fragment.AiActionTrainFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AIActionBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class AiActionTrainViewModel extends SportExamViewModel implements SportDataNotify {
    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public boolean isStopSportByTarget() {
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void playPreSportTipsVoice() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mSportDataOutputApi.setParas(SportParamsType.AI_TRAIN_TEMPLATE_DATA, a());
        super.onPreSport();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList(super.getSubscribeTagList());
        arrayList.add("CALORIES_DATA");
        arrayList.add("AI_TRAIN_RESULT_CODE");
        arrayList.add("AI_TRAIN_VALID_TIMES");
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
        LogUtil.a("Track_AiActionTrainViewModel", "initFragment");
        addFragment(new AiActionTrainFragment(d()));
    }

    public int d() {
        AIActionBundle b = b();
        int towards = b != null ? b.getTowards() : 0;
        LogUtil.a("Track_AiActionTrainViewModel", "initFragment, sportLaunchParams ", Integer.valueOf(towards));
        return towards;
    }

    public int c() {
        AIActionBundle b = b();
        if (b != null) {
            return b.getAiMeasurement();
        }
        return -1;
    }

    public AIActionBundle b() {
        SportLaunchParams sportLaunchParams = getSportLaunchParams();
        if (sportLaunchParams != null) {
            return (AIActionBundle) sportLaunchParams.getExtra("AI_ACTION_BUNDLE", AIActionBundle.class);
        }
        return null;
    }

    private String a() {
        AIActionBundle b = b();
        String describe = b != null ? b.getDescribe() : null;
        LogUtil.a("Track_AiActionTrainViewModel", "initFragment, template ", describe);
        return describe;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        super.initReceivedDataHandlers();
        h();
        i();
        receivedAllData();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        LogUtil.a("Track_AiActionTrainViewModel", "init");
        addSportDataMap("result_code");
        addSportDataMap("valid_times");
    }

    public WorkoutRecord e() {
        WorkoutRecord workoutRecord;
        if (this.mSportDataOutputApi != null) {
            Object data = this.mSportDataOutputApi.getData("AI_TRAIN_WORKOUT_RECORD");
            if (data instanceof WorkoutRecord) {
                workoutRecord = (WorkoutRecord) data;
                LogUtil.a("Track_AiActionTrainViewModel", "workoutRecord ", workoutRecord);
                return workoutRecord;
            }
        }
        workoutRecord = null;
        LogUtil.a("Track_AiActionTrainViewModel", "workoutRecord ", workoutRecord);
        return workoutRecord;
    }

    private void h() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "AI_TRAIN_RESULT_CODE", "result_code", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.AiActionTrainViewModel.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                AiActionTrainViewModel.this.postValue("result_code", num);
            }
        });
    }

    private void i() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "AI_TRAIN_VALID_TIMES", "valid_times", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.AiActionTrainViewModel.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                AiActionTrainViewModel.this.postValue("valid_times", num);
            }
        });
    }
}
