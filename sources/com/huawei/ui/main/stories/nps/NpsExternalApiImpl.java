package com.huawei.ui.main.stories.nps;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.nps.api.NpsExternalApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.main.stories.nps.activity.QuestionMainActivity;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;

@ApiDefine(uri = NpsExternalApi.class)
/* loaded from: classes7.dex */
public class NpsExternalApiImpl implements NpsExternalApi {
    @Override // com.huawei.health.nps.api.NpsExternalApi
    public boolean isShowNps() {
        return NpsUserShowController.getInstance(BaseApplication.getContext()).isShowNps();
    }

    @Override // com.huawei.health.nps.api.NpsExternalApi
    public boolean isShowDeviceNps() {
        return HwNpsManager.getInstance().isShowToDo();
    }

    @Override // com.huawei.health.nps.api.NpsExternalApi
    public boolean isWeightDeviceNps() {
        return HagridNpsManager.getInstance().isWeightDeviceNps();
    }

    @Override // com.huawei.health.nps.api.NpsExternalApi
    public void showNpsPage(Context context) {
        NpsUserShowController.getInstance(BaseApplication.getContext()).showNpsPage(context);
    }

    @Override // com.huawei.health.nps.api.NpsExternalApi
    public void showDeviceNpsPage(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, (Class<?>) QuestionMainActivity.class));
    }

    @Override // com.huawei.health.nps.api.NpsExternalApi
    public void sendNpsAfterRun() {
        NpsUserShowController.getInstance(BaseApplication.getContext()).sendNpsAfterRun();
    }
}
