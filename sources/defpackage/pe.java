package defpackage;

import com.github.promeg.pinyinhelper.SegmentationSelector;
import defpackage.pg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* loaded from: classes8.dex */
final class pe implements SegmentationSelector {
    static final pg.d d = new pg.d();

    pe() {
    }

    @Override // com.github.promeg.pinyinhelper.SegmentationSelector
    public List<uwy> select(Collection<uwy> collection) {
        if (collection == null) {
            return null;
        }
        ArrayList<uwy> arrayList = new ArrayList(collection);
        Collections.sort(arrayList, d);
        TreeSet treeSet = new TreeSet();
        int i = -1;
        for (uwy uwyVar : arrayList) {
            if (uwyVar.getStart() > i && uwyVar.getEnd() > i) {
                i = uwyVar.getEnd();
            } else {
                treeSet.add(uwyVar);
            }
        }
        arrayList.removeAll(treeSet);
        return arrayList;
    }
}
