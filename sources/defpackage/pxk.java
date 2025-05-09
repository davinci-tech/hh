package defpackage;

import com.huawei.health.main.api.FitnessDataInteractorApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;

@ApiDefine(uri = FitnessDataInteractorApi.class)
@Singleton
/* loaded from: classes6.dex */
public class pxk implements FitnessDataInteractorApi {
    @Override // com.huawei.health.main.api.FitnessDataInteractorApi
    public void requestGoogleFitPonitDatas(CommonUiBaseResponse commonUiBaseResponse) {
        pwn.b().b(commonUiBaseResponse);
    }

    @Override // com.huawei.health.main.api.FitnessDataInteractorApi
    public void requestGoogleFitSegentDatas(CommonUiBaseResponse commonUiBaseResponse) {
        pwn.b().d(commonUiBaseResponse);
    }
}
