package defpackage;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.huawei.hwcommonmodel.utils.animation.EnterAnimationCallback;
import com.huawei.hwcommonmodel.utils.animation.ExitAnimationCallback;
import com.huawei.hwcommonmodel.utils.animation.ExitAnimationStartCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.SafeIntent;
import com.huawei.ui.device.views.animation.ActivityAnimationCallback;

/* loaded from: classes6.dex */
public class obt {
    private ActivityAnimationCallback b;
    private jea d;
    private boolean e = true;

    public void b(boolean z) {
        this.e = z;
    }

    public void cUX_(Activity activity, Intent intent, boolean z) {
        if (this.e) {
            cUW_(activity, intent, z);
        }
    }

    public void d(ActivityAnimationCallback activityAnimationCallback) {
        LogUtil.a("ActivityAnimationObject", "exitAnimation mAnimationHelper:", this.d, ",mCallback:", this.b);
        if (!this.e) {
            if (activityAnimationCallback != null) {
                activityAnimationCallback.onProcessExit(false);
            }
        } else {
            jea jeaVar = this.d;
            if (jeaVar != null) {
                this.b = activityAnimationCallback;
                jeaVar.a();
            }
        }
    }

    public void cUY_(Activity activity) {
        if (activity == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) nsy.cMc_(activity, R.id.content);
        jea jeaVar = this.d;
        if (jeaVar != null) {
            jeaVar.bGo_(viewGroup);
        }
    }

    private void cUW_(Activity activity, Intent intent, boolean z) {
        if (cUV_(activity, intent)) {
            Rect sourceBounds = new SafeIntent(intent).getSourceBounds();
            LogUtil.a("ActivityAnimationObject", "setActivityAnimationHelper start:", Boolean.valueOf(z), ",sourceRect:", sourceBounds);
            ViewGroup viewGroup = (ViewGroup) nsy.cMc_(activity, R.id.content);
            if (viewGroup == null) {
                this.d = null;
                LogUtil.h("ActivityAnimationObject", "view is null");
                return;
            }
            if (!z || sourceBounds == null) {
                viewGroup.setVisibility(0);
                this.d = null;
                LogUtil.h("ActivityAnimationObject", "not from click");
                return;
            }
            viewGroup.setVisibility(4);
            jea jeaVar = new jea(sourceBounds, viewGroup);
            this.d = jeaVar;
            jeaVar.d(new ExitAnimationCallback() { // from class: obt.4
                @Override // com.huawei.hwcommonmodel.utils.animation.ExitAnimationCallback
                public void onExitAnimationEnd() {
                    LogUtil.a("ActivityAnimationObject", "mAnimationHelper onExitAnimationEnd");
                    if (obt.this.b != null) {
                        obt.this.b.onProcessExit(true);
                    }
                }
            });
            this.d.e(new EnterAnimationCallback() { // from class: obt.5
                @Override // com.huawei.hwcommonmodel.utils.animation.EnterAnimationCallback
                public void onEnterAnimationStart() {
                    LogUtil.a("ActivityAnimationObject", "mAnimationHelper onEnterAnimationStart");
                }

                @Override // com.huawei.hwcommonmodel.utils.animation.EnterAnimationCallback
                public void onEnterAnimationEnd() {
                    LogUtil.a("ActivityAnimationObject", "mAnimationHelper onEnterAnimationEnd");
                }
            });
            this.d.b(new ExitAnimationStartCallback() { // from class: obt.2
                @Override // com.huawei.hwcommonmodel.utils.animation.ExitAnimationStartCallback
                public void onExitAnimationStart() {
                    LogUtil.a("ActivityAnimationObject", "mAnimationHelper onExitAnimationStart");
                }
            });
            this.d.b();
        }
    }

    private boolean cUV_(Activity activity, Intent intent) {
        boolean z;
        if (intent == null) {
            LogUtil.h("ActivityAnimationObject", "intent is null");
            z = false;
        } else {
            z = true;
        }
        if (activity == null) {
            return false;
        }
        return z;
    }
}
