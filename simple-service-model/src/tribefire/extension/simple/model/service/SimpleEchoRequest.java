// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package tribefire.extension.simple.model.service;

import com.braintribe.model.accessapi.AccessDataRequest;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.AuthorizedRequest;
import com.braintribe.model.service.api.ServiceRequest;

/**
 * The request processed by the respective processor <code>SimpleEchoService</code>. It holds the {@link #getMessage() message} to be echoed.
 *
 * @see SimpleEchoResponse
 *
 * @author michael.lafite
 */
public interface SimpleEchoRequest extends AccessDataRequest, AuthorizedRequest {

	EntityType<SimpleEchoRequest> T = EntityTypes.T(SimpleEchoRequest.class);

	@Override
	EvalContext<? extends SimpleEchoResponse> eval(Evaluator<ServiceRequest> evaluator);

	/**
	 * The message to echo.
	 */
	String getMessage();
	void setMessage(String message);
}
