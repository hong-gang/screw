package cn.smallbun.screw.core.query.db2.model;

import cn.smallbun.screw.core.mapping.MappingField;
import cn.smallbun.screw.core.metadata.ForeignKey;
import lombok.Data;

/**
 * @author gavin
 * @desc
 * @date 2020-09-05
 */
@Data
public class DB2ForeignKeyModel implements ForeignKey {

    /**
     * 表名
     */
    @MappingField(value = "TABLE_NAME")
    private String tableName;
    /**
     * fk name
     */
    @MappingField(value = "FK_NAME")
    private String fkName;
    /**
     * 表模式
     */
    @MappingField(value = "REF_TABLE_NAME")
    private String refTableName;
    /**
     * 列名
     */
    @MappingField(value = "REF_COLUMN_NAME")
    private String refColumnName;

}
