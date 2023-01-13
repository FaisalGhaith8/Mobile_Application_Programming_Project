package com.example.sell_it
import android.annotation.SuppressLint
import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap

class FavoriteProvider(): ContentProvider() {
    //public
    companion object {
        val PROVIDER_NAME = "com.example.sell_it.FavoriteProvider"
        val URL = "content://" + PROVIDER_NAME + "/favorite"
        val CONTENT_URI = Uri.parse(URL)
        val ORDER_ID = "order_id"
        val PHONE_NAME = "name"
        val PRICE = "price"
        private val ORDERS_PROJECTION_MAP: HashMap<String, String>? = null
        val NAME = 1
        val ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "Favorite"
        val ORDERS_TABLE_NAME = "favorite"
        val DATABASE_VERSION = 1
        val CREATE_DB_TABLE = "CREATE TABLE " + ORDERS_TABLE_NAME + " (order_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT NOT NULL, " +
                "price TEXT NOT NULL);"
        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        init
        {
            sUriMatcher.addURI(PROVIDER_NAME, "favorite", NAME);
            sUriMatcher.addURI(PROVIDER_NAME, "favorite/#", ID);
        }
    }
    private var db: SQLiteDatabase? = null  //private
    //internal class
    private class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name = '" + ORDERS_TABLE_NAME + "'");
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME)
            onCreate(db)
        }
    }

    //6 functions
    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)
        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.  */
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    @SuppressLint("SuspiciousIndentation")
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        /**
         * Add a new student record
         */
        val rowID = db!!.insert(ORDERS_TABLE_NAME, "", values)
        /**
         * If record is added successfully
         */
        //if (rowID > 0) {
        val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
        context!!.contentResolver.notifyChange(_uri, null)
        return _uri
        //}
        //throw SQLException("Failed to add a record into $uri")
    }

    override fun query(
        uri: Uri, projection: Array<String>?,
        selection: String?, selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = ORDERS_TABLE_NAME
        if (uriMatcher != null) {
            when (uriMatcher.match(uri)) {
                /* STUDENTS -> qb.projectionMap =
                    STUDENTS_PROJECTION_MAP */
                ID -> qb.appendWhere(ORDER_ID + "=" + uri.pathSegments[1])
                else -> {null
                }
            }
        }


        if (sortOrder == null || sortOrder === "") {
            /**
             * By default sort on student names
             */
            sortOrder = PHONE_NAME
        }
        val c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        /**
         * register to watch a content URI for changes  */
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            NAME -> count = db!!.delete(
                ORDERS_TABLE_NAME, selection,
                selectionArgs
            )
            ID -> {
                val id = uri.pathSegments[1]
                count = db!!.delete(
                    ORDERS_TABLE_NAME,
                    ORDER_ID + " = " + id +
                            if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "",
                    selectionArgs
                )
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?  ): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            NAME -> count = db!!.update(
                ORDERS_TABLE_NAME, values, selection,
                selectionArgs
            )
            ID -> count = db!!.update(
                ORDERS_TABLE_NAME,
                values,
                ORDER_ID + " = " + uri.pathSegments[1] + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""),
                selectionArgs
            )
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            NAME -> return "These info about the order's phone name"
            ID -> return "This info about specific order"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }
}