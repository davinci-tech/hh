package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryActionInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryStrategy;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kby extends DictionaryStrategy {
    private DictionaryActionInterface e;

    private kby() {
        this.e = new kbz();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryStrategy
    public <T> T execute(int i, Object... objArr) {
        LogUtil.a("DictionaryStrategyImpl", "execute type:", Integer.valueOf(i), ",objects:", objArr);
        if (objArr == null) {
            return null;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (objArr.length != 2) {
                        return null;
                    }
                    Object obj = objArr[0];
                    if (obj instanceof kbn) {
                        Object obj2 = objArr[1];
                        if (obj2 instanceof HiDataOperateListener) {
                            this.e.insertToHiHealth((kbn) obj, (HiDataOperateListener) obj2);
                        }
                    }
                } else {
                    return (T) b(i, objArr);
                }
            } else {
                if (objArr.length != 3) {
                    return null;
                }
                Object obj3 = objArr[0];
                if ((obj3 instanceof Integer) && (objArr[1] instanceof DeviceInfo) && (objArr[2] instanceof HiDataReadResultListener)) {
                    this.e.readHiHealthData(((Integer) obj3).intValue(), (DeviceInfo) objArr[1], (HiDataReadResultListener) objArr[2]);
                }
            }
        } else {
            if (objArr.length != 1) {
                return null;
            }
            Object obj4 = objArr[0];
            if (obj4 instanceof cwe) {
                return (T) execute(5, (cwe) obj4);
            }
        }
        return null;
    }

    private <T> T b(int i, Object[] objArr) {
        LogUtil.a("DictionaryStrategyImpl", "execute processOtherCase type:", Integer.valueOf(i));
        if (i != 4) {
            if (i != 5) {
                if (i == 6) {
                    if (objArr.length != 2) {
                        return null;
                    }
                    Object obj = objArr[0];
                    if (obj instanceof kbn) {
                        Object obj2 = objArr[1];
                        if (obj2 instanceof kbo) {
                            return (T) Boolean.valueOf(this.e.syncNextInfo((kbn) obj, (kbo) obj2));
                        }
                    }
                } else {
                    LogUtil.h("DictionaryStrategyImpl", "execute default");
                    return (T) a(i, objArr);
                }
            } else {
                if (objArr.length != 1) {
                    return null;
                }
                Object obj3 = objArr[0];
                if (obj3 instanceof cwe) {
                    return (T) this.e.parsePointData((cwe) obj3);
                }
            }
        } else {
            if (objArr.length != 3) {
                return null;
            }
            Object obj4 = objArr[0];
            if ((obj4 instanceof Integer) && (objArr[1] instanceof Long) && (objArr[2] instanceof Long)) {
                return (T) this.e.genCacheDicInfo(((Integer) obj4).intValue(), ((Long) objArr[1]).longValue(), ((Long) objArr[2]).longValue());
            }
        }
        return null;
    }

    private <T> T a(int i, Object[] objArr) {
        LogUtil.a("DictionaryStrategyImpl", "execute processOtherCase type:", Integer.valueOf(i));
        if (i == 7) {
            if (objArr.length != 1) {
                return null;
            }
            Object obj = objArr[0];
            if (obj instanceof kbo) {
                return (T) this.e.buildSendMessage((kbo) obj);
            }
        } else {
            LogUtil.h("DictionaryStrategyImpl", "execute default");
        }
        return null;
    }

    public static kby b() {
        return b.d;
    }

    static class b {
        private static kby d = new kby();
    }
}
