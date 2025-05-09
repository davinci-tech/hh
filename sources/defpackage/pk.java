package defpackage;

import com.github.promeg.pinyinhelper.PinyinDict;
import defpackage.uxc;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* loaded from: classes8.dex */
final class pk {
    static uxc e(List<PinyinDict> list) {
        TreeSet treeSet = new TreeSet();
        uxc.b b = uxc.b();
        if (list == null) {
            return null;
        }
        for (PinyinDict pinyinDict : list) {
            if (pinyinDict != null && pinyinDict.words() != null) {
                treeSet.addAll(pinyinDict.words());
            }
        }
        if (treeSet.size() <= 0) {
            return null;
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            b.a((String) it.next());
        }
        return b.b();
    }
}
