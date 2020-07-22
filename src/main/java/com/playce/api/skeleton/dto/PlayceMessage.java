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
package com.playce.api.skeleton.dto;

/**
 * <pre>
 * 서버 C,U,D 작업 결과에 대한 JSON Response 및 WebSocket을 이용한 메시지 송수신에 사용하는 공통 메시지 클래스
 * - ex) {"success": true, "msg":"Create sucess"}
 * </pre>
 *
 * @author Sang -cheon Park
 * @version 1.0
 */
public class PlayceMessage {

	/**
	 * The Timestamp.
	 */
	private long timestamp;
	/**
	 * The Message.
	 */
	private String message;
	/**
	 * The Status.
	 */
	private Status status;
	/**
	 * The Code.
	 */
	private Integer code;
	/**
	 * The Data.
	 */
	private Object data;

	/**
	 * Instantiates a new Wasup message.
	 */
	public PlayceMessage() {
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * Instantiates a new Wasup message.
	 *
	 * @param code the code
	 */
	public PlayceMessage(int code) {
		this();
		this.code = code;
	}

	/**
	 * Gets timestamp.
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets timestamp.
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PlayceMessage{" +
				"timestamp=" + timestamp +
				", message='" + message + '\'' +
				", status=" + status +
				", code=" + code +
				", data=" + data +
				'}';
	}
}
//end of PlayceMessage.java