package defpackage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.ahocorasick.interval.IntervalNode;
import org.ahocorasick.interval.Intervalable;

/* loaded from: classes7.dex */
public class uwx {
    private IntervalNode d;

    public uwx(List<Intervalable> list) {
        this.d = null;
        this.d = new IntervalNode(list);
    }

    public List<Intervalable> a(List<Intervalable> list) {
        Collections.sort(list, new uxb());
        TreeSet treeSet = new TreeSet();
        for (Intervalable intervalable : list) {
            if (!treeSet.contains(intervalable)) {
                treeSet.addAll(e(intervalable));
            }
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            list.remove((Intervalable) it.next());
        }
        Collections.sort(list, new uwz());
        return list;
    }

    public List<Intervalable> e(Intervalable intervalable) {
        return this.d.b(intervalable);
    }
}
