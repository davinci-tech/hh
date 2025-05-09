package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import com.huawei.hms.support.api.entity.common.CommonConstant;

/* loaded from: classes3.dex */
public class bex {
    public static Intent pM_() {
        if (!beu.d(bec.e(), "com.tencent.mm")) {
            bes.e("WeiXinUtil", "weixin not install");
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
        intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
        intent.setFlags(335544320);
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        bes.e("WeiXinUtil", "openWeiXinScan");
        return intent;
    }
}
