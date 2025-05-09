package defpackage;

import android.database.Cursor;
import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ihh {
    public static List<HiHealthData> bxL_(Cursor cursor, String[] strArr) {
        ArrayList arrayList = new ArrayList(10);
        int[] iArr = null;
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"start_time", "end_time", "client_id", "type_id", "metadata", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 6];
                    for (int i2 = 0; i2 < 6; i2++) {
                        iArr2[i2] = cursor.getColumnIndex(strArr2[i2]);
                    }
                    for (int i3 = 0; i3 < strArr.length; i3++) {
                        iArr2[i3 + 6] = cursor.getColumnIndex(strArr[i3]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setClientId(cursor.getInt(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setMetaData(cursor.getString(iArr[4]));
                int i4 = iArr[5];
                if (i4 >= 0) {
                    hiHealthData.setModifiedTime(cursor.getLong(i4));
                }
                int length = iArr.length - strArr.length;
                int length2 = strArr.length;
                while (i < length2) {
                    hiHealthData.putDouble(strArr[i], cursor.getDouble(iArr[length]));
                    i++;
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> bxK_(Cursor cursor) {
        ArrayList arrayList = new ArrayList(10);
        int[] iArr = null;
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"client_id", "value"};
                    int[] iArr2 = new int[2];
                    for (int i = 0; i < 2; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setClientId(cursor.getInt(iArr[0]));
                hiHealthData.setValue(cursor.getInt(iArr[1]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }
}
