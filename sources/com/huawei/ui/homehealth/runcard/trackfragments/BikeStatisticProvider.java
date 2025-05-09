package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.nsf;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BikeStatisticProvider extends SportStatisticProvider {
    private Context b;

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected int getSportType() {
        return 259;
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected void setData(Context context, HashMap<String, Object> hashMap, Object obj) {
        String string;
        this.b = context == null ? BaseApplication.getContext() : context;
        if (obj instanceof HiHealthData) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(R.drawable._2131430478_res_0x7f0b0c4e));
            arrayList.add(Integer.valueOf(R.drawable._2131430479_res_0x7f0b0c4f));
            hashMap.put("BACKGROUND", arrayList);
            hashMap.put("MARKER", Integer.valueOf(R.drawable._2131430480_res_0x7f0b0c50));
            hashMap.put("SECTION_STYLE", BaseSection.BIKE_STYLE);
            if (UnitUtil.h()) {
                string = super.getContext().getResources().getString(R.string._2130841383_res_0x7f020f27);
            } else {
                string = super.getContext().getResources().getString(R.string._2130837660_res_0x7f02009c);
            }
            hashMap.put("ACCUMULATED_DURATION_TEXT", super.getContext().getResources().getString(R.string._2130844761_res_0x7f021c59, string));
            if (LoginInit.getInstance(context).getIsLogined()) {
                hashMap.put("IS_GOAL_BUTTON_SHOW", true);
            }
            hashMap.put("GOAL_BUTTON", context.getResources().getString(R.string._2130842526_res_0x7f02139e));
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Integer.valueOf(R.drawable._2131430544_res_0x7f0b0c90));
            arrayList2.add(Integer.valueOf(R.drawable._2131430544_res_0x7f0b0c90));
            hashMap.put("MIDDLE_IMAGEVIEW", arrayList2);
            hashMap.put("MIDDLE_IMAGEVIEW_DESC", nsf.h(R.string._2130841798_res_0x7f0210c6));
            ArrayList arrayList3 = new ArrayList(4);
            arrayList3.add(String.valueOf(d()));
            arrayList3.add(super.getContext().getResources().getString(R.string._2130844014_res_0x7f02196e));
            arrayList3.add(Integer.valueOf(R.drawable._2131430298_res_0x7f0b0b9a));
            arrayList3.add(Integer.valueOf(R.drawable._2131430298_res_0x7f0b0b9a));
            hashMap.put("CANCEL_GUIDE_BUBBLE_TEXT", arrayList3);
            if (Utils.j()) {
                hashMap.put("LEFT_IMAGEVIEW", getWarmUpIcon());
            }
            LogUtil.a("Track_Provider_Track_BikeStatisticProvider", hashMap.toString());
        }
    }

    public boolean d() {
        Context context = this.b;
        if (context == null) {
            LogUtil.b("Track_Provider_Track_BikeStatisticProvider", "isShowOutdoorCycleTip ", "mContext is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "show_outdoor_cycle_tip");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        return Boolean.parseBoolean(b);
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected double getHiHealthDataDistance(HiHealthData hiHealthData) {
        return hiHealthData.getDouble("Track_Ride_Distance_Sum");
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getTag() {
        return "Track_Provider_Track_BikeStatisticProvider";
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider
    protected String getHiHealthDataDistanceKey() {
        return "Track_Ride_Distance_Sum";
    }
}
