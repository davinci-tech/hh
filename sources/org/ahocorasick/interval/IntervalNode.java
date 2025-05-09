package org.ahocorasick.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class IntervalNode {
    private List<Intervalable> b = new ArrayList();
    private int c;
    private IntervalNode d;
    private IntervalNode e;

    enum Direction {
        LEFT,
        RIGHT
    }

    public IntervalNode(List<Intervalable> list) {
        this.d = null;
        this.e = null;
        this.c = a(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Intervalable intervalable : list) {
            if (intervalable.getEnd() < this.c) {
                arrayList.add(intervalable);
            } else if (intervalable.getStart() > this.c) {
                arrayList2.add(intervalable);
            } else {
                this.b.add(intervalable);
            }
        }
        if (arrayList.size() > 0) {
            this.d = new IntervalNode(arrayList);
        }
        if (arrayList2.size() > 0) {
            this.e = new IntervalNode(arrayList2);
        }
    }

    public int a(List<Intervalable> list) {
        int i = -1;
        int i2 = -1;
        for (Intervalable intervalable : list) {
            int start = intervalable.getStart();
            int end = intervalable.getEnd();
            if (i == -1 || start < i) {
                i = start;
            }
            if (i2 == -1 || end > i2) {
                i2 = end;
            }
        }
        return (i + i2) / 2;
    }

    public List<Intervalable> b(Intervalable intervalable) {
        ArrayList arrayList = new ArrayList();
        if (this.c < intervalable.getStart()) {
            c(intervalable, arrayList, c(this.e, intervalable));
            c(intervalable, arrayList, e(intervalable));
        } else if (this.c > intervalable.getEnd()) {
            c(intervalable, arrayList, c(this.d, intervalable));
            c(intervalable, arrayList, c(intervalable));
        } else {
            c(intervalable, arrayList, this.b);
            c(intervalable, arrayList, c(this.d, intervalable));
            c(intervalable, arrayList, c(this.e, intervalable));
        }
        return arrayList;
    }

    protected void c(Intervalable intervalable, List<Intervalable> list, List<Intervalable> list2) {
        for (Intervalable intervalable2 : list2) {
            if (!intervalable2.equals(intervalable)) {
                list.add(intervalable2);
            }
        }
    }

    protected List<Intervalable> c(Intervalable intervalable) {
        return c(intervalable, Direction.LEFT);
    }

    protected List<Intervalable> e(Intervalable intervalable) {
        return c(intervalable, Direction.RIGHT);
    }

    protected List<Intervalable> c(Intervalable intervalable, Direction direction) {
        ArrayList arrayList = new ArrayList();
        for (Intervalable intervalable2 : this.b) {
            int i = AnonymousClass5.c[direction.ordinal()];
            if (i == 1) {
                if (intervalable2.getStart() <= intervalable.getEnd()) {
                    arrayList.add(intervalable2);
                }
            } else if (i == 2 && intervalable2.getEnd() >= intervalable.getStart()) {
                arrayList.add(intervalable2);
            }
        }
        return arrayList;
    }

    /* renamed from: org.ahocorasick.interval.IntervalNode$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[Direction.values().length];
            c = iArr;
            try {
                iArr[Direction.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[Direction.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected List<Intervalable> c(IntervalNode intervalNode, Intervalable intervalable) {
        if (intervalNode != null) {
            return intervalNode.b(intervalable);
        }
        return Collections.emptyList();
    }
}
