package com.chinatelecom.operations.metricsservice.entity;
import com.chinatelecom.udp.core.dataaccess.ormapping.DataObject;
import com.chinatelecom.udp.core.dataaccess.ormapping.TableMapping;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

/**
 * @author 孙虎
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableMapping( tableName = "kpi_info")
public class KpiInfo extends DataObject implements Serializable {
    private static final long serialVersionUID = -7900813102119457976L;
    @ApiModelProperty(value = "指标编码", example = "11005000014300")
    private String kpi_code;
    @ApiModelProperty(value = "指标名称", example = "11005005143009")
    private String kpi_name;
    @ApiModelProperty(value = "指标状态", example = "110050450014300")
    private String state;
    @ApiModelProperty(value = "指标属性", example = "11005000014300")
    private String kpi_property;
    @ApiModelProperty(value = "加工频率", example = "11005000015300")
    private String proc_rate;
    @ApiModelProperty(value = "指标单位", example = "11005000016300")
    private String unit;
    @ApiModelProperty(value = "参考范围", example = "11005000017300")
    private String scope;
    @ApiModelProperty(value = "归属团队", example = "11005000018300")
    private String group;
    @ApiModelProperty(value = "指标说明", example = "11005000019300")
    private String kpi_desc;
    @ApiModelProperty(value = "指标取值类型", example = "11005000020300")
    private String kpi_value_type;


}
