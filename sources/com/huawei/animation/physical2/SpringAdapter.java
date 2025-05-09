package com.huawei.animation.physical2;

import com.huawei.animation.physical2.SpringNode;

/* loaded from: classes8.dex */
public abstract class SpringAdapter<VH extends SpringNode> {
    private static final int DEFAULT_DELTA = 1;
    private AdapterObserve observe;

    interface AdapterObserve {
        void onControlNodeChange();

        void onNodeAdd(SpringNode springNode);

        void onNodesDelete(SpringNode springNode, int i);
    }

    public abstract VH getControlNode();

    public abstract VH getNext(VH vh);

    public abstract VH getNode(int i);

    public abstract VH getPrev(VH vh);

    public abstract int getSize();

    public void notifyControlIndexChange() {
        AdapterObserve adapterObserve = this.observe;
        if (adapterObserve != null) {
            adapterObserve.onControlNodeChange();
        }
    }

    public void notifyNodeAdd(SpringNode springNode) {
        AdapterObserve adapterObserve = this.observe;
        if (adapterObserve != null) {
            adapterObserve.onNodeAdd(springNode);
        }
    }

    private void notifyNodesDelete(SpringNode springNode, int i) {
        AdapterObserve adapterObserve = this.observe;
        if (adapterObserve != null) {
            adapterObserve.onNodesDelete(springNode, i);
        }
    }

    public void notifyNodeDelete(SpringNode springNode) {
        notifyNodesDelete(springNode, 1);
    }

    void setObserve(AdapterObserve adapterObserve) {
        this.observe = adapterObserve;
    }
}
