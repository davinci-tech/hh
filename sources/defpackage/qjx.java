package defpackage;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes8.dex */
public class qjx {
    private final Activity c;

    public qjx(Activity activity) {
        this.c = activity;
    }

    public List<HiTimeInterval> e(String str) {
        ArrayList arrayList = new ArrayList(31);
        List<cfe> list = WeightDataManager.INSTANCE.getUserWeightDataMap().get(str);
        if (list != null) {
            for (cfe cfeVar : list) {
                HiTimeInterval hiTimeInterval = new HiTimeInterval();
                hiTimeInterval.setStartTime(cfeVar.au());
                hiTimeInterval.setEndTime(cfeVar.au());
                arrayList.add(hiTimeInterval);
            }
        }
        return arrayList;
    }

    public void dEG_(cfi cfiVar, View view) {
        if (view == null) {
            LogUtil.h("AddUserOrEditWeightUserInteractor", "deleteLayout is null");
        } else if (cfiVar == null) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
    }

    public void c(String str, CustomTitleBar customTitleBar) {
        if (customTitleBar == null || this.c == null) {
            LogUtil.h("AddUserOrEditWeightUserInteractor", "tvTitle or mActivity is null");
        } else if (TextUtils.isEmpty(str)) {
            customTitleBar.setTitleText(this.c.getResources().getString(R$string.IDS_hw_base_health_weight_add_user));
        } else {
            customTitleBar.setTitleText(this.c.getResources().getString(R$string.IDS_hw_base_health_weight_edit_user));
        }
    }

    public void d(cfi cfiVar, HealthTextView healthTextView, boolean z) {
        if (healthTextView == null || this.c == null) {
            LogUtil.h("AddUserOrEditWeightUserInteractor", "userHeightText or mActivity is null");
            return;
        }
        int d = cfiVar == null ? 0 : cfiVar.d();
        if (z && cfiVar == null) {
            healthTextView.setText(this.c.getString(R$string.IDS_device_wifi_userinfo_please_select));
            return;
        }
        if (UnitUtil.h()) {
            int[] iArr = {5, 3};
            if (d > 30) {
                iArr = UnitUtil.j(d / 100.0d);
            }
            healthTextView.setText(this.c.getString(R$string.IDS_ft_string, new Object[]{UnitUtil.e(iArr[0], 1, 0)}) + " " + this.c.getString(R$string.IDS_ins_string, new Object[]{UnitUtil.e(iArr[1], 1, 0)}));
            return;
        }
        healthTextView.setText(this.c.getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, new Object[]{UnitUtil.e(cfiVar == null ? 160 : cfiVar.d(), 1, 0)}));
    }

    public cfi d(cfi cfiVar, int i) {
        if (cfiVar == null) {
            cfiVar = new cfi();
            cfiVar.d(UUID.randomUUID().toString().replace(Constants.LINK, ""));
            cfiVar.b(true);
            cfiVar.e(2);
        } else {
            cfiVar.b(true);
        }
        cfiVar.a((byte) i);
        return cfiVar;
    }

    public cfi e(cfi cfiVar) {
        if (cfiVar == null) {
            LogUtil.h("AddUserOrEditWeightUserInteractor", "user is null");
            return cfiVar;
        }
        String i = cfiVar.i();
        String sid = MultiUsersManager.INSTANCE.getSid(i);
        cfiVar.a(sid);
        MultiUsersManager.INSTANCE.setUuidBySid(sid, i);
        return cfiVar;
    }

    public void d(cfi cfiVar, int i, IBaseResponseCallback iBaseResponseCallback) {
        if (UnitUtil.h()) {
            LogUtil.a("AddUserOrEditWeightUserInteractor", "enter showInchDialog");
            qpz.c(this.c, cfiVar, i, iBaseResponseCallback);
        } else {
            LogUtil.a("AddUserOrEditWeightUserInteractor", "enter showValueSetDialog()");
            qpz.b(this.c, i, iBaseResponseCallback);
        }
    }
}
