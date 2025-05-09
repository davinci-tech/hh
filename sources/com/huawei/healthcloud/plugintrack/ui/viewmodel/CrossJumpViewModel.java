package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.CrossJumpIntroduceFragment;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.gyr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CrossJumpViewModel extends SportExamViewModel implements SportDataNotify {
    private int e;
    private final Map<Integer, Bitmap> c = new HashMap();
    private int b = 0;

    public boolean c(int i) {
        return i >= 110;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void playPreSportTipsVoice() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
        addFragment(new CrossJumpFragment());
        addFragment(new CrossJumpIntroduceFragment());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        addSportDataMap("COMPLETE_ONE_GROUP");
        addSportDataMap("OVER_TIME_JUMP");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList(super.getSubscribeTagList());
        arrayList.add("CROSS_JUMP_GROUP_DATA");
        arrayList.add("AI_SPORT_UI_UPDATE_SIGNAL");
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        super.initReceivedDataHandlers();
        e();
        a();
        receivedAllData();
    }

    private void e() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "AI_SPORT_UI_UPDATE_SIGNAL", "OVER_TIME_JUMP", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.CrossJumpViewModel.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                CrossJumpViewModel.this.postValue("OVER_TIME_JUMP", num);
            }
        });
    }

    private void a() {
        this.mReceivedData.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "CROSS_JUMP_GROUP_DATA", "COMPLETE_ONE_GROUP", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.CrossJumpViewModel.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                CrossJumpViewModel.this.b = num.intValue();
                CrossJumpViewModel.this.postValue("COMPLETE_ONE_GROUP", num);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void sendSportTime(int i) {
        this.e = i;
        postValue(ParsedFieldTag.NPES_SPORT_TIME, Integer.valueOf(i));
    }

    public int b() {
        return Math.min(this.b, 10);
    }

    public boolean d() {
        return this.e >= 120;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestLocalData() {
        super.requestLocalData();
        c();
    }

    private void c() {
        this.c.put(1, gyr.e().aWO_("public_square_first"));
        this.c.put(2, gyr.e().aWO_("public_square_second"));
        this.c.put(3, gyr.e().aWO_("public_square_third"));
        this.c.put(4, gyr.e().aWO_("public_square_fourth"));
        this.c.put(-4002, gyr.e().aWO_("public_jump_error"));
    }

    public Bitmap bnH_(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return this.c.get(Integer.valueOf(i));
        }
        return gyr.e().aWO_("public_square_first");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_CrossJumpViewModel";
    }
}
