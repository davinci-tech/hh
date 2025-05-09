package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionarySequenceInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryStrategy;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kbx extends DictionaryStrategy {
    private DictionarySequenceInterface d;

    private kbx() {
        this.d = new kbw();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy.DictionaryStrategy
    public <T> T execute(int i, Object... objArr) {
        LogUtil.a("DictionarySequenceStrategyImpl", "execute type:", Integer.valueOf(i), ",objects:", objArr);
        if (objArr == null) {
            return null;
        }
        if (i != 2) {
            if (i != 3) {
                if (i == 5) {
                    if (objArr.length != 1) {
                        return null;
                    }
                    Object obj = objArr[0];
                    if (obj instanceof String) {
                        return (T) this.d.parseSequenceData((String) obj);
                    }
                } else {
                    return (T) e(i, objArr);
                }
            } else {
                if (objArr.length != 2) {
                    return null;
                }
                Object obj2 = objArr[0];
                if (obj2 instanceof kbu) {
                    Object obj3 = objArr[1];
                    if (obj3 instanceof HiDataOperateListener) {
                        this.d.insertToHiHealth((kbu) obj2, (HiDataOperateListener) obj3);
                    }
                }
            }
        } else {
            if (objArr.length != 3) {
                return null;
            }
            Object obj4 = objArr[0];
            if ((obj4 instanceof Integer) && (objArr[1] instanceof DeviceInfo) && (objArr[2] instanceof HiDataReadResultListener)) {
                this.d.readHiHealthData(((Integer) obj4).intValue(), (DeviceInfo) objArr[1], (HiDataReadResultListener) objArr[2]);
            }
        }
        return null;
    }

    private <T> T e(int i, Object[] objArr) {
        LogUtil.a("DictionarySequenceStrategyImpl", "execute processOtherCase type:", Integer.valueOf(i));
        if (i != 4) {
            if (i != 6) {
                if (i == 7) {
                    if (objArr.length != 1) {
                        return null;
                    }
                    Object obj = objArr[0];
                    if (obj instanceof kbs) {
                        return (T) this.d.buildSendMessage((kbs) obj);
                    }
                } else {
                    LogUtil.h("DictionarySequenceStrategyImpl", "execute default");
                }
            } else {
                if (objArr.length != 2) {
                    return null;
                }
                Object obj2 = objArr[0];
                if (obj2 instanceof kbu) {
                    Object obj3 = objArr[1];
                    if (obj3 instanceof kbs) {
                        return (T) Boolean.valueOf(this.d.syncNextInfo((kbu) obj2, (kbs) obj3));
                    }
                }
            }
        } else {
            if (objArr.length != 3) {
                return null;
            }
            Object obj4 = objArr[0];
            if ((obj4 instanceof Integer) && (objArr[1] instanceof Integer) && (objArr[2] instanceof Integer)) {
                return (T) this.d.genCacheDicInfo(((Integer) obj4).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
        return null;
    }

    public static kbx d() {
        return c.e;
    }

    static class c {
        private static kbx e = new kbx();
    }
}
