package vasundhra.info.solution;

import static vasundhra.info.solution.DbConstants.COLUMN_DOB;
import static vasundhra.info.solution.DbConstants.COLUMN_EMAIL;
import static vasundhra.info.solution.DbConstants.COLUMN_GEN;
import static vasundhra.info.solution.DbConstants.COLUMN_ID;
import static vasundhra.info.solution.DbConstants.COLUMN_NAME;
import static vasundhra.info.solution.DbConstants.COLUMN_PASS;
import static vasundhra.info.solution.DbConstants.DATABASE_NAME;
import static vasundhra.info.solution.DbConstants.DATABASE_VERSION;
import static vasundhra.info.solution.DbConstants.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + "TEXT, " +
                COLUMN_EMAIL + "TEXT, " +
                COLUMN_PASS + "TEXT, " +
                COLUMN_GEN + "TEXT, " +
                COLUMN_DOB + "TEXT)";
        db.execSQL(query);

        Log.d("checkDatabase","database created with tableName");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }













}
