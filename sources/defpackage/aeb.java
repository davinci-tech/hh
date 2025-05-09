package defpackage;

import android.util.ArrayMap;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/* loaded from: classes8.dex */
public class aeb implements Variable {

    /* renamed from: a, reason: collision with root package name */
    private static final aeb f179a = new aeb();
    private final Map<String, Variable> d = new ArrayMap();

    public static aeb a() {
        return f179a;
    }

    private aeb() {
        e(VariableType.Random.RANDOM.getKey(), new aea());
        ady adyVar = new ady();
        e(VariableType.Crown.SCROLL.getKey(), adyVar);
        e(VariableType.Crown.DEGREE.getKey(), adyVar);
        e(VariableType.Crown.SMOOTH_DEGREE.getKey(), adyVar);
    }

    public void e(String str, Variable variable) {
        this.d.put(str, variable);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public void updateFloat(String str, float f) {
        b(str).updateFloat(str, f);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public void updateInt(String str, int i) {
        b(str).updateInt(str, i);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public int nextInt(String str, int i, int i2) {
        return b(str).nextInt(str, i, i2);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public float nextFloat(String str, float f, float f2) {
        return b(str).nextFloat(str, f, f2);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public float nextFraction(String str) {
        return b(str).nextFraction(str);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public void addUpdateObserver(String str, Variable.VariableObserver variableObserver) {
        b(str).addUpdateObserver(str, variableObserver);
    }

    private Variable b(final String str) {
        return (Variable) Optional.ofNullable(this.d.get(str)).orElseThrow(new Supplier() { // from class: adz
            @Override // java.util.function.Supplier
            public final Object get() {
                return aeb.a(str);
            }
        });
    }

    static /* synthetic */ Variable.d a(String str) {
        return new Variable.d(str);
    }
}
