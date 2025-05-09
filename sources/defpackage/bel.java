package defpackage;

import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.converter.gson.GsonConverterFactory;

/* loaded from: classes3.dex */
public class bel {
    public static <T> T b(Class<T> cls, String str) {
        return (T) new RestClient.Builder().baseUrl(str).httpClient(bem.b()).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).build().create(cls);
    }
}
