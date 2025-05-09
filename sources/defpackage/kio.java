package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class kio {
    private static kio d;
    private static final Object e = new Object();
    private Context c;

    private kio(Context context) {
        this.c = context;
    }

    public static kio b() {
        kio kioVar;
        synchronized (e) {
            if (d == null) {
                d = new kio(BaseApplication.getContext());
            }
            kioVar = d;
        }
        return kioVar;
    }

    public void a(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            LogUtil.h("HwOpenPrivacyStatementManager", "HwOpenPrivacyStatementManager dataInfo is null");
            return;
        }
        String d2 = cvx.d(bArr);
        LogUtil.a("HwOpenPrivacyStatementManager", "HwOpenPrivacyStatementManager data ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwOpenPrivacyStatementManager", "HwOpenPrivacyStatementManager messageHex is error");
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                for (cwd cwdVar : e2) {
                    if (CommonUtil.a(cwdVar.e(), 16) == 1) {
                        b(cvx.e(cwdVar.c()));
                    }
                }
                return;
            }
            LogUtil.h("HwOpenPrivacyStatementManager", "HwOpenPrivacyStatementManager tlv is error");
        } catch (cwg unused) {
            LogUtil.b("HwOpenPrivacyStatementManager", "COMMAND_ID_GET_DATE TlvException");
        }
    }

    private boolean e(String str) {
        if (str == null) {
            return false;
        }
        if (Pattern.compile("[<>]").matcher(Normalizer.normalize(str, Normalizer.Form.NFKC)).find()) {
            LogUtil.a("HwOpenPrivacyStatementManager", " url is illegal.");
            return false;
        }
        LogUtil.a("HwOpenPrivacyStatementManager", "url is correct.");
        return str.contains("huawei") || str.contains("harmonyos.com");
    }

    private void b(String str) {
        if (e(str)) {
            LogUtil.h("HwOpenPrivacyStatementManager", "Enter openHttpsUrl httpUrl ", str);
            try {
                Intent intent = new Intent();
                intent.setData(Uri.parse(str));
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.setFlags(268435456);
                this.c.startActivity(intent);
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("HwOpenPrivacyStatementManager", "ActivityNotFoundException e = ", e2.getMessage());
            }
        }
    }
}
