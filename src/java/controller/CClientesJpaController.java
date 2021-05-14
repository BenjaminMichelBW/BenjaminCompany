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
import entidades.CCiudad;
import entidades.CClientes;
import entidades.CDistribuidor;
import entidades.SUsuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import objetos.ReporteCliente;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author admin
 */
public class CClientesJpaController implements Serializable {

    public CClientesJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CClientes CClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad idCiudad = CClientes.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                CClientes.setIdCiudad(idCiudad);
            }
            CDistribuidor idDistribuidor = CClientes.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor = em.getReference(idDistribuidor.getClass(), idDistribuidor.getIdDistribuidor());
                CClientes.setIdDistribuidor(idDistribuidor);
            }
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModifica);
            }
            em.persist(CClientes);
            if (idCiudad != null) {
                idCiudad.getCClientesCollection().add(CClientes);
                idCiudad = em.merge(idCiudad);
            }
            if (idDistribuidor != null) {
                idDistribuidor.getCClientesCollection().add(CClientes);
                idDistribuidor = em.merge(idDistribuidor);
            }
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCClientesCollection().add(CClientes);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CClientes CClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CClientes persistentCClientes = em.find(CClientes.class, CClientes.getIdCliente());
            CCiudad idCiudadOld = persistentCClientes.getIdCiudad();
            CCiudad idCiudadNew = CClientes.getIdCiudad();
            CDistribuidor idDistribuidorOld = persistentCClientes.getIdDistribuidor();
            CDistribuidor idDistribuidorNew = CClientes.getIdDistribuidor();
            SUsuarios idUsuarioModificaOld = persistentCClientes.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = CClientes.getIdUsuarioModifica();
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                CClientes.setIdCiudad(idCiudadNew);
            }
            if (idDistribuidorNew != null) {
                idDistribuidorNew = em.getReference(idDistribuidorNew.getClass(), idDistribuidorNew.getIdDistribuidor());
                CClientes.setIdDistribuidor(idDistribuidorNew);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            CClientes = em.merge(CClientes);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getCClientesCollection().remove(CClientes);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getCClientesCollection().add(CClientes);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idDistribuidorOld != null && !idDistribuidorOld.equals(idDistribuidorNew)) {
                idDistribuidorOld.getCClientesCollection().remove(CClientes);
                idDistribuidorOld = em.merge(idDistribuidorOld);
            }
            if (idDistribuidorNew != null && !idDistribuidorNew.equals(idDistribuidorOld)) {
                idDistribuidorNew.getCClientesCollection().add(CClientes);
                idDistribuidorNew = em.merge(idDistribuidorNew);
            }
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getCClientesCollection().remove(CClientes);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getCClientesCollection().add(CClientes);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CClientes.getIdCliente();
                if (findCClientes(id) == null) {
                    throw new NonexistentEntityException("The cClientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CClientes CClientes;
            try {
                CClientes = em.getReference(CClientes.class, id);
                CClientes.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CClientes with id " + id + " no longer exists.", enfe);
            }
            CCiudad idCiudad = CClientes.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getCClientesCollection().remove(CClientes);
                idCiudad = em.merge(idCiudad);
            }
            CDistribuidor idDistribuidor = CClientes.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor.getCClientesCollection().remove(CClientes);
                idDistribuidor = em.merge(idDistribuidor);
            }
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCClientesCollection().remove(CClientes);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            em.remove(CClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CClientes> findCClientesEntities() {
        return findCClientesEntities(true, -1, -1);
    }

    public List<CClientes> findCClientesEntities(int maxResults, int firstResult) {
        return findCClientesEntities(false, maxResults, firstResult);
    }

    private List<CClientes> findCClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CClientes.class));
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

    public CClientes findCClientes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CClientes> rt = cq.from(CClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca un listado de clientes el cual modifico un usuario
     * 
     * @param idUsuario
     * @return 
     */
    public List<ReporteCliente> traerClientesRegistroUsuario (int idUsuario) {
        List<ReporteCliente> listaClientes = new ArrayList<>();
        ReporteCliente cliente = new ReporteCliente();
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            
            StoredProcedureQuery sp = em.createStoredProcedureQuery("stp_SelectCClientesBenja");
            sp.registerStoredProcedureParameter("idUsuario", Integer.class, ParameterMode.IN);
            sp.setParameter("idUsuario", idUsuario);
            sp.execute();
            
            List<Object[]> resultado = sp.getResultList();
            
            for(Object[] iterador : resultado){
                cliente.setNumeroCliente(iterador[0].toString());
                cliente.setNomCliente(iterador[1].toString());
                cliente.setNomDistribuidor(iterador[2].toString());
                cliente.setNomCiudad(iterador[3].toString());
                cliente.setTelContacto(iterador[4].toString());
                cliente.setNomUsuario(iterador[5].toString());
                listaClientes.add(cliente);
                cliente = new ReporteCliente();
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listaClientes;
    }
    
}
