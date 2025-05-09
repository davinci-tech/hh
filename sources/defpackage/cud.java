package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubUserAuthMsg;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cud {
    public static void e(Context context, int i, boolean z, boolean z2) {
        LogUtil.a("MultiUserAuthUtil", "dealReplyAuthResult, ", Integer.valueOf(i), " isAgree:", Boolean.valueOf(z2), r3.A, Boolean.valueOf(z));
        if (z) {
            if (z2) {
                nrh.b(context, R.string.IDS_device_wifi_my_qrcode_add_member_success_title);
                return;
            } else {
                nrh.b(context, R.string.IDS_hw_device_wifi_authorized_status_rejected);
                return;
            }
        }
        if (i == 112000010) {
            nrh.b(context, R.string.IDS_device_wifi_share_account_limit_content);
        } else {
            nrh.b(context, R.string.IDS_device_wifi_my_qrcode_add_member_failed_title);
        }
    }

    public static int a(List<WifiDeviceSubUserAuthMsg> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        Iterator<WifiDeviceSubUserAuthMsg> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getStatus() == 1) {
                i++;
            }
        }
        return i;
    }
}
