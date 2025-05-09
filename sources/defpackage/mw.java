package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class mw {
    public ArrayList<ms> b;
    public int d;

    public int a() {
        int i;
        try {
            Iterator<ms> it = this.b.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                ms next = it.next();
                if (next.d.f15123a.equals("continuous")) {
                    mr mrVar = next.d;
                    i = mrVar.b + mrVar.d;
                } else {
                    i = next.d.b + 48;
                }
                if (i > i2) {
                    i2 = i;
                }
            }
            return i2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
