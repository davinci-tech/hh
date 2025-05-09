package com.huawei.pluginmessagecenter.service;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class MessageObserverController {
    private static final int INIT_LIST_SIZE = 16;
    private static final String TAG = "UIDV_MessageObserverController";
    private List<MessageObserver> mObservers = Collections.synchronizedList(new ArrayList(16));

    public void addObservers(MessageObserver messageObserver) {
        if (messageObserver == null) {
            LogUtil.a(TAG, "addObservers ==> observer == null");
        } else {
            this.mObservers.add(messageObserver);
        }
    }

    public void deleteObservers(MessageObserver messageObserver) {
        if (messageObserver == null) {
            LogUtil.a(TAG, "deleteObservers ==> observer == null");
        } else {
            LogUtil.a(TAG, "deleteObservers ==> observer == ", messageObserver);
            this.mObservers.remove(messageObserver);
        }
    }

    public void notifyAllObservers(int i, MessageChangeEvent messageChangeEvent) {
        if (this.mObservers != null) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                MessageObserver messageObserver = (MessageObserver) it.next();
                if (messageObserver == null) {
                    LogUtil.a(TAG, "notifyAllObservers ==> observer == null");
                    return;
                } else {
                    LogUtil.a(TAG, "notifyAllObservers ==> observer == ", messageObserver);
                    messageObserver.onChange(i, messageChangeEvent);
                }
            }
        }
    }

    public void clear() {
        List<MessageObserver> list = this.mObservers;
        if (list != null) {
            list.clear();
        }
    }
}
