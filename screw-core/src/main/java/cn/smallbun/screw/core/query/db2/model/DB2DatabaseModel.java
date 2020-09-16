package cn.smallbun.screw.core.query.db2.model;

import cn.smallbun.screw.core.metadata.Database;
import lombok.Data;

/**
 * @author gavin
 * @desc
 * @date 2020-07-09
 */
@Data
public class DB2DatabaseModel implements Database {

    /**
     * 数据库名称
     */
    private String database;
}
