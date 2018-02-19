/**
 *
 */
package com.bmstu.coursework.ethernet.manager.model;

/**
 *
 * Instance of this class represents ethernet adapter info pair.
 * Contains old and new info.
 *
 * @author Aleksei Petrov
 *
 */
public class OldNewInfoPair {

	private EthernetAdapterInfo oldInfo;
	private EthernetAdapterInfo newInfo;

	/**
	 *
	 * Constructor.
	 *
	 * @param oldInfo - old adapters info. Can't be <code>null</code>.
	 * @param newInfo - new adapters info. Can't be <code>null</code>.
	 */
	public OldNewInfoPair(EthernetAdapterInfo oldInfo, EthernetAdapterInfo newInfo) {
		this.oldInfo = oldInfo;
		this.newInfo = newInfo;
	}

	/**
	 *
	 * Returns old info.
	 *
	 * @return old info. Can't return <code>null</code>.
	 */
	public EthernetAdapterInfo getOldInfo() {
		return oldInfo;
	}

	/**
	 *
	 * Returns new info.
	 *
	 * @return new info. Can't return <code>null</code>.
	 */
	public EthernetAdapterInfo getNewInfo() {
		return newInfo;
	}
}
