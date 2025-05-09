package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.networkclient.repository.DataConverterRepository;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public class lre implements DataConverterRepository.DataConverter<String> {
    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String revert(String str) {
        return str;
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String convert(String str, Class<String> cls) {
        return str;
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String convert(ResponseBody responseBody, Class<String> cls) {
        try {
            return new String(responseBody.bytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LogUtil.e("StringConverter", "convert error ", ExceptionUtils.d(e));
            return null;
        }
    }
}
