package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.nsf;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class WalkStatisticProvider extends SportStatisticProvider {
    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected int getSportType() {
        return 257;
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected void setData(Context context, HashMap<String, Object> hashMap, Object obj) {
        String string;
        if (obj instanceof HiHealthData) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(R.drawable._2131430478_res_0x7f0b0c4e));
            arrayList.add(Integer.valueOf(R.drawable._2131430479_res_0x7f0b0c4f));
            hashMap.put("BACKGROUND", arrayList);
            hashMap.put("MARKER", Integer.valueOf(R.drawable._2131430480_res_0x7f0b0c50));
            hashMap.put("SECTION_STYLE", BaseSection.WALK_STYLE);
            if (UnitUtil.h()) {
                string = super.getContext().getResources().getString(R.string._2130841383_res_0x7f020f27);
            } else {
                string = super.getContext().getResources().getString(R.string._2130837660_res_0x7f02009c);
            }
            hashMap.put("ACCUMULATED_DURATION_TEXT", super.getContext().getResources().getString(R.string._2130844760_res_0x7f021c58, string));
            if (LoginInit.getInstance(context).getIsLogined()) {
                hashMap.put("IS_GOAL_BUTTON_SHOW", true);
            }
            hashMap.put("GOAL_BUTTON", context.getResources().getString(R.string._2130842526_res_0x7f02139e));
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Integer.valueOf(R.drawable._2131430548_res_0x7f0b0c94));
            arrayList2.add(Integer.valueOf(R.drawable._2131430548_res_0x7f0b0c94));
            hashMap.put("MIDDLE_IMAGEVIEW", arrayList2);
            hashMap.put("MIDDLE_IMAGEVIEW_DESC", nsf.h(R.string._2130841799_res_0x7f0210c7));
            if (Utils.j()) {
                hashMap.put("LEFT_IMAGEVIEW", getWarmUpIcon());
            }
            LogUtil.a("Track_Provider_Track_WalkStatisticProvider", hashMap.toString());
        }
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected double getHiHealthDataDistance(HiHealthData hiHealthData) {
        if (hiHealthData != null) {
            return hiHealthData.getDouble("Track_Walk_Distance_Sum");
        }
        return 0.0d;
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getTag() {
        return "Track_Provider_Track_WalkStatisticProvider";
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getHiHealthDataDistanceKey() {
        return "Track_Walk_Distance_Sum";
    }
}
