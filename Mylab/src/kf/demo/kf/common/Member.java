package kf.common;

import java.util.List;

public class Member  extends BasePojo{

	
	private String id;   //for partitions
	
	private List<String> actions;
	
	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	private String tier;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}
	
}
