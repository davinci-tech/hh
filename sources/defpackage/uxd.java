package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.ahocorasick.trie.handler.EmitHandler;

/* loaded from: classes7.dex */
public class uxd implements EmitHandler {
    private List<uwy> d = new ArrayList();

    @Override // org.ahocorasick.trie.handler.EmitHandler
    public void emit(uwy uwyVar) {
        this.d.add(uwyVar);
    }

    public List<uwy> c() {
        return this.d;
    }
}
