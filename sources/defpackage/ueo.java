package defpackage;

import com.huawei.hms.network.embedded.g4;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.Serializable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00060\u0003j\u0002`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0001¢\u0006\u0002\u0010\u0007J\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\u000e\u0010\r\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lkotlin/Pair;", "A", "B", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "first", "second", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getFirst", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSecond", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class ueo<A, B> implements Serializable {
    private final A b;
    private final B e;

    public ueo(A a2, B b) {
        this.b = a2;
        this.e = b;
    }

    public final A e() {
        return this.b;
    }

    public final B c() {
        return this.e;
    }

    public String toString() {
        return Constants.LEFT_BRACKET_ONLY + this.b + ", " + this.e + g4.l;
    }

    public int hashCode() {
        A a2 = this.b;
        int hashCode = a2 == null ? 0 : a2.hashCode();
        B b = this.e;
        return (hashCode * 31) + (b != null ? b.hashCode() : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ueo)) {
            return false;
        }
        ueo ueoVar = (ueo) other;
        return uhy.e(this.b, ueoVar.b) && uhy.e(this.e, ueoVar.e);
    }

    public final B d() {
        return this.e;
    }

    public final A b() {
        return this.b;
    }
}
