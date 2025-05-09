package defpackage;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class stf {
    private static final String[] c = {"Service/app/v", "WiseCloudVirtualCardMgmtService/api/v1/client/getUserInfo"};
    private static volatile stf b = null;
    private static volatile boolean d = false;

    public static boolean e(String str) {
        if (d && !TextUtils.isEmpty(str)) {
            int length = c.length;
            for (int i = 0; i < length; i++) {
                if (str.contains(c[i])) {
                    return true;
                }
            }
        }
        return d && str.contains("WiseCloudWalletMallSpikeService");
    }
}
