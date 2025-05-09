package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\b\u0010\u0012\u001a\u00020\u000eH\u0002J\r\u0010\u0013\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0014R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/internal/ArrayQueue;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "elements", "", "[Ljava/lang/Object;", "head", "", "isEmpty", "", "()Z", "tail", "addLast", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)V", "clear", "ensureCapacity", "removeFirstOrNull", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class uox<T> {

    /* renamed from: a, reason: collision with root package name */
    private int f17492a;
    private int b;
    private Object[] d = new Object[16];

    public final boolean e() {
        return this.f17492a == this.b;
    }

    public final void e(T t) {
        Object[] objArr = this.d;
        int i = this.b;
        objArr[i] = t;
        int length = (objArr.length - 1) & (i + 1);
        this.b = length;
        if (length == this.f17492a) {
            d();
        }
    }

    public final T b() {
        int i = this.f17492a;
        if (i == this.b) {
            return null;
        }
        Object[] objArr = this.d;
        T t = (T) objArr[i];
        objArr[i] = null;
        this.f17492a = (i + 1) & (objArr.length - 1);
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
    }

    private final void d() {
        Object[] objArr = this.d;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        uez.d(objArr, objArr2, 0, this.f17492a, 0, 10, null);
        Object[] objArr3 = this.d;
        int length2 = objArr3.length;
        int i = this.f17492a;
        uez.d(objArr3, objArr2, length2 - i, 0, i, 4, null);
        this.d = objArr2;
        this.f17492a = 0;
        this.b = length;
    }
}
