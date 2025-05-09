package defpackage;

import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public final class vbc {
    public static final vay b = new vay("SYS.HEALTH_STATUS_INTERVAL", "Health status interval. 0 to disable the health status.", 0, TimeUnit.SECONDS);
    public static final Configuration.ModuleDefinitionsProvider d;

    static {
        Configuration.ModuleDefinitionsProvider moduleDefinitionsProvider = new Configuration.ModuleDefinitionsProvider() { // from class: vbc.1
            @Override // org.eclipse.californium.elements.config.Configuration.DefinitionsProvider
            public void applyDefinitions(Configuration configuration) {
                configuration.a(vbc.b, 0, TimeUnit.SECONDS);
            }

            @Override // org.eclipse.californium.elements.config.Configuration.ModuleDefinitionsProvider
            public String getModule() {
                return "SYS.";
            }
        };
        d = moduleDefinitionsProvider;
        Configuration.b(moduleDefinitionsProvider);
    }
}
