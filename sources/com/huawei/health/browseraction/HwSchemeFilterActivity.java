package com.huawei.health.browseraction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.browseraction.HwSchemeFilterActivity;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity;
import defpackage.buc;
import defpackage.dzn;

/* loaded from: classes8.dex */
public class HwSchemeFilterActivity extends Activity {
    private Context c = null;
    private String e;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_HwSchemeFilterActivity", "intent == null");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            LogUtil.h("Suggestion_HwSchemeFilterActivity", "schemeData == null");
            return;
        }
        String scheme = data.getScheme();
        String host = data.getHost();
        LogUtil.a("Suggestion_HwSchemeFilterActivity", "schemeName:", scheme, " host:", host, " path:", data.getPath());
        if (CommonConstant.ACTION.HWID_SCHEME_URL.equals(intent.getAction()) && CQ_(data)) {
            LogUtil.a("Suggestion_HwSchemeFilterActivity", "begin to deal with route");
            e(host);
        } else {
            LogUtil.h("Suggestion_HwSchemeFilterActivity", "is not match Intent.ACTION_VIEW");
        }
    }

    private void e(String str) {
        if (!dzn.i()) {
            Intent launchIntentForPackage = this.c.getPackageManager().getLaunchIntentForPackage(this.c.getPackageName());
            launchIntentForPackage.putExtra("schemeTrackFilePath", this.e);
            launchIntentForPackage.addFlags(AppRouterExtras.COLDSTART);
            LogUtil.a("Suggestion_HwSchemeFilterActivity", "no login");
            startActivity(launchIntentForPackage);
        } else {
            Intent intent = new Intent(this.c, (Class<?>) MyRouteDetailActivity.class);
            intent.putExtra("fromFlag", "file_flag");
            intent.putExtra("filePath", this.e);
            intent.setFlags(603979776);
            intent.putExtra("from", str);
            startActivity(intent);
        }
        finish();
    }

    private String CP_(Uri uri) {
        String str = "";
        if (uri == null || uri.getScheme() == null) {
            LogUtil.h("Suggestion_HwSchemeFilterActivity", "uri is null");
            return "";
        }
        String scheme = uri.getScheme();
        scheme.hashCode();
        if (scheme.equals("file")) {
            str = uri.getPath().split("/")[r10.length - 1];
        } else if (scheme.equals("content")) {
            try {
                Cursor query = getContentResolver().query(uri, null, null, null, null);
                try {
                    str = buc.uQ_(query);
                    if (query != null) {
                        query.close();
                    }
                } finally {
                }
            } catch (SecurityException unused) {
                LogUtil.b("Suggestion_HwSchemeFilterActivity", "query file address, catch securityException");
            }
        }
        LogUtil.a("Suggestion_HwSchemeFilterActivity", "trackFileName = ", str);
        return str;
    }

    private boolean CQ_(Uri uri) {
        String CP_ = CP_(uri);
        if (TextUtils.isEmpty(CP_) || (!CP_.endsWith(NavigationFileParser.GPX) && !CP_.endsWith(NavigationFileParser.TCX) && !CP_.endsWith(NavigationFileParser.KML))) {
            Toast.makeText(this.c, R.string._2130838788_res_0x7f020504, 0).show();
            return false;
        }
        this.e = buc.uR_(uri, CP_);
        return true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        HandlerExecutor.d(new Runnable() { // from class: ccf
            @Override // java.lang.Runnable
            public final void run() {
                HwSchemeFilterActivity.this.e();
            }
        }, 200L);
    }

    public /* synthetic */ void e() {
        setIntent(null);
        finish();
        LogUtil.a("Suggestion_HwSchemeFilterActivity", "HwSchemeFilterActivity onFinish");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
