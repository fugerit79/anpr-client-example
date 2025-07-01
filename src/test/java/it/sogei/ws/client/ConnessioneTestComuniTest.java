package it.sogei.ws.client;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.interno.anpr.activator.DispatchHandler;
import it.interno.anpr.config.ConfigHandler;
import it.interno.anpr.config.EnvironmentHandler;
import it.interno.anpr.config.ParamHandler;
import it.interno.anpr.config.WSTypeHandler;

public class ConnessioneTestComuniTest {
	@BeforeClass
	public static void setTrustStore () throws Exception {
		System.setProperty("javax.net.ssl.trustStore", "keystore/cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}

	@Before
	public void resetConfig () {
		ConfigHandler.reload();
	}

	@Test
	public void test1_ConnessioneOnTest() throws Exception {
		ParamHandler param = new ParamHandler();
		param.setEnvironment(EnvironmentHandler.TEST);
		WSTypeHandler wsHandler = new WSTypeHandler();
		wsHandler.setWSFamily(WSTypeHandler.TESTCONN);
		param.setWsType(wsHandler);
		param.setFileRequest("request/TestConn/testConn_TEST.req");
		DispatchHandler dispatch = new DispatchHandler(param);
		assert(dispatch.execute());
	}

	@Test
	public void test4_3002OnTest() throws Exception {
		ParamHandler param = new ParamHandler();
		param.setEnvironment(EnvironmentHandler.TEST);
		WSTypeHandler wsHandler = new WSTypeHandler();
		wsHandler.setWSFamily(WSTypeHandler.WS3002);
		param.setWsType(wsHandler);
		param.setFileRequest("request/3002/3002_888002_TEST.req");
		DispatchHandler dispatch = new DispatchHandler(param);
		assert(dispatch.execute());
	}

	@Test
	public void test_6001OnTest() throws Exception {
		ParamHandler param = new ParamHandler();
		param.setEnvironment(EnvironmentHandler.TEST);
		WSTypeHandler wsHandler = new WSTypeHandler();
		wsHandler.setWSFamily(WSTypeHandler.WS6001);
		param.setWsType(wsHandler);
		param.setFileRequest("request/6001/6001_888013_TEST.req");
		DispatchHandler dispatch = new DispatchHandler(param);
		assert(dispatch.execute());
	}

}
