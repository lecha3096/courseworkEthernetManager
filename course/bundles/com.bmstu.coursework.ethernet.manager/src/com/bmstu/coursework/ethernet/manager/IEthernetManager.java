/**
 * Copyright (C) 2017, 1C
 */
package com.bmstu.coursework.ethernet.manager;

import java.util.Collection;

import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterInfo;

/**
 *
 * Instance of this class sends event to listeners on ethernet adapters changes.
 *
 * @author Aleksei Petrov
 *
 */
public interface IEthernetManager {

	/**
	 *
	 * Returns current ethernet adapters info.
	 *
	 * @return current ethernet adapters info. Can't return <code>null</code>.
	 */
	Collection<EthernetAdapterInfo> getCurrentAdaptersInfo();
}
