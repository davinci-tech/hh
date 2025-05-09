package com.huawei.pluginmessagecenter.util;

import com.huawei.health.messagecenter.model.MessageObject;
import java.util.List;

/* loaded from: classes6.dex */
public interface PullMessageCallback {
    void pullMessageResult(int i, List<MessageObject> list, List<String> list2);
}
