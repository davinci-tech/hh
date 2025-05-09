package defpackage;

import com.huawei.haf.common.os.FileUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.networkclient.repository.DataConverterRepository;

/* loaded from: classes5.dex */
public class lrd<Output> implements DataConverterRepository.DataConverter<Output> {
    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    public Output convert(String str, Class<Output> cls) {
        return (Output) lql.b().fromJson(str, (Class) cls);
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    public Output convert(ResponseBody responseBody, Class<Output> cls) {
        try {
            return (Output) lql.b().fromJson(responseBody.charStream(), (Class) cls);
        } finally {
            FileUtils.d(responseBody.getInputStream());
        }
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    public String revert(Output output) {
        return lql.b(output);
    }
}
