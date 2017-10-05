package org.hiease.authserver.service;

import org.hiease.authserver.data.Branch;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

@Service
public class BranchService {

    private static final Integer ROOT_ID = 0;

    @PersistenceContext
    private EntityManager entityManager;

    public Branch getBranchTree(){
        entityManager.createNamedQuery
                ("findAllNodesWithTheirChildren").getResultList();
        Branch root = entityManager.find(Branch.class, ROOT_ID);
        return root;
    }

}
