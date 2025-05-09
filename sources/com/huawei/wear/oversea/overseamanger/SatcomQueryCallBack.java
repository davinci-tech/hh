package com.huawei.wear.oversea.overseamanger;

import com.huawei.wear.oversea.satcomcard.SatcomCardSupportInfo;

/* loaded from: classes7.dex */
public interface SatcomQueryCallBack {
    int onFail(SatcomCardSupportInfo satcomCardSupportInfo);

    int onSuccess(SatcomCardSupportInfo satcomCardSupportInfo);
}
