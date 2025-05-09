package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.UrlConstant;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class k extends j {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        int i;
        char c;
        if (ho.a()) {
            ho.a("JsbClickComplianceEle", "start");
        }
        String optString = new JSONObject(str).optString(JsbMapKeyNames.H5_COMPLIANCE_TYPE, "");
        ho.b("JsbClickComplianceEle", "type:" + optString);
        ContentRecord b = b(context, str);
        if (b != null) {
            AppInfo ae = b.ae();
            if (ae != null) {
                optString.hashCode();
                switch (optString.hashCode()) {
                    case -1891164985:
                        if (optString.equals(UrlConstant.PRIVACY_URL)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1642034336:
                        if (optString.equals("permissionInWeb")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1431863008:
                        if (optString.equals("permissionUrl")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -649602745:
                        if (optString.equals("privacyInWeb")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -262766019:
                        if (optString.equals("appDetailUrl")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    ae.showPrivacyPolicy(a(context));
                } else if (c == 1) {
                    ae.showPermissionPageInWeb(a(context));
                } else if (c == 2) {
                    ae.showPermissionPage(a(context));
                } else if (c == 3) {
                    ae.showPrivacyPolicyInWeb(a(context));
                } else if (c == 4) {
                    com.huawei.openalliance.ad.inter.data.e a2 = pd.a(context, b, true);
                    MaterialClickInfo materialClickInfo = (MaterialClickInfo) com.huawei.openalliance.ad.utils.be.b(str, MaterialClickInfo.class, new Class[0]);
                    if (materialClickInfo == null || !com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c()) || materialClickInfo.a() == null) {
                        a2.showAppDetailPage(a(context));
                    } else {
                        a2.a(a(context), materialClickInfo);
                    }
                }
                i = 1000;
                a(remoteCallResultCallback, this.f7108a, i, null, true);
            }
            str2 = "appInfo not exist";
        } else {
            str2 = "ad not exist";
        }
        ho.a("JsbClickComplianceEle", str2);
        i = 3002;
        a(remoteCallResultCallback, this.f7108a, i, null, true);
    }

    public k() {
        super("pps.click.complianceele");
    }
}
