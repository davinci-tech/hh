package com.huawei.health.health.utils.vippage;

import android.content.Context;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.HandlerExecutor;
import defpackage.dqb;
import defpackage.jdx;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public abstract class MemberBaseBootPageHelper {
    protected dqb mBootPageDialog;
    protected Context mContext;

    protected abstract void configBootPage();

    protected abstract String getSharePreferenceString();

    public MemberBaseBootPageHelper(Context context) {
        this.mContext = context;
    }

    public void showBootPage() {
        jdx.b(new Runnable() { // from class: com.huawei.health.health.utils.vippage.MemberBaseBootPageHelper.5
            @Override // java.lang.Runnable
            public void run() {
                String sharePreferenceString = MemberBaseBootPageHelper.this.getSharePreferenceString();
                String b = SharedPreferenceManager.b(MemberBaseBootPageHelper.this.mContext, String.valueOf(PrebakedEffectId.RT_AWARD), sharePreferenceString);
                if (b == null || TextUtils.isEmpty(b) || String.valueOf(true).equals(b)) {
                    MemberBaseBootPageHelper.this.showBootPageInner();
                    SharedPreferenceManager.e(MemberBaseBootPageHelper.this.mContext, String.valueOf(PrebakedEffectId.RT_AWARD), sharePreferenceString, String.valueOf(false), new StorageParams());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBootPageInner() {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.health.utils.vippage.MemberBaseBootPageHelper.4
                @Override // java.lang.Runnable
                public void run() {
                    MemberBaseBootPageHelper.this.showBootPageInner();
                }
            });
            return;
        }
        configBootPage();
        dqb dqbVar = this.mBootPageDialog;
        if (dqbVar != null) {
            dqbVar.show();
        }
    }
}
