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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileInfoLazyBean class.
 */
@SessionScoped
@Named
public class FileInfoLazyBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3843708547414235388L;

    /** The log. */
    @Inject
    private Logger log;

    /** The file info service. */
    @Inject
    private FileInfoService fileInfoService;

    /** The lazy data model. */
    private LazyDataModel<FileInfo> lazyDataModel;

    /**
     * Gets the lazy data model used for test lazy loaded PrimeFaces table.
     *
     * @return the lazy data model
     */
    public LazyDataModel<FileInfo> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LazyDataModel<FileInfo>() {

                private static final long serialVersionUID = 1678907483750487431L;

                @PostConstruct
                public void init() {
                    log.log(Level.INFO, "[LazyFileInfoDataModel] constructor finished.");
                }

                @Override
                public FileInfo getRowData(String rowKey) {
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setId(Long.valueOf(rowKey));
                    return fileInfoService.find(fileInfo);
                }

                @Override
                public Object getRowKey(FileInfo fileInfo) {
                    return fileInfo.getId();
                }

                @Override
                public List<FileInfo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    this.setPageSize(pageSize);
                    List<FileInfo> result = (fileInfoService.findRange(first, pageSize));
                    log.log(Level.INFO, "[LazyFileInfoDataModel] load finished.");
                    return result;
                }

                @Override
                public int getRowCount() {
                    int result = fileInfoService.count();
                    return result;
                }

            };
        }
        return lazyDataModel;
    }

}
