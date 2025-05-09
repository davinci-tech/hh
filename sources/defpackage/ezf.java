package defpackage;

import com.huawei.health.provider.cursor.BaseStatisticsHealthCursor;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class ezf extends BaseStatisticsHealthCursor {
    public ezf(Map<String, Object> map) {
        super(map);
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addColumnName() {
        addColumnName("version_code");
        addColumnName("is_browse_mode");
        addColumnName("is_oversea");
        addColumnName("is_authorized");
        addColumnName("health_data");
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addData() {
        if (this.mDataMap == null || this.mDataMap.size() <= 0) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(4);
        arrayList.add(String.valueOf(this.mDataMap.get("version_code")));
        arrayList.add(String.valueOf(this.mDataMap.get("is_browse_mode")));
        arrayList.add(String.valueOf(this.mDataMap.get("is_oversea")));
        arrayList.add(String.valueOf(this.mDataMap.get("is_authorized")));
        arrayList.add(String.valueOf(this.mDataMap.get("health_data")));
        addRowData(arrayList);
    }
}
