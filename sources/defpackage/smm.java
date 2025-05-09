package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.huawei.uikit.hwrecyclerview.widget.HwRollbackRuleDetector;

/* loaded from: classes7.dex */
public class smm {
    private HwRollbackRuleDetector c;

    public smm(HwRollbackRuleDetector.RollBackScrollListener rollBackScrollListener) {
        this.c = new HwRollbackRuleDetector(rollBackScrollListener);
    }

    public void c() {
        HwRollbackRuleDetector hwRollbackRuleDetector = this.c;
        if (hwRollbackRuleDetector != null) {
            hwRollbackRuleDetector.b("com.huawei.control.intent.action.RollBackUsedEvent");
        }
    }

    public void d() {
        HwRollbackRuleDetector hwRollbackRuleDetector = this.c;
        if (hwRollbackRuleDetector != null) {
            hwRollbackRuleDetector.e();
        }
    }

    public void egq_(MotionEvent motionEvent) {
        HwRollbackRuleDetector hwRollbackRuleDetector = this.c;
        if (hwRollbackRuleDetector != null) {
            hwRollbackRuleDetector.ego_(motionEvent);
        }
    }

    public void egr_(View view) {
        HwRollbackRuleDetector hwRollbackRuleDetector = this.c;
        if (hwRollbackRuleDetector != null) {
            hwRollbackRuleDetector.egp_(view);
        }
    }
}
