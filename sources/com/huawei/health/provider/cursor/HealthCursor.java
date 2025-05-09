package com.huawei.health.provider.cursor;

import android.database.AbstractCursor;
import android.database.CursorWindow;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class HealthCursor extends AbstractCursor {
    private static final String TAG = "Step_HealthCursor";
    private ArrayList<String> mColumnNameList = new ArrayList<>(10);
    private ArrayList<ArrayList<String>> mAllDataList = new ArrayList<>(10);
    private ArrayList<String> mOneLineData = null;

    protected abstract void addColumnName();

    protected abstract void addData();

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean isNull(int i) {
        return false;
    }

    public void addColumnName(String str) {
        this.mColumnNameList.add(str);
    }

    public void addRowData(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mAllDataList.add(arrayList);
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public void fillWindow(int i, CursorWindow cursorWindow) {
        LogUtil.a(TAG, "fillWindow() fillWindow position =", this.mOneLineData, "|this.mAllDataList.size() = ", Integer.valueOf(this.mAllDataList.size()));
        if (koq.b(this.mAllDataList, i)) {
            this.mOneLineData = null;
        } else {
            this.mOneLineData = this.mAllDataList.get(i);
            super.fillWindow(i, cursorWindow);
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.mAllDataList.size();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return (String[]) this.mColumnNameList.toArray(new String[this.mColumnNameList.size()]);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String getString(int i) {
        ArrayList<String> arrayList = this.mOneLineData;
        if (arrayList != null && i >= 0 && i < arrayList.size()) {
            return this.mOneLineData.get(i);
        }
        LogUtil.a(TAG, "getString() mOneLineData.size=", this.mOneLineData, "column ", Integer.valueOf(i));
        return null;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public short getShort(int i) {
        String string = getString(i);
        if (string == null) {
            return (short) 0;
        }
        try {
            return Short.valueOf(string).shortValue();
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "NumberFormatException ", e.getMessage());
            return (short) 0;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getInt(int i) {
        String string = getString(i);
        if (string == null) {
            return 0;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "NumberFormatException ", e.getMessage());
            return 0;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public long getLong(int i) {
        String string = getString(i);
        if (string == null) {
            return 0L;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "NumberFormatException ", e.getMessage());
            return 0L;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public float getFloat(int i) {
        String string = getString(i);
        if (string == null) {
            return 0.0f;
        }
        try {
            return Float.valueOf(string).floatValue();
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "NumberFormatException ", e.getMessage());
            return 0.0f;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public double getDouble(int i) {
        String string = getString(i);
        if (string == null) {
            return 0.0d;
        }
        try {
            return Double.valueOf(string).doubleValue();
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "NumberFormatException ", e.getMessage());
            return 0.0d;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isClosed()) {
            return;
        }
        if (this.mOneLineData != null) {
            this.mOneLineData = null;
        }
        Iterator<ArrayList<String>> it = this.mAllDataList.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.mAllDataList.clear();
        this.mColumnNameList.clear();
        super.close();
    }

    public void initData() {
        addColumnName();
        addData();
    }
}
