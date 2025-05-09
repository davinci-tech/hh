package defpackage;

import android.content.Intent;
import android.net.Uri;
import net.openid.appauth.AuthorizationManagementResponse;
import net.openid.appauth.JsonUtil;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class utu extends AuthorizationManagementResponse {
    public final utr c;
    public final String d;

    public static final class b {
        private String c;
        private utr e;

        public b(utr utrVar) {
            a(utrVar);
        }

        b fgm_(Uri uri) {
            c(uri.getQueryParameter("state"));
            return this;
        }

        public b a(utr utrVar) {
            this.e = (utr) utq.d(utrVar, "request cannot be null");
            return this;
        }

        public b c(String str) {
            this.c = utq.e(str, "state must not be empty");
            return this;
        }

        public utu d() {
            return new utu(this.e, this.c);
        }
    }

    private utu(utr utrVar, String str) {
        this.c = utrVar;
        this.d = str;
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public String getState() {
        return this.d;
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "request", this.c.jsonSerialize());
        JsonUtil.b(jSONObject, "state", this.d);
        return jSONObject;
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtra("net.openid.appauth.EndSessionResponse", jsonSerializeString());
        return intent;
    }
}
