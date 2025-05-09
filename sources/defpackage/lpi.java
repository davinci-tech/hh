package defpackage;

import android.text.TextUtils;
import com.huawei.multisimsdk.odsa.response.OdsaResponseParam;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class lpi {
    public static OdsaResponseParam c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        loq.c("OdsaResolveXmlParser", "readResponseFromServer start.");
        if (xmlPullParser == null) {
            loq.b("OdsaResolveXmlParser", "readResponseFromServer parser is null.");
            return null;
        }
        int eventType = xmlPullParser.getEventType();
        OdsaResponseParam odsaResponseParam = new OdsaResponseParam();
        while (eventType != 1) {
            if (eventType == 0) {
                loq.d("OdsaResolveXmlParser", "readResponseFromServer start document");
            } else if (eventType == 2) {
                d(xmlPullParser, odsaResponseParam);
            }
            eventType = xmlPullParser.next();
        }
        loq.c("OdsaResolveXmlParser", odsaResponseParam.toString());
        return odsaResponseParam;
    }

    private static void d(XmlPullParser xmlPullParser, OdsaResponseParam odsaResponseParam) throws IOException, XmlPullParserException {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount < 2) {
            loq.b("OdsaResolveXmlParser", "other type");
            return;
        }
        loq.c("OdsaResolveXmlParser", "getEventInfo, count=" + attributeCount);
        String attributeValue = xmlPullParser.getAttributeValue(0);
        String attributeValue2 = xmlPullParser.getAttributeValue(1);
        loq.b("OdsaResolveXmlParser", "key=" + attributeValue + ",value=", attributeValue2);
        if ("version".equals(attributeValue)) {
            odsaResponseParam.setVersion(attributeValue2);
            return;
        }
        if ("validity".equals(attributeValue)) {
            odsaResponseParam.setValidity(attributeValue2);
            return;
        }
        if ("token".equals(attributeValue)) {
            odsaResponseParam.setToken(attributeValue2);
            if (TextUtils.isEmpty(attributeValue2)) {
                return;
            }
            lpu.a(attributeValue2);
            return;
        }
        if ("AppID".equals(attributeValue)) {
            odsaResponseParam.setAppId(attributeValue2);
            return;
        }
        if ("OperationResult".equals(attributeValue)) {
            odsaResponseParam.setOperationResult(Integer.parseInt(attributeValue2));
            return;
        }
        if ("CompanionAppEligibility".equals(attributeValue)) {
            odsaResponseParam.setCompanionAppEligibility(attributeValue2);
            odsaResponseParam.setOperationType(lpr.b[0]);
            return;
        }
        if ("CompanionDeviceServices".equals(attributeValue)) {
            odsaResponseParam.setCompanionDeviceServices(attributeValue2);
            return;
        }
        if ("NotEnabledURL".equals(attributeValue)) {
            odsaResponseParam.setNotEnabledUrl(attributeValue2);
            return;
        }
        if ("NotEnabledUserData".equals(attributeValue)) {
            odsaResponseParam.setNotEnabledUserData(attributeValue2);
            return;
        }
        if ("NotEnabledContentsType".equals(attributeValue)) {
            odsaResponseParam.setNotEnabledContentsType(attributeValue2);
            return;
        }
        if ("ICCID".equals(attributeValue)) {
            odsaResponseParam.setIccid(attributeValue2);
            odsaResponseParam.setOperationType(lpr.b[3]);
            return;
        }
        if ("CompanionDeviceService".equals(attributeValue)) {
            odsaResponseParam.setCompanionDeviceServices(attributeValue2);
            return;
        }
        if ("ServiceStatus".equals(attributeValue)) {
            odsaResponseParam.setServiceStatus(attributeValue2);
            return;
        }
        if ("SubscriptionServiceURL".equals(attributeValue)) {
            odsaResponseParam.setSubscriptionServiceUrl(attributeValue2);
            odsaResponseParam.setOperationType(lpr.b[1]);
            return;
        }
        if ("SubscriptionServiceUserData".equals(attributeValue)) {
            odsaResponseParam.setSubscriptionServiceUserData(attributeValue2);
            return;
        }
        if ("SubscriptionServiceContentsType".equals(attributeValue)) {
            odsaResponseParam.setsubscriptionServiceContentsType(attributeValue2);
            return;
        }
        if ("SubscriptionResult".equals(attributeValue)) {
            odsaResponseParam.setSubscriptionResult(Integer.parseInt(attributeValue2));
            return;
        }
        if ("ProfileSmdpAddress".equals(attributeValue)) {
            odsaResponseParam.setProfileSmdpAddress(attributeValue2);
            odsaResponseParam.setOperationType(lpr.b[1]);
        } else if ("ProfileActivationCode".equals(attributeValue)) {
            odsaResponseParam.setProfileActivationCode(attributeValue2);
        } else {
            if ("PollingInterval".equals(attributeValue)) {
                loq.c("OdsaResolveXmlParser", "OdsaHttpConnectionUtils.POLLING_INTERVAL equals name");
                odsaResponseParam.setPollingInterval(Integer.parseInt(attributeValue2));
                odsaResponseParam.setOperationType(lpr.b[5]);
                return;
            }
            loq.d("OdsaResolveXmlParser", "invalid type");
        }
    }
}
