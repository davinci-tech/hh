package defpackage;

import com.huawei.operation.utils.Constants;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;

/* loaded from: classes7.dex */
public class uxr extends Message {

    /* renamed from: a, reason: collision with root package name */
    private final CoAP.ResponseCode f17577a;
    private volatile Long b;
    private final boolean c;
    private volatile Long d;

    public uxr(CoAP.ResponseCode responseCode) {
        this(responseCode, false);
    }

    public uxr(CoAP.ResponseCode responseCode, boolean z) {
        if (responseCode == null) {
            throw new NullPointerException("ResponseCode must not be null!");
        }
        this.f17577a = responseCode;
        this.c = z;
    }

    public CoAP.ResponseCode a() {
        return this.f17577a;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public int getRawCode() {
        return this.f17577a.value;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void assertPayloadMatchsBlocksize() {
        uxh j = getOptions().j();
        if (j != null) {
            j.b(getPayloadSize());
        }
    }

    public String toString() {
        return toTracingString(this.f17577a.toString());
    }

    public Long d() {
        return this.b;
    }

    public void e(long j) {
        this.b = Long.valueOf(j);
    }

    public Long e() {
        return this.d;
    }

    public void d(long j) {
        this.d = Long.valueOf(j);
    }

    public void e(uxu uxuVar) {
        uxu token = getToken();
        if (token == null) {
            setToken(uxuVar);
            return;
        }
        if (token.equals(uxuVar)) {
            return;
        }
        throw new IllegalArgumentException("token mismatch! (" + token + "!=" + uxuVar + Constants.RIGHT_BRACKET_ONLY);
    }

    public boolean g() {
        return getOptions().an();
    }

    public boolean c() {
        return getOptions().ai() || getOptions().ae();
    }

    @Override // org.eclipse.californium.core.coap.Message
    public boolean hasBlock(uxh uxhVar) {
        return hasBlock(uxhVar, getOptions().j());
    }

    public final boolean i() {
        return this.f17577a.isSuccess();
    }

    public final boolean f() {
        return b() || h();
    }

    public final boolean b() {
        return this.f17577a.isClientError();
    }

    public final boolean h() {
        return this.f17577a.isServerError();
    }
}
