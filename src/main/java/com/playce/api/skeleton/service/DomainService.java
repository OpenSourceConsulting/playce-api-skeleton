package com.playce.api.skeleton.service;

import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Domain;
import com.playce.api.skeleton.model.History;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
public interface DomainService {

    List<Domain> getDomainList();

    Domain getDomain(Long id, boolean writable) throws PlayceException;

    History createDomain(Domain domain);

    List<Domain> getDomainByName(String name);

    History modifyDomain(Domain domain, Long afterClusterId) throws PlayceException;

    void updateMemberRolesDomainsByDomainId(Domain domain);

    History removeDomain(Domain domain) throws PlayceException;
}
//end of DomainService.java