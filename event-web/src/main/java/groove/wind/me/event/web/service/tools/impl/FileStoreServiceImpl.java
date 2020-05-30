package groove.wind.me.event.web.service.tools.impl;

import groove.wind.me.event.web.entity.biz.FileStore;
import groove.wind.me.event.web.repository.FileStoreRepository;
import groove.wind.me.event.web.service.tools.FileStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStoreServiceImpl implements FileStoreService {

    @Autowired
    FileStoreRepository fileStoreRepository;

    @Override
    public boolean saveFileStore(FileStore fileStore) {
        FileStore store = fileStoreRepository.save(fileStore);
        return StringUtils.isNotBlank(store.getId());
    }

}
