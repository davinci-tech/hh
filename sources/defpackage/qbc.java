package defpackage;

import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.common.UserView;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.base.BaseHistoryModel;

/* loaded from: classes6.dex */
public class qbc extends BaseHistoryModel {
    private long c;
    private long e;

    public qbc(long j, long j2) {
        this.e = j;
        this.c = j2;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.base.BaseHistoryModel, com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public long queryModelStartTime() {
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public UserView queryUserView() {
        return UserView.DETAIL_DATAS;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.base.BaseHistoryModel, com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public long queryModelEndTime() {
        return this.c;
    }
}
