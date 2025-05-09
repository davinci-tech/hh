package defpackage;

import com.apprichtap.haptic.b.a.c;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class na implements c {
    public ArrayList<mw> b;
    public mz d;

    @Override // com.apprichtap.haptic.b.a.c
    public int getDuration() {
        int i;
        try {
            mw mwVar = this.b.get(r1.size() - 1);
            Iterator<ms> it = mwVar.b.iterator();
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
            return i2 + mwVar.d;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int e(int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            try {
                for (int i3 = 0; i3 < this.b.get(i2).b.size(); i3++) {
                    if (this.b.get(i2).d <= i && this.b.get(i2).d + this.b.get(i2).b.get(i3).d.b >= i) {
                        return i2;
                    }
                    if (this.b.get(i2).d > i) {
                        return i2 - 1;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return Integer.MIN_VALUE;
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override // com.apprichtap.haptic.b.a.c
    public int a() {
        return this.d.b;
    }
}
