package defpackage;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.network.stack.BlockwiseStatus;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class uyt extends BlockwiseStatus {
    private static final Logger d = vha.a((Class<?>) uyt.class);

    private uyt(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxt uxtVar, int i, int i2) {
        super(uzeVar, removeHandler, exchange, uxtVar, i, i2);
    }

    public static uyt e(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxt uxtVar, int i) {
        uyt uytVar = new uyt(uzeVar, removeHandler, exchange, uxtVar, uxtVar.getPayloadSize(), i);
        try {
            uytVar.addBlock(uxtVar.getPayload(), uxtVar.getMessageSize());
            uytVar.flipBlocksBuffer();
        } catch (uyx e) {
            d.warn("buffer overflow on start", (Throwable) e);
        }
        return uytVar;
    }

    public static uyt e(uze uzeVar, BlockwiseStatus.RemoveHandler removeHandler, Exchange exchange, uxt uxtVar, int i, int i2) {
        if (uxtVar.getOptions().ao()) {
            i = uxtVar.getOptions().s().intValue();
        }
        return new uyt(uzeVar, removeHandler, exchange, uxtVar, i, i2);
    }

    public void b(uxt uxtVar) throws uyx {
        synchronized (this) {
            if (uxtVar == null) {
                throw new NullPointerException("request block must not be null");
            }
            uxh f = uxtVar.getOptions().f();
            if (f == null) {
                throw new IllegalArgumentException("request block has no block1 option");
            }
            int currentPosition = getCurrentPosition();
            int a2 = f.a();
            if (currentPosition != a2) {
                throw new uyx("request block1 offset " + a2 + " doesn't match the current position " + currentPosition + "!", CoAP.ResponseCode.REQUEST_ENTITY_INCOMPLETE);
            }
            addBlock(uxtVar.getPayload(), uxtVar.getMessageSize());
            if (f.f()) {
                setCurrentSzx(f.d());
                int c = f.c();
                int currentPosition2 = getCurrentPosition();
                if (currentPosition2 % c != 0) {
                    throw new uyx("Block1 buffer position " + currentPosition2 + " doesn't align with blocksize " + c + "!", CoAP.ResponseCode.REQUEST_ENTITY_INCOMPLETE);
                }
                setCurrentNum(currentPosition2 / c);
            }
        }
    }

    public uxt c(int i) throws uyx {
        uxt uxtVar;
        byte[] block;
        synchronized (this) {
            setCurrentSzx(i);
            int currentSize = getCurrentSize();
            int currentPosition = getCurrentPosition();
            if (currentPosition % currentSize != 0) {
                throw new uyx("Block1 buffer position " + currentPosition + " doesn't align with blocksize " + currentSize + "!");
            }
            int bufferSize = getBufferSize();
            int i2 = currentPosition / currentSize;
            setCurrentNum(i2);
            uxtVar = new uxt(((uxt) this.firstMessage).e());
            prepareOutgoingMessage(this.firstMessage, uxtVar, i2 == 0);
            if (i2 == 0) {
                if (!uxtVar.getOptions().ao()) {
                    uxtVar.getOptions().h(bufferSize);
                }
            } else {
                uxtVar.getOptions().av();
                uxtVar.getOptions().b(false);
            }
            if (bufferSize > 0 && currentPosition < bufferSize && (block = getBlock(currentPosition, getCurrentPayloadSize())) != null) {
                r6 = currentPosition + block.length < bufferSize;
                uxtVar.setPayload(block);
            }
            uxtVar.getOptions().b(i, r6, i2);
            setComplete(!r6);
        }
        return uxtVar;
    }

    public boolean d() {
        if (!complete()) {
            return false;
        }
        ((uxt) this.firstMessage).cancel();
        return true;
    }

    public boolean d(uxr uxrVar) {
        return uxrVar.getToken().equals(this.firstMessage.getToken());
    }
}
