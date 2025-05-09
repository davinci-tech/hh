package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.userprofile.card.familycard.beans.FamilyHealthCardInfo;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes7.dex */
public class rzz {
    public static void a(FamilyHealthCardInfo familyHealthCardInfo) {
        if (familyHealthCardInfo == null) {
            return;
        }
        String a2 = nrv.a(familyHealthCardInfo);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "family_health_card_data", a2, (StorageParams) null);
    }

    public static FamilyHealthCardInfo a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "family_health_card_data");
        if (TextUtils.isEmpty(b)) {
            return new FamilyHealthCardInfo();
        }
        try {
            return (FamilyHealthCardInfo) nrv.b(b, FamilyHealthCardInfo.class);
        } catch (JsonSyntaxException | NumberFormatException e) {
            LogUtil.b("FamilyHealthCardCacheUtil", LogAnonymous.b(e));
            return new FamilyHealthCardInfo();
        }
    }
}
