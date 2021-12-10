package com.chinatelecom.operations.metricsservice.entity;

import com.chinatelecom.udp.core.dataaccess.ormapping.DataObject;
import com.chinatelecom.udp.core.dataaccess.ormapping.TableMapping;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

/**
 * @author 孙虎
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableMapping( tableName = "kpi_value")
public class KpiValue extends DataObject implements Serializable {
    private static final long serialVersionUID = -7900813102299457976L;

    @ApiModelProperty(value = "账期", example = "11005000014300")
    private Integer data_id;
    @ApiModelProperty(value = "数据周期", example = "11005000014300")
    private String data_type;
    @ApiModelProperty(value = "本地网", example = "11005000014300")
    private Integer latn_id;
    @ApiModelProperty(value = "指标编码", example = "11005000014300")
    private String kpi_code;
    @ApiModelProperty(value = "指标值", example = "11005000014300")
    private Double kpi_value;
    @ApiModelProperty(value = "状态", example = "11005000014300")
    private String state;
    @ApiModelProperty(value = "更新时间", example = "11005000014300")
    private Timestamp crt_date;
}
