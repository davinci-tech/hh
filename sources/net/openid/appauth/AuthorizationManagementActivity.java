package net.openid.appauth;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.constant.VastAttribute;
import defpackage.utg;
import defpackage.utk;
import net.openid.appauth.internal.Logger;
import org.json.JSONException;

/* loaded from: classes10.dex */
public class AuthorizationManagementActivity extends AppCompatActivity {

    /* renamed from: a, reason: collision with root package name */
    private PendingIntent f15275a;
    private PendingIntent b;
    private Intent c;
    private AuthorizationManagementRequest d;
    private boolean e = false;

    public static Intent ffY_(Context context, Uri uri) {
        Intent ffX_ = ffX_(context);
        ffX_.setData(uri);
        ffX_.addFlags(603979776);
        return ffX_;
    }

    private static Intent ffX_(Context context) {
        return new Intent(context, (Class<?>) AuthorizationManagementActivity.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (bundle == null) {
                fga_(getIntent().getExtras());
            } else {
                fga_(bundle);
            }
        } catch (RuntimeException e) {
            Log.e("AuthorizationManagementActivity", "onCreate->RuntimeException: " + e.toString());
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        Intent intent;
        super.onResume();
        if (this.e || (intent = this.c) == null) {
            return;
        }
        startActivity(intent);
        this.e = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            try {
                if (intent.getData() != null) {
                    b();
                    finish();
                }
            } catch (RuntimeException e) {
                Log.e("AuthorizationManagementActivity", "onResume->RuntimeException: " + e.toString());
                finish();
                return;
            }
        }
        a();
        finish();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("authStarted", this.e);
        bundle.putParcelable("authIntent", this.c);
        AuthorizationManagementRequest authorizationManagementRequest = this.d;
        bundle.putString("authRequest", authorizationManagementRequest == null ? null : authorizationManagementRequest.jsonSerializeString());
        bundle.putString("authRequestType", utk.b(this.d));
        bundle.putParcelable("completeIntent", this.b);
        bundle.putParcelable("cancelIntent", this.f15275a);
    }

    private void b() {
        Uri data = getIntent().getData();
        Intent ffZ_ = ffZ_(data);
        if (ffZ_ == null) {
            Logger.e("Failed to extract OAuth2 response from redirect", new Object[0]);
        } else {
            ffZ_.setData(data);
            fgb_(this.b, ffZ_, -1);
        }
    }

    private void a() {
        Logger.b("Authorization flow canceled by user", new Object[0]);
        fgb_(this.f15275a, utg.c(utg.c.j, (Throwable) null).ffW_(), 0);
    }

    private void fga_(Bundle bundle) {
        if (bundle == null) {
            Logger.c("No stored state - unable to handle response", new Object[0]);
            finish();
            return;
        }
        this.c = (Intent) bundle.getParcelable("authIntent");
        this.e = bundle.getBoolean("authStarted", false);
        this.b = (PendingIntent) bundle.getParcelable("completeIntent");
        this.f15275a = (PendingIntent) bundle.getParcelable("cancelIntent");
        try {
            String string = bundle.getString("authRequest", null);
            this.d = string != null ? utk.c(string, bundle.getString("authRequestType", null)) : null;
        } catch (JSONException unused) {
            fgb_(this.f15275a, utg.d.f17541a.ffW_(), 0);
        }
    }

    private void fgb_(PendingIntent pendingIntent, Intent intent, int i) {
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this, 0, intent);
                return;
            } catch (PendingIntent.CanceledException e) {
                Logger.e("Failed to send cancel intent", e);
                return;
            }
        }
        setResult(i, intent);
    }

    private Intent ffZ_(Uri uri) {
        if (uri.getQueryParameterNames().contains(VastAttribute.ERROR)) {
            return utg.ffU_(uri).ffW_();
        }
        AuthorizationManagementResponse fgc_ = utk.fgc_(this.d, uri);
        if ((this.d.getState() == null && fgc_.getState() != null) || (this.d.getState() != null && !this.d.getState().equals(fgc_.getState()))) {
            Logger.c("State returned in authorization response (%s) does not match state from request (%s) - discarding response", fgc_.getState(), this.d.getState());
            return utg.d.g.ffW_();
        }
        return fgc_.toIntent();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
