package cn.smallbun.screw.core.query.db2.model;

import cn.smallbun.screw.core.mapping.MappingField;
import cn.smallbun.screw.core.metadata.Table;
import lombok.Data;

/**
 * @author gavin
 * @desc
 * @date 2020-07-09
 */
@Data
public class DB2TableModel implements Table {

    /**
     * TABLE_CAT
     */
    @MappingField(value = "TABLE_CAT")
    private String tableCat;
    /**
     * 表名
     */
    //COLUMN_NAME
    //@MappingField(value = "NAME")
    //LABEL_NAME
    @MappingField(value = "TABLE_NAME")
    private String tableName;
    /**
     * 表模式
     */
    @MappingField(value = "TABLE_SCHEM")
    private String tableSchem;
    /**
     * 表类型
     */
    @MappingField(value = "TABLE_TYPE")
    private String tableType;
    /**
     * 备注
     */
    @MappingField(value = "REMARKS")
    private String remarks;
}
