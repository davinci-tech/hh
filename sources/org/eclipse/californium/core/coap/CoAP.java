package org.eclipse.californium.core.coap;

import defpackage.uxq;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public final class CoAP {
    public static final Charset d = StandardCharsets.UTF_8;
    public static final InetAddress c = new InetSocketAddress("224.0.1.187", 0).getAddress();

    /* renamed from: a, reason: collision with root package name */
    public static final InetAddress f15851a = new InetSocketAddress("[FF02::FD]", 0).getAddress();
    public static final InetAddress e = new InetSocketAddress("[FF05::FD]", 0).getAddress();
    private static final Map<String, Code> b = new HashMap();
    private static final Map<String, ResponseCode> g = new HashMap();

    public static boolean a(int i) {
        return i >= 1 && i <= 31;
    }

    public static boolean b(int i) {
        return i == 0;
    }

    public static int c(int i) {
        return (i & 224) >> 5;
    }

    public static int e(int i) {
        return i & 31;
    }

    public static boolean i(int i) {
        return i >= 64 && i <= 191;
    }

    public static String d(int i) {
        return c(c(i), e(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(int i, int i2) {
        return String.format("%d.%02d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static String e(String str) {
        if ("UDP".equalsIgnoreCase(str)) {
            return "coap";
        }
        if ("DTLS".equalsIgnoreCase(str)) {
            return "coaps";
        }
        if ("TCP".equalsIgnoreCase(str)) {
            return "coap+tcp";
        }
        if ("TLS".equalsIgnoreCase(str)) {
            return "coaps+tcp";
        }
        throw new IllegalArgumentException("Protocol " + str + " not supported!");
    }

    public static boolean a(String str) {
        return "TCP".equalsIgnoreCase(str) || "TLS".equalsIgnoreCase(str);
    }

    public static boolean c(String str) {
        return "coap".equalsIgnoreCase(str) || "coap+tcp".equalsIgnoreCase(str) || "coaps".equalsIgnoreCase(str) || "coaps+tcp".equalsIgnoreCase(str);
    }

    public static int b(String str) {
        if ("coap".equalsIgnoreCase(str)) {
            return 5683;
        }
        if ("coaps".equalsIgnoreCase(str)) {
            return 5684;
        }
        if ("coap+tcp".equalsIgnoreCase(str)) {
            return 5683;
        }
        if ("coaps+tcp".equalsIgnoreCase(str)) {
            return 5684;
        }
        throw new IllegalArgumentException("URI scheme '" + str + "' is not supported!");
    }

    public static String d(String str) {
        int indexOf = str.indexOf("://");
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return null;
    }

    public static String j(int i) {
        String str;
        String d2 = d(i);
        try {
            if (a(i)) {
                str = d2 + "/" + Code.valueOf(i).text;
            } else if (i(i)) {
                str = d2 + "/" + ResponseCode.valueOf(i).text;
            } else {
                if (!b(i)) {
                    return d2;
                }
                str = d2 + "/EMPTY";
            }
            return str;
        } catch (uxq unused) {
            return d2;
        }
    }

    public enum Type {
        CON(0),
        NON(1),
        ACK(2),
        RST(3);

        public final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type valueOf(int i) {
            if (i == 0) {
                return CON;
            }
            if (i == 1) {
                return NON;
            }
            if (i == 2) {
                return ACK;
            }
            if (i == 3) {
                return RST;
            }
            throw new IllegalArgumentException("Unknown CoAP type " + i);
        }
    }

    public enum CodeClass {
        REQUEST(0),
        SUCCESS_RESPONSE(2),
        ERROR_RESPONSE(4),
        SERVER_ERROR_RESPONSE(5),
        SIGNAL(7);

        public final int value;

        CodeClass(int i) {
            this.value = i;
        }

        public static CodeClass valueOf(int i) {
            if (i == 0) {
                return REQUEST;
            }
            if (i == 2) {
                return SUCCESS_RESPONSE;
            }
            if (i == 7) {
                return SIGNAL;
            }
            if (i == 4) {
                return ERROR_RESPONSE;
            }
            if (i == 5) {
                return SERVER_ERROR_RESPONSE;
            }
            throw new uxq(String.format("Unknown CoAP class code: %d", Integer.valueOf(i)));
        }
    }

    public enum Code {
        GET(1),
        POST(2),
        PUT(3),
        DELETE(4),
        FETCH(5),
        PATCH(6),
        IPATCH(7),
        CUSTOM_30(30);

        public final String text;
        public final int value;

        Code(int i) {
            this.value = i;
            String c = CoAP.c(CoAP.c(i), CoAP.e(i));
            this.text = c;
            CoAP.b.put(c, this);
        }

        public static Code valueOf(int i) {
            int c = CoAP.c(i);
            int e = CoAP.e(i);
            if (c > 0) {
                throw new uxq(String.format("Not a CoAP request code: %s", CoAP.c(c, e)));
            }
            if (e != 30) {
                switch (e) {
                    case 1:
                        return GET;
                    case 2:
                        return POST;
                    case 3:
                        return PUT;
                    case 4:
                        return DELETE;
                    case 5:
                        return FETCH;
                    case 6:
                        return PATCH;
                    case 7:
                        return IPATCH;
                    default:
                        throw new uxq(String.format("Unknown CoAP request code: %s", CoAP.c(c, e)));
                }
            }
            return CUSTOM_30;
        }

        public static Code valueOfText(String str) {
            return (Code) CoAP.b.get(str);
        }
    }

    public enum ResponseCode {
        _UNKNOWN_SUCCESS_CODE(CodeClass.SUCCESS_RESPONSE, 0),
        CREATED(CodeClass.SUCCESS_RESPONSE, 1),
        DELETED(CodeClass.SUCCESS_RESPONSE, 2),
        VALID(CodeClass.SUCCESS_RESPONSE, 3),
        CHANGED(CodeClass.SUCCESS_RESPONSE, 4),
        CONTENT(CodeClass.SUCCESS_RESPONSE, 5),
        CONTINUE(CodeClass.SUCCESS_RESPONSE, 31),
        BAD_REQUEST(CodeClass.ERROR_RESPONSE, 0),
        UNAUTHORIZED(CodeClass.ERROR_RESPONSE, 1),
        BAD_OPTION(CodeClass.ERROR_RESPONSE, 2),
        FORBIDDEN(CodeClass.ERROR_RESPONSE, 3),
        NOT_FOUND(CodeClass.ERROR_RESPONSE, 4),
        METHOD_NOT_ALLOWED(CodeClass.ERROR_RESPONSE, 5),
        NOT_ACCEPTABLE(CodeClass.ERROR_RESPONSE, 6),
        REQUEST_ENTITY_INCOMPLETE(CodeClass.ERROR_RESPONSE, 8),
        CONFLICT(CodeClass.ERROR_RESPONSE, 9),
        PRECONDITION_FAILED(CodeClass.ERROR_RESPONSE, 12),
        REQUEST_ENTITY_TOO_LARGE(CodeClass.ERROR_RESPONSE, 13),
        UNSUPPORTED_CONTENT_FORMAT(CodeClass.ERROR_RESPONSE, 15),
        UNPROCESSABLE_ENTITY(CodeClass.ERROR_RESPONSE, 22),
        TOO_MANY_REQUESTS(CodeClass.ERROR_RESPONSE, 29),
        INTERNAL_SERVER_ERROR(CodeClass.SERVER_ERROR_RESPONSE, 0),
        NOT_IMPLEMENTED(CodeClass.SERVER_ERROR_RESPONSE, 1),
        BAD_GATEWAY(CodeClass.SERVER_ERROR_RESPONSE, 2),
        SERVICE_UNAVAILABLE(CodeClass.SERVER_ERROR_RESPONSE, 3),
        GATEWAY_TIMEOUT(CodeClass.SERVER_ERROR_RESPONSE, 4),
        PROXY_NOT_SUPPORTED(CodeClass.SERVER_ERROR_RESPONSE, 5);

        public final int codeClass;
        public final int codeDetail;
        public final String text;
        public final int value;

        ResponseCode(CodeClass codeClass, int i) {
            this.codeClass = codeClass.value;
            this.codeDetail = i;
            this.value = (codeClass.value << 5) | i;
            String c = CoAP.c(codeClass.value, i);
            this.text = c;
            CoAP.g.put(c, this);
        }

        public boolean isSuccess() {
            return this.codeClass == CodeClass.SUCCESS_RESPONSE.value;
        }

        public boolean isClientError() {
            return this.codeClass == CodeClass.ERROR_RESPONSE.value;
        }

        public boolean isServerError() {
            return this.codeClass == CodeClass.SERVER_ERROR_RESPONSE.value;
        }

        public static ResponseCode valueOf(int i) {
            int c = CoAP.c(i);
            int e = CoAP.e(i);
            if (c == 2) {
                return valueOfSuccessCode(e);
            }
            if (c == 4) {
                return valueOfClientErrorCode(e);
            }
            if (c != 5) {
                throw new uxq(String.format("Not a CoAP response code: %s", CoAP.c(c, e)));
            }
            return valueOfServerErrorCode(e);
        }

        public static ResponseCode valueOfText(String str) {
            return (ResponseCode) CoAP.g.get(str);
        }

        private static ResponseCode valueOfSuccessCode(int i) {
            if (i == 1) {
                return CREATED;
            }
            if (i == 2) {
                return DELETED;
            }
            if (i == 3) {
                return VALID;
            }
            if (i == 4) {
                return CHANGED;
            }
            if (i == 5) {
                return CONTENT;
            }
            if (i == 31) {
                return CONTINUE;
            }
            return _UNKNOWN_SUCCESS_CODE;
        }

        private static ResponseCode valueOfClientErrorCode(int i) {
            if (i == 8) {
                return REQUEST_ENTITY_INCOMPLETE;
            }
            if (i == 9) {
                return CONFLICT;
            }
            if (i == 12) {
                return PRECONDITION_FAILED;
            }
            if (i == 13) {
                return REQUEST_ENTITY_TOO_LARGE;
            }
            if (i == 15) {
                return UNSUPPORTED_CONTENT_FORMAT;
            }
            if (i == 22) {
                return UNPROCESSABLE_ENTITY;
            }
            if (i != 29) {
                switch (i) {
                    case 0:
                        return BAD_REQUEST;
                    case 1:
                        return UNAUTHORIZED;
                    case 2:
                        return BAD_OPTION;
                    case 3:
                        return FORBIDDEN;
                    case 4:
                        return NOT_FOUND;
                    case 5:
                        return METHOD_NOT_ALLOWED;
                    case 6:
                        return NOT_ACCEPTABLE;
                    default:
                        return BAD_REQUEST;
                }
            }
            return TOO_MANY_REQUESTS;
        }

        private static ResponseCode valueOfServerErrorCode(int i) {
            if (i == 0) {
                return INTERNAL_SERVER_ERROR;
            }
            if (i == 1) {
                return NOT_IMPLEMENTED;
            }
            if (i == 2) {
                return BAD_GATEWAY;
            }
            if (i == 3) {
                return SERVICE_UNAVAILABLE;
            }
            if (i == 4) {
                return GATEWAY_TIMEOUT;
            }
            if (i == 5) {
                return PROXY_NOT_SUPPORTED;
            }
            return INTERNAL_SERVER_ERROR;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }
}
