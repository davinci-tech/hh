package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import net.openid.appauth.AuthState;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class lox {
    private static final Object c = new Object();
    private static volatile lox e;

    /* renamed from: a, reason: collision with root package name */
    private final ReentrantLock f14819a = new ReentrantLock();
    private final AtomicReference<AuthState> b = new AtomicReference<>();
    private final SharedPreferences d;

    public static lox d(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new lox(context.getApplicationContext());
                }
            }
        }
        return e;
    }

    private lox(Context context) {
        this.d = context.getSharedPreferences("AuthState", 0);
    }

    public AuthState d() {
        if (this.b.get() != null) {
            return this.b.get();
        }
        AuthState b = b();
        return ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.b, null, b) ? b : this.b.get();
    }

    public AuthState d(AuthState authState) {
        b(authState);
        this.b.set(authState);
        return authState;
    }

    public AuthState e(utl utlVar, utg utgVar) {
        AuthState d = d();
        d.d(utlVar, utgVar);
        return d(d);
    }

    public AuthState e(utv utvVar, utg utgVar) {
        AuthState d = d();
        d.a(utvVar, utgVar);
        return d(d);
    }

    private AuthState b() {
        AuthState authState;
        this.f14819a.lock();
        try {
            String string = this.d.getString("state", null);
            if (string == null) {
                authState = new AuthState();
            } else {
                try {
                    authState = AuthState.e(string);
                } catch (JSONException unused) {
                    Log.w("AuthStateManager", "Failed to deserialize stored auth state - discarding");
                    authState = new AuthState();
                }
            }
            return authState;
        } finally {
            this.f14819a.unlock();
        }
    }

    private void b(AuthState authState) {
        this.f14819a.lock();
        try {
            SharedPreferences.Editor edit = this.d.edit();
            if (authState == null) {
                edit.remove("state");
            } else {
                edit.putString("state", authState.h());
            }
            if (edit.commit()) {
            } else {
                throw new IllegalStateException("Failed to write state to shared prefs");
            }
        } finally {
            this.f14819a.unlock();
        }
    }
}
