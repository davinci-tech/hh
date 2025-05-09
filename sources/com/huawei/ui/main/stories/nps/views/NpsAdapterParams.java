package com.huawei.ui.main.stories.nps.views;

import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class NpsAdapterParams {
    private OnCheckedChangeListener mCheckedChangeListener;
    private String[] mDataInfos;
    private boolean mIsMultiple;
    private HashMap<String, Boolean> mStateMap;

    public String[] getDataInfos() {
        String[] strArr = this.mDataInfos;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public void setDataInfos(String[] strArr) {
        this.mDataInfos = (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public HashMap<String, Boolean> getStateMap() {
        return this.mStateMap;
    }

    public void setStateMap(HashMap<String, Boolean> hashMap) {
        this.mStateMap = hashMap;
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.mCheckedChangeListener;
    }

    public void setCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mCheckedChangeListener = onCheckedChangeListener;
    }

    public boolean getIsMultiple() {
        return this.mIsMultiple;
    }

    public void setIsMultiple(boolean z) {
        this.mIsMultiple = z;
    }
}
