/**
 * 
 */
package hu.infokristaly.homework.jeeweb.front.controller;

import hu.infokristaly.homework.jeeweb.front.manager.FileInfoListBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FileInfoListController {

    @Inject
    private FileInfoListBean fileInfoListBean;

    public void initFileInfoListBean() {
        fileInfoListBean.loadFileInfoList();
    }
}
