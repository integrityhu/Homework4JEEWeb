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
package hu.infokristaly.homework.jeeweb.middle.service;

import hu.infokristaly.homework.jeeweb.back.model.FileInfo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Logger;

/**
 * The FileInfoService class. Used for manage fileInfo entities.
 * 
 * @author pzoli
 * 
 */
@Stateless
public class FileInfoService {

    /** The logger. */
    @Inject
    private Logger log;

    /** The entity manager. */
    @Inject
    private EntityManager em;

    /**
     * Find the fileInfo.
     * 
     * @param fileInfo
     *            the file info
     * @return the file info
     */
    public FileInfo find(FileInfo fileInfo) {
        log.info("Find by Id [" + fileInfo.getId() + "]" + fileInfo.getFileName());
        FileInfo result = em.find(FileInfo.class, fileInfo.getId());
        return result;
    }


    /**
     * Find filtered range.
     * 
     * @param first
     *            the first
     * @param pageSize
     *            the page size
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<FileInfo> findRange(int first, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<FileInfo> cq = builder.createQuery(FileInfo.class);
        Root<FileInfo> from = cq.from(FileInfo.class);
        cq.select(from);
        Query q = em.createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    /**
     * Find All
     * 
     * @param first
     *            the first
     * @param pageSize
     *            the page size
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<FileInfo> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<FileInfo> cq = builder.createQuery(FileInfo.class);
        Root<FileInfo> from = cq.from(FileInfo.class);
        cq.select(from);
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    /**
     * Count filtered file items.
     * 
     * @param filters
     *            the filters
     * @return the int
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int count() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<FileInfo> from = cq.from(FileInfo.class);
        cq.select(builder.count(from));
        Query q = em.createQuery(cq);
        int result = ((Long) q.getSingleResult()).intValue();
        return result;
    }

}
