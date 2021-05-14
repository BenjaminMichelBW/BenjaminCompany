/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import entidades.SPerfiles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author admin
 */
public class SPerfilesJpaController implements Serializable {

    public SPerfilesJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SPerfiles SPerfiles) {
        if (SPerfiles.getSUsuariosCollection() == null) {
            SPerfiles.setSUsuariosCollection(new ArrayList<SUsuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SUsuarios> attachedSUsuariosCollection = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionSUsuariosToAttach : SPerfiles.getSUsuariosCollection()) {
                SUsuariosCollectionSUsuariosToAttach = em.getReference(SUsuariosCollectionSUsuariosToAttach.getClass(), SUsuariosCollectionSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollection.add(SUsuariosCollectionSUsuariosToAttach);
            }
            SPerfiles.setSUsuariosCollection(attachedSUsuariosCollection);
            em.persist(SPerfiles);
            for (SUsuarios SUsuariosCollectionSUsuarios : SPerfiles.getSUsuariosCollection()) {
                SPerfiles oldIdPerfilOfSUsuariosCollectionSUsuarios = SUsuariosCollectionSUsuarios.getIdPerfil();
                SUsuariosCollectionSUsuarios.setIdPerfil(SPerfiles);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
                if (oldIdPerfilOfSUsuariosCollectionSUsuarios != null) {
                    oldIdPerfilOfSUsuariosCollectionSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionSUsuarios);
                    oldIdPerfilOfSUsuariosCollectionSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionSUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SPerfiles SPerfiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles persistentSPerfiles = em.find(SPerfiles.class, SPerfiles.getIdPerfil());
            Collection<SUsuarios> SUsuariosCollectionOld = persistentSPerfiles.getSUsuariosCollection();
            Collection<SUsuarios> SUsuariosCollectionNew = SPerfiles.getSUsuariosCollection();
            Collection<SUsuarios> attachedSUsuariosCollectionNew = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionNewSUsuariosToAttach : SUsuariosCollectionNew) {
                SUsuariosCollectionNewSUsuariosToAttach = em.getReference(SUsuariosCollectionNewSUsuariosToAttach.getClass(), SUsuariosCollectionNewSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollectionNew.add(SUsuariosCollectionNewSUsuariosToAttach);
            }
            SUsuariosCollectionNew = attachedSUsuariosCollectionNew;
            SPerfiles.setSUsuariosCollection(SUsuariosCollectionNew);
            SPerfiles = em.merge(SPerfiles);
            for (SUsuarios SUsuariosCollectionOldSUsuarios : SUsuariosCollectionOld) {
                if (!SUsuariosCollectionNew.contains(SUsuariosCollectionOldSUsuarios)) {
                    SUsuariosCollectionOldSUsuarios.setIdPerfil(null);
                    SUsuariosCollectionOldSUsuarios = em.merge(SUsuariosCollectionOldSUsuarios);
                }
            }
            for (SUsuarios SUsuariosCollectionNewSUsuarios : SUsuariosCollectionNew) {
                if (!SUsuariosCollectionOld.contains(SUsuariosCollectionNewSUsuarios)) {
                    SPerfiles oldIdPerfilOfSUsuariosCollectionNewSUsuarios = SUsuariosCollectionNewSUsuarios.getIdPerfil();
                    SUsuariosCollectionNewSUsuarios.setIdPerfil(SPerfiles);
                    SUsuariosCollectionNewSUsuarios = em.merge(SUsuariosCollectionNewSUsuarios);
                    if (oldIdPerfilOfSUsuariosCollectionNewSUsuarios != null && !oldIdPerfilOfSUsuariosCollectionNewSUsuarios.equals(SPerfiles)) {
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionNewSUsuarios);
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionNewSUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SPerfiles.getIdPerfil();
                if (findSPerfiles(id) == null) {
                    throw new NonexistentEntityException("The sPerfiles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles SPerfiles;
            try {
                SPerfiles = em.getReference(SPerfiles.class, id);
                SPerfiles.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SPerfiles with id " + id + " no longer exists.", enfe);
            }
            Collection<SUsuarios> SUsuariosCollection = SPerfiles.getSUsuariosCollection();
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuariosCollection) {
                SUsuariosCollectionSUsuarios.setIdPerfil(null);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
            }
            em.remove(SPerfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SPerfiles> findSPerfilesEntities() {
        return findSPerfilesEntities(true, -1, -1);
    }

    public List<SPerfiles> findSPerfilesEntities(int maxResults, int firstResult) {
        return findSPerfilesEntities(false, maxResults, firstResult);
    }

    private List<SPerfiles> findSPerfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfiles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SPerfiles findSPerfiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfiles> rt = cq.from(SPerfiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
