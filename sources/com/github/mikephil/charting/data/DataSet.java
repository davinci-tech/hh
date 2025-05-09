package com.github.mikephil.charting.data;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {
    protected List<T> mEntries;
    protected float mXMax;
    protected float mXMin;
    protected float mYMax;
    protected float mYMin;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public abstract DataSet<T> copy();

    public DataSet(List<T> list, String str) {
        super(str);
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mEntries = list;
        if (list == null) {
            this.mEntries = new ArrayList();
        }
        calcMinMax();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMax() {
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<T> it = this.mEntries.iterator();
        while (it.hasNext()) {
            calcMinMax(it.next());
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMaxY(float f, float f2) {
        int entryIndex;
        int entryIndex2;
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty() || (entryIndex2 = getEntryIndex(f2, Float.NaN, Rounding.UP)) < (entryIndex = getEntryIndex(f, Float.NaN, Rounding.DOWN))) {
            return;
        }
        for (entryIndex = getEntryIndex(f, Float.NaN, Rounding.DOWN); entryIndex <= entryIndex2; entryIndex++) {
            calcMinMaxY(this.mEntries.get(entryIndex));
        }
    }

    protected void calcMinMax(T t) {
        if (t == null) {
            return;
        }
        calcMinMaxX(t);
        calcMinMaxY(t);
    }

    protected void calcMinMaxX(T t) {
        if (t.getX() < this.mXMin) {
            this.mXMin = t.getX();
        }
        if (t.getX() > this.mXMax) {
            this.mXMax = t.getX();
        }
    }

    protected void calcMinMaxY(T t) {
        if (t.getY() < this.mYMin) {
            this.mYMin = t.getY();
        }
        if (t.getY() > this.mYMax) {
            this.mYMax = t.getY();
        }
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryCount() {
        return this.mEntries.size();
    }

    @Deprecated
    public List<T> getValues() {
        return this.mEntries;
    }

    public List<T> getEntries() {
        return this.mEntries;
    }

    @Deprecated
    public void setValues(List<T> list) {
        setEntries(list);
    }

    public void setEntries(List<T> list) {
        this.mEntries = list;
        notifyDataSetChanged();
    }

    protected void copy(DataSet dataSet) {
        super.copy((BaseDataSet) dataSet);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toSimpleString());
        for (int i = 0; i < this.mEntries.size(); i++) {
            stringBuffer.append(this.mEntries.get(i).toString() + " ");
        }
        return stringBuffer.toString();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder("DataSet, label: ");
        sb.append(getLabel() == null ? "" : getLabel());
        sb.append(", entries: ");
        sb.append(this.mEntries.size());
        sb.append("\n");
        stringBuffer.append(sb.toString());
        return stringBuffer.toString();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMin() {
        return this.mYMin;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getYMax() {
        return this.mYMax;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMin() {
        return this.mXMin;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public float getXMax() {
        return this.mXMax;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void addEntryOrdered(T t) {
        if (t == null) {
            return;
        }
        if (this.mEntries == null) {
            this.mEntries = new ArrayList();
        }
        calcMinMax(t);
        if (this.mEntries.size() > 0) {
            if (this.mEntries.get(r0.size() - 1).getX() > t.getX()) {
                this.mEntries.add(getEntryIndex(t.getX(), t.getY(), Rounding.UP), t);
                return;
            }
        }
        this.mEntries.add(t);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void clear() {
        this.mEntries.clear();
        notifyDataSetChanged();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean addEntry(T t) {
        if (t == null) {
            return false;
        }
        List<T> entries = getEntries();
        if (entries == null) {
            entries = new ArrayList<>();
        }
        calcMinMax(t);
        return entries.add(t);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public boolean removeEntry(T t) {
        List<T> list;
        if (t == null || (list = this.mEntries) == null) {
            return false;
        }
        boolean remove = list.remove(t);
        if (remove) {
            calcMinMax();
        }
        return remove;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(Entry entry) {
        return this.mEntries.indexOf(entry);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f, float f2, Rounding rounding) {
        int entryIndex = getEntryIndex(f, f2, rounding);
        if (entryIndex > -1) {
            return this.mEntries.get(entryIndex);
        }
        return null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForXValue(float f, float f2) {
        return getEntryForXValue(f, f2, Rounding.CLOSEST);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public T getEntryForIndex(int i) {
        if (i >= 0 && i < this.mEntries.size()) {
            return this.mEntries.get(i);
        }
        return null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getEntryIndex(float f, float f2, Rounding rounding) {
        int i;
        T t;
        List<T> list = this.mEntries;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        int size = this.mEntries.size() - 1;
        int i2 = 0;
        while (i2 < size) {
            int i3 = (i2 + size) / 2;
            float x = this.mEntries.get(i3).getX() - f;
            int i4 = i3 + 1;
            float x2 = this.mEntries.get(i4).getX();
            float abs = Math.abs(x);
            float abs2 = Math.abs(x2 - f);
            if (abs2 >= abs) {
                if (abs >= abs2) {
                    double d = x;
                    if (d < 0.0d) {
                        if (d < 0.0d) {
                        }
                    }
                }
                size = i3;
            }
            i2 = i4;
        }
        if (size == -1) {
            return size;
        }
        float x3 = this.mEntries.get(size).getX();
        if (rounding == Rounding.UP) {
            if (x3 < f && size < this.mEntries.size() - 1) {
                size++;
            }
        } else if (rounding == Rounding.DOWN && x3 > f && size > 0) {
            size--;
        }
        if (Float.isNaN(f2)) {
            return size;
        }
        while (size > 0 && this.mEntries.get(size - 1).getX() == x3) {
            size--;
        }
        float y = this.mEntries.get(size).getY();
        loop2: while (true) {
            i = size;
            size = i;
            do {
                size++;
                if (size >= this.mEntries.size()) {
                    break loop2;
                }
                t = this.mEntries.get(size);
                if (t.getX() != x3) {
                    break loop2;
                }
            } while (Math.abs(t.getY() - f2) > Math.abs(y - f2));
            y = f2;
        }
        return i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<T> getEntriesForXValue(float f) {
        ArrayList arrayList = new ArrayList();
        int size = this.mEntries.size() - 1;
        int i = 0;
        while (true) {
            if (i > size) {
                break;
            }
            int i2 = (size + i) / 2;
            T t = this.mEntries.get(i2);
            if (f == t.getX()) {
                while (i2 > 0 && this.mEntries.get(i2 - 1).getX() == f) {
                    i2--;
                }
                int size2 = this.mEntries.size();
                while (i2 < size2) {
                    T t2 = this.mEntries.get(i2);
                    if (t2.getX() != f) {
                        break;
                    }
                    arrayList.add(t2);
                    i2++;
                }
            } else if (f > t.getX()) {
                i = i2 + 1;
            } else {
                size = i2 - 1;
            }
        }
        return arrayList;
    }
}
