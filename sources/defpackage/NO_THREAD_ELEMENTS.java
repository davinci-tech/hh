package defpackage;

import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0002\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\",\u0010\u0006\u001a \u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"NO_THREAD_ELEMENTS", "Lkotlinx/coroutines/internal/Symbol;", "countAll", "Lkotlin/Function2;", "", "Lkotlin/coroutines/CoroutineContext$Element;", "findOne", "Lkotlinx/coroutines/ThreadContextElement;", "updateState", "Lkotlinx/coroutines/internal/ThreadState;", "restoreThreadContext", "", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "oldState", "threadContextElements", "updateThreadContext", "countOrElement", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: upv, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class NO_THREAD_ELEMENTS {
    public static final upu d = new upu("NO_THREAD_ELEMENTS");
    private static final Function2<Object, CoroutineContext.Element, Object> c = b.d;
    private static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> b = d.f17501a;
    private static final Function2<uqb, CoroutineContext.Element, uqb> e = e.b;

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "", "countOrElement", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upv$b */
    static final class b extends Lambda implements Function2<Object, CoroutineContext.Element, Object> {
        public static final b d = new b();

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Object invoke(Object obj, CoroutineContext.Element element) {
            if (!(element instanceof ThreadContextElement)) {
                return obj;
            }
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            int intValue = num == null ? 1 : num.intValue();
            return intValue == 0 ? element : Integer.valueOf(intValue + 1);
        }

        b() {
            super(2);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "Lkotlinx/coroutines/ThreadContextElement;", "found", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upv$d */
    static final class d extends Lambda implements Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> {

        /* renamed from: a, reason: collision with root package name */
        public static final d f17501a = new d();

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final ThreadContextElement<?> invoke(ThreadContextElement<?> threadContextElement, CoroutineContext.Element element) {
            if (threadContextElement != null) {
                return threadContextElement;
            }
            if (element instanceof ThreadContextElement) {
                return (ThreadContextElement) element;
            }
            return null;
        }

        d() {
            super(2);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<no name provided>", "Lkotlinx/coroutines/internal/ThreadState;", "state", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upv$e */
    static final class e extends Lambda implements Function2<uqb, CoroutineContext.Element, uqb> {
        public static final e b = new e();

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final uqb invoke(uqb uqbVar, CoroutineContext.Element element) {
            if (element instanceof ThreadContextElement) {
                ThreadContextElement<?> threadContextElement = (ThreadContextElement) element;
                uqbVar.a(threadContextElement, threadContextElement.updateThreadContext(uqbVar.b));
            }
            return uqbVar;
        }

        e() {
            super(2);
        }
    }

    public static final Object b(CoroutineContext coroutineContext) {
        Object fold = coroutineContext.fold(0, c);
        uhy.d(fold);
        return fold;
    }

    public static final Object d(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = b(coroutineContext);
        }
        if (obj == 0) {
            return d;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new uqb(coroutineContext, ((Number) obj).intValue()), e);
        }
        return ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }

    public static final void a(CoroutineContext coroutineContext, Object obj) {
        if (obj == d) {
            return;
        }
        if (obj instanceof uqb) {
            ((uqb) obj).e(coroutineContext);
            return;
        }
        Object fold = coroutineContext.fold(null, b);
        if (fold == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        }
        ((ThreadContextElement) fold).restoreThreadContext(coroutineContext, obj);
    }
}
