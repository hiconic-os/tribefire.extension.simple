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
package tribefire.extension.simple.deployables.service;

import static com.braintribe.testing.junit.assertions.assertj.core.api.Assertions.assertThat;
import static com.braintribe.testing.junit.assertions.assertj.core.api.Assertions.assertThatExecuting;

import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.braintribe.cartridge.common.processing.accessrequest.BasicAccessAwareRequestContext;

import tribefire.extension.simple.model.service.SimpleEchoRequest;
import tribefire.extension.simple.model.service.SimpleEchoResponse;

/**
 * Provides tests for {@link SimpleEchoServiceProcessor}.<br>
 * The main purpose of this class is to demonstrate how to easily write unit tests for a service processor.
 *
 * @author michael.lafite
 */
public class SimpleEchoServiceProcessorTest {

	/**
	 * Creates a new {@link SimpleEchoServiceProcessor} configured with a specific {@link SimpleEchoServiceProcessor#setDelay(Long) delay} and
	 * {@link SimpleEchoServiceProcessor#setEchoCount(Integer) echo count}. The test then first makes sure that the <code>delay</code> setting is
	 * respected, i.e. the processor waits for the specified amount of time. Afterwards the {@link SimpleEchoResponse#getEcho() echo} is checked.
	 */
	@Test
	public void test() {
		long delay = 1000;
		int echoCount = 3;

		SimpleEchoServiceProcessor processor = new SimpleEchoServiceProcessor();
		processor.setDelay(delay);
		processor.setEchoCount(echoCount);

		SimpleEchoRequest request = SimpleEchoRequest.T.create();
		request.setMessage("test message to echo");

		// process request: make sure method call succeeds (i.e. no exception) and that there is the expected delay.
		// afterwards get return value of method, so that we can check the response separately.
		BasicAccessAwareRequestContext<SimpleEchoRequest> requestContext = new BasicAccessAwareRequestContext<>(null, null, null, request);

		SimpleEchoResponse response = assertThatExecuting(() -> processor.process(requestContext)).succeeds().afterMoreThan(delay, ChronoUnit.MILLIS)
				.getReturnValue();

		// check response
		assertThat(response.getEcho()).startsWith("(ECHO)");
		assertThat(response.getEcho()).containsNTimes(request.getMessage(), echoCount);
	}
}
