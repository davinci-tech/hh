package com.huawei.hms.health;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.health.R;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.HuaweiHiHealth;
import com.huawei.hms.hihealth.SettingController;
import com.huawei.hms.hihealth.result.HealthKitAuthResult;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class aaba extends Fragment {
    private View aab;
    private Activity aaba;
    private SettingController aabb;
    private AuthAccount aabc;
    private String[] aabd;
    private boolean aabe;

    /* JADX INFO: Access modifiers changed from: private */
    public void aab(int i) {
        aabz.aabb("HealthKitAuthHubFragment", "auth fail with errorCode is " + i);
        this.aaba.setResult(-1, aacs.aab(i, this.aabc));
        this.aaba.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaba(boolean z) {
        if (z) {
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/achievement?module=kit"));
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setComponent(null);
                intent.setSelector(null);
                intent.setPackage("com.huawei.health");
                if (intent.resolveActivity(this.aaba.getPackageManager()) == null || !aacs.aab(this.aaba, "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", "com.huawei.health")) {
                    aabz.aabb("HealthKitAuthHubFragment", "handleCheckAuthFail begin to open H5");
                    this.aabb.getAuthUrl().addOnSuccessListener(new com.huawei.hms.health.aabd(this)).addOnFailureListener(new com.huawei.hms.health.aabc(this));
                } else {
                    aabz.aabb("HealthKitAuthHubFragment", "handleCheckAuthFail begin to open activity from health app");
                    startActivityForResult(intent, 1004);
                }
                return;
            } catch (IllegalArgumentException unused) {
                aabz.aabb("HealthKitAuthHubFragment", "handleCheckAuthFail is IllegalArgumentException");
            }
        }
        aab(HiHealthStatusCodes.HEALTH_APP_NOT_AUTHORISED);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        aabz.aabb("HealthKitAuthHubFragment", "onActivityResult requestCode:" + i);
        try {
            aab(i, intent);
            if (i == 1003) {
                aabz.aabb("HealthKitAuthHubFragment", "handleHealthAuthResult to checkAuthorizeHealth" + i2);
                if (i2 == -1) {
                    aab();
                } else {
                    aab(HiHealthStatusCodes.HEALTH_APP_NOT_AUTHORISED);
                }
            }
            if (i != 1004) {
                return;
            }
            aab(false);
        } catch (Throwable unused) {
            aabz.aabc("HealthKitAuthHubFragment", "onActivityResult has throwable");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        String str;
        aabz.aabb("HealthKitAuthHubFragment", "HealthKitAuthHubActivity onCreate");
        try {
            super.onCreate(bundle);
            aabz.aabb("HealthKitAuthHubFragment", "HiHealthKitClient connect to service");
            FragmentActivity activity = getActivity();
            this.aaba = activity;
            this.aabb = HuaweiHiHealth.getSettingController((Activity) activity);
            aaba();
            aabb();
        } catch (IllegalArgumentException unused) {
            str = "finish has IllegalArgumentException";
            aabz.aab("HealthKitAuthHubFragment", str);
        } catch (Throwable unused2) {
            str = "finish has exception";
            aabz.aab("HealthKitAuthHubFragment", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aab() {
        aabz.aabb("HealthKitAuthHubFragment", "auth success");
        this.aaba.setResult(-1, aacs.aab(0, this.aabc));
        this.aaba.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aab(boolean z) {
        aabz.aabb("HealthKitAuthHubFragment", "begint to checkOrAuthorizeHealth");
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setHeight(-2);
        popupWindow.setWidth(-2);
        popupWindow.setFocusable(true);
        View inflate = LayoutInflater.from(this.aaba).inflate(R.layout.healthkit_waitting, (ViewGroup) null);
        popupWindow.setContentView(inflate);
        this.aaba.getWindow().getDecorView().post(new aabd(popupWindow, inflate));
        this.aabb.getHealthAppAuthorization().addOnSuccessListener(new aabc(popupWindow, z)).addOnFailureListener(new aabb(popupWindow));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.aab == null) {
            this.aab = layoutInflater.inflate(R.layout.fragment_health_kit_auth_hub, viewGroup, false);
        }
        return this.aab;
    }

    private void aabb() {
        aabz.aabb("HealthKitAuthHubFragment", "on signInHwId click");
        ArrayList arrayList = new ArrayList();
        String[] strArr = this.aabd;
        if (strArr != null) {
            for (String str : strArr) {
                arrayList.add(new Scope(str));
            }
        }
        arrayList.toString();
        AccountAuthService service = AccountAuthManager.getService(this.aaba, new AccountAuthParamsHelper().setScopeList(arrayList).createParams());
        service.silentSignIn().addOnSuccessListener(new C0098aaba()).addOnFailureListener(new aab(service));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaba(String str) {
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                aab(HiHealthStatusCodes.HEALTH_APP_NOT_AUTHORISED);
                return;
            }
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            HealthKitAuthResult healthKitAuthResult = new HealthKitAuthResult();
            healthKitAuthResult.setAuthAccount(this.aabc);
            try {
                str2 = healthKitAuthResult.toJson();
            } catch (JSONException unused) {
                str2 = "";
            }
            bundle.putString("HEALTHKIT_AUTH_RESULT", str2);
            bundle.putString("authUrl", str);
            aabg aabgVar = new aabg();
            aabgVar.setArguments(bundle);
            beginTransaction.addToBackStack(null).replace(R.id.healthkit_main, aabgVar).show(aabgVar).commit();
        } catch (Throwable unused2) {
            aabz.aab("HealthKitAuthHubFragment", "startCloudAuth has exception");
        }
    }

    private void aaba() {
        try {
            Intent intent = this.aaba.getIntent();
            if (intent != null) {
                this.aabd = intent.getStringArrayExtra("scopes");
                this.aabe = intent.getBooleanExtra("enableHealthAuth", false);
                aabz.aabb("HealthKitAuthHubFragment", "init params success, to enable auth is " + this.aabe);
            }
            if (this.aabd == null) {
                aabz.aabc("HealthKitAuthHubFragment", "scopes params is null");
                this.aabd = new String[0];
            }
        } catch (IllegalArgumentException unused) {
            aabz.aab("HealthKitAuthHubFragment", "IllegalArgumentException error");
            this.aabd = new String[0];
        } catch (Throwable unused2) {
            aabz.aab("HealthKitAuthHubFragment", "initParams error");
            this.aabd = new String[0];
        }
    }

    private void aab(int i, Intent intent) {
        String str;
        if (i != 1002) {
            return;
        }
        aabz.aabb("HealthKitAuthHubFragment", "HMS handleSignInResult");
        if (intent == null) {
            str = "HMS SignInResult data is null";
        } else {
            Task<AuthAccount> parseAuthResultFromIntent = AccountAuthManager.parseAuthResultFromIntent(intent);
            if (parseAuthResultFromIntent != null) {
                if (!parseAuthResultFromIntent.isSuccessful()) {
                    aabz.aabc("HealthKitAuthHubFragment", "HMS SignInResult result is fail");
                    aab(((ApiException) parseAuthResultFromIntent.getException()).getStatusCode());
                    return;
                }
                aabz.aabb("HealthKitAuthHubFragment", "handleSignInResult isSuccess");
                this.aabc = parseAuthResultFromIntent.getResult();
                if (this.aabe) {
                    aab(true);
                    return;
                } else {
                    aab();
                    return;
                }
            }
            str = "HMS SignInResult result is null";
        }
        aabz.aabc("HealthKitAuthHubFragment", str);
        aab(HiHealthStatusCodes.HUAWEI_ID_SIGNIN_ERROR);
    }

    class aabb implements OnFailureListener {
        final /* synthetic */ PopupWindow aab;

        class aab implements Runnable {
            final /* synthetic */ Exception aab;

            @Override // java.lang.Runnable
            public void run() {
                aaba aabaVar;
                int aab;
                try {
                    aabb.this.aab.dismiss();
                    if (this.aab != null) {
                        aabz.aabb("HealthKitAuthHubFragment", "checkOrAuthorizeHealth has exception");
                        aabaVar = aaba.this;
                        aab = aaba.this.aab(this.aab.getMessage());
                    } else {
                        aabz.aabb("HealthKitAuthHubFragment", "checkOrAuthorizeHealth Failure");
                        aabaVar = aaba.this;
                        aab = aaba.this.aab((String) null);
                    }
                    aabaVar.aab(aab);
                } catch (Throwable unused) {
                    aabz.aab("HealthKitAuthHubFragment", "checkAuthorizeHealth fail run has exception");
                }
            }

            aab(Exception exc) {
                this.aab = exc;
            }
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            try {
                aaba.this.aaba.getWindow().getDecorView().post(new aab(exc));
            } catch (Throwable unused) {
                aabz.aab("HealthKitAuthHubFragment", "checkAuthorizeHealth fail run has exception");
            }
        }

        aabb(PopupWindow popupWindow) {
            this.aab = popupWindow;
        }
    }

    class aab implements OnFailureListener {
        final /* synthetic */ AccountAuthService aab;

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            try {
                aabz.aabb("HealthKitAuthHubFragment", "silentSignIn failure on exception");
                aaba.this.startActivityForResult(this.aab.getSignInIntent(), 1002);
            } catch (Throwable unused) {
                aabz.aab("HealthKitAuthHubFragment", "authHuaweiIdTask signInIntent has exception");
            }
        }

        aab(AccountAuthService accountAuthService) {
            this.aab = accountAuthService;
        }
    }

    static class aabd implements Runnable {
        private PopupWindow aab;
        private View aaba;

        @Override // java.lang.Runnable
        public void run() {
            String str;
            try {
                this.aab.showAtLocation(this.aaba, 17, 0, 0);
            } catch (WindowManager.BadTokenException unused) {
                str = "PopupWindow show failed BadTokenException";
                aabz.aabc("HealthKitAuthHubFragment", str);
            } catch (Throwable unused2) {
                str = "PopupWindow show failed Exception";
                aabz.aabc("HealthKitAuthHubFragment", str);
            }
        }

        aabd(PopupWindow popupWindow, View view) {
            this.aab = popupWindow;
            this.aaba = view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int aab(String str) {
        return !TextUtils.isEmpty(str) ? str.contains(String.valueOf(HiHealthStatusCodes.NON_HEALTH_USER)) ? HiHealthStatusCodes.NON_HEALTH_USER : str.contains(String.valueOf(HiHealthStatusCodes.UNTRUST_COUNTRY_CODE)) ? HiHealthStatusCodes.UNTRUST_COUNTRY_CODE : str.contains(String.valueOf(HiHealthStatusCodes.NO_NETWORK)) ? HiHealthStatusCodes.NO_NETWORK : str.contains(String.valueOf(HiHealthStatusCodes.UNKNOWN_AUTH_ERROR)) ? HiHealthStatusCodes.UNKNOWN_AUTH_ERROR : str.contains(String.valueOf(HiHealthStatusCodes.HEALTH_APP_NOT_ENABLED)) ? HiHealthStatusCodes.HEALTH_APP_NOT_ENABLED : HiHealthStatusCodes.API_EXCEPTION_ERROR : HiHealthStatusCodes.API_EXCEPTION_ERROR;
    }

    /* renamed from: com.huawei.hms.health.aaba$aaba, reason: collision with other inner class name */
    class C0098aaba implements OnSuccessListener<AuthAccount> {
        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public void onSuccess(AuthAccount authAccount) {
            AuthAccount authAccount2 = authAccount;
            try {
                if (authAccount2 == null) {
                    aabz.aabc("HealthKitAuthHubFragment", "silentSignIn result is null");
                    aaba.this.aab(HiHealthStatusCodes.HUAWEI_ID_SIGNIN_ERROR);
                } else {
                    aabz.aabb("HealthKitAuthHubFragment", "silentSignIn success");
                    aaba.this.aabc = authAccount2;
                    if (aaba.this.aabe) {
                        aaba.this.aab(true);
                    } else {
                        aaba.this.aab();
                    }
                }
            } catch (Throwable unused) {
                aabz.aab("HealthKitAuthHubFragment", "authHuaweiIdTask has exception");
            }
        }

        C0098aaba() {
        }
    }

    class aabc implements OnSuccessListener<Boolean> {
        final /* synthetic */ PopupWindow aab;
        final /* synthetic */ boolean aaba;

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public void onSuccess(Boolean bool) {
            try {
                aaba.this.aaba.getWindow().getDecorView().post(new com.huawei.hms.health.aabb(this, bool));
            } catch (Throwable unused) {
                aabz.aab("HealthKitAuthHubFragment", "checkAuthorizeHealth success has exception");
            }
        }

        aabc(PopupWindow popupWindow, boolean z) {
            this.aab = popupWindow;
            this.aaba = z;
        }
    }
}
