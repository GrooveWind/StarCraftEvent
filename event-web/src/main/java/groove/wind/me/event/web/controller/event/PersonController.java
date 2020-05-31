package groove.wind.me.event.web.controller.event;


import groove.wind.me.event.web.entity.biz.event.Person;
import groove.wind.me.event.web.service.event.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "人员管理api", tags = "人员管理api")
@RequestMapping("/biz/person")
@Log4j2
public class PersonController {

    @Autowired
    PersonService personService;


    @PostMapping("/list/pageNum/{pageNum}/pageSize/{pageSize}")
    @ApiOperation(value = "查看个人列表", notes = "查看个人列表")
    public Page<Person> list(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return personService.queryWithPage(pageNum, pageSize);
    }


    @GetMapping("/detail/{id}")
    @ApiOperation(value = "查看个人详情", notes = "查看个人详情")
    public Person detail(@PathVariable("id") String id) {
        return personService.queryById(id);
    }


    @PostMapping("/create")
    @ApiOperation(value = "创建个人信息", notes = "创建个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@Valid @RequestBody Person entity) {
        return personService.saveOrUpdateEvent(entity);
    }

    @PostMapping("/modify")
    @ApiOperation(value = "更新个人信息", notes = "更新个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person modify(@Valid @RequestBody Person entity) {
        return personService.saveOrUpdateEvent(entity);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除个人信息", notes = "删除个人信息")
    public boolean delete(@PathVariable("id") String id) {
        return personService.delete(id);
    }


}
