package com.huawei.health.device.wifi.entity.builder;

import android.text.TextUtils;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.cpw;
import defpackage.ctc;
import defpackage.cth;
import defpackage.ctm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BaseBuilder implements Serializable {
    private static final int COMMON_LENGTH = 2;
    private static final int REQUEST_TIMEOUT = 5000;
    private static final String TAG = "BaseBuilder";
    private static final long serialVersionUID = -2536229911637793283L;
    private String uri = "";
    private long defaultTime = 5000;

    public abstract String makeRequestStream();

    public abstract ctc makeResponseEntity(String str);

    public ctc makeResponseEntity(String str, String str2) {
        return null;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public String getUri() {
        return this.uri;
    }

    public void setDefaultTime(long j) {
        this.defaultTime = j;
    }

    public long getDefaultTime() {
        return this.defaultTime;
    }

    public ctc makeResponseEntity(byte[] bArr) {
        return new ctc();
    }

    protected cth parseResponseEntity(String str, String str2) {
        cth cthVar = new cth();
        if (!TextUtils.isEmpty(str)) {
            Map<String, Object> c = JsonParser.c(str);
            try {
                if (c.containsKey("errcode") && (c.get("errcode") instanceof Integer)) {
                    cthVar.c(((Integer) c.get("errcode")).intValue());
                }
                if (c.containsKey("devId") && (c.get("devId") instanceof String)) {
                    cthVar.a((String) c.get("devId"));
                }
                if (c.containsKey(BleConstants.DEV_INFO)) {
                    cthVar.b(parserObjToDevInfo(c.get(BleConstants.DEV_INFO).toString()));
                } else {
                    cthVar.b(null);
                }
                if (str2 != null) {
                    cpw.c(true, TAG, "address: ", str2);
                    cthVar.c(str2);
                } else if (c.containsKey("baseUrl") && (c.get("baseUrl") instanceof String)) {
                    cthVar.c((String) c.get("baseUrl"));
                } else {
                    cpw.a(true, TAG, "address is null and baseUrl is not right");
                }
                if (c.containsKey("services")) {
                    cthVar.d(parserObjToServiceInfo(c.get("services").toString()));
                }
            } catch (ClassCastException e) {
                cpw.e(true, TAG, e.getMessage());
            }
        }
        return cthVar;
    }

    protected cth parseResponseEntity(String str) {
        cth cthVar = new cth();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "parseResponseEntity stream is empty");
            return cthVar;
        }
        Map<String, Object> c = JsonParser.c(str);
        try {
            if (c.containsKey("errcode")) {
                Object obj = c.get("errcode");
                if (obj instanceof Integer) {
                    cthVar.c(((Integer) obj).intValue());
                }
            }
            if (c.containsKey("devId")) {
                Object obj2 = c.get("devId");
                if (obj2 instanceof String) {
                    cthVar.a((String) obj2);
                }
            }
            if (c.containsKey(BleConstants.DEV_INFO)) {
                cthVar.b(parserObjToDevInfo(c.get(BleConstants.DEV_INFO).toString()));
            } else {
                cthVar.b(null);
            }
            if (c.containsKey("baseUrl")) {
                Object obj3 = c.get("baseUrl");
                if (obj3 instanceof String) {
                    cthVar.c((String) obj3);
                }
            }
            if (c.containsKey("services")) {
                cthVar.d(parserObjToServiceInfo(c.get("services").toString()));
            }
        } catch (ClassCastException e) {
            cpw.e(true, TAG, e.getMessage());
        }
        return cthVar;
    }

    private cth.a parserObjToDevInfo(String str) {
        cpw.c(false, TAG, "parserObjToDevInfo: obj is ", str);
        cth.a aVar = new cth.a();
        if (str != null && str.length() > 1) {
            HashMap hashMap = new HashMap(16);
            String substring = str.substring(1, str.length() - 1);
            cpw.c(false, TAG, "parserObjToDevInfo: str substring is ", substring);
            for (String str2 : substring.split(",")) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    cpw.c(false, TAG, "parserObjToDevInfo: cell[0] is " + split[0].trim() + ", cell[1] is " + split[1].trim());
                    hashMap.put(split[0].trim(), split[1].trim());
                }
            }
            try {
                ctm.d(hashMap, aVar);
            } catch (ClassCastException e) {
                cpw.e(true, TAG, e.getMessage());
            } catch (NumberFormatException e2) {
                cpw.e(true, TAG, e2.getMessage());
            }
        }
        return aVar;
    }

    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v4 */
    private List<cth.c> parserObjToServiceInfo(String str) {
        ?? r2;
        int i = 16;
        ArrayList arrayList = new ArrayList(16);
        if (str != null && str.length() > 0) {
            ?? r4 = 0;
            cpw.c(false, TAG, "parserObjToServiceInfo: obj is ", str);
            String replaceAll = str.replaceAll("(?:\\{|\\}|\\[|\\])", "");
            cpw.c(false, TAG, "parserObjToServiceInfo: service is ", replaceAll);
            String[] split = replaceAll.split(",");
            int length = split.length;
            if (length > 1 && length % 2 == 0) {
                int i2 = 0;
                while (i2 < length) {
                    cth.c cVar = new cth.c();
                    HashMap hashMap = new HashMap(i);
                    String trim = split[i2].trim();
                    String trim2 = split[i2 + 1].trim();
                    Object[] objArr = new Object[2];
                    objArr[r4] = "parserObjToServiceInfo: stCell is ";
                    objArr[1] = trim2;
                    cpw.c(r4, TAG, objArr);
                    String[] split2 = trim.split("=");
                    if (split2.length == 2) {
                        Object[] objArr2 = new Object[3];
                        objArr2[r4] = "parserObjToServiceInfo: cell[0] is ";
                        objArr2[1] = split2[r4].trim();
                        objArr2[2] = ", cell[1] is " + split2[1];
                        r2 = 0;
                        cpw.c(false, TAG, objArr2);
                        hashMap.put(split2[0].trim(), split2[1].trim());
                    } else {
                        r2 = r4;
                    }
                    String[] split3 = trim2.split("=");
                    if (split2.length > 0) {
                        cpw.c(r2, TAG, "parserObjToServiceInfo: celll[0] is ", split3[r2].trim());
                        hashMap.put(split3[r2].trim(), "");
                    }
                    if (hashMap.containsKey("st") && (hashMap.get("st") instanceof String)) {
                        cVar.e((String) hashMap.get("st"));
                    }
                    if (hashMap.containsKey("sid") && (hashMap.get("st") instanceof String)) {
                        cVar.d((String) hashMap.get("sid"));
                    }
                    arrayList.add(cVar);
                    i2 += 2;
                    r4 = r2;
                    i = 16;
                }
            }
        }
        return arrayList;
    }

    public byte[] makeRequestByte() {
        return new byte[0];
    }
}
