package response;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * api接口响应数据对象
 */
@Data
@ApiModel(value = "接口调用结果")
public class ApiResult {
    /**
     * 接口业务正常执行状态标识
     */
    public static final Integer CODE_SUCCESS = 1;

    /**
     * 接口业务执行失败标识
     */
    public static final Integer CODE_FAIL = 0;

    /**
     * 未登录返回码
     */
    public static final Integer NOT_LOGIN = 401;

    public static final Integer IS_SALE = 110;

    /**
     * 业务执行结果
     */
    @ApiModelProperty(value = "业务执行结果,1:成功,0:失败")
    protected Integer code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    protected String msg;

    /**
     * 接口响应具体业务数据
     */
    @ApiModelProperty(value = "接口响应具体业务数据")
    protected Object data;

    /**
     * 请求成功，有业务数据，默认为接口成功
     * @param data
     */
    public ApiResult(Object data) {
        this.code = CODE_SUCCESS;
        this.msg = "ok";
        this.data = data;
    }

    public ApiResult(PageInfo pageInfo) {
        this.code = CODE_SUCCESS;
        this.msg = "ok";
        PageRes pageRes = new PageRes();
        int pageNum = pageInfo.getPageNum();
        long total = pageInfo.getTotal();
        int pageSize = pageInfo.getPageSize();
        List list = pageInfo.getList();
        pageRes.setCurrentPage(pageNum);
        pageRes.setTotal(total);
        pageRes.setPageSize(pageSize);
        pageRes.setHasNextPage(pageNum<pageInfo.getPages());
        pageRes.setList(list);
        this.data = pageInfo;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ApiResult(Object data, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ApiResult(Integer code) {
        this.code = code;
        if (CODE_SUCCESS - code == 0){
            this.msg = "ok";
        }else{
            this.msg = "fail";
        }
    }

    public ApiResult() {
    }

    public static ApiResult fail(String msg) {
        return new ApiResult(CODE_FAIL, msg);
    }


    public static ApiResult success(Object data, String msg) {
        return new ApiResult(data, CODE_SUCCESS, msg);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(data, CODE_SUCCESS, "");
    }

    public static ApiResult successMsg(String msg) {
        return new ApiResult(null, CODE_SUCCESS, msg);
    }
}
