package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.networkclient.repository.DataConverterRepository;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public class lqz implements DataConverterRepository.DataConverter<byte[]> {
    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public byte[] convert(String str, Class<byte[]> cls) {
        return str.getBytes();
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public byte[] convert(ResponseBody responseBody, Class<byte[]> cls) {
        InputStream inputStream = responseBody.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    LogUtil.e("ByteArrayConverter", "convert error ", ExceptionUtils.d(e));
                }
            } catch (Throwable th) {
                FileUtils.d(responseBody.getInputStream());
                throw th;
            }
        }
        FileUtils.d(responseBody.getInputStream());
        return byteArrayOutputStream.toByteArray();
    }

    @Override // com.huawei.networkclient.repository.DataConverterRepository.DataConverter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String revert(byte[] bArr) {
        return new String(bArr, StandardCharsets.UTF_8);
    }
}
