package com.groupmicrofin.moneycalc.moneycalculator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jignesh on 18-12-2017.
 */
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.LoanMasterEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.MeetingRegEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.GroupRegistrationEntry;
import com.groupmicrofin.moneycalc.moneycalculator.db.MoneyCalcDbContract.AccountRegistrationEntry;
public class MoneyCalcOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MoneyCalculator7.db";
    //public static final String DB_NAME=null;
    public static final int DB_VERSION = 1;

    public MoneyCalcOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        sqLiteDatabase.execSQL(GroupRegistrationEntry.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(AccountRegistrationEntry.SQL_CREATE_AC_TABLE);
        sqLiteDatabase.execSQL(MeetingRegEntry.SQL_CREATE_MEET_TABLE);
        sqLiteDatabase.execSQL(LoanMasterEntry.SQL_CREATE_LN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
