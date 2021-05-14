/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SPerfiles;
import entidades.CClientes;
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
public class SUsuariosJpaController implements Serializable {

    public SUsuariosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SUsuarios SUsuarios) {
        if (SUsuarios.getCClientesCollection() == null) {
            SUsuarios.setCClientesCollection(new ArrayList<CClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfil);
            }
            Collection<CClientes> attachedCClientesCollection = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionCClientesToAttach : SUsuarios.getCClientesCollection()) {
                CClientesCollectionCClientesToAttach = em.getReference(CClientesCollectionCClientesToAttach.getClass(), CClientesCollectionCClientesToAttach.getIdCliente());
                attachedCClientesCollection.add(CClientesCollectionCClientesToAttach);
            }
            SUsuarios.setCClientesCollection(attachedCClientesCollection);
            em.persist(SUsuarios);
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().add(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            for (CClientes CClientesCollectionCClientes : SUsuarios.getCClientesCollection()) {
                SUsuarios oldIdUsuarioModificaOfCClientesCollectionCClientes = CClientesCollectionCClientes.getIdUsuarioModifica();
                CClientesCollectionCClientes.setIdUsuarioModifica(SUsuarios);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
                if (oldIdUsuarioModificaOfCClientesCollectionCClientes != null) {
                    oldIdUsuarioModificaOfCClientesCollectionCClientes.getCClientesCollection().remove(CClientesCollectionCClientes);
                    oldIdUsuarioModificaOfCClientesCollectionCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionCClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SUsuarios SUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios persistentSUsuarios = em.find(SUsuarios.class, SUsuarios.getIdUsuario());
            SPerfiles idPerfilOld = persistentSUsuarios.getIdPerfil();
            SPerfiles idPerfilNew = SUsuarios.getIdPerfil();
            Collection<CClientes> CClientesCollectionOld = persistentSUsuarios.getCClientesCollection();
            Collection<CClientes> CClientesCollectionNew = SUsuarios.getCClientesCollection();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfilNew);
            }
            Collection<CClientes> attachedCClientesCollectionNew = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionNewCClientesToAttach : CClientesCollectionNew) {
                CClientesCollectionNewCClientesToAttach = em.getReference(CClientesCollectionNewCClientesToAttach.getClass(), CClientesCollectionNewCClientesToAttach.getIdCliente());
                attachedCClientesCollectionNew.add(CClientesCollectionNewCClientesToAttach);
            }
            CClientesCollectionNew = attachedCClientesCollectionNew;
            SUsuarios.setCClientesCollection(CClientesCollectionNew);
            SUsuarios = em.merge(SUsuarios);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getSUsuariosCollection().remove(SUsuarios);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getSUsuariosCollection().add(SUsuarios);
                idPerfilNew = em.merge(idPerfilNew);
            }
            for (CClientes CClientesCollectionOldCClientes : CClientesCollectionOld) {
                if (!CClientesCollectionNew.contains(CClientesCollectionOldCClientes)) {
                    CClientesCollectionOldCClientes.setIdUsuarioModifica(null);
                    CClientesCollectionOldCClientes = em.merge(CClientesCollectionOldCClientes);
                }
            }
            for (CClientes CClientesCollectionNewCClientes : CClientesCollectionNew) {
                if (!CClientesCollectionOld.contains(CClientesCollectionNewCClientes)) {
                    SUsuarios oldIdUsuarioModificaOfCClientesCollectionNewCClientes = CClientesCollectionNewCClientes.getIdUsuarioModifica();
                    CClientesCollectionNewCClientes.setIdUsuarioModifica(SUsuarios);
                    CClientesCollectionNewCClientes = em.merge(CClientesCollectionNewCClientes);
                    if (oldIdUsuarioModificaOfCClientesCollectionNewCClientes != null && !oldIdUsuarioModificaOfCClientesCollectionNewCClientes.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes.getCClientesCollection().remove(CClientesCollectionNewCClientes);
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionNewCClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SUsuarios.getIdUsuario();
                if (findSUsuarios(id) == null) {
                    throw new NonexistentEntityException("The sUsuarios with id " + id + " no longer exists.");
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
            SUsuarios SUsuarios;
            try {
                SUsuarios = em.getReference(SUsuarios.class, id);
                SUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SUsuarios with id " + id + " no longer exists.", enfe);
            }
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().remove(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            Collection<CClientes> CClientesCollection = SUsuarios.getCClientesCollection();
            for (CClientes CClientesCollectionCClientes : CClientesCollection) {
                CClientesCollectionCClientes.setIdUsuarioModifica(null);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
            }
            em.remove(SUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SUsuarios> findSUsuariosEntities() {
        return findSUsuariosEntities(true, -1, -1);
    }

    public List<SUsuarios> findSUsuariosEntities(int maxResults, int firstResult) {
        return findSUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SUsuarios> findSUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SUsuarios.class));
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

    public SUsuarios findSUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SUsuarios> rt = cq.from(SUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
