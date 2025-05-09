package defpackage;

import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginhealthalgorithm.MenstrualPredictApi;

@ApiDefine(uri = MenstrualPredictApi.class)
@Singleton
/* loaded from: classes6.dex */
public class nwr implements MenstrualPredictApi {
    @Override // com.huawei.pluginhealthalgorithm.MenstrualPredictApi
    public void getSwitchState(String str, IBaseResponseCallback iBaseResponseCallback) {
        nwp.c(str, iBaseResponseCallback);
    }

    @Override // com.huawei.pluginhealthalgorithm.MenstrualPredictApi
    public void updateSwitchState(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        nwp.a(str, str2, iBaseResponseCallback);
        nwp.d(str2);
    }
}
