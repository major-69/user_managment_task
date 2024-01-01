package vasundhra.info.solution;

import static vasundhra.info.solution.DbConstants.COLUMN_DOB;
import static vasundhra.info.solution.DbConstants.COLUMN_EMAIL;
import static vasundhra.info.solution.DbConstants.COLUMN_GEN;
import static vasundhra.info.solution.DbConstants.COLUMN_ID;
import static vasundhra.info.solution.DbConstants.COLUMN_NAME;
import static vasundhra.info.solution.DbConstants.COLUMN_PASS;
import static vasundhra.info.solution.DbConstants.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NewDatabaseHelper extends SQLiteOpenHelper {

    public NewDatabaseHelper(@Nullable Context context) {
        super(context, "Vibhav", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + "datatable" + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASS + " TEXT," +
                COLUMN_GEN + " TEXT," +
                COLUMN_DOB + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            // Add the 'dob' column to the existing table
            db.execSQL("ALTER TABLE datatable ADD COLUMN dob TEXT");
        }

        db.execSQL("DROP TABLE IF EXISTS " + "datatable");
        onCreate(db);
    }


    public Long insertData(String name, String email, String password, String dob, String gender) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASS, password);
        contentValues.put(COLUMN_GEN, gender);
        contentValues.put(COLUMN_DOB, dob);

        SQLiteDatabase db = this.getWritableDatabase();
        Long result = db.insert("datatable", null, contentValues);
        db.close();
        return result;
    }

    @SuppressLint("Range")
    public UserModal getDataOfUser(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM datatable ",null);

        if(cursor.moveToFirst()){
            do{
                int userId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String pass = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String gen = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String dob = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
//                String  = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                Log.d("getDataOfUser","userId: "+userId+"email "+email);

            }while (cursor.moveToNext());
        }
        return new UserModal();
    }


    public boolean checkEmailExists(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM datatable WHERE email = ?",new String[]{email});
        boolean exists  = cursor.moveToFirst();
        cursor.close();
        db.close();

        return exists;
    }

    public boolean checkPassword(String email,String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean isCorrect = false;
        String selection = "email=?";
        String[] projection = {"pass"};
        String[] selectionArgs = {email};

        Cursor cursor = db.query("datatable", projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String storedPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASS));

            if (storedPass.equalsIgnoreCase(pass)){
                isCorrect = true;
            }
            cursor.close();

        }

        db.close();
        return isCorrect;
    }
    @SuppressLint("Range")
    public List<UserModal> getUsersByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "name",
                "email",
                "dob",
                "gen",
                "pass"
        };

        String selection = "email = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                "datatable",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<UserModal> userList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int userId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                String userName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String userEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String userDob = cursor.getString(cursor.getColumnIndex(COLUMN_DOB));
                String userGender = cursor.getString(cursor.getColumnIndex(COLUMN_GEN));
                String userPass = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                UserModal user = new UserModal(userId+"",userName,userGender,userEmail,userPass,userDob);
                userList.add(user);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return userList;
    }

    public int updateData(String name, String email, String password, String dob, String gender){

        boolean updateStatus = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASS, password);
        contentValues.put(COLUMN_GEN, gender);
        contentValues.put(COLUMN_DOB, dob);

        int update = db.update("datatable",contentValues,"email=?",new String[]{email});

        return update;
    }

}


