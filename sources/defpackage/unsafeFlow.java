package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0005\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0018\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0001\u001a\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0080\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"unsafeFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "checkContext", "Lkotlinx/coroutines/flow/internal/SafeCollector;", "currentContext", "Lkotlin/coroutines/CoroutineContext;", "transitiveCoroutineParent", "Lkotlinx/coroutines/Job;", "collectJob", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: uou, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class unsafeFlow {

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "count", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE, "(ILkotlin/coroutines/CoroutineContext$Element;)Ljava/lang/Integer;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: uou$e */
    static final class e extends Lambda implements Function2<Integer, CoroutineContext.Element, Integer> {
        final /* synthetic */ uop<?> c;

        @Override // kotlin.jvm.functions.Function2
        public /* synthetic */ Integer invoke(Integer num, CoroutineContext.Element element) {
            return b(num.intValue(), element);
        }

        public final Integer b(int i, CoroutineContext.Element element) {
            CoroutineContext.Key<?> key = element.getKey();
            CoroutineContext.Element element2 = this.c.e.get(key);
            if (key != Job.INSTANCE) {
                return Integer.valueOf(element != element2 ? Integer.MIN_VALUE : i + 1);
            }
            Job job = (Job) element2;
            Job c = unsafeFlow.c((Job) element, job);
            if (c != job) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + c + ", expected child of " + job + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
            }
            if (job != null) {
                i++;
            }
            return Integer.valueOf(i);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(uop<?> uopVar) {
            super(2);
            this.c = uopVar;
        }
    }

    public static final void a(uop<?> uopVar, CoroutineContext coroutineContext) {
        if (((Number) coroutineContext.fold(0, new e(uopVar))).intValue() == uopVar.b) {
            return;
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + uopVar.e + ",\n\t\tbut emission happened in " + coroutineContext + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
    }

    public static final Job c(Job job, Job job2) {
        while (job != null) {
            if (job == job2 || !(job instanceof upq)) {
                return job;
            }
            job = ((upq) job).e();
        }
        return null;
    }
}
