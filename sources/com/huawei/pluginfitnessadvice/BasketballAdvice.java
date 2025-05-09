package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BasketballAdvice implements Parcelable {
    public static final int AVERAGE_JUMP_HEIGHT = 0;
    public static final int AVERAGE_JUMP_TIME = 1;
    public static final Parcelable.Creator<BasketballAdvice> CREATOR = new Parcelable.Creator<BasketballAdvice>() { // from class: com.huawei.pluginfitnessadvice.BasketballAdvice.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: clE_, reason: merged with bridge method [inline-methods] */
        public BasketballAdvice createFromParcel(Parcel parcel) {
            return new BasketballAdvice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BasketballAdvice[] newArray(int i) {
            return new BasketballAdvice[i];
        }
    };
    private static final String TAG = "BasketballAdvice";
    private List<String> mActionList;
    private int mType;
    private int mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BasketballAdvice(int i, int i2) {
        this.mValue = i;
        this.mType = i2;
        initActionList();
    }

    protected BasketballAdvice(Parcel parcel) {
        this.mValue = parcel.readInt();
        this.mType = parcel.readInt();
        this.mActionList = parcel.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel, dest is null");
            return;
        }
        parcel.writeInt(this.mValue);
        parcel.writeInt(this.mType);
        parcel.writeStringList(this.mActionList);
    }

    private void initActionList() {
        ArrayList arrayList = new ArrayList(4);
        this.mActionList = arrayList;
        int i = this.mType;
        if (i == 0) {
            arrayList.add("007B");
            this.mActionList.add("023B");
            this.mActionList.add("020B");
            this.mActionList.add("078B");
            LogUtil.a(TAG, "initActionList, type == AVERAGE_JUMP_HEIGHT");
            return;
        }
        if (i == 1) {
            arrayList.add("007B");
            this.mActionList.add("214B");
            this.mActionList.add("202B");
            this.mActionList.add("220B");
            LogUtil.a(TAG, "initActionList, type == AVERAGE_JUMP_TIME");
            return;
        }
        LogUtil.a(TAG, "initActionList, type = ", Integer.valueOf(i));
    }

    public int getValue() {
        return this.mValue;
    }

    public void setValue(int i) {
        this.mValue = i;
    }

    public int getType() {
        return this.mType;
    }

    public List<String> getActionList() {
        return this.mActionList;
    }

    public int[] getValueList() {
        return new int[]{this.mValue};
    }
}
