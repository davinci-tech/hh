package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import defpackage.uel;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u001a\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\t\u001a\u00020\u0001H\u0007\u001a9\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u0002H\u000b2\u0006\u0010\u000e\u001a\u0002H\u000b2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00102\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002\u001a1\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\fH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a+\u0010\u001f\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002¢\u0006\u0002\u0010 \u001a\u001f\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a,\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030#H\u0080\b¢\u0006\u0002\u0010$\u001a!\u0010%\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0002¢\u0006\u0002\u0010\"\u001a \u0010&\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0080\b¢\u0006\u0002\u0010\"\u001a\u001f\u0010'\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a1\u0010(\u001a\u0018\u0012\u0004\u0012\u0002H\u000b\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00190)\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020,*\u00060\u0007j\u0002`\b2\n\u0010-\u001a\u00060\u0007j\u0002`\bH\u0002\u001a#\u0010.\u001a\u00020/*\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0006\u00100\u001a\u00020\u0001H\u0002¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\u0017*\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000\u001a\u0010\u00103\u001a\u00020,*\u00060\u0007j\u0002`\bH\u0000\u001a\u001b\u00104\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010\"\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u00105\"\u00020\u00142\u00020\u0014*\f\b\u0000\u00106\"\u00020\u00072\u00020\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"baseContinuationImplClass", "", "baseContinuationImplClassName", "kotlin.jvm.PlatformType", "stackTraceRecoveryClass", "stackTraceRecoveryClassName", "artificialFrame", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "message", "createFinalException", ExifInterface.LONGITUDE_EAST, "", "cause", "result", "resultStackTrace", "Ljava/util/ArrayDeque;", "(Ljava/lang/Throwable;Ljava/lang/Throwable;Ljava/util/ArrayDeque;)Ljava/lang/Throwable;", "createStackTrace", "continuation", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "mergeRecoveredTraces", "", "recoveredStacktrace", "", "([Ljava/lang/StackTraceElement;Ljava/util/ArrayDeque;)V", "recoverAndThrow", "", TrackConstants$Events.EXCEPTION, "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverFromStackFrame", "(Ljava/lang/Throwable;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/lang/Throwable;", "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Throwable;", "tryCopyAndVerify", "unwrap", "unwrapImpl", "causeAndStacktrace", "Lkotlin/Pair;", "(Ljava/lang/Throwable;)Lkotlin/Pair;", "elementWiseEquals", "", "e", "frameIndex", "", "methodName", "([Ljava/lang/StackTraceElement;Ljava/lang/String;)I", "initCause", "isArtificial", "sanitizeStackTrace", "CoroutineStackFrame", "StackTraceElement", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ups, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class baseContinuationImplClass {
    private static final String b;
    private static final String e;

    static {
        Object obj;
        Object obj2;
        try {
            uel.b bVar = uel.d;
            obj = uel.b(Class.forName("kotlin.coroutines.jvm.internal.BaseContinuationImpl").getCanonicalName());
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            obj = uel.b(createFailure.b(th));
        }
        b = (String) (uel.e(obj) == null ? obj : "kotlin.coroutines.jvm.internal.BaseContinuationImpl");
        try {
            uel.b bVar3 = uel.d;
            obj2 = uel.b(Class.forName("ups").getCanonicalName());
        } catch (Throwable th2) {
            uel.b bVar4 = uel.d;
            obj2 = uel.b(createFailure.b(th2));
        }
        e = (String) (uel.e(obj2) == null ? obj2 : "ups");
    }

    public static final <E extends Throwable> E a(E e2) {
        Throwable d;
        return (ASSERTIONS_ENABLED.b() && (d = d(e2)) != null) ? (E) b(d) : e2;
    }

    private static final <E extends Throwable> E b(E e2) {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = e2.getStackTrace();
        int length = stackTrace.length;
        int e3 = e(stackTrace, e);
        int e4 = e(stackTrace, b);
        int i = (length - e3) - (e4 == -1 ? 0 : length - e4);
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stackTraceElement = c("Coroutine boundary");
            } else {
                stackTraceElement = stackTrace[((e3 + 1) + i2) - 1];
            }
            stackTraceElementArr[i2] = stackTraceElement;
        }
        e2.setStackTrace(stackTraceElementArr);
        return e2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E extends Throwable> E c(E e2, CoroutineStackFrame coroutineStackFrame) {
        ueo c = c(e2);
        Throwable th = (Throwable) c.b();
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) c.d();
        Throwable d = d(th);
        if (d == null) {
            return e2;
        }
        ArrayDeque<StackTraceElement> c2 = c(coroutineStackFrame);
        if (c2.isEmpty()) {
            return e2;
        }
        if (th != e2) {
            e(stackTraceElementArr, c2);
        }
        return (E) d(th, d, c2);
    }

    private static final <E extends Throwable> E d(E e2) {
        E e3 = (E) ctorCache.d(e2);
        if (e3 == null) {
            return null;
        }
        if ((e2 instanceof CopyableThrowable) || uhy.e((Object) e3.getMessage(), (Object) e2.getMessage())) {
            return e3;
        }
        return null;
    }

    private static final <E extends Throwable> E d(E e2, E e3, ArrayDeque<StackTraceElement> arrayDeque) {
        arrayDeque.addFirst(c("Coroutine boundary"));
        StackTraceElement[] stackTrace = e2.getStackTrace();
        int e4 = e(stackTrace, b);
        int i = 0;
        if (e4 != -1) {
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[arrayDeque.size() + e4];
            for (int i2 = 0; i2 < e4; i2++) {
                stackTraceElementArr[i2] = stackTrace[i2];
            }
            Iterator<StackTraceElement> it = arrayDeque.iterator();
            while (it.hasNext()) {
                stackTraceElementArr[i + e4] = it.next();
                i++;
            }
            e3.setStackTrace(stackTraceElementArr);
            return e3;
        }
        Object[] array = arrayDeque.toArray(new StackTraceElement[0]);
        if (array != null) {
            e3.setStackTrace((StackTraceElement[]) array);
            return e3;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    private static final <E extends Throwable> ueo<E, StackTraceElement[]> c(E e2) {
        Throwable cause = e2.getCause();
        if (cause != null && uhy.e(cause.getClass(), e2.getClass())) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (i < length) {
                StackTraceElement stackTraceElement = stackTrace[i];
                i++;
                if (a(stackTraceElement)) {
                    return to.d(cause, stackTrace);
                }
            }
            return to.d(e2, new StackTraceElement[0]);
        }
        return to.d(e2, new StackTraceElement[0]);
    }

    public static final <E extends Throwable> E e(E e2) {
        E e3 = (E) e2.getCause();
        if (e3 != null && uhy.e(e3.getClass(), e2.getClass())) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (i < length) {
                StackTraceElement stackTraceElement = stackTrace[i];
                i++;
                if (a(stackTraceElement)) {
                    return e3;
                }
            }
        }
        return e2;
    }

    private static final ArrayDeque<StackTraceElement> c(CoroutineStackFrame coroutineStackFrame) {
        ArrayDeque<StackTraceElement> arrayDeque = new ArrayDeque<>();
        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
        if (stackTraceElement != null) {
            arrayDeque.add(stackTraceElement);
        }
        while (true) {
            if (!(coroutineStackFrame instanceof CoroutineStackFrame)) {
                coroutineStackFrame = null;
            }
            coroutineStackFrame = coroutineStackFrame == null ? null : coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return arrayDeque;
            }
            StackTraceElement stackTraceElement2 = coroutineStackFrame.getStackTraceElement();
            if (stackTraceElement2 != null) {
                arrayDeque.add(stackTraceElement2);
            }
        }
    }

    public static final StackTraceElement c(String str) {
        return new StackTraceElement(uhy.b("\b\b\b(", (Object) str), "\b", "\b", -1);
    }

    public static final boolean a(StackTraceElement stackTraceElement) {
        return ujy.c(stackTraceElement.getClassName(), "\b\b\b", false, 2, (Object) null);
    }

    private static final boolean a(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        return stackTraceElement.getLineNumber() == stackTraceElement2.getLineNumber() && uhy.e((Object) stackTraceElement.getMethodName(), (Object) stackTraceElement2.getMethodName()) && uhy.e((Object) stackTraceElement.getFileName(), (Object) stackTraceElement2.getFileName()) && uhy.e((Object) stackTraceElement.getClassName(), (Object) stackTraceElement2.getClassName());
    }

    private static final void e(StackTraceElement[] stackTraceElementArr, ArrayDeque<StackTraceElement> arrayDeque) {
        int length = stackTraceElementArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (a(stackTraceElementArr[i])) {
                break;
            } else {
                i++;
            }
        }
        int i2 = i + 1;
        int length2 = stackTraceElementArr.length - 1;
        if (i2 > length2) {
            return;
        }
        while (true) {
            if (a(stackTraceElementArr[length2], arrayDeque.getLast())) {
                arrayDeque.removeLast();
            }
            arrayDeque.addFirst(stackTraceElementArr[length2]);
            if (length2 == i2) {
                return;
            } else {
                length2--;
            }
        }
    }

    private static final int e(StackTraceElement[] stackTraceElementArr, String str) {
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            if (uhy.e((Object) str, (Object) stackTraceElementArr[i].getClassName())) {
                return i;
            }
        }
        return -1;
    }
}
