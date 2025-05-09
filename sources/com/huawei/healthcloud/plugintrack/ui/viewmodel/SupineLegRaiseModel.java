package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.os.Bundle;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegIntroduceFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.SupineLegRaiseFragment;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SupineLegRaiseModel;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import com.huawei.hwsportmodel.SupineLegAchieveType;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SupineLegRaiseModel extends SportExamViewModel implements SportDataNotify {
    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public boolean isStopSportByTarget() {
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void playPreSportTipsVoice() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        addSportDataMap("reStartSport");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestData() {
        super.requestData();
        this.mSportDataOutputApi.registerAchieveLevel(getTag(), new SportAchieveSubscribe() { // from class: hop
            @Override // com.huawei.hwsportmodel.SportAchieveSubscribe
            public final void onChange(Object obj) {
                SupineLegRaiseModel.this.c(obj);
            }
        });
    }

    public /* synthetic */ void c(Object obj) {
        if (obj instanceof SupineLegAchieveType) {
            postValue("encourageType", Integer.valueOf(((SupineLegAchieveType) obj).getLegRaiseTimes()));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList(super.getSubscribeTagList());
        arrayList.add("INIT_START_SPORT");
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        super.initReceivedDataHandlers();
        b();
        receivedAllData();
    }

    private void b() {
        this.mReceivedData.add(new BaseSportingViewModel.c(this, "INIT_START_SPORT", "reStartSport", Boolean.class));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
        addFragment(new SupineLegRaiseFragment());
        addFragment(new SupineLegIntroduceFragment());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_SupineLegRaiseModel";
    }
}
