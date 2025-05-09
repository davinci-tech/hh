package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import java.nio.charset.Charset;
import java.util.List;

/* loaded from: classes5.dex */
public class kje {
    public static void c(byte[] bArr) {
        kja a2 = a(bArr);
        if (a2 == null) {
            return;
        }
        e(a2.c(), a2.d());
    }

    public static List<cwd> d(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("VoipSendCommandUtils", "parseBytesToTlv dataInfos is invalid");
            return null;
        }
        String d = cvx.d(bArr);
        try {
            return new cwl().a(d.substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("VoipSendCommandUtils", "parseBytesToTlv TlvException");
            return null;
        }
    }

    private static kja a(byte[] bArr) {
        List<cwd> d = d(bArr);
        if (d == null) {
            LogUtil.h("VoipSendCommandUtils", "generateSmsReceivedInfo tlvList is null");
            return null;
        }
        String str = "";
        int i = 2;
        String str2 = "";
        for (cwd cwdVar : d) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                str = cvx.e(cwdVar.c());
            } else if (w == 2) {
                i = CommonUtil.w(cwdVar.c());
            } else if (w == 3) {
                str2 = cwdVar.c();
            }
        }
        kja kjaVar = new kja();
        kjaVar.b(str);
        kjaVar.b(i);
        if (!TextUtils.isEmpty(str2)) {
            kjaVar.e(e(i, str2));
        }
        return kjaVar;
    }

    private static String e(int i, String str) {
        if (i == 3) {
            return new String(cvx.a(str), Charset.forName("utf16"));
        }
        return cvx.e(str);
    }

    private static void e(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.ACTION_NOTIFICATION_PUSH");
        intent.putExtra("action_type", "VOIP");
        intent.putExtra("key_replay_destination", str);
        intent.putExtra("key_replay_content", str2);
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), intent);
    }
}
