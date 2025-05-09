package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.security.SecurityCertUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.AssetMessage;
import com.huawei.trade.datatype.AssetMessageTemple;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import health.compact.a.CommonUtil;
import health.compact.a.HwEncryptUtil;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.Closeable;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class njn {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f15333a = false;

    public static List<MessageObject> e(List<AssetMessage> list, boolean z) {
        String format;
        boolean z2;
        String str;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.d("TradeUtils", "assetMessages is empty");
            return arrayList;
        }
        LogUtil.d("TradeUtils", "assetMessages size:", Integer.valueOf(list.size()));
        for (AssetMessage assetMessage : list) {
            int infoType = assetMessage.getInfoType();
            String d = d(infoType);
            if (TextUtils.isEmpty(d)) {
                LogUtil.c("TradeUtils", "type not match");
            } else {
                String str2 = "messagecenter://asset?type=" + assetMessage.getInfoType();
                ContentValues contentValues = new ContentValues();
                if (infoType == 5) {
                    try {
                        JSONObject jSONObject = new JSONObject(assetMessage.getInfoValue());
                        String optString = jSONObject.optString("orderCode");
                        String optString2 = jSONObject.optString(HwPayConstant.KEY_PRODUCTNAME);
                        int optInt = jSONObject.optInt("attachProductsNum");
                        if (optInt != 0) {
                            if (optInt > 0) {
                                str = BaseApplication.getContext().getResources().getString(R.string._2130847224_res_0x7f0225f8, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903401_res_0x7f030169, optInt, String.valueOf(optInt)));
                                String string = BaseApplication.getContext().getResources().getString(R.string._2130847692_res_0x7f0227cc, optString2);
                                contentValues.put("orderCode", optString);
                                str2 = "/TradeService/TradeOrderDetailActivity";
                                format = str;
                                d = string;
                                z2 = true;
                            } else {
                                LogUtil.c("TradeUtils", "convertData, attachProductsNum is less than 0");
                            }
                        }
                        str = "";
                        String string2 = BaseApplication.getContext().getResources().getString(R.string._2130847692_res_0x7f0227cc, optString2);
                        contentValues.put("orderCode", optString);
                        str2 = "/TradeService/TradeOrderDetailActivity";
                        format = str;
                        d = string2;
                        z2 = true;
                    } catch (JSONException e) {
                        LogUtil.c("TradeUtils", "convertData, JSONException e is: ", e.getMessage());
                    }
                } else {
                    format = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845026_res_0x7f021d62), d(assetMessage.getInfoValue()).getAssertName());
                    z2 = false;
                }
                MessageObject messageObject = new MessageObject();
                messageObject.setMsgTitle(d);
                messageObject.setInfoClassify(BaseApplication.getContext().getResources().getString(R.string._2130845025_res_0x7f021d61));
                messageObject.setMsgType(2);
                if (z) {
                    messageObject.setPosition(3);
                } else {
                    messageObject.setPosition(1);
                }
                messageObject.setMsgPosition(11);
                messageObject.setReceiveTime(assetMessage.getGenTime());
                messageObject.setMsgId(assetMessage.getMsgId());
                messageObject.setModule(String.valueOf(80));
                messageObject.setType("asset");
                if (assetMessage.getVisitFlag()) {
                    messageObject.setReadFlag(1);
                } else {
                    messageObject.setReadFlag(0);
                }
                messageObject.setDetailUri(str2);
                messageObject.setMsgContent(format);
                messageObject.setCreateTime(assetMessage.getGenTime());
                if (z2) {
                    messageObject.setNewMsg(1);
                    messageObject.setJumpType(1);
                    messageObject.setExtra(contentValues);
                }
                arrayList.add(messageObject);
            }
        }
        List<MessageObject> c = c(arrayList);
        LogUtil.d("TradeUtils", "messageObjects.size:", Integer.valueOf(c.size()));
        return c;
    }

    private static AssetMessageTemple d(String str) {
        AssetMessageTemple assetMessageTemple = new AssetMessageTemple();
        try {
            return (AssetMessageTemple) new Gson().fromJson(str, AssetMessageTemple.class);
        } catch (JsonSyntaxException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("TradeUtils", "getAssetMessageTemple gson exception");
            return assetMessageTemple;
        }
    }

    private static String b(int i) {
        if (i == 3) {
            return "#/CardCase";
        }
        if (i == 4) {
            return "#/Coupons";
        }
        LogUtil.d("TradeUtils", "skipToAsset type not match:", Integer.valueOf(i));
        return "";
    }

    private static String d(int i) {
        LogUtil.d("TradeUtils", "getTitle type:", Integer.valueOf(i));
        if (i == 3) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845028_res_0x7f021d64);
        }
        if (i == 4) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845027_res_0x7f021d63);
        }
        if (i == 5) {
            return BaseApplication.getContext().getString(R.string._2130847692_res_0x7f0227cc);
        }
        LogUtil.d("TradeUtils", "getTitle type not match:", Integer.valueOf(i));
        return "";
    }

    public static void a(int i, int i2) {
        LogUtil.d("TradeUtils", "enter skipToAsset type:", Integer.valueOf(i));
        String b = b(i);
        if (TextUtils.isEmpty(b)) {
            LogUtil.c("TradeUtils", "path is empty");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(b);
        bzs.e().loadH5ProApp(com.huawei.haf.application.BaseApplication.e(), "com.huawei.health.h5.assets", builder);
        b(i, i2);
    }

    private static List<MessageObject> c(List<MessageObject> list) {
        Collections.sort(list, new Comparator<MessageObject>() { // from class: njn.5
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(MessageObject messageObject, MessageObject messageObject2) {
                if (messageObject.getReceiveTime() > messageObject2.getReceiveTime()) {
                    return -1;
                }
                return messageObject.getReceiveTime() == messageObject2.getReceiveTime() ? 0 : 1;
            }
        });
        return list;
    }

    private static void b(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(c(i)));
        hashMap.put("activity", Integer.valueOf(i2));
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.MY_ASSET_ENTRANCE_2120121.value(), hashMap, 0);
    }

    private static int c(int i) {
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 1;
        }
        LogUtil.d("TradeUtils", "getTitle type not match:", Integer.valueOf(i));
        return 0;
    }

    public static List<DeviceBenefitQueryParam> e(String str, DeviceBenefitQueryParam.DeviceBenefitType deviceBenefitType) {
        DeviceBenefitQueryParam e;
        ArrayList arrayList = new ArrayList();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, str);
        if (koq.c(deviceList)) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo != null && (e = e(deviceInfo, deviceBenefitType, true)) != null) {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    public static DeviceBenefitQueryParam e(DeviceInfo deviceInfo, DeviceBenefitQueryParam.DeviceBenefitType deviceBenefitType, boolean z) {
        String str;
        String str2;
        if (deviceInfo == null) {
            com.huawei.hwlogsmodel.LogUtil.h("TradeUtils", "deviceInfoToDevBenefitQueryParam deviceInfo is null");
            return null;
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String deviceModel = deviceInfo.getDeviceModel();
        List<String> fieldList = deviceInfo.getFieldList();
        if (koq.b(fieldList) || fieldList.size() < 2) {
            str = "";
            str2 = "";
        } else {
            str = fieldList.get(0);
            str2 = fieldList.get(1);
        }
        return new DeviceBenefitQueryParam.Builder().setDeviceType(deviceModel).setDeviceSn(securityDeviceId).setDeviceNumber(str).setSalt(str2).setBenefitType(deviceBenefitType).setDeviceCategory(DeviceBenefitQueryParam.DeviceCategory.DEVICE_CATEGORY_WEAR.getCategory()).setNeedProductInfo(z).setDeviceInfo(deviceInfo).build();
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("TradeUtils", "currency is empty");
            return "";
        }
        if ("CNY".equals(str)) {
            return "¥";
        }
        try {
            return Currency.getInstance(str).getSymbol();
        } catch (IllegalArgumentException unused) {
            com.huawei.hwlogsmodel.LogUtil.h("TradeUtils", "Currency exception");
            return "";
        }
    }

    public static String a(String str, String str2, int i) {
        return BaseApplication.getContext().getString(i, BaseApplication.getContext().getResources().getString(R.string._2130844959_res_0x7f021d1f, e(str), str2));
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        return (!CommonUtil.bh() || CommonUtil.aw()) && !Utils.o() && LanguageUtil.m(context) && "CN".equals(LoginInit.getInstance(context).getAccountInfo(1010));
    }

    public static boolean a(List<DeviceInboxInfo> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<DeviceInboxInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isBenefitInfoValid()) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(List<DevicePerfPurchaseInfo> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<DevicePerfPurchaseInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isBenefitInfoValid()) {
                return true;
            }
        }
        return false;
    }

    public static String a(Context context) {
        LogUtil.d("TradeUtils", "getDeviceSnFromCert() in");
        String b = SharedPreferenceManager.b(context, "device_sn_module", "device_sn_from_cert");
        try {
        } catch (IOException | GeneralSecurityException e) {
            LogUtil.e("TradeUtils", "getDeviceSnByCert() exception ", e);
        }
        if (!TextUtils.isEmpty(b)) {
            return HwEncryptUtil.c(context).a(2, b);
        }
        if (!SecurityCertUtils.e()) {
            return "";
        }
        if (f15333a) {
            ReleaseLogUtil.e("R_TradeUtils", "the last time getCertificateChains failed, return empty");
            return "";
        }
        X509Certificate[] c = SecurityCertUtils.c(context, new int[]{1}, "device_sn_from_cert".getBytes("utf-8"));
        if (c == null) {
            f15333a = true;
            ReleaseLogUtil.c("R_TradeUtils", "getCertificateChains failed, not get certificate chains next time");
            return "";
        }
        if (c.length != 4) {
            ReleaseLogUtil.c("R_TradeUtils", "getCertificateChains length is error, return empty");
            return "";
        }
        for (X509Certificate x509Certificate : c) {
            byte[] extensionValue = x509Certificate.getExtensionValue("1.3.6.1.4.1.11129.2.1.17");
            if (extensionValue != null && extensionValue.length > 0) {
                b = d(extensionValue);
            }
        }
        if (!TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(context, "device_sn_module", "device_sn_from_cert", HwEncryptUtil.c(context).b(2, b), (StorageParams) null);
        }
        return b;
    }

    private static String d(byte[] bArr) {
        try {
            try {
                ASN1Sequence e = e(bArr);
                if (e == null) {
                    return null;
                }
                ASN1Sequence objectAt = e.getObjectAt(7);
                if (!(objectAt instanceof ASN1Sequence)) {
                    return null;
                }
                byte[] bArr2 = null;
                for (ASN1TaggedObject aSN1TaggedObject : objectAt.toArray()) {
                    if (aSN1TaggedObject instanceof ASN1TaggedObject) {
                        ASN1TaggedObject aSN1TaggedObject2 = aSN1TaggedObject;
                        if (aSN1TaggedObject2.getTagNo() == 713) {
                            try {
                                bArr2 = d((ASN1Encodable) aSN1TaggedObject2.getObject());
                            } catch (NoSuchMethodError unused) {
                                ReleaseLogUtil.c("R_TradeUtils", "extractDeviceInfoFromCert() NoSuchMethodError getObject");
                                bArr2 = d((ASN1Encodable) aSN1TaggedObject2.getBaseUniversal(aSN1TaggedObject2.isExplicit(), 4));
                            }
                        }
                    }
                }
                if (bArr2 == null || bArr2.length <= 0) {
                    return null;
                }
                return new String(bArr2, "utf-8");
            } catch (NoSuchMethodError e2) {
                e = e2;
                ReleaseLogUtil.c("R_TradeUtils", "extractDeviceInfoFromCert() exception ", e.getClass().getSimpleName());
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            ReleaseLogUtil.c("R_TradeUtils", "extractDeviceInfoFromCert() exception ", e.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable, org.bouncycastle.asn1.ASN1InputStream] */
    private static ASN1Sequence e(byte[] bArr) {
        Closeable closeable;
        Closeable closeable2;
        Closeable closeable3;
        if (bArr != null) {
            ?? length = bArr.length;
            try {
                if (length > 0) {
                    try {
                        length = new ASN1InputStream(bArr);
                    } catch (IOException e) {
                        e = e;
                        length = 0;
                        closeable2 = null;
                    } catch (Throwable th) {
                        th = th;
                        closeable = null;
                        IoUtils.e(r0);
                        IoUtils.e(closeable);
                        throw th;
                    }
                    try {
                        ASN1OctetString readObject = length.readObject();
                        if (readObject instanceof ASN1OctetString) {
                            closeable2 = new ASN1InputStream(readObject.getOctets());
                            try {
                                Closeable readObject2 = closeable2.readObject();
                                closeable3 = readObject2 instanceof ASN1Sequence ? (ASN1Sequence) readObject2 : null;
                                r0 = closeable2;
                            } catch (IOException e2) {
                                e = e2;
                                LogUtil.e("TradeUtils", "extractAttestationSequence() exception ", e);
                                IoUtils.e((Closeable) length);
                                IoUtils.e(closeable2);
                                return null;
                            }
                        } else {
                            closeable3 = null;
                        }
                        IoUtils.e((Closeable) length);
                        IoUtils.e(r0);
                        return closeable3;
                    } catch (IOException e3) {
                        e = e3;
                        closeable2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = null;
                        r0 = length;
                        IoUtils.e(r0);
                        IoUtils.e(closeable);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    private static byte[] d(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable == null) {
            return new byte[0];
        }
        if (aSN1Encodable instanceof ASN1OctetString) {
            return ((ASN1OctetString) aSN1Encodable).getOctets();
        }
        LogUtil.e("TradeUtils", "getOctetStringFromAsn1() parse octet string failed");
        return new byte[0];
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("TradeUtils", "changeDotToComma content is empty");
            return str;
        }
        return str.replaceAll("\\.", ",");
    }

    public static void cvi_(View view, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.d("TradeUtils", "back key pressed");
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        if (tradeViewApi != null && view != null) {
            tradeViewApi.backIntercept(view, iBaseResponseCallback);
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "trade view is null: ";
        objArr[1] = Boolean.valueOf(tradeViewApi == null);
        objArr[2] = "purchase view is null ";
        objArr[3] = Boolean.valueOf(view == null);
        LogUtil.d("TradeUtils", objArr);
        iBaseResponseCallback.d(0, null);
    }
}
