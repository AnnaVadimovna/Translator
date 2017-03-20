package av.translator.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import av.translator.model.entity.TranslationEntity;
import av.translator.model.entity.TranslationEntityDb;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "myappname.db";
    private static final int DATABASE_VERSION = 1;
    private TranslationDAO dao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            dao = new TranslationDAO(getConnectionSource(), TranslationEntityDb.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TranslationEntityDb.class);

        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer) {
        try {
            TableUtils.dropTable(connectionSource, TranslationEntityDb.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
        dao = null;
    }

    public TranslationDAO getDao() {
        return dao;
    }

    static class TranslationDAO extends BaseDaoImpl<TranslationEntityDb, Integer> {
        protected TranslationDAO(ConnectionSource connectionSource,
                                 Class<TranslationEntityDb> dataClass) throws SQLException {
            super(connectionSource, dataClass);
        }

        public List<TranslationEntityDb> getAllTranslations() throws SQLException {
            return this.queryForAll();
        }

        public void insertNew(TranslationEntity entity) {
            try {
                create(new TranslationEntityDb(entity));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}