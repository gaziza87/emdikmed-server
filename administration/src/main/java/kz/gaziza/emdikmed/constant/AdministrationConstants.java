package kz.gaziza.emdikmed.constant;


import java.util.ArrayList;
import java.util.List;

public class AdministrationConstants {
    //general
    public final static String DEFAULT_ORG = "ROOT_ORG";
    public final static String SORT_DIRECTION_ASC = "asc";
    public final static String SORT_DIRECTION_DESC = "desc";
    public final static int DEFAUT_PAGE_NUMBER = 0;
    public final static int DEFAUT_PAGE_NUMBER_1 = 1;
    public final static int DEFAUT_PAGE_SIZE = 10;
    public final static String STATE_FIELD_NAME = "state";

    // field names
    public final static String ID_FIELD_NAME = "id";

    public final static String CODE_FIELD_NAME = "code";

    public final static String LAST_MODIFIED_DATE_FIELD_NAME = "lastModifiedDate";

    public final static String LAST_MODIFIED_BY_FIELD_NAME = "lastModifiedBy";

    public final static String CREATED_DATE_FIELD_NAME = "createdDate";

    public final static String CREATED_BY_FIELD_NAME = "createdBy";

    public final static String SYS_REG_DATE_BY_FIELD_NAME = "sysRegDate";

    // field values
    public final static int STATUS_ACTIVE = 1;

    public final static int STATUS_CHECKING = 2;

    public final static int STATUS_SKIP = 3;

    public final static int STATUS_BANNED = 4;

    public final static int STATUS_DELETED = 5;

    public final static int STATUS_HIDED = 7;


    public final static boolean DEFAULT_IS_ACTIVE = false;

    public static String SEPARATOR = ">";

    // Http Headers
    public final static String ERROR_MESSAGE = "errors";

    public enum ROLE {
        GLOBAL_ADMINISTRATOR,
        LOCAL_ADMINISTRATOR,
        MANAGER,
        DOCTOR,
        REGISTRANT,
        PATIENT
    }



    public static final String ACM_ID = "5d0b367f1127c10463339417";

    public static final String ANONYMOUS = "Anonymous";


    public static final String RECAPTCHA_SITE_KEY   = "6LfcPb8UAAAAAH2BMQWG3bc9tGUci7lSDdcxTrfN";

    public static final String RECAPTCHA_SECRET_KEY = "6LfcPb8UAAAAADyVqxhfM0DmkCJUSU_tmLu2MAQ1";

    public static final List<String> HOST_NAMES = new ArrayList<String>() {{
        add("localhost");
    }};

    // Quality points
    public static final int QP_SUPPORT = 1;
    public static final int QP_CLOSED  = 2;
    public static final int QP_3_DAYS  = 3;
    public static final int QP_HELP    = 4;

}
