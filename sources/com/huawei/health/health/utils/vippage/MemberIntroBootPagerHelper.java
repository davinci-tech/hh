package com.huawei.health.health.utils.vippage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.dol;
import defpackage.dpy;
import defpackage.dqb;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MemberIntroBootPagerHelper extends MemberBaseBootPageHelper {
    public MemberIntroBootPagerHelper(Context context) {
        super(context);
    }

    @Override // com.huawei.health.health.utils.vippage.MemberBaseBootPageHelper
    protected void configBootPage() {
        dol dolVar = new dol();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.drawable._2131430866_res_0x7f0b0dd2));
        arrayList.add(Integer.valueOf(R.drawable._2131430867_res_0x7f0b0dd3));
        dolVar.e(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_member_guide_instruction));
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_member_old_content_description));
        dolVar.d(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("");
        arrayList3.add("");
        dolVar.c(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_startup_next));
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_achive_ok_check_text));
        dolVar.b(arrayList4);
        dolVar.a(new ArrayList<String>() { // from class: com.huawei.health.health.utils.vippage.MemberIntroBootPagerHelper.4
            {
                add("");
                add("");
            }
        });
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
        return "IS_FIRST_ENTER_MEMBER_INTRO";
    }
}
