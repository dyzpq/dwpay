package response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 接口分页参数响应对象
 */
@Data
@ApiModel(value = "接口分页数据返回对象")
public class PageRes<T> implements Serializable {
    private static final long serialVersionUID = 6866124669522074236L;

    @ApiModelProperty(value = "总条数")
    private long total;

    @ApiModelProperty(value = "当前页码")
    private int currentPage = 1;

    @ApiModelProperty(value = "当前页多少数据")
    private int pageSize = 20;
    @ApiModelProperty(value = "hasNextPage")
    private boolean hasNextPage;

    @ApiModelProperty(value = "具体的数据对象")
    private List<T> list;

}
