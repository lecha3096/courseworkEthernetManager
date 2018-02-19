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
public class ChangeAdapterListener implements IEthernetListener {

	@Override
	public void adapterChanged(EthernetAdapterEvent event) {
		if (!event.getChanged().isEmpty()) {
			System.out.println("Changed: ");
			event.getChanged().stream().forEach(changedPair -> {
				System.out.println("Ethernet adapter\n" + changedPair.getOldInfo() + "\nchanged to\n" + changedPair.getNewInfo());
			});
		}
	}

}
