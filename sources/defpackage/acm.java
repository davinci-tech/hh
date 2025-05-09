package defpackage;

import android.util.Log;
import android.util.Pair;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class acm implements ColorPipelineNode {
    private static final List<a> d;

    static {
        ArrayList arrayList = new ArrayList();
        d = arrayList;
        arrayList.add(new a(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, 25));
        arrayList.add(new a(15, 50));
        arrayList.add(new a(40, 70));
        arrayList.add(new a(60, MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE));
        arrayList.add(new a(160, 200));
        arrayList.add(new a(190, 260));
        arrayList.add(new a(255, 290));
        arrayList.add(new a(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, 340));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode
    public void process(ColorResult colorResult) {
        Log.i("ColorMerger", "before merge: " + colorResult);
        ArrayList arrayList = new ArrayList();
        List<acf> mainColors = colorResult.getMainColors();
        for (int i = 0; i < mainColors.size(); i++) {
            acf acfVar = mainColors.get(i);
            arrayList.add(new Pair(Float.valueOf((acfVar.d() + (acfVar.b() * 1.4f)) - (i * 0.05f)), mainColors.get(i)));
        }
        List list = (List) arrayList.stream().sorted(new Comparator() { // from class: aco
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return acm.fK_((Pair) obj, (Pair) obj2);
            }
        }).map(new Function() { // from class: acn
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return acm.fL_((Pair) obj);
            }
        }).collect(Collectors.toList());
        int i2 = 0;
        while (i2 < list.size()) {
            int i3 = i2 + 1;
            for (int i4 = i3; i4 < list.size(); i4++) {
                if (((acf) list.get(i2)).a() != 0 && ((acf) list.get(i4)).a() != 0 && e((acf) list.get(i2), (acf) list.get(i4))) {
                    ((acf) list.get(i2)).a(((acf) list.get(i2)).a() + ((acf) list.get(i4)).a());
                    ((acf) list.get(i4)).a(0);
                }
            }
            i2 = i3;
        }
        colorResult.setMainColors((List) list.stream().filter(new Predicate() { // from class: act
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return acm.a((acf) obj);
            }
        }).sorted(new Comparator() { // from class: acu
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return acm.a((acf) obj, (acf) obj2);
            }
        }).collect(Collectors.toList()));
        Log.i("ColorMerger", "after merge: " + colorResult);
    }

    static /* synthetic */ int fK_(Pair pair, Pair pair2) {
        return -Float.compare(((Float) pair.first).floatValue(), ((Float) pair2.first).floatValue());
    }

    static /* synthetic */ acf fL_(Pair pair) {
        return (acf) pair.second;
    }

    static /* synthetic */ boolean a(acf acfVar) {
        return acfVar.a() > 0;
    }

    static /* synthetic */ int a(acf acfVar, acf acfVar2) {
        return -Float.compare(acfVar.a(), acfVar2.a());
    }

    private boolean e(acf acfVar, acf acfVar2) {
        if (acfVar.d() < 0.08f && acfVar2.d() < 0.08f && Math.abs(acfVar.b() - acfVar2.b()) < 0.1f) {
            return true;
        }
        for (a aVar : d) {
            if (aVar.c(acfVar.e()) && aVar.c(acfVar2.e()) && b(acfVar.e(), acfVar2.e()) < 25.0f) {
                return true;
            }
        }
        return false;
    }

    private float b(float f, float f2) {
        float abs = Math.abs(f - f2);
        return Math.min(abs, 360.0f - abs);
    }

    static class a {
        private final int d;
        private final int e;

        private a(int i, int i2) {
            this.d = i;
            this.e = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c(float f) {
            int i = this.e;
            int i2 = this.d;
            return i > i2 ? f > ((float) i2) && f <= ((float) i) : f > ((float) i2) || f <= ((float) i);
        }
    }
}
