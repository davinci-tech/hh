package defpackage;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jjw {
    private int b;
    private List<Integer> c;
    private int d;

    public void a(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void d(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<Integer> a() {
        return (List) jdy.d(this.c);
    }

    public void e(List<Integer> list) {
        this.c = (List) jdy.d(list);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("folderIndex: ");
        sb.append(this.b);
        sb.append(" folderMusicAssociationFrameIndex: ");
        sb.append(this.d);
        sb.append(" musicIndexList: [");
        Iterator<Integer> it = this.c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().intValue());
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
