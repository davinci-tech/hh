package defpackage;

import com.alipay.sdk.m.x.e;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes7.dex */
public class mh {
    public Stack<e> e = new Stack<>();

    public e a() {
        return this.e.pop();
    }

    public void d(e eVar) {
        this.e.push(eVar);
    }

    public boolean e() {
        return this.e.isEmpty();
    }

    public void b() {
        if (e()) {
            return;
        }
        Iterator<e> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.e.clear();
    }
}
