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
package com.playce.api.skeleton.exception;

/**
 * <pre>
 * An exception which raises when has no permission
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class NoPermissionException extends PlayceException {

    private static final long serialVersionUID = 4610406634943872782L;

    private Integer errorCode;

    /**
     * <pre>
     * Constructor of NoPermissionException with message
     * </pre>
     */
    public NoPermissionException() {
    }

    /**
     * <pre>
     * Constructor of NoPermissionException with message
     * </pre>
     * @param message
     */
    public NoPermissionException(String message) {
        super(message);
    }

    /**
     * <pre>
     * Constructor of NoPermissionException with cause
     * </pre>
     * @param cause
     */
    public NoPermissionException(Throwable cause) {
        super(cause);
    }

    /**
     * <pre>
     * Constructor of NoPermissionException with errorCode & message
     * </pre>
     * @param errorCode
     * @param message
     */
    public NoPermissionException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * <pre>
     * Constructor of NoPermissionException with message and cause
     * </pre>
     * @param message
     * @param cause
     */
    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }
}
//end of NoPermissionException.java