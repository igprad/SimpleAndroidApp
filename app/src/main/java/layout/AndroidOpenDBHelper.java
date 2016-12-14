package layout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by Aldi on 21/09/2016.
 */

public class AndroidOpenDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "db_obat";
    public static final int DB_VERSION = 1;

    public static final String Table_Name="data_obat";
    public static final String Column_name_Nama="nama_obat";
    public static final String Column_name_Jenis="jenis_obat";
    public static final String Column_name_Harga="harga_obat";
    public static final String Column_name_Deskripsi="deskripsi";

    SQLiteDatabase sqliteDB;

    public AndroidOpenDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        sqliteDB=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery="create table if not exists "+Table_Name+" (" +
                BaseColumns._ID+" integer primary key autoincrement, " +
                Column_name_Nama+" text not null, "+
                Column_name_Jenis+" text not null, "+
                Column_name_Harga+" text not null, "+
                Column_name_Deskripsi+" text not null);";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Obat> getData(){
        ArrayList<Obat> userList = new ArrayList<>();

        Cursor cursor = sqliteDB.query(
                Table_Name,null,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String nama = cursor.getString(
                    cursor.getColumnIndex(Column_name_Nama));
            double harga = Double.parseDouble(cursor.getString(
                    cursor.getColumnIndex(Column_name_Harga)));
            String jenis = cursor.getString(
                    cursor.getColumnIndex(Column_name_Jenis));
            String deskripsi = cursor.getString(
                    cursor.getColumnIndex(Column_name_Deskripsi));
            Obat user = new Obat();
            user.setNama(nama);
            user.setJenis(jenis);
            user.setHarga(harga);
            user.setDeskripsi(deskripsi);

            userList.add(user);
        }
        return userList;
    }

    public ArrayList<String> ShowObat(){
        ArrayList<String> userList = new ArrayList<>();

        Cursor cursor = sqliteDB.query(
                Table_Name,null,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String u = cursor.getString(
                    cursor.getColumnIndex(Column_name_Nama));
            userList.add(u);
        }
        return userList;
    }

    public String ShowDetailObat(String nama_obat){
       String userList="";

        Cursor cursor = sqliteDB.query(
                Table_Name,null,"nama_obat =?",new String[]{nama_obat},null,null,null,null);
        while(cursor.moveToNext()){
            String u = "Nama Obat : "+cursor.getString(
                    cursor.getColumnIndex(Column_name_Nama))+"\n" +
                    "Jenis Obat : "+cursor.getString(
                    cursor.getColumnIndex(Column_name_Jenis))+"\n" +
                    "Harga Obat : Rp"+cursor.getString(
                    cursor.getColumnIndex(Column_name_Harga))+"\n" +
                    "Deskripsi Obat : "+cursor.getString(
                    cursor.getColumnIndex(Column_name_Deskripsi))+"\n";
            userList=u;
        }
        return userList;
    }

    public void insert(Obat user){

        ContentValues contentValues = new ContentValues();

        contentValues.put(Column_name_Nama,user.getNama());
        contentValues.put(Column_name_Jenis,user.getJenis());
        contentValues.put(Column_name_Harga,user.getHarga());
        contentValues.put(Column_name_Deskripsi,user.getDeskripsi());

        sqliteDB.insert(Table_Name,null,contentValues);

    }

    public void closedb(){
        sqliteDB.close();
    }
//
//    public void delete (User id){
//        String[] whereClauseArgument = new String[1];
//        whereClauseArgument[0] = id.getNPM();
//
//        sqliteDB.delete(Table_Name,Column_name_Nama+"=?",whereClauseArgument);
//        sqliteDB.close();
//    }
//
//    public void update(User id, String npm, String ipk,String fakultas, String Prodi){
//        ContentValues cv = new ContentValues();
//        cv.put(Column_name_Nama,npm);
//        cv.put(Column_name_Jenis,ipk);
//        cv.put(Column_name_Harga,fakultas);
//        cv.put(Column_name_Deskripsi,Prodi);
//        String[] whereClauseArgument = new String[1];
//        whereClauseArgument[0] = id.getNPM();
//        System.out.println("whereClauseArgument[0] is : "
//                +npm+" "+ipk+" "+fakultas+" "+Prodi+whereClauseArgument[0]);
//        sqliteDB.update(Table_Name,cv,Column_name_Nama+"=?",whereClauseArgument);
//        sqliteDB.close();
//    }
}
