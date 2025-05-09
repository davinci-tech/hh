package net.openid.appauth;

import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import defpackage.utg;
import defpackage.utl;
import defpackage.uto;
import defpackage.utp;
import defpackage.utq;
import defpackage.utt;
import defpackage.utv;
import defpackage.utw;
import defpackage.utx;
import defpackage.utz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.internal.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AuthState {

    /* renamed from: a, reason: collision with root package name */
    private utv f15274a;
    private utl b;
    private AuthorizationServiceConfiguration c;
    private utz d;
    private utg e;
    private List<AuthStateAction> f;
    private boolean g;
    private String h;
    private final Object i = new Object();
    private String j;

    public interface AuthStateAction {
        void execute(String str, String str2, utg utgVar);
    }

    public AuthState() {
    }

    public AuthState(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
        this.c = authorizationServiceConfiguration;
    }

    public AuthorizationServiceConfiguration b() {
        utl utlVar = this.b;
        if (utlVar != null) {
            return utlVar.g.f;
        }
        return this.c;
    }

    public String d() {
        if (this.e != null) {
            return null;
        }
        utv utvVar = this.f15274a;
        if (utvVar != null && utvVar.f17552a != null) {
            return this.f15274a.f17552a;
        }
        utl utlVar = this.b;
        if (utlVar != null) {
            return utlVar.c;
        }
        return null;
    }

    public Long e() {
        if (this.e != null) {
            return null;
        }
        utv utvVar = this.f15274a;
        if (utvVar != null && utvVar.f17552a != null) {
            return this.f15274a.c;
        }
        utl utlVar = this.b;
        if (utlVar == null || utlVar.c == null) {
            return null;
        }
        return this.b.d;
    }

    public String g() {
        if (this.e != null) {
            return null;
        }
        utv utvVar = this.f15274a;
        if (utvVar != null && utvVar.d != null) {
            return this.f15274a.d;
        }
        utl utlVar = this.b;
        if (utlVar != null) {
            return utlVar.b;
        }
        return null;
    }

    public String c() {
        utz utzVar = this.d;
        if (utzVar != null) {
            return utzVar.e;
        }
        return null;
    }

    public boolean f() {
        return this.e == null && !(d() == null && g() == null);
    }

    boolean c(Clock clock) {
        if (this.g) {
            return true;
        }
        return e() == null ? d() == null : e().longValue() <= clock.getCurrentTimeMillis() + 60000;
    }

    public void d(utl utlVar, utg utgVar) {
        utq.e((utgVar != null) ^ (utlVar != null), "exactly one of authResponse or authException should be non-null");
        if (utgVar != null) {
            if (utgVar.b == 1) {
                this.e = utgVar;
            }
        } else {
            this.b = utlVar;
            this.c = null;
            this.f15274a = null;
            this.h = null;
            this.e = null;
            this.j = utlVar.i != null ? utlVar.i : utlVar.g.q;
        }
    }

    public void a(utv utvVar, utg utgVar) {
        utq.e((utgVar != null) ^ (utvVar != null), "exactly one of tokenResponse or authException should be non-null");
        utg utgVar2 = this.e;
        if (utgVar2 != null) {
            Logger.c("AuthState.update should not be called in an error state (%s), call updatewith the result of the fresh authorization response first", utgVar2);
            this.e = null;
        }
        if (utgVar != null) {
            if (utgVar.b == 2) {
                this.e = utgVar;
            }
        } else {
            this.f15274a = utvVar;
            if (utvVar.i != null) {
                this.j = utvVar.i;
            }
            if (utvVar.e != null) {
                this.h = utvVar.e;
            }
        }
    }

    public void c(AuthorizationService authorizationService, AuthStateAction authStateAction) {
        d(authorizationService, utt.b, Collections.emptyMap(), utw.d, authStateAction);
    }

    void d(AuthorizationService authorizationService, ClientAuthentication clientAuthentication, Map<String, String> map, Clock clock, AuthStateAction authStateAction) {
        utq.d(authorizationService, "service cannot be null");
        utq.d(clientAuthentication, "client authentication cannot be null");
        utq.d(map, "additional params cannot be null");
        utq.d(clock, "clock cannot be null");
        utq.d(authStateAction, "action cannot be null");
        if (!c(clock)) {
            authStateAction.execute(d(), g(), null);
            return;
        }
        if (this.h == null) {
            authStateAction.execute(null, null, utg.c(utg.d.d, new IllegalStateException("No refresh token available and token have expired")));
            return;
        }
        utq.d(this.i, "pending actions sync object cannot be null");
        synchronized (this.i) {
            List<AuthStateAction> list = this.f;
            if (list != null) {
                list.add(authStateAction);
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.f = arrayList;
            arrayList.add(authStateAction);
            authorizationService.a(b(map), clientAuthentication, new AuthorizationService.TokenResponseCallback() { // from class: net.openid.appauth.AuthState.3
                @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
                public void onTokenRequestCompleted(utv utvVar, utg utgVar) {
                    String str;
                    String str2;
                    List list2;
                    AuthState.this.a(utvVar, utgVar);
                    if (utgVar == null) {
                        AuthState.this.g = false;
                        String d = AuthState.this.d();
                        str2 = AuthState.this.g();
                        str = d;
                        utgVar = null;
                    } else {
                        str = null;
                        str2 = null;
                    }
                    synchronized (AuthState.this.i) {
                        list2 = AuthState.this.f;
                        AuthState.this.f = null;
                    }
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        ((AuthStateAction) it.next()).execute(str, str2, utgVar);
                    }
                }
            });
        }
    }

    public utx b(Map<String, String> map) {
        if (this.h == null) {
            throw new IllegalStateException("No refresh token available for refresh request");
        }
        utl utlVar = this.b;
        if (utlVar == null) {
            throw new IllegalStateException("No authorization configuration available for refresh request");
        }
        return new utx.c(utlVar.g.f, this.b.g.d).e("refresh_token").i(null).g(this.h).a(map).b();
    }

    public JSONObject j() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.b(jSONObject, FaqConstants.FAQ_REFRESH, this.h);
        JsonUtil.b(jSONObject, "scope", this.j);
        AuthorizationServiceConfiguration authorizationServiceConfiguration = this.c;
        if (authorizationServiceConfiguration != null) {
            JsonUtil.c(jSONObject, NetworkService.Constants.CONFIG_SERVICE, authorizationServiceConfiguration.e());
        }
        utg utgVar = this.e;
        if (utgVar != null) {
            JsonUtil.c(jSONObject, "mAuthorizationException", utgVar.a());
        }
        utl utlVar = this.b;
        if (utlVar != null) {
            JsonUtil.c(jSONObject, "lastAuthorizationResponse", utlVar.jsonSerialize());
        }
        utv utvVar = this.f15274a;
        if (utvVar != null) {
            JsonUtil.c(jSONObject, "mLastTokenResponse", utvVar.c());
        }
        utz utzVar = this.d;
        if (utzVar != null) {
            JsonUtil.c(jSONObject, "lastRegistrationResponse", utzVar.e());
        }
        return jSONObject;
    }

    public String h() {
        return j().toString();
    }

    public static AuthState a(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json cannot be null");
        AuthState authState = new AuthState();
        authState.h = JsonUtil.d(jSONObject, FaqConstants.FAQ_REFRESH);
        authState.j = JsonUtil.d(jSONObject, "scope");
        if (jSONObject.has(NetworkService.Constants.CONFIG_SERVICE)) {
            authState.c = AuthorizationServiceConfiguration.d(jSONObject.getJSONObject(NetworkService.Constants.CONFIG_SERVICE));
        }
        if (jSONObject.has("mAuthorizationException")) {
            authState.e = utg.c(jSONObject.getJSONObject("mAuthorizationException"));
        }
        if (jSONObject.has("lastAuthorizationResponse")) {
            authState.b = utl.d(jSONObject.getJSONObject("lastAuthorizationResponse"));
        }
        if (jSONObject.has("mLastTokenResponse")) {
            authState.f15274a = utv.d(jSONObject.getJSONObject("mLastTokenResponse"));
        }
        if (jSONObject.has("lastRegistrationResponse")) {
            authState.d = utz.e(jSONObject.getJSONObject("lastRegistrationResponse"));
        }
        return authState;
    }

    public static AuthState e(String str) throws JSONException {
        utq.d(str, (Object) "jsonStr cannot be null or empty");
        return a(new JSONObject(str));
    }

    public ClientAuthentication a() throws ClientAuthentication.e {
        char c;
        if (c() == null) {
            return utt.b;
        }
        if (this.d.g == null) {
            return new uto(c());
        }
        String str = this.d.g;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -2034587045) {
            if (str.equals("client_secret_post")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 3387192) {
            if (hashCode == 1338964435 && str.equals("client_secret_basic")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("none")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return new utp(c());
        }
        if (c == 1) {
            return utt.b;
        }
        if (c == 2) {
            return new uto(c());
        }
        throw new ClientAuthentication.e(this.d.g);
    }
}
