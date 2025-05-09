package com.huawei.animation.physical2;

import android.util.Log;
import android.view.Choreographer;
import com.huawei.animation.physical2.SpringAdapter;
import defpackage.abm;
import defpackage.abq;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes8.dex */
public class SimpleSpringChain implements SpringAdapter.AdapterObserve, Choreographer.FrameCallback {
    private SpringAdapter g;
    protected List<Listener> e = new CopyOnWriteArrayList();
    private float b = 228.0f;
    private float c = 30.0f;
    private ParamTransfer<Float> j = new abq(1.0f);
    private ParamTransfer<Float> d = new abq();

    /* renamed from: a, reason: collision with root package name */
    private float f1849a = 1.0f;
    private int f = -1;
    private int i = -1;
    private boolean h = false;

    public interface Listener {
        void onSpringChainEnd();

        void onSpringChainStart();

        void onSpringChainUpdate();
    }

    public static abstract class ListenerAdapter implements Listener {
        @Override // com.huawei.animation.physical2.SimpleSpringChain.Listener
        public void onSpringChainEnd() {
        }

        @Override // com.huawei.animation.physical2.SimpleSpringChain.Listener
        public void onSpringChainStart() {
        }

        @Override // com.huawei.animation.physical2.SimpleSpringChain.Listener
        public void onSpringChainUpdate() {
        }
    }

    public SimpleSpringChain(SpringAdapter springAdapter) {
        if (springAdapter == null) {
            throw new IllegalArgumentException("springAdapter can not be null");
        }
        this.g = springAdapter;
        springAdapter.setObserve(this);
        g();
    }

    @Override // com.huawei.animation.physical2.SpringAdapter.AdapterObserve
    public void onControlNodeChange() {
        g();
    }

    @Override // com.huawei.animation.physical2.SpringAdapter.AdapterObserve
    public void onNodeAdd(SpringNode springNode) {
        if (springNode == null) {
            return;
        }
        e(springNode);
    }

    @Override // com.huawei.animation.physical2.SpringAdapter.AdapterObserve
    public void onNodesDelete(SpringNode springNode, int i) {
        if (springNode == null) {
            return;
        }
        SpringNode next = this.g.getNext(springNode);
        while (next != null) {
            next.setIndex(next.getIndex() - i);
            e(next);
            next = this.g.getNext(next);
        }
    }

    private void g() {
        if (this.g.getControlNode() instanceof SimpleSpringNodeEx) {
            SpringAdapter springAdapter = this.g;
            if (springAdapter instanceof abm) {
                ((abm) springAdapter).b(springAdapter.getSize() / 2);
            }
        }
        for (int i = 0; i < this.g.getSize(); i++) {
            SpringNode node = this.g.getNode(i);
            if (node != null) {
                e(node);
            }
        }
    }

    private void e(SpringNode springNode) {
        int i;
        int index = springNode.getIndex();
        SpringNode controlNode = this.g.getControlNode();
        if (controlNode == null) {
            controlNode = springNode;
        }
        int abs = Math.abs(index - controlNode.getIndex());
        springNode.transferParams(this.j.transfer(Float.valueOf(this.b), abs).floatValue(), this.d.transfer(Float.valueOf(this.c), abs).floatValue());
        springNode.setFrameDelta(this.f1849a);
        int i2 = this.f;
        if (i2 != -1 && (i = this.i) != -1) {
            springNode.setDistanceDelta(i2, i);
        }
        if (springNode.getAdapter() == null) {
            springNode.setAdapter(this.g);
        }
    }

    public SimpleSpringChain b(float f) {
        SpringNode controlNode = this.g.getControlNode();
        if (controlNode != null) {
            controlNode.setValue(f);
        }
        f();
        return this;
    }

    public SimpleSpringChain e() {
        for (int i = 0; i < this.g.getSize(); i++) {
            this.g.getNode(i).cancel();
        }
        this.h = false;
        return this;
    }

    public SimpleSpringChain a() {
        g();
        return this;
    }

    public SpringNode b() {
        return this.g.getControlNode();
    }

    public SimpleSpringChain d(float f) {
        this.b = f;
        return this;
    }

    public SimpleSpringChain a(float f) {
        this.c = f;
        return this;
    }

    public SimpleSpringChain b(ParamTransfer<Float> paramTransfer) {
        this.j = paramTransfer;
        return this;
    }

    public SimpleSpringChain e(ParamTransfer<Float> paramTransfer) {
        this.d = paramTransfer;
        return this;
    }

    public SimpleSpringChain e(float f) {
        this.f1849a = f;
        return this;
    }

    public SimpleSpringChain b(int i, int i2) {
        if (i < 0 || i2 < 0 || i2 < i) {
            Log.w("SimpleSpringChain", "setDistanceDelta: distance delta should be greater than zero.");
            return this;
        }
        this.f = i;
        this.i = i2;
        return this;
    }

    public boolean c() {
        return this.h;
    }

    private void f() {
        if (this.h) {
            return;
        }
        Choreographer.getInstance().postFrameCallback(this);
        this.h = true;
        for (Listener listener : this.e) {
            if (listener != null) {
                listener.onSpringChainStart();
            }
        }
    }

    private void d() {
        this.h = false;
        Choreographer.getInstance().removeFrameCallback(this);
        for (Listener listener : this.e) {
            if (listener != null) {
                listener.onSpringChainEnd();
            }
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        boolean z;
        if (this.h) {
            SpringNode controlNode = this.g.getControlNode();
            boolean z2 = true;
            if ((controlNode instanceof SimpleSpringNodeEx) && (this.g instanceof abm)) {
                z = controlNode.isDoFrame() & true;
                abm abmVar = (abm) this.g;
                int a2 = abmVar.a();
                for (int i = 1; i <= a2; i++) {
                    int i2 = a2 + i;
                    if (abmVar.c(i2)) {
                        z &= this.g.getNode(i2).isDoFrame();
                    }
                    int i3 = a2 - i;
                    if (abmVar.c(i3)) {
                        z &= this.g.getNode(i3).isDoFrame();
                    }
                }
            } else {
                while (controlNode != null) {
                    z2 &= controlNode.isDoFrame();
                    controlNode = this.g.getNext(controlNode);
                }
                z = z2;
            }
            if (z) {
                d();
            } else {
                Choreographer.getInstance().postFrameCallback(this);
            }
        }
    }

    public void d(int i) {
        SpringAdapter springAdapter = this.g;
        if (springAdapter instanceof abm) {
            ((abm) springAdapter).e(i);
        }
    }
}
