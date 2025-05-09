package defpackage;

import org.eclipse.californium.scandium.DtlsClusterHealth;

/* loaded from: classes10.dex */
public class vbz extends vcg implements DtlsClusterHealth {
    private final vca b;
    private final vca c;
    private final vca d;
    private final vca e;
    private final vca f;
    private final vca g;
    private final vca h;
    private final vca i;
    private final vca j;
    private final vca m;
    private final vca n;

    public vbz() {
        this("");
    }

    public vbz(String str) {
        super(str);
        this.j = new vca("forwarded", this.f17662a);
        this.i = new vca("process forwarded", this.f17662a);
        this.e = new vca("bad forward", this.f17662a);
        this.f = new vca("drop forward", this.f17662a);
        this.c = new vca("backwarded", this.f17662a);
        this.m = new vca("send backwarded", this.f17662a);
        this.d = new vca("bad backward", this.f17662a);
        this.b = new vca("drop backward", this.f17662a);
        this.n = new vca("sent cluster mgmt", this.f17662a);
        this.h = new vca("recv cluster mgmt", this.f17662a);
        this.g = new vca("dropped internal udp", this.f17662a);
        a();
    }

    private void a() {
        add(this.j);
        add(this.i);
        add(this.e);
        add(this.f);
        add(this.c);
        add(this.m);
        add(this.d);
        add(this.b);
        add(this.n);
        add(this.h);
        add(this.g);
    }

    @Override // defpackage.vcg
    protected boolean b() {
        return super.b() || this.j.d() || this.f.d() || this.e.d();
    }

    @Override // defpackage.vcg
    protected void c(String str, StringBuilder sb) {
        String a2 = vcb.a();
        sb.append(a2);
        sb.append(str);
        sb.append(this.j);
        sb.append(a2);
        sb.append(str);
        sb.append(this.i);
        sb.append(a2);
        sb.append(str);
        sb.append(this.e);
        sb.append(a2);
        sb.append(str);
        sb.append(this.f);
        sb.append(a2);
        sb.append(str);
        sb.append(this.c);
        sb.append(a2);
        sb.append(str);
        sb.append(this.m);
        sb.append(a2);
        sb.append(str);
        sb.append(this.d);
        sb.append(a2);
        sb.append(str);
        sb.append(this.b);
        sb.append(a2);
        sb.append(str);
        sb.append(this.n);
        sb.append(a2);
        sb.append(str);
        sb.append(this.h);
        if (this.g.c()) {
            sb.append(a2);
            sb.append(str);
            sb.append(this.g);
        }
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void forwardMessage() {
        this.j.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void backwardMessage() {
        this.c.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void processForwardedMessage() {
        this.i.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void sendBackwardedMessage() {
        this.m.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void dropForwardMessage() {
        this.f.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void dropBackwardMessage() {
        this.b.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void badForwardMessage() {
        this.e.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void badBackwardMessage() {
        this.d.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void sendingClusterManagementMessage() {
        this.n.a();
    }

    @Override // org.eclipse.californium.scandium.DtlsClusterHealth
    public void receivingClusterManagementMessage() {
        this.h.a();
    }
}
