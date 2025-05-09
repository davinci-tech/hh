package kotlin.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.ugq;
import defpackage.ugs;
import defpackage.uhy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¨\u0006\u0013"}, d2 = {"Lkotlin/coroutines/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, MedalConstants.EVENT_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "plus", ParamConstants.Param.CONTEXT, "Element", "Key", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface CoroutineContext {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", ExifInterface.LONGITUDE_EAST, "Lkotlin/coroutines/CoroutineContext$Element;", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Key<E extends Element> {
    }

    <R> R fold(R initial, Function2<? super R, ? super Element, ? extends R> operation);

    <E extends Element> E get(Key<E> key);

    CoroutineContext minusKey(Key<?> key);

    CoroutineContext plus(CoroutineContext context);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class d {
        public static CoroutineContext e(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
            uhy.e((Object) coroutineContext2, "");
            return coroutineContext2 == ugq.f17420a ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, C0316d.f14477a);
        }

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "acc", FunctionSetBeanReader.BI_ELEMENT, "Lkotlin/coroutines/CoroutineContext$Element;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
        /* renamed from: kotlin.coroutines.CoroutineContext$d$d, reason: collision with other inner class name */
        static final class C0316d extends Lambda implements Function2<CoroutineContext, Element, CoroutineContext> {

            /* renamed from: a, reason: collision with root package name */
            public static final C0316d f14477a = new C0316d();

            @Override // kotlin.jvm.functions.Function2
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public final CoroutineContext invoke(CoroutineContext coroutineContext, Element element) {
                ugs ugsVar;
                uhy.e((Object) coroutineContext, "");
                uhy.e((Object) element, "");
                CoroutineContext minusKey = coroutineContext.minusKey(element.getKey());
                if (minusKey == ugq.f17420a) {
                    return element;
                }
                ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(ContinuationInterceptor.INSTANCE);
                if (continuationInterceptor == null) {
                    ugsVar = new ugs(minusKey, element);
                } else {
                    CoroutineContext minusKey2 = minusKey.minusKey(ContinuationInterceptor.INSTANCE);
                    ugsVar = minusKey2 == ugq.f17420a ? new ugs(element, continuationInterceptor) : new ugs(new ugs(minusKey2, element), continuationInterceptor);
                }
                return ugsVar;
            }

            C0316d() {
                super(2);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", MedalConstants.EVENT_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", ExifInterface.LONGITUDE_EAST, "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Element extends CoroutineContext {
        @Override // kotlin.coroutines.CoroutineContext
        <R> R fold(R initial, Function2<? super R, ? super Element, ? extends R> operation);

        @Override // kotlin.coroutines.CoroutineContext
        <E extends Element> E get(Key<E> key);

        Key<?> getKey();

        @Override // kotlin.coroutines.CoroutineContext
        CoroutineContext minusKey(Key<?> key);

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class b {
            public static CoroutineContext b(Element element, CoroutineContext coroutineContext) {
                uhy.e((Object) coroutineContext, "");
                return d.e(element, coroutineContext);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E d(Element element, Key<E> key) {
                uhy.e((Object) key, "");
                if (!uhy.e(element.getKey(), key)) {
                    return null;
                }
                uhy.b(element, "");
                return element;
            }

            public static <R> R b(Element element, R r, Function2<? super R, ? super Element, ? extends R> function2) {
                uhy.e((Object) function2, "");
                return function2.invoke(r, element);
            }

            public static CoroutineContext e(Element element, Key<?> key) {
                uhy.e((Object) key, "");
                boolean e = uhy.e(element.getKey(), key);
                Object obj = element;
                if (e) {
                    obj = ugq.f17420a;
                }
                return (CoroutineContext) obj;
            }
        }
    }
}
