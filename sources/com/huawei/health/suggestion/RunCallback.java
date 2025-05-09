package com.huawei.health.suggestion;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.basefitnessadvice.model.Summary;
import java.util.List;

/* loaded from: classes4.dex */
public interface RunCallback {
    public static final int DIALOG_TYPE_ETE = 1;
    public static final int TYPE_CONNECT_STATUS_CANCEL = 2;
    public static final int TYPE_CONNECT_STATUS_CONNECTING = 5;
    public static final int TYPE_CONNECT_STATUS_FAILED = 0;
    public static final int TYPE_CONNECT_STATUS_NONSUPPORT = 4;
    public static final int TYPE_CONNECT_STATUS_NO_DEVICE = 3;
    public static final int TYPE_CONNECT_STATUS_SUCCESS = 1;
    public static final int TYPE_RTG_KEEP = 3;
    public static final int TYPE_RTG_RUN_FAST = 4;
    public static final int TYPE_RTG_RUN_LOW = 2;

    void dialog(int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, Context context);

    void onConnectStatus(int i);

    void onSummary(Summary summary);

    void onUpdate(Bundle bundle);

    void realTimeGuidance(int i, List<Integer> list);
}
