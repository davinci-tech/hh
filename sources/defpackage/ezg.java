package defpackage;

import com.huawei.health.provider.cursor.BaseStatisticsHealthCursor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class ezg extends BaseStatisticsHealthCursor {
    public ezg(LinkedHashMap<String, Object> linkedHashMap) {
        super(linkedHashMap);
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addColumnName() {
        Iterator<String> it = this.mDataMap.keySet().iterator();
        while (it.hasNext()) {
            addColumnName(it.next());
        }
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addData() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : getColumnNames()) {
            arrayList.add(String.valueOf(this.mDataMap.get(str)));
        }
        addRowData(arrayList);
    }
}
