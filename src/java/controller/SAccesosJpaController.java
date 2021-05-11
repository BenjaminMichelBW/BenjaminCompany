/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entidades.SAccesos;
import entidades.SAccesos_;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SAplicaciones;
import entidades.SPerfiles;
import java.util.ArrayList;
import java.util.Collection;
import entidades.SPerfilesAccesos;
import entidades.SPerfilesAccesos_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author admin
 */
public class SAccesosJpaController implements Serializable {

    public SAccesosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;
    private List<SAccesos> lista;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SAccesos SAccesos) {
        if (SAccesos.getSAplicacionesCollection() == null) {
            SAccesos.setSAplicacionesCollection(new ArrayList<SAplicaciones>());
        }
        if (SAccesos.getsPerfilesAccesosCollection() == null) {
            SAccesos.setsPerfilesAccesosCollection(new ArrayList<SPerfilesAccesos>());
        }
        if (SAccesos.getSPerfilesAccesosCollection() == null) {
            SAccesos.setSPerfilesAccesosCollection(new ArrayList<SPerfilesAccesos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SAplicaciones> attachedSAplicacionesCollection = new ArrayList<SAplicaciones>();
            for (SAplicaciones SAplicacionesCollectionSAplicacionesToAttach : SAccesos.getSAplicacionesCollection()) {
                SAplicacionesCollectionSAplicacionesToAttach = em.getReference(SAplicacionesCollectionSAplicacionesToAttach.getClass(), SAplicacionesCollectionSAplicacionesToAttach.getIdAplicacion());
                attachedSAplicacionesCollection.add(SAplicacionesCollectionSAplicacionesToAttach);
            }
            SAccesos.setSAplicacionesCollection(attachedSAplicacionesCollection);
            Collection<SPerfilesAccesos> attachedsPerfilesAccesosCollection = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos sPerfilesAccesosCollectionSPerfilesAccesosToAttach : SAccesos.getsPerfilesAccesosCollection()) {
                sPerfilesAccesosCollectionSPerfilesAccesosToAttach = em.getReference(sPerfilesAccesosCollectionSPerfilesAccesosToAttach.getClass(), sPerfilesAccesosCollectionSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedsPerfilesAccesosCollection.add(sPerfilesAccesosCollectionSPerfilesAccesosToAttach);
            }
            SAccesos.setsPerfilesAccesosCollection(attachedsPerfilesAccesosCollection);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollection = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesosToAttach : SAccesos.getSPerfilesAccesosCollection()) {
                SPerfilesAccesosCollectionSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollection.add(SPerfilesAccesosCollectionSPerfilesAccesosToAttach);
            }
            SAccesos.setSPerfilesAccesosCollection(attachedSPerfilesAccesosCollection);
            em.persist(SAccesos);
            for (SAplicaciones SAplicacionesCollectionSAplicaciones : SAccesos.getSAplicacionesCollection()) {
                SAccesos oldIdAccesoOfSAplicacionesCollectionSAplicaciones = SAplicacionesCollectionSAplicaciones.getIdAcceso();
                SAplicacionesCollectionSAplicaciones.setIdAcceso(SAccesos);
                SAplicacionesCollectionSAplicaciones = em.merge(SAplicacionesCollectionSAplicaciones);
                if (oldIdAccesoOfSAplicacionesCollectionSAplicaciones != null) {
                    oldIdAccesoOfSAplicacionesCollectionSAplicaciones.getSAplicacionesCollection().remove(SAplicacionesCollectionSAplicaciones);
                    oldIdAccesoOfSAplicacionesCollectionSAplicaciones = em.merge(oldIdAccesoOfSAplicacionesCollectionSAplicaciones);
                }
            }
            for (SPerfilesAccesos sPerfilesAccesosCollectionSPerfilesAccesos : SAccesos.getsPerfilesAccesosCollection()) {
                SAccesos oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos = sPerfilesAccesosCollectionSPerfilesAccesos.getSAccesos();
                sPerfilesAccesosCollectionSPerfilesAccesos.setSAccesos(SAccesos);
                sPerfilesAccesosCollectionSPerfilesAccesos = em.merge(sPerfilesAccesosCollectionSPerfilesAccesos);
                if (oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos != null) {
                    oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos.getsPerfilesAccesosCollection().remove(sPerfilesAccesosCollectionSPerfilesAccesos);
                    oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos = em.merge(oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos);
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesos : SAccesos.getSPerfilesAccesosCollection()) {
                SAccesos oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos = SPerfilesAccesosCollectionSPerfilesAccesos.getSAccesos();
                SPerfilesAccesosCollectionSPerfilesAccesos.setSAccesos(SAccesos);
                SPerfilesAccesosCollectionSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionSPerfilesAccesos);
                if (oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos != null) {
                    oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionSPerfilesAccesos);
                    oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos = em.merge(oldSAccesosOfSPerfilesAccesosCollectionSPerfilesAccesos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SAccesos SAccesos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos persistentSAccesos = em.find(SAccesos.class, SAccesos.getIdAcceso());
            Collection<SAplicaciones> SAplicacionesCollectionOld = persistentSAccesos.getSAplicacionesCollection();
            Collection<SAplicaciones> SAplicacionesCollectionNew = SAccesos.getSAplicacionesCollection();
            Collection<SPerfilesAccesos> sPerfilesAccesosCollectionOld = persistentSAccesos.getsPerfilesAccesosCollection();
            Collection<SPerfilesAccesos> sPerfilesAccesosCollectionNew = SAccesos.getsPerfilesAccesosCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionOld = persistentSAccesos.getSPerfilesAccesosCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionNew = SAccesos.getSPerfilesAccesosCollection();
            List<String> illegalOrphanMessages = null;
            for (SPerfilesAccesos sPerfilesAccesosCollectionOldSPerfilesAccesos : sPerfilesAccesosCollectionOld) {
                if (!sPerfilesAccesosCollectionNew.contains(sPerfilesAccesosCollectionOldSPerfilesAccesos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SPerfilesAccesos " + sPerfilesAccesosCollectionOldSPerfilesAccesos + " since its SAccesos field is not nullable.");
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionOldSPerfilesAccesos : SPerfilesAccesosCollectionOld) {
                if (!SPerfilesAccesosCollectionNew.contains(SPerfilesAccesosCollectionOldSPerfilesAccesos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SPerfilesAccesos " + SPerfilesAccesosCollectionOldSPerfilesAccesos + " since its SAccesos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<SAplicaciones> attachedSAplicacionesCollectionNew = new ArrayList<SAplicaciones>();
            for (SAplicaciones SAplicacionesCollectionNewSAplicacionesToAttach : SAplicacionesCollectionNew) {
                SAplicacionesCollectionNewSAplicacionesToAttach = em.getReference(SAplicacionesCollectionNewSAplicacionesToAttach.getClass(), SAplicacionesCollectionNewSAplicacionesToAttach.getIdAplicacion());
                attachedSAplicacionesCollectionNew.add(SAplicacionesCollectionNewSAplicacionesToAttach);
            }
            SAplicacionesCollectionNew = attachedSAplicacionesCollectionNew;
            SAccesos.setSAplicacionesCollection(SAplicacionesCollectionNew);
            Collection<SPerfilesAccesos> attachedsPerfilesAccesosCollectionNew = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos sPerfilesAccesosCollectionNewSPerfilesAccesosToAttach : sPerfilesAccesosCollectionNew) {
                sPerfilesAccesosCollectionNewSPerfilesAccesosToAttach = em.getReference(sPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getClass(), sPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedsPerfilesAccesosCollectionNew.add(sPerfilesAccesosCollectionNewSPerfilesAccesosToAttach);
            }
            sPerfilesAccesosCollectionNew = attachedsPerfilesAccesosCollectionNew;
            SAccesos.setsPerfilesAccesosCollection(sPerfilesAccesosCollectionNew);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollectionNew = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach : SPerfilesAccesosCollectionNew) {
                SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollectionNew.add(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach);
            }
            SPerfilesAccesosCollectionNew = attachedSPerfilesAccesosCollectionNew;
            SAccesos.setSPerfilesAccesosCollection(SPerfilesAccesosCollectionNew);
            SAccesos = em.merge(SAccesos);
            for (SAplicaciones SAplicacionesCollectionOldSAplicaciones : SAplicacionesCollectionOld) {
                if (!SAplicacionesCollectionNew.contains(SAplicacionesCollectionOldSAplicaciones)) {
                    SAplicacionesCollectionOldSAplicaciones.setIdAcceso(null);
                    SAplicacionesCollectionOldSAplicaciones = em.merge(SAplicacionesCollectionOldSAplicaciones);
                }
            }
            for (SAplicaciones SAplicacionesCollectionNewSAplicaciones : SAplicacionesCollectionNew) {
                if (!SAplicacionesCollectionOld.contains(SAplicacionesCollectionNewSAplicaciones)) {
                    SAccesos oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones = SAplicacionesCollectionNewSAplicaciones.getIdAcceso();
                    SAplicacionesCollectionNewSAplicaciones.setIdAcceso(SAccesos);
                    SAplicacionesCollectionNewSAplicaciones = em.merge(SAplicacionesCollectionNewSAplicaciones);
                    if (oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones != null && !oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones.equals(SAccesos)) {
                        oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones.getSAplicacionesCollection().remove(SAplicacionesCollectionNewSAplicaciones);
                        oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones = em.merge(oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones);
                    }
                }
            }
            for (SPerfilesAccesos sPerfilesAccesosCollectionNewSPerfilesAccesos : sPerfilesAccesosCollectionNew) {
                if (!sPerfilesAccesosCollectionOld.contains(sPerfilesAccesosCollectionNewSPerfilesAccesos)) {
                    SAccesos oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos = sPerfilesAccesosCollectionNewSPerfilesAccesos.getSAccesos();
                    sPerfilesAccesosCollectionNewSPerfilesAccesos.setSAccesos(SAccesos);
                    sPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(sPerfilesAccesosCollectionNewSPerfilesAccesos);
                    if (oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos != null && !oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos.equals(SAccesos)) {
                        oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos.getsPerfilesAccesosCollection().remove(sPerfilesAccesosCollectionNewSPerfilesAccesos);
                        oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos);
                    }
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesos : SPerfilesAccesosCollectionNew) {
                if (!SPerfilesAccesosCollectionOld.contains(SPerfilesAccesosCollectionNewSPerfilesAccesos)) {
                    SAccesos oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos = SPerfilesAccesosCollectionNewSPerfilesAccesos.getSAccesos();
                    SPerfilesAccesosCollectionNewSPerfilesAccesos.setSAccesos(SAccesos);
                    SPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                    if (oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos != null && !oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos.equals(SAccesos)) {
                        oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                        oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(oldSAccesosOfSPerfilesAccesosCollectionNewSPerfilesAccesos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SAccesos.getIdAcceso();
                if (findSAccesos(id) == null) {
                    throw new NonexistentEntityException("The sAccesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos SAccesos;
            try {
                SAccesos = em.getReference(SAccesos.class, id);
                SAccesos.getIdAcceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SAccesos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SPerfilesAccesos> sPerfilesAccesosCollectionOrphanCheck = SAccesos.getsPerfilesAccesosCollection();
            for (SPerfilesAccesos sPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos : sPerfilesAccesosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SAccesos (" + SAccesos + ") cannot be destroyed since the SPerfilesAccesos " + sPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos + " in its sPerfilesAccesosCollection field has a non-nullable SAccesos field.");
            }
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionOrphanCheck = SAccesos.getSPerfilesAccesosCollection();
            for (SPerfilesAccesos SPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos : SPerfilesAccesosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SAccesos (" + SAccesos + ") cannot be destroyed since the SPerfilesAccesos " + SPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos + " in its SPerfilesAccesosCollection field has a non-nullable SAccesos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<SAplicaciones> SAplicacionesCollection = SAccesos.getSAplicacionesCollection();
            for (SAplicaciones SAplicacionesCollectionSAplicaciones : SAplicacionesCollection) {
                SAplicacionesCollectionSAplicaciones.setIdAcceso(null);
                SAplicacionesCollectionSAplicaciones = em.merge(SAplicacionesCollectionSAplicaciones);
            }
            em.remove(SAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SAccesos> findSAccesosEntities() {
        return findSAccesosEntities(true, -1, -1);
    }

    public List<SAccesos> findSAccesosEntities(int maxResults, int firstResult) {
        return findSAccesosEntities(false, maxResults, firstResult);
    }

    private List<SAccesos> findSAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SAccesos.class));
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

    public SAccesos findSAccesos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SAccesos> rt = cq.from(SAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SAccesos> traerAccesosActuales(SPerfiles idPerfil) {
        lista = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SAccesos> query = cb.createQuery(SAccesos.class);
            Root<SAccesos> perfil = query.from(SAccesos.class);
            CollectionJoin<SAccesos, SPerfilesAccesos> usuarioPerfil = perfil.join(SAccesos_.sPerfilesAccesosCollection);
            query.select(perfil)
                    .where(cb.equal(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles), idPerfil));
            TypedQuery<SAccesos> typedQuery = em.createQuery(query);
            
            lista = typedQuery.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }
    
    public List<SAccesos> traerAccesosDisponibles(SPerfiles idPerfil){
        EntityManager em = getEntityManager();
        lista = new ArrayList<>();
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SAccesos> query = cb.createQuery(SAccesos.class);
            Root<SAccesos> perfil = query.from(SAccesos.class);
            CollectionJoin<SAccesos, SPerfilesAccesos> usuarioPerfil = perfil.join(SAccesos_.sPerfilesAccesosCollection, JoinType.LEFT);
            usuarioPerfil.on(cb.equal(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles), idPerfil));
            query.select(perfil)
                    .where(cb.isNull(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles)));
            TypedQuery<SAccesos> typedQuery = em.createQuery(query);

            lista = typedQuery.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }
    
    public List<SPerfilesAccesos> traerAccesosByPerfil(SPerfiles perfil) {
        List<SPerfilesAccesos> listaAccesos = new ArrayList<>();
        
        if (perfil != null) {
            //SELECT r FROM RPerfilAcceso r WHERE r.rPerfilAccesoPK.idPerfil = :idPerfil
        EntityManager em = getEntityManager();
        Query query = null;
        try {

            query = em.createNamedQuery("RPerfilAcceso.findByIdPerfil", SPerfilesAccesos.class).setParameter("idPerfil", perfil.getIdPerfil());

            listaAccesos = query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
}
        }
        
        return listaAccesos;
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the lista
     */
    public List<SAccesos> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<SAccesos> lista) {
        this.lista = lista;
    }
//</editor-fold>

}
