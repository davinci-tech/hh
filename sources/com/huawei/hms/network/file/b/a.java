package com.huawei.hms.network.file.b;

import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.file.api.Converter;
import com.huawei.hms.network.file.core.task.ITaskResult;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.Closeable;

/* loaded from: classes4.dex */
public final class a implements Converter<BodyRequest, String> {
    @Override // com.huawei.hms.network.file.api.Converter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String convertResponse(BodyRequest bodyRequest, ITaskResult iTaskResult) {
        Closeable rawResponse = iTaskResult.getRawResponse();
        if (rawResponse == null || !(rawResponse instanceof Response)) {
            return null;
        }
        return StringUtils.byte2Str(((ResponseBody) ((Response) rawResponse).getBody()).bytes());
    }
}
