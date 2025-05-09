package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class skp {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, String> f17094a;
    private List<a> b;

    public static class a {
        private String b;
        private int d;
    }

    static {
        HashMap hashMap = new HashMap(32);
        f17094a = hashMap;
        hashMap.put("ROOT_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        f17094a.put("ALBANIAN_ALPHA_INDEX", "A B C Ç D(1) DH(1) E Ë F G(1) GJ(1) H I J K L(1) LL(1) M N(1) NJ(1) O P Q R(1) RR(1) S SH(1) T(1) TH(1) U V X(1) XH(1) Y Z ZH");
        f17094a.put("ARMENIAN_ALPHA_INDEX", "Ա Բ Գ Դ Ե Զ Է(1) Ը(1) Թ Ժ Ի Լ Խ(1) Ծ(1) Կ Հ Ձ(1) Ղ(1) Ճ(1) Մ Յ(1) Ն(1) Շ Ո(1) Չ(1) Պ Ջ(1) Ռ(1) Ս Վ Տ(1) Ր(1) Ց Փ(1) Ք(1) Օ Ֆ");
        f17094a.put("ARABIC_ALPHA_INDEX", "ا ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي");
        f17094a.put("AZERBAIJANI_ALPHA_INDEX", "A B C(1) Ç(1) D E(1) Ə(1) F G(1) Ğ(1) H X I İ J K Q L M N O(1) Ö(1) P R S Ş T U(1) Ü(1) V Y Z W");
        f17094a.put("BELARUSIAN_ALPHA_INDEX", "А Б В Г Д Е Ё Ж З І(1) Й(1) К Л М Н О П Р С Т У Ў(1) Ф(1) Х Ц Ч Ш Ы(1) Ь(1) Э(1) Ю Я");
        f17094a.put("BENGALI_ALPHA_INDEX", "অ আ ই(1) ঈ(1) উ ঊ(1) ঋ(1) এ ঐ(1) ও(1) ঔ ক(1) খ(1) গ ঘ(1) ঙ(1) চ(1) ছ জ ঝ(1) ঞ(1) ট(1) ঠ ড(1) ঢ(1) ণ(1) ত থ দ(1) ধ(1) ন(1) প ফ(1) ব(1) ভ ম(1) য(1) র ল শ ষ(1) স(1) হ");
        f17094a.put("BOSNIAN_ALPHA_INDEX", "A B C(1) Č(1) Ć(1) D DŽ(2) Đ E F G H I J K L LJ M N NJ O P Q R S Š(2) T U V W X Y Z Ž(2)");
        f17094a.put("BULGARIAN_ALPHA_INDEX", "А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ю Я");
        f17094a.put("BURMESE_ALPHA_INDEX", "က ခ ဂ ဃ င စ ဆ ဇ ဈ ဉ ည ဋ(1) ဌ(1) ဍ ဎ ဏ တ(1) ထ(1) ဒ ဓ န ပ ဖ ဗ(1) ဘ(1) မ ယ(1) ရ(1) လ ဝ သ(1) ဟ(1) ဠ အ");
        f17094a.put("CROATIAN_ALPHA_INDEX", "A B C(1) Č(1) Ć(1) D DŽ(2) Đ E F G H I J K L LJ M N NJ O P Q R S Š(2) T U V W X Y Z Ž(2)");
        f17094a.put("CZECH_ALPHA_INDEX", "A B C Č(2) D E F G H CH I J K L M N O P Q R Ř(2) S Š(2) T U V W X Y Z Ž(2)");
        f17094a.put("DANISH_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å");
        f17094a.put("ESTONIAN_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S(1) Š(1) Z Ž T U V Õ Ä(1) Ö(1) Ü(1) X Y");
        f17094a.put("FINNISH_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å(1) Ä(1) Ö");
        f17094a.put("FILIPINO_ALPHA_INDEX", "A B C D E F G H I J K L M N Ñ NG O P Q R S T U V W X Y Z");
        f17094a.put("GEORGIAN_ALPHA_INDEX", "ა ბ გ დ ე ვ ზ თ ი კ ლ მ ნ ო პ(1) ჟ(1) რ ს ტ უ(1) ფ(1) ქ ღ(1) ყ(1) შ ჩ ც ძ წ(1) ჭ(1) ხ(1) ჯ ჰ");
        f17094a.put("GREEK_ALPHA_INDEX", "Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ Σ Τ Υ Φ Χ Ψ Ω");
        f17094a.put("GUJARATI_ALPHA_INDEX", "અ આ ઇ ઈ ઉ ઊ(1) એ(1) ઐ(1) ઓ ઔ ક ખ(1) ગ(1) ઘ ઙ(1) ચ(1) છ(1) જ ઝ(1) ઞ(1) ટ(1) ઠ ડ(1) ઢ(1) ણ(1) ત થ દ(1) ધ(1) ન(1) પ ફ(1) બ(1) ભ(1) મ ય ર(1) લ(1) વ(1) શ ષ(1) સ(1) હ(1) ળ");
        f17094a.put("HEBREW_ALPHA_INDEX", "א ב ג ד ה ו ז ח ט י כ ל מ נ ס ע פ צ ק ר ש ת");
        f17094a.put("HINDI_ALPHA_INDEX", "अ आ(1) इ(1) ई उ(1) ऊ(1) ऋ ए(1) ऐ(1) ओ औ(1) क(1) ख ग(1) घ(1) ङ च(1) छ(1) ज झ(1) ञ(1) ट ठ(1) ड(1) ढ ण(1) त(1) थ द(1) ध(1) न प(1) फ(1) ब भ(1) म(1) य र(1) ल(1) व श(1) ष(1) स(1) ह");
        f17094a.put("HUNGARIAN_ALPHA_INDEX", "A B C CS(2) D DZ(2) DZS(2) E F G(1) GY(1) H I J K L(1) LY(1) M N NY O(1) Ö(1) P Q R S SZ(2) T TY U(1) Ü(1) V W X Y Z ZS(2)");
        f17094a.put("ICELANDIC_ALPHA_INDEX", "A Á B D Ð E É F G H I(1) Í(1) J K L M N O(1) Ó(1) P R S T U(1) Ú(1) V X Y(1) Ý(1) Þ Æ Ö");
        f17094a.put("JAPANESE_ALPHA_INDEX", "あ か さ た な は ま や ら わ 他");
        f17094a.put("KAZAKH_ALPHA_INDEX", "А Ә Б В Г(1) Ғ(1) Д Е(1) Ё(1) Ж З И(1) Й(1) К Қ(1) Л(1) М Н(1) Ң(1) О Ө(1) П(1) Р(1) С Т У(1) Ұ(1) Ү(1) Ф Х(1) Һ(1) Ц(1) Ч Ш Щ(1) Ъ(1) Ы(1) І Ь(1) Э(1) Ю Я");
        f17094a.put("KYRGYZ_ALPHA_INDEX", "А Б В Г Д(1) Е(1) Ё(1) Ж З И(1) Й(1) К Л М Н(1) Ң(1) О Ө П Р С Т У Ү Ф(1) Х(1) Ц(1) Ч Ш Щ(1) Ъ(1) Ы(1) Ь Э Ю Я");
        f17094a.put("KHMER_ALPHA_INDEX", "ក ខ គ ឃ ង(1) ច(1) ឆ ជ(1) ឈ(1) ញ(1) ដ ឋ(1) ឌ(1) ឍ(1) ណ ត ថ ទ ធ ន ប ផ ព ភ ម យ រ ល វ ស ហ ឡ អ");
        f17094a.put("KANNADA_ALPHA_INDEX", "ಅ ಆ ಈ ಉ ಎ ಐ ಕ ಗ ಚ ಜ ತ ದ ಧ ನ ಪ ಫ ಬ ಭ ಮ ಯ ರ ಲ ವ ಶ ಷ ಸ ಹ");
        f17094a.put("KOREAN_ALPHA_INDEX", "ㄱ ㄴ ㄷ ㄹ ㅁ ㅂ ㅅ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ");
        f17094a.put("LAO_ALPHA_INDEX", "ກ ຂ ຄ ງ ຈ ສ ຊ ຍ ດ ຕ ຖ ທ ນ ບ ປ ຜ ຝ ພ ຟ ມ ຢ ຣ ລ ວ ຫ ອ ຮ");
        f17094a.put("LATVIAN_ALPHA_INDEX", "A B C Č D E F G(1) Ģ(1) H I J K(1) Ķ(1) L Ļ M N(1) Ņ(1) O P Q R S(1) Š(1) T U V W X Z Ž");
        f17094a.put("LITHUANIAN_ALPHA_INDEX", "A B C Č D E F G H I J K L M N O P R S Š T U V Z Ž");
        f17094a.put("MACEDONIAN_ALPHA_INDEX", "А Б В Г Д Ѓ Е Ж З Ѕ И Ј К Л(1) Љ(1) М Н(1) Њ(1) О П Р С Т(1) Ќ(1) У Ф Х Ц Ч Џ Ш");
        f17094a.put("MARATHI_ALPHA_INDEX", "अ आ इ ई उ ऊ ऋ(1) ए(1) ऐ(1) ओ(1) औ(1) अं अ: क ख ग घ(1) ङ(1) च(1) छ(1) ज(1) झ ञ ट ठ ड(1) ढ(1) ण(1) त(1) थ(1) द ध न प(1) फ(1) ब(1) भ(1) म(1) य र ल व(1) श(1) ष(1) स(1) ह(1) ळ क्ष ज्ञ");
        f17094a.put("NEPALI_ALPHA_INDEX", "अ आ इ(1) ई(1) उ(1) ऊ ऋ ए(1) ऐ(1) ओ(1) औ क ख(1) ग(1) घ(1) ङ च छ ज(1) झ(1) ञ(1) ट ठ ड ढ(1) ण(1) त(1) थ द(1) ध(1) न(1) प फ ब भ(1) म(1) य(1) र ल व(1) श(1) ष(1) स ह");
        f17094a.put("NORWEGIAN_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å");
        f17094a.put("PASHOTO_ALPHA_INDEX", "آ ا ب پ ت(1) ث(1) ج چ ح خ(1) د(1) ذ ر ز(1) ژ(1) س ش ص(1) ض(1) ط ظ ع غ ف(1) ق(1) ک گ ل م ن ه و ی");
        f17094a.put("PERSIAN_ALPHA_INDEX", "آ ا ب پ ت(1) ث(1) ج چ ح خ(1) د(1) ذ ر ز(1) ژ(1) س ش ص(1) ض(1) ط ظ ع غ ف(1) ق(1) ک گ ل م ن و ه ی");
        f17094a.put("POLISH_ALPHA_INDEX", "A Ą B C Ć D E Ę F G H I J K L Ł M N Ń O Ó P Q R S Ś T U V W X Y Z Ź Ż");
        f17094a.put("PUNJABI_ALPHA_INDEX", "ੳ ਅ(1) ੲ(1) ਸ(1) ਹ ਕ ਖ ਗ ਘ(1) ਙ(1) ਚ ਛ ਜ ਝ(1) ਞ(1) ਟ ਠ ਡ ਢ(1) ਣ(1) ਤ ਥ ਦ ਧ ਨ ਪ ਫ ਬ ਭ(1) ਮ(1) ਯ(1) ਰ ਲ ਵ ੜ");
        f17094a.put("ROMANIAN_ALPHA_INDEX", "A Ă(1) Â(1) B C D E F G H I(1) Î(1) J K L M N O P Q R S(1) Ș(1) T Ț U V W X Y Z");
        f17094a.put("RUSSIAN_ALPHA_INDEX", "А Б В Г Д Е Ж З И(1) Й(1) К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ(1) Ы(1) Э Ю Я");
        f17094a.put("SERBIAN_ALPHA_INDEX", "A B C Č(1) Ć(1) D Đ E F G H I J K L LJ M N NJ O P R S Š T U V Z Ž");
        f17094a.put("SINHALA_ALPHA_INDEX", "අ ආ(1) ඇ(1) ඈ(1) ඉ(1) ඊ උ(1) ඌ(1) ඍ(1) එ(1) ඒ ඓ(1) ඔ(1) ඕ(1) ඖ ක(1) ඛ(1) ග(1) ඝ ඞ(1) ඟ(1) ච(1) ඡ ජ(1) ඣ(1) ඥ(1) ඤ ට(1) ඨ(1) ඩ(1) ඪ ණ(1) ඬ(1) ත(1) ථ ද(1) ධ(1) න(1) ඳ ප(1) ඵ(1) බ(1) භ ම(1) ඹ(1) ය(1) ර ල(1) ව(1) ශ(1) ෂ ස(1) හ(1) ළ(1) ෆ");
        f17094a.put("SLOVAK_ALPHA_INDEX", "A Ä(2) B C Č D E F G H CH I J K L M N O Ô(2) P Q R S Š T U V W X Y Z Ž(2)");
        f17094a.put("SLOVENIAN_ALPHA_INDEX", "A B C(1) Č(1) Ć(1) D Đ E F G H I J K L M N O P Q R S Š T U V W X Y Z Ž(2)");
        f17094a.put("SPANISH_ALPHA_INDEX", "A B C D E F G H I J K L M N Ñ O P Q R S T U V W X Y Z");
        f17094a.put("SWAHILI_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        f17094a.put("AMHARIC_ALPHA_INDEX", "ሀ ለ ሐ መ ሠ ረ ሰ ሸ ቀ በ ተ ቸ ኀ ነ(1) ኘ(1) አ ከ(1) ኸ(1) ወ ዐ(1) ዘ(1) ዠ(1) የ ደ ጀ ገ ጠ(1) ጨ(1) ጰ ጸ(1) ፀ(1) ፈ ፐ");
        f17094a.put("MAORI_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
        f17094a.put("TELUGR_ALPHA_INDEX", "అ ఆ ఇ ఈ ఉ(1) ఊ(1) ఎ(1) ఏ(1) ఐ ఒ ఓ ఔ(1) అం(1) క(1) ఖ(1) గ ఘ చ ఛ జ ఝ(1) ట(1) ఠ(1) డ(1) ఢ(1) ణ త థ ద ధ న ప ఫ(1) బ(1) భ(1) మ(1) య ర ల వ(1) శ(1) ష(1) స(1) హ");
        f17094a.put("MALAYALAM_ALPHA_INDEX", "അ ആ ഇ(1) ഈ(1) ഉ(1) ഊ ഋ എ ഏ(1) ഐ(1) ഒ(1) ഓ ഔ ക ൿ(1) ഖ(1) ഗ(1) ഘ(1) ങ ച ഛ ജ(1) ഝ(1) ഞ(1) ട ഠ ഡ ഢ(1) ണ(1) ത(1) ഥ ദ ധ ന(1) പ(1) ഫ(1) ബ ഭ മ യ(1) ര(1) ല(1) വ ശ ഷ സ(1) ഹ(1) ള(1) ഴ റ");
        f17094a.put("ODIA_ALPHA_INDEX", "ଅ ଆ ଇ(1) ଈ(1) ଉ(1) ଊ ଋ ଏ ଐ(1) ଓ(1) ଔ(1) କ ଖ ଗ ଘ(1) ଙ(1) ଚ(1) ଛ ଜ ଝ ଞ(1) ଟ(1) ଠ(1) ଡ ଢ ଣ ତ(1) ଥ(1) ଦ(1) ଧ ନ ପ ଫ(1) ବ(1) ଭ(1) ମ ଯ ର ଲ(1) ଶ(1) ଷ(1) ସ ହ");
        f17094a.put("ASSAMESE_ALPHA_INDEX", "অ আ ই(1) ঈ(1) উ(1) ঊ ঋ এ ঐ(1) ও(1) ঔ(1) ক খ গ ঘ(1) ঙ(1) চ(1) ছ জ ঝ ঞ(1) ট(1) ঠ(1) ড ঢ ণ ত(1) থ(1) দ(1) ধ ন প ফ(1) ব(1) ভ(1) ম য ৰ ল(1) ৱ(1) শ(1) ষ স হ");
        f17094a.put("MAITHILI_ALPHA_INDEX", "अ आ इ(1) ई(1) उ(1) ऊ ए ऐ ओ(1) औ(1) क(1) ख ग घ ङ(1) च(1) छ(1) ज झ ञ ट(1) ठ(1) ड(1) ढ ण त थ(1) द(1) ध(1) न प फ ब(1) भ(1) म(1) य र ल व(1) श(1) ष(1) स ह");
        f17094a.put("MONGOLIAN_ALPHA_INDEX", "А Б В Г(1) Д(1) Е(1) Ж З И Й(1) К(1) Л(1) М(1) Н О Ө П(1) Р(1) С(1) Т У Ү Ф(1) Х(1) Ц(1) Ч Ш Щ Ъ(1) Ы(1) Ь(1) Э Ю Я");
        f17094a.put("SWEDISH_ALPHA_INDEX", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å Ä Ö");
        f17094a.put("TAIWAN_ALPHA_INDEX", "ㄅ ㄆ ㄇ ㄈ ㄉ ㄊ ㄋ ㄌ ㄍ ㄎ ㄏ ㄐ ㄑ ㄒ ㄓ ㄔ ㄕ ㄖ(1) ㄗ(1) ㄘ(1) ㄙ ㄚ(1) ㄛ(1) ㄜ(1) ㄝ(1) ㄞ ㄟ(1) ㄠ(1) ㄡ(1) ㄢ ㄣ(1) ㄤ(1) ㄥ(1) ㄦ(1) ㄧ ㄨ ㄩ");
        f17094a.put("TAMIL_ALPHA_INDEX", "அ ஆ இ ஈ உ ஊ எ ஏ ஐ ஒ ஓ ஔ க ச ஞ த ந ப ம ய ர ல வ ஜ ஷ ஹ ஸ்ரீ");
        f17094a.put("THAI_ALPHA_INDEX", "ก ข(1) ฃ(1) ค(1) ฅ ฆ(1) ง(1) จ(1) ฉ ช(1) ซ(1) ฌ(1) ญ ฎ(1) ฏ(1) ฐ(1) ฑ ฒ(1) ณ(1) ด(1) ต ถ(1) ท(1) ธ(1) น บ(1) ป(1) ผ ฝ(1) พ(1) ฟ ภ(1) ม(1) ย ร(1) ฤ(1) ล ฦ(1) ว(1) ศ ษ(1) ส(1) ห ฬ(1) อ(1) ฮ");
        f17094a.put("TELUGU_ALPHA_INDEX", "అ ఆ ఇ ఈ ఉ(1) ఊ(1) ఎ(1) ఏ(1) ఐ ఒ ఓ ఔ(1) అం(1) క(1) ఖ(1) గ ఘ చ ఛ జ ఝ(1) ట(1) ఠ(1) డ(1) ఢ(1) ణ త థ ద ధ న ప ఫ(1) బ(1) భ(1) మ(1) య ర ల వ(1) శ(1) ష(1) స(1) హ");
        f17094a.put("TIBETAN_ALPHA_INDEX", "ཀ ཁ ག ང ཅ ཆ ཇ ཉ ཏ ཐ ད ན པ ཕ བ མ ཙ ཚ ཛ ཝ ཞ ཟ འ ཡ ར ལ ཤ ས ཧ ཨ");
        f17094a.put("TRADITIONAL_CHINESE_ALPHA_INDEX", "1劃 2劃 3劃 4劃 5劃 6劃 7劃 8劃 9劃 10劃 11劃 12劃 13劃 14劃 15劃 16劃 17劃 18劃 19劃 20劃 21劃 22劃 23劃 24劃 25劃 26劃");
        f17094a.put("TURKISH_ALPHA_INDEX", "A B C Ç D E F G H I(1) İ(1) J K L M N O(1) Ö(1) P Q R S Ş T U(1) Ü(1) V W X Y Z");
        f17094a.put("UKRAINIAN_ALPHA_INDEX", "А Б В Г(1) Ґ(1) Д Е(1) Є(1) Ж З И І(1) Ї(1) Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш(1) Щ(1) Ю Я");
        f17094a.put("URDU_ALPHA_INDEX", "ا ب(1) پ(1) ت ٹ(1) ث(1) ج چ(1) ح(1) خ د(1) ڈ(1) ذ ر(1) ڑ(1) ز ژ(1) س(1) ش ص(1) ض(1) ط ظ(1) ع(1) غ ف(1) ق(1) ک گ(1) ل(1) م ن(1) و(1) ہ ھ ء ی ے");
        f17094a.put("UYGHUR_ALPHA_INDEX", "ا ە ب پ ت ج(1) چ(1) خ د ر ز ژ س ش غ ف ق ك گ ڭ ل م ن ھ و ۇ(1) ۆ(1) ۈ(1) ۋ ې(1) ى(1) ي");
        f17094a.put("UZBEK_ALPHA_INDEX", "A B D E F G H I J K L M N O P Q R S T U V X Y Z Oʻ Gʻ CH SH NG");
        f17094a.put("VIETNAMESE_ALPHA_INDEX", "A Ă(1) Â(1) B C D Đ E(1) Ê(1) F G H I J K L M N O(1) Ô(1) Ơ(1) P Q R S T U(1) Ư(1) V W X Y Z");
        f17094a.put("UYGUR_ALPHA_INDEX", "ا ە ب پ ت ج چ خ د ر ز ژ س ش غ ف ق ك گ ڭ ل م ن ھ و ۇ ۆ ۈ ۋ ې ى ي");
    }

    private skp(Locale locale) {
        this.b = new ArrayList(32);
        String language = locale.getLanguage();
        if (MLAsrConstants.LAN_ZH.equals(language) && "Hant".equals(locale.getScript())) {
            language = "TW".equals(locale.getCountry()) ? "zh_TW" : "zh_Hant";
        }
        if ("fa".equals(language) && "AF".equals(locale.getCountry())) {
            language = "fa_AF";
        }
        try {
            f17094a = c(Resources.getSystem().getAssets().open("LanguageIndexerFile.conf"));
        } catch (IOException unused) {
            Log.w("HwAlphaIndexRM", "Can not find the file");
        }
        if ("zh_Hant".equals(language)) {
            this.b = b("TRADITIONAL_CHINESE_ALPHA_INDEX");
        } else if ("zh_TW".equals(language)) {
            this.b = b("TRADITIONAL_CHINESE_ALPHA_INDEX");
        } else if ("fa_AF".equals(language)) {
            this.b = b("PASHOTO_ALPHA_INDEX");
        } else if ("tl".equals(language)) {
            this.b = b("FILIPINO_ALPHA_INDEX");
        } else if ("ug".equals(language)) {
            this.b = b("UYGUR_ALPHA_INDEX");
        } else {
            this.b = b(locale.getDisplayLanguage(Locale.ENGLISH).split(" ")[0].toUpperCase(Locale.ENGLISH) + "_ALPHA_INDEX");
        }
        if (this.b.isEmpty()) {
            this.b = b("ROOT_ALPHA_INDEX");
        }
    }

    public static List<String> b() {
        return new ArrayList(Arrays.asList("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ")));
    }

    public static List<String> b(int i, List<String> list) {
        int i2;
        if (list == null || list.size() < i || list.size() <= 0) {
            return list;
        }
        int size = list.size();
        int i3 = (i - 1) / 2;
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            i2 = size - 1;
            if (i5 * i3 >= i2) {
                break;
            }
            i4 = i5;
        }
        int i6 = i4 - 1;
        if (i4 * i3 != i2) {
            i4 = i6;
        }
        ArrayList arrayList = new ArrayList(32);
        arrayList.add(list.get(0));
        if (1 >= size) {
            Log.w("HwAlphaIndexRM", "populateBulletAlphaIndex: pos(1) is invalid");
            return arrayList;
        }
        int i7 = i4 + 1;
        int i8 = i2 - (i3 * i7);
        int i9 = 1;
        int i10 = i8;
        while (i10 > 0) {
            i10--;
            StringBuilder sb = new StringBuilder(32);
            int i11 = 0;
            while (i11 < i7 && i9 < size) {
                sb.append(list.get(i9));
                sb.append(' ');
                i11++;
                i9++;
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                arrayList.add(sb.toString());
            }
            if (i9 >= size) {
                Log.w("HwAlphaIndexRM", "populateBulletAlphaIndex: pos(2) is invalid");
                return arrayList;
            }
            arrayList.add(list.get(i9));
            i9++;
        }
        if (i9 < size) {
            return d(i3 - i8, i4, i9, list, arrayList);
        }
        Log.w("HwAlphaIndexRM", "populateBulletAlphaIndex: pos(3) is invalid");
        return arrayList;
    }

    public static List<String> c() {
        return b(18, new ArrayList(Arrays.asList("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" "))));
    }

    private static List<String> c(List<String> list) {
        ArrayList arrayList = new ArrayList(32);
        for (String str : list) {
            if (str.split(" ").length > 1) {
                arrayList.add("•");
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static List<String> d(int i, int i2, int i3, List<String> list, List<String> list2) {
        int size = list.size();
        while (i > 0) {
            i--;
            StringBuilder sb = new StringBuilder(32);
            int i4 = 0;
            while (i4 < i2 && i3 < size) {
                sb.append(list.get(i3));
                sb.append(' ');
                i4++;
                i3++;
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                list2.add(sb.toString());
            }
            if (i3 >= size) {
                Log.w("HwAlphaIndexRM", "populateBulletAlphaIndex: pos(4) is invalid");
                return list2;
            }
            list2.add(list.get(i3));
            i3++;
        }
        while (i3 < size) {
            list2.add(list.get(i3));
            i3++;
        }
        return list2;
    }

    public static skp d() {
        skp b;
        synchronized (skp.class) {
            b = b(Locale.getDefault());
        }
        return b;
    }

    public static List<String> e() {
        return c(c());
    }

    public List<String> a() {
        return b(18, d(this.b));
    }

    public List<String> h() {
        return c(i());
    }

    public List<String> i() {
        return g();
    }

    public List<String> j() {
        return c(a());
    }

    public static skp b(Locale locale) {
        skp skpVar;
        synchronized (skp.class) {
            skpVar = new skp(locale);
        }
        return skpVar;
    }

    private List<a> b(String str) {
        String str2 = f17094a.get(str);
        if (str2 == null) {
            return new ArrayList(0);
        }
        String[] split = str2.split(" ");
        ArrayList arrayList = new ArrayList(32);
        for (String str3 : split) {
            if (!TextUtils.isEmpty(str3.trim())) {
                String trim = str3.trim();
                a aVar = new a();
                int indexOf = trim.indexOf(Constants.LEFT_BRACKET_ONLY);
                if (indexOf != -1) {
                    aVar.b = trim.substring(0, indexOf);
                    aVar.d = Character.digit(trim.charAt(indexOf + 1), 10);
                } else {
                    aVar.b = trim;
                    aVar.d = 0;
                }
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:39:0x0089
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private java.util.Map<java.lang.String, java.lang.String> c(java.io.InputStream r12) {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r1 = 32
            r0.<init>(r1)
            java.lang.String r1 = "ROOT_ALPHA_INDEX"
            java.lang.String r2 = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
            r0.put(r1, r2)
            java.lang.String r1 = "Exception when trying to close BufferedReader."
            java.lang.String r2 = "Exception when trying to close InputStreamReader."
            java.lang.String r3 = "HwAlphaIndexRM"
            r4 = 0
            if (r12 != 0) goto L1d
            java.lang.String r12 = "file is null"
            android.util.Log.w(r3, r12)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            return r0
        L1d:
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            java.lang.String r6 = "utf-8"
            r5.<init>(r12, r6)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L74
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L75
            r6 = 2048(0x800, float:2.87E-42)
            r12.<init>(r5, r6)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L75
            r4 = 0
            r6 = r4
        L2d:
            java.lang.String r7 = r12.readLine()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            if (r7 == 0) goto L5f
            r8 = 1
            int r6 = r6 + r8
            r9 = 100000(0x186a0, float:1.4013E-40)
            if (r6 <= r9) goto L40
            java.lang.String r4 = "beyond max line number"
            android.util.Log.w(r3, r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            goto L5f
        L40:
            java.lang.String r9 = "#"
            boolean r9 = r7.startsWith(r9)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            if (r9 != 0) goto L2d
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r9 = ":"
            java.lang.String[] r7 = r7.split(r9)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            int r9 = r7.length     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r10 = 2
            if (r9 == r10) goto L57
            goto L2d
        L57:
            r9 = r7[r4]     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r7 = r7[r8]     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r0.put(r9, r7)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            goto L2d
        L5f:
            r5.close()     // Catch: java.io.IOException -> L63
            goto L66
        L63:
            android.util.Log.w(r3, r2)
        L66:
            r12.close()     // Catch: java.io.IOException -> L89
            goto L8c
        L6a:
            r0 = move-exception
            r4 = r12
            goto L8f
        L6d:
            r4 = r12
            goto L75
        L6f:
            r12 = move-exception
            goto L90
        L71:
            r12 = move-exception
            r0 = r4
            goto L92
        L74:
            r5 = r4
        L75:
            java.lang.String r12 = "Exception when parsing LANGUAGE_INDEXER_FILE."
            android.util.Log.w(r3, r12)     // Catch: java.lang.Throwable -> L8d
            if (r5 == 0) goto L83
            r5.close()     // Catch: java.io.IOException -> L80
            goto L83
        L80:
            android.util.Log.w(r3, r2)
        L83:
            if (r4 == 0) goto L8c
            r4.close()     // Catch: java.io.IOException -> L89
            goto L8c
        L89:
            android.util.Log.w(r3, r1)
        L8c:
            return r0
        L8d:
            r12 = move-exception
            r0 = r12
        L8f:
            r12 = r0
        L90:
            r0 = r4
            r4 = r5
        L92:
            if (r4 == 0) goto L9b
            r4.close()     // Catch: java.io.IOException -> L98
            goto L9b
        L98:
            android.util.Log.w(r3, r2)
        L9b:
            if (r0 == 0) goto La4
            r0.close()     // Catch: java.io.IOException -> La1
            goto La4
        La1:
            android.util.Log.w(r3, r1)
        La4:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.skp.c(java.io.InputStream):java.util.Map");
    }

    private List<String> g() {
        ArrayList arrayList = new ArrayList(32);
        StringBuilder sb = new StringBuilder(32);
        for (a aVar : this.b) {
            if (aVar.d == 0) {
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }
                arrayList.add(aVar.b);
            }
            if (aVar.d == 1) {
                sb.append(aVar.b);
                sb.append(' ');
            }
        }
        return arrayList;
    }

    private List<String> d(List<a> list) {
        ArrayList arrayList = new ArrayList(32);
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().b);
        }
        return arrayList;
    }
}
