package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.Context;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jll;
import defpackage.koq;
import defpackage.ojg;
import defpackage.qpk;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class BodyTemperatureCardConstructor extends BaseCardConstructor {
    private static final String TAG = "FunctionSet_BodyTemperatureCardConstructor";

    public BodyTemperatureCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        return jll.c() ? 1 : 2;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        if (Utils.o()) {
            this.mCardConfig.setCardNameRes("IDS_health_skin_temperature");
            this.mCardConfig.setNoDataDescRes("IDS_skin_temperature_desc");
        }
        return new ojg(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(10);
        qpk d = qpk.d();
        arrayList.add(Integer.valueOf(d.g()));
        arrayList.add(Integer.valueOf(d.m()));
        arrayList.add(Integer.valueOf(d.a()));
        arrayList.add(Integer.valueOf(d.e()));
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
