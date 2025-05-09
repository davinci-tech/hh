package com.huawei.ui.thirdpartservice.activity.base;

import android.app.Activity;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.activity.BaseConnectActivity;
import com.huawei.ui.thirdpartservice.activity.base.CheckConnectCallback;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback;
import defpackage.nrh;

/* loaded from: classes7.dex */
public abstract class CheckConnectCallback extends ReferenceCheckConnectCallback<Activity> {
    private String TAG;

    /* loaded from: classes2.dex */
    public interface Interrupt {
        default boolean isFinish() {
            return false;
        }

        void onStatusGot();
    }

    protected abstract void startToConnectActivity(Activity activity);

    protected abstract void startToDisConnectActivity(Activity activity);

    public CheckConnectCallback(Activity activity) {
        super(activity);
        this.TAG = "CheckConnectCallback";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceCheckConnectCallback
    public void connectResultWhenReferenceNotNull(final Activity activity, final boolean z, final boolean z2) {
        LogUtil.a(this.TAG, "connectResultWhenReferenceNotNull", Boolean.valueOf(z2));
        activity.runOnUiThread(new Runnable() { // from class: sfk
            @Override // java.lang.Runnable
            public final void run() {
                CheckConnectCallback.this.m867x9900f33(activity, z, z2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$connectResultWhenReferenceNotNull$0$com-huawei-ui-thirdpartservice-activity-base-CheckConnectCallback, reason: not valid java name */
    public /* synthetic */ void m867x9900f33(Activity activity, boolean z, boolean z2) {
        boolean z3 = activity instanceof Interrupt;
        if (z3) {
            ((Interrupt) activity).onStatusGot();
        }
        if (!z) {
            nrh.b(activity, R.string._2130841884_res_0x7f02111c);
            return;
        }
        LogUtil.a(this.TAG, "jump activity", Boolean.valueOf(z2));
        if (z2) {
            startToDisConnectActivity(activity);
            if (!z3 || ((Interrupt) activity).isFinish()) {
                activity.finish();
                return;
            }
            return;
        }
        if (activity instanceof BaseConnectActivity) {
            return;
        }
        startToConnectActivity(activity);
    }
}
