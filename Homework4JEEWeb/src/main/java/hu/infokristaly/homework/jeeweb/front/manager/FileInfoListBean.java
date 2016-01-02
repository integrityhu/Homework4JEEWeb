/*
 * Copyright 2013 Integrity Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Zoltan Papp
 * 
 */
package hu.infokristaly.homework.jeeweb.front.manager;

import hu.infokristaly.homework.jeeweb.back.model.FileInfo;
import hu.infokristaly.homework.jeeweb.middle.service.FileInfoService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileInfoLazyBean class.
 */
@SessionScoped
@Named
public class FileInfoListBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3843708547414235388L;

    /** The log. */
    @Inject
    private Logger log;

    /** The file info service. */
    @Inject
    private FileInfoService fileInfoService;

    /** The data model. */
    private List<FileInfo> fileInfoList;

    /** The data model. */
    private List<FileInfo> filteredFileInfoList;

    @PostConstruct
    public void init() {
        loadFileInfoList();
    }

    /**
     * Gets the lazy data model used for test lazy loaded PrimeFaces table.
     * 
     * @return the lazy data model
     */
    public void loadFileInfoList() {
        fileInfoList = fileInfoService.findAll();
        log.log(Level.INFO, "[FileInfoListBean] load finished.");
    }

    public List<FileInfo> getFileInfoList() {
        return fileInfoList;
    }

    public List<FileInfo> getFilteredFileInfoList() {  
        return filteredFileInfoList;  
    }  
  
    public void setFilteredFileInfoList(List<FileInfo> filteredFileInfoList) {  
        this.filteredFileInfoList = filteredFileInfoList;  
    }  
}
