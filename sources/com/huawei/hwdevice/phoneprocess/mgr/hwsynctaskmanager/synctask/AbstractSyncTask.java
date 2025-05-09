package com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes5.dex */
public abstract class AbstractSyncTask {
    private String mTaskTag;

    public abstract void startTask(IBaseResponseCallback iBaseResponseCallback);

    public AbstractSyncTask(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("taskTag is empty. must be string.");
        }
        this.mTaskTag = str;
    }

    public int hashCode() {
        return this.mTaskTag.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractSyncTask)) {
            return false;
        }
        AbstractSyncTask abstractSyncTask = (AbstractSyncTask) obj;
        if (TextUtils.isEmpty(abstractSyncTask.mTaskTag)) {
            return false;
        }
        return abstractSyncTask.mTaskTag.equals(this.mTaskTag);
    }

    public String getTaskTag() {
        return this.mTaskTag;
    }
}
