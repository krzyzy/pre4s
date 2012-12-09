package org.krzyzak.pre4s.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ImportResource({ "classpath*:*pre4sContext.xml" })
@ComponentScan({ "org.krzyzak.pre4s.controller" })
public class Pre4STestConfig {
}
