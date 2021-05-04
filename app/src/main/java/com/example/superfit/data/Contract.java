package com.example.superfit.data;

import android.provider.BaseColumns;

/**
 * Класс-контракт для работы с БД. Предоставляет всю необходимяе данные
 */
public final class Contract {
    private Contract() {
    }

    public static final class UserEntry implements BaseColumns{
        public final static String TABLE_NAME = "user";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME = "username";
        public final static String COLUMN_CODE = "code";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_WEIGHT = "weight";
        public final static String COLUMN_HEIGHT = "height";
    }
}
