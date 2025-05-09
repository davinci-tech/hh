package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.random.Random;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u0013"}, d2 = {"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", TrackConstants$Events.EXCEPTION, "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getSuppressed", "", "ReflectThrowable", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
public class uhg {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class a {
        public static final Method b;
        public static final a c = new a();
        public static final Method e;

        private a() {
        }

        static {
            Method method;
            Method method2;
            Method[] methods = Throwable.class.getMethods();
            uhy.a(methods, "");
            int length = methods.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                method = null;
                if (i2 >= length) {
                    method2 = null;
                    break;
                }
                method2 = methods[i2];
                if (uhy.e((Object) method2.getName(), (Object) "addSuppressed")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    uhy.a(parameterTypes, "");
                    if (uhy.e(uez.a(parameterTypes), Throwable.class)) {
                        break;
                    }
                }
                i2++;
            }
            b = method2;
            int length2 = methods.length;
            while (true) {
                if (i >= length2) {
                    break;
                }
                Method method3 = methods[i];
                if (uhy.e((Object) method3.getName(), (Object) "getSuppressed")) {
                    method = method3;
                    break;
                }
                i++;
            }
            e = method;
        }
    }

    public void c(Throwable th, Throwable th2) {
        uhy.e((Object) th, "");
        uhy.e((Object) th2, "");
        Method method = a.b;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    public Random d() {
        return new uin();
    }
}
