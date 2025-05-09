package org.eclipse.californium.core.network.serialization;

import com.google.flatbuffers.reflection.BaseType;
import defpackage.uxk;
import defpackage.uxn;
import defpackage.uxo;
import defpackage.uxq;
import defpackage.uxr;
import defpackage.uxs;
import defpackage.uxt;
import defpackage.uxv;
import defpackage.uyp;
import defpackage.vaf;
import defpackage.vbj;
import defpackage.vbn;
import java.util.Arrays;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.coap.OptionNumberRegistry;

/* loaded from: classes7.dex */
public abstract class DataParser {
    private final int[] criticalCustomOptions;

    protected void assertValidOptions(uxv uxvVar) {
    }

    protected abstract uyp parseHeader(vbn vbnVar);

    public DataParser() {
        this.criticalCustomOptions = null;
    }

    public DataParser(int[] iArr) {
        iArr = iArr == null ? OptionNumberRegistry.e() : iArr;
        if (iArr != null) {
            int[] iArr2 = (int[]) iArr.clone();
            this.criticalCustomOptions = iArr2;
            Arrays.sort(iArr2);
            return;
        }
        this.criticalCustomOptions = null;
    }

    public final Message parseMessage(vaf vafVar) {
        if (vafVar == null) {
            throw new NullPointerException("raw-data must not be null!");
        }
        if (vafVar.b() == null) {
            throw new NullPointerException("raw-data connector's address must not be null!");
        }
        Message parseMessage = parseMessage(vafVar.e());
        parseMessage.setSourceContext(vafVar.c());
        if (parseMessage instanceof uxt) {
            ((uxt) parseMessage).b(vafVar.b(), vafVar.i());
        } else {
            parseMessage.setLocalAddress(vafVar.b());
        }
        parseMessage.setNanoTimestamp(vafVar.d());
        return parseMessage;
    }

    public final Message parseMessage(byte[] bArr) {
        String message;
        Message parseMessage;
        vbn vbnVar = new vbn(bArr);
        uyp parseHeader = parseHeader(vbnVar);
        try {
            if (CoAP.a(parseHeader.d())) {
                parseMessage = parseMessage(vbnVar, parseHeader, new uxt(CoAP.Code.valueOf(parseHeader.d())));
            } else if (CoAP.i(parseHeader.d())) {
                parseMessage = parseMessage(vbnVar, parseHeader, new uxr(CoAP.ResponseCode.valueOf(parseHeader.d())));
            } else {
                parseMessage = CoAP.b(parseHeader.d()) ? parseMessage(vbnVar, parseHeader, new uxn(parseHeader.c())) : null;
            }
        } catch (uxk e) {
            throw e;
        } catch (uxq e2) {
            message = e2.getMessage();
        }
        if (parseMessage != null) {
            parseMessage.setBytes(bArr);
            return parseMessage;
        }
        message = "illegal message code";
        throw new uxk(message, parseHeader.e(), parseHeader.a(), parseHeader.d(), CoAP.Type.CON == parseHeader.c());
    }

    protected Message parseMessage(vbn vbnVar, uyp uypVar, Message message) {
        message.setMID(uypVar.a());
        message.setType(uypVar.c());
        message.setToken(uypVar.e());
        parseOptionsAndPayload(vbnVar, message);
        return message;
    }

    protected boolean isCiriticalCustomOption(int i) {
        return Arrays.binarySearch(this.criticalCustomOptions, i) >= 0;
    }

    public void parseOptionsAndPayload(vbn vbnVar, Message message) {
        if (vbnVar == null) {
            throw new NullPointerException("reader must not be null!");
        }
        if (message == null) {
            throw new NullPointerException("message must not be null!");
        }
        uxv options = message.getOptions();
        int i = 0;
        byte b = 0;
        while (vbnVar.e() && (b = vbnVar.c()) != -1) {
            try {
                i += determineValueFromNibble(vbnVar, (b & 240) >> 4);
                int determineValueFromNibble = determineValueFromNibble(vbnVar, b & BaseType.Obj);
                if (vbnVar.g(determineValueFromNibble)) {
                    uxs createOption = createOption(i, vbnVar.a(determineValueFromNibble));
                    if (createOption != null) {
                        options.d(createOption);
                    }
                } else {
                    throw new IllegalArgumentException(String.format("Message contains option of length %d with only fewer bytes left in the message", Integer.valueOf(determineValueFromNibble)));
                }
            } catch (IllegalArgumentException e) {
                throw new uxk(e.getMessage(), message.getToken(), message.getMID(), message.getRawCode(), message.isConfirmable());
            } catch (uxo e2) {
                throw new uxk(e2.getMessage(), message.getToken(), message.getMID(), message.getRawCode(), message.isConfirmable(), e2.e());
            }
        }
        try {
            assertValidOptions(message.getOptions());
            if (b == -1) {
                if (!vbnVar.e()) {
                    throw new uxk("Found payload marker (0xFF) but message contains no payload", message.getToken(), message.getMID(), message.getRawCode(), message.isConfirmable());
                }
                if (!message.isIntendedPayload()) {
                    message.setUnintendedPayload();
                }
                message.setPayload(vbnVar.i());
                message.assertPayloadMatchsBlocksize();
                return;
            }
            message.setPayload(vbj.c);
        } catch (IllegalArgumentException e3) {
            throw new uxk(e3.getMessage(), message.getToken(), message.getMID(), message.getRawCode(), message.isConfirmable(), CoAP.ResponseCode.BAD_REQUEST);
        }
    }

    public uxs createOption(int i, byte[] bArr) {
        if (this.criticalCustomOptions != null && OptionNumberRegistry.b(i) && OptionNumberRegistry.c(i) && !isCiriticalCustomOption(i)) {
            throw new IllegalArgumentException("Unknown critical option " + i);
        }
        uxs uxsVar = new uxs(i);
        uxsVar.d(bArr);
        return uxsVar;
    }

    private static int determineValueFromNibble(vbn vbnVar, int i) {
        if (i <= 12) {
            return i;
        }
        if (i == 13) {
            return vbnVar.c(8) + 13;
        }
        if (i == 14) {
            return vbnVar.c(16) + 269;
        }
        throw new IllegalArgumentException("Message contains illegal option delta/length: " + i);
    }
}
