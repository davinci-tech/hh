package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.media.Image;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.LongJumpIntroduceFragment;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LongJumpViewModel extends SportExamViewModel implements SportDataNotify {
    private List<Integer> d = new ArrayList(3);

    /* renamed from: a, reason: collision with root package name */
    private boolean f3825a = false;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void playPreSportTipsVoice() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.OnPreviewListener
    public void onPreviewFrame(Image image, int i, int i2) {
        if (this.f3825a) {
            super.onPreviewFrame(image, i, i2);
        }
    }

    public void c(boolean z) {
        this.f3825a = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
        addFragment(new LongJumpFragment());
        addFragment(new LongJumpIntroduceFragment());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        super.initReceivedDataHandlers();
        receivedAllData();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel
    protected void handlePostSportExamScore(int i) {
        if (i < 50) {
            LogUtil.a("Track_LongJumpViewModel", "handlePostSportExamScore score < 50");
            super.handlePostSportExamScore(i);
        } else {
            this.d.add(Integer.valueOf(i));
            super.handlePostSportExamScore(i);
        }
    }

    public List<Integer> b() {
        if (this.d == null) {
            this.d = new ArrayList(3);
        }
        return this.d;
    }

    public boolean e() {
        if (this.d.size() > 1) {
            List<Integer> list = this.d;
            if (list.get(list.size() - 1).intValue() > this.d.get(r2.size() - 2).intValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_LongJumpViewModel";
    }
}
