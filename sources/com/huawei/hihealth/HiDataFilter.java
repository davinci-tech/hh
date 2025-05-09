package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hihealth.data.type.HiHealthDataType;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class HiDataFilter implements Parcelable {
    public static final int AND = 0;
    public static final Parcelable.Creator<HiDataFilter> CREATOR = new Parcelable.Creator<HiDataFilter>() { // from class: com.huawei.hihealth.HiDataFilter.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buu_, reason: merged with bridge method [inline-methods] */
        public HiDataFilter createFromParcel(Parcel parcel) {
            return new HiDataFilter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiDataFilter[] newArray(int i) {
            return new HiDataFilter[i];
        }
    };
    public static final int OR = 1;
    public static final int RAW_DATA_FILTER = 1;
    public static final int RESULT_DATA_FILTER = 0;
    private List<DataFilterExpression> filterOptions;
    private int filterType;
    private List<Integer> operators;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiDataFilter(List<DataFilterExpression> list, List<Integer> list2) {
        this.filterOptions = new ArrayList(10);
        this.operators = new ArrayList(10);
        this.filterType = 0;
        if (list == null || list2 == null) {
            throw new InvalidParameterException("HiDataFilter param null");
        }
        if (list.size() - list2.size() != 1) {
            throw new RuntimeException("HiDataFilter param size error");
        }
        this.filterOptions = list;
        this.operators = list2;
    }

    public HiDataFilter(DataFilterExpression dataFilterExpression) {
        this.filterOptions = new ArrayList(10);
        this.operators = new ArrayList(10);
        this.filterType = 0;
        if (dataFilterExpression == null) {
            throw new InvalidParameterException("HiDataFilter param error");
        }
        this.filterOptions.add(dataFilterExpression);
    }

    protected HiDataFilter(Parcel parcel) {
        this.filterOptions = new ArrayList(10);
        this.operators = new ArrayList(10);
        this.filterType = 0;
        this.filterOptions = parcel.createTypedArrayList(DataFilterExpression.CREATOR);
        ArrayList arrayList = new ArrayList(10);
        this.operators = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.filterType = parcel.readInt();
    }

    public List<DataFilterExpression> getFilterOptions() {
        return this.filterOptions;
    }

    public void setFilterOptions(List<DataFilterExpression> list) {
        this.filterOptions = list;
    }

    public List<Integer> getOperators() {
        return this.operators;
    }

    public void setOperators(List<Integer> list) {
        this.operators = list;
    }

    public int getFilterType() {
        return this.filterType;
    }

    public void setFilterType(int i) {
        this.filterType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.filterOptions);
        parcel.writeList(this.operators);
        parcel.writeInt(this.filterType);
    }

    public String toString() {
        return "HiDataFilter{filterOptions=" + this.filterOptions + ", operators=" + this.operators + ", filterType=" + this.filterType + '}';
    }

    public static class DataFilterExpression implements Parcelable {
        public static final String EQUAL = "=";
        private String compareSymbols;
        private String constantsKey;
        private int dataType;
        private double value;
        public static final Parcelable.Creator<DataFilterExpression> CREATOR = new Parcelable.Creator<DataFilterExpression>() { // from class: com.huawei.hihealth.HiDataFilter.DataFilterExpression.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: buv_, reason: merged with bridge method [inline-methods] */
            public DataFilterExpression createFromParcel(Parcel parcel) {
                return new DataFilterExpression(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public DataFilterExpression[] newArray(int i) {
                return new DataFilterExpression[i];
            }
        };
        public static final String BIGGER_THAN = ">";
        public static final String LESS_THAN = "<";
        public static final String BIGGER_EQUAL = ">=";
        public static final String LESS_EQUAL = "<=";
        private static final List<String> COMPARESYMBOLSLIST = Arrays.asList(BIGGER_THAN, LESS_THAN, BIGGER_EQUAL, LESS_EQUAL, "=");

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public DataFilterExpression(int i, String str, double d) {
            HiHealthDataType.Category e = HiHealthDataType.e(i);
            if ((e != HiHealthDataType.Category.POINT && e != HiHealthDataType.Category.STAT) || !COMPARESYMBOLSLIST.contains(str)) {
                throw new InvalidParameterException("DataFilterExpression param error");
            }
            this.dataType = i;
            this.compareSymbols = str;
            this.value = d;
        }

        public DataFilterExpression(String str, String str2, double d) {
            if (TextUtils.isEmpty(str) || !COMPARESYMBOLSLIST.contains(str2)) {
                throw new InvalidParameterException("DataFilterExpression param error");
            }
            this.constantsKey = str;
            this.compareSymbols = str2;
            this.value = d;
        }

        protected DataFilterExpression(Parcel parcel) {
            this.dataType = parcel.readInt();
            this.compareSymbols = parcel.readString();
            this.value = parcel.readDouble();
            this.constantsKey = parcel.readString();
        }

        public int getDataType() {
            return this.dataType;
        }

        public void setDataType(int i) {
            this.dataType = i;
        }

        public String getConstantsKey() {
            return this.constantsKey;
        }

        public void setConstantsKey(String str) {
            this.constantsKey = str;
        }

        public String getCompareSymbols() {
            return this.compareSymbols;
        }

        public void setCompareSymbols(String str) {
            this.compareSymbols = str;
        }

        public double getValue() {
            return this.value;
        }

        public void setValue(double d) {
            this.value = d;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.dataType);
            parcel.writeString(this.compareSymbols);
            parcel.writeDouble(this.value);
            parcel.writeString(this.constantsKey);
        }
    }
}
