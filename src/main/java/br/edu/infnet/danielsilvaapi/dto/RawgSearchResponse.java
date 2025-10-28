package br.edu.infnet.danielsilvaapi.dto;

import java.util.List;

public class RawgSearchResponse {
	private List<RawgGameResult> results;

	public List<RawgGameResult> getResults() {
		return results;
	}

	public void setResults(List<RawgGameResult> results) {
		this.results = results;
	}
	
	
}
