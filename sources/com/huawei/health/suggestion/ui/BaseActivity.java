package com.huawei.health.suggestion.ui;

import android.os.Bundle;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import defpackage.gcd;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public abstract class BaseActivity extends BaseActivityTemp {
    protected static final int HAVE_NO_NET = -404;
    private static final String TAG = "Suggestion_BaseActivity";

    public abstract void initData();

    public abstract void initLayout();

    public abstract void initViewController();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        gcd.d(new WeakReference(this));
        setHealthTheme();
        initData();
        initLayout();
        initViewController();
    }

    public void setHealthTheme() {
        int identifier = getResources().getIdentifier("HealthTheme", TemplateStyleRecord.STYLE, "com.huawei.health");
        if (identifier == 0) {
            LogUtil.h(TAG, "onCreate if (healthTheme == 0)");
        } else {
            LogUtil.a(TAG, "onCreate if (healthTheme == 0) ELSE healthTheme=", Integer.valueOf(identifier));
            setTheme(identifier);
        }
    }

    protected String getError(int i) {
        if (i != -404) {
            return getString(R.string._2130851533_res_0x7f0236cd);
        }
        return getString(R.string._2130839508_res_0x7f0207d4);
    }
}
