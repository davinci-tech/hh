package defpackage;

import java.util.Arrays;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.BlockwiseStatus;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uys extends BlockwiseStatus {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17605a = vha.a((Class<?>) uys.class);
    private final byte[] c;
    private final uzq e;

    private uys(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxr uxrVar, int i, int i2) {
        super(uzeVar, removeHandler, exchange, uxrVar, i, i2);
        Integer t = uxrVar.getOptions().t();
        if (t != null && uxv.a(t.intValue())) {
            this.e = new uzq(t.intValue());
            exchange.a(t.intValue());
        } else {
            this.e = null;
        }
        if (uxrVar.getOptions().i() > 0) {
            this.c = uxrVar.getOptions().g().get(0);
        } else {
            this.c = null;
        }
    }

    public static uys c(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxr uxrVar, int i) {
        int payloadSize = uxrVar.getPayloadSize();
        uys uysVar = new uys(uzeVar, removeHandler, exchange, uxrVar, payloadSize, i);
        if (payloadSize > 0) {
            try {
                uysVar.addBlock(uxrVar.getPayload(), uxrVar.getMessageSize());
                uysVar.flipBlocksBuffer();
            } catch (uyx e) {
                f17605a.warn("buffer overflow on start", (Throwable) e);
            }
        }
        return uysVar;
    }

    public static uys e(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxr uxrVar, int i, int i2) {
        if (uxrVar.getOptions().ap()) {
            i = uxrVar.getOptions().v().intValue();
        }
        return new uys(uzeVar, removeHandler, exchange, uxrVar, i, i2);
    }

    public final Integer d() {
        uzq uzqVar = this.e;
        if (uzqVar == null) {
            return null;
        }
        return Integer.valueOf(uzqVar.c());
    }

    public final boolean b(uxr uxrVar) {
        if (uxrVar == null) {
            throw new NullPointerException("response block must not be null");
        }
        if (!uxrVar.getOptions().an()) {
            return false;
        }
        uzq uzqVar = this.e;
        return uzqVar == null || uzqVar.a(uxrVar);
    }

    public final boolean c(Exchange exchange) {
        uzq uzqVar;
        Integer l = exchange.l();
        return (l == null || (uzqVar = this.e) == null) ? l == null && this.e == null : uzqVar.c() == l.intValue();
    }

    public void d(uxr uxrVar) throws uyx {
        synchronized (this) {
            if (uxrVar == null) {
                throw new NullPointerException("response block must not be null");
            }
            if (!uxrVar.getOptions().ae()) {
                throw new IllegalArgumentException("response block has no block2 option");
            }
            int currentPosition = getCurrentPosition();
            int a2 = uxrVar.getOptions().j().a();
            if (currentPosition != a2) {
                throw new uyx(String.format("response offset %d does not match the expected offset %d!", Integer.valueOf(a2), Integer.valueOf(currentPosition)));
            }
            if (this.c != null) {
                if (uxrVar.getOptions().i() != 1) {
                    throw new uyx("response does not contain a single ETag");
                }
                if (!Arrays.equals(this.c, uxrVar.getOptions().g().get(0))) {
                    throw new uyx("response does not contain expected ETag");
                }
            }
            addBlock(uxrVar.getPayload(), uxrVar.getMessageSize());
            setCurrentNum(getCurrentPosition() / getCurrentSize());
        }
    }

    public uxt a(int i) throws uyx {
        uxt uxtVar;
        synchronized (this) {
            Exchange exchange = getExchange(false);
            boolean z = true;
            if (exchange == null) {
                throw new uyx("Block2 exchange already completed!", true);
            }
            setCurrentSzx(i);
            int currentSize = getCurrentSize();
            int currentPosition = getCurrentPosition();
            if (currentPosition % currentSize != 0) {
                throw new uyx("Block2 buffer position " + currentPosition + " doesn't align with blocksize " + currentSize + "!");
            }
            int i2 = currentPosition / currentSize;
            setCurrentNum(i2);
            uxt p = exchange.p();
            uxtVar = new uxt(p.e());
            if (i2 != 0) {
                z = false;
            }
            prepareOutgoingMessage(p, uxtVar, z);
            uxtVar.getOptions().at();
            uxtVar.getOptions().e(i, false, i2);
        }
        return uxtVar;
    }

    public uxr e(uxh uxhVar) {
        uxr uxrVar;
        synchronized (this) {
            if (uxhVar == null) {
                throw new NullPointerException("block option must not be null.");
            }
            int a2 = uxhVar.a();
            int d = uxhVar.d();
            int c = uxhVar.c();
            setCurrentSzx(d);
            int i = a2 / c;
            setCurrentNum(i);
            uxrVar = new uxr(((uxr) this.firstMessage).a());
            int bufferSize = getBufferSize();
            prepareOutgoingMessage(this.firstMessage, uxrVar, i == 0);
            if (i == 0) {
                if (!uxrVar.getOptions().ap()) {
                    uxrVar.getOptions().f(bufferSize);
                }
            } else {
                uxrVar.getOptions().at();
                uxrVar.setType(null);
            }
            if (bufferSize > 0 && a2 < bufferSize) {
                byte[] block = getBlock(a2, getCurrentPayloadSize());
                r6 = a2 + block.length < bufferSize;
                uxrVar.setPayload(block);
            }
            uxrVar.getOptions().e(d, r6, i);
            if (!r6) {
                setComplete(true);
            }
        }
        return uxrVar;
    }

    public final void b(Exchange exchange) {
        Exchange exchange2 = getExchange(true);
        if (exchange2 != null) {
            if (exchange != exchange2) {
                if (exchange2.y()) {
                    exchange2.c();
                    return;
                } else {
                    exchange2.p().setCanceled(true);
                    return;
                }
            }
            exchange2.c(exchange2.p());
        }
    }

    public final void d(Exchange exchange) {
        if (exchange != getExchange(false)) {
            if (exchange.y()) {
                exchange.af();
            } else {
                exchange.p().setCanceled(true);
            }
        }
    }

    final boolean c() {
        uxr uxrVar;
        if (!complete()) {
            return false;
        }
        synchronized (this) {
            uxrVar = (uxr) this.firstMessage;
        }
        if (uxrVar == null) {
            return false;
        }
        uxrVar.onTransferComplete();
        return true;
    }

    @Override // org.eclipse.californium.core.network.stack.BlockwiseStatus
    public String toString() {
        String blockwiseStatus;
        synchronized (this) {
            blockwiseStatus = super.toString();
            if (this.e != null) {
                StringBuilder sb = new StringBuilder(blockwiseStatus);
                sb.setLength(blockwiseStatus.length() - 1);
                sb.append(", observe=");
                sb.append(this.e.c());
                sb.append("]");
                blockwiseStatus = sb.toString();
            }
        }
        return blockwiseStatus;
    }

    public static final void b(uxr uxrVar, uxh uxhVar, int i) {
        boolean z;
        if (uxrVar == null) {
            throw new NullPointerException("response message must not be null");
        }
        if (uxhVar == null) {
            throw new NullPointerException("block option must not be null");
        }
        if (!uxrVar.hasBlock(uxhVar)) {
            throw new IllegalArgumentException("given response does not contain block");
        }
        int payloadSize = uxrVar.getPayloadSize();
        int a2 = uxhVar.a();
        int c = uxhVar.c();
        if (uxhVar.g()) {
            c *= i;
        }
        if (uxrVar.getOptions().ae()) {
            a2 -= uxrVar.getOptions().j().a();
            z = uxrVar.getOptions().j().f();
        } else {
            z = false;
        }
        int min = Math.min(c + a2, payloadSize);
        int i2 = min - a2;
        uxrVar.getOptions().e(uxhVar.d(), z || min < payloadSize, uxhVar.b());
        f17605a.debug("cropping response body [size={}] to block {}", Integer.valueOf(payloadSize), uxhVar);
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            System.arraycopy(uxrVar.getPayload(), a2, bArr, 0, i2);
            uxrVar.setPayload(bArr);
            return;
        }
        uxrVar.setPayload(vbj.c);
    }
}
