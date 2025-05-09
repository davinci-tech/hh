package defpackage;

import com.huawei.hihealthservice.sync.syncerrormgr.ErrorHandlingBase;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class iuo implements ErrorHandlingBase {
    @Override // com.huawei.hihealthservice.sync.syncerrormgr.ErrorHandlingBase
    public void dealError(CloudCommonReponse cloudCommonReponse) throws iut {
        throw new iut(iuk.a(cloudCommonReponse.getResultCode().intValue()) + cloudCommonReponse.getResultDesc());
    }
}
