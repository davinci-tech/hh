package defpackage;

import android.util.JsonReader;
import com.huawei.multisimsdk.odsa.response.OdsaResponseParam;
import java.io.IOException;
import java.io.StringReader;

/* loaded from: classes5.dex */
public class lpe {
    public static OdsaResponseParam a(String str) {
        char c;
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        OdsaResponseParam odsaResponseParam = new OdsaResponseParam();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                switch (nextName.hashCode()) {
                    case -1412968077:
                        if (nextName.equals("ap2006")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2662736:
                        if (nextName.equals("Vers")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 80988633:
                        if (nextName.equals("Token")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1597464377:
                        if (nextName.equals("AccessControl")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    bYT_(jsonReader, odsaResponseParam);
                } else if (c == 1) {
                    bYS_(jsonReader, odsaResponseParam);
                } else if (c == 2) {
                    bYM_(jsonReader, odsaResponseParam);
                } else if (c == 3) {
                    bYN_(jsonReader, odsaResponseParam);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (IOException | IllegalStateException unused) {
            loq.b("OdsaResolveJsonParser", "pullParserJson error");
        }
        return odsaResponseParam;
    }

    private static void bYP_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        jsonReader.beginArray();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("CompanionConfiguration")) {
                bYO_(jsonReader, odsaResponseParam);
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        jsonReader.endArray();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void bYO_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        char c;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            switch (nextName.hashCode()) {
                case -1267656985:
                    if (nextName.equals("ServiceStatus")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1196228216:
                    if (nextName.equals("PollingInterval")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 69479748:
                    if (nextName.equals("ICCID")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1273065846:
                    if (nextName.equals("DownloadInfo")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1434771443:
                    if (nextName.equals("CompanionDeviceService")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                odsaResponseParam.setServiceStatus(jsonReader.nextString());
            } else if (c == 1) {
                odsaResponseParam.setPollingInterval(jsonReader.nextInt());
                odsaResponseParam.setOperationType(lpr.b[5]);
            } else if (c == 2) {
                odsaResponseParam.setIccid(jsonReader.nextString());
                odsaResponseParam.setOperationType(lpr.b[3]);
            } else if (c == 3) {
                bYR_(jsonReader, odsaResponseParam);
            } else if (c == 4) {
                odsaResponseParam.setCompanionDeviceServices(jsonReader.nextString());
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    private static void bYR_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        char c;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            int hashCode = nextName.hashCode();
            if (hashCode == -1144770036) {
                if (nextName.equals("ProfileActivationCode")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 112594789) {
                if (hashCode == 1367384411 && nextName.equals("ProfileIccid")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (nextName.equals("ProfileSmdpAddress")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                odsaResponseParam.setProfileActivationCode(jsonReader.nextString());
            } else if (c == 1) {
                odsaResponseParam.setProfileSmdpAddress(jsonReader.nextString());
            } else if (c == 2) {
                odsaResponseParam.setProfileIccid(jsonReader.nextString());
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    private static void bYT_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("validity")) {
                odsaResponseParam.setValidity(String.valueOf(jsonReader.nextInt()));
            } else if (nextName.equals("version")) {
                odsaResponseParam.setVersion(String.valueOf(jsonReader.nextInt()));
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    private static void bYS_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("token")) {
                odsaResponseParam.setToken(jsonReader.nextString());
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    private static void bYM_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("default")) {
                bYQ_(jsonReader, odsaResponseParam);
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    private static void bYQ_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            if (nextName.equals("appId")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    odsaResponseParam.setAppId(jsonReader.nextString());
                }
                jsonReader.endArray();
            } else {
                loq.d("OdsaResolveJsonParser", "unexpected tag");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void bYN_(JsonReader jsonReader, OdsaResponseParam odsaResponseParam) throws IOException, IllegalStateException {
        char c;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            switch (nextName.hashCode()) {
                case -1818237768:
                    if (nextName.equals("CompanionAppEligibility")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1528725086:
                    if (nextName.equals("NotEnabledContentsType")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1150617277:
                    if (nextName.equals("NotEnabledUserData")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1070601321:
                    if (nextName.equals("SubscriptionServiceURL")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -29686708:
                    if (nextName.equals("SubscriptionServiceContentsType")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 66764730:
                    if (nextName.equals("SubscriptionResult")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 112594789:
                    if (nextName.equals("ProfileSmdpAddress")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 208953581:
                    if (nextName.equals("SubscriptionServiceUserData")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 249064521:
                    if (nextName.equals("CompanionConfigurations")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1439586372:
                    if (nextName.equals("OperationResult")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 1528241888:
                    if (nextName.equals("CompanionDeviceServices")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1631909249:
                    if (nextName.equals("NotEnabledURL")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    odsaResponseParam.setCompanionAppEligibility(String.valueOf(jsonReader.nextInt()));
                    odsaResponseParam.setOperationType(lpr.b[0]);
                    break;
                case 1:
                    odsaResponseParam.setNotEnabledContentsType(jsonReader.nextString());
                    break;
                case 2:
                    odsaResponseParam.setNotEnabledUserData(jsonReader.nextString());
                    break;
                case 3:
                    odsaResponseParam.setSubscriptionServiceUrl(jsonReader.nextString());
                    odsaResponseParam.setOperationType(lpr.b[1]);
                    break;
                case 4:
                    odsaResponseParam.setsubscriptionServiceContentsType(jsonReader.nextString());
                    break;
                case 5:
                    odsaResponseParam.setSubscriptionResult(jsonReader.nextInt());
                    break;
                case 6:
                    odsaResponseParam.setProfileSmdpAddress(jsonReader.nextString());
                    odsaResponseParam.setOperationType(lpr.b[1]);
                    break;
                case 7:
                    odsaResponseParam.setSubscriptionServiceUserData(jsonReader.nextString());
                    break;
                case '\b':
                    bYP_(jsonReader, odsaResponseParam);
                    break;
                case '\t':
                    odsaResponseParam.setOperationResult(jsonReader.nextInt());
                    break;
                case '\n':
                    odsaResponseParam.setCompanionDeviceServices(jsonReader.nextString());
                    break;
                case 11:
                    odsaResponseParam.setNotEnabledUrl(jsonReader.nextString());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
    }
}
