package com.huawei.hms.kit.awareness.status;

import android.os.Parcelable;
import com.huawei.hms.kit.awareness.a.a.b;
import java.util.List;

/* loaded from: classes4.dex */
public interface BeaconStatus {

    /* loaded from: classes9.dex */
    public interface BeaconData {
        String getBeaconId();

        byte[] getContent();

        String getNamespace();

        String getType();
    }

    List<BeaconData> getBeaconData();

    public static abstract class Filter implements Parcelable {
        public static Filter match(String str, String str2, byte[] bArr) {
            return new b(str, str2, bArr);
        }

        public static Filter match(String str, String str2) {
            return new b(str, str2);
        }

        public static Filter match(String str) {
            return new b(str);
        }
    }
}
