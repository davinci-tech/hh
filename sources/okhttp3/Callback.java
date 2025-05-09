package okhttp3;

import androidx.core.app.NotificationCompat;
import com.huawei.hms.network.embedded.y;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lokhttp3/Callback;", "", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", TrackConstants$Opers.RESPONSE, "Lokhttp3/Response;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface Callback {
    void onFailure(Call call, IOException e);

    void onResponse(Call call, Response response) throws IOException;
}
