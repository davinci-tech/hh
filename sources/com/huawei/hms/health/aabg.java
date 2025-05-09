package com.huawei.hms.health;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.HuaweiHiHealth;
import com.huawei.hms.hihealth.SettingController;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.operation.view.ConfigConstants;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes4.dex */
public class aabg extends Fragment {
    private View aab;
    private WebView aaba;
    private TextView aabb;
    private String aabc;
    private AuthAccount aabd;
    private SettingController aabe;
    private Activity aabf;
    private int aabg;

    private boolean aab(String str) {
        String str2;
        String str3;
        if (!URLUtil.isHttpsUrl(str)) {
            return false;
        }
        try {
            str2 = new URL(str.replaceAll("[\\\\#]", "/")).getHost();
        } catch (MalformedURLException unused) {
            aabz.aab("HealthKitWebView", "getHostByURI error  MalformedURLException");
            str2 = "";
        }
        String[] strArr = {ConfigConstants.WATCH_FACE_NEW_URL, ConfigConstants.WATCH_FACE_URL, "hwcloudtest.cn"};
        for (int i = 0; i < 3; i++) {
            String str4 = strArr[i];
            if (str2.endsWith(str4)) {
                try {
                    String substring = str2.substring(0, str2.length() - str4.length());
                    if (substring.endsWith(".")) {
                        return substring.matches("^[A-Za-z0-9.-]+$");
                    }
                    return false;
                } catch (IndexOutOfBoundsException unused2) {
                    str3 = "catch IndexOutOfBoundsException";
                    aabz.aab("HealthKitWebView", str3);
                    return false;
                } catch (Throwable unused3) {
                    str3 = "catch exception";
                    aabz.aab("HealthKitWebView", str3);
                    return false;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void aaba() {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.health.aabg.aaba():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aab(int i) {
        Activity activity;
        AuthAccount authAccount;
        int i2;
        try {
            if (i == -1) {
                activity = this.aabf;
                authAccount = this.aabd;
                i2 = 0;
            } else {
                activity = this.aabf;
                authAccount = this.aabd;
                i2 = HiHealthStatusCodes.HEALTH_APP_NOT_AUTHORISED;
            }
            activity.setResult(-1, aacs.aab(i2, authAccount));
            this.aabf.finish();
        } catch (Throwable unused) {
            aabz.aab("HealthKitWebView", "setHealthAuthResult has exception");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            FragmentActivity activity = getActivity();
            this.aabf = activity;
            this.aabe = HuaweiHiHealth.getSettingController((Activity) activity);
            if (this.aab == null) {
                this.aab = layoutInflater.inflate(R.layout.fragment_health_kit_web_view, viewGroup, false);
            }
            aab();
            aaba();
            return this.aab;
        } catch (Throwable unused) {
            aabz.aab("HealthKitWebView", "webView onCreateView has exception");
            return null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(new aaba(true));
    }

    class aab implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            aabg.this.aabf.onBackPressed();
        }

        aab() {
        }
    }

    class aaba extends OnBackPressedCallback {
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            aabg.this.aab(1);
        }

        aaba(boolean z) {
            super(z);
        }
    }

    class aabb extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (str == null || !("healthkit_vue".equals(str) || str.contains(aabg.this.aabc))) {
                aabg.this.aabb.setText(str);
                aabg.this.aabb.setVisibility(8);
            }
        }

        aabb() {
        }
    }

    private void aab() {
        this.aaba = (WebView) this.aab.findViewById(R.id.healthkit_webView);
        TextView textView = (TextView) this.aab.findViewById(R.id.healthkit_auth_title);
        this.aabb = textView;
        textView.setVisibility(8);
        ((ImageView) this.aab.findViewById(R.id.healthkit_auth_back_icon)).setOnClickListener(new aab());
    }

    class aabc {
        /* synthetic */ aabc(aab aabVar) {
        }

        @JavascriptInterface
        public void invoke() throws Exception {
            try {
                aabz.aabb("HealthKitWebView", "AuthorizationtoJs invoke is Called");
                if (aabg.this.aabg == 1) {
                    aabg.this.aabf.setResult(-2, null);
                    aabg.this.aabf.finish();
                }
                if (aabg.this.aabe.openAuthFromCloud()) {
                    aabg.this.aab(-1);
                } else {
                    aabg.this.aab(1);
                }
            } catch (Throwable unused) {
                aabz.aab("HealthKitWebView", "WebAuthorizationtoJs has exception");
            }
        }
    }
}
