package com.google.android.gms.common.data;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zame;
    private ArrayList<Integer> zamf;

    public EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zame = false;
    }

    protected String getChildDataMarkerColumn() {
        return null;
    }

    protected abstract T getEntry(int i, int i2);

    protected abstract String getPrimaryDataMarkerColumn();

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0062, code lost:
    
        if (r5.mDataHolder.getString(r3, r6, r2) == null) goto L17;
     */
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final T get(int r6) {
        /*
            r5 = this;
            r5.zacb()
            int r0 = r5.zah(r6)
            if (r6 < 0) goto L64
            java.util.ArrayList<java.lang.Integer> r1 = r5.zamf
            int r1 = r1.size()
            if (r6 != r1) goto L12
            goto L64
        L12:
            java.util.ArrayList<java.lang.Integer> r1 = r5.zamf
            int r1 = r1.size()
            r2 = 1
            int r1 = r1 - r2
            if (r6 != r1) goto L2f
            com.google.android.gms.common.data.DataHolder r1 = r5.mDataHolder
            int r1 = r1.getCount()
            java.util.ArrayList<java.lang.Integer> r3 = r5.zamf
            java.lang.Object r3 = r3.get(r6)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L49
        L2f:
            java.util.ArrayList<java.lang.Integer> r1 = r5.zamf
            int r3 = r6 + 1
            java.lang.Object r1 = r1.get(r3)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.util.ArrayList<java.lang.Integer> r3 = r5.zamf
            java.lang.Object r3 = r3.get(r6)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
        L49:
            int r1 = r1 - r3
            if (r1 != r2) goto L65
            int r6 = r5.zah(r6)
            com.google.android.gms.common.data.DataHolder r2 = r5.mDataHolder
            int r2 = r2.getWindowIndex(r6)
            java.lang.String r3 = r5.getChildDataMarkerColumn()
            if (r3 == 0) goto L65
            com.google.android.gms.common.data.DataHolder r4 = r5.mDataHolder
            java.lang.String r6 = r4.getString(r3, r6, r2)
            if (r6 != 0) goto L65
        L64:
            r1 = 0
        L65:
            java.lang.Object r6 = r5.getEntry(r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.EntityBuffer.get(int):java.lang.Object");
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zacb();
        return this.zamf.size();
    }

    private final void zacb() {
        synchronized (this) {
            if (!this.zame) {
                int count = this.mDataHolder.getCount();
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zamf = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i = 1; i < count; i++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 == null) {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!string2.equals(string)) {
                            this.zamf.add(Integer.valueOf(i));
                            string = string2;
                        }
                    }
                }
                this.zame = true;
            }
        }
    }

    private final int zah(int i) {
        if (i < 0 || i >= this.zamf.size()) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is out of bounds for this buffer");
            throw new IllegalArgumentException(sb.toString());
        }
        return this.zamf.get(i).intValue();
    }
}
