package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.pkr;
import defpackage.rsn;
import health.compact.a.LanguageUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BloodOxygenRangeProvider extends MinorProvider<pkr> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pkr pkrVar) {
        String e;
        LogUtil.a("BloodOxygenRangeProvider", "begin parseParams");
        if (pkrVar.g()) {
            hashMap.put("LEFT_TOP_VALUE", context.getString(R$string.IDS_hw_health_blood_oxygen_range));
            if (TextUtils.isEmpty(pkrVar.b())) {
                hashMap.put("LEFT_TEXT", dqA_("--"));
            } else {
                hashMap.put("LEFT_TEXT", dqA_(pkrVar.b()));
            }
            hashMap.put("RIGHT_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_range_elevation));
            if (TextUtils.isEmpty(pkrVar.d())) {
                hashMap.put("RIGHT_BOTTOM_TEXT", dqA_("--"));
                return;
            } else {
                hashMap.put("RIGHT_BOTTOM_TEXT", dqA_(pkrVar.d()));
                return;
            }
        }
        if (LanguageUtil.aw(BaseApplication.getContext())) {
            e = e(", ", pkrVar);
        } else {
            e = e(" ", pkrVar);
        }
        hashMap.put("LEFT_TOP_VALUE", e);
        if (TextUtils.isEmpty(pkrVar.e())) {
            hashMap.put("LEFT_TEXT", dqA_("--"));
        } else {
            hashMap.put("LEFT_TEXT", dqA_(pkrVar.e()));
        }
        hashMap.put("RIGHT_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_range));
        if (TextUtils.isEmpty(pkrVar.b())) {
            hashMap.put("RIGHT_BOTTOM_TEXT", dqA_("--"));
        } else {
            hashMap.put("RIGHT_BOTTOM_TEXT", dqA_(pkrVar.b()));
        }
    }

    private SpannableString dqA_(String str) {
        return rsn.dQG_(BaseApplication.getContext(), "[\\d\\-]", str, R.style.privacy_only_risk_num, R.style.privacy_only_risk_unit);
    }

    private String e(String str, pkr pkrVar) {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_blood_oxygen_latest_value) + str + pkrVar.c();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodOxygenRangeProvider";
    }
}
