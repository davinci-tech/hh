package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.Context;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.ojo;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class WeightCardConstructor extends BaseCardConstructor {
    private static final String IDS_MAIN_HOME_PAGE_WEIGHT = "IDS_hw_show_main_home_page_weight";
    private static final String TAG = "FunctionSet_WeightCardConstructor";

    public WeightCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        int i = 2;
        if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((DeviceCapability) it.next()) != null) {
                    i = 1;
                    break;
                }
            }
            LogUtil.a(TAG, "getShowFlag show = ", Integer.valueOf(i));
        }
        return i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        return super.isSupport(z, i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        if (Utils.o()) {
            this.mCardConfig.setCardNameRes(IDS_MAIN_HOME_PAGE_WEIGHT);
        }
        LogUtil.a(TAG, "getCardReader");
        return new ojo(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(Integer.valueOf(HiSubscribeType.c));
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
