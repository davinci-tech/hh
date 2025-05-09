package com.huawei.animation.physical2;

import android.os.SystemClock;
import android.util.Log;
import defpackage.abm;

/* loaded from: classes8.dex */
public abstract class SimpleSpringNodeEx extends SimpleSpringNode {
    private static final int DEFAULT_VALUE = 1;
    private static final String TAG = "SimpleSpringNodeEx";
    protected int fixMode;
    private float lastTheoryValue;

    public SimpleSpringNodeEx(int i, float f) {
        super(i, f);
        this.fixMode = 0;
        this.lastTheoryValue = 0.0f;
    }

    public SimpleSpringNodeEx(int i) {
        super(i);
        this.fixMode = 0;
        this.lastTheoryValue = 0.0f;
    }

    @Override // com.huawei.animation.physical2.SimpleSpringNode
    protected void notifyNext(float f, float f2) {
        if ((this.adapter instanceof abm) && this == this.adapter.getControlNode()) {
            abm abmVar = (abm) this.adapter;
            int a2 = abmVar.a();
            for (int i = 1; i <= a2; i++) {
                int i2 = a2 + i;
                if (abmVar.c(i2)) {
                    this.adapter.getNode(i2).endToValue(f, f2);
                }
                int i3 = a2 - i;
                if (abmVar.c(i3)) {
                    this.adapter.getNode(i3).endToValue(f, f2);
                }
            }
        }
    }

    @Override // com.huawei.animation.physical2.SimpleSpringNode, com.huawei.animation.physical2.SpringNode
    public boolean isDoFrame() {
        if (!this.isRunning) {
            return true;
        }
        long uptimeMillis = SystemClock.uptimeMillis() - this.startTime;
        float e = this.spring.e(uptimeMillis);
        fixValue(e);
        doDistanceToNeighbor();
        this.velocity = this.spring.d(uptimeMillis);
        if (this.spring.e(this.value, this.velocity) || this.spring.e(e, this.velocity)) {
            this.isRunning = false;
            this.value = this.spring.a();
            this.velocity = 0.0f;
            onUpdateInternal();
            onEnd(this.value);
            Log.w(TAG, "isDoFrame: index:" + getIndex() + " is at equilibrium value:" + this.value);
        } else {
            this.isRunning = true;
            onUpdateInternal();
        }
        return !this.isRunning;
    }

    @Override // com.huawei.animation.physical2.SpringNode
    protected void onRunning() {
        doDistanceToNeighbor();
        onUpdateInternal();
    }

    private void fixValue(float f) {
        if (this.fixMode == 1) {
            simpleFixedValue(f);
        } else {
            this.value = f;
        }
    }

    @Override // com.huawei.animation.physical2.SimpleSpringNode, com.huawei.animation.physical2.SpringNode
    protected void doDistanceToNeighbor() {
        if (this.minimumDistanceDelta == -1 || this.maximumDistanceDelta == -1) {
            Log.w(TAG, "doDistanceToNeighbor: minimumDistanceDelta or maximumDistanceDelta is not configured.");
            return;
        }
        if (this.adapter.getControlNode().getIndex() > getIndex()) {
            SpringNode node = this.adapter.getNode(getIndex() + 1);
            if (!(node instanceof SimpleSpringNode)) {
                return;
            }
            float value = ((SimpleSpringNode) node).getValue();
            this.value = Math.max(Math.min(this.minimumDistanceDelta + value, this.value), value - this.maximumDistanceDelta);
        }
        if (this.adapter.getControlNode().getIndex() < getIndex()) {
            SpringNode node2 = this.adapter.getNode(getIndex() - 1);
            if (node2 instanceof SimpleSpringNode) {
                float value2 = ((SimpleSpringNode) node2).getValue();
                this.value = Math.max(Math.min(this.maximumDistanceDelta + value2, this.value), value2 - this.minimumDistanceDelta);
            }
        }
    }

    private void simpleFixedValue(float f) {
        if (this.fixMode != 1) {
            Log.w(TAG, "fix mode is unmatch.");
            this.value = f;
            return;
        }
        float f2 = f - this.lastTheoryValue;
        this.lastTheoryValue = f;
        if (Math.abs(f2) >= 1.0f) {
            this.value = f;
        } else {
            this.value += Math.signum(f2);
        }
    }

    public int getFixMode() {
        return this.fixMode;
    }

    public void setFixMode(int i) {
        this.fixMode = i;
    }
}
