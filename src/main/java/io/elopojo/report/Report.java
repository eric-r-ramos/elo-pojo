package io.elopojo.report;

import io.elopojo.core.Championship;

public abstract class Report {
	
	public Championship championship;
	
	public abstract void print();
	
	public Report(Championship championship){
		this.championship = championship;
	}
	

}
