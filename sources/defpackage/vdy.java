package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public final class vdy extends vdd {
    private final byte[] d;
    private final List<a> e;

    static class a {
        private int b;
        private int c;
        private int e;

        private a(int i, int i2) {
            this.b = i;
            this.c = i2;
            this.e = i + i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            if (this.e > i) {
                int i2 = this.b;
                if (i < i2) {
                    throw new IllegalArgumentException("adjusted end before offset!");
                }
                this.e = i;
                this.c = i - i2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i) {
            if (this.e < i) {
                this.e = i;
                this.c = i - this.b;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a(int i) {
            int i2 = this.b;
            if (i2 >= i) {
                return 0;
            }
            int i3 = this.e;
            if (i3 <= i) {
                this.c = 0;
                this.b = i;
                this.e = i;
                return 0;
            }
            int i4 = i - i2;
            this.b = i;
            this.c = i3 - i;
            return i4;
        }

        public String toString() {
            return String.format("range[%d:%d)", Integer.valueOf(this.b), Integer.valueOf(this.e));
        }
    }

    public vdy(vdc vdcVar) {
        super(vdcVar.getMessageType());
        this.e = new ArrayList();
        setMessageSeq(vdcVar.getMessageSeq());
        this.d = new byte[vdcVar.getMessageLength()];
        b(0, 0, new a(vdcVar.getFragmentOffset(), vdcVar.getFragmentLength()), vdcVar);
    }

    public boolean a() {
        a aVar = this.e.get(0);
        return aVar.b == 0 && getMessageLength() <= aVar.e;
    }

    public void e(vdc vdcVar) {
        if (getMessageType() != vdcVar.getMessageType()) {
            throw new IllegalArgumentException("Fragment message type " + vdcVar.getMessageType() + " differs from " + getMessageType() + "!");
        }
        if (getMessageSeq() != vdcVar.getMessageSeq()) {
            throw new IllegalArgumentException("Fragment message sequence number " + vdcVar.getMessageSeq() + " differs from " + getMessageSeq() + "!");
        }
        if (getMessageLength() != vdcVar.getMessageLength()) {
            throw new IllegalArgumentException("Fragment message length " + vdcVar.getMessageLength() + " differs from " + getMessageLength() + "!");
        }
        if (a()) {
            return;
        }
        a aVar = new a(vdcVar.getFragmentOffset(), vdcVar.getFragmentLength());
        if (getMessageLength() >= aVar.e) {
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= this.e.size()) {
                    break;
                }
                a aVar2 = this.e.get(i);
                if (aVar.b < aVar2.b) {
                    if (aVar2.b < aVar.e && aVar.e <= aVar2.e) {
                        aVar.b(aVar2.b);
                    }
                } else {
                    if (aVar.e <= aVar2.e) {
                        return;
                    }
                    if (aVar.b == aVar2.b) {
                        i++;
                        break;
                    } else {
                        i2 = aVar2.e;
                        i++;
                    }
                }
            }
            int a2 = aVar.a(i2);
            if (aVar.c == 0) {
                return;
            }
            b(i, a2, aVar, vdcVar);
            a aVar3 = this.e.get(0);
            int i3 = 1;
            while (i3 < this.e.size()) {
                a aVar4 = this.e.get(i3);
                if (aVar4.b <= aVar3.e) {
                    aVar3.d(aVar4.e);
                    this.e.remove(i3);
                    i3--;
                } else {
                    aVar3 = aVar4;
                }
                i3++;
            }
            return;
        }
        throw new IllegalArgumentException("Fragment message " + aVar.e + " bytes exceeds message " + getMessageLength() + " bytes!");
    }

    private void b(int i, int i2, a aVar, vdc vdcVar) {
        this.e.add(i, aVar);
        System.arraycopy(vdcVar.fragmentToByteArray(), i2, this.d, aVar.b, aVar.c);
    }

    @Override // defpackage.vdd, org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.d.length;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        String b = vcb.b(i);
        String b2 = vcb.b(i + 1);
        sb.append(b);
        sb.append("Reassembled Fragments: ");
        sb.append(this.e.size());
        sb.append(vcb.a());
        for (a aVar : this.e) {
            sb.append(b2);
            sb.append(aVar);
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    @Override // defpackage.vdd, org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        return this.d;
    }

    @Override // defpackage.vdd, org.eclipse.californium.scandium.dtls.HandshakeMessage
    public String getImplementationTypePrefix() {
        return "Reassembling ";
    }
}
