package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.Context;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.ojc;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class HeartRateConstructor extends BaseCardConstructor {
    private static final int SUBSCRIBE_HEART_SIZE = 2;
    private static final String TAG = "FunctionSet_HeartRateConstructor";

    public HeartRateConstructor(CardConfig cardConfig) {
        super(cardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        int i = 2;
        if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                DeviceCapability deviceCapability = (DeviceCapability) it.next();
                if (deviceCapability != null && (deviceCapability.isSupportContinueHeartRate() || deviceCapability.isSupportHeartRateEnable() || deviceCapability.isSupportHeartRate())) {
                    i = 1;
                    break;
                }
            }
            LogUtil.a(TAG, "getShowFlag isHeartRate = ", Integer.valueOf(i));
        }
        return i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        return new ojc(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        return super.isSupport(z, i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(5);
        arrayList.add(6);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new BaseCardConstructor.InnerHiSubscribeListener(TAG, this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void unSubscribeDataChange() {
        if (koq.b(this.mSuccessList)) {
            LogUtil.h(TAG, "unSubscribeDataChange no type list");
        } else {
            LogUtil.a(TAG, "unSubscribeDataChange");
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.mSuccessList, new BaseCardConstructor.InnerHiUnSubscribeListener(TAG, "unSubscribeDataChange, isSuccess:"));
        }
    }
}
