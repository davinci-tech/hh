package com.huawei.health.tradeservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.tradeservice.activity.RedeemCodeScanActivity;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.iap.Iap;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.RedeemCodeResultInfo;
import com.huawei.hms.iap.entity.ScanRedeemCodeResult;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class RedeemCodeScanActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
    }

    private void c() {
        Iap.getIapClient((Activity) this).scanRedeemCode().addOnSuccessListener(new OnSuccessListener() { // from class: gkl
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                RedeemCodeScanActivity.this.a((ScanRedeemCodeResult) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: gkr
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                RedeemCodeScanActivity.this.a(exc);
            }
        });
        overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
    }

    public /* synthetic */ void a(ScanRedeemCodeResult scanRedeemCodeResult) {
        if (scanRedeemCodeResult != null) {
            scanRedeemCodeResult.startActivityForResult(this, 20230330);
            LogUtil.c("RedeemCodeScanActivity", "start iap redeem scan activity success");
        }
    }

    public /* synthetic */ void a(Exception exc) {
        if (exc instanceof IapApiException) {
            LogUtil.e("RedeemCodeScanActivity", "start iap redeem scan activity failed, iap throws exception, code: " + ((IapApiException) exc).getStatusCode());
        } else {
            LogUtil.e("RedeemCodeScanActivity", "start iap redeem scan activity failed, e:" + exc);
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        super.onActivityResult(i, i2, intent);
        if (i == 20230330) {
            RedeemCodeResultInfo parseRedeemCodeResultInfoFromIntent = Iap.getIapClient((Activity) this).parseRedeemCodeResultInfoFromIntent(intent);
            int returnCode = parseRedeemCodeResultInfoFromIntent.getReturnCode();
            LogUtil.c("RedeemCodeScanActivity", "Iap redeem scan finished, redeemReturnCode: " + returnCode);
            if (returnCode == -1) {
                str = "failed";
            } else if (returnCode == 0) {
                str = parseRedeemCodeResultInfoFromIntent.getRedeemCode();
            } else if (returnCode == 60000) {
                str = "cancel";
            } else if (returnCode != 70000) {
                LogUtil.c("RedeemCodeScanActivity", "unexpected error code from iap, do nothing");
                str = null;
            } else {
                str = "not_support";
            }
            setResult(-1, new Intent().putExtra("result", str));
        }
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
