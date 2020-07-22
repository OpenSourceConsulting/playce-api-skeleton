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
 * An exception which raises when communication meets any problem
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class PlayceException extends Exception {

	private static final long serialVersionUID = 4610406634943872782L;

	private Integer errorCode;

	/**
     * <pre>
     * Constructor of PlayceException with message
     * </pre>
     */
	public PlayceException() {
	}

	/**
     * <pre>
     * Constructor of PlayceException with message
     * </pre>
     * @param message
     */
	public PlayceException(String message) {
		super(message);
	}

	/**
     * <pre>
     * Constructor of PlayceException with cause
     * </pre>
     * @param cause
     */
	public PlayceException(Throwable cause) {
		super(cause);
	}

	/**
     * <pre>
     * Constructor of PlayceException with errorCode & message
     * </pre>
     * @param errorCode
     * @param message
     */
	public PlayceException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
     * <pre>
     * Constructor of PlayceException with message and cause
     * </pre>
     * @param message
     * @param cause
     */
	public PlayceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}
}
//end of RoRoException.java