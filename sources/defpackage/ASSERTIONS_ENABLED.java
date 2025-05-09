package defpackage;

import com.huawei.operation.utils.Constants;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0081\b\u001a\b\u0010\u0016\u001a\u00020\u0013H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u0014\u0010\u000f\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0003\"\u000e\u0010\u0011\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", Constants.LOG_DEBUG, "getDEBUG", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "assert", "", "value", "Lkotlin/Function0;", "resetCoroutineId", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: uln, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class ASSERTIONS_ENABLED {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f17458a;
    private static final boolean b = false;
    private static final boolean d;
    private static final AtomicLong e;

    public static final boolean a() {
        return b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0025, code lost:
    
        if (r0.equals("auto") != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003a, code lost:
    
        if (r0.equals("on") != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0045, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0043, code lost:
    
        if (r0.equals("") != false) goto L23;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.debug"
            java.lang.String r0 = defpackage.upy.c(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L64
            int r3 = r0.hashCode()
            if (r3 == 0) goto L3d
            r4 = 3551(0xddf, float:4.976E-42)
            if (r3 == r4) goto L33
            r4 = 109935(0x1ad6f, float:1.54052E-40)
            if (r3 == r4) goto L28
            r4 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r3 != r4) goto L47
            java.lang.String r3 = "auto"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L47
            goto L64
        L28:
            java.lang.String r3 = "off"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L47
            r0 = r2
            goto L68
        L33:
            java.lang.String r3 = "on"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L47
            goto L45
        L3d:
            java.lang.String r3 = ""
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L47
        L45:
            r0 = r1
            goto L68
        L47:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
            r2.<init>(r3)
            r2.append(r0)
            r0 = 39
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L64:
            boolean r0 = a()
        L68:
            defpackage.ASSERTIONS_ENABLED.f17458a = r0
            if (r0 == 0) goto L76
            java.lang.String r0 = "kotlinx.coroutines.stacktrace.recovery"
            boolean r0 = defpackage.upy.c(r0, r1)
            if (r0 == 0) goto L76
            goto L77
        L76:
            r1 = r2
        L77:
            defpackage.ASSERTIONS_ENABLED.d = r1
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r1 = 0
            r0.<init>(r1)
            defpackage.ASSERTIONS_ENABLED.e = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ASSERTIONS_ENABLED.<clinit>():void");
    }

    public static final boolean d() {
        return f17458a;
    }

    public static final boolean b() {
        return d;
    }

    public static final AtomicLong c() {
        return e;
    }
}
