package com.kukode.progem.gateway_mc_v3.utils.applicationproperties;

/**
 * A Class which is going to store attribute structure name to retrieve values from properties config file which is then going to be used in several places
 */
public final class TableAttributeNames {
    private TableAttributeNames() {
    }

    public static final String TABLE_USERS = "${table.users.name}";

    public static final String COLUMN_USERS_ID = "${table.users.email}";

    public static final String COLUMN_USERS_PASSWORD = "${table.users.password}";


}
