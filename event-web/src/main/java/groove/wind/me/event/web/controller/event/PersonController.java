package groove.wind.me.event.web.controller.event;


import groove.wind.me.event.web.entity.event.Person;
import groove.wind.me.event.web.service.event.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "人员管理api", tags = "人员管理api")
@RequestMapping("/biz/person")
@Log4j2
public class PersonController {

    @Autowired
    PersonService personService;


    @PostMapping("/create")
    @ApiOperation(value = "创建个人信息", notes = "创建个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createEvent(@RequestBody Person entity) {
        return personService.insert(entity);
    }


}
