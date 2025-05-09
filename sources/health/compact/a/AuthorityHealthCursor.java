package health.compact.a;

import com.huawei.health.provider.cursor.HealthCursor;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AuthorityHealthCursor extends HealthCursor {
    public AuthorityHealthCursor() {
        initData();
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addColumnName() {
        addColumnName("authority_age");
        addColumnName("authority_gender");
        addColumnName("authority_sport");
        addColumnName("authority_sleep");
        addColumnName("authority_weight");
        addColumnName("authority_height");
        addColumnName("authority_heart_rate");
        addColumnName("authority_blood_sugar");
        addColumnName("authority_blood_pressure");
    }

    @Override // com.huawei.health.provider.cursor.HealthCursor
    public void addData() {
        ArrayList<String> arrayList = new ArrayList<>(9);
        c(arrayList, 0);
        c(arrayList, 1);
        c(arrayList, 2);
        c(arrayList, 3);
        c(arrayList, 4);
        c(arrayList, 5);
        c(arrayList, 7);
        c(arrayList, 8);
        c(arrayList, 9);
        addRowData(arrayList);
    }

    private void c(ArrayList<String> arrayList, int i) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_AuthorityHealthCursor", "addAuthorityColumn ", Integer.valueOf(i));
        arrayList.add(String.valueOf(1));
    }
}
