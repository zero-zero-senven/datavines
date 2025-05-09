/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.datavines.connector.plugin;

import io.datavines.common.datasource.jdbc.entity.ColumnInfo;
import io.datavines.common.datasource.jdbc.entity.DatabaseInfo;
import io.datavines.common.datasource.jdbc.entity.TableColumnInfo;
import io.datavines.common.datasource.jdbc.entity.TableInfo;
import io.datavines.common.param.*;
import io.datavines.connector.api.Connector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenyuanbo
 * @DATE: 2024/2/5
 */
public class HdfsConnector implements Connector {

    protected static final String TABLE = "TABLE";

    protected static final String DATABASE = "DATABASE";

    @Override
    public List<String> keyProperties() {
        return Collections.emptyList();
    }

    @Override
    public ConnectorResponse getDatabases(GetDatabasesRequestParam param) throws SQLException {
        List<DatabaseInfo> databaseInfos = new ArrayList<>();
        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setName("default_database");
        databaseInfo.setType(DATABASE);
        databaseInfos.add(databaseInfo);
        return ConnectorResponse.builder().status(ConnectorResponse.Status.SUCCESS).result(databaseInfos).build();
    }

    @Override
    public ConnectorResponse getTables(GetTablesRequestParam param) throws SQLException {
        List<TableInfo> tableInfoInfos = new ArrayList<>();
        TableInfo tableInfo = new TableInfo();
        tableInfo.setName("default_table");
        tableInfo.setType(TABLE);
        tableInfoInfos.add(tableInfo);
        return ConnectorResponse.builder().status(ConnectorResponse.Status.SUCCESS).result(tableInfoInfos).build();
    }

    @Override
    public ConnectorResponse getColumns(GetColumnsRequestParam param) throws SQLException {
        ArrayList<ColumnInfo> columnInfos = new ArrayList<>();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName("id");
        columnInfo.setType("BIGINT");
        columnInfos.add(columnInfo);
        ColumnInfo columnInfo2 = new ColumnInfo();
        columnInfo2.setName("name");
        columnInfo2.setType("STRING");
        columnInfos.add(columnInfo2);

        List<String> primaryKeys = new ArrayList<>();
        primaryKeys.add("id");
        TableColumnInfo tableColumnInfo = new TableColumnInfo("default", primaryKeys, columnInfos);

        return ConnectorResponse.builder().status(ConnectorResponse.Status.SUCCESS).result(tableColumnInfo).build();
    }

    @Override
    public ConnectorResponse testConnect(TestConnectionRequestParam param) {
        return ConnectorResponse.builder().status(ConnectorResponse.Status.SUCCESS).result(true).build();
    }
}
