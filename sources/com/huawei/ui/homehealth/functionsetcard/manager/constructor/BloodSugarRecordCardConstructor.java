package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.oit;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class BloodSugarRecordCardConstructor extends BaseCardConstructor {
    private static final String TAG = "FunctionSet_BloodSugarRecordCardConstructor";

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        return 0;
    }

    public BloodSugarRecordCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        return super.isSupport(z, i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        return new oit(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(10);
        arrayList.add(Integer.valueOf(HiSubscribeType.e));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new BaseCardConstructor.InnerHiSubscribeListener(TAG, this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void unSubscribeDataChange() {
        if (koq.b(this.mSuccessList)) {
            LogUtil.a(TAG, "unSubscribeDataChange no type list");
        } else {
            LogUtil.a(TAG, "unSubscribeDataChange");
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.mSuccessList, new BaseCardConstructor.InnerHiUnSubscribeListener(TAG, "unSubscribeDataChange, isSuccess:"));
        }
    }
}
