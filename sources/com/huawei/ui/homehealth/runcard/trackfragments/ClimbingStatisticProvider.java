package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.hkc;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class ClimbingStatisticProvider extends SportStatisticProvider {
    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected int getSportType() {
        return 260;
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
            hashMap.put("SECTION_STYLE", BaseSection.CLIMB_STYLE);
            if (UnitUtil.h()) {
                string = super.getContext().getResources().getString(R.string._2130841383_res_0x7f020f27);
            } else {
                string = super.getContext().getResources().getString(R.string._2130837660_res_0x7f02009c);
            }
            hashMap.put("ACCUMULATED_DURATION_TEXT", super.getContext().getResources().getString(R.string._2130844762_res_0x7f021c5a, string));
            if (LoginInit.getInstance(context).getIsLogined()) {
                hashMap.put("IS_GOAL_BUTTON_SHOW", true);
            }
            hashMap.put("GOAL_BUTTON", context.getResources().getString(R.string._2130842526_res_0x7f02139e));
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Integer.valueOf(R.drawable._2131430543_res_0x7f0b0c8f));
            arrayList2.add(Integer.valueOf(R.drawable._2131430543_res_0x7f0b0c8f));
            hashMap.put("MIDDLE_IMAGEVIEW", arrayList2);
            if (Utils.j()) {
                hashMap.put("LEFT_IMAGEVIEW", getWarmUpIcon());
            }
            LogUtil.a("Track_Provider_ClimbingStatisticProvider", hashMap.toString());
        }
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected double getHiHealthDataDistance(HiHealthData hiHealthData) {
        if (hiHealthData != null) {
            String[] a2 = hkc.a(this.mContext, 260);
            if (a2.length > 0) {
                return hiHealthData.getDouble(a2[0]);
            }
        }
        return 0.0d;
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getHiHealthDataDistanceKey() {
        String[] a2 = hkc.a(BaseApplication.e(), 260);
        return a2.length > 0 ? a2[0] : "";
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getTag() {
        return "Track_Provider_ClimbingStatisticProvider";
    }
}
