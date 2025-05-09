package defpackage;

import org.eclipse.californium.core.network.serialization.DataSerializer;

/* loaded from: classes7.dex */
public final class uyu extends DataSerializer {
    @Override // org.eclipse.californium.core.network.serialization.DataSerializer
    public void serializeHeader(vbo vboVar, uyp uypVar) {
        if (uypVar.b() < 13) {
            vboVar.b(uypVar.b(), 4);
            vboVar.b(uypVar.e().b(), 4);
        } else if (uypVar.b() < 269) {
            vboVar.b(13, 4);
            vboVar.b(uypVar.e().b(), 4);
            vboVar.b(uypVar.b() - 13, 8);
        } else if (uypVar.b() < 65805) {
            vboVar.b(14, 4);
            vboVar.b(uypVar.e().b(), 4);
            vboVar.b(uypVar.b() - 269, 16);
        } else {
            vboVar.b(15, 4);
            vboVar.b(uypVar.e().b(), 4);
            vboVar.b(uypVar.b() - 65805, 32);
        }
        vboVar.b(uypVar.d(), 8);
        vboVar.d(uypVar.e().c());
    }
}
