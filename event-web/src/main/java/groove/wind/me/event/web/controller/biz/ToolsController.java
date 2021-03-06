package groove.wind.me.event.web.controller.biz;

import com.google.common.collect.Lists;
import groove.wind.me.event.web.entity.biz.FileStore;
import groove.wind.me.event.web.enums.StatusEnum;
import groove.wind.me.event.web.exception.EventBizException;
import groove.wind.me.event.web.service.tools.FileStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;


@RestController
@Api(value = "工具api", tags = "工具api")
@RequestMapping("/biz/tools")
@Log4j2
public class ToolsController {

    private static final List<String> IMAGE_SUFFIX = Lists.newArrayList("JPG", "JPEG", "PNG");

    @Autowired
    FileStoreService fileStoreService;


    @PostMapping("/uploadPhoto")
    @ApiOperation(value = "上传图片", notes = "上传图片", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPhoto(@RequestPart(value = "pic") MultipartFile file) {
        String realName = file.getOriginalFilename();
        String suffix = realName.substring(realName.lastIndexOf(".") + 1);
        if (!IMAGE_SUFFIX.contains(suffix.toUpperCase())) {
            throw new EventBizException(StatusEnum.NOT_SUPPORT_FILE_TYPE);
        }

        String storeName =  UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;

        try {
            File targetFile = new File("/opt/images", storeName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.error(e);
            throw new EventBizException(StatusEnum.FILE_UPLOAD_ERR);
        }

        FileStore fileStore = new FileStore();
        fileStore.setRealName(realName);
        fileStore.setStoreName(storeName);
        fileStoreService.saveFileStore(fileStore);

        return storeName;

    }

}
