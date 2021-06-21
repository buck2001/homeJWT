package com.pn.home.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Service response.
 *
 * @author Peter Naylor
 * @param <T> The payload type.
 **/
public class ServiceResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer payloadSize;
	private Integer totalSize;
	private boolean success;
	private String errorCode;
	private String errorMessage;
	private String status;

	// initialise the payload
	private List<T> payload = Collections.emptyList();

	public ServiceResponse() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<T> getPayload() {
		return payload;
	}

	public void setPayload(final List<T> payload) {
		this.payload = payload;
	}

	public Integer getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(final Integer payloadSize) {
		this.payloadSize = payloadSize;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(final Integer totalSize) {
		this.totalSize = totalSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
