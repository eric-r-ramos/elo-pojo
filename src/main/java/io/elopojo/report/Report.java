package io.elopojo.report;

import io.elopojo.core.Championship;

/**
 * 
 * Abstract class for report Implementation
 * @author ericramos
 *
 */
public abstract class Report {
	
	Championship championship;
	
	public abstract void print();
	
	/**
	 *
	 * Constructor for report. 
	 * Should pass Championship as a context for report
	 * @param championship
	 */
	public Report(Championship championship){
		this.championship = championship;
	}
	

}
