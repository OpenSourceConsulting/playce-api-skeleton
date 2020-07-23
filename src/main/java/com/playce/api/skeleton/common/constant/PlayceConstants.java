package com.playce.api.skeleton.common.constant;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
public class PlayceConstants {

    /**
     * Instantiates a new Playce constants.
     */
    private PlayceConstants() {
    }

    // Session Cluster
    public static final int HISTORY_CODE_CLUSTER_CREATE             = 262;    // Cluster create
    public static final int HISTORY_CODE_CLUSTER_DELETE             = 263;    // Cluster delete
    public static final int HISTORY_CODE_CLUSTER_UPDATE             = 264;    // Cluster update

    // Domain
    public static final int HISTORY_CODE_DOMAIN_CREATE              = 323;    // Domain create
    public static final int HISTORY_CODE_DOMAIN_DELETE              = 324;    // Domain delete
    public static final int HISTORY_CODE_DOMAIN_UPDATE              = 325;    // Domain update

    // Host
    public static final int HISTORY_CODE_HOST_CREATE                = 390;    // Host create

    // History Status Type
    public static final String HISTORY_STATUS_SUCCESS           = "Success";
    public static final String HISTORY_STATUS_RUNNING           = "Running";
    public static final String HISTORY_STATUS_FAILED            = "Failed";

    // Monitoring Time Type
    public static final String MONITOR_TIME_FIVE_MINUTE         = "5M";
    public static final String MONITOR_TIME_TEN_MINUTE          = "10M";
    public static final String MONITOR_TIME_TWENTY_MINUTE       = "20M";
    public static final String MONITOR_TIME_THIRTY_MINUTE       = "30M";
    public static final String MONITOR_TIME_ONE_HOUR            = "1H";
    public static final String MONITOR_TIME_TWO_HOUR            = "2H";
    public static final String MONITOR_TIME_THREE_HOUR          = "3H";
    public static final String MONITOR_TIME_SIX_HOUR            = "6H";
    public static final String MONITOR_TIME_TWELVE_HOUR         = "12H";
    public static final String MONITOR_TIME_ONE_DAY             = "1D";
    public static final String MONITOR_TIME_THREE_DAY           = "3D";
    public static final String MONITOR_TIME_ONE_WEEK            = "1W";
    public static final String MONITOR_TIME_TWO_WEEK            = "2W";
    public static final String MONITOR_TIME_ONE_MONTH           = "1M";
}
//end of PlayceConstant.java