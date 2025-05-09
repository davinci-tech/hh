package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.uig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CopyableThreadContextElement;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0002\u001a8\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\f0\u0012H\u0080\b¢\u0006\u0002\u0010\u0013\u001a4\u0010\u0014\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\u0015\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\f0\u0012H\u0080\b¢\u0006\u0002\u0010\u0016\u001a\f\u0010\u0017\u001a\u00020\n*\u00020\u0003H\u0002\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0003H\u0007\u001a\u0013\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u00020\u001dH\u0080\u0010\u001a(\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0015\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006 "}, d2 = {"DEBUG_THREAD_NAME_SEPARATOR", "", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "foldCopies", "originalContext", "appendContext", "isNewCoroutine", "", "withContinuationContext", ExifInterface.GPS_DIRECTION_TRUE, "continuation", "Lkotlin/coroutines/Continuation;", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCoroutineContext", ParamConstants.Param.CONTEXT, "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "hasCopyableElements", "newCoroutineContext", "addedContext", "Lkotlinx/coroutines/CoroutineScope;", "undispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateUndispatchedCompletion", "oldValue", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulh, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class DEBUG_THREAD_NAME_SEPARATOR {
    public static final CoroutineContext d(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext e = e(coroutineScope.getCoroutineContext(), coroutineContext, true);
        CoroutineContext plus = ASSERTIONS_ENABLED.d() ? e.plus(new ulf(ASSERTIONS_ENABLED.c().incrementAndGet())) : e;
        return (e == ulo.d() || e.get(ContinuationInterceptor.INSTANCE) != null) ? plus : plus.plus(ulo.d());
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "result", "it", "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE, "(ZLkotlin/coroutines/CoroutineContext$Element;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: ulh$b */
    static final class b extends Lambda implements Function2<Boolean, CoroutineContext.Element, Boolean> {

        /* renamed from: a, reason: collision with root package name */
        public static final b f17457a = new b();

        public final Boolean d(boolean z, CoroutineContext.Element element) {
            return Boolean.valueOf(z || (element instanceof CopyableThreadContextElement));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* synthetic */ Boolean invoke(Boolean bool, CoroutineContext.Element element) {
            return d(bool.booleanValue(), element);
        }

        b() {
            super(2);
        }
    }

    private static final boolean b(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.fold(false, b.f17457a)).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6, types: [T, java.lang.Object] */
    private static final CoroutineContext e(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, boolean z) {
        boolean b2 = b(coroutineContext);
        boolean b3 = b(coroutineContext2);
        if (!b2 && !b3) {
            return coroutineContext.plus(coroutineContext2);
        }
        uig.e eVar = new uig.e();
        eVar.d = coroutineContext2;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(ugq.f17420a, new d(eVar, z));
        if (b3) {
            eVar.d = ((CoroutineContext) eVar.d).fold(ugq.f17420a, a.e);
        }
        return coroutineContext3.plus((CoroutineContext) eVar.d);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "result", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: ulh$d */
    static final class d extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
        final /* synthetic */ uig.e<CoroutineContext> c;
        final /* synthetic */ boolean e;

        /* JADX WARN: Type inference failed for: r2v2, types: [T, kotlin.coroutines.CoroutineContext] */
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final CoroutineContext invoke(CoroutineContext coroutineContext, CoroutineContext.Element element) {
            if (!(element instanceof CopyableThreadContextElement)) {
                return coroutineContext.plus(element);
            }
            CoroutineContext.Element element2 = this.c.d.get(element.getKey());
            if (element2 == null) {
                CopyableThreadContextElement copyableThreadContextElement = (CopyableThreadContextElement) element;
                if (this.e) {
                    copyableThreadContextElement = copyableThreadContextElement.copyForChild();
                }
                return coroutineContext.plus(copyableThreadContextElement);
            }
            uig.e<CoroutineContext> eVar = this.c;
            eVar.d = eVar.d.minusKey(element.getKey());
            return coroutineContext.plus(((CopyableThreadContextElement) element).mergeForChild(element2));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(uig.e<CoroutineContext> eVar, boolean z) {
            super(2);
            this.c = eVar;
            this.e = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "result", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: ulh$a */
    static final class a extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {
        public static final a e = new a();

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final CoroutineContext invoke(CoroutineContext coroutineContext, CoroutineContext.Element element) {
            if (element instanceof CopyableThreadContextElement) {
                return coroutineContext.plus(((CopyableThreadContextElement) element).copyForChild());
            }
            return coroutineContext.plus(element);
        }

        a() {
            super(2);
        }
    }

    public static final umt<?> c(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        if (!(continuation instanceof CoroutineStackFrame) || coroutineContext.get(ums.f17469a) == null) {
            return null;
        }
        umt<?> a2 = a((CoroutineStackFrame) continuation);
        if (a2 != null) {
            a2.e(coroutineContext, obj);
        }
        return a2;
    }

    public static final umt<?> a(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof ulr) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof umt) {
                return (umt) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final String d(CoroutineContext coroutineContext) {
        ulf ulfVar;
        String str;
        if (!ASSERTIONS_ENABLED.d() || (ulfVar = (ulf) coroutineContext.get(ulf.f17455a)) == null) {
            return null;
        }
        uli uliVar = (uli) coroutineContext.get(uli.b);
        if (uliVar == null || (str = uliVar.getE()) == null) {
            str = "coroutine";
        }
        return str + '#' + ulfVar.getE();
    }
}
