package defpackage;

import android.graphics.Shader;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes8.dex */
public class aep {
    public static List<Integer> a(VectorDrawableCompat vectorDrawableCompat, ColorResult colorResult) {
        return a(vectorDrawableCompat, colorResult.getAllColors(), colorResult.getAllGradients());
    }

    public static List<Integer> a(VectorDrawableCompat vectorDrawableCompat, List<Integer> list, List<Integer> list2) {
        a aVar = new a(list, list2);
        d(aVar, vectorDrawableCompat.b());
        return new ArrayList(aVar.d);
    }

    private static void d(a aVar, Object obj) {
        if (obj instanceof VectorDrawableCompat.a) {
            Iterator<VectorDrawableCompat.VObject> it = ((VectorDrawableCompat.a) obj).e().iterator();
            while (it.hasNext()) {
                d(aVar, it.next());
            }
        } else if (obj instanceof VectorDrawableCompat.d) {
            b(aVar, (VectorDrawableCompat.d) obj);
        }
    }

    private static void b(a aVar, VectorDrawableCompat.d dVar) {
        if (dVar.b()) {
            d(aVar, dVar);
        } else {
            c(aVar, dVar);
        }
    }

    private static void d(a aVar, VectorDrawableCompat.d dVar) {
        Shader gx_ = dVar.gx_();
        if (gx_ instanceof aem) {
            aem aemVar = (aem) gx_;
            if (aemVar.a().length >= 2) {
                int i = aemVar.a()[0];
                int i2 = aemVar.a()[1];
                if (aVar.c(i) && aVar.c(i2)) {
                    dVar.a(new int[]{aVar.b(i), aVar.b(i2)});
                }
            }
        }
    }

    private static void c(a aVar, VectorDrawableCompat.d dVar) {
        if (aVar.c(dVar.d())) {
            dVar.a(aVar.b(dVar.d()));
        }
        if (aVar.c(dVar.c())) {
            dVar.e(aVar.b(dVar.c()));
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final List<Integer> f189a = Arrays.asList(1052688, 2105376, 3158064);
        private static final List<Integer> c = Arrays.asList(1118481, 2171169, 3223857);
        private final Set<Integer> d;
        private final ArrayMap<Integer, Integer> e;

        private static int d(int i) {
            return i & ViewCompat.MEASURED_SIZE_MASK;
        }

        private static int e(int i) {
            return i & (-16777216);
        }

        private a(List<Integer> list, List<Integer> list2) {
            this.e = new ArrayMap<>();
            this.d = new HashSet();
            for (int i = 0; i < list.size(); i++) {
                List<Integer> list3 = f189a;
                if (i >= list3.size()) {
                    break;
                }
                this.e.put(list3.get(i), list.get(i));
            }
            for (int i2 = 0; i2 < list2.size(); i2++) {
                List<Integer> list4 = c;
                if (i2 >= list4.size()) {
                    return;
                }
                this.e.put(list4.get(i2), list2.get(i2));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c(int i) {
            return this.e.containsKey(Integer.valueOf(d(i)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int b(int i) {
            int d = d(i);
            int e = e(i);
            if (this.e.get(Integer.valueOf(d)) == null) {
                return i;
            }
            this.d.add(this.e.get(Integer.valueOf(d)));
            return d(this.e.getOrDefault(Integer.valueOf(d), Integer.valueOf(d)).intValue()) | e;
        }
    }
}
