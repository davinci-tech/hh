package defpackage;

import com.apprichtap.haptic.b.a.c;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class mu implements c {
    public ArrayList<ms> c;
    public mt e;

    @Override // com.apprichtap.haptic.b.a.c
    public int getDuration() {
        try {
            mr mrVar = this.c.get(r0.size() - 1).d;
            return "continuous".equals(mrVar.f15123a) ? mrVar.b + mrVar.d : mrVar.b + 48;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.apprichtap.haptic.b.a.c
    public int a() {
        return this.e.c;
    }
}
