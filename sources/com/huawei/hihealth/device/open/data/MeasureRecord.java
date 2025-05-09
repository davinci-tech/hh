package com.huawei.hihealth.device.open.data;

import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes.dex */
public interface MeasureRecord {
    ArrayList<String> getDataTypes();

    int getFieldNum();

    Date getMeasureTime();

    String getUnit(int i);

    String getUnit(String str);

    Number getValue(int i);

    Number getValue(String str);

    ArrayList<Number> getValueList(int i);

    ArrayList<Number> getValueList(String str);

    void setMeasureTime(Date date);

    boolean setValue(int i, Number number);

    boolean setValue(String str, Number number);

    boolean setValueList(int i, ArrayList<Number> arrayList);

    boolean setValueList(String str, ArrayList<Number> arrayList);
}
