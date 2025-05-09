package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class InternalPayRequest implements IMessageEntity {

    @Packed
    public String accessToken;

    @Packed
    public String amount;

    @Packed
    public String applicationID;

    @Packed
    public String expireTime;

    @Packed
    public String extReserved;

    @Packed
    public String notifyUrl;

    @Packed
    public String productDesc;

    @Packed
    public String productName;

    @Packed
    public String requestId;

    @Packed
    public int sdkChannel;

    @Packed
    public String serviceCatalog;

    @Packed
    public String sign;

    @Packed
    public String signType;

    @Packed
    public String urlver;

    @Packed
    public String userID;

    @Packed
    public String userName;

    @Packed
    public int validTime;
}
