package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

/* loaded from: classes2.dex */
public class ObjectPool<T extends Poolable> {
    private static int ids;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    public static abstract class Poolable {
        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        protected abstract Poolable instantiate();
    }

    public int getPoolId() {
        return this.poolId;
    }

    public static ObjectPool create(int i, Poolable poolable) {
        ObjectPool objectPool;
        synchronized (ObjectPool.class) {
            objectPool = new ObjectPool(i, poolable);
            int i2 = ids;
            objectPool.poolId = i2;
            ids = i2 + 1;
        }
        return objectPool;
    }

    private ObjectPool(int i, T t) {
        if (i <= 0) {
            throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
        }
        this.desiredCapacity = i;
        this.objects = new Object[i];
        this.objectsPointer = 0;
        this.modelObject = t;
        this.replenishPercentage = 1.0f;
        refillPool();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
    
        if (r3 < 0.0f) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setReplenishPercentage(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L8
        L6:
            r3 = r0
            goto Le
        L8:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto Le
            goto L6
        Le:
            r2.replenishPercentage = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.utils.ObjectPool.setReplenishPercentage(float):void");
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void refillPool(float f) {
        int i = this.desiredCapacity;
        int i2 = (int) (i * f);
        if (i2 < 1) {
            i = 1;
        } else if (i2 <= i) {
            i = i2;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.objects[i3] = this.modelObject.instantiate();
        }
        this.objectsPointer = i - 1;
    }

    public T get() {
        T t;
        synchronized (this) {
            if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
                refillPool();
            }
            t = (T) this.objects[this.objectsPointer];
            t.currentOwnerId = Poolable.NO_OWNER;
            this.objectsPointer--;
        }
        return t;
    }

    public void recycle(T t) {
        synchronized (this) {
            if (t.currentOwnerId != Poolable.NO_OWNER) {
                if (t.currentOwnerId == this.poolId) {
                    throw new IllegalArgumentException("The object passed is already stored in this pool!");
                }
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
            int i = this.objectsPointer + 1;
            this.objectsPointer = i;
            if (i >= this.objects.length) {
                resizePool();
            }
            t.currentOwnerId = this.poolId;
            this.objects[this.objectsPointer] = t;
        }
    }

    public void recycle(List<T> list) {
        synchronized (this) {
            while (list.size() + this.objectsPointer + 1 > this.desiredCapacity) {
                resizePool();
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                T t = list.get(i);
                if (t.currentOwnerId != Poolable.NO_OWNER) {
                    if (t.currentOwnerId == this.poolId) {
                        throw new IllegalArgumentException("The object passed is already stored in this pool!");
                    }
                    throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
                }
                t.currentOwnerId = this.poolId;
                this.objects[this.objectsPointer + 1 + i] = t;
            }
            this.objectsPointer += size;
        }
    }

    private void resizePool() {
        int i = this.desiredCapacity;
        int i2 = i * 2;
        this.desiredCapacity = i2;
        Object[] objArr = new Object[i2];
        for (int i3 = 0; i3 < i; i3++) {
            objArr[i3] = this.objects[i3];
        }
        this.objects = objArr;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }
}
