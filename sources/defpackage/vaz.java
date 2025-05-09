package defpackage;

import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public final class vaz {
    public static final Configuration.ModuleDefinitionsProvider e;
    public static final vat b = new vat("UDP.RECEIVER_THREAD_COUNT", "Number of UDP receiver threads.", 1, 0);
    public static final vat i = new vat("UDP.SENDER_THREAD_COUNT", "Number of UDP sender threads.", 1, 0);
    public static final vat d = new vat("UDP.DATAGRAM_SIZE", "Maxium size of UDP datagram.", 2048, 64);

    /* renamed from: a, reason: collision with root package name */
    public static final vat f17640a = new vat("UDP.RECEIVE_BUFFER_SIZE", "UDP receive-buffer size. Empty or 0 to use the OS default.", null, 64);
    public static final vat j = new vat("UDP.SEND_BUFFER_SIZE", "UDP send-buffer size. Empty or 0 to use the OS default.", null, 64);
    public static final vat c = new vat("UDP.CONNECTOR_OUT_CAPACITY", "Maximum number of pending outgoing messages.", Integer.MAX_VALUE, 32);

    static {
        Configuration.ModuleDefinitionsProvider moduleDefinitionsProvider = new Configuration.ModuleDefinitionsProvider() { // from class: vaz.5
            @Override // org.eclipse.californium.elements.config.Configuration.DefinitionsProvider
            public void applyDefinitions(Configuration configuration) {
                int i2 = Runtime.getRuntime().availableProcessors() > 3 ? 2 : 1;
                configuration.e((BasicDefinition<vat>) vaz.b, (vat) Integer.valueOf(i2));
                configuration.e((BasicDefinition<vat>) vaz.i, (vat) Integer.valueOf(i2));
                configuration.e((BasicDefinition<vat>) vaz.d, (vat) 2048);
                configuration.e((BasicDefinition<vat>) vaz.f17640a, (vat) null);
                configuration.e((BasicDefinition<vat>) vaz.j, (vat) null);
                configuration.e((BasicDefinition<vat>) vaz.c, (vat) Integer.MAX_VALUE);
            }

            @Override // org.eclipse.californium.elements.config.Configuration.ModuleDefinitionsProvider
            public String getModule() {
                return "UDP.";
            }
        };
        e = moduleDefinitionsProvider;
        Configuration.b(moduleDefinitionsProvider);
    }
}
