package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.Context;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.efb;
import defpackage.koq;
import defpackage.ois;
import defpackage.oji;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class StressCardConstructor extends BaseCardConstructor {
    private static final String TAG = "FunctionSet_StressCardConstructor";

    public StressCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        int i = 2;
        if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                DeviceCapability deviceCapability = (DeviceCapability) it.next();
                if (deviceCapability != null && (deviceCapability.isSupportPressAutoMonitor() || deviceCapability.isSupportStress())) {
                    i = 1;
                    break;
                }
            }
            LogUtil.a(TAG, "getShowFlag isStress = ", Integer.valueOf(i));
        }
        return i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        LogUtil.a(TAG, "getCardReader");
        if (!efb.e()) {
            return new oji(context, this.mCardConfig);
        }
        if (this.mCardConfig != null) {
            this.mCardConfig.setCardNameRes("IDS_hw_show_main_home_page_card_emotion_title");
            this.mCardConfig.setNoDataDescRes("IDS_hw_show_main_home_page_card_emotion");
            this.mCardConfig.setDefaultMarketingDescRes("IDS_hw_show_main_home_page_card_emotion");
        }
        return new ois(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        return super.isSupport(z, i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(14);
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
