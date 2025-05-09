package com.huawei.hms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;

/* loaded from: classes9.dex */
public class DataBufferRef {

    /* renamed from: a, reason: collision with root package name */
    private int f4441a;
    protected final DataHolder mDataHolder;
    protected int mDataRow;

    public DataBufferRef(DataHolder dataHolder, int i) {
        Preconditions.checkNotNull(dataHolder, "dataHolder cannot be null");
        this.mDataHolder = dataHolder;
        getWindowIndex(i);
    }

    protected void copyToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.copyToBuffer(str, this.mDataRow, this.f4441a, charArrayBuffer);
    }

    public boolean equals(Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            if (dataBufferRef.mDataRow == this.mDataRow && dataBufferRef.f4441a == this.f4441a && dataBufferRef.mDataHolder == this.mDataHolder) {
                return true;
            }
        }
        return false;
    }

    protected boolean getBoolean(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_BOOLEAN);
        if (value != null) {
            return ((Boolean) value).booleanValue();
        }
        return false;
    }

    protected byte[] getByteArray(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_BYTE_ARRAY);
        if (value != null) {
            return (byte[]) value;
        }
        return null;
    }

    protected int getDataRow() {
        return this.mDataRow;
    }

    protected double getDouble(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_DOUBLE);
        if (value != null) {
            return ((Double) value).doubleValue();
        }
        return -1.0d;
    }

    protected float getFloat(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_FLOAT);
        if (value != null) {
            return ((Float) value).floatValue();
        }
        return -1.0f;
    }

    protected int getInteger(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_INT);
        if (value != null) {
            return ((Integer) value).intValue();
        }
        return -1;
    }

    protected long getLong(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_LONG);
        if (value != null) {
            return ((Long) value).longValue();
        }
        return -1L;
    }

    protected String getString(String str) {
        Object value = this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_STRING);
        return value != null ? (String) value : "";
    }

    protected final void getWindowIndex(int i) {
        Preconditions.checkArgument(i >= 0 && i < this.mDataHolder.getCount(), "rowNum is out of index");
        this.mDataRow = i;
        this.f4441a = this.mDataHolder.getWindowIndex(i);
    }

    public boolean hasColumn(String str) {
        return this.mDataHolder.hasColumn(str);
    }

    protected boolean hasNull(String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.f4441a);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDataRow), Integer.valueOf(this.f4441a), this.mDataHolder);
    }

    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    protected Uri parseUri(String str) {
        String str2 = (String) this.mDataHolder.getValue(str, this.mDataRow, this.f4441a, DataHolder.TYPE_STRING);
        if (str2 == null) {
            return null;
        }
        return Uri.parse(str2);
    }
}
