package defpackage;

import com.huawei.health.trusport_ee.Sex;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/huawei/health/trusport_ee/Profile;", "", "age", "", "height", "", "weight", "sex", "Lcom/huawei/health/trusport_ee/Sex;", "(IFFLcom/huawei/health/trusport_ee/Sex;)V", "getAge", "()I", "getHeight", "()F", "getSex", "()Lcom/huawei/health/trusport_ee/Sex;", "getWeight", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", WatchFaceConstant.HASHCODE, "toString", "", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class glb {
    private final float b;
    private final Sex c;
    private final float d;
    private final int e;

    public glb(int i, float f, float f2, Sex sex) {
        uhy.e((Object) sex, "");
        this.e = i;
        this.b = f;
        this.d = f2;
        this.c = sex;
    }

    /* renamed from: e, reason: from getter */
    public final int getE() {
        return this.e;
    }

    /* renamed from: b, reason: from getter */
    public final float getB() {
        return this.b;
    }

    /* renamed from: d, reason: from getter */
    public final float getD() {
        return this.d;
    }

    /* renamed from: a, reason: from getter */
    public final Sex getC() {
        return this.c;
    }

    public String toString() {
        return "Profile(age=" + this.e + ", height=" + this.b + ", weight=" + this.d + ", sex=" + this.c + Constants.RIGHT_BRACKET_ONLY;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.e) * 31) + Float.hashCode(this.b)) * 31) + Float.hashCode(this.d)) * 31) + this.c.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof glb)) {
            return false;
        }
        glb glbVar = (glb) other;
        return this.e == glbVar.e && Float.compare(this.b, glbVar.b) == 0 && Float.compare(this.d, glbVar.d) == 0 && this.c == glbVar.c;
    }
}
