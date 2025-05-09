package com.huawei.hms.mlsdk.asr.engine;

/* loaded from: classes9.dex */
public class AsrConstants {
    public static int bufferSize = 1280;

    public interface AsrGrs {
        public static final String ASR_LONG_QUERY_LANGUAGE_URL = "/v1/asr/languages?serviceInterface=ASR_LONG";
        public static final String ASR_LONG_URL = "/v1/rasr/long";
        public static final String ASR_SHORT_QUERY_LANGUAGE_URL = "/v1/asr/languages?serviceInterface=ASR_SHORT";
        public static final String ASR_SHORT_URL = "/v1/rasr/short";
    }

    public interface RecognizerType {
        public static final int RECOGNIZER_LONG = 1;
    }

    public interface State {
        public static final int ABNORMAL_INTERUPTED = 41;
        public static final int INVALIDATE_TOKEN = 44;
        public static final int LISTENING = 1;
        public static final int MAX_DURATION_EXCEED = 4;
        public static final int NO_NETWORK = 7;
        public static final int NO_SOUND = 2;
        public static final int NO_SOUND_TIMES_EXCEED = 3;
        public static final int NO_UNDERSTAND = 6;
        public static final int OK_FINISHED = 8;
        public static final int RECOGNIZING = 5;
        public static final int SERVICE_RECONNECTED = 43;
        public static final int SERVICE_RECONNECTING = 42;
        public static final int SERVICE_UNAVAILABLE = 40;
        public static final int STARTING_SPEECH = 10;
        public static final int WAITING = 9;
    }
}
