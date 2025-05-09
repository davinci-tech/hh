package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vhi implements ILoggerFactory {

    /* renamed from: a, reason: collision with root package name */
    boolean f17713a = false;
    final Map<String, vhh> e = new HashMap();
    final LinkedBlockingQueue<vhc> b = new LinkedBlockingQueue<>();

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        vhh vhhVar;
        synchronized (this) {
            vhhVar = this.e.get(str);
            if (vhhVar == null) {
                vhhVar = new vhh(str, this.b, this.f17713a);
                this.e.put(str, vhhVar);
            }
        }
        return vhhVar;
    }

    public List<vhh> c() {
        return new ArrayList(this.e.values());
    }

    public LinkedBlockingQueue<vhc> b() {
        return this.b;
    }

    public void a() {
        this.f17713a = true;
    }

    public void d() {
        this.e.clear();
        this.b.clear();
    }
}
