package com.huawei.hms.support.api.paytask.fullsdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;

/* loaded from: classes9.dex */
public class FullSdkWithholdTask extends BaseFullSdkTask<WithholdRequest> {
    @Override // com.huawei.hms.support.api.paytask.fullsdk.BaseFullSdkTask
    protected Intent getFullSdkIntent() {
        PayRequest createRequestParams;
        if (this.mContext == null || (createRequestParams = createRequestParams()) == null) {
            return null;
        }
        return IapFullAPIFactory.createIapFullAPI(this.mContext).getWithholdIntent(this.mContext, createRequestParams);
    }

    @Override // com.huawei.hms.support.api.paytask.fullsdk.BaseFullSdkTask
    protected PayRequest createRequestParams() {
        if (this.mRequest == 0) {
            return null;
        }
        PayRequest payRequest = new PayRequest();
        payRequest.setMerchantId(((WithholdRequest) this.mRequest).merchantId);
        payRequest.setProductName(((WithholdRequest) this.mRequest).productName);
        payRequest.setApplicationID(((WithholdRequest) this.mRequest).applicationID);
        payRequest.setProductDesc(((WithholdRequest) this.mRequest).productDesc);
        payRequest.setAmount(((WithholdRequest) this.mRequest).amount);
        payRequest.setRequestId(((WithholdRequest) this.mRequest).requestId);
        payRequest.setCountry(((WithholdRequest) this.mRequest).country);
        payRequest.setCurrency(((WithholdRequest) this.mRequest).currency);
        payRequest.setSdkChannel(((WithholdRequest) this.mRequest).sdkChannel);
        payRequest.setUrlver(((WithholdRequest) this.mRequest).urlVer);
        payRequest.setTradeType(((WithholdRequest) this.mRequest).tradeType);
        payRequest.setReservedInfor(((WithholdRequest) this.mRequest).reservedInfor);
        payRequest.setSign(((WithholdRequest) this.mRequest).sign);
        payRequest.setUrl(((WithholdRequest) this.mRequest).url);
        payRequest.setMerchantName(((WithholdRequest) this.mRequest).merchantName);
        payRequest.setServiceCatalog(((WithholdRequest) this.mRequest).serviceCatalog);
        payRequest.setExtReserved(((WithholdRequest) this.mRequest).extReserved);
        String str = ((WithholdRequest) this.mRequest).signatureAlgorithm;
        if (!TextUtils.isEmpty(str) && checkFieldExist(payRequest, "signatureAlgorithm")) {
            payRequest.setSignatureAlgorithm(str);
        }
        return payRequest;
    }

    public FullSdkWithholdTask(Context context, WithholdRequest withholdRequest) {
        super(context, withholdRequest);
    }
}
