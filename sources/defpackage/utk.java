package defpackage;

import android.net.Uri;
import android.util.Base64;
import defpackage.utl;
import defpackage.utu;
import java.security.SecureRandom;
import net.openid.appauth.AuthorizationManagementRequest;
import net.openid.appauth.AuthorizationManagementResponse;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utk {
    utk() {
    }

    static String a() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }

    public static String b(AuthorizationManagementRequest authorizationManagementRequest) {
        if (authorizationManagementRequest instanceof uth) {
            return "authorization";
        }
        if (authorizationManagementRequest instanceof utr) {
            return "end_session";
        }
        return null;
    }

    public static AuthorizationManagementRequest c(String str, String str2) throws JSONException {
        utq.d(str, (Object) "jsonStr can not be null");
        JSONObject jSONObject = new JSONObject(str);
        if ("authorization".equals(str2)) {
            return uth.b(jSONObject);
        }
        if ("end_session".equals(str2)) {
            return utr.b(jSONObject);
        }
        throw new IllegalArgumentException("No AuthorizationManagementRequest found matching to this json schema");
    }

    public static AuthorizationManagementResponse fgc_(AuthorizationManagementRequest authorizationManagementRequest, Uri uri) {
        if (authorizationManagementRequest instanceof uth) {
            return new utl.e((uth) authorizationManagementRequest).fgf_(uri).b();
        }
        if (authorizationManagementRequest instanceof utr) {
            return new utu.b((utr) authorizationManagementRequest).fgm_(uri).d();
        }
        throw new IllegalArgumentException("Malformed request or uri");
    }
}
