package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.watchface.videoedit.gles.transition.SquareChangeAnimation;
import defpackage.uig;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0000H\u0002J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J5\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u0002H\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016¢\u0006\u0002\u0010\u0014J(\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", SquareChangeAnimation.LEFT, FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "contains", "", "containsAll", ParamConstants.Param.CONTEXT, "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, MedalConstants.EVENT_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", WatchFaceConstant.HASHCODE, "", "minusKey", "size", "toString", "", "writeReplace", "Serialized", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ugs implements CoroutineContext, Serializable {
    private final CoroutineContext b;
    private final CoroutineContext.Element c;

    public ugs(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        uhy.e((Object) coroutineContext, "");
        uhy.e((Object) element, "");
        this.b = coroutineContext;
        this.c = element;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.d.e(this, coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        uhy.e((Object) key, "");
        ugs ugsVar = this;
        while (true) {
            E e2 = (E) ugsVar.c.get(key);
            if (e2 != null) {
                return e2;
            }
            CoroutineContext coroutineContext = ugsVar.b;
            if (coroutineContext instanceof ugs) {
                ugsVar = (ugs) coroutineContext;
            } else {
                return (E) coroutineContext.get(key);
            }
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R initial, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        uhy.e((Object) operation, "");
        return operation.invoke((Object) this.b.fold(initial, operation), this.c);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        uhy.e((Object) key, "");
        if (this.c.get(key) != null) {
            return this.b;
        }
        CoroutineContext minusKey = this.b.minusKey(key);
        return minusKey == this.b ? this : minusKey == ugq.f17420a ? this.c : new ugs(minusKey, this.c);
    }

    private final int c() {
        int i = 2;
        ugs ugsVar = this;
        while (true) {
            CoroutineContext coroutineContext = ugsVar.b;
            ugsVar = coroutineContext instanceof ugs ? (ugs) coroutineContext : null;
            if (ugsVar == null) {
                return i;
            }
            i++;
        }
    }

    private final boolean b(CoroutineContext.Element element) {
        return uhy.e(get(element.getKey()), element);
    }

    private final boolean b(ugs ugsVar) {
        while (b(ugsVar.c)) {
            CoroutineContext coroutineContext = ugsVar.b;
            if (coroutineContext instanceof ugs) {
                ugsVar = (ugs) coroutineContext;
            } else {
                uhy.b(coroutineContext, "");
                return b((CoroutineContext.Element) coroutineContext);
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof ugs) {
                ugs ugsVar = (ugs) other;
                if (ugsVar.c() != c() || !ugsVar.b(this)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.b.hashCode() + this.c.hashCode();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "acc", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class e extends Lambda implements Function2<String, CoroutineContext.Element, String> {
        public static final e e = new e();

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str, CoroutineContext.Element element) {
            uhy.e((Object) str, "");
            uhy.e((Object) element, "");
            if (str.length() == 0) {
                return element.toString();
            }
            return str + ", " + element;
        }

        e() {
            super(2);
        }
    }

    public String toString() {
        return "[" + ((String) fold("", e.e)) + ']';
    }

    private final Object writeReplace() {
        int c2 = c();
        CoroutineContext[] coroutineContextArr = new CoroutineContext[c2];
        uig.a aVar = new uig.a();
        fold(ueu.d, new c(coroutineContextArr, aVar));
        if (aVar.d != c2) {
            throw new IllegalStateException("Check failed.".toString());
        }
        return new a(coroutineContextArr);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE, "(Lkotlin/Unit;Lkotlin/coroutines/CoroutineContext$Element;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class c extends Lambda implements Function2<ueu, CoroutineContext.Element, ueu> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CoroutineContext[] f17423a;
        final /* synthetic */ uig.a d;

        public final void a(ueu ueuVar, CoroutineContext.Element element) {
            uhy.e((Object) ueuVar, "");
            uhy.e((Object) element, "");
            CoroutineContext[] coroutineContextArr = this.f17423a;
            int i = this.d.d;
            this.d.d = i + 1;
            coroutineContextArr[i] = element;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* synthetic */ ueu invoke(ueu ueuVar, CoroutineContext.Element element) {
            a(ueuVar, element);
            return ueu.d;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(CoroutineContext[] coroutineContextArr, uig.a aVar) {
            super(2);
            this.f17423a = coroutineContextArr;
            this.d = aVar;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \f2\u00060\u0001j\u0002`\u0002:\u0001\fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", "", "Lkotlin/coroutines/CoroutineContext;", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final C0335a f17422a = new C0335a(null);
        private static final long serialVersionUID = 0;
        private final CoroutineContext[] c;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* renamed from: ugs$a$a, reason: collision with other inner class name */
        public static final class C0335a {
            private C0335a() {
            }

            public /* synthetic */ C0335a(uib uibVar) {
                this();
            }
        }

        public a(CoroutineContext[] coroutineContextArr) {
            uhy.e((Object) coroutineContextArr, "");
            this.c = coroutineContextArr;
        }

        private final Object readResolve() {
            CoroutineContext[] coroutineContextArr = this.c;
            CoroutineContext coroutineContext = ugq.f17420a;
            for (CoroutineContext coroutineContext2 : coroutineContextArr) {
                coroutineContext = coroutineContext.plus(coroutineContext2);
            }
            return coroutineContext;
        }
    }
}
