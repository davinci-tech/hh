package com.huawei.hms.iapfull.network.model;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AliPaySignResponse extends BaseResponse {
    private static final String CHARSET = "UTF-8";
    private static final String TAG = "AliPaySignResponse";
    private String alipayTradeType;
    private String customerNumber;
    private String invokeUrl;
    private String orderID;
    private String pactNo;
    private String reservedInfor;
    private Map<String, String> reservedInforMap;
    private String sign;
    public WeChatPayInfo wxpayInfo;

    public void setWeChatPayInfo(WeChatPayInfo weChatPayInfo) {
        this.wxpayInfo = weChatPayInfo;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setPartnerId(String str) {
        this.customerNumber = str;
    }

    public void setPactNo(String str) {
        this.pactNo = str;
    }

    public void setOrderID(String str) {
        this.orderID = str;
    }

    public void setInvokeUrl(String str) {
        this.invokeUrl = str;
    }

    public void setAlipayTradeType(String str) {
        this.alipayTradeType = str;
    }

    public void parseWeChatJson() {
        if ("0".equals(this.returnCode) && this.wxpayInfo == null) {
            y0.a(TAG, "parseWeChatJson wxpayInfo is null");
        }
    }

    public void parseJson() {
        try {
            if (!"0".equals(this.returnCode) || TextUtils.isEmpty(this.reservedInfor)) {
                return;
            }
            this.reservedInforMap = jsonToMap(new JSONObject(this.reservedInfor));
        } catch (JSONException unused) {
            y0.a(TAG, "parseJson JSONException");
        }
    }

    public WeChatPayInfo getWeChatPayInfo() {
        return this.wxpayInfo;
    }

    public String getSign() {
        return this.sign;
    }

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getPartnerId() {
        return this.customerNumber;
    }

    public String getPactNo() {
        return this.pactNo;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public String getInvokeUrl() {
        return this.invokeUrl;
    }

    public String getAlipayTradeType() {
        return this.alipayTradeType;
    }

    public Uri getAliPayUri() {
        String str = this.invokeUrl;
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            y0.a(TAG, "getAliPayUri alipay invokeUrl URLEncoder error");
        }
        return Uri.parse("alipays://platformapi/startapp?appId=20000067&url=" + str);
    }

    public String getAliPayOrderInfo() {
        String str;
        String str2;
        String str3;
        Map<String, String> map = this.reservedInforMap;
        if (map != null) {
            str = map.get("charset");
            str2 = getAlipaySignparam(map, str);
        } else {
            str = null;
            str2 = null;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = "UTF-8";
            }
            str3 = URLEncoder.encode(this.sign, str);
        } catch (UnsupportedEncodingException unused) {
            str3 = this.sign;
            y0.b(TAG, "getAliPayOrderInfo Alipay params URLEncoder encode error");
        }
        return getSign(str2, str3);
    }

    private Map<String, String> jsonToMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        hashMap.put("charset", jSONObject.optString("charset"));
        hashMap.put("biz_content", jSONObject.optString("biz_content"));
        hashMap.put("method", jSONObject.optString("method"));
        hashMap.put("format", jSONObject.optString("format"));
        hashMap.put("notify_url", jSONObject.optString("notify_url"));
        hashMap.put("app_id", jSONObject.optString("app_id"));
        hashMap.put("sign_type", jSONObject.optString("sign_type"));
        hashMap.put("version", jSONObject.optString("version"));
        hashMap.put("timestamp", jSONObject.optString("timestamp"));
        return hashMap;
    }

    private String getSign(String str, String str2) {
        return str + "&sign=" + str2;
    }

    public static String getAlipaySignparam(Map<String, String> map, String str) {
        String str2;
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str3 = (String) arrayList.get(i);
            if (!HwPayConstant.KEY_SIGN.equals(str3) && (str2 = map.get(str3)) != null) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        str2 = URLEncoder.encode(str2, str);
                    }
                } catch (UnsupportedEncodingException unused) {
                    y0.b(TAG, "getAlipaySignparam Alipay sign param fail");
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i == 0 ? "" : "&");
                sb2.append(str3);
                sb2.append("=");
                sb2.append(str2);
                sb.append(sb2.toString());
            }
            i++;
        }
        return sb.toString();
    }

    public AliPaySignResponse(String str) {
        super(str);
    }

    public AliPaySignResponse() {
    }
}
