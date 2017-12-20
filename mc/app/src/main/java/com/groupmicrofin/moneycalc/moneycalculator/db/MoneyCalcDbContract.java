package com.groupmicrofin.moneycalc.moneycalculator.db;

/**
 * Created by jignesh on 18-12-2017.
 */

public class MoneyCalcDbContract {
    private static MoneyCalcDbContract moneyDb;

    private MoneyCalcDbContract() {
    }

    public static MoneyCalcDbContract getInstance() {
        if (moneyDb == null) {
            moneyDb = new MoneyCalcDbContract();

        }
        return moneyDb;

    }

    public static class GroupRegistrationEntry implements BaseColumns {

        public static final String TABLE_NAME = "society_master";

        public static final String COLMN_GROUP_REF_NO = "society_ref_no";
        public static final String COLMN_GROUP_NAME = "society_name";
        public static final String COLMN_GROUP_START_DATE = "society_start_dt";
        public static final String COLMN_GROUP_SHARE_AMT = "share_amount";
        public static final String COLMN_GROUP_INTREST_REST = "int_rate";
        public static final String COLMN_GROUP_SCHEDULE_FREQ = "schedule_frequency";
        public static final String COLMN_CREATED_DT_TM = "created_dttm";
        public static final String COLMN_LAST_UPDATED_DTTM = "last_updated_dttm";
        public static final String COLMN_USER = "user";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COLMN_GROUP_REF_NO + " TEXT, " +
                COLMN_GROUP_NAME + " TEXT NOT NULL, " +
                COLMN_GROUP_START_DATE + " TEXT, " +
                COLMN_GROUP_SHARE_AMT + " INTEGER NOT NULL, " +
                COLMN_GROUP_INTREST_REST + " NUMERIC NOT NULL, " +
                COLMN_GROUP_SCHEDULE_FREQ + " TEXT, " +
                COLMN_CREATED_DT_TM + " TEXT, " +
                COLMN_LAST_UPDATED_DTTM + " TEXT, " +
                COLMN_USER + " TEXT)";
    }

    public static class AccountRegistrationEntry implements BaseColumns {
        public static final String TABLE_NAME = "account_master";

        public static final String COLMN_GROUP_ID = "society_master_id";
        public static final String COLMN_SOC_AC_ID = "society_account_id";
        public static final String COLMN_MEMBER_NM = "member_name";
        public static final String COLMN_EMAIL = "email_id";
        public static final String COLMN_PHOTO_ID = "photo_id";
        public static final String COLMN_PHONE_NUM = "phone_num";
        public static final String COLMN_LAST_MEET_DT = "last_meeting_dt";
        public static final String COLMN_ALERT_DTTM = "alert_dttm";
        public static final String COLMN_USER = "user";

        public static final String SQL_CREATE_AC_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COLMN_GROUP_ID + " INTEGER NOT NULL, " +
                COLMN_SOC_AC_ID + " TEXT , " +
                COLMN_MEMBER_NM + " TEXT NOT NULL, " +
                COLMN_EMAIL + " TEXT ," +
                COLMN_PHOTO_ID + " NUMERIC NOT NULL, " +
                COLMN_PHONE_NUM + " TEXT, " +
                COLMN_LAST_MEET_DT + " TEXT, " +
                COLMN_ALERT_DTTM + " TEXT, " +
                COLMN_USER + " TEXT)";
    }
    public static class MeetingRegEntry implements BaseColumns{
        public static final String TABLE_NAME="meeting_nobook";

        public static final String COLMN_GROUP_ID="society_master_id";
        public static final String COLMN_SOC_AC_ID="account_master_id";
        public static final String COLMN_LN_DIS_AMT="loan_disburse";
        public static final String COLMN_PAID_AMT="paid_Amount";
        public static final String COLMN_MEETING_DT="meeting_dt";

        public static final String SQL_CREATE_MEET_TABLE="CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COLMN_GROUP_ID + " INTEGER NOT NULL, " +
                COLMN_SOC_AC_ID + " TEXT , " +
                COLMN_LN_DIS_AMT + " TEXT , " +
                COLMN_PAID_AMT + " TEXT ," +
                COLMN_MEETING_DT + " TEXT NOT NULL)";


    }
        public static class LoanMasterEntry implements BaseColumns{

        public static final String TABLE_NAME = "loan_master";

        public static final String COLMN_SOC_AC_ID= "account_master_id";
        public static final String COLMN_LN_DIS_AMT="loan_disbus_amt";
        public static final String COLMN_PENDING_PRI_LN="pending_princi_loan";
        public static final String COLMN_TOTAL_INT_PAID="total_int_paid";
        public static final String COLMN_CRE_DTTM="created_dttm";
        public static final String COLMN_UPDA_DTTM="updated_dttm";
        public static final String COLMN_AC_STATUS="account_status";
        public static final String COLMN_DISBUS_DT="disbursment_dt";
        public static final String COLMN_CLS_DT="close_dt";

        public static final String SQL_CREATE_LN_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLMN_SOC_AC_ID + " TEXT , " +
                COLMN_LN_DIS_AMT + " TEXT NOT NULL, " +
                COLMN_PENDING_PRI_LN + " TEXT ," +
                COLMN_TOTAL_INT_PAID + " NUMERIC NOT NULL, " +
                COLMN_CRE_DTTM + " TEXT, " +
                COLMN_UPDA_DTTM + " TEXT, " +
                COLMN_AC_STATUS + " TEXT, " +
                COLMN_CLS_DT + " TEXT, " +
                COLMN_DISBUS_DT + " TEXT)";
    }

    public static class LoanRecordBookEntry {

        public static final String TABLE_NAME ="loan_nobook";

        public static final String COLMN_LOAN_MASTER_ID="loan_master_id";
        public static final String COLMN_TRACTION_CODE="txn_code";
        public static final String COLMN_TXN_AMT="txn_amt";
        public static final String COLMN_MEET_DT="meeting_date";
        public static final String COLMN_CREAT_DTTM="created_dttm";

        public static final String SQL_CREATE_LN_BOOK_TABLE="CREATE TABLE " + TABLE_NAME + " (" +

                COLMN_LOAN_MASTER_ID + " TEXT , " +
                COLMN_TRACTION_CODE + " TEXT NOT NULL, " +
                COLMN_TXN_AMT + " TEXT ," +
                COLMN_MEET_DT + " NUMERIC NOT NULL, " +
                COLMN_CREAT_DTTM + " TEXT)";
    }
}
