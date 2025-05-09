package com.huawei.health.health.utils.vippage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.dol;
import defpackage.dpy;
import defpackage.dqb;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MemberCenterBootPagerHelper extends MemberBaseBootPageHelper {
    public MemberCenterBootPagerHelper(Context context) {
        super(context);
    }

    @Override // com.huawei.health.health.utils.vippage.MemberBaseBootPageHelper
    protected void configBootPage() {
        dol dolVar = new dol();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.drawable._2131430864_res_0x7f0b0dd0));
        dolVar.e(arrayList);
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1002);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("Hi, " + accountInfo);
        dolVar.d(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(BaseApplication.getContext().getString(R$string.IDS_member_center_description));
        dolVar.c(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_member_center_start_trip));
        dolVar.b(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(BaseApplication.getContext().getString(R$string.IDS_member_center_plan_recommend));
        dolVar.a(arrayList5);
        dpy dpyVar = new dpy(this.mContext, dolVar);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.member_guide_page, (ViewGroup) null, false);
        this.mBootPageDialog = new dqb(this.mContext, inflate);
        dpyVar.a(this.mBootPageDialog);
        dpyVar.Zt_(inflate.findViewById(R.id.member_guide_page_layout));
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.member_boot_view_pager);
        dpyVar.a(healthViewPager);
        healthViewPager.setAdapter(dpyVar);
    }

    @Override // com.huawei.health.health.utils.vippage.MemberBaseBootPageHelper
    protected String getSharePreferenceString() {
        return "IS_FIRST_ENTER_MEMBER_CENTER";
    }
}
