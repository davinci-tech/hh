package health.compact.a;

import com.huawei.health.provider.cursor.BaseStatisticsHealthCursor;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class StatisticsSleepHealthCursor extends BaseStatisticsHealthCursor {
    private int c(int i, int i2, int i3) {
        if (i < 390 || i2 < 120 || i3 > 2) {
            return (i < 330 || i2 < 90 || i2 >= 120 || i3 > 3) ? 1 : 2;
        }
        return 3;
    }

    public StatisticsSleepHealthCursor(Map<String, Object> map) {
        super(map);
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addColumnName() {
        addColumnName("start_time");
        addColumnName("end_time");
        addColumnName("sleep_Sum");
        addColumnName("deep_sleep_sum");
        addColumnName("light_sleep_sum");
        addColumnName("wake_sum");
        addColumnName("wake_count");
        addColumnName("sleep_quality");
        addColumnName("sleep_avg");
        addColumnName("deep_sleep_avg");
        addColumnName("light_sleep_avg");
        addColumnName("wake_avg");
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addData() {
        if (this.mDataMap == null || this.mDataMap.size() <= 0) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(12);
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_start_time")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_end_time")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_duration_sum")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_deep_duration")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_shallow_duration")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_wake_duration")));
        arrayList.add(String.valueOf(this.mDataMap.get("sleep_wake_count")));
        arrayList.add(String.valueOf(c(((Integer) this.mDataMap.get("sleep_duration_sum")).intValue(), ((Integer) this.mDataMap.get("sleep_deep_duration")).intValue(), ((Integer) this.mDataMap.get("sleep_wake_count")).intValue())));
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        addRowData(arrayList);
    }
}
