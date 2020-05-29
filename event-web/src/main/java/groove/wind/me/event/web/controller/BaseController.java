package groove.wind.me.event.web.controller;

import groove.wind.me.event.web.entity.BaseCondition;
import groove.wind.me.event.web.entity.BaseEntity;
import groove.wind.me.event.web.entity.CommonResult;
import groove.wind.me.event.web.enums.StatusEnum;
import groove.wind.me.event.web.exception.EventBizException;
import groove.wind.me.event.web.utils.EventReflectionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController<T extends BaseEntity, PK> {

//    @Autowired
//    BaseService<T, PK> baseService;
//
//
//    @PostMapping("list")
//    @ApiOperation(value = "查看列表", notes = "查看列表", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<T> list(@RequestBody BaseCondition condition) {
//        return baseService.queryList(condition);
//    }
//
//    @GetMapping("detail")
//    @ApiOperation(value = "查看详情", notes = "查看详情")
//    public T detail(PK id) {
//        return baseService.queryById(id);
//    }
//
//    @PostMapping("save")
//    @ApiOperation(value = "保存数据", notes = "保存数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public T save(@RequestBody T entity) {
//        return baseService.saveOrUpdate(entity);
//    }
//
//    @PostMapping("modify")
//    @ApiOperation(value = "修改数据", notes = "修改数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public T modify(@RequestBody T entity) {
//        if (!EventReflectionUtils.checkIdExists(entity)) {
//            throw new EventBizException(StatusEnum.MODIFY_LOSE_ID);
//        }
//        return baseService.saveOrUpdate(entity);
//    }
//
//    @PostMapping("delete")
//    @ApiOperation(value = "删除数据", notes = "删除数据", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResult delete(@RequestBody PK id) {
//        boolean result = baseService.delete(id);
//        if (result) {
//            return CommonResult.success("删除成功");
//        }
//        return CommonResult.fail("删除失败");
//    }

}
