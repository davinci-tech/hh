package com.huawei.featureuserprofile.todo.datatype;

import com.amap.api.col.p0003sl.it;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import java.util.List;

/* loaded from: classes3.dex */
public class TodoSwitchSync {

    @SerializedName(it.k)
    private List<String> keys;

    @SerializedName(FitRunPlayAudio.PLAY_TYPE_V)
    private List<Integer> values;

    public List<String> getKeys() {
        return this.keys;
    }

    public void setKeys(List<String> list) {
        this.keys = list;
    }

    public List<Integer> getValues() {
        return this.values;
    }

    public void setValues(List<Integer> list) {
        this.values = list;
    }
}
