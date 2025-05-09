package net.openid.appauth;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.openalliance.ad.constant.VastAttribute;
import defpackage.utg;
import defpackage.uti;
import defpackage.utq;
import defpackage.uts;
import defpackage.utv;
import defpackage.utw;
import defpackage.utx;
import defpackage.utz;
import defpackage.uub;
import defpackage.uuc;
import defpackage.uue;
import defpackage.uuh;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Map;
import net.openid.appauth.browser.CustomTabManager;
import net.openid.appauth.connectivity.ConnectionBuilder;
import net.openid.appauth.internal.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AuthorizationService {

    /* renamed from: a, reason: collision with root package name */
    private final uuc f15276a;
    private boolean b;
    Context c;
    private final CustomTabManager d;
    private final uti e;

    /* loaded from: classes10.dex */
    public interface RegistrationResponseCallback {
        void onRegistrationRequestCompleted(utz utzVar, utg utgVar);
    }

    public interface TokenResponseCallback {
        void onTokenRequestCompleted(utv utvVar, utg utgVar);
    }

    public AuthorizationService(Context context) {
        this(context, uti.d);
    }

    public AuthorizationService(Context context, uti utiVar) {
        this(context, utiVar, uub.a(context, utiVar.c()), new CustomTabManager(context));
    }

    AuthorizationService(Context context, uti utiVar, uuc uucVar, CustomTabManager customTabManager) {
        this.b = false;
        this.c = (Context) utq.a(context);
        this.e = utiVar;
        this.d = customTabManager;
        this.f15276a = uucVar;
    }

    public void a(utx utxVar, ClientAuthentication clientAuthentication, TokenResponseCallback tokenResponseCallback) {
        d();
        Logger.b("Initiating code exchange request to %s", utxVar.e.e);
        new c(utxVar, clientAuthentication, this.e.e(), utw.d, tokenResponseCallback, Boolean.valueOf(this.e.a())).execute(new Void[0]);
    }

    public void c() {
        if (this.b) {
            return;
        }
        this.d.e();
        this.b = true;
    }

    private void d() {
        if (this.b) {
            throw new IllegalStateException("Service has been disposed and rendered inoperable");
        }
    }

    static class c extends AsyncTask<Void, Void, JSONObject> {

        /* renamed from: a, reason: collision with root package name */
        private final ConnectionBuilder f15277a;
        private Clock b;
        private ClientAuthentication c;
        private utg d;
        private TokenResponseCallback e;
        private boolean f;
        private utx g;

        c(utx utxVar, ClientAuthentication clientAuthentication, ConnectionBuilder connectionBuilder, Clock clock, TokenResponseCallback tokenResponseCallback, Boolean bool) {
            this.g = utxVar;
            this.c = clientAuthentication;
            this.f15277a = connectionBuilder;
            this.b = clock;
            this.e = tokenResponseCallback;
            this.f = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Not initialized variable reg: 2, insn: 0x00ae: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:32:0x00ae */
        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public JSONObject doInBackground(Void... voidArr) {
            JSONException e;
            InputStream inputStream;
            IOException e2;
            InputStream inputStream2;
            InputStream inputStream3 = null;
            try {
                try {
                    HttpURLConnection openConnection = this.f15277a.openConnection(this.g.e.e);
                    openConnection.setRequestMethod("POST");
                    openConnection.setRequestProperty("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
                    d(openConnection);
                    openConnection.setDoOutput(true);
                    Map<String, String> requestHeaders = this.c.getRequestHeaders(this.g.c);
                    if (requestHeaders != null) {
                        for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                            openConnection.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    Map<String, String> a2 = this.g.a();
                    Map<String, String> requestParameters = this.c.getRequestParameters(this.g.c);
                    if (requestParameters != null) {
                        a2.putAll(requestParameters);
                    }
                    String e3 = uuh.e(a2);
                    openConnection.setRequestProperty("Content-Length", String.valueOf(e3.length()));
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
                    outputStreamWriter.write(e3);
                    outputStreamWriter.flush();
                    if (openConnection.getResponseCode() >= 200 && openConnection.getResponseCode() < 300) {
                        inputStream = openConnection.getInputStream();
                    } else {
                        inputStream = openConnection.getErrorStream();
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream3 = inputStream2;
                    uue.c(inputStream3);
                    throw th;
                }
            } catch (IOException e4) {
                e2 = e4;
                inputStream = null;
            } catch (JSONException e5) {
                e = e5;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                uue.c(inputStream3);
                throw th;
            }
            try {
                JSONObject jSONObject = new JSONObject(uue.d(inputStream));
                uue.c(inputStream);
                return jSONObject;
            } catch (IOException e6) {
                e2 = e6;
                Logger.d(e2, "Failed to complete exchange request", new Object[0]);
                this.d = utg.c(utg.c.h, e2);
                uue.c(inputStream);
                return null;
            } catch (JSONException e7) {
                e = e7;
                Logger.d(e, "Failed to complete exchange request", new Object[0]);
                this.d = utg.c(utg.c.e, e);
                uue.c(inputStream);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(JSONObject jSONObject) {
            utg c;
            utg utgVar = this.d;
            if (utgVar != null) {
                this.e.onTokenRequestCompleted(null, utgVar);
                return;
            }
            if (jSONObject.has(VastAttribute.ERROR)) {
                try {
                    String string = jSONObject.getString(VastAttribute.ERROR);
                    c = utg.ffV_(utg.e.d(string), string, jSONObject.optString("error_description", null), uuh.fgy_(jSONObject.optString("error_uri")));
                } catch (JSONException e) {
                    c = utg.c(utg.c.e, e);
                }
                this.e.onTokenRequestCompleted(null, c);
                return;
            }
            try {
                utv b = new utv.e(this.g).e(jSONObject).b();
                if (b.d != null) {
                    try {
                        try {
                            uts.d(b.d).e(this.g, this.b, this.f);
                        } catch (utg e2) {
                            this.e.onTokenRequestCompleted(null, e2);
                            return;
                        }
                    } catch (JSONException | uts.b e3) {
                        this.e.onTokenRequestCompleted(null, utg.c(utg.c.d, e3));
                        return;
                    }
                }
                Logger.b("Token exchange with %s completed", this.g.e.e);
                this.e.onTokenRequestCompleted(b, null);
            } catch (JSONException e4) {
                this.e.onTokenRequestCompleted(null, utg.c(utg.c.e, e4));
            }
        }

        private void d(URLConnection uRLConnection) {
            if (TextUtils.isEmpty(uRLConnection.getRequestProperty("Accept"))) {
                uRLConnection.setRequestProperty("Accept", "application/json");
            }
        }
    }
}
