package defpackage;

import android.view.Choreographer;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.ObservableVariable;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;

/* loaded from: classes8.dex */
public class ady extends ObservableVariable {
    private float c = 0.0f;
    private float d = 0.0f;
    private float e = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private final Choreographer.FrameCallback f177a = new Choreographer.FrameCallback() { // from class: ady.3
        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            if (Math.abs(ady.this.d - ady.this.e) > 1.0f) {
                ady.this.e += Math.signum(ady.this.d - ady.this.e) * 1.0f;
                Choreographer.getInstance().postFrameCallback(this);
            } else {
                ady adyVar = ady.this;
                adyVar.e = adyVar.d;
            }
            ady.this.notifyObserver(VariableType.Crown.SMOOTH_DEGREE.getKey());
        }
    };

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public void updateFloat(String str, float f) {
        if (VariableType.Crown.SCROLL.getKey().equals(str)) {
            c(str, f);
            e(str, f);
            return;
        }
        throw new Variable.b(str);
    }

    private void c(String str, float f) {
        this.c = f;
        this.d += f;
        notifyObserver(VariableType.Crown.SCROLL.getKey());
        notifyObserver(VariableType.Crown.DEGREE.getKey());
    }

    private void e(String str, float f) {
        if (getObservers(VariableType.Crown.SMOOTH_DEGREE.getKey()).isPresent()) {
            this.c = f;
            this.d += f;
            Choreographer.getInstance().postFrameCallback(this.f177a);
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public float nextFloat(String str, float f, float f2) {
        if (VariableType.Crown.SCROLL.getKey().equals(str)) {
            return this.c;
        }
        if (VariableType.Crown.DEGREE.getKey().equals(str)) {
            return b(str, f, f2);
        }
        if (VariableType.Crown.SMOOTH_DEGREE.getKey().equals(str)) {
            return c(str, f, f2);
        }
        throw new Variable.b(str);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public int nextInt(String str, int i, int i2) {
        float c;
        if (VariableType.Crown.DEGREE.getKey().equals(str)) {
            c = b(str, i, i2);
        } else if (VariableType.Crown.SMOOTH_DEGREE.getKey().equals(str)) {
            c = c(str, i, i2);
        } else {
            throw new Variable.b(str);
        }
        return (int) c;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public float nextFraction(String str) {
        float abs;
        if (VariableType.Crown.DEGREE.getKey().equals(str)) {
            abs = Math.abs(this.d);
        } else if (VariableType.Crown.SMOOTH_DEGREE.getKey().equals(str)) {
            abs = Math.abs(this.e);
        } else {
            throw new Variable.b(str);
        }
        return (abs % 360.0f) / 360.0f;
    }

    private float b(String str, float f, float f2) {
        if (this.d < 0.0f && f < 0.0f) {
            return Math.min(0.0f, f2) - (nextFraction(str) * (Math.min(0.0f, f2) - f));
        }
        return (nextFraction(str) * (f2 - Math.max(0.0f, f))) + Math.max(0.0f, f);
    }

    private float c(String str, float f, float f2) {
        if (this.e < 0.0f && f < 0.0f) {
            return Math.min(0.0f, f2) - (nextFraction(str) * (Math.min(0.0f, f2) - f));
        }
        return (nextFraction(str) * (f2 - Math.max(0.0f, f))) + Math.max(0.0f, f);
    }
}
