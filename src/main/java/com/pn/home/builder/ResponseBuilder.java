package com.pn.home.builder;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.pn.home.common.AppException;
import com.pn.home.common.ServiceResponse;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResponseBuilder {
	public static ServiceResponse<?> buildSuccessResponse(List<?> response) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setSuccess(true);
		serviceResponse.setPayloadSize(response.size());
		serviceResponse.setStatus(HttpStatus.OK.toString());
		serviceResponse.setPayload(response);

		return serviceResponse;
	}

	public static ServiceResponse<?> buildErrorResponse(AppException e) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setSuccess(false);
		serviceResponse.setErrorCode(e.getErrorCode());
		serviceResponse.setErrorMessage(e.getLocalizedMessage());

		return serviceResponse;
	}

	public static ServiceResponse<?> buildSuccessResponseForPreferences(List<?> preference) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setSuccess(true);
		serviceResponse.setPayloadSize(preference.size());
		serviceResponse.setStatus(HttpStatus.OK.toString());
		serviceResponse.setPayload(preference);

		return serviceResponse;
	}
}
