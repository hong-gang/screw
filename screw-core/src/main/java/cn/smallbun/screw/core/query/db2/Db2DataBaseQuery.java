/*
 * screw-core - 简洁好用的数据库表结构文档生成工具
 * Copyright © 2020 SanLi (qinggang.zuo@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cn.smallbun.screw.core.query.db2;

import cn.smallbun.screw.core.exception.QueryException;
import cn.smallbun.screw.core.mapping.Mapping;
import cn.smallbun.screw.core.metadata.Column;
import cn.smallbun.screw.core.metadata.Database;
import cn.smallbun.screw.core.metadata.ForeignKey;
import cn.smallbun.screw.core.metadata.PrimaryKey;
import cn.smallbun.screw.core.query.AbstractDatabaseQuery;
import cn.smallbun.screw.core.query.db2.model.*;
import cn.smallbun.screw.core.query.oracle.model.OraclePrimaryKeyModel;
import cn.smallbun.screw.core.util.Assert;
import cn.smallbun.screw.core.util.ExceptionUtils;
import cn.smallbun.screw.core.util.JdbcUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static cn.smallbun.screw.core.constant.DefaultConstants.PERCENT_SIGN;

/**
 * db2 数据库查询
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/18 14:00
 */
public class Db2DataBaseQuery extends AbstractDatabaseQuery {
    /**
     * 构造函数
     *
     * @param dataSource {@link DataSource}
     */
    public Db2DataBaseQuery(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 获取数据库
     *
     * @return {@link Database} 数据库信息
     */
    @Override
    public Database getDataBase() throws QueryException {
        //throw ExceptionUtils.mpe(NOT_SUPPORTED);
        DB2DatabaseModel model = new DB2DatabaseModel();
        model.setDatabase(getSchema());
        return model;
    }

    /**
     * 获取表信息
     *
     * @return {@link List} 所有表信息
     */
    @Override
    public List<DB2TableModel> getTables() {
        //throw ExceptionUtils.mpe(NOT_SUPPORTED);
        ResultSet resultSet = null;
        try {
            //查询
            resultSet = getMetaData().getTables(getCatalog(), getSchema(), PERCENT_SIGN,
                new String[] { "TABLE" });
            //映射
            List<DB2TableModel> list = Mapping.convertList(resultSet, DB2TableModel.class);
            //由于ORACLE 查询 REMARKS 非常耗费性能，所以这里使用自定义SQL查询
            //https://docs.oracle.com/en/database/oracle/oracle-database/20/jjdbc/performance-extensions.html#GUID-15865071-39F2-430F-9EDA-EB34D0B2D560
            //获取所有表 查询表名、说明
            /*
            String sql = "SELECT TABLE_NAME,COMMENTS AS REMARKS FROM USER_TAB_COMMENTS WHERE TABLE_TYPE = 'TABLE'";
            resultSet = prepareStatement(String.format(sql, getSchema())).executeQuery();
            List<OracleTableModel> inquires = Mapping.convertList(resultSet,
                    OracleTableModel.class);
            //处理备注信息
            list.forEach((DB2TableModel model) -> {
                //备注
                inquires.stream()
                        .filter(inquire -> model.getTableName().equals(inquire.getTableName()))
                        .forEachOrdered(inquire -> model.setRemarks(inquire.getRemarks()));
            });
            */
            return list;
        } catch (SQLException e) {
            throw ExceptionUtils.mpe(e);
        } finally {
            JdbcUtils.close(resultSet, this.connection);
        }
    }

    /**
     * 获取列信息
     *
     * @param table {@link String} 表名
     * @return {@link List} 表字段信息
     * @throws QueryException QueryException
     */
    @Override
    public List<DB2ColumnModel> getTableColumns(String table) throws QueryException {
        Assert.notEmpty(table, "Table name can not be empty!");
        //throw ExceptionUtils.mpe(NOT_SUPPORTED);
        ResultSet resultSet = null;
        try {
            //查询指定表的欄位
            resultSet = getMetaData().getColumns(getCatalog(), getSchema(), table, PERCENT_SIGN);
            //映射
            return Mapping.convertList(resultSet, DB2ColumnModel.class);
        } catch (SQLException e) {
            throw ExceptionUtils.mpe(e);
        } finally {
            JdbcUtils.close(resultSet);
        }
    }

    /**
     * 获取所有列信息
     *
     * @return {@link List} 表字段信息
     * @throws QueryException QueryException
     */
    @Override
    public List<? extends Column> getTableColumns() throws QueryException {

        return getTableColumns(PERCENT_SIGN);

        //throw ExceptionUtils.mpe(NOT_SUPPORTED);
    }

    /**
     * 根据表名获取主键
     *
     * @param table {@link String}
     * @return {@link List}
     * @throws QueryException QueryException
     */
    @Override
    public List<? extends PrimaryKey> getPrimaryKeys(String table) throws QueryException {

        ResultSet resultSet = null;
        try {
            //查询
            resultSet = getMetaData().getPrimaryKeys(getCatalog(), getSchema(), table);
            //映射
            return Mapping.convertList(resultSet, OraclePrimaryKeyModel.class);
        } catch (SQLException e) {
            throw ExceptionUtils.mpe(e);
        } finally {
            JdbcUtils.close(resultSet, this.connection);
        }
        //throw ExceptionUtils.mpe(NOT_SUPPORTED);
    }

    /**
     * 获取所有主键
     *
     * @return {@link List}
     * @throws QueryException QueryException
     */
    @Override
    public List<? extends PrimaryKey> getPrimaryKeys() throws QueryException {
        ResultSet resultSet = null;
        try {
            // 由于单条循环查询存在性能问题，所以这里通过自定义SQL查询数据库主键信息
            String sql = "SELECT TABLE_CAT, TABLE_SCHEM, TABLE_NAME, COLUMN_NAME, KEY_SEQ, PK_NAME "
                         + "FROM SYSIBM.SQLPRIMARYKEYS ORDER BY TABLE_NAME, KEY_SEQ";
            // 拼接参数
            resultSet = prepareStatement(String.format(sql, getDataBase().getDatabase()))
                .executeQuery();
            sql = null;
            return Mapping.convertList(resultSet, DB2PrimaryKeyModel.class);
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            JdbcUtils.close(resultSet);
        }
    }

    /**
     * 獲取外鍵列表
     *
     * @return {@link List}
     * @throws QueryException QueryException
     */
    public List<? extends ForeignKey> getForeignKeys(String table) throws QueryException {
        ResultSet resultSet = null;
        try {
            // 由于单条循环查询存在性能问题，所以这里通过自定义SQL查询数据库主键信息
            String sql = "SELECT TBNAME AS TABLE_NAME, RELNAME AS FK_NAME, REFTBNAME AS REF_TABLE_NAME, "
                         + "FKCOLNAMES AS REF_COLUMN_NAME FROM SYSIBM.SYSRELS WHERE TBNAME ='"
                         + table + "'";
            // 拼接参数
            resultSet = prepareStatement(String.format(sql, getDataBase().getDatabase()))
                .executeQuery();
            sql = null;
            return Mapping.convertList(resultSet, DB2ForeignKeyModel.class);
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            JdbcUtils.close(resultSet);
        }
    }

    //@Override
    /**
     * 獲取外鍵列表
     *
     * @return {@link List}
     * @throws QueryException QueryException
     */
    public List<? extends ForeignKey> getForeignKeys() throws QueryException {
        ResultSet resultSet = null;
        try {
            // 由于单条循环查询存在性能问题，所以这里通过自定义SQL查询数据库主键信息
            String sql = "SELECT TBNAME AS TABLE_NAME, RELNAME AS FK_NAME, REFTBNAME AS REF_TABLE_NAME, FKCOLNAMES AS REF_COLUMN_NAME FROM SYSIBM.SYSRELS";
            // 拼接参数
            resultSet = prepareStatement(String.format(sql, getDataBase().getDatabase()))
                .executeQuery();
            sql = null;
            return Mapping.convertList(resultSet, DB2ForeignKeyModel.class);
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            JdbcUtils.close(resultSet);
        }
    }

}
