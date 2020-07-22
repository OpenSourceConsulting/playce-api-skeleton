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
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Entity
public class HostMonitor {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="host_monitor_generator")
    @GenericGenerator(name = "host_monitor_generator", strategy = "native")
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(nullable = false)
    private String type = "5M";

    /**
     * The Host.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @JsonProperty("hostId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Host host;

    /**
     * The Host Name.
     */
    @Transient
    private String hostName;

    /**
     * The Monitor date.
     */
    private Date monitorDate;
    /**
     * The Cpu avg.
     */
    private double cpuAvg;
    /**
     * The Cpu max.
     */
    private double cpuMax;
    /**
     * The Mem total.
     */
    private long memTotal;
    /**
     * The Mem avg.
     */
    private long memAvg;
    /**
     * The Mem max.
     */
    private long memMax;
    /**
     * The Mem usage avg.
     */
    private double memUsageAvg;
    /**
     * The Mem usage max.
     */
    private double memUsageMax;
    /**
     * The Net rx avg.
     */
    private long netRxAvg;
    /**
     * The Net rx max.
     */
    private long netRxMax;
    /**
     * The Net tx avg.
     */
    private long netTxAvg;
    /**
     * The Net tx max.
     */
    private long netTxMax;
    /**
     * The Disk read avg.
     */
    private long diskReadAvg;
    /**
     * The Disk read max.
     */
    private long diskReadMax;
    /**
     * The Disk write avg.
     */
    private long diskWriteAvg;
    /**
     * The Disk write max.
     */
    private long diskWriteMax;
    /**
     * The Disk usage.
     */
    private double diskUsage;

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
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets host name.
     *
     * @return the host name
     */
    public String getHostName() {
        if (host != null) {
            hostName = host.getName();
        }

        return hostName;
    }

    /**
     * Sets host name.
     *
     * @param hostName the host name
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Gets monitor date.
     *
     * @return the monitor date
     */
    public Date getMonitorDate() {
        return monitorDate;
    }

    /**
     * Sets monitor date.
     *
     * @param monitorDate the monitor date
     */
    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    /**
     * Gets cpu avg.
     *
     * @return the cpu avg
     */
    public double getCpuAvg() {
        return cpuAvg;
    }

    /**
     * Sets cpu avg.
     *
     * @param cpuAvg the cpu avg
     */
    public void setCpuAvg(double cpuAvg) {
        this.cpuAvg = cpuAvg;
    }

    /**
     * Gets cpu max.
     *
     * @return the cpu max
     */
    public double getCpuMax() {
        return cpuMax;
    }

    /**
     * Sets cpu max.
     *
     * @param cpuMax the cpu max
     */
    public void setCpuMax(double cpuMax) {
        this.cpuMax = cpuMax;
    }

    /**
     * Gets mem total.
     *
     * @return the mem total
     */
    public long getMemTotal() {
        return memTotal;
    }

    /**
     * Sets mem total.
     *
     * @param memTotal the mem total
     */
    public void setMemTotal(long memTotal) {
        this.memTotal = memTotal;
    }

    /**
     * Gets mem avg.
     *
     * @return the mem avg
     */
    public long getMemAvg() {
        return memAvg;
    }

    /**
     * Sets mem avg.
     *
     * @param memAvg the mem avg
     */
    public void setMemAvg(long memAvg) {
        this.memAvg = memAvg;
    }

    /**
     * Gets mem max.
     *
     * @return the mem max
     */
    public long getMemMax() {
        return memMax;
    }

    /**
     * Sets mem max.
     *
     * @param memMax the mem max
     */
    public void setMemMax(long memMax) {
        this.memMax = memMax;
    }

    /**
     * Gets mem usage avg.
     *
     * @return the mem usage avg
     */
    public double getMemUsageAvg() {
        return memUsageAvg;
    }

    /**
     * Sets mem usage avg.
     *
     * @param memUsageAvg the mem usage avg
     */
    public void setMemUsageAvg(double memUsageAvg) {
        this.memUsageAvg = memUsageAvg;
    }

    /**
     * Gets mem usage max.
     *
     * @return the mem usage max
     */
    public double getMemUsageMax() {
        return memUsageMax;
    }

    /**
     * Sets mem usage max.
     *
     * @param memUsageMax the mem usage max
     */
    public void setMemUsageMax(double memUsageMax) {
        this.memUsageMax = memUsageMax;
    }

    /**
     * Gets net rx avg.
     *
     * @return the net rx avg
     */
    public long getNetRxAvg() {
        return netRxAvg;
    }

    /**
     * Sets net rx avg.
     *
     * @param netRxAvg the net rx avg
     */
    public void setNetRxAvg(long netRxAvg) {
        this.netRxAvg = netRxAvg;
    }

    /**
     * Gets net rx max.
     *
     * @return the net rx max
     */
    public long getNetRxMax() {
        return netRxMax;
    }

    /**
     * Sets net rx max.
     *
     * @param netRxMax the net rx max
     */
    public void setNetRxMax(long netRxMax) {
        this.netRxMax = netRxMax;
    }

    /**
     * Gets net tx avg.
     *
     * @return the net tx avg
     */
    public long getNetTxAvg() {
        return netTxAvg;
    }

    /**
     * Sets net tx avg.
     *
     * @param netTxAvg the net tx avg
     */
    public void setNetTxAvg(long netTxAvg) {
        this.netTxAvg = netTxAvg;
    }

    /**
     * Gets net tx max.
     *
     * @return the net tx max
     */
    public long getNetTxMax() {
        return netTxMax;
    }

    /**
     * Sets net tx max.
     *
     * @param netTxMax the net tx max
     */
    public void setNetTxMax(long netTxMax) {
        this.netTxMax = netTxMax;
    }

    /**
     * Gets disk read avg.
     *
     * @return the disk read avg
     */
    public long getDiskReadAvg() {
        return diskReadAvg;
    }

    /**
     * Sets disk read avg.
     *
     * @param diskReadAvg the disk read avg
     */
    public void setDiskReadAvg(long diskReadAvg) {
        this.diskReadAvg = diskReadAvg;
    }

    /**
     * Gets disk read max.
     *
     * @return the disk read max
     */
    public long getDiskReadMax() {
        return diskReadMax;
    }

    /**
     * Sets disk read max.
     *
     * @param diskReadMax the disk read max
     */
    public void setDiskReadMax(long diskReadMax) {
        this.diskReadMax = diskReadMax;
    }

    /**
     * Gets disk write avg.
     *
     * @return the disk write avg
     */
    public long getDiskWriteAvg() {
        return diskWriteAvg;
    }

    /**
     * Sets disk write avg.
     *
     * @param diskWriteAvg the disk write avg
     */
    public void setDiskWriteAvg(long diskWriteAvg) {
        this.diskWriteAvg = diskWriteAvg;
    }

    /**
     * Gets disk write max.
     *
     * @return the disk write max
     */
    public long getDiskWriteMax() {
        return diskWriteMax;
    }

    /**
     * Sets disk write max.
     *
     * @param diskWriteMax the disk write max
     */
    public void setDiskWriteMax(long diskWriteMax) {
        this.diskWriteMax = diskWriteMax;
    }

    /**
     * Gets disk usage.
     *
     * @return the disk usage
     */
    public double getDiskUsage() {
        return diskUsage;
    }

    /**
     * Sets disk usage.
     *
     * @param diskUsage the disk usage
     */
    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

}
//end of HostMonitor.java