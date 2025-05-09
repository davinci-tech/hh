package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class row {
    private BaseOperate e;

    public row(int i) {
        switch (i) {
            case 100:
                this.e = new rqo();
                break;
            case 101:
                this.e = new rot();
                break;
            case 102:
                this.e = new rou();
                break;
            case 103:
                this.e = new rpw();
                break;
            case 104:
                this.e = new rpj();
                break;
            case 105:
                this.e = new rlo();
                break;
            case 106:
                this.e = new rnl();
                break;
            case 107:
                this.e = new rmq();
                break;
            case 108:
                this.e = new rnj();
                break;
            case 109:
                this.e = new rqn();
                break;
            case 110:
                this.e = new roo();
                break;
            case 111:
                this.e = new rop();
                break;
            case 113:
                this.e = new rpm();
                break;
        }
    }

    public void b(final List<PrivacyDataModel> list, final rkb rkbVar, final DataSourceCallback<Boolean> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("DataManager", "callback is null");
        } else if (koq.b(list)) {
            LogUtil.b("DataManager", "delete data is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: row.1
                @Override // java.lang.Runnable
                public void run() {
                    row.this.e.deleteDatas(list, rkbVar, dataSourceCallback);
                }
            });
        }
    }

    public void a(int i, String str, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("DataManager", "getDataByMonth callback is null");
            return;
        }
        if (i == 1) {
            this.e.readByMonthByAppSource(str2, dataSourceCallback);
        } else if (i == 2 || i == 3) {
            this.e.readByMonthByDeviceSource(str, i, str2, dataSourceCallback);
        }
    }

    public void a(DataSourceCallback<List<rsg>> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("DataManager", "callback null");
        } else {
            this.e.readByMonthNoSource(dataSourceCallback);
        }
    }

    public void a(int i, long j, String str, String str2, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("DataManager", "Parameter callback is null");
            return;
        }
        if (i != 1) {
            if (i == 2) {
                this.e.readInOneDayByDeviceSource(j, str, dataSourceCallback);
                return;
            } else if (i != 3) {
                return;
            }
        }
        this.e.readInOneDayByAppSource(j, str, str2, i, dataSourceCallback);
    }

    public void b(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("DataManager", "callback null");
        } else {
            this.e.readDayData(rkbVar, dataSourceCallback);
        }
    }

    public void d(int i, String str, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        a(i, str, str2, dataSourceCallback);
    }

    public void a(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        this.e.readGroupData(rkbVar, dataSourceCallback);
    }

    public void e(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsg>>> dataSourceCallback) {
        this.e.readCategoryGroupData(rkbVar, dataSourceCallback);
    }

    public void c(rkb rkbVar, DataSourceCallback<List<rsb>> dataSourceCallback) {
        this.e.readDoubleGroupData(rkbVar, dataSourceCallback);
    }

    public void d(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsb>>> dataSourceCallback) {
        this.e.readCategoryDoubleGroupData(rkbVar, dataSourceCallback);
    }
}
