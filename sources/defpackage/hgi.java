package defpackage;

import android.app.Activity;
import android.net.Uri;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class hgi {
    private Activity c;
    private fdu e;

    public hgi(Activity activity) {
        this.c = activity;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void bde_(String str, int i, String str2, Uri uri) {
        char c;
        fdu fduVar = new fdu(8);
        this.e = fduVar;
        fduVar.j(str);
        int i2 = 1;
        this.e.b(1);
        if (i == 264 || i == 258) {
            this.e.b(6);
        }
        this.e.c((String) null);
        this.e.a((String) null);
        this.e.f(null);
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1581950918:
                if (str2.equals("shareWeBo")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -212289789:
                if (str2.equals("shareWeChatFriend")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 69012206:
                if (str2.equals("shareSystem")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 163616773:
                if (str2.equals("shareWeChat")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 590612483:
                if (str2.equals("sharefamily")) {
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
            i2 = 3;
        } else if (c != 1) {
            i2 = c != 2 ? c != 4 ? 2 : 6 : 5;
        }
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(this.c, i2, this.e);
    }
}
