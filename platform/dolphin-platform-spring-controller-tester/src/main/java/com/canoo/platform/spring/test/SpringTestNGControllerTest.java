/*
 * Copyright 2015-2017 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.platform.spring.test;

import com.canoo.dp.impl.platform.core.Assert;
import com.canoo.impl.dp.spring.test.ClientTestFactory;
import com.canoo.impl.dp.spring.test.DolphinPlatformSpringTestBootstrap;
import com.canoo.platform.remoting.client.ClientContext;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.apiguardian.api.API.Status.MAINTAINED;

/**
 * Base class for TestNG based controller tests in Spring. This class can be extended to write custom controller tests.
 *
 * @author Hendrik Ebbers
 * @see ControllerTest
 * @see AbstractTestNGSpringContextTests
 * @see ControllerUnderTest
 */
@SpringBootTest
@ContextConfiguration(classes = DolphinPlatformSpringTestBootstrap.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@API(since = "0.x", status = MAINTAINED)
public abstract class SpringTestNGControllerTest extends AbstractTestNGSpringContextTests implements ControllerTest {

    @Autowired
    private ClientContext clientContext;

    /**
     * Methods that is called before each tests and automatically creates a connection to the server
     */
    @BeforeMethod(alwaysRun = true)
    protected void connectClientContext() {
        try {
            clientContext.connect().get();
        } catch (Exception e) {
            throw new ControllerTestException("Can not connect client context!", e);
        }
    }

    /**
     * Method that is called after each tests and automatically disconnects the connection to the server
     */
    @AfterMethod(alwaysRun = true)
    protected void disconnectClientContext() {
        try {
            clientContext.disconnect().get();
        } catch (Exception e) {
            throw new ControllerTestException("Can not disconnect client context!", e);
        }
    }

    public <T> ControllerUnderTest<T> createController(final String controllerName) {
        Assert.requireNonBlank(controllerName, "controllerName");
        try {
            return ClientTestFactory.createController(clientContext, controllerName);
        } catch (Exception e) {
            throw new ControllerTestException("Can't create controller proxy", e);
        }
    }
}

