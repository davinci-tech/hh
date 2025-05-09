package defpackage;

import com.huawei.health.baseapi.pluginlocation.EphemerisExtApi;
import com.huawei.health.pluginlocation.eph.pgnss.PgnssManager;
import com.huawei.health.pluginlocation.logger.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class eyg implements EphemerisExtApi {
    public eyg(Logger logger) {
        eym.c(logger);
    }

    @Override // com.huawei.health.baseapi.pluginlocation.EphemerisExtApi
    public eyk getEphRequest(List<String> list) {
        eym.b("EphemerisExtApiImpl", "getEphRequest start");
        eyk eykVar = new eyk();
        if (list == null || list.size() == 0) {
            eym.c("EphemerisExtApiImpl", "getEphResponse urls is invalid");
            eykVar.d(false);
            return eykVar;
        }
        int a2 = a(list);
        switch (a2) {
            case 1:
                eykVar = eyh.b().d(list);
                break;
            case 2:
                eykVar = PgnssManager.c().c(list);
                break;
            case 3:
            default:
                eykVar.d(false);
                break;
            case 4:
                eykVar = PgnssManager.c().a(list);
                break;
            case 5:
            case 6:
            case 7:
                eykVar = PgnssManager.c().d(list, a2);
                break;
            case 8:
                eykVar = PgnssManager.c().e(list, a2);
                break;
        }
        eym.b("EphemerisExtApiImpl", "getEphRequest end");
        return eykVar;
    }

    @Override // com.huawei.health.baseapi.pluginlocation.EphemerisExtApi
    public Map<String, String> getEphResponse(eyl eylVar) {
        eym.b("EphemerisExtApiImpl", "getEphResponse start");
        Map<String, String> hashMap = new HashMap<>();
        if (eylVar == null) {
            eym.c("EphemerisExtApiImpl", "getEphResponse response is invalid");
            return hashMap;
        }
        eyd.c(180000);
        switch (eylVar.c()) {
            case 1:
                hashMap = eyh.b().b(eylVar);
                break;
            case 2:
                hashMap = PgnssManager.c().a(eylVar);
                break;
            case 4:
                hashMap = PgnssManager.c().c(eylVar);
                break;
            case 5:
            case 6:
            case 7:
                hashMap = PgnssManager.c().d(eylVar);
                break;
            case 8:
                hashMap = PgnssManager.c().b(eylVar);
                break;
        }
        eyd.b();
        eym.b("EphemerisExtApiImpl", "getEphResponse end");
        return hashMap;
    }

    private int a(List<String> list) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                break;
            }
            String str = list.get(i2);
            if (str.contains("HW_AGNSS")) {
                i = 1;
                break;
            }
            if (str.contains("HW_PGNSS_PRED")) {
                i = 2;
                break;
            }
            if (str.contains("HW_PGNSS_TRANS")) {
                i = 4;
                break;
            }
            if (str.contains("HW_RSMC_PGNSS_BDS")) {
                i = 5;
            } else if (str.contains("HW_RSMC_SYSTEM_CONFIG")) {
                i = 6;
            } else if (str.contains("HW_RSMC_ORBITS_CONFIG")) {
                i = 7;
            } else {
                if (str.contains("HW_PRECSION_ION")) {
                    i = 8;
                    break;
                }
                eym.b("EphemerisExtApiImpl", "not supported EPH type");
            }
            i2++;
        }
        eym.b("EphemerisExtApiImpl", "getEphType, ephType: " + i);
        return i;
    }
}
