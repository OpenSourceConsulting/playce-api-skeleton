/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Entity
public class HostDetail extends BaseDomain {

    /**
     * The HostId.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="hd_generator")
    @GenericGenerator(name = "hd_generator", strategy = "native")
    private Long id;

    /**
     * The Host.
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "host_id", nullable = false)
    @JsonProperty("hostId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Host host;

    /**
     * The Hostname.
     */
    @Column
    private String hostname;

    /**
     * The Vendor.
     */
    @Column
    private String vendor;

    /**
     * The Cpu.
     */
    @Column
    private Integer cpu;

    /**
     * The Kernel.
     */
    @Column
    private String kernel;

    /**
     * The Cores.
     */
    @Column
    private Integer cores;

    /**
     * The Architecture.
     */
    @Column
    private String architecture;

    /**
     * The Os.
     */
    @Column
    private String os;

    /**
     * The Memory.
     */
    @Column
    private Long memory;

    /**
     * The Swap.
     */
    @Column
    private Long swap;

    /**
     * The Disk.
     */
    @Column
    private Long disk;

    /**
     * The Ip address.
     */
    @Column
    private String ipAddress;

    /**
     * The gateway.
     */
    @Column
    private String gateway;

    /**
     * The Netmask.
     */
    @Column
    private String dns;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public Host getHost() {
        return host;
    }

    /**
     * Sets host.
     *
     * @param host the host
     */
    public void setHost(Host host) {
        this.host = host;
    }

    /**
     * Gets hostname.
     *
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets hostname.
     *
     * @param hostname the hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Gets vendor.
     *
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets vendor.
     *
     * @param vendor the vendor
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Gets cpu.
     *
     * @return the cpu
     */
    public Integer getCpu() {
        return cpu;
    }

    /**
     * Sets cpu.
     *
     * @param cpu the cpu
     */
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    /**
     * Gets kernel.
     *
     * @return the kernel
     */
    public String getKernel() {
        return kernel;
    }

    /**
     * Sets kernel.
     *
     * @param kernel the kernel
     */
    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    /**
     * Gets cores.
     *
     * @return the cores
     */
    public Integer getCores() {
        return cores;
    }

    /**
     * Sets cores.
     *
     * @param cores the cores
     */
    public void setCores(Integer cores) {
        this.cores = cores;
    }

    /**
     * Gets architecture.
     *
     * @return the architecture
     */
    public String getArchitecture() {
        return architecture;
    }

    /**
     * Sets architecture.
     *
     * @param architecture the architecture
     */
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    /**
     * Gets os.
     *
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * Sets os.
     *
     * @param os the os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * Gets memory.
     *
     * @return the memory
     */
    public Long getMemory() {
        return memory;
    }

    /**
     * Sets memory.
     *
     * @param memory the memory
     */
    public void setMemory(Long memory) {
        this.memory = memory;
    }

    /**
     * Gets swap.
     *
     * @return the swap
     */
    public Long getSwap() {
        return swap;
    }

    /**
     * Sets swap.
     *
     * @param swap the swap
     */
    public void setSwap(Long swap) {
        this.swap = swap;
    }

    /**
     * Gets disk.
     *
     * @return the disk
     */
    public Long getDisk() {
        return disk;
    }

    /**
     * Sets disk.
     *
     * @param disk the disk
     */
    public void setDisk(Long disk) {
        this.disk = disk;
    }

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets ip address.
     *
     * @param ipAddress the ip address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets gateway.
     *
     * @return the gateway
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * Sets gateway.
     *
     * @param gateway the gateway
     */
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    /**
     * Gets dns.
     *
     * @return the dns
     */
    public String getDns() {
        return dns;
    }

    /**
     * Sets dns.
     *
     * @param dns the dns
     */
    public void setDns(String dns) {
        this.dns = dns;
    }

}
//end of HostDetail.java