package defpackage;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.elements.EndpointContext;

/* loaded from: classes7.dex */
public class uxn extends Message {
    @Override // org.eclipse.californium.core.coap.Message
    public int getRawCode() {
        return 0;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public boolean hasBlock(uxh uxhVar) {
        return false;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public boolean isIntendedPayload() {
        return false;
    }

    public uxn(CoAP.Type type) {
        super(type);
        setProtectFromOffload();
    }

    public String toString() {
        String str;
        String str2;
        if (!hasEmptyToken() || getOptions().d().size() > 0 || getPayloadSize() > 0) {
            String payloadString = getPayloadString();
            if (payloadString == null) {
                str = "no payload";
            } else {
                int length = payloadString.length();
                if (payloadString.indexOf("\n") != -1) {
                    payloadString = payloadString.substring(0, payloadString.indexOf("\n"));
                }
                if (payloadString.length() > 24) {
                    payloadString = payloadString.substring(0, 20);
                }
                str = "\"" + payloadString + "\"";
                if (str.length() != length + 2) {
                    str = str + ".. " + str.length() + " bytes";
                }
            }
            str2 = " NON-EMPTY: Token=" + getTokenString() + ", " + getOptions() + ", " + str;
        } else {
            str2 = "";
        }
        return String.format("%s        MID=%5d%s", getType(), Integer.valueOf(getMID()), str2);
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void assertPayloadMatchsBlocksize() {
        if (getPayloadSize() <= 0) {
            return;
        }
        throw new IllegalStateException("Empty message contains " + getPayloadSize() + " bytes payload!");
    }

    public static uxn d(Message message) {
        return c(message, message.getSourceContext());
    }

    public static uxn c(Message message, EndpointContext endpointContext) {
        uxn uxnVar = new uxn(CoAP.Type.ACK);
        uxnVar.setDestinationContext(endpointContext);
        uxnVar.setMID(message.getMID());
        return uxnVar;
    }

    public static uxn b(Message message) {
        return b(message, message.getSourceContext());
    }

    public static uxn b(Message message, EndpointContext endpointContext) {
        uxn uxnVar = new uxn(CoAP.Type.RST);
        uxnVar.setDestinationContext(endpointContext);
        uxnVar.setMID(message.getMID());
        return uxnVar;
    }
}
