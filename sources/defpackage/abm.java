package defpackage;

import com.huawei.animation.physical2.SimpleSpringNode;
import com.huawei.animation.physical2.SpringAdapter;
import com.huawei.animation.physical2.SpringNode;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class abm extends SpringAdapter<SpringNode> {
    private int d = 0;
    private List<SpringNode> c = new ArrayList();

    public void e(SpringNode springNode) {
        this.c.add(springNode);
        notifyNodeAdd(springNode);
    }

    @Override // com.huawei.animation.physical2.SpringAdapter
    public SpringNode getNext(SpringNode springNode) {
        if (springNode == null) {
            return null;
        }
        return getNode(springNode.getIndex() + 1);
    }

    @Override // com.huawei.animation.physical2.SpringAdapter
    public SpringNode getPrev(SpringNode springNode) {
        if (springNode == null) {
            return null;
        }
        return getNode(springNode.getIndex() - 1);
    }

    @Override // com.huawei.animation.physical2.SpringAdapter
    public int getSize() {
        return this.c.size();
    }

    @Override // com.huawei.animation.physical2.SpringAdapter
    public SpringNode getControlNode() {
        return getNode(a());
    }

    @Override // com.huawei.animation.physical2.SpringAdapter
    public SpringNode getNode(int i) {
        if (c(i)) {
            return this.c.get(i);
        }
        return null;
    }

    public int a() {
        return this.d;
    }

    public void b(int i) {
        if (c(i)) {
            this.d = i;
        }
    }

    public boolean c(int i) {
        return i < this.c.size() && i >= 0;
    }

    public void e(int i) {
        if (i < 0) {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                int i2 = size + i;
                SpringNode springNode = i2 < 0 ? this.c.get(0) : this.c.get(i2);
                SpringNode springNode2 = this.c.get(size);
                if (!(springNode instanceof SimpleSpringNode) || !(springNode2 instanceof SimpleSpringNode)) {
                    return;
                }
                e(springNode2, springNode);
            }
            return;
        }
        a(i);
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        */
    private void a(int r5) {
        /*
            r4 = this;
            r0 = 0
        L1:
            java.util.List<com.huawei.animation.physical2.SpringNode> r1 = r4.c
            int r1 = r1.size()
            if (r0 >= r1) goto L3e
            int r1 = r0 + r5
            java.util.List<com.huawei.animation.physical2.SpringNode> r2 = r4.c
            int r2 = r2.size()
            if (r1 < r2) goto L20
            java.util.List<com.huawei.animation.physical2.SpringNode> r1 = r4.c
            int r2 = r1.size()
            int r2 = r2 + (-1)
            java.lang.Object r1 = r1.get(r2)
            goto L26
        L20:
            java.util.List<com.huawei.animation.physical2.SpringNode> r2 = r4.c
            java.lang.Object r1 = r2.get(r1)
        L26:
            com.huawei.animation.physical2.SpringNode r1 = (com.huawei.animation.physical2.SpringNode) r1
            java.util.List<com.huawei.animation.physical2.SpringNode> r2 = r4.c
            java.lang.Object r2 = r2.get(r0)
            com.huawei.animation.physical2.SpringNode r2 = (com.huawei.animation.physical2.SpringNode) r2
            boolean r3 = r1 instanceof com.huawei.animation.physical2.SimpleSpringNode
            if (r3 == 0) goto L3e
            boolean r3 = r2 instanceof com.huawei.animation.physical2.SimpleSpringNode
            if (r3 == 0) goto L3e
            r4.e(r2, r1)
            int r0 = r0 + 1
            goto L1
        L3e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.abm.a(int):void");
    }

    private void e(SpringNode springNode, SpringNode springNode2) {
        SimpleSpringNode simpleSpringNode = (SimpleSpringNode) springNode;
        SimpleSpringNode simpleSpringNode2 = (SimpleSpringNode) springNode2;
        if (springNode.getIndex() == a()) {
            simpleSpringNode.resetNode(simpleSpringNode2.getValue(), 0.0f);
        } else {
            simpleSpringNode.resetNode(simpleSpringNode2.getValue(), simpleSpringNode2.getVelocity());
        }
    }
}
