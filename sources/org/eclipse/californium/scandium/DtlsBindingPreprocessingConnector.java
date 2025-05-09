package org.eclipse.californium.scandium;

import defpackage.vcp;
import defpackage.vdz;
import java.net.InetSocketAddress;
import java.util.List;

/* loaded from: classes10.dex */
public class DtlsBindingPreprocessingConnector extends DTLSConnector {
    private static final DtlsBindingActionWithCallback f = new DtlsBindingActionWithCallback() { // from class: org.eclipse.californium.scandium.DtlsBindingPreprocessingConnector.4
        @Override // org.eclipse.californium.scandium.DtlsBindingPreprocessingConnector.DtlsBindingActionWithCallback
        public void run(vcp vcpVar, InetSocketAddress inetSocketAddress, Runnable runnable) {
            runnable.run();
        }
    };
    private DtlsBindingActionWithCallback h;

    public interface DtlsBindingActionWithCallback {
        void run(vcp vcpVar, InetSocketAddress inetSocketAddress, Runnable runnable);
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector
    protected void d(final List<vdz> list, final InetSocketAddress inetSocketAddress, final InetSocketAddress inetSocketAddress2) {
        this.h.run(list.get(0).d(), inetSocketAddress, new Runnable() { // from class: org.eclipse.californium.scandium.DtlsBindingPreprocessingConnector.5
            @Override // java.lang.Runnable
            public void run() {
                DtlsBindingPreprocessingConnector.this.c((List<vdz>) list, inetSocketAddress, inetSocketAddress2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<vdz> list, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        super.d(list, inetSocketAddress, inetSocketAddress2);
    }
}
