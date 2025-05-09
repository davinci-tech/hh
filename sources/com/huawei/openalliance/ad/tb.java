package com.huawei.openalliance.ad;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.huawei.ohos.localability.AbilityUtils;
import java.util.List;

/* loaded from: classes5.dex */
public class tb {
    public void a(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.addFlags(268435456);
        intent.putExtra("ohos.extra.param.key.INSTALL_ON_DEMAND", true);
        AbilityUtils.startAbility(context, intent);
    }

    public void a(Context context, Intent intent) {
        AbilityUtils.startAbility(context, intent);
    }

    public List<Intent> a(String str) {
        return AbilityUtils.getLaunchIntents(str);
    }
}
