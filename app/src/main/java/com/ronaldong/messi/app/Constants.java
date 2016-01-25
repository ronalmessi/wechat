package com.ronaldong.messi.app;

/**
 * Created by ronaldong on 2016/1/4.
 */
public class Constants {

    public static final String Base_URL = "http://ydxx.nbu.edu.cn/api/";
    public static final String COURSE_URL = Base_URL+"/appIndex";
    public static final String APP_MARKET_URL = Base_URL+"/apps/myApps";
    public static final String NETSUBJECT_URL ="apps://goToNetSubject";

    public static final String FANYA_APK_DOWNLOAD_URL ="http://115.238.128.214/filedownload/file/fanya.apk";
    public static final String FANYA_APK_LOGIN_URL = "cxFanya://cxFanya/login?username=%s&password=%s";

    public static final int XMPP_SERVER_PORT = 5222;
    public static final String XMPP_SERVER_NAME = "iclassserver";
    public static final String XMPP_SERVER_IP = "ydxx.nbu.edu.cn";
    public static final String XMPP_SERVER_DOMAIN = "ydxx.nbu.edu.cn";


    public static final String CHAT_EXTRAKEY_CONVERSATION_ID = "conversationId";
    public static final String XMPP_EXTRAKEY_MESSAGE_BODY = "msg_body";
    public static final String XMPP_EXTRAKEY_MESSAGE_PACKEDID = "packedID";
    public static final String XMPP_EXTRAKEY_MESSAGE_TO = "to";
    public static final String XMPP_EXTRAKEY_MESSAGE_FROM = "from";
    public static final String XMPP_EXTRAKEY_MESSAGE_OBJECT = "msg_object";


    public static final String GETUI_CLIENTID = "clientid";


    public static final String LOG_TAG = "LogTag";
    public static final String ACTION_SYNCDATA = "android.intent.action.SYNCDATA";
    public static final String ACTION_MESSAGE_RECEIVED = "android.intent.action.MESSAGE_RECEIVED";


    public static final String HAS_LAUNCHED = "hasLaunched";
    public static final String ACCESSTOKEN = "accesstoken";
    public static final String USER_ID = "userId";
    public static final String LOGIN_ID = "loginId";
    public static final String SEX = "sex";
    public static final String PASSWORD = "password";
    public static final String AVATAR_URL = "avatarURL";
    public static final String INTRO = "intro";


    public final static String MSG_TYPE_TEXT ="101";
    public final static String MSG_TYPE_Token = "601";

    public final static int MSG_STATE_SENDING = 0;//消息正在发送
    public final static int MSG_STATE_SENDING_SUCCESS = 1;//消息发送成功
    public final static int MSG_STATE_SENDING_FAIL = 2;//消息发送失败

    public final static int MSG_STATE_RECEIVED_UNREAD = 3;//消息已接收但未读
    public final static int MSG_STATE_RECEIVED_READ = 4;//消息已接受并阅读

}
