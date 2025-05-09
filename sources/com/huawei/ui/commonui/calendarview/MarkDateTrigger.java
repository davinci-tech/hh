package com.huawei.ui.commonui.calendarview;

import android.os.Parcelable;
import com.huawei.basefitnessadvice.callback.UiCallback;
import java.util.Map;

/* loaded from: classes6.dex */
public interface MarkDateTrigger extends Parcelable {
    void retrieveMarkDate(long j, long j2, UiCallback<Map<String, HealthCalendar>> uiCallback);
}
