package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class OutdoorRunStatisticProvider extends SportStatisticProvider {
    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected int getSportType() {
        return 258;
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected void setData(Context context, HashMap<String, Object> hashMap, Object obj) {
        setInitData(context, hashMap, obj, true, Integer.valueOf(R.drawable._2131430547_res_0x7f0b0c93), "Track_Provider_Track_OutdoorRunStatisticProvider");
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected double getHiHealthDataDistance(HiHealthData hiHealthData) {
        return SportStatisticProvider.getDataDistance(hiHealthData);
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getTag() {
        return "Track_Provider_Track_OutdoorRunStatisticProvider";
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getHiHealthDataDistanceKey() {
        return "Track_Run_Distance_Sum";
    }
}
