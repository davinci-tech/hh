package defpackage;

import android.net.Uri;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class fel {
    public static String b(Object obj) {
        LogUtil.a("Track_SportSoundDataParser", "getEventType enter");
        if (!(obj instanceof Uri)) {
            return null;
        }
        String uri = ((Uri) obj).toString();
        LogUtil.a("Track_SportSoundDataParser", "getEventType uri = ", uri);
        StringBuilder sb = new StringBuilder(uri);
        return sb.substring(sb.lastIndexOf("/") + 1);
    }
}
