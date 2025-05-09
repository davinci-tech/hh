package com.huawei.hihealth.device.indoor;

import com.huawei.hihealth.device.open.data.MeasureRecord;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes.dex */
public class MeasureRecordImpl implements MeasureRecord {
    private Date mMeasureTime;
    private MeasureResultImpl mResultHolder;
    private ArrayList<ArrayList<Number>> mValues = new ArrayList<>();

    public MeasureRecordImpl(MeasureResultImpl measureResultImpl) {
        this.mResultHolder = measureResultImpl;
        if (measureResultImpl != null) {
            for (int i = 0; i < this.mResultHolder.getFieldNum(); i++) {
                this.mValues.add(null);
            }
        }
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public void setMeasureTime(Date date) {
        if (date == null) {
            this.mMeasureTime = null;
        } else {
            this.mMeasureTime = (Date) date.clone();
        }
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public Date getMeasureTime() {
        Date date = this.mMeasureTime;
        if (date == null || !(date.clone() instanceof Date)) {
            return null;
        }
        return (Date) this.mMeasureTime.clone();
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public boolean setValue(int i, Number number) {
        if (i < 0 || i >= this.mValues.size()) {
            return false;
        }
        ArrayList<Number> arrayList = new ArrayList<>();
        arrayList.add(number);
        this.mValues.set(i, arrayList);
        return true;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public boolean setValue(String str, Number number) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl != null) {
            return setValue(measureResultImpl.indexOfDataType(str), number);
        }
        return false;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public boolean setValueList(int i, ArrayList<Number> arrayList) {
        if (i < 0 || i >= this.mValues.size()) {
            return false;
        }
        this.mValues.set(i, arrayList);
        return true;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public boolean setValueList(String str, ArrayList<Number> arrayList) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl != null) {
            return setValueList(measureResultImpl.indexOfDataType(str), arrayList);
        }
        return false;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public Number getValue(int i) {
        if (i < 0 || i >= this.mValues.size()) {
            return null;
        }
        return this.mValues.get(i).get(0);
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public Number getValue(String str) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl != null) {
            return getValue(measureResultImpl.indexOfDataType(str));
        }
        return null;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public ArrayList<Number> getValueList(String str) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl != null) {
            return getValueList(measureResultImpl.indexOfDataType(str));
        }
        return null;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public ArrayList<Number> getValueList(int i) {
        if (i < 0 || i >= this.mValues.size()) {
            return null;
        }
        return this.mValues.get(i);
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public int getFieldNum() {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl == null) {
            return 0;
        }
        return measureResultImpl.getFieldNum();
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public ArrayList<String> getDataTypes() {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl == null) {
            return null;
        }
        return measureResultImpl.getDataTypes();
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public String getUnit(int i) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl == null) {
            return null;
        }
        return measureResultImpl.getUnit(i);
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureRecord
    public String getUnit(String str) {
        MeasureResultImpl measureResultImpl = this.mResultHolder;
        if (measureResultImpl == null) {
            return null;
        }
        return measureResultImpl.getUnit(str);
    }
}
