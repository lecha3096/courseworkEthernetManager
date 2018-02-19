/**
 * Copyright (C) 2017, 1C
 */
package com.bmstu.coursework.ethernet.manager.tests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.bmstu.coursework.ethernet.manager.IEthernetListener;
import com.bmstu.coursework.ethernet.manager.IEthernetManager;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterEvent;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterInfo;

/**
 *
 * Ethernet manager tests.
 *
 * @author Aleksei Petrov
 *
 */
public class MainTests {

	@Test
	public void managerTest() {
		IEthernetManager manager = getService(IEthernetManager.class);
		assertNotNull("Ethernet manager not found", manager);

		Collection<EthernetAdapterInfo> infos = manager.getCurrentAdaptersInfo();
		assertNotNull("Infos is null", infos);
	}

	@Test
	public void clientTest() {
		IEthernetListener listener = getService(IEthernetListener.class);
		listener.adapterChanged(new EthernetAdapterEvent());
	}

	static <T> T getService(Class<T> clazz) {
		Bundle bundle = FrameworkUtil.getBundle(MainTests.class);
		if (bundle != null) {
			ServiceTracker<T, T> st = new ServiceTracker<T, T>(bundle.getBundleContext(), clazz, null);
			st.open();

			try {
				// give the runtime some time to startup
				return st.waitForService(500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
