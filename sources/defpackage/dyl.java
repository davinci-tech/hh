package defpackage;

import com.huawei.health.device.api.ConverResisToWeightDataUtilApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;

@ApiDefine(uri = ConverResisToWeightDataUtilApi.class)
/* loaded from: classes3.dex */
public class dyl implements ConverResisToWeightDataUtilApi {
    @Override // com.huawei.health.device.api.ConverResisToWeightDataUtilApi
    public void convertWeightAndFatRateData(HiHealthData hiHealthData, ckm ckmVar, cfi cfiVar) {
        LogUtil.a("ConverResisToWeightDataUtilImpl", "convertWeightAndFatRateData");
        ckn.a(hiHealthData, ckmVar, cfiVar);
    }
}
