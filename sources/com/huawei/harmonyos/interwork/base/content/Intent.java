package com.huawei.harmonyos.interwork.base.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.harmonyos.interwork.base.bundle.ElementName;
import com.huawei.harmonyos.interwork.base.utils.Uri;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes3.dex */
public class Intent implements Parcelable {
    public static final String ACTION_BOOK_FLIGHT = "ability.intent.BOOK_FLIGHT";
    public static final String ACTION_BOOK_HOTEL = "ability.intent.BOOK_HOTEL";
    public static final String ACTION_BOOK_TRAIN_TICKET = "ability.intent.BOOK_TRAIN_TICKET";
    public static final String ACTION_BUNDLE_ADD = "action.bundle.add";
    public static final String ACTION_BUNDLE_REMOVE = "action.bundle.remove";
    public static final String ACTION_BUNDLE_UPDATE = "action.bundle.update";
    public static final String ACTION_BUY = "ability.intent.BUY";
    public static final String ACTION_BUY_TAKEOUT = "ability.intent.BUY_TAKEOUT";
    public static final String ACTION_HOME = "action.system.home";
    public static final String ACTION_LOCALE_CHANGED = "ability.intent.LOCALE_CHANGED";
    public static final String ACTION_ORDER_TAXI = "ability.intent.ORDER_TAXI";
    public static final String ACTION_PLAN_ROUTE = "ability.intent.PLAN_ROUTE";
    public static final String ACTION_PLAY = "action.system.play";
    public static final String ACTION_QUERY_ALMANC = "ability.intent.QUERY_ALMANC";
    public static final String ACTION_QUERY_CONSTELLATION_FORTUNE = "ability.intent.QUERY_CONSTELLATION_FORTUNE";
    public static final String ACTION_QUERY_ENCYCLOPEDIA = "ability.intent.QUERY_ENCYCLOPEDIA";
    public static final String ACTION_QUERY_JOKE = "ability.intent.QUERY_JOKE";
    public static final String ACTION_QUERY_LOGISTICS_INFO = "ability.intent.QUERY_LOGISTICS_INFO";
    public static final String ACTION_QUERY_NEWS = "ability.intent.QUERY_NEWS";
    public static final String ACTION_QUERY_POI_INFO = "ability.intent.QUERY_POI_INFO";
    public static final String ACTION_QUERY_RECIPE = "ability.intent.QUERY_RECIPE";
    public static final String ACTION_QUERY_SPORTS_INFO = "ability.intent.QUERY_SPORTS_INFO";
    public static final String ACTION_QUERY_STOCK_INFO = "ability.intent.QUERY_STOCK_INFO";
    public static final String ACTION_QUERY_TRAFFIC_RESTRICTION = "ability.intent.QUERY_TRAFFIC_RESTRICTION";
    public static final String ACTION_QUERY_TRAVELLING_GUIDELINE = "ability.intent.QUERY_TRAVELLING_GUIDELINE";
    public static final String ACTION_QUERY_WEATHER = "ability.intent.QUERY_WEATHER";
    public static final String ACTION_SEND_LOGISTICS = "ability.intent.SEND_LOGISTICS";
    public static final String ACTION_TRANSLATE_TEXT = "ability.intent.TRANSLATE_TEXT";
    public static final String ACTION_WATCH_VIDEO_CLIPS = "ability.intent.WATCH_VIDEO_CLIPS";
    public static final Parcelable.Creator<Intent> CREATOR = new Parcelable.Creator<Intent>() { // from class: com.huawei.harmonyos.interwork.base.content.Intent.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: Ai_, reason: merged with bridge method [inline-methods] */
        public final Intent createFromParcel(Parcel parcel) {
            return new Intent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Intent[] newArray(int i) {
            return new Intent[i];
        }
    };
    private static final int DEFAULT_STRING_BUILDER_LENGTH = 128;
    public static final String ENTITY_HOME = "entity.system.home";
    public static final String ENTITY_VIDEO = "entity.system.video";
    public static final int FLAG_ABILITYSLICE_FORWARD_RESULT = 128;
    public static final int FLAG_ABILITYSLICE_MULTI_DEVICE = 256;
    public static final int FLAG_ABILITY_CONTINUATION = 8;
    public static final int FLAG_ABILITY_FORM_ENABLED = 32;
    public static final int FLAG_ABILITY_FORWARD_RESULT = 4;
    public static final int FLAG_AUTH_PERSISTABLE_URI_PERMISSION = 64;
    public static final int FLAG_AUTH_READ_URI_PERMISSION = 1;
    public static final int FLAG_AUTH_WRITE_URI_PERMISSION = 2;
    public static final int FLAG_NOT_HARMONYOS_COMPONENT = 16;
    public static final int FLAG_START_FOREGROUND_ABILITY = 512;
    private static final int MAX_LIMITE_SIZE = 512000;
    private static final String MIME_TYPE = "mime-type";
    private static final String OCT_EQUALS_SIGN = "075";
    private static final String OCT_SEMICOLON = "073";
    private static final int VALUE_NULL = -1;
    private static final int VALUE_OBJECT = 1;
    private String action;
    private String bundleName;
    private ElementName element;
    private Set<String> entities;
    private int flags;
    private IntentParams parameters;
    private Uri uri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Intent() {
        this.flags = 0;
    }

    public Intent(Intent intent) {
        if (intent != null) {
            this.action = intent.action;
            this.flags = intent.flags;
            this.bundleName = intent.bundleName;
            if (intent.entities != null) {
                this.entities = new HashSet(intent.entities);
            }
            if (intent.element != null) {
                this.element = new ElementName(intent.element);
            }
            if (intent.parameters != null) {
                this.parameters = new IntentParams(intent.parameters);
            }
            Uri uri = intent.uri;
            if (uri != null) {
                this.uri = Uri.parse(uri.toString());
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.action);
        if (this.uri == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeString(this.uri.toString());
        }
        if (this.entities == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            String[] strArr = new String[this.entities.size()];
            this.entities.toArray(strArr);
            parcel.writeStringArray(strArr);
        }
        parcel.writeInt(this.flags);
        if (this.element == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeTypedObject(this.element, 0);
        }
        if (this.parameters == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeTypedObject(this.parameters, 0);
        }
        parcel.writeString(this.bundleName);
    }

    public Intent(Parcel parcel) {
        this.action = parcel.readString();
        this.uri = null;
        if (parcel.readInt() == 1) {
            this.uri = Uri.parse(parcel.readString());
        }
        this.entities = null;
        if (parcel.readInt() == 1) {
            this.entities = new HashSet(Arrays.asList(readStringArray(parcel)));
        }
        this.flags = parcel.readInt();
        this.element = null;
        if (parcel.readInt() == 1) {
            this.element = (ElementName) parcel.readTypedObject(ElementName.CREATOR);
        }
        this.parameters = null;
        if (parcel.readInt() == 1) {
            this.parameters = (IntentParams) parcel.readTypedObject(IntentParams.CREATOR);
        }
        this.bundleName = parcel.readString();
    }

    private String[] readStringArray(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0 || readInt > MAX_LIMITE_SIZE) {
            return new String[0];
        }
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            strArr[i] = parcel.readString();
        }
        return strArr;
    }

    private static boolean parseSingleParam(String str, String str2, String str3, Intent intent) {
        if ("Boolean".equals(str2)) {
            intent.setParam(str, Boolean.parseBoolean(str3));
        } else if ("Byte".equals(str2)) {
            intent.setParam(str, Byte.parseByte(str3));
        } else if ("Char".equals(str2)) {
            if (str3.length() != 1) {
                return false;
            }
            intent.setParam(str, str3.charAt(0));
        } else if ("Short".equals(str2)) {
            intent.setParam(str, Short.parseShort(str3));
        } else if ("Int".equals(str2)) {
            intent.setParam(str, Integer.parseInt(str3));
        } else if ("Long".equals(str2)) {
            intent.setParam(str, Long.parseLong(str3));
        } else if ("Float".equals(str2)) {
            intent.setParam(str, Float.parseFloat(str3));
        } else if ("Double".equals(str2)) {
            intent.setParam(str, Double.parseDouble(str3));
        } else if ("String".equals(str2)) {
            intent.setParam(str, str3);
        } else {
            if ("CharSequence".equals(str2)) {
                intent.setParam(str, (CharSequence) str3);
            }
            return false;
        }
        return true;
    }

    private static boolean parseArrayParam(String str, String str2, String str3, Intent intent) {
        if (str3.startsWith("[") && str3.endsWith("]")) {
            String[] split = str3.substring(1, str3.length() - 1).split(", ");
            try {
                if ("BooleanArray".equals(str2)) {
                    intent.setParam(str, convertToBooleanArray(split));
                } else if ("ByteArray".equals(str2)) {
                    intent.setParam(str, convertToByteArray(split));
                } else if ("CharArray".equals(str2)) {
                    int length = split.length;
                    char[] cArr = new char[length];
                    for (int i = 0; i < length; i++) {
                        if (split[i].length() != 1) {
                            return false;
                        }
                        cArr[i] = split[i].charAt(0);
                    }
                    intent.setParam(str, cArr);
                } else if ("ShortArray".equals(str2)) {
                    intent.setParam(str, convertToShortArray(split));
                } else if ("IntArray".equals(str2)) {
                    intent.setParam(str, convertToIntArray(split));
                } else if ("LongArray".equals(str2)) {
                    intent.setParam(str, convertToLongArray(split));
                } else if ("FloatArray".equals(str2)) {
                    intent.setParam(str, convertToFloatArray(split));
                } else if ("DoubleArray".equals(str2)) {
                    intent.setParam(str, convertToDoubleArray(split));
                } else if ("StringArray".equals(str2)) {
                    intent.setParam(str, split);
                } else if ("CharSequenceArray".equals(str2)) {
                    intent.setParam(str, (CharSequence[]) split);
                }
                return true;
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    private static boolean[] convertToBooleanArray(String[] strArr) {
        int length = strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = Boolean.parseBoolean(strArr[i].trim());
        }
        return zArr;
    }

    private static byte[] convertToByteArray(String[] strArr) {
        int length = strArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Byte.parseByte(strArr[i].trim());
        }
        return bArr;
    }

    private static short[] convertToShortArray(String[] strArr) {
        int length = strArr.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = Short.parseShort(strArr[i].trim());
        }
        return sArr;
    }

    private static int[] convertToIntArray(String[] strArr) {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.parseInt(strArr[i].trim());
        }
        return iArr;
    }

    private static long[] convertToLongArray(String[] strArr) {
        int length = strArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = Long.parseLong(strArr[i].trim());
        }
        return jArr;
    }

    private static float[] convertToFloatArray(String[] strArr) {
        int length = strArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = Float.parseFloat(strArr[i].trim());
        }
        return fArr;
    }

    private static double[] convertToDoubleArray(String[] strArr) {
        int length = strArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = Double.parseDouble(strArr[i].trim());
        }
        return dArr;
    }

    private static boolean parseParam(String str, Intent intent) {
        int i;
        int lastIndexOf = str.lastIndexOf(61);
        int lastIndexOf2 = str.substring(0, lastIndexOf).lastIndexOf(46);
        if (6 >= lastIndexOf2 || (i = lastIndexOf2 + 1) >= lastIndexOf) {
            return false;
        }
        String substring = str.substring(6, lastIndexOf2);
        String substring2 = str.substring(i, lastIndexOf);
        String substring3 = str.substring(lastIndexOf + 1);
        String decode = decode(substring);
        String trim = decode(substring3).trim();
        if (trim.isEmpty()) {
            return true;
        }
        if (substring2.endsWith("Array")) {
            return parseArrayParam(decode, substring2, trim, intent);
        }
        return parseSingleParam(decode, substring2, trim, intent);
    }

    private static ElementName getElementFromIntent(Intent intent) {
        ElementName element = intent.getElement();
        if (element != null) {
            return element;
        }
        ElementName elementName = new ElementName();
        intent.setElement(elementName);
        return elementName;
    }

    private static boolean parseUriInternal(String str, Intent intent) {
        if (str.trim().isEmpty()) {
            return true;
        }
        int indexOf = str.indexOf("=");
        if (indexOf < 0) {
            return false;
        }
        String trim = str.substring(0, indexOf).trim();
        String substring = str.substring(indexOf + 1);
        if (!substring.isEmpty()) {
            if ("action".equals(trim)) {
                intent.setAction(decode(substring));
            } else if ("uri".equals(trim)) {
                intent.setUri(Uri.parse(decode(substring)));
            } else if ("entity".equals(trim)) {
                intent.addEntity(decode(substring));
            } else if ("flag".equals(trim)) {
                try {
                    intent.setFlags(Integer.decode(substring).intValue());
                } catch (NumberFormatException unused) {
                    return false;
                }
            } else if ("device".equals(trim)) {
                getElementFromIntent(intent).setDeviceId(decode(substring));
            } else if ("bundle".equals(trim)) {
                getElementFromIntent(intent).setBundleName(decode(substring));
            } else if ("ability".equals(trim)) {
                getElementFromIntent(intent).setAbilityName(decode(substring));
            } else {
                if (!"package".equals(trim)) {
                    return false;
                }
                intent.setBundle(decode(substring));
            }
        }
        return !trim.startsWith("param.") || parseParam(str, intent);
    }

    public static Optional<Intent> parseUri(String str) {
        if (str == null) {
            return Optional.empty();
        }
        if (!str.startsWith("#Intent;") || !str.endsWith("end")) {
            return Optional.empty();
        }
        String[] split = str.split(";");
        Intent intent = new Intent();
        for (int i = 1; i < split.length - 1; i++) {
            if ("PICK".equals(split[i].trim())) {
                intent = new Intent();
            } else if (!parseUriInternal(split[i], intent)) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(intent);
    }

    private static String decode(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char charAt = str.charAt(i);
            if (charAt != '\\') {
                sb.append(charAt);
            } else {
                int i2 = i + 1;
                if (i2 >= str.length()) {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    break;
                }
                char charAt2 = str.charAt(i2);
                if (charAt2 == '\\') {
                    sb.append(charAt2);
                } else if (charAt2 == '0') {
                    if (str.regionMatches(i2, OCT_EQUALS_SIGN, 0, 3)) {
                        sb.append('=');
                    } else if (str.regionMatches(i2, OCT_SEMICOLON, 0, 3)) {
                        sb.append(';');
                    } else {
                        sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        sb.append(charAt2);
                    }
                    i += 4;
                } else {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    sb.append(charAt2);
                }
                i = i2;
            }
            i++;
        }
        return sb.toString();
    }

    private StringBuilder encode(String str) {
        if (str == null) {
            return new StringBuilder(0);
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                sb.append("\\\\");
            } else if (charAt == '=') {
                sb.append("\\075");
            } else if (charAt == ';') {
                sb.append("\\073");
            } else {
                sb.append(charAt);
            }
        }
        return sb;
    }

    private void getInternalElement(StringBuilder sb) {
        ElementName elementName = this.element;
        if (elementName == null) {
            return;
        }
        String deviceId = elementName.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            sb.append("device=");
            sb.append((CharSequence) encode(deviceId));
            sb.append(';');
        }
        String bundleName = this.element.getBundleName();
        if (!TextUtils.isEmpty(bundleName)) {
            sb.append("bundle=");
            sb.append((CharSequence) encode(bundleName));
            sb.append(';');
        }
        String abilityName = this.element.getAbilityName();
        if (TextUtils.isEmpty(abilityName)) {
            return;
        }
        sb.append("ability=");
        sb.append((CharSequence) encode(abilityName));
        sb.append(';');
    }

    private String[] getDataTypeAndValue(Object obj) {
        if (obj == null) {
            return new String[0];
        }
        if (obj.getClass().isArray()) {
            return getBaseArrayTypeAndValue(obj);
        }
        return getBaseTypeAndValue(obj);
    }

    private String[] getBaseTypeAndValue(Object obj) {
        String[] strArr = new String[2];
        if (obj instanceof Boolean) {
            strArr[0] = "Boolean";
            strArr[1] = obj.toString();
        } else if (obj instanceof Byte) {
            strArr[0] = "Byte";
            strArr[1] = obj.toString();
        } else if (obj instanceof Character) {
            strArr[0] = "Char";
            strArr[1] = obj.toString();
        } else if (obj instanceof Short) {
            strArr[0] = "Short";
            strArr[1] = obj.toString();
        } else if (obj instanceof Integer) {
            strArr[0] = "Int";
            strArr[1] = obj.toString();
        } else if (obj instanceof Long) {
            strArr[0] = "Long";
            strArr[1] = obj.toString();
        } else if (obj instanceof Float) {
            strArr[0] = "Float";
            strArr[1] = obj.toString();
        } else if (obj instanceof Double) {
            strArr[0] = "Double";
            strArr[1] = obj.toString();
        } else if (obj instanceof String) {
            strArr[0] = "String";
            strArr[1] = obj.toString();
        } else {
            if (!(obj instanceof CharSequence)) {
                return new String[0];
            }
            strArr[0] = "CharSequence";
            strArr[1] = obj.toString();
        }
        return strArr;
    }

    private String[] getBaseArrayTypeAndValue(Object obj) {
        String[] strArr = new String[2];
        if (obj instanceof boolean[]) {
            strArr[0] = "BooleanArray";
            strArr[1] = Arrays.toString((boolean[]) obj);
        } else if (obj instanceof byte[]) {
            strArr[0] = "ByteArray";
            strArr[1] = Arrays.toString((byte[]) obj);
        } else if (obj instanceof char[]) {
            strArr[0] = "CharArray";
            strArr[1] = Arrays.toString((char[]) obj);
        } else if (obj instanceof short[]) {
            strArr[0] = "ShortArray";
            strArr[1] = Arrays.toString((short[]) obj);
        } else if (obj instanceof int[]) {
            strArr[0] = "IntArray";
            strArr[1] = Arrays.toString((int[]) obj);
        } else if (obj instanceof long[]) {
            strArr[0] = "LongArray";
            strArr[1] = Arrays.toString((long[]) obj);
        } else if (obj instanceof float[]) {
            strArr[0] = "FloatArray";
            strArr[1] = Arrays.toString((float[]) obj);
        } else if (obj instanceof double[]) {
            strArr[0] = "DoubleArray";
            strArr[1] = Arrays.toString((double[]) obj);
        } else if (obj instanceof String[]) {
            strArr[0] = "StringArray";
            strArr[1] = Arrays.toString((String[]) obj);
        } else {
            if (!(obj instanceof CharSequence[])) {
                return new String[0];
            }
            strArr[0] = "CharSequenceArray";
            strArr[1] = Arrays.toString((CharSequence[]) obj);
        }
        return strArr;
    }

    private void toUriInner(StringBuilder sb) {
        if (!TextUtils.isEmpty(this.action)) {
            sb.append("action=");
            sb.append((CharSequence) encode(this.action));
            sb.append(';');
        }
        if (this.uri != null) {
            sb.append("uri=");
            sb.append((CharSequence) encode(this.uri.toString()));
            sb.append(';');
        }
        Set<String> set = this.entities;
        if (set != null) {
            for (String str : set) {
                if (!TextUtils.isEmpty(str)) {
                    sb.append("entity=");
                    sb.append((CharSequence) encode(str));
                    sb.append(';');
                }
            }
        }
        getInternalElement(sb);
        if (this.flags != 0) {
            sb.append("flag=0x");
            sb.append(Integer.toHexString(this.flags));
            sb.append(';');
        }
        if (!TextUtils.isEmpty(this.bundleName)) {
            sb.append("package=");
            sb.append((CharSequence) encode(this.bundleName));
            sb.append(';');
        }
        IntentParams intentParams = this.parameters;
        if (intentParams != null) {
            for (String str2 : intentParams.keySet()) {
                String[] dataTypeAndValue = getDataTypeAndValue(this.parameters.getParam(str2));
                if (dataTypeAndValue != null && dataTypeAndValue.length > 1) {
                    sb.append("param.");
                    sb.append((CharSequence) encode(str2));
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    sb.append(dataTypeAndValue[0]);
                    sb.append('=');
                    sb.append((CharSequence) encode(dataTypeAndValue[1]));
                    sb.append(';');
                }
            }
        }
    }

    public String toUri() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("#Intent;");
        toUriInner(sb);
        sb.append("end");
        return sb.toString();
    }

    public String getAction() {
        return this.action;
    }

    public Intent setAction(String str) {
        this.action = str;
        return this;
    }

    public Intent setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public Uri getUri() {
        return this.uri;
    }

    public Intent addEntity(String str) {
        if (this.entities == null) {
            this.entities = new HashSet();
        }
        this.entities.add(str);
        return this;
    }

    public void removeEntity(String str) {
        Set<String> set = this.entities;
        if (set != null) {
            set.remove(str);
            if (this.entities.size() == 0) {
                this.entities = null;
            }
        }
    }

    public boolean hasEntity(String str) {
        Set<String> set = this.entities;
        return set != null && set.contains(str);
    }

    public Set<String> getEntities() {
        return this.entities;
    }

    public int countEntities() {
        Set<String> set = this.entities;
        if (set != null) {
            return set.size();
        }
        return 0;
    }

    public Intent setFlags(int i) {
        this.flags = i;
        return this;
    }

    public Intent addFlags(int i) {
        this.flags = i | this.flags;
        return this;
    }

    public int getFlags() {
        return this.flags;
    }

    public void removeFlags(int i) {
        this.flags = (~i) & this.flags;
    }

    public ElementName getElement() {
        return this.element;
    }

    public Intent setElement(ElementName elementName) {
        this.element = elementName;
        return this;
    }

    public Intent setBundle(String str) {
        this.bundleName = str;
        return this;
    }

    public String getBundle() {
        return this.bundleName;
    }

    public Optional<String> getScheme() {
        Uri uri = this.uri;
        if (uri != null) {
            return Optional.ofNullable(uri.getScheme());
        }
        return Optional.empty();
    }

    public Intent setType(String str) {
        if (this.parameters == null) {
            this.parameters = new IntentParams();
        }
        this.parameters.setParam(MIME_TYPE, str);
        return this;
    }

    public Optional<String> getType() {
        IntentParams intentParams = this.parameters;
        if (intentParams != null) {
            Object param = intentParams.getParam(MIME_TYPE);
            return param instanceof String ? Optional.ofNullable((String) param) : Optional.empty();
        }
        return Optional.empty();
    }

    public Intent setParams(IntentParams intentParams) {
        if (intentParams == null) {
            intentParams = new IntentParams();
        }
        this.parameters.getParams().putAll(intentParams.getParams());
        return this;
    }

    public IntentParams getParams() {
        return this.parameters;
    }

    public boolean hasParameter(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return false;
        }
        return intentParams.hasParam(str);
    }

    public boolean getBooleanParam(String str, boolean z) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return z;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Boolean ? ((Boolean) param).booleanValue() : z;
    }

    public boolean[] getBooleanArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new boolean[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof boolean[] ? (boolean[]) param : new boolean[0];
    }

    public byte getByteParam(String str, byte b) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return b;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Byte ? ((Byte) param).byteValue() : b;
    }

    public byte[] getByteArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new byte[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof byte[] ? (byte[]) param : new byte[0];
    }

    public short getShortParam(String str, short s) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return s;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Short ? ((Short) param).shortValue() : s;
    }

    public short[] getShortArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new short[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof short[] ? (short[]) param : new short[0];
    }

    public char getCharParam(String str, char c) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return c;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Character ? ((Character) param).charValue() : c;
    }

    public char[] getCharArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new char[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof char[] ? (char[]) param : new char[0];
    }

    public int getIntParam(String str, int i) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return i;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Integer ? ((Integer) param).intValue() : i;
    }

    public int[] getIntArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new int[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof int[] ? (int[]) param : new int[0];
    }

    public long getLongParam(String str, long j) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return j;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Long ? ((Long) param).longValue() : j;
    }

    public long[] getLongArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new long[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof long[] ? (long[]) param : new long[0];
    }

    public float getFloatParam(String str, float f) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return f;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Float ? ((Float) param).floatValue() : f;
    }

    public float[] getFloatArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new float[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof float[] ? (float[]) param : new float[0];
    }

    public double getDoubleParam(String str, double d) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return d;
        }
        Object param = intentParams.getParam(str);
        return param instanceof Double ? ((Double) param).doubleValue() : d;
    }

    public double[] getDoubleArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new double[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof double[] ? (double[]) param : new double[0];
    }

    public Optional<String> getStringParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return Optional.empty();
        }
        Object param = intentParams.getParam(str);
        if (param instanceof String) {
            return Optional.ofNullable((String) param);
        }
        return Optional.empty();
    }

    public Optional<CharSequence> getCharSequenceParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return Optional.empty();
        }
        Object param = intentParams.getParam(str);
        if (param instanceof CharSequence) {
            return Optional.ofNullable((CharSequence) param);
        }
        return Optional.empty();
    }

    public CharSequence[] getCharSequenceArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new CharSequence[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof CharSequence[] ? (CharSequence[]) param : new CharSequence[0];
    }

    public String[] getStringArrayParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return new String[0];
        }
        Object param = intentParams.getParam(str);
        return param instanceof String[] ? (String[]) param : new String[0];
    }

    public Intent setParam(String str, boolean z) {
        return setParamInternal(str, Boolean.valueOf(z));
    }

    public Intent setParam(String str, boolean[] zArr) {
        return setParamInternal(str, zArr);
    }

    public Intent setParam(String str, byte b) {
        return setParamInternal(str, Byte.valueOf(b));
    }

    public Intent setParam(String str, byte[] bArr) {
        return setParamInternal(str, bArr);
    }

    public Intent setParam(String str, char c) {
        return setParamInternal(str, Character.valueOf(c));
    }

    public Intent setParam(String str, char[] cArr) {
        return setParamInternal(str, cArr);
    }

    public Intent setParam(String str, short s) {
        return setParamInternal(str, Short.valueOf(s));
    }

    public Intent setParam(String str, short[] sArr) {
        return setParamInternal(str, sArr);
    }

    public Intent setParam(String str, int i) {
        return setParamInternal(str, Integer.valueOf(i));
    }

    public Intent setParam(String str, int[] iArr) {
        return setParamInternal(str, iArr);
    }

    public Intent setParam(String str, long j) {
        return setParamInternal(str, Long.valueOf(j));
    }

    public Intent setParam(String str, long[] jArr) {
        return setParamInternal(str, jArr);
    }

    public Intent setParam(String str, float f) {
        return setParamInternal(str, Float.valueOf(f));
    }

    public Intent setParam(String str, float[] fArr) {
        return setParamInternal(str, fArr);
    }

    public Intent setParam(String str, double d) {
        return setParamInternal(str, Double.valueOf(d));
    }

    public Intent setParam(String str, double[] dArr) {
        return setParamInternal(str, dArr);
    }

    public Intent setParam(String str, String str2) {
        return setParamInternal(str, str2);
    }

    public Intent setParam(String str, String[] strArr) {
        return setParamInternal(str, strArr);
    }

    public Intent setParam(String str, CharSequence charSequence) {
        return setParamInternal(str, charSequence);
    }

    public Intent setParam(String str, CharSequence[] charSequenceArr) {
        return setParamInternal(str, charSequenceArr);
    }

    private <T> Intent setParamInternal(String str, T t) {
        if (this.parameters == null) {
            this.parameters = new IntentParams();
        }
        this.parameters.setParam(str, t);
        return this;
    }

    public void removeParam(String str) {
        IntentParams intentParams = this.parameters;
        if (intentParams == null) {
            return;
        }
        intentParams.remove(str);
        if (this.parameters.isEmpty()) {
            this.parameters = null;
        }
    }

    public Intent replaceParams(IntentParams intentParams) {
        this.parameters = intentParams == null ? null : new IntentParams(intentParams);
        return this;
    }

    public Intent replaceParams(Intent intent) {
        this.parameters = (intent == null || intent.getParams() == null) ? null : new IntentParams(intent.getParams());
        return this;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Intent)) {
            Intent intent = (Intent) obj;
            if (Objects.equals(this.action, intent.action) && Objects.equals(this.uri, intent.uri) && Objects.equals(this.bundleName, intent.bundleName) && Objects.equals(this.element, intent.element) && Objects.equals(this.entities, intent.entities)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.action;
        int hashCode = str != null ? str.hashCode() : 0;
        Uri uri = this.uri;
        if (uri != null) {
            hashCode += uri.hashCode();
        }
        ElementName elementName = this.element;
        if (elementName != null) {
            hashCode += elementName.hashCode();
        }
        Set<String> set = this.entities;
        if (set != null) {
            hashCode += set.hashCode();
        }
        String str2 = this.bundleName;
        return str2 != null ? hashCode + str2.hashCode() : hashCode;
    }
}
