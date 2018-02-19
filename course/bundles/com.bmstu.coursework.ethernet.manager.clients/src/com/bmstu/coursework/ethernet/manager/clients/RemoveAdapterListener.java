/**
 *
 */
package com.bmstu.coursework.ethernet.manager.clients;

import org.osgi.service.component.annotations.Component;

import com.bmstu.coursework.ethernet.manager.IEthernetListener;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterEvent;

/**
 *
 * Instance of this class represents remove adapter listener.
 *
 * @author Aleksei Petrov
 *
 */
@Component
public class RemoveAdapterListener implements IEthernetListener {

	@Override
	public void adapterChanged(EthernetAdapterEvent event) {
		if (!event.getRemoved().isEmpty()) {
			System.out.println("Removed: ");
			System.out.println(event.getRemoved());
		}
	}

}
