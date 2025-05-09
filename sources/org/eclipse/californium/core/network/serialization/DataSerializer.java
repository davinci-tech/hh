package org.eclipse.californium.core.network.serialization;

import defpackage.uxn;
import defpackage.uxr;
import defpackage.uxs;
import defpackage.uxt;
import defpackage.uxv;
import defpackage.uyp;
import defpackage.vaf;
import defpackage.vbo;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.elements.MessageCallback;

/* loaded from: classes7.dex */
public abstract class DataSerializer {
    protected void assertValidOptions(uxv uxvVar) {
    }

    protected abstract void serializeHeader(vbo vboVar, uyp uypVar);

    public final byte[] getByteArray(Message message) {
        if (message == null) {
            throw new NullPointerException("message must not be null!");
        }
        assertValidOptions(message.getOptions());
        message.assertPayloadMatchsBlocksize();
        if (message.getRawCode() == 0) {
            if (message.getType() == CoAP.Type.NON) {
                throw new IllegalArgumentException("NON message must not use code 0 (empty message)!");
            }
            if (!message.getToken().d()) {
                throw new IllegalArgumentException("Empty messages must not use a token!");
            }
            if (message.getPayloadSize() > 0) {
                throw new IllegalArgumentException("Empty messages must not contain payload!");
            }
            vbo vboVar = new vbo(4);
            serializeEmpytMessage(vboVar, message);
            return vboVar.c();
        }
        vbo vboVar2 = new vbo();
        serializeMessage(vboVar2, message);
        return vboVar2.c();
    }

    public final vaf serializeRequest(uxt uxtVar) {
        return serializeRequest(uxtVar, null);
    }

    public final vaf serializeRequest(uxt uxtVar, MessageCallback messageCallback) {
        if (uxtVar == null) {
            throw new NullPointerException("request must not be null!");
        }
        if (uxtVar.getBytes() == null) {
            uxtVar.setBytes(getByteArray(uxtVar));
        }
        return vaf.d(uxtVar.getBytes(), uxtVar.getEffectiveDestinationContext(), messageCallback, uxtVar.h());
    }

    public final vaf serializeResponse(uxr uxrVar) {
        return serializeResponse(uxrVar, null);
    }

    public final vaf serializeResponse(uxr uxrVar, MessageCallback messageCallback) {
        if (uxrVar == null) {
            throw new NullPointerException("response must not be null!");
        }
        if (uxrVar.getBytes() == null) {
            uxrVar.setBytes(getByteArray(uxrVar));
        }
        return vaf.d(uxrVar.getBytes(), uxrVar.getEffectiveDestinationContext(), messageCallback, false);
    }

    public final vaf serializeEmptyMessage(uxn uxnVar) {
        return serializeEmptyMessage(uxnVar, null);
    }

    public final vaf serializeEmptyMessage(uxn uxnVar, MessageCallback messageCallback) {
        if (uxnVar == null) {
            throw new NullPointerException("empty-message must not be null!");
        }
        if (uxnVar.getBytes() == null) {
            uxnVar.setBytes(getByteArray(uxnVar));
        }
        return vaf.d(uxnVar.getBytes(), uxnVar.getEffectiveDestinationContext(), messageCallback, false);
    }

    protected void serializeEmpytMessage(vbo vboVar, Message message) {
        serializeHeader(vboVar, new uyp(1, message.getType(), message.getToken(), 0, message.getMID(), 0));
        vboVar.i();
    }

    protected void serializeMessage(vbo vboVar, Message message) {
        vbo vboVar2 = new vbo();
        serializeOptionsAndPayload(vboVar2, message.getOptions(), message.getPayload());
        vboVar2.i();
        serializeHeader(vboVar, new uyp(1, message.getType(), message.getToken(), message.getRawCode(), message.getMID(), vboVar2.d()));
        vboVar.i();
        vboVar.e(vboVar2);
    }

    public static void serializeOptionsAndPayload(vbo vboVar, uxv uxvVar, byte[] bArr) {
        if (vboVar == null) {
            throw new NullPointerException("writer must not be null!");
        }
        if (uxvVar == null) {
            throw new NullPointerException("option-set must not be null!");
        }
        int i = 0;
        for (uxs uxsVar : uxvVar.d()) {
            byte[] b = uxsVar.b();
            int e = uxsVar.e();
            int i2 = e - i;
            int optionNibble = getOptionNibble(i2);
            vboVar.b(optionNibble, 4);
            int length = b.length;
            int optionNibble2 = getOptionNibble(length);
            vboVar.b(optionNibble2, 4);
            if (optionNibble == 13) {
                vboVar.b(i2 - 13, 8);
            } else if (optionNibble == 14) {
                vboVar.b(i2 - 269, 16);
            }
            if (optionNibble2 == 13) {
                vboVar.b(length - 13, 8);
            } else if (optionNibble2 == 14) {
                vboVar.b(length - 269, 16);
            }
            vboVar.d(b);
            i = e;
        }
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        vboVar.d((byte) -1);
        vboVar.d(bArr);
    }

    private static int getOptionNibble(int i) {
        if (i <= 12) {
            return i;
        }
        if (i <= 268) {
            return 13;
        }
        if (i <= 65804) {
            return 14;
        }
        throw new IllegalArgumentException("Unsupported option delta " + i);
    }
}
