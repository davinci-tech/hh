package net.openid.appauth;

import android.net.Uri;
import android.os.AsyncTask;
import defpackage.utg;
import defpackage.utn;
import defpackage.utq;
import defpackage.uue;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import net.openid.appauth.connectivity.ConnectionBuilder;
import net.openid.appauth.internal.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AuthorizationServiceConfiguration {

    /* renamed from: a, reason: collision with root package name */
    public final Uri f15278a;
    public final Uri b;
    public final utn c;
    public final Uri d;
    public final Uri e;

    public interface RetrieveConfigurationCallback {
        void onFetchConfigurationCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration, utg utgVar);
    }

    public AuthorizationServiceConfiguration(Uri uri, Uri uri2) {
        this(uri, uri2, null);
    }

    public AuthorizationServiceConfiguration(Uri uri, Uri uri2, Uri uri3) {
        this(uri, uri2, uri3, null);
    }

    public AuthorizationServiceConfiguration(Uri uri, Uri uri2, Uri uri3, Uri uri4) {
        this.f15278a = (Uri) utq.a(uri);
        this.e = (Uri) utq.a(uri2);
        this.d = uri3;
        this.b = uri4;
        this.c = null;
    }

    public AuthorizationServiceConfiguration(utn utnVar) {
        utq.d(utnVar, "docJson cannot be null");
        this.c = utnVar;
        this.f15278a = utnVar.fgi_();
        this.e = utnVar.fgl_();
        this.d = utnVar.fgk_();
        this.b = utnVar.fgj_();
    }

    public JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.e(jSONObject, "authorizationEndpoint", this.f15278a.toString());
        JsonUtil.e(jSONObject, "tokenEndpoint", this.e.toString());
        Uri uri = this.d;
        if (uri != null) {
            JsonUtil.e(jSONObject, "registrationEndpoint", uri.toString());
        }
        Uri uri2 = this.b;
        if (uri2 != null) {
            JsonUtil.e(jSONObject, "endSessionEndpoint", uri2.toString());
        }
        utn utnVar = this.c;
        if (utnVar != null) {
            JsonUtil.c(jSONObject, "discoveryDoc", utnVar.an);
        }
        return jSONObject;
    }

    public String b() {
        return e().toString();
    }

    public static AuthorizationServiceConfiguration d(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json object cannot be null");
        if (jSONObject.has("discoveryDoc")) {
            try {
                return new AuthorizationServiceConfiguration(new utn(jSONObject.optJSONObject("discoveryDoc")));
            } catch (utn.a e) {
                throw new JSONException("Missing required field in discovery doc: " + e.d());
            }
        }
        utq.e(jSONObject.has("authorizationEndpoint"), "missing authorizationEndpoint");
        utq.e(jSONObject.has("tokenEndpoint"), "missing tokenEndpoint");
        return new AuthorizationServiceConfiguration(JsonUtil.fgn_(jSONObject, "authorizationEndpoint"), JsonUtil.fgn_(jSONObject, "tokenEndpoint"), JsonUtil.fgo_(jSONObject, "registrationEndpoint"), JsonUtil.fgo_(jSONObject, "endSessionEndpoint"));
    }

    public static void fgh_(Uri uri, RetrieveConfigurationCallback retrieveConfigurationCallback, ConnectionBuilder connectionBuilder) {
        utq.d(uri, "openIDConnectDiscoveryUri cannot be null");
        utq.d(retrieveConfigurationCallback, "callback cannot be null");
        utq.d(connectionBuilder, "connectionBuilder must not be null");
        new c(uri, connectionBuilder, retrieveConfigurationCallback).execute(new Void[0]);
    }

    static class c extends AsyncTask<Void, Void, AuthorizationServiceConfiguration> {

        /* renamed from: a, reason: collision with root package name */
        private RetrieveConfigurationCallback f15279a;
        private Uri b;
        private utg c = null;
        private ConnectionBuilder e;

        c(Uri uri, ConnectionBuilder connectionBuilder, RetrieveConfigurationCallback retrieveConfigurationCallback) {
            this.b = uri;
            this.e = connectionBuilder;
            this.f15279a = retrieveConfigurationCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Not initialized variable reg: 1, insn: 0x0076: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:20:0x0076 */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthorizationServiceConfiguration doInBackground(Void... voidArr) {
            utn.a e;
            InputStream inputStream;
            JSONException e2;
            IOException e3;
            InputStream inputStream2;
            InputStream inputStream3 = null;
            try {
                try {
                    HttpURLConnection openConnection = this.e.openConnection(this.b);
                    openConnection.setRequestMethod("GET");
                    openConnection.setDoInput(true);
                    openConnection.connect();
                    inputStream = openConnection.getInputStream();
                    try {
                        AuthorizationServiceConfiguration authorizationServiceConfiguration = new AuthorizationServiceConfiguration(new utn(new JSONObject(uue.d(inputStream))));
                        uue.c(inputStream);
                        return authorizationServiceConfiguration;
                    } catch (IOException e4) {
                        e3 = e4;
                        Logger.b(e3, "Network error when retrieving discovery document", new Object[0]);
                        this.c = utg.c(utg.c.h, e3);
                        uue.c(inputStream);
                        return null;
                    } catch (JSONException e5) {
                        e2 = e5;
                        Logger.b(e2, "Error parsing discovery document", new Object[0]);
                        this.c = utg.c(utg.c.e, e2);
                        uue.c(inputStream);
                        return null;
                    } catch (utn.a e6) {
                        e = e6;
                        Logger.b(e, "Malformed discovery document", new Object[0]);
                        this.c = utg.c(utg.c.b, e);
                        uue.c(inputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream3 = inputStream2;
                    uue.c(inputStream3);
                    throw th;
                }
            } catch (IOException e7) {
                e3 = e7;
                inputStream = null;
            } catch (JSONException e8) {
                e2 = e8;
                inputStream = null;
            } catch (utn.a e9) {
                e = e9;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                uue.c(inputStream3);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
            utg utgVar = this.c;
            if (utgVar != null) {
                this.f15279a.onFetchConfigurationCompleted(null, utgVar);
            } else {
                this.f15279a.onFetchConfigurationCompleted(authorizationServiceConfiguration, null);
            }
        }
    }
}
