package health.compact.a;

import android.os.Bundle;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.health.ui.notification.constants.UiComponent;
import com.huawei.health.ui.notification.uihandler.UiConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class UiHandler {
    private Map<UiComponent, IReporter> e = new HashMap(10);
    private StepsRecord b = new StepsRecord();

    public UiHandler(UiConfig uiConfig) {
        uiConfig.b(this);
    }

    public void a(UiComponent uiComponent, IReporter iReporter) {
        this.e.put(uiComponent, iReporter);
    }

    public void b(UiComponent uiComponent) {
        aOa_(uiComponent, new Bundle());
    }

    public void aOa_(UiComponent uiComponent, Bundle bundle) {
        IReporter iReporter = this.e.get(uiComponent);
        if (iReporter != null) {
            iReporter.onStart(bundle);
        }
    }

    public void c(UiComponent uiComponent) {
        IReporter iReporter = this.e.get(uiComponent);
        if (iReporter == null || !iReporter.isRunning()) {
            return;
        }
        iReporter.onStop();
    }

    public void d(StepsRecord stepsRecord) {
        if (stepsRecord != null) {
            this.b.b(stepsRecord);
            b();
        }
    }

    public void b() {
        for (IReporter iReporter : this.e.values()) {
            if (iReporter != null) {
                try {
                    if (iReporter.isRunning()) {
                        iReporter.onUpdate(this.b);
                    }
                } catch (Exception e) {
                    com.huawei.hwlogsmodel.LogUtil.h("Step_UiHandler", "update exception:", LogAnonymous.b((Object) e));
                }
            }
        }
    }

    public void a() {
        for (IReporter iReporter : this.e.values()) {
            if (iReporter.isRunning()) {
                iReporter.onLanguageChanged();
            }
        }
    }
}
