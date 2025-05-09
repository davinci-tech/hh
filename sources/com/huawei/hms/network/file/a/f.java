package com.huawei.hms.network.file.a;

import com.huawei.hms.network.file.api.Converter;
import com.huawei.hms.network.file.core.task.ITaskResult;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.io.File;

/* loaded from: classes4.dex */
public final class f implements Converter<GetRequest, File> {
    @Override // com.huawei.hms.network.file.api.Converter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public File convertResponse(GetRequest getRequest, ITaskResult iTaskResult) {
        if (iTaskResult.getTask() instanceof d) {
            return new File(((d) iTaskResult.getTask()).z());
        }
        return null;
    }
}
