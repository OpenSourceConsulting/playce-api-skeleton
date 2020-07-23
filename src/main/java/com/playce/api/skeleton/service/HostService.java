package com.playce.api.skeleton.service;

import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.model.Host;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
public interface HostService {

    List<Host> getHostList() throws PlayceException;

    List<Host> getHostListWithPermission() throws PlayceException;

    List<Host> getHostListWithPermission(Long scouterServerId) throws PlayceException;

    Host getHost(Long hostId) throws PlayceException;

}
//end of HostService.java