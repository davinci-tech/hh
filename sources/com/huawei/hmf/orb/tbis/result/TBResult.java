package com.huawei.hmf.orb.tbis.result;

/* loaded from: classes9.dex */
public abstract class TBResult {

    public interface Callback {
        void onException(String str);

        void onResult(String str);

        void onStreamingResult(String str);
    }

    public abstract Object getValue();
}
