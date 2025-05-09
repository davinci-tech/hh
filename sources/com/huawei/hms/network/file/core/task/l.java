package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.k;
import java.io.Closeable;

/* loaded from: classes4.dex */
public class l<R extends k> implements ITaskResult<R> {

    /* renamed from: a, reason: collision with root package name */
    int f5637a;
    String b;
    FileManagerException c;
    Closeable d;
    R e;

    public String toString() {
        return "TaskResult{errorCode=" + this.f5637a + ", message='" + this.b + "', rawResponse=" + this.d + '}';
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public void setTask(R r) {
        this.e = r;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public void setRawResponse(Closeable closeable) {
        this.d = closeable;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public void setException(FileManagerException fileManagerException) {
        this.c = fileManagerException;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public e getTask() {
        return this.e;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public Closeable getRawResponse() {
        return this.d;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public FileManagerException getException() {
        return this.c;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public String getErrorMessage() {
        return this.b;
    }

    @Override // com.huawei.hms.network.file.core.task.ITaskResult
    public int getErrorCode() {
        return this.f5637a;
    }

    public void a(String str) {
        this.b = str;
    }

    public l(Constants.ErrorCode errorCode) {
        this.f5637a = errorCode.getErrorCode();
        this.b = errorCode.getErrorMessage();
    }
}
