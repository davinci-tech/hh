package org.apache.commons.io.file;

import java.io.InputStream;
import java.net.URL;
import org.apache.commons.io.function.IOSupplier;

/* loaded from: classes10.dex */
public final /* synthetic */ class PathUtils$$ExternalSyntheticLambda0 implements IOSupplier {
    public final /* synthetic */ URL f$0;

    @Override // org.apache.commons.io.function.IOSupplier
    public final Object get() {
        InputStream openStream;
        openStream = this.f$0.openStream();
        return openStream;
    }

    public /* synthetic */ PathUtils$$ExternalSyntheticLambda0(URL url) {
        this.f$0 = url;
    }
}
